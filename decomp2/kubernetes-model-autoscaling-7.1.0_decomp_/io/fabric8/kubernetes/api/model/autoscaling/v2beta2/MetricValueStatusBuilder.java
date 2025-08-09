package io.fabric8.kubernetes.api.model.autoscaling.v2beta2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MetricValueStatusBuilder extends MetricValueStatusFluent implements VisitableBuilder {
   MetricValueStatusFluent fluent;

   public MetricValueStatusBuilder() {
      this(new MetricValueStatus());
   }

   public MetricValueStatusBuilder(MetricValueStatusFluent fluent) {
      this(fluent, new MetricValueStatus());
   }

   public MetricValueStatusBuilder(MetricValueStatusFluent fluent, MetricValueStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MetricValueStatusBuilder(MetricValueStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MetricValueStatus build() {
      MetricValueStatus buildable = new MetricValueStatus(this.fluent.getAverageUtilization(), this.fluent.getAverageValue(), this.fluent.getValue());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
