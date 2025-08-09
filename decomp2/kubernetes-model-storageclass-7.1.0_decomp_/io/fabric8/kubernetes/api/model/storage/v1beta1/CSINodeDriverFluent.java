package io.fabric8.kubernetes.api.model.storage.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class CSINodeDriverFluent extends BaseFluent {
   private VolumeNodeResourcesBuilder allocatable;
   private String name;
   private String nodeID;
   private List topologyKeys = new ArrayList();
   private Map additionalProperties;

   public CSINodeDriverFluent() {
   }

   public CSINodeDriverFluent(CSINodeDriver instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CSINodeDriver instance) {
      instance = instance != null ? instance : new CSINodeDriver();
      if (instance != null) {
         this.withAllocatable(instance.getAllocatable());
         this.withName(instance.getName());
         this.withNodeID(instance.getNodeID());
         this.withTopologyKeys(instance.getTopologyKeys());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public VolumeNodeResources buildAllocatable() {
      return this.allocatable != null ? this.allocatable.build() : null;
   }

   public CSINodeDriverFluent withAllocatable(VolumeNodeResources allocatable) {
      this._visitables.remove("allocatable");
      if (allocatable != null) {
         this.allocatable = new VolumeNodeResourcesBuilder(allocatable);
         this._visitables.get("allocatable").add(this.allocatable);
      } else {
         this.allocatable = null;
         this._visitables.get("allocatable").remove(this.allocatable);
      }

      return this;
   }

   public boolean hasAllocatable() {
      return this.allocatable != null;
   }

   public CSINodeDriverFluent withNewAllocatable(Integer count) {
      return this.withAllocatable(new VolumeNodeResources(count));
   }

   public AllocatableNested withNewAllocatable() {
      return new AllocatableNested((VolumeNodeResources)null);
   }

   public AllocatableNested withNewAllocatableLike(VolumeNodeResources item) {
      return new AllocatableNested(item);
   }

   public AllocatableNested editAllocatable() {
      return this.withNewAllocatableLike((VolumeNodeResources)Optional.ofNullable(this.buildAllocatable()).orElse((Object)null));
   }

   public AllocatableNested editOrNewAllocatable() {
      return this.withNewAllocatableLike((VolumeNodeResources)Optional.ofNullable(this.buildAllocatable()).orElse((new VolumeNodeResourcesBuilder()).build()));
   }

   public AllocatableNested editOrNewAllocatableLike(VolumeNodeResources item) {
      return this.withNewAllocatableLike((VolumeNodeResources)Optional.ofNullable(this.buildAllocatable()).orElse(item));
   }

   public String getName() {
      return this.name;
   }

   public CSINodeDriverFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public String getNodeID() {
      return this.nodeID;
   }

   public CSINodeDriverFluent withNodeID(String nodeID) {
      this.nodeID = nodeID;
      return this;
   }

   public boolean hasNodeID() {
      return this.nodeID != null;
   }

   public CSINodeDriverFluent addToTopologyKeys(int index, String item) {
      if (this.topologyKeys == null) {
         this.topologyKeys = new ArrayList();
      }

      this.topologyKeys.add(index, item);
      return this;
   }

   public CSINodeDriverFluent setToTopologyKeys(int index, String item) {
      if (this.topologyKeys == null) {
         this.topologyKeys = new ArrayList();
      }

      this.topologyKeys.set(index, item);
      return this;
   }

   public CSINodeDriverFluent addToTopologyKeys(String... items) {
      if (this.topologyKeys == null) {
         this.topologyKeys = new ArrayList();
      }

      for(String item : items) {
         this.topologyKeys.add(item);
      }

      return this;
   }

   public CSINodeDriverFluent addAllToTopologyKeys(Collection items) {
      if (this.topologyKeys == null) {
         this.topologyKeys = new ArrayList();
      }

      for(String item : items) {
         this.topologyKeys.add(item);
      }

      return this;
   }

   public CSINodeDriverFluent removeFromTopologyKeys(String... items) {
      if (this.topologyKeys == null) {
         return this;
      } else {
         for(String item : items) {
            this.topologyKeys.remove(item);
         }

         return this;
      }
   }

   public CSINodeDriverFluent removeAllFromTopologyKeys(Collection items) {
      if (this.topologyKeys == null) {
         return this;
      } else {
         for(String item : items) {
            this.topologyKeys.remove(item);
         }

         return this;
      }
   }

   public List getTopologyKeys() {
      return this.topologyKeys;
   }

   public String getTopologyKey(int index) {
      return (String)this.topologyKeys.get(index);
   }

   public String getFirstTopologyKey() {
      return (String)this.topologyKeys.get(0);
   }

   public String getLastTopologyKey() {
      return (String)this.topologyKeys.get(this.topologyKeys.size() - 1);
   }

   public String getMatchingTopologyKey(Predicate predicate) {
      for(String item : this.topologyKeys) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingTopologyKey(Predicate predicate) {
      for(String item : this.topologyKeys) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public CSINodeDriverFluent withTopologyKeys(List topologyKeys) {
      if (topologyKeys != null) {
         this.topologyKeys = new ArrayList();

         for(String item : topologyKeys) {
            this.addToTopologyKeys(item);
         }
      } else {
         this.topologyKeys = null;
      }

      return this;
   }

   public CSINodeDriverFluent withTopologyKeys(String... topologyKeys) {
      if (this.topologyKeys != null) {
         this.topologyKeys.clear();
         this._visitables.remove("topologyKeys");
      }

      if (topologyKeys != null) {
         for(String item : topologyKeys) {
            this.addToTopologyKeys(item);
         }
      }

      return this;
   }

   public boolean hasTopologyKeys() {
      return this.topologyKeys != null && !this.topologyKeys.isEmpty();
   }

   public CSINodeDriverFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CSINodeDriverFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CSINodeDriverFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CSINodeDriverFluent removeFromAdditionalProperties(Map map) {
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

   public CSINodeDriverFluent withAdditionalProperties(Map additionalProperties) {
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
            CSINodeDriverFluent that = (CSINodeDriverFluent)o;
            if (!Objects.equals(this.allocatable, that.allocatable)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.nodeID, that.nodeID)) {
               return false;
            } else if (!Objects.equals(this.topologyKeys, that.topologyKeys)) {
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
      return Objects.hash(new Object[]{this.allocatable, this.name, this.nodeID, this.topologyKeys, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.allocatable != null) {
         sb.append("allocatable:");
         sb.append(this.allocatable + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.nodeID != null) {
         sb.append("nodeID:");
         sb.append(this.nodeID + ",");
      }

      if (this.topologyKeys != null && !this.topologyKeys.isEmpty()) {
         sb.append("topologyKeys:");
         sb.append(this.topologyKeys + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class AllocatableNested extends VolumeNodeResourcesFluent implements Nested {
      VolumeNodeResourcesBuilder builder;

      AllocatableNested(VolumeNodeResources item) {
         this.builder = new VolumeNodeResourcesBuilder(this, item);
      }

      public Object and() {
         return CSINodeDriverFluent.this.withAllocatable(this.builder.build());
      }

      public Object endAllocatable() {
         return this.and();
      }
   }
}
