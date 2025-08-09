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
@JsonPropertyOrder({"cel"})
public class DeviceSelector implements Editable, KubernetesResource {
   @JsonProperty("cel")
   private CELDeviceSelector cel;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public DeviceSelector() {
   }

   public DeviceSelector(CELDeviceSelector cel) {
      this.cel = cel;
   }

   @JsonProperty("cel")
   public CELDeviceSelector getCel() {
      return this.cel;
   }

   @JsonProperty("cel")
   public void setCel(CELDeviceSelector cel) {
      this.cel = cel;
   }

   @JsonIgnore
   public DeviceSelectorBuilder edit() {
      return new DeviceSelectorBuilder(this);
   }

   @JsonIgnore
   public DeviceSelectorBuilder toBuilder() {
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
      CELDeviceSelector var10000 = this.getCel();
      return "DeviceSelector(cel=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DeviceSelector)) {
         return false;
      } else {
         DeviceSelector other = (DeviceSelector)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$cel = this.getCel();
            Object other$cel = other.getCel();
            if (this$cel == null) {
               if (other$cel != null) {
                  return false;
               }
            } else if (!this$cel.equals(other$cel)) {
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
      return other instanceof DeviceSelector;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $cel = this.getCel();
      result = result * 59 + ($cel == null ? 43 : $cel.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
