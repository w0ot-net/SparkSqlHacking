package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class AffinityBuilder extends AffinityFluent implements VisitableBuilder {
   AffinityFluent fluent;

   public AffinityBuilder() {
      this(new Affinity());
   }

   public AffinityBuilder(AffinityFluent fluent) {
      this(fluent, new Affinity());
   }

   public AffinityBuilder(AffinityFluent fluent, Affinity instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public AffinityBuilder(Affinity instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Affinity build() {
      Affinity buildable = new Affinity(this.fluent.buildNodeAffinity(), this.fluent.buildPodAffinity(), this.fluent.buildPodAntiAffinity());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
