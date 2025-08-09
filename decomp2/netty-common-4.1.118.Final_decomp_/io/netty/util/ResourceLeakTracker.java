package io.netty.util;

public interface ResourceLeakTracker {
   void record();

   void record(Object var1);

   boolean close(Object var1);
}
