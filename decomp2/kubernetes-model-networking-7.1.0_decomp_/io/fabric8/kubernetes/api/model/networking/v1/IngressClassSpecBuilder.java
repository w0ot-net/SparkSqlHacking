package io.fabric8.kubernetes.api.model.networking.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class IngressClassSpecBuilder extends IngressClassSpecFluent implements VisitableBuilder {
   IngressClassSpecFluent fluent;

   public IngressClassSpecBuilder() {
      this(new IngressClassSpec());
   }

   public IngressClassSpecBuilder(IngressClassSpecFluent fluent) {
      this(fluent, new IngressClassSpec());
   }

   public IngressClassSpecBuilder(IngressClassSpecFluent fluent, IngressClassSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public IngressClassSpecBuilder(IngressClassSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public IngressClassSpec build() {
      IngressClassSpec buildable = new IngressClassSpec(this.fluent.getController(), this.fluent.buildParameters());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
