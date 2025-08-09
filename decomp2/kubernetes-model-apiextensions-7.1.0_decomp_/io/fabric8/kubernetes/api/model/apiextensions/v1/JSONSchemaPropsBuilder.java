package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class JSONSchemaPropsBuilder extends JSONSchemaPropsFluent implements VisitableBuilder {
   JSONSchemaPropsFluent fluent;

   public JSONSchemaPropsBuilder() {
      this(new JSONSchemaProps());
   }

   public JSONSchemaPropsBuilder(JSONSchemaPropsFluent fluent) {
      this(fluent, new JSONSchemaProps());
   }

   public JSONSchemaPropsBuilder(JSONSchemaPropsFluent fluent, JSONSchemaProps instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public JSONSchemaPropsBuilder(JSONSchemaProps instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public JSONSchemaProps build() {
      JSONSchemaProps buildable = new JSONSchemaProps(this.fluent.getRef(), this.fluent.getSchema(), this.fluent.buildAdditionalItems(), this.fluent.buildAdditionalProperties(), this.fluent.buildAllOf(), this.fluent.buildAnyOf(), this.fluent.getDefault(), this.fluent.getDefinitions(), this.fluent.getDependencies(), this.fluent.getDescription(), this.fluent.getEnum(), this.fluent.getExample(), this.fluent.getExclusiveMaximum(), this.fluent.getExclusiveMinimum(), this.fluent.buildExternalDocs(), this.fluent.getFormat(), this.fluent.getId(), this.fluent.buildItems(), this.fluent.getMaxItems(), this.fluent.getMaxLength(), this.fluent.getMaxProperties(), this.fluent.getMaximum(), this.fluent.getMinItems(), this.fluent.getMinLength(), this.fluent.getMinProperties(), this.fluent.getMinimum(), this.fluent.getMultipleOf(), this.fluent.buildNot(), this.fluent.getNullable(), this.fluent.buildOneOf(), this.fluent.getPattern(), this.fluent.getPatternProperties(), this.fluent.getProperties(), this.fluent.getRequired(), this.fluent.getTitle(), this.fluent.getType(), this.fluent.getUniqueItems(), this.fluent.getXKubernetesEmbeddedResource(), this.fluent.getXKubernetesIntOrString(), this.fluent.getXKubernetesListMapKeys(), this.fluent.getXKubernetesListType(), this.fluent.getXKubernetesMapType(), this.fluent.getXKubernetesPreserveUnknownFields(), this.fluent.buildXKubernetesValidations());
      return buildable;
   }
}
