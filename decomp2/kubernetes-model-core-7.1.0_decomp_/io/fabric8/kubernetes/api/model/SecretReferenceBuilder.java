package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SecretReferenceBuilder extends SecretReferenceFluent implements VisitableBuilder {
   SecretReferenceFluent fluent;

   public SecretReferenceBuilder() {
      this(new SecretReference());
   }

   public SecretReferenceBuilder(SecretReferenceFluent fluent) {
      this(fluent, new SecretReference());
   }

   public SecretReferenceBuilder(SecretReferenceFluent fluent, SecretReference instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SecretReferenceBuilder(SecretReference instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SecretReference build() {
      SecretReference buildable = new SecretReference(this.fluent.getName(), this.fluent.getNamespace());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
