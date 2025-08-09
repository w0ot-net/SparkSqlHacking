package io.fabric8.kubernetes.api.model.authorization.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SelfSubjectAccessReviewBuilder extends SelfSubjectAccessReviewFluent implements VisitableBuilder {
   SelfSubjectAccessReviewFluent fluent;

   public SelfSubjectAccessReviewBuilder() {
      this(new SelfSubjectAccessReview());
   }

   public SelfSubjectAccessReviewBuilder(SelfSubjectAccessReviewFluent fluent) {
      this(fluent, new SelfSubjectAccessReview());
   }

   public SelfSubjectAccessReviewBuilder(SelfSubjectAccessReviewFluent fluent, SelfSubjectAccessReview instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SelfSubjectAccessReviewBuilder(SelfSubjectAccessReview instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SelfSubjectAccessReview build() {
      SelfSubjectAccessReview buildable = new SelfSubjectAccessReview(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
