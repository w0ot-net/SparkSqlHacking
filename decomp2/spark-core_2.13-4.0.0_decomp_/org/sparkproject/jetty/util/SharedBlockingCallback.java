package org.sparkproject.jetty.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.thread.Invocable;

public class SharedBlockingCallback {
   private static final Logger LOG = LoggerFactory.getLogger(SharedBlockingCallback.class);
   private static final Throwable IDLE = new ConstantThrowable("IDLE");
   private static final Throwable SUCCEEDED = new ConstantThrowable("SUCCEEDED");
   private static final Throwable FAILED = new ConstantThrowable("FAILED");
   private final ReentrantLock _lock = new ReentrantLock();
   private final Condition _idle;
   private final Condition _complete;
   private Blocker _blocker;

   public SharedBlockingCallback() {
      this._idle = this._lock.newCondition();
      this._complete = this._lock.newCondition();
      this._blocker = new Blocker();
   }

   public Blocker acquire() throws IOException {
      this._lock.lock();

      Blocker var1;
      try {
         while(this._blocker._state != IDLE) {
            this._idle.await();
         }

         this._blocker._state = null;
         var1 = this._blocker;
      } catch (InterruptedException var5) {
         throw new InterruptedIOException();
      } finally {
         this._lock.unlock();
      }

      return var1;
   }

   public boolean fail(Throwable cause) {
      Objects.requireNonNull(cause);
      this._lock.lock();

      boolean var2;
      try {
         if (this._blocker._state != null) {
            return false;
         }

         this._blocker._state = new BlockerFailedException(cause);
         this._complete.signalAll();
         var2 = true;
      } finally {
         this._lock.unlock();
      }

      return var2;
   }

   protected void notComplete(Blocker blocker) {
      LOG.warn("Blocker not complete {}", blocker);
      if (LOG.isDebugEnabled()) {
         LOG.debug("Blocker not complete stacktrace", new Throwable());
      }

   }

   public class Blocker implements Callback, Closeable {
      private Throwable _state;

      protected Blocker() {
         this._state = SharedBlockingCallback.IDLE;
      }

      public Invocable.InvocationType getInvocationType() {
         return Invocable.InvocationType.NON_BLOCKING;
      }

      public void succeeded() {
         SharedBlockingCallback.this._lock.lock();

         try {
            if (this._state == null) {
               this._state = SharedBlockingCallback.SUCCEEDED;
               SharedBlockingCallback.this._complete.signalAll();
            } else {
               SharedBlockingCallback.LOG.warn("Succeeded after {}", this._state.toString());
               if (SharedBlockingCallback.LOG.isDebugEnabled()) {
                  SharedBlockingCallback.LOG.debug("State", this._state);
               }
            }
         } finally {
            SharedBlockingCallback.this._lock.unlock();
         }

      }

      public void failed(Throwable cause) {
         SharedBlockingCallback.this._lock.lock();

         try {
            if (this._state == null) {
               if (cause == null) {
                  this._state = SharedBlockingCallback.FAILED;
               } else if (cause instanceof BlockerTimeoutException) {
                  this._state = new IOException(cause);
               } else {
                  this._state = cause;
               }

               SharedBlockingCallback.this._complete.signalAll();
            } else if (!(this._state instanceof BlockerTimeoutException) && !(this._state instanceof BlockerFailedException)) {
               String msg = String.format("Failed after %s: %s", this._state, cause);
               SharedBlockingCallback.LOG.warn(msg);
               if (SharedBlockingCallback.LOG.isDebugEnabled()) {
                  SharedBlockingCallback.LOG.debug(msg, this._state);
                  SharedBlockingCallback.LOG.debug(msg, cause);
               }
            } else if (SharedBlockingCallback.LOG.isDebugEnabled()) {
               SharedBlockingCallback.LOG.debug("Failed after {}", this._state);
            }
         } finally {
            SharedBlockingCallback.this._lock.unlock();
         }

      }

      public void block() throws IOException {
         SharedBlockingCallback.this._lock.lock();

         try {
            while(this._state == null) {
               SharedBlockingCallback.this._complete.await();
            }

            if (this._state != SharedBlockingCallback.SUCCEEDED) {
               if (this._state == SharedBlockingCallback.IDLE) {
                  throw new IllegalStateException("IDLE");
               }

               if (this._state instanceof IOException) {
                  throw (IOException)this._state;
               }

               if (this._state instanceof CancellationException) {
                  throw (CancellationException)this._state;
               }

               if (this._state instanceof RuntimeException) {
                  throw (RuntimeException)this._state;
               }

               if (this._state instanceof Error) {
                  throw (Error)this._state;
               }

               throw new IOException(this._state);
            }
         } catch (InterruptedException e) {
            this._state = e;
            throw new InterruptedIOException();
         } finally {
            SharedBlockingCallback.this._lock.unlock();
         }

      }

      public void close() {
         SharedBlockingCallback.this._lock.lock();

         try {
            if (this._state == SharedBlockingCallback.IDLE) {
               throw new IllegalStateException("IDLE");
            }

            if (this._state == null) {
               SharedBlockingCallback.this.notComplete(this);
            }
         } finally {
            try {
               if (this._state != null && this._state != SharedBlockingCallback.SUCCEEDED) {
                  SharedBlockingCallback.this._blocker = SharedBlockingCallback.this.new Blocker();
               } else {
                  this._state = SharedBlockingCallback.IDLE;
               }

               SharedBlockingCallback.this._idle.signalAll();
               SharedBlockingCallback.this._complete.signalAll();
            } finally {
               SharedBlockingCallback.this._lock.unlock();
            }
         }

      }

      public String toString() {
         SharedBlockingCallback.this._lock.lock();

         String var1;
         try {
            var1 = String.format("%s@%x{%s}", Blocker.class.getSimpleName(), this.hashCode(), this._state);
         } finally {
            SharedBlockingCallback.this._lock.unlock();
         }

         return var1;
      }
   }

   private static class BlockerTimeoutException extends TimeoutException {
   }

   private static class BlockerFailedException extends Exception {
      public BlockerFailedException(Throwable cause) {
         super(cause);
      }
   }
}
