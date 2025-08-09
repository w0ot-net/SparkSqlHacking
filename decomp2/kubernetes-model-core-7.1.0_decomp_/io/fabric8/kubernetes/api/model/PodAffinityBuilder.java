package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodAffinityBuilder extends PodAffinityFluent implements VisitableBuilder {
   PodAffinityFluent fluent;

   public PodAffinityBuilder() {
      this(new PodAffinity());
   }

   public PodAffinityBuilder(PodAffinityFluent fluent) {
      this(fluent, new PodAffinity());
   }

   public PodAffinityBuilder(PodAffinityFluent fluent, PodAffinity instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodAffinityBuilder(PodAffinity instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodAffinity build() {
      PodAffinity buildable = new PodAffinity(this.fluent.buildPreferredDuringSchedulingIgnoredDuringExecution(), this.fluent.buildRequiredDuringSchedulingIgnoredDuringExecution());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
