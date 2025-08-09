package org.apache.curator;

import java.util.concurrent.TimeUnit;

public interface RetrySleeper {
   void sleepFor(long var1, TimeUnit var3) throws InterruptedException;
}
