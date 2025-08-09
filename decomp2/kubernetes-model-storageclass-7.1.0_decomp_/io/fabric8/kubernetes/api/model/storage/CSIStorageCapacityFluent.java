package io.fabric8.kubernetes.api.model.storage;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.LabelSelector;
import io.fabric8.kubernetes.api.model.LabelSelectorBuilder;
import io.fabric8.kubernetes.api.model.LabelSelectorFluent;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.ObjectMetaFluent;
import io.fabric8.kubernetes.api.model.Quantity;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class CSIStorageCapacityFluent extends BaseFluent {
   private String apiVersion;
   private Quantity capacity;
   private String kind;
   private Quantity maximumVolumeSize;
   private ObjectMetaBuilder metadata;
   private LabelSelectorBuilder nodeTopology;
   private String storageClassName;
   private Map additionalProperties;

   public CSIStorageCapacityFluent() {
   }

   public CSIStorageCapacityFluent(CSIStorageCapacity instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CSIStorageCapacity instance) {
      instance = instance != null ? instance : new CSIStorageCapacity();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withCapacity(instance.getCapacity());
         this.withKind(instance.getKind());
         this.withMaximumVolumeSize(instance.getMaximumVolumeSize());
         this.withMetadata(instance.getMetadata());
         this.withNodeTopology(instance.getNodeTopology());
         this.withStorageClassName(instance.getStorageClassName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public CSIStorageCapacityFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public Quantity getCapacity() {
      return this.capacity;
   }

   public CSIStorageCapacityFluent withCapacity(Quantity capacity) {
      this.capacity = capacity;
      return this;
   }

   public boolean hasCapacity() {
      return this.capacity != null;
   }

   public CSIStorageCapacityFluent withNewCapacity(String amount, String format) {
      return this.withCapacity(new Quantity(amount, format));
   }

   public CSIStorageCapacityFluent withNewCapacity(String amount) {
      return this.withCapacity(new Quantity(amount));
   }

   public String getKind() {
      return this.kind;
   }

   public CSIStorageCapacityFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public Quantity getMaximumVolumeSize() {
      return this.maximumVolumeSize;
   }

   public CSIStorageCapacityFluent withMaximumVolumeSize(Quantity maximumVolumeSize) {
      this.maximumVolumeSize = maximumVolumeSize;
      return this;
   }

   public boolean hasMaximumVolumeSize() {
      return this.maximumVolumeSize != null;
   }

   public CSIStorageCapacityFluent withNewMaximumVolumeSize(String amount, String format) {
      return this.withMaximumVolumeSize(new Quantity(amount, format));
   }

   public CSIStorageCapacityFluent withNewMaximumVolumeSize(String amount) {
      return this.withMaximumVolumeSize(new Quantity(amount));
   }

   public ObjectMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public CSIStorageCapacityFluent withMetadata(ObjectMeta metadata) {
      this._visitables.remove("metadata");
      if (metadata != null) {
         this.metadata = new ObjectMetaBuilder(metadata);
         this._visitables.get("metadata").add(this.metadata);
      } else {
         this.metadata = null;
         this._visitables.get("metadata").remove(this.metadata);
      }

      return this;
   }

   public boolean hasMetadata() {
      return this.metadata != null;
   }

   public MetadataNested withNewMetadata() {
      return new MetadataNested((ObjectMeta)null);
   }

   public MetadataNested withNewMetadataLike(ObjectMeta item) {
      return new MetadataNested(item);
   }

   public MetadataNested editMetadata() {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse((Object)null));
   }

   public MetadataNested editOrNewMetadata() {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse((new ObjectMetaBuilder()).build()));
   }

   public MetadataNested editOrNewMetadataLike(ObjectMeta item) {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse(item));
   }

   public LabelSelector buildNodeTopology() {
      return this.nodeTopology != null ? this.nodeTopology.build() : null;
   }

   public CSIStorageCapacityFluent withNodeTopology(LabelSelector nodeTopology) {
      this._visitables.remove("nodeTopology");
      if (nodeTopology != null) {
         this.nodeTopology = new LabelSelectorBuilder(nodeTopology);
         this._visitables.get("nodeTopology").add(this.nodeTopology);
      } else {
         this.nodeTopology = null;
         this._visitables.get("nodeTopology").remove(this.nodeTopology);
      }

      return this;
   }

   public boolean hasNodeTopology() {
      return this.nodeTopology != null;
   }

   public NodeTopologyNested withNewNodeTopology() {
      return new NodeTopologyNested((LabelSelector)null);
   }

   public NodeTopologyNested withNewNodeTopologyLike(LabelSelector item) {
      return new NodeTopologyNested(item);
   }

   public NodeTopologyNested editNodeTopology() {
      return this.withNewNodeTopologyLike((LabelSelector)Optional.ofNullable(this.buildNodeTopology()).orElse((Object)null));
   }

   public NodeTopologyNested editOrNewNodeTopology() {
      return this.withNewNodeTopologyLike((LabelSelector)Optional.ofNullable(this.buildNodeTopology()).orElse((new LabelSelectorBuilder()).build()));
   }

   public NodeTopologyNested editOrNewNodeTopologyLike(LabelSelector item) {
      return this.withNewNodeTopologyLike((LabelSelector)Optional.ofNullable(this.buildNodeTopology()).orElse(item));
   }

   public String getStorageClassName() {
      return this.storageClassName;
   }

   public CSIStorageCapacityFluent withStorageClassName(String storageClassName) {
      this.storageClassName = storageClassName;
      return this;
   }

   public boolean hasStorageClassName() {
      return this.storageClassName != null;
   }

   public CSIStorageCapacityFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CSIStorageCapacityFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CSIStorageCapacityFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CSIStorageCapacityFluent removeFromAdditionalProperties(Map map) {
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

   public CSIStorageCapacityFluent withAdditionalProperties(Map additionalProperties) {
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
            CSIStorageCapacityFluent that = (CSIStorageCapacityFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.capacity, that.capacity)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.maximumVolumeSize, that.maximumVolumeSize)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
               return false;
            } else if (!Objects.equals(this.nodeTopology, that.nodeTopology)) {
               return false;
            } else if (!Objects.equals(this.storageClassName, that.storageClassName)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.capacity, this.kind, this.maximumVolumeSize, this.metadata, this.nodeTopology, this.storageClassName, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.capacity != null) {
         sb.append("capacity:");
         sb.append(this.capacity + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.maximumVolumeSize != null) {
         sb.append("maximumVolumeSize:");
         sb.append(this.maximumVolumeSize + ",");
      }

      if (this.metadata != null) {
         sb.append("metadata:");
         sb.append(this.metadata + ",");
      }

      if (this.nodeTopology != null) {
         sb.append("nodeTopology:");
         sb.append(this.nodeTopology + ",");
      }

      if (this.storageClassName != null) {
         sb.append("storageClassName:");
         sb.append(this.storageClassName + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class MetadataNested extends ObjectMetaFluent implements Nested {
      ObjectMetaBuilder builder;

      MetadataNested(ObjectMeta item) {
         this.builder = new ObjectMetaBuilder(this, item);
      }

      public Object and() {
         return CSIStorageCapacityFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }

   public class NodeTopologyNested extends LabelSelectorFluent implements Nested {
      LabelSelectorBuilder builder;

      NodeTopologyNested(LabelSelector item) {
         this.builder = new LabelSelectorBuilder(this, item);
      }

      public Object and() {
         return CSIStorageCapacityFluent.this.withNodeTopology(this.builder.build());
      }

      public Object endNodeTopology() {
         return this.and();
      }
   }
}
