package io.fabric8.kubernetes.api.model.admissionregistration.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ValidatingWebhookBuilder extends ValidatingWebhookFluent implements VisitableBuilder {
   ValidatingWebhookFluent fluent;

   public ValidatingWebhookBuilder() {
      this(new ValidatingWebhook());
   }

   public ValidatingWebhookBuilder(ValidatingWebhookFluent fluent) {
      this(fluent, new ValidatingWebhook());
   }

   public ValidatingWebhookBuilder(ValidatingWebhookFluent fluent, ValidatingWebhook instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ValidatingWebhookBuilder(ValidatingWebhook instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ValidatingWebhook build() {
      ValidatingWebhook buildable = new ValidatingWebhook(this.fluent.getAdmissionReviewVersions(), this.fluent.buildClientConfig(), this.fluent.getFailurePolicy(), this.fluent.buildMatchConditions(), this.fluent.getMatchPolicy(), this.fluent.getName(), this.fluent.buildNamespaceSelector(), this.fluent.buildObjectSelector(), this.fluent.buildRules(), this.fluent.getSideEffects(), this.fluent.getTimeoutSeconds());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
