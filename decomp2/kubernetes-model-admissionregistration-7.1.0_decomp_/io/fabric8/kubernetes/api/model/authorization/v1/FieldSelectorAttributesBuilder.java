package io.fabric8.kubernetes.api.model.authorization.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class FieldSelectorAttributesBuilder extends FieldSelectorAttributesFluent implements VisitableBuilder {
   FieldSelectorAttributesFluent fluent;

   public FieldSelectorAttributesBuilder() {
      this(new FieldSelectorAttributes());
   }

   public FieldSelectorAttributesBuilder(FieldSelectorAttributesFluent fluent) {
      this(fluent, new FieldSelectorAttributes());
   }

   public FieldSelectorAttributesBuilder(FieldSelectorAttributesFluent fluent, FieldSelectorAttributes instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public FieldSelectorAttributesBuilder(FieldSelectorAttributes instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public FieldSelectorAttributes build() {
      FieldSelectorAttributes buildable = new FieldSelectorAttributes(this.fluent.getRawSelector(), this.fluent.getRequirements());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
