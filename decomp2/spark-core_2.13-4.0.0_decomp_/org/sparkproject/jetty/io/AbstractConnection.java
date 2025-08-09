package org.sparkproject.jetty.io;

import java.util.EventListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.thread.Invocable;

public abstract class AbstractConnection implements Connection {
   private static final Logger LOG = LoggerFactory.getLogger(AbstractConnection.class);
   private final List _listeners = new CopyOnWriteArrayList();
   private final long _created = System.currentTimeMillis();
   private final EndPoint _endPoint;
   private final Executor _executor;
   private final Callback _readCallback;
   private int _inputBufferSize = 2048;

   protected AbstractConnection(EndPoint endPoint, Executor executor) {
      if (executor == null) {
         throw new IllegalArgumentException("Executor must not be null!");
      } else {
         this._endPoint = endPoint;
         this._executor = executor;
         this._readCallback = new ReadCallback();
      }
   }

   public void addEventListener(EventListener listener) {
      if (listener instanceof Connection.Listener) {
         this._listeners.add((Connection.Listener)listener);
      }

   }

   public void removeEventListener(EventListener listener) {
      this._listeners.remove(listener);
   }

   public int getInputBufferSize() {
      return this._inputBufferSize;
   }

   public void setInputBufferSize(int inputBufferSize) {
      this._inputBufferSize = inputBufferSize;
   }

   protected Executor getExecutor() {
      return this._executor;
   }

   protected void failedCallback(Callback callback, Throwable x) {
      Runnable failCallback = () -> {
         try {
            callback.failed(x);
         } catch (Exception var3) {
            LOG.warn("Failed callback", x);
         }

      };
      switch (Invocable.getInvocationType(callback)) {
         case BLOCKING:
            try {
               this.getExecutor().execute(failCallback);
            } catch (RejectedExecutionException e) {
               LOG.debug("Rejected", e);
               callback.failed(x);
            }
            break;
         case NON_BLOCKING:
            failCallback.run();
            break;
         case EITHER:
            Invocable.invokeNonBlocking(failCallback);
            break;
         default:
            throw new IllegalStateException();
      }

   }

   public void fillInterested() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("fillInterested {}", this);
      }

      this.getEndPoint().fillInterested(this._readCallback);
   }

   public void tryFillInterested(Callback callback) {
      this.getEndPoint().tryFillInterested(callback);
   }

   public boolean isFillInterested() {
      return this.getEndPoint().isFillInterested();
   }

   public abstract void onFillable();

   protected void onFillInterestedFailed(Throwable cause) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("{} onFillInterestedFailed {}", this, cause);
      }

      if (this._endPoint.isOpen()) {
         boolean close = true;
         if (cause instanceof TimeoutException) {
            close = this.onReadTimeout(cause);
         }

         if (close) {
            if (this._endPoint.isOutputShutdown()) {
               this._endPoint.close();
            } else {
               this._endPoint.shutdownOutput();
               this.fillInterested();
            }
         }
      }

   }

   protected boolean onReadTimeout(Throwable timeout) {
      return true;
   }

   public void onOpen() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("onOpen {}", this);
      }

      for(Connection.Listener listener : this._listeners) {
         this.onOpened(listener);
      }

   }

   private void onOpened(Connection.Listener listener) {
      try {
         listener.onOpened(this);
      } catch (Throwable x) {
         LOG.info("Failure while notifying listener {}", listener, x);
      }

   }

   public void onClose(Throwable cause) {
      if (LOG.isDebugEnabled()) {
         if (cause == null) {
            LOG.debug("onClose {}", this);
         } else {
            LOG.debug("onClose {}", this, cause);
         }
      }

      for(Connection.Listener listener : this._listeners) {
         this.onClosed(listener);
      }

   }

   private void onClosed(Connection.Listener listener) {
      try {
         listener.onClosed(this);
      } catch (Throwable x) {
         if (LOG.isDebugEnabled()) {
            LOG.info("Failure while notifying listener {}", listener, x);
         } else {
            LOG.info("Failure while notifying listener {} {}", listener, x.toString());
         }
      }

   }

   public EndPoint getEndPoint() {
      return this._endPoint;
   }

   public void close() {
      this.getEndPoint().close();
   }

   public boolean onIdleExpired() {
      return true;
   }

   public long getMessagesIn() {
      return -1L;
   }

   public long getMessagesOut() {
      return -1L;
   }

   public long getBytesIn() {
      return -1L;
   }

   public long getBytesOut() {
      return -1L;
   }

   public long getCreatedTimeStamp() {
      return this._created;
   }

   public final String toString() {
      return String.format("%s@%h::%s", this.getClass().getSimpleName(), this, this.getEndPoint());
   }

   public String toConnectionString() {
      return String.format("%s@%h", this.getClass().getSimpleName(), this);
   }

   private class ReadCallback implements Callback {
      public void succeeded() {
         AbstractConnection.this.onFillable();
      }

      public void failed(Throwable x) {
         AbstractConnection.this.onFillInterestedFailed(x);
      }

      public String toString() {
         return String.format("%s@%x{%s}", this.getClass().getSimpleName(), this.hashCode(), AbstractConnection.this);
      }
   }
}
