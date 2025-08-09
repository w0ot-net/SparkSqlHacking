package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceClaimTemplateSpecBuilder extends ResourceClaimTemplateSpecFluent implements VisitableBuilder {
   ResourceClaimTemplateSpecFluent fluent;

   public ResourceClaimTemplateSpecBuilder() {
      this(new ResourceClaimTemplateSpec());
   }

   public ResourceClaimTemplateSpecBuilder(ResourceClaimTemplateSpecFluent fluent) {
      this(fluent, new ResourceClaimTemplateSpec());
   }

   public ResourceClaimTemplateSpecBuilder(ResourceClaimTemplateSpecFluent fluent, ResourceClaimTemplateSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceClaimTemplateSpecBuilder(ResourceClaimTemplateSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceClaimTemplateSpec build() {
      ResourceClaimTemplateSpec buildable = new ResourceClaimTemplateSpec(this.fluent.buildMetadata(), this.fluent.buildSpec());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
