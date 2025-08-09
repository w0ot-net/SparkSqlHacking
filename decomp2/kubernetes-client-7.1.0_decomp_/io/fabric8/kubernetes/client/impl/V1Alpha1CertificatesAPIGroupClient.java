package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.certificates.v1alpha1.ClusterTrustBundle;
import io.fabric8.kubernetes.api.model.certificates.v1alpha1.ClusterTrustBundleList;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.V1Alpha1CertificatesAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1Alpha1CertificatesAPIGroupClient extends ClientAdapter implements V1Alpha1CertificatesAPIGroupDSL {
   public NonNamespaceOperation clusterTrustBundles() {
      return this.resources(ClusterTrustBundle.class, ClusterTrustBundleList.class);
   }

   public V1Alpha1CertificatesAPIGroupClient newInstance() {
      return new V1Alpha1CertificatesAPIGroupClient();
   }
}
