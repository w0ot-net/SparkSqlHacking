package io.fabric8.kubernetes.api.model.admissionregistration.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ValidatingWebhookConfigurationListBuilder extends ValidatingWebhookConfigurationListFluent implements VisitableBuilder {
   ValidatingWebhookConfigurationListFluent fluent;

   public ValidatingWebhookConfigurationListBuilder() {
      this(new ValidatingWebhookConfigurationList());
   }

   public ValidatingWebhookConfigurationListBuilder(ValidatingWebhookConfigurationListFluent fluent) {
      this(fluent, new ValidatingWebhookConfigurationList());
   }

   public ValidatingWebhookConfigurationListBuilder(ValidatingWebhookConfigurationListFluent fluent, ValidatingWebhookConfigurationList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ValidatingWebhookConfigurationListBuilder(ValidatingWebhookConfigurationList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ValidatingWebhookConfigurationList build() {
      ValidatingWebhookConfigurationList buildable = new ValidatingWebhookConfigurationList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
