package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceClassBuilder extends ResourceClassFluent implements VisitableBuilder {
   ResourceClassFluent fluent;

   public ResourceClassBuilder() {
      this(new ResourceClass());
   }

   public ResourceClassBuilder(ResourceClassFluent fluent) {
      this(fluent, new ResourceClass());
   }

   public ResourceClassBuilder(ResourceClassFluent fluent, ResourceClass instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceClassBuilder(ResourceClass instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceClass build() {
      ResourceClass buildable = new ResourceClass(this.fluent.getApiVersion(), this.fluent.getDriverName(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildParametersRef(), this.fluent.getStructuredParameters(), this.fluent.getSuitableNodes());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
