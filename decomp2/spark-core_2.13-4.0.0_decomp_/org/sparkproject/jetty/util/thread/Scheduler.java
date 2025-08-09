package org.sparkproject.jetty.util.thread;

import java.util.concurrent.TimeUnit;
import org.sparkproject.jetty.util.component.LifeCycle;

public interface Scheduler extends LifeCycle {
   Task schedule(Runnable var1, long var2, TimeUnit var4);

   public interface Task {
      boolean cancel();
   }
}
