package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodExecOptionsBuilder extends PodExecOptionsFluent implements VisitableBuilder {
   PodExecOptionsFluent fluent;

   public PodExecOptionsBuilder() {
      this(new PodExecOptions());
   }

   public PodExecOptionsBuilder(PodExecOptionsFluent fluent) {
      this(fluent, new PodExecOptions());
   }

   public PodExecOptionsBuilder(PodExecOptionsFluent fluent, PodExecOptions instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodExecOptionsBuilder(PodExecOptions instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodExecOptions build() {
      PodExecOptions buildable = new PodExecOptions(this.fluent.getApiVersion(), this.fluent.getCommand(), this.fluent.getContainer(), this.fluent.getKind(), this.fluent.getStderr(), this.fluent.getStdin(), this.fluent.getStdout(), this.fluent.getTty());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
