package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TypedLocalObjectReferenceBuilder extends TypedLocalObjectReferenceFluent implements VisitableBuilder {
   TypedLocalObjectReferenceFluent fluent;

   public TypedLocalObjectReferenceBuilder() {
      this(new TypedLocalObjectReference());
   }

   public TypedLocalObjectReferenceBuilder(TypedLocalObjectReferenceFluent fluent) {
      this(fluent, new TypedLocalObjectReference());
   }

   public TypedLocalObjectReferenceBuilder(TypedLocalObjectReferenceFluent fluent, TypedLocalObjectReference instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TypedLocalObjectReferenceBuilder(TypedLocalObjectReference instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TypedLocalObjectReference build() {
      TypedLocalObjectReference buildable = new TypedLocalObjectReference(this.fluent.getApiGroup(), this.fluent.getKind(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
