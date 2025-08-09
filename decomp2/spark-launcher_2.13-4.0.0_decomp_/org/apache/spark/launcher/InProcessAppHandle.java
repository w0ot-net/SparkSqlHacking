package org.apache.spark.launcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

class InProcessAppHandle extends AbstractAppHandle {
   private static final String THREAD_NAME_FMT = "spark-app-%d: '%s'";
   private static final Logger LOG = Logger.getLogger(InProcessAppHandle.class.getName());
   private static final AtomicLong THREAD_IDS = new AtomicLong();
   private static final int MAX_APP_NAME_LEN = 16;
   private volatile Throwable error;
   private Thread app;

   InProcessAppHandle(LauncherServer server) {
      super(server);
   }

   public synchronized void kill() {
      if (!this.isDisposed()) {
         LOG.warning("kill() may leave the underlying app running in in-process mode.");
         this.setState(SparkAppHandle.State.KILLED);
         this.disconnect();
         if (this.app != null) {
            this.app.interrupt();
         }
      }

   }

   public Optional getError() {
      return Optional.ofNullable(this.error);
   }

   synchronized void start(String appName, Method main, String[] args) {
      CommandBuilderUtils.checkState(this.app == null, "Handle already started.");
      if (appName.length() > 16) {
         String var10000 = appName.substring(appName.length() - 16);
         appName = "..." + var10000;
      }

      this.app = new Thread(() -> {
         try {
            main.invoke((Object)null, args);
         } catch (Throwable var4) {
            Throwable t = var4;
            if (var4 instanceof InvocationTargetException) {
               t = var4.getCause();
            }

            LOG.log(Level.WARNING, "Application failed with exception.", t);
            this.error = t;
            this.setState(SparkAppHandle.State.FAILED);
         }

         this.dispose();
      });
      this.app.setName(String.format("spark-app-%d: '%s'", THREAD_IDS.incrementAndGet(), appName));
      this.app.start();
   }
}
