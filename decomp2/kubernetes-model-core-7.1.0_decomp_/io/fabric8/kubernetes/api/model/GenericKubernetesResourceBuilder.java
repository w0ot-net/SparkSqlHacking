package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GenericKubernetesResourceBuilder extends GenericKubernetesResourceFluent implements VisitableBuilder {
   GenericKubernetesResourceFluent fluent;

   public GenericKubernetesResourceBuilder() {
      this(new GenericKubernetesResource());
   }

   public GenericKubernetesResourceBuilder(GenericKubernetesResourceFluent fluent) {
      this(fluent, new GenericKubernetesResource());
   }

   public GenericKubernetesResourceBuilder(GenericKubernetesResourceFluent fluent, GenericKubernetesResource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GenericKubernetesResourceBuilder(GenericKubernetesResource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GenericKubernetesResource build() {
      GenericKubernetesResource buildable = new GenericKubernetesResource();
      buildable.setApiVersion(this.fluent.getApiVersion());
      buildable.setKind(this.fluent.getKind());
      buildable.setMetadata(this.fluent.buildMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
