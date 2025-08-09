package org.apache.hadoop.hive.serde2.columnar;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableFactories;
import org.apache.hadoop.io.WritableFactory;

public class BytesRefArrayWritable implements Writable, Comparable {
   private BytesRefWritable[] bytesRefWritables;
   private int valid;

   public BytesRefArrayWritable(int capacity) {
      this.bytesRefWritables = null;
      this.valid = 0;
      if (capacity < 0) {
         throw new IllegalArgumentException("Capacity can not be negative.");
      } else {
         this.bytesRefWritables = new BytesRefWritable[0];
         this.ensureCapacity(capacity);
      }
   }

   public BytesRefArrayWritable() {
      this(10);
   }

   public int size() {
      return this.valid;
   }

   public BytesRefWritable get(int index) {
      if (index >= this.valid) {
         throw new IndexOutOfBoundsException("This BytesRefArrayWritable only has " + this.valid + " valid values.");
      } else {
         return this.bytesRefWritables[index];
      }
   }

   public BytesRefWritable unCheckedGet(int index) {
      return this.bytesRefWritables[index];
   }

   public void set(int index, BytesRefWritable bytesRefWritable) {
      this.ensureCapacity(index + 1);
      this.bytesRefWritables[index] = bytesRefWritable;
      if (this.valid <= index) {
         this.valid = index + 1;
      }

   }

   public int compareTo(BytesRefArrayWritable other) {
      if (other == null) {
         throw new IllegalArgumentException("Argument can not be null.");
      } else if (this == other) {
         return 0;
      } else {
         int sizeDiff = this.valid - other.valid;
         if (sizeDiff != 0) {
            return sizeDiff;
         } else {
            for(int i = 0; i < this.valid; ++i) {
               int res = this.bytesRefWritables[i].compareTo(other.bytesRefWritables[i]);
               if (res != 0) {
                  return res;
               }
            }

            return 0;
         }
      }
   }

   public boolean equals(Object o) {
      if (o != null && o instanceof BytesRefArrayWritable) {
         return this.compareTo((BytesRefArrayWritable)o) == 0;
      } else {
         return false;
      }
   }

   public void clear() {
      this.valid = 0;
   }

   public void resetValid(int newValidCapacity) {
      this.ensureCapacity(newValidCapacity);
      this.valid = newValidCapacity;
   }

   protected void ensureCapacity(int newCapacity) {
      int size = this.bytesRefWritables.length;
      if (size < newCapacity) {
         for(this.bytesRefWritables = (BytesRefWritable[])Arrays.copyOf(this.bytesRefWritables, newCapacity); size < newCapacity; ++size) {
            this.bytesRefWritables[size] = new BytesRefWritable();
         }
      }

   }

   public void readFields(DataInput in) throws IOException {
      int count = in.readInt();
      this.ensureCapacity(count);

      for(int i = 0; i < count; ++i) {
         this.bytesRefWritables[i].readFields(in);
      }

      this.valid = count;
   }

   public void write(DataOutput out) throws IOException {
      out.writeInt(this.valid);

      for(int i = 0; i < this.valid; ++i) {
         BytesRefWritable cu = this.bytesRefWritables[i];
         cu.write(out);
      }

   }

   static {
      WritableFactories.setFactory(BytesRefArrayWritable.class, new WritableFactory() {
         public Writable newInstance() {
            return new BytesRefArrayWritable();
         }
      });
   }
}
