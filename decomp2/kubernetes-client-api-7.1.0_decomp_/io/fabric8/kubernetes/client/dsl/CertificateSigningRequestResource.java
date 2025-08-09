package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.api.model.certificates.v1.CertificateSigningRequestCondition;
import io.fabric8.kubernetes.api.model.certificates.v1.CertificateSigningRequestConditionBuilder;

public interface CertificateSigningRequestResource extends Resource {
   default Object deny() {
      return this.deny(((CertificateSigningRequestConditionBuilder)((CertificateSigningRequestConditionBuilder)((CertificateSigningRequestConditionBuilder)((CertificateSigningRequestConditionBuilder)(new CertificateSigningRequestConditionBuilder()).withType("Denied")).withStatus("True")).withReason("DeniedViaRESTApi")).withMessage("Denied by REST API /approval endpoint.")).build());
   }

   Object deny(CertificateSigningRequestCondition var1);

   Object approve(CertificateSigningRequestCondition var1);

   default Object approve() {
      return this.approve(((CertificateSigningRequestConditionBuilder)((CertificateSigningRequestConditionBuilder)((CertificateSigningRequestConditionBuilder)((CertificateSigningRequestConditionBuilder)(new CertificateSigningRequestConditionBuilder()).withType("Approved")).withStatus("True")).withReason("ApprovedViaRESTApi")).withMessage("Approved by REST API /approval endpoint.")).build());
   }
}
