package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueueUtil;
import io.netty.util.internal.shaded.org.jctools.util.RangeUtil;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class MpmcAtomicArrayQueue extends MpmcAtomicArrayQueueL3Pad {
   public static final int MAX_LOOK_AHEAD_STEP = Integer.getInteger("jctools.mpmc.max.lookahead.step", 4096);
   private final int lookAheadStep;

   public MpmcAtomicArrayQueue(int capacity) {
      super(RangeUtil.checkGreaterThanOrEqual(capacity, 2, "capacity"));
      this.lookAheadStep = Math.max(2, Math.min(this.capacity() / 4, MAX_LOOK_AHEAD_STEP));
   }

   public boolean offer(Object e) {
      if (null == e) {
         throw new NullPointerException();
      } else {
         int mask = this.mask;
         long capacity = (long)(mask + 1);
         AtomicLongArray sBuffer = this.sequenceBuffer;
         long cIndex = Long.MIN_VALUE;

         long pIndex;
         int seqOffset;
         long seq;
         do {
            pIndex = this.lvProducerIndex();
            seqOffset = AtomicQueueUtil.calcCircularLongElementOffset(pIndex, mask);
            seq = AtomicQueueUtil.lvLongElement(sBuffer, seqOffset);
            if (seq < pIndex) {
               if (pIndex - capacity >= cIndex && pIndex - capacity >= (cIndex = this.lvConsumerIndex())) {
                  return false;
               }

               seq = pIndex + 1L;
            }
         } while(seq > pIndex || !this.casProducerIndex(pIndex, pIndex + 1L));

         AtomicQueueUtil.spRefElement(this.buffer, AtomicQueueUtil.calcCircularRefElementOffset(pIndex, (long)mask), e);
         AtomicQueueUtil.soLongElement(sBuffer, seqOffset, pIndex + 1L);
         return true;
      }
   }

   public Object poll() {
      AtomicLongArray sBuffer = this.sequenceBuffer;
      int mask = this.mask;
      long pIndex = -1L;

      long cIndex;
      long seq;
      int seqOffset;
      long expectedSeq;
      do {
         cIndex = this.lvConsumerIndex();
         seqOffset = AtomicQueueUtil.calcCircularLongElementOffset(cIndex, mask);
         seq = AtomicQueueUtil.lvLongElement(sBuffer, seqOffset);
         expectedSeq = cIndex + 1L;
         if (seq < expectedSeq) {
            if (cIndex >= pIndex && cIndex == (pIndex = this.lvProducerIndex())) {
               return null;
            }

            seq = expectedSeq + 1L;
         }
      } while(seq > expectedSeq || !this.casConsumerIndex(cIndex, cIndex + 1L));

      int offset = AtomicQueueUtil.calcCircularRefElementOffset(cIndex, (long)mask);
      E e = (E)AtomicQueueUtil.lpRefElement(this.buffer, offset);
      AtomicQueueUtil.spRefElement(this.buffer, offset, (Object)null);
      AtomicQueueUtil.soLongElement(sBuffer, seqOffset, cIndex + (long)mask + 1L);
      return e;
   }

   public Object peek() {
      AtomicLongArray sBuffer = this.sequenceBuffer;
      int mask = this.mask;
      long pIndex = -1L;

      while(true) {
         long cIndex = this.lvConsumerIndex();
         int seqOffset = AtomicQueueUtil.calcCircularLongElementOffset(cIndex, mask);
         long seq = AtomicQueueUtil.lvLongElement(sBuffer, seqOffset);
         long expectedSeq = cIndex + 1L;
         if (seq < expectedSeq) {
            if (cIndex >= pIndex && cIndex == (pIndex = this.lvProducerIndex())) {
               return null;
            }
         } else if (seq == expectedSeq) {
            int offset = AtomicQueueUtil.calcCircularRefElementOffset(cIndex, (long)mask);
            E e = (E)AtomicQueueUtil.lvRefElement(this.buffer, offset);
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
         int mask = this.mask;
         AtomicLongArray sBuffer = this.sequenceBuffer;

         long pIndex;
         int seqOffset;
         long seq;
         do {
            pIndex = this.lvProducerIndex();
            seqOffset = AtomicQueueUtil.calcCircularLongElementOffset(pIndex, mask);
            seq = AtomicQueueUtil.lvLongElement(sBuffer, seqOffset);
            if (seq < pIndex) {
               return false;
            }
         } while(seq > pIndex || !this.casProducerIndex(pIndex, pIndex + 1L));

         AtomicQueueUtil.spRefElement(this.buffer, AtomicQueueUtil.calcCircularRefElementOffset(pIndex, (long)mask), e);
         AtomicQueueUtil.soLongElement(sBuffer, seqOffset, pIndex + 1L);
         return true;
      }
   }

   public Object relaxedPoll() {
      AtomicLongArray sBuffer = this.sequenceBuffer;
      int mask = this.mask;

      long cIndex;
      int seqOffset;
      long seq;
      long expectedSeq;
      do {
         cIndex = this.lvConsumerIndex();
         seqOffset = AtomicQueueUtil.calcCircularLongElementOffset(cIndex, mask);
         seq = AtomicQueueUtil.lvLongElement(sBuffer, seqOffset);
         expectedSeq = cIndex + 1L;
         if (seq < expectedSeq) {
            return null;
         }
      } while(seq > expectedSeq || !this.casConsumerIndex(cIndex, cIndex + 1L));

      int offset = AtomicQueueUtil.calcCircularRefElementOffset(cIndex, (long)mask);
      E e = (E)AtomicQueueUtil.lpRefElement(this.buffer, offset);
      AtomicQueueUtil.spRefElement(this.buffer, offset, (Object)null);
      AtomicQueueUtil.soLongElement(sBuffer, seqOffset, cIndex + (long)mask + 1L);
      return e;
   }

   public Object relaxedPeek() {
      AtomicLongArray sBuffer = this.sequenceBuffer;
      int mask = this.mask;

      while(true) {
         long cIndex = this.lvConsumerIndex();
         int seqOffset = AtomicQueueUtil.calcCircularLongElementOffset(cIndex, mask);
         long seq = AtomicQueueUtil.lvLongElement(sBuffer, seqOffset);
         long expectedSeq = cIndex + 1L;
         if (seq < expectedSeq) {
            return null;
         }

         if (seq == expectedSeq) {
            int offset = AtomicQueueUtil.calcCircularRefElementOffset(cIndex, (long)mask);
            E e = (E)AtomicQueueUtil.lvRefElement(this.buffer, offset);
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
         AtomicLongArray sBuffer = this.sequenceBuffer;
         int mask = this.mask;
         AtomicReferenceArray<E> buffer = this.buffer;
         int maxLookAheadStep = Math.min(this.lookAheadStep, limit);

         int lookAheadStep;
         for(int consumed = 0; consumed < limit; consumed += lookAheadStep) {
            int remaining = limit - consumed;
            lookAheadStep = Math.min(remaining, maxLookAheadStep);
            long cIndex = this.lvConsumerIndex();
            long lookAheadIndex = cIndex + (long)lookAheadStep - 1L;
            int lookAheadSeqOffset = AtomicQueueUtil.calcCircularLongElementOffset(lookAheadIndex, mask);
            long lookAheadSeq = AtomicQueueUtil.lvLongElement(sBuffer, lookAheadSeqOffset);
            long expectedLookAheadSeq = lookAheadIndex + 1L;
            if (lookAheadSeq != expectedLookAheadSeq || !this.casConsumerIndex(cIndex, expectedLookAheadSeq)) {
               return lookAheadSeq < expectedLookAheadSeq && this.notAvailable(cIndex, mask, sBuffer, cIndex + 1L) ? consumed : consumed + this.drainOneByOne(c, remaining);
            }

            for(int i = 0; i < lookAheadStep; ++i) {
               long index = cIndex + (long)i;
               int seqOffset = AtomicQueueUtil.calcCircularLongElementOffset(index, mask);
               int offset = AtomicQueueUtil.calcCircularRefElementOffset(index, (long)mask);
               long expectedSeq = index + 1L;

               while(AtomicQueueUtil.lvLongElement(sBuffer, seqOffset) != expectedSeq) {
               }

               E e = (E)AtomicQueueUtil.lpRefElement(buffer, offset);
               AtomicQueueUtil.spRefElement(buffer, offset, (Object)null);
               AtomicQueueUtil.soLongElement(sBuffer, seqOffset, index + (long)mask + 1L);
               c.accept(e);
            }
         }

         return limit;
      }
   }

   private int drainOneByOne(MessagePassingQueue.Consumer c, int limit) {
      AtomicLongArray sBuffer = this.sequenceBuffer;
      int mask = this.mask;
      AtomicReferenceArray<E> buffer = this.buffer;

      for(int i = 0; i < limit; ++i) {
         long cIndex;
         int seqOffset;
         long seq;
         long expectedSeq;
         do {
            cIndex = this.lvConsumerIndex();
            seqOffset = AtomicQueueUtil.calcCircularLongElementOffset(cIndex, mask);
            seq = AtomicQueueUtil.lvLongElement(sBuffer, seqOffset);
            expectedSeq = cIndex + 1L;
            if (seq < expectedSeq) {
               return i;
            }
         } while(seq > expectedSeq || !this.casConsumerIndex(cIndex, cIndex + 1L));

         int offset = AtomicQueueUtil.calcCircularRefElementOffset(cIndex, (long)mask);
         E e = (E)AtomicQueueUtil.lpRefElement(buffer, offset);
         AtomicQueueUtil.spRefElement(buffer, offset, (Object)null);
         AtomicQueueUtil.soLongElement(sBuffer, seqOffset, cIndex + (long)mask + 1L);
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
         AtomicLongArray sBuffer = this.sequenceBuffer;
         int mask = this.mask;
         AtomicReferenceArray<E> buffer = this.buffer;
         int maxLookAheadStep = Math.min(this.lookAheadStep, limit);

         int lookAheadStep;
         for(int produced = 0; produced < limit; produced += lookAheadStep) {
            int remaining = limit - produced;
            lookAheadStep = Math.min(remaining, maxLookAheadStep);
            long pIndex = this.lvProducerIndex();
            long lookAheadIndex = pIndex + (long)lookAheadStep - 1L;
            int lookAheadSeqOffset = AtomicQueueUtil.calcCircularLongElementOffset(lookAheadIndex, mask);
            long lookAheadSeq = AtomicQueueUtil.lvLongElement(sBuffer, lookAheadSeqOffset);
            if (lookAheadSeq != lookAheadIndex || !this.casProducerIndex(pIndex, lookAheadIndex + 1L)) {
               return lookAheadSeq < lookAheadIndex && this.notAvailable(pIndex, mask, sBuffer, pIndex) ? produced : produced + this.fillOneByOne(s, remaining);
            }

            for(int i = 0; i < lookAheadStep; ++i) {
               long index = pIndex + (long)i;
               int seqOffset = AtomicQueueUtil.calcCircularLongElementOffset(index, mask);
               int offset = AtomicQueueUtil.calcCircularRefElementOffset(index, (long)mask);

               while(AtomicQueueUtil.lvLongElement(sBuffer, seqOffset) != index) {
               }

               AtomicQueueUtil.soRefElement(buffer, offset, s.get());
               AtomicQueueUtil.soLongElement(sBuffer, seqOffset, index + 1L);
            }
         }

         return limit;
      }
   }

   private boolean notAvailable(long index, int mask, AtomicLongArray sBuffer, long expectedSeq) {
      int seqOffset = AtomicQueueUtil.calcCircularLongElementOffset(index, mask);
      long seq = AtomicQueueUtil.lvLongElement(sBuffer, seqOffset);
      return seq < expectedSeq;
   }

   private int fillOneByOne(MessagePassingQueue.Supplier s, int limit) {
      AtomicLongArray sBuffer = this.sequenceBuffer;
      int mask = this.mask;
      AtomicReferenceArray<E> buffer = this.buffer;

      for(int i = 0; i < limit; ++i) {
         long pIndex;
         int seqOffset;
         long seq;
         do {
            pIndex = this.lvProducerIndex();
            seqOffset = AtomicQueueUtil.calcCircularLongElementOffset(pIndex, mask);
            seq = AtomicQueueUtil.lvLongElement(sBuffer, seqOffset);
            if (seq < pIndex) {
               return i;
            }
         } while(seq > pIndex || !this.casProducerIndex(pIndex, pIndex + 1L));

         AtomicQueueUtil.soRefElement(buffer, AtomicQueueUtil.calcCircularRefElementOffset(pIndex, (long)mask), s.get());
         AtomicQueueUtil.soLongElement(sBuffer, seqOffset, pIndex + 1L);
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
