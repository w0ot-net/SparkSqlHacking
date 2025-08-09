package io.vertx.core.spi.cluster;

import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.impl.VertxBuilder;
import io.vertx.core.spi.VertxServiceProvider;
import java.util.List;
import java.util.Map;

public interface ClusterManager extends VertxServiceProvider {
   default void init(VertxBuilder builder) {
      if (builder.clusterManager() == null) {
         builder.clusterManager(this);
      }

   }

   void init(Vertx var1, NodeSelector var2);

   void getAsyncMap(String var1, Promise var2);

   Map getSyncMap(String var1);

   void getLockWithTimeout(String var1, long var2, Promise var4);

   void getCounter(String var1, Promise var2);

   String getNodeId();

   List getNodes();

   void nodeListener(NodeListener var1);

   void setNodeInfo(NodeInfo var1, Promise var2);

   NodeInfo getNodeInfo();

   void getNodeInfo(String var1, Promise var2);

   void join(Promise var1);

   void leave(Promise var1);

   boolean isActive();

   void addRegistration(String var1, RegistrationInfo var2, Promise var3);

   void removeRegistration(String var1, RegistrationInfo var2, Promise var3);

   void getRegistrations(String var1, Promise var2);

   default String clusterHost() {
      return null;
   }

   default String clusterPublicHost() {
      return null;
   }
}
