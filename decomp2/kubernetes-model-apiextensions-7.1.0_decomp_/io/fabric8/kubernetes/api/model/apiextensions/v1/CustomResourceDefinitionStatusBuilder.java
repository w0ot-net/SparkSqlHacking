package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CustomResourceDefinitionStatusBuilder extends CustomResourceDefinitionStatusFluent implements VisitableBuilder {
   CustomResourceDefinitionStatusFluent fluent;

   public CustomResourceDefinitionStatusBuilder() {
      this(new CustomResourceDefinitionStatus());
   }

   public CustomResourceDefinitionStatusBuilder(CustomResourceDefinitionStatusFluent fluent) {
      this(fluent, new CustomResourceDefinitionStatus());
   }

   public CustomResourceDefinitionStatusBuilder(CustomResourceDefinitionStatusFluent fluent, CustomResourceDefinitionStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CustomResourceDefinitionStatusBuilder(CustomResourceDefinitionStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CustomResourceDefinitionStatus build() {
      CustomResourceDefinitionStatus buildable = new CustomResourceDefinitionStatus(this.fluent.buildAcceptedNames(), this.fluent.buildConditions(), this.fluent.getStoredVersions());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
