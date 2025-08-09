package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class APIGroupListBuilder extends APIGroupListFluent implements VisitableBuilder {
   APIGroupListFluent fluent;

   public APIGroupListBuilder() {
      this(new APIGroupList());
   }

   public APIGroupListBuilder(APIGroupListFluent fluent) {
      this(fluent, new APIGroupList());
   }

   public APIGroupListBuilder(APIGroupListFluent fluent, APIGroupList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public APIGroupListBuilder(APIGroupList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public APIGroupList build() {
      APIGroupList buildable = new APIGroupList(this.fluent.getApiVersion(), this.fluent.buildGroups(), this.fluent.getKind());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
