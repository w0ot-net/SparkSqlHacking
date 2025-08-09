package io.vertx.core.impl.future;

import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class CompositeFutureImpl extends FutureImpl implements CompositeFuture, Listener {
   private static final int OP_ALL = 0;
   private static final int OP_ANY = 1;
   private static final int OP_JOIN = 2;
   private final Future[] results;
   private final int op;
   private boolean initializing;
   private Object completed;
   private int completions;

   public static CompositeFuture all(Future... results) {
      return create(0, results);
   }

   public static CompositeFuture any(Future... results) {
      return create(1, results);
   }

   public static CompositeFuture join(Future... results) {
      return create(2, results);
   }

   private static CompositeFuture create(int op, Future... results) {
      int len = results.length;
      CompositeFutureImpl composite;
      if (len > 0) {
         composite = new CompositeFutureImpl(op, true, results);
         composite.init();
      } else {
         composite = new CompositeFutureImpl(op, false, results);
         composite.complete(composite);
      }

      return composite;
   }

   private CompositeFutureImpl(int op, boolean initializing, Future... results) {
      this.op = op;
      this.initializing = initializing;
      this.results = results;
   }

   private void init() {
      for(Future result : this.results) {
         if (result instanceof FutureInternal) {
            FutureInternal internal = (FutureInternal)result;
            internal.addListener(this);
         } else {
            result.onComplete(this::onSuccess, this::onFailure);
         }
      }

      Object o;
      synchronized(this) {
         this.initializing = false;
         o = this.completed;
         if (o == null) {
            return;
         }
      }

      this.complete(o);
   }

   public void onSuccess(Object value) {
      int len = this.results.length;
      Object completion;
      synchronized(this) {
         int val = ++this.completions;
         if (this.completed != null) {
            return;
         }

         switch (this.op) {
            case 0:
               if (val < len) {
                  return;
               }

               completion = this;
               break;
            case 1:
               completion = this;
               break;
            case 2:
               if (val < len) {
                  return;
               }

               completion = this.anyFailureOrThis();
               break;
            default:
               throw new AssertionError();
         }

         this.completed = completion;
         if (this.initializing) {
            return;
         }
      }

      this.complete(completion);
   }

   public void onFailure(Throwable failure) {
      int len = this.results.length;
      Object completion;
      synchronized(this) {
         int val = ++this.completions;
         if (this.completed != null) {
            return;
         }

         switch (this.op) {
            case 0:
               completion = failure;
               break;
            case 1:
               if (val < len) {
                  return;
               }

               completion = failure;
               break;
            case 2:
               if (val < len) {
                  return;
               }

               completion = this.anyFailureOrThis();
               break;
            default:
               throw new AssertionError();
         }

         this.completed = completion;
         if (this.initializing) {
            return;
         }
      }

      this.complete(completion);
   }

   private Object anyFailureOrThis() {
      int size = this.size();

      for(int i = 0; i < size; ++i) {
         Future<?> res = this.results[i];
         if (!res.succeeded()) {
            return res.cause();
         }
      }

      return this;
   }

   public Throwable cause(int index) {
      return this.future(index).cause();
   }

   public boolean succeeded(int index) {
      return this.future(index).succeeded();
   }

   public boolean failed(int index) {
      return this.future(index).failed();
   }

   public boolean isComplete(int index) {
      return this.future(index).isComplete();
   }

   public Object resultAt(int index) {
      return this.future(index).result();
   }

   private Future future(int index) {
      if (index >= 0 && index < this.results.length) {
         return this.results[index];
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public int size() {
      return this.results.length;
   }

   private void complete(Object result) {
      for(Future r : this.results) {
         if (r instanceof FutureInternal) {
            FutureInternal internal = (FutureInternal)r;
            internal.removeListener(this);
         }
      }

      if (result == this) {
         this.tryComplete(this);
      } else if (result instanceof Throwable) {
         this.tryFail((Throwable)result);
      }

   }

   public CompositeFuture onComplete(Handler handler) {
      return (CompositeFuture)super.onComplete(handler);
   }

   public CompositeFuture onSuccess(Handler handler) {
      return (CompositeFuture)super.onSuccess(handler);
   }

   public CompositeFuture onFailure(Handler handler) {
      return (CompositeFuture)super.onFailure(handler);
   }

   protected void formatValue(Object value, StringBuilder sb) {
      sb.append('(');

      for(int i = 0; i < this.results.length; ++i) {
         if (i > 0) {
            sb.append(',');
         }

         sb.append(this.results[i]);
      }

      sb.append(')');
   }
}
