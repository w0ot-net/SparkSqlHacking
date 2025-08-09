package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ReplicationControllerSpecBuilder extends ReplicationControllerSpecFluent implements VisitableBuilder {
   ReplicationControllerSpecFluent fluent;

   public ReplicationControllerSpecBuilder() {
      this(new ReplicationControllerSpec());
   }

   public ReplicationControllerSpecBuilder(ReplicationControllerSpecFluent fluent) {
      this(fluent, new ReplicationControllerSpec());
   }

   public ReplicationControllerSpecBuilder(ReplicationControllerSpecFluent fluent, ReplicationControllerSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ReplicationControllerSpecBuilder(ReplicationControllerSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ReplicationControllerSpec build() {
      ReplicationControllerSpec buildable = new ReplicationControllerSpec(this.fluent.getMinReadySeconds(), this.fluent.getReplicas(), this.fluent.getSelector(), this.fluent.buildTemplate());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
