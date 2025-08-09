package io.fabric8.kubernetes.api.model;

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
import io.fabric8.kubernetes.internal.KubernetesDeserializer;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"object", "type"})
public class WatchEvent implements Editable, KubernetesResource {
   @JsonProperty("object")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   private Object object;
   @JsonProperty("type")
   private String type;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public WatchEvent() {
   }

   public WatchEvent(Object object, String type) {
      this.object = object;
      this.type = type;
   }

   @JsonProperty("object")
   public Object getObject() {
      return this.object;
   }

   @JsonProperty("object")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   public void setObject(Object object) {
      this.object = object;
   }

   @JsonProperty("type")
   public String getType() {
      return this.type;
   }

   @JsonProperty("type")
   public void setType(String type) {
      this.type = type;
   }

   @JsonIgnore
   public WatchEventBuilder edit() {
      return new WatchEventBuilder(this);
   }

   @JsonIgnore
   public WatchEventBuilder toBuilder() {
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
      Object var10000 = this.getObject();
      return "WatchEvent(object=" + var10000 + ", type=" + this.getType() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof WatchEvent)) {
         return false;
      } else {
         WatchEvent other = (WatchEvent)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$object = this.getObject();
            Object other$object = other.getObject();
            if (this$object == null) {
               if (other$object != null) {
                  return false;
               }
            } else if (!this$object.equals(other$object)) {
               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
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
      return other instanceof WatchEvent;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $object = this.getObject();
      result = result * 59 + ($object == null ? 43 : $object.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
