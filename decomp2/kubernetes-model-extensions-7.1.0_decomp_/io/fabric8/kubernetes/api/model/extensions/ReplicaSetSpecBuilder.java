package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ReplicaSetSpecBuilder extends ReplicaSetSpecFluent implements VisitableBuilder {
   ReplicaSetSpecFluent fluent;

   public ReplicaSetSpecBuilder() {
      this(new ReplicaSetSpec());
   }

   public ReplicaSetSpecBuilder(ReplicaSetSpecFluent fluent) {
      this(fluent, new ReplicaSetSpec());
   }

   public ReplicaSetSpecBuilder(ReplicaSetSpecFluent fluent, ReplicaSetSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ReplicaSetSpecBuilder(ReplicaSetSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ReplicaSetSpec build() {
      ReplicaSetSpec buildable = new ReplicaSetSpec(this.fluent.getMinReadySeconds(), this.fluent.getReplicas(), this.fluent.buildSelector(), this.fluent.buildTemplate());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
