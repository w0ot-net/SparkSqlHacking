package io.fabric8.kubernetes.api.model.networking.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class IngressClassParametersReferenceBuilder extends IngressClassParametersReferenceFluent implements VisitableBuilder {
   IngressClassParametersReferenceFluent fluent;

   public IngressClassParametersReferenceBuilder() {
      this(new IngressClassParametersReference());
   }

   public IngressClassParametersReferenceBuilder(IngressClassParametersReferenceFluent fluent) {
      this(fluent, new IngressClassParametersReference());
   }

   public IngressClassParametersReferenceBuilder(IngressClassParametersReferenceFluent fluent, IngressClassParametersReference instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public IngressClassParametersReferenceBuilder(IngressClassParametersReference instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public IngressClassParametersReference build() {
      IngressClassParametersReference buildable = new IngressClassParametersReference(this.fluent.getApiGroup(), this.fluent.getKind(), this.fluent.getName(), this.fluent.getNamespace(), this.fluent.getScope());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
