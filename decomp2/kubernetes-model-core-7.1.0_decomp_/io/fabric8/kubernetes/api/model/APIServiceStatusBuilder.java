package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class APIServiceStatusBuilder extends APIServiceStatusFluent implements VisitableBuilder {
   APIServiceStatusFluent fluent;

   public APIServiceStatusBuilder() {
      this(new APIServiceStatus());
   }

   public APIServiceStatusBuilder(APIServiceStatusFluent fluent) {
      this(fluent, new APIServiceStatus());
   }

   public APIServiceStatusBuilder(APIServiceStatusFluent fluent, APIServiceStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public APIServiceStatusBuilder(APIServiceStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public APIServiceStatus build() {
      APIServiceStatus buildable = new APIServiceStatus(this.fluent.buildConditions());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
