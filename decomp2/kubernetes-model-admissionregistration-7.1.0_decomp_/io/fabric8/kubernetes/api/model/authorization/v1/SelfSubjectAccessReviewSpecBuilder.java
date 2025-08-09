package io.fabric8.kubernetes.api.model.authorization.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SelfSubjectAccessReviewSpecBuilder extends SelfSubjectAccessReviewSpecFluent implements VisitableBuilder {
   SelfSubjectAccessReviewSpecFluent fluent;

   public SelfSubjectAccessReviewSpecBuilder() {
      this(new SelfSubjectAccessReviewSpec());
   }

   public SelfSubjectAccessReviewSpecBuilder(SelfSubjectAccessReviewSpecFluent fluent) {
      this(fluent, new SelfSubjectAccessReviewSpec());
   }

   public SelfSubjectAccessReviewSpecBuilder(SelfSubjectAccessReviewSpecFluent fluent, SelfSubjectAccessReviewSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SelfSubjectAccessReviewSpecBuilder(SelfSubjectAccessReviewSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SelfSubjectAccessReviewSpec build() {
      SelfSubjectAccessReviewSpec buildable = new SelfSubjectAccessReviewSpec(this.fluent.buildNonResourceAttributes(), this.fluent.buildResourceAttributes());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
