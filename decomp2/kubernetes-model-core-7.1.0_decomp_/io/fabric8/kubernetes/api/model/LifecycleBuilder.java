package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LifecycleBuilder extends LifecycleFluent implements VisitableBuilder {
   LifecycleFluent fluent;

   public LifecycleBuilder() {
      this(new Lifecycle());
   }

   public LifecycleBuilder(LifecycleFluent fluent) {
      this(fluent, new Lifecycle());
   }

   public LifecycleBuilder(LifecycleFluent fluent, Lifecycle instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LifecycleBuilder(Lifecycle instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Lifecycle build() {
      Lifecycle buildable = new Lifecycle(this.fluent.buildPostStart(), this.fluent.buildPreStop());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
