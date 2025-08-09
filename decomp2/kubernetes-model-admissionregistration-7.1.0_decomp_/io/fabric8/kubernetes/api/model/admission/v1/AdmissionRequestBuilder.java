package io.fabric8.kubernetes.api.model.admission.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class AdmissionRequestBuilder extends AdmissionRequestFluent implements VisitableBuilder {
   AdmissionRequestFluent fluent;

   public AdmissionRequestBuilder() {
      this(new AdmissionRequest());
   }

   public AdmissionRequestBuilder(AdmissionRequestFluent fluent) {
      this(fluent, new AdmissionRequest());
   }

   public AdmissionRequestBuilder(AdmissionRequestFluent fluent, AdmissionRequest instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public AdmissionRequestBuilder(AdmissionRequest instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public AdmissionRequest build() {
      AdmissionRequest buildable = new AdmissionRequest(this.fluent.getDryRun(), this.fluent.getKind(), this.fluent.getName(), this.fluent.getNamespace(), this.fluent.getObject(), this.fluent.getOldObject(), this.fluent.getOperation(), this.fluent.getOptions(), this.fluent.getRequestKind(), this.fluent.getRequestResource(), this.fluent.getRequestSubResource(), this.fluent.getResource(), this.fluent.getSubResource(), this.fluent.getUid(), this.fluent.buildUserInfo());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
