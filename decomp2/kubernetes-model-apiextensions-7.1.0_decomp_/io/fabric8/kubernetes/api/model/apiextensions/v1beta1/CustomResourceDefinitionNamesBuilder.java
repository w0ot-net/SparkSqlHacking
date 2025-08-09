package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CustomResourceDefinitionNamesBuilder extends CustomResourceDefinitionNamesFluent implements VisitableBuilder {
   CustomResourceDefinitionNamesFluent fluent;

   public CustomResourceDefinitionNamesBuilder() {
      this(new CustomResourceDefinitionNames());
   }

   public CustomResourceDefinitionNamesBuilder(CustomResourceDefinitionNamesFluent fluent) {
      this(fluent, new CustomResourceDefinitionNames());
   }

   public CustomResourceDefinitionNamesBuilder(CustomResourceDefinitionNamesFluent fluent, CustomResourceDefinitionNames instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CustomResourceDefinitionNamesBuilder(CustomResourceDefinitionNames instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CustomResourceDefinitionNames build() {
      CustomResourceDefinitionNames buildable = new CustomResourceDefinitionNames(this.fluent.getCategories(), this.fluent.getKind(), this.fluent.getListKind(), this.fluent.getPlural(), this.fluent.getShortNames(), this.fluent.getSingular());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
