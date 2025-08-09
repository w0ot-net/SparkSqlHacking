package io.fabric8.kubernetes.api.model.admissionregistration.v1;

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
@JsonPropertyOrder({"fieldRef", "warning"})
public class ExpressionWarning implements Editable, KubernetesResource {
   @JsonProperty("fieldRef")
   private String fieldRef;
   @JsonProperty("warning")
   private String warning;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ExpressionWarning() {
   }

   public ExpressionWarning(String fieldRef, String warning) {
      this.fieldRef = fieldRef;
      this.warning = warning;
   }

   @JsonProperty("fieldRef")
   public String getFieldRef() {
      return this.fieldRef;
   }

   @JsonProperty("fieldRef")
   public void setFieldRef(String fieldRef) {
      this.fieldRef = fieldRef;
   }

   @JsonProperty("warning")
   public String getWarning() {
      return this.warning;
   }

   @JsonProperty("warning")
   public void setWarning(String warning) {
      this.warning = warning;
   }

   @JsonIgnore
   public ExpressionWarningBuilder edit() {
      return new ExpressionWarningBuilder(this);
   }

   @JsonIgnore
   public ExpressionWarningBuilder toBuilder() {
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
      String var10000 = this.getFieldRef();
      return "ExpressionWarning(fieldRef=" + var10000 + ", warning=" + this.getWarning() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ExpressionWarning)) {
         return false;
      } else {
         ExpressionWarning other = (ExpressionWarning)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$fieldRef = this.getFieldRef();
            Object other$fieldRef = other.getFieldRef();
            if (this$fieldRef == null) {
               if (other$fieldRef != null) {
                  return false;
               }
            } else if (!this$fieldRef.equals(other$fieldRef)) {
               return false;
            }

            Object this$warning = this.getWarning();
            Object other$warning = other.getWarning();
            if (this$warning == null) {
               if (other$warning != null) {
                  return false;
               }
            } else if (!this$warning.equals(other$warning)) {
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
      return other instanceof ExpressionWarning;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $fieldRef = this.getFieldRef();
      result = result * 59 + ($fieldRef == null ? 43 : $fieldRef.hashCode());
      Object $warning = this.getWarning();
      result = result * 59 + ($warning == null ? 43 : $warning.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
