package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CustomResourceDefinitionListBuilder extends CustomResourceDefinitionListFluent implements VisitableBuilder {
   CustomResourceDefinitionListFluent fluent;

   public CustomResourceDefinitionListBuilder() {
      this(new CustomResourceDefinitionList());
   }

   public CustomResourceDefinitionListBuilder(CustomResourceDefinitionListFluent fluent) {
      this(fluent, new CustomResourceDefinitionList());
   }

   public CustomResourceDefinitionListBuilder(CustomResourceDefinitionListFluent fluent, CustomResourceDefinitionList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CustomResourceDefinitionListBuilder(CustomResourceDefinitionList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CustomResourceDefinitionList build() {
      CustomResourceDefinitionList buildable = new CustomResourceDefinitionList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
