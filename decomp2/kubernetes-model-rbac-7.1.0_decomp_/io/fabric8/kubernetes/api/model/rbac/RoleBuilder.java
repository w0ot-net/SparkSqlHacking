package io.fabric8.kubernetes.api.model.rbac;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RoleBuilder extends RoleFluent implements VisitableBuilder {
   RoleFluent fluent;

   public RoleBuilder() {
      this(new Role());
   }

   public RoleBuilder(RoleFluent fluent) {
      this(fluent, new Role());
   }

   public RoleBuilder(RoleFluent fluent, Role instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RoleBuilder(Role instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Role build() {
      Role buildable = new Role(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildRules());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
