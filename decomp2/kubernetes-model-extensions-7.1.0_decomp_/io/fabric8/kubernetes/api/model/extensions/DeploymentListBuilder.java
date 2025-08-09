package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeploymentListBuilder extends DeploymentListFluent implements VisitableBuilder {
   DeploymentListFluent fluent;

   public DeploymentListBuilder() {
      this(new DeploymentList());
   }

   public DeploymentListBuilder(DeploymentListFluent fluent) {
      this(fluent, new DeploymentList());
   }

   public DeploymentListBuilder(DeploymentListFluent fluent, DeploymentList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeploymentListBuilder(DeploymentList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DeploymentList build() {
      DeploymentList buildable = new DeploymentList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
