package io.fabric8.kubernetes.api.model.gatewayapi.v1;

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
      ObjectReference buildable = new ObjectReference(this.fluent.getGroup(), this.fluent.getKind(), this.fluent.getName(), this.fluent.getNamespace());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
