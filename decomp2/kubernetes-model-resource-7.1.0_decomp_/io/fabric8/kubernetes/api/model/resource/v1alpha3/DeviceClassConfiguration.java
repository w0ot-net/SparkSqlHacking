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
@JsonPropertyOrder({"opaque"})
public class DeviceClassConfiguration implements Editable, KubernetesResource {
   @JsonProperty("opaque")
   private OpaqueDeviceConfiguration opaque;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public DeviceClassConfiguration() {
   }

   public DeviceClassConfiguration(OpaqueDeviceConfiguration opaque) {
      this.opaque = opaque;
   }

   @JsonProperty("opaque")
   public OpaqueDeviceConfiguration getOpaque() {
      return this.opaque;
   }

   @JsonProperty("opaque")
   public void setOpaque(OpaqueDeviceConfiguration opaque) {
      this.opaque = opaque;
   }

   @JsonIgnore
   public DeviceClassConfigurationBuilder edit() {
      return new DeviceClassConfigurationBuilder(this);
   }

   @JsonIgnore
   public DeviceClassConfigurationBuilder toBuilder() {
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
      OpaqueDeviceConfiguration var10000 = this.getOpaque();
      return "DeviceClassConfiguration(opaque=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DeviceClassConfiguration)) {
         return false;
      } else {
         DeviceClassConfiguration other = (DeviceClassConfiguration)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$opaque = this.getOpaque();
            Object other$opaque = other.getOpaque();
            if (this$opaque == null) {
               if (other$opaque != null) {
                  return false;
               }
            } else if (!this$opaque.equals(other$opaque)) {
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
      return other instanceof DeviceClassConfiguration;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $opaque = this.getOpaque();
      result = result * 59 + ($opaque == null ? 43 : $opaque.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
