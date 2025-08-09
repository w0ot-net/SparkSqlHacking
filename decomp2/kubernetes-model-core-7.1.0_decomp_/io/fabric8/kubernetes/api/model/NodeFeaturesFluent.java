package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class NodeFeaturesFluent extends BaseFluent {
   private Boolean supplementalGroupsPolicy;
   private Map additionalProperties;

   public NodeFeaturesFluent() {
   }

   public NodeFeaturesFluent(NodeFeatures instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NodeFeatures instance) {
      instance = instance != null ? instance : new NodeFeatures();
      if (instance != null) {
         this.withSupplementalGroupsPolicy(instance.getSupplementalGroupsPolicy());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Boolean getSupplementalGroupsPolicy() {
      return this.supplementalGroupsPolicy;
   }

   public NodeFeaturesFluent withSupplementalGroupsPolicy(Boolean supplementalGroupsPolicy) {
      this.supplementalGroupsPolicy = supplementalGroupsPolicy;
      return this;
   }

   public boolean hasSupplementalGroupsPolicy() {
      return this.supplementalGroupsPolicy != null;
   }

   public NodeFeaturesFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NodeFeaturesFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NodeFeaturesFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NodeFeaturesFluent removeFromAdditionalProperties(Map map) {
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

   public NodeFeaturesFluent withAdditionalProperties(Map additionalProperties) {
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
            NodeFeaturesFluent that = (NodeFeaturesFluent)o;
            if (!Objects.equals(this.supplementalGroupsPolicy, that.supplementalGroupsPolicy)) {
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
      return Objects.hash(new Object[]{this.supplementalGroupsPolicy, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.supplementalGroupsPolicy != null) {
         sb.append("supplementalGroupsPolicy:");
         sb.append(this.supplementalGroupsPolicy + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public NodeFeaturesFluent withSupplementalGroupsPolicy() {
      return this.withSupplementalGroupsPolicy(true);
   }
}
