package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TypedObjectReferenceBuilder extends TypedObjectReferenceFluent implements VisitableBuilder {
   TypedObjectReferenceFluent fluent;

   public TypedObjectReferenceBuilder() {
      this(new TypedObjectReference());
   }

   public TypedObjectReferenceBuilder(TypedObjectReferenceFluent fluent) {
      this(fluent, new TypedObjectReference());
   }

   public TypedObjectReferenceBuilder(TypedObjectReferenceFluent fluent, TypedObjectReference instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TypedObjectReferenceBuilder(TypedObjectReference instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TypedObjectReference build() {
      TypedObjectReference buildable = new TypedObjectReference(this.fluent.getApiGroup(), this.fluent.getKind(), this.fluent.getName(), this.fluent.getNamespace());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
