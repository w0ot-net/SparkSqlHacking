package org.sparkproject.jetty.client.api;

import java.io.Closeable;
import java.net.SocketAddress;

public interface Connection extends Closeable {
   void send(Request var1, Response.CompleteListener var2);

   void close();

   boolean isClosed();

   default SocketAddress getLocalSocketAddress() {
      return null;
   }

   default SocketAddress getRemoteSocketAddress() {
      return null;
   }
}
