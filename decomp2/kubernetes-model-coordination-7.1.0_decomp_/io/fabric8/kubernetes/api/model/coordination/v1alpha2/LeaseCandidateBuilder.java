package io.fabric8.kubernetes.api.model.coordination.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LeaseCandidateBuilder extends LeaseCandidateFluent implements VisitableBuilder {
   LeaseCandidateFluent fluent;

   public LeaseCandidateBuilder() {
      this(new LeaseCandidate());
   }

   public LeaseCandidateBuilder(LeaseCandidateFluent fluent) {
      this(fluent, new LeaseCandidate());
   }

   public LeaseCandidateBuilder(LeaseCandidateFluent fluent, LeaseCandidate instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LeaseCandidateBuilder(LeaseCandidate instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LeaseCandidate build() {
      LeaseCandidate buildable = new LeaseCandidate(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
