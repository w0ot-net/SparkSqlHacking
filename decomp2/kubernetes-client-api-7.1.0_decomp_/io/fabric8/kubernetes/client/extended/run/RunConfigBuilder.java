package io.fabric8.kubernetes.client.extended.run;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RunConfigBuilder extends RunConfigFluent implements VisitableBuilder {
   RunConfigFluent fluent;

   public RunConfigBuilder() {
      this.fluent = this;
   }

   public RunConfigBuilder(RunConfigFluent fluent) {
      this.fluent = fluent;
   }

   public RunConfigBuilder(RunConfigFluent fluent, RunConfig instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RunConfigBuilder(RunConfig instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public EditableRunConfig build() {
      EditableRunConfig buildable = new EditableRunConfig(this.fluent.getName(), this.fluent.getImage(), this.fluent.getImagePullPolicy(), this.fluent.getCommand(), this.fluent.getArgs(), this.fluent.getRestartPolicy(), this.fluent.getServiceAccount(), this.fluent.getLabels(), this.fluent.getEnv(), this.fluent.getLimits(), this.fluent.getRequests(), this.fluent.getPort());
      return buildable;
   }
}
