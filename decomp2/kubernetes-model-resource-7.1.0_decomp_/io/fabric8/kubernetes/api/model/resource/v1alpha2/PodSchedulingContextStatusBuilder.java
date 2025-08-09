package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodSchedulingContextStatusBuilder extends PodSchedulingContextStatusFluent implements VisitableBuilder {
   PodSchedulingContextStatusFluent fluent;

   public PodSchedulingContextStatusBuilder() {
      this(new PodSchedulingContextStatus());
   }

   public PodSchedulingContextStatusBuilder(PodSchedulingContextStatusFluent fluent) {
      this(fluent, new PodSchedulingContextStatus());
   }

   public PodSchedulingContextStatusBuilder(PodSchedulingContextStatusFluent fluent, PodSchedulingContextStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodSchedulingContextStatusBuilder(PodSchedulingContextStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodSchedulingContextStatus build() {
      PodSchedulingContextStatus buildable = new PodSchedulingContextStatus(this.fluent.buildResourceClaims());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
