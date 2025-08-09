package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CustomResourceSubresourcesBuilder extends CustomResourceSubresourcesFluent implements VisitableBuilder {
   CustomResourceSubresourcesFluent fluent;

   public CustomResourceSubresourcesBuilder() {
      this(new CustomResourceSubresources());
   }

   public CustomResourceSubresourcesBuilder(CustomResourceSubresourcesFluent fluent) {
      this(fluent, new CustomResourceSubresources());
   }

   public CustomResourceSubresourcesBuilder(CustomResourceSubresourcesFluent fluent, CustomResourceSubresources instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CustomResourceSubresourcesBuilder(CustomResourceSubresources instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CustomResourceSubresources build() {
      CustomResourceSubresources buildable = new CustomResourceSubresources(this.fluent.buildScale(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
