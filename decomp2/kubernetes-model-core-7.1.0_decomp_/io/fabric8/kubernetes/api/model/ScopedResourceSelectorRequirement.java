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
@JsonPropertyOrder({"operator", "scopeName", "values"})
public class ScopedResourceSelectorRequirement implements Editable, KubernetesResource {
   @JsonProperty("operator")
   private String operator;
   @JsonProperty("scopeName")
   private String scopeName;
   @JsonProperty("values")
   @JsonInclude(Include.NON_EMPTY)
   private List values = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ScopedResourceSelectorRequirement() {
   }

   public ScopedResourceSelectorRequirement(String operator, String scopeName, List values) {
      this.operator = operator;
      this.scopeName = scopeName;
      this.values = values;
   }

   @JsonProperty("operator")
   public String getOperator() {
      return this.operator;
   }

   @JsonProperty("operator")
   public void setOperator(String operator) {
      this.operator = operator;
   }

   @JsonProperty("scopeName")
   public String getScopeName() {
      return this.scopeName;
   }

   @JsonProperty("scopeName")
   public void setScopeName(String scopeName) {
      this.scopeName = scopeName;
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
   public ScopedResourceSelectorRequirementBuilder edit() {
      return new ScopedResourceSelectorRequirementBuilder(this);
   }

   @JsonIgnore
   public ScopedResourceSelectorRequirementBuilder toBuilder() {
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
      String var10000 = this.getOperator();
      return "ScopedResourceSelectorRequirement(operator=" + var10000 + ", scopeName=" + this.getScopeName() + ", values=" + this.getValues() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ScopedResourceSelectorRequirement)) {
         return false;
      } else {
         ScopedResourceSelectorRequirement other = (ScopedResourceSelectorRequirement)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$operator = this.getOperator();
            Object other$operator = other.getOperator();
            if (this$operator == null) {
               if (other$operator != null) {
                  return false;
               }
            } else if (!this$operator.equals(other$operator)) {
               return false;
            }

            Object this$scopeName = this.getScopeName();
            Object other$scopeName = other.getScopeName();
            if (this$scopeName == null) {
               if (other$scopeName != null) {
                  return false;
               }
            } else if (!this$scopeName.equals(other$scopeName)) {
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
      return other instanceof ScopedResourceSelectorRequirement;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $operator = this.getOperator();
      result = result * 59 + ($operator == null ? 43 : $operator.hashCode());
      Object $scopeName = this.getScopeName();
      result = result * 59 + ($scopeName == null ? 43 : $scopeName.hashCode());
      Object $values = this.getValues();
      result = result * 59 + ($values == null ? 43 : $values.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
