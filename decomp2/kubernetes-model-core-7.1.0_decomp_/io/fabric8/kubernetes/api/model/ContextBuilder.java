package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ContextBuilder extends ContextFluent implements VisitableBuilder {
   ContextFluent fluent;

   public ContextBuilder() {
      this(new Context());
   }

   public ContextBuilder(ContextFluent fluent) {
      this(fluent, new Context());
   }

   public ContextBuilder(ContextFluent fluent, Context instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ContextBuilder(Context instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Context build() {
      Context buildable = new Context(this.fluent.getCluster(), this.fluent.buildExtensions(), this.fluent.getNamespace(), this.fluent.getUser());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
