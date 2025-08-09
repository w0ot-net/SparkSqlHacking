package io.fabric8.kubernetes.api.model.flowcontrol.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class FlowSchemaStatusBuilder extends FlowSchemaStatusFluent implements VisitableBuilder {
   FlowSchemaStatusFluent fluent;

   public FlowSchemaStatusBuilder() {
      this(new FlowSchemaStatus());
   }

   public FlowSchemaStatusBuilder(FlowSchemaStatusFluent fluent) {
      this(fluent, new FlowSchemaStatus());
   }

   public FlowSchemaStatusBuilder(FlowSchemaStatusFluent fluent, FlowSchemaStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public FlowSchemaStatusBuilder(FlowSchemaStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public FlowSchemaStatus build() {
      FlowSchemaStatus buildable = new FlowSchemaStatus(this.fluent.buildConditions());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
