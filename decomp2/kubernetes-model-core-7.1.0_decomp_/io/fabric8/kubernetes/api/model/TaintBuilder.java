package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TaintBuilder extends TaintFluent implements VisitableBuilder {
   TaintFluent fluent;

   public TaintBuilder() {
      this(new Taint());
   }

   public TaintBuilder(TaintFluent fluent) {
      this(fluent, new Taint());
   }

   public TaintBuilder(TaintFluent fluent, Taint instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TaintBuilder(Taint instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Taint build() {
      Taint buildable = new Taint(this.fluent.getEffect(), this.fluent.getKey(), this.fluent.getTimeAdded(), this.fluent.getValue());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
