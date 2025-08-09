package io.fabric8.kubernetes.api.model.admissionregistration.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NamedRuleWithOperationsBuilder extends NamedRuleWithOperationsFluent implements VisitableBuilder {
   NamedRuleWithOperationsFluent fluent;

   public NamedRuleWithOperationsBuilder() {
      this(new NamedRuleWithOperations());
   }

   public NamedRuleWithOperationsBuilder(NamedRuleWithOperationsFluent fluent) {
      this(fluent, new NamedRuleWithOperations());
   }

   public NamedRuleWithOperationsBuilder(NamedRuleWithOperationsFluent fluent, NamedRuleWithOperations instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NamedRuleWithOperationsBuilder(NamedRuleWithOperations instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NamedRuleWithOperations build() {
      NamedRuleWithOperations buildable = new NamedRuleWithOperations(this.fluent.getApiGroups(), this.fluent.getApiVersions(), this.fluent.getOperations(), this.fluent.getResourceNames(), this.fluent.getResources(), this.fluent.getScope());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
