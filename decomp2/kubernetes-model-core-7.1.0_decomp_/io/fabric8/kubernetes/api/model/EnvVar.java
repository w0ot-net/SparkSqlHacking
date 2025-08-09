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
@JsonPropertyOrder({"name", "value", "valueFrom"})
public class EnvVar implements Editable, KubernetesResource {
   @JsonProperty("name")
   private String name;
   @JsonProperty("value")
   private String value;
   @JsonProperty("valueFrom")
   private EnvVarSource valueFrom;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public EnvVar() {
   }

   public EnvVar(String name, String value, EnvVarSource valueFrom) {
      this.name = name;
      this.value = value;
      this.valueFrom = valueFrom;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("value")
   public String getValue() {
      return this.value;
   }

   @JsonProperty("value")
   public void setValue(String value) {
      this.value = value;
   }

   @JsonProperty("valueFrom")
   public EnvVarSource getValueFrom() {
      return this.valueFrom;
   }

   @JsonProperty("valueFrom")
   public void setValueFrom(EnvVarSource valueFrom) {
      this.valueFrom = valueFrom;
   }

   @JsonIgnore
   public EnvVarBuilder edit() {
      return new EnvVarBuilder(this);
   }

   @JsonIgnore
   public EnvVarBuilder toBuilder() {
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
      String var10000 = this.getName();
      return "EnvVar(name=" + var10000 + ", value=" + this.getValue() + ", valueFrom=" + this.getValueFrom() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EnvVar)) {
         return false;
      } else {
         EnvVar other = (EnvVar)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
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

            Object this$valueFrom = this.getValueFrom();
            Object other$valueFrom = other.getValueFrom();
            if (this$valueFrom == null) {
               if (other$valueFrom != null) {
                  return false;
               }
            } else if (!this$valueFrom.equals(other$valueFrom)) {
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
      return other instanceof EnvVar;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $value = this.getValue();
      result = result * 59 + ($value == null ? 43 : $value.hashCode());
      Object $valueFrom = this.getValueFrom();
      result = result * 59 + ($valueFrom == null ? 43 : $valueFrom.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
