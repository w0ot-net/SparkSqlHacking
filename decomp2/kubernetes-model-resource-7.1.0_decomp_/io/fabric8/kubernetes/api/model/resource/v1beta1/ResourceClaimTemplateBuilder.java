package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceClaimTemplateBuilder extends ResourceClaimTemplateFluent implements VisitableBuilder {
   ResourceClaimTemplateFluent fluent;

   public ResourceClaimTemplateBuilder() {
      this(new ResourceClaimTemplate());
   }

   public ResourceClaimTemplateBuilder(ResourceClaimTemplateFluent fluent) {
      this(fluent, new ResourceClaimTemplate());
   }

   public ResourceClaimTemplateBuilder(ResourceClaimTemplateFluent fluent, ResourceClaimTemplate instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceClaimTemplateBuilder(ResourceClaimTemplate instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceClaimTemplate build() {
      ResourceClaimTemplate buildable = new ResourceClaimTemplate(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
