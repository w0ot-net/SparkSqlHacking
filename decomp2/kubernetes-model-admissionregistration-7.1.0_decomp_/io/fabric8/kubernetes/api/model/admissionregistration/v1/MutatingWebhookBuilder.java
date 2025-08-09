package io.fabric8.kubernetes.api.model.admissionregistration.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MutatingWebhookBuilder extends MutatingWebhookFluent implements VisitableBuilder {
   MutatingWebhookFluent fluent;

   public MutatingWebhookBuilder() {
      this(new MutatingWebhook());
   }

   public MutatingWebhookBuilder(MutatingWebhookFluent fluent) {
      this(fluent, new MutatingWebhook());
   }

   public MutatingWebhookBuilder(MutatingWebhookFluent fluent, MutatingWebhook instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MutatingWebhookBuilder(MutatingWebhook instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MutatingWebhook build() {
      MutatingWebhook buildable = new MutatingWebhook(this.fluent.getAdmissionReviewVersions(), this.fluent.buildClientConfig(), this.fluent.getFailurePolicy(), this.fluent.buildMatchConditions(), this.fluent.getMatchPolicy(), this.fluent.getName(), this.fluent.buildNamespaceSelector(), this.fluent.buildObjectSelector(), this.fluent.getReinvocationPolicy(), this.fluent.buildRules(), this.fluent.getSideEffects(), this.fluent.getTimeoutSeconds());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
