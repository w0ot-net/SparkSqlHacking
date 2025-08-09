package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class APIServiceConditionBuilder extends APIServiceConditionFluent implements VisitableBuilder {
   APIServiceConditionFluent fluent;

   public APIServiceConditionBuilder() {
      this(new APIServiceCondition());
   }

   public APIServiceConditionBuilder(APIServiceConditionFluent fluent) {
      this(fluent, new APIServiceCondition());
   }

   public APIServiceConditionBuilder(APIServiceConditionFluent fluent, APIServiceCondition instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public APIServiceConditionBuilder(APIServiceCondition instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public APIServiceCondition build() {
      APIServiceCondition buildable = new APIServiceCondition(this.fluent.getLastTransitionTime(), this.fluent.getMessage(), this.fluent.getReason(), this.fluent.getStatus(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
