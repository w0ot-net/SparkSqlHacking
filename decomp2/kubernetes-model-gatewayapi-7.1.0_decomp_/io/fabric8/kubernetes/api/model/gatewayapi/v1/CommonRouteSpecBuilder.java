package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CommonRouteSpecBuilder extends CommonRouteSpecFluent implements VisitableBuilder {
   CommonRouteSpecFluent fluent;

   public CommonRouteSpecBuilder() {
      this(new CommonRouteSpec());
   }

   public CommonRouteSpecBuilder(CommonRouteSpecFluent fluent) {
      this(fluent, new CommonRouteSpec());
   }

   public CommonRouteSpecBuilder(CommonRouteSpecFluent fluent, CommonRouteSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CommonRouteSpecBuilder(CommonRouteSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CommonRouteSpec build() {
      CommonRouteSpec buildable = new CommonRouteSpec(this.fluent.buildParentRefs());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
