package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class KubernetesListBuilder extends KubernetesListFluent implements VisitableBuilder {
   KubernetesListFluent fluent;

   public KubernetesListBuilder() {
      this(new KubernetesList());
   }

   public KubernetesListBuilder(KubernetesListFluent fluent) {
      this(fluent, new KubernetesList());
   }

   public KubernetesListBuilder(KubernetesListFluent fluent, KubernetesList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public KubernetesListBuilder(KubernetesList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public KubernetesList build() {
      KubernetesList buildable = new KubernetesList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.buildMetadata());
      return buildable;
   }
}
