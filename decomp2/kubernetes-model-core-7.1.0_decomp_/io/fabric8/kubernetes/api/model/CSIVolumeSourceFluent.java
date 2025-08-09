package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class CSIVolumeSourceFluent extends BaseFluent {
   private String driver;
   private String fsType;
   private LocalObjectReferenceBuilder nodePublishSecretRef;
   private Boolean readOnly;
   private Map volumeAttributes;
   private Map additionalProperties;

   public CSIVolumeSourceFluent() {
   }

   public CSIVolumeSourceFluent(CSIVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CSIVolumeSource instance) {
      instance = instance != null ? instance : new CSIVolumeSource();
      if (instance != null) {
         this.withDriver(instance.getDriver());
         this.withFsType(instance.getFsType());
         this.withNodePublishSecretRef(instance.getNodePublishSecretRef());
         this.withReadOnly(instance.getReadOnly());
         this.withVolumeAttributes(instance.getVolumeAttributes());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getDriver() {
      return this.driver;
   }

   public CSIVolumeSourceFluent withDriver(String driver) {
      this.driver = driver;
      return this;
   }

   public boolean hasDriver() {
      return this.driver != null;
   }

   public String getFsType() {
      return this.fsType;
   }

   public CSIVolumeSourceFluent withFsType(String fsType) {
      this.fsType = fsType;
      return this;
   }

   public boolean hasFsType() {
      return this.fsType != null;
   }

   public LocalObjectReference buildNodePublishSecretRef() {
      return this.nodePublishSecretRef != null ? this.nodePublishSecretRef.build() : null;
   }

   public CSIVolumeSourceFluent withNodePublishSecretRef(LocalObjectReference nodePublishSecretRef) {
      this._visitables.remove("nodePublishSecretRef");
      if (nodePublishSecretRef != null) {
         this.nodePublishSecretRef = new LocalObjectReferenceBuilder(nodePublishSecretRef);
         this._visitables.get("nodePublishSecretRef").add(this.nodePublishSecretRef);
      } else {
         this.nodePublishSecretRef = null;
         this._visitables.get("nodePublishSecretRef").remove(this.nodePublishSecretRef);
      }

      return this;
   }

   public boolean hasNodePublishSecretRef() {
      return this.nodePublishSecretRef != null;
   }

   public CSIVolumeSourceFluent withNewNodePublishSecretRef(String name) {
      return this.withNodePublishSecretRef(new LocalObjectReference(name));
   }

   public NodePublishSecretRefNested withNewNodePublishSecretRef() {
      return new NodePublishSecretRefNested((LocalObjectReference)null);
   }

   public NodePublishSecretRefNested withNewNodePublishSecretRefLike(LocalObjectReference item) {
      return new NodePublishSecretRefNested(item);
   }

   public NodePublishSecretRefNested editNodePublishSecretRef() {
      return this.withNewNodePublishSecretRefLike((LocalObjectReference)Optional.ofNullable(this.buildNodePublishSecretRef()).orElse((Object)null));
   }

   public NodePublishSecretRefNested editOrNewNodePublishSecretRef() {
      return this.withNewNodePublishSecretRefLike((LocalObjectReference)Optional.ofNullable(this.buildNodePublishSecretRef()).orElse((new LocalObjectReferenceBuilder()).build()));
   }

   public NodePublishSecretRefNested editOrNewNodePublishSecretRefLike(LocalObjectReference item) {
      return this.withNewNodePublishSecretRefLike((LocalObjectReference)Optional.ofNullable(this.buildNodePublishSecretRef()).orElse(item));
   }

   public Boolean getReadOnly() {
      return this.readOnly;
   }

   public CSIVolumeSourceFluent withReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
   }

   public boolean hasReadOnly() {
      return this.readOnly != null;
   }

   public CSIVolumeSourceFluent addToVolumeAttributes(String key, String value) {
      if (this.volumeAttributes == null && key != null && value != null) {
         this.volumeAttributes = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.volumeAttributes.put(key, value);
      }

      return this;
   }

   public CSIVolumeSourceFluent addToVolumeAttributes(Map map) {
      if (this.volumeAttributes == null && map != null) {
         this.volumeAttributes = new LinkedHashMap();
      }

      if (map != null) {
         this.volumeAttributes.putAll(map);
      }

      return this;
   }

   public CSIVolumeSourceFluent removeFromVolumeAttributes(String key) {
      if (this.volumeAttributes == null) {
         return this;
      } else {
         if (key != null && this.volumeAttributes != null) {
            this.volumeAttributes.remove(key);
         }

         return this;
      }
   }

   public CSIVolumeSourceFluent removeFromVolumeAttributes(Map map) {
      if (this.volumeAttributes == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.volumeAttributes != null) {
                  this.volumeAttributes.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getVolumeAttributes() {
      return this.volumeAttributes;
   }

   public CSIVolumeSourceFluent withVolumeAttributes(Map volumeAttributes) {
      if (volumeAttributes == null) {
         this.volumeAttributes = null;
      } else {
         this.volumeAttributes = new LinkedHashMap(volumeAttributes);
      }

      return this;
   }

   public boolean hasVolumeAttributes() {
      return this.volumeAttributes != null;
   }

   public CSIVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CSIVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CSIVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CSIVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public CSIVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            CSIVolumeSourceFluent that = (CSIVolumeSourceFluent)o;
            if (!Objects.equals(this.driver, that.driver)) {
               return false;
            } else if (!Objects.equals(this.fsType, that.fsType)) {
               return false;
            } else if (!Objects.equals(this.nodePublishSecretRef, that.nodePublishSecretRef)) {
               return false;
            } else if (!Objects.equals(this.readOnly, that.readOnly)) {
               return false;
            } else if (!Objects.equals(this.volumeAttributes, that.volumeAttributes)) {
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
      return Objects.hash(new Object[]{this.driver, this.fsType, this.nodePublishSecretRef, this.readOnly, this.volumeAttributes, this.additionalProperties, super.hashCode()});
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

      if (this.nodePublishSecretRef != null) {
         sb.append("nodePublishSecretRef:");
         sb.append(this.nodePublishSecretRef + ",");
      }

      if (this.readOnly != null) {
         sb.append("readOnly:");
         sb.append(this.readOnly + ",");
      }

      if (this.volumeAttributes != null && !this.volumeAttributes.isEmpty()) {
         sb.append("volumeAttributes:");
         sb.append(this.volumeAttributes + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public CSIVolumeSourceFluent withReadOnly() {
      return this.withReadOnly(true);
   }

   public class NodePublishSecretRefNested extends LocalObjectReferenceFluent implements Nested {
      LocalObjectReferenceBuilder builder;

      NodePublishSecretRefNested(LocalObjectReference item) {
         this.builder = new LocalObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return CSIVolumeSourceFluent.this.withNodePublishSecretRef(this.builder.build());
      }

      public Object endNodePublishSecretRef() {
         return this.and();
      }
   }
}
