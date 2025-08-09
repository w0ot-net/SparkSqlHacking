package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class NodeRuntimeHandlerFeaturesFluent extends BaseFluent {
   private Boolean recursiveReadOnlyMounts;
   private Boolean userNamespaces;
   private Map additionalProperties;

   public NodeRuntimeHandlerFeaturesFluent() {
   }

   public NodeRuntimeHandlerFeaturesFluent(NodeRuntimeHandlerFeatures instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NodeRuntimeHandlerFeatures instance) {
      instance = instance != null ? instance : new NodeRuntimeHandlerFeatures();
      if (instance != null) {
         this.withRecursiveReadOnlyMounts(instance.getRecursiveReadOnlyMounts());
         this.withUserNamespaces(instance.getUserNamespaces());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Boolean getRecursiveReadOnlyMounts() {
      return this.recursiveReadOnlyMounts;
   }

   public NodeRuntimeHandlerFeaturesFluent withRecursiveReadOnlyMounts(Boolean recursiveReadOnlyMounts) {
      this.recursiveReadOnlyMounts = recursiveReadOnlyMounts;
      return this;
   }

   public boolean hasRecursiveReadOnlyMounts() {
      return this.recursiveReadOnlyMounts != null;
   }

   public Boolean getUserNamespaces() {
      return this.userNamespaces;
   }

   public NodeRuntimeHandlerFeaturesFluent withUserNamespaces(Boolean userNamespaces) {
      this.userNamespaces = userNamespaces;
      return this;
   }

   public boolean hasUserNamespaces() {
      return this.userNamespaces != null;
   }

   public NodeRuntimeHandlerFeaturesFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NodeRuntimeHandlerFeaturesFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NodeRuntimeHandlerFeaturesFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NodeRuntimeHandlerFeaturesFluent removeFromAdditionalProperties(Map map) {
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

   public NodeRuntimeHandlerFeaturesFluent withAdditionalProperties(Map additionalProperties) {
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
            NodeRuntimeHandlerFeaturesFluent that = (NodeRuntimeHandlerFeaturesFluent)o;
            if (!Objects.equals(this.recursiveReadOnlyMounts, that.recursiveReadOnlyMounts)) {
               return false;
            } else if (!Objects.equals(this.userNamespaces, that.userNamespaces)) {
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
      return Objects.hash(new Object[]{this.recursiveReadOnlyMounts, this.userNamespaces, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.recursiveReadOnlyMounts != null) {
         sb.append("recursiveReadOnlyMounts:");
         sb.append(this.recursiveReadOnlyMounts + ",");
      }

      if (this.userNamespaces != null) {
         sb.append("userNamespaces:");
         sb.append(this.userNamespaces + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public NodeRuntimeHandlerFeaturesFluent withRecursiveReadOnlyMounts() {
      return this.withRecursiveReadOnlyMounts(true);
   }

   public NodeRuntimeHandlerFeaturesFluent withUserNamespaces() {
      return this.withUserNamespaces(true);
   }
}
