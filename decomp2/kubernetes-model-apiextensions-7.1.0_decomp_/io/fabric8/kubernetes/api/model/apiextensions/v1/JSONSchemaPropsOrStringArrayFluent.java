package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class JSONSchemaPropsOrStringArrayFluent extends BaseFluent {
   private List property = new ArrayList();
   private JSONSchemaPropsBuilder schema;
   private Map additionalProperties;

   public JSONSchemaPropsOrStringArrayFluent() {
   }

   public JSONSchemaPropsOrStringArrayFluent(JSONSchemaPropsOrStringArray instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(JSONSchemaPropsOrStringArray instance) {
      instance = instance != null ? instance : new JSONSchemaPropsOrStringArray();
      if (instance != null) {
         this.withProperty(instance.getProperty());
         this.withSchema(instance.getSchema());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public JSONSchemaPropsOrStringArrayFluent addToProperty(int index, String item) {
      if (this.property == null) {
         this.property = new ArrayList();
      }

      this.property.add(index, item);
      return this;
   }

   public JSONSchemaPropsOrStringArrayFluent setToProperty(int index, String item) {
      if (this.property == null) {
         this.property = new ArrayList();
      }

      this.property.set(index, item);
      return this;
   }

   public JSONSchemaPropsOrStringArrayFluent addToProperty(String... items) {
      if (this.property == null) {
         this.property = new ArrayList();
      }

      for(String item : items) {
         this.property.add(item);
      }

      return this;
   }

   public JSONSchemaPropsOrStringArrayFluent addAllToProperty(Collection items) {
      if (this.property == null) {
         this.property = new ArrayList();
      }

      for(String item : items) {
         this.property.add(item);
      }

      return this;
   }

   public JSONSchemaPropsOrStringArrayFluent removeFromProperty(String... items) {
      if (this.property == null) {
         return this;
      } else {
         for(String item : items) {
            this.property.remove(item);
         }

         return this;
      }
   }

   public JSONSchemaPropsOrStringArrayFluent removeAllFromProperty(Collection items) {
      if (this.property == null) {
         return this;
      } else {
         for(String item : items) {
            this.property.remove(item);
         }

         return this;
      }
   }

   public List getProperty() {
      return this.property;
   }

   public String getProperty(int index) {
      return (String)this.property.get(index);
   }

   public String getFirstProperty() {
      return (String)this.property.get(0);
   }

   public String getLastProperty() {
      return (String)this.property.get(this.property.size() - 1);
   }

   public String getMatchingProperty(Predicate predicate) {
      for(String item : this.property) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingProperty(Predicate predicate) {
      for(String item : this.property) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public JSONSchemaPropsOrStringArrayFluent withProperty(List property) {
      if (property != null) {
         this.property = new ArrayList();

         for(String item : property) {
            this.addToProperty(item);
         }
      } else {
         this.property = null;
      }

      return this;
   }

   public JSONSchemaPropsOrStringArrayFluent withProperty(String... property) {
      if (this.property != null) {
         this.property.clear();
         this._visitables.remove("property");
      }

      if (property != null) {
         for(String item : property) {
            this.addToProperty(item);
         }
      }

      return this;
   }

   public boolean hasProperty() {
      return this.property != null && !this.property.isEmpty();
   }

   public JSONSchemaProps buildSchema() {
      return this.schema != null ? this.schema.build() : null;
   }

   public JSONSchemaPropsOrStringArrayFluent withSchema(JSONSchemaProps schema) {
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

   public JSONSchemaPropsOrStringArrayFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public JSONSchemaPropsOrStringArrayFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public JSONSchemaPropsOrStringArrayFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public JSONSchemaPropsOrStringArrayFluent removeFromAdditionalProperties(Map map) {
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

   public JSONSchemaPropsOrStringArrayFluent withAdditionalProperties(Map additionalProperties) {
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
            JSONSchemaPropsOrStringArrayFluent that = (JSONSchemaPropsOrStringArrayFluent)o;
            if (!Objects.equals(this.property, that.property)) {
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
      return Objects.hash(new Object[]{this.property, this.schema, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.property != null && !this.property.isEmpty()) {
         sb.append("property:");
         sb.append(this.property + ",");
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

   public class SchemaNested extends JSONSchemaPropsFluent implements Nested {
      JSONSchemaPropsBuilder builder;

      SchemaNested(JSONSchemaProps item) {
         this.builder = new JSONSchemaPropsBuilder(this, item);
      }

      public Object and() {
         return JSONSchemaPropsOrStringArrayFluent.this.withSchema(this.builder.build());
      }

      public Object endSchema() {
         return this.and();
      }
   }
}
