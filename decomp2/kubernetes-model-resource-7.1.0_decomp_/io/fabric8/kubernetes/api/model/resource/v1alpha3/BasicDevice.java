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
import io.fabric8.kubernetes.api.model.Quantity;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"attributes", "capacity"})
public class BasicDevice implements Editable, KubernetesResource {
   @JsonProperty("attributes")
   @JsonInclude(Include.NON_EMPTY)
   private Map attributes = new LinkedHashMap();
   @JsonProperty("capacity")
   @JsonInclude(Include.NON_EMPTY)
   private Map capacity = new LinkedHashMap();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public BasicDevice() {
   }

   public BasicDevice(Map attributes, Map capacity) {
      this.attributes = attributes;
      this.capacity = capacity;
   }

   @JsonProperty("attributes")
   @JsonInclude(Include.NON_EMPTY)
   public Map getAttributes() {
      return this.attributes;
   }

   @JsonProperty("attributes")
   public void setAttributes(Map attributes) {
      this.attributes = attributes;
   }

   @JsonProperty("capacity")
   @JsonInclude(Include.NON_EMPTY)
   public Map getCapacity() {
      return this.capacity;
   }

   @JsonProperty("capacity")
   public void setCapacity(Map capacity) {
      this.capacity = capacity;
   }

   @JsonIgnore
   public BasicDeviceBuilder edit() {
      return new BasicDeviceBuilder(this);
   }

   @JsonIgnore
   public BasicDeviceBuilder toBuilder() {
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
      Map var10000 = this.getAttributes();
      return "BasicDevice(attributes=" + var10000 + ", capacity=" + this.getCapacity() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof BasicDevice)) {
         return false;
      } else {
         BasicDevice other = (BasicDevice)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$attributes = this.getAttributes();
            Object other$attributes = other.getAttributes();
            if (this$attributes == null) {
               if (other$attributes != null) {
                  return false;
               }
            } else if (!this$attributes.equals(other$attributes)) {
               return false;
            }

            Object this$capacity = this.getCapacity();
            Object other$capacity = other.getCapacity();
            if (this$capacity == null) {
               if (other$capacity != null) {
                  return false;
               }
            } else if (!this$capacity.equals(other$capacity)) {
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
      return other instanceof BasicDevice;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $attributes = this.getAttributes();
      result = result * 59 + ($attributes == null ? 43 : $attributes.hashCode());
      Object $capacity = this.getCapacity();
      result = result * 59 + ($capacity == null ? 43 : $capacity.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
