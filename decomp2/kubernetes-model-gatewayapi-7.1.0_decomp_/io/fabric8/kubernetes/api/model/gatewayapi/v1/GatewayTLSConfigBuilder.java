package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GatewayTLSConfigBuilder extends GatewayTLSConfigFluent implements VisitableBuilder {
   GatewayTLSConfigFluent fluent;

   public GatewayTLSConfigBuilder() {
      this(new GatewayTLSConfig());
   }

   public GatewayTLSConfigBuilder(GatewayTLSConfigFluent fluent) {
      this(fluent, new GatewayTLSConfig());
   }

   public GatewayTLSConfigBuilder(GatewayTLSConfigFluent fluent, GatewayTLSConfig instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GatewayTLSConfigBuilder(GatewayTLSConfig instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GatewayTLSConfig build() {
      GatewayTLSConfig buildable = new GatewayTLSConfig(this.fluent.buildCertificateRefs(), this.fluent.buildFrontendValidation(), this.fluent.getMode(), this.fluent.getOptions());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
