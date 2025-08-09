package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CustomResourceDefinitionBuilder extends CustomResourceDefinitionFluent implements VisitableBuilder {
   CustomResourceDefinitionFluent fluent;

   public CustomResourceDefinitionBuilder() {
      this(new CustomResourceDefinition());
   }

   public CustomResourceDefinitionBuilder(CustomResourceDefinitionFluent fluent) {
      this(fluent, new CustomResourceDefinition());
   }

   public CustomResourceDefinitionBuilder(CustomResourceDefinitionFluent fluent, CustomResourceDefinition instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CustomResourceDefinitionBuilder(CustomResourceDefinition instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CustomResourceDefinition build() {
      CustomResourceDefinition buildable = new CustomResourceDefinition(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
