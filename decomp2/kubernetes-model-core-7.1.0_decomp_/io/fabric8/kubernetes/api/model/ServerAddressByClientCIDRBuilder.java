package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ServerAddressByClientCIDRBuilder extends ServerAddressByClientCIDRFluent implements VisitableBuilder {
   ServerAddressByClientCIDRFluent fluent;

   public ServerAddressByClientCIDRBuilder() {
      this(new ServerAddressByClientCIDR());
   }

   public ServerAddressByClientCIDRBuilder(ServerAddressByClientCIDRFluent fluent) {
      this(fluent, new ServerAddressByClientCIDR());
   }

   public ServerAddressByClientCIDRBuilder(ServerAddressByClientCIDRFluent fluent, ServerAddressByClientCIDR instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ServerAddressByClientCIDRBuilder(ServerAddressByClientCIDR instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ServerAddressByClientCIDR build() {
      ServerAddressByClientCIDR buildable = new ServerAddressByClientCIDR(this.fluent.getClientCIDR(), this.fluent.getServerAddress());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
