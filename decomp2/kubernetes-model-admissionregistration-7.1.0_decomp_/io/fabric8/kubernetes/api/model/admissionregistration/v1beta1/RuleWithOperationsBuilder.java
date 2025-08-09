package io.fabric8.kubernetes.api.model.admissionregistration.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RuleWithOperationsBuilder extends RuleWithOperationsFluent implements VisitableBuilder {
   RuleWithOperationsFluent fluent;

   public RuleWithOperationsBuilder() {
      this(new RuleWithOperations());
   }

   public RuleWithOperationsBuilder(RuleWithOperationsFluent fluent) {
      this(fluent, new RuleWithOperations());
   }

   public RuleWithOperationsBuilder(RuleWithOperationsFluent fluent, RuleWithOperations instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RuleWithOperationsBuilder(RuleWithOperations instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RuleWithOperations build() {
      RuleWithOperations buildable = new RuleWithOperations(this.fluent.getApiGroups(), this.fluent.getApiVersions(), this.fluent.getOperations(), this.fluent.getResources(), this.fluent.getScope());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
