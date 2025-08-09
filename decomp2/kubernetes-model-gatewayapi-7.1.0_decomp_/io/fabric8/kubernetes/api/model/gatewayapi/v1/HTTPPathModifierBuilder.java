package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HTTPPathModifierBuilder extends HTTPPathModifierFluent implements VisitableBuilder {
   HTTPPathModifierFluent fluent;

   public HTTPPathModifierBuilder() {
      this(new HTTPPathModifier());
   }

   public HTTPPathModifierBuilder(HTTPPathModifierFluent fluent) {
      this(fluent, new HTTPPathModifier());
   }

   public HTTPPathModifierBuilder(HTTPPathModifierFluent fluent, HTTPPathModifier instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HTTPPathModifierBuilder(HTTPPathModifier instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HTTPPathModifier build() {
      HTTPPathModifier buildable = new HTTPPathModifier(this.fluent.getReplaceFullPath(), this.fluent.getReplacePrefixMatch(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
