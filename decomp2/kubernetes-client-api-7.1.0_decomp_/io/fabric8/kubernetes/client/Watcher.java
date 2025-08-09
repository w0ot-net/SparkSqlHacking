package io.fabric8.kubernetes.client;

import org.slf4j.LoggerFactory;

public interface Watcher {
   default boolean reconnecting() {
      return false;
   }

   void eventReceived(Action var1, Object var2);

   default void onClose() {
      LoggerFactory.getLogger(Watcher.class).debug("Watcher closed");
   }

   void onClose(WatcherException var1);

   public static enum Action {
      ADDED,
      MODIFIED,
      DELETED,
      ERROR,
      BOOKMARK;
   }
}
