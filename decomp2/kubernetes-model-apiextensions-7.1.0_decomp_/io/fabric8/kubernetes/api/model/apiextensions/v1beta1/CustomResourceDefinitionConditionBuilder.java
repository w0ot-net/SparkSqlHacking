package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CustomResourceDefinitionConditionBuilder extends CustomResourceDefinitionConditionFluent implements VisitableBuilder {
   CustomResourceDefinitionConditionFluent fluent;

   public CustomResourceDefinitionConditionBuilder() {
      this(new CustomResourceDefinitionCondition());
   }

   public CustomResourceDefinitionConditionBuilder(CustomResourceDefinitionConditionFluent fluent) {
      this(fluent, new CustomResourceDefinitionCondition());
   }

   public CustomResourceDefinitionConditionBuilder(CustomResourceDefinitionConditionFluent fluent, CustomResourceDefinitionCondition instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CustomResourceDefinitionConditionBuilder(CustomResourceDefinitionCondition instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CustomResourceDefinitionCondition build() {
      CustomResourceDefinitionCondition buildable = new CustomResourceDefinitionCondition(this.fluent.getLastTransitionTime(), this.fluent.getMessage(), this.fluent.getReason(), this.fluent.getStatus(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
