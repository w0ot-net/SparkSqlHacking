package io.fabric8.kubernetes.api.model.rbac;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RoleBindingBuilder extends RoleBindingFluent implements VisitableBuilder {
   RoleBindingFluent fluent;

   public RoleBindingBuilder() {
      this(new RoleBinding());
   }

   public RoleBindingBuilder(RoleBindingFluent fluent) {
      this(fluent, new RoleBinding());
   }

   public RoleBindingBuilder(RoleBindingFluent fluent, RoleBinding instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RoleBindingBuilder(RoleBinding instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RoleBinding build() {
      RoleBinding buildable = new RoleBinding(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildRoleRef(), this.fluent.buildSubjects());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
