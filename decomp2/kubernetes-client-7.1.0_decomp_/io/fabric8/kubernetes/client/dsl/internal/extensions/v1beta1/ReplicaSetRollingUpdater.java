package io.fabric8.kubernetes.client.dsl.internal.extensions.v1beta1;

import io.fabric8.kubernetes.api.model.LabelSelector;
import io.fabric8.kubernetes.api.model.PodTemplateSpecFluent;
import io.fabric8.kubernetes.api.model.extensions.ReplicaSet;
import io.fabric8.kubernetes.api.model.extensions.ReplicaSetBuilder;
import io.fabric8.kubernetes.api.model.extensions.ReplicaSetFluent;
import io.fabric8.kubernetes.api.model.extensions.ReplicaSetSpecFluent;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.dsl.FilterWatchListDeletable;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.RollableScalableResource;
import io.fabric8.kubernetes.client.dsl.internal.apps.v1.RollingUpdater;

class ReplicaSetRollingUpdater extends RollingUpdater {
   ReplicaSetRollingUpdater(Client client, String namespace, long rollingTimeoutMillis, long loggingIntervalMillis) {
      super(client, namespace, rollingTimeoutMillis, loggingIntervalMillis);
   }

   protected ReplicaSet createClone(ReplicaSet obj, String newName, String newDeploymentHash) {
      return ((ReplicaSetBuilder)((ReplicaSetFluent.SpecNested)((ReplicaSetSpecFluent.TemplateNested)((PodTemplateSpecFluent.MetadataNested)((ReplicaSetFluent.SpecNested)((ReplicaSetSpecFluent.SelectorNested)((ReplicaSetFluent.SpecNested)((ReplicaSetBuilder)((ReplicaSetFluent.MetadataNested)((ReplicaSetFluent.MetadataNested)(new ReplicaSetBuilder(obj)).editMetadata().withResourceVersion((String)null)).withName(newName)).endMetadata()).editSpec().withReplicas(0)).editSelector().addToMatchLabels("deployment", newDeploymentHash)).endSelector()).editTemplate().editMetadata().addToLabels("deployment", newDeploymentHash)).endMetadata()).endTemplate()).endSpec()).build();
   }

   protected FilterWatchListDeletable selectedPodLister(ReplicaSet obj) {
      return this.selectedPodLister((LabelSelector)obj.getSpec().getSelector());
   }

   protected ReplicaSet updateDeploymentKey(String name, String hash) {
      return (ReplicaSet)((RollableScalableResource)((NonNamespaceOperation)this.resources().inNamespace(this.namespace)).withName(name)).edit((r) -> ((ReplicaSetBuilder)((ReplicaSetFluent.SpecNested)((ReplicaSetSpecFluent.TemplateNested)((PodTemplateSpecFluent.MetadataNested)((ReplicaSetFluent.SpecNested)((ReplicaSetSpecFluent.SelectorNested)(new ReplicaSetBuilder(r)).editSpec().editSelector().addToMatchLabels("deployment", hash)).endSelector()).editTemplate().editMetadata().addToLabels("deployment", hash)).endMetadata()).endTemplate()).endSpec()).build());
   }

   protected ReplicaSet removeDeploymentKey(String name) {
      return (ReplicaSet)((RollableScalableResource)((NonNamespaceOperation)this.resources().inNamespace(this.namespace)).withName(name)).edit((r) -> ((ReplicaSetBuilder)((ReplicaSetFluent.SpecNested)((ReplicaSetSpecFluent.TemplateNested)((PodTemplateSpecFluent.MetadataNested)((ReplicaSetFluent.SpecNested)((ReplicaSetSpecFluent.SelectorNested)(new ReplicaSetBuilder(r)).editSpec().editSelector().removeFromMatchLabels("deployment")).endSelector()).editTemplate().editMetadata().removeFromLabels("deployment")).endMetadata()).endTemplate()).endSpec()).build());
   }

   protected int getReplicas(ReplicaSet obj) {
      return obj.getSpec().getReplicas();
   }

   protected ReplicaSet setReplicas(ReplicaSet obj, int replicas) {
      return ((ReplicaSetBuilder)((ReplicaSetFluent.SpecNested)(new ReplicaSetBuilder(obj)).editSpec().withReplicas(replicas)).endSpec()).build();
   }

   protected MixedOperation resources() {
      return new ReplicaSetOperationsImpl(this.client);
   }
}
