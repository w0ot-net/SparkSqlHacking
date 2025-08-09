package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LocalParametersReferenceBuilder extends LocalParametersReferenceFluent implements VisitableBuilder {
   LocalParametersReferenceFluent fluent;

   public LocalParametersReferenceBuilder() {
      this(new LocalParametersReference());
   }

   public LocalParametersReferenceBuilder(LocalParametersReferenceFluent fluent) {
      this(fluent, new LocalParametersReference());
   }

   public LocalParametersReferenceBuilder(LocalParametersReferenceFluent fluent, LocalParametersReference instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LocalParametersReferenceBuilder(LocalParametersReference instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LocalParametersReference build() {
      LocalParametersReference buildable = new LocalParametersReference(this.fluent.getGroup(), this.fluent.getKind(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
