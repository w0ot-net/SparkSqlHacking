package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceStatusBuilder extends ResourceStatusFluent implements VisitableBuilder {
   ResourceStatusFluent fluent;

   public ResourceStatusBuilder() {
      this(new ResourceStatus());
   }

   public ResourceStatusBuilder(ResourceStatusFluent fluent) {
      this(fluent, new ResourceStatus());
   }

   public ResourceStatusBuilder(ResourceStatusFluent fluent, ResourceStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceStatusBuilder(ResourceStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceStatus build() {
      ResourceStatus buildable = new ResourceStatus(this.fluent.getName(), this.fluent.buildResources());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
