package io.netty.util.internal.shaded.org.jctools.queues;

import io.netty.util.internal.shaded.org.jctools.util.RangeUtil;
import io.netty.util.internal.shaded.org.jctools.util.UnsafeLongArrayAccess;
import io.netty.util.internal.shaded.org.jctools.util.UnsafeRefArrayAccess;

public class MpmcArrayQueue extends MpmcArrayQueueL3Pad {
   public static final int MAX_LOOK_AHEAD_STEP = Integer.getInteger("jctools.mpmc.max.lookahead.step", 4096);
   private final int lookAheadStep;

   public MpmcArrayQueue(int capacity) {
      super(RangeUtil.checkGreaterThanOrEqual(capacity, 2, "capacity"));
      this.lookAheadStep = Math.max(2, Math.min(this.capacity() / 4, MAX_LOOK_AHEAD_STEP));
   }

   public boolean offer(Object e) {
      if (null == e) {
         throw new NullPointerException();
      } else {
         long mask = this.mask;
         long capacity = mask + 1L;
         long[] sBuffer = this.sequenceBuffer;
         long cIndex = Long.MIN_VALUE;

         long pIndex;
         long seqOffset;
         long seq;
         do {
            pIndex = this.lvProducerIndex();
            seqOffset = UnsafeLongArrayAccess.calcCircularLongElementOffset(pIndex, mask);
            seq = UnsafeLongArrayAccess.lvLongElement(sBuffer, seqOffset);
            if (seq < pIndex) {
               if (pIndex - capacity >= cIndex && pIndex - capacity >= (cIndex = this.lvConsumerIndex())) {
                  return false;
               }

               seq = pIndex + 1L;
            }
         } while(seq > pIndex || !this.casProducerIndex(pIndex, pIndex + 1L));

         UnsafeRefArrayAccess.spRefElement(this.buffer, UnsafeRefArrayAccess.calcCircularRefElementOffset(pIndex, mask), e);
         UnsafeLongArrayAccess.soLongElement(sBuffer, seqOffset, pIndex + 1L);
         return true;
      }
   }

   public Object poll() {
      long[] sBuffer = this.sequenceBuffer;
      long mask = this.mask;
      long pIndex = -1L;

      long cIndex;
      long seq;
      long seqOffset;
      long expectedSeq;
      do {
         cIndex = this.lvConsumerIndex();
         seqOffset = UnsafeLongArrayAccess.calcCircularLongElementOffset(cIndex, mask);
         seq = UnsafeLongArrayAccess.lvLongElement(sBuffer, seqOffset);
         expectedSeq = cIndex + 1L;
         if (seq < expectedSeq) {
            if (cIndex >= pIndex && cIndex == (pIndex = this.lvProducerIndex())) {
               return null;
            }

            seq = expectedSeq + 1L;
         }
      } while(seq > expectedSeq || !this.casConsumerIndex(cIndex, cIndex + 1L));

      long offset = UnsafeRefArrayAccess.calcCircularRefElementOffset(cIndex, mask);
      E e = (E)UnsafeRefArrayAccess.lpRefElement(this.buffer, offset);
      UnsafeRefArrayAccess.spRefElement(this.buffer, offset, (Object)null);
      UnsafeLongArrayAccess.soLongElement(sBuffer, seqOffset, cIndex + mask + 1L);
      return e;
   }

   public Object peek() {
      long[] sBuffer = this.sequenceBuffer;
      long mask = this.mask;
      long pIndex = -1L;

      while(true) {
         long cIndex = this.lvConsumerIndex();
         long seqOffset = UnsafeLongArrayAccess.calcCircularLongElementOffset(cIndex, mask);
         long seq = UnsafeLongArrayAccess.lvLongElement(sBuffer, seqOffset);
         long expectedSeq = cIndex + 1L;
         if (seq < expectedSeq) {
            if (cIndex >= pIndex && cIndex == (pIndex = this.lvProducerIndex())) {
               return null;
            }
         } else if (seq == expectedSeq) {
            long offset = UnsafeRefArrayAccess.calcCircularRefElementOffset(cIndex, mask);
            E e = (E)UnsafeRefArrayAccess.lvRefElement(this.buffer, offset);
            if (this.lvConsumerIndex() == cIndex) {
               return e;
            }
         }
      }
   }

   public boolean relaxedOffer(Object e) {
      if (null == e) {
         throw new NullPointerException();
      } else {
         long mask = this.mask;
         long[] sBuffer = this.sequenceBuffer;

         long pIndex;
         long seqOffset;
         long seq;
         do {
            pIndex = this.lvProducerIndex();
            seqOffset = UnsafeLongArrayAccess.calcCircularLongElementOffset(pIndex, mask);
            seq = UnsafeLongArrayAccess.lvLongElement(sBuffer, seqOffset);
            if (seq < pIndex) {
               return false;
            }
         } while(seq > pIndex || !this.casProducerIndex(pIndex, pIndex + 1L));

         UnsafeRefArrayAccess.spRefElement(this.buffer, UnsafeRefArrayAccess.calcCircularRefElementOffset(pIndex, mask), e);
         UnsafeLongArrayAccess.soLongElement(sBuffer, seqOffset, pIndex + 1L);
         return true;
      }
   }

   public Object relaxedPoll() {
      long[] sBuffer = this.sequenceBuffer;
      long mask = this.mask;

      long cIndex;
      long seqOffset;
      long seq;
      long expectedSeq;
      do {
         cIndex = this.lvConsumerIndex();
         seqOffset = UnsafeLongArrayAccess.calcCircularLongElementOffset(cIndex, mask);
         seq = UnsafeLongArrayAccess.lvLongElement(sBuffer, seqOffset);
         expectedSeq = cIndex + 1L;
         if (seq < expectedSeq) {
            return null;
         }
      } while(seq > expectedSeq || !this.casConsumerIndex(cIndex, cIndex + 1L));

      long offset = UnsafeRefArrayAccess.calcCircularRefElementOffset(cIndex, mask);
      E e = (E)UnsafeRefArrayAccess.lpRefElement(this.buffer, offset);
      UnsafeRefArrayAccess.spRefElement(this.buffer, offset, (Object)null);
      UnsafeLongArrayAccess.soLongElement(sBuffer, seqOffset, cIndex + mask + 1L);
      return e;
   }

   public Object relaxedPeek() {
      long[] sBuffer = this.sequenceBuffer;
      long mask = this.mask;

      while(true) {
         long cIndex = this.lvConsumerIndex();
         long seqOffset = UnsafeLongArrayAccess.calcCircularLongElementOffset(cIndex, mask);
         long seq = UnsafeLongArrayAccess.lvLongElement(sBuffer, seqOffset);
         long expectedSeq = cIndex + 1L;
         if (seq < expectedSeq) {
            return null;
         }

         if (seq == expectedSeq) {
            long offset = UnsafeRefArrayAccess.calcCircularRefElementOffset(cIndex, mask);
            E e = (E)UnsafeRefArrayAccess.lvRefElement(this.buffer, offset);
            if (this.lvConsumerIndex() == cIndex) {
               return e;
            }
         }
      }
   }

   public int drain(MessagePassingQueue.Consumer c, int limit) {
      if (null == c) {
         throw new IllegalArgumentException("c is null");
      } else if (limit < 0) {
         throw new IllegalArgumentException("limit is negative: " + limit);
      } else if (limit == 0) {
         return 0;
      } else {
         long[] sBuffer = this.sequenceBuffer;
         long mask = this.mask;
         E[] buffer = (E[])this.buffer;
         int maxLookAheadStep = Math.min(this.lookAheadStep, limit);

         int lookAheadStep;
         for(int consumed = 0; consumed < limit; consumed += lookAheadStep) {
            int remaining = limit - consumed;
            lookAheadStep = Math.min(remaining, maxLookAheadStep);
            long cIndex = this.lvConsumerIndex();
            long lookAheadIndex = cIndex + (long)lookAheadStep - 1L;
            long lookAheadSeqOffset = UnsafeLongArrayAccess.calcCircularLongElementOffset(lookAheadIndex, mask);
            long lookAheadSeq = UnsafeLongArrayAccess.lvLongElement(sBuffer, lookAheadSeqOffset);
            long expectedLookAheadSeq = lookAheadIndex + 1L;
            if (lookAheadSeq != expectedLookAheadSeq || !this.casConsumerIndex(cIndex, expectedLookAheadSeq)) {
               return lookAheadSeq < expectedLookAheadSeq && this.notAvailable(cIndex, mask, sBuffer, cIndex + 1L) ? consumed : consumed + this.drainOneByOne(c, remaining);
            }

            for(int i = 0; i < lookAheadStep; ++i) {
               long index = cIndex + (long)i;
               long seqOffset = UnsafeLongArrayAccess.calcCircularLongElementOffset(index, mask);
               long offset = UnsafeRefArrayAccess.calcCircularRefElementOffset(index, mask);
               long expectedSeq = index + 1L;

               while(UnsafeLongArrayAccess.lvLongElement(sBuffer, seqOffset) != expectedSeq) {
               }

               E e = (E)UnsafeRefArrayAccess.lpRefElement(buffer, offset);
               UnsafeRefArrayAccess.spRefElement(buffer, offset, (Object)null);
               UnsafeLongArrayAccess.soLongElement(sBuffer, seqOffset, index + mask + 1L);
               c.accept(e);
            }
         }

         return limit;
      }
   }

   private int drainOneByOne(MessagePassingQueue.Consumer c, int limit) {
      long[] sBuffer = this.sequenceBuffer;
      long mask = this.mask;
      E[] buffer = (E[])this.buffer;

      for(int i = 0; i < limit; ++i) {
         long cIndex;
         long seqOffset;
         long seq;
         long expectedSeq;
         do {
            cIndex = this.lvConsumerIndex();
            seqOffset = UnsafeLongArrayAccess.calcCircularLongElementOffset(cIndex, mask);
            seq = UnsafeLongArrayAccess.lvLongElement(sBuffer, seqOffset);
            expectedSeq = cIndex + 1L;
            if (seq < expectedSeq) {
               return i;
            }
         } while(seq > expectedSeq || !this.casConsumerIndex(cIndex, cIndex + 1L));

         long offset = UnsafeRefArrayAccess.calcCircularRefElementOffset(cIndex, mask);
         E e = (E)UnsafeRefArrayAccess.lpRefElement(buffer, offset);
         UnsafeRefArrayAccess.spRefElement(buffer, offset, (Object)null);
         UnsafeLongArrayAccess.soLongElement(sBuffer, seqOffset, cIndex + mask + 1L);
         c.accept(e);
      }

      return limit;
   }

   public int fill(MessagePassingQueue.Supplier s, int limit) {
      if (null == s) {
         throw new IllegalArgumentException("supplier is null");
      } else if (limit < 0) {
         throw new IllegalArgumentException("limit is negative:" + limit);
      } else if (limit == 0) {
         return 0;
      } else {
         long[] sBuffer = this.sequenceBuffer;
         long mask = this.mask;
         E[] buffer = (E[])this.buffer;
         int maxLookAheadStep = Math.min(this.lookAheadStep, limit);

         int lookAheadStep;
         for(int produced = 0; produced < limit; produced += lookAheadStep) {
            int remaining = limit - produced;
            lookAheadStep = Math.min(remaining, maxLookAheadStep);
            long pIndex = this.lvProducerIndex();
            long lookAheadIndex = pIndex + (long)lookAheadStep - 1L;
            long lookAheadSeqOffset = UnsafeLongArrayAccess.calcCircularLongElementOffset(lookAheadIndex, mask);
            long lookAheadSeq = UnsafeLongArrayAccess.lvLongElement(sBuffer, lookAheadSeqOffset);
            if (lookAheadSeq != lookAheadIndex || !this.casProducerIndex(pIndex, lookAheadIndex + 1L)) {
               return lookAheadSeq < lookAheadIndex && this.notAvailable(pIndex, mask, sBuffer, pIndex) ? produced : produced + this.fillOneByOne(s, remaining);
            }

            for(int i = 0; i < lookAheadStep; ++i) {
               long index = pIndex + (long)i;
               long seqOffset = UnsafeLongArrayAccess.calcCircularLongElementOffset(index, mask);
               long offset = UnsafeRefArrayAccess.calcCircularRefElementOffset(index, mask);

               while(UnsafeLongArrayAccess.lvLongElement(sBuffer, seqOffset) != index) {
               }

               UnsafeRefArrayAccess.soRefElement(buffer, offset, s.get());
               UnsafeLongArrayAccess.soLongElement(sBuffer, seqOffset, index + 1L);
            }
         }

         return limit;
      }
   }

   private boolean notAvailable(long index, long mask, long[] sBuffer, long expectedSeq) {
      long seqOffset = UnsafeLongArrayAccess.calcCircularLongElementOffset(index, mask);
      long seq = UnsafeLongArrayAccess.lvLongElement(sBuffer, seqOffset);
      return seq < expectedSeq;
   }

   private int fillOneByOne(MessagePassingQueue.Supplier s, int limit) {
      long[] sBuffer = this.sequenceBuffer;
      long mask = this.mask;
      E[] buffer = (E[])this.buffer;

      for(int i = 0; i < limit; ++i) {
         long pIndex;
         long seqOffset;
         long seq;
         do {
            pIndex = this.lvProducerIndex();
            seqOffset = UnsafeLongArrayAccess.calcCircularLongElementOffset(pIndex, mask);
            seq = UnsafeLongArrayAccess.lvLongElement(sBuffer, seqOffset);
            if (seq < pIndex) {
               return i;
            }
         } while(seq > pIndex || !this.casProducerIndex(pIndex, pIndex + 1L));

         UnsafeRefArrayAccess.soRefElement(buffer, UnsafeRefArrayAccess.calcCircularRefElementOffset(pIndex, mask), s.get());
         UnsafeLongArrayAccess.soLongElement(sBuffer, seqOffset, pIndex + 1L);
      }

      return limit;
   }

   public int drain(MessagePassingQueue.Consumer c) {
      return MessagePassingQueueUtil.drain(this, c);
   }

   public int fill(MessagePassingQueue.Supplier s) {
      return MessagePassingQueueUtil.fillBounded(this, s);
   }

   public void drain(MessagePassingQueue.Consumer c, MessagePassingQueue.WaitStrategy w, MessagePassingQueue.ExitCondition exit) {
      MessagePassingQueueUtil.drain(this, c, w, exit);
   }

   public void fill(MessagePassingQueue.Supplier s, MessagePassingQueue.WaitStrategy wait, MessagePassingQueue.ExitCondition exit) {
      MessagePassingQueueUtil.fill(this, s, wait, exit);
   }
}
