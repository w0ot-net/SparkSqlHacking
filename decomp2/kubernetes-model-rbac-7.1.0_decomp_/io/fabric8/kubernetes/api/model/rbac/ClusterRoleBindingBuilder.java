package io.fabric8.kubernetes.api.model.rbac;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ClusterRoleBindingBuilder extends ClusterRoleBindingFluent implements VisitableBuilder {
   ClusterRoleBindingFluent fluent;

   public ClusterRoleBindingBuilder() {
      this(new ClusterRoleBinding());
   }

   public ClusterRoleBindingBuilder(ClusterRoleBindingFluent fluent) {
      this(fluent, new ClusterRoleBinding());
   }

   public ClusterRoleBindingBuilder(ClusterRoleBindingFluent fluent, ClusterRoleBinding instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ClusterRoleBindingBuilder(ClusterRoleBinding instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ClusterRoleBinding build() {
      ClusterRoleBinding buildable = new ClusterRoleBinding(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildRoleRef(), this.fluent.buildSubjects());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
