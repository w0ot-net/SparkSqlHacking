package org.sparkproject.jpmml.model.collections;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;

public class DoubletonList extends AbstractFixedSizeList implements RandomAccess, Serializable {
   private Object first = null;
   private Object second = null;

   public DoubletonList(Object first, Object second) {
      this.first = first;
      this.second = second;
   }

   public int size() {
      return 2;
   }

   public boolean isEmpty() {
      return false;
   }

   public boolean equals(Object object) {
      if (object instanceof List) {
         List<?> that = (List)object;
         if (that.size() == 2) {
            return Objects.equals(this.first, that.get(0)) && Objects.equals(this.second, that.get(1));
         }
      }

      return false;
   }

   public Object get(int index) {
      switch (index) {
         case 0:
            return this.first;
         case 1:
            return this.second;
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
         default:
            throw new IndexOutOfBoundsException();
      }
   }

   public List subList(int fromIndex, int toIndex) {
      if (fromIndex >= 0 && toIndex <= 2) {
         int length = toIndex - fromIndex;
         switch (length) {
            case 0:
               return Collections.emptyList();
            case 1:
               return new SingletonList(this.get(fromIndex));
            case 2:
               return this;
            default:
               throw new IllegalArgumentException();
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }
}
