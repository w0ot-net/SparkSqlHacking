package io.fabric8.kubernetes.client.dsl.internal.certificates.v1;

import io.fabric8.kubernetes.api.model.certificates.v1.CertificateSigningRequest;
import io.fabric8.kubernetes.api.model.certificates.v1.CertificateSigningRequestCondition;
import io.fabric8.kubernetes.api.model.certificates.v1.CertificateSigningRequestList;
import io.fabric8.kubernetes.api.model.certificates.v1.CertificateSigningRequestStatus;
import io.fabric8.kubernetes.api.model.certificates.v1.CertificateSigningRequestStatusBuilder;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.dsl.CertificateSigningRequestResource;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperation;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.OperationContext;

public class CertificateSigningRequestOperationsImpl extends HasMetadataOperation implements CertificateSigningRequestResource {
   public CertificateSigningRequestOperationsImpl(Client client) {
      this(HasMetadataOperationsImpl.defaultContext(client));
   }

   CertificateSigningRequestOperationsImpl(OperationContext context) {
      super(context.withApiGroupName("certificates.k8s.io").withApiGroupVersion("v1").withPlural("certificatesigningrequests"), CertificateSigningRequest.class, CertificateSigningRequestList.class);
   }

   public CertificateSigningRequestOperationsImpl newInstance(OperationContext context) {
      return new CertificateSigningRequestOperationsImpl(context);
   }

   public CertificateSigningRequest approve(CertificateSigningRequestCondition certificateSigningRequestCondition) {
      return this.addStatusToCSRAndSubmit(certificateSigningRequestCondition);
   }

   public CertificateSigningRequest deny(CertificateSigningRequestCondition certificateSigningRequestCondition) {
      return this.addStatusToCSRAndSubmit(certificateSigningRequestCondition);
   }

   private CertificateSigningRequest addStatusToCSRAndSubmit(CertificateSigningRequestCondition certificateSigningRequestCondition) {
      CertificateSigningRequest fromServerCsr = (CertificateSigningRequest)this.get();
      fromServerCsr.setStatus(this.createCertificateSigningRequestStatus(certificateSigningRequestCondition));
      return (CertificateSigningRequest)this.newInstance(this.context.withSubresource("approval")).update(fromServerCsr);
   }

   private CertificateSigningRequestStatus createCertificateSigningRequestStatus(CertificateSigningRequestCondition certificateSigningRequestCondition) {
      return ((CertificateSigningRequestStatusBuilder)(new CertificateSigningRequestStatusBuilder()).addToConditions(new CertificateSigningRequestCondition[]{certificateSigningRequestCondition})).build();
   }
}
