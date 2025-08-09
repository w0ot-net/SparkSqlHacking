package org.glassfish.jersey.client;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public interface ClientExecutor {
   Future submit(Callable var1);

   Future submit(Runnable var1);

   Future submit(Runnable var1, Object var2);

   ScheduledFuture schedule(Callable var1, long var2, TimeUnit var4);

   ScheduledFuture schedule(Runnable var1, long var2, TimeUnit var4);
}
