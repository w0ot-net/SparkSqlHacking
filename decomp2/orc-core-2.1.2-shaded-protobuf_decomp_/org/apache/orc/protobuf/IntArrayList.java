package org.apache.orc.protobuf;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class IntArrayList extends AbstractProtobufList implements Internal.IntList, RandomAccess, PrimitiveNonBoxingCollection {
   private static final IntArrayList EMPTY_LIST = new IntArrayList(new int[0], 0, false);
   private int[] array;
   private int size;

   public static IntArrayList emptyList() {
      return EMPTY_LIST;
   }

   IntArrayList() {
      this(new int[10], 0, true);
   }

   private IntArrayList(int[] other, int size, boolean isMutable) {
      super(isMutable);
      this.array = other;
      this.size = size;
   }

   protected void removeRange(int fromIndex, int toIndex) {
      this.ensureIsMutable();
      if (toIndex < fromIndex) {
         throw new IndexOutOfBoundsException("toIndex < fromIndex");
      } else {
         System.arraycopy(this.array, toIndex, this.array, fromIndex, this.size - toIndex);
         this.size -= toIndex - fromIndex;
         ++this.modCount;
      }
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof IntArrayList)) {
         return super.equals(o);
      } else {
         IntArrayList other = (IntArrayList)o;
         if (this.size != other.size) {
            return false;
         } else {
            int[] arr = other.array;

            for(int i = 0; i < this.size; ++i) {
               if (this.array[i] != arr[i]) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int result = 1;

      for(int i = 0; i < this.size; ++i) {
         result = 31 * result + this.array[i];
      }

      return result;
   }

   public Internal.IntList mutableCopyWithCapacity(int capacity) {
      if (capacity < this.size) {
         throw new IllegalArgumentException();
      } else {
         return new IntArrayList(Arrays.copyOf(this.array, capacity), this.size, true);
      }
   }

   public Integer get(int index) {
      return this.getInt(index);
   }

   public int getInt(int index) {
      this.ensureIndexInRange(index);
      return this.array[index];
   }

   public int indexOf(Object element) {
      if (!(element instanceof Integer)) {
         return -1;
      } else {
         int unboxedElement = (Integer)element;
         int numElems = this.size();

         for(int i = 0; i < numElems; ++i) {
            if (this.array[i] == unboxedElement) {
               return i;
            }
         }

         return -1;
      }
   }

   public boolean contains(Object element) {
      return this.indexOf(element) != -1;
   }

   public int size() {
      return this.size;
   }

   public Integer set(int index, Integer element) {
      return this.setInt(index, element);
   }

   public int setInt(int index, int element) {
      this.ensureIsMutable();
      this.ensureIndexInRange(index);
      int previousValue = this.array[index];
      this.array[index] = element;
      return previousValue;
   }

   public boolean add(Integer element) {
      this.addInt(element);
      return true;
   }

   public void add(int index, Integer element) {
      this.addInt(index, element);
   }

   public void addInt(int element) {
      this.ensureIsMutable();
      if (this.size == this.array.length) {
         int length = this.size * 3 / 2 + 1;
         int[] newArray = new int[length];
         System.arraycopy(this.array, 0, newArray, 0, this.size);
         this.array = newArray;
      }

      this.array[this.size++] = element;
   }

   private void addInt(int index, int element) {
      this.ensureIsMutable();
      if (index >= 0 && index <= this.size) {
         if (this.size < this.array.length) {
            System.arraycopy(this.array, index, this.array, index + 1, this.size - index);
         } else {
            int length = this.size * 3 / 2 + 1;
            int[] newArray = new int[length];
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

   public boolean addAll(Collection collection) {
      this.ensureIsMutable();
      Internal.checkNotNull(collection);
      if (!(collection instanceof IntArrayList)) {
         return super.addAll(collection);
      } else {
         IntArrayList list = (IntArrayList)collection;
         if (list.size == 0) {
            return false;
         } else {
            int overflow = Integer.MAX_VALUE - this.size;
            if (overflow < list.size) {
               throw new OutOfMemoryError();
            } else {
               int newSize = this.size + list.size;
               if (newSize > this.array.length) {
                  this.array = Arrays.copyOf(this.array, newSize);
               }

               System.arraycopy(list.array, 0, this.array, this.size, list.size);
               this.size = newSize;
               ++this.modCount;
               return true;
            }
         }
      }
   }

   public Integer remove(int index) {
      this.ensureIsMutable();
      this.ensureIndexInRange(index);
      int value = this.array[index];
      if (index < this.size - 1) {
         System.arraycopy(this.array, index + 1, this.array, index, this.size - index - 1);
      }

      --this.size;
      ++this.modCount;
      return value;
   }

   private void ensureIndexInRange(int index) {
      if (index < 0 || index >= this.size) {
         throw new IndexOutOfBoundsException(this.makeOutOfBoundsExceptionMessage(index));
      }
   }

   private String makeOutOfBoundsExceptionMessage(int index) {
      return "Index:" + index + ", Size:" + this.size;
   }
}
