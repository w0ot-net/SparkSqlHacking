package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ExecEnvVarBuilder extends ExecEnvVarFluent implements VisitableBuilder {
   ExecEnvVarFluent fluent;

   public ExecEnvVarBuilder() {
      this(new ExecEnvVar());
   }

   public ExecEnvVarBuilder(ExecEnvVarFluent fluent) {
      this(fluent, new ExecEnvVar());
   }

   public ExecEnvVarBuilder(ExecEnvVarFluent fluent, ExecEnvVar instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ExecEnvVarBuilder(ExecEnvVar instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ExecEnvVar build() {
      ExecEnvVar buildable = new ExecEnvVar(this.fluent.getName(), this.fluent.getValue());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
