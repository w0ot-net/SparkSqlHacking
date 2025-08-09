package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CustomResourceSubresourceStatusBuilder extends CustomResourceSubresourceStatusFluent implements VisitableBuilder {
   CustomResourceSubresourceStatusFluent fluent;

   public CustomResourceSubresourceStatusBuilder() {
      this(new CustomResourceSubresourceStatus());
   }

   public CustomResourceSubresourceStatusBuilder(CustomResourceSubresourceStatusFluent fluent) {
      this(fluent, new CustomResourceSubresourceStatus());
   }

   public CustomResourceSubresourceStatusBuilder(CustomResourceSubresourceStatusFluent fluent, CustomResourceSubresourceStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CustomResourceSubresourceStatusBuilder(CustomResourceSubresourceStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CustomResourceSubresourceStatus build() {
      CustomResourceSubresourceStatus buildable = new CustomResourceSubresourceStatus();
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
