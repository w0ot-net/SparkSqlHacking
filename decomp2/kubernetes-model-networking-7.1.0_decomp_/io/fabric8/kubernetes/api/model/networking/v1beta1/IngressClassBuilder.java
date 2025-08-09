package io.fabric8.kubernetes.api.model.networking.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class IngressClassBuilder extends IngressClassFluent implements VisitableBuilder {
   IngressClassFluent fluent;

   public IngressClassBuilder() {
      this(new IngressClass());
   }

   public IngressClassBuilder(IngressClassFluent fluent) {
      this(fluent, new IngressClass());
   }

   public IngressClassBuilder(IngressClassFluent fluent, IngressClass instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public IngressClassBuilder(IngressClass instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public IngressClass build() {
      IngressClass buildable = new IngressClass(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
