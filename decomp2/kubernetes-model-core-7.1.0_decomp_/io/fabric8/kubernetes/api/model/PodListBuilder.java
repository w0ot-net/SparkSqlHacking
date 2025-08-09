package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodListBuilder extends PodListFluent implements VisitableBuilder {
   PodListFluent fluent;

   public PodListBuilder() {
      this(new PodList());
   }

   public PodListBuilder(PodListFluent fluent) {
      this(fluent, new PodList());
   }

   public PodListBuilder(PodListFluent fluent, PodList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodListBuilder(PodList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodList build() {
      PodList buildable = new PodList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.buildMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
