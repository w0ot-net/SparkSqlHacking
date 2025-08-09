package io.fabric8.kubernetes.api.model.coordination.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LeaseListBuilder extends LeaseListFluent implements VisitableBuilder {
   LeaseListFluent fluent;

   public LeaseListBuilder() {
      this(new LeaseList());
   }

   public LeaseListBuilder(LeaseListFluent fluent) {
      this(fluent, new LeaseList());
   }

   public LeaseListBuilder(LeaseListFluent fluent, LeaseList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LeaseListBuilder(LeaseList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LeaseList build() {
      LeaseList buildable = new LeaseList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
