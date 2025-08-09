package org.apache.curator.drivers;

import java.util.concurrent.TimeUnit;

public interface TracerDriver {
   void addTrace(String var1, long var2, TimeUnit var4);

   void addCount(String var1, int var2);
}
