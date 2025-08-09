package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodAffinityTermBuilder extends PodAffinityTermFluent implements VisitableBuilder {
   PodAffinityTermFluent fluent;

   public PodAffinityTermBuilder() {
      this(new PodAffinityTerm());
   }

   public PodAffinityTermBuilder(PodAffinityTermFluent fluent) {
      this(fluent, new PodAffinityTerm());
   }

   public PodAffinityTermBuilder(PodAffinityTermFluent fluent, PodAffinityTerm instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodAffinityTermBuilder(PodAffinityTerm instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodAffinityTerm build() {
      PodAffinityTerm buildable = new PodAffinityTerm(this.fluent.buildLabelSelector(), this.fluent.getMatchLabelKeys(), this.fluent.getMismatchLabelKeys(), this.fluent.buildNamespaceSelector(), this.fluent.getNamespaces(), this.fluent.getTopologyKey());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
