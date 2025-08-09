package org.apache.curator.drivers;

import java.util.concurrent.TimeUnit;

public abstract class AdvancedTracerDriver implements TracerDriver {
   public abstract void addTrace(OperationTrace var1);

   public abstract void addEvent(EventTrace var1);

   /** @deprecated */
   @Deprecated
   public final void addTrace(String name, long time, TimeUnit unit) {
   }

   /** @deprecated */
   @Deprecated
   public final void addCount(String name, int increment) {
   }
}
