package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GetOptionsBuilder extends GetOptionsFluent implements VisitableBuilder {
   GetOptionsFluent fluent;

   public GetOptionsBuilder() {
      this(new GetOptions());
   }

   public GetOptionsBuilder(GetOptionsFluent fluent) {
      this(fluent, new GetOptions());
   }

   public GetOptionsBuilder(GetOptionsFluent fluent, GetOptions instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GetOptionsBuilder(GetOptions instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GetOptions build() {
      GetOptions buildable = new GetOptions(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.getResourceVersion());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
