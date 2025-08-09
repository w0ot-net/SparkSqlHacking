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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"devices"})
public class ResourceClaimSpec implements Editable, KubernetesResource {
   @JsonProperty("devices")
   private DeviceClaim devices;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourceClaimSpec() {
   }

   public ResourceClaimSpec(DeviceClaim devices) {
      this.devices = devices;
   }

   @JsonProperty("devices")
   public DeviceClaim getDevices() {
      return this.devices;
   }

   @JsonProperty("devices")
   public void setDevices(DeviceClaim devices) {
      this.devices = devices;
   }

   @JsonIgnore
   public ResourceClaimSpecBuilder edit() {
      return new ResourceClaimSpecBuilder(this);
   }

   @JsonIgnore
   public ResourceClaimSpecBuilder toBuilder() {
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
      DeviceClaim var10000 = this.getDevices();
      return "ResourceClaimSpec(devices=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourceClaimSpec)) {
         return false;
      } else {
         ResourceClaimSpec other = (ResourceClaimSpec)o;
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
      return other instanceof ResourceClaimSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $devices = this.getDevices();
      result = result * 59 + ($devices == null ? 43 : $devices.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
