package io.fabric8.kubernetes.api.model.clusterapi.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MachineBuilder extends MachineFluent implements VisitableBuilder {
   MachineFluent fluent;

   public MachineBuilder() {
      this(new Machine());
   }

   public MachineBuilder(MachineFluent fluent) {
      this(fluent, new Machine());
   }

   public MachineBuilder(MachineFluent fluent, Machine instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MachineBuilder(Machine instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Machine build() {
      Machine buildable = new Machine(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
