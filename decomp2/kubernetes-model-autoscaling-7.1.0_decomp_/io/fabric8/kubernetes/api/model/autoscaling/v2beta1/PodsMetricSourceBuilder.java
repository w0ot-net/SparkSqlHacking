package io.fabric8.kubernetes.api.model.autoscaling.v2beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodsMetricSourceBuilder extends PodsMetricSourceFluent implements VisitableBuilder {
   PodsMetricSourceFluent fluent;

   public PodsMetricSourceBuilder() {
      this(new PodsMetricSource());
   }

   public PodsMetricSourceBuilder(PodsMetricSourceFluent fluent) {
      this(fluent, new PodsMetricSource());
   }

   public PodsMetricSourceBuilder(PodsMetricSourceFluent fluent, PodsMetricSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodsMetricSourceBuilder(PodsMetricSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodsMetricSource build() {
      PodsMetricSource buildable = new PodsMetricSource(this.fluent.getMetricName(), this.fluent.buildSelector(), this.fluent.getTargetAverageValue());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
