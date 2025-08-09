package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SessionAffinityConfigBuilder extends SessionAffinityConfigFluent implements VisitableBuilder {
   SessionAffinityConfigFluent fluent;

   public SessionAffinityConfigBuilder() {
      this(new SessionAffinityConfig());
   }

   public SessionAffinityConfigBuilder(SessionAffinityConfigFluent fluent) {
      this(fluent, new SessionAffinityConfig());
   }

   public SessionAffinityConfigBuilder(SessionAffinityConfigFluent fluent, SessionAffinityConfig instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SessionAffinityConfigBuilder(SessionAffinityConfig instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SessionAffinityConfig build() {
      SessionAffinityConfig buildable = new SessionAffinityConfig(this.fluent.buildClientIP());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
