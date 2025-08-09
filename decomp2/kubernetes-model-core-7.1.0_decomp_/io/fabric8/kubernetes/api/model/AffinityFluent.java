package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class AffinityFluent extends BaseFluent {
   private NodeAffinityBuilder nodeAffinity;
   private PodAffinityBuilder podAffinity;
   private PodAntiAffinityBuilder podAntiAffinity;
   private Map additionalProperties;

   public AffinityFluent() {
   }

   public AffinityFluent(Affinity instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Affinity instance) {
      instance = instance != null ? instance : new Affinity();
      if (instance != null) {
         this.withNodeAffinity(instance.getNodeAffinity());
         this.withPodAffinity(instance.getPodAffinity());
         this.withPodAntiAffinity(instance.getPodAntiAffinity());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NodeAffinity buildNodeAffinity() {
      return this.nodeAffinity != null ? this.nodeAffinity.build() : null;
   }

   public AffinityFluent withNodeAffinity(NodeAffinity nodeAffinity) {
      this._visitables.remove("nodeAffinity");
      if (nodeAffinity != null) {
         this.nodeAffinity = new NodeAffinityBuilder(nodeAffinity);
         this._visitables.get("nodeAffinity").add(this.nodeAffinity);
      } else {
         this.nodeAffinity = null;
         this._visitables.get("nodeAffinity").remove(this.nodeAffinity);
      }

      return this;
   }

   public boolean hasNodeAffinity() {
      return this.nodeAffinity != null;
   }

   public NodeAffinityNested withNewNodeAffinity() {
      return new NodeAffinityNested((NodeAffinity)null);
   }

   public NodeAffinityNested withNewNodeAffinityLike(NodeAffinity item) {
      return new NodeAffinityNested(item);
   }

   public NodeAffinityNested editNodeAffinity() {
      return this.withNewNodeAffinityLike((NodeAffinity)Optional.ofNullable(this.buildNodeAffinity()).orElse((Object)null));
   }

   public NodeAffinityNested editOrNewNodeAffinity() {
      return this.withNewNodeAffinityLike((NodeAffinity)Optional.ofNullable(this.buildNodeAffinity()).orElse((new NodeAffinityBuilder()).build()));
   }

   public NodeAffinityNested editOrNewNodeAffinityLike(NodeAffinity item) {
      return this.withNewNodeAffinityLike((NodeAffinity)Optional.ofNullable(this.buildNodeAffinity()).orElse(item));
   }

   public PodAffinity buildPodAffinity() {
      return this.podAffinity != null ? this.podAffinity.build() : null;
   }

   public AffinityFluent withPodAffinity(PodAffinity podAffinity) {
      this._visitables.remove("podAffinity");
      if (podAffinity != null) {
         this.podAffinity = new PodAffinityBuilder(podAffinity);
         this._visitables.get("podAffinity").add(this.podAffinity);
      } else {
         this.podAffinity = null;
         this._visitables.get("podAffinity").remove(this.podAffinity);
      }

      return this;
   }

   public boolean hasPodAffinity() {
      return this.podAffinity != null;
   }

   public PodAffinityNested withNewPodAffinity() {
      return new PodAffinityNested((PodAffinity)null);
   }

   public PodAffinityNested withNewPodAffinityLike(PodAffinity item) {
      return new PodAffinityNested(item);
   }

   public PodAffinityNested editPodAffinity() {
      return this.withNewPodAffinityLike((PodAffinity)Optional.ofNullable(this.buildPodAffinity()).orElse((Object)null));
   }

   public PodAffinityNested editOrNewPodAffinity() {
      return this.withNewPodAffinityLike((PodAffinity)Optional.ofNullable(this.buildPodAffinity()).orElse((new PodAffinityBuilder()).build()));
   }

   public PodAffinityNested editOrNewPodAffinityLike(PodAffinity item) {
      return this.withNewPodAffinityLike((PodAffinity)Optional.ofNullable(this.buildPodAffinity()).orElse(item));
   }

   public PodAntiAffinity buildPodAntiAffinity() {
      return this.podAntiAffinity != null ? this.podAntiAffinity.build() : null;
   }

   public AffinityFluent withPodAntiAffinity(PodAntiAffinity podAntiAffinity) {
      this._visitables.remove("podAntiAffinity");
      if (podAntiAffinity != null) {
         this.podAntiAffinity = new PodAntiAffinityBuilder(podAntiAffinity);
         this._visitables.get("podAntiAffinity").add(this.podAntiAffinity);
      } else {
         this.podAntiAffinity = null;
         this._visitables.get("podAntiAffinity").remove(this.podAntiAffinity);
      }

      return this;
   }

   public boolean hasPodAntiAffinity() {
      return this.podAntiAffinity != null;
   }

   public PodAntiAffinityNested withNewPodAntiAffinity() {
      return new PodAntiAffinityNested((PodAntiAffinity)null);
   }

   public PodAntiAffinityNested withNewPodAntiAffinityLike(PodAntiAffinity item) {
      return new PodAntiAffinityNested(item);
   }

   public PodAntiAffinityNested editPodAntiAffinity() {
      return this.withNewPodAntiAffinityLike((PodAntiAffinity)Optional.ofNullable(this.buildPodAntiAffinity()).orElse((Object)null));
   }

   public PodAntiAffinityNested editOrNewPodAntiAffinity() {
      return this.withNewPodAntiAffinityLike((PodAntiAffinity)Optional.ofNullable(this.buildPodAntiAffinity()).orElse((new PodAntiAffinityBuilder()).build()));
   }

   public PodAntiAffinityNested editOrNewPodAntiAffinityLike(PodAntiAffinity item) {
      return this.withNewPodAntiAffinityLike((PodAntiAffinity)Optional.ofNullable(this.buildPodAntiAffinity()).orElse(item));
   }

   public AffinityFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public AffinityFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public AffinityFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public AffinityFluent removeFromAdditionalProperties(Map map) {
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

   public AffinityFluent withAdditionalProperties(Map additionalProperties) {
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
            AffinityFluent that = (AffinityFluent)o;
            if (!Objects.equals(this.nodeAffinity, that.nodeAffinity)) {
               return false;
            } else if (!Objects.equals(this.podAffinity, that.podAffinity)) {
               return false;
            } else if (!Objects.equals(this.podAntiAffinity, that.podAntiAffinity)) {
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
      return Objects.hash(new Object[]{this.nodeAffinity, this.podAffinity, this.podAntiAffinity, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.nodeAffinity != null) {
         sb.append("nodeAffinity:");
         sb.append(this.nodeAffinity + ",");
      }

      if (this.podAffinity != null) {
         sb.append("podAffinity:");
         sb.append(this.podAffinity + ",");
      }

      if (this.podAntiAffinity != null) {
         sb.append("podAntiAffinity:");
         sb.append(this.podAntiAffinity + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class NodeAffinityNested extends NodeAffinityFluent implements Nested {
      NodeAffinityBuilder builder;

      NodeAffinityNested(NodeAffinity item) {
         this.builder = new NodeAffinityBuilder(this, item);
      }

      public Object and() {
         return AffinityFluent.this.withNodeAffinity(this.builder.build());
      }

      public Object endNodeAffinity() {
         return this.and();
      }
   }

   public class PodAffinityNested extends PodAffinityFluent implements Nested {
      PodAffinityBuilder builder;

      PodAffinityNested(PodAffinity item) {
         this.builder = new PodAffinityBuilder(this, item);
      }

      public Object and() {
         return AffinityFluent.this.withPodAffinity(this.builder.build());
      }

      public Object endPodAffinity() {
         return this.and();
      }
   }

   public class PodAntiAffinityNested extends PodAntiAffinityFluent implements Nested {
      PodAntiAffinityBuilder builder;

      PodAntiAffinityNested(PodAntiAffinity item) {
         this.builder = new PodAntiAffinityBuilder(this, item);
      }

      public Object and() {
         return AffinityFluent.this.withPodAntiAffinity(this.builder.build());
      }

      public Object endPodAntiAffinity() {
         return this.and();
      }
   }
}
