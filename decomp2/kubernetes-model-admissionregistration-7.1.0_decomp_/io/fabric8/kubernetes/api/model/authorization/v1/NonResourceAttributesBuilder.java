package io.fabric8.kubernetes.api.model.authorization.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NonResourceAttributesBuilder extends NonResourceAttributesFluent implements VisitableBuilder {
   NonResourceAttributesFluent fluent;

   public NonResourceAttributesBuilder() {
      this(new NonResourceAttributes());
   }

   public NonResourceAttributesBuilder(NonResourceAttributesFluent fluent) {
      this(fluent, new NonResourceAttributes());
   }

   public NonResourceAttributesBuilder(NonResourceAttributesFluent fluent, NonResourceAttributes instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NonResourceAttributesBuilder(NonResourceAttributes instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NonResourceAttributes build() {
      NonResourceAttributes buildable = new NonResourceAttributes(this.fluent.getPath(), this.fluent.getVerb());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
