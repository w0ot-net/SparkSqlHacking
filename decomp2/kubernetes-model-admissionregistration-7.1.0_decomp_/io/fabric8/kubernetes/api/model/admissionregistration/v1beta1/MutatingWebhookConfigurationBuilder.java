package io.fabric8.kubernetes.api.model.admissionregistration.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MutatingWebhookConfigurationBuilder extends MutatingWebhookConfigurationFluent implements VisitableBuilder {
   MutatingWebhookConfigurationFluent fluent;

   public MutatingWebhookConfigurationBuilder() {
      this(new MutatingWebhookConfiguration());
   }

   public MutatingWebhookConfigurationBuilder(MutatingWebhookConfigurationFluent fluent) {
      this(fluent, new MutatingWebhookConfiguration());
   }

   public MutatingWebhookConfigurationBuilder(MutatingWebhookConfigurationFluent fluent, MutatingWebhookConfiguration instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MutatingWebhookConfigurationBuilder(MutatingWebhookConfiguration instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MutatingWebhookConfiguration build() {
      MutatingWebhookConfiguration buildable = new MutatingWebhookConfiguration(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildWebhooks());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
