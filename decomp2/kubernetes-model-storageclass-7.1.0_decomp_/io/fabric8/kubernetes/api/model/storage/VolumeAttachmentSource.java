package io.fabric8.kubernetes.api.model.storage;

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
import io.fabric8.kubernetes.api.model.PersistentVolumeSpec;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"inlineVolumeSpec", "persistentVolumeName"})
public class VolumeAttachmentSource implements Editable, KubernetesResource {
   @JsonProperty("inlineVolumeSpec")
   private PersistentVolumeSpec inlineVolumeSpec;
   @JsonProperty("persistentVolumeName")
   private String persistentVolumeName;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public VolumeAttachmentSource() {
   }

   public VolumeAttachmentSource(PersistentVolumeSpec inlineVolumeSpec, String persistentVolumeName) {
      this.inlineVolumeSpec = inlineVolumeSpec;
      this.persistentVolumeName = persistentVolumeName;
   }

   @JsonProperty("inlineVolumeSpec")
   public PersistentVolumeSpec getInlineVolumeSpec() {
      return this.inlineVolumeSpec;
   }

   @JsonProperty("inlineVolumeSpec")
   public void setInlineVolumeSpec(PersistentVolumeSpec inlineVolumeSpec) {
      this.inlineVolumeSpec = inlineVolumeSpec;
   }

   @JsonProperty("persistentVolumeName")
   public String getPersistentVolumeName() {
      return this.persistentVolumeName;
   }

   @JsonProperty("persistentVolumeName")
   public void setPersistentVolumeName(String persistentVolumeName) {
      this.persistentVolumeName = persistentVolumeName;
   }

   @JsonIgnore
   public VolumeAttachmentSourceBuilder edit() {
      return new VolumeAttachmentSourceBuilder(this);
   }

   @JsonIgnore
   public VolumeAttachmentSourceBuilder toBuilder() {
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
      PersistentVolumeSpec var10000 = this.getInlineVolumeSpec();
      return "VolumeAttachmentSource(inlineVolumeSpec=" + var10000 + ", persistentVolumeName=" + this.getPersistentVolumeName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof VolumeAttachmentSource)) {
         return false;
      } else {
         VolumeAttachmentSource other = (VolumeAttachmentSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$inlineVolumeSpec = this.getInlineVolumeSpec();
            Object other$inlineVolumeSpec = other.getInlineVolumeSpec();
            if (this$inlineVolumeSpec == null) {
               if (other$inlineVolumeSpec != null) {
                  return false;
               }
            } else if (!this$inlineVolumeSpec.equals(other$inlineVolumeSpec)) {
               return false;
            }

            Object this$persistentVolumeName = this.getPersistentVolumeName();
            Object other$persistentVolumeName = other.getPersistentVolumeName();
            if (this$persistentVolumeName == null) {
               if (other$persistentVolumeName != null) {
                  return false;
               }
            } else if (!this$persistentVolumeName.equals(other$persistentVolumeName)) {
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
      return other instanceof VolumeAttachmentSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $inlineVolumeSpec = this.getInlineVolumeSpec();
      result = result * 59 + ($inlineVolumeSpec == null ? 43 : $inlineVolumeSpec.hashCode());
      Object $persistentVolumeName = this.getPersistentVolumeName();
      result = result * 59 + ($persistentVolumeName == null ? 43 : $persistentVolumeName.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
