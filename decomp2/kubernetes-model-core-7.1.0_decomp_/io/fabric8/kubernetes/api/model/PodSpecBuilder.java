package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodSpecBuilder extends PodSpecFluent implements VisitableBuilder {
   PodSpecFluent fluent;

   public PodSpecBuilder() {
      this(new PodSpec());
   }

   public PodSpecBuilder(PodSpecFluent fluent) {
      this(fluent, new PodSpec());
   }

   public PodSpecBuilder(PodSpecFluent fluent, PodSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodSpecBuilder(PodSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodSpec build() {
      PodSpec buildable = new PodSpec(this.fluent.getActiveDeadlineSeconds(), this.fluent.buildAffinity(), this.fluent.getAutomountServiceAccountToken(), this.fluent.buildContainers(), this.fluent.buildDnsConfig(), this.fluent.getDnsPolicy(), this.fluent.getEnableServiceLinks(), this.fluent.buildEphemeralContainers(), this.fluent.buildHostAliases(), this.fluent.getHostIPC(), this.fluent.getHostNetwork(), this.fluent.getHostPID(), this.fluent.getHostUsers(), this.fluent.getHostname(), this.fluent.buildImagePullSecrets(), this.fluent.buildInitContainers(), this.fluent.getNodeName(), this.fluent.getNodeSelector(), this.fluent.buildOs(), this.fluent.getOverhead(), this.fluent.getPreemptionPolicy(), this.fluent.getPriority(), this.fluent.getPriorityClassName(), this.fluent.buildReadinessGates(), this.fluent.buildResourceClaims(), this.fluent.buildResources(), this.fluent.getRestartPolicy(), this.fluent.getRuntimeClassName(), this.fluent.getSchedulerName(), this.fluent.buildSchedulingGates(), this.fluent.buildSecurityContext(), this.fluent.getServiceAccount(), this.fluent.getServiceAccountName(), this.fluent.getSetHostnameAsFQDN(), this.fluent.getShareProcessNamespace(), this.fluent.getSubdomain(), this.fluent.getTerminationGracePeriodSeconds(), this.fluent.buildTolerations(), this.fluent.buildTopologySpreadConstraints(), this.fluent.buildVolumes());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
