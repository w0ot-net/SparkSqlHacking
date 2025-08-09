package io.vertx.core.spi.cluster.impl;

import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.impl.Arguments;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.core.spi.cluster.NodeSelector;
import io.vertx.core.spi.cluster.RegistrationUpdateEvent;
import io.vertx.core.spi.cluster.impl.selector.Selectors;

public class DefaultNodeSelector implements NodeSelector {
   private Selectors selectors;

   public void init(Vertx vertx, ClusterManager clusterManager) {
      this.selectors = new Selectors(clusterManager);
   }

   public void eventBusStarted() {
   }

   public void selectForSend(Message message, Promise promise) {
      Arguments.require(message.isSend(), "selectForSend used for publishing");
      this.selectors.withSelector(message, promise, (prom, selector) -> prom.tryComplete(selector.selectForSend()));
   }

   public void selectForPublish(Message message, Promise promise) {
      Arguments.require(!message.isSend(), "selectForPublish used for sending");
      this.selectors.withSelector(message, promise, (prom, selector) -> prom.tryComplete(selector.selectForPublish()));
   }

   public void registrationsUpdated(RegistrationUpdateEvent event) {
      this.selectors.dataReceived(event.address(), event.registrations(), true);
   }

   public void registrationsLost() {
      this.selectors.dataLost();
   }

   public boolean wantsUpdatesFor(String address) {
      return this.selectors.hasEntryFor(address);
   }
}
