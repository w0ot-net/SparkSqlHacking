package io.fabric8.kubernetes.api.model.autoscaling.v2beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ContainerResourceMetricStatusBuilder extends ContainerResourceMetricStatusFluent implements VisitableBuilder {
   ContainerResourceMetricStatusFluent fluent;

   public ContainerResourceMetricStatusBuilder() {
      this(new ContainerResourceMetricStatus());
   }

   public ContainerResourceMetricStatusBuilder(ContainerResourceMetricStatusFluent fluent) {
      this(fluent, new ContainerResourceMetricStatus());
   }

   public ContainerResourceMetricStatusBuilder(ContainerResourceMetricStatusFluent fluent, ContainerResourceMetricStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ContainerResourceMetricStatusBuilder(ContainerResourceMetricStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ContainerResourceMetricStatus build() {
      ContainerResourceMetricStatus buildable = new ContainerResourceMetricStatus(this.fluent.getContainer(), this.fluent.getCurrentAverageUtilization(), this.fluent.getCurrentAverageValue(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
