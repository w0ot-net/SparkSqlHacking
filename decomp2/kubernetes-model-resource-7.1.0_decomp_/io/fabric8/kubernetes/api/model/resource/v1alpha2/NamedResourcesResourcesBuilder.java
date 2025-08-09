package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NamedResourcesResourcesBuilder extends NamedResourcesResourcesFluent implements VisitableBuilder {
   NamedResourcesResourcesFluent fluent;

   public NamedResourcesResourcesBuilder() {
      this(new NamedResourcesResources());
   }

   public NamedResourcesResourcesBuilder(NamedResourcesResourcesFluent fluent) {
      this(fluent, new NamedResourcesResources());
   }

   public NamedResourcesResourcesBuilder(NamedResourcesResourcesFluent fluent, NamedResourcesResources instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NamedResourcesResourcesBuilder(NamedResourcesResources instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NamedResourcesResources build() {
      NamedResourcesResources buildable = new NamedResourcesResources(this.fluent.buildInstances());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
