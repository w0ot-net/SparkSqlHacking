package io.fabric8.kubernetes.api.model.resource.v1alpha3;

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
import io.fabric8.kubernetes.api.model.KubernetesResource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"config", "constraints", "requests"})
public class DeviceClaim implements Editable, KubernetesResource {
   @JsonProperty("config")
   @JsonInclude(Include.NON_EMPTY)
   private List config = new ArrayList();
   @JsonProperty("constraints")
   @JsonInclude(Include.NON_EMPTY)
   private List constraints = new ArrayList();
   @JsonProperty("requests")
   @JsonInclude(Include.NON_EMPTY)
   private List requests = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public DeviceClaim() {
   }

   public DeviceClaim(List config, List constraints, List requests) {
      this.config = config;
      this.constraints = constraints;
      this.requests = requests;
   }

   @JsonProperty("config")
   @JsonInclude(Include.NON_EMPTY)
   public List getConfig() {
      return this.config;
   }

   @JsonProperty("config")
   public void setConfig(List config) {
      this.config = config;
   }

   @JsonProperty("constraints")
   @JsonInclude(Include.NON_EMPTY)
   public List getConstraints() {
      return this.constraints;
   }

   @JsonProperty("constraints")
   public void setConstraints(List constraints) {
      this.constraints = constraints;
   }

   @JsonProperty("requests")
   @JsonInclude(Include.NON_EMPTY)
   public List getRequests() {
      return this.requests;
   }

   @JsonProperty("requests")
   public void setRequests(List requests) {
      this.requests = requests;
   }

   @JsonIgnore
   public DeviceClaimBuilder edit() {
      return new DeviceClaimBuilder(this);
   }

   @JsonIgnore
   public DeviceClaimBuilder toBuilder() {
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
      List var10000 = this.getConfig();
      return "DeviceClaim(config=" + var10000 + ", constraints=" + this.getConstraints() + ", requests=" + this.getRequests() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DeviceClaim)) {
         return false;
      } else {
         DeviceClaim other = (DeviceClaim)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$config = this.getConfig();
            Object other$config = other.getConfig();
            if (this$config == null) {
               if (other$config != null) {
                  return false;
               }
            } else if (!this$config.equals(other$config)) {
               return false;
            }

            Object this$constraints = this.getConstraints();
            Object other$constraints = other.getConstraints();
            if (this$constraints == null) {
               if (other$constraints != null) {
                  return false;
               }
            } else if (!this$constraints.equals(other$constraints)) {
               return false;
            }

            Object this$requests = this.getRequests();
            Object other$requests = other.getRequests();
            if (this$requests == null) {
               if (other$requests != null) {
                  return false;
               }
            } else if (!this$requests.equals(other$requests)) {
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
      return other instanceof DeviceClaim;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $config = this.getConfig();
      result = result * 59 + ($config == null ? 43 : $config.hashCode());
      Object $constraints = this.getConstraints();
      result = result * 59 + ($constraints == null ? 43 : $constraints.hashCode());
      Object $requests = this.getRequests();
      result = result * 59 + ($requests == null ? 43 : $requests.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
