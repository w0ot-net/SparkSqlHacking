package io.netty.util.internal.shaded.org.jctools.queues;

import io.netty.util.internal.shaded.org.jctools.util.PortableJvmInfo;
import io.netty.util.internal.shaded.org.jctools.util.Pow2;
import io.netty.util.internal.shaded.org.jctools.util.RangeUtil;
import io.netty.util.internal.shaded.org.jctools.util.UnsafeRefArrayAccess;
import java.util.Iterator;
import java.util.NoSuchElementException;

abstract class BaseMpscLinkedArrayQueue extends BaseMpscLinkedArrayQueueColdProducerFields implements MessagePassingQueue, QueueProgressIndicators {
   private static final Object JUMP = new Object();
   private static final Object BUFFER_CONSUMED = new Object();
   private static final int CONTINUE_TO_P_INDEX_CAS = 0;
   private static final int RETRY = 1;
   private static final int QUEUE_FULL = 2;
   private static final int QUEUE_RESIZE = 3;

   public BaseMpscLinkedArrayQueue(int initialCapacity) {
      RangeUtil.checkGreaterThanOrEqual(initialCapacity, 2, "initialCapacity");
      int p2capacity = Pow2.roundToPowerOfTwo(initialCapacity);
      long mask = (long)(p2capacity - 1 << 1);
      E[] buffer = (E[])UnsafeRefArrayAccess.allocateRefArray(p2capacity + 1);
      this.producerBuffer = buffer;
      this.producerMask = mask;
      this.consumerBuffer = buffer;
      this.consumerMask = mask;
      this.soProducerLimit(mask);
   }

   public int size() {
      return IndexedQueueSizeUtil.size(this, 2);
   }

   public boolean isEmpty() {
      return (this.lvConsumerIndex() - this.lvProducerIndex()) / 2L == 0L;
   }

   public String toString() {
      return this.getClass().getName();
   }

   public boolean offer(Object e) {
      if (null == e) {
         throw new NullPointerException();
      } else {
         while(true) {
            long producerLimit = this.lvProducerLimit();
            long pIndex = this.lvProducerIndex();
            if ((pIndex & 1L) != 1L) {
               long mask = this.producerMask;
               E[] buffer = (E[])this.producerBuffer;
               if (producerLimit <= pIndex) {
                  int result = this.offerSlowPath(mask, pIndex, producerLimit);
                  switch (result) {
                     case 0:
                     default:
                        break;
                     case 1:
                        continue;
                     case 2:
                        return false;
                     case 3:
                        this.resize(mask, buffer, pIndex, e, (MessagePassingQueue.Supplier)null);
                        return true;
                  }
               }

               if (this.casProducerIndex(pIndex, pIndex + 2L)) {
                  producerLimit = LinkedArrayQueueUtil.modifiedCalcCircularRefElementOffset(pIndex, mask);
                  UnsafeRefArrayAccess.soRefElement(buffer, producerLimit, e);
                  return true;
               }
            }
         }
      }
   }

   public Object poll() {
      E[] buffer = (E[])this.consumerBuffer;
      long cIndex = this.lpConsumerIndex();
      long mask = this.consumerMask;
      long offset = LinkedArrayQueueUtil.modifiedCalcCircularRefElementOffset(cIndex, mask);
      Object e = UnsafeRefArrayAccess.lvRefElement(buffer, offset);
      if (e == null) {
         long pIndex = this.lvProducerIndex();
         if ((cIndex - pIndex) / 2L == 0L) {
            return null;
         }

         do {
            e = UnsafeRefArrayAccess.lvRefElement(buffer, offset);
         } while(e == null);
      }

      if (e == JUMP) {
         E[] nextBuffer = (E[])this.nextBuffer(buffer, mask);
         return this.newBufferPoll(nextBuffer, cIndex);
      } else {
         UnsafeRefArrayAccess.soRefElement(buffer, offset, (Object)null);
         this.soConsumerIndex(cIndex + 2L);
         return e;
      }
   }

   public Object peek() {
      E[] buffer = (E[])this.consumerBuffer;
      long cIndex = this.lpConsumerIndex();
      long mask = this.consumerMask;
      long offset = LinkedArrayQueueUtil.modifiedCalcCircularRefElementOffset(cIndex, mask);
      Object e = UnsafeRefArrayAccess.lvRefElement(buffer, offset);
      if (e == null) {
         long pIndex = this.lvProducerIndex();
         if ((cIndex - pIndex) / 2L == 0L) {
            return null;
         }

         do {
            e = UnsafeRefArrayAccess.lvRefElement(buffer, offset);
         } while(e == null);
      }

      return e == JUMP ? this.newBufferPeek(this.nextBuffer(buffer, mask), cIndex) : e;
   }

   private int offerSlowPath(long mask, long pIndex, long producerLimit) {
      long cIndex = this.lvConsumerIndex();
      long bufferCapacity = this.getCurrentBufferCapacity(mask);
      if (cIndex + bufferCapacity > pIndex) {
         return !this.casProducerLimit(producerLimit, cIndex + bufferCapacity) ? 1 : 0;
      } else if (this.availableInQueue(pIndex, cIndex) <= 0L) {
         return 2;
      } else {
         return this.casProducerIndex(pIndex, pIndex + 1L) ? 3 : 1;
      }
   }

   protected abstract long availableInQueue(long var1, long var3);

   private Object[] nextBuffer(Object[] buffer, long mask) {
      long offset = nextArrayOffset(mask);
      E[] nextBuffer = (E[])((Object[])((Object[])UnsafeRefArrayAccess.lvRefElement(buffer, offset)));
      this.consumerBuffer = nextBuffer;
      this.consumerMask = (long)(LinkedArrayQueueUtil.length(nextBuffer) - 2 << 1);
      UnsafeRefArrayAccess.soRefElement(buffer, offset, BUFFER_CONSUMED);
      return nextBuffer;
   }

   private static long nextArrayOffset(long mask) {
      return LinkedArrayQueueUtil.modifiedCalcCircularRefElementOffset(mask + 2L, Long.MAX_VALUE);
   }

   private Object newBufferPoll(Object[] nextBuffer, long cIndex) {
      long offset = LinkedArrayQueueUtil.modifiedCalcCircularRefElementOffset(cIndex, this.consumerMask);
      E n = (E)UnsafeRefArrayAccess.lvRefElement(nextBuffer, offset);
      if (n == null) {
         throw new IllegalStateException("new buffer must have at least one element");
      } else {
         UnsafeRefArrayAccess.soRefElement(nextBuffer, offset, (Object)null);
         this.soConsumerIndex(cIndex + 2L);
         return n;
      }
   }

   private Object newBufferPeek(Object[] nextBuffer, long cIndex) {
      long offset = LinkedArrayQueueUtil.modifiedCalcCircularRefElementOffset(cIndex, this.consumerMask);
      E n = (E)UnsafeRefArrayAccess.lvRefElement(nextBuffer, offset);
      if (null == n) {
         throw new IllegalStateException("new buffer must have at least one element");
      } else {
         return n;
      }
   }

   public long currentProducerIndex() {
      return this.lvProducerIndex() / 2L;
   }

   public long currentConsumerIndex() {
      return this.lvConsumerIndex() / 2L;
   }

   public abstract int capacity();

   public boolean relaxedOffer(Object e) {
      return this.offer(e);
   }

   public Object relaxedPoll() {
      E[] buffer = (E[])this.consumerBuffer;
      long cIndex = this.lpConsumerIndex();
      long mask = this.consumerMask;
      long offset = LinkedArrayQueueUtil.modifiedCalcCircularRefElementOffset(cIndex, mask);
      Object e = UnsafeRefArrayAccess.lvRefElement(buffer, offset);
      if (e == null) {
         return null;
      } else if (e == JUMP) {
         E[] nextBuffer = (E[])this.nextBuffer(buffer, mask);
         return this.newBufferPoll(nextBuffer, cIndex);
      } else {
         UnsafeRefArrayAccess.soRefElement(buffer, offset, (Object)null);
         this.soConsumerIndex(cIndex + 2L);
         return e;
      }
   }

   public Object relaxedPeek() {
      E[] buffer = (E[])this.consumerBuffer;
      long cIndex = this.lpConsumerIndex();
      long mask = this.consumerMask;
      long offset = LinkedArrayQueueUtil.modifiedCalcCircularRefElementOffset(cIndex, mask);
      Object e = UnsafeRefArrayAccess.lvRefElement(buffer, offset);
      return e == JUMP ? this.newBufferPeek(this.nextBuffer(buffer, mask), cIndex) : e;
   }

   public int fill(MessagePassingQueue.Supplier s) {
      long result = 0L;
      int capacity = this.capacity();

      do {
         int filled = this.fill(s, PortableJvmInfo.RECOMENDED_OFFER_BATCH);
         if (filled == 0) {
            return (int)result;
         }

         result += (long)filled;
      } while(result <= (long)capacity);

      return (int)result;
   }

   public int fill(MessagePassingQueue.Supplier s, int limit) {
      if (null == s) {
         throw new IllegalArgumentException("supplier is null");
      } else if (limit < 0) {
         throw new IllegalArgumentException("limit is negative:" + limit);
      } else if (limit == 0) {
         return 0;
      } else {
         while(true) {
            long producerLimit = this.lvProducerLimit();
            long pIndex = this.lvProducerIndex();
            if ((pIndex & 1L) != 1L) {
               long mask = this.producerMask;
               E[] buffer = (E[])this.producerBuffer;
               long batchIndex = Math.min(producerLimit, pIndex + 2L * (long)limit);
               if (pIndex >= producerLimit) {
                  int result = this.offerSlowPath(mask, pIndex, producerLimit);
                  switch (result) {
                     case 0:
                     case 1:
                        continue;
                     case 2:
                        return 0;
                     case 3:
                        this.resize(mask, buffer, pIndex, (Object)null, s);
                        return 1;
                  }
               }

               if (this.casProducerIndex(pIndex, batchIndex)) {
                  int claimedSlots = (int)((batchIndex - pIndex) / 2L);

                  for(int i = 0; i < claimedSlots; ++i) {
                     long offset = LinkedArrayQueueUtil.modifiedCalcCircularRefElementOffset(pIndex + 2L * (long)i, mask);
                     UnsafeRefArrayAccess.soRefElement(buffer, offset, s.get());
                  }

                  return claimedSlots;
               }
            }
         }
      }
   }

   public void fill(MessagePassingQueue.Supplier s, MessagePassingQueue.WaitStrategy wait, MessagePassingQueue.ExitCondition exit) {
      MessagePassingQueueUtil.fill(this, s, wait, exit);
   }

   public int drain(MessagePassingQueue.Consumer c) {
      return this.drain(c, this.capacity());
   }

   public int drain(MessagePassingQueue.Consumer c, int limit) {
      return MessagePassingQueueUtil.drain(this, c, limit);
   }

   public void drain(MessagePassingQueue.Consumer c, MessagePassingQueue.WaitStrategy wait, MessagePassingQueue.ExitCondition exit) {
      MessagePassingQueueUtil.drain(this, c, wait, exit);
   }

   public Iterator iterator() {
      return new WeakIterator(this.consumerBuffer, this.lvConsumerIndex(), this.lvProducerIndex());
   }

   private void resize(long oldMask, Object[] oldBuffer, long pIndex, Object e, MessagePassingQueue.Supplier s) {
      assert e != null && s == null || e == null || s != null;

      int newBufferLength = this.getNextBufferSize(oldBuffer);

      E[] newBuffer;
      try {
         newBuffer = (E[])UnsafeRefArrayAccess.allocateRefArray(newBufferLength);
      } catch (OutOfMemoryError oom) {
         assert this.lvProducerIndex() == pIndex + 1L;

         this.soProducerIndex(pIndex);
         throw oom;
      }

      this.producerBuffer = newBuffer;
      int newMask = newBufferLength - 2 << 1;
      this.producerMask = (long)newMask;
      long offsetInOld = LinkedArrayQueueUtil.modifiedCalcCircularRefElementOffset(pIndex, oldMask);
      long offsetInNew = LinkedArrayQueueUtil.modifiedCalcCircularRefElementOffset(pIndex, (long)newMask);
      UnsafeRefArrayAccess.soRefElement(newBuffer, offsetInNew, e == null ? s.get() : e);
      UnsafeRefArrayAccess.soRefElement(oldBuffer, nextArrayOffset(oldMask), newBuffer);
      long cIndex = this.lvConsumerIndex();
      long availableInQueue = this.availableInQueue(pIndex, cIndex);
      RangeUtil.checkPositive(availableInQueue, "availableInQueue");
      this.soProducerLimit(pIndex + Math.min((long)newMask, availableInQueue));
      this.soProducerIndex(pIndex + 2L);
      UnsafeRefArrayAccess.soRefElement(oldBuffer, offsetInOld, JUMP);
   }

   protected abstract int getNextBufferSize(Object[] var1);

   protected abstract long getCurrentBufferCapacity(long var1);

   private static class WeakIterator implements Iterator {
      private final long pIndex;
      private long nextIndex;
      private Object nextElement;
      private Object[] currentBuffer;
      private int mask;

      WeakIterator(Object[] currentBuffer, long cIndex, long pIndex) {
         this.pIndex = pIndex >> 1;
         this.nextIndex = cIndex >> 1;
         this.setBuffer(currentBuffer);
         this.nextElement = this.getNext();
      }

      public void remove() {
         throw new UnsupportedOperationException("remove");
      }

      public boolean hasNext() {
         return this.nextElement != null;
      }

      public Object next() {
         E e = (E)this.nextElement;
         if (e == null) {
            throw new NoSuchElementException();
         } else {
            this.nextElement = this.getNext();
            return e;
         }
      }

      private void setBuffer(Object[] buffer) {
         this.currentBuffer = buffer;
         this.mask = LinkedArrayQueueUtil.length(buffer) - 2;
      }

      private Object getNext() {
         while(true) {
            if (this.nextIndex < this.pIndex) {
               long index = (long)(this.nextIndex++);
               E e = (E)UnsafeRefArrayAccess.lvRefElement(this.currentBuffer, UnsafeRefArrayAccess.calcCircularRefElementOffset(index, (long)this.mask));
               if (e == null) {
                  continue;
               }

               if (e != BaseMpscLinkedArrayQueue.JUMP) {
                  return e;
               }

               int nextBufferIndex = this.mask + 1;
               Object nextBuffer = UnsafeRefArrayAccess.lvRefElement(this.currentBuffer, UnsafeRefArrayAccess.calcRefElementOffset((long)nextBufferIndex));
               if (nextBuffer != BaseMpscLinkedArrayQueue.BUFFER_CONSUMED && nextBuffer != null) {
                  this.setBuffer(nextBuffer);
                  e = (E)UnsafeRefArrayAccess.lvRefElement(this.currentBuffer, UnsafeRefArrayAccess.calcCircularRefElementOffset(index, (long)this.mask));
                  if (e == null) {
                     continue;
                  }

                  return e;
               }

               return null;
            }

            return null;
         }
      }
   }
}
