package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class GroupVersionForDiscoveryFluent extends BaseFluent {
   private String groupVersion;
   private String version;
   private Map additionalProperties;

   public GroupVersionForDiscoveryFluent() {
   }

   public GroupVersionForDiscoveryFluent(GroupVersionForDiscovery instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(GroupVersionForDiscovery instance) {
      instance = instance != null ? instance : new GroupVersionForDiscovery();
      if (instance != null) {
         this.withGroupVersion(instance.getGroupVersion());
         this.withVersion(instance.getVersion());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getGroupVersion() {
      return this.groupVersion;
   }

   public GroupVersionForDiscoveryFluent withGroupVersion(String groupVersion) {
      this.groupVersion = groupVersion;
      return this;
   }

   public boolean hasGroupVersion() {
      return this.groupVersion != null;
   }

   public String getVersion() {
      return this.version;
   }

   public GroupVersionForDiscoveryFluent withVersion(String version) {
      this.version = version;
      return this;
   }

   public boolean hasVersion() {
      return this.version != null;
   }

   public GroupVersionForDiscoveryFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public GroupVersionForDiscoveryFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public GroupVersionForDiscoveryFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public GroupVersionForDiscoveryFluent removeFromAdditionalProperties(Map map) {
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

   public GroupVersionForDiscoveryFluent withAdditionalProperties(Map additionalProperties) {
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
            GroupVersionForDiscoveryFluent that = (GroupVersionForDiscoveryFluent)o;
            if (!Objects.equals(this.groupVersion, that.groupVersion)) {
               return false;
            } else if (!Objects.equals(this.version, that.version)) {
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
      return Objects.hash(new Object[]{this.groupVersion, this.version, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.groupVersion != null) {
         sb.append("groupVersion:");
         sb.append(this.groupVersion + ",");
      }

      if (this.version != null) {
         sb.append("version:");
         sb.append(this.version + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
