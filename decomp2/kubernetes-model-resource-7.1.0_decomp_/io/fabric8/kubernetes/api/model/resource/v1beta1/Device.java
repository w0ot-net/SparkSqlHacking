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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"basic", "name"})
public class Device implements Editable, KubernetesResource {
   @JsonProperty("basic")
   private BasicDevice basic;
   @JsonProperty("name")
   private String name;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Device() {
   }

   public Device(BasicDevice basic, String name) {
      this.basic = basic;
      this.name = name;
   }

   @JsonProperty("basic")
   public BasicDevice getBasic() {
      return this.basic;
   }

   @JsonProperty("basic")
   public void setBasic(BasicDevice basic) {
      this.basic = basic;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonIgnore
   public DeviceBuilder edit() {
      return new DeviceBuilder(this);
   }

   @JsonIgnore
   public DeviceBuilder toBuilder() {
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
      BasicDevice var10000 = this.getBasic();
      return "Device(basic=" + var10000 + ", name=" + this.getName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Device)) {
         return false;
      } else {
         Device other = (Device)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$basic = this.getBasic();
            Object other$basic = other.getBasic();
            if (this$basic == null) {
               if (other$basic != null) {
                  return false;
               }
            } else if (!this$basic.equals(other$basic)) {
               return false;
            }

            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
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
      return other instanceof Device;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $basic = this.getBasic();
      result = result * 59 + ($basic == null ? 43 : $basic.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
