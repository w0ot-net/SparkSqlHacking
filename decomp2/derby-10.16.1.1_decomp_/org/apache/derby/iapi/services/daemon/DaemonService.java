package org.apache.derby.iapi.services.daemon;

public interface DaemonService {
   int TIMER_DELAY = 10000;
   String DaemonTrace = null;
   String DaemonOff = null;

   int subscribe(Serviceable var1, boolean var2);

   void unsubscribe(int var1);

   void serviceNow(int var1);

   boolean enqueue(Serviceable var1, boolean var2);

   void pause();

   void resume();

   void stop();

   void clear();

   void waitUntilQueueIsEmpty();
}
