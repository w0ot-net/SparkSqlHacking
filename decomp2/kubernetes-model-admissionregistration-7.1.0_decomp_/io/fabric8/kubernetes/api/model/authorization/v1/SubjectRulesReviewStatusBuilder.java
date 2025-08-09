package io.fabric8.kubernetes.api.model.authorization.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SubjectRulesReviewStatusBuilder extends SubjectRulesReviewStatusFluent implements VisitableBuilder {
   SubjectRulesReviewStatusFluent fluent;

   public SubjectRulesReviewStatusBuilder() {
      this(new SubjectRulesReviewStatus());
   }

   public SubjectRulesReviewStatusBuilder(SubjectRulesReviewStatusFluent fluent) {
      this(fluent, new SubjectRulesReviewStatus());
   }

   public SubjectRulesReviewStatusBuilder(SubjectRulesReviewStatusFluent fluent, SubjectRulesReviewStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SubjectRulesReviewStatusBuilder(SubjectRulesReviewStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SubjectRulesReviewStatus build() {
      SubjectRulesReviewStatus buildable = new SubjectRulesReviewStatus(this.fluent.getEvaluationError(), this.fluent.getIncomplete(), this.fluent.buildNonResourceRules(), this.fluent.buildResourceRules());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
