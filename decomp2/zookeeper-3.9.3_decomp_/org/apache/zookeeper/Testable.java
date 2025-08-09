package org.apache.zookeeper;

import java.io.IOException;

public interface Testable {
   void injectSessionExpiration();

   void queueEvent(WatchedEvent var1);

   default void closeSocket() throws IOException {
   }
}
