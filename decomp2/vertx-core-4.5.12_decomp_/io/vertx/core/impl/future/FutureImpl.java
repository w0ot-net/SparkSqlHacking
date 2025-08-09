package io.vertx.core.impl.future;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.NoStackTraceThrowable;
import java.util.ArrayList;
import java.util.Objects;

public class FutureImpl extends FutureBase {
   private static final Object NULL_VALUE = new Object();
   private Object value;
   private Listener listener;

   protected FutureImpl() {
   }

   protected FutureImpl(ContextInternal context) {
      super(context);
   }

   public synchronized Object result() {
      return this.value instanceof CauseHolder ? null : (this.value == NULL_VALUE ? null : this.value);
   }

   public synchronized Throwable cause() {
      return this.value instanceof CauseHolder ? ((CauseHolder)this.value).cause : null;
   }

   public synchronized boolean succeeded() {
      return this.value != null && !(this.value instanceof CauseHolder);
   }

   public synchronized boolean failed() {
      return this.value instanceof CauseHolder;
   }

   public synchronized boolean isComplete() {
      return this.value != null;
   }

   public Future onSuccess(final Handler handler) {
      Objects.requireNonNull(handler, "No null handler accepted");
      this.addListener(new Listener() {
         public void onSuccess(Object value) {
            try {
               handler.handle(value);
            } catch (Throwable t) {
               if (FutureImpl.this.context == null) {
                  throw t;
               }

               FutureImpl.this.context.reportException(t);
            }

         }

         public void onFailure(Throwable failure) {
         }
      });
      return this;
   }

   public Future onFailure(final Handler handler) {
      Objects.requireNonNull(handler, "No null handler accepted");
      this.addListener(new Listener() {
         public void onSuccess(Object value) {
         }

         public void onFailure(Throwable failure) {
            try {
               handler.handle(failure);
            } catch (Throwable t) {
               if (FutureImpl.this.context == null) {
                  throw t;
               }

               FutureImpl.this.context.reportException(t);
            }

         }
      });
      return this;
   }

   public Future onComplete(final Handler successHandler, final Handler failureHandler) {
      this.addListener(new Listener() {
         public void onSuccess(Object value) {
            try {
               if (successHandler != null) {
                  successHandler.handle(value);
               }
            } catch (Throwable t) {
               if (FutureImpl.this.context == null) {
                  throw t;
               }

               FutureImpl.this.context.reportException(t);
            }

         }

         public void onFailure(Throwable failure) {
            try {
               if (failureHandler != null) {
                  failureHandler.handle(failure);
               }
            } catch (Throwable t) {
               if (FutureImpl.this.context == null) {
                  throw t;
               }

               FutureImpl.this.context.reportException(t);
            }

         }
      });
      return this;
   }

   public Future onComplete(final Handler handler) {
      Objects.requireNonNull(handler, "No null handler accepted");
      Listener<T> listener;
      if (handler instanceof Listener) {
         listener = (Listener)handler;
      } else {
         listener = new Listener() {
            public void onSuccess(Object value) {
               try {
                  handler.handle(FutureImpl.this);
               } catch (Throwable t) {
                  if (FutureImpl.this.context == null) {
                     throw t;
                  }

                  FutureImpl.this.context.reportException(t);
               }

            }

            public void onFailure(Throwable failure) {
               try {
                  handler.handle(FutureImpl.this);
               } catch (Throwable t) {
                  if (FutureImpl.this.context == null) {
                     throw t;
                  }

                  FutureImpl.this.context.reportException(t);
               }

            }
         };
      }

      this.addListener(listener);
      return this;
   }

   public void addListener(Listener listener) {
      Object v;
      synchronized(this) {
         v = this.value;
         if (v == null) {
            if (this.listener == null) {
               this.listener = listener;
            } else {
               ListenerArray<T> listeners;
               if (this.listener instanceof ListenerArray) {
                  listeners = (ListenerArray)this.listener;
               } else {
                  listeners = new ListenerArray();
                  listeners.add(this.listener);
                  this.listener = listeners;
               }

               listeners.add(listener);
            }

            return;
         }
      }

      if (v instanceof CauseHolder) {
         this.emitFailure(((CauseHolder)v).cause, listener);
      } else {
         if (v == NULL_VALUE) {
            v = null;
         }

         this.emitSuccess(v, listener);
      }

   }

   public void removeListener(Listener l) {
      synchronized(this) {
         Object listener = this.listener;
         if (listener == l) {
            this.listener = null;
         } else if (listener instanceof ListenerArray) {
            ListenerArray<?> listeners = (ListenerArray)listener;
            listeners.remove(l);
         }

      }
   }

   public boolean tryComplete(Object result) {
      Listener<T> l;
      synchronized(this) {
         if (this.value != null) {
            return false;
         }

         this.value = result == null ? NULL_VALUE : result;
         l = this.listener;
         this.listener = null;
      }

      if (l != null) {
         this.emitSuccess(result, l);
      }

      return true;
   }

   public boolean tryFail(Throwable cause) {
      if (cause == null) {
         cause = new NoStackTraceThrowable((String)null);
      }

      Listener<T> l;
      synchronized(this) {
         if (this.value != null) {
            return false;
         }

         this.value = new CauseHolder(cause);
         l = this.listener;
         this.listener = null;
      }

      if (l != null) {
         this.emitFailure(cause, l);
      }

      return true;
   }

   public String toString() {
      synchronized(this) {
         if (this.value instanceof CauseHolder) {
            return "Future{cause=" + ((CauseHolder)this.value).cause.getMessage() + "}";
         } else if (this.value != null) {
            if (this.value == NULL_VALUE) {
               return "Future{result=null}";
            } else {
               StringBuilder sb = new StringBuilder("Future{result=");
               this.formatValue(this.value, sb);
               sb.append("}");
               return sb.toString();
            }
         } else {
            return "Future{unresolved}";
         }
      }
   }

   protected void formatValue(Object value, StringBuilder sb) {
      sb.append(value);
   }

   private static class ListenerArray extends ArrayList implements Listener {
      private ListenerArray() {
      }

      public void onSuccess(Object value) {
         for(Listener handler : this) {
            handler.onSuccess(value);
         }

      }

      public void onFailure(Throwable failure) {
         for(Listener handler : this) {
            handler.onFailure(failure);
         }

      }
   }

   private static class CauseHolder {
      private final Throwable cause;

      CauseHolder(Throwable cause) {
         this.cause = cause;
      }
   }
}
