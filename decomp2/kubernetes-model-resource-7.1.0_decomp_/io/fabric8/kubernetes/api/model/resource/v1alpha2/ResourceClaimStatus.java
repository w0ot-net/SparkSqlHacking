package io.fabric8.kubernetes.api.model.resource.v1alpha2;

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
@JsonPropertyOrder({"allocation", "deallocationRequested", "driverName", "reservedFor"})
public class ResourceClaimStatus implements Editable, KubernetesResource {
   @JsonProperty("allocation")
   private AllocationResult allocation;
   @JsonProperty("deallocationRequested")
   private Boolean deallocationRequested;
   @JsonProperty("driverName")
   private String driverName;
   @JsonProperty("reservedFor")
   @JsonInclude(Include.NON_EMPTY)
   private List reservedFor = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourceClaimStatus() {
   }

   public ResourceClaimStatus(AllocationResult allocation, Boolean deallocationRequested, String driverName, List reservedFor) {
      this.allocation = allocation;
      this.deallocationRequested = deallocationRequested;
      this.driverName = driverName;
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

   @JsonProperty("deallocationRequested")
   public Boolean getDeallocationRequested() {
      return this.deallocationRequested;
   }

   @JsonProperty("deallocationRequested")
   public void setDeallocationRequested(Boolean deallocationRequested) {
      this.deallocationRequested = deallocationRequested;
   }

   @JsonProperty("driverName")
   public String getDriverName() {
      return this.driverName;
   }

   @JsonProperty("driverName")
   public void setDriverName(String driverName) {
      this.driverName = driverName;
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
      return "ResourceClaimStatus(allocation=" + var10000 + ", deallocationRequested=" + this.getDeallocationRequested() + ", driverName=" + this.getDriverName() + ", reservedFor=" + this.getReservedFor() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
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
            Object this$deallocationRequested = this.getDeallocationRequested();
            Object other$deallocationRequested = other.getDeallocationRequested();
            if (this$deallocationRequested == null) {
               if (other$deallocationRequested != null) {
                  return false;
               }
            } else if (!this$deallocationRequested.equals(other$deallocationRequested)) {
               return false;
            }

            Object this$allocation = this.getAllocation();
            Object other$allocation = other.getAllocation();
            if (this$allocation == null) {
               if (other$allocation != null) {
                  return false;
               }
            } else if (!this$allocation.equals(other$allocation)) {
               return false;
            }

            Object this$driverName = this.getDriverName();
            Object other$driverName = other.getDriverName();
            if (this$driverName == null) {
               if (other$driverName != null) {
                  return false;
               }
            } else if (!this$driverName.equals(other$driverName)) {
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
      Object $deallocationRequested = this.getDeallocationRequested();
      result = result * 59 + ($deallocationRequested == null ? 43 : $deallocationRequested.hashCode());
      Object $allocation = this.getAllocation();
      result = result * 59 + ($allocation == null ? 43 : $allocation.hashCode());
      Object $driverName = this.getDriverName();
      result = result * 59 + ($driverName == null ? 43 : $driverName.hashCode());
      Object $reservedFor = this.getReservedFor();
      result = result * 59 + ($reservedFor == null ? 43 : $reservedFor.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
