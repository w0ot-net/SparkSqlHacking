package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodBuilder extends PodFluent implements VisitableBuilder {
   PodFluent fluent;

   public PodBuilder() {
      this(new Pod());
   }

   public PodBuilder(PodFluent fluent) {
      this(fluent, new Pod());
   }

   public PodBuilder(PodFluent fluent, Pod instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodBuilder(Pod instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Pod build() {
      Pod buildable = new Pod(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
