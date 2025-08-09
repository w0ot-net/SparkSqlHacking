package org.apache.commons.collections.buffer;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.collections.BoundedCollection;
import org.apache.commons.collections.Buffer;
import org.apache.commons.collections.BufferOverflowException;
import org.apache.commons.collections.BufferUnderflowException;
import org.apache.commons.collections.iterators.AbstractIteratorDecorator;

public class BoundedBuffer extends SynchronizedBuffer implements BoundedCollection {
   private static final long serialVersionUID = 1536432911093974264L;
   private final int maximumSize;
   private final long timeout;

   public static BoundedBuffer decorate(Buffer buffer, int maximumSize) {
      return new BoundedBuffer(buffer, maximumSize, 0L);
   }

   public static BoundedBuffer decorate(Buffer buffer, int maximumSize, long timeout) {
      return new BoundedBuffer(buffer, maximumSize, timeout);
   }

   protected BoundedBuffer(Buffer buffer, int maximumSize, long timeout) {
      super(buffer);
      if (maximumSize < 1) {
         throw new IllegalArgumentException();
      } else {
         this.maximumSize = maximumSize;
         this.timeout = timeout;
      }
   }

   public Object remove() {
      synchronized(this.lock) {
         Object returnValue = this.getBuffer().remove();
         this.lock.notifyAll();
         return returnValue;
      }
   }

   public boolean add(Object o) {
      synchronized(this.lock) {
         this.timeoutWait(1);
         return this.getBuffer().add(o);
      }
   }

   public boolean addAll(Collection c) {
      synchronized(this.lock) {
         this.timeoutWait(c.size());
         return this.getBuffer().addAll(c);
      }
   }

   public Iterator iterator() {
      return new NotifyingIterator(this.collection.iterator());
   }

   private void timeoutWait(int nAdditions) {
      if (nAdditions > this.maximumSize) {
         throw new BufferOverflowException("Buffer size cannot exceed " + this.maximumSize);
      } else if (this.timeout <= 0L) {
         if (this.getBuffer().size() + nAdditions > this.maximumSize) {
            throw new BufferOverflowException("Buffer size cannot exceed " + this.maximumSize);
         }
      } else {
         long expiration = System.currentTimeMillis() + this.timeout;
         long timeLeft = expiration - System.currentTimeMillis();

         while(timeLeft > 0L && this.getBuffer().size() + nAdditions > this.maximumSize) {
            try {
               this.lock.wait(timeLeft);
               timeLeft = expiration - System.currentTimeMillis();
            } catch (InterruptedException ex) {
               PrintWriter out = new PrintWriter(new StringWriter());
               ex.printStackTrace(out);
               throw new BufferUnderflowException("Caused by InterruptedException: " + out.toString());
            }
         }

         if (this.getBuffer().size() + nAdditions > this.maximumSize) {
            throw new BufferOverflowException("Timeout expired");
         }
      }
   }

   public boolean isFull() {
      return this.size() == this.maxSize();
   }

   public int maxSize() {
      return this.maximumSize;
   }

   private class NotifyingIterator extends AbstractIteratorDecorator {
      public NotifyingIterator(Iterator it) {
         super(it);
      }

      public void remove() {
         synchronized(BoundedBuffer.this.lock) {
            this.iterator.remove();
            BoundedBuffer.this.lock.notifyAll();
         }
      }
   }
}
