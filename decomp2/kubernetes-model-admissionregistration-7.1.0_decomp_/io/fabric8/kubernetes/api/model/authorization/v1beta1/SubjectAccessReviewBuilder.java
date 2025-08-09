package io.fabric8.kubernetes.api.model.authorization.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SubjectAccessReviewBuilder extends SubjectAccessReviewFluent implements VisitableBuilder {
   SubjectAccessReviewFluent fluent;

   public SubjectAccessReviewBuilder() {
      this(new SubjectAccessReview());
   }

   public SubjectAccessReviewBuilder(SubjectAccessReviewFluent fluent) {
      this(fluent, new SubjectAccessReview());
   }

   public SubjectAccessReviewBuilder(SubjectAccessReviewFluent fluent, SubjectAccessReview instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SubjectAccessReviewBuilder(SubjectAccessReview instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SubjectAccessReview build() {
      SubjectAccessReview buildable = new SubjectAccessReview(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
