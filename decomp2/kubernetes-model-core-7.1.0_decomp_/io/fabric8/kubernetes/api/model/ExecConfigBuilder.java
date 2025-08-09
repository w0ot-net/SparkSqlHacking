package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ExecConfigBuilder extends ExecConfigFluent implements VisitableBuilder {
   ExecConfigFluent fluent;

   public ExecConfigBuilder() {
      this(new ExecConfig());
   }

   public ExecConfigBuilder(ExecConfigFluent fluent) {
      this(fluent, new ExecConfig());
   }

   public ExecConfigBuilder(ExecConfigFluent fluent, ExecConfig instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ExecConfigBuilder(ExecConfig instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ExecConfig build() {
      ExecConfig buildable = new ExecConfig(this.fluent.getApiVersion(), this.fluent.getArgs(), this.fluent.getCommand(), this.fluent.buildEnv(), this.fluent.getInstallHint(), this.fluent.getInteractiveMode(), this.fluent.getProvideClusterInfo());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
