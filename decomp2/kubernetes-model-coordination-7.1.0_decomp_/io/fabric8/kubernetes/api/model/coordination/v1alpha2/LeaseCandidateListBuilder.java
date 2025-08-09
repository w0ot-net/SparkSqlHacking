package io.fabric8.kubernetes.api.model.coordination.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LeaseCandidateListBuilder extends LeaseCandidateListFluent implements VisitableBuilder {
   LeaseCandidateListFluent fluent;

   public LeaseCandidateListBuilder() {
      this(new LeaseCandidateList());
   }

   public LeaseCandidateListBuilder(LeaseCandidateListFluent fluent) {
      this(fluent, new LeaseCandidateList());
   }

   public LeaseCandidateListBuilder(LeaseCandidateListFluent fluent, LeaseCandidateList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LeaseCandidateListBuilder(LeaseCandidateList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LeaseCandidateList build() {
      LeaseCandidateList buildable = new LeaseCandidateList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
