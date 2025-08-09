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
public class PersistentVolumeClaimTemplate implements Editable, KubernetesResource {
   @JsonProperty("metadata")
   private ObjectMeta metadata;
   @JsonProperty("spec")
   private PersistentVolumeClaimSpec spec;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PersistentVolumeClaimTemplate() {
   }

   public PersistentVolumeClaimTemplate(ObjectMeta metadata, PersistentVolumeClaimSpec spec) {
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
   public PersistentVolumeClaimSpec getSpec() {
      return this.spec;
   }

   @JsonProperty("spec")
   public void setSpec(PersistentVolumeClaimSpec spec) {
      this.spec = spec;
   }

   @JsonIgnore
   public PersistentVolumeClaimTemplateBuilder edit() {
      return new PersistentVolumeClaimTemplateBuilder(this);
   }

   @JsonIgnore
   public PersistentVolumeClaimTemplateBuilder toBuilder() {
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
      return "PersistentVolumeClaimTemplate(metadata=" + var10000 + ", spec=" + this.getSpec() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PersistentVolumeClaimTemplate)) {
         return false;
      } else {
         PersistentVolumeClaimTemplate other = (PersistentVolumeClaimTemplate)o;
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
      return other instanceof PersistentVolumeClaimTemplate;
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
