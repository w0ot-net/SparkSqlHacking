package org.sparkproject.jetty.util;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import org.sparkproject.jetty.util.thread.Invocable;

public interface Callback extends Invocable {
   Callback NOOP = new Callback() {
      public Invocable.InvocationType getInvocationType() {
         return Invocable.InvocationType.NON_BLOCKING;
      }
   };

   default void completeWith(CompletableFuture completable) {
      completable.whenComplete((o, x) -> {
         if (x == null) {
            this.succeeded();
         } else {
            this.failed(x);
         }

      });
   }

   default void succeeded() {
   }

   default void failed(Throwable x) {
   }

   static Callback from(CompletableFuture completable) {
      return from(completable, Invocable.InvocationType.NON_BLOCKING);
   }

   static Callback from(final CompletableFuture completable, final Invocable.InvocationType invocation) {
      return completable instanceof Callback ? (Callback)completable : new Callback() {
         public void succeeded() {
            completable.complete((Object)null);
         }

         public void failed(Throwable x) {
            completable.completeExceptionally(x);
         }

         public Invocable.InvocationType getInvocationType() {
            return invocation;
         }
      };
   }

   static Callback from(Runnable success, Consumer failure) {
      return from(Invocable.InvocationType.BLOCKING, success, failure);
   }

   static Callback from(final Invocable.InvocationType invocationType, final Runnable success, final Consumer failure) {
      return new Callback() {
         public void succeeded() {
            success.run();
         }

         public void failed(Throwable x) {
            failure.accept(x);
         }

         public Invocable.InvocationType getInvocationType() {
            return invocationType;
         }
      };
   }

   static Callback from(final Runnable completed) {
      return new Completing() {
         public void completed() {
            completed.run();
         }
      };
   }

   static Callback from(Invocable.InvocationType invocationType, final Runnable completed) {
      return new Completing(invocationType) {
         public void completed() {
            completed.run();
         }
      };
   }

   static Callback from(Callback callback, final Runnable completed) {
      return new Nested(callback) {
         public void completed() {
            completed.run();
         }
      };
   }

   static Callback from(final Runnable completed, final Callback callback) {
      return new Callback() {
         public void succeeded() {
            try {
               completed.run();
               callback.succeeded();
            } catch (Throwable t) {
               callback.failed(t);
            }

         }

         public void failed(Throwable x) {
            try {
               completed.run();
            } catch (Throwable t) {
               x.addSuppressed(t);
            }

            callback.failed(x);
         }
      };
   }

   static Callback from(final Callback callback, final Throwable cause) {
      return new Callback() {
         public void succeeded() {
            callback.failed(cause);
         }

         public void failed(Throwable x) {
            cause.addSuppressed(x);
            callback.failed(cause);
         }
      };
   }

   static Callback from(final Callback callback1, final Callback callback2) {
      return new Callback() {
         public void succeeded() {
            callback1.succeeded();
            callback2.succeeded();
         }

         public void failed(Throwable x) {
            callback1.failed(x);
            callback2.failed(x);
         }
      };
   }

   static Callback combine(final Callback cb1, final Callback cb2) {
      if (cb1 != null && cb1 != cb2) {
         return cb2 == null ? cb1 : new Callback() {
            public void succeeded() {
               try {
                  cb1.succeeded();
               } finally {
                  cb2.succeeded();
               }

            }

            public void failed(Throwable x) {
               try {
                  cb1.failed(x);
               } catch (Throwable t) {
                  if (x != t) {
                     x.addSuppressed(t);
                  }
               } finally {
                  cb2.failed(x);
               }

            }

            public Invocable.InvocationType getInvocationType() {
               return Invocable.combine(Invocable.getInvocationType(cb1), Invocable.getInvocationType(cb2));
            }
         };
      } else {
         return cb2;
      }
   }

   public static class Completing implements Callback {
      private final Invocable.InvocationType invocationType;

      public Completing() {
         this(Invocable.InvocationType.BLOCKING);
      }

      public Completing(Invocable.InvocationType invocationType) {
         this.invocationType = invocationType;
      }

      public void succeeded() {
         this.completed();
      }

      public void failed(Throwable x) {
         this.completed();
      }

      public Invocable.InvocationType getInvocationType() {
         return this.invocationType;
      }

      public void completed() {
      }
   }

   public static class Nested extends Completing {
      private final Callback callback;

      public Nested(Callback callback) {
         this.callback = callback;
      }

      public Nested(Nested nested) {
         this.callback = nested.callback;
      }

      public Callback getCallback() {
         return this.callback;
      }

      public void succeeded() {
         try {
            this.callback.succeeded();
         } finally {
            this.completed();
         }

      }

      public void failed(Throwable x) {
         try {
            this.callback.failed(x);
         } finally {
            this.completed();
         }

      }

      public Invocable.InvocationType getInvocationType() {
         return this.callback.getInvocationType();
      }
   }

   public static class Completable extends CompletableFuture implements Callback {
      private final Invocable.InvocationType invocation;

      public static Completable from(final Callback callback) {
         return new Completable(callback.getInvocationType()) {
            public void succeeded() {
               callback.succeeded();
               super.succeeded();
            }

            public void failed(Throwable x) {
               callback.failed(x);
               super.failed(x);
            }
         };
      }

      public Completable() {
         this(Invocable.InvocationType.NON_BLOCKING);
      }

      public Completable(Invocable.InvocationType invocation) {
         this.invocation = invocation;
      }

      public void succeeded() {
         this.complete((Object)null);
      }

      public void failed(Throwable x) {
         this.completeExceptionally(x);
      }

      public Invocable.InvocationType getInvocationType() {
         return this.invocation;
      }
   }
}
