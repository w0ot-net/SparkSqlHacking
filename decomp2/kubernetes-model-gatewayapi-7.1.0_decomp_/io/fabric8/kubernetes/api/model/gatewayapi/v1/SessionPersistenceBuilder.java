package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SessionPersistenceBuilder extends SessionPersistenceFluent implements VisitableBuilder {
   SessionPersistenceFluent fluent;

   public SessionPersistenceBuilder() {
      this(new SessionPersistence());
   }

   public SessionPersistenceBuilder(SessionPersistenceFluent fluent) {
      this(fluent, new SessionPersistence());
   }

   public SessionPersistenceBuilder(SessionPersistenceFluent fluent, SessionPersistence instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SessionPersistenceBuilder(SessionPersistence instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SessionPersistence build() {
      SessionPersistence buildable = new SessionPersistence(this.fluent.getAbsoluteTimeout(), this.fluent.buildCookieConfig(), this.fluent.getIdleTimeout(), this.fluent.getSessionName(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
