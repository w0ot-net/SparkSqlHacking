package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NamedContextBuilder extends NamedContextFluent implements VisitableBuilder {
   NamedContextFluent fluent;

   public NamedContextBuilder() {
      this(new NamedContext());
   }

   public NamedContextBuilder(NamedContextFluent fluent) {
      this(fluent, new NamedContext());
   }

   public NamedContextBuilder(NamedContextFluent fluent, NamedContext instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NamedContextBuilder(NamedContext instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NamedContext build() {
      NamedContext buildable = new NamedContext(this.fluent.buildContext(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
