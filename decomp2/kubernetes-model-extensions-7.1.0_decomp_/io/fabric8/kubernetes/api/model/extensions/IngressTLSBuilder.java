package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class IngressTLSBuilder extends IngressTLSFluent implements VisitableBuilder {
   IngressTLSFluent fluent;

   public IngressTLSBuilder() {
      this(new IngressTLS());
   }

   public IngressTLSBuilder(IngressTLSFluent fluent) {
      this(fluent, new IngressTLS());
   }

   public IngressTLSBuilder(IngressTLSFluent fluent, IngressTLS instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public IngressTLSBuilder(IngressTLS instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public IngressTLS build() {
      IngressTLS buildable = new IngressTLS(this.fluent.getHosts(), this.fluent.getSecretName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
