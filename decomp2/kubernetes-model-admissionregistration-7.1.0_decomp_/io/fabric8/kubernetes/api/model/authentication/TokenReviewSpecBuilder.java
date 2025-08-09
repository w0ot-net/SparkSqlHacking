package io.fabric8.kubernetes.api.model.authentication;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TokenReviewSpecBuilder extends TokenReviewSpecFluent implements VisitableBuilder {
   TokenReviewSpecFluent fluent;

   public TokenReviewSpecBuilder() {
      this(new TokenReviewSpec());
   }

   public TokenReviewSpecBuilder(TokenReviewSpecFluent fluent) {
      this(fluent, new TokenReviewSpec());
   }

   public TokenReviewSpecBuilder(TokenReviewSpecFluent fluent, TokenReviewSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TokenReviewSpecBuilder(TokenReviewSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TokenReviewSpec build() {
      TokenReviewSpec buildable = new TokenReviewSpec(this.fluent.getAudiences(), this.fluent.getToken());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
