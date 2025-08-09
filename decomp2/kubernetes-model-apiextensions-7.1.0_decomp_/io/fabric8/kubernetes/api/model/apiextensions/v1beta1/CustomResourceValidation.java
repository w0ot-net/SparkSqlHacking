package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

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
@JsonPropertyOrder({"openAPIV3Schema"})
public class CustomResourceValidation implements Editable, KubernetesResource {
   @JsonProperty("openAPIV3Schema")
   private JSONSchemaProps openAPIV3Schema;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CustomResourceValidation() {
   }

   public CustomResourceValidation(JSONSchemaProps openAPIV3Schema) {
      this.openAPIV3Schema = openAPIV3Schema;
   }

   @JsonProperty("openAPIV3Schema")
   public JSONSchemaProps getOpenAPIV3Schema() {
      return this.openAPIV3Schema;
   }

   @JsonProperty("openAPIV3Schema")
   public void setOpenAPIV3Schema(JSONSchemaProps openAPIV3Schema) {
      this.openAPIV3Schema = openAPIV3Schema;
   }

   @JsonIgnore
   public CustomResourceValidationBuilder edit() {
      return new CustomResourceValidationBuilder(this);
   }

   @JsonIgnore
   public CustomResourceValidationBuilder toBuilder() {
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
      JSONSchemaProps var10000 = this.getOpenAPIV3Schema();
      return "CustomResourceValidation(openAPIV3Schema=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CustomResourceValidation)) {
         return false;
      } else {
         CustomResourceValidation other = (CustomResourceValidation)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$openAPIV3Schema = this.getOpenAPIV3Schema();
            Object other$openAPIV3Schema = other.getOpenAPIV3Schema();
            if (this$openAPIV3Schema == null) {
               if (other$openAPIV3Schema != null) {
                  return false;
               }
            } else if (!this$openAPIV3Schema.equals(other$openAPIV3Schema)) {
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
      return other instanceof CustomResourceValidation;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $openAPIV3Schema = this.getOpenAPIV3Schema();
      result = result * 59 + ($openAPIV3Schema == null ? 43 : $openAPIV3Schema.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
