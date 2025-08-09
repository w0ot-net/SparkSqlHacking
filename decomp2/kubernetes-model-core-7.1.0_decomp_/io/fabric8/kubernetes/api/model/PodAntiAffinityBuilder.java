package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodAntiAffinityBuilder extends PodAntiAffinityFluent implements VisitableBuilder {
   PodAntiAffinityFluent fluent;

   public PodAntiAffinityBuilder() {
      this(new PodAntiAffinity());
   }

   public PodAntiAffinityBuilder(PodAntiAffinityFluent fluent) {
      this(fluent, new PodAntiAffinity());
   }

   public PodAntiAffinityBuilder(PodAntiAffinityFluent fluent, PodAntiAffinity instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodAntiAffinityBuilder(PodAntiAffinity instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodAntiAffinity build() {
      PodAntiAffinity buildable = new PodAntiAffinity(this.fluent.buildPreferredDuringSchedulingIgnoredDuringExecution(), this.fluent.buildRequiredDuringSchedulingIgnoredDuringExecution());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
