## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Requirements](#requirements)
* [Build](#build)
* [Examples](#examples)

## General info
This repository contains everything you need to create a connection between your application and Live Objects. This project is intended for Live Objects users who want to explore integration patterns of device and message data synchronization. When you want to download device data and messages from Live Objects to your application, you can use this library.

Main features are:
* retrieving data about devices
* retrieving data about groups
* subscribe and retrieving data from FIFO queues

## Technologies and libraries
* Java 17
* Spring Web 6.1.4
* Eclipse Paho Client Mqttv3 1.2.5
* Jackson Databind 2.16.1
* Apache HttpClient 5.3.1
* SLF4J API Module 2.0.12

## Requirements
In order to use the x-connector-library you need to have:
* **Live Objects account with API key** (API key generation is described in the [user guide](https://liveobjects.orange-business.com/cms/app/uploads/EN_User-guide-Live-Objects-4.pdf#%5B%7B%22num%22%3A190%2C%22gen%22%3A0%7D%2C%7B%22name%22%3A%22XYZ%22%7D%2C68%2C574%2C0%5D)),
* **Java SE Development Kit 17 installed**
* **Apache Maven installed**

## Build
If you want to build library, please first clone this repository, then:  
```
mvn clean package
```
The jar file built in this way should be attached to the project as a library. 

## Examples

#### Creating an LOApiClient
To create an `LOApiClient` instance and create a connection to Live Objects, you must create an `LOApiClientParameters` instance and pass it to `LOApiClient` constructor:
```java
LOApiClientParameters parameters = LOApiClientParameters.builder()
        .apiKey("abcDEfgH123I")
        .build();
LOApiClient client = new LOApiClient(parameters);
```
Note that an API key is required. If you do not set the hostname, the default one will be used (liveobjects.orange-business.com).

#### Retrieving data about devices
`LOApiClient` allows retrieve data about a single device or list of devices. To retrieve the data about devices from Live Objects, you can use the sample code:

Getting device inventory client:
```java
DeviceManagement deviceManagement = client.getDeviceManagement();
Inventory inventory = deviceManagement.getInventory();
```
Retrieving data of a single device:
```java
Device device = inventory.getDevice("urn:lo:nsid:mqtt:device-id");
```
Retrieving data about the list of devices:
```java
GetDevicesFilter getDevicesFilter = new GetDevicesFilter().withOffset(0)
        .withLimit(10);
List<Device> devices = inventory.getDevices(getDevicesFilter)
```

#### Retrieving data about groups
`LOApiClient` allows retrieve data about a single group or list of group. To retrieve the data about groups from Live Objects, you can use the sample code:

Getting groups client:
```java
DeviceManagement deviceManagement = client.getDeviceManagement();
Groups groups = deviceManagement.getGroups();
```
Retrieving data of a single group:
```java
Group root = groups.getGroup("root");
```
Retrieving data about the list of groups:
```java
GetGroupsFilter getGroupsFilter = new GetGroupsFilter().withOffset(0)
        .withLimit(10);
List<Group> groupsList = groups.getGroups(getGroupsFilter);
```

#### Retrieving data about commands
`LOApiClient` allows retrieve data about a single command or list of commands. To retrieve the data about commands from Live Objects, you can use the sample code:

Getting commands client:
```java
DeviceManagement deviceManagement = client.getDeviceManagement();
Commands commands = deviceManagement.getCommands();
```
Retrieving data of a single command:
```java
Command command = commands.getCommand("commandId");
```
Retrieving data about the list of commands:
```java
GetCommandsFilter getCommandsFilter = new GetCommandsFilter().withOffset(0)
        .withLimit(20);
List<Command> commands = commands.getCommands("deviceId", getCommandsFilter);
```

#### Subscribing and retrieving data from FIFO queues
`LOApiClient` allows retrieve data from fifo queues.

In order to receive all messages from your queue, you should implement your own message handling class implementing `DataManagementMqttCallback`:
```java
public class MyDataManagementFifoCallback implements DataManagementFifoCallback {
    @Override
    public void onMessage(String message) {

    }
}
```
Then in the configuration you should set the list of queues and use the `DataManagementMqttCallback` implementation:
```java
List<String> queuesList = Arrays.asList("queue-1", "queue-2", "queue-n");
LOApiClientParameters parameters = LOApiClientParameters.builder()
        .apiKey("abcDEfgH123I")
        .topics(queuesList)
        .dataManagementFifoCallback(new MyDataManagementFifoCallback())
        .build();
LOApiClient client = new LOApiClient(parameters);
```
To start retrieving message, you should use subscribe method:
```java
DataManagementFifo dataManagementFifo = client.getDataManagementFifo);
dataManagementFifo.connectAndSubscribe();
```

To stop retrieving messages, you should use disconnect method:
```java
dataManagementFifo.disconnect();
```

#### External connector mode connection
`LOApiClient` allows to create the external connector mode connection with Live Objects.

You can use the sample code to open the connection:
```java
DataManagementExtConnector dataManagementExtConnector = client.getDataManagementExtConnector();
dataManagementExtConnector.connect();
```

NodeStatus publication allows to set the ONLINE/OFFLINE status of the device and its capacity to receive or not command requests. To send the NodeStatus to Live Objects, you can use the sample code:
```
NodeStatus nodeStatus = new NodeStatus();
nodeStatus.setStatus(Status.ONLINE);
nodeStatus.setCapabilities(new NodeStatus.Capabilities(true));
dataManagementExtConnector.sendStatus(exConnectorNodeId, nodeStatus);
```

DataMessage publication allows to send a DataMessage on behalf of a specific device. To send DataMessage to Live Objects, you can use the sample code:
```
Value payload = new Value("payload value");
DataMessage dataMessage = new DataMessage();
dataMessage.setValue(payload);
dataManagementExtConnector.sendMessage(exConnectorNodeId, dataMessage);
```
The data messages sent to the Live Objects platform can be encoded in a customer specific format. For instance, the payload may be a string containing an hexadecimal value or a csv value. In order to use the decoding capability of LiveObjects, a DataMessage must contains additional `value.payload` and `metadata.encoding` fields. To send encoded DataMessage to Live Objects, you can use the sample code:
```
Value value = new Value("15;25");
Metadata metadata = new Metadata("test_csv");
DataMessage dataMessage = new DataMessage();
dataMessage.setValue(value);
dataMessage.setMetadata(metadata);
dataManagementExtConnector.sendMessage(exConnectorNodeId, dataMessage);
```
For more information on decoding, see the [user guide](https://liveobjects.orange-business.com/doc/html/lo_manual_v2.html#DEC).

In order to receive all command requests targeting your devices, you should implement your own message handling class implementing `DataManagementExtConnectorCommandCallback`:
```
public class MyDataManagementExtConnectorCommandCallback implements DataManagementExtConnectorCommandCallback {
    @Override
    public Object onCommandRequest(CommandRequest commandRequest) {
        return null;
    }
}
```
If acknowledgement mode isn't set to `NONE`, the object returned by `onMessage` method will be used as value of response field inside command response. In this case the command response will be published automatically. If you do not wish to publish the response here, return `null` and the response will not be published.  
If you want to publish the command response yourself, you can use the following code:

```
CommandResponse commandResponse = new CommandResponse(commandRequest.getId(), commandRequest.getNodeId());
commandResponse.setResponse(yourResponse);
dataManagementExtConnector.sendCommandResponse(commandResponse);
```

Use that prepared `DataManagementExtConnectorCommandCallback` to create an `LOApiClient`:
```
LOApiClientParameters parameters = LOApiClientParameters.builder()
        .apiKey("abcDEfgH123I")
        .dataManagementExtConnectorCommandCallback(new MyDataManagementExtConnectorCommandCallback())
        .build();
LOApiClient client = new LOApiClient(parameters);
```

To close the connection, you should use disconnect method:
```
dataManagementExtConnector.disconnect();
```