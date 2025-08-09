package io.vertx.core.spi.cluster;

import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.impl.VertxBuilder;
import io.vertx.core.spi.VertxServiceProvider;

public interface NodeSelector extends VertxServiceProvider {
   default void init(VertxBuilder builder) {
      if (builder.clusterNodeSelector() == null) {
         builder.clusterNodeSelector(this);
      }

   }

   void init(Vertx var1, ClusterManager var2);

   void eventBusStarted();

   void selectForSend(Message var1, Promise var2);

   void selectForPublish(Message var1, Promise var2);

   void registrationsUpdated(RegistrationUpdateEvent var1);

   void registrationsLost();

   default boolean wantsUpdatesFor(String address) {
      return true;
   }
}
