package io.fabric8.kubernetes.api.model.storagemigration.v1alpha1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class GroupVersionResourceFluent extends BaseFluent {
   private String group;
   private String resource;
   private String version;
   private Map additionalProperties;

   public GroupVersionResourceFluent() {
   }

   public GroupVersionResourceFluent(GroupVersionResource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(GroupVersionResource instance) {
      instance = instance != null ? instance : new GroupVersionResource();
      if (instance != null) {
         this.withGroup(instance.getGroup());
         this.withResource(instance.getResource());
         this.withVersion(instance.getVersion());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getGroup() {
      return this.group;
   }

   public GroupVersionResourceFluent withGroup(String group) {
      this.group = group;
      return this;
   }

   public boolean hasGroup() {
      return this.group != null;
   }

   public String getResource() {
      return this.resource;
   }

   public GroupVersionResourceFluent withResource(String resource) {
      this.resource = resource;
      return this;
   }

   public boolean hasResource() {
      return this.resource != null;
   }

   public String getVersion() {
      return this.version;
   }

   public GroupVersionResourceFluent withVersion(String version) {
      this.version = version;
      return this;
   }

   public boolean hasVersion() {
      return this.version != null;
   }

   public GroupVersionResourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public GroupVersionResourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public GroupVersionResourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public GroupVersionResourceFluent removeFromAdditionalProperties(Map map) {
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

   public GroupVersionResourceFluent withAdditionalProperties(Map additionalProperties) {
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
            GroupVersionResourceFluent that = (GroupVersionResourceFluent)o;
            if (!Objects.equals(this.group, that.group)) {
               return false;
            } else if (!Objects.equals(this.resource, that.resource)) {
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
      return Objects.hash(new Object[]{this.group, this.resource, this.version, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.group != null) {
         sb.append("group:");
         sb.append(this.group + ",");
      }

      if (this.resource != null) {
         sb.append("resource:");
         sb.append(this.resource + ",");
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
