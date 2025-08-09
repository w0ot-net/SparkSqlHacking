package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ManagedFieldsEntryFluent extends BaseFluent {
   private String apiVersion;
   private String fieldsType;
   private FieldsV1Builder fieldsV1;
   private String manager;
   private String operation;
   private String subresource;
   private String time;
   private Map additionalProperties;

   public ManagedFieldsEntryFluent() {
   }

   public ManagedFieldsEntryFluent(ManagedFieldsEntry instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ManagedFieldsEntry instance) {
      instance = instance != null ? instance : new ManagedFieldsEntry();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withFieldsType(instance.getFieldsType());
         this.withFieldsV1(instance.getFieldsV1());
         this.withManager(instance.getManager());
         this.withOperation(instance.getOperation());
         this.withSubresource(instance.getSubresource());
         this.withTime(instance.getTime());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public ManagedFieldsEntryFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public String getFieldsType() {
      return this.fieldsType;
   }

   public ManagedFieldsEntryFluent withFieldsType(String fieldsType) {
      this.fieldsType = fieldsType;
      return this;
   }

   public boolean hasFieldsType() {
      return this.fieldsType != null;
   }

   public FieldsV1 buildFieldsV1() {
      return this.fieldsV1 != null ? this.fieldsV1.build() : null;
   }

   public ManagedFieldsEntryFluent withFieldsV1(FieldsV1 fieldsV1) {
      this._visitables.remove("fieldsV1");
      if (fieldsV1 != null) {
         this.fieldsV1 = new FieldsV1Builder(fieldsV1);
         this._visitables.get("fieldsV1").add(this.fieldsV1);
      } else {
         this.fieldsV1 = null;
         this._visitables.get("fieldsV1").remove(this.fieldsV1);
      }

      return this;
   }

   public boolean hasFieldsV1() {
      return this.fieldsV1 != null;
   }

   public FieldsV1Nested withNewFieldsV1() {
      return new FieldsV1Nested((FieldsV1)null);
   }

   public FieldsV1Nested withNewFieldsV1Like(FieldsV1 item) {
      return new FieldsV1Nested(item);
   }

   public FieldsV1Nested editFieldsV1() {
      return this.withNewFieldsV1Like((FieldsV1)Optional.ofNullable(this.buildFieldsV1()).orElse((Object)null));
   }

   public FieldsV1Nested editOrNewFieldsV1() {
      return this.withNewFieldsV1Like((FieldsV1)Optional.ofNullable(this.buildFieldsV1()).orElse((new FieldsV1Builder()).build()));
   }

   public FieldsV1Nested editOrNewFieldsV1Like(FieldsV1 item) {
      return this.withNewFieldsV1Like((FieldsV1)Optional.ofNullable(this.buildFieldsV1()).orElse(item));
   }

   public String getManager() {
      return this.manager;
   }

   public ManagedFieldsEntryFluent withManager(String manager) {
      this.manager = manager;
      return this;
   }

   public boolean hasManager() {
      return this.manager != null;
   }

   public String getOperation() {
      return this.operation;
   }

   public ManagedFieldsEntryFluent withOperation(String operation) {
      this.operation = operation;
      return this;
   }

   public boolean hasOperation() {
      return this.operation != null;
   }

   public String getSubresource() {
      return this.subresource;
   }

   public ManagedFieldsEntryFluent withSubresource(String subresource) {
      this.subresource = subresource;
      return this;
   }

   public boolean hasSubresource() {
      return this.subresource != null;
   }

   public String getTime() {
      return this.time;
   }

   public ManagedFieldsEntryFluent withTime(String time) {
      this.time = time;
      return this;
   }

   public boolean hasTime() {
      return this.time != null;
   }

   public ManagedFieldsEntryFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ManagedFieldsEntryFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ManagedFieldsEntryFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ManagedFieldsEntryFluent removeFromAdditionalProperties(Map map) {
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

   public ManagedFieldsEntryFluent withAdditionalProperties(Map additionalProperties) {
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
            ManagedFieldsEntryFluent that = (ManagedFieldsEntryFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.fieldsType, that.fieldsType)) {
               return false;
            } else if (!Objects.equals(this.fieldsV1, that.fieldsV1)) {
               return false;
            } else if (!Objects.equals(this.manager, that.manager)) {
               return false;
            } else if (!Objects.equals(this.operation, that.operation)) {
               return false;
            } else if (!Objects.equals(this.subresource, that.subresource)) {
               return false;
            } else if (!Objects.equals(this.time, that.time)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.fieldsType, this.fieldsV1, this.manager, this.operation, this.subresource, this.time, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.fieldsType != null) {
         sb.append("fieldsType:");
         sb.append(this.fieldsType + ",");
      }

      if (this.fieldsV1 != null) {
         sb.append("fieldsV1:");
         sb.append(this.fieldsV1 + ",");
      }

      if (this.manager != null) {
         sb.append("manager:");
         sb.append(this.manager + ",");
      }

      if (this.operation != null) {
         sb.append("operation:");
         sb.append(this.operation + ",");
      }

      if (this.subresource != null) {
         sb.append("subresource:");
         sb.append(this.subresource + ",");
      }

      if (this.time != null) {
         sb.append("time:");
         sb.append(this.time + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class FieldsV1Nested extends FieldsV1Fluent implements Nested {
      FieldsV1Builder builder;

      FieldsV1Nested(FieldsV1 item) {
         this.builder = new FieldsV1Builder(this, item);
      }

      public Object and() {
         return ManagedFieldsEntryFluent.this.withFieldsV1(this.builder.build());
      }

      public Object endFieldsV1() {
         return this.and();
      }
   }
}
