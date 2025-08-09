package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.certificates.v1.CertificateSigningRequest;
import io.fabric8.kubernetes.api.model.certificates.v1.CertificateSigningRequestList;
import io.fabric8.kubernetes.client.dsl.CertificateSigningRequestResource;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.V1CertificatesAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1CertificatesAPIGroupClient extends ClientAdapter implements V1CertificatesAPIGroupDSL {
   public NonNamespaceOperation certificateSigningRequests() {
      return this.resources(CertificateSigningRequest.class, CertificateSigningRequestList.class, CertificateSigningRequestResource.class);
   }

   public V1CertificatesAPIGroupClient newInstance() {
      return new V1CertificatesAPIGroupClient();
   }
}
