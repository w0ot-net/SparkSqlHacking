package org.sparkproject.jetty.proxy;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.sparkproject.jetty.io.AbstractConnection;
import org.sparkproject.jetty.io.ByteBufferPool;
import org.sparkproject.jetty.io.Connection;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.IteratingCallback;

public abstract class ProxyConnection extends AbstractConnection {
   protected static final Logger LOG;
   private final IteratingCallback pipe = new ProxyIteratingCallback();
   private final ByteBufferPool bufferPool;
   private final ConcurrentMap context;
   private ProxyConnection connection;

   protected ProxyConnection(EndPoint endp, Executor executor, ByteBufferPool bufferPool, ConcurrentMap context) {
      super(endp, executor);
      this.bufferPool = bufferPool;
      this.context = context;
   }

   public ByteBufferPool getByteBufferPool() {
      return this.bufferPool;
   }

   public ConcurrentMap getContext() {
      return this.context;
   }

   public Connection getConnection() {
      return this.connection;
   }

   public void setConnection(ProxyConnection connection) {
      this.connection = connection;
   }

   public void onFillable() {
      this.pipe.iterate();
   }

   protected abstract int read(EndPoint var1, ByteBuffer var2) throws IOException;

   protected abstract void write(EndPoint var1, ByteBuffer var2, Callback var3);

   protected void close(Throwable failure) {
      this.getEndPoint().close(failure);
   }

   public String toConnectionString() {
      EndPoint endPoint = this.getEndPoint();
      return String.format("%s@%x[l:%s<=>r:%s]", this.getClass().getSimpleName(), this.hashCode(), endPoint.getLocalSocketAddress(), endPoint.getRemoteSocketAddress());
   }

   static {
      LOG = ConnectHandler.LOG;
   }

   private class ProxyIteratingCallback extends IteratingCallback {
      private ByteBuffer buffer;
      private int filled;

      protected IteratingCallback.Action process() {
         this.buffer = ProxyConnection.this.bufferPool.acquire(ProxyConnection.this.getInputBufferSize(), true);

         try {
            int filled = this.filled = ProxyConnection.this.read(ProxyConnection.this.getEndPoint(), this.buffer);
            if (ProxyConnection.LOG.isDebugEnabled()) {
               ProxyConnection.LOG.debug("{} filled {} bytes", ProxyConnection.this, filled);
            }

            if (filled > 0) {
               ProxyConnection.this.write(ProxyConnection.this.connection.getEndPoint(), this.buffer, this);
               return IteratingCallback.Action.SCHEDULED;
            } else if (filled == 0) {
               ProxyConnection.this.bufferPool.release(this.buffer);
               ProxyConnection.this.fillInterested();
               return IteratingCallback.Action.IDLE;
            } else {
               ProxyConnection.this.bufferPool.release(this.buffer);
               ProxyConnection.this.connection.getEndPoint().shutdownOutput();
               return IteratingCallback.Action.SUCCEEDED;
            }
         } catch (IOException var2) {
            if (ProxyConnection.LOG.isDebugEnabled()) {
               ProxyConnection.LOG.debug("{} could not fill", ProxyConnection.this, var2);
            }

            ProxyConnection.this.bufferPool.release(this.buffer);
            this.disconnect(var2);
            return IteratingCallback.Action.SUCCEEDED;
         }
      }

      public void succeeded() {
         if (ProxyConnection.LOG.isDebugEnabled()) {
            ProxyConnection.LOG.debug("{} wrote {} bytes", ProxyConnection.this, this.filled);
         }

         ProxyConnection.this.bufferPool.release(this.buffer);
         super.succeeded();
      }

      protected void onCompleteSuccess() {
      }

      protected void onCompleteFailure(Throwable x) {
         if (ProxyConnection.LOG.isDebugEnabled()) {
            ProxyConnection.LOG.debug("{} failed to write {} bytes", new Object[]{ProxyConnection.this, this.filled, x});
         }

         ProxyConnection.this.bufferPool.release(this.buffer);
         this.disconnect(x);
      }

      private void disconnect(Throwable x) {
         ProxyConnection.this.close(x);
         ProxyConnection.this.connection.close(x);
      }
   }
}
