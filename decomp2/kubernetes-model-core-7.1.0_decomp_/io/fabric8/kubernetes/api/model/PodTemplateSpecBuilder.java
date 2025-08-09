package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodTemplateSpecBuilder extends PodTemplateSpecFluent implements VisitableBuilder {
   PodTemplateSpecFluent fluent;

   public PodTemplateSpecBuilder() {
      this(new PodTemplateSpec());
   }

   public PodTemplateSpecBuilder(PodTemplateSpecFluent fluent) {
      this(fluent, new PodTemplateSpec());
   }

   public PodTemplateSpecBuilder(PodTemplateSpecFluent fluent, PodTemplateSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodTemplateSpecBuilder(PodTemplateSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodTemplateSpec build() {
      PodTemplateSpec buildable = new PodTemplateSpec(this.fluent.buildMetadata(), this.fluent.buildSpec());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
