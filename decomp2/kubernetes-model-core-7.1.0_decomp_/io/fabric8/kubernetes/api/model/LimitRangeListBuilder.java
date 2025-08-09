package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LimitRangeListBuilder extends LimitRangeListFluent implements VisitableBuilder {
   LimitRangeListFluent fluent;

   public LimitRangeListBuilder() {
      this(new LimitRangeList());
   }

   public LimitRangeListBuilder(LimitRangeListFluent fluent) {
      this(fluent, new LimitRangeList());
   }

   public LimitRangeListBuilder(LimitRangeListFluent fluent, LimitRangeList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LimitRangeListBuilder(LimitRangeList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LimitRangeList build() {
      LimitRangeList buildable = new LimitRangeList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.buildMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
