package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodSchedulingContextSpecBuilder extends PodSchedulingContextSpecFluent implements VisitableBuilder {
   PodSchedulingContextSpecFluent fluent;

   public PodSchedulingContextSpecBuilder() {
      this(new PodSchedulingContextSpec());
   }

   public PodSchedulingContextSpecBuilder(PodSchedulingContextSpecFluent fluent) {
      this(fluent, new PodSchedulingContextSpec());
   }

   public PodSchedulingContextSpecBuilder(PodSchedulingContextSpecFluent fluent, PodSchedulingContextSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodSchedulingContextSpecBuilder(PodSchedulingContextSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodSchedulingContextSpec build() {
      PodSchedulingContextSpec buildable = new PodSchedulingContextSpec(this.fluent.getPotentialNodes(), this.fluent.getSelectedNode());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
