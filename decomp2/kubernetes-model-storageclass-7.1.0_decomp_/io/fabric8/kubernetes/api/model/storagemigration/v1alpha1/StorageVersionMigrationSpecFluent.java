package io.fabric8.kubernetes.api.model.storagemigration.v1alpha1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class StorageVersionMigrationSpecFluent extends BaseFluent {
   private String continueToken;
   private GroupVersionResourceBuilder resource;
   private Map additionalProperties;

   public StorageVersionMigrationSpecFluent() {
   }

   public StorageVersionMigrationSpecFluent(StorageVersionMigrationSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(StorageVersionMigrationSpec instance) {
      instance = instance != null ? instance : new StorageVersionMigrationSpec();
      if (instance != null) {
         this.withContinueToken(instance.getContinueToken());
         this.withResource(instance.getResource());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getContinueToken() {
      return this.continueToken;
   }

   public StorageVersionMigrationSpecFluent withContinueToken(String continueToken) {
      this.continueToken = continueToken;
      return this;
   }

   public boolean hasContinueToken() {
      return this.continueToken != null;
   }

   public GroupVersionResource buildResource() {
      return this.resource != null ? this.resource.build() : null;
   }

   public StorageVersionMigrationSpecFluent withResource(GroupVersionResource resource) {
      this._visitables.remove("resource");
      if (resource != null) {
         this.resource = new GroupVersionResourceBuilder(resource);
         this._visitables.get("resource").add(this.resource);
      } else {
         this.resource = null;
         this._visitables.get("resource").remove(this.resource);
      }

      return this;
   }

   public boolean hasResource() {
      return this.resource != null;
   }

   public StorageVersionMigrationSpecFluent withNewResource(String group, String resource, String version) {
      return this.withResource(new GroupVersionResource(group, resource, version));
   }

   public ResourceNested withNewResource() {
      return new ResourceNested((GroupVersionResource)null);
   }

   public ResourceNested withNewResourceLike(GroupVersionResource item) {
      return new ResourceNested(item);
   }

   public ResourceNested editResource() {
      return this.withNewResourceLike((GroupVersionResource)Optional.ofNullable(this.buildResource()).orElse((Object)null));
   }

   public ResourceNested editOrNewResource() {
      return this.withNewResourceLike((GroupVersionResource)Optional.ofNullable(this.buildResource()).orElse((new GroupVersionResourceBuilder()).build()));
   }

   public ResourceNested editOrNewResourceLike(GroupVersionResource item) {
      return this.withNewResourceLike((GroupVersionResource)Optional.ofNullable(this.buildResource()).orElse(item));
   }

   public StorageVersionMigrationSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public StorageVersionMigrationSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public StorageVersionMigrationSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public StorageVersionMigrationSpecFluent removeFromAdditionalProperties(Map map) {
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

   public StorageVersionMigrationSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            StorageVersionMigrationSpecFluent that = (StorageVersionMigrationSpecFluent)o;
            if (!Objects.equals(this.continueToken, that.continueToken)) {
               return false;
            } else if (!Objects.equals(this.resource, that.resource)) {
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
      return Objects.hash(new Object[]{this.continueToken, this.resource, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.continueToken != null) {
         sb.append("continueToken:");
         sb.append(this.continueToken + ",");
      }

      if (this.resource != null) {
         sb.append("resource:");
         sb.append(this.resource + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ResourceNested extends GroupVersionResourceFluent implements Nested {
      GroupVersionResourceBuilder builder;

      ResourceNested(GroupVersionResource item) {
         this.builder = new GroupVersionResourceBuilder(this, item);
      }

      public Object and() {
         return StorageVersionMigrationSpecFluent.this.withResource(this.builder.build());
      }

      public Object endResource() {
         return this.and();
      }
   }
}
