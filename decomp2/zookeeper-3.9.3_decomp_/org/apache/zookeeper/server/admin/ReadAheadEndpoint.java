package org.apache.zookeeper.server.admin;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ReadPendingException;
import java.nio.channels.WritePendingException;
import org.eclipse.jetty.io.Connection;
import org.eclipse.jetty.io.EndPoint;
import org.eclipse.jetty.util.Callback;

public class ReadAheadEndpoint implements EndPoint {
   private final EndPoint endPoint;
   private final ByteBuffer start;
   private final byte[] bytes;
   private int leftToRead;
   private IOException pendingException = null;

   public InetSocketAddress getLocalAddress() {
      return this.endPoint.getLocalAddress();
   }

   public InetSocketAddress getRemoteAddress() {
      return this.endPoint.getRemoteAddress();
   }

   public boolean isOpen() {
      return this.endPoint.isOpen();
   }

   public long getCreatedTimeStamp() {
      return this.endPoint.getCreatedTimeStamp();
   }

   public boolean isOutputShutdown() {
      return this.endPoint.isOutputShutdown();
   }

   public boolean isInputShutdown() {
      return this.endPoint.isInputShutdown();
   }

   public void shutdownOutput() {
      this.endPoint.shutdownOutput();
   }

   public void close() {
      this.endPoint.close();
   }

   public Object getTransport() {
      return this.endPoint.getTransport();
   }

   public long getIdleTimeout() {
      return this.endPoint.getIdleTimeout();
   }

   public Connection getConnection() {
      return this.endPoint.getConnection();
   }

   public void onOpen() {
      this.endPoint.onOpen();
   }

   public void onClose() {
      this.endPoint.onClose();
   }

   public boolean isOptimizedForDirectBuffers() {
      return this.endPoint.isOptimizedForDirectBuffers();
   }

   public boolean isFillInterested() {
      return this.endPoint.isFillInterested();
   }

   public boolean tryFillInterested(Callback v) {
      return this.endPoint.tryFillInterested(v);
   }

   public boolean flush(ByteBuffer... v) throws IOException {
      return this.endPoint.flush(v);
   }

   public void setIdleTimeout(long v) {
      this.endPoint.setIdleTimeout(v);
   }

   public void write(Callback v, ByteBuffer... b) throws WritePendingException {
      this.endPoint.write(v, b);
   }

   public void setConnection(Connection v) {
      this.endPoint.setConnection(v);
   }

   public void upgrade(Connection v) {
      this.endPoint.upgrade(v);
   }

   public void fillInterested(Callback v) throws ReadPendingException {
      this.endPoint.fillInterested(v);
   }

   public ReadAheadEndpoint(EndPoint channel, int readAheadLength) {
      if (channel == null) {
         throw new IllegalArgumentException("channel cannot be null");
      } else {
         this.endPoint = channel;
         this.start = ByteBuffer.wrap(this.bytes = new byte[readAheadLength]);
         this.start.flip();
         this.leftToRead = readAheadLength;
      }
   }

   private synchronized void readAhead() throws IOException {
      if (this.leftToRead > 0) {
         int n = 0;

         do {
            n = this.endPoint.fill(this.start);
         } while(n == 0 && this.endPoint.isOpen() && !this.endPoint.isInputShutdown());

         if (n == -1) {
            this.leftToRead = -1;
         } else {
            this.leftToRead -= n;
         }

         if (this.leftToRead <= 0) {
            this.start.rewind();
         }
      }

   }

   private int readFromStart(ByteBuffer dst) throws IOException {
      int n = Math.min(dst.remaining(), this.start.remaining());
      if (n > 0) {
         dst.put(this.bytes, this.start.position(), n);
         this.start.position(this.start.position() + n);
         dst.flip();
      }

      return n;
   }

   public synchronized int fill(ByteBuffer dst) throws IOException {
      this.throwPendingException();
      if (this.leftToRead > 0) {
         this.readAhead();
      }

      if (this.leftToRead > 0) {
         return 0;
      } else {
         int sr = this.start.remaining();
         if (sr > 0) {
            dst.compact();
            int n = this.readFromStart(dst);
            if (n < sr) {
               return n;
            }
         }

         return sr + this.endPoint.fill(dst);
      }
   }

   public byte[] getBytes() {
      if (this.pendingException == null) {
         try {
            this.readAhead();
         } catch (IOException e) {
            this.pendingException = e;
         }
      }

      byte[] ret = new byte[this.bytes.length];
      System.arraycopy(this.bytes, 0, ret, 0, ret.length);
      return ret;
   }

   private void throwPendingException() throws IOException {
      if (this.pendingException != null) {
         IOException e = this.pendingException;
         this.pendingException = null;
         throw e;
      }
   }
}
