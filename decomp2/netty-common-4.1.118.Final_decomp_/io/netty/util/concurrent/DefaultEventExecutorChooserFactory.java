package io.netty.util.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class DefaultEventExecutorChooserFactory implements EventExecutorChooserFactory {
   public static final DefaultEventExecutorChooserFactory INSTANCE = new DefaultEventExecutorChooserFactory();

   private DefaultEventExecutorChooserFactory() {
   }

   public EventExecutorChooserFactory.EventExecutorChooser newChooser(EventExecutor[] executors) {
      return (EventExecutorChooserFactory.EventExecutorChooser)(isPowerOfTwo(executors.length) ? new PowerOfTwoEventExecutorChooser(executors) : new GenericEventExecutorChooser(executors));
   }

   private static boolean isPowerOfTwo(int val) {
      return (val & -val) == val;
   }

   private static final class PowerOfTwoEventExecutorChooser implements EventExecutorChooserFactory.EventExecutorChooser {
      private final AtomicInteger idx = new AtomicInteger();
      private final EventExecutor[] executors;

      PowerOfTwoEventExecutorChooser(EventExecutor[] executors) {
         this.executors = executors;
      }

      public EventExecutor next() {
         return this.executors[this.idx.getAndIncrement() & this.executors.length - 1];
      }
   }

   private static final class GenericEventExecutorChooser implements EventExecutorChooserFactory.EventExecutorChooser {
      private final AtomicLong idx = new AtomicLong();
      private final EventExecutor[] executors;

      GenericEventExecutorChooser(EventExecutor[] executors) {
         this.executors = executors;
      }

      public EventExecutor next() {
         return this.executors[(int)Math.abs(this.idx.getAndIncrement() % (long)this.executors.length)];
      }
   }
}
