package io.netty.util.internal.shaded.org.jctools.queues;

public interface MessagePassingQueue {
   int UNBOUNDED_CAPACITY = -1;

   boolean offer(Object var1);

   Object poll();

   Object peek();

   int size();

   void clear();

   boolean isEmpty();

   int capacity();

   boolean relaxedOffer(Object var1);

   Object relaxedPoll();

   Object relaxedPeek();

   int drain(Consumer var1, int var2);

   int fill(Supplier var1, int var2);

   int drain(Consumer var1);

   int fill(Supplier var1);

   void drain(Consumer var1, WaitStrategy var2, ExitCondition var3);

   void fill(Supplier var1, WaitStrategy var2, ExitCondition var3);

   public interface Consumer {
      void accept(Object var1);
   }

   public interface ExitCondition {
      boolean keepRunning();
   }

   public interface Supplier {
      Object get();
   }

   public interface WaitStrategy {
      int idle(int var1);
   }
}
