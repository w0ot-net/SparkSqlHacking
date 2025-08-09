package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PersistentVolumeClaimTemplateBuilder extends PersistentVolumeClaimTemplateFluent implements VisitableBuilder {
   PersistentVolumeClaimTemplateFluent fluent;

   public PersistentVolumeClaimTemplateBuilder() {
      this(new PersistentVolumeClaimTemplate());
   }

   public PersistentVolumeClaimTemplateBuilder(PersistentVolumeClaimTemplateFluent fluent) {
      this(fluent, new PersistentVolumeClaimTemplate());
   }

   public PersistentVolumeClaimTemplateBuilder(PersistentVolumeClaimTemplateFluent fluent, PersistentVolumeClaimTemplate instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PersistentVolumeClaimTemplateBuilder(PersistentVolumeClaimTemplate instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PersistentVolumeClaimTemplate build() {
      PersistentVolumeClaimTemplate buildable = new PersistentVolumeClaimTemplate(this.fluent.buildMetadata(), this.fluent.buildSpec());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
