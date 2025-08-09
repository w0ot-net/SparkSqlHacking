package org.codehaus.commons.compiler.util.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.codehaus.commons.compiler.util.Producer;
import org.codehaus.commons.nullanalysis.Nullable;

public class ProducerIterator implements Iterator {
   private final Producer producer;
   private static final Object UNKNOWN = new Object();
   @Nullable
   private static final Object AT_END = null;
   @Nullable
   private Object nextElement;

   public ProducerIterator(Producer producer) {
      this.nextElement = UNKNOWN;
      this.producer = producer;
   }

   public boolean hasNext() {
      if (this.nextElement == UNKNOWN) {
         this.nextElement = this.producer.produce();
      }

      return this.nextElement != AT_END;
   }

   public Object next() {
      if (this.nextElement == UNKNOWN) {
         this.nextElement = this.producer.produce();
      }

      if (this.nextElement == AT_END) {
         throw new NoSuchElementException();
      } else {
         T result = (T)this.nextElement;
         this.nextElement = UNKNOWN;

         assert result != null;

         return result;
      }
   }

   public void remove() {
      throw new UnsupportedOperationException("remove");
   }
}
