package io.fabric8.kubernetes.api.model.flowcontrol.v1beta2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class FlowSchemaConditionBuilder extends FlowSchemaConditionFluent implements VisitableBuilder {
   FlowSchemaConditionFluent fluent;

   public FlowSchemaConditionBuilder() {
      this(new FlowSchemaCondition());
   }

   public FlowSchemaConditionBuilder(FlowSchemaConditionFluent fluent) {
      this(fluent, new FlowSchemaCondition());
   }

   public FlowSchemaConditionBuilder(FlowSchemaConditionFluent fluent, FlowSchemaCondition instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public FlowSchemaConditionBuilder(FlowSchemaCondition instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public FlowSchemaCondition build() {
      FlowSchemaCondition buildable = new FlowSchemaCondition(this.fluent.getLastTransitionTime(), this.fluent.getMessage(), this.fluent.getReason(), this.fluent.getStatus(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
