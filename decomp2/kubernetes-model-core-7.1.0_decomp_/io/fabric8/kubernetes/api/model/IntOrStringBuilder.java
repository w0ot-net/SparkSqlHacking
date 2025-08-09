package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class IntOrStringBuilder extends IntOrStringFluent implements VisitableBuilder {
   IntOrStringFluent fluent;

   public IntOrStringBuilder() {
      this(new IntOrString());
   }

   public IntOrStringBuilder(IntOrStringFluent fluent) {
      this(fluent, new IntOrString());
   }

   public IntOrStringBuilder(IntOrStringFluent fluent, IntOrString instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public IntOrStringBuilder(IntOrString instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public IntOrString build() {
      IntOrString buildable = new IntOrString(this.fluent.getValue());
      return buildable;
   }
}
