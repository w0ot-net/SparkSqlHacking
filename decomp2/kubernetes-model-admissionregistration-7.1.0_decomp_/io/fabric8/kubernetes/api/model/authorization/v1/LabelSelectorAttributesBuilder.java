package io.fabric8.kubernetes.api.model.authorization.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LabelSelectorAttributesBuilder extends LabelSelectorAttributesFluent implements VisitableBuilder {
   LabelSelectorAttributesFluent fluent;

   public LabelSelectorAttributesBuilder() {
      this(new LabelSelectorAttributes());
   }

   public LabelSelectorAttributesBuilder(LabelSelectorAttributesFluent fluent) {
      this(fluent, new LabelSelectorAttributes());
   }

   public LabelSelectorAttributesBuilder(LabelSelectorAttributesFluent fluent, LabelSelectorAttributes instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LabelSelectorAttributesBuilder(LabelSelectorAttributes instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LabelSelectorAttributes build() {
      LabelSelectorAttributes buildable = new LabelSelectorAttributes(this.fluent.getRawSelector(), this.fluent.getRequirements());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
