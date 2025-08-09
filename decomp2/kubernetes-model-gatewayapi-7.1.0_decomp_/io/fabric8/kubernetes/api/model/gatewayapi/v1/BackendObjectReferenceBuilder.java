package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class BackendObjectReferenceBuilder extends BackendObjectReferenceFluent implements VisitableBuilder {
   BackendObjectReferenceFluent fluent;

   public BackendObjectReferenceBuilder() {
      this(new BackendObjectReference());
   }

   public BackendObjectReferenceBuilder(BackendObjectReferenceFluent fluent) {
      this(fluent, new BackendObjectReference());
   }

   public BackendObjectReferenceBuilder(BackendObjectReferenceFluent fluent, BackendObjectReference instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public BackendObjectReferenceBuilder(BackendObjectReference instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public BackendObjectReference build() {
      BackendObjectReference buildable = new BackendObjectReference(this.fluent.getGroup(), this.fluent.getKind(), this.fluent.getName(), this.fluent.getNamespace(), this.fluent.getPort());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
