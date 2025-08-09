package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NamedResourcesRequestBuilder extends NamedResourcesRequestFluent implements VisitableBuilder {
   NamedResourcesRequestFluent fluent;

   public NamedResourcesRequestBuilder() {
      this(new NamedResourcesRequest());
   }

   public NamedResourcesRequestBuilder(NamedResourcesRequestFluent fluent) {
      this(fluent, new NamedResourcesRequest());
   }

   public NamedResourcesRequestBuilder(NamedResourcesRequestFluent fluent, NamedResourcesRequest instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NamedResourcesRequestBuilder(NamedResourcesRequest instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NamedResourcesRequest build() {
      NamedResourcesRequest buildable = new NamedResourcesRequest(this.fluent.getSelector());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
