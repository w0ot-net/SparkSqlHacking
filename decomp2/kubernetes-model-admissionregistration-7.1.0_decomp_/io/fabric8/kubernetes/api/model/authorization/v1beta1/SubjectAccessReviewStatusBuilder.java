package io.fabric8.kubernetes.api.model.authorization.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SubjectAccessReviewStatusBuilder extends SubjectAccessReviewStatusFluent implements VisitableBuilder {
   SubjectAccessReviewStatusFluent fluent;

   public SubjectAccessReviewStatusBuilder() {
      this(new SubjectAccessReviewStatus());
   }

   public SubjectAccessReviewStatusBuilder(SubjectAccessReviewStatusFluent fluent) {
      this(fluent, new SubjectAccessReviewStatus());
   }

   public SubjectAccessReviewStatusBuilder(SubjectAccessReviewStatusFluent fluent, SubjectAccessReviewStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SubjectAccessReviewStatusBuilder(SubjectAccessReviewStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SubjectAccessReviewStatus build() {
      SubjectAccessReviewStatus buildable = new SubjectAccessReviewStatus(this.fluent.getAllowed(), this.fluent.getDenied(), this.fluent.getEvaluationError(), this.fluent.getReason());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
