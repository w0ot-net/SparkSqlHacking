package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class JSONSchemaPropsOrStringArrayBuilder extends JSONSchemaPropsOrStringArrayFluent implements VisitableBuilder {
   JSONSchemaPropsOrStringArrayFluent fluent;

   public JSONSchemaPropsOrStringArrayBuilder() {
      this(new JSONSchemaPropsOrStringArray());
   }

   public JSONSchemaPropsOrStringArrayBuilder(JSONSchemaPropsOrStringArrayFluent fluent) {
      this(fluent, new JSONSchemaPropsOrStringArray());
   }

   public JSONSchemaPropsOrStringArrayBuilder(JSONSchemaPropsOrStringArrayFluent fluent, JSONSchemaPropsOrStringArray instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public JSONSchemaPropsOrStringArrayBuilder(JSONSchemaPropsOrStringArray instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public JSONSchemaPropsOrStringArray build() {
      JSONSchemaPropsOrStringArray buildable = new JSONSchemaPropsOrStringArray(this.fluent.getProperty(), this.fluent.buildSchema());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
