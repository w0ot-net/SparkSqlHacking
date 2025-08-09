package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class EphemeralContainerBuilder extends EphemeralContainerFluent implements VisitableBuilder {
   EphemeralContainerFluent fluent;

   public EphemeralContainerBuilder() {
      this(new EphemeralContainer());
   }

   public EphemeralContainerBuilder(EphemeralContainerFluent fluent) {
      this(fluent, new EphemeralContainer());
   }

   public EphemeralContainerBuilder(EphemeralContainerFluent fluent, EphemeralContainer instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public EphemeralContainerBuilder(EphemeralContainer instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public EphemeralContainer build() {
      EphemeralContainer buildable = new EphemeralContainer(this.fluent.getArgs(), this.fluent.getCommand(), this.fluent.buildEnv(), this.fluent.buildEnvFrom(), this.fluent.getImage(), this.fluent.getImagePullPolicy(), this.fluent.buildLifecycle(), this.fluent.buildLivenessProbe(), this.fluent.getName(), this.fluent.buildPorts(), this.fluent.buildReadinessProbe(), this.fluent.buildResizePolicy(), this.fluent.buildResources(), this.fluent.getRestartPolicy(), this.fluent.buildSecurityContext(), this.fluent.buildStartupProbe(), this.fluent.getStdin(), this.fluent.getStdinOnce(), this.fluent.getTargetContainerName(), this.fluent.getTerminationMessagePath(), this.fluent.getTerminationMessagePolicy(), this.fluent.getTty(), this.fluent.buildVolumeDevices(), this.fluent.buildVolumeMounts(), this.fluent.getWorkingDir());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
