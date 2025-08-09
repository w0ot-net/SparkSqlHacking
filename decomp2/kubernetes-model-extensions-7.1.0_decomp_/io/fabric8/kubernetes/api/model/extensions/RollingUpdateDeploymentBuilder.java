package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RollingUpdateDeploymentBuilder extends RollingUpdateDeploymentFluent implements VisitableBuilder {
   RollingUpdateDeploymentFluent fluent;

   public RollingUpdateDeploymentBuilder() {
      this(new RollingUpdateDeployment());
   }

   public RollingUpdateDeploymentBuilder(RollingUpdateDeploymentFluent fluent) {
      this(fluent, new RollingUpdateDeployment());
   }

   public RollingUpdateDeploymentBuilder(RollingUpdateDeploymentFluent fluent, RollingUpdateDeployment instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RollingUpdateDeploymentBuilder(RollingUpdateDeployment instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RollingUpdateDeployment build() {
      RollingUpdateDeployment buildable = new RollingUpdateDeployment(this.fluent.buildMaxSurge(), this.fluent.buildMaxUnavailable());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
