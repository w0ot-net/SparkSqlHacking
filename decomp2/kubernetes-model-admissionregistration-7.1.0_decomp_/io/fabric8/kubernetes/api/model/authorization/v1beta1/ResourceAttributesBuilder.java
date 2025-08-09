package io.fabric8.kubernetes.api.model.authorization.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceAttributesBuilder extends ResourceAttributesFluent implements VisitableBuilder {
   ResourceAttributesFluent fluent;

   public ResourceAttributesBuilder() {
      this(new ResourceAttributes());
   }

   public ResourceAttributesBuilder(ResourceAttributesFluent fluent) {
      this(fluent, new ResourceAttributes());
   }

   public ResourceAttributesBuilder(ResourceAttributesFluent fluent, ResourceAttributes instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceAttributesBuilder(ResourceAttributes instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceAttributes build() {
      ResourceAttributes buildable = new ResourceAttributes(this.fluent.getGroup(), this.fluent.getName(), this.fluent.getNamespace(), this.fluent.getResource(), this.fluent.getSubresource(), this.fluent.getVerb(), this.fluent.getVersion());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
