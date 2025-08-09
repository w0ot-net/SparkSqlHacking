package io.fabric8.kubernetes.api.model.flowcontrol.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PolicyRulesWithSubjectsBuilder extends PolicyRulesWithSubjectsFluent implements VisitableBuilder {
   PolicyRulesWithSubjectsFluent fluent;

   public PolicyRulesWithSubjectsBuilder() {
      this(new PolicyRulesWithSubjects());
   }

   public PolicyRulesWithSubjectsBuilder(PolicyRulesWithSubjectsFluent fluent) {
      this(fluent, new PolicyRulesWithSubjects());
   }

   public PolicyRulesWithSubjectsBuilder(PolicyRulesWithSubjectsFluent fluent, PolicyRulesWithSubjects instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PolicyRulesWithSubjectsBuilder(PolicyRulesWithSubjects instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PolicyRulesWithSubjects build() {
      PolicyRulesWithSubjects buildable = new PolicyRulesWithSubjects(this.fluent.buildNonResourceRules(), this.fluent.buildResourceRules(), this.fluent.buildSubjects());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
