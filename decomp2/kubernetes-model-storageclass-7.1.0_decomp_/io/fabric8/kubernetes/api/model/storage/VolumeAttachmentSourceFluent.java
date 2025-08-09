package io.fabric8.kubernetes.api.model.storage;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.model.PersistentVolumeSpec;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class VolumeAttachmentSourceFluent extends BaseFluent {
   private PersistentVolumeSpec inlineVolumeSpec;
   private String persistentVolumeName;
   private Map additionalProperties;

   public VolumeAttachmentSourceFluent() {
   }

   public VolumeAttachmentSourceFluent(VolumeAttachmentSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(VolumeAttachmentSource instance) {
      instance = instance != null ? instance : new VolumeAttachmentSource();
      if (instance != null) {
         this.withInlineVolumeSpec(instance.getInlineVolumeSpec());
         this.withPersistentVolumeName(instance.getPersistentVolumeName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public PersistentVolumeSpec getInlineVolumeSpec() {
      return this.inlineVolumeSpec;
   }

   public VolumeAttachmentSourceFluent withInlineVolumeSpec(PersistentVolumeSpec inlineVolumeSpec) {
      this.inlineVolumeSpec = inlineVolumeSpec;
      return this;
   }

   public boolean hasInlineVolumeSpec() {
      return this.inlineVolumeSpec != null;
   }

   public String getPersistentVolumeName() {
      return this.persistentVolumeName;
   }

   public VolumeAttachmentSourceFluent withPersistentVolumeName(String persistentVolumeName) {
      this.persistentVolumeName = persistentVolumeName;
      return this;
   }

   public boolean hasPersistentVolumeName() {
      return this.persistentVolumeName != null;
   }

   public VolumeAttachmentSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public VolumeAttachmentSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public VolumeAttachmentSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public VolumeAttachmentSourceFluent removeFromAdditionalProperties(Map map) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.additionalProperties != null) {
                  this.additionalProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public VolumeAttachmentSourceFluent withAdditionalProperties(Map additionalProperties) {
      if (additionalProperties == null) {
         this.additionalProperties = null;
      } else {
         this.additionalProperties = new LinkedHashMap(additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            VolumeAttachmentSourceFluent that = (VolumeAttachmentSourceFluent)o;
            if (!Objects.equals(this.inlineVolumeSpec, that.inlineVolumeSpec)) {
               return false;
            } else if (!Objects.equals(this.persistentVolumeName, that.persistentVolumeName)) {
               return false;
            } else {
               return Objects.equals(this.additionalProperties, that.additionalProperties);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.inlineVolumeSpec, this.persistentVolumeName, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.inlineVolumeSpec != null) {
         sb.append("inlineVolumeSpec:");
         sb.append(this.inlineVolumeSpec + ",");
      }

      if (this.persistentVolumeName != null) {
         sb.append("persistentVolumeName:");
         sb.append(this.persistentVolumeName + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
