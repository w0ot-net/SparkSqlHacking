package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceHealthBuilder extends ResourceHealthFluent implements VisitableBuilder {
   ResourceHealthFluent fluent;

   public ResourceHealthBuilder() {
      this(new ResourceHealth());
   }

   public ResourceHealthBuilder(ResourceHealthFluent fluent) {
      this(fluent, new ResourceHealth());
   }

   public ResourceHealthBuilder(ResourceHealthFluent fluent, ResourceHealth instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceHealthBuilder(ResourceHealth instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceHealth build() {
      ResourceHealth buildable = new ResourceHealth(this.fluent.getHealth(), this.fluent.getResourceID());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
