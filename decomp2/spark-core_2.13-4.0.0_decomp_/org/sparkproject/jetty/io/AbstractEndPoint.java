package org.sparkproject.jetty.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.WritePendingException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.thread.Scheduler;

public abstract class AbstractEndPoint extends IdleTimeout implements EndPoint {
   private static final Logger LOG = LoggerFactory.getLogger(AbstractEndPoint.class);
   private final AtomicReference _state;
   private final long _created;
   private volatile Connection _connection;
   private final FillInterest _fillInterest;
   private final WriteFlusher _writeFlusher;

   protected AbstractEndPoint(Scheduler scheduler) {
      super(scheduler);
      this._state = new AtomicReference(AbstractEndPoint.State.OPEN);
      this._created = System.currentTimeMillis();
      this._fillInterest = new FillInterest() {
         protected void needsFillInterest() throws IOException {
            AbstractEndPoint.this.needsFillInterest();
         }
      };
      this._writeFlusher = new WriteFlusher(this) {
         protected void onIncompleteFlush() {
            AbstractEndPoint.this.onIncompleteFlush();
         }
      };
   }

   public InetSocketAddress getLocalAddress() {
      SocketAddress local = this.getLocalSocketAddress();
      return local instanceof InetSocketAddress ? (InetSocketAddress)local : null;
   }

   public SocketAddress getLocalSocketAddress() {
      return null;
   }

   public InetSocketAddress getRemoteAddress() {
      SocketAddress remote = this.getRemoteSocketAddress();
      return remote instanceof InetSocketAddress ? (InetSocketAddress)remote : null;
   }

   public SocketAddress getRemoteSocketAddress() {
      return null;
   }

   protected final void shutdownInput() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("shutdownInput {}", this);
      }

      while(true) {
         State s = (State)this._state.get();
         switch (s.ordinal()) {
            case 0:
               if (this._state.compareAndSet(s, AbstractEndPoint.State.ISHUTTING)) {
                  try {
                     this.doShutdownInput();
                  } finally {
                     if (!this._state.compareAndSet(AbstractEndPoint.State.ISHUTTING, AbstractEndPoint.State.ISHUT)) {
                        if (this._state.get() != AbstractEndPoint.State.CLOSED) {
                           throw new IllegalStateException();
                        }

                        this.doOnClose((Throwable)null);
                     }

                  }

                  return;
               }
               break;
            case 1:
            case 2:
               return;
            case 3:
               if (this._state.compareAndSet(s, AbstractEndPoint.State.CLOSED)) {
                  return;
               }
               break;
            case 4:
               if (this._state.compareAndSet(s, AbstractEndPoint.State.CLOSED)) {
                  this.doOnClose((Throwable)null);
                  return;
               }
               break;
            case 5:
               return;
            default:
               throw new IllegalStateException(s.toString());
         }
      }
   }

   public final void shutdownOutput() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("shutdownOutput {}", this);
      }

      while(true) {
         State s = (State)this._state.get();
         switch (s.ordinal()) {
            case 0:
               if (this._state.compareAndSet(s, AbstractEndPoint.State.OSHUTTING)) {
                  try {
                     this.doShutdownOutput();
                  } finally {
                     if (!this._state.compareAndSet(AbstractEndPoint.State.OSHUTTING, AbstractEndPoint.State.OSHUT)) {
                        if (this._state.get() != AbstractEndPoint.State.CLOSED) {
                           throw new IllegalStateException();
                        }

                        this.doOnClose((Throwable)null);
                     }

                  }

                  return;
               }
               break;
            case 1:
               if (this._state.compareAndSet(s, AbstractEndPoint.State.CLOSED)) {
                  return;
               }
               break;
            case 2:
               if (this._state.compareAndSet(s, AbstractEndPoint.State.CLOSED)) {
                  this.doOnClose((Throwable)null);
                  return;
               }
               break;
            case 3:
            case 4:
               return;
            case 5:
               return;
            default:
               throw new IllegalStateException(s.toString());
         }
      }
   }

   public final void close() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("close {}", this);
      }

      this.close((Throwable)null);
   }

   public final void close(Throwable failure) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("close({}) {}", failure, this);
      }

      while(true) {
         State s = (State)this._state.get();
         switch (s.ordinal()) {
            case 0:
            case 2:
            case 4:
               if (this._state.compareAndSet(s, AbstractEndPoint.State.CLOSED)) {
                  this.doOnClose(failure);
                  return;
               }
               break;
            case 1:
            case 3:
               if (this._state.compareAndSet(s, AbstractEndPoint.State.CLOSED)) {
                  return;
               }
               break;
            case 5:
               return;
            default:
               throw new IllegalStateException(s.toString());
         }
      }
   }

   protected void doShutdownInput() {
   }

   protected void doShutdownOutput() {
   }

   private void doOnClose(Throwable failure) {
      try {
         this.doClose();
      } finally {
         if (failure == null) {
            this.onClose();
         } else {
            this.onClose(failure);
         }

      }

   }

   protected void doClose() {
   }

   public boolean isOutputShutdown() {
      switch (((State)this._state.get()).ordinal()) {
         case 3:
         case 4:
         case 5:
            return true;
         default:
            return false;
      }
   }

   public boolean isInputShutdown() {
      switch (((State)this._state.get()).ordinal()) {
         case 1:
         case 2:
         case 5:
            return true;
         case 3:
         case 4:
         default:
            return false;
      }
   }

   public boolean isOpen() {
      return this._state.get() != AbstractEndPoint.State.CLOSED;
   }

   public long getCreatedTimeStamp() {
      return this._created;
   }

   public Connection getConnection() {
      return this._connection;
   }

   public void setConnection(Connection connection) {
      this._connection = connection;
   }

   protected void reset() {
      this._state.set(AbstractEndPoint.State.OPEN);
      this._writeFlusher.onClose();
      this._fillInterest.onClose();
   }

   public void onOpen() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("onOpen {}", this);
      }

      if (this._state.get() != AbstractEndPoint.State.OPEN) {
         throw new IllegalStateException();
      }
   }

   public final void onClose() {
      this.onClose((Throwable)null);
   }

   public void onClose(Throwable failure) {
      super.onClose();
      if (failure == null) {
         this._writeFlusher.onClose();
         this._fillInterest.onClose();
      } else {
         this._writeFlusher.onFail(failure);
         this._fillInterest.onFail(failure);
      }

   }

   public void fillInterested(Callback callback) {
      this.notIdle();
      this._fillInterest.register(callback);
   }

   public boolean tryFillInterested(Callback callback) {
      this.notIdle();
      return this._fillInterest.tryRegister(callback);
   }

   public boolean isFillInterested() {
      return this._fillInterest.isInterested();
   }

   public void write(Callback callback, ByteBuffer... buffers) throws WritePendingException {
      this._writeFlusher.write(callback, buffers);
   }

   protected abstract void onIncompleteFlush();

   protected abstract void needsFillInterest() throws IOException;

   public FillInterest getFillInterest() {
      return this._fillInterest;
   }

   public WriteFlusher getWriteFlusher() {
      return this._writeFlusher;
   }

   protected void onIdleExpired(TimeoutException timeout) {
      Connection connection = this._connection;
      if (connection == null || connection.onIdleExpired()) {
         boolean outputShutdown = this.isOutputShutdown();
         boolean inputShutdown = this.isInputShutdown();
         boolean fillFailed = this._fillInterest.onFail(timeout);
         boolean writeFailed = this._writeFlusher.onFail(timeout);
         if (this.isOpen() && (outputShutdown || inputShutdown) && !fillFailed && !writeFailed) {
            this.close();
         } else {
            LOG.debug("Ignored idle endpoint {}", this);
         }

      }
   }

   public void upgrade(Connection newConnection) {
      Connection oldConnection = this.getConnection();
      ByteBuffer buffer = oldConnection instanceof Connection.UpgradeFrom ? ((Connection.UpgradeFrom)oldConnection).onUpgradeFrom() : null;
      oldConnection.onClose((Throwable)null);
      oldConnection.getEndPoint().setConnection(newConnection);
      if (LOG.isDebugEnabled()) {
         LOG.debug("{} upgrading from {} to {} with {}", new Object[]{this, oldConnection, newConnection, BufferUtil.toDetailString(buffer)});
      }

      if (BufferUtil.hasContent(buffer)) {
         if (!(newConnection instanceof Connection.UpgradeTo)) {
            String var10002 = String.valueOf(newConnection);
            throw new IllegalStateException("Cannot upgrade: " + var10002 + " does not implement " + Connection.UpgradeTo.class.getName());
         }

         ((Connection.UpgradeTo)newConnection).onUpgradeTo(buffer);
      }

      newConnection.onOpen();
   }

   public String toString() {
      return String.format("%s@%x[%s]->[%s]", this.getClass().getSimpleName(), this.hashCode(), this.toEndPointString(), this.toConnectionString());
   }

   public String toEndPointString() {
      return String.format("{l=%s,r=%s,%s,fill=%s,flush=%s,to=%d/%d}", this.getLocalSocketAddress(), this.getRemoteSocketAddress(), this._state.get(), this._fillInterest.toStateString(), this._writeFlusher.toStateString(), this.getIdleFor(), this.getIdleTimeout());
   }

   public String toConnectionString() {
      Connection connection = this.getConnection();
      if (connection == null) {
         return "<null>";
      } else {
         return connection instanceof AbstractConnection ? ((AbstractConnection)connection).toConnectionString() : String.format("%s@%x", connection.getClass().getSimpleName(), connection.hashCode());
      }
   }

   private static enum State {
      OPEN,
      ISHUTTING,
      ISHUT,
      OSHUTTING,
      OSHUT,
      CLOSED;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{OPEN, ISHUTTING, ISHUT, OSHUTTING, OSHUT, CLOSED};
      }
   }
}
