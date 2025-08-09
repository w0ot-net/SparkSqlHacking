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
@JsonPropertyOrder({"metadata", "spec"})
public class PodTemplateSpec implements Editable, KubernetesResource {
   @JsonProperty("metadata")
   private ObjectMeta metadata;
   @JsonProperty("spec")
   private PodSpec spec;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PodTemplateSpec() {
   }

   public PodTemplateSpec(ObjectMeta metadata, PodSpec spec) {
      this.metadata = metadata;
      this.spec = spec;
   }

   @JsonProperty("metadata")
   public ObjectMeta getMetadata() {
      return this.metadata;
   }

   @JsonProperty("metadata")
   public void setMetadata(ObjectMeta metadata) {
      this.metadata = metadata;
   }

   @JsonProperty("spec")
   public PodSpec getSpec() {
      return this.spec;
   }

   @JsonProperty("spec")
   public void setSpec(PodSpec spec) {
      this.spec = spec;
   }

   @JsonIgnore
   public PodTemplateSpecBuilder edit() {
      return new PodTemplateSpecBuilder(this);
   }

   @JsonIgnore
   public PodTemplateSpecBuilder toBuilder() {
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
      ObjectMeta var10000 = this.getMetadata();
      return "PodTemplateSpec(metadata=" + var10000 + ", spec=" + this.getSpec() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PodTemplateSpec)) {
         return false;
      } else {
         PodTemplateSpec other = (PodTemplateSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$metadata = this.getMetadata();
            Object other$metadata = other.getMetadata();
            if (this$metadata == null) {
               if (other$metadata != null) {
                  return false;
               }
            } else if (!this$metadata.equals(other$metadata)) {
               return false;
            }

            Object this$spec = this.getSpec();
            Object other$spec = other.getSpec();
            if (this$spec == null) {
               if (other$spec != null) {
                  return false;
               }
            } else if (!this$spec.equals(other$spec)) {
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
      return other instanceof PodTemplateSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $metadata = this.getMetadata();
      result = result * 59 + ($metadata == null ? 43 : $metadata.hashCode());
      Object $spec = this.getSpec();
      result = result * 59 + ($spec == null ? 43 : $spec.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
