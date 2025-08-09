package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class BindingBuilder extends BindingFluent implements VisitableBuilder {
   BindingFluent fluent;

   public BindingBuilder() {
      this(new Binding());
   }

   public BindingBuilder(BindingFluent fluent) {
      this(fluent, new Binding());
   }

   public BindingBuilder(BindingFluent fluent, Binding instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public BindingBuilder(Binding instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Binding build() {
      Binding buildable = new Binding(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildTarget());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
