package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CustomResourceConversionBuilder extends CustomResourceConversionFluent implements VisitableBuilder {
   CustomResourceConversionFluent fluent;

   public CustomResourceConversionBuilder() {
      this(new CustomResourceConversion());
   }

   public CustomResourceConversionBuilder(CustomResourceConversionFluent fluent) {
      this(fluent, new CustomResourceConversion());
   }

   public CustomResourceConversionBuilder(CustomResourceConversionFluent fluent, CustomResourceConversion instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CustomResourceConversionBuilder(CustomResourceConversion instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CustomResourceConversion build() {
      CustomResourceConversion buildable = new CustomResourceConversion(this.fluent.getConversionReviewVersions(), this.fluent.getStrategy(), this.fluent.buildWebhookClientConfig());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
