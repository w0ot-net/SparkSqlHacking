package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ScaleIOPersistentVolumeSourceFluent extends BaseFluent {
   private String fsType;
   private String gateway;
   private String protectionDomain;
   private Boolean readOnly;
   private SecretReferenceBuilder secretRef;
   private Boolean sslEnabled;
   private String storageMode;
   private String storagePool;
   private String system;
   private String volumeName;
   private Map additionalProperties;

   public ScaleIOPersistentVolumeSourceFluent() {
   }

   public ScaleIOPersistentVolumeSourceFluent(ScaleIOPersistentVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ScaleIOPersistentVolumeSource instance) {
      instance = instance != null ? instance : new ScaleIOPersistentVolumeSource();
      if (instance != null) {
         this.withFsType(instance.getFsType());
         this.withGateway(instance.getGateway());
         this.withProtectionDomain(instance.getProtectionDomain());
         this.withReadOnly(instance.getReadOnly());
         this.withSecretRef(instance.getSecretRef());
         this.withSslEnabled(instance.getSslEnabled());
         this.withStorageMode(instance.getStorageMode());
         this.withStoragePool(instance.getStoragePool());
         this.withSystem(instance.getSystem());
         this.withVolumeName(instance.getVolumeName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getFsType() {
      return this.fsType;
   }

   public ScaleIOPersistentVolumeSourceFluent withFsType(String fsType) {
      this.fsType = fsType;
      return this;
   }

   public boolean hasFsType() {
      return this.fsType != null;
   }

   public String getGateway() {
      return this.gateway;
   }

   public ScaleIOPersistentVolumeSourceFluent withGateway(String gateway) {
      this.gateway = gateway;
      return this;
   }

   public boolean hasGateway() {
      return this.gateway != null;
   }

   public String getProtectionDomain() {
      return this.protectionDomain;
   }

   public ScaleIOPersistentVolumeSourceFluent withProtectionDomain(String protectionDomain) {
      this.protectionDomain = protectionDomain;
      return this;
   }

   public boolean hasProtectionDomain() {
      return this.protectionDomain != null;
   }

   public Boolean getReadOnly() {
      return this.readOnly;
   }

   public ScaleIOPersistentVolumeSourceFluent withReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
   }

   public boolean hasReadOnly() {
      return this.readOnly != null;
   }

   public SecretReference buildSecretRef() {
      return this.secretRef != null ? this.secretRef.build() : null;
   }

   public ScaleIOPersistentVolumeSourceFluent withSecretRef(SecretReference secretRef) {
      this._visitables.remove("secretRef");
      if (secretRef != null) {
         this.secretRef = new SecretReferenceBuilder(secretRef);
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

   public ScaleIOPersistentVolumeSourceFluent withNewSecretRef(String name, String namespace) {
      return this.withSecretRef(new SecretReference(name, namespace));
   }

   public SecretRefNested withNewSecretRef() {
      return new SecretRefNested((SecretReference)null);
   }

   public SecretRefNested withNewSecretRefLike(SecretReference item) {
      return new SecretRefNested(item);
   }

   public SecretRefNested editSecretRef() {
      return this.withNewSecretRefLike((SecretReference)Optional.ofNullable(this.buildSecretRef()).orElse((Object)null));
   }

   public SecretRefNested editOrNewSecretRef() {
      return this.withNewSecretRefLike((SecretReference)Optional.ofNullable(this.buildSecretRef()).orElse((new SecretReferenceBuilder()).build()));
   }

   public SecretRefNested editOrNewSecretRefLike(SecretReference item) {
      return this.withNewSecretRefLike((SecretReference)Optional.ofNullable(this.buildSecretRef()).orElse(item));
   }

   public Boolean getSslEnabled() {
      return this.sslEnabled;
   }

   public ScaleIOPersistentVolumeSourceFluent withSslEnabled(Boolean sslEnabled) {
      this.sslEnabled = sslEnabled;
      return this;
   }

   public boolean hasSslEnabled() {
      return this.sslEnabled != null;
   }

   public String getStorageMode() {
      return this.storageMode;
   }

   public ScaleIOPersistentVolumeSourceFluent withStorageMode(String storageMode) {
      this.storageMode = storageMode;
      return this;
   }

   public boolean hasStorageMode() {
      return this.storageMode != null;
   }

   public String getStoragePool() {
      return this.storagePool;
   }

   public ScaleIOPersistentVolumeSourceFluent withStoragePool(String storagePool) {
      this.storagePool = storagePool;
      return this;
   }

   public boolean hasStoragePool() {
      return this.storagePool != null;
   }

   public String getSystem() {
      return this.system;
   }

   public ScaleIOPersistentVolumeSourceFluent withSystem(String system) {
      this.system = system;
      return this;
   }

   public boolean hasSystem() {
      return this.system != null;
   }

   public String getVolumeName() {
      return this.volumeName;
   }

   public ScaleIOPersistentVolumeSourceFluent withVolumeName(String volumeName) {
      this.volumeName = volumeName;
      return this;
   }

   public boolean hasVolumeName() {
      return this.volumeName != null;
   }

   public ScaleIOPersistentVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ScaleIOPersistentVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ScaleIOPersistentVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ScaleIOPersistentVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public ScaleIOPersistentVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            ScaleIOPersistentVolumeSourceFluent that = (ScaleIOPersistentVolumeSourceFluent)o;
            if (!Objects.equals(this.fsType, that.fsType)) {
               return false;
            } else if (!Objects.equals(this.gateway, that.gateway)) {
               return false;
            } else if (!Objects.equals(this.protectionDomain, that.protectionDomain)) {
               return false;
            } else if (!Objects.equals(this.readOnly, that.readOnly)) {
               return false;
            } else if (!Objects.equals(this.secretRef, that.secretRef)) {
               return false;
            } else if (!Objects.equals(this.sslEnabled, that.sslEnabled)) {
               return false;
            } else if (!Objects.equals(this.storageMode, that.storageMode)) {
               return false;
            } else if (!Objects.equals(this.storagePool, that.storagePool)) {
               return false;
            } else if (!Objects.equals(this.system, that.system)) {
               return false;
            } else if (!Objects.equals(this.volumeName, that.volumeName)) {
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
      return Objects.hash(new Object[]{this.fsType, this.gateway, this.protectionDomain, this.readOnly, this.secretRef, this.sslEnabled, this.storageMode, this.storagePool, this.system, this.volumeName, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.fsType != null) {
         sb.append("fsType:");
         sb.append(this.fsType + ",");
      }

      if (this.gateway != null) {
         sb.append("gateway:");
         sb.append(this.gateway + ",");
      }

      if (this.protectionDomain != null) {
         sb.append("protectionDomain:");
         sb.append(this.protectionDomain + ",");
      }

      if (this.readOnly != null) {
         sb.append("readOnly:");
         sb.append(this.readOnly + ",");
      }

      if (this.secretRef != null) {
         sb.append("secretRef:");
         sb.append(this.secretRef + ",");
      }

      if (this.sslEnabled != null) {
         sb.append("sslEnabled:");
         sb.append(this.sslEnabled + ",");
      }

      if (this.storageMode != null) {
         sb.append("storageMode:");
         sb.append(this.storageMode + ",");
      }

      if (this.storagePool != null) {
         sb.append("storagePool:");
         sb.append(this.storagePool + ",");
      }

      if (this.system != null) {
         sb.append("system:");
         sb.append(this.system + ",");
      }

      if (this.volumeName != null) {
         sb.append("volumeName:");
         sb.append(this.volumeName + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public ScaleIOPersistentVolumeSourceFluent withReadOnly() {
      return this.withReadOnly(true);
   }

   public ScaleIOPersistentVolumeSourceFluent withSslEnabled() {
      return this.withSslEnabled(true);
   }

   public class SecretRefNested extends SecretReferenceFluent implements Nested {
      SecretReferenceBuilder builder;

      SecretRefNested(SecretReference item) {
         this.builder = new SecretReferenceBuilder(this, item);
      }

      public Object and() {
         return ScaleIOPersistentVolumeSourceFluent.this.withSecretRef(this.builder.build());
      }

      public Object endSecretRef() {
         return this.and();
      }
   }
}
