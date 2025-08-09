package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.certificates.v1beta1.CertificateSigningRequest;
import io.fabric8.kubernetes.api.model.certificates.v1beta1.CertificateSigningRequestList;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.V1beta1CertificateSigningRequestResource;
import io.fabric8.kubernetes.client.dsl.V1beta1CertificatesAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1beta1CertificatesAPIGroupClient extends ClientAdapter implements V1beta1CertificatesAPIGroupDSL {
   public NonNamespaceOperation certificateSigningRequests() {
      return this.resources(CertificateSigningRequest.class, CertificateSigningRequestList.class, V1beta1CertificateSigningRequestResource.class);
   }

   public V1beta1CertificatesAPIGroupClient newInstance() {
      return new V1beta1CertificatesAPIGroupClient();
   }
}
