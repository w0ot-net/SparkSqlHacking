package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ExternalDocumentationBuilder extends ExternalDocumentationFluent implements VisitableBuilder {
   ExternalDocumentationFluent fluent;

   public ExternalDocumentationBuilder() {
      this(new ExternalDocumentation());
   }

   public ExternalDocumentationBuilder(ExternalDocumentationFluent fluent) {
      this(fluent, new ExternalDocumentation());
   }

   public ExternalDocumentationBuilder(ExternalDocumentationFluent fluent, ExternalDocumentation instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ExternalDocumentationBuilder(ExternalDocumentation instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ExternalDocumentation build() {
      ExternalDocumentation buildable = new ExternalDocumentation(this.fluent.getDescription(), this.fluent.getUrl());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
