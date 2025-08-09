package io.fabric8.kubernetes.api.model.authorization.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LocalSubjectAccessReviewBuilder extends LocalSubjectAccessReviewFluent implements VisitableBuilder {
   LocalSubjectAccessReviewFluent fluent;

   public LocalSubjectAccessReviewBuilder() {
      this(new LocalSubjectAccessReview());
   }

   public LocalSubjectAccessReviewBuilder(LocalSubjectAccessReviewFluent fluent) {
      this(fluent, new LocalSubjectAccessReview());
   }

   public LocalSubjectAccessReviewBuilder(LocalSubjectAccessReviewFluent fluent, LocalSubjectAccessReview instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LocalSubjectAccessReviewBuilder(LocalSubjectAccessReview instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LocalSubjectAccessReview build() {
      LocalSubjectAccessReview buildable = new LocalSubjectAccessReview(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
