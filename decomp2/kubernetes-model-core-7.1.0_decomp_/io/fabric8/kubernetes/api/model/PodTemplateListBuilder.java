package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodTemplateListBuilder extends PodTemplateListFluent implements VisitableBuilder {
   PodTemplateListFluent fluent;

   public PodTemplateListBuilder() {
      this(new PodTemplateList());
   }

   public PodTemplateListBuilder(PodTemplateListFluent fluent) {
      this(fluent, new PodTemplateList());
   }

   public PodTemplateListBuilder(PodTemplateListFluent fluent, PodTemplateList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodTemplateListBuilder(PodTemplateList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodTemplateList build() {
      PodTemplateList buildable = new PodTemplateList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.buildMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
