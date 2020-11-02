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

## Technologies
* Java 8
* Spring Web 5.2.9.RELEASE
* Eclipse Paho Client Mqttv3 1.2.5
* Jackson Databind 2.11.3

## Requirements
In order to use the x-connector-library you need to have:
* **Live Objects account with API key** (API key generation is described in the [user guide](https://liveobjects.orange-business.com/cms/app/uploads/EN_User-guide-Live-Objects-4.pdf#%5B%7B%22num%22%3A190%2C%22gen%22%3A0%7D%2C%7B%22name%22%3A%22XYZ%22%7D%2C68%2C574%2C0%5D)),
* **Java SE Development Kit 8 installed**
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

#### Subscribing and retrieving data from FIFO queues
`LOApiClient` allows retrieve data from fifo queues.

In order to receive all messages from your queue, you should implement your own message handling class implementing `DataManagementMqttCallback`:
```java
public class MyDataManagementMqttCallback implements DataManagementMqttCallback {
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
        .dataManagementMqttCallback(new MyDataManagementMqttCallback())
        .build();
LOApiClient client = new LOApiClient(parameters);
```
To start retrieving message, you should use subscribe method:
```java
DataManagementMqtt dataManagementMqtt = client.getDataManagementMqtt();
dataManagementMqtt.subscribe();
```

To stop retrieving messages, you should use disconnect method:
```java
dataManagementMqtt.disconnect();
```