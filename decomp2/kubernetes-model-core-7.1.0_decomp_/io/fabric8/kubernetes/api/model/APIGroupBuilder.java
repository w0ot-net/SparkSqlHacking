package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class APIGroupBuilder extends APIGroupFluent implements VisitableBuilder {
   APIGroupFluent fluent;

   public APIGroupBuilder() {
      this(new APIGroup());
   }

   public APIGroupBuilder(APIGroupFluent fluent) {
      this(fluent, new APIGroup());
   }

   public APIGroupBuilder(APIGroupFluent fluent, APIGroup instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public APIGroupBuilder(APIGroup instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public APIGroup build() {
      APIGroup buildable = new APIGroup(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.getName(), this.fluent.buildPreferredVersion(), this.fluent.buildServerAddressByClientCIDRs(), this.fluent.buildVersions());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
