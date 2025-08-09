package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LimitRangeBuilder extends LimitRangeFluent implements VisitableBuilder {
   LimitRangeFluent fluent;

   public LimitRangeBuilder() {
      this(new LimitRange());
   }

   public LimitRangeBuilder(LimitRangeFluent fluent) {
      this(fluent, new LimitRange());
   }

   public LimitRangeBuilder(LimitRangeFluent fluent, LimitRange instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LimitRangeBuilder(LimitRange instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LimitRange build() {
      LimitRange buildable = new LimitRange(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
