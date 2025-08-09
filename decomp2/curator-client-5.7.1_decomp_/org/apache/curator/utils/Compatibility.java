package org.apache.curator.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import org.apache.zookeeper.server.quorum.QuorumPeer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Compatibility {
   private static final Logger log = LoggerFactory.getLogger(Compatibility.class);
   private static final Method getReachableOrOneMethod;
   private static final Field addrField;
   private static final boolean hasPersistentWatchers;

   public static boolean hasGetReachableOrOneMethod() {
      return getReachableOrOneMethod != null;
   }

   public static boolean hasAddrField() {
      return addrField != null;
   }

   public static String getHostString(QuorumPeer.QuorumServer server) {
      InetSocketAddress address = null;
      if (getReachableOrOneMethod != null) {
         try {
            address = (InetSocketAddress)getReachableOrOneMethod.invoke(server.addr);
         } catch (Exception e) {
            log.error("Could not call getReachableOrOneMethod.invoke({})", server.addr, e);
         }
      } else if (addrField != null) {
         try {
            address = (InetSocketAddress)addrField.get(server);
         } catch (Exception e) {
            log.error("Could not call addrField.get({})", server, e);
         }
      }

      return address != null ? address.getHostString() : "unknown";
   }

   public static boolean hasPersistentWatchers() {
      return hasPersistentWatchers;
   }

   static {
      Method localGetReachableOrOneMethod;
      try {
         Class<?> multipleAddressesClass = Class.forName("org.apache.zookeeper.server.quorum.MultipleAddresses");
         localGetReachableOrOneMethod = multipleAddressesClass.getMethod("getReachableOrOne");
         log.info("Using org.apache.zookeeper.server.quorum.MultipleAddresses");
      } catch (ReflectiveOperationException var6) {
         localGetReachableOrOneMethod = null;
      }

      getReachableOrOneMethod = localGetReachableOrOneMethod;

      Field localAddrField;
      try {
         localAddrField = QuorumPeer.QuorumServer.class.getField("addr");
      } catch (NoSuchFieldException var5) {
         localAddrField = null;
         log.error("Could not get addr field! Reconfiguration fail!");
      }

      addrField = localAddrField;

      boolean localHasPersistentWatchers;
      try {
         Class.forName("org.apache.zookeeper.AddWatchMode");
         localHasPersistentWatchers = true;
      } catch (ClassNotFoundException var4) {
         localHasPersistentWatchers = false;
         log.info("Persistent Watchers are not available in the version of the ZooKeeper library being used");
      }

      hasPersistentWatchers = localHasPersistentWatchers;
   }
}
