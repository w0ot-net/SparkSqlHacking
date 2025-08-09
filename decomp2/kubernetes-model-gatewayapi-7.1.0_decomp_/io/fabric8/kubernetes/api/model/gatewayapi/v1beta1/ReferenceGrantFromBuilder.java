package io.fabric8.kubernetes.api.model.gatewayapi.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ReferenceGrantFromBuilder extends ReferenceGrantFromFluent implements VisitableBuilder {
   ReferenceGrantFromFluent fluent;

   public ReferenceGrantFromBuilder() {
      this(new ReferenceGrantFrom());
   }

   public ReferenceGrantFromBuilder(ReferenceGrantFromFluent fluent) {
      this(fluent, new ReferenceGrantFrom());
   }

   public ReferenceGrantFromBuilder(ReferenceGrantFromFluent fluent, ReferenceGrantFrom instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ReferenceGrantFromBuilder(ReferenceGrantFrom instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ReferenceGrantFrom build() {
      ReferenceGrantFrom buildable = new ReferenceGrantFrom(this.fluent.getGroup(), this.fluent.getKind(), this.fluent.getNamespace());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
