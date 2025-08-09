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
@JsonPropertyOrder({"effect", "key", "operator", "tolerationSeconds", "value"})
public class Toleration implements Editable, KubernetesResource {
   @JsonProperty("effect")
   private String effect;
   @JsonProperty("key")
   private String key;
   @JsonProperty("operator")
   private String operator;
   @JsonProperty("tolerationSeconds")
   private Long tolerationSeconds;
   @JsonProperty("value")
   private String value;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Toleration() {
   }

   public Toleration(String effect, String key, String operator, Long tolerationSeconds, String value) {
      this.effect = effect;
      this.key = key;
      this.operator = operator;
      this.tolerationSeconds = tolerationSeconds;
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

   @JsonProperty("operator")
   public String getOperator() {
      return this.operator;
   }

   @JsonProperty("operator")
   public void setOperator(String operator) {
      this.operator = operator;
   }

   @JsonProperty("tolerationSeconds")
   public Long getTolerationSeconds() {
      return this.tolerationSeconds;
   }

   @JsonProperty("tolerationSeconds")
   public void setTolerationSeconds(Long tolerationSeconds) {
      this.tolerationSeconds = tolerationSeconds;
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
   public TolerationBuilder edit() {
      return new TolerationBuilder(this);
   }

   @JsonIgnore
   public TolerationBuilder toBuilder() {
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
      return "Toleration(effect=" + var10000 + ", key=" + this.getKey() + ", operator=" + this.getOperator() + ", tolerationSeconds=" + this.getTolerationSeconds() + ", value=" + this.getValue() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Toleration)) {
         return false;
      } else {
         Toleration other = (Toleration)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$tolerationSeconds = this.getTolerationSeconds();
            Object other$tolerationSeconds = other.getTolerationSeconds();
            if (this$tolerationSeconds == null) {
               if (other$tolerationSeconds != null) {
                  return false;
               }
            } else if (!this$tolerationSeconds.equals(other$tolerationSeconds)) {
               return false;
            }

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

            Object this$operator = this.getOperator();
            Object other$operator = other.getOperator();
            if (this$operator == null) {
               if (other$operator != null) {
                  return false;
               }
            } else if (!this$operator.equals(other$operator)) {
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
      return other instanceof Toleration;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $tolerationSeconds = this.getTolerationSeconds();
      result = result * 59 + ($tolerationSeconds == null ? 43 : $tolerationSeconds.hashCode());
      Object $effect = this.getEffect();
      result = result * 59 + ($effect == null ? 43 : $effect.hashCode());
      Object $key = this.getKey();
      result = result * 59 + ($key == null ? 43 : $key.hashCode());
      Object $operator = this.getOperator();
      result = result * 59 + ($operator == null ? 43 : $operator.hashCode());
      Object $value = this.getValue();
      result = result * 59 + ($value == null ? 43 : $value.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
