package io.fabric8.kubernetes.api.model.admission.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class AdmissionResponseBuilder extends AdmissionResponseFluent implements VisitableBuilder {
   AdmissionResponseFluent fluent;

   public AdmissionResponseBuilder() {
      this(new AdmissionResponse());
   }

   public AdmissionResponseBuilder(AdmissionResponseFluent fluent) {
      this(fluent, new AdmissionResponse());
   }

   public AdmissionResponseBuilder(AdmissionResponseFluent fluent, AdmissionResponse instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public AdmissionResponseBuilder(AdmissionResponse instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public AdmissionResponse build() {
      AdmissionResponse buildable = new AdmissionResponse(this.fluent.getAllowed(), this.fluent.getAuditAnnotations(), this.fluent.getPatch(), this.fluent.getPatchType(), this.fluent.getStatus(), this.fluent.getUid(), this.fluent.getWarnings());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
