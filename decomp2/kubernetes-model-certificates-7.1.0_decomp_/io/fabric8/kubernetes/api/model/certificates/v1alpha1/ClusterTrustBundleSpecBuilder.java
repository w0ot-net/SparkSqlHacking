package io.fabric8.kubernetes.api.model.certificates.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ClusterTrustBundleSpecBuilder extends ClusterTrustBundleSpecFluent implements VisitableBuilder {
   ClusterTrustBundleSpecFluent fluent;

   public ClusterTrustBundleSpecBuilder() {
      this(new ClusterTrustBundleSpec());
   }

   public ClusterTrustBundleSpecBuilder(ClusterTrustBundleSpecFluent fluent) {
      this(fluent, new ClusterTrustBundleSpec());
   }

   public ClusterTrustBundleSpecBuilder(ClusterTrustBundleSpecFluent fluent, ClusterTrustBundleSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ClusterTrustBundleSpecBuilder(ClusterTrustBundleSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ClusterTrustBundleSpec build() {
      ClusterTrustBundleSpec buildable = new ClusterTrustBundleSpec(this.fluent.getSignerName(), this.fluent.getTrustBundle());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
