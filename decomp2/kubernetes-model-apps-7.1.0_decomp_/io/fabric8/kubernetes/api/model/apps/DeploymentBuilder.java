package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeploymentBuilder extends DeploymentFluent implements VisitableBuilder {
   DeploymentFluent fluent;

   public DeploymentBuilder() {
      this(new Deployment());
   }

   public DeploymentBuilder(DeploymentFluent fluent) {
      this(fluent, new Deployment());
   }

   public DeploymentBuilder(DeploymentFluent fluent, Deployment instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeploymentBuilder(Deployment instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Deployment build() {
      Deployment buildable = new Deployment(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
