package io.fabric8.kubernetes.api.model.authentication;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TokenRequestStatusBuilder extends TokenRequestStatusFluent implements VisitableBuilder {
   TokenRequestStatusFluent fluent;

   public TokenRequestStatusBuilder() {
      this(new TokenRequestStatus());
   }

   public TokenRequestStatusBuilder(TokenRequestStatusFluent fluent) {
      this(fluent, new TokenRequestStatus());
   }

   public TokenRequestStatusBuilder(TokenRequestStatusFluent fluent, TokenRequestStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TokenRequestStatusBuilder(TokenRequestStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TokenRequestStatus build() {
      TokenRequestStatus buildable = new TokenRequestStatus(this.fluent.getExpirationTimestamp(), this.fluent.getToken());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
