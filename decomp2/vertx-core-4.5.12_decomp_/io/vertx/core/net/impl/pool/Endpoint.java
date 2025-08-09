package io.vertx.core.net.impl.pool;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.impl.ContextInternal;

public abstract class Endpoint {
   private final Runnable dispose;
   private boolean closed;
   private boolean disposed;
   private long pendingRequestCount;
   private long refCount;

   public Endpoint(Runnable dispose) {
      this.dispose = dispose;
   }

   public boolean getConnection(ContextInternal ctx, long timeout, Handler handler) {
      synchronized(this) {
         if (this.disposed) {
            return false;
         }

         ++this.pendingRequestCount;
      }

      this.requestConnection(ctx, timeout, (ar) -> {
         boolean dispose;
         synchronized(this) {
            --this.pendingRequestCount;
            dispose = this.checkDispose();
         }

         if (dispose) {
            this.disposeInternal();
         }

         handler.handle(ar);
      });
      return true;
   }

   public abstract void requestConnection(ContextInternal var1, long var2, Handler var4);

   protected boolean incRefCount() {
      synchronized(this) {
         ++this.refCount;
         return !this.closed;
      }
   }

   protected boolean decRefCount() {
      synchronized(this) {
         --this.refCount;
         if (!this.checkDispose()) {
            return false;
         }
      }

      this.disposeInternal();
      return true;
   }

   private void disposeInternal() {
      this.dispose.run();
      this.dispose();
   }

   private boolean checkDispose() {
      if (!this.disposed && this.refCount == 0L && this.pendingRequestCount == 0L) {
         this.disposed = true;
         return true;
      } else {
         return false;
      }
   }

   protected void dispose() {
   }

   protected void close() {
      synchronized(this) {
         if (this.closed) {
            throw new IllegalStateException();
         } else {
            this.closed = true;
         }
      }
   }
}
