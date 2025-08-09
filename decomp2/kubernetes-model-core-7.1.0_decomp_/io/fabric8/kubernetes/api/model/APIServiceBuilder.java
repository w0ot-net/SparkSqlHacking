package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class APIServiceBuilder extends APIServiceFluent implements VisitableBuilder {
   APIServiceFluent fluent;

   public APIServiceBuilder() {
      this(new APIService());
   }

   public APIServiceBuilder(APIServiceFluent fluent) {
      this(fluent, new APIService());
   }

   public APIServiceBuilder(APIServiceFluent fluent, APIService instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public APIServiceBuilder(APIService instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public APIService build() {
      APIService buildable = new APIService(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
