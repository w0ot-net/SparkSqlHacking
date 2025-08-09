package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class AuthInfoBuilder extends AuthInfoFluent implements VisitableBuilder {
   AuthInfoFluent fluent;

   public AuthInfoBuilder() {
      this(new AuthInfo());
   }

   public AuthInfoBuilder(AuthInfoFluent fluent) {
      this(fluent, new AuthInfo());
   }

   public AuthInfoBuilder(AuthInfoFluent fluent, AuthInfo instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public AuthInfoBuilder(AuthInfo instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public AuthInfo build() {
      AuthInfo buildable = new AuthInfo(this.fluent.getAs(), this.fluent.getAsGroups(), this.fluent.getAsUid(), this.fluent.getAsUserExtra(), this.fluent.buildAuthProvider(), this.fluent.getClientCertificate(), this.fluent.getClientCertificateData(), this.fluent.getClientKey(), this.fluent.getClientKeyData(), this.fluent.buildExec(), this.fluent.buildExtensions(), this.fluent.getPassword(), this.fluent.getToken(), this.fluent.getTokenFile(), this.fluent.getUsername());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
