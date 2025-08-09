package org.sparkproject.jetty.io;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ReadPendingException;
import java.nio.channels.WritePendingException;
import org.sparkproject.jetty.util.Callback;

public interface EndPoint extends Closeable {
   /** @deprecated */
   @Deprecated
   InetSocketAddress getLocalAddress();

   default SocketAddress getLocalSocketAddress() {
      return this.getLocalAddress();
   }

   /** @deprecated */
   @Deprecated
   InetSocketAddress getRemoteAddress();

   default SocketAddress getRemoteSocketAddress() {
      return this.getRemoteAddress();
   }

   boolean isOpen();

   long getCreatedTimeStamp();

   void shutdownOutput();

   boolean isOutputShutdown();

   boolean isInputShutdown();

   default void close() {
      this.close((Throwable)null);
   }

   void close(Throwable var1);

   default int fill(ByteBuffer buffer) throws IOException {
      throw new UnsupportedOperationException();
   }

   default boolean flush(ByteBuffer... buffer) throws IOException {
      throw new UnsupportedOperationException();
   }

   Object getTransport();

   long getIdleTimeout();

   void setIdleTimeout(long var1);

   void fillInterested(Callback var1) throws ReadPendingException;

   boolean tryFillInterested(Callback var1);

   boolean isFillInterested();

   default void write(Callback callback, ByteBuffer... buffers) throws WritePendingException {
      throw new UnsupportedOperationException();
   }

   Connection getConnection();

   void setConnection(Connection var1);

   void onOpen();

   void onClose(Throwable var1);

   void upgrade(Connection var1);

   public interface Wrapper {
      EndPoint unwrap();
   }
}
