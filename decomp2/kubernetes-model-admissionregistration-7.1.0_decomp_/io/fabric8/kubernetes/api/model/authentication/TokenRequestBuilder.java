package io.fabric8.kubernetes.api.model.authentication;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TokenRequestBuilder extends TokenRequestFluent implements VisitableBuilder {
   TokenRequestFluent fluent;

   public TokenRequestBuilder() {
      this(new TokenRequest());
   }

   public TokenRequestBuilder(TokenRequestFluent fluent) {
      this(fluent, new TokenRequest());
   }

   public TokenRequestBuilder(TokenRequestFluent fluent, TokenRequest instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TokenRequestBuilder(TokenRequest instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TokenRequest build() {
      TokenRequest buildable = new TokenRequest(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
