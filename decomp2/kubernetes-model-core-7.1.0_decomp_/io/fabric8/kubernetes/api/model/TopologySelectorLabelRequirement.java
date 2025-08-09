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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"key", "values"})
public class TopologySelectorLabelRequirement implements Editable, KubernetesResource {
   @JsonProperty("key")
   private String key;
   @JsonProperty("values")
   @JsonInclude(Include.NON_EMPTY)
   private List values = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public TopologySelectorLabelRequirement() {
   }

   public TopologySelectorLabelRequirement(String key, List values) {
      this.key = key;
      this.values = values;
   }

   @JsonProperty("key")
   public String getKey() {
      return this.key;
   }

   @JsonProperty("key")
   public void setKey(String key) {
      this.key = key;
   }

   @JsonProperty("values")
   @JsonInclude(Include.NON_EMPTY)
   public List getValues() {
      return this.values;
   }

   @JsonProperty("values")
   public void setValues(List values) {
      this.values = values;
   }

   @JsonIgnore
   public TopologySelectorLabelRequirementBuilder edit() {
      return new TopologySelectorLabelRequirementBuilder(this);
   }

   @JsonIgnore
   public TopologySelectorLabelRequirementBuilder toBuilder() {
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
      String var10000 = this.getKey();
      return "TopologySelectorLabelRequirement(key=" + var10000 + ", values=" + this.getValues() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TopologySelectorLabelRequirement)) {
         return false;
      } else {
         TopologySelectorLabelRequirement other = (TopologySelectorLabelRequirement)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$key = this.getKey();
            Object other$key = other.getKey();
            if (this$key == null) {
               if (other$key != null) {
                  return false;
               }
            } else if (!this$key.equals(other$key)) {
               return false;
            }

            Object this$values = this.getValues();
            Object other$values = other.getValues();
            if (this$values == null) {
               if (other$values != null) {
                  return false;
               }
            } else if (!this$values.equals(other$values)) {
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
      return other instanceof TopologySelectorLabelRequirement;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $key = this.getKey();
      result = result * 59 + ($key == null ? 43 : $key.hashCode());
      Object $values = this.getValues();
      result = result * 59 + ($values == null ? 43 : $values.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
