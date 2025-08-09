package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class NodeConfigSourceFluent extends BaseFluent {
   private ConfigMapNodeConfigSourceBuilder configMap;
   private Map additionalProperties;

   public NodeConfigSourceFluent() {
   }

   public NodeConfigSourceFluent(NodeConfigSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NodeConfigSource instance) {
      instance = instance != null ? instance : new NodeConfigSource();
      if (instance != null) {
         this.withConfigMap(instance.getConfigMap());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ConfigMapNodeConfigSource buildConfigMap() {
      return this.configMap != null ? this.configMap.build() : null;
   }

   public NodeConfigSourceFluent withConfigMap(ConfigMapNodeConfigSource configMap) {
      this._visitables.remove("configMap");
      if (configMap != null) {
         this.configMap = new ConfigMapNodeConfigSourceBuilder(configMap);
         this._visitables.get("configMap").add(this.configMap);
      } else {
         this.configMap = null;
         this._visitables.get("configMap").remove(this.configMap);
      }

      return this;
   }

   public boolean hasConfigMap() {
      return this.configMap != null;
   }

   public NodeConfigSourceFluent withNewConfigMap(String kubeletConfigKey, String name, String namespace, String resourceVersion, String uid) {
      return this.withConfigMap(new ConfigMapNodeConfigSource(kubeletConfigKey, name, namespace, resourceVersion, uid));
   }

   public ConfigMapNested withNewConfigMap() {
      return new ConfigMapNested((ConfigMapNodeConfigSource)null);
   }

   public ConfigMapNested withNewConfigMapLike(ConfigMapNodeConfigSource item) {
      return new ConfigMapNested(item);
   }

   public ConfigMapNested editConfigMap() {
      return this.withNewConfigMapLike((ConfigMapNodeConfigSource)Optional.ofNullable(this.buildConfigMap()).orElse((Object)null));
   }

   public ConfigMapNested editOrNewConfigMap() {
      return this.withNewConfigMapLike((ConfigMapNodeConfigSource)Optional.ofNullable(this.buildConfigMap()).orElse((new ConfigMapNodeConfigSourceBuilder()).build()));
   }

   public ConfigMapNested editOrNewConfigMapLike(ConfigMapNodeConfigSource item) {
      return this.withNewConfigMapLike((ConfigMapNodeConfigSource)Optional.ofNullable(this.buildConfigMap()).orElse(item));
   }

   public NodeConfigSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NodeConfigSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NodeConfigSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NodeConfigSourceFluent removeFromAdditionalProperties(Map map) {
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

   public NodeConfigSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            NodeConfigSourceFluent that = (NodeConfigSourceFluent)o;
            if (!Objects.equals(this.configMap, that.configMap)) {
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
      return Objects.hash(new Object[]{this.configMap, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.configMap != null) {
         sb.append("configMap:");
         sb.append(this.configMap + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ConfigMapNested extends ConfigMapNodeConfigSourceFluent implements Nested {
      ConfigMapNodeConfigSourceBuilder builder;

      ConfigMapNested(ConfigMapNodeConfigSource item) {
         this.builder = new ConfigMapNodeConfigSourceBuilder(this, item);
      }

      public Object and() {
         return NodeConfigSourceFluent.this.withConfigMap(this.builder.build());
      }

      public Object endConfigMap() {
         return this.and();
      }
   }
}
