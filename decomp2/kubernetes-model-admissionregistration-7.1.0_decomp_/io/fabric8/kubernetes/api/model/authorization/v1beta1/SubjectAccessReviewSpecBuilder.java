package io.fabric8.kubernetes.api.model.authorization.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SubjectAccessReviewSpecBuilder extends SubjectAccessReviewSpecFluent implements VisitableBuilder {
   SubjectAccessReviewSpecFluent fluent;

   public SubjectAccessReviewSpecBuilder() {
      this(new SubjectAccessReviewSpec());
   }

   public SubjectAccessReviewSpecBuilder(SubjectAccessReviewSpecFluent fluent) {
      this(fluent, new SubjectAccessReviewSpec());
   }

   public SubjectAccessReviewSpecBuilder(SubjectAccessReviewSpecFluent fluent, SubjectAccessReviewSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SubjectAccessReviewSpecBuilder(SubjectAccessReviewSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SubjectAccessReviewSpec build() {
      SubjectAccessReviewSpec buildable = new SubjectAccessReviewSpec(this.fluent.getExtra(), this.fluent.getGroup(), this.fluent.buildNonResourceAttributes(), this.fluent.buildResourceAttributes(), this.fluent.getUid(), this.fluent.getUser());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
