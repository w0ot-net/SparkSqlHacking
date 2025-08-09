package io.fabric8.kubernetes.api.model.certificates.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CertificateSigningRequestBuilder extends CertificateSigningRequestFluent implements VisitableBuilder {
   CertificateSigningRequestFluent fluent;

   public CertificateSigningRequestBuilder() {
      this(new CertificateSigningRequest());
   }

   public CertificateSigningRequestBuilder(CertificateSigningRequestFluent fluent) {
      this(fluent, new CertificateSigningRequest());
   }

   public CertificateSigningRequestBuilder(CertificateSigningRequestFluent fluent, CertificateSigningRequest instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CertificateSigningRequestBuilder(CertificateSigningRequest instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CertificateSigningRequest build() {
      CertificateSigningRequest buildable = new CertificateSigningRequest(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
