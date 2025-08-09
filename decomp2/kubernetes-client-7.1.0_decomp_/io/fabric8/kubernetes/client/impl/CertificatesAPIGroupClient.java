package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.client.dsl.CertificatesAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1Alpha1CertificatesAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1CertificatesAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1beta1CertificatesAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class CertificatesAPIGroupClient extends ClientAdapter implements CertificatesAPIGroupDSL {
   public V1CertificatesAPIGroupDSL v1() {
      return (V1CertificatesAPIGroupDSL)this.adapt(V1CertificatesAPIGroupClient.class);
   }

   public V1beta1CertificatesAPIGroupDSL v1beta1() {
      return (V1beta1CertificatesAPIGroupDSL)this.adapt(V1beta1CertificatesAPIGroupClient.class);
   }

   public V1Alpha1CertificatesAPIGroupDSL v1alpha1() {
      return (V1Alpha1CertificatesAPIGroupDSL)this.adapt(V1Alpha1CertificatesAPIGroupClient.class);
   }

   public CertificatesAPIGroupClient newInstance() {
      return new CertificatesAPIGroupClient();
   }
}
