package io.fabric8.kubernetes.model.util;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DummyBuilder extends DummyFluent implements VisitableBuilder {
   DummyFluent fluent;

   public DummyBuilder() {
      this(new Dummy());
   }

   public DummyBuilder(DummyFluent fluent) {
      this(fluent, new Dummy());
   }

   public DummyBuilder(DummyFluent fluent, Dummy instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DummyBuilder(Dummy instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Dummy build() {
      Dummy buildable = new Dummy();
      return buildable;
   }
}
