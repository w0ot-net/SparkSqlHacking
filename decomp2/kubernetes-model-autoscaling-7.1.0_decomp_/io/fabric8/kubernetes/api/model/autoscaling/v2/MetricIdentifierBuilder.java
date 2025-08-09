package io.fabric8.kubernetes.api.model.autoscaling.v2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MetricIdentifierBuilder extends MetricIdentifierFluent implements VisitableBuilder {
   MetricIdentifierFluent fluent;

   public MetricIdentifierBuilder() {
      this(new MetricIdentifier());
   }

   public MetricIdentifierBuilder(MetricIdentifierFluent fluent) {
      this(fluent, new MetricIdentifier());
   }

   public MetricIdentifierBuilder(MetricIdentifierFluent fluent, MetricIdentifier instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MetricIdentifierBuilder(MetricIdentifier instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MetricIdentifier build() {
      MetricIdentifier buildable = new MetricIdentifier(this.fluent.getName(), this.fluent.buildSelector());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
