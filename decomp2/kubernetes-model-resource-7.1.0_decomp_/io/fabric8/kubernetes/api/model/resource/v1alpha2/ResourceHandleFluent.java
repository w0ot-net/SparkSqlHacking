package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ResourceHandleFluent extends BaseFluent {
   private String data;
   private String driverName;
   private StructuredResourceHandleBuilder structuredData;
   private Map additionalProperties;

   public ResourceHandleFluent() {
   }

   public ResourceHandleFluent(ResourceHandle instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourceHandle instance) {
      instance = instance != null ? instance : new ResourceHandle();
      if (instance != null) {
         this.withData(instance.getData());
         this.withDriverName(instance.getDriverName());
         this.withStructuredData(instance.getStructuredData());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getData() {
      return this.data;
   }

   public ResourceHandleFluent withData(String data) {
      this.data = data;
      return this;
   }

   public boolean hasData() {
      return this.data != null;
   }

   public String getDriverName() {
      return this.driverName;
   }

   public ResourceHandleFluent withDriverName(String driverName) {
      this.driverName = driverName;
      return this;
   }

   public boolean hasDriverName() {
      return this.driverName != null;
   }

   public StructuredResourceHandle buildStructuredData() {
      return this.structuredData != null ? this.structuredData.build() : null;
   }

   public ResourceHandleFluent withStructuredData(StructuredResourceHandle structuredData) {
      this._visitables.remove("structuredData");
      if (structuredData != null) {
         this.structuredData = new StructuredResourceHandleBuilder(structuredData);
         this._visitables.get("structuredData").add(this.structuredData);
      } else {
         this.structuredData = null;
         this._visitables.get("structuredData").remove(this.structuredData);
      }

      return this;
   }

   public boolean hasStructuredData() {
      return this.structuredData != null;
   }

   public StructuredDataNested withNewStructuredData() {
      return new StructuredDataNested((StructuredResourceHandle)null);
   }

   public StructuredDataNested withNewStructuredDataLike(StructuredResourceHandle item) {
      return new StructuredDataNested(item);
   }

   public StructuredDataNested editStructuredData() {
      return this.withNewStructuredDataLike((StructuredResourceHandle)Optional.ofNullable(this.buildStructuredData()).orElse((Object)null));
   }

   public StructuredDataNested editOrNewStructuredData() {
      return this.withNewStructuredDataLike((StructuredResourceHandle)Optional.ofNullable(this.buildStructuredData()).orElse((new StructuredResourceHandleBuilder()).build()));
   }

   public StructuredDataNested editOrNewStructuredDataLike(StructuredResourceHandle item) {
      return this.withNewStructuredDataLike((StructuredResourceHandle)Optional.ofNullable(this.buildStructuredData()).orElse(item));
   }

   public ResourceHandleFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourceHandleFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourceHandleFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourceHandleFluent removeFromAdditionalProperties(Map map) {
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

   public ResourceHandleFluent withAdditionalProperties(Map additionalProperties) {
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
            ResourceHandleFluent that = (ResourceHandleFluent)o;
            if (!Objects.equals(this.data, that.data)) {
               return false;
            } else if (!Objects.equals(this.driverName, that.driverName)) {
               return false;
            } else if (!Objects.equals(this.structuredData, that.structuredData)) {
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
      return Objects.hash(new Object[]{this.data, this.driverName, this.structuredData, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.data != null) {
         sb.append("data:");
         sb.append(this.data + ",");
      }

      if (this.driverName != null) {
         sb.append("driverName:");
         sb.append(this.driverName + ",");
      }

      if (this.structuredData != null) {
         sb.append("structuredData:");
         sb.append(this.structuredData + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class StructuredDataNested extends StructuredResourceHandleFluent implements Nested {
      StructuredResourceHandleBuilder builder;

      StructuredDataNested(StructuredResourceHandle item) {
         this.builder = new StructuredResourceHandleBuilder(this, item);
      }

      public Object and() {
         return ResourceHandleFluent.this.withStructuredData(this.builder.build());
      }

      public Object endStructuredData() {
         return this.and();
      }
   }
}
