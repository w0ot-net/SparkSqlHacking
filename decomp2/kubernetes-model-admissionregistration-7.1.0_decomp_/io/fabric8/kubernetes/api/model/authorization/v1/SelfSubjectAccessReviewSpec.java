package io.fabric8.kubernetes.api.model.authorization.v1;

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
@JsonPropertyOrder({"nonResourceAttributes", "resourceAttributes"})
public class SelfSubjectAccessReviewSpec implements Editable, KubernetesResource {
   @JsonProperty("nonResourceAttributes")
   private NonResourceAttributes nonResourceAttributes;
   @JsonProperty("resourceAttributes")
   private ResourceAttributes resourceAttributes;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public SelfSubjectAccessReviewSpec() {
   }

   public SelfSubjectAccessReviewSpec(NonResourceAttributes nonResourceAttributes, ResourceAttributes resourceAttributes) {
      this.nonResourceAttributes = nonResourceAttributes;
      this.resourceAttributes = resourceAttributes;
   }

   @JsonProperty("nonResourceAttributes")
   public NonResourceAttributes getNonResourceAttributes() {
      return this.nonResourceAttributes;
   }

   @JsonProperty("nonResourceAttributes")
   public void setNonResourceAttributes(NonResourceAttributes nonResourceAttributes) {
      this.nonResourceAttributes = nonResourceAttributes;
   }

   @JsonProperty("resourceAttributes")
   public ResourceAttributes getResourceAttributes() {
      return this.resourceAttributes;
   }

   @JsonProperty("resourceAttributes")
   public void setResourceAttributes(ResourceAttributes resourceAttributes) {
      this.resourceAttributes = resourceAttributes;
   }

   @JsonIgnore
   public SelfSubjectAccessReviewSpecBuilder edit() {
      return new SelfSubjectAccessReviewSpecBuilder(this);
   }

   @JsonIgnore
   public SelfSubjectAccessReviewSpecBuilder toBuilder() {
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
      NonResourceAttributes var10000 = this.getNonResourceAttributes();
      return "SelfSubjectAccessReviewSpec(nonResourceAttributes=" + var10000 + ", resourceAttributes=" + this.getResourceAttributes() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SelfSubjectAccessReviewSpec)) {
         return false;
      } else {
         SelfSubjectAccessReviewSpec other = (SelfSubjectAccessReviewSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$nonResourceAttributes = this.getNonResourceAttributes();
            Object other$nonResourceAttributes = other.getNonResourceAttributes();
            if (this$nonResourceAttributes == null) {
               if (other$nonResourceAttributes != null) {
                  return false;
               }
            } else if (!this$nonResourceAttributes.equals(other$nonResourceAttributes)) {
               return false;
            }

            Object this$resourceAttributes = this.getResourceAttributes();
            Object other$resourceAttributes = other.getResourceAttributes();
            if (this$resourceAttributes == null) {
               if (other$resourceAttributes != null) {
                  return false;
               }
            } else if (!this$resourceAttributes.equals(other$resourceAttributes)) {
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
      return other instanceof SelfSubjectAccessReviewSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $nonResourceAttributes = this.getNonResourceAttributes();
      result = result * 59 + ($nonResourceAttributes == null ? 43 : $nonResourceAttributes.hashCode());
      Object $resourceAttributes = this.getResourceAttributes();
      result = result * 59 + ($resourceAttributes == null ? 43 : $resourceAttributes.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
