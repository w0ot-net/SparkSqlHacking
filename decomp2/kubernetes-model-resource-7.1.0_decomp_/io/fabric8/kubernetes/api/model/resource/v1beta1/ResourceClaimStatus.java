package io.fabric8.kubernetes.api.model.resource.v1beta1;

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
@JsonPropertyOrder({"allocation", "devices", "reservedFor"})
public class ResourceClaimStatus implements Editable, KubernetesResource {
   @JsonProperty("allocation")
   private AllocationResult allocation;
   @JsonProperty("devices")
   @JsonInclude(Include.NON_EMPTY)
   private List devices = new ArrayList();
   @JsonProperty("reservedFor")
   @JsonInclude(Include.NON_EMPTY)
   private List reservedFor = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourceClaimStatus() {
   }

   public ResourceClaimStatus(AllocationResult allocation, List devices, List reservedFor) {
      this.allocation = allocation;
      this.devices = devices;
      this.reservedFor = reservedFor;
   }

   @JsonProperty("allocation")
   public AllocationResult getAllocation() {
      return this.allocation;
   }

   @JsonProperty("allocation")
   public void setAllocation(AllocationResult allocation) {
      this.allocation = allocation;
   }

   @JsonProperty("devices")
   @JsonInclude(Include.NON_EMPTY)
   public List getDevices() {
      return this.devices;
   }

   @JsonProperty("devices")
   public void setDevices(List devices) {
      this.devices = devices;
   }

   @JsonProperty("reservedFor")
   @JsonInclude(Include.NON_EMPTY)
   public List getReservedFor() {
      return this.reservedFor;
   }

   @JsonProperty("reservedFor")
   public void setReservedFor(List reservedFor) {
      this.reservedFor = reservedFor;
   }

   @JsonIgnore
   public ResourceClaimStatusBuilder edit() {
      return new ResourceClaimStatusBuilder(this);
   }

   @JsonIgnore
   public ResourceClaimStatusBuilder toBuilder() {
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
      AllocationResult var10000 = this.getAllocation();
      return "ResourceClaimStatus(allocation=" + var10000 + ", devices=" + this.getDevices() + ", reservedFor=" + this.getReservedFor() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourceClaimStatus)) {
         return false;
      } else {
         ResourceClaimStatus other = (ResourceClaimStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$allocation = this.getAllocation();
            Object other$allocation = other.getAllocation();
            if (this$allocation == null) {
               if (other$allocation != null) {
                  return false;
               }
            } else if (!this$allocation.equals(other$allocation)) {
               return false;
            }

            Object this$devices = this.getDevices();
            Object other$devices = other.getDevices();
            if (this$devices == null) {
               if (other$devices != null) {
                  return false;
               }
            } else if (!this$devices.equals(other$devices)) {
               return false;
            }

            Object this$reservedFor = this.getReservedFor();
            Object other$reservedFor = other.getReservedFor();
            if (this$reservedFor == null) {
               if (other$reservedFor != null) {
                  return false;
               }
            } else if (!this$reservedFor.equals(other$reservedFor)) {
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
      return other instanceof ResourceClaimStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $allocation = this.getAllocation();
      result = result * 59 + ($allocation == null ? 43 : $allocation.hashCode());
      Object $devices = this.getDevices();
      result = result * 59 + ($devices == null ? 43 : $devices.hashCode());
      Object $reservedFor = this.getReservedFor();
      result = result * 59 + ($reservedFor == null ? 43 : $reservedFor.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
