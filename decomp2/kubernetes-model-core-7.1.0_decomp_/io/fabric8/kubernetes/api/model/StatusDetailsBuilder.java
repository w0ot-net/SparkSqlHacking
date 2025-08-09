package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class StatusDetailsBuilder extends StatusDetailsFluent implements VisitableBuilder {
   StatusDetailsFluent fluent;

   public StatusDetailsBuilder() {
      this(new StatusDetails());
   }

   public StatusDetailsBuilder(StatusDetailsFluent fluent) {
      this(fluent, new StatusDetails());
   }

   public StatusDetailsBuilder(StatusDetailsFluent fluent, StatusDetails instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public StatusDetailsBuilder(StatusDetails instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public StatusDetails build() {
      StatusDetails buildable = new StatusDetails(this.fluent.buildCauses(), this.fluent.getGroup(), this.fluent.getKind(), this.fluent.getName(), this.fluent.getRetryAfterSeconds(), this.fluent.getUid());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
