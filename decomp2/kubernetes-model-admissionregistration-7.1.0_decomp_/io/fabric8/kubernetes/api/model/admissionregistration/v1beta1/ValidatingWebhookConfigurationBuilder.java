package io.fabric8.kubernetes.api.model.admissionregistration.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ValidatingWebhookConfigurationBuilder extends ValidatingWebhookConfigurationFluent implements VisitableBuilder {
   ValidatingWebhookConfigurationFluent fluent;

   public ValidatingWebhookConfigurationBuilder() {
      this(new ValidatingWebhookConfiguration());
   }

   public ValidatingWebhookConfigurationBuilder(ValidatingWebhookConfigurationFluent fluent) {
      this(fluent, new ValidatingWebhookConfiguration());
   }

   public ValidatingWebhookConfigurationBuilder(ValidatingWebhookConfigurationFluent fluent, ValidatingWebhookConfiguration instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ValidatingWebhookConfigurationBuilder(ValidatingWebhookConfiguration instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ValidatingWebhookConfiguration build() {
      ValidatingWebhookConfiguration buildable = new ValidatingWebhookConfiguration(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildWebhooks());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
