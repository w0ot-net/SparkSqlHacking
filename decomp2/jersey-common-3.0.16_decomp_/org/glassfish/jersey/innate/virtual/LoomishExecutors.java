package org.glassfish.jersey.innate.virtual;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

public interface LoomishExecutors {
   ExecutorService newCachedThreadPool();

   ExecutorService newFixedThreadPool(int var1);

   ThreadFactory getThreadFactory();

   boolean isVirtual();
}
