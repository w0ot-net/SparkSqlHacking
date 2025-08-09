package org.apache.curator.framework.imps;

import java.util.concurrent.Executor;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.shaded.com.google.common.base.Throwables;
import org.apache.curator.utils.ThreadUtils;
import org.apache.zookeeper.KeeperException;

public class Backgrounding {
   private final boolean inBackground;
   private final Object context;
   private final BackgroundCallback callback;
   private final UnhandledErrorListener errorListener;

   Backgrounding(Object context) {
      this.inBackground = true;
      this.context = context;
      this.callback = null;
      this.errorListener = null;
   }

   Backgrounding(BackgroundCallback callback) {
      this.inBackground = true;
      this.context = null;
      this.callback = callback;
      this.errorListener = null;
   }

   Backgrounding(boolean inBackground) {
      this.inBackground = inBackground;
      this.context = null;
      this.callback = null;
      this.errorListener = null;
   }

   Backgrounding(BackgroundCallback callback, Object context) {
      this.inBackground = true;
      this.context = context;
      this.callback = callback;
      this.errorListener = null;
   }

   Backgrounding(CuratorFrameworkImpl client, BackgroundCallback callback, Object context, Executor executor) {
      this(wrapCallback(client, callback, executor), context);
   }

   Backgrounding(CuratorFrameworkImpl client, BackgroundCallback callback, Executor executor) {
      this(wrapCallback(client, callback, executor));
   }

   Backgrounding(Backgrounding rhs, UnhandledErrorListener errorListener) {
      if (rhs == null) {
         rhs = new Backgrounding();
      }

      this.inBackground = rhs.inBackground;
      this.context = rhs.context;
      this.callback = rhs.callback;
      this.errorListener = errorListener;
   }

   public Backgrounding(BackgroundCallback callback, UnhandledErrorListener errorListener) {
      this.callback = callback;
      this.errorListener = errorListener;
      this.inBackground = true;
      this.context = null;
   }

   Backgrounding() {
      this.inBackground = false;
      this.context = null;
      this.callback = null;
      this.errorListener = null;
   }

   boolean inBackground() {
      return this.inBackground;
   }

   Object getContext() {
      return this.context;
   }

   BackgroundCallback getCallback() {
      return this.callback;
   }

   void checkError(Throwable e, Watching watching) throws Exception {
      if (e != null) {
         if (this.errorListener != null) {
            this.errorListener.unhandledError("n/a", e);
         } else {
            if (e instanceof Exception) {
               throw (Exception)e;
            }

            Throwables.propagate(e);
         }
      }

   }

   private static BackgroundCallback wrapCallback(final CuratorFrameworkImpl client, final BackgroundCallback callback, final Executor executor) {
      return new BackgroundCallback() {
         public void processResult(CuratorFramework dummy, final CuratorEvent event) throws Exception {
            executor.execute(new Runnable() {
               public void run() {
                  try {
                     callback.processResult(client, event);
                  } catch (Exception var2) {
                     ThreadUtils.checkInterrupted(var2);
                     if (var2 instanceof KeeperException) {
                        client.validateConnection(client.codeToState(((KeeperException)var2).code()));
                     }

                     client.logError("Background operation result handling threw exception", var2);
                  }

               }
            });
         }
      };
   }
}
