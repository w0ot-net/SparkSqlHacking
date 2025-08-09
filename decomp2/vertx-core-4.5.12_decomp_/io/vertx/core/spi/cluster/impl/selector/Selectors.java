package io.vertx.core.spi.cluster.impl.selector;

import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.core.spi.cluster.RegistrationInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;

public class Selectors {
   private final ConcurrentMap map = new ConcurrentHashMap(0);
   private final ClusterManager clusterManager;

   public Selectors(ClusterManager clusterManager) {
      this.clusterManager = clusterManager;
   }

   public void withSelector(Message message, Promise promise, BiConsumer task) {
      String address = message.address();
      SelectorEntry entry = (SelectorEntry)this.map.compute(address, (addr, curr) -> curr == null ? new SelectorEntry() : (curr.isNotReady() ? curr.increment() : curr));
      if (entry.isNotReady()) {
         if (entry.shouldInitialize()) {
            this.initialize(address);
         }

         entry.selectorPromise.future().onComplete((ar) -> {
            if (ar.succeeded()) {
               task.accept(promise, ar.result());
            } else {
               promise.fail(ar.cause());
            }

         });
      } else {
         task.accept(promise, entry.selector);
      }

   }

   private void initialize(String address) {
      Promise<List<RegistrationInfo>> getPromise = Promise.promise();
      this.clusterManager.getRegistrations(address, getPromise);
      getPromise.future().onComplete((ar) -> {
         if (ar.succeeded()) {
            this.dataReceived(address, (List)ar.result(), false);
         } else {
            SelectorEntry entry = (SelectorEntry)this.map.remove(address);
            if (entry != null && entry.isNotReady()) {
               entry.selectorPromise.fail(ar.cause());
            }
         }

      });
   }

   public void dataReceived(String address, List registrations, boolean isUpdate) {
      List<String> accessible = this.computeAccessible(registrations);

      while(true) {
         SelectorEntry previous = (SelectorEntry)this.map.get(address);
         if (previous == null || isUpdate && previous.isNotReady()) {
            break;
         }

         SelectorEntry next = previous.data(accessible);
         if (next == null) {
            if (this.map.remove(address, previous)) {
               if (previous.isNotReady()) {
                  previous.selectorPromise.complete(NullRoundRobinSelector.INSTANCE);
               }
               break;
            }
         } else if (this.map.replace(address, previous, next)) {
            if (previous.isNotReady()) {
               previous.selectorPromise.complete(next.selector);
            }
            break;
         }
      }

   }

   private List computeAccessible(List registrations) {
      if (registrations != null && !registrations.isEmpty()) {
         ArrayList<String> list = new ArrayList(registrations.size());

         for(RegistrationInfo registration : registrations) {
            if (this.isAccessible(registration)) {
               String nodeId = registration.nodeId();
               list.add(nodeId);
            }
         }

         list.trimToSize();
         return list;
      } else {
         return Collections.emptyList();
      }
   }

   private boolean isAccessible(RegistrationInfo registrationInfo) {
      return !registrationInfo.localOnly() || this.clusterManager.getNodeId().equals(registrationInfo.nodeId());
   }

   public void dataLost() {
      for(String address : this.map.keySet()) {
         SelectorEntry entry = (SelectorEntry)this.map.remove(address);
         if (entry.isNotReady()) {
            entry.selectorPromise.complete(NullRoundRobinSelector.INSTANCE);
         }
      }

   }

   public boolean hasEntryFor(String address) {
      return this.map.containsKey(address);
   }
}
