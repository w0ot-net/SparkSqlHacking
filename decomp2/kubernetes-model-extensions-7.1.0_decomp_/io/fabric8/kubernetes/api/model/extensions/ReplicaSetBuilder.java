package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ReplicaSetBuilder extends ReplicaSetFluent implements VisitableBuilder {
   ReplicaSetFluent fluent;

   public ReplicaSetBuilder() {
      this(new ReplicaSet());
   }

   public ReplicaSetBuilder(ReplicaSetFluent fluent) {
      this(fluent, new ReplicaSet());
   }

   public ReplicaSetBuilder(ReplicaSetFluent fluent, ReplicaSet instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ReplicaSetBuilder(ReplicaSet instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ReplicaSet build() {
      ReplicaSet buildable = new ReplicaSet(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
