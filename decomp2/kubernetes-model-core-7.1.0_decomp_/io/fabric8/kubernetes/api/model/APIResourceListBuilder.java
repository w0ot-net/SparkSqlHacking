package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class APIResourceListBuilder extends APIResourceListFluent implements VisitableBuilder {
   APIResourceListFluent fluent;

   public APIResourceListBuilder() {
      this(new APIResourceList());
   }

   public APIResourceListBuilder(APIResourceListFluent fluent) {
      this(fluent, new APIResourceList());
   }

   public APIResourceListBuilder(APIResourceListFluent fluent, APIResourceList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public APIResourceListBuilder(APIResourceList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public APIResourceList build() {
      APIResourceList buildable = new APIResourceList(this.fluent.getApiVersion(), this.fluent.getGroupVersion(), this.fluent.getKind(), this.fluent.buildResources());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
