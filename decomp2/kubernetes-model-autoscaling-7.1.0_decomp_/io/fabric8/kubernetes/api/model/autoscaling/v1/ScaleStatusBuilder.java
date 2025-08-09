package io.fabric8.kubernetes.api.model.autoscaling.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ScaleStatusBuilder extends ScaleStatusFluent implements VisitableBuilder {
   ScaleStatusFluent fluent;

   public ScaleStatusBuilder() {
      this(new ScaleStatus());
   }

   public ScaleStatusBuilder(ScaleStatusFluent fluent) {
      this(fluent, new ScaleStatus());
   }

   public ScaleStatusBuilder(ScaleStatusFluent fluent, ScaleStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ScaleStatusBuilder(ScaleStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ScaleStatus build() {
      ScaleStatus buildable = new ScaleStatus(this.fluent.getReplicas(), this.fluent.getSelector());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
