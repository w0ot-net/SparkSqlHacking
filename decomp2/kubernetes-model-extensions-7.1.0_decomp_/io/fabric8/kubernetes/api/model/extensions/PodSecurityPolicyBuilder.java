package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodSecurityPolicyBuilder extends PodSecurityPolicyFluent implements VisitableBuilder {
   PodSecurityPolicyFluent fluent;

   public PodSecurityPolicyBuilder() {
      this(new PodSecurityPolicy());
   }

   public PodSecurityPolicyBuilder(PodSecurityPolicyFluent fluent) {
      this(fluent, new PodSecurityPolicy());
   }

   public PodSecurityPolicyBuilder(PodSecurityPolicyFluent fluent, PodSecurityPolicy instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodSecurityPolicyBuilder(PodSecurityPolicy instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodSecurityPolicy build() {
      PodSecurityPolicy buildable = new PodSecurityPolicy(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
