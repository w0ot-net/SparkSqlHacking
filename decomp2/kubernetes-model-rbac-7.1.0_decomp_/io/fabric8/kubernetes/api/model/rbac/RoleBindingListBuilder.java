package io.fabric8.kubernetes.api.model.rbac;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RoleBindingListBuilder extends RoleBindingListFluent implements VisitableBuilder {
   RoleBindingListFluent fluent;

   public RoleBindingListBuilder() {
      this(new RoleBindingList());
   }

   public RoleBindingListBuilder(RoleBindingListFluent fluent) {
      this(fluent, new RoleBindingList());
   }

   public RoleBindingListBuilder(RoleBindingListFluent fluent, RoleBindingList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RoleBindingListBuilder(RoleBindingList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RoleBindingList build() {
      RoleBindingList buildable = new RoleBindingList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
