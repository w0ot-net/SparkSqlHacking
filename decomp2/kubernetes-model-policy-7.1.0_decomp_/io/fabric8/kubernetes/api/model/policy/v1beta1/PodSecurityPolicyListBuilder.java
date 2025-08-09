package io.fabric8.kubernetes.api.model.policy.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodSecurityPolicyListBuilder extends PodSecurityPolicyListFluent implements VisitableBuilder {
   PodSecurityPolicyListFluent fluent;

   public PodSecurityPolicyListBuilder() {
      this(new PodSecurityPolicyList());
   }

   public PodSecurityPolicyListBuilder(PodSecurityPolicyListFluent fluent) {
      this(fluent, new PodSecurityPolicyList());
   }

   public PodSecurityPolicyListBuilder(PodSecurityPolicyListFluent fluent, PodSecurityPolicyList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodSecurityPolicyListBuilder(PodSecurityPolicyList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodSecurityPolicyList build() {
      PodSecurityPolicyList buildable = new PodSecurityPolicyList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
