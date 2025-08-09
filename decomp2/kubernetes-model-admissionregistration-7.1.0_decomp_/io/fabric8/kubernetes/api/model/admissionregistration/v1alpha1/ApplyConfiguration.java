package io.fabric8.kubernetes.api.model.admissionregistration.v1alpha1;

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
@JsonPropertyOrder({"expression"})
public class ApplyConfiguration implements Editable, KubernetesResource {
   @JsonProperty("expression")
   private String expression;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ApplyConfiguration() {
   }

   public ApplyConfiguration(String expression) {
      this.expression = expression;
   }

   @JsonProperty("expression")
   public String getExpression() {
      return this.expression;
   }

   @JsonProperty("expression")
   public void setExpression(String expression) {
      this.expression = expression;
   }

   @JsonIgnore
   public ApplyConfigurationBuilder edit() {
      return new ApplyConfigurationBuilder(this);
   }

   @JsonIgnore
   public ApplyConfigurationBuilder toBuilder() {
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
      String var10000 = this.getExpression();
      return "ApplyConfiguration(expression=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ApplyConfiguration)) {
         return false;
      } else {
         ApplyConfiguration other = (ApplyConfiguration)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$expression = this.getExpression();
            Object other$expression = other.getExpression();
            if (this$expression == null) {
               if (other$expression != null) {
                  return false;
               }
            } else if (!this$expression.equals(other$expression)) {
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
      return other instanceof ApplyConfiguration;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $expression = this.getExpression();
      result = result * 59 + ($expression == null ? 43 : $expression.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
