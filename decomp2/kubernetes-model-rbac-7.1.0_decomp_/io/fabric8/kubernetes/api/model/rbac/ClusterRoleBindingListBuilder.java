package io.fabric8.kubernetes.api.model.rbac;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ClusterRoleBindingListBuilder extends ClusterRoleBindingListFluent implements VisitableBuilder {
   ClusterRoleBindingListFluent fluent;

   public ClusterRoleBindingListBuilder() {
      this(new ClusterRoleBindingList());
   }

   public ClusterRoleBindingListBuilder(ClusterRoleBindingListFluent fluent) {
      this(fluent, new ClusterRoleBindingList());
   }

   public ClusterRoleBindingListBuilder(ClusterRoleBindingListFluent fluent, ClusterRoleBindingList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ClusterRoleBindingListBuilder(ClusterRoleBindingList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ClusterRoleBindingList build() {
      ClusterRoleBindingList buildable = new ClusterRoleBindingList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
