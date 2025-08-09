package io.fabric8.kubernetes.api.model.autoscaling.v2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MetricStatusBuilder extends MetricStatusFluent implements VisitableBuilder {
   MetricStatusFluent fluent;

   public MetricStatusBuilder() {
      this(new MetricStatus());
   }

   public MetricStatusBuilder(MetricStatusFluent fluent) {
      this(fluent, new MetricStatus());
   }

   public MetricStatusBuilder(MetricStatusFluent fluent, MetricStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MetricStatusBuilder(MetricStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MetricStatus build() {
      MetricStatus buildable = new MetricStatus(this.fluent.buildContainerResource(), this.fluent.buildExternal(), this.fluent.buildObject(), this.fluent.buildPods(), this.fluent.buildResource(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
