package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CookieConfigBuilder extends CookieConfigFluent implements VisitableBuilder {
   CookieConfigFluent fluent;

   public CookieConfigBuilder() {
      this(new CookieConfig());
   }

   public CookieConfigBuilder(CookieConfigFluent fluent) {
      this(fluent, new CookieConfig());
   }

   public CookieConfigBuilder(CookieConfigFluent fluent, CookieConfig instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CookieConfigBuilder(CookieConfig instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CookieConfig build() {
      CookieConfig buildable = new CookieConfig(this.fluent.getLifetimeType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
