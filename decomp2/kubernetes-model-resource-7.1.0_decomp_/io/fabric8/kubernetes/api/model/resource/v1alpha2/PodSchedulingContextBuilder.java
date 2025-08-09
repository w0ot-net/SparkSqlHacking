package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodSchedulingContextBuilder extends PodSchedulingContextFluent implements VisitableBuilder {
   PodSchedulingContextFluent fluent;

   public PodSchedulingContextBuilder() {
      this(new PodSchedulingContext());
   }

   public PodSchedulingContextBuilder(PodSchedulingContextFluent fluent) {
      this(fluent, new PodSchedulingContext());
   }

   public PodSchedulingContextBuilder(PodSchedulingContextFluent fluent, PodSchedulingContext instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodSchedulingContextBuilder(PodSchedulingContext instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodSchedulingContext build() {
      PodSchedulingContext buildable = new PodSchedulingContext(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
