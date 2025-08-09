package io.fabric8.kubernetes.api.model.autoscaling.v2beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ObjectMetricStatusBuilder extends ObjectMetricStatusFluent implements VisitableBuilder {
   ObjectMetricStatusFluent fluent;

   public ObjectMetricStatusBuilder() {
      this(new ObjectMetricStatus());
   }

   public ObjectMetricStatusBuilder(ObjectMetricStatusFluent fluent) {
      this(fluent, new ObjectMetricStatus());
   }

   public ObjectMetricStatusBuilder(ObjectMetricStatusFluent fluent, ObjectMetricStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ObjectMetricStatusBuilder(ObjectMetricStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ObjectMetricStatus build() {
      ObjectMetricStatus buildable = new ObjectMetricStatus(this.fluent.getAverageValue(), this.fluent.getCurrentValue(), this.fluent.getMetricName(), this.fluent.buildSelector(), this.fluent.buildTarget());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
