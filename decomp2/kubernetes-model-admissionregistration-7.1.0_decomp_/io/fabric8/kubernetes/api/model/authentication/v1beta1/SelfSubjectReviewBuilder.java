package io.fabric8.kubernetes.api.model.authentication.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SelfSubjectReviewBuilder extends SelfSubjectReviewFluent implements VisitableBuilder {
   SelfSubjectReviewFluent fluent;

   public SelfSubjectReviewBuilder() {
      this(new SelfSubjectReview());
   }

   public SelfSubjectReviewBuilder(SelfSubjectReviewFluent fluent) {
      this(fluent, new SelfSubjectReview());
   }

   public SelfSubjectReviewBuilder(SelfSubjectReviewFluent fluent, SelfSubjectReview instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SelfSubjectReviewBuilder(SelfSubjectReview instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SelfSubjectReview build() {
      SelfSubjectReview buildable = new SelfSubjectReview(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
