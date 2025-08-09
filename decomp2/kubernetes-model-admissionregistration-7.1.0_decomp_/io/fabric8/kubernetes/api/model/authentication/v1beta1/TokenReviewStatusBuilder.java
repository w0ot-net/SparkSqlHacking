package io.fabric8.kubernetes.api.model.authentication.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TokenReviewStatusBuilder extends TokenReviewStatusFluent implements VisitableBuilder {
   TokenReviewStatusFluent fluent;

   public TokenReviewStatusBuilder() {
      this(new TokenReviewStatus());
   }

   public TokenReviewStatusBuilder(TokenReviewStatusFluent fluent) {
      this(fluent, new TokenReviewStatus());
   }

   public TokenReviewStatusBuilder(TokenReviewStatusFluent fluent, TokenReviewStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TokenReviewStatusBuilder(TokenReviewStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TokenReviewStatus build() {
      TokenReviewStatus buildable = new TokenReviewStatus(this.fluent.getAudiences(), this.fluent.getAuthenticated(), this.fluent.getError(), this.fluent.buildUser());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
