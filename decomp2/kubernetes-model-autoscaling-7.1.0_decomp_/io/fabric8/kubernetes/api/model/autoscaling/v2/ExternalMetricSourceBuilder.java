package io.fabric8.kubernetes.api.model.autoscaling.v2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ExternalMetricSourceBuilder extends ExternalMetricSourceFluent implements VisitableBuilder {
   ExternalMetricSourceFluent fluent;

   public ExternalMetricSourceBuilder() {
      this(new ExternalMetricSource());
   }

   public ExternalMetricSourceBuilder(ExternalMetricSourceFluent fluent) {
      this(fluent, new ExternalMetricSource());
   }

   public ExternalMetricSourceBuilder(ExternalMetricSourceFluent fluent, ExternalMetricSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ExternalMetricSourceBuilder(ExternalMetricSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ExternalMetricSource build() {
      ExternalMetricSource buildable = new ExternalMetricSource(this.fluent.buildMetric(), this.fluent.buildTarget());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
