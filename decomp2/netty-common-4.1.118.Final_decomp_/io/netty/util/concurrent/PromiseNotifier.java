package io.netty.util.concurrent;

import [Lio.netty.util.concurrent.Promise;;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PromiseNotificationUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public class PromiseNotifier implements GenericFutureListener {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(PromiseNotifier.class);
   private final Promise[] promises;
   private final boolean logNotifyFailure;

   @SafeVarargs
   public PromiseNotifier(Promise... promises) {
      this(true, promises);
   }

   @SafeVarargs
   public PromiseNotifier(boolean logNotifyFailure, Promise... promises) {
      ObjectUtil.checkNotNull(promises, "promises");

      for(Promise promise : promises) {
         ObjectUtil.checkNotNullWithIAE(promise, "promise");
      }

      this.promises = (Promise[])((Promise;)promises).clone();
      this.logNotifyFailure = logNotifyFailure;
   }

   public static Future cascade(Future future, Promise promise) {
      return cascade(true, future, promise);
   }

   public static Future cascade(boolean logNotifyFailure, final Future future, final Promise promise) {
      promise.addListener(new FutureListener() {
         public void operationComplete(Future f) {
            if (f.isCancelled()) {
               future.cancel(false);
            }

         }
      });
      future.addListener(new PromiseNotifier(logNotifyFailure, new Promise[]{promise}) {
         public void operationComplete(Future f) throws Exception {
            if (!promise.isCancelled() || !f.isCancelled()) {
               super.operationComplete(future);
            }
         }
      });
      return future;
   }

   public void operationComplete(Future future) throws Exception {
      InternalLogger internalLogger = this.logNotifyFailure ? logger : null;
      if (future.isSuccess()) {
         V result = (V)future.get();

         for(Promise p : this.promises) {
            PromiseNotificationUtil.trySuccess(p, result, internalLogger);
         }
      } else if (future.isCancelled()) {
         for(Promise p : this.promises) {
            PromiseNotificationUtil.tryCancel(p, internalLogger);
         }
      } else {
         Throwable cause = future.cause();

         for(Promise p : this.promises) {
            PromiseNotificationUtil.tryFailure(p, cause, internalLogger);
         }
      }

   }
}
