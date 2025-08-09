package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodTemplateBuilder extends PodTemplateFluent implements VisitableBuilder {
   PodTemplateFluent fluent;

   public PodTemplateBuilder() {
      this(new PodTemplate());
   }

   public PodTemplateBuilder(PodTemplateFluent fluent) {
      this(fluent, new PodTemplate());
   }

   public PodTemplateBuilder(PodTemplateFluent fluent, PodTemplate instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodTemplateBuilder(PodTemplate instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodTemplate build() {
      PodTemplate buildable = new PodTemplate(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildTemplate());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
