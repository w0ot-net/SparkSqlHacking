package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class NamedClusterFluent extends BaseFluent {
   private ClusterBuilder cluster;
   private String name;
   private Map additionalProperties;

   public NamedClusterFluent() {
   }

   public NamedClusterFluent(NamedCluster instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NamedCluster instance) {
      instance = instance != null ? instance : new NamedCluster();
      if (instance != null) {
         this.withCluster(instance.getCluster());
         this.withName(instance.getName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Cluster buildCluster() {
      return this.cluster != null ? this.cluster.build() : null;
   }

   public NamedClusterFluent withCluster(Cluster cluster) {
      this._visitables.remove("cluster");
      if (cluster != null) {
         this.cluster = new ClusterBuilder(cluster);
         this._visitables.get("cluster").add(this.cluster);
      } else {
         this.cluster = null;
         this._visitables.get("cluster").remove(this.cluster);
      }

      return this;
   }

   public boolean hasCluster() {
      return this.cluster != null;
   }

   public ClusterNested withNewCluster() {
      return new ClusterNested((Cluster)null);
   }

   public ClusterNested withNewClusterLike(Cluster item) {
      return new ClusterNested(item);
   }

   public ClusterNested editCluster() {
      return this.withNewClusterLike((Cluster)Optional.ofNullable(this.buildCluster()).orElse((Object)null));
   }

   public ClusterNested editOrNewCluster() {
      return this.withNewClusterLike((Cluster)Optional.ofNullable(this.buildCluster()).orElse((new ClusterBuilder()).build()));
   }

   public ClusterNested editOrNewClusterLike(Cluster item) {
      return this.withNewClusterLike((Cluster)Optional.ofNullable(this.buildCluster()).orElse(item));
   }

   public String getName() {
      return this.name;
   }

   public NamedClusterFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public NamedClusterFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NamedClusterFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NamedClusterFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NamedClusterFluent removeFromAdditionalProperties(Map map) {
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

   public NamedClusterFluent withAdditionalProperties(Map additionalProperties) {
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
            NamedClusterFluent that = (NamedClusterFluent)o;
            if (!Objects.equals(this.cluster, that.cluster)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
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
      return Objects.hash(new Object[]{this.cluster, this.name, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.cluster != null) {
         sb.append("cluster:");
         sb.append(this.cluster + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ClusterNested extends ClusterFluent implements Nested {
      ClusterBuilder builder;

      ClusterNested(Cluster item) {
         this.builder = new ClusterBuilder(this, item);
      }

      public Object and() {
         return NamedClusterFluent.this.withCluster(this.builder.build());
      }

      public Object endCluster() {
         return this.and();
      }
   }
}
