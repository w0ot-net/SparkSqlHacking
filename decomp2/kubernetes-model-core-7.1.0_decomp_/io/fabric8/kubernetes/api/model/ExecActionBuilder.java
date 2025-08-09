package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ExecActionBuilder extends ExecActionFluent implements VisitableBuilder {
   ExecActionFluent fluent;

   public ExecActionBuilder() {
      this(new ExecAction());
   }

   public ExecActionBuilder(ExecActionFluent fluent) {
      this(fluent, new ExecAction());
   }

   public ExecActionBuilder(ExecActionFluent fluent, ExecAction instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ExecActionBuilder(ExecAction instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ExecAction build() {
      ExecAction buildable = new ExecAction(this.fluent.getCommand());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
