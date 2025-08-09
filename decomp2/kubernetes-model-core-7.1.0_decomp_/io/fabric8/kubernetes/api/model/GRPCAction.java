package io.fabric8.kubernetes.api.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.builder.Editable;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"port", "service"})
public class GRPCAction implements Editable, KubernetesResource {
   @JsonProperty("port")
   private Integer port;
   @JsonProperty("service")
   private String service;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public GRPCAction() {
   }

   public GRPCAction(Integer port, String service) {
      this.port = port;
      this.service = service;
   }

   @JsonProperty("port")
   public Integer getPort() {
      return this.port;
   }

   @JsonProperty("port")
   public void setPort(Integer port) {
      this.port = port;
   }

   @JsonProperty("service")
   public String getService() {
      return this.service;
   }

   @JsonProperty("service")
   public void setService(String service) {
      this.service = service;
   }

   @JsonIgnore
   public GRPCActionBuilder edit() {
      return new GRPCActionBuilder(this);
   }

   @JsonIgnore
   public GRPCActionBuilder toBuilder() {
      return this.edit();
   }

   @JsonAnyGetter
   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   @JsonAnySetter
   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }

   public void setAdditionalProperties(Map additionalProperties) {
      this.additionalProperties = additionalProperties;
   }

   @Generated
   public String toString() {
      Integer var10000 = this.getPort();
      return "GRPCAction(port=" + var10000 + ", service=" + this.getService() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GRPCAction)) {
         return false;
      } else {
         GRPCAction other = (GRPCAction)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$port = this.getPort();
            Object other$port = other.getPort();
            if (this$port == null) {
               if (other$port != null) {
                  return false;
               }
            } else if (!this$port.equals(other$port)) {
               return false;
            }

            Object this$service = this.getService();
            Object other$service = other.getService();
            if (this$service == null) {
               if (other$service != null) {
                  return false;
               }
            } else if (!this$service.equals(other$service)) {
               return false;
            }

            Object this$additionalProperties = this.getAdditionalProperties();
            Object other$additionalProperties = other.getAdditionalProperties();
            if (this$additionalProperties == null) {
               if (other$additionalProperties != null) {
                  return false;
               }
            } else if (!this$additionalProperties.equals(other$additionalProperties)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object other) {
      return other instanceof GRPCAction;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $port = this.getPort();
      result = result * 59 + ($port == null ? 43 : $port.hashCode());
      Object $service = this.getService();
      result = result * 59 + ($service == null ? 43 : $service.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
