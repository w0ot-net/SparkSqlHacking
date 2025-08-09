package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class JSONSchemaPropsOrArrayFluent extends BaseFluent {
   private ArrayList jSONSchemas = new ArrayList();
   private JSONSchemaPropsBuilder schema;
   private Map additionalProperties;

   public JSONSchemaPropsOrArrayFluent() {
   }

   public JSONSchemaPropsOrArrayFluent(JSONSchemaPropsOrArray instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(JSONSchemaPropsOrArray instance) {
      instance = instance != null ? instance : new JSONSchemaPropsOrArray();
      if (instance != null) {
         this.withJSONSchemas(instance.getJSONSchemas());
         this.withSchema(instance.getSchema());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public JSONSchemaPropsOrArrayFluent addToJSONSchemas(int index, JSONSchemaProps item) {
      if (this.jSONSchemas == null) {
         this.jSONSchemas = new ArrayList();
      }

      JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
      if (index >= 0 && index < this.jSONSchemas.size()) {
         this._visitables.get("jSONSchemas").add(index, builder);
         this.jSONSchemas.add(index, builder);
      } else {
         this._visitables.get("jSONSchemas").add(builder);
         this.jSONSchemas.add(builder);
      }

      return this;
   }

   public JSONSchemaPropsOrArrayFluent setToJSONSchemas(int index, JSONSchemaProps item) {
      if (this.jSONSchemas == null) {
         this.jSONSchemas = new ArrayList();
      }

      JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
      if (index >= 0 && index < this.jSONSchemas.size()) {
         this._visitables.get("jSONSchemas").set(index, builder);
         this.jSONSchemas.set(index, builder);
      } else {
         this._visitables.get("jSONSchemas").add(builder);
         this.jSONSchemas.add(builder);
      }

      return this;
   }

   public JSONSchemaPropsOrArrayFluent addToJSONSchemas(JSONSchemaProps... items) {
      if (this.jSONSchemas == null) {
         this.jSONSchemas = new ArrayList();
      }

      for(JSONSchemaProps item : items) {
         JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
         this._visitables.get("jSONSchemas").add(builder);
         this.jSONSchemas.add(builder);
      }

      return this;
   }

   public JSONSchemaPropsOrArrayFluent addAllToJSONSchemas(Collection items) {
      if (this.jSONSchemas == null) {
         this.jSONSchemas = new ArrayList();
      }

      for(JSONSchemaProps item : items) {
         JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
         this._visitables.get("jSONSchemas").add(builder);
         this.jSONSchemas.add(builder);
      }

      return this;
   }

   public JSONSchemaPropsOrArrayFluent removeFromJSONSchemas(JSONSchemaProps... items) {
      if (this.jSONSchemas == null) {
         return this;
      } else {
         for(JSONSchemaProps item : items) {
            JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
            this._visitables.get("jSONSchemas").remove(builder);
            this.jSONSchemas.remove(builder);
         }

         return this;
      }
   }

   public JSONSchemaPropsOrArrayFluent removeAllFromJSONSchemas(Collection items) {
      if (this.jSONSchemas == null) {
         return this;
      } else {
         for(JSONSchemaProps item : items) {
            JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
            this._visitables.get("jSONSchemas").remove(builder);
            this.jSONSchemas.remove(builder);
         }

         return this;
      }
   }

   public JSONSchemaPropsOrArrayFluent removeMatchingFromJSONSchemas(Predicate predicate) {
      if (this.jSONSchemas == null) {
         return this;
      } else {
         Iterator<JSONSchemaPropsBuilder> each = this.jSONSchemas.iterator();
         List visitables = this._visitables.get("jSONSchemas");

         while(each.hasNext()) {
            JSONSchemaPropsBuilder builder = (JSONSchemaPropsBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildJSONSchemas() {
      return this.jSONSchemas != null ? build(this.jSONSchemas) : null;
   }

   public JSONSchemaProps buildJSONSchema(int index) {
      return ((JSONSchemaPropsBuilder)this.jSONSchemas.get(index)).build();
   }

   public JSONSchemaProps buildFirstJSONSchema() {
      return ((JSONSchemaPropsBuilder)this.jSONSchemas.get(0)).build();
   }

   public JSONSchemaProps buildLastJSONSchema() {
      return ((JSONSchemaPropsBuilder)this.jSONSchemas.get(this.jSONSchemas.size() - 1)).build();
   }

   public JSONSchemaProps buildMatchingJSONSchema(Predicate predicate) {
      for(JSONSchemaPropsBuilder item : this.jSONSchemas) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingJSONSchema(Predicate predicate) {
      for(JSONSchemaPropsBuilder item : this.jSONSchemas) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public JSONSchemaPropsOrArrayFluent withJSONSchemas(List jSONSchemas) {
      if (this.jSONSchemas != null) {
         this._visitables.get("jSONSchemas").clear();
      }

      if (jSONSchemas != null) {
         this.jSONSchemas = new ArrayList();

         for(JSONSchemaProps item : jSONSchemas) {
            this.addToJSONSchemas(item);
         }
      } else {
         this.jSONSchemas = null;
      }

      return this;
   }

   public JSONSchemaPropsOrArrayFluent withJSONSchemas(JSONSchemaProps... jSONSchemas) {
      if (this.jSONSchemas != null) {
         this.jSONSchemas.clear();
         this._visitables.remove("jSONSchemas");
      }

      if (jSONSchemas != null) {
         for(JSONSchemaProps item : jSONSchemas) {
            this.addToJSONSchemas(item);
         }
      }

      return this;
   }

   public boolean hasJSONSchemas() {
      return this.jSONSchemas != null && !this.jSONSchemas.isEmpty();
   }

   public JSONSchemasNested addNewJSONSchema() {
      return new JSONSchemasNested(-1, (JSONSchemaProps)null);
   }

   public JSONSchemasNested addNewJSONSchemaLike(JSONSchemaProps item) {
      return new JSONSchemasNested(-1, item);
   }

   public JSONSchemasNested setNewJSONSchemaLike(int index, JSONSchemaProps item) {
      return new JSONSchemasNested(index, item);
   }

   public JSONSchemasNested editJSONSchema(int index) {
      if (this.jSONSchemas.size() <= index) {
         throw new RuntimeException("Can't edit jSONSchemas. Index exceeds size.");
      } else {
         return this.setNewJSONSchemaLike(index, this.buildJSONSchema(index));
      }
   }

   public JSONSchemasNested editFirstJSONSchema() {
      if (this.jSONSchemas.size() == 0) {
         throw new RuntimeException("Can't edit first jSONSchemas. The list is empty.");
      } else {
         return this.setNewJSONSchemaLike(0, this.buildJSONSchema(0));
      }
   }

   public JSONSchemasNested editLastJSONSchema() {
      int index = this.jSONSchemas.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last jSONSchemas. The list is empty.");
      } else {
         return this.setNewJSONSchemaLike(index, this.buildJSONSchema(index));
      }
   }

   public JSONSchemasNested editMatchingJSONSchema(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.jSONSchemas.size(); ++i) {
         if (predicate.test((JSONSchemaPropsBuilder)this.jSONSchemas.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching jSONSchemas. No match found.");
      } else {
         return this.setNewJSONSchemaLike(index, this.buildJSONSchema(index));
      }
   }

   public JSONSchemaProps buildSchema() {
      return this.schema != null ? this.schema.build() : null;
   }

   public JSONSchemaPropsOrArrayFluent withSchema(JSONSchemaProps schema) {
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

   public JSONSchemaPropsOrArrayFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public JSONSchemaPropsOrArrayFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public JSONSchemaPropsOrArrayFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public JSONSchemaPropsOrArrayFluent removeFromAdditionalProperties(Map map) {
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

   public JSONSchemaPropsOrArrayFluent withAdditionalProperties(Map additionalProperties) {
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
            JSONSchemaPropsOrArrayFluent that = (JSONSchemaPropsOrArrayFluent)o;
            if (!Objects.equals(this.jSONSchemas, that.jSONSchemas)) {
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
      return Objects.hash(new Object[]{this.jSONSchemas, this.schema, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.jSONSchemas != null && !this.jSONSchemas.isEmpty()) {
         sb.append("jSONSchemas:");
         sb.append(this.jSONSchemas + ",");
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

   public class JSONSchemasNested extends JSONSchemaPropsFluent implements Nested {
      JSONSchemaPropsBuilder builder;
      int index;

      JSONSchemasNested(int index, JSONSchemaProps item) {
         this.index = index;
         this.builder = new JSONSchemaPropsBuilder(this, item);
      }

      public Object and() {
         return JSONSchemaPropsOrArrayFluent.this.setToJSONSchemas(this.index, this.builder.build());
      }

      public Object endJSONSchema() {
         return this.and();
      }
   }

   public class SchemaNested extends JSONSchemaPropsFluent implements Nested {
      JSONSchemaPropsBuilder builder;

      SchemaNested(JSONSchemaProps item) {
         this.builder = new JSONSchemaPropsBuilder(this, item);
      }

      public Object and() {
         return JSONSchemaPropsOrArrayFluent.this.withSchema(this.builder.build());
      }

      public Object endSchema() {
         return this.and();
      }
   }
}
