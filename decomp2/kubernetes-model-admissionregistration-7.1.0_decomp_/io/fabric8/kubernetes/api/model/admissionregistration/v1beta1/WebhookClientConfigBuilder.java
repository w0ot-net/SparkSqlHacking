package io.fabric8.kubernetes.api.model.admissionregistration.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class WebhookClientConfigBuilder extends WebhookClientConfigFluent implements VisitableBuilder {
   WebhookClientConfigFluent fluent;

   public WebhookClientConfigBuilder() {
      this(new WebhookClientConfig());
   }

   public WebhookClientConfigBuilder(WebhookClientConfigFluent fluent) {
      this(fluent, new WebhookClientConfig());
   }

   public WebhookClientConfigBuilder(WebhookClientConfigFluent fluent, WebhookClientConfig instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public WebhookClientConfigBuilder(WebhookClientConfig instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public WebhookClientConfig build() {
      WebhookClientConfig buildable = new WebhookClientConfig(this.fluent.getCaBundle(), this.fluent.buildService(), this.fluent.getUrl());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
