package io.fabric8.kubernetes.api.model.scheduling.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PriorityClassBuilder extends PriorityClassFluent implements VisitableBuilder {
   PriorityClassFluent fluent;

   public PriorityClassBuilder() {
      this(new PriorityClass());
   }

   public PriorityClassBuilder(PriorityClassFluent fluent) {
      this(fluent, new PriorityClass());
   }

   public PriorityClassBuilder(PriorityClassFluent fluent, PriorityClass instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PriorityClassBuilder(PriorityClass instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PriorityClass build() {
      PriorityClass buildable = new PriorityClass(this.fluent.getApiVersion(), this.fluent.getDescription(), this.fluent.getGlobalDefault(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.getPreemptionPolicy(), this.fluent.getValue());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
