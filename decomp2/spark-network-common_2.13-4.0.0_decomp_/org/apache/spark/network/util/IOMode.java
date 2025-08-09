package org.apache.spark.network.util;

public enum IOMode {
   NIO,
   EPOLL;

   // $FF: synthetic method
   private static IOMode[] $values() {
      return new IOMode[]{NIO, EPOLL};
   }
}
