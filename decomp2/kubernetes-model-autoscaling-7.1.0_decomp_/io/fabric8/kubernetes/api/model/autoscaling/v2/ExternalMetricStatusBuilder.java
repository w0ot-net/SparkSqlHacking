package io.fabric8.kubernetes.api.model.autoscaling.v2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ExternalMetricStatusBuilder extends ExternalMetricStatusFluent implements VisitableBuilder {
   ExternalMetricStatusFluent fluent;

   public ExternalMetricStatusBuilder() {
      this(new ExternalMetricStatus());
   }

   public ExternalMetricStatusBuilder(ExternalMetricStatusFluent fluent) {
      this(fluent, new ExternalMetricStatus());
   }

   public ExternalMetricStatusBuilder(ExternalMetricStatusFluent fluent, ExternalMetricStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ExternalMetricStatusBuilder(ExternalMetricStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ExternalMetricStatus build() {
      ExternalMetricStatus buildable = new ExternalMetricStatus(this.fluent.buildCurrent(), this.fluent.buildMetric());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
