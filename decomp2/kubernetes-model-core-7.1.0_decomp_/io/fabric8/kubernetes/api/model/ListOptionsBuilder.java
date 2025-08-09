package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ListOptionsBuilder extends ListOptionsFluent implements VisitableBuilder {
   ListOptionsFluent fluent;

   public ListOptionsBuilder() {
      this(new ListOptions());
   }

   public ListOptionsBuilder(ListOptionsFluent fluent) {
      this(fluent, new ListOptions());
   }

   public ListOptionsBuilder(ListOptionsFluent fluent, ListOptions instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ListOptionsBuilder(ListOptions instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ListOptions build() {
      ListOptions buildable = new ListOptions(this.fluent.getAllowWatchBookmarks(), this.fluent.getApiVersion(), this.fluent.getContinue(), this.fluent.getFieldSelector(), this.fluent.getKind(), this.fluent.getLabelSelector(), this.fluent.getLimit(), this.fluent.getResourceVersion(), this.fluent.getResourceVersionMatch(), this.fluent.getSendInitialEvents(), this.fluent.getTimeoutSeconds(), this.fluent.getWatch());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
