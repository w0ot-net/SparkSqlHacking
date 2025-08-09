package io.fabric8.kubernetes.api.model.authentication.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SelfSubjectReviewStatusBuilder extends SelfSubjectReviewStatusFluent implements VisitableBuilder {
   SelfSubjectReviewStatusFluent fluent;

   public SelfSubjectReviewStatusBuilder() {
      this(new SelfSubjectReviewStatus());
   }

   public SelfSubjectReviewStatusBuilder(SelfSubjectReviewStatusFluent fluent) {
      this(fluent, new SelfSubjectReviewStatus());
   }

   public SelfSubjectReviewStatusBuilder(SelfSubjectReviewStatusFluent fluent, SelfSubjectReviewStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SelfSubjectReviewStatusBuilder(SelfSubjectReviewStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SelfSubjectReviewStatus build() {
      SelfSubjectReviewStatus buildable = new SelfSubjectReviewStatus(this.fluent.buildUserInfo());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
