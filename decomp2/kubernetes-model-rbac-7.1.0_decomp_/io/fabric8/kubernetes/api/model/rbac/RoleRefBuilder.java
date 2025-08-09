package io.fabric8.kubernetes.api.model.rbac;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RoleRefBuilder extends RoleRefFluent implements VisitableBuilder {
   RoleRefFluent fluent;

   public RoleRefBuilder() {
      this(new RoleRef());
   }

   public RoleRefBuilder(RoleRefFluent fluent) {
      this(fluent, new RoleRef());
   }

   public RoleRefBuilder(RoleRefFluent fluent, RoleRef instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RoleRefBuilder(RoleRef instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RoleRef build() {
      RoleRef buildable = new RoleRef(this.fluent.getApiGroup(), this.fluent.getKind(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
