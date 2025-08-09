package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ConfigMapNodeConfigSourceFluent extends BaseFluent {
   private String kubeletConfigKey;
   private String name;
   private String namespace;
   private String resourceVersion;
   private String uid;
   private Map additionalProperties;

   public ConfigMapNodeConfigSourceFluent() {
   }

   public ConfigMapNodeConfigSourceFluent(ConfigMapNodeConfigSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ConfigMapNodeConfigSource instance) {
      instance = instance != null ? instance : new ConfigMapNodeConfigSource();
      if (instance != null) {
         this.withKubeletConfigKey(instance.getKubeletConfigKey());
         this.withName(instance.getName());
         this.withNamespace(instance.getNamespace());
         this.withResourceVersion(instance.getResourceVersion());
         this.withUid(instance.getUid());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getKubeletConfigKey() {
      return this.kubeletConfigKey;
   }

   public ConfigMapNodeConfigSourceFluent withKubeletConfigKey(String kubeletConfigKey) {
      this.kubeletConfigKey = kubeletConfigKey;
      return this;
   }

   public boolean hasKubeletConfigKey() {
      return this.kubeletConfigKey != null;
   }

   public String getName() {
      return this.name;
   }

   public ConfigMapNodeConfigSourceFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public String getNamespace() {
      return this.namespace;
   }

   public ConfigMapNodeConfigSourceFluent withNamespace(String namespace) {
      this.namespace = namespace;
      return this;
   }

   public boolean hasNamespace() {
      return this.namespace != null;
   }

   public String getResourceVersion() {
      return this.resourceVersion;
   }

   public ConfigMapNodeConfigSourceFluent withResourceVersion(String resourceVersion) {
      this.resourceVersion = resourceVersion;
      return this;
   }

   public boolean hasResourceVersion() {
      return this.resourceVersion != null;
   }

   public String getUid() {
      return this.uid;
   }

   public ConfigMapNodeConfigSourceFluent withUid(String uid) {
      this.uid = uid;
      return this;
   }

   public boolean hasUid() {
      return this.uid != null;
   }

   public ConfigMapNodeConfigSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ConfigMapNodeConfigSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ConfigMapNodeConfigSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ConfigMapNodeConfigSourceFluent removeFromAdditionalProperties(Map map) {
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

   public ConfigMapNodeConfigSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            ConfigMapNodeConfigSourceFluent that = (ConfigMapNodeConfigSourceFluent)o;
            if (!Objects.equals(this.kubeletConfigKey, that.kubeletConfigKey)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.namespace, that.namespace)) {
               return false;
            } else if (!Objects.equals(this.resourceVersion, that.resourceVersion)) {
               return false;
            } else if (!Objects.equals(this.uid, that.uid)) {
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
      return Objects.hash(new Object[]{this.kubeletConfigKey, this.name, this.namespace, this.resourceVersion, this.uid, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.kubeletConfigKey != null) {
         sb.append("kubeletConfigKey:");
         sb.append(this.kubeletConfigKey + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.namespace != null) {
         sb.append("namespace:");
         sb.append(this.namespace + ",");
      }

      if (this.resourceVersion != null) {
         sb.append("resourceVersion:");
         sb.append(this.resourceVersion + ",");
      }

      if (this.uid != null) {
         sb.append("uid:");
         sb.append(this.uid + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
