package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;

public interface V1beta1CertificatesAPIGroupDSL extends Client {
   NonNamespaceOperation certificateSigningRequests();
}
