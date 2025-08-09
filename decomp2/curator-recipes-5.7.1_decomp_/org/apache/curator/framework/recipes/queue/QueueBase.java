package org.apache.curator.framework.recipes.queue;

import java.io.Closeable;
import java.util.concurrent.TimeUnit;
import org.apache.curator.framework.listen.Listenable;

public interface QueueBase extends Closeable {
   void start() throws Exception;

   Listenable getPutListenerContainer();

   void setErrorMode(ErrorMode var1);

   boolean flushPuts(long var1, TimeUnit var3) throws InterruptedException;

   int getLastMessageCount();
}
