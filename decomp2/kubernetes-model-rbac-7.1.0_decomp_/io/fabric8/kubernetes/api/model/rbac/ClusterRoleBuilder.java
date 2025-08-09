package io.fabric8.kubernetes.api.model.rbac;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ClusterRoleBuilder extends ClusterRoleFluent implements VisitableBuilder {
   ClusterRoleFluent fluent;

   public ClusterRoleBuilder() {
      this(new ClusterRole());
   }

   public ClusterRoleBuilder(ClusterRoleFluent fluent) {
      this(fluent, new ClusterRole());
   }

   public ClusterRoleBuilder(ClusterRoleFluent fluent, ClusterRole instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ClusterRoleBuilder(ClusterRole instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ClusterRole build() {
      ClusterRole buildable = new ClusterRole(this.fluent.buildAggregationRule(), this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildRules());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
