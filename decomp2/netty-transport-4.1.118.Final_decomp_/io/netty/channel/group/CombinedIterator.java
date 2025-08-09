package io.netty.channel.group;

import io.netty.util.internal.ObjectUtil;
import java.util.Iterator;
import java.util.NoSuchElementException;

final class CombinedIterator implements Iterator {
   private final Iterator i1;
   private final Iterator i2;
   private Iterator currentIterator;

   CombinedIterator(Iterator i1, Iterator i2) {
      this.i1 = (Iterator)ObjectUtil.checkNotNull(i1, "i1");
      this.i2 = (Iterator)ObjectUtil.checkNotNull(i2, "i2");
      this.currentIterator = i1;
   }

   public boolean hasNext() {
      while(!this.currentIterator.hasNext()) {
         if (this.currentIterator != this.i1) {
            return false;
         }

         this.currentIterator = this.i2;
      }

      return true;
   }

   public Object next() {
      while(true) {
         try {
            return this.currentIterator.next();
         } catch (NoSuchElementException e) {
            if (this.currentIterator != this.i1) {
               throw e;
            }

            this.currentIterator = this.i2;
         }
      }
   }

   public void remove() {
      this.currentIterator.remove();
   }
}
