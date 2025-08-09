package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeploymentStrategyBuilder extends DeploymentStrategyFluent implements VisitableBuilder {
   DeploymentStrategyFluent fluent;

   public DeploymentStrategyBuilder() {
      this(new DeploymentStrategy());
   }

   public DeploymentStrategyBuilder(DeploymentStrategyFluent fluent) {
      this(fluent, new DeploymentStrategy());
   }

   public DeploymentStrategyBuilder(DeploymentStrategyFluent fluent, DeploymentStrategy instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeploymentStrategyBuilder(DeploymentStrategy instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DeploymentStrategy build() {
      DeploymentStrategy buildable = new DeploymentStrategy(this.fluent.buildRollingUpdate(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
