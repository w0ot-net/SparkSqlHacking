package org.apache.curator.shaded.com.google.common.primitives;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.DoubleConsumer;
import java.util.stream.DoubleStream;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.curator.shaded.com.google.errorprone.annotations.Immutable;

@Immutable
@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class ImmutableDoubleArray implements Serializable {
   private static final ImmutableDoubleArray EMPTY = new ImmutableDoubleArray(new double[0]);
   private final double[] array;
   private final transient int start;
   private final int end;

   public static ImmutableDoubleArray of() {
      return EMPTY;
   }

   public static ImmutableDoubleArray of(double e0) {
      return new ImmutableDoubleArray(new double[]{e0});
   }

   public static ImmutableDoubleArray of(double e0, double e1) {
      return new ImmutableDoubleArray(new double[]{e0, e1});
   }

   public static ImmutableDoubleArray of(double e0, double e1, double e2) {
      return new ImmutableDoubleArray(new double[]{e0, e1, e2});
   }

   public static ImmutableDoubleArray of(double e0, double e1, double e2, double e3) {
      return new ImmutableDoubleArray(new double[]{e0, e1, e2, e3});
   }

   public static ImmutableDoubleArray of(double e0, double e1, double e2, double e3, double e4) {
      return new ImmutableDoubleArray(new double[]{e0, e1, e2, e3, e4});
   }

   public static ImmutableDoubleArray of(double e0, double e1, double e2, double e3, double e4, double e5) {
      return new ImmutableDoubleArray(new double[]{e0, e1, e2, e3, e4, e5});
   }

   public static ImmutableDoubleArray of(double first, double... rest) {
      Preconditions.checkArgument(rest.length <= 2147483646, "the total number of elements must fit in an int");
      double[] array = new double[rest.length + 1];
      array[0] = first;
      System.arraycopy(rest, 0, array, 1, rest.length);
      return new ImmutableDoubleArray(array);
   }

   public static ImmutableDoubleArray copyOf(double[] values) {
      return values.length == 0 ? EMPTY : new ImmutableDoubleArray(Arrays.copyOf(values, values.length));
   }

   public static ImmutableDoubleArray copyOf(Collection values) {
      return values.isEmpty() ? EMPTY : new ImmutableDoubleArray(Doubles.toArray(values));
   }

   public static ImmutableDoubleArray copyOf(Iterable values) {
      return values instanceof Collection ? copyOf((Collection)values) : builder().addAll(values).build();
   }

   public static ImmutableDoubleArray copyOf(DoubleStream stream) {
      double[] array = stream.toArray();
      return array.length == 0 ? EMPTY : new ImmutableDoubleArray(array);
   }

   public static Builder builder(int initialCapacity) {
      Preconditions.checkArgument(initialCapacity >= 0, "Invalid initialCapacity: %s", initialCapacity);
      return new Builder(initialCapacity);
   }

   public static Builder builder() {
      return new Builder(10);
   }

   private ImmutableDoubleArray(double[] array) {
      this(array, 0, array.length);
   }

   private ImmutableDoubleArray(double[] array, int start, int end) {
      this.array = array;
      this.start = start;
      this.end = end;
   }

   public int length() {
      return this.end - this.start;
   }

   public boolean isEmpty() {
      return this.end == this.start;
   }

   public double get(int index) {
      Preconditions.checkElementIndex(index, this.length());
      return this.array[this.start + index];
   }

   public int indexOf(double target) {
      for(int i = this.start; i < this.end; ++i) {
         if (areEqual(this.array[i], target)) {
            return i - this.start;
         }
      }

      return -1;
   }

   public int lastIndexOf(double target) {
      for(int i = this.end - 1; i >= this.start; --i) {
         if (areEqual(this.array[i], target)) {
            return i - this.start;
         }
      }

      return -1;
   }

   public boolean contains(double target) {
      return this.indexOf(target) >= 0;
   }

   public void forEach(DoubleConsumer consumer) {
      Preconditions.checkNotNull(consumer);

      for(int i = this.start; i < this.end; ++i) {
         consumer.accept(this.array[i]);
      }

   }

   public DoubleStream stream() {
      return Arrays.stream(this.array, this.start, this.end);
   }

   public double[] toArray() {
      return Arrays.copyOfRange(this.array, this.start, this.end);
   }

   public ImmutableDoubleArray subArray(int startIndex, int endIndex) {
      Preconditions.checkPositionIndexes(startIndex, endIndex, this.length());
      return startIndex == endIndex ? EMPTY : new ImmutableDoubleArray(this.array, this.start + startIndex, this.start + endIndex);
   }

   private Spliterator.OfDouble spliterator() {
      return Spliterators.spliterator(this.array, this.start, this.end, 1040);
   }

   public List asList() {
      return new AsList(this);
   }

   public boolean equals(@CheckForNull Object object) {
      if (object == this) {
         return true;
      } else if (!(object instanceof ImmutableDoubleArray)) {
         return false;
      } else {
         ImmutableDoubleArray that = (ImmutableDoubleArray)object;
         if (this.length() != that.length()) {
            return false;
         } else {
            for(int i = 0; i < this.length(); ++i) {
               if (!areEqual(this.get(i), that.get(i))) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   private static boolean areEqual(double a, double b) {
      return Double.doubleToLongBits(a) == Double.doubleToLongBits(b);
   }

   public int hashCode() {
      int hash = 1;

      for(int i = this.start; i < this.end; ++i) {
         hash *= 31;
         hash += Doubles.hashCode(this.array[i]);
      }

      return hash;
   }

   public String toString() {
      if (this.isEmpty()) {
         return "[]";
      } else {
         StringBuilder builder = new StringBuilder(this.length() * 5);
         builder.append('[').append(this.array[this.start]);

         for(int i = this.start + 1; i < this.end; ++i) {
            builder.append(", ").append(this.array[i]);
         }

         builder.append(']');
         return builder.toString();
      }
   }

   public ImmutableDoubleArray trimmed() {
      return this.isPartialView() ? new ImmutableDoubleArray(this.toArray()) : this;
   }

   private boolean isPartialView() {
      return this.start > 0 || this.end < this.array.length;
   }

   Object writeReplace() {
      return this.trimmed();
   }

   Object readResolve() {
      return this.isEmpty() ? EMPTY : this;
   }

   public static final class Builder {
      private double[] array;
      private int count = 0;

      Builder(int initialCapacity) {
         this.array = new double[initialCapacity];
      }

      @CanIgnoreReturnValue
      public Builder add(double value) {
         this.ensureRoomFor(1);
         this.array[this.count] = value;
         ++this.count;
         return this;
      }

      @CanIgnoreReturnValue
      public Builder addAll(double[] values) {
         this.ensureRoomFor(values.length);
         System.arraycopy(values, 0, this.array, this.count, values.length);
         this.count += values.length;
         return this;
      }

      @CanIgnoreReturnValue
      public Builder addAll(Iterable values) {
         if (values instanceof Collection) {
            return this.addAll((Collection)values);
         } else {
            for(Double value : values) {
               this.add(value);
            }

            return this;
         }
      }

      @CanIgnoreReturnValue
      public Builder addAll(Collection values) {
         this.ensureRoomFor(values.size());

         for(Double value : values) {
            this.array[this.count++] = value;
         }

         return this;
      }

      @CanIgnoreReturnValue
      public Builder addAll(DoubleStream stream) {
         Spliterator.OfDouble spliterator = stream.spliterator();
         long size = spliterator.getExactSizeIfKnown();
         if (size > 0L) {
            this.ensureRoomFor(Ints.saturatedCast(size));
         }

         spliterator.forEachRemaining(this::add);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder addAll(ImmutableDoubleArray values) {
         this.ensureRoomFor(values.length());
         System.arraycopy(values.array, values.start, this.array, this.count, values.length());
         this.count += values.length();
         return this;
      }

      private void ensureRoomFor(int numberToAdd) {
         int newCount = this.count + numberToAdd;
         if (newCount > this.array.length) {
            this.array = Arrays.copyOf(this.array, expandedCapacity(this.array.length, newCount));
         }

      }

      private static int expandedCapacity(int oldCapacity, int minCapacity) {
         if (minCapacity < 0) {
            throw new AssertionError("cannot store more than MAX_VALUE elements");
         } else {
            int newCapacity = oldCapacity + (oldCapacity >> 1) + 1;
            if (newCapacity < minCapacity) {
               newCapacity = Integer.highestOneBit(minCapacity - 1) << 1;
            }

            if (newCapacity < 0) {
               newCapacity = Integer.MAX_VALUE;
            }

            return newCapacity;
         }
      }

      public ImmutableDoubleArray build() {
         return this.count == 0 ? ImmutableDoubleArray.EMPTY : new ImmutableDoubleArray(this.array, 0, this.count);
      }
   }

   static class AsList extends AbstractList implements RandomAccess, Serializable {
      private final ImmutableDoubleArray parent;

      private AsList(ImmutableDoubleArray parent) {
         this.parent = parent;
      }

      public int size() {
         return this.parent.length();
      }

      public Double get(int index) {
         return this.parent.get(index);
      }

      public boolean contains(@CheckForNull Object target) {
         return this.indexOf(target) >= 0;
      }

      public int indexOf(@CheckForNull Object target) {
         return target instanceof Double ? this.parent.indexOf((Double)target) : -1;
      }

      public int lastIndexOf(@CheckForNull Object target) {
         return target instanceof Double ? this.parent.lastIndexOf((Double)target) : -1;
      }

      public List subList(int fromIndex, int toIndex) {
         return this.parent.subArray(fromIndex, toIndex).asList();
      }

      public Spliterator spliterator() {
         return this.parent.spliterator();
      }

      public boolean equals(@CheckForNull Object object) {
         if (object instanceof AsList) {
            AsList that = (AsList)object;
            return this.parent.equals(that.parent);
         } else if (!(object instanceof List)) {
            return false;
         } else {
            List<?> that = (List)object;
            if (this.size() != that.size()) {
               return false;
            } else {
               int i = this.parent.start;

               for(Object element : that) {
                  if (!(element instanceof Double) || !ImmutableDoubleArray.areEqual(this.parent.array[i++], (Double)element)) {
                     return false;
                  }
               }

               return true;
            }
         }
      }

      public int hashCode() {
         return this.parent.hashCode();
      }

      public String toString() {
         return this.parent.toString();
      }
   }
}
