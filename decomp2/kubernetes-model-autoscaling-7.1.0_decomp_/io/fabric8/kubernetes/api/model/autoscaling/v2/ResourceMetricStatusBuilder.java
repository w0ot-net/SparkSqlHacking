package io.fabric8.kubernetes.api.model.autoscaling.v2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceMetricStatusBuilder extends ResourceMetricStatusFluent implements VisitableBuilder {
   ResourceMetricStatusFluent fluent;

   public ResourceMetricStatusBuilder() {
      this(new ResourceMetricStatus());
   }

   public ResourceMetricStatusBuilder(ResourceMetricStatusFluent fluent) {
      this(fluent, new ResourceMetricStatus());
   }

   public ResourceMetricStatusBuilder(ResourceMetricStatusFluent fluent, ResourceMetricStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceMetricStatusBuilder(ResourceMetricStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceMetricStatus build() {
      ResourceMetricStatus buildable = new ResourceMetricStatus(this.fluent.buildCurrent(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
