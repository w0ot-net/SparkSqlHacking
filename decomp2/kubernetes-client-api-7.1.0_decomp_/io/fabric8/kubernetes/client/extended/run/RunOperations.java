package io.fabric8.kubernetes.client.extended.run;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.NamespacedKubernetesClient;
import io.fabric8.kubernetes.client.dsl.PodResource;

public class RunOperations {
   private final KubernetesClient client;
   private final RunConfig runConfig;

   public RunOperations(KubernetesClient client, RunConfig runConfig) {
      this.client = client;
      this.runConfig = runConfig;
   }

   public RunOperations inNamespace(String namespace) {
      return new RunOperations(((NamespacedKubernetesClient)this.client.adapt(NamespacedKubernetesClient.class)).inNamespace(namespace), this.runConfig);
   }

   public RunOperations withImage(String image) {
      return new RunOperations(this.client, ((RunConfigBuilder)(new RunConfigBuilder(this.runConfig)).withImage(image)).build());
   }

   public RunOperations withName(String name) {
      return new RunOperations(this.client, ((RunConfigBuilder)(new RunConfigBuilder(this.runConfig)).withName(name)).build());
   }

   /** @deprecated */
   @Deprecated
   public RunOperations withRunConfig(RunConfig generatorRunConfig) {
      return new RunOperations(this.client, (new RunConfigBuilder(generatorRunConfig)).build());
   }

   public RunConfigNested withNewRunConfig() {
      return new RunConfigNested();
   }

   public Pod done() {
      return (Pod)((PodResource)this.client.pods().resource(this.convertRunConfigIntoPod())).create();
   }

   Pod convertRunConfigIntoPod() {
      return ((PodBuilder)((PodBuilder)(new PodBuilder()).withMetadata(RunConfigUtil.getObjectMetadataFromRunConfig(this.runConfig))).withSpec(RunConfigUtil.getPodSpecFromRunConfig(this.runConfig))).build();
   }

   public class RunConfigNested extends RunConfigFluent {
      RunConfigNested() {
         super(RunOperations.this.runConfig);
      }

      public Pod done() {
         RunConfig runConfig = (new RunConfigBuilder(this)).build();
         return RunOperations.this.withRunConfig(runConfig).done();
      }
   }
}
