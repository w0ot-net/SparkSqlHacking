package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ParametersReferenceBuilder extends ParametersReferenceFluent implements VisitableBuilder {
   ParametersReferenceFluent fluent;

   public ParametersReferenceBuilder() {
      this(new ParametersReference());
   }

   public ParametersReferenceBuilder(ParametersReferenceFluent fluent) {
      this(fluent, new ParametersReference());
   }

   public ParametersReferenceBuilder(ParametersReferenceFluent fluent, ParametersReference instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ParametersReferenceBuilder(ParametersReference instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ParametersReference build() {
      ParametersReference buildable = new ParametersReference(this.fluent.getGroup(), this.fluent.getKind(), this.fluent.getName(), this.fluent.getNamespace());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
