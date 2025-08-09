package io.fabric8.kubernetes.api.model.rbac;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ClusterRoleListBuilder extends ClusterRoleListFluent implements VisitableBuilder {
   ClusterRoleListFluent fluent;

   public ClusterRoleListBuilder() {
      this(new ClusterRoleList());
   }

   public ClusterRoleListBuilder(ClusterRoleListFluent fluent) {
      this(fluent, new ClusterRoleList());
   }

   public ClusterRoleListBuilder(ClusterRoleListFluent fluent, ClusterRoleList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ClusterRoleListBuilder(ClusterRoleList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ClusterRoleList build() {
      ClusterRoleList buildable = new ClusterRoleList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
