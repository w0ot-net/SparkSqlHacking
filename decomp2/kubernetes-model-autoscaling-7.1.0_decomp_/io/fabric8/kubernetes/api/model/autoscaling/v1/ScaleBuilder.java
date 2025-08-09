package io.fabric8.kubernetes.api.model.autoscaling.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ScaleBuilder extends ScaleFluent implements VisitableBuilder {
   ScaleFluent fluent;

   public ScaleBuilder() {
      this(new Scale());
   }

   public ScaleBuilder(ScaleFluent fluent) {
      this(fluent, new Scale());
   }

   public ScaleBuilder(ScaleFluent fluent, Scale instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ScaleBuilder(Scale instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Scale build() {
      Scale buildable = new Scale(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
