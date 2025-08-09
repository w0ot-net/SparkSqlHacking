package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class DownwardAPIVolumeFileFluent extends BaseFluent {
   private ObjectFieldSelectorBuilder fieldRef;
   private Integer mode;
   private String path;
   private ResourceFieldSelectorBuilder resourceFieldRef;
   private Map additionalProperties;

   public DownwardAPIVolumeFileFluent() {
   }

   public DownwardAPIVolumeFileFluent(DownwardAPIVolumeFile instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DownwardAPIVolumeFile instance) {
      instance = instance != null ? instance : new DownwardAPIVolumeFile();
      if (instance != null) {
         this.withFieldRef(instance.getFieldRef());
         this.withMode(instance.getMode());
         this.withPath(instance.getPath());
         this.withResourceFieldRef(instance.getResourceFieldRef());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ObjectFieldSelector buildFieldRef() {
      return this.fieldRef != null ? this.fieldRef.build() : null;
   }

   public DownwardAPIVolumeFileFluent withFieldRef(ObjectFieldSelector fieldRef) {
      this._visitables.remove("fieldRef");
      if (fieldRef != null) {
         this.fieldRef = new ObjectFieldSelectorBuilder(fieldRef);
         this._visitables.get("fieldRef").add(this.fieldRef);
      } else {
         this.fieldRef = null;
         this._visitables.get("fieldRef").remove(this.fieldRef);
      }

      return this;
   }

   public boolean hasFieldRef() {
      return this.fieldRef != null;
   }

   public DownwardAPIVolumeFileFluent withNewFieldRef(String apiVersion, String fieldPath) {
      return this.withFieldRef(new ObjectFieldSelector(apiVersion, fieldPath));
   }

   public FieldRefNested withNewFieldRef() {
      return new FieldRefNested((ObjectFieldSelector)null);
   }

   public FieldRefNested withNewFieldRefLike(ObjectFieldSelector item) {
      return new FieldRefNested(item);
   }

   public FieldRefNested editFieldRef() {
      return this.withNewFieldRefLike((ObjectFieldSelector)Optional.ofNullable(this.buildFieldRef()).orElse((Object)null));
   }

   public FieldRefNested editOrNewFieldRef() {
      return this.withNewFieldRefLike((ObjectFieldSelector)Optional.ofNullable(this.buildFieldRef()).orElse((new ObjectFieldSelectorBuilder()).build()));
   }

   public FieldRefNested editOrNewFieldRefLike(ObjectFieldSelector item) {
      return this.withNewFieldRefLike((ObjectFieldSelector)Optional.ofNullable(this.buildFieldRef()).orElse(item));
   }

   public Integer getMode() {
      return this.mode;
   }

   public DownwardAPIVolumeFileFluent withMode(Integer mode) {
      this.mode = mode;
      return this;
   }

   public boolean hasMode() {
      return this.mode != null;
   }

   public String getPath() {
      return this.path;
   }

   public DownwardAPIVolumeFileFluent withPath(String path) {
      this.path = path;
      return this;
   }

   public boolean hasPath() {
      return this.path != null;
   }

   public ResourceFieldSelector buildResourceFieldRef() {
      return this.resourceFieldRef != null ? this.resourceFieldRef.build() : null;
   }

   public DownwardAPIVolumeFileFluent withResourceFieldRef(ResourceFieldSelector resourceFieldRef) {
      this._visitables.remove("resourceFieldRef");
      if (resourceFieldRef != null) {
         this.resourceFieldRef = new ResourceFieldSelectorBuilder(resourceFieldRef);
         this._visitables.get("resourceFieldRef").add(this.resourceFieldRef);
      } else {
         this.resourceFieldRef = null;
         this._visitables.get("resourceFieldRef").remove(this.resourceFieldRef);
      }

      return this;
   }

   public boolean hasResourceFieldRef() {
      return this.resourceFieldRef != null;
   }

   public ResourceFieldRefNested withNewResourceFieldRef() {
      return new ResourceFieldRefNested((ResourceFieldSelector)null);
   }

   public ResourceFieldRefNested withNewResourceFieldRefLike(ResourceFieldSelector item) {
      return new ResourceFieldRefNested(item);
   }

   public ResourceFieldRefNested editResourceFieldRef() {
      return this.withNewResourceFieldRefLike((ResourceFieldSelector)Optional.ofNullable(this.buildResourceFieldRef()).orElse((Object)null));
   }

   public ResourceFieldRefNested editOrNewResourceFieldRef() {
      return this.withNewResourceFieldRefLike((ResourceFieldSelector)Optional.ofNullable(this.buildResourceFieldRef()).orElse((new ResourceFieldSelectorBuilder()).build()));
   }

   public ResourceFieldRefNested editOrNewResourceFieldRefLike(ResourceFieldSelector item) {
      return this.withNewResourceFieldRefLike((ResourceFieldSelector)Optional.ofNullable(this.buildResourceFieldRef()).orElse(item));
   }

   public DownwardAPIVolumeFileFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DownwardAPIVolumeFileFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DownwardAPIVolumeFileFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DownwardAPIVolumeFileFluent removeFromAdditionalProperties(Map map) {
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

   public DownwardAPIVolumeFileFluent withAdditionalProperties(Map additionalProperties) {
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
            DownwardAPIVolumeFileFluent that = (DownwardAPIVolumeFileFluent)o;
            if (!Objects.equals(this.fieldRef, that.fieldRef)) {
               return false;
            } else if (!Objects.equals(this.mode, that.mode)) {
               return false;
            } else if (!Objects.equals(this.path, that.path)) {
               return false;
            } else if (!Objects.equals(this.resourceFieldRef, that.resourceFieldRef)) {
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
      return Objects.hash(new Object[]{this.fieldRef, this.mode, this.path, this.resourceFieldRef, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.fieldRef != null) {
         sb.append("fieldRef:");
         sb.append(this.fieldRef + ",");
      }

      if (this.mode != null) {
         sb.append("mode:");
         sb.append(this.mode + ",");
      }

      if (this.path != null) {
         sb.append("path:");
         sb.append(this.path + ",");
      }

      if (this.resourceFieldRef != null) {
         sb.append("resourceFieldRef:");
         sb.append(this.resourceFieldRef + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class FieldRefNested extends ObjectFieldSelectorFluent implements Nested {
      ObjectFieldSelectorBuilder builder;

      FieldRefNested(ObjectFieldSelector item) {
         this.builder = new ObjectFieldSelectorBuilder(this, item);
      }

      public Object and() {
         return DownwardAPIVolumeFileFluent.this.withFieldRef(this.builder.build());
      }

      public Object endFieldRef() {
         return this.and();
      }
   }

   public class ResourceFieldRefNested extends ResourceFieldSelectorFluent implements Nested {
      ResourceFieldSelectorBuilder builder;

      ResourceFieldRefNested(ResourceFieldSelector item) {
         this.builder = new ResourceFieldSelectorBuilder(this, item);
      }

      public Object and() {
         return DownwardAPIVolumeFileFluent.this.withResourceFieldRef(this.builder.build());
      }

      public Object endResourceFieldRef() {
         return this.and();
      }
   }
}
