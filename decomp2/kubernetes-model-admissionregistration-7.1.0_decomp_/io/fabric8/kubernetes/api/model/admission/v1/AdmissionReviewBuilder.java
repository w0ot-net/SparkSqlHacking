package io.fabric8.kubernetes.api.model.admission.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class AdmissionReviewBuilder extends AdmissionReviewFluent implements VisitableBuilder {
   AdmissionReviewFluent fluent;

   public AdmissionReviewBuilder() {
      this(new AdmissionReview());
   }

   public AdmissionReviewBuilder(AdmissionReviewFluent fluent) {
      this(fluent, new AdmissionReview());
   }

   public AdmissionReviewBuilder(AdmissionReviewFluent fluent, AdmissionReview instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public AdmissionReviewBuilder(AdmissionReview instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public AdmissionReview build() {
      AdmissionReview buildable = new AdmissionReview(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildRequest(), this.fluent.buildResponse());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
