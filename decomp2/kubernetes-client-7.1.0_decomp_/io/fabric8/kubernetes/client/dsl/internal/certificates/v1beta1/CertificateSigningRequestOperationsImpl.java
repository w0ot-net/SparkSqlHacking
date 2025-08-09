package io.fabric8.kubernetes.client.dsl.internal.certificates.v1beta1;

import io.fabric8.kubernetes.api.model.certificates.v1beta1.CertificateSigningRequest;
import io.fabric8.kubernetes.api.model.certificates.v1beta1.CertificateSigningRequestCondition;
import io.fabric8.kubernetes.api.model.certificates.v1beta1.CertificateSigningRequestList;
import io.fabric8.kubernetes.api.model.certificates.v1beta1.CertificateSigningRequestStatus;
import io.fabric8.kubernetes.api.model.certificates.v1beta1.CertificateSigningRequestStatusBuilder;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.dsl.V1beta1CertificateSigningRequestResource;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperation;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.OperationContext;

public class CertificateSigningRequestOperationsImpl extends HasMetadataOperation implements V1beta1CertificateSigningRequestResource {
   public CertificateSigningRequestOperationsImpl(Client client) {
      this(HasMetadataOperationsImpl.defaultContext(client));
   }

   CertificateSigningRequestOperationsImpl(OperationContext context) {
      super(context.withApiGroupName("certificates.k8s.io").withApiGroupVersion("v1beta1").withPlural("certificatesigningrequests"), CertificateSigningRequest.class, CertificateSigningRequestList.class);
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

   private CertificateSigningRequestStatus createCertificateSigningRequestStatus(CertificateSigningRequestCondition certificateSigningRequestCondition) {
      return ((CertificateSigningRequestStatusBuilder)(new CertificateSigningRequestStatusBuilder()).addToConditions(new CertificateSigningRequestCondition[]{certificateSigningRequestCondition})).build();
   }

   private CertificateSigningRequest addStatusToCSRAndSubmit(CertificateSigningRequestCondition certificateSigningRequestCondition) {
      CertificateSigningRequest fromServerCsr = (CertificateSigningRequest)this.get();
      fromServerCsr.setStatus(this.createCertificateSigningRequestStatus(certificateSigningRequestCondition));
      return (CertificateSigningRequest)this.newInstance(this.context.withSubresource("approval")).update(fromServerCsr);
   }
}
