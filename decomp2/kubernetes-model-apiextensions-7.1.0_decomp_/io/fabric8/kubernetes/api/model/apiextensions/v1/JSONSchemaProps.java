package io.fabric8.kubernetes.api.model.apiextensions.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.builder.Editable;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"$ref", "$schema", "additionalItems", "additionalProperties", "allOf", "anyOf", "default", "definitions", "dependencies", "description", "enum", "example", "exclusiveMaximum", "exclusiveMinimum", "externalDocs", "format", "id", "items", "maxItems", "maxLength", "maxProperties", "maximum", "minItems", "minLength", "minProperties", "minimum", "multipleOf", "not", "nullable", "oneOf", "pattern", "patternProperties", "properties", "required", "title", "type", "uniqueItems", "x-kubernetes-embedded-resource", "x-kubernetes-int-or-string", "x-kubernetes-list-map-keys", "x-kubernetes-list-type", "x-kubernetes-map-type", "x-kubernetes-preserve-unknown-fields", "x-kubernetes-validations"})
public class JSONSchemaProps implements Editable, KubernetesResource {
   @JsonProperty("$ref")
   private String $ref;
   @JsonProperty("$schema")
   private String $schema;
   @JsonProperty("additionalItems")
   private JSONSchemaPropsOrBool additionalItems;
   @JsonProperty("additionalProperties")
   private JSONSchemaPropsOrBool additionalProperties;
   @JsonProperty("allOf")
   @JsonInclude(Include.NON_EMPTY)
   private List allOf = new ArrayList();
   @JsonProperty("anyOf")
   @JsonInclude(Include.NON_EMPTY)
   private List anyOf = new ArrayList();
   @JsonProperty("default")
   private JsonNode _default;
   @JsonProperty("definitions")
   @JsonInclude(Include.NON_EMPTY)
   private Map definitions = new LinkedHashMap();
   @JsonProperty("dependencies")
   @JsonInclude(Include.NON_EMPTY)
   private Map dependencies = new LinkedHashMap();
   @JsonProperty("description")
   private String description;
   @JsonProperty("enum")
   @JsonInclude(Include.NON_EMPTY)
   private List _enum = new ArrayList();
   @JsonProperty("example")
   private JsonNode example;
   @JsonProperty("exclusiveMaximum")
   private Boolean exclusiveMaximum;
   @JsonProperty("exclusiveMinimum")
   private Boolean exclusiveMinimum;
   @JsonProperty("externalDocs")
   private ExternalDocumentation externalDocs;
   @JsonProperty("format")
   private String format;
   @JsonProperty("id")
   private String id;
   @JsonProperty("items")
   private JSONSchemaPropsOrArray items;
   @JsonProperty("maxItems")
   private Long maxItems;
   @JsonProperty("maxLength")
   private Long maxLength;
   @JsonProperty("maxProperties")
   private Long maxProperties;
   @JsonProperty("maximum")
   private Double maximum;
   @JsonProperty("minItems")
   private Long minItems;
   @JsonProperty("minLength")
   private Long minLength;
   @JsonProperty("minProperties")
   private Long minProperties;
   @JsonProperty("minimum")
   private Double minimum;
   @JsonProperty("multipleOf")
   private Double multipleOf;
   @JsonProperty("not")
   private JSONSchemaProps not;
   @JsonProperty("nullable")
   private Boolean nullable;
   @JsonProperty("oneOf")
   @JsonInclude(Include.NON_EMPTY)
   private List oneOf = new ArrayList();
   @JsonProperty("pattern")
   private String pattern;
   @JsonProperty("patternProperties")
   @JsonInclude(Include.NON_EMPTY)
   private Map patternProperties = new LinkedHashMap();
   @JsonProperty("properties")
   @JsonInclude(Include.NON_EMPTY)
   private Map properties = new LinkedHashMap();
   @JsonProperty("required")
   @JsonInclude(Include.NON_EMPTY)
   private List required = new ArrayList();
   @JsonProperty("title")
   private String title;
   @JsonProperty("type")
   private String type;
   @JsonProperty("uniqueItems")
   private Boolean uniqueItems;
   @JsonProperty("x-kubernetes-embedded-resource")
   private Boolean xKubernetesEmbeddedResource;
   @JsonProperty("x-kubernetes-int-or-string")
   private Boolean xKubernetesIntOrString;
   @JsonProperty("x-kubernetes-list-map-keys")
   @JsonInclude(Include.NON_EMPTY)
   private List xKubernetesListMapKeys = new ArrayList();
   @JsonProperty("x-kubernetes-list-type")
   private String xKubernetesListType;
   @JsonProperty("x-kubernetes-map-type")
   private String xKubernetesMapType;
   @JsonProperty("x-kubernetes-preserve-unknown-fields")
   private Boolean xKubernetesPreserveUnknownFields;
   @JsonProperty("x-kubernetes-validations")
   @JsonInclude(Include.NON_EMPTY)
   private List xKubernetesValidations = new ArrayList();

   public JSONSchemaProps() {
   }

   public JSONSchemaProps(String $ref, String $schema, JSONSchemaPropsOrBool additionalItems, JSONSchemaPropsOrBool additionalProperties, List allOf, List anyOf, JsonNode _default, Map definitions, Map dependencies, String description, List _enum, JsonNode example, Boolean exclusiveMaximum, Boolean exclusiveMinimum, ExternalDocumentation externalDocs, String format, String id, JSONSchemaPropsOrArray items, Long maxItems, Long maxLength, Long maxProperties, Double maximum, Long minItems, Long minLength, Long minProperties, Double minimum, Double multipleOf, JSONSchemaProps not, Boolean nullable, List oneOf, String pattern, Map patternProperties, Map properties, List required, String title, String type, Boolean uniqueItems, Boolean xKubernetesEmbeddedResource, Boolean xKubernetesIntOrString, List xKubernetesListMapKeys, String xKubernetesListType, String xKubernetesMapType, Boolean xKubernetesPreserveUnknownFields, List xKubernetesValidations) {
      this.$ref = $ref;
      this.$schema = $schema;
      this.additionalItems = additionalItems;
      this.additionalProperties = additionalProperties;
      this.allOf = allOf;
      this.anyOf = anyOf;
      this._default = _default;
      this.definitions = definitions;
      this.dependencies = dependencies;
      this.description = description;
      this._enum = _enum;
      this.example = example;
      this.exclusiveMaximum = exclusiveMaximum;
      this.exclusiveMinimum = exclusiveMinimum;
      this.externalDocs = externalDocs;
      this.format = format;
      this.id = id;
      this.items = items;
      this.maxItems = maxItems;
      this.maxLength = maxLength;
      this.maxProperties = maxProperties;
      this.maximum = maximum;
      this.minItems = minItems;
      this.minLength = minLength;
      this.minProperties = minProperties;
      this.minimum = minimum;
      this.multipleOf = multipleOf;
      this.not = not;
      this.nullable = nullable;
      this.oneOf = oneOf;
      this.pattern = pattern;
      this.patternProperties = patternProperties;
      this.properties = properties;
      this.required = required;
      this.title = title;
      this.type = type;
      this.uniqueItems = uniqueItems;
      this.xKubernetesEmbeddedResource = xKubernetesEmbeddedResource;
      this.xKubernetesIntOrString = xKubernetesIntOrString;
      this.xKubernetesListMapKeys = xKubernetesListMapKeys;
      this.xKubernetesListType = xKubernetesListType;
      this.xKubernetesMapType = xKubernetesMapType;
      this.xKubernetesPreserveUnknownFields = xKubernetesPreserveUnknownFields;
      this.xKubernetesValidations = xKubernetesValidations;
   }

   @JsonProperty("$ref")
   public String get$ref() {
      return this.$ref;
   }

   @JsonProperty("$ref")
   public void set$ref(String $ref) {
      this.$ref = $ref;
   }

   @JsonProperty("$schema")
   public String get$schema() {
      return this.$schema;
   }

   @JsonProperty("$schema")
   public void set$schema(String $schema) {
      this.$schema = $schema;
   }

   @JsonProperty("additionalItems")
   public JSONSchemaPropsOrBool getAdditionalItems() {
      return this.additionalItems;
   }

   @JsonProperty("additionalItems")
   public void setAdditionalItems(JSONSchemaPropsOrBool additionalItems) {
      this.additionalItems = additionalItems;
   }

   @JsonProperty("additionalProperties")
   public JSONSchemaPropsOrBool getAdditionalProperties() {
      return this.additionalProperties;
   }

   @JsonProperty("additionalProperties")
   public void setAdditionalProperties(JSONSchemaPropsOrBool additionalProperties) {
      this.additionalProperties = additionalProperties;
   }

   @JsonProperty("allOf")
   @JsonInclude(Include.NON_EMPTY)
   public List getAllOf() {
      return this.allOf;
   }

   @JsonProperty("allOf")
   public void setAllOf(List allOf) {
      this.allOf = allOf;
   }

   @JsonProperty("anyOf")
   @JsonInclude(Include.NON_EMPTY)
   public List getAnyOf() {
      return this.anyOf;
   }

   @JsonProperty("anyOf")
   public void setAnyOf(List anyOf) {
      this.anyOf = anyOf;
   }

   @JsonProperty("default")
   public JsonNode getDefault() {
      return this._default;
   }

   @JsonProperty("default")
   public void setDefault(JsonNode _default) {
      this._default = _default;
   }

   @JsonProperty("definitions")
   @JsonInclude(Include.NON_EMPTY)
   public Map getDefinitions() {
      return this.definitions;
   }

   @JsonProperty("definitions")
   public void setDefinitions(Map definitions) {
      this.definitions = definitions;
   }

   @JsonProperty("dependencies")
   @JsonInclude(Include.NON_EMPTY)
   public Map getDependencies() {
      return this.dependencies;
   }

   @JsonProperty("dependencies")
   public void setDependencies(Map dependencies) {
      this.dependencies = dependencies;
   }

   @JsonProperty("description")
   public String getDescription() {
      return this.description;
   }

   @JsonProperty("description")
   public void setDescription(String description) {
      this.description = description;
   }

   @JsonProperty("enum")
   @JsonInclude(Include.NON_EMPTY)
   public List getEnum() {
      return this._enum;
   }

   @JsonProperty("enum")
   public void setEnum(List _enum) {
      this._enum = _enum;
   }

   @JsonProperty("example")
   public JsonNode getExample() {
      return this.example;
   }

   @JsonProperty("example")
   public void setExample(JsonNode example) {
      this.example = example;
   }

   @JsonProperty("exclusiveMaximum")
   public Boolean getExclusiveMaximum() {
      return this.exclusiveMaximum;
   }

   @JsonProperty("exclusiveMaximum")
   public void setExclusiveMaximum(Boolean exclusiveMaximum) {
      this.exclusiveMaximum = exclusiveMaximum;
   }

   @JsonProperty("exclusiveMinimum")
   public Boolean getExclusiveMinimum() {
      return this.exclusiveMinimum;
   }

   @JsonProperty("exclusiveMinimum")
   public void setExclusiveMinimum(Boolean exclusiveMinimum) {
      this.exclusiveMinimum = exclusiveMinimum;
   }

   @JsonProperty("externalDocs")
   public ExternalDocumentation getExternalDocs() {
      return this.externalDocs;
   }

   @JsonProperty("externalDocs")
   public void setExternalDocs(ExternalDocumentation externalDocs) {
      this.externalDocs = externalDocs;
   }

   @JsonProperty("format")
   public String getFormat() {
      return this.format;
   }

   @JsonProperty("format")
   public void setFormat(String format) {
      this.format = format;
   }

   @JsonProperty("id")
   public String getId() {
      return this.id;
   }

   @JsonProperty("id")
   public void setId(String id) {
      this.id = id;
   }

   @JsonProperty("items")
   public JSONSchemaPropsOrArray getItems() {
      return this.items;
   }

   @JsonProperty("items")
   public void setItems(JSONSchemaPropsOrArray items) {
      this.items = items;
   }

   @JsonProperty("maxItems")
   public Long getMaxItems() {
      return this.maxItems;
   }

   @JsonProperty("maxItems")
   public void setMaxItems(Long maxItems) {
      this.maxItems = maxItems;
   }

   @JsonProperty("maxLength")
   public Long getMaxLength() {
      return this.maxLength;
   }

   @JsonProperty("maxLength")
   public void setMaxLength(Long maxLength) {
      this.maxLength = maxLength;
   }

   @JsonProperty("maxProperties")
   public Long getMaxProperties() {
      return this.maxProperties;
   }

   @JsonProperty("maxProperties")
   public void setMaxProperties(Long maxProperties) {
      this.maxProperties = maxProperties;
   }

   @JsonProperty("maximum")
   public Double getMaximum() {
      return this.maximum;
   }

   @JsonProperty("maximum")
   public void setMaximum(Double maximum) {
      this.maximum = maximum;
   }

   @JsonProperty("minItems")
   public Long getMinItems() {
      return this.minItems;
   }

   @JsonProperty("minItems")
   public void setMinItems(Long minItems) {
      this.minItems = minItems;
   }

   @JsonProperty("minLength")
   public Long getMinLength() {
      return this.minLength;
   }

   @JsonProperty("minLength")
   public void setMinLength(Long minLength) {
      this.minLength = minLength;
   }

   @JsonProperty("minProperties")
   public Long getMinProperties() {
      return this.minProperties;
   }

   @JsonProperty("minProperties")
   public void setMinProperties(Long minProperties) {
      this.minProperties = minProperties;
   }

   @JsonProperty("minimum")
   public Double getMinimum() {
      return this.minimum;
   }

   @JsonProperty("minimum")
   public void setMinimum(Double minimum) {
      this.minimum = minimum;
   }

   @JsonProperty("multipleOf")
   public Double getMultipleOf() {
      return this.multipleOf;
   }

   @JsonProperty("multipleOf")
   public void setMultipleOf(Double multipleOf) {
      this.multipleOf = multipleOf;
   }

   @JsonProperty("not")
   public JSONSchemaProps getNot() {
      return this.not;
   }

   @JsonProperty("not")
   public void setNot(JSONSchemaProps not) {
      this.not = not;
   }

   @JsonProperty("nullable")
   public Boolean getNullable() {
      return this.nullable;
   }

   @JsonProperty("nullable")
   public void setNullable(Boolean nullable) {
      this.nullable = nullable;
   }

   @JsonProperty("oneOf")
   @JsonInclude(Include.NON_EMPTY)
   public List getOneOf() {
      return this.oneOf;
   }

   @JsonProperty("oneOf")
   public void setOneOf(List oneOf) {
      this.oneOf = oneOf;
   }

   @JsonProperty("pattern")
   public String getPattern() {
      return this.pattern;
   }

   @JsonProperty("pattern")
   public void setPattern(String pattern) {
      this.pattern = pattern;
   }

   @JsonProperty("patternProperties")
   @JsonInclude(Include.NON_EMPTY)
   public Map getPatternProperties() {
      return this.patternProperties;
   }

   @JsonProperty("patternProperties")
   public void setPatternProperties(Map patternProperties) {
      this.patternProperties = patternProperties;
   }

   @JsonProperty("properties")
   @JsonInclude(Include.NON_EMPTY)
   public Map getProperties() {
      return this.properties;
   }

   @JsonProperty("properties")
   public void setProperties(Map properties) {
      this.properties = properties;
   }

   @JsonProperty("required")
   @JsonInclude(Include.NON_EMPTY)
   public List getRequired() {
      return this.required;
   }

   @JsonProperty("required")
   public void setRequired(List required) {
      this.required = required;
   }

   @JsonProperty("title")
   public String getTitle() {
      return this.title;
   }

   @JsonProperty("title")
   public void setTitle(String title) {
      this.title = title;
   }

   @JsonProperty("type")
   public String getType() {
      return this.type;
   }

   @JsonProperty("type")
   public void setType(String type) {
      this.type = type;
   }

   @JsonProperty("uniqueItems")
   public Boolean getUniqueItems() {
      return this.uniqueItems;
   }

   @JsonProperty("uniqueItems")
   public void setUniqueItems(Boolean uniqueItems) {
      this.uniqueItems = uniqueItems;
   }

   @JsonProperty("x-kubernetes-embedded-resource")
   public Boolean getXKubernetesEmbeddedResource() {
      return this.xKubernetesEmbeddedResource;
   }

   @JsonProperty("x-kubernetes-embedded-resource")
   public void setXKubernetesEmbeddedResource(Boolean xKubernetesEmbeddedResource) {
      this.xKubernetesEmbeddedResource = xKubernetesEmbeddedResource;
   }

   @JsonProperty("x-kubernetes-int-or-string")
   public Boolean getXKubernetesIntOrString() {
      return this.xKubernetesIntOrString;
   }

   @JsonProperty("x-kubernetes-int-or-string")
   public void setXKubernetesIntOrString(Boolean xKubernetesIntOrString) {
      this.xKubernetesIntOrString = xKubernetesIntOrString;
   }

   @JsonProperty("x-kubernetes-list-map-keys")
   @JsonInclude(Include.NON_EMPTY)
   public List getXKubernetesListMapKeys() {
      return this.xKubernetesListMapKeys;
   }

   @JsonProperty("x-kubernetes-list-map-keys")
   public void setXKubernetesListMapKeys(List xKubernetesListMapKeys) {
      this.xKubernetesListMapKeys = xKubernetesListMapKeys;
   }

   @JsonProperty("x-kubernetes-list-type")
   public String getXKubernetesListType() {
      return this.xKubernetesListType;
   }

   @JsonProperty("x-kubernetes-list-type")
   public void setXKubernetesListType(String xKubernetesListType) {
      this.xKubernetesListType = xKubernetesListType;
   }

   @JsonProperty("x-kubernetes-map-type")
   public String getXKubernetesMapType() {
      return this.xKubernetesMapType;
   }

   @JsonProperty("x-kubernetes-map-type")
   public void setXKubernetesMapType(String xKubernetesMapType) {
      this.xKubernetesMapType = xKubernetesMapType;
   }

   @JsonProperty("x-kubernetes-preserve-unknown-fields")
   public Boolean getXKubernetesPreserveUnknownFields() {
      return this.xKubernetesPreserveUnknownFields;
   }

   @JsonProperty("x-kubernetes-preserve-unknown-fields")
   public void setXKubernetesPreserveUnknownFields(Boolean xKubernetesPreserveUnknownFields) {
      this.xKubernetesPreserveUnknownFields = xKubernetesPreserveUnknownFields;
   }

   @JsonProperty("x-kubernetes-validations")
   @JsonInclude(Include.NON_EMPTY)
   public List getXKubernetesValidations() {
      return this.xKubernetesValidations;
   }

   @JsonProperty("x-kubernetes-validations")
   public void setXKubernetesValidations(List xKubernetesValidations) {
      this.xKubernetesValidations = xKubernetesValidations;
   }

   @JsonIgnore
   public JSONSchemaPropsBuilder edit() {
      return new JSONSchemaPropsBuilder(this);
   }

   @JsonIgnore
   public JSONSchemaPropsBuilder toBuilder() {
      return this.edit();
   }

   @Generated
   public String toString() {
      JSONSchemaPropsOrBool var10000 = this.getAdditionalItems();
      return "JSONSchemaProps(additionalItems=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ", allOf=" + this.getAllOf() + ", anyOf=" + this.getAnyOf() + ", _default=" + this.getDefault() + ", definitions=" + this.getDefinitions() + ", dependencies=" + this.getDependencies() + ", description=" + this.getDescription() + ", _enum=" + this.getEnum() + ", example=" + this.getExample() + ", exclusiveMaximum=" + this.getExclusiveMaximum() + ", exclusiveMinimum=" + this.getExclusiveMinimum() + ", externalDocs=" + this.getExternalDocs() + ", format=" + this.getFormat() + ", id=" + this.getId() + ", items=" + this.getItems() + ", maxItems=" + this.getMaxItems() + ", maxLength=" + this.getMaxLength() + ", maxProperties=" + this.getMaxProperties() + ", maximum=" + this.getMaximum() + ", minItems=" + this.getMinItems() + ", minLength=" + this.getMinLength() + ", minProperties=" + this.getMinProperties() + ", minimum=" + this.getMinimum() + ", multipleOf=" + this.getMultipleOf() + ", not=" + this.getNot() + ", nullable=" + this.getNullable() + ", oneOf=" + this.getOneOf() + ", pattern=" + this.getPattern() + ", patternProperties=" + this.getPatternProperties() + ", properties=" + this.getProperties() + ", required=" + this.getRequired() + ", title=" + this.getTitle() + ", type=" + this.getType() + ", uniqueItems=" + this.getUniqueItems() + ", xKubernetesEmbeddedResource=" + this.getXKubernetesEmbeddedResource() + ", xKubernetesIntOrString=" + this.getXKubernetesIntOrString() + ", xKubernetesListMapKeys=" + this.getXKubernetesListMapKeys() + ", xKubernetesListType=" + this.getXKubernetesListType() + ", xKubernetesMapType=" + this.getXKubernetesMapType() + ", xKubernetesPreserveUnknownFields=" + this.getXKubernetesPreserveUnknownFields() + ", xKubernetesValidations=" + this.getXKubernetesValidations() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof JSONSchemaProps)) {
         return false;
      } else {
         JSONSchemaProps other = (JSONSchemaProps)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$exclusiveMaximum = this.getExclusiveMaximum();
            Object other$exclusiveMaximum = other.getExclusiveMaximum();
            if (this$exclusiveMaximum == null) {
               if (other$exclusiveMaximum != null) {
                  return false;
               }
            } else if (!this$exclusiveMaximum.equals(other$exclusiveMaximum)) {
               return false;
            }

            Object this$exclusiveMinimum = this.getExclusiveMinimum();
            Object other$exclusiveMinimum = other.getExclusiveMinimum();
            if (this$exclusiveMinimum == null) {
               if (other$exclusiveMinimum != null) {
                  return false;
               }
            } else if (!this$exclusiveMinimum.equals(other$exclusiveMinimum)) {
               return false;
            }

            Object this$maxItems = this.getMaxItems();
            Object other$maxItems = other.getMaxItems();
            if (this$maxItems == null) {
               if (other$maxItems != null) {
                  return false;
               }
            } else if (!this$maxItems.equals(other$maxItems)) {
               return false;
            }

            Object this$maxLength = this.getMaxLength();
            Object other$maxLength = other.getMaxLength();
            if (this$maxLength == null) {
               if (other$maxLength != null) {
                  return false;
               }
            } else if (!this$maxLength.equals(other$maxLength)) {
               return false;
            }

            Object this$maxProperties = this.getMaxProperties();
            Object other$maxProperties = other.getMaxProperties();
            if (this$maxProperties == null) {
               if (other$maxProperties != null) {
                  return false;
               }
            } else if (!this$maxProperties.equals(other$maxProperties)) {
               return false;
            }

            Object this$maximum = this.getMaximum();
            Object other$maximum = other.getMaximum();
            if (this$maximum == null) {
               if (other$maximum != null) {
                  return false;
               }
            } else if (!this$maximum.equals(other$maximum)) {
               return false;
            }

            Object this$minItems = this.getMinItems();
            Object other$minItems = other.getMinItems();
            if (this$minItems == null) {
               if (other$minItems != null) {
                  return false;
               }
            } else if (!this$minItems.equals(other$minItems)) {
               return false;
            }

            Object this$minLength = this.getMinLength();
            Object other$minLength = other.getMinLength();
            if (this$minLength == null) {
               if (other$minLength != null) {
                  return false;
               }
            } else if (!this$minLength.equals(other$minLength)) {
               return false;
            }

            Object this$minProperties = this.getMinProperties();
            Object other$minProperties = other.getMinProperties();
            if (this$minProperties == null) {
               if (other$minProperties != null) {
                  return false;
               }
            } else if (!this$minProperties.equals(other$minProperties)) {
               return false;
            }

            Object this$minimum = this.getMinimum();
            Object other$minimum = other.getMinimum();
            if (this$minimum == null) {
               if (other$minimum != null) {
                  return false;
               }
            } else if (!this$minimum.equals(other$minimum)) {
               return false;
            }

            Object this$multipleOf = this.getMultipleOf();
            Object other$multipleOf = other.getMultipleOf();
            if (this$multipleOf == null) {
               if (other$multipleOf != null) {
                  return false;
               }
            } else if (!this$multipleOf.equals(other$multipleOf)) {
               return false;
            }

            Object this$nullable = this.getNullable();
            Object other$nullable = other.getNullable();
            if (this$nullable == null) {
               if (other$nullable != null) {
                  return false;
               }
            } else if (!this$nullable.equals(other$nullable)) {
               return false;
            }

            Object this$uniqueItems = this.getUniqueItems();
            Object other$uniqueItems = other.getUniqueItems();
            if (this$uniqueItems == null) {
               if (other$uniqueItems != null) {
                  return false;
               }
            } else if (!this$uniqueItems.equals(other$uniqueItems)) {
               return false;
            }

            Object this$xKubernetesEmbeddedResource = this.getXKubernetesEmbeddedResource();
            Object other$xKubernetesEmbeddedResource = other.getXKubernetesEmbeddedResource();
            if (this$xKubernetesEmbeddedResource == null) {
               if (other$xKubernetesEmbeddedResource != null) {
                  return false;
               }
            } else if (!this$xKubernetesEmbeddedResource.equals(other$xKubernetesEmbeddedResource)) {
               return false;
            }

            Object this$xKubernetesIntOrString = this.getXKubernetesIntOrString();
            Object other$xKubernetesIntOrString = other.getXKubernetesIntOrString();
            if (this$xKubernetesIntOrString == null) {
               if (other$xKubernetesIntOrString != null) {
                  return false;
               }
            } else if (!this$xKubernetesIntOrString.equals(other$xKubernetesIntOrString)) {
               return false;
            }

            Object this$xKubernetesPreserveUnknownFields = this.getXKubernetesPreserveUnknownFields();
            Object other$xKubernetesPreserveUnknownFields = other.getXKubernetesPreserveUnknownFields();
            if (this$xKubernetesPreserveUnknownFields == null) {
               if (other$xKubernetesPreserveUnknownFields != null) {
                  return false;
               }
            } else if (!this$xKubernetesPreserveUnknownFields.equals(other$xKubernetesPreserveUnknownFields)) {
               return false;
            }

            Object this$additionalItems = this.getAdditionalItems();
            Object other$additionalItems = other.getAdditionalItems();
            if (this$additionalItems == null) {
               if (other$additionalItems != null) {
                  return false;
               }
            } else if (!this$additionalItems.equals(other$additionalItems)) {
               return false;
            }

            Object this$additionalProperties = this.getAdditionalProperties();
            Object other$additionalProperties = other.getAdditionalProperties();
            if (this$additionalProperties == null) {
               if (other$additionalProperties != null) {
                  return false;
               }
            } else if (!this$additionalProperties.equals(other$additionalProperties)) {
               return false;
            }

            Object this$allOf = this.getAllOf();
            Object other$allOf = other.getAllOf();
            if (this$allOf == null) {
               if (other$allOf != null) {
                  return false;
               }
            } else if (!this$allOf.equals(other$allOf)) {
               return false;
            }

            Object this$anyOf = this.getAnyOf();
            Object other$anyOf = other.getAnyOf();
            if (this$anyOf == null) {
               if (other$anyOf != null) {
                  return false;
               }
            } else if (!this$anyOf.equals(other$anyOf)) {
               return false;
            }

            Object this$_default = this.getDefault();
            Object other$_default = other.getDefault();
            if (this$_default == null) {
               if (other$_default != null) {
                  return false;
               }
            } else if (!this$_default.equals(other$_default)) {
               return false;
            }

            Object this$definitions = this.getDefinitions();
            Object other$definitions = other.getDefinitions();
            if (this$definitions == null) {
               if (other$definitions != null) {
                  return false;
               }
            } else if (!this$definitions.equals(other$definitions)) {
               return false;
            }

            Object this$dependencies = this.getDependencies();
            Object other$dependencies = other.getDependencies();
            if (this$dependencies == null) {
               if (other$dependencies != null) {
                  return false;
               }
            } else if (!this$dependencies.equals(other$dependencies)) {
               return false;
            }

            Object this$description = this.getDescription();
            Object other$description = other.getDescription();
            if (this$description == null) {
               if (other$description != null) {
                  return false;
               }
            } else if (!this$description.equals(other$description)) {
               return false;
            }

            Object this$_enum = this.getEnum();
            Object other$_enum = other.getEnum();
            if (this$_enum == null) {
               if (other$_enum != null) {
                  return false;
               }
            } else if (!this$_enum.equals(other$_enum)) {
               return false;
            }

            Object this$example = this.getExample();
            Object other$example = other.getExample();
            if (this$example == null) {
               if (other$example != null) {
                  return false;
               }
            } else if (!this$example.equals(other$example)) {
               return false;
            }

            Object this$externalDocs = this.getExternalDocs();
            Object other$externalDocs = other.getExternalDocs();
            if (this$externalDocs == null) {
               if (other$externalDocs != null) {
                  return false;
               }
            } else if (!this$externalDocs.equals(other$externalDocs)) {
               return false;
            }

            Object this$format = this.getFormat();
            Object other$format = other.getFormat();
            if (this$format == null) {
               if (other$format != null) {
                  return false;
               }
            } else if (!this$format.equals(other$format)) {
               return false;
            }

            Object this$id = this.getId();
            Object other$id = other.getId();
            if (this$id == null) {
               if (other$id != null) {
                  return false;
               }
            } else if (!this$id.equals(other$id)) {
               return false;
            }

            Object this$items = this.getItems();
            Object other$items = other.getItems();
            if (this$items == null) {
               if (other$items != null) {
                  return false;
               }
            } else if (!this$items.equals(other$items)) {
               return false;
            }

            Object this$not = this.getNot();
            Object other$not = other.getNot();
            if (this$not == null) {
               if (other$not != null) {
                  return false;
               }
            } else if (!this$not.equals(other$not)) {
               return false;
            }

            Object this$oneOf = this.getOneOf();
            Object other$oneOf = other.getOneOf();
            if (this$oneOf == null) {
               if (other$oneOf != null) {
                  return false;
               }
            } else if (!this$oneOf.equals(other$oneOf)) {
               return false;
            }

            Object this$pattern = this.getPattern();
            Object other$pattern = other.getPattern();
            if (this$pattern == null) {
               if (other$pattern != null) {
                  return false;
               }
            } else if (!this$pattern.equals(other$pattern)) {
               return false;
            }

            Object this$patternProperties = this.getPatternProperties();
            Object other$patternProperties = other.getPatternProperties();
            if (this$patternProperties == null) {
               if (other$patternProperties != null) {
                  return false;
               }
            } else if (!this$patternProperties.equals(other$patternProperties)) {
               return false;
            }

            Object this$properties = this.getProperties();
            Object other$properties = other.getProperties();
            if (this$properties == null) {
               if (other$properties != null) {
                  return false;
               }
            } else if (!this$properties.equals(other$properties)) {
               return false;
            }

            Object this$required = this.getRequired();
            Object other$required = other.getRequired();
            if (this$required == null) {
               if (other$required != null) {
                  return false;
               }
            } else if (!this$required.equals(other$required)) {
               return false;
            }

            Object this$title = this.getTitle();
            Object other$title = other.getTitle();
            if (this$title == null) {
               if (other$title != null) {
                  return false;
               }
            } else if (!this$title.equals(other$title)) {
               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            Object this$xKubernetesListMapKeys = this.getXKubernetesListMapKeys();
            Object other$xKubernetesListMapKeys = other.getXKubernetesListMapKeys();
            if (this$xKubernetesListMapKeys == null) {
               if (other$xKubernetesListMapKeys != null) {
                  return false;
               }
            } else if (!this$xKubernetesListMapKeys.equals(other$xKubernetesListMapKeys)) {
               return false;
            }

            Object this$xKubernetesListType = this.getXKubernetesListType();
            Object other$xKubernetesListType = other.getXKubernetesListType();
            if (this$xKubernetesListType == null) {
               if (other$xKubernetesListType != null) {
                  return false;
               }
            } else if (!this$xKubernetesListType.equals(other$xKubernetesListType)) {
               return false;
            }

            Object this$xKubernetesMapType = this.getXKubernetesMapType();
            Object other$xKubernetesMapType = other.getXKubernetesMapType();
            if (this$xKubernetesMapType == null) {
               if (other$xKubernetesMapType != null) {
                  return false;
               }
            } else if (!this$xKubernetesMapType.equals(other$xKubernetesMapType)) {
               return false;
            }

            Object this$xKubernetesValidations = this.getXKubernetesValidations();
            Object other$xKubernetesValidations = other.getXKubernetesValidations();
            if (this$xKubernetesValidations == null) {
               if (other$xKubernetesValidations != null) {
                  return false;
               }
            } else if (!this$xKubernetesValidations.equals(other$xKubernetesValidations)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object other) {
      return other instanceof JSONSchemaProps;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $exclusiveMaximum = this.getExclusiveMaximum();
      result = result * 59 + ($exclusiveMaximum == null ? 43 : $exclusiveMaximum.hashCode());
      Object $exclusiveMinimum = this.getExclusiveMinimum();
      result = result * 59 + ($exclusiveMinimum == null ? 43 : $exclusiveMinimum.hashCode());
      Object $maxItems = this.getMaxItems();
      result = result * 59 + ($maxItems == null ? 43 : $maxItems.hashCode());
      Object $maxLength = this.getMaxLength();
      result = result * 59 + ($maxLength == null ? 43 : $maxLength.hashCode());
      Object $maxProperties = this.getMaxProperties();
      result = result * 59 + ($maxProperties == null ? 43 : $maxProperties.hashCode());
      Object $maximum = this.getMaximum();
      result = result * 59 + ($maximum == null ? 43 : $maximum.hashCode());
      Object $minItems = this.getMinItems();
      result = result * 59 + ($minItems == null ? 43 : $minItems.hashCode());
      Object $minLength = this.getMinLength();
      result = result * 59 + ($minLength == null ? 43 : $minLength.hashCode());
      Object $minProperties = this.getMinProperties();
      result = result * 59 + ($minProperties == null ? 43 : $minProperties.hashCode());
      Object $minimum = this.getMinimum();
      result = result * 59 + ($minimum == null ? 43 : $minimum.hashCode());
      Object $multipleOf = this.getMultipleOf();
      result = result * 59 + ($multipleOf == null ? 43 : $multipleOf.hashCode());
      Object $nullable = this.getNullable();
      result = result * 59 + ($nullable == null ? 43 : $nullable.hashCode());
      Object $uniqueItems = this.getUniqueItems();
      result = result * 59 + ($uniqueItems == null ? 43 : $uniqueItems.hashCode());
      Object $xKubernetesEmbeddedResource = this.getXKubernetesEmbeddedResource();
      result = result * 59 + ($xKubernetesEmbeddedResource == null ? 43 : $xKubernetesEmbeddedResource.hashCode());
      Object $xKubernetesIntOrString = this.getXKubernetesIntOrString();
      result = result * 59 + ($xKubernetesIntOrString == null ? 43 : $xKubernetesIntOrString.hashCode());
      Object $xKubernetesPreserveUnknownFields = this.getXKubernetesPreserveUnknownFields();
      result = result * 59 + ($xKubernetesPreserveUnknownFields == null ? 43 : $xKubernetesPreserveUnknownFields.hashCode());
      Object $additionalItems = this.getAdditionalItems();
      result = result * 59 + ($additionalItems == null ? 43 : $additionalItems.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      Object $allOf = this.getAllOf();
      result = result * 59 + ($allOf == null ? 43 : $allOf.hashCode());
      Object $anyOf = this.getAnyOf();
      result = result * 59 + ($anyOf == null ? 43 : $anyOf.hashCode());
      Object $_default = this.getDefault();
      result = result * 59 + ($_default == null ? 43 : $_default.hashCode());
      Object $definitions = this.getDefinitions();
      result = result * 59 + ($definitions == null ? 43 : $definitions.hashCode());
      Object $dependencies = this.getDependencies();
      result = result * 59 + ($dependencies == null ? 43 : $dependencies.hashCode());
      Object $description = this.getDescription();
      result = result * 59 + ($description == null ? 43 : $description.hashCode());
      Object $_enum = this.getEnum();
      result = result * 59 + ($_enum == null ? 43 : $_enum.hashCode());
      Object $example = this.getExample();
      result = result * 59 + ($example == null ? 43 : $example.hashCode());
      Object $externalDocs = this.getExternalDocs();
      result = result * 59 + ($externalDocs == null ? 43 : $externalDocs.hashCode());
      Object $format = this.getFormat();
      result = result * 59 + ($format == null ? 43 : $format.hashCode());
      Object $id = this.getId();
      result = result * 59 + ($id == null ? 43 : $id.hashCode());
      Object $items = this.getItems();
      result = result * 59 + ($items == null ? 43 : $items.hashCode());
      Object $not = this.getNot();
      result = result * 59 + ($not == null ? 43 : $not.hashCode());
      Object $oneOf = this.getOneOf();
      result = result * 59 + ($oneOf == null ? 43 : $oneOf.hashCode());
      Object $pattern = this.getPattern();
      result = result * 59 + ($pattern == null ? 43 : $pattern.hashCode());
      Object $patternProperties = this.getPatternProperties();
      result = result * 59 + ($patternProperties == null ? 43 : $patternProperties.hashCode());
      Object $properties = this.getProperties();
      result = result * 59 + ($properties == null ? 43 : $properties.hashCode());
      Object $required = this.getRequired();
      result = result * 59 + ($required == null ? 43 : $required.hashCode());
      Object $title = this.getTitle();
      result = result * 59 + ($title == null ? 43 : $title.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $xKubernetesListMapKeys = this.getXKubernetesListMapKeys();
      result = result * 59 + ($xKubernetesListMapKeys == null ? 43 : $xKubernetesListMapKeys.hashCode());
      Object $xKubernetesListType = this.getXKubernetesListType();
      result = result * 59 + ($xKubernetesListType == null ? 43 : $xKubernetesListType.hashCode());
      Object $xKubernetesMapType = this.getXKubernetesMapType();
      result = result * 59 + ($xKubernetesMapType == null ? 43 : $xKubernetesMapType.hashCode());
      Object $xKubernetesValidations = this.getXKubernetesValidations();
      result = result * 59 + ($xKubernetesValidations == null ? 43 : $xKubernetesValidations.hashCode());
      return result;
   }
}
