package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ObjectFieldSelectorBuilder extends ObjectFieldSelectorFluent implements VisitableBuilder {
   ObjectFieldSelectorFluent fluent;

   public ObjectFieldSelectorBuilder() {
      this(new ObjectFieldSelector());
   }

   public ObjectFieldSelectorBuilder(ObjectFieldSelectorFluent fluent) {
      this(fluent, new ObjectFieldSelector());
   }

   public ObjectFieldSelectorBuilder(ObjectFieldSelectorFluent fluent, ObjectFieldSelector instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ObjectFieldSelectorBuilder(ObjectFieldSelector instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ObjectFieldSelector build() {
      ObjectFieldSelector buildable = new ObjectFieldSelector(this.fluent.getApiVersion(), this.fluent.getFieldPath());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
