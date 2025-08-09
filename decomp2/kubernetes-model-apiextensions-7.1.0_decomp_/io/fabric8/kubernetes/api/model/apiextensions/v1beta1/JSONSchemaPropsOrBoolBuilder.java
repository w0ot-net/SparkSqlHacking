package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class JSONSchemaPropsOrBoolBuilder extends JSONSchemaPropsOrBoolFluent implements VisitableBuilder {
   JSONSchemaPropsOrBoolFluent fluent;

   public JSONSchemaPropsOrBoolBuilder() {
      this(new JSONSchemaPropsOrBool());
   }

   public JSONSchemaPropsOrBoolBuilder(JSONSchemaPropsOrBoolFluent fluent) {
      this(fluent, new JSONSchemaPropsOrBool());
   }

   public JSONSchemaPropsOrBoolBuilder(JSONSchemaPropsOrBoolFluent fluent, JSONSchemaPropsOrBool instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public JSONSchemaPropsOrBoolBuilder(JSONSchemaPropsOrBool instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public JSONSchemaPropsOrBool build() {
      JSONSchemaPropsOrBool buildable = new JSONSchemaPropsOrBool(this.fluent.getAllows(), this.fluent.buildSchema());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
