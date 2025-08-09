package io.fabric8.kubernetes.api.model.autoscaling.v2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MetricSpecBuilder extends MetricSpecFluent implements VisitableBuilder {
   MetricSpecFluent fluent;

   public MetricSpecBuilder() {
      this(new MetricSpec());
   }

   public MetricSpecBuilder(MetricSpecFluent fluent) {
      this(fluent, new MetricSpec());
   }

   public MetricSpecBuilder(MetricSpecFluent fluent, MetricSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MetricSpecBuilder(MetricSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MetricSpec build() {
      MetricSpec buildable = new MetricSpec(this.fluent.buildContainerResource(), this.fluent.buildExternal(), this.fluent.buildObject(), this.fluent.buildPods(), this.fluent.buildResource(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
