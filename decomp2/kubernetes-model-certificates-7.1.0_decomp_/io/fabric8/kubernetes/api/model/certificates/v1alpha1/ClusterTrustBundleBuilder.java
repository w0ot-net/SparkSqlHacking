package io.fabric8.kubernetes.api.model.certificates.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ClusterTrustBundleBuilder extends ClusterTrustBundleFluent implements VisitableBuilder {
   ClusterTrustBundleFluent fluent;

   public ClusterTrustBundleBuilder() {
      this(new ClusterTrustBundle());
   }

   public ClusterTrustBundleBuilder(ClusterTrustBundleFluent fluent) {
      this(fluent, new ClusterTrustBundle());
   }

   public ClusterTrustBundleBuilder(ClusterTrustBundleFluent fluent, ClusterTrustBundle instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ClusterTrustBundleBuilder(ClusterTrustBundle instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ClusterTrustBundle build() {
      ClusterTrustBundle buildable = new ClusterTrustBundle(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
