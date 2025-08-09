package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CustomResourceSubresourceScaleBuilder extends CustomResourceSubresourceScaleFluent implements VisitableBuilder {
   CustomResourceSubresourceScaleFluent fluent;

   public CustomResourceSubresourceScaleBuilder() {
      this(new CustomResourceSubresourceScale());
   }

   public CustomResourceSubresourceScaleBuilder(CustomResourceSubresourceScaleFluent fluent) {
      this(fluent, new CustomResourceSubresourceScale());
   }

   public CustomResourceSubresourceScaleBuilder(CustomResourceSubresourceScaleFluent fluent, CustomResourceSubresourceScale instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CustomResourceSubresourceScaleBuilder(CustomResourceSubresourceScale instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CustomResourceSubresourceScale build() {
      CustomResourceSubresourceScale buildable = new CustomResourceSubresourceScale(this.fluent.getLabelSelectorPath(), this.fluent.getSpecReplicasPath(), this.fluent.getStatusReplicasPath());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
