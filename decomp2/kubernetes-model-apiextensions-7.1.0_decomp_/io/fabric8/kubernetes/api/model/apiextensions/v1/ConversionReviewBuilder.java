package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ConversionReviewBuilder extends ConversionReviewFluent implements VisitableBuilder {
   ConversionReviewFluent fluent;

   public ConversionReviewBuilder() {
      this(new ConversionReview());
   }

   public ConversionReviewBuilder(ConversionReviewFluent fluent) {
      this(fluent, new ConversionReview());
   }

   public ConversionReviewBuilder(ConversionReviewFluent fluent, ConversionReview instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ConversionReviewBuilder(ConversionReview instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ConversionReview build() {
      ConversionReview buildable = new ConversionReview(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildRequest(), this.fluent.buildResponse());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
