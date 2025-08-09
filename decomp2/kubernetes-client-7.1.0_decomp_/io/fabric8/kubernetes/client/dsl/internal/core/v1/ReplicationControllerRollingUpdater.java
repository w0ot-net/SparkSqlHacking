package io.fabric8.kubernetes.client.dsl.internal.core.v1;

import io.fabric8.kubernetes.api.model.PodTemplateSpecFluent;
import io.fabric8.kubernetes.api.model.ReplicationController;
import io.fabric8.kubernetes.api.model.ReplicationControllerBuilder;
import io.fabric8.kubernetes.api.model.ReplicationControllerFluent;
import io.fabric8.kubernetes.api.model.ReplicationControllerSpecFluent;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.dsl.FilterWatchListDeletable;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.RollableScalableResource;
import io.fabric8.kubernetes.client.dsl.internal.apps.v1.RollingUpdater;

class ReplicationControllerRollingUpdater extends RollingUpdater {
   ReplicationControllerRollingUpdater(Client client, String namespace, long rollingTimeoutMillis, long loggingIntervalMillis) {
      super(client, namespace, rollingTimeoutMillis, loggingIntervalMillis);
   }

   protected ReplicationController createClone(ReplicationController obj, String newName, String newDeploymentHash) {
      return ((ReplicationControllerBuilder)((ReplicationControllerFluent.SpecNested)((ReplicationControllerSpecFluent.TemplateNested)((PodTemplateSpecFluent.MetadataNested)((ReplicationControllerFluent.SpecNested)((ReplicationControllerFluent.SpecNested)((ReplicationControllerBuilder)((ReplicationControllerFluent.MetadataNested)((ReplicationControllerFluent.MetadataNested)(new ReplicationControllerBuilder(obj)).editMetadata().withResourceVersion((String)null)).withName(newName)).endMetadata()).editSpec().withReplicas(0)).addToSelector("deployment", newDeploymentHash)).editTemplate().editMetadata().addToLabels("deployment", newDeploymentHash)).endMetadata()).endTemplate()).endSpec()).build();
   }

   protected FilterWatchListDeletable selectedPodLister(ReplicationController obj) {
      return (FilterWatchListDeletable)((NonNamespaceOperation)this.pods().inNamespace(this.namespace)).withLabels(obj.getSpec().getSelector());
   }

   protected ReplicationController updateDeploymentKey(String name, String hash) {
      ReplicationController old = (ReplicationController)((RollableScalableResource)((NonNamespaceOperation)this.resources().inNamespace(this.namespace)).withName(name)).get();
      ReplicationController updated = ((ReplicationControllerBuilder)((ReplicationControllerFluent.SpecNested)((ReplicationControllerSpecFluent.TemplateNested)((PodTemplateSpecFluent.MetadataNested)((ReplicationControllerFluent.SpecNested)(new ReplicationControllerBuilder(old)).editSpec().addToSelector("deployment", hash)).editTemplate().editMetadata().addToLabels("deployment", hash)).endMetadata()).endTemplate()).endSpec()).build();
      return (ReplicationController)((RollableScalableResource)((NonNamespaceOperation)this.resources().inNamespace(this.namespace)).withName(name)).replace(updated);
   }

   protected ReplicationController removeDeploymentKey(String name) {
      ReplicationController old = (ReplicationController)((RollableScalableResource)((NonNamespaceOperation)this.resources().inNamespace(this.namespace)).withName(name)).get();
      ReplicationController updated = ((ReplicationControllerBuilder)((ReplicationControllerFluent.SpecNested)((ReplicationControllerSpecFluent.TemplateNested)((PodTemplateSpecFluent.MetadataNested)((ReplicationControllerFluent.SpecNested)(new ReplicationControllerBuilder(old)).editSpec().removeFromSelector("deployment")).editTemplate().editMetadata().removeFromLabels("deployment")).endMetadata()).endTemplate()).endSpec()).build();
      return (ReplicationController)((RollableScalableResource)((NonNamespaceOperation)this.resources().inNamespace(this.namespace)).withName(name)).replace(updated);
   }

   protected int getReplicas(ReplicationController obj) {
      return obj.getSpec().getReplicas();
   }

   protected ReplicationController setReplicas(ReplicationController obj, int replicas) {
      return ((ReplicationControllerBuilder)((ReplicationControllerFluent.SpecNested)(new ReplicationControllerBuilder(obj)).editSpec().withReplicas(replicas)).endSpec()).build();
   }

   protected MixedOperation resources() {
      return new ReplicationControllerOperationsImpl(this.client);
   }
}
