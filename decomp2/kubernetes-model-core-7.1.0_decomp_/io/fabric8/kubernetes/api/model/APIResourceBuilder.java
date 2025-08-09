package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class APIResourceBuilder extends APIResourceFluent implements VisitableBuilder {
   APIResourceFluent fluent;

   public APIResourceBuilder() {
      this(new APIResource());
   }

   public APIResourceBuilder(APIResourceFluent fluent) {
      this(fluent, new APIResource());
   }

   public APIResourceBuilder(APIResourceFluent fluent, APIResource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public APIResourceBuilder(APIResource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public APIResource build() {
      APIResource buildable = new APIResource(this.fluent.getCategories(), this.fluent.getGroup(), this.fluent.getKind(), this.fluent.getName(), this.fluent.getNamespaced(), this.fluent.getShortNames(), this.fluent.getSingularName(), this.fluent.getStorageVersionHash(), this.fluent.getVerbs(), this.fluent.getVersion());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
