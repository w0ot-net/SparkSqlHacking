package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class WeightedPodAffinityTermBuilder extends WeightedPodAffinityTermFluent implements VisitableBuilder {
   WeightedPodAffinityTermFluent fluent;

   public WeightedPodAffinityTermBuilder() {
      this(new WeightedPodAffinityTerm());
   }

   public WeightedPodAffinityTermBuilder(WeightedPodAffinityTermFluent fluent) {
      this(fluent, new WeightedPodAffinityTerm());
   }

   public WeightedPodAffinityTermBuilder(WeightedPodAffinityTermFluent fluent, WeightedPodAffinityTerm instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public WeightedPodAffinityTermBuilder(WeightedPodAffinityTerm instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public WeightedPodAffinityTerm build() {
      WeightedPodAffinityTerm buildable = new WeightedPodAffinityTerm(this.fluent.buildPodAffinityTerm(), this.fluent.getWeight());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
