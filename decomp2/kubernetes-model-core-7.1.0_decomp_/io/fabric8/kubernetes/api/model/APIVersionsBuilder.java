package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class APIVersionsBuilder extends APIVersionsFluent implements VisitableBuilder {
   APIVersionsFluent fluent;

   public APIVersionsBuilder() {
      this(new APIVersions());
   }

   public APIVersionsBuilder(APIVersionsFluent fluent) {
      this(fluent, new APIVersions());
   }

   public APIVersionsBuilder(APIVersionsFluent fluent, APIVersions instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public APIVersionsBuilder(APIVersions instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public APIVersions build() {
      APIVersions buildable = new APIVersions(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildServerAddressByClientCIDRs(), this.fluent.getVersions());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
