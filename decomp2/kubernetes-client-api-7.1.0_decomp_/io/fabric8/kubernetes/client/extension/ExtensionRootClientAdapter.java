package io.fabric8.kubernetes.client.extension;

import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

public abstract class ExtensionRootClientAdapter extends ClientAdapter {
   protected ExtensionRootClientAdapter() {
      this((Client)(new KubernetesClientBuilder()).build());
   }

   protected ExtensionRootClientAdapter(Config configuration) {
      this((Client)(new KubernetesClientBuilder()).withConfig(configuration).build());
   }

   protected ExtensionRootClientAdapter(Client client) {
      this.init(client);
   }

   public final ClientAdapter newInstance() {
      return this.newInstance(this.getClient());
   }

   protected abstract ClientAdapter newInstance(Client var1);
}
