package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class JSONSchemaPropsOrBoolFluent extends BaseFluent {
   private Boolean allows;
   private JSONSchemaPropsBuilder schema;
   private Map additionalProperties;

   public JSONSchemaPropsOrBoolFluent() {
   }

   public JSONSchemaPropsOrBoolFluent(JSONSchemaPropsOrBool instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(JSONSchemaPropsOrBool instance) {
      instance = instance != null ? instance : new JSONSchemaPropsOrBool();
      if (instance != null) {
         this.withAllows(instance.getAllows());
         this.withSchema(instance.getSchema());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Boolean getAllows() {
      return this.allows;
   }

   public JSONSchemaPropsOrBoolFluent withAllows(Boolean allows) {
      this.allows = allows;
      return this;
   }

   public boolean hasAllows() {
      return this.allows != null;
   }

   public JSONSchemaProps buildSchema() {
      return this.schema != null ? this.schema.build() : null;
   }

   public JSONSchemaPropsOrBoolFluent withSchema(JSONSchemaProps schema) {
      this._visitables.remove("schema");
      if (schema != null) {
         this.schema = new JSONSchemaPropsBuilder(schema);
         this._visitables.get("schema").add(this.schema);
      } else {
         this.schema = null;
         this._visitables.get("schema").remove(this.schema);
      }

      return this;
   }

   public boolean hasSchema() {
      return this.schema != null;
   }

   public SchemaNested withNewSchema() {
      return new SchemaNested((JSONSchemaProps)null);
   }

   public SchemaNested withNewSchemaLike(JSONSchemaProps item) {
      return new SchemaNested(item);
   }

   public SchemaNested editSchema() {
      return this.withNewSchemaLike((JSONSchemaProps)Optional.ofNullable(this.buildSchema()).orElse((Object)null));
   }

   public SchemaNested editOrNewSchema() {
      return this.withNewSchemaLike((JSONSchemaProps)Optional.ofNullable(this.buildSchema()).orElse((new JSONSchemaPropsBuilder()).build()));
   }

   public SchemaNested editOrNewSchemaLike(JSONSchemaProps item) {
      return this.withNewSchemaLike((JSONSchemaProps)Optional.ofNullable(this.buildSchema()).orElse(item));
   }

   public JSONSchemaPropsOrBoolFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public JSONSchemaPropsOrBoolFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public JSONSchemaPropsOrBoolFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public JSONSchemaPropsOrBoolFluent removeFromAdditionalProperties(Map map) {
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

   public JSONSchemaPropsOrBoolFluent withAdditionalProperties(Map additionalProperties) {
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
            JSONSchemaPropsOrBoolFluent that = (JSONSchemaPropsOrBoolFluent)o;
            if (!Objects.equals(this.allows, that.allows)) {
               return false;
            } else if (!Objects.equals(this.schema, that.schema)) {
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
      return Objects.hash(new Object[]{this.allows, this.schema, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.allows != null) {
         sb.append("allows:");
         sb.append(this.allows + ",");
      }

      if (this.schema != null) {
         sb.append("schema:");
         sb.append(this.schema + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public JSONSchemaPropsOrBoolFluent withAllows() {
      return this.withAllows(true);
   }

   public class SchemaNested extends JSONSchemaPropsFluent implements Nested {
      JSONSchemaPropsBuilder builder;

      SchemaNested(JSONSchemaProps item) {
         this.builder = new JSONSchemaPropsBuilder(this, item);
      }

      public Object and() {
         return JSONSchemaPropsOrBoolFluent.this.withSchema(this.builder.build());
      }

      public Object endSchema() {
         return this.and();
      }
   }
}
