package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NamespacedPolicyTargetReferenceBuilder extends NamespacedPolicyTargetReferenceFluent implements VisitableBuilder {
   NamespacedPolicyTargetReferenceFluent fluent;

   public NamespacedPolicyTargetReferenceBuilder() {
      this(new NamespacedPolicyTargetReference());
   }

   public NamespacedPolicyTargetReferenceBuilder(NamespacedPolicyTargetReferenceFluent fluent) {
      this(fluent, new NamespacedPolicyTargetReference());
   }

   public NamespacedPolicyTargetReferenceBuilder(NamespacedPolicyTargetReferenceFluent fluent, NamespacedPolicyTargetReference instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NamespacedPolicyTargetReferenceBuilder(NamespacedPolicyTargetReference instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NamespacedPolicyTargetReference build() {
      NamespacedPolicyTargetReference buildable = new NamespacedPolicyTargetReference(this.fluent.getGroup(), this.fluent.getKind(), this.fluent.getName(), this.fluent.getNamespace());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
