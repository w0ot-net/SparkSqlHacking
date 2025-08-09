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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"key", "valueExpression"})
public class AuditAnnotation implements Editable, KubernetesResource {
   @JsonProperty("key")
   private String key;
   @JsonProperty("valueExpression")
   private String valueExpression;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public AuditAnnotation() {
   }

   public AuditAnnotation(String key, String valueExpression) {
      this.key = key;
      this.valueExpression = valueExpression;
   }

   @JsonProperty("key")
   public String getKey() {
      return this.key;
   }

   @JsonProperty("key")
   public void setKey(String key) {
      this.key = key;
   }

   @JsonProperty("valueExpression")
   public String getValueExpression() {
      return this.valueExpression;
   }

   @JsonProperty("valueExpression")
   public void setValueExpression(String valueExpression) {
      this.valueExpression = valueExpression;
   }

   @JsonIgnore
   public AuditAnnotationBuilder edit() {
      return new AuditAnnotationBuilder(this);
   }

   @JsonIgnore
   public AuditAnnotationBuilder toBuilder() {
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
      return "AuditAnnotation(key=" + var10000 + ", valueExpression=" + this.getValueExpression() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AuditAnnotation)) {
         return false;
      } else {
         AuditAnnotation other = (AuditAnnotation)o;
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

            Object this$valueExpression = this.getValueExpression();
            Object other$valueExpression = other.getValueExpression();
            if (this$valueExpression == null) {
               if (other$valueExpression != null) {
                  return false;
               }
            } else if (!this$valueExpression.equals(other$valueExpression)) {
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
      return other instanceof AuditAnnotation;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $key = this.getKey();
      result = result * 59 + ($key == null ? 43 : $key.hashCode());
      Object $valueExpression = this.getValueExpression();
      result = result * 59 + ($valueExpression == null ? 43 : $valueExpression.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
