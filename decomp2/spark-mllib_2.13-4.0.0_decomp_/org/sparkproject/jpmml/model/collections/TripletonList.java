package org.sparkproject.jpmml.model.collections;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public class TripletonList extends AbstractFixedSizeList implements RandomAccess, Serializable {
   private Object first = null;
   private Object second = null;
   private Object third = null;

   public TripletonList(Object first, Object second, Object third) {
      this.first = first;
      this.second = second;
      this.third = third;
   }

   public int size() {
      return 3;
   }

   public boolean isEmpty() {
      return false;
   }

   public Object get(int index) {
      switch (index) {
         case 0:
            return this.first;
         case 1:
            return this.second;
         case 2:
            return this.third;
         default:
            throw new IndexOutOfBoundsException();
      }
   }

   public Object set(int index, Object element) {
      switch (index) {
         case 0:
            E result = (E)this.first;
            this.first = element;
            return result;
         case 1:
            E result = (E)this.second;
            this.second = element;
            return result;
         case 2:
            E result = (E)this.third;
            this.third = element;
            return result;
         default:
            throw new IndexOutOfBoundsException();
      }
   }

   public List subList(int fromIndex, int toIndex) {
      if (fromIndex >= 0 && toIndex <= 3) {
         int length = toIndex - fromIndex;
         switch (length) {
            case 0:
               return Collections.emptyList();
            case 1:
               return new SingletonList(this.get(fromIndex));
            case 2:
               return new DoubletonList(this.get(fromIndex), this.get(fromIndex + 1));
            case 3:
               return this;
            default:
               throw new IllegalArgumentException();
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }
}
