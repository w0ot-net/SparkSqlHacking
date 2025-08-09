package org.sparkproject.jetty.util.thread;

public interface ExecutionStrategy {
   void dispatch();

   void produce();

   public interface Producer {
      Runnable produce();
   }
}
