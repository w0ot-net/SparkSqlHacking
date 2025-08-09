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
@JsonPropertyOrder({"key", "operator", "values"})
public class NodeSelectorRequirement implements Editable, KubernetesResource {
   @JsonProperty("key")
   private String key;
   @JsonProperty("operator")
   private String operator;
   @JsonProperty("values")
   @JsonInclude(Include.NON_EMPTY)
   private List values = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NodeSelectorRequirement() {
   }

   public NodeSelectorRequirement(String key, String operator, List values) {
      this.key = key;
      this.operator = operator;
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

   @JsonProperty("operator")
   public String getOperator() {
      return this.operator;
   }

   @JsonProperty("operator")
   public void setOperator(String operator) {
      this.operator = operator;
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
   public NodeSelectorRequirementBuilder edit() {
      return new NodeSelectorRequirementBuilder(this);
   }

   @JsonIgnore
   public NodeSelectorRequirementBuilder toBuilder() {
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
      return "NodeSelectorRequirement(key=" + var10000 + ", operator=" + this.getOperator() + ", values=" + this.getValues() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NodeSelectorRequirement)) {
         return false;
      } else {
         NodeSelectorRequirement other = (NodeSelectorRequirement)o;
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

            Object this$operator = this.getOperator();
            Object other$operator = other.getOperator();
            if (this$operator == null) {
               if (other$operator != null) {
                  return false;
               }
            } else if (!this$operator.equals(other$operator)) {
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
      return other instanceof NodeSelectorRequirement;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $key = this.getKey();
      result = result * 59 + ($key == null ? 43 : $key.hashCode());
      Object $operator = this.getOperator();
      result = result * 59 + ($operator == null ? 43 : $operator.hashCode());
      Object $values = this.getValues();
      result = result * 59 + ($values == null ? 43 : $values.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
