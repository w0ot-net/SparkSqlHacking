package io.fabric8.kubernetes.api.model.autoscaling.v2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodsMetricStatusBuilder extends PodsMetricStatusFluent implements VisitableBuilder {
   PodsMetricStatusFluent fluent;

   public PodsMetricStatusBuilder() {
      this(new PodsMetricStatus());
   }

   public PodsMetricStatusBuilder(PodsMetricStatusFluent fluent) {
      this(fluent, new PodsMetricStatus());
   }

   public PodsMetricStatusBuilder(PodsMetricStatusFluent fluent, PodsMetricStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodsMetricStatusBuilder(PodsMetricStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodsMetricStatus build() {
      PodsMetricStatus buildable = new PodsMetricStatus(this.fluent.buildCurrent(), this.fluent.buildMetric());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
