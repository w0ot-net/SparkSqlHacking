package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class FlexVolumeSourceFluent extends BaseFluent {
   private String driver;
   private String fsType;
   private Map options;
   private Boolean readOnly;
   private LocalObjectReferenceBuilder secretRef;
   private Map additionalProperties;

   public FlexVolumeSourceFluent() {
   }

   public FlexVolumeSourceFluent(FlexVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(FlexVolumeSource instance) {
      instance = instance != null ? instance : new FlexVolumeSource();
      if (instance != null) {
         this.withDriver(instance.getDriver());
         this.withFsType(instance.getFsType());
         this.withOptions(instance.getOptions());
         this.withReadOnly(instance.getReadOnly());
         this.withSecretRef(instance.getSecretRef());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getDriver() {
      return this.driver;
   }

   public FlexVolumeSourceFluent withDriver(String driver) {
      this.driver = driver;
      return this;
   }

   public boolean hasDriver() {
      return this.driver != null;
   }

   public String getFsType() {
      return this.fsType;
   }

   public FlexVolumeSourceFluent withFsType(String fsType) {
      this.fsType = fsType;
      return this;
   }

   public boolean hasFsType() {
      return this.fsType != null;
   }

   public FlexVolumeSourceFluent addToOptions(String key, String value) {
      if (this.options == null && key != null && value != null) {
         this.options = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.options.put(key, value);
      }

      return this;
   }

   public FlexVolumeSourceFluent addToOptions(Map map) {
      if (this.options == null && map != null) {
         this.options = new LinkedHashMap();
      }

      if (map != null) {
         this.options.putAll(map);
      }

      return this;
   }

   public FlexVolumeSourceFluent removeFromOptions(String key) {
      if (this.options == null) {
         return this;
      } else {
         if (key != null && this.options != null) {
            this.options.remove(key);
         }

         return this;
      }
   }

   public FlexVolumeSourceFluent removeFromOptions(Map map) {
      if (this.options == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.options != null) {
                  this.options.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getOptions() {
      return this.options;
   }

   public FlexVolumeSourceFluent withOptions(Map options) {
      if (options == null) {
         this.options = null;
      } else {
         this.options = new LinkedHashMap(options);
      }

      return this;
   }

   public boolean hasOptions() {
      return this.options != null;
   }

   public Boolean getReadOnly() {
      return this.readOnly;
   }

   public FlexVolumeSourceFluent withReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
   }

   public boolean hasReadOnly() {
      return this.readOnly != null;
   }

   public LocalObjectReference buildSecretRef() {
      return this.secretRef != null ? this.secretRef.build() : null;
   }

   public FlexVolumeSourceFluent withSecretRef(LocalObjectReference secretRef) {
      this._visitables.remove("secretRef");
      if (secretRef != null) {
         this.secretRef = new LocalObjectReferenceBuilder(secretRef);
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

   public FlexVolumeSourceFluent withNewSecretRef(String name) {
      return this.withSecretRef(new LocalObjectReference(name));
   }

   public SecretRefNested withNewSecretRef() {
      return new SecretRefNested((LocalObjectReference)null);
   }

   public SecretRefNested withNewSecretRefLike(LocalObjectReference item) {
      return new SecretRefNested(item);
   }

   public SecretRefNested editSecretRef() {
      return this.withNewSecretRefLike((LocalObjectReference)Optional.ofNullable(this.buildSecretRef()).orElse((Object)null));
   }

   public SecretRefNested editOrNewSecretRef() {
      return this.withNewSecretRefLike((LocalObjectReference)Optional.ofNullable(this.buildSecretRef()).orElse((new LocalObjectReferenceBuilder()).build()));
   }

   public SecretRefNested editOrNewSecretRefLike(LocalObjectReference item) {
      return this.withNewSecretRefLike((LocalObjectReference)Optional.ofNullable(this.buildSecretRef()).orElse(item));
   }

   public FlexVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public FlexVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public FlexVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public FlexVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public FlexVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            FlexVolumeSourceFluent that = (FlexVolumeSourceFluent)o;
            if (!Objects.equals(this.driver, that.driver)) {
               return false;
            } else if (!Objects.equals(this.fsType, that.fsType)) {
               return false;
            } else if (!Objects.equals(this.options, that.options)) {
               return false;
            } else if (!Objects.equals(this.readOnly, that.readOnly)) {
               return false;
            } else if (!Objects.equals(this.secretRef, that.secretRef)) {
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
      return Objects.hash(new Object[]{this.driver, this.fsType, this.options, this.readOnly, this.secretRef, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.driver != null) {
         sb.append("driver:");
         sb.append(this.driver + ",");
      }

      if (this.fsType != null) {
         sb.append("fsType:");
         sb.append(this.fsType + ",");
      }

      if (this.options != null && !this.options.isEmpty()) {
         sb.append("options:");
         sb.append(this.options + ",");
      }

      if (this.readOnly != null) {
         sb.append("readOnly:");
         sb.append(this.readOnly + ",");
      }

      if (this.secretRef != null) {
         sb.append("secretRef:");
         sb.append(this.secretRef + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public FlexVolumeSourceFluent withReadOnly() {
      return this.withReadOnly(true);
   }

   public class SecretRefNested extends LocalObjectReferenceFluent implements Nested {
      LocalObjectReferenceBuilder builder;

      SecretRefNested(LocalObjectReference item) {
         this.builder = new LocalObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return FlexVolumeSourceFluent.this.withSecretRef(this.builder.build());
      }

      public Object endSecretRef() {
         return this.and();
      }
   }
}
