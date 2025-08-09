package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class CSIPersistentVolumeSourceFluent extends BaseFluent {
   private SecretReferenceBuilder controllerExpandSecretRef;
   private SecretReferenceBuilder controllerPublishSecretRef;
   private String driver;
   private String fsType;
   private SecretReferenceBuilder nodeExpandSecretRef;
   private SecretReferenceBuilder nodePublishSecretRef;
   private SecretReferenceBuilder nodeStageSecretRef;
   private Boolean readOnly;
   private Map volumeAttributes;
   private String volumeHandle;
   private Map additionalProperties;

   public CSIPersistentVolumeSourceFluent() {
   }

   public CSIPersistentVolumeSourceFluent(CSIPersistentVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CSIPersistentVolumeSource instance) {
      instance = instance != null ? instance : new CSIPersistentVolumeSource();
      if (instance != null) {
         this.withControllerExpandSecretRef(instance.getControllerExpandSecretRef());
         this.withControllerPublishSecretRef(instance.getControllerPublishSecretRef());
         this.withDriver(instance.getDriver());
         this.withFsType(instance.getFsType());
         this.withNodeExpandSecretRef(instance.getNodeExpandSecretRef());
         this.withNodePublishSecretRef(instance.getNodePublishSecretRef());
         this.withNodeStageSecretRef(instance.getNodeStageSecretRef());
         this.withReadOnly(instance.getReadOnly());
         this.withVolumeAttributes(instance.getVolumeAttributes());
         this.withVolumeHandle(instance.getVolumeHandle());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public SecretReference buildControllerExpandSecretRef() {
      return this.controllerExpandSecretRef != null ? this.controllerExpandSecretRef.build() : null;
   }

   public CSIPersistentVolumeSourceFluent withControllerExpandSecretRef(SecretReference controllerExpandSecretRef) {
      this._visitables.remove("controllerExpandSecretRef");
      if (controllerExpandSecretRef != null) {
         this.controllerExpandSecretRef = new SecretReferenceBuilder(controllerExpandSecretRef);
         this._visitables.get("controllerExpandSecretRef").add(this.controllerExpandSecretRef);
      } else {
         this.controllerExpandSecretRef = null;
         this._visitables.get("controllerExpandSecretRef").remove(this.controllerExpandSecretRef);
      }

      return this;
   }

   public boolean hasControllerExpandSecretRef() {
      return this.controllerExpandSecretRef != null;
   }

   public CSIPersistentVolumeSourceFluent withNewControllerExpandSecretRef(String name, String namespace) {
      return this.withControllerExpandSecretRef(new SecretReference(name, namespace));
   }

   public ControllerExpandSecretRefNested withNewControllerExpandSecretRef() {
      return new ControllerExpandSecretRefNested((SecretReference)null);
   }

   public ControllerExpandSecretRefNested withNewControllerExpandSecretRefLike(SecretReference item) {
      return new ControllerExpandSecretRefNested(item);
   }

   public ControllerExpandSecretRefNested editControllerExpandSecretRef() {
      return this.withNewControllerExpandSecretRefLike((SecretReference)Optional.ofNullable(this.buildControllerExpandSecretRef()).orElse((Object)null));
   }

   public ControllerExpandSecretRefNested editOrNewControllerExpandSecretRef() {
      return this.withNewControllerExpandSecretRefLike((SecretReference)Optional.ofNullable(this.buildControllerExpandSecretRef()).orElse((new SecretReferenceBuilder()).build()));
   }

   public ControllerExpandSecretRefNested editOrNewControllerExpandSecretRefLike(SecretReference item) {
      return this.withNewControllerExpandSecretRefLike((SecretReference)Optional.ofNullable(this.buildControllerExpandSecretRef()).orElse(item));
   }

   public SecretReference buildControllerPublishSecretRef() {
      return this.controllerPublishSecretRef != null ? this.controllerPublishSecretRef.build() : null;
   }

   public CSIPersistentVolumeSourceFluent withControllerPublishSecretRef(SecretReference controllerPublishSecretRef) {
      this._visitables.remove("controllerPublishSecretRef");
      if (controllerPublishSecretRef != null) {
         this.controllerPublishSecretRef = new SecretReferenceBuilder(controllerPublishSecretRef);
         this._visitables.get("controllerPublishSecretRef").add(this.controllerPublishSecretRef);
      } else {
         this.controllerPublishSecretRef = null;
         this._visitables.get("controllerPublishSecretRef").remove(this.controllerPublishSecretRef);
      }

      return this;
   }

   public boolean hasControllerPublishSecretRef() {
      return this.controllerPublishSecretRef != null;
   }

   public CSIPersistentVolumeSourceFluent withNewControllerPublishSecretRef(String name, String namespace) {
      return this.withControllerPublishSecretRef(new SecretReference(name, namespace));
   }

   public ControllerPublishSecretRefNested withNewControllerPublishSecretRef() {
      return new ControllerPublishSecretRefNested((SecretReference)null);
   }

   public ControllerPublishSecretRefNested withNewControllerPublishSecretRefLike(SecretReference item) {
      return new ControllerPublishSecretRefNested(item);
   }

   public ControllerPublishSecretRefNested editControllerPublishSecretRef() {
      return this.withNewControllerPublishSecretRefLike((SecretReference)Optional.ofNullable(this.buildControllerPublishSecretRef()).orElse((Object)null));
   }

   public ControllerPublishSecretRefNested editOrNewControllerPublishSecretRef() {
      return this.withNewControllerPublishSecretRefLike((SecretReference)Optional.ofNullable(this.buildControllerPublishSecretRef()).orElse((new SecretReferenceBuilder()).build()));
   }

   public ControllerPublishSecretRefNested editOrNewControllerPublishSecretRefLike(SecretReference item) {
      return this.withNewControllerPublishSecretRefLike((SecretReference)Optional.ofNullable(this.buildControllerPublishSecretRef()).orElse(item));
   }

   public String getDriver() {
      return this.driver;
   }

   public CSIPersistentVolumeSourceFluent withDriver(String driver) {
      this.driver = driver;
      return this;
   }

   public boolean hasDriver() {
      return this.driver != null;
   }

   public String getFsType() {
      return this.fsType;
   }

   public CSIPersistentVolumeSourceFluent withFsType(String fsType) {
      this.fsType = fsType;
      return this;
   }

   public boolean hasFsType() {
      return this.fsType != null;
   }

   public SecretReference buildNodeExpandSecretRef() {
      return this.nodeExpandSecretRef != null ? this.nodeExpandSecretRef.build() : null;
   }

   public CSIPersistentVolumeSourceFluent withNodeExpandSecretRef(SecretReference nodeExpandSecretRef) {
      this._visitables.remove("nodeExpandSecretRef");
      if (nodeExpandSecretRef != null) {
         this.nodeExpandSecretRef = new SecretReferenceBuilder(nodeExpandSecretRef);
         this._visitables.get("nodeExpandSecretRef").add(this.nodeExpandSecretRef);
      } else {
         this.nodeExpandSecretRef = null;
         this._visitables.get("nodeExpandSecretRef").remove(this.nodeExpandSecretRef);
      }

      return this;
   }

   public boolean hasNodeExpandSecretRef() {
      return this.nodeExpandSecretRef != null;
   }

   public CSIPersistentVolumeSourceFluent withNewNodeExpandSecretRef(String name, String namespace) {
      return this.withNodeExpandSecretRef(new SecretReference(name, namespace));
   }

   public NodeExpandSecretRefNested withNewNodeExpandSecretRef() {
      return new NodeExpandSecretRefNested((SecretReference)null);
   }

   public NodeExpandSecretRefNested withNewNodeExpandSecretRefLike(SecretReference item) {
      return new NodeExpandSecretRefNested(item);
   }

   public NodeExpandSecretRefNested editNodeExpandSecretRef() {
      return this.withNewNodeExpandSecretRefLike((SecretReference)Optional.ofNullable(this.buildNodeExpandSecretRef()).orElse((Object)null));
   }

   public NodeExpandSecretRefNested editOrNewNodeExpandSecretRef() {
      return this.withNewNodeExpandSecretRefLike((SecretReference)Optional.ofNullable(this.buildNodeExpandSecretRef()).orElse((new SecretReferenceBuilder()).build()));
   }

   public NodeExpandSecretRefNested editOrNewNodeExpandSecretRefLike(SecretReference item) {
      return this.withNewNodeExpandSecretRefLike((SecretReference)Optional.ofNullable(this.buildNodeExpandSecretRef()).orElse(item));
   }

   public SecretReference buildNodePublishSecretRef() {
      return this.nodePublishSecretRef != null ? this.nodePublishSecretRef.build() : null;
   }

   public CSIPersistentVolumeSourceFluent withNodePublishSecretRef(SecretReference nodePublishSecretRef) {
      this._visitables.remove("nodePublishSecretRef");
      if (nodePublishSecretRef != null) {
         this.nodePublishSecretRef = new SecretReferenceBuilder(nodePublishSecretRef);
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

   public CSIPersistentVolumeSourceFluent withNewNodePublishSecretRef(String name, String namespace) {
      return this.withNodePublishSecretRef(new SecretReference(name, namespace));
   }

   public NodePublishSecretRefNested withNewNodePublishSecretRef() {
      return new NodePublishSecretRefNested((SecretReference)null);
   }

   public NodePublishSecretRefNested withNewNodePublishSecretRefLike(SecretReference item) {
      return new NodePublishSecretRefNested(item);
   }

   public NodePublishSecretRefNested editNodePublishSecretRef() {
      return this.withNewNodePublishSecretRefLike((SecretReference)Optional.ofNullable(this.buildNodePublishSecretRef()).orElse((Object)null));
   }

   public NodePublishSecretRefNested editOrNewNodePublishSecretRef() {
      return this.withNewNodePublishSecretRefLike((SecretReference)Optional.ofNullable(this.buildNodePublishSecretRef()).orElse((new SecretReferenceBuilder()).build()));
   }

   public NodePublishSecretRefNested editOrNewNodePublishSecretRefLike(SecretReference item) {
      return this.withNewNodePublishSecretRefLike((SecretReference)Optional.ofNullable(this.buildNodePublishSecretRef()).orElse(item));
   }

   public SecretReference buildNodeStageSecretRef() {
      return this.nodeStageSecretRef != null ? this.nodeStageSecretRef.build() : null;
   }

   public CSIPersistentVolumeSourceFluent withNodeStageSecretRef(SecretReference nodeStageSecretRef) {
      this._visitables.remove("nodeStageSecretRef");
      if (nodeStageSecretRef != null) {
         this.nodeStageSecretRef = new SecretReferenceBuilder(nodeStageSecretRef);
         this._visitables.get("nodeStageSecretRef").add(this.nodeStageSecretRef);
      } else {
         this.nodeStageSecretRef = null;
         this._visitables.get("nodeStageSecretRef").remove(this.nodeStageSecretRef);
      }

      return this;
   }

   public boolean hasNodeStageSecretRef() {
      return this.nodeStageSecretRef != null;
   }

   public CSIPersistentVolumeSourceFluent withNewNodeStageSecretRef(String name, String namespace) {
      return this.withNodeStageSecretRef(new SecretReference(name, namespace));
   }

   public NodeStageSecretRefNested withNewNodeStageSecretRef() {
      return new NodeStageSecretRefNested((SecretReference)null);
   }

   public NodeStageSecretRefNested withNewNodeStageSecretRefLike(SecretReference item) {
      return new NodeStageSecretRefNested(item);
   }

   public NodeStageSecretRefNested editNodeStageSecretRef() {
      return this.withNewNodeStageSecretRefLike((SecretReference)Optional.ofNullable(this.buildNodeStageSecretRef()).orElse((Object)null));
   }

   public NodeStageSecretRefNested editOrNewNodeStageSecretRef() {
      return this.withNewNodeStageSecretRefLike((SecretReference)Optional.ofNullable(this.buildNodeStageSecretRef()).orElse((new SecretReferenceBuilder()).build()));
   }

   public NodeStageSecretRefNested editOrNewNodeStageSecretRefLike(SecretReference item) {
      return this.withNewNodeStageSecretRefLike((SecretReference)Optional.ofNullable(this.buildNodeStageSecretRef()).orElse(item));
   }

   public Boolean getReadOnly() {
      return this.readOnly;
   }

   public CSIPersistentVolumeSourceFluent withReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
   }

   public boolean hasReadOnly() {
      return this.readOnly != null;
   }

   public CSIPersistentVolumeSourceFluent addToVolumeAttributes(String key, String value) {
      if (this.volumeAttributes == null && key != null && value != null) {
         this.volumeAttributes = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.volumeAttributes.put(key, value);
      }

      return this;
   }

   public CSIPersistentVolumeSourceFluent addToVolumeAttributes(Map map) {
      if (this.volumeAttributes == null && map != null) {
         this.volumeAttributes = new LinkedHashMap();
      }

      if (map != null) {
         this.volumeAttributes.putAll(map);
      }

      return this;
   }

   public CSIPersistentVolumeSourceFluent removeFromVolumeAttributes(String key) {
      if (this.volumeAttributes == null) {
         return this;
      } else {
         if (key != null && this.volumeAttributes != null) {
            this.volumeAttributes.remove(key);
         }

         return this;
      }
   }

   public CSIPersistentVolumeSourceFluent removeFromVolumeAttributes(Map map) {
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

   public CSIPersistentVolumeSourceFluent withVolumeAttributes(Map volumeAttributes) {
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

   public String getVolumeHandle() {
      return this.volumeHandle;
   }

   public CSIPersistentVolumeSourceFluent withVolumeHandle(String volumeHandle) {
      this.volumeHandle = volumeHandle;
      return this;
   }

   public boolean hasVolumeHandle() {
      return this.volumeHandle != null;
   }

   public CSIPersistentVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CSIPersistentVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CSIPersistentVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CSIPersistentVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public CSIPersistentVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            CSIPersistentVolumeSourceFluent that = (CSIPersistentVolumeSourceFluent)o;
            if (!Objects.equals(this.controllerExpandSecretRef, that.controllerExpandSecretRef)) {
               return false;
            } else if (!Objects.equals(this.controllerPublishSecretRef, that.controllerPublishSecretRef)) {
               return false;
            } else if (!Objects.equals(this.driver, that.driver)) {
               return false;
            } else if (!Objects.equals(this.fsType, that.fsType)) {
               return false;
            } else if (!Objects.equals(this.nodeExpandSecretRef, that.nodeExpandSecretRef)) {
               return false;
            } else if (!Objects.equals(this.nodePublishSecretRef, that.nodePublishSecretRef)) {
               return false;
            } else if (!Objects.equals(this.nodeStageSecretRef, that.nodeStageSecretRef)) {
               return false;
            } else if (!Objects.equals(this.readOnly, that.readOnly)) {
               return false;
            } else if (!Objects.equals(this.volumeAttributes, that.volumeAttributes)) {
               return false;
            } else if (!Objects.equals(this.volumeHandle, that.volumeHandle)) {
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
      return Objects.hash(new Object[]{this.controllerExpandSecretRef, this.controllerPublishSecretRef, this.driver, this.fsType, this.nodeExpandSecretRef, this.nodePublishSecretRef, this.nodeStageSecretRef, this.readOnly, this.volumeAttributes, this.volumeHandle, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.controllerExpandSecretRef != null) {
         sb.append("controllerExpandSecretRef:");
         sb.append(this.controllerExpandSecretRef + ",");
      }

      if (this.controllerPublishSecretRef != null) {
         sb.append("controllerPublishSecretRef:");
         sb.append(this.controllerPublishSecretRef + ",");
      }

      if (this.driver != null) {
         sb.append("driver:");
         sb.append(this.driver + ",");
      }

      if (this.fsType != null) {
         sb.append("fsType:");
         sb.append(this.fsType + ",");
      }

      if (this.nodeExpandSecretRef != null) {
         sb.append("nodeExpandSecretRef:");
         sb.append(this.nodeExpandSecretRef + ",");
      }

      if (this.nodePublishSecretRef != null) {
         sb.append("nodePublishSecretRef:");
         sb.append(this.nodePublishSecretRef + ",");
      }

      if (this.nodeStageSecretRef != null) {
         sb.append("nodeStageSecretRef:");
         sb.append(this.nodeStageSecretRef + ",");
      }

      if (this.readOnly != null) {
         sb.append("readOnly:");
         sb.append(this.readOnly + ",");
      }

      if (this.volumeAttributes != null && !this.volumeAttributes.isEmpty()) {
         sb.append("volumeAttributes:");
         sb.append(this.volumeAttributes + ",");
      }

      if (this.volumeHandle != null) {
         sb.append("volumeHandle:");
         sb.append(this.volumeHandle + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public CSIPersistentVolumeSourceFluent withReadOnly() {
      return this.withReadOnly(true);
   }

   public class ControllerExpandSecretRefNested extends SecretReferenceFluent implements Nested {
      SecretReferenceBuilder builder;

      ControllerExpandSecretRefNested(SecretReference item) {
         this.builder = new SecretReferenceBuilder(this, item);
      }

      public Object and() {
         return CSIPersistentVolumeSourceFluent.this.withControllerExpandSecretRef(this.builder.build());
      }

      public Object endControllerExpandSecretRef() {
         return this.and();
      }
   }

   public class ControllerPublishSecretRefNested extends SecretReferenceFluent implements Nested {
      SecretReferenceBuilder builder;

      ControllerPublishSecretRefNested(SecretReference item) {
         this.builder = new SecretReferenceBuilder(this, item);
      }

      public Object and() {
         return CSIPersistentVolumeSourceFluent.this.withControllerPublishSecretRef(this.builder.build());
      }

      public Object endControllerPublishSecretRef() {
         return this.and();
      }
   }

   public class NodeExpandSecretRefNested extends SecretReferenceFluent implements Nested {
      SecretReferenceBuilder builder;

      NodeExpandSecretRefNested(SecretReference item) {
         this.builder = new SecretReferenceBuilder(this, item);
      }

      public Object and() {
         return CSIPersistentVolumeSourceFluent.this.withNodeExpandSecretRef(this.builder.build());
      }

      public Object endNodeExpandSecretRef() {
         return this.and();
      }
   }

   public class NodePublishSecretRefNested extends SecretReferenceFluent implements Nested {
      SecretReferenceBuilder builder;

      NodePublishSecretRefNested(SecretReference item) {
         this.builder = new SecretReferenceBuilder(this, item);
      }

      public Object and() {
         return CSIPersistentVolumeSourceFluent.this.withNodePublishSecretRef(this.builder.build());
      }

      public Object endNodePublishSecretRef() {
         return this.and();
      }
   }

   public class NodeStageSecretRefNested extends SecretReferenceFluent implements Nested {
      SecretReferenceBuilder builder;

      NodeStageSecretRefNested(SecretReference item) {
         this.builder = new SecretReferenceBuilder(this, item);
      }

      public Object and() {
         return CSIPersistentVolumeSourceFluent.this.withNodeStageSecretRef(this.builder.build());
      }

      public Object endNodeStageSecretRef() {
         return this.and();
      }
   }
}
