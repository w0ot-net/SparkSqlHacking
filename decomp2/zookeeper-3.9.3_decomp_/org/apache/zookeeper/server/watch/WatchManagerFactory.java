package org.apache.zookeeper.server.watch;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WatchManagerFactory {
   private static final Logger LOG = LoggerFactory.getLogger(WatchManagerFactory.class);
   public static final String ZOOKEEPER_WATCH_MANAGER_NAME = "zookeeper.watchManagerName";

   public static IWatchManager createWatchManager() throws IOException {
      String watchManagerName = System.getProperty("zookeeper.watchManagerName");
      if (watchManagerName == null) {
         watchManagerName = WatchManager.class.getName();
      }

      try {
         IWatchManager watchManager = (IWatchManager)Class.forName(watchManagerName).getConstructor().newInstance();
         LOG.info("Using {} as watch manager", watchManagerName);
         return watchManager;
      } catch (Exception e) {
         IOException ioe = new IOException("Couldn't instantiate " + watchManagerName, e);
         throw ioe;
      }
   }
}
