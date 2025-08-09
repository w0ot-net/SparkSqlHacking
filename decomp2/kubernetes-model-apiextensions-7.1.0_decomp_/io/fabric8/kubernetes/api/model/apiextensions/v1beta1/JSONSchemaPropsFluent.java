package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

import com.fasterxml.jackson.databind.JsonNode;
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

public class JSONSchemaPropsFluent extends BaseFluent {
   private String $ref;
   private String $schema;
   private JSONSchemaPropsOrBoolBuilder additionalItems;
   private JSONSchemaPropsOrBoolBuilder additionalProperties;
   private ArrayList allOf = new ArrayList();
   private ArrayList anyOf = new ArrayList();
   private JsonNode _default;
   private Map definitions;
   private Map dependencies;
   private String description;
   private List _enum = new ArrayList();
   private JsonNode example;
   private Boolean exclusiveMaximum;
   private Boolean exclusiveMinimum;
   private ExternalDocumentationBuilder externalDocs;
   private String format;
   private String id;
   private JSONSchemaPropsOrArrayBuilder items;
   private Long maxItems;
   private Long maxLength;
   private Long maxProperties;
   private Double maximum;
   private Long minItems;
   private Long minLength;
   private Long minProperties;
   private Double minimum;
   private Double multipleOf;
   private JSONSchemaPropsBuilder not;
   private Boolean nullable;
   private ArrayList oneOf = new ArrayList();
   private String pattern;
   private Map patternProperties;
   private Map properties;
   private List required = new ArrayList();
   private String title;
   private String type;
   private Boolean uniqueItems;
   private Boolean xKubernetesEmbeddedResource;
   private Boolean xKubernetesIntOrString;
   private List xKubernetesListMapKeys = new ArrayList();
   private String xKubernetesListType;
   private String xKubernetesMapType;
   private Boolean xKubernetesPreserveUnknownFields;
   private ArrayList xKubernetesValidations = new ArrayList();

   public JSONSchemaPropsFluent() {
   }

   public JSONSchemaPropsFluent(JSONSchemaProps instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(JSONSchemaProps instance) {
      instance = instance != null ? instance : new JSONSchemaProps();
      if (instance != null) {
         this.withRef(instance.get$ref());
         this.withSchema(instance.get$schema());
         this.withAdditionalItems(instance.getAdditionalItems());
         this.withAdditionalProperties(instance.getAdditionalProperties());
         this.withAllOf(instance.getAllOf());
         this.withAnyOf(instance.getAnyOf());
         this.withDefault(instance.getDefault());
         this.withDefinitions(instance.getDefinitions());
         this.withDependencies(instance.getDependencies());
         this.withDescription(instance.getDescription());
         this.withEnum(instance.getEnum());
         this.withExample(instance.getExample());
         this.withExclusiveMaximum(instance.getExclusiveMaximum());
         this.withExclusiveMinimum(instance.getExclusiveMinimum());
         this.withExternalDocs(instance.getExternalDocs());
         this.withFormat(instance.getFormat());
         this.withId(instance.getId());
         this.withItems(instance.getItems());
         this.withMaxItems(instance.getMaxItems());
         this.withMaxLength(instance.getMaxLength());
         this.withMaxProperties(instance.getMaxProperties());
         this.withMaximum(instance.getMaximum());
         this.withMinItems(instance.getMinItems());
         this.withMinLength(instance.getMinLength());
         this.withMinProperties(instance.getMinProperties());
         this.withMinimum(instance.getMinimum());
         this.withMultipleOf(instance.getMultipleOf());
         this.withNot(instance.getNot());
         this.withNullable(instance.getNullable());
         this.withOneOf(instance.getOneOf());
         this.withPattern(instance.getPattern());
         this.withPatternProperties(instance.getPatternProperties());
         this.withProperties(instance.getProperties());
         this.withRequired(instance.getRequired());
         this.withTitle(instance.getTitle());
         this.withType(instance.getType());
         this.withUniqueItems(instance.getUniqueItems());
         this.withXKubernetesEmbeddedResource(instance.getXKubernetesEmbeddedResource());
         this.withXKubernetesIntOrString(instance.getXKubernetesIntOrString());
         this.withXKubernetesListMapKeys(instance.getXKubernetesListMapKeys());
         this.withXKubernetesListType(instance.getXKubernetesListType());
         this.withXKubernetesMapType(instance.getXKubernetesMapType());
         this.withXKubernetesPreserveUnknownFields(instance.getXKubernetesPreserveUnknownFields());
         this.withXKubernetesValidations(instance.getXKubernetesValidations());
      }

   }

   public String getRef() {
      return this.$ref;
   }

   public JSONSchemaPropsFluent withRef(String $ref) {
      this.$ref = $ref;
      return this;
   }

   public boolean hasRef() {
      return this.$ref != null;
   }

   public String getSchema() {
      return this.$schema;
   }

   public JSONSchemaPropsFluent withSchema(String $schema) {
      this.$schema = $schema;
      return this;
   }

   public boolean hasSchema() {
      return this.$schema != null;
   }

   public JSONSchemaPropsOrBool buildAdditionalItems() {
      return this.additionalItems != null ? this.additionalItems.build() : null;
   }

   public JSONSchemaPropsFluent withAdditionalItems(JSONSchemaPropsOrBool additionalItems) {
      this._visitables.remove("additionalItems");
      if (additionalItems != null) {
         this.additionalItems = new JSONSchemaPropsOrBoolBuilder(additionalItems);
         this._visitables.get("additionalItems").add(this.additionalItems);
      } else {
         this.additionalItems = null;
         this._visitables.get("additionalItems").remove(this.additionalItems);
      }

      return this;
   }

   public boolean hasAdditionalItems() {
      return this.additionalItems != null;
   }

   public AdditionalItemsNested withNewAdditionalItems() {
      return new AdditionalItemsNested((JSONSchemaPropsOrBool)null);
   }

   public AdditionalItemsNested withNewAdditionalItemsLike(JSONSchemaPropsOrBool item) {
      return new AdditionalItemsNested(item);
   }

   public AdditionalItemsNested editAdditionalItems() {
      return this.withNewAdditionalItemsLike((JSONSchemaPropsOrBool)Optional.ofNullable(this.buildAdditionalItems()).orElse((Object)null));
   }

   public AdditionalItemsNested editOrNewAdditionalItems() {
      return this.withNewAdditionalItemsLike((JSONSchemaPropsOrBool)Optional.ofNullable(this.buildAdditionalItems()).orElse((new JSONSchemaPropsOrBoolBuilder()).build()));
   }

   public AdditionalItemsNested editOrNewAdditionalItemsLike(JSONSchemaPropsOrBool item) {
      return this.withNewAdditionalItemsLike((JSONSchemaPropsOrBool)Optional.ofNullable(this.buildAdditionalItems()).orElse(item));
   }

   public JSONSchemaPropsOrBool buildAdditionalProperties() {
      return this.additionalProperties != null ? this.additionalProperties.build() : null;
   }

   public JSONSchemaPropsFluent withAdditionalProperties(JSONSchemaPropsOrBool additionalProperties) {
      this._visitables.remove("additionalProperties");
      if (additionalProperties != null) {
         this.additionalProperties = new JSONSchemaPropsOrBoolBuilder(additionalProperties);
         this._visitables.get("additionalProperties").add(this.additionalProperties);
      } else {
         this.additionalProperties = null;
         this._visitables.get("additionalProperties").remove(this.additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public AdditionalPropertiesNested withNewAdditionalProperties() {
      return new AdditionalPropertiesNested((JSONSchemaPropsOrBool)null);
   }

   public AdditionalPropertiesNested withNewAdditionalPropertiesLike(JSONSchemaPropsOrBool item) {
      return new AdditionalPropertiesNested(item);
   }

   public AdditionalPropertiesNested editAdditionalProperties() {
      return this.withNewAdditionalPropertiesLike((JSONSchemaPropsOrBool)Optional.ofNullable(this.buildAdditionalProperties()).orElse((Object)null));
   }

   public AdditionalPropertiesNested editOrNewAdditionalProperties() {
      return this.withNewAdditionalPropertiesLike((JSONSchemaPropsOrBool)Optional.ofNullable(this.buildAdditionalProperties()).orElse((new JSONSchemaPropsOrBoolBuilder()).build()));
   }

   public AdditionalPropertiesNested editOrNewAdditionalPropertiesLike(JSONSchemaPropsOrBool item) {
      return this.withNewAdditionalPropertiesLike((JSONSchemaPropsOrBool)Optional.ofNullable(this.buildAdditionalProperties()).orElse(item));
   }

   public JSONSchemaPropsFluent addToAllOf(int index, JSONSchemaProps item) {
      if (this.allOf == null) {
         this.allOf = new ArrayList();
      }

      JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
      if (index >= 0 && index < this.allOf.size()) {
         this._visitables.get("allOf").add(index, builder);
         this.allOf.add(index, builder);
      } else {
         this._visitables.get("allOf").add(builder);
         this.allOf.add(builder);
      }

      return this;
   }

   public JSONSchemaPropsFluent setToAllOf(int index, JSONSchemaProps item) {
      if (this.allOf == null) {
         this.allOf = new ArrayList();
      }

      JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
      if (index >= 0 && index < this.allOf.size()) {
         this._visitables.get("allOf").set(index, builder);
         this.allOf.set(index, builder);
      } else {
         this._visitables.get("allOf").add(builder);
         this.allOf.add(builder);
      }

      return this;
   }

   public JSONSchemaPropsFluent addToAllOf(JSONSchemaProps... items) {
      if (this.allOf == null) {
         this.allOf = new ArrayList();
      }

      for(JSONSchemaProps item : items) {
         JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
         this._visitables.get("allOf").add(builder);
         this.allOf.add(builder);
      }

      return this;
   }

   public JSONSchemaPropsFluent addAllToAllOf(Collection items) {
      if (this.allOf == null) {
         this.allOf = new ArrayList();
      }

      for(JSONSchemaProps item : items) {
         JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
         this._visitables.get("allOf").add(builder);
         this.allOf.add(builder);
      }

      return this;
   }

   public JSONSchemaPropsFluent removeFromAllOf(JSONSchemaProps... items) {
      if (this.allOf == null) {
         return this;
      } else {
         for(JSONSchemaProps item : items) {
            JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
            this._visitables.get("allOf").remove(builder);
            this.allOf.remove(builder);
         }

         return this;
      }
   }

   public JSONSchemaPropsFluent removeAllFromAllOf(Collection items) {
      if (this.allOf == null) {
         return this;
      } else {
         for(JSONSchemaProps item : items) {
            JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
            this._visitables.get("allOf").remove(builder);
            this.allOf.remove(builder);
         }

         return this;
      }
   }

   public JSONSchemaPropsFluent removeMatchingFromAllOf(Predicate predicate) {
      if (this.allOf == null) {
         return this;
      } else {
         Iterator<JSONSchemaPropsBuilder> each = this.allOf.iterator();
         List visitables = this._visitables.get("allOf");

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

   public List buildAllOf() {
      return this.allOf != null ? build(this.allOf) : null;
   }

   public JSONSchemaProps buildAllOf(int index) {
      return ((JSONSchemaPropsBuilder)this.allOf.get(index)).build();
   }

   public JSONSchemaProps buildFirstAllOf() {
      return ((JSONSchemaPropsBuilder)this.allOf.get(0)).build();
   }

   public JSONSchemaProps buildLastAllOf() {
      return ((JSONSchemaPropsBuilder)this.allOf.get(this.allOf.size() - 1)).build();
   }

   public JSONSchemaProps buildMatchingAllOf(Predicate predicate) {
      for(JSONSchemaPropsBuilder item : this.allOf) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingAllOf(Predicate predicate) {
      for(JSONSchemaPropsBuilder item : this.allOf) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public JSONSchemaPropsFluent withAllOf(List allOf) {
      if (this.allOf != null) {
         this._visitables.get("allOf").clear();
      }

      if (allOf != null) {
         this.allOf = new ArrayList();

         for(JSONSchemaProps item : allOf) {
            this.addToAllOf(item);
         }
      } else {
         this.allOf = null;
      }

      return this;
   }

   public JSONSchemaPropsFluent withAllOf(JSONSchemaProps... allOf) {
      if (this.allOf != null) {
         this.allOf.clear();
         this._visitables.remove("allOf");
      }

      if (allOf != null) {
         for(JSONSchemaProps item : allOf) {
            this.addToAllOf(item);
         }
      }

      return this;
   }

   public boolean hasAllOf() {
      return this.allOf != null && !this.allOf.isEmpty();
   }

   public AllOfNested addNewAllOf() {
      return new AllOfNested(-1, (JSONSchemaProps)null);
   }

   public AllOfNested addNewAllOfLike(JSONSchemaProps item) {
      return new AllOfNested(-1, item);
   }

   public AllOfNested setNewAllOfLike(int index, JSONSchemaProps item) {
      return new AllOfNested(index, item);
   }

   public AllOfNested editAllOf(int index) {
      if (this.allOf.size() <= index) {
         throw new RuntimeException("Can't edit allOf. Index exceeds size.");
      } else {
         return this.setNewAllOfLike(index, this.buildAllOf(index));
      }
   }

   public AllOfNested editFirstAllOf() {
      if (this.allOf.size() == 0) {
         throw new RuntimeException("Can't edit first allOf. The list is empty.");
      } else {
         return this.setNewAllOfLike(0, this.buildAllOf(0));
      }
   }

   public AllOfNested editLastAllOf() {
      int index = this.allOf.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last allOf. The list is empty.");
      } else {
         return this.setNewAllOfLike(index, this.buildAllOf(index));
      }
   }

   public AllOfNested editMatchingAllOf(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.allOf.size(); ++i) {
         if (predicate.test((JSONSchemaPropsBuilder)this.allOf.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching allOf. No match found.");
      } else {
         return this.setNewAllOfLike(index, this.buildAllOf(index));
      }
   }

   public JSONSchemaPropsFluent addToAnyOf(int index, JSONSchemaProps item) {
      if (this.anyOf == null) {
         this.anyOf = new ArrayList();
      }

      JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
      if (index >= 0 && index < this.anyOf.size()) {
         this._visitables.get("anyOf").add(index, builder);
         this.anyOf.add(index, builder);
      } else {
         this._visitables.get("anyOf").add(builder);
         this.anyOf.add(builder);
      }

      return this;
   }

   public JSONSchemaPropsFluent setToAnyOf(int index, JSONSchemaProps item) {
      if (this.anyOf == null) {
         this.anyOf = new ArrayList();
      }

      JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
      if (index >= 0 && index < this.anyOf.size()) {
         this._visitables.get("anyOf").set(index, builder);
         this.anyOf.set(index, builder);
      } else {
         this._visitables.get("anyOf").add(builder);
         this.anyOf.add(builder);
      }

      return this;
   }

   public JSONSchemaPropsFluent addToAnyOf(JSONSchemaProps... items) {
      if (this.anyOf == null) {
         this.anyOf = new ArrayList();
      }

      for(JSONSchemaProps item : items) {
         JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
         this._visitables.get("anyOf").add(builder);
         this.anyOf.add(builder);
      }

      return this;
   }

   public JSONSchemaPropsFluent addAllToAnyOf(Collection items) {
      if (this.anyOf == null) {
         this.anyOf = new ArrayList();
      }

      for(JSONSchemaProps item : items) {
         JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
         this._visitables.get("anyOf").add(builder);
         this.anyOf.add(builder);
      }

      return this;
   }

   public JSONSchemaPropsFluent removeFromAnyOf(JSONSchemaProps... items) {
      if (this.anyOf == null) {
         return this;
      } else {
         for(JSONSchemaProps item : items) {
            JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
            this._visitables.get("anyOf").remove(builder);
            this.anyOf.remove(builder);
         }

         return this;
      }
   }

   public JSONSchemaPropsFluent removeAllFromAnyOf(Collection items) {
      if (this.anyOf == null) {
         return this;
      } else {
         for(JSONSchemaProps item : items) {
            JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
            this._visitables.get("anyOf").remove(builder);
            this.anyOf.remove(builder);
         }

         return this;
      }
   }

   public JSONSchemaPropsFluent removeMatchingFromAnyOf(Predicate predicate) {
      if (this.anyOf == null) {
         return this;
      } else {
         Iterator<JSONSchemaPropsBuilder> each = this.anyOf.iterator();
         List visitables = this._visitables.get("anyOf");

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

   public List buildAnyOf() {
      return this.anyOf != null ? build(this.anyOf) : null;
   }

   public JSONSchemaProps buildAnyOf(int index) {
      return ((JSONSchemaPropsBuilder)this.anyOf.get(index)).build();
   }

   public JSONSchemaProps buildFirstAnyOf() {
      return ((JSONSchemaPropsBuilder)this.anyOf.get(0)).build();
   }

   public JSONSchemaProps buildLastAnyOf() {
      return ((JSONSchemaPropsBuilder)this.anyOf.get(this.anyOf.size() - 1)).build();
   }

   public JSONSchemaProps buildMatchingAnyOf(Predicate predicate) {
      for(JSONSchemaPropsBuilder item : this.anyOf) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingAnyOf(Predicate predicate) {
      for(JSONSchemaPropsBuilder item : this.anyOf) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public JSONSchemaPropsFluent withAnyOf(List anyOf) {
      if (this.anyOf != null) {
         this._visitables.get("anyOf").clear();
      }

      if (anyOf != null) {
         this.anyOf = new ArrayList();

         for(JSONSchemaProps item : anyOf) {
            this.addToAnyOf(item);
         }
      } else {
         this.anyOf = null;
      }

      return this;
   }

   public JSONSchemaPropsFluent withAnyOf(JSONSchemaProps... anyOf) {
      if (this.anyOf != null) {
         this.anyOf.clear();
         this._visitables.remove("anyOf");
      }

      if (anyOf != null) {
         for(JSONSchemaProps item : anyOf) {
            this.addToAnyOf(item);
         }
      }

      return this;
   }

   public boolean hasAnyOf() {
      return this.anyOf != null && !this.anyOf.isEmpty();
   }

   public AnyOfNested addNewAnyOf() {
      return new AnyOfNested(-1, (JSONSchemaProps)null);
   }

   public AnyOfNested addNewAnyOfLike(JSONSchemaProps item) {
      return new AnyOfNested(-1, item);
   }

   public AnyOfNested setNewAnyOfLike(int index, JSONSchemaProps item) {
      return new AnyOfNested(index, item);
   }

   public AnyOfNested editAnyOf(int index) {
      if (this.anyOf.size() <= index) {
         throw new RuntimeException("Can't edit anyOf. Index exceeds size.");
      } else {
         return this.setNewAnyOfLike(index, this.buildAnyOf(index));
      }
   }

   public AnyOfNested editFirstAnyOf() {
      if (this.anyOf.size() == 0) {
         throw new RuntimeException("Can't edit first anyOf. The list is empty.");
      } else {
         return this.setNewAnyOfLike(0, this.buildAnyOf(0));
      }
   }

   public AnyOfNested editLastAnyOf() {
      int index = this.anyOf.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last anyOf. The list is empty.");
      } else {
         return this.setNewAnyOfLike(index, this.buildAnyOf(index));
      }
   }

   public AnyOfNested editMatchingAnyOf(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.anyOf.size(); ++i) {
         if (predicate.test((JSONSchemaPropsBuilder)this.anyOf.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching anyOf. No match found.");
      } else {
         return this.setNewAnyOfLike(index, this.buildAnyOf(index));
      }
   }

   public JsonNode getDefault() {
      return this._default;
   }

   public JSONSchemaPropsFluent withDefault(JsonNode _default) {
      this._default = _default;
      return this;
   }

   public boolean hasDefault() {
      return this._default != null;
   }

   public JSONSchemaPropsFluent addToDefinitions(String key, JSONSchemaProps value) {
      if (this.definitions == null && key != null && value != null) {
         this.definitions = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.definitions.put(key, value);
      }

      return this;
   }

   public JSONSchemaPropsFluent addToDefinitions(Map map) {
      if (this.definitions == null && map != null) {
         this.definitions = new LinkedHashMap();
      }

      if (map != null) {
         this.definitions.putAll(map);
      }

      return this;
   }

   public JSONSchemaPropsFluent removeFromDefinitions(String key) {
      if (this.definitions == null) {
         return this;
      } else {
         if (key != null && this.definitions != null) {
            this.definitions.remove(key);
         }

         return this;
      }
   }

   public JSONSchemaPropsFluent removeFromDefinitions(Map map) {
      if (this.definitions == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.definitions != null) {
                  this.definitions.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getDefinitions() {
      return this.definitions;
   }

   public JSONSchemaPropsFluent withDefinitions(Map definitions) {
      if (definitions == null) {
         this.definitions = null;
      } else {
         this.definitions = new LinkedHashMap(definitions);
      }

      return this;
   }

   public boolean hasDefinitions() {
      return this.definitions != null;
   }

   public JSONSchemaPropsFluent addToDependencies(String key, JSONSchemaPropsOrStringArray value) {
      if (this.dependencies == null && key != null && value != null) {
         this.dependencies = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.dependencies.put(key, value);
      }

      return this;
   }

   public JSONSchemaPropsFluent addToDependencies(Map map) {
      if (this.dependencies == null && map != null) {
         this.dependencies = new LinkedHashMap();
      }

      if (map != null) {
         this.dependencies.putAll(map);
      }

      return this;
   }

   public JSONSchemaPropsFluent removeFromDependencies(String key) {
      if (this.dependencies == null) {
         return this;
      } else {
         if (key != null && this.dependencies != null) {
            this.dependencies.remove(key);
         }

         return this;
      }
   }

   public JSONSchemaPropsFluent removeFromDependencies(Map map) {
      if (this.dependencies == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.dependencies != null) {
                  this.dependencies.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getDependencies() {
      return this.dependencies;
   }

   public JSONSchemaPropsFluent withDependencies(Map dependencies) {
      if (dependencies == null) {
         this.dependencies = null;
      } else {
         this.dependencies = new LinkedHashMap(dependencies);
      }

      return this;
   }

   public boolean hasDependencies() {
      return this.dependencies != null;
   }

   public String getDescription() {
      return this.description;
   }

   public JSONSchemaPropsFluent withDescription(String description) {
      this.description = description;
      return this;
   }

   public boolean hasDescription() {
      return this.description != null;
   }

   public JSONSchemaPropsFluent addToEnum(int index, JsonNode item) {
      if (this._enum == null) {
         this._enum = new ArrayList();
      }

      this._enum.add(index, item);
      return this;
   }

   public JSONSchemaPropsFluent setToEnum(int index, JsonNode item) {
      if (this._enum == null) {
         this._enum = new ArrayList();
      }

      this._enum.set(index, item);
      return this;
   }

   public JSONSchemaPropsFluent addToEnum(JsonNode... items) {
      if (this._enum == null) {
         this._enum = new ArrayList();
      }

      for(JsonNode item : items) {
         this._enum.add(item);
      }

      return this;
   }

   public JSONSchemaPropsFluent addAllToEnum(Collection items) {
      if (this._enum == null) {
         this._enum = new ArrayList();
      }

      for(JsonNode item : items) {
         this._enum.add(item);
      }

      return this;
   }

   public JSONSchemaPropsFluent removeFromEnum(JsonNode... items) {
      if (this._enum == null) {
         return this;
      } else {
         for(JsonNode item : items) {
            this._enum.remove(item);
         }

         return this;
      }
   }

   public JSONSchemaPropsFluent removeAllFromEnum(Collection items) {
      if (this._enum == null) {
         return this;
      } else {
         for(JsonNode item : items) {
            this._enum.remove(item);
         }

         return this;
      }
   }

   public List getEnum() {
      return this._enum;
   }

   public JsonNode getEnum(int index) {
      return (JsonNode)this._enum.get(index);
   }

   public JsonNode getFirstEnum() {
      return (JsonNode)this._enum.get(0);
   }

   public JsonNode getLastEnum() {
      return (JsonNode)this._enum.get(this._enum.size() - 1);
   }

   public JsonNode getMatchingEnum(Predicate predicate) {
      for(JsonNode item : this._enum) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingEnum(Predicate predicate) {
      for(JsonNode item : this._enum) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public JSONSchemaPropsFluent withEnum(List _enum) {
      if (_enum != null) {
         this._enum = new ArrayList();

         for(JsonNode item : _enum) {
            this.addToEnum(item);
         }
      } else {
         this._enum = null;
      }

      return this;
   }

   public JSONSchemaPropsFluent withEnum(JsonNode... _enum) {
      if (this._enum != null) {
         this._enum.clear();
         this._visitables.remove("_enum");
      }

      if (_enum != null) {
         for(JsonNode item : _enum) {
            this.addToEnum(item);
         }
      }

      return this;
   }

   public boolean hasEnum() {
      return this._enum != null && !this._enum.isEmpty();
   }

   public JsonNode getExample() {
      return this.example;
   }

   public JSONSchemaPropsFluent withExample(JsonNode example) {
      this.example = example;
      return this;
   }

   public boolean hasExample() {
      return this.example != null;
   }

   public Boolean getExclusiveMaximum() {
      return this.exclusiveMaximum;
   }

   public JSONSchemaPropsFluent withExclusiveMaximum(Boolean exclusiveMaximum) {
      this.exclusiveMaximum = exclusiveMaximum;
      return this;
   }

   public boolean hasExclusiveMaximum() {
      return this.exclusiveMaximum != null;
   }

   public Boolean getExclusiveMinimum() {
      return this.exclusiveMinimum;
   }

   public JSONSchemaPropsFluent withExclusiveMinimum(Boolean exclusiveMinimum) {
      this.exclusiveMinimum = exclusiveMinimum;
      return this;
   }

   public boolean hasExclusiveMinimum() {
      return this.exclusiveMinimum != null;
   }

   public ExternalDocumentation buildExternalDocs() {
      return this.externalDocs != null ? this.externalDocs.build() : null;
   }

   public JSONSchemaPropsFluent withExternalDocs(ExternalDocumentation externalDocs) {
      this._visitables.remove("externalDocs");
      if (externalDocs != null) {
         this.externalDocs = new ExternalDocumentationBuilder(externalDocs);
         this._visitables.get("externalDocs").add(this.externalDocs);
      } else {
         this.externalDocs = null;
         this._visitables.get("externalDocs").remove(this.externalDocs);
      }

      return this;
   }

   public boolean hasExternalDocs() {
      return this.externalDocs != null;
   }

   public JSONSchemaPropsFluent withNewExternalDocs(String description, String url) {
      return this.withExternalDocs(new ExternalDocumentation(description, url));
   }

   public ExternalDocsNested withNewExternalDocs() {
      return new ExternalDocsNested((ExternalDocumentation)null);
   }

   public ExternalDocsNested withNewExternalDocsLike(ExternalDocumentation item) {
      return new ExternalDocsNested(item);
   }

   public ExternalDocsNested editExternalDocs() {
      return this.withNewExternalDocsLike((ExternalDocumentation)Optional.ofNullable(this.buildExternalDocs()).orElse((Object)null));
   }

   public ExternalDocsNested editOrNewExternalDocs() {
      return this.withNewExternalDocsLike((ExternalDocumentation)Optional.ofNullable(this.buildExternalDocs()).orElse((new ExternalDocumentationBuilder()).build()));
   }

   public ExternalDocsNested editOrNewExternalDocsLike(ExternalDocumentation item) {
      return this.withNewExternalDocsLike((ExternalDocumentation)Optional.ofNullable(this.buildExternalDocs()).orElse(item));
   }

   public String getFormat() {
      return this.format;
   }

   public JSONSchemaPropsFluent withFormat(String format) {
      this.format = format;
      return this;
   }

   public boolean hasFormat() {
      return this.format != null;
   }

   public String getId() {
      return this.id;
   }

   public JSONSchemaPropsFluent withId(String id) {
      this.id = id;
      return this;
   }

   public boolean hasId() {
      return this.id != null;
   }

   public JSONSchemaPropsOrArray buildItems() {
      return this.items != null ? this.items.build() : null;
   }

   public JSONSchemaPropsFluent withItems(JSONSchemaPropsOrArray items) {
      this._visitables.remove("items");
      if (items != null) {
         this.items = new JSONSchemaPropsOrArrayBuilder(items);
         this._visitables.get("items").add(this.items);
      } else {
         this.items = null;
         this._visitables.get("items").remove(this.items);
      }

      return this;
   }

   public boolean hasItems() {
      return this.items != null;
   }

   public ItemsNested withNewItems() {
      return new ItemsNested((JSONSchemaPropsOrArray)null);
   }

   public ItemsNested withNewItemsLike(JSONSchemaPropsOrArray item) {
      return new ItemsNested(item);
   }

   public ItemsNested editItems() {
      return this.withNewItemsLike((JSONSchemaPropsOrArray)Optional.ofNullable(this.buildItems()).orElse((Object)null));
   }

   public ItemsNested editOrNewItems() {
      return this.withNewItemsLike((JSONSchemaPropsOrArray)Optional.ofNullable(this.buildItems()).orElse((new JSONSchemaPropsOrArrayBuilder()).build()));
   }

   public ItemsNested editOrNewItemsLike(JSONSchemaPropsOrArray item) {
      return this.withNewItemsLike((JSONSchemaPropsOrArray)Optional.ofNullable(this.buildItems()).orElse(item));
   }

   public Long getMaxItems() {
      return this.maxItems;
   }

   public JSONSchemaPropsFluent withMaxItems(Long maxItems) {
      this.maxItems = maxItems;
      return this;
   }

   public boolean hasMaxItems() {
      return this.maxItems != null;
   }

   public Long getMaxLength() {
      return this.maxLength;
   }

   public JSONSchemaPropsFluent withMaxLength(Long maxLength) {
      this.maxLength = maxLength;
      return this;
   }

   public boolean hasMaxLength() {
      return this.maxLength != null;
   }

   public Long getMaxProperties() {
      return this.maxProperties;
   }

   public JSONSchemaPropsFluent withMaxProperties(Long maxProperties) {
      this.maxProperties = maxProperties;
      return this;
   }

   public boolean hasMaxProperties() {
      return this.maxProperties != null;
   }

   public Double getMaximum() {
      return this.maximum;
   }

   public JSONSchemaPropsFluent withMaximum(Double maximum) {
      this.maximum = maximum;
      return this;
   }

   public boolean hasMaximum() {
      return this.maximum != null;
   }

   public Long getMinItems() {
      return this.minItems;
   }

   public JSONSchemaPropsFluent withMinItems(Long minItems) {
      this.minItems = minItems;
      return this;
   }

   public boolean hasMinItems() {
      return this.minItems != null;
   }

   public Long getMinLength() {
      return this.minLength;
   }

   public JSONSchemaPropsFluent withMinLength(Long minLength) {
      this.minLength = minLength;
      return this;
   }

   public boolean hasMinLength() {
      return this.minLength != null;
   }

   public Long getMinProperties() {
      return this.minProperties;
   }

   public JSONSchemaPropsFluent withMinProperties(Long minProperties) {
      this.minProperties = minProperties;
      return this;
   }

   public boolean hasMinProperties() {
      return this.minProperties != null;
   }

   public Double getMinimum() {
      return this.minimum;
   }

   public JSONSchemaPropsFluent withMinimum(Double minimum) {
      this.minimum = minimum;
      return this;
   }

   public boolean hasMinimum() {
      return this.minimum != null;
   }

   public Double getMultipleOf() {
      return this.multipleOf;
   }

   public JSONSchemaPropsFluent withMultipleOf(Double multipleOf) {
      this.multipleOf = multipleOf;
      return this;
   }

   public boolean hasMultipleOf() {
      return this.multipleOf != null;
   }

   public JSONSchemaProps buildNot() {
      return this.not != null ? this.not.build() : null;
   }

   public JSONSchemaPropsFluent withNot(JSONSchemaProps not) {
      this._visitables.remove("not");
      if (not != null) {
         this.not = new JSONSchemaPropsBuilder(not);
         this._visitables.get("not").add(this.not);
      } else {
         this.not = null;
         this._visitables.get("not").remove(this.not);
      }

      return this;
   }

   public boolean hasNot() {
      return this.not != null;
   }

   public NotNested withNewNot() {
      return new NotNested((JSONSchemaProps)null);
   }

   public NotNested withNewNotLike(JSONSchemaProps item) {
      return new NotNested(item);
   }

   public NotNested editNot() {
      return this.withNewNotLike((JSONSchemaProps)Optional.ofNullable(this.buildNot()).orElse((Object)null));
   }

   public NotNested editOrNewNot() {
      return this.withNewNotLike((JSONSchemaProps)Optional.ofNullable(this.buildNot()).orElse((new JSONSchemaPropsBuilder()).build()));
   }

   public NotNested editOrNewNotLike(JSONSchemaProps item) {
      return this.withNewNotLike((JSONSchemaProps)Optional.ofNullable(this.buildNot()).orElse(item));
   }

   public Boolean getNullable() {
      return this.nullable;
   }

   public JSONSchemaPropsFluent withNullable(Boolean nullable) {
      this.nullable = nullable;
      return this;
   }

   public boolean hasNullable() {
      return this.nullable != null;
   }

   public JSONSchemaPropsFluent addToOneOf(int index, JSONSchemaProps item) {
      if (this.oneOf == null) {
         this.oneOf = new ArrayList();
      }

      JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
      if (index >= 0 && index < this.oneOf.size()) {
         this._visitables.get("oneOf").add(index, builder);
         this.oneOf.add(index, builder);
      } else {
         this._visitables.get("oneOf").add(builder);
         this.oneOf.add(builder);
      }

      return this;
   }

   public JSONSchemaPropsFluent setToOneOf(int index, JSONSchemaProps item) {
      if (this.oneOf == null) {
         this.oneOf = new ArrayList();
      }

      JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
      if (index >= 0 && index < this.oneOf.size()) {
         this._visitables.get("oneOf").set(index, builder);
         this.oneOf.set(index, builder);
      } else {
         this._visitables.get("oneOf").add(builder);
         this.oneOf.add(builder);
      }

      return this;
   }

   public JSONSchemaPropsFluent addToOneOf(JSONSchemaProps... items) {
      if (this.oneOf == null) {
         this.oneOf = new ArrayList();
      }

      for(JSONSchemaProps item : items) {
         JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
         this._visitables.get("oneOf").add(builder);
         this.oneOf.add(builder);
      }

      return this;
   }

   public JSONSchemaPropsFluent addAllToOneOf(Collection items) {
      if (this.oneOf == null) {
         this.oneOf = new ArrayList();
      }

      for(JSONSchemaProps item : items) {
         JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
         this._visitables.get("oneOf").add(builder);
         this.oneOf.add(builder);
      }

      return this;
   }

   public JSONSchemaPropsFluent removeFromOneOf(JSONSchemaProps... items) {
      if (this.oneOf == null) {
         return this;
      } else {
         for(JSONSchemaProps item : items) {
            JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
            this._visitables.get("oneOf").remove(builder);
            this.oneOf.remove(builder);
         }

         return this;
      }
   }

   public JSONSchemaPropsFluent removeAllFromOneOf(Collection items) {
      if (this.oneOf == null) {
         return this;
      } else {
         for(JSONSchemaProps item : items) {
            JSONSchemaPropsBuilder builder = new JSONSchemaPropsBuilder(item);
            this._visitables.get("oneOf").remove(builder);
            this.oneOf.remove(builder);
         }

         return this;
      }
   }

   public JSONSchemaPropsFluent removeMatchingFromOneOf(Predicate predicate) {
      if (this.oneOf == null) {
         return this;
      } else {
         Iterator<JSONSchemaPropsBuilder> each = this.oneOf.iterator();
         List visitables = this._visitables.get("oneOf");

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

   public List buildOneOf() {
      return this.oneOf != null ? build(this.oneOf) : null;
   }

   public JSONSchemaProps buildOneOf(int index) {
      return ((JSONSchemaPropsBuilder)this.oneOf.get(index)).build();
   }

   public JSONSchemaProps buildFirstOneOf() {
      return ((JSONSchemaPropsBuilder)this.oneOf.get(0)).build();
   }

   public JSONSchemaProps buildLastOneOf() {
      return ((JSONSchemaPropsBuilder)this.oneOf.get(this.oneOf.size() - 1)).build();
   }

   public JSONSchemaProps buildMatchingOneOf(Predicate predicate) {
      for(JSONSchemaPropsBuilder item : this.oneOf) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingOneOf(Predicate predicate) {
      for(JSONSchemaPropsBuilder item : this.oneOf) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public JSONSchemaPropsFluent withOneOf(List oneOf) {
      if (this.oneOf != null) {
         this._visitables.get("oneOf").clear();
      }

      if (oneOf != null) {
         this.oneOf = new ArrayList();

         for(JSONSchemaProps item : oneOf) {
            this.addToOneOf(item);
         }
      } else {
         this.oneOf = null;
      }

      return this;
   }

   public JSONSchemaPropsFluent withOneOf(JSONSchemaProps... oneOf) {
      if (this.oneOf != null) {
         this.oneOf.clear();
         this._visitables.remove("oneOf");
      }

      if (oneOf != null) {
         for(JSONSchemaProps item : oneOf) {
            this.addToOneOf(item);
         }
      }

      return this;
   }

   public boolean hasOneOf() {
      return this.oneOf != null && !this.oneOf.isEmpty();
   }

   public OneOfNested addNewOneOf() {
      return new OneOfNested(-1, (JSONSchemaProps)null);
   }

   public OneOfNested addNewOneOfLike(JSONSchemaProps item) {
      return new OneOfNested(-1, item);
   }

   public OneOfNested setNewOneOfLike(int index, JSONSchemaProps item) {
      return new OneOfNested(index, item);
   }

   public OneOfNested editOneOf(int index) {
      if (this.oneOf.size() <= index) {
         throw new RuntimeException("Can't edit oneOf. Index exceeds size.");
      } else {
         return this.setNewOneOfLike(index, this.buildOneOf(index));
      }
   }

   public OneOfNested editFirstOneOf() {
      if (this.oneOf.size() == 0) {
         throw new RuntimeException("Can't edit first oneOf. The list is empty.");
      } else {
         return this.setNewOneOfLike(0, this.buildOneOf(0));
      }
   }

   public OneOfNested editLastOneOf() {
      int index = this.oneOf.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last oneOf. The list is empty.");
      } else {
         return this.setNewOneOfLike(index, this.buildOneOf(index));
      }
   }

   public OneOfNested editMatchingOneOf(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.oneOf.size(); ++i) {
         if (predicate.test((JSONSchemaPropsBuilder)this.oneOf.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching oneOf. No match found.");
      } else {
         return this.setNewOneOfLike(index, this.buildOneOf(index));
      }
   }

   public String getPattern() {
      return this.pattern;
   }

   public JSONSchemaPropsFluent withPattern(String pattern) {
      this.pattern = pattern;
      return this;
   }

   public boolean hasPattern() {
      return this.pattern != null;
   }

   public JSONSchemaPropsFluent addToPatternProperties(String key, JSONSchemaProps value) {
      if (this.patternProperties == null && key != null && value != null) {
         this.patternProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.patternProperties.put(key, value);
      }

      return this;
   }

   public JSONSchemaPropsFluent addToPatternProperties(Map map) {
      if (this.patternProperties == null && map != null) {
         this.patternProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.patternProperties.putAll(map);
      }

      return this;
   }

   public JSONSchemaPropsFluent removeFromPatternProperties(String key) {
      if (this.patternProperties == null) {
         return this;
      } else {
         if (key != null && this.patternProperties != null) {
            this.patternProperties.remove(key);
         }

         return this;
      }
   }

   public JSONSchemaPropsFluent removeFromPatternProperties(Map map) {
      if (this.patternProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.patternProperties != null) {
                  this.patternProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getPatternProperties() {
      return this.patternProperties;
   }

   public JSONSchemaPropsFluent withPatternProperties(Map patternProperties) {
      if (patternProperties == null) {
         this.patternProperties = null;
      } else {
         this.patternProperties = new LinkedHashMap(patternProperties);
      }

      return this;
   }

   public boolean hasPatternProperties() {
      return this.patternProperties != null;
   }

   public JSONSchemaPropsFluent addToProperties(String key, JSONSchemaProps value) {
      if (this.properties == null && key != null && value != null) {
         this.properties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.properties.put(key, value);
      }

      return this;
   }

   public JSONSchemaPropsFluent addToProperties(Map map) {
      if (this.properties == null && map != null) {
         this.properties = new LinkedHashMap();
      }

      if (map != null) {
         this.properties.putAll(map);
      }

      return this;
   }

   public JSONSchemaPropsFluent removeFromProperties(String key) {
      if (this.properties == null) {
         return this;
      } else {
         if (key != null && this.properties != null) {
            this.properties.remove(key);
         }

         return this;
      }
   }

   public JSONSchemaPropsFluent removeFromProperties(Map map) {
      if (this.properties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.properties != null) {
                  this.properties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getProperties() {
      return this.properties;
   }

   public JSONSchemaPropsFluent withProperties(Map properties) {
      if (properties == null) {
         this.properties = null;
      } else {
         this.properties = new LinkedHashMap(properties);
      }

      return this;
   }

   public boolean hasProperties() {
      return this.properties != null;
   }

   public JSONSchemaPropsFluent addToRequired(int index, String item) {
      if (this.required == null) {
         this.required = new ArrayList();
      }

      this.required.add(index, item);
      return this;
   }

   public JSONSchemaPropsFluent setToRequired(int index, String item) {
      if (this.required == null) {
         this.required = new ArrayList();
      }

      this.required.set(index, item);
      return this;
   }

   public JSONSchemaPropsFluent addToRequired(String... items) {
      if (this.required == null) {
         this.required = new ArrayList();
      }

      for(String item : items) {
         this.required.add(item);
      }

      return this;
   }

   public JSONSchemaPropsFluent addAllToRequired(Collection items) {
      if (this.required == null) {
         this.required = new ArrayList();
      }

      for(String item : items) {
         this.required.add(item);
      }

      return this;
   }

   public JSONSchemaPropsFluent removeFromRequired(String... items) {
      if (this.required == null) {
         return this;
      } else {
         for(String item : items) {
            this.required.remove(item);
         }

         return this;
      }
   }

   public JSONSchemaPropsFluent removeAllFromRequired(Collection items) {
      if (this.required == null) {
         return this;
      } else {
         for(String item : items) {
            this.required.remove(item);
         }

         return this;
      }
   }

   public List getRequired() {
      return this.required;
   }

   public String getRequired(int index) {
      return (String)this.required.get(index);
   }

   public String getFirstRequired() {
      return (String)this.required.get(0);
   }

   public String getLastRequired() {
      return (String)this.required.get(this.required.size() - 1);
   }

   public String getMatchingRequired(Predicate predicate) {
      for(String item : this.required) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingRequired(Predicate predicate) {
      for(String item : this.required) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public JSONSchemaPropsFluent withRequired(List required) {
      if (required != null) {
         this.required = new ArrayList();

         for(String item : required) {
            this.addToRequired(item);
         }
      } else {
         this.required = null;
      }

      return this;
   }

   public JSONSchemaPropsFluent withRequired(String... required) {
      if (this.required != null) {
         this.required.clear();
         this._visitables.remove("required");
      }

      if (required != null) {
         for(String item : required) {
            this.addToRequired(item);
         }
      }

      return this;
   }

   public boolean hasRequired() {
      return this.required != null && !this.required.isEmpty();
   }

   public String getTitle() {
      return this.title;
   }

   public JSONSchemaPropsFluent withTitle(String title) {
      this.title = title;
      return this;
   }

   public boolean hasTitle() {
      return this.title != null;
   }

   public String getType() {
      return this.type;
   }

   public JSONSchemaPropsFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public Boolean getUniqueItems() {
      return this.uniqueItems;
   }

   public JSONSchemaPropsFluent withUniqueItems(Boolean uniqueItems) {
      this.uniqueItems = uniqueItems;
      return this;
   }

   public boolean hasUniqueItems() {
      return this.uniqueItems != null;
   }

   public Boolean getXKubernetesEmbeddedResource() {
      return this.xKubernetesEmbeddedResource;
   }

   public JSONSchemaPropsFluent withXKubernetesEmbeddedResource(Boolean xKubernetesEmbeddedResource) {
      this.xKubernetesEmbeddedResource = xKubernetesEmbeddedResource;
      return this;
   }

   public boolean hasXKubernetesEmbeddedResource() {
      return this.xKubernetesEmbeddedResource != null;
   }

   public Boolean getXKubernetesIntOrString() {
      return this.xKubernetesIntOrString;
   }

   public JSONSchemaPropsFluent withXKubernetesIntOrString(Boolean xKubernetesIntOrString) {
      this.xKubernetesIntOrString = xKubernetesIntOrString;
      return this;
   }

   public boolean hasXKubernetesIntOrString() {
      return this.xKubernetesIntOrString != null;
   }

   public JSONSchemaPropsFluent addToXKubernetesListMapKeys(int index, String item) {
      if (this.xKubernetesListMapKeys == null) {
         this.xKubernetesListMapKeys = new ArrayList();
      }

      this.xKubernetesListMapKeys.add(index, item);
      return this;
   }

   public JSONSchemaPropsFluent setToXKubernetesListMapKeys(int index, String item) {
      if (this.xKubernetesListMapKeys == null) {
         this.xKubernetesListMapKeys = new ArrayList();
      }

      this.xKubernetesListMapKeys.set(index, item);
      return this;
   }

   public JSONSchemaPropsFluent addToXKubernetesListMapKeys(String... items) {
      if (this.xKubernetesListMapKeys == null) {
         this.xKubernetesListMapKeys = new ArrayList();
      }

      for(String item : items) {
         this.xKubernetesListMapKeys.add(item);
      }

      return this;
   }

   public JSONSchemaPropsFluent addAllToXKubernetesListMapKeys(Collection items) {
      if (this.xKubernetesListMapKeys == null) {
         this.xKubernetesListMapKeys = new ArrayList();
      }

      for(String item : items) {
         this.xKubernetesListMapKeys.add(item);
      }

      return this;
   }

   public JSONSchemaPropsFluent removeFromXKubernetesListMapKeys(String... items) {
      if (this.xKubernetesListMapKeys == null) {
         return this;
      } else {
         for(String item : items) {
            this.xKubernetesListMapKeys.remove(item);
         }

         return this;
      }
   }

   public JSONSchemaPropsFluent removeAllFromXKubernetesListMapKeys(Collection items) {
      if (this.xKubernetesListMapKeys == null) {
         return this;
      } else {
         for(String item : items) {
            this.xKubernetesListMapKeys.remove(item);
         }

         return this;
      }
   }

   public List getXKubernetesListMapKeys() {
      return this.xKubernetesListMapKeys;
   }

   public String getXKubernetesListMapKey(int index) {
      return (String)this.xKubernetesListMapKeys.get(index);
   }

   public String getFirstXKubernetesListMapKey() {
      return (String)this.xKubernetesListMapKeys.get(0);
   }

   public String getLastXKubernetesListMapKey() {
      return (String)this.xKubernetesListMapKeys.get(this.xKubernetesListMapKeys.size() - 1);
   }

   public String getMatchingXKubernetesListMapKey(Predicate predicate) {
      for(String item : this.xKubernetesListMapKeys) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingXKubernetesListMapKey(Predicate predicate) {
      for(String item : this.xKubernetesListMapKeys) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public JSONSchemaPropsFluent withXKubernetesListMapKeys(List xKubernetesListMapKeys) {
      if (xKubernetesListMapKeys != null) {
         this.xKubernetesListMapKeys = new ArrayList();

         for(String item : xKubernetesListMapKeys) {
            this.addToXKubernetesListMapKeys(item);
         }
      } else {
         this.xKubernetesListMapKeys = null;
      }

      return this;
   }

   public JSONSchemaPropsFluent withXKubernetesListMapKeys(String... xKubernetesListMapKeys) {
      if (this.xKubernetesListMapKeys != null) {
         this.xKubernetesListMapKeys.clear();
         this._visitables.remove("xKubernetesListMapKeys");
      }

      if (xKubernetesListMapKeys != null) {
         for(String item : xKubernetesListMapKeys) {
            this.addToXKubernetesListMapKeys(item);
         }
      }

      return this;
   }

   public boolean hasXKubernetesListMapKeys() {
      return this.xKubernetesListMapKeys != null && !this.xKubernetesListMapKeys.isEmpty();
   }

   public String getXKubernetesListType() {
      return this.xKubernetesListType;
   }

   public JSONSchemaPropsFluent withXKubernetesListType(String xKubernetesListType) {
      this.xKubernetesListType = xKubernetesListType;
      return this;
   }

   public boolean hasXKubernetesListType() {
      return this.xKubernetesListType != null;
   }

   public String getXKubernetesMapType() {
      return this.xKubernetesMapType;
   }

   public JSONSchemaPropsFluent withXKubernetesMapType(String xKubernetesMapType) {
      this.xKubernetesMapType = xKubernetesMapType;
      return this;
   }

   public boolean hasXKubernetesMapType() {
      return this.xKubernetesMapType != null;
   }

   public Boolean getXKubernetesPreserveUnknownFields() {
      return this.xKubernetesPreserveUnknownFields;
   }

   public JSONSchemaPropsFluent withXKubernetesPreserveUnknownFields(Boolean xKubernetesPreserveUnknownFields) {
      this.xKubernetesPreserveUnknownFields = xKubernetesPreserveUnknownFields;
      return this;
   }

   public boolean hasXKubernetesPreserveUnknownFields() {
      return this.xKubernetesPreserveUnknownFields != null;
   }

   public JSONSchemaPropsFluent addToXKubernetesValidations(int index, ValidationRule item) {
      if (this.xKubernetesValidations == null) {
         this.xKubernetesValidations = new ArrayList();
      }

      ValidationRuleBuilder builder = new ValidationRuleBuilder(item);
      if (index >= 0 && index < this.xKubernetesValidations.size()) {
         this._visitables.get("xKubernetesValidations").add(index, builder);
         this.xKubernetesValidations.add(index, builder);
      } else {
         this._visitables.get("xKubernetesValidations").add(builder);
         this.xKubernetesValidations.add(builder);
      }

      return this;
   }

   public JSONSchemaPropsFluent setToXKubernetesValidations(int index, ValidationRule item) {
      if (this.xKubernetesValidations == null) {
         this.xKubernetesValidations = new ArrayList();
      }

      ValidationRuleBuilder builder = new ValidationRuleBuilder(item);
      if (index >= 0 && index < this.xKubernetesValidations.size()) {
         this._visitables.get("xKubernetesValidations").set(index, builder);
         this.xKubernetesValidations.set(index, builder);
      } else {
         this._visitables.get("xKubernetesValidations").add(builder);
         this.xKubernetesValidations.add(builder);
      }

      return this;
   }

   public JSONSchemaPropsFluent addToXKubernetesValidations(ValidationRule... items) {
      if (this.xKubernetesValidations == null) {
         this.xKubernetesValidations = new ArrayList();
      }

      for(ValidationRule item : items) {
         ValidationRuleBuilder builder = new ValidationRuleBuilder(item);
         this._visitables.get("xKubernetesValidations").add(builder);
         this.xKubernetesValidations.add(builder);
      }

      return this;
   }

   public JSONSchemaPropsFluent addAllToXKubernetesValidations(Collection items) {
      if (this.xKubernetesValidations == null) {
         this.xKubernetesValidations = new ArrayList();
      }

      for(ValidationRule item : items) {
         ValidationRuleBuilder builder = new ValidationRuleBuilder(item);
         this._visitables.get("xKubernetesValidations").add(builder);
         this.xKubernetesValidations.add(builder);
      }

      return this;
   }

   public JSONSchemaPropsFluent removeFromXKubernetesValidations(ValidationRule... items) {
      if (this.xKubernetesValidations == null) {
         return this;
      } else {
         for(ValidationRule item : items) {
            ValidationRuleBuilder builder = new ValidationRuleBuilder(item);
            this._visitables.get("xKubernetesValidations").remove(builder);
            this.xKubernetesValidations.remove(builder);
         }

         return this;
      }
   }

   public JSONSchemaPropsFluent removeAllFromXKubernetesValidations(Collection items) {
      if (this.xKubernetesValidations == null) {
         return this;
      } else {
         for(ValidationRule item : items) {
            ValidationRuleBuilder builder = new ValidationRuleBuilder(item);
            this._visitables.get("xKubernetesValidations").remove(builder);
            this.xKubernetesValidations.remove(builder);
         }

         return this;
      }
   }

   public JSONSchemaPropsFluent removeMatchingFromXKubernetesValidations(Predicate predicate) {
      if (this.xKubernetesValidations == null) {
         return this;
      } else {
         Iterator<ValidationRuleBuilder> each = this.xKubernetesValidations.iterator();
         List visitables = this._visitables.get("xKubernetesValidations");

         while(each.hasNext()) {
            ValidationRuleBuilder builder = (ValidationRuleBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildXKubernetesValidations() {
      return this.xKubernetesValidations != null ? build(this.xKubernetesValidations) : null;
   }

   public ValidationRule buildXKubernetesValidation(int index) {
      return ((ValidationRuleBuilder)this.xKubernetesValidations.get(index)).build();
   }

   public ValidationRule buildFirstXKubernetesValidation() {
      return ((ValidationRuleBuilder)this.xKubernetesValidations.get(0)).build();
   }

   public ValidationRule buildLastXKubernetesValidation() {
      return ((ValidationRuleBuilder)this.xKubernetesValidations.get(this.xKubernetesValidations.size() - 1)).build();
   }

   public ValidationRule buildMatchingXKubernetesValidation(Predicate predicate) {
      for(ValidationRuleBuilder item : this.xKubernetesValidations) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingXKubernetesValidation(Predicate predicate) {
      for(ValidationRuleBuilder item : this.xKubernetesValidations) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public JSONSchemaPropsFluent withXKubernetesValidations(List xKubernetesValidations) {
      if (this.xKubernetesValidations != null) {
         this._visitables.get("xKubernetesValidations").clear();
      }

      if (xKubernetesValidations != null) {
         this.xKubernetesValidations = new ArrayList();

         for(ValidationRule item : xKubernetesValidations) {
            this.addToXKubernetesValidations(item);
         }
      } else {
         this.xKubernetesValidations = null;
      }

      return this;
   }

   public JSONSchemaPropsFluent withXKubernetesValidations(ValidationRule... xKubernetesValidations) {
      if (this.xKubernetesValidations != null) {
         this.xKubernetesValidations.clear();
         this._visitables.remove("xKubernetesValidations");
      }

      if (xKubernetesValidations != null) {
         for(ValidationRule item : xKubernetesValidations) {
            this.addToXKubernetesValidations(item);
         }
      }

      return this;
   }

   public boolean hasXKubernetesValidations() {
      return this.xKubernetesValidations != null && !this.xKubernetesValidations.isEmpty();
   }

   public XKubernetesValidationsNested addNewXKubernetesValidation() {
      return new XKubernetesValidationsNested(-1, (ValidationRule)null);
   }

   public XKubernetesValidationsNested addNewXKubernetesValidationLike(ValidationRule item) {
      return new XKubernetesValidationsNested(-1, item);
   }

   public XKubernetesValidationsNested setNewXKubernetesValidationLike(int index, ValidationRule item) {
      return new XKubernetesValidationsNested(index, item);
   }

   public XKubernetesValidationsNested editXKubernetesValidation(int index) {
      if (this.xKubernetesValidations.size() <= index) {
         throw new RuntimeException("Can't edit xKubernetesValidations. Index exceeds size.");
      } else {
         return this.setNewXKubernetesValidationLike(index, this.buildXKubernetesValidation(index));
      }
   }

   public XKubernetesValidationsNested editFirstXKubernetesValidation() {
      if (this.xKubernetesValidations.size() == 0) {
         throw new RuntimeException("Can't edit first xKubernetesValidations. The list is empty.");
      } else {
         return this.setNewXKubernetesValidationLike(0, this.buildXKubernetesValidation(0));
      }
   }

   public XKubernetesValidationsNested editLastXKubernetesValidation() {
      int index = this.xKubernetesValidations.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last xKubernetesValidations. The list is empty.");
      } else {
         return this.setNewXKubernetesValidationLike(index, this.buildXKubernetesValidation(index));
      }
   }

   public XKubernetesValidationsNested editMatchingXKubernetesValidation(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.xKubernetesValidations.size(); ++i) {
         if (predicate.test((ValidationRuleBuilder)this.xKubernetesValidations.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching xKubernetesValidations. No match found.");
      } else {
         return this.setNewXKubernetesValidationLike(index, this.buildXKubernetesValidation(index));
      }
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            JSONSchemaPropsFluent that = (JSONSchemaPropsFluent)o;
            if (!Objects.equals(this.$ref, that.$ref)) {
               return false;
            } else if (!Objects.equals(this.$schema, that.$schema)) {
               return false;
            } else if (!Objects.equals(this.additionalItems, that.additionalItems)) {
               return false;
            } else if (!Objects.equals(this.additionalProperties, that.additionalProperties)) {
               return false;
            } else if (!Objects.equals(this.allOf, that.allOf)) {
               return false;
            } else if (!Objects.equals(this.anyOf, that.anyOf)) {
               return false;
            } else if (!Objects.equals(this._default, that._default)) {
               return false;
            } else if (!Objects.equals(this.definitions, that.definitions)) {
               return false;
            } else if (!Objects.equals(this.dependencies, that.dependencies)) {
               return false;
            } else if (!Objects.equals(this.description, that.description)) {
               return false;
            } else if (!Objects.equals(this._enum, that._enum)) {
               return false;
            } else if (!Objects.equals(this.example, that.example)) {
               return false;
            } else if (!Objects.equals(this.exclusiveMaximum, that.exclusiveMaximum)) {
               return false;
            } else if (!Objects.equals(this.exclusiveMinimum, that.exclusiveMinimum)) {
               return false;
            } else if (!Objects.equals(this.externalDocs, that.externalDocs)) {
               return false;
            } else if (!Objects.equals(this.format, that.format)) {
               return false;
            } else if (!Objects.equals(this.id, that.id)) {
               return false;
            } else if (!Objects.equals(this.items, that.items)) {
               return false;
            } else if (!Objects.equals(this.maxItems, that.maxItems)) {
               return false;
            } else if (!Objects.equals(this.maxLength, that.maxLength)) {
               return false;
            } else if (!Objects.equals(this.maxProperties, that.maxProperties)) {
               return false;
            } else if (!Objects.equals(this.maximum, that.maximum)) {
               return false;
            } else if (!Objects.equals(this.minItems, that.minItems)) {
               return false;
            } else if (!Objects.equals(this.minLength, that.minLength)) {
               return false;
            } else if (!Objects.equals(this.minProperties, that.minProperties)) {
               return false;
            } else if (!Objects.equals(this.minimum, that.minimum)) {
               return false;
            } else if (!Objects.equals(this.multipleOf, that.multipleOf)) {
               return false;
            } else if (!Objects.equals(this.not, that.not)) {
               return false;
            } else if (!Objects.equals(this.nullable, that.nullable)) {
               return false;
            } else if (!Objects.equals(this.oneOf, that.oneOf)) {
               return false;
            } else if (!Objects.equals(this.pattern, that.pattern)) {
               return false;
            } else if (!Objects.equals(this.patternProperties, that.patternProperties)) {
               return false;
            } else if (!Objects.equals(this.properties, that.properties)) {
               return false;
            } else if (!Objects.equals(this.required, that.required)) {
               return false;
            } else if (!Objects.equals(this.title, that.title)) {
               return false;
            } else if (!Objects.equals(this.type, that.type)) {
               return false;
            } else if (!Objects.equals(this.uniqueItems, that.uniqueItems)) {
               return false;
            } else if (!Objects.equals(this.xKubernetesEmbeddedResource, that.xKubernetesEmbeddedResource)) {
               return false;
            } else if (!Objects.equals(this.xKubernetesIntOrString, that.xKubernetesIntOrString)) {
               return false;
            } else if (!Objects.equals(this.xKubernetesListMapKeys, that.xKubernetesListMapKeys)) {
               return false;
            } else if (!Objects.equals(this.xKubernetesListType, that.xKubernetesListType)) {
               return false;
            } else if (!Objects.equals(this.xKubernetesMapType, that.xKubernetesMapType)) {
               return false;
            } else if (!Objects.equals(this.xKubernetesPreserveUnknownFields, that.xKubernetesPreserveUnknownFields)) {
               return false;
            } else {
               return Objects.equals(this.xKubernetesValidations, that.xKubernetesValidations);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.$ref, this.$schema, this.additionalItems, this.additionalProperties, this.allOf, this.anyOf, this._default, this.definitions, this.dependencies, this.description, this._enum, this.example, this.exclusiveMaximum, this.exclusiveMinimum, this.externalDocs, this.format, this.id, this.items, this.maxItems, this.maxLength, this.maxProperties, this.maximum, this.minItems, this.minLength, this.minProperties, this.minimum, this.multipleOf, this.not, this.nullable, this.oneOf, this.pattern, this.patternProperties, this.properties, this.required, this.title, this.type, this.uniqueItems, this.xKubernetesEmbeddedResource, this.xKubernetesIntOrString, this.xKubernetesListMapKeys, this.xKubernetesListType, this.xKubernetesMapType, this.xKubernetesPreserveUnknownFields, this.xKubernetesValidations, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.$ref != null) {
         sb.append("$ref:");
         sb.append(this.$ref + ",");
      }

      if (this.$schema != null) {
         sb.append("$schema:");
         sb.append(this.$schema + ",");
      }

      if (this.additionalItems != null) {
         sb.append("additionalItems:");
         sb.append(this.additionalItems + ",");
      }

      if (this.additionalProperties != null) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties + ",");
      }

      if (this.allOf != null && !this.allOf.isEmpty()) {
         sb.append("allOf:");
         sb.append(this.allOf + ",");
      }

      if (this.anyOf != null && !this.anyOf.isEmpty()) {
         sb.append("anyOf:");
         sb.append(this.anyOf + ",");
      }

      if (this._default != null) {
         sb.append("_default:");
         sb.append(this._default + ",");
      }

      if (this.definitions != null && !this.definitions.isEmpty()) {
         sb.append("definitions:");
         sb.append(this.definitions + ",");
      }

      if (this.dependencies != null && !this.dependencies.isEmpty()) {
         sb.append("dependencies:");
         sb.append(this.dependencies + ",");
      }

      if (this.description != null) {
         sb.append("description:");
         sb.append(this.description + ",");
      }

      if (this._enum != null && !this._enum.isEmpty()) {
         sb.append("_enum:");
         sb.append(this._enum + ",");
      }

      if (this.example != null) {
         sb.append("example:");
         sb.append(this.example + ",");
      }

      if (this.exclusiveMaximum != null) {
         sb.append("exclusiveMaximum:");
         sb.append(this.exclusiveMaximum + ",");
      }

      if (this.exclusiveMinimum != null) {
         sb.append("exclusiveMinimum:");
         sb.append(this.exclusiveMinimum + ",");
      }

      if (this.externalDocs != null) {
         sb.append("externalDocs:");
         sb.append(this.externalDocs + ",");
      }

      if (this.format != null) {
         sb.append("format:");
         sb.append(this.format + ",");
      }

      if (this.id != null) {
         sb.append("id:");
         sb.append(this.id + ",");
      }

      if (this.items != null) {
         sb.append("items:");
         sb.append(this.items + ",");
      }

      if (this.maxItems != null) {
         sb.append("maxItems:");
         sb.append(this.maxItems + ",");
      }

      if (this.maxLength != null) {
         sb.append("maxLength:");
         sb.append(this.maxLength + ",");
      }

      if (this.maxProperties != null) {
         sb.append("maxProperties:");
         sb.append(this.maxProperties + ",");
      }

      if (this.maximum != null) {
         sb.append("maximum:");
         sb.append(this.maximum + ",");
      }

      if (this.minItems != null) {
         sb.append("minItems:");
         sb.append(this.minItems + ",");
      }

      if (this.minLength != null) {
         sb.append("minLength:");
         sb.append(this.minLength + ",");
      }

      if (this.minProperties != null) {
         sb.append("minProperties:");
         sb.append(this.minProperties + ",");
      }

      if (this.minimum != null) {
         sb.append("minimum:");
         sb.append(this.minimum + ",");
      }

      if (this.multipleOf != null) {
         sb.append("multipleOf:");
         sb.append(this.multipleOf + ",");
      }

      if (this.not != null) {
         sb.append("not:");
         sb.append(this.not + ",");
      }

      if (this.nullable != null) {
         sb.append("nullable:");
         sb.append(this.nullable + ",");
      }

      if (this.oneOf != null && !this.oneOf.isEmpty()) {
         sb.append("oneOf:");
         sb.append(this.oneOf + ",");
      }

      if (this.pattern != null) {
         sb.append("pattern:");
         sb.append(this.pattern + ",");
      }

      if (this.patternProperties != null && !this.patternProperties.isEmpty()) {
         sb.append("patternProperties:");
         sb.append(this.patternProperties + ",");
      }

      if (this.properties != null && !this.properties.isEmpty()) {
         sb.append("properties:");
         sb.append(this.properties + ",");
      }

      if (this.required != null && !this.required.isEmpty()) {
         sb.append("required:");
         sb.append(this.required + ",");
      }

      if (this.title != null) {
         sb.append("title:");
         sb.append(this.title + ",");
      }

      if (this.type != null) {
         sb.append("type:");
         sb.append(this.type + ",");
      }

      if (this.uniqueItems != null) {
         sb.append("uniqueItems:");
         sb.append(this.uniqueItems + ",");
      }

      if (this.xKubernetesEmbeddedResource != null) {
         sb.append("xKubernetesEmbeddedResource:");
         sb.append(this.xKubernetesEmbeddedResource + ",");
      }

      if (this.xKubernetesIntOrString != null) {
         sb.append("xKubernetesIntOrString:");
         sb.append(this.xKubernetesIntOrString + ",");
      }

      if (this.xKubernetesListMapKeys != null && !this.xKubernetesListMapKeys.isEmpty()) {
         sb.append("xKubernetesListMapKeys:");
         sb.append(this.xKubernetesListMapKeys + ",");
      }

      if (this.xKubernetesListType != null) {
         sb.append("xKubernetesListType:");
         sb.append(this.xKubernetesListType + ",");
      }

      if (this.xKubernetesMapType != null) {
         sb.append("xKubernetesMapType:");
         sb.append(this.xKubernetesMapType + ",");
      }

      if (this.xKubernetesPreserveUnknownFields != null) {
         sb.append("xKubernetesPreserveUnknownFields:");
         sb.append(this.xKubernetesPreserveUnknownFields + ",");
      }

      if (this.xKubernetesValidations != null && !this.xKubernetesValidations.isEmpty()) {
         sb.append("xKubernetesValidations:");
         sb.append(this.xKubernetesValidations);
      }

      sb.append("}");
      return sb.toString();
   }

   public JSONSchemaPropsFluent withExclusiveMaximum() {
      return this.withExclusiveMaximum(true);
   }

   public JSONSchemaPropsFluent withExclusiveMinimum() {
      return this.withExclusiveMinimum(true);
   }

   public JSONSchemaPropsFluent withNullable() {
      return this.withNullable(true);
   }

   public JSONSchemaPropsFluent withUniqueItems() {
      return this.withUniqueItems(true);
   }

   public JSONSchemaPropsFluent withXKubernetesEmbeddedResource() {
      return this.withXKubernetesEmbeddedResource(true);
   }

   public JSONSchemaPropsFluent withXKubernetesIntOrString() {
      return this.withXKubernetesIntOrString(true);
   }

   public JSONSchemaPropsFluent withXKubernetesPreserveUnknownFields() {
      return this.withXKubernetesPreserveUnknownFields(true);
   }

   public class AdditionalItemsNested extends JSONSchemaPropsOrBoolFluent implements Nested {
      JSONSchemaPropsOrBoolBuilder builder;

      AdditionalItemsNested(JSONSchemaPropsOrBool item) {
         this.builder = new JSONSchemaPropsOrBoolBuilder(this, item);
      }

      public Object and() {
         return JSONSchemaPropsFluent.this.withAdditionalItems(this.builder.build());
      }

      public Object endAdditionalItems() {
         return this.and();
      }
   }

   public class AdditionalPropertiesNested extends JSONSchemaPropsOrBoolFluent implements Nested {
      JSONSchemaPropsOrBoolBuilder builder;

      AdditionalPropertiesNested(JSONSchemaPropsOrBool item) {
         this.builder = new JSONSchemaPropsOrBoolBuilder(this, item);
      }

      public Object and() {
         return JSONSchemaPropsFluent.this.withAdditionalProperties(this.builder.build());
      }

      public Object endAdditionalProperties() {
         return this.and();
      }
   }

   public class AllOfNested extends JSONSchemaPropsFluent implements Nested {
      JSONSchemaPropsBuilder builder;
      int index;

      AllOfNested(int index, JSONSchemaProps item) {
         this.index = index;
         this.builder = new JSONSchemaPropsBuilder(this, item);
      }

      public Object and() {
         return JSONSchemaPropsFluent.this.setToAllOf(this.index, this.builder.build());
      }

      public Object endAllOf() {
         return this.and();
      }
   }

   public class AnyOfNested extends JSONSchemaPropsFluent implements Nested {
      JSONSchemaPropsBuilder builder;
      int index;

      AnyOfNested(int index, JSONSchemaProps item) {
         this.index = index;
         this.builder = new JSONSchemaPropsBuilder(this, item);
      }

      public Object and() {
         return JSONSchemaPropsFluent.this.setToAnyOf(this.index, this.builder.build());
      }

      public Object endAnyOf() {
         return this.and();
      }
   }

   public class ExternalDocsNested extends ExternalDocumentationFluent implements Nested {
      ExternalDocumentationBuilder builder;

      ExternalDocsNested(ExternalDocumentation item) {
         this.builder = new ExternalDocumentationBuilder(this, item);
      }

      public Object and() {
         return JSONSchemaPropsFluent.this.withExternalDocs(this.builder.build());
      }

      public Object endExternalDocs() {
         return this.and();
      }
   }

   public class ItemsNested extends JSONSchemaPropsOrArrayFluent implements Nested {
      JSONSchemaPropsOrArrayBuilder builder;

      ItemsNested(JSONSchemaPropsOrArray item) {
         this.builder = new JSONSchemaPropsOrArrayBuilder(this, item);
      }

      public Object and() {
         return JSONSchemaPropsFluent.this.withItems(this.builder.build());
      }

      public Object endItems() {
         return this.and();
      }
   }

   public class NotNested extends JSONSchemaPropsFluent implements Nested {
      JSONSchemaPropsBuilder builder;

      NotNested(JSONSchemaProps item) {
         this.builder = new JSONSchemaPropsBuilder(this, item);
      }

      public Object and() {
         return JSONSchemaPropsFluent.this.withNot(this.builder.build());
      }

      public Object endNot() {
         return this.and();
      }
   }

   public class OneOfNested extends JSONSchemaPropsFluent implements Nested {
      JSONSchemaPropsBuilder builder;
      int index;

      OneOfNested(int index, JSONSchemaProps item) {
         this.index = index;
         this.builder = new JSONSchemaPropsBuilder(this, item);
      }

      public Object and() {
         return JSONSchemaPropsFluent.this.setToOneOf(this.index, this.builder.build());
      }

      public Object endOneOf() {
         return this.and();
      }
   }

   public class XKubernetesValidationsNested extends ValidationRuleFluent implements Nested {
      ValidationRuleBuilder builder;
      int index;

      XKubernetesValidationsNested(int index, ValidationRule item) {
         this.index = index;
         this.builder = new ValidationRuleBuilder(this, item);
      }

      public Object and() {
         return JSONSchemaPropsFluent.this.setToXKubernetesValidations(this.index, this.builder.build());
      }

      public Object endXKubernetesValidation() {
         return this.and();
      }
   }
}
