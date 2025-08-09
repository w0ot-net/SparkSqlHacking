package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GatewayBackendTLSBuilder extends GatewayBackendTLSFluent implements VisitableBuilder {
   GatewayBackendTLSFluent fluent;

   public GatewayBackendTLSBuilder() {
      this(new GatewayBackendTLS());
   }

   public GatewayBackendTLSBuilder(GatewayBackendTLSFluent fluent) {
      this(fluent, new GatewayBackendTLS());
   }

   public GatewayBackendTLSBuilder(GatewayBackendTLSFluent fluent, GatewayBackendTLS instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GatewayBackendTLSBuilder(GatewayBackendTLS instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GatewayBackendTLS build() {
      GatewayBackendTLS buildable = new GatewayBackendTLS(this.fluent.buildClientCertificateRef());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
