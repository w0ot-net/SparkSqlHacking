package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PreferredSchedulingTermBuilder extends PreferredSchedulingTermFluent implements VisitableBuilder {
   PreferredSchedulingTermFluent fluent;

   public PreferredSchedulingTermBuilder() {
      this(new PreferredSchedulingTerm());
   }

   public PreferredSchedulingTermBuilder(PreferredSchedulingTermFluent fluent) {
      this(fluent, new PreferredSchedulingTerm());
   }

   public PreferredSchedulingTermBuilder(PreferredSchedulingTermFluent fluent, PreferredSchedulingTerm instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PreferredSchedulingTermBuilder(PreferredSchedulingTerm instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PreferredSchedulingTerm build() {
      PreferredSchedulingTerm buildable = new PreferredSchedulingTerm(this.fluent.buildPreference(), this.fluent.getWeight());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
