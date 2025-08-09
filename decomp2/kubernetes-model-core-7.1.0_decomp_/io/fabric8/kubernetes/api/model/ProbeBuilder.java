package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ProbeBuilder extends ProbeFluent implements VisitableBuilder {
   ProbeFluent fluent;

   public ProbeBuilder() {
      this(new Probe());
   }

   public ProbeBuilder(ProbeFluent fluent) {
      this(fluent, new Probe());
   }

   public ProbeBuilder(ProbeFluent fluent, Probe instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ProbeBuilder(Probe instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Probe build() {
      Probe buildable = new Probe(this.fluent.buildExec(), this.fluent.getFailureThreshold(), this.fluent.buildGrpc(), this.fluent.buildHttpGet(), this.fluent.getInitialDelaySeconds(), this.fluent.getPeriodSeconds(), this.fluent.getSuccessThreshold(), this.fluent.buildTcpSocket(), this.fluent.getTerminationGracePeriodSeconds(), this.fluent.getTimeoutSeconds());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
