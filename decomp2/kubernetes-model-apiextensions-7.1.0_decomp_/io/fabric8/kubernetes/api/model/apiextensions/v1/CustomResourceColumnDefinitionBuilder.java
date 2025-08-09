package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CustomResourceColumnDefinitionBuilder extends CustomResourceColumnDefinitionFluent implements VisitableBuilder {
   CustomResourceColumnDefinitionFluent fluent;

   public CustomResourceColumnDefinitionBuilder() {
      this(new CustomResourceColumnDefinition());
   }

   public CustomResourceColumnDefinitionBuilder(CustomResourceColumnDefinitionFluent fluent) {
      this(fluent, new CustomResourceColumnDefinition());
   }

   public CustomResourceColumnDefinitionBuilder(CustomResourceColumnDefinitionFluent fluent, CustomResourceColumnDefinition instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CustomResourceColumnDefinitionBuilder(CustomResourceColumnDefinition instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CustomResourceColumnDefinition build() {
      CustomResourceColumnDefinition buildable = new CustomResourceColumnDefinition(this.fluent.getDescription(), this.fluent.getFormat(), this.fluent.getJsonPath(), this.fluent.getName(), this.fluent.getPriority(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
