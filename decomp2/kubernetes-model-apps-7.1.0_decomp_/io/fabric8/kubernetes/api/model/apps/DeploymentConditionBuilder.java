package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeploymentConditionBuilder extends DeploymentConditionFluent implements VisitableBuilder {
   DeploymentConditionFluent fluent;

   public DeploymentConditionBuilder() {
      this(new DeploymentCondition());
   }

   public DeploymentConditionBuilder(DeploymentConditionFluent fluent) {
      this(fluent, new DeploymentCondition());
   }

   public DeploymentConditionBuilder(DeploymentConditionFluent fluent, DeploymentCondition instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeploymentConditionBuilder(DeploymentCondition instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DeploymentCondition build() {
      DeploymentCondition buildable = new DeploymentCondition(this.fluent.getLastTransitionTime(), this.fluent.getLastUpdateTime(), this.fluent.getMessage(), this.fluent.getReason(), this.fluent.getStatus(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
