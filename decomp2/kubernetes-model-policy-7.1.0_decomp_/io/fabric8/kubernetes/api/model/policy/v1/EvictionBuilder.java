package io.fabric8.kubernetes.api.model.policy.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class EvictionBuilder extends EvictionFluent implements VisitableBuilder {
   EvictionFluent fluent;

   public EvictionBuilder() {
      this(new Eviction());
   }

   public EvictionBuilder(EvictionFluent fluent) {
      this(fluent, new Eviction());
   }

   public EvictionBuilder(EvictionFluent fluent, Eviction instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public EvictionBuilder(Eviction instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Eviction build() {
      Eviction buildable = new Eviction(this.fluent.getApiVersion(), this.fluent.getDeleteOptions(), this.fluent.getKind(), this.fluent.buildMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
