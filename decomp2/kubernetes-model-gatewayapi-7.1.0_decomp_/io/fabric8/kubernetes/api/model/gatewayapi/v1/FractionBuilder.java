package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class FractionBuilder extends FractionFluent implements VisitableBuilder {
   FractionFluent fluent;

   public FractionBuilder() {
      this(new Fraction());
   }

   public FractionBuilder(FractionFluent fluent) {
      this(fluent, new Fraction());
   }

   public FractionBuilder(FractionFluent fluent, Fraction instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public FractionBuilder(Fraction instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Fraction build() {
      Fraction buildable = new Fraction(this.fluent.getDenominator(), this.fluent.getNumerator());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
