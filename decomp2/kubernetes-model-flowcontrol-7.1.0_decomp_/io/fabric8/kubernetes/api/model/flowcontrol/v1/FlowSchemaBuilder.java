package io.fabric8.kubernetes.api.model.flowcontrol.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class FlowSchemaBuilder extends FlowSchemaFluent implements VisitableBuilder {
   FlowSchemaFluent fluent;

   public FlowSchemaBuilder() {
      this(new FlowSchema());
   }

   public FlowSchemaBuilder(FlowSchemaFluent fluent) {
      this(fluent, new FlowSchema());
   }

   public FlowSchemaBuilder(FlowSchemaFluent fluent, FlowSchema instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public FlowSchemaBuilder(FlowSchema instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public FlowSchema build() {
      FlowSchema buildable = new FlowSchema(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
