package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class EnvVarBuilder extends EnvVarFluent implements VisitableBuilder {
   EnvVarFluent fluent;

   public EnvVarBuilder() {
      this(new EnvVar());
   }

   public EnvVarBuilder(EnvVarFluent fluent) {
      this(fluent, new EnvVar());
   }

   public EnvVarBuilder(EnvVarFluent fluent, EnvVar instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public EnvVarBuilder(EnvVar instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public EnvVar build() {
      EnvVar buildable = new EnvVar(this.fluent.getName(), this.fluent.getValue(), this.fluent.buildValueFrom());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
