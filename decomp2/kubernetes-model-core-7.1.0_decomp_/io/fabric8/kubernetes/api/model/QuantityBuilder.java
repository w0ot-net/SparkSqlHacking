package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class QuantityBuilder extends QuantityFluent implements VisitableBuilder {
   QuantityFluent fluent;

   public QuantityBuilder() {
      this(new Quantity());
   }

   public QuantityBuilder(QuantityFluent fluent) {
      this(fluent, new Quantity());
   }

   public QuantityBuilder(QuantityFluent fluent, Quantity instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public QuantityBuilder(Quantity instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Quantity build() {
      Quantity buildable = new Quantity(this.fluent.getAmount());
      buildable.setFormat(this.fluent.getFormat());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
