package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HTTPRequestMirrorFilterBuilder extends HTTPRequestMirrorFilterFluent implements VisitableBuilder {
   HTTPRequestMirrorFilterFluent fluent;

   public HTTPRequestMirrorFilterBuilder() {
      this(new HTTPRequestMirrorFilter());
   }

   public HTTPRequestMirrorFilterBuilder(HTTPRequestMirrorFilterFluent fluent) {
      this(fluent, new HTTPRequestMirrorFilter());
   }

   public HTTPRequestMirrorFilterBuilder(HTTPRequestMirrorFilterFluent fluent, HTTPRequestMirrorFilter instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HTTPRequestMirrorFilterBuilder(HTTPRequestMirrorFilter instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HTTPRequestMirrorFilter build() {
      HTTPRequestMirrorFilter buildable = new HTTPRequestMirrorFilter(this.fluent.buildBackendRef(), this.fluent.buildFraction(), this.fluent.getPercent());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
