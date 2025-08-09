package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class OwnerReferenceBuilder extends OwnerReferenceFluent implements VisitableBuilder {
   OwnerReferenceFluent fluent;

   public OwnerReferenceBuilder() {
      this(new OwnerReference());
   }

   public OwnerReferenceBuilder(OwnerReferenceFluent fluent) {
      this(fluent, new OwnerReference());
   }

   public OwnerReferenceBuilder(OwnerReferenceFluent fluent, OwnerReference instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public OwnerReferenceBuilder(OwnerReference instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public OwnerReference build() {
      OwnerReference buildable = new OwnerReference(this.fluent.getApiVersion(), this.fluent.getBlockOwnerDeletion(), this.fluent.getController(), this.fluent.getKind(), this.fluent.getName(), this.fluent.getUid());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
