package io.fabric8.kubernetes.client.informers.impl;

import io.fabric8.kubernetes.api.model.KubernetesResourceList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.dsl.Informable;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.client.informers.SharedIndexInformer;
import io.fabric8.kubernetes.client.informers.SharedInformerEventListener;
import io.fabric8.kubernetes.client.informers.SharedInformerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SharedInformerFactoryImpl implements SharedInformerFactory {
   private static final Logger log = LoggerFactory.getLogger(SharedInformerFactoryImpl.class);
   private final List informers = new ArrayList();
   private final ConcurrentLinkedQueue eventListeners = new ConcurrentLinkedQueue();
   private String name;
   private String namespace;
   private final KubernetesClient client;

   public SharedInformerFactoryImpl(KubernetesClient client) {
      this.client = client;
   }

   public SharedInformerFactory inNamespace(String namespace) {
      this.namespace = namespace;
      return this;
   }

   public SharedInformerFactory withName(String name) {
      this.name = name;
      return this;
   }

   public synchronized SharedIndexInformer sharedIndexInformerFor(Class apiTypeClass, long resyncPeriodInMillis) {
      MixedOperation<T, KubernetesResourceList<T>, Resource<T>> resources = this.client.resources(apiTypeClass);
      Informable<T> informable = null;
      if (this.namespace != null) {
         NonNamespaceOperation<T, KubernetesResourceList<T>, Resource<T>> nonNamespaceOp = (NonNamespaceOperation)resources.inNamespace(this.namespace);
         informable = nonNamespaceOp;
         if (this.name != null) {
            informable = (Informable)nonNamespaceOp.withName(this.name);
         }
      } else if (this.name != null) {
         informable = (Informable)resources.withName(this.name);
      } else {
         informable = (Informable)resources.inAnyNamespace();
      }

      SharedIndexInformer<T> informer = informable.runnableInformer(resyncPeriodInMillis);
      this.informers.add(informer);
      return informer;
   }

   public synchronized SharedIndexInformer getExistingSharedIndexInformer(Class apiTypeClass) {
      for(SharedIndexInformer informer : this.informers) {
         if (informer.getApiTypeClass().equals(apiTypeClass)) {
            return informer;
         }
      }

      return null;
   }

   public synchronized Future startAllRegisteredInformers() {
      List<CompletableFuture<Void>> startInformerTasks = new ArrayList();
      if (!this.informers.isEmpty()) {
         for(SharedIndexInformer informer : this.informers) {
            CompletableFuture<Void> future = informer.start().toCompletableFuture();
            startInformerTasks.add(future);
            future.whenComplete((v, t) -> {
               if (t != null) {
                  if (this.eventListeners.isEmpty()) {
                     log.warn("Failed to start informer {}", informer, t);
                  } else {
                     this.eventListeners.forEach((listener) -> listener.onException(informer, KubernetesClientException.launderThrowable(t)));
                  }
               }

            });
         }
      }

      return CompletableFuture.allOf((CompletableFuture[])startInformerTasks.toArray(new CompletableFuture[0]));
   }

   public synchronized void stopAllRegisteredInformers() {
      this.informers.forEach(SharedIndexInformer::stop);
   }

   public void addSharedInformerEventListener(SharedInformerEventListener event) {
      this.eventListeners.add(event);
   }
}
