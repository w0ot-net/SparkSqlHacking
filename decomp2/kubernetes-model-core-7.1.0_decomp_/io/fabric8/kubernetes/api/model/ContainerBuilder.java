package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ContainerBuilder extends ContainerFluent implements VisitableBuilder {
   ContainerFluent fluent;

   public ContainerBuilder() {
      this(new Container());
   }

   public ContainerBuilder(ContainerFluent fluent) {
      this(fluent, new Container());
   }

   public ContainerBuilder(ContainerFluent fluent, Container instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ContainerBuilder(Container instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Container build() {
      Container buildable = new Container(this.fluent.getArgs(), this.fluent.getCommand(), this.fluent.buildEnv(), this.fluent.buildEnvFrom(), this.fluent.getImage(), this.fluent.getImagePullPolicy(), this.fluent.buildLifecycle(), this.fluent.buildLivenessProbe(), this.fluent.getName(), this.fluent.buildPorts(), this.fluent.buildReadinessProbe(), this.fluent.buildResizePolicy(), this.fluent.buildResources(), this.fluent.getRestartPolicy(), this.fluent.buildSecurityContext(), this.fluent.buildStartupProbe(), this.fluent.getStdin(), this.fluent.getStdinOnce(), this.fluent.getTerminationMessagePath(), this.fluent.getTerminationMessagePolicy(), this.fluent.getTty(), this.fluent.buildVolumeDevices(), this.fluent.buildVolumeMounts(), this.fluent.getWorkingDir());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
