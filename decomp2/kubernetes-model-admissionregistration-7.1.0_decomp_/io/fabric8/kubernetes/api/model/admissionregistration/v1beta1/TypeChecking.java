package io.fabric8.kubernetes.api.model.admissionregistration.v1beta1;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"expressionWarnings"})
public class TypeChecking implements Editable, KubernetesResource {
   @JsonProperty("expressionWarnings")
   @JsonInclude(Include.NON_EMPTY)
   private List expressionWarnings = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public TypeChecking() {
   }

   public TypeChecking(List expressionWarnings) {
      this.expressionWarnings = expressionWarnings;
   }

   @JsonProperty("expressionWarnings")
   @JsonInclude(Include.NON_EMPTY)
   public List getExpressionWarnings() {
      return this.expressionWarnings;
   }

   @JsonProperty("expressionWarnings")
   public void setExpressionWarnings(List expressionWarnings) {
      this.expressionWarnings = expressionWarnings;
   }

   @JsonIgnore
   public TypeCheckingBuilder edit() {
      return new TypeCheckingBuilder(this);
   }

   @JsonIgnore
   public TypeCheckingBuilder toBuilder() {
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
      List var10000 = this.getExpressionWarnings();
      return "TypeChecking(expressionWarnings=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TypeChecking)) {
         return false;
      } else {
         TypeChecking other = (TypeChecking)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$expressionWarnings = this.getExpressionWarnings();
            Object other$expressionWarnings = other.getExpressionWarnings();
            if (this$expressionWarnings == null) {
               if (other$expressionWarnings != null) {
                  return false;
               }
            } else if (!this$expressionWarnings.equals(other$expressionWarnings)) {
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
      return other instanceof TypeChecking;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $expressionWarnings = this.getExpressionWarnings();
      result = result * 59 + ($expressionWarnings == null ? 43 : $expressionWarnings.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
