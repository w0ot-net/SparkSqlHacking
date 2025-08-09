package org.sparkproject.spark_core.protobuf;

import java.util.Arrays;
import java.util.RandomAccess;

final class ProtobufArrayList extends AbstractProtobufList implements RandomAccess {
   private static final Object[] EMPTY_ARRAY = new Object[0];
   private static final ProtobufArrayList EMPTY_LIST;
   private Object[] array;
   private int size;

   public static ProtobufArrayList emptyList() {
      return EMPTY_LIST;
   }

   ProtobufArrayList() {
      this(EMPTY_ARRAY, 0, true);
   }

   private ProtobufArrayList(Object[] array, int size, boolean isMutable) {
      super(isMutable);
      this.array = array;
      this.size = size;
   }

   public ProtobufArrayList mutableCopyWithCapacity(int capacity) {
      if (capacity < this.size) {
         throw new IllegalArgumentException();
      } else {
         E[] newArray = (E[])(capacity == 0 ? EMPTY_ARRAY : Arrays.copyOf(this.array, capacity));
         return new ProtobufArrayList(newArray, this.size, true);
      }
   }

   public boolean add(Object element) {
      this.ensureIsMutable();
      if (this.size == this.array.length) {
         int length = growSize(this.array.length);
         E[] newArray = (E[])Arrays.copyOf(this.array, length);
         this.array = newArray;
      }

      this.array[this.size++] = element;
      ++this.modCount;
      return true;
   }

   private static int growSize(int previousSize) {
      return Math.max(previousSize * 3 / 2 + 1, 10);
   }

   public void add(int index, Object element) {
      this.ensureIsMutable();
      if (index >= 0 && index <= this.size) {
         if (this.size < this.array.length) {
            System.arraycopy(this.array, index, this.array, index + 1, this.size - index);
         } else {
            int length = growSize(this.array.length);
            E[] newArray = (E[])createArray(length);
            System.arraycopy(this.array, 0, newArray, 0, index);
            System.arraycopy(this.array, index, newArray, index + 1, this.size - index);
            this.array = newArray;
         }

         this.array[index] = element;
         ++this.size;
         ++this.modCount;
      } else {
         throw new IndexOutOfBoundsException(this.makeOutOfBoundsExceptionMessage(index));
      }
   }

   public Object get(int index) {
      this.ensureIndexInRange(index);
      return this.array[index];
   }

   public Object remove(int index) {
      this.ensureIsMutable();
      this.ensureIndexInRange(index);
      E value = (E)this.array[index];
      if (index < this.size - 1) {
         System.arraycopy(this.array, index + 1, this.array, index, this.size - index - 1);
      }

      --this.size;
      ++this.modCount;
      return value;
   }

   public Object set(int index, Object element) {
      this.ensureIsMutable();
      this.ensureIndexInRange(index);
      E toReturn = (E)this.array[index];
      this.array[index] = element;
      ++this.modCount;
      return toReturn;
   }

   public int size() {
      return this.size;
   }

   void ensureCapacity(int minCapacity) {
      if (minCapacity > this.array.length) {
         if (this.array.length == 0) {
            this.array = new Object[Math.max(minCapacity, 10)];
         } else {
            int n;
            for(n = this.array.length; n < minCapacity; n = growSize(n)) {
            }

            this.array = Arrays.copyOf(this.array, n);
         }
      }
   }

   private static Object[] createArray(int capacity) {
      return new Object[capacity];
   }

   private void ensureIndexInRange(int index) {
      if (index < 0 || index >= this.size) {
         throw new IndexOutOfBoundsException(this.makeOutOfBoundsExceptionMessage(index));
      }
   }

   private String makeOutOfBoundsExceptionMessage(int index) {
      return "Index:" + index + ", Size:" + this.size;
   }

   static {
      EMPTY_LIST = new ProtobufArrayList(EMPTY_ARRAY, 0, false);
   }
}
