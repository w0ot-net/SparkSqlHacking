package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PreconditionsBuilder extends PreconditionsFluent implements VisitableBuilder {
   PreconditionsFluent fluent;

   public PreconditionsBuilder() {
      this(new Preconditions());
   }

   public PreconditionsBuilder(PreconditionsFluent fluent) {
      this(fluent, new Preconditions());
   }

   public PreconditionsBuilder(PreconditionsFluent fluent, Preconditions instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PreconditionsBuilder(Preconditions instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Preconditions build() {
      Preconditions buildable = new Preconditions(this.fluent.getResourceVersion(), this.fluent.getUid());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
