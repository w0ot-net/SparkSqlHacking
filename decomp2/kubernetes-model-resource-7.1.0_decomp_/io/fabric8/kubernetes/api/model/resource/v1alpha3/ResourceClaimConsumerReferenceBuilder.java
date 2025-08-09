package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceClaimConsumerReferenceBuilder extends ResourceClaimConsumerReferenceFluent implements VisitableBuilder {
   ResourceClaimConsumerReferenceFluent fluent;

   public ResourceClaimConsumerReferenceBuilder() {
      this(new ResourceClaimConsumerReference());
   }

   public ResourceClaimConsumerReferenceBuilder(ResourceClaimConsumerReferenceFluent fluent) {
      this(fluent, new ResourceClaimConsumerReference());
   }

   public ResourceClaimConsumerReferenceBuilder(ResourceClaimConsumerReferenceFluent fluent, ResourceClaimConsumerReference instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceClaimConsumerReferenceBuilder(ResourceClaimConsumerReference instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceClaimConsumerReference build() {
      ResourceClaimConsumerReference buildable = new ResourceClaimConsumerReference(this.fluent.getApiGroup(), this.fluent.getName(), this.fluent.getResource(), this.fluent.getUid());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
