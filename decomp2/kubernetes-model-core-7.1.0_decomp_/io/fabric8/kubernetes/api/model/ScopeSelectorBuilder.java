package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ScopeSelectorBuilder extends ScopeSelectorFluent implements VisitableBuilder {
   ScopeSelectorFluent fluent;

   public ScopeSelectorBuilder() {
      this(new ScopeSelector());
   }

   public ScopeSelectorBuilder(ScopeSelectorFluent fluent) {
      this(fluent, new ScopeSelector());
   }

   public ScopeSelectorBuilder(ScopeSelectorFluent fluent, ScopeSelector instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ScopeSelectorBuilder(ScopeSelector instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ScopeSelector build() {
      ScopeSelector buildable = new ScopeSelector(this.fluent.buildMatchExpressions());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
