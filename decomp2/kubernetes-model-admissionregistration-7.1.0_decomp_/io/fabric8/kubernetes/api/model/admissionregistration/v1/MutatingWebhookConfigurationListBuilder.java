package io.fabric8.kubernetes.api.model.admissionregistration.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MutatingWebhookConfigurationListBuilder extends MutatingWebhookConfigurationListFluent implements VisitableBuilder {
   MutatingWebhookConfigurationListFluent fluent;

   public MutatingWebhookConfigurationListBuilder() {
      this(new MutatingWebhookConfigurationList());
   }

   public MutatingWebhookConfigurationListBuilder(MutatingWebhookConfigurationListFluent fluent) {
      this(fluent, new MutatingWebhookConfigurationList());
   }

   public MutatingWebhookConfigurationListBuilder(MutatingWebhookConfigurationListFluent fluent, MutatingWebhookConfigurationList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MutatingWebhookConfigurationListBuilder(MutatingWebhookConfigurationList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MutatingWebhookConfigurationList build() {
      MutatingWebhookConfigurationList buildable = new MutatingWebhookConfigurationList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
