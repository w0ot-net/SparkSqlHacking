package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class IPBlockBuilder extends IPBlockFluent implements VisitableBuilder {
   IPBlockFluent fluent;

   public IPBlockBuilder() {
      this(new IPBlock());
   }

   public IPBlockBuilder(IPBlockFluent fluent) {
      this(fluent, new IPBlock());
   }

   public IPBlockBuilder(IPBlockFluent fluent, IPBlock instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public IPBlockBuilder(IPBlock instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public IPBlock build() {
      IPBlock buildable = new IPBlock(this.fluent.getCidr(), this.fluent.getExcept());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
