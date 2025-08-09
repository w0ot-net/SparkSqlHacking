package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ReplicationControllerBuilder extends ReplicationControllerFluent implements VisitableBuilder {
   ReplicationControllerFluent fluent;

   public ReplicationControllerBuilder() {
      this(new ReplicationController());
   }

   public ReplicationControllerBuilder(ReplicationControllerFluent fluent) {
      this(fluent, new ReplicationController());
   }

   public ReplicationControllerBuilder(ReplicationControllerFluent fluent, ReplicationController instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ReplicationControllerBuilder(ReplicationController instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ReplicationController build() {
      ReplicationController buildable = new ReplicationController(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
