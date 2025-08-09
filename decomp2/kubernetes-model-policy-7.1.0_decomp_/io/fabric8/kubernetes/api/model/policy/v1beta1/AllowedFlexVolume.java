package io.fabric8.kubernetes.api.model.policy.v1beta1;

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
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "driver"})
public class AllowedFlexVolume implements Editable, KubernetesResource {
   @JsonProperty("driver")
   private String driver;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public AllowedFlexVolume() {
   }

   public AllowedFlexVolume(String driver) {
      this.driver = driver;
   }

   @JsonProperty("driver")
   public String getDriver() {
      return this.driver;
   }

   @JsonProperty("driver")
   public void setDriver(String driver) {
      this.driver = driver;
   }

   @JsonIgnore
   public AllowedFlexVolumeBuilder edit() {
      return new AllowedFlexVolumeBuilder(this);
   }

   @JsonIgnore
   public AllowedFlexVolumeBuilder toBuilder() {
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

   @Generated
   public String toString() {
      String var10000 = this.getDriver();
      return "AllowedFlexVolume(driver=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AllowedFlexVolume)) {
         return false;
      } else {
         AllowedFlexVolume other = (AllowedFlexVolume)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$driver = this.getDriver();
            Object other$driver = other.getDriver();
            if (this$driver == null) {
               if (other$driver != null) {
                  return false;
               }
            } else if (!this$driver.equals(other$driver)) {
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
      return other instanceof AllowedFlexVolume;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $driver = this.getDriver();
      result = result * 59 + ($driver == null ? 43 : $driver.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }

   @JsonIgnore
   @Generated
   public void setAdditionalProperties(Map additionalProperties) {
      this.additionalProperties = additionalProperties;
   }
}
