package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceFieldSelectorBuilder extends ResourceFieldSelectorFluent implements VisitableBuilder {
   ResourceFieldSelectorFluent fluent;

   public ResourceFieldSelectorBuilder() {
      this(new ResourceFieldSelector());
   }

   public ResourceFieldSelectorBuilder(ResourceFieldSelectorFluent fluent) {
      this(fluent, new ResourceFieldSelector());
   }

   public ResourceFieldSelectorBuilder(ResourceFieldSelectorFluent fluent, ResourceFieldSelector instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceFieldSelectorBuilder(ResourceFieldSelector instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceFieldSelector build() {
      ResourceFieldSelector buildable = new ResourceFieldSelector(this.fluent.getContainerName(), this.fluent.buildDivisor(), this.fluent.getResource());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
