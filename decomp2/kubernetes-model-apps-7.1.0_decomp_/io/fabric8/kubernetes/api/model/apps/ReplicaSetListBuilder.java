package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ReplicaSetListBuilder extends ReplicaSetListFluent implements VisitableBuilder {
   ReplicaSetListFluent fluent;

   public ReplicaSetListBuilder() {
      this(new ReplicaSetList());
   }

   public ReplicaSetListBuilder(ReplicaSetListFluent fluent) {
      this(fluent, new ReplicaSetList());
   }

   public ReplicaSetListBuilder(ReplicaSetListFluent fluent, ReplicaSetList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ReplicaSetListBuilder(ReplicaSetList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ReplicaSetList build() {
      ReplicaSetList buildable = new ReplicaSetList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
