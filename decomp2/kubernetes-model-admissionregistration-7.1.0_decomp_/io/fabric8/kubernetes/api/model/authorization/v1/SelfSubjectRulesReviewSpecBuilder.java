package io.fabric8.kubernetes.api.model.authorization.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SelfSubjectRulesReviewSpecBuilder extends SelfSubjectRulesReviewSpecFluent implements VisitableBuilder {
   SelfSubjectRulesReviewSpecFluent fluent;

   public SelfSubjectRulesReviewSpecBuilder() {
      this(new SelfSubjectRulesReviewSpec());
   }

   public SelfSubjectRulesReviewSpecBuilder(SelfSubjectRulesReviewSpecFluent fluent) {
      this(fluent, new SelfSubjectRulesReviewSpec());
   }

   public SelfSubjectRulesReviewSpecBuilder(SelfSubjectRulesReviewSpecFluent fluent, SelfSubjectRulesReviewSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SelfSubjectRulesReviewSpecBuilder(SelfSubjectRulesReviewSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SelfSubjectRulesReviewSpec build() {
      SelfSubjectRulesReviewSpec buildable = new SelfSubjectRulesReviewSpec(this.fluent.getNamespace());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
