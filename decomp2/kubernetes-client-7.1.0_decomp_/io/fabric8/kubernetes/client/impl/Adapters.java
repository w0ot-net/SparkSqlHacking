package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.KubernetesResourceList;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.dsl.base.ResourceDefinitionContext;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperationsImpl;
import io.fabric8.kubernetes.client.extension.ClientAdapter;
import io.fabric8.kubernetes.client.extension.ExtensibleResourceAdapter;
import io.fabric8.kubernetes.client.extension.ExtensionAdapter;
import io.fabric8.kubernetes.client.utils.KubernetesResourceUtil;
import java.util.Collections;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class Adapters {
   private final Set classLoaders = Collections.newSetFromMap(new ConcurrentHashMap());
   private final Map extensionAdapters = new ConcurrentHashMap();
   private final Handlers handlers;

   public Adapters(Handlers handlers) {
      this.handlers = handlers;
      this.discoverServices(Adapters.class.getClassLoader());
      this.discoverServices(Thread.currentThread().getContextClassLoader());
   }

   public void registerClient(final Class type, final ClientAdapter target) {
      if (!type.isAssignableFrom(target.getClass())) {
         throw new IllegalArgumentException("The adapter should implement the type");
      } else if (target.getClient() != null) {
         throw new IllegalArgumentException("The client adapter should already be initialized");
      } else {
         ExtensionAdapter<C> adapter = new ExtensionAdapter() {
            public Class getExtensionType() {
               return type;
            }

            public ClientAdapter adapt(Client client) {
               C result = (C)target.newInstance();
               result.init(client);
               return result;
            }
         };
         this.extensionAdapters.put(type, adapter);
         this.extensionAdapters.put(target.getClass(), adapter);
      }
   }

   public void register(ExtensionAdapter adapter) {
      if (this.extensionAdapters.putIfAbsent(adapter.getExtensionType(), adapter) == null) {
         adapter.registerResources(this::registerResource);
         adapter.registerClients(this::registerClient);
         if (adapter instanceof InternalExtensionAdapter) {
            ((InternalExtensionAdapter)adapter).registerHandlers(this.handlers);
         }
      }

   }

   public void unregister(ExtensionAdapter adapter) {
      this.extensionAdapters.remove(adapter.getExtensionType());
   }

   public ExtensionAdapter get(Class type) {
      this.discoverServices(type.getClassLoader());
      return (ExtensionAdapter)this.extensionAdapters.get(type);
   }

   private void discoverServices(ClassLoader classLoader) {
      if (classLoader != null && this.classLoaders.add(classLoader)) {
         for(ExtensionAdapter adapter : ServiceLoader.load(ExtensionAdapter.class, classLoader)) {
            this.register(adapter);
         }
      }

   }

   public void registerResource(Class type, ExtensibleResourceAdapter target) {
      ResourceDefinitionContext definitionContest = ResourceDefinitionContext.fromResourceType(type);
      Class<? extends KubernetesResourceList> listType = KubernetesResourceUtil.inferListType(type);
      this.handlers.register(type, (c) -> new ResourcedHasMetadataOperation(HasMetadataOperationsImpl.defaultContext(c), definitionContest, type, listType, target));
   }
}
