package io.fabric8.kubernetes.api.model.autoscaling.v2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceMetricSourceBuilder extends ResourceMetricSourceFluent implements VisitableBuilder {
   ResourceMetricSourceFluent fluent;

   public ResourceMetricSourceBuilder() {
      this(new ResourceMetricSource());
   }

   public ResourceMetricSourceBuilder(ResourceMetricSourceFluent fluent) {
      this(fluent, new ResourceMetricSource());
   }

   public ResourceMetricSourceBuilder(ResourceMetricSourceFluent fluent, ResourceMetricSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceMetricSourceBuilder(ResourceMetricSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceMetricSource build() {
      ResourceMetricSource buildable = new ResourceMetricSource(this.fluent.getName(), this.fluent.buildTarget());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
