package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LimitRangeItemBuilder extends LimitRangeItemFluent implements VisitableBuilder {
   LimitRangeItemFluent fluent;

   public LimitRangeItemBuilder() {
      this(new LimitRangeItem());
   }

   public LimitRangeItemBuilder(LimitRangeItemFluent fluent) {
      this(fluent, new LimitRangeItem());
   }

   public LimitRangeItemBuilder(LimitRangeItemFluent fluent, LimitRangeItem instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LimitRangeItemBuilder(LimitRangeItem instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LimitRangeItem build() {
      LimitRangeItem buildable = new LimitRangeItem(this.fluent.getDefault(), this.fluent.getDefaultRequest(), this.fluent.getMax(), this.fluent.getMaxLimitRequestRatio(), this.fluent.getMin(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
