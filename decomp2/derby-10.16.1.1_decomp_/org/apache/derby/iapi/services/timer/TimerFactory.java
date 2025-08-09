package org.apache.derby.iapi.services.timer;

import java.util.TimerTask;

public interface TimerFactory {
   void schedule(TimerTask var1, long var2);

   void cancel(TimerTask var1);
}
