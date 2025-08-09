package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeploymentRollbackBuilder extends DeploymentRollbackFluent implements VisitableBuilder {
   DeploymentRollbackFluent fluent;

   public DeploymentRollbackBuilder() {
      this(new DeploymentRollback());
   }

   public DeploymentRollbackBuilder(DeploymentRollbackFluent fluent) {
      this(fluent, new DeploymentRollback());
   }

   public DeploymentRollbackBuilder(DeploymentRollbackFluent fluent, DeploymentRollback instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeploymentRollbackBuilder(DeploymentRollback instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DeploymentRollback build() {
      DeploymentRollback buildable = new DeploymentRollback(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.getName(), this.fluent.buildRollbackTo(), this.fluent.getUpdatedAnnotations());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
