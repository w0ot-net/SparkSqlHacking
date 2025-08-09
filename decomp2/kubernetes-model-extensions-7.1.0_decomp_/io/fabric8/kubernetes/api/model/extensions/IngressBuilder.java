package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class IngressBuilder extends IngressFluent implements VisitableBuilder {
   IngressFluent fluent;

   public IngressBuilder() {
      this(new Ingress());
   }

   public IngressBuilder(IngressFluent fluent) {
      this(fluent, new Ingress());
   }

   public IngressBuilder(IngressFluent fluent, Ingress instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public IngressBuilder(Ingress instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Ingress build() {
      Ingress buildable = new Ingress(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
