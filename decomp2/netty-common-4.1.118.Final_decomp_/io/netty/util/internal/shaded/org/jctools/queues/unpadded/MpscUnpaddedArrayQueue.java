package io.netty.util.internal.shaded.org.jctools.queues.unpadded;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueueUtil;
import io.netty.util.internal.shaded.org.jctools.util.UnsafeRefArrayAccess;

public class MpscUnpaddedArrayQueue extends MpscUnpaddedArrayQueueL3Pad {
   public MpscUnpaddedArrayQueue(int capacity) {
      super(capacity);
   }

   public boolean offerIfBelowThreshold(Object e, int threshold) {
      if (null == e) {
         throw new NullPointerException();
      } else {
         long mask = this.mask;
         long capacity = mask + 1L;
         long producerLimit = this.lvProducerLimit();

         long pIndex;
         do {
            pIndex = this.lvProducerIndex();
            long available = producerLimit - pIndex;
            long size = capacity - available;
            if (size >= (long)threshold) {
               long cIndex = this.lvConsumerIndex();
               size = pIndex - cIndex;
               if (size >= (long)threshold) {
                  return false;
               }

               producerLimit = cIndex + capacity;
               this.soProducerLimit(producerLimit);
            }
         } while(!this.casProducerIndex(pIndex, pIndex + 1L));

         long offset = UnsafeRefArrayAccess.calcCircularRefElementOffset(pIndex, mask);
         UnsafeRefArrayAccess.soRefElement(this.buffer, offset, e);
         return true;
      }
   }

   public boolean offer(Object e) {
      if (null == e) {
         throw new NullPointerException();
      } else {
         long mask = this.mask;
         long producerLimit = this.lvProducerLimit();

         long pIndex;
         do {
            pIndex = this.lvProducerIndex();
            if (pIndex >= producerLimit) {
               long cIndex = this.lvConsumerIndex();
               producerLimit = cIndex + mask + 1L;
               if (pIndex >= producerLimit) {
                  return false;
               }

               this.soProducerLimit(producerLimit);
            }
         } while(!this.casProducerIndex(pIndex, pIndex + 1L));

         long offset = UnsafeRefArrayAccess.calcCircularRefElementOffset(pIndex, mask);
         UnsafeRefArrayAccess.soRefElement(this.buffer, offset, e);
         return true;
      }
   }

   public final int failFastOffer(Object e) {
      if (null == e) {
         throw new NullPointerException();
      } else {
         long mask = this.mask;
         long capacity = mask + 1L;
         long pIndex = this.lvProducerIndex();
         long producerLimit = this.lvProducerLimit();
         if (pIndex >= producerLimit) {
            long cIndex = this.lvConsumerIndex();
            producerLimit = cIndex + capacity;
            if (pIndex >= producerLimit) {
               return 1;
            }

            this.soProducerLimit(producerLimit);
         }

         if (!this.casProducerIndex(pIndex, pIndex + 1L)) {
            return -1;
         } else {
            long offset = UnsafeRefArrayAccess.calcCircularRefElementOffset(pIndex, mask);
            UnsafeRefArrayAccess.soRefElement(this.buffer, offset, e);
            return 0;
         }
      }
   }

   public Object poll() {
      long cIndex = this.lpConsumerIndex();
      long offset = UnsafeRefArrayAccess.calcCircularRefElementOffset(cIndex, this.mask);
      E[] buffer = (E[])this.buffer;
      E e = (E)UnsafeRefArrayAccess.lvRefElement(buffer, offset);
      if (null == e) {
         if (cIndex == this.lvProducerIndex()) {
            return null;
         }

         do {
            e = (E)UnsafeRefArrayAccess.lvRefElement(buffer, offset);
         } while(e == null);
      }

      UnsafeRefArrayAccess.spRefElement(buffer, offset, (Object)null);
      this.soConsumerIndex(cIndex + 1L);
      return e;
   }

   public Object peek() {
      E[] buffer = (E[])this.buffer;
      long cIndex = this.lpConsumerIndex();
      long offset = UnsafeRefArrayAccess.calcCircularRefElementOffset(cIndex, this.mask);
      E e = (E)UnsafeRefArrayAccess.lvRefElement(buffer, offset);
      if (null == e) {
         if (cIndex == this.lvProducerIndex()) {
            return null;
         }

         do {
            e = (E)UnsafeRefArrayAccess.lvRefElement(buffer, offset);
         } while(e == null);
      }

      return e;
   }

   public boolean relaxedOffer(Object e) {
      return this.offer(e);
   }

   public Object relaxedPoll() {
      E[] buffer = (E[])this.buffer;
      long cIndex = this.lpConsumerIndex();
      long offset = UnsafeRefArrayAccess.calcCircularRefElementOffset(cIndex, this.mask);
      E e = (E)UnsafeRefArrayAccess.lvRefElement(buffer, offset);
      if (null == e) {
         return null;
      } else {
         UnsafeRefArrayAccess.spRefElement(buffer, offset, (Object)null);
         this.soConsumerIndex(cIndex + 1L);
         return e;
      }
   }

   public Object relaxedPeek() {
      E[] buffer = (E[])this.buffer;
      long mask = this.mask;
      long cIndex = this.lpConsumerIndex();
      return UnsafeRefArrayAccess.lvRefElement(buffer, UnsafeRefArrayAccess.calcCircularRefElementOffset(cIndex, mask));
   }

   public int drain(MessagePassingQueue.Consumer c, int limit) {
      if (null == c) {
         throw new IllegalArgumentException("c is null");
      } else if (limit < 0) {
         throw new IllegalArgumentException("limit is negative: " + limit);
      } else if (limit == 0) {
         return 0;
      } else {
         E[] buffer = (E[])this.buffer;
         long mask = this.mask;
         long cIndex = this.lpConsumerIndex();

         for(int i = 0; i < limit; ++i) {
            long index = cIndex + (long)i;
            long offset = UnsafeRefArrayAccess.calcCircularRefElementOffset(index, mask);
            E e = (E)UnsafeRefArrayAccess.lvRefElement(buffer, offset);
            if (null == e) {
               return i;
            }

            UnsafeRefArrayAccess.spRefElement(buffer, offset, (Object)null);
            this.soConsumerIndex(index + 1L);
            c.accept(e);
         }

         return limit;
      }
   }

   public int fill(MessagePassingQueue.Supplier s, int limit) {
      if (null == s) {
         throw new IllegalArgumentException("supplier is null");
      } else if (limit < 0) {
         throw new IllegalArgumentException("limit is negative:" + limit);
      } else if (limit == 0) {
         return 0;
      } else {
         long mask = this.mask;
         long capacity = mask + 1L;
         long producerLimit = this.lvProducerLimit();

         long pIndex;
         int actualLimit;
         do {
            pIndex = this.lvProducerIndex();
            long available = producerLimit - pIndex;
            if (available <= 0L) {
               long cIndex = this.lvConsumerIndex();
               producerLimit = cIndex + capacity;
               available = producerLimit - pIndex;
               if (available <= 0L) {
                  return 0;
               }

               this.soProducerLimit(producerLimit);
            }

            actualLimit = Math.min((int)available, limit);
         } while(!this.casProducerIndex(pIndex, pIndex + (long)actualLimit));

         E[] buffer = (E[])this.buffer;

         for(int i = 0; i < actualLimit; ++i) {
            long offset = UnsafeRefArrayAccess.calcCircularRefElementOffset(pIndex + (long)i, mask);
            UnsafeRefArrayAccess.soRefElement(buffer, offset, s.get());
         }

         return actualLimit;
      }
   }

   public int drain(MessagePassingQueue.Consumer c) {
      return this.drain(c, this.capacity());
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
