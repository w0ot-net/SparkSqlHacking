package io.fabric8.kubernetes.api.model.rbac;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RoleListBuilder extends RoleListFluent implements VisitableBuilder {
   RoleListFluent fluent;

   public RoleListBuilder() {
      this(new RoleList());
   }

   public RoleListBuilder(RoleListFluent fluent) {
      this(fluent, new RoleList());
   }

   public RoleListBuilder(RoleListFluent fluent, RoleList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RoleListBuilder(RoleList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RoleList build() {
      RoleList buildable = new RoleList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
