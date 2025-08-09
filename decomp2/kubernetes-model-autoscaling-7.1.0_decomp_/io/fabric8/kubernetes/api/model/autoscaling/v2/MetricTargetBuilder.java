package io.fabric8.kubernetes.api.model.autoscaling.v2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MetricTargetBuilder extends MetricTargetFluent implements VisitableBuilder {
   MetricTargetFluent fluent;

   public MetricTargetBuilder() {
      this(new MetricTarget());
   }

   public MetricTargetBuilder(MetricTargetFluent fluent) {
      this(fluent, new MetricTarget());
   }

   public MetricTargetBuilder(MetricTargetFluent fluent, MetricTarget instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MetricTargetBuilder(MetricTarget instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MetricTarget build() {
      MetricTarget buildable = new MetricTarget(this.fluent.getAverageUtilization(), this.fluent.getAverageValue(), this.fluent.getType(), this.fluent.getValue());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
