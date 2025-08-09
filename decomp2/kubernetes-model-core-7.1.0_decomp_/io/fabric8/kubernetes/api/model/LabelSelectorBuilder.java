package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LabelSelectorBuilder extends LabelSelectorFluent implements VisitableBuilder {
   LabelSelectorFluent fluent;

   public LabelSelectorBuilder() {
      this(new LabelSelector());
   }

   public LabelSelectorBuilder(LabelSelectorFluent fluent) {
      this(fluent, new LabelSelector());
   }

   public LabelSelectorBuilder(LabelSelectorFluent fluent, LabelSelector instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LabelSelectorBuilder(LabelSelector instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LabelSelector build() {
      LabelSelector buildable = new LabelSelector(this.fluent.buildMatchExpressions(), this.fluent.getMatchLabels());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
