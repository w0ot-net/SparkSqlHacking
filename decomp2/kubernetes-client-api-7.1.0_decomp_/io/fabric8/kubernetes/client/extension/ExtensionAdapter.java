package io.fabric8.kubernetes.client.extension;

import io.fabric8.kubernetes.client.Client;

public interface ExtensionAdapter {
   Class getExtensionType();

   Client adapt(Client var1);

   default void registerResources(ResourceFactory factory) {
   }

   default void registerClients(ClientFactory factory) {
   }

   public interface ClientFactory {
      void register(Class var1, ClientAdapter var2);
   }

   public interface ResourceFactory {
      void register(Class var1, ExtensibleResourceAdapter var2);
   }
}
