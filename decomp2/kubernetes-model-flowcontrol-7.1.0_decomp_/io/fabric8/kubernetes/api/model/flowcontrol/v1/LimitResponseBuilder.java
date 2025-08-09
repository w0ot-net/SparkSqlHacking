package io.fabric8.kubernetes.api.model.flowcontrol.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LimitResponseBuilder extends LimitResponseFluent implements VisitableBuilder {
   LimitResponseFluent fluent;

   public LimitResponseBuilder() {
      this(new LimitResponse());
   }

   public LimitResponseBuilder(LimitResponseFluent fluent) {
      this(fluent, new LimitResponse());
   }

   public LimitResponseBuilder(LimitResponseFluent fluent, LimitResponse instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LimitResponseBuilder(LimitResponse instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LimitResponse build() {
      LimitResponse buildable = new LimitResponse(this.fluent.buildQueuing(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
