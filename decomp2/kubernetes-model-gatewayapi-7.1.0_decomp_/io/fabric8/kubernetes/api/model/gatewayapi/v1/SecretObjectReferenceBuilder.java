package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SecretObjectReferenceBuilder extends SecretObjectReferenceFluent implements VisitableBuilder {
   SecretObjectReferenceFluent fluent;

   public SecretObjectReferenceBuilder() {
      this(new SecretObjectReference());
   }

   public SecretObjectReferenceBuilder(SecretObjectReferenceFluent fluent) {
      this(fluent, new SecretObjectReference());
   }

   public SecretObjectReferenceBuilder(SecretObjectReferenceFluent fluent, SecretObjectReference instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SecretObjectReferenceBuilder(SecretObjectReference instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SecretObjectReference build() {
      SecretObjectReference buildable = new SecretObjectReference(this.fluent.getGroup(), this.fluent.getKind(), this.fluent.getName(), this.fluent.getNamespace());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
