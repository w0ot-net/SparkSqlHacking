package io.fabric8.kubernetes.api.model.flowcontrol.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class FlowSchemaSpecBuilder extends FlowSchemaSpecFluent implements VisitableBuilder {
   FlowSchemaSpecFluent fluent;

   public FlowSchemaSpecBuilder() {
      this(new FlowSchemaSpec());
   }

   public FlowSchemaSpecBuilder(FlowSchemaSpecFluent fluent) {
      this(fluent, new FlowSchemaSpec());
   }

   public FlowSchemaSpecBuilder(FlowSchemaSpecFluent fluent, FlowSchemaSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public FlowSchemaSpecBuilder(FlowSchemaSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public FlowSchemaSpec build() {
      FlowSchemaSpec buildable = new FlowSchemaSpec(this.fluent.buildDistinguisherMethod(), this.fluent.getMatchingPrecedence(), this.fluent.buildPriorityLevelConfiguration(), this.fluent.buildRules());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
