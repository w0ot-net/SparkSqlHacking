package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ControllerRevisionListBuilder extends ControllerRevisionListFluent implements VisitableBuilder {
   ControllerRevisionListFluent fluent;

   public ControllerRevisionListBuilder() {
      this(new ControllerRevisionList());
   }

   public ControllerRevisionListBuilder(ControllerRevisionListFluent fluent) {
      this(fluent, new ControllerRevisionList());
   }

   public ControllerRevisionListBuilder(ControllerRevisionListFluent fluent, ControllerRevisionList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ControllerRevisionListBuilder(ControllerRevisionList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ControllerRevisionList build() {
      ControllerRevisionList buildable = new ControllerRevisionList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
