package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NamedResourcesInstanceBuilder extends NamedResourcesInstanceFluent implements VisitableBuilder {
   NamedResourcesInstanceFluent fluent;

   public NamedResourcesInstanceBuilder() {
      this(new NamedResourcesInstance());
   }

   public NamedResourcesInstanceBuilder(NamedResourcesInstanceFluent fluent) {
      this(fluent, new NamedResourcesInstance());
   }

   public NamedResourcesInstanceBuilder(NamedResourcesInstanceFluent fluent, NamedResourcesInstance instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NamedResourcesInstanceBuilder(NamedResourcesInstance instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NamedResourcesInstance build() {
      NamedResourcesInstance buildable = new NamedResourcesInstance(this.fluent.buildAttributes(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
