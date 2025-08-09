package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TolerationBuilder extends TolerationFluent implements VisitableBuilder {
   TolerationFluent fluent;

   public TolerationBuilder() {
      this(new Toleration());
   }

   public TolerationBuilder(TolerationFluent fluent) {
      this(fluent, new Toleration());
   }

   public TolerationBuilder(TolerationFluent fluent, Toleration instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TolerationBuilder(Toleration instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Toleration build() {
      Toleration buildable = new Toleration(this.fluent.getEffect(), this.fluent.getKey(), this.fluent.getOperator(), this.fluent.getTolerationSeconds(), this.fluent.getValue());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
