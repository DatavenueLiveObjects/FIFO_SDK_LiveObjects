package com.orange.lo.sdk.rest.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScopeApplication {

  @JsonProperty("fifos")
  private List<String> fifos = null;
  
  public ScopeApplication withFifos(List<String> fifos) {
    this.fifos = fifos;
    return this;
  }

  public ScopeApplication addFifosItem(String fifosItem) {
    if (this.fifos == null) {
      this.fifos = new ArrayList<>();
    }
    this.fifos.add(fifosItem);
    return this;
  }

  public List<String> getFifos() {
    return fifos;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ScopeApplication scopeApplication = (ScopeApplication) o;
    return Objects.equals(this.fifos, scopeApplication.fifos);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(fifos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScopeApplication {\n");
    
    sb.append("    fifos: ").append(toIndentedString(fifos)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}