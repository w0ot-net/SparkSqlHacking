package io.fabric8.kubernetes.api.model.scheduling.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PriorityClassListBuilder extends PriorityClassListFluent implements VisitableBuilder {
   PriorityClassListFluent fluent;

   public PriorityClassListBuilder() {
      this(new PriorityClassList());
   }

   public PriorityClassListBuilder(PriorityClassListFluent fluent) {
      this(fluent, new PriorityClassList());
   }

   public PriorityClassListBuilder(PriorityClassListFluent fluent, PriorityClassList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PriorityClassListBuilder(PriorityClassList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PriorityClassList build() {
      PriorityClassList buildable = new PriorityClassList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
