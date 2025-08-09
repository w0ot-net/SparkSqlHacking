package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class JSONSchemaPropsOrArrayBuilder extends JSONSchemaPropsOrArrayFluent implements VisitableBuilder {
   JSONSchemaPropsOrArrayFluent fluent;

   public JSONSchemaPropsOrArrayBuilder() {
      this(new JSONSchemaPropsOrArray());
   }

   public JSONSchemaPropsOrArrayBuilder(JSONSchemaPropsOrArrayFluent fluent) {
      this(fluent, new JSONSchemaPropsOrArray());
   }

   public JSONSchemaPropsOrArrayBuilder(JSONSchemaPropsOrArrayFluent fluent, JSONSchemaPropsOrArray instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public JSONSchemaPropsOrArrayBuilder(JSONSchemaPropsOrArray instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public JSONSchemaPropsOrArray build() {
      JSONSchemaPropsOrArray buildable = new JSONSchemaPropsOrArray(this.fluent.buildJSONSchemas(), this.fluent.buildSchema());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
