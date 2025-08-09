package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class APIServiceListBuilder extends APIServiceListFluent implements VisitableBuilder {
   APIServiceListFluent fluent;

   public APIServiceListBuilder() {
      this(new APIServiceList());
   }

   public APIServiceListBuilder(APIServiceListFluent fluent) {
      this(fluent, new APIServiceList());
   }

   public APIServiceListBuilder(APIServiceListFluent fluent, APIServiceList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public APIServiceListBuilder(APIServiceList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public APIServiceList build() {
      APIServiceList buildable = new APIServiceList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.buildMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
