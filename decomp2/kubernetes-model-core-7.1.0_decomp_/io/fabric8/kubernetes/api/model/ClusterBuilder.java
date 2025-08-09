package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ClusterBuilder extends ClusterFluent implements VisitableBuilder {
   ClusterFluent fluent;

   public ClusterBuilder() {
      this(new Cluster());
   }

   public ClusterBuilder(ClusterFluent fluent) {
      this(fluent, new Cluster());
   }

   public ClusterBuilder(ClusterFluent fluent, Cluster instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ClusterBuilder(Cluster instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Cluster build() {
      Cluster buildable = new Cluster(this.fluent.getCertificateAuthority(), this.fluent.getCertificateAuthorityData(), this.fluent.getDisableCompression(), this.fluent.buildExtensions(), this.fluent.getInsecureSkipTlsVerify(), this.fluent.getProxyUrl(), this.fluent.getServer(), this.fluent.getTlsServerName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
