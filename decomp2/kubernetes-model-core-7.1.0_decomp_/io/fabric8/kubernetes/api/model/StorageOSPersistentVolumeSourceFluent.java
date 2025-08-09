package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class StorageOSPersistentVolumeSourceFluent extends BaseFluent {
   private String fsType;
   private Boolean readOnly;
   private ObjectReferenceBuilder secretRef;
   private String volumeName;
   private String volumeNamespace;
   private Map additionalProperties;

   public StorageOSPersistentVolumeSourceFluent() {
   }

   public StorageOSPersistentVolumeSourceFluent(StorageOSPersistentVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(StorageOSPersistentVolumeSource instance) {
      instance = instance != null ? instance : new StorageOSPersistentVolumeSource();
      if (instance != null) {
         this.withFsType(instance.getFsType());
         this.withReadOnly(instance.getReadOnly());
         this.withSecretRef(instance.getSecretRef());
         this.withVolumeName(instance.getVolumeName());
         this.withVolumeNamespace(instance.getVolumeNamespace());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getFsType() {
      return this.fsType;
   }

   public StorageOSPersistentVolumeSourceFluent withFsType(String fsType) {
      this.fsType = fsType;
      return this;
   }

   public boolean hasFsType() {
      return this.fsType != null;
   }

   public Boolean getReadOnly() {
      return this.readOnly;
   }

   public StorageOSPersistentVolumeSourceFluent withReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
   }

   public boolean hasReadOnly() {
      return this.readOnly != null;
   }

   public ObjectReference buildSecretRef() {
      return this.secretRef != null ? this.secretRef.build() : null;
   }

   public StorageOSPersistentVolumeSourceFluent withSecretRef(ObjectReference secretRef) {
      this._visitables.remove("secretRef");
      if (secretRef != null) {
         this.secretRef = new ObjectReferenceBuilder(secretRef);
         this._visitables.get("secretRef").add(this.secretRef);
      } else {
         this.secretRef = null;
         this._visitables.get("secretRef").remove(this.secretRef);
      }

      return this;
   }

   public boolean hasSecretRef() {
      return this.secretRef != null;
   }

   public SecretRefNested withNewSecretRef() {
      return new SecretRefNested((ObjectReference)null);
   }

   public SecretRefNested withNewSecretRefLike(ObjectReference item) {
      return new SecretRefNested(item);
   }

   public SecretRefNested editSecretRef() {
      return this.withNewSecretRefLike((ObjectReference)Optional.ofNullable(this.buildSecretRef()).orElse((Object)null));
   }

   public SecretRefNested editOrNewSecretRef() {
      return this.withNewSecretRefLike((ObjectReference)Optional.ofNullable(this.buildSecretRef()).orElse((new ObjectReferenceBuilder()).build()));
   }

   public SecretRefNested editOrNewSecretRefLike(ObjectReference item) {
      return this.withNewSecretRefLike((ObjectReference)Optional.ofNullable(this.buildSecretRef()).orElse(item));
   }

   public String getVolumeName() {
      return this.volumeName;
   }

   public StorageOSPersistentVolumeSourceFluent withVolumeName(String volumeName) {
      this.volumeName = volumeName;
      return this;
   }

   public boolean hasVolumeName() {
      return this.volumeName != null;
   }

   public String getVolumeNamespace() {
      return this.volumeNamespace;
   }

   public StorageOSPersistentVolumeSourceFluent withVolumeNamespace(String volumeNamespace) {
      this.volumeNamespace = volumeNamespace;
      return this;
   }

   public boolean hasVolumeNamespace() {
      return this.volumeNamespace != null;
   }

   public StorageOSPersistentVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public StorageOSPersistentVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public StorageOSPersistentVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public StorageOSPersistentVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public StorageOSPersistentVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            StorageOSPersistentVolumeSourceFluent that = (StorageOSPersistentVolumeSourceFluent)o;
            if (!Objects.equals(this.fsType, that.fsType)) {
               return false;
            } else if (!Objects.equals(this.readOnly, that.readOnly)) {
               return false;
            } else if (!Objects.equals(this.secretRef, that.secretRef)) {
               return false;
            } else if (!Objects.equals(this.volumeName, that.volumeName)) {
               return false;
            } else if (!Objects.equals(this.volumeNamespace, that.volumeNamespace)) {
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
      return Objects.hash(new Object[]{this.fsType, this.readOnly, this.secretRef, this.volumeName, this.volumeNamespace, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.fsType != null) {
         sb.append("fsType:");
         sb.append(this.fsType + ",");
      }

      if (this.readOnly != null) {
         sb.append("readOnly:");
         sb.append(this.readOnly + ",");
      }

      if (this.secretRef != null) {
         sb.append("secretRef:");
         sb.append(this.secretRef + ",");
      }

      if (this.volumeName != null) {
         sb.append("volumeName:");
         sb.append(this.volumeName + ",");
      }

      if (this.volumeNamespace != null) {
         sb.append("volumeNamespace:");
         sb.append(this.volumeNamespace + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public StorageOSPersistentVolumeSourceFluent withReadOnly() {
      return this.withReadOnly(true);
   }

   public class SecretRefNested extends ObjectReferenceFluent implements Nested {
      ObjectReferenceBuilder builder;

      SecretRefNested(ObjectReference item) {
         this.builder = new ObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return StorageOSPersistentVolumeSourceFluent.this.withSecretRef(this.builder.build());
      }

      public Object endSecretRef() {
         return this.and();
      }
   }
}
