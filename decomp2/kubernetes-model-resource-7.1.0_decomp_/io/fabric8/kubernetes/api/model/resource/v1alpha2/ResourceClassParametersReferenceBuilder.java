package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceClassParametersReferenceBuilder extends ResourceClassParametersReferenceFluent implements VisitableBuilder {
   ResourceClassParametersReferenceFluent fluent;

   public ResourceClassParametersReferenceBuilder() {
      this(new ResourceClassParametersReference());
   }

   public ResourceClassParametersReferenceBuilder(ResourceClassParametersReferenceFluent fluent) {
      this(fluent, new ResourceClassParametersReference());
   }

   public ResourceClassParametersReferenceBuilder(ResourceClassParametersReferenceFluent fluent, ResourceClassParametersReference instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceClassParametersReferenceBuilder(ResourceClassParametersReference instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceClassParametersReference build() {
      ResourceClassParametersReference buildable = new ResourceClassParametersReference(this.fluent.getApiGroup(), this.fluent.getKind(), this.fluent.getName(), this.fluent.getNamespace());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
