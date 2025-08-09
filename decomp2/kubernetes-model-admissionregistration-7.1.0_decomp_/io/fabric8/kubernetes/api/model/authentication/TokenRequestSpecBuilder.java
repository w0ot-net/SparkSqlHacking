package io.fabric8.kubernetes.api.model.authentication;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TokenRequestSpecBuilder extends TokenRequestSpecFluent implements VisitableBuilder {
   TokenRequestSpecFluent fluent;

   public TokenRequestSpecBuilder() {
      this(new TokenRequestSpec());
   }

   public TokenRequestSpecBuilder(TokenRequestSpecFluent fluent) {
      this(fluent, new TokenRequestSpec());
   }

   public TokenRequestSpecBuilder(TokenRequestSpecFluent fluent, TokenRequestSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TokenRequestSpecBuilder(TokenRequestSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TokenRequestSpec build() {
      TokenRequestSpec buildable = new TokenRequestSpec(this.fluent.getAudiences(), this.fluent.buildBoundObjectRef(), this.fluent.getExpirationSeconds());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
