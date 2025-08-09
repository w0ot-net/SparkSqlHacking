package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LocalObjectReferenceBuilder extends LocalObjectReferenceFluent implements VisitableBuilder {
   LocalObjectReferenceFluent fluent;

   public LocalObjectReferenceBuilder() {
      this(new LocalObjectReference());
   }

   public LocalObjectReferenceBuilder(LocalObjectReferenceFluent fluent) {
      this(fluent, new LocalObjectReference());
   }

   public LocalObjectReferenceBuilder(LocalObjectReferenceFluent fluent, LocalObjectReference instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LocalObjectReferenceBuilder(LocalObjectReference instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LocalObjectReference build() {
      LocalObjectReference buildable = new LocalObjectReference(this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
