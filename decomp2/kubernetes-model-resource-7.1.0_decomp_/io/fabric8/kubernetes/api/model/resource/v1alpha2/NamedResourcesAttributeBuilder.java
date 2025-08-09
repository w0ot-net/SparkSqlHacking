package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NamedResourcesAttributeBuilder extends NamedResourcesAttributeFluent implements VisitableBuilder {
   NamedResourcesAttributeFluent fluent;

   public NamedResourcesAttributeBuilder() {
      this(new NamedResourcesAttribute());
   }

   public NamedResourcesAttributeBuilder(NamedResourcesAttributeFluent fluent) {
      this(fluent, new NamedResourcesAttribute());
   }

   public NamedResourcesAttributeBuilder(NamedResourcesAttributeFluent fluent, NamedResourcesAttribute instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NamedResourcesAttributeBuilder(NamedResourcesAttribute instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NamedResourcesAttribute build() {
      NamedResourcesAttribute buildable = new NamedResourcesAttribute(this.fluent.getBool(), this.fluent.getInt(), this.fluent.buildIntSlice(), this.fluent.getName(), this.fluent.getQuantity(), this.fluent.getString(), this.fluent.buildStringSlice(), this.fluent.getVersion());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
