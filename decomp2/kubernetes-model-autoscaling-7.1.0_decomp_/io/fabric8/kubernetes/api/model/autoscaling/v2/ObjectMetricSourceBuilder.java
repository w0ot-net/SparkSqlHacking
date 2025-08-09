package io.fabric8.kubernetes.api.model.autoscaling.v2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ObjectMetricSourceBuilder extends ObjectMetricSourceFluent implements VisitableBuilder {
   ObjectMetricSourceFluent fluent;

   public ObjectMetricSourceBuilder() {
      this(new ObjectMetricSource());
   }

   public ObjectMetricSourceBuilder(ObjectMetricSourceFluent fluent) {
      this(fluent, new ObjectMetricSource());
   }

   public ObjectMetricSourceBuilder(ObjectMetricSourceFluent fluent, ObjectMetricSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ObjectMetricSourceBuilder(ObjectMetricSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ObjectMetricSource build() {
      ObjectMetricSource buildable = new ObjectMetricSource(this.fluent.buildDescribedObject(), this.fluent.buildMetric(), this.fluent.buildTarget());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
