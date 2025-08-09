package io.netty.util.internal.shaded.org.jctools.queues;

import io.netty.util.internal.shaded.org.jctools.util.Pow2;
import io.netty.util.internal.shaded.org.jctools.util.UnsafeRefArrayAccess;
import java.util.Iterator;
import java.util.NoSuchElementException;

abstract class ConcurrentCircularArrayQueue extends ConcurrentCircularArrayQueueL0Pad implements MessagePassingQueue, IndexedQueueSizeUtil.IndexedQueue, QueueProgressIndicators, SupportsIterator {
   protected final long mask;
   protected final Object[] buffer;

   ConcurrentCircularArrayQueue(int capacity) {
      int actualCapacity = Pow2.roundToPowerOfTwo(capacity);
      this.mask = (long)(actualCapacity - 1);
      this.buffer = UnsafeRefArrayAccess.allocateRefArray(actualCapacity);
   }

   public int size() {
      return IndexedQueueSizeUtil.size(this, 1);
   }

   public boolean isEmpty() {
      return IndexedQueueSizeUtil.isEmpty(this);
   }

   public String toString() {
      return this.getClass().getName();
   }

   public void clear() {
      while(this.poll() != null) {
      }

   }

   public int capacity() {
      return (int)(this.mask + 1L);
   }

   public long currentProducerIndex() {
      return this.lvProducerIndex();
   }

   public long currentConsumerIndex() {
      return this.lvConsumerIndex();
   }

   public Iterator iterator() {
      long cIndex = this.lvConsumerIndex();
      long pIndex = this.lvProducerIndex();
      return new WeakIterator(cIndex, pIndex, this.mask, this.buffer);
   }

   private static class WeakIterator implements Iterator {
      private final long pIndex;
      private final long mask;
      private final Object[] buffer;
      private long nextIndex;
      private Object nextElement;

      WeakIterator(long cIndex, long pIndex, long mask, Object[] buffer) {
         this.nextIndex = cIndex;
         this.pIndex = pIndex;
         this.mask = mask;
         this.buffer = buffer;
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

      private Object getNext() {
         while(true) {
            if (this.nextIndex < this.pIndex) {
               long offset = UnsafeRefArrayAccess.calcCircularRefElementOffset((long)(this.nextIndex++), this.mask);
               E e = (E)UnsafeRefArrayAccess.lvRefElement(this.buffer, offset);
               if (e == null) {
                  continue;
               }

               return e;
            }

            return null;
         }
      }
   }
}
