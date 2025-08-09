package io.fabric8.kubernetes.api.model.certificates.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CertificateSigningRequestStatusBuilder extends CertificateSigningRequestStatusFluent implements VisitableBuilder {
   CertificateSigningRequestStatusFluent fluent;

   public CertificateSigningRequestStatusBuilder() {
      this(new CertificateSigningRequestStatus());
   }

   public CertificateSigningRequestStatusBuilder(CertificateSigningRequestStatusFluent fluent) {
      this(fluent, new CertificateSigningRequestStatus());
   }

   public CertificateSigningRequestStatusBuilder(CertificateSigningRequestStatusFluent fluent, CertificateSigningRequestStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CertificateSigningRequestStatusBuilder(CertificateSigningRequestStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CertificateSigningRequestStatus build() {
      CertificateSigningRequestStatus buildable = new CertificateSigningRequestStatus(this.fluent.getCertificate(), this.fluent.buildConditions());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
