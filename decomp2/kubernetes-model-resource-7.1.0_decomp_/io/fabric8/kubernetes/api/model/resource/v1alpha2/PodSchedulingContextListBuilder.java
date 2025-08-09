package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodSchedulingContextListBuilder extends PodSchedulingContextListFluent implements VisitableBuilder {
   PodSchedulingContextListFluent fluent;

   public PodSchedulingContextListBuilder() {
      this(new PodSchedulingContextList());
   }

   public PodSchedulingContextListBuilder(PodSchedulingContextListFluent fluent) {
      this(fluent, new PodSchedulingContextList());
   }

   public PodSchedulingContextListBuilder(PodSchedulingContextListFluent fluent, PodSchedulingContextList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodSchedulingContextListBuilder(PodSchedulingContextList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodSchedulingContextList build() {
      PodSchedulingContextList buildable = new PodSchedulingContextList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
