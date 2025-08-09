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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"effect", "key", "timeAdded", "value"})
public class Taint implements Editable, KubernetesResource {
   @JsonProperty("effect")
   private String effect;
   @JsonProperty("key")
   private String key;
   @JsonProperty("timeAdded")
   private String timeAdded;
   @JsonProperty("value")
   private String value;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Taint() {
   }

   public Taint(String effect, String key, String timeAdded, String value) {
      this.effect = effect;
      this.key = key;
      this.timeAdded = timeAdded;
      this.value = value;
   }

   @JsonProperty("effect")
   public String getEffect() {
      return this.effect;
   }

   @JsonProperty("effect")
   public void setEffect(String effect) {
      this.effect = effect;
   }

   @JsonProperty("key")
   public String getKey() {
      return this.key;
   }

   @JsonProperty("key")
   public void setKey(String key) {
      this.key = key;
   }

   @JsonProperty("timeAdded")
   public String getTimeAdded() {
      return this.timeAdded;
   }

   @JsonProperty("timeAdded")
   public void setTimeAdded(String timeAdded) {
      this.timeAdded = timeAdded;
   }

   @JsonProperty("value")
   public String getValue() {
      return this.value;
   }

   @JsonProperty("value")
   public void setValue(String value) {
      this.value = value;
   }

   @JsonIgnore
   public TaintBuilder edit() {
      return new TaintBuilder(this);
   }

   @JsonIgnore
   public TaintBuilder toBuilder() {
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
      String var10000 = this.getEffect();
      return "Taint(effect=" + var10000 + ", key=" + this.getKey() + ", timeAdded=" + this.getTimeAdded() + ", value=" + this.getValue() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Taint)) {
         return false;
      } else {
         Taint other = (Taint)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$effect = this.getEffect();
            Object other$effect = other.getEffect();
            if (this$effect == null) {
               if (other$effect != null) {
                  return false;
               }
            } else if (!this$effect.equals(other$effect)) {
               return false;
            }

            Object this$key = this.getKey();
            Object other$key = other.getKey();
            if (this$key == null) {
               if (other$key != null) {
                  return false;
               }
            } else if (!this$key.equals(other$key)) {
               return false;
            }

            Object this$timeAdded = this.getTimeAdded();
            Object other$timeAdded = other.getTimeAdded();
            if (this$timeAdded == null) {
               if (other$timeAdded != null) {
                  return false;
               }
            } else if (!this$timeAdded.equals(other$timeAdded)) {
               return false;
            }

            Object this$value = this.getValue();
            Object other$value = other.getValue();
            if (this$value == null) {
               if (other$value != null) {
                  return false;
               }
            } else if (!this$value.equals(other$value)) {
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
      return other instanceof Taint;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $effect = this.getEffect();
      result = result * 59 + ($effect == null ? 43 : $effect.hashCode());
      Object $key = this.getKey();
      result = result * 59 + ($key == null ? 43 : $key.hashCode());
      Object $timeAdded = this.getTimeAdded();
      result = result * 59 + ($timeAdded == null ? 43 : $timeAdded.hashCode());
      Object $value = this.getValue();
      result = result * 59 + ($value == null ? 43 : $value.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
