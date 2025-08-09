package io.fabric8.kubernetes.api.model.certificates.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CertificateSigningRequestConditionBuilder extends CertificateSigningRequestConditionFluent implements VisitableBuilder {
   CertificateSigningRequestConditionFluent fluent;

   public CertificateSigningRequestConditionBuilder() {
      this(new CertificateSigningRequestCondition());
   }

   public CertificateSigningRequestConditionBuilder(CertificateSigningRequestConditionFluent fluent) {
      this(fluent, new CertificateSigningRequestCondition());
   }

   public CertificateSigningRequestConditionBuilder(CertificateSigningRequestConditionFluent fluent, CertificateSigningRequestCondition instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CertificateSigningRequestConditionBuilder(CertificateSigningRequestCondition instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CertificateSigningRequestCondition build() {
      CertificateSigningRequestCondition buildable = new CertificateSigningRequestCondition(this.fluent.getLastTransitionTime(), this.fluent.getLastUpdateTime(), this.fluent.getMessage(), this.fluent.getReason(), this.fluent.getStatus(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
