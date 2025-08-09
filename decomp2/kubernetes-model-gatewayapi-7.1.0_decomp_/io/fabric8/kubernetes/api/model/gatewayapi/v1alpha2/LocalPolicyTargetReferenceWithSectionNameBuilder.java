package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LocalPolicyTargetReferenceWithSectionNameBuilder extends LocalPolicyTargetReferenceWithSectionNameFluent implements VisitableBuilder {
   LocalPolicyTargetReferenceWithSectionNameFluent fluent;

   public LocalPolicyTargetReferenceWithSectionNameBuilder() {
      this(new LocalPolicyTargetReferenceWithSectionName());
   }

   public LocalPolicyTargetReferenceWithSectionNameBuilder(LocalPolicyTargetReferenceWithSectionNameFluent fluent) {
      this(fluent, new LocalPolicyTargetReferenceWithSectionName());
   }

   public LocalPolicyTargetReferenceWithSectionNameBuilder(LocalPolicyTargetReferenceWithSectionNameFluent fluent, LocalPolicyTargetReferenceWithSectionName instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LocalPolicyTargetReferenceWithSectionNameBuilder(LocalPolicyTargetReferenceWithSectionName instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LocalPolicyTargetReferenceWithSectionName build() {
      LocalPolicyTargetReferenceWithSectionName buildable = new LocalPolicyTargetReferenceWithSectionName(this.fluent.getGroup(), this.fluent.getKind(), this.fluent.getName(), this.fluent.getSectionName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
