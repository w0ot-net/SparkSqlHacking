package org.apache.avro.generic;

import java.util.Arrays;
import java.util.Collection;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Schema;

public class PrimitivesArrays {
   public static class IntArray extends GenericData.AbstractArray {
      private static final int[] EMPTY = new int[0];
      private int[] elements;

      public IntArray(int capacity, Schema schema) {
         super(schema);
         this.elements = EMPTY;
         if (!Schema.Type.INT.equals(schema.getElementType().getType())) {
            throw new AvroRuntimeException("Not a int array schema: " + String.valueOf(schema));
         } else {
            if (capacity != 0) {
               this.elements = new int[capacity];
            }

         }
      }

      public IntArray(Schema schema, Collection c) {
         super(schema);
         this.elements = EMPTY;
         if (c != null) {
            this.elements = new int[c.size()];
            this.addAll(c);
         }

      }

      public void clear() {
         this.size = 0;
      }

      public Integer get(int i) {
         return this.getInt(i);
      }

      public int getInt(int i) {
         if (i >= this.size) {
            throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
         } else {
            return this.elements[i];
         }
      }

      public void add(int location, Integer o) {
         if (o != null) {
            this.add(location, o);
         }
      }

      public void add(int location, int o) {
         if (location <= this.size && location >= 0) {
            if (this.size == this.elements.length) {
               int newSize = this.size + (this.size >> 1) + 1;
               this.elements = Arrays.copyOf(this.elements, newSize);
            }

            System.arraycopy(this.elements, location, this.elements, location + 1, this.size - location);
            this.elements[location] = o;
            ++this.size;
         } else {
            throw new IndexOutOfBoundsException("Index " + location + " out of bounds.");
         }
      }

      public Integer set(int i, Integer o) {
         return o == null ? null : this.set(i, o);
      }

      public int set(int i, int o) {
         if (i >= this.size) {
            throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
         } else {
            int response = this.elements[i];
            this.elements[i] = o;
            return response;
         }
      }

      public Integer remove(int i) {
         if (i >= this.size) {
            throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
         } else {
            int result = this.elements[i];
            --this.size;
            System.arraycopy(this.elements, i + 1, this.elements, i, this.size - i);
            return result;
         }
      }

      public Integer peek() {
         return this.size < this.elements.length ? this.elements[this.size] : null;
      }

      protected void swap(final int index1, final int index2) {
         int tmp = this.elements[index1];
         this.elements[index1] = this.elements[index2];
         this.elements[index2] = tmp;
      }
   }

   public static class LongArray extends GenericData.AbstractArray {
      private static final long[] EMPTY = new long[0];
      private long[] elements;

      public LongArray(int capacity, Schema schema) {
         super(schema);
         this.elements = EMPTY;
         if (!Schema.Type.LONG.equals(schema.getElementType().getType())) {
            throw new AvroRuntimeException("Not a long array schema: " + String.valueOf(schema));
         } else {
            if (capacity != 0) {
               this.elements = new long[capacity];
            }

         }
      }

      public LongArray(Schema schema, Collection c) {
         super(schema);
         this.elements = EMPTY;
         if (c != null) {
            this.elements = new long[c.size()];
            this.addAll(c);
         }

      }

      public void clear() {
         this.size = 0;
      }

      public Long get(int i) {
         return this.getLong(i);
      }

      public long getLong(int i) {
         if (i >= this.size) {
            throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
         } else {
            return this.elements[i];
         }
      }

      public void add(int location, Long o) {
         if (o != null) {
            this.add(location, o);
         }
      }

      public void add(int location, long o) {
         if (location <= this.size && location >= 0) {
            if (this.size == this.elements.length) {
               int newSize = this.size + (this.size >> 1) + 1;
               this.elements = Arrays.copyOf(this.elements, newSize);
            }

            System.arraycopy(this.elements, location, this.elements, location + 1, this.size - location);
            this.elements[location] = o;
            ++this.size;
         } else {
            throw new IndexOutOfBoundsException("Index " + location + " out of bounds.");
         }
      }

      public Long set(int i, Long o) {
         return o == null ? null : this.set(i, o);
      }

      public long set(int i, long o) {
         if (i >= this.size) {
            throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
         } else {
            long response = this.elements[i];
            this.elements[i] = o;
            return response;
         }
      }

      public Long remove(int i) {
         if (i >= this.size) {
            throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
         } else {
            long result = this.elements[i];
            --this.size;
            System.arraycopy(this.elements, i + 1, this.elements, i, this.size - i);
            return result;
         }
      }

      public Long peek() {
         return this.size < this.elements.length ? this.elements[this.size] : null;
      }

      protected void swap(final int index1, final int index2) {
         long tmp = this.elements[index1];
         this.elements[index1] = this.elements[index2];
         this.elements[index2] = tmp;
      }
   }

   public static class BooleanArray extends GenericData.AbstractArray {
      private static final byte[] EMPTY = new byte[0];
      private byte[] elements;

      public BooleanArray(int capacity, Schema schema) {
         super(schema);
         this.elements = EMPTY;
         if (!Schema.Type.BOOLEAN.equals(schema.getElementType().getType())) {
            throw new AvroRuntimeException("Not a boolean array schema: " + String.valueOf(schema));
         } else {
            if (capacity != 0) {
               this.elements = new byte[1 + capacity / 8];
            }

         }
      }

      public BooleanArray(Schema schema, Collection c) {
         super(schema);
         this.elements = EMPTY;
         if (c != null) {
            this.elements = new byte[1 + c.size() / 8];
            if (c instanceof BooleanArray) {
               BooleanArray other = (BooleanArray)c;
               this.size = other.size;
               System.arraycopy(other.elements, 0, this.elements, 0, other.elements.length);
            } else {
               this.addAll(c);
            }
         }

      }

      public void clear() {
         this.size = 0;
      }

      public Boolean get(int i) {
         return this.getBoolean(i);
      }

      public boolean getBoolean(int i) {
         if (i >= this.size) {
            throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
         } else {
            return (this.elements[i / 8] & 1 << i % 8) > 0;
         }
      }

      public boolean add(final Boolean o) {
         return o == null ? false : this.add(o);
      }

      public boolean add(final boolean o) {
         if (this.size == this.elements.length * 8) {
            int newLength = this.elements.length + (this.elements.length >> 1) + 1;
            this.elements = Arrays.copyOf(this.elements, newLength);
         }

         ++this.size;
         this.set(this.size - 1, o);
         return true;
      }

      public void add(int location, Boolean o) {
         if (o != null) {
            this.add(location, o);
         }
      }

      public void add(int location, boolean o) {
         if (location <= this.size && location >= 0) {
            if (this.size == this.elements.length * 8) {
               int newLength = this.elements.length + (this.elements.length >> 1) + 1;
               this.elements = Arrays.copyOf(this.elements, newLength);
            }

            ++this.size;

            for(int index = this.size / 8; index > location / 8; --index) {
               byte[] var10000 = this.elements;
               var10000[index] = (byte)(var10000[index] << 1);
               if (index > 0 && (this.elements[index - 1] & 256) > 0) {
                  var10000 = this.elements;
                  var10000[index] = (byte)(var10000[index] | 1);
               }
            }

            byte pos = (byte)(1 << location % 8);
            byte highbits = (byte)(~(pos + (pos - 1)));
            byte lowbits = (byte)(pos - 1);
            byte currentHigh = (byte)((this.elements[location / 8] & highbits) << 1);
            byte currentLow = (byte)(this.elements[location / 8] & lowbits);
            if (o) {
               this.elements[location / 8] = (byte)(currentHigh | currentLow | pos);
            } else {
               this.elements[location / 8] = (byte)(currentHigh | currentLow);
            }

         } else {
            throw new IndexOutOfBoundsException("Index " + location + " out of bounds.");
         }
      }

      public Boolean set(int i, Boolean o) {
         return o == null ? null : this.set(i, o);
      }

      public boolean set(int i, boolean o) {
         if (i >= this.size) {
            throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
         } else {
            boolean response = (this.elements[i / 8] & 1 << i % 8) > 0;
            if (o) {
               byte[] var10000 = this.elements;
               var10000[i / 8] = (byte)(var10000[i / 8] | 1 << i % 8);
            } else {
               byte[] var4 = this.elements;
               var4[i / 8] = (byte)(var4[i / 8] & 255 - (1 << i % 8));
            }

            return response;
         }
      }

      public Boolean remove(int i) {
         if (i >= this.size) {
            throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
         } else {
            boolean result = (this.elements[i / 8] & 1 << i % 8) > 0;
            --this.size;
            byte memo = 0;
            if (i / 8 + 1 < this.elements.length) {
               memo = (byte)((1 & this.elements[i / 8 + 1]) << 7);
            }

            for(int index = i / 8 + 1; index <= this.size / 8; ++index) {
               this.elements[index] = (byte)((this.elements[index] & 255) >>> 1);
               if (index + 1 < this.elements.length && (this.elements[index + 1] & 1) == 1) {
                  byte[] var10000 = this.elements;
                  var10000[index] = (byte)(var10000[index] | 128);
               }
            }

            byte start = (byte)((1 << (i + 1) % 8) - 1);
            byte end = (byte)(~start);
            this.elements[i / 8] = (byte)((start & 255) >>> 1 & this.elements[i / 8] | end & this.elements[i / 8] >> 1 | memo);
            return result;
         }
      }

      public Boolean peek() {
         return this.size < this.elements.length * 8 ? (this.elements[this.size / 8] & 1 << this.size % 8) > 0 : null;
      }

      protected void swap(final int index1, final int index2) {
         boolean tmp = this.get(index1);
         this.set(index1, this.get(index2));
         this.set(index2, tmp);
      }
   }

   public static class FloatArray extends GenericData.AbstractArray {
      private static final float[] EMPTY = new float[0];
      private float[] elements;

      public FloatArray(int capacity, Schema schema) {
         super(schema);
         this.elements = EMPTY;
         if (!Schema.Type.FLOAT.equals(schema.getElementType().getType())) {
            throw new AvroRuntimeException("Not a float array schema: " + String.valueOf(schema));
         } else {
            if (capacity != 0) {
               this.elements = new float[capacity];
            }

         }
      }

      public FloatArray(Schema schema, Collection c) {
         super(schema);
         this.elements = EMPTY;
         if (c != null) {
            this.elements = new float[c.size()];
            this.addAll(c);
         }

      }

      public void clear() {
         this.size = 0;
      }

      public Float get(int i) {
         return this.getFloat(i);
      }

      public float getFloat(int i) {
         if (i >= this.size) {
            throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
         } else {
            return this.elements[i];
         }
      }

      public void add(int location, Float o) {
         if (o != null) {
            this.add(location, o);
         }
      }

      public void add(int location, float o) {
         if (location <= this.size && location >= 0) {
            if (this.size == this.elements.length) {
               int newSize = this.size + (this.size >> 1) + 1;
               this.elements = Arrays.copyOf(this.elements, newSize);
            }

            System.arraycopy(this.elements, location, this.elements, location + 1, this.size - location);
            this.elements[location] = o;
            ++this.size;
         } else {
            throw new IndexOutOfBoundsException("Index " + location + " out of bounds.");
         }
      }

      public Float set(int i, Float o) {
         return o == null ? null : this.set(i, o);
      }

      public float set(int i, float o) {
         if (i >= this.size) {
            throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
         } else {
            float response = this.elements[i];
            this.elements[i] = o;
            return response;
         }
      }

      public Float remove(int i) {
         if (i >= this.size) {
            throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
         } else {
            float result = this.elements[i];
            --this.size;
            System.arraycopy(this.elements, i + 1, this.elements, i, this.size - i);
            return result;
         }
      }

      public Float peek() {
         return this.size < this.elements.length ? this.elements[this.size] : null;
      }

      protected void swap(final int index1, final int index2) {
         float tmp = this.get(index1);
         this.set(index1, this.get(index2));
         this.set(index2, tmp);
      }
   }

   public static class DoubleArray extends GenericData.AbstractArray {
      private static final double[] EMPTY = new double[0];
      private double[] elements;

      public DoubleArray(int capacity, Schema schema) {
         super(schema);
         this.elements = EMPTY;
         if (!Schema.Type.DOUBLE.equals(schema.getElementType().getType())) {
            throw new AvroRuntimeException("Not a double array schema: " + String.valueOf(schema));
         } else {
            if (capacity != 0) {
               this.elements = new double[capacity];
            }

         }
      }

      public DoubleArray(Schema schema, Collection c) {
         super(schema);
         this.elements = EMPTY;
         if (c != null) {
            this.elements = new double[c.size()];
            this.addAll(c);
         }

      }

      public void clear() {
         this.size = 0;
      }

      public Double get(int i) {
         return this.getDouble(i);
      }

      public double getDouble(int i) {
         if (i >= this.size) {
            throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
         } else {
            return this.elements[i];
         }
      }

      public void add(int location, Double o) {
         if (o != null) {
            this.add(location, (double)o.floatValue());
         }
      }

      public void add(int location, double o) {
         if (location <= this.size && location >= 0) {
            if (this.size == this.elements.length) {
               int newSize = this.size + (this.size >> 1) + 1;
               this.elements = Arrays.copyOf(this.elements, newSize);
            }

            System.arraycopy(this.elements, location, this.elements, location + 1, this.size - location);
            this.elements[location] = o;
            ++this.size;
         } else {
            throw new IndexOutOfBoundsException("Index " + location + " out of bounds.");
         }
      }

      public Double set(int i, Double o) {
         return o == null ? null : this.set(i, (double)o.floatValue());
      }

      public double set(int i, double o) {
         if (i >= this.size) {
            throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
         } else {
            double response = this.elements[i];
            this.elements[i] = o;
            return response;
         }
      }

      public Double remove(int i) {
         if (i >= this.size) {
            throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
         } else {
            double result = this.elements[i];
            --this.size;
            System.arraycopy(this.elements, i + 1, this.elements, i, this.size - i);
            return result;
         }
      }

      public Double peek() {
         return this.size < this.elements.length ? this.elements[this.size] : null;
      }

      protected void swap(final int index1, final int index2) {
         double tmp = this.get(index1);
         this.set(index1, this.get(index2));
         this.set(index2, tmp);
      }
   }
}
