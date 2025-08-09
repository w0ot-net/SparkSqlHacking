package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ClientIPConfigBuilder extends ClientIPConfigFluent implements VisitableBuilder {
   ClientIPConfigFluent fluent;

   public ClientIPConfigBuilder() {
      this(new ClientIPConfig());
   }

   public ClientIPConfigBuilder(ClientIPConfigFluent fluent) {
      this(fluent, new ClientIPConfig());
   }

   public ClientIPConfigBuilder(ClientIPConfigFluent fluent, ClientIPConfig instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ClientIPConfigBuilder(ClientIPConfig instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ClientIPConfig build() {
      ClientIPConfig buildable = new ClientIPConfig(this.fluent.getTimeoutSeconds());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
