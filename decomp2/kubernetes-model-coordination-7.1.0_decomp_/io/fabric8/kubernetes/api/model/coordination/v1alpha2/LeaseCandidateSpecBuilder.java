package io.fabric8.kubernetes.api.model.coordination.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LeaseCandidateSpecBuilder extends LeaseCandidateSpecFluent implements VisitableBuilder {
   LeaseCandidateSpecFluent fluent;

   public LeaseCandidateSpecBuilder() {
      this(new LeaseCandidateSpec());
   }

   public LeaseCandidateSpecBuilder(LeaseCandidateSpecFluent fluent) {
      this(fluent, new LeaseCandidateSpec());
   }

   public LeaseCandidateSpecBuilder(LeaseCandidateSpecFluent fluent, LeaseCandidateSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LeaseCandidateSpecBuilder(LeaseCandidateSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LeaseCandidateSpec build() {
      LeaseCandidateSpec buildable = new LeaseCandidateSpec(this.fluent.getBinaryVersion(), this.fluent.getEmulationVersion(), this.fluent.getLeaseName(), this.fluent.getPingTime(), this.fluent.getRenewTime(), this.fluent.getStrategy());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
