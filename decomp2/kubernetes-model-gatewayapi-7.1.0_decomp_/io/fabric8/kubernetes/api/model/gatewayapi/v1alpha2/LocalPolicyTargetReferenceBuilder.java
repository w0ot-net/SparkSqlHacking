package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LocalPolicyTargetReferenceBuilder extends LocalPolicyTargetReferenceFluent implements VisitableBuilder {
   LocalPolicyTargetReferenceFluent fluent;

   public LocalPolicyTargetReferenceBuilder() {
      this(new LocalPolicyTargetReference());
   }

   public LocalPolicyTargetReferenceBuilder(LocalPolicyTargetReferenceFluent fluent) {
      this(fluent, new LocalPolicyTargetReference());
   }

   public LocalPolicyTargetReferenceBuilder(LocalPolicyTargetReferenceFluent fluent, LocalPolicyTargetReference instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LocalPolicyTargetReferenceBuilder(LocalPolicyTargetReference instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LocalPolicyTargetReference build() {
      LocalPolicyTargetReference buildable = new LocalPolicyTargetReference(this.fluent.getGroup(), this.fluent.getKind(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
