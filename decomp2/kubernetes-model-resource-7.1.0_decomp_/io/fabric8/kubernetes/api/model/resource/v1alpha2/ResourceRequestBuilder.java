package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceRequestBuilder extends ResourceRequestFluent implements VisitableBuilder {
   ResourceRequestFluent fluent;

   public ResourceRequestBuilder() {
      this(new ResourceRequest());
   }

   public ResourceRequestBuilder(ResourceRequestFluent fluent) {
      this(fluent, new ResourceRequest());
   }

   public ResourceRequestBuilder(ResourceRequestFluent fluent, ResourceRequest instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceRequestBuilder(ResourceRequest instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceRequest build() {
      ResourceRequest buildable = new ResourceRequest(this.fluent.buildNamedResources(), this.fluent.getVendorParameters());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
