package org.apache.logging.log4j.core.async;

import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.jctools.queues.MpscArrayQueue;

@Plugin(
   name = "JCToolsBlockingQueue",
   category = "Core",
   elementType = "BlockingQueueFactory"
)
public class JCToolsBlockingQueueFactory implements BlockingQueueFactory {
   private final WaitStrategy waitStrategy;

   private JCToolsBlockingQueueFactory(final WaitStrategy waitStrategy) {
      this.waitStrategy = waitStrategy;
   }

   public BlockingQueue create(final int capacity) {
      return new MpscBlockingQueue(capacity, this.waitStrategy);
   }

   @PluginFactory
   public static JCToolsBlockingQueueFactory createFactory(@PluginAttribute(value = "WaitStrategy",defaultString = "PARK") final WaitStrategy waitStrategy) {
      return new JCToolsBlockingQueueFactory(waitStrategy);
   }

   private static final class MpscBlockingQueue extends MpscArrayQueue implements BlockingQueue {
      private final WaitStrategy waitStrategy;

      MpscBlockingQueue(final int capacity, final WaitStrategy waitStrategy) {
         super(capacity);
         this.waitStrategy = waitStrategy;
      }

      public int drainTo(final Collection c) {
         return this.drainTo(c, this.capacity());
      }

      public int drainTo(final Collection c, final int maxElements) {
         return this.drain((e) -> c.add(e), maxElements);
      }

      public boolean offer(final Object e, final long timeout, final TimeUnit unit) throws InterruptedException {
         int idleCounter = 0;
         long timeoutNanos = System.nanoTime() + unit.toNanos(timeout);

         while(!this.offer(e)) {
            if (System.nanoTime() - timeoutNanos > 0L) {
               return false;
            }

            idleCounter = this.waitStrategy.idle(idleCounter);
            if (Thread.interrupted()) {
               throw new InterruptedException();
            }
         }

         return true;
      }

      public Object poll(final long timeout, final TimeUnit unit) throws InterruptedException {
         int idleCounter = 0;
         long timeoutNanos = System.nanoTime() + unit.toNanos(timeout);

         do {
            E result = (E)this.poll();
            if (result != null) {
               return result;
            }

            if (System.nanoTime() - timeoutNanos > 0L) {
               return null;
            }

            idleCounter = this.waitStrategy.idle(idleCounter);
         } while(!Thread.interrupted());

         throw new InterruptedException();
      }

      public void put(final Object e) throws InterruptedException {
         int idleCounter = 0;

         while(!this.offer(e)) {
            idleCounter = this.waitStrategy.idle(idleCounter);
            if (Thread.interrupted()) {
               throw new InterruptedException();
            }
         }

      }

      public boolean offer(final Object e) {
         return this.offerIfBelowThreshold(e, this.capacity() - 32);
      }

      public int remainingCapacity() {
         return this.capacity() - this.size();
      }

      public Object take() throws InterruptedException {
         int idleCounter = 100;

         do {
            E result = (E)this.relaxedPoll();
            if (result != null) {
               return result;
            }

            idleCounter = this.waitStrategy.idle(idleCounter);
         } while(!Thread.interrupted());

         throw new InterruptedException();
      }
   }

   public static enum WaitStrategy {
      SPIN((idleCounter) -> idleCounter + 1),
      YIELD((idleCounter) -> {
         Thread.yield();
         return idleCounter + 1;
      }),
      PARK((idleCounter) -> {
         LockSupport.parkNanos(1L);
         return idleCounter + 1;
      }),
      PROGRESSIVE((idleCounter) -> {
         if (idleCounter > 200) {
            LockSupport.parkNanos(1L);
         } else if (idleCounter > 100) {
            Thread.yield();
         }

         return idleCounter + 1;
      });

      private final Idle idle;

      private int idle(final int idleCounter) {
         return this.idle.idle(idleCounter);
      }

      private WaitStrategy(final Idle idle) {
         this.idle = idle;
      }

      // $FF: synthetic method
      private static WaitStrategy[] $values() {
         return new WaitStrategy[]{SPIN, YIELD, PARK, PROGRESSIVE};
      }
   }

   private interface Idle {
      int idle(int idleCounter);
   }
}
