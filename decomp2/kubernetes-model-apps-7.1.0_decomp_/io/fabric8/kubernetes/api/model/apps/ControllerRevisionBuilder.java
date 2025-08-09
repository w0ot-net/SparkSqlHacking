package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ControllerRevisionBuilder extends ControllerRevisionFluent implements VisitableBuilder {
   ControllerRevisionFluent fluent;

   public ControllerRevisionBuilder() {
      this(new ControllerRevision());
   }

   public ControllerRevisionBuilder(ControllerRevisionFluent fluent) {
      this(fluent, new ControllerRevision());
   }

   public ControllerRevisionBuilder(ControllerRevisionFluent fluent, ControllerRevision instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ControllerRevisionBuilder(ControllerRevision instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ControllerRevision build() {
      ControllerRevision buildable = new ControllerRevision(this.fluent.getApiVersion(), this.fluent.getData(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.getRevision());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
