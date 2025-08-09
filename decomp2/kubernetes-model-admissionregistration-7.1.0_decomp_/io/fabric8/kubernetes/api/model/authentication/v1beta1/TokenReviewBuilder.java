package io.fabric8.kubernetes.api.model.authentication.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TokenReviewBuilder extends TokenReviewFluent implements VisitableBuilder {
   TokenReviewFluent fluent;

   public TokenReviewBuilder() {
      this(new TokenReview());
   }

   public TokenReviewBuilder(TokenReviewFluent fluent) {
      this(fluent, new TokenReview());
   }

   public TokenReviewBuilder(TokenReviewFluent fluent, TokenReview instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TokenReviewBuilder(TokenReview instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TokenReview build() {
      TokenReview buildable = new TokenReview(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
