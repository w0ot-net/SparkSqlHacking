package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NodeSystemInfoBuilder extends NodeSystemInfoFluent implements VisitableBuilder {
   NodeSystemInfoFluent fluent;

   public NodeSystemInfoBuilder() {
      this(new NodeSystemInfo());
   }

   public NodeSystemInfoBuilder(NodeSystemInfoFluent fluent) {
      this(fluent, new NodeSystemInfo());
   }

   public NodeSystemInfoBuilder(NodeSystemInfoFluent fluent, NodeSystemInfo instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NodeSystemInfoBuilder(NodeSystemInfo instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NodeSystemInfo build() {
      NodeSystemInfo buildable = new NodeSystemInfo(this.fluent.getArchitecture(), this.fluent.getBootID(), this.fluent.getContainerRuntimeVersion(), this.fluent.getKernelVersion(), this.fluent.getKubeProxyVersion(), this.fluent.getKubeletVersion(), this.fluent.getMachineID(), this.fluent.getOperatingSystem(), this.fluent.getOsImage(), this.fluent.getSystemUUID());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
