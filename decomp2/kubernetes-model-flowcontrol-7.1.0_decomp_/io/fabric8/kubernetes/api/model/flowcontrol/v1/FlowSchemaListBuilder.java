package io.fabric8.kubernetes.api.model.flowcontrol.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class FlowSchemaListBuilder extends FlowSchemaListFluent implements VisitableBuilder {
   FlowSchemaListFluent fluent;

   public FlowSchemaListBuilder() {
      this(new FlowSchemaList());
   }

   public FlowSchemaListBuilder(FlowSchemaListFluent fluent) {
      this(fluent, new FlowSchemaList());
   }

   public FlowSchemaListBuilder(FlowSchemaListFluent fluent, FlowSchemaList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public FlowSchemaListBuilder(FlowSchemaList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public FlowSchemaList build() {
      FlowSchemaList buildable = new FlowSchemaList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
