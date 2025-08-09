package io.fabric8.kubernetes.api.model.autoscaling.v2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ContainerResourceMetricSourceBuilder extends ContainerResourceMetricSourceFluent implements VisitableBuilder {
   ContainerResourceMetricSourceFluent fluent;

   public ContainerResourceMetricSourceBuilder() {
      this(new ContainerResourceMetricSource());
   }

   public ContainerResourceMetricSourceBuilder(ContainerResourceMetricSourceFluent fluent) {
      this(fluent, new ContainerResourceMetricSource());
   }

   public ContainerResourceMetricSourceBuilder(ContainerResourceMetricSourceFluent fluent, ContainerResourceMetricSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ContainerResourceMetricSourceBuilder(ContainerResourceMetricSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ContainerResourceMetricSource build() {
      ContainerResourceMetricSource buildable = new ContainerResourceMetricSource(this.fluent.getContainer(), this.fluent.getName(), this.fluent.buildTarget());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
