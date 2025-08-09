package org.sparkproject.jpmml.model.collections;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;

public class SingletonList extends AbstractFixedSizeList implements RandomAccess, Serializable {
   private Object element = null;

   public SingletonList(Object element) {
      this.element = element;
   }

   public int size() {
      return 1;
   }

   public boolean isEmpty() {
      return false;
   }

   public boolean equals(Object object) {
      if (object instanceof List) {
         List<?> that = (List)object;
         if (that.size() == 1) {
            return Objects.equals(this.element, that.get(0));
         }
      }

      return false;
   }

   public Object get(int index) {
      switch (index) {
         case 0:
            return this.element;
         default:
            throw new IndexOutOfBoundsException();
      }
   }

   public Object set(int index, Object element) {
      switch (index) {
         case 0:
            E result = (E)this.element;
            this.element = element;
            return result;
         default:
            throw new IndexOutOfBoundsException();
      }
   }

   public List subList(int fromIndex, int toIndex) {
      if (fromIndex >= 0 && toIndex <= 1) {
         int length = toIndex - fromIndex;
         switch (length) {
            case 0:
               return Collections.emptyList();
            case 1:
               return this;
            default:
               throw new IllegalArgumentException();
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }
}
