package io.fabric8.kubernetes.api.model.networking.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class IngressListBuilder extends IngressListFluent implements VisitableBuilder {
   IngressListFluent fluent;

   public IngressListBuilder() {
      this(new IngressList());
   }

   public IngressListBuilder(IngressListFluent fluent) {
      this(fluent, new IngressList());
   }

   public IngressListBuilder(IngressListFluent fluent, IngressList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public IngressListBuilder(IngressList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public IngressList build() {
      IngressList buildable = new IngressList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
