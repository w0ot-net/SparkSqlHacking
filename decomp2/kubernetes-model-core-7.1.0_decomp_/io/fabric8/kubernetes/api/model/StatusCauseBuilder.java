package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class StatusCauseBuilder extends StatusCauseFluent implements VisitableBuilder {
   StatusCauseFluent fluent;

   public StatusCauseBuilder() {
      this(new StatusCause());
   }

   public StatusCauseBuilder(StatusCauseFluent fluent) {
      this(fluent, new StatusCause());
   }

   public StatusCauseBuilder(StatusCauseFluent fluent, StatusCause instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public StatusCauseBuilder(StatusCause instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public StatusCause build() {
      StatusCause buildable = new StatusCause(this.fluent.getField(), this.fluent.getMessage(), this.fluent.getReason());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
