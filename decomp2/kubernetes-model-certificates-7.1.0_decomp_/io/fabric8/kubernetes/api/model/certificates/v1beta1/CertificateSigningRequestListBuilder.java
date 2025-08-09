package io.fabric8.kubernetes.api.model.certificates.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CertificateSigningRequestListBuilder extends CertificateSigningRequestListFluent implements VisitableBuilder {
   CertificateSigningRequestListFluent fluent;

   public CertificateSigningRequestListBuilder() {
      this(new CertificateSigningRequestList());
   }

   public CertificateSigningRequestListBuilder(CertificateSigningRequestListFluent fluent) {
      this(fluent, new CertificateSigningRequestList());
   }

   public CertificateSigningRequestListBuilder(CertificateSigningRequestListFluent fluent, CertificateSigningRequestList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CertificateSigningRequestListBuilder(CertificateSigningRequestList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CertificateSigningRequestList build() {
      CertificateSigningRequestList buildable = new CertificateSigningRequestList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
