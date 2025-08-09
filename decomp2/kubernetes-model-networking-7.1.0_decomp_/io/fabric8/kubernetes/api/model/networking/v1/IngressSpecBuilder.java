package io.fabric8.kubernetes.api.model.networking.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class IngressSpecBuilder extends IngressSpecFluent implements VisitableBuilder {
   IngressSpecFluent fluent;

   public IngressSpecBuilder() {
      this(new IngressSpec());
   }

   public IngressSpecBuilder(IngressSpecFluent fluent) {
      this(fluent, new IngressSpec());
   }

   public IngressSpecBuilder(IngressSpecFluent fluent, IngressSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public IngressSpecBuilder(IngressSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public IngressSpec build() {
      IngressSpec buildable = new IngressSpec(this.fluent.buildDefaultBackend(), this.fluent.getIngressClassName(), this.fluent.buildRules(), this.fluent.buildTls());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
