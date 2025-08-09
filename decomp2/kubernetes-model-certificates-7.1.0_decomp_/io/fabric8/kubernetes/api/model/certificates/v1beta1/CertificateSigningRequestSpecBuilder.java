package io.fabric8.kubernetes.api.model.certificates.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CertificateSigningRequestSpecBuilder extends CertificateSigningRequestSpecFluent implements VisitableBuilder {
   CertificateSigningRequestSpecFluent fluent;

   public CertificateSigningRequestSpecBuilder() {
      this(new CertificateSigningRequestSpec());
   }

   public CertificateSigningRequestSpecBuilder(CertificateSigningRequestSpecFluent fluent) {
      this(fluent, new CertificateSigningRequestSpec());
   }

   public CertificateSigningRequestSpecBuilder(CertificateSigningRequestSpecFluent fluent, CertificateSigningRequestSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CertificateSigningRequestSpecBuilder(CertificateSigningRequestSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CertificateSigningRequestSpec build() {
      CertificateSigningRequestSpec buildable = new CertificateSigningRequestSpec(this.fluent.getExtra(), this.fluent.getGroups(), this.fluent.getRequest(), this.fluent.getSignerName(), this.fluent.getUid(), this.fluent.getUsages(), this.fluent.getUsername());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
