package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class AuthProviderConfigBuilder extends AuthProviderConfigFluent implements VisitableBuilder {
   AuthProviderConfigFluent fluent;

   public AuthProviderConfigBuilder() {
      this(new AuthProviderConfig());
   }

   public AuthProviderConfigBuilder(AuthProviderConfigFluent fluent) {
      this(fluent, new AuthProviderConfig());
   }

   public AuthProviderConfigBuilder(AuthProviderConfigFluent fluent, AuthProviderConfig instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public AuthProviderConfigBuilder(AuthProviderConfig instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public AuthProviderConfig build() {
      AuthProviderConfig buildable = new AuthProviderConfig(this.fluent.getConfig(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
