package io.fabric8.kubernetes.api.model.authentication;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class BoundObjectReferenceBuilder extends BoundObjectReferenceFluent implements VisitableBuilder {
   BoundObjectReferenceFluent fluent;

   public BoundObjectReferenceBuilder() {
      this(new BoundObjectReference());
   }

   public BoundObjectReferenceBuilder(BoundObjectReferenceFluent fluent) {
      this(fluent, new BoundObjectReference());
   }

   public BoundObjectReferenceBuilder(BoundObjectReferenceFluent fluent, BoundObjectReference instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public BoundObjectReferenceBuilder(BoundObjectReference instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public BoundObjectReference build() {
      BoundObjectReference buildable = new BoundObjectReference(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.getName(), this.fluent.getUid());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
