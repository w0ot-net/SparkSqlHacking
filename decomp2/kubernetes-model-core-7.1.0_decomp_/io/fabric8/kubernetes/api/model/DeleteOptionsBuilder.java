package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeleteOptionsBuilder extends DeleteOptionsFluent implements VisitableBuilder {
   DeleteOptionsFluent fluent;

   public DeleteOptionsBuilder() {
      this(new DeleteOptions());
   }

   public DeleteOptionsBuilder(DeleteOptionsFluent fluent) {
      this(fluent, new DeleteOptions());
   }

   public DeleteOptionsBuilder(DeleteOptionsFluent fluent, DeleteOptions instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeleteOptionsBuilder(DeleteOptions instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DeleteOptions build() {
      DeleteOptions buildable = new DeleteOptions(this.fluent.getApiVersion(), this.fluent.getDryRun(), this.fluent.getGracePeriodSeconds(), this.fluent.getIgnoreStoreReadErrorWithClusterBreakingPotential(), this.fluent.getKind(), this.fluent.getOrphanDependents(), this.fluent.buildPreconditions(), this.fluent.getPropagationPolicy());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
