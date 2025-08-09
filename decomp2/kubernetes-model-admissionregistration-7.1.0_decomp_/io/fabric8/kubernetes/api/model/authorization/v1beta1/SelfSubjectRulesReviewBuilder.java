package io.fabric8.kubernetes.api.model.authorization.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SelfSubjectRulesReviewBuilder extends SelfSubjectRulesReviewFluent implements VisitableBuilder {
   SelfSubjectRulesReviewFluent fluent;

   public SelfSubjectRulesReviewBuilder() {
      this(new SelfSubjectRulesReview());
   }

   public SelfSubjectRulesReviewBuilder(SelfSubjectRulesReviewFluent fluent) {
      this(fluent, new SelfSubjectRulesReview());
   }

   public SelfSubjectRulesReviewBuilder(SelfSubjectRulesReviewFluent fluent, SelfSubjectRulesReview instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SelfSubjectRulesReviewBuilder(SelfSubjectRulesReview instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SelfSubjectRulesReview build() {
      SelfSubjectRulesReview buildable = new SelfSubjectRulesReview(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
