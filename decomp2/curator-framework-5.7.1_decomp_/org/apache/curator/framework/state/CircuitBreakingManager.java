package org.apache.curator.framework.state;

import java.util.concurrent.Executor;
import java.util.function.Consumer;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.listen.StandardListenerManager;
import org.apache.curator.framework.listen.UnaryListenerManager;

class CircuitBreakingManager implements UnaryListenerManager {
   private final StandardListenerManager mainContainer = StandardListenerManager.standard();
   private final StandardListenerManager doNotProxyContainer = StandardListenerManager.standard();
   private final CircuitBreakingConnectionStateListener masterListener;

   CircuitBreakingManager(CuratorFramework client, CircuitBreaker circuitBreaker) {
      ConnectionStateListener masterStateChanged = (__, newState) -> this.mainContainer.forEach((listener) -> listener.stateChanged(client, newState));
      this.masterListener = new CircuitBreakingConnectionStateListener(client, masterStateChanged, circuitBreaker);
   }

   public void clear() {
      this.doNotProxyContainer.clear();
      this.mainContainer.clear();
   }

   public int size() {
      return this.mainContainer.size() + this.doNotProxyContainer.size();
   }

   public void forEach(Consumer function) {
      this.doNotProxyContainer.forEach(function);
      function.accept(this.masterListener);
   }

   public void addListener(ConnectionStateListener listener) {
      if (listener.doNotProxy()) {
         this.doNotProxyContainer.addListener(listener);
      } else {
         this.mainContainer.addListener(listener);
      }

   }

   public void addListener(ConnectionStateListener listener, Executor executor) {
      if (listener.doNotProxy()) {
         this.doNotProxyContainer.addListener(listener, executor);
      } else {
         this.mainContainer.addListener(listener, executor);
      }

   }

   public void removeListener(ConnectionStateListener listener) {
      this.mainContainer.removeListener(listener);
      this.doNotProxyContainer.removeListener(listener);
   }
}
