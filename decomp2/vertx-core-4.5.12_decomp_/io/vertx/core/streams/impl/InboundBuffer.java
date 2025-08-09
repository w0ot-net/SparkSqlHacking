package io.vertx.core.streams.impl;

import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.impl.ContextInternal;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Objects;

public class InboundBuffer {
   public static final Object END_SENTINEL = new Object();
   private final ContextInternal context;
   private ArrayDeque pending;
   private final long highWaterMark;
   private long demand;
   private Handler handler;
   private boolean overflow;
   private Handler drainHandler;
   private Handler emptyHandler;
   private Handler exceptionHandler;
   private boolean emitting;

   public InboundBuffer(Context context) {
      this(context, 16L);
   }

   public InboundBuffer(Context context, long highWaterMark) {
      this(context, highWaterMark, Long.MAX_VALUE, (Handler)null, (Handler)null);
   }

   public static InboundBuffer createPaused(Context context, long highWaterMark, Handler drainHandler, Handler handler) {
      Objects.requireNonNull(drainHandler);
      Objects.requireNonNull(handler);
      return new InboundBuffer(context, highWaterMark, 0L, drainHandler, handler);
   }

   public static InboundBuffer createAndFetch(Context context, long highWaterMark, long demand, Handler drainHandler, Handler handler) {
      Objects.requireNonNull(drainHandler);
      Objects.requireNonNull(handler);
      checkPositiveAmount(demand);
      InboundBuffer<E> inboundBuffer = new InboundBuffer(context, highWaterMark, Long.MAX_VALUE, drainHandler, handler);
      if (inboundBuffer.emit(demand)) {
         inboundBuffer.asyncDrain();
         inboundBuffer.context.runOnContext((v) -> inboundBuffer.drain());
      }

      return inboundBuffer;
   }

   private InboundBuffer(Context context, long highWaterMark, long demand, Handler drainHandler, Handler handler) {
      if (context == null) {
         throw new NullPointerException("context must not be null");
      } else if (highWaterMark < 0L) {
         throw new IllegalArgumentException("highWaterMark " + highWaterMark + " >= 0");
      } else {
         this.context = (ContextInternal)context;
         this.highWaterMark = highWaterMark;
         this.demand = demand;
         this.pending = null;
         this.drainHandler = drainHandler;
         this.handler = handler;
      }
   }

   private void checkThread() {
      if (!this.context.inThread()) {
         throw new IllegalStateException("This operation must be called from a Vert.x thread");
      }
   }

   public boolean write(Object element) {
      this.checkThread();
      Handler<E> handler;
      synchronized(this) {
         if (this.demand == 0L || this.emitting) {
            if (this.pending == null) {
               this.pending = new ArrayDeque(1);
            }

            this.pending.add(element);
            return this.checkWritable();
         }

         if (this.demand != Long.MAX_VALUE) {
            --this.demand;
         }

         this.emitting = true;
         handler = this.handler;
      }

      this.handleEvent(handler, element);
      return this.emitPending();
   }

   private boolean checkWritable() {
      if (this.demand == Long.MAX_VALUE) {
         return true;
      } else {
         int size = this.pending == null ? 0 : this.pending.size();
         long actual = (long)size - this.demand;
         boolean writable = actual < this.highWaterMark;
         this.overflow |= !writable;
         return writable;
      }
   }

   public boolean write(Iterable elements) {
      this.checkThread();
      synchronized(this) {
         if (this.pending == null) {
            int requiredCapacity;
            if (elements instanceof Collection) {
               requiredCapacity = ((Collection)elements).size();
            } else {
               requiredCapacity = 1;
            }

            this.pending = new ArrayDeque(requiredCapacity);
         }

         for(Object element : elements) {
            this.pending.add(element);
         }

         if (this.demand == 0L || this.emitting) {
            return this.checkWritable();
         }

         this.emitting = true;
      }

      return this.emitPending();
   }

   private boolean emitPending() {
      while(true) {
         E element;
         Handler<E> h;
         synchronized(this) {
            int size = this.size();
            if (this.demand == 0L) {
               this.emitting = false;
               boolean writable = (long)size < this.highWaterMark;
               this.overflow |= !writable;
               return writable;
            }

            if (size == 0) {
               this.emitting = false;
               return true;
            }

            if (this.demand != Long.MAX_VALUE) {
               --this.demand;
            }

            assert this.pending != null;

            element = (E)this.pending.poll();
            h = this.handler;
         }

         this.handleEvent(h, element);
      }
   }

   private void drain() {
      int emitted = 0;

      Handler<Void> drainHandler;
      Handler<Void> emptyHandler;
      while(true) {
         E element;
         Handler<E> handler;
         synchronized(this) {
            int size = this.size();
            if (size == 0) {
               this.emitting = false;
               if (this.overflow) {
                  this.overflow = false;
                  drainHandler = this.drainHandler;
               } else {
                  drainHandler = null;
               }

               emptyHandler = emitted > 0 ? this.emptyHandler : null;
               break;
            }

            if (this.demand == 0L) {
               this.emitting = false;
               return;
            }

            ++emitted;
            if (this.demand != Long.MAX_VALUE) {
               --this.demand;
            }

            assert this.pending != null;

            element = (E)this.pending.poll();
            handler = this.handler;
         }

         this.handleEvent(handler, element);
      }

      if (drainHandler != null) {
         this.handleEvent(drainHandler, (Object)null);
      }

      if (emptyHandler != null) {
         this.handleEvent(emptyHandler, (Object)null);
      }

   }

   private void handleEvent(Handler handler, Object element) {
      if (handler != null) {
         try {
            handler.handle(element);
         } catch (Throwable t) {
            this.handleException(t);
         }
      }

   }

   private void handleException(Throwable err) {
      Handler<Throwable> handler;
      synchronized(this) {
         if ((handler = this.exceptionHandler) == null) {
            return;
         }
      }

      handler.handle(err);
   }

   public boolean fetch(long amount) {
      checkPositiveAmount(amount);
      synchronized(this) {
         if (!this.emit(amount)) {
            return false;
         }
      }

      this.asyncDrain();
      return true;
   }

   private void asyncDrain() {
      this.context.runOnContext((v) -> this.drain());
   }

   private boolean emit(long amount) {
      assert amount >= 0L;

      this.demand += amount;
      if (this.demand < 0L) {
         this.demand = Long.MAX_VALUE;
      }

      if (!this.emitting && (!this.isEmpty() || this.overflow)) {
         this.emitting = true;
         return true;
      } else {
         return false;
      }
   }

   private static void checkPositiveAmount(long amount) {
      if (amount < 0L) {
         throw new IllegalArgumentException();
      }
   }

   public Object read() {
      synchronized(this) {
         return this.isEmpty() ? null : this.pending.poll();
      }
   }

   public synchronized InboundBuffer clear() {
      if (this.isEmpty()) {
         return this;
      } else {
         this.pending.clear();
         return this;
      }
   }

   public synchronized InboundBuffer pause() {
      this.demand = 0L;
      return this;
   }

   public boolean resume() {
      return this.fetch(Long.MAX_VALUE);
   }

   public synchronized InboundBuffer handler(Handler handler) {
      this.handler = handler;
      return this;
   }

   public synchronized InboundBuffer drainHandler(Handler handler) {
      this.drainHandler = handler;
      return this;
   }

   public synchronized InboundBuffer emptyHandler(Handler handler) {
      this.emptyHandler = handler;
      return this;
   }

   public synchronized InboundBuffer exceptionHandler(Handler handler) {
      this.exceptionHandler = handler;
      return this;
   }

   public synchronized boolean isEmpty() {
      return this.pending == null ? true : this.pending.isEmpty();
   }

   public synchronized boolean isWritable() {
      return (long)this.size() < this.highWaterMark;
   }

   public synchronized boolean isPaused() {
      return this.demand == 0L;
   }

   public synchronized int size() {
      return this.pending == null ? 0 : this.pending.size();
   }
}
