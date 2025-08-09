package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;

public interface CertificatesAPIGroupDSL extends Client {
   V1CertificatesAPIGroupDSL v1();

   V1beta1CertificatesAPIGroupDSL v1beta1();

   V1Alpha1CertificatesAPIGroupDSL v1alpha1();
}
