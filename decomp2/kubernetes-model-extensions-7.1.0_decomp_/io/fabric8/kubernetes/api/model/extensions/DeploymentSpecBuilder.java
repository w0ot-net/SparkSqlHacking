package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeploymentSpecBuilder extends DeploymentSpecFluent implements VisitableBuilder {
   DeploymentSpecFluent fluent;

   public DeploymentSpecBuilder() {
      this(new DeploymentSpec());
   }

   public DeploymentSpecBuilder(DeploymentSpecFluent fluent) {
      this(fluent, new DeploymentSpec());
   }

   public DeploymentSpecBuilder(DeploymentSpecFluent fluent, DeploymentSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeploymentSpecBuilder(DeploymentSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DeploymentSpec build() {
      DeploymentSpec buildable = new DeploymentSpec(this.fluent.getMinReadySeconds(), this.fluent.getPaused(), this.fluent.getProgressDeadlineSeconds(), this.fluent.getReplicas(), this.fluent.getRevisionHistoryLimit(), this.fluent.buildRollbackTo(), this.fluent.buildSelector(), this.fluent.buildStrategy(), this.fluent.buildTemplate());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
