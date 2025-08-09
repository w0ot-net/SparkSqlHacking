package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class AnyTypeBuilder extends AnyTypeFluent implements VisitableBuilder {
   AnyTypeFluent fluent;

   public AnyTypeBuilder() {
      this(new AnyType());
   }

   public AnyTypeBuilder(AnyTypeFluent fluent) {
      this(fluent, new AnyType());
   }

   public AnyTypeBuilder(AnyTypeFluent fluent, AnyType instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public AnyTypeBuilder(AnyType instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public AnyType build() {
      AnyType buildable = new AnyType(this.fluent.getValue());
      return buildable;
   }
}
