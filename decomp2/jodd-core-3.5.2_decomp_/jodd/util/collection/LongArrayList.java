package jodd.util.collection;

public class LongArrayList {
   private long[] array;
   private int size;
   public static int initialCapacity = 10;

   public LongArrayList() {
      this(initialCapacity);
   }

   public LongArrayList(int initialCapacity) {
      if (initialCapacity < 0) {
         throw new IllegalArgumentException("Invalid capacity: " + initialCapacity);
      } else {
         this.array = new long[initialCapacity];
         this.size = 0;
      }
   }

   public LongArrayList(long[] data) {
      this.array = new long[(int)((double)data.length * 1.1) + 1];
      this.size = data.length;
      System.arraycopy(data, 0, this.array, 0, this.size);
   }

   public long[] toArray() {
      long[] result = new long[this.size];
      System.arraycopy(this.array, 0, result, 0, this.size);
      return result;
   }

   public long get(int index) {
      this.checkRange(index);
      return this.array[index];
   }

   public int size() {
      return this.size;
   }

   public long remove(int index) {
      this.checkRange(index);
      long oldval = this.array[index];
      int numtomove = this.size - index - 1;
      if (numtomove > 0) {
         System.arraycopy(this.array, index + 1, this.array, index, numtomove);
      }

      --this.size;
      return oldval;
   }

   public void removeRange(int fromIndex, int toIndex) {
      this.checkRange(fromIndex);
      this.checkRange(toIndex);
      if (fromIndex < toIndex) {
         int numtomove = this.size - toIndex;
         if (numtomove > 0) {
            System.arraycopy(this.array, toIndex, this.array, fromIndex, numtomove);
         }

         this.size -= toIndex - fromIndex;
      }
   }

   public long set(int index, long element) {
      this.checkRange(index);
      long oldval = this.array[index];
      this.array[index] = element;
      return oldval;
   }

   public void add(long element) {
      this.ensureCapacity(this.size + 1);
      this.array[this.size++] = element;
   }

   public void add(int index, long element) {
      this.checkRangeIncludingEndpoint(index);
      this.ensureCapacity(this.size + 1);
      int numtomove = this.size - index;
      System.arraycopy(this.array, index, this.array, index + 1, numtomove);
      this.array[index] = element;
      ++this.size;
   }

   public void addAll(long[] data) {
      int dataLen = data.length;
      if (dataLen != 0) {
         int newcap = this.size + (int)((double)dataLen * 1.1) + 1;
         this.ensureCapacity(newcap);
         System.arraycopy(data, 0, this.array, this.size, dataLen);
         this.size += dataLen;
      }
   }

   public void addAll(int index, long[] data) {
      int dataLen = data.length;
      if (dataLen != 0) {
         int newcap = this.size + (int)((double)dataLen * 1.1) + 1;
         this.ensureCapacity(newcap);
         System.arraycopy(this.array, index, this.array, index + dataLen, this.size - index);
         System.arraycopy(data, 0, this.array, index, dataLen);
         this.size += dataLen;
      }
   }

   public void clear() {
      this.size = 0;
   }

   public boolean contains(long data) {
      for(int i = 0; i < this.size; ++i) {
         if (this.array[i] == data) {
            return true;
         }
      }

      return false;
   }

   public int indexOf(long data) {
      for(int i = 0; i < this.size; ++i) {
         if (this.array[i] == data) {
            return i;
         }
      }

      return -1;
   }

   public int lastIndexOf(long data) {
      for(int i = this.size - 1; i >= 0; --i) {
         if (this.array[i] == data) {
            return i;
         }
      }

      return -1;
   }

   public boolean isEmpty() {
      return this.size == 0;
   }

   public void ensureCapacity(int mincap) {
      if (mincap > this.array.length) {
         int newcap = (this.array.length * 3 >> 1) + 1;
         long[] olddata = this.array;
         this.array = new long[newcap < mincap ? mincap : newcap];
         System.arraycopy(olddata, 0, this.array, 0, this.size);
      }

   }

   public void trimToSize() {
      if (this.size < this.array.length) {
         long[] olddata = this.array;
         this.array = new long[this.size];
         System.arraycopy(olddata, 0, this.array, 0, this.size);
      }

   }

   private void checkRange(int index) {
      if (index < 0 || index >= this.size) {
         throw new IndexOutOfBoundsException();
      }
   }

   private void checkRangeIncludingEndpoint(int index) {
      if (index < 0 || index > this.size) {
         throw new IndexOutOfBoundsException();
      }
   }
}
