package pl.edu.icm.jlargearrays;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.apache.commons.math3.util.FastMath;

public abstract class LargeArray implements Serializable, Cloneable {
   private static final long serialVersionUID = 7921589398878016801L;
   protected LargeArrayType type;
   protected long length;
   protected long sizeof;
   protected boolean isConstant = false;
   protected Object parent = null;
   protected long ptr = 0L;
   private static int maxSizeOf32bitArray = 1073741824;
   public static final int LARGEST_SUBARRAY = 1073741824;

   protected LargeArray() {
   }

   public LargeArray(Object parent, long nativePointer, LargeArrayType largeArrayType, long length) {
      this.parent = parent;
      this.ptr = nativePointer;
      this.type = largeArrayType;
      this.sizeof = largeArrayType.sizeOf();
      if (length <= 0L) {
         throw new IllegalArgumentException(length + " is not a positive long value");
      } else {
         this.length = length;
      }
   }

   public long nativePointer() {
      return this.ptr;
   }

   public long length() {
      return this.length;
   }

   public LargeArrayType getType() {
      return this.type;
   }

   public abstract Object get(long var1);

   public Object get_safe(long i) {
      if (i >= 0L && i < this.length) {
         return this.get(i);
      } else {
         throw new ArrayIndexOutOfBoundsException(Long.toString(i));
      }
   }

   public abstract Object getFromNative(long var1);

   public abstract boolean getBoolean(long var1);

   public boolean getBoolean_safe(long i) {
      if (i >= 0L && i < this.length) {
         return this.getBoolean(i);
      } else {
         throw new ArrayIndexOutOfBoundsException(Long.toString(i));
      }
   }

   public abstract byte getByte(long var1);

   public byte getByte_safe(long i) {
      if (i >= 0L && i < this.length) {
         return this.getByte(i);
      } else {
         throw new ArrayIndexOutOfBoundsException(Long.toString(i));
      }
   }

   public abstract short getUnsignedByte(long var1);

   public short getUnsignedByte_safe(long i) {
      if (i >= 0L && i < this.length) {
         return this.getUnsignedByte(i);
      } else {
         throw new ArrayIndexOutOfBoundsException(Long.toString(i));
      }
   }

   public abstract short getShort(long var1);

   public short getShort_safe(long i) {
      if (i >= 0L && i < this.length) {
         return this.getShort(i);
      } else {
         throw new ArrayIndexOutOfBoundsException(Long.toString(i));
      }
   }

   public abstract int getInt(long var1);

   public int getInt_safe(long i) {
      if (i >= 0L && i < this.length) {
         return this.getInt(i);
      } else {
         throw new ArrayIndexOutOfBoundsException(Long.toString(i));
      }
   }

   public abstract long getLong(long var1);

   public long getLong_safe(long i) {
      if (i >= 0L && i < this.length) {
         return this.getLong(i);
      } else {
         throw new ArrayIndexOutOfBoundsException(Long.toString(i));
      }
   }

   public abstract float getFloat(long var1);

   public float getFloat_safe(long i) {
      if (i >= 0L && i < this.length) {
         return this.getFloat(i);
      } else {
         throw new ArrayIndexOutOfBoundsException(Long.toString(i));
      }
   }

   public abstract double getDouble(long var1);

   public double getDouble_safe(long i) {
      if (i >= 0L && i < this.length) {
         return this.getDouble(i);
      } else {
         throw new ArrayIndexOutOfBoundsException(Long.toString(i));
      }
   }

   public abstract Object getData();

   public abstract boolean[] getBooleanData();

   public abstract boolean[] getBooleanData(boolean[] var1, long var2, long var4, long var6);

   public abstract byte[] getByteData();

   public abstract byte[] getByteData(byte[] var1, long var2, long var4, long var6);

   public abstract short[] getShortData();

   public abstract short[] getShortData(short[] var1, long var2, long var4, long var6);

   public abstract int[] getIntData();

   public abstract int[] getIntData(int[] var1, long var2, long var4, long var6);

   public abstract long[] getLongData();

   public abstract long[] getLongData(long[] var1, long var2, long var4, long var6);

   public abstract float[] getFloatData();

   public abstract float[] getFloatData(float[] var1, long var2, long var4, long var6);

   public abstract double[] getDoubleData();

   public abstract double[] getDoubleData(double[] var1, long var2, long var4, long var6);

   public void set(long i, Object value) {
      if (value instanceof Boolean) {
         this.setBoolean(i, (Boolean)value);
      } else if (value instanceof Byte) {
         this.setByte(i, (Byte)value);
      } else if (value instanceof Short) {
         this.setShort(i, (Short)value);
      } else if (value instanceof Integer) {
         this.setInt(i, (Integer)value);
      } else if (value instanceof Long) {
         this.setLong(i, (Long)value);
      } else if (value instanceof Float) {
         this.setFloat(i, (Float)value);
      } else {
         if (!(value instanceof Double)) {
            throw new IllegalArgumentException("Unsupported type.");
         }

         this.setDouble(i, (Double)value);
      }

   }

   public abstract void setToNative(long var1, Object var3);

   public void set_safe(long i, Object value) {
      if (value instanceof Boolean) {
         this.setBoolean_safe(i, (Boolean)value);
      } else if (value instanceof Byte) {
         this.setByte_safe(i, (Byte)value);
      } else if (value instanceof Short) {
         this.setShort_safe(i, (Short)value);
      } else if (value instanceof Integer) {
         this.setInt_safe(i, (Integer)value);
      } else if (value instanceof Long) {
         this.setLong_safe(i, (Long)value);
      } else if (value instanceof Float) {
         this.setFloat_safe(i, (Float)value);
      } else {
         if (!(value instanceof Double)) {
            throw new IllegalArgumentException("Unsupported type.");
         }

         this.setDouble_safe(i, (Double)value);
      }

   }

   public abstract void setBoolean(long var1, boolean var3);

   public void setBoolean_safe(long i, boolean value) {
      if (i >= 0L && i < this.length) {
         this.setBoolean(i, value);
      } else {
         throw new ArrayIndexOutOfBoundsException(Long.toString(i));
      }
   }

   public abstract void setByte(long var1, byte var3);

   public void setByte_safe(long i, byte value) {
      if (i >= 0L && i < this.length) {
         this.setByte(i, value);
      } else {
         throw new ArrayIndexOutOfBoundsException(Long.toString(i));
      }
   }

   public abstract void setUnsignedByte(long var1, short var3);

   public void setUnsignedByte_safe(long i, byte value) {
      if (i >= 0L && i < this.length) {
         this.setUnsignedByte(i, (short)value);
      } else {
         throw new ArrayIndexOutOfBoundsException(Long.toString(i));
      }
   }

   public abstract void setShort(long var1, short var3);

   public void setShort_safe(long i, short value) {
      if (i >= 0L && i < this.length) {
         this.setShort(i, value);
      } else {
         throw new ArrayIndexOutOfBoundsException(Long.toString(i));
      }
   }

   public abstract void setInt(long var1, int var3);

   public void setInt_safe(long i, int value) {
      if (i >= 0L && i < this.length) {
         this.setInt(i, value);
      } else {
         throw new ArrayIndexOutOfBoundsException(Long.toString(i));
      }
   }

   public abstract void setLong(long var1, long var3);

   public void setLong_safe(long i, long value) {
      if (i >= 0L && i < this.length) {
         this.setLong(i, value);
      } else {
         throw new ArrayIndexOutOfBoundsException(Long.toString(i));
      }
   }

   public abstract void setFloat(long var1, float var3);

   public void setFloat_safe(long i, float value) {
      if (i >= 0L && i < this.length) {
         this.setFloat(i, value);
      } else {
         throw new ArrayIndexOutOfBoundsException(Long.toString(i));
      }
   }

   public abstract void setDouble(long var1, double var3);

   public void setDouble_safe(long i, double value) {
      if (i >= 0L && i < this.length) {
         this.setDouble(i, value);
      } else {
         throw new ArrayIndexOutOfBoundsException(Long.toString(i));
      }
   }

   public boolean isLarge() {
      return this.ptr != 0L;
   }

   public boolean isNumeric() {
      return this.type.isNumericType();
   }

   public boolean isConstant() {
      return this.isConstant;
   }

   public static void setMaxSizeOf32bitArray(int index) {
      if (index < 0) {
         throw new IllegalArgumentException("index cannot be negative");
      } else {
         maxSizeOf32bitArray = index;
      }
   }

   public static int getMaxSizeOf32bitArray() {
      return maxSizeOf32bitArray;
   }

   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new InternalError();
      }
   }

   public boolean equals(Object o) {
      if (o != null && o instanceof LargeArray) {
         LargeArray la = (LargeArray)o;
         boolean equal = this.type == la.type && this.length == la.length && this.sizeof == la.sizeof && this.isConstant == la.isConstant && this.ptr == la.ptr;
         if (this.parent != null && la.parent != null) {
            equal = equal && this.parent.equals(la.parent);
         } else if (this.parent == null && la.parent == null) {
            equal = equal;
         } else {
            equal = false;
         }

         return equal;
      } else {
         return false;
      }
   }

   public int hashCode() {
      int hash = 7;
      hash = 29 * hash + (this.type != null ? this.type.hashCode() : 0);
      hash = 29 * hash + (int)(this.length ^ this.length >>> 32);
      hash = 29 * hash + (int)(this.sizeof ^ this.sizeof >>> 32);
      hash = 29 * hash + (this.isConstant ? 1 : 0);
      hash = 29 * hash + (this.parent != null ? this.parent.hashCode() : 0);
      hash = 29 * hash + (int)(this.ptr ^ this.ptr >>> 32);
      return hash;
   }

   protected void zeroNativeMemory(long size) {
      if (this.ptr != 0L) {
         int nthreads = (int)FastMath.min(size, (long)ConcurrencyUtils.getNumberOfThreads());
         if (nthreads > 2 && size >= ConcurrencyUtils.getConcurrentThreshold()) {
            long k = size / (long)nthreads;
            Future[] threads = new Future[nthreads];
            final long ptrf = this.ptr;

            for(int j = 0; j < nthreads; ++j) {
               final long firstIdx = (long)j * k;
               final long lastIdx = j == nthreads - 1 ? size : firstIdx + k;
               threads[j] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     switch (LargeArray.this.type) {
                        case LOGIC:
                        case BYTE:
                        case UNSIGNED_BYTE:
                        case STRING:
                        case OBJECT:
                           for(long k = firstIdx; k < lastIdx; ++k) {
                              LargeArrayUtils.UNSAFE.putByte(ptrf + LargeArray.this.sizeof * k, (byte)0);
                           }
                           break;
                        case SHORT:
                           for(long k = firstIdx; k < lastIdx; ++k) {
                              LargeArrayUtils.UNSAFE.putShort(ptrf + LargeArray.this.sizeof * k, (short)0);
                           }
                           break;
                        case INT:
                           for(long k = firstIdx; k < lastIdx; ++k) {
                              LargeArrayUtils.UNSAFE.putInt(ptrf + LargeArray.this.sizeof * k, 0);
                           }
                           break;
                        case LONG:
                           for(long k = firstIdx; k < lastIdx; ++k) {
                              LargeArrayUtils.UNSAFE.putLong(ptrf + LargeArray.this.sizeof * k, 0L);
                           }
                           break;
                        case FLOAT:
                           for(long k = firstIdx; k < lastIdx; ++k) {
                              LargeArrayUtils.UNSAFE.putFloat(ptrf + LargeArray.this.sizeof * k, 0.0F);
                           }
                           break;
                        case DOUBLE:
                           for(long k = firstIdx; k < lastIdx; ++k) {
                              LargeArrayUtils.UNSAFE.putDouble(ptrf + LargeArray.this.sizeof * k, (double)0.0F);
                           }
                           break;
                        default:
                           throw new IllegalArgumentException("Invalid array type.");
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(threads);
            } catch (InterruptedException var14) {
               LargeArrayUtils.UNSAFE.setMemory(this.ptr, size * this.sizeof, (byte)0);
            } catch (ExecutionException var15) {
               LargeArrayUtils.UNSAFE.setMemory(this.ptr, size * this.sizeof, (byte)0);
            }
         } else {
            LargeArrayUtils.UNSAFE.setMemory(this.ptr, size * this.sizeof, (byte)0);
         }
      }

   }

   protected static class Deallocator implements Runnable {
      private long ptr;
      private final long length;
      private final long sizeof;

      public Deallocator(long ptr, long length, long sizeof) {
         this.ptr = ptr;
         this.length = length;
         this.sizeof = sizeof;
      }

      public void run() {
         if (this.ptr != 0L) {
            LargeArrayUtils.UNSAFE.freeMemory(this.ptr);
            this.ptr = 0L;
            MemoryCounter.decreaseCounter(this.length * this.sizeof);
         }

      }
   }
}
