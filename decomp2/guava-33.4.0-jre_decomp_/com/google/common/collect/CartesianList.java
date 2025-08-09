package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
final class CartesianList extends AbstractList implements RandomAccess {
   private final transient ImmutableList axes;
   private final transient int[] axesSizeProduct;

   static List create(List lists) {
      ImmutableList.Builder<List<E>> axesBuilder = new ImmutableList.Builder(lists.size());

      for(List list : lists) {
         List<E> copy = ImmutableList.copyOf((Collection)list);
         if (copy.isEmpty()) {
            return ImmutableList.of();
         }

         axesBuilder.add((Object)copy);
      }

      return new CartesianList(axesBuilder.build());
   }

   CartesianList(ImmutableList axes) {
      this.axes = axes;
      int[] axesSizeProduct = new int[axes.size() + 1];
      axesSizeProduct[axes.size()] = 1;

      try {
         for(int i = axes.size() - 1; i >= 0; --i) {
            axesSizeProduct[i] = IntMath.checkedMultiply(axesSizeProduct[i + 1], ((List)axes.get(i)).size());
         }
      } catch (ArithmeticException var4) {
         throw new IllegalArgumentException("Cartesian product too large; must have size at most Integer.MAX_VALUE");
      }

      this.axesSizeProduct = axesSizeProduct;
   }

   private int getAxisIndexForProductIndex(int index, int axis) {
      return index / this.axesSizeProduct[axis + 1] % ((List)this.axes.get(axis)).size();
   }

   public int indexOf(@CheckForNull Object o) {
      if (!(o instanceof List)) {
         return -1;
      } else {
         List<?> list = (List)o;
         if (list.size() != this.axes.size()) {
            return -1;
         } else {
            ListIterator<?> itr = list.listIterator();

            int computedIndex;
            int axisIndex;
            int elemIndex;
            for(computedIndex = 0; itr.hasNext(); computedIndex += elemIndex * this.axesSizeProduct[axisIndex + 1]) {
               axisIndex = itr.nextIndex();
               elemIndex = ((List)this.axes.get(axisIndex)).indexOf(itr.next());
               if (elemIndex == -1) {
                  return -1;
               }
            }

            return computedIndex;
         }
      }
   }

   public int lastIndexOf(@CheckForNull Object o) {
      if (!(o instanceof List)) {
         return -1;
      } else {
         List<?> list = (List)o;
         if (list.size() != this.axes.size()) {
            return -1;
         } else {
            ListIterator<?> itr = list.listIterator();

            int computedIndex;
            int axisIndex;
            int elemIndex;
            for(computedIndex = 0; itr.hasNext(); computedIndex += elemIndex * this.axesSizeProduct[axisIndex + 1]) {
               axisIndex = itr.nextIndex();
               elemIndex = ((List)this.axes.get(axisIndex)).lastIndexOf(itr.next());
               if (elemIndex == -1) {
                  return -1;
               }
            }

            return computedIndex;
         }
      }
   }

   public ImmutableList get(final int index) {
      Preconditions.checkElementIndex(index, this.size());
      return new ImmutableList() {
         public int size() {
            return CartesianList.this.axes.size();
         }

         public Object get(int axis) {
            Preconditions.checkElementIndex(axis, this.size());
            int axisIndex = CartesianList.this.getAxisIndexForProductIndex(index, axis);
            return ((List)CartesianList.this.axes.get(axis)).get(axisIndex);
         }

         boolean isPartialView() {
            return true;
         }

         @J2ktIncompatible
         @GwtIncompatible
         Object writeReplace() {
            return super.writeReplace();
         }
      };
   }

   public int size() {
      return this.axesSizeProduct[0];
   }

   public boolean contains(@CheckForNull Object object) {
      if (!(object instanceof List)) {
         return false;
      } else {
         List<?> list = (List)object;
         if (list.size() != this.axes.size()) {
            return false;
         } else {
            int i = 0;

            for(Object o : list) {
               if (!((List)this.axes.get(i)).contains(o)) {
                  return false;
               }

               ++i;
            }

            return true;
         }
      }
   }
}
