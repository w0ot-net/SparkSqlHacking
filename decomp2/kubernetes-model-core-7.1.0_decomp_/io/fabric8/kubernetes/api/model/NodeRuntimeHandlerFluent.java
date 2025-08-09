package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class NodeRuntimeHandlerFluent extends BaseFluent {
   private NodeRuntimeHandlerFeaturesBuilder features;
   private String name;
   private Map additionalProperties;

   public NodeRuntimeHandlerFluent() {
   }

   public NodeRuntimeHandlerFluent(NodeRuntimeHandler instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NodeRuntimeHandler instance) {
      instance = instance != null ? instance : new NodeRuntimeHandler();
      if (instance != null) {
         this.withFeatures(instance.getFeatures());
         this.withName(instance.getName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NodeRuntimeHandlerFeatures buildFeatures() {
      return this.features != null ? this.features.build() : null;
   }

   public NodeRuntimeHandlerFluent withFeatures(NodeRuntimeHandlerFeatures features) {
      this._visitables.remove("features");
      if (features != null) {
         this.features = new NodeRuntimeHandlerFeaturesBuilder(features);
         this._visitables.get("features").add(this.features);
      } else {
         this.features = null;
         this._visitables.get("features").remove(this.features);
      }

      return this;
   }

   public boolean hasFeatures() {
      return this.features != null;
   }

   public NodeRuntimeHandlerFluent withNewFeatures(Boolean recursiveReadOnlyMounts, Boolean userNamespaces) {
      return this.withFeatures(new NodeRuntimeHandlerFeatures(recursiveReadOnlyMounts, userNamespaces));
   }

   public FeaturesNested withNewFeatures() {
      return new FeaturesNested((NodeRuntimeHandlerFeatures)null);
   }

   public FeaturesNested withNewFeaturesLike(NodeRuntimeHandlerFeatures item) {
      return new FeaturesNested(item);
   }

   public FeaturesNested editFeatures() {
      return this.withNewFeaturesLike((NodeRuntimeHandlerFeatures)Optional.ofNullable(this.buildFeatures()).orElse((Object)null));
   }

   public FeaturesNested editOrNewFeatures() {
      return this.withNewFeaturesLike((NodeRuntimeHandlerFeatures)Optional.ofNullable(this.buildFeatures()).orElse((new NodeRuntimeHandlerFeaturesBuilder()).build()));
   }

   public FeaturesNested editOrNewFeaturesLike(NodeRuntimeHandlerFeatures item) {
      return this.withNewFeaturesLike((NodeRuntimeHandlerFeatures)Optional.ofNullable(this.buildFeatures()).orElse(item));
   }

   public String getName() {
      return this.name;
   }

   public NodeRuntimeHandlerFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public NodeRuntimeHandlerFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NodeRuntimeHandlerFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NodeRuntimeHandlerFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NodeRuntimeHandlerFluent removeFromAdditionalProperties(Map map) {
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

   public NodeRuntimeHandlerFluent withAdditionalProperties(Map additionalProperties) {
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
            NodeRuntimeHandlerFluent that = (NodeRuntimeHandlerFluent)o;
            if (!Objects.equals(this.features, that.features)) {
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
      return Objects.hash(new Object[]{this.features, this.name, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.features != null) {
         sb.append("features:");
         sb.append(this.features + ",");
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

   public class FeaturesNested extends NodeRuntimeHandlerFeaturesFluent implements Nested {
      NodeRuntimeHandlerFeaturesBuilder builder;

      FeaturesNested(NodeRuntimeHandlerFeatures item) {
         this.builder = new NodeRuntimeHandlerFeaturesBuilder(this, item);
      }

      public Object and() {
         return NodeRuntimeHandlerFluent.this.withFeatures(this.builder.build());
      }

      public Object endFeatures() {
         return this.and();
      }
   }
}
