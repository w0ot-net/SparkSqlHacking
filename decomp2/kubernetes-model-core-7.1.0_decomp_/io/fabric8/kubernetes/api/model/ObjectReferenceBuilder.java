package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ObjectReferenceBuilder extends ObjectReferenceFluent implements VisitableBuilder {
   ObjectReferenceFluent fluent;

   public ObjectReferenceBuilder() {
      this(new ObjectReference());
   }

   public ObjectReferenceBuilder(ObjectReferenceFluent fluent) {
      this(fluent, new ObjectReference());
   }

   public ObjectReferenceBuilder(ObjectReferenceFluent fluent, ObjectReference instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ObjectReferenceBuilder(ObjectReference instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ObjectReference build() {
      ObjectReference buildable = new ObjectReference(this.fluent.getApiVersion(), this.fluent.getFieldPath(), this.fluent.getKind(), this.fluent.getName(), this.fluent.getNamespace(), this.fluent.getResourceVersion(), this.fluent.getUid());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
