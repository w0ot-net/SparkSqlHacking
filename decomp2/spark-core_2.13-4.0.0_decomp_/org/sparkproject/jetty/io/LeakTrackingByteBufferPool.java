package org.sparkproject.jetty.io;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.LeakDetector;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;

@ManagedObject
public class LeakTrackingByteBufferPool extends ContainerLifeCycle implements ByteBufferPool {
   private static final Logger LOG = LoggerFactory.getLogger(LeakTrackingByteBufferPool.class);
   private final LeakDetector leakDetector = new LeakDetector() {
      public String id(ByteBuffer resource) {
         return BufferUtil.toIDString(resource);
      }

      protected void leaked(LeakDetector.LeakInfo leakInfo) {
         LeakTrackingByteBufferPool.this.leaked.incrementAndGet();
         LeakTrackingByteBufferPool.this.leaked(leakInfo);
      }
   };
   private final AtomicLong leakedAcquires = new AtomicLong(0L);
   private final AtomicLong leakedReleases = new AtomicLong(0L);
   private final AtomicLong leakedRemoves = new AtomicLong(0L);
   private final AtomicLong leaked = new AtomicLong(0L);
   private final ByteBufferPool delegate;

   public LeakTrackingByteBufferPool(ByteBufferPool delegate) {
      this.delegate = delegate;
      this.addBean(this.leakDetector);
      this.addBean(delegate);
   }

   public RetainableByteBufferPool asRetainableByteBufferPool() {
      return this.delegate.asRetainableByteBufferPool();
   }

   public ByteBuffer acquire(int size, boolean direct) {
      ByteBuffer buffer = this.delegate.acquire(size, direct);
      boolean acquired = this.leakDetector.acquired(buffer);
      if (!acquired) {
         this.leakedAcquires.incrementAndGet();
         if (LOG.isDebugEnabled()) {
            LOG.debug("ByteBuffer leaked acquire for id {}", this.leakDetector.id(buffer), new Throwable("acquire"));
         }
      }

      return buffer;
   }

   public void release(ByteBuffer buffer) {
      if (buffer != null) {
         boolean released = this.leakDetector.released(buffer);
         if (!released) {
            this.leakedReleases.incrementAndGet();
            if (LOG.isDebugEnabled()) {
               LOG.debug("ByteBuffer leaked release for id {}", this.leakDetector.id(buffer), new Throwable("release"));
            }
         }

         this.delegate.release(buffer);
      }
   }

   public void remove(ByteBuffer buffer) {
      if (buffer != null) {
         boolean released = this.leakDetector.released(buffer);
         if (!released) {
            this.leakedRemoves.incrementAndGet();
            if (LOG.isDebugEnabled()) {
               LOG.debug("ByteBuffer leaked remove for id {}", this.leakDetector.id(buffer), new Throwable("remove"));
            }
         }

         this.delegate.remove(buffer);
      }
   }

   @ManagedAttribute("Clears the tracking data")
   public void clearTracking() {
      this.leakedAcquires.set(0L);
      this.leakedReleases.set(0L);
   }

   @ManagedAttribute("The number of acquires that produced a leak")
   public long getLeakedAcquires() {
      return this.leakedAcquires.get();
   }

   @ManagedAttribute("The number of releases that produced a leak")
   public long getLeakedReleases() {
      return this.leakedReleases.get();
   }

   @ManagedAttribute("The number of removes that produced a leak")
   public long getLeakedRemoves() {
      return this.leakedRemoves.get();
   }

   @ManagedAttribute("The number of resources that were leaked")
   public long getLeakedResources() {
      return this.leaked.get();
   }

   protected void leaked(LeakDetector.LeakInfo leakInfo) {
      LOG.warn("ByteBuffer {} leaked at: {}", leakInfo.getResourceDescription(), leakInfo.getStackFrames());
   }
}
