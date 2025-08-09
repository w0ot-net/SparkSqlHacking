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
import io.fabric8.kubernetes.api.model.NodeSelector;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"devices", "nodeSelector"})
public class AllocationResult implements Editable, KubernetesResource {
   @JsonProperty("devices")
   private DeviceAllocationResult devices;
   @JsonProperty("nodeSelector")
   private NodeSelector nodeSelector;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public AllocationResult() {
   }

   public AllocationResult(DeviceAllocationResult devices, NodeSelector nodeSelector) {
      this.devices = devices;
      this.nodeSelector = nodeSelector;
   }

   @JsonProperty("devices")
   public DeviceAllocationResult getDevices() {
      return this.devices;
   }

   @JsonProperty("devices")
   public void setDevices(DeviceAllocationResult devices) {
      this.devices = devices;
   }

   @JsonProperty("nodeSelector")
   public NodeSelector getNodeSelector() {
      return this.nodeSelector;
   }

   @JsonProperty("nodeSelector")
   public void setNodeSelector(NodeSelector nodeSelector) {
      this.nodeSelector = nodeSelector;
   }

   @JsonIgnore
   public AllocationResultBuilder edit() {
      return new AllocationResultBuilder(this);
   }

   @JsonIgnore
   public AllocationResultBuilder toBuilder() {
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
      DeviceAllocationResult var10000 = this.getDevices();
      return "AllocationResult(devices=" + var10000 + ", nodeSelector=" + this.getNodeSelector() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AllocationResult)) {
         return false;
      } else {
         AllocationResult other = (AllocationResult)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$devices = this.getDevices();
            Object other$devices = other.getDevices();
            if (this$devices == null) {
               if (other$devices != null) {
                  return false;
               }
            } else if (!this$devices.equals(other$devices)) {
               return false;
            }

            Object this$nodeSelector = this.getNodeSelector();
            Object other$nodeSelector = other.getNodeSelector();
            if (this$nodeSelector == null) {
               if (other$nodeSelector != null) {
                  return false;
               }
            } else if (!this$nodeSelector.equals(other$nodeSelector)) {
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
      return other instanceof AllocationResult;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $devices = this.getDevices();
      result = result * 59 + ($devices == null ? 43 : $devices.hashCode());
      Object $nodeSelector = this.getNodeSelector();
      result = result * 59 + ($nodeSelector == null ? 43 : $nodeSelector.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
