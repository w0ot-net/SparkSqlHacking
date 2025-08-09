package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ClusterTrustBundleProjectionBuilder extends ClusterTrustBundleProjectionFluent implements VisitableBuilder {
   ClusterTrustBundleProjectionFluent fluent;

   public ClusterTrustBundleProjectionBuilder() {
      this(new ClusterTrustBundleProjection());
   }

   public ClusterTrustBundleProjectionBuilder(ClusterTrustBundleProjectionFluent fluent) {
      this(fluent, new ClusterTrustBundleProjection());
   }

   public ClusterTrustBundleProjectionBuilder(ClusterTrustBundleProjectionFluent fluent, ClusterTrustBundleProjection instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ClusterTrustBundleProjectionBuilder(ClusterTrustBundleProjection instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ClusterTrustBundleProjection build() {
      ClusterTrustBundleProjection buildable = new ClusterTrustBundleProjection(this.fluent.buildLabelSelector(), this.fluent.getName(), this.fluent.getOptional(), this.fluent.getPath(), this.fluent.getSignerName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
