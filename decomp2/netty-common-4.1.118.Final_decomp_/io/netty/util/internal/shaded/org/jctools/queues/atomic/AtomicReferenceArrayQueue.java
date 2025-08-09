package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import io.netty.util.internal.shaded.org.jctools.queues.IndexedQueueSizeUtil;
import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import io.netty.util.internal.shaded.org.jctools.queues.QueueProgressIndicators;
import io.netty.util.internal.shaded.org.jctools.queues.SupportsIterator;
import io.netty.util.internal.shaded.org.jctools.util.Pow2;
import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReferenceArray;

public abstract class AtomicReferenceArrayQueue extends AbstractQueue implements IndexedQueueSizeUtil.IndexedQueue, QueueProgressIndicators, MessagePassingQueue, SupportsIterator {
   protected final AtomicReferenceArray buffer;
   protected final int mask;

   public AtomicReferenceArrayQueue(int capacity) {
      int actualCapacity = Pow2.roundToPowerOfTwo(capacity);
      this.mask = actualCapacity - 1;
      this.buffer = new AtomicReferenceArray(actualCapacity);
   }

   public String toString() {
      return this.getClass().getName();
   }

   public void clear() {
      while(this.poll() != null) {
      }

   }

   public final int capacity() {
      return this.mask + 1;
   }

   public final int size() {
      return IndexedQueueSizeUtil.size(this, 1);
   }

   public final boolean isEmpty() {
      return IndexedQueueSizeUtil.isEmpty(this);
   }

   public final long currentProducerIndex() {
      return this.lvProducerIndex();
   }

   public final long currentConsumerIndex() {
      return this.lvConsumerIndex();
   }

   public final Iterator iterator() {
      long cIndex = this.lvConsumerIndex();
      long pIndex = this.lvProducerIndex();
      return new WeakIterator(cIndex, pIndex, this.mask, this.buffer);
   }

   private static class WeakIterator implements Iterator {
      private final long pIndex;
      private final int mask;
      private final AtomicReferenceArray buffer;
      private long nextIndex;
      private Object nextElement;

      WeakIterator(long cIndex, long pIndex, int mask, AtomicReferenceArray buffer) {
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
         int mask = this.mask;
         AtomicReferenceArray<E> buffer = this.buffer;

         while(this.nextIndex < this.pIndex) {
            int offset = AtomicQueueUtil.calcCircularRefElementOffset((long)(this.nextIndex++), (long)mask);
            E e = (E)AtomicQueueUtil.lvRefElement(buffer, offset);
            if (e != null) {
               return e;
            }
         }

         return null;
      }
   }
}
