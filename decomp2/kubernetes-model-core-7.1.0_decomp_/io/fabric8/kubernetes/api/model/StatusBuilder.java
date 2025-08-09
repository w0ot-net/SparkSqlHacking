package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class StatusBuilder extends StatusFluent implements VisitableBuilder {
   StatusFluent fluent;

   public StatusBuilder() {
      this(new Status());
   }

   public StatusBuilder(StatusFluent fluent) {
      this(fluent, new Status());
   }

   public StatusBuilder(StatusFluent fluent, Status instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public StatusBuilder(Status instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Status build() {
      Status buildable = new Status(this.fluent.getApiVersion(), this.fluent.getCode(), this.fluent.buildDetails(), this.fluent.getKind(), this.fluent.getMessage(), this.fluent.buildMetadata(), this.fluent.getReason(), this.fluent.getStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
