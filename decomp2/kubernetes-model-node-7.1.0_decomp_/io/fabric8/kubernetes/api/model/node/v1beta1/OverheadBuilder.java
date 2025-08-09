package io.fabric8.kubernetes.api.model.node.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class OverheadBuilder extends OverheadFluent implements VisitableBuilder {
   OverheadFluent fluent;

   public OverheadBuilder() {
      this(new Overhead());
   }

   public OverheadBuilder(OverheadFluent fluent) {
      this(fluent, new Overhead());
   }

   public OverheadBuilder(OverheadFluent fluent, Overhead instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public OverheadBuilder(Overhead instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Overhead build() {
      Overhead buildable = new Overhead(this.fluent.getPodFixed());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
