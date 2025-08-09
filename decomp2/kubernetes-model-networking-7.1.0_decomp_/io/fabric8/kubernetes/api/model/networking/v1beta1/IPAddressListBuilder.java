package io.fabric8.kubernetes.api.model.networking.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class IPAddressListBuilder extends IPAddressListFluent implements VisitableBuilder {
   IPAddressListFluent fluent;

   public IPAddressListBuilder() {
      this(new IPAddressList());
   }

   public IPAddressListBuilder(IPAddressListFluent fluent) {
      this(fluent, new IPAddressList());
   }

   public IPAddressListBuilder(IPAddressListFluent fluent, IPAddressList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public IPAddressListBuilder(IPAddressList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public IPAddressList build() {
      IPAddressList buildable = new IPAddressList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
