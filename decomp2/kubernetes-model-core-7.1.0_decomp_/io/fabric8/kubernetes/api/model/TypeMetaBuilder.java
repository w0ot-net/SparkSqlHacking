package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TypeMetaBuilder extends TypeMetaFluent implements VisitableBuilder {
   TypeMetaFluent fluent;

   public TypeMetaBuilder() {
      this(new TypeMeta());
   }

   public TypeMetaBuilder(TypeMetaFluent fluent) {
      this(fluent, new TypeMeta());
   }

   public TypeMetaBuilder(TypeMetaFluent fluent, TypeMeta instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TypeMetaBuilder(TypeMeta instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TypeMeta build() {
      TypeMeta buildable = new TypeMeta(this.fluent.getApiVersion(), this.fluent.getKind());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
