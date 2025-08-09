package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class WebhookConversionBuilder extends WebhookConversionFluent implements VisitableBuilder {
   WebhookConversionFluent fluent;

   public WebhookConversionBuilder() {
      this(new WebhookConversion());
   }

   public WebhookConversionBuilder(WebhookConversionFluent fluent) {
      this(fluent, new WebhookConversion());
   }

   public WebhookConversionBuilder(WebhookConversionFluent fluent, WebhookConversion instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public WebhookConversionBuilder(WebhookConversion instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public WebhookConversion build() {
      WebhookConversion buildable = new WebhookConversion(this.fluent.buildClientConfig(), this.fluent.getConversionReviewVersions());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
