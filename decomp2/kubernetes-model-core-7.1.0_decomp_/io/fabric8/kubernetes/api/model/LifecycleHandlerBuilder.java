package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LifecycleHandlerBuilder extends LifecycleHandlerFluent implements VisitableBuilder {
   LifecycleHandlerFluent fluent;

   public LifecycleHandlerBuilder() {
      this(new LifecycleHandler());
   }

   public LifecycleHandlerBuilder(LifecycleHandlerFluent fluent) {
      this(fluent, new LifecycleHandler());
   }

   public LifecycleHandlerBuilder(LifecycleHandlerFluent fluent, LifecycleHandler instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LifecycleHandlerBuilder(LifecycleHandler instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LifecycleHandler build() {
      LifecycleHandler buildable = new LifecycleHandler(this.fluent.buildExec(), this.fluent.buildHttpGet(), this.fluent.buildSleep(), this.fluent.buildTcpSocket());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
