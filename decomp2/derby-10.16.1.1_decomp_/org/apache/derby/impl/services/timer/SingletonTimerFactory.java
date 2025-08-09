package org.apache.derby.impl.services.timer;

import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.timer.TimerFactory;
import org.apache.derby.shared.common.error.StandardException;

public class SingletonTimerFactory implements TimerFactory, ModuleControl {
   private final Timer singletonTimer;
   private final AtomicInteger cancelCount = new AtomicInteger();
   private StringBuilder warnings = new StringBuilder();

   public SingletonTimerFactory() {
      ClassLoader var1 = this.getContextClassLoader();
      if (var1 != null) {
         this.setContextClassLoader((ClassLoader)null);
      }

      this.singletonTimer = new Timer(true);
      if (var1 != null) {
         this.setContextClassLoader(var1);
      }

   }

   public void schedule(TimerTask var1, long var2) {
      this.singletonTimer.schedule(var1, var2);
   }

   public void cancel(TimerTask var1) {
      var1.cancel();
      if (this.cancelCount.incrementAndGet() % 1000 == 0) {
         this.singletonTimer.purge();
      }

   }

   public void boot(boolean var1, Properties var2) throws StandardException {
   }

   public void stop() {
      this.singletonTimer.cancel();
   }

   private ClassLoader getContextClassLoader() {
      ClassLoader var1 = Thread.currentThread().getContextClassLoader();
      return var1 != this.getClass().getClassLoader() && var1 != Thread.class.getClassLoader() ? var1 : null;
   }

   private void setContextClassLoader(ClassLoader var1) {
      Thread.currentThread().setContextClassLoader(var1);
   }

   public String getWarnings() {
      String var1 = this.warnings.toString();
      this.warnings = null;
      return "".equals(var1) ? null : var1;
   }
}
