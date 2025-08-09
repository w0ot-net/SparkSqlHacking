package io.fabric8.kubernetes.api.model.certificates.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ClusterTrustBundleListBuilder extends ClusterTrustBundleListFluent implements VisitableBuilder {
   ClusterTrustBundleListFluent fluent;

   public ClusterTrustBundleListBuilder() {
      this(new ClusterTrustBundleList());
   }

   public ClusterTrustBundleListBuilder(ClusterTrustBundleListFluent fluent) {
      this(fluent, new ClusterTrustBundleList());
   }

   public ClusterTrustBundleListBuilder(ClusterTrustBundleListFluent fluent, ClusterTrustBundleList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ClusterTrustBundleListBuilder(ClusterTrustBundleList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ClusterTrustBundleList build() {
      ClusterTrustBundleList buildable = new ClusterTrustBundleList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
