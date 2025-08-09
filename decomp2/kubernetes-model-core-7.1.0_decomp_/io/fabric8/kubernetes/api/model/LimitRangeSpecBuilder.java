package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LimitRangeSpecBuilder extends LimitRangeSpecFluent implements VisitableBuilder {
   LimitRangeSpecFluent fluent;

   public LimitRangeSpecBuilder() {
      this(new LimitRangeSpec());
   }

   public LimitRangeSpecBuilder(LimitRangeSpecFluent fluent) {
      this(fluent, new LimitRangeSpec());
   }

   public LimitRangeSpecBuilder(LimitRangeSpecFluent fluent, LimitRangeSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LimitRangeSpecBuilder(LimitRangeSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LimitRangeSpec build() {
      LimitRangeSpec buildable = new LimitRangeSpec(this.fluent.buildLimits());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
