package pl.edu.icm.jlargearrays;

import com.sun.xml.internal.ws.encoding.soap.SerializationException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.commons.math3.util.FastMath;
import sun.misc.Cleaner;

public class ObjectLargeArray extends LargeArray {
   private static final long serialVersionUID = -4096759496772248522L;
   private Object[] data;
   private ShortLargeArray objectLengths;
   private int maxObjectLength;
   private long size;
   private byte[] byteArray;

   public ObjectLargeArray(long length) {
      this(length, 1024);
   }

   public ObjectLargeArray(long length, int maxObjectLength) {
      this(length, maxObjectLength, true);
   }

   public ObjectLargeArray(long length, int maxObjectLength, boolean zeroNativeMemory) {
      this.type = LargeArrayType.OBJECT;
      this.sizeof = 1L;
      if (length <= 0L) {
         throw new IllegalArgumentException(length + " is not a positive long value.");
      } else if (maxObjectLength <= 0) {
         throw new IllegalArgumentException(maxObjectLength + " is not a positive int value.");
      } else {
         this.length = length;
         this.size = length * (long)maxObjectLength;
         this.maxObjectLength = maxObjectLength;
         if (length > (long)getMaxSizeOf32bitArray()) {
            this.ptr = LargeArrayUtils.UNSAFE.allocateMemory(this.size * this.sizeof);
            if (zeroNativeMemory) {
               this.zeroNativeMemory(this.size);
            }

            Cleaner.create(this, new LargeArray.Deallocator(this.ptr, this.size, this.sizeof));
            MemoryCounter.increaseCounter(this.size * this.sizeof);
            this.objectLengths = new ShortLargeArray(length);
            this.byteArray = new byte[maxObjectLength];
         } else {
            this.data = new Object[(int)length];
         }

      }
   }

   public ObjectLargeArray(long length, Object constantValue) {
      this.type = LargeArrayType.OBJECT;
      this.sizeof = 1L;
      if (length <= 0L) {
         throw new IllegalArgumentException(length + " is not a positive long value");
      } else {
         this.length = length;
         this.isConstant = true;
         this.data = new Object[]{constantValue};
      }
   }

   public ObjectLargeArray(Object[] data) {
      this.type = LargeArrayType.OBJECT;
      this.sizeof = 1L;
      this.length = (long)data.length;
      this.data = data;
   }

   public ObjectLargeArray clone() {
      if (this.isConstant) {
         return new ObjectLargeArray(this.length, this.get(0L));
      } else {
         ObjectLargeArray v = new ObjectLargeArray(this.length, FastMath.max(1, this.maxObjectLength), false);
         LargeArrayUtils.arraycopy(this, 0L, v, 0L, this.length);
         return v;
      }
   }

   public boolean equals(Object o) {
      if (!super.equals(o)) {
         return false;
      } else {
         ObjectLargeArray la = (ObjectLargeArray)o;
         boolean res = this.maxObjectLength == la.maxObjectLength && this.data == la.data;
         if (this.objectLengths != null && la.objectLengths != null) {
            return res && this.objectLengths.equals(la.objectLengths);
         } else {
            return this.objectLengths == la.objectLengths ? res : false;
         }
      }
   }

   public int hashCode() {
      int hash = 29 * super.hashCode() + (this.data != null ? this.data.hashCode() : 0);
      hash = 29 * hash + (this.maxObjectLength ^ this.maxObjectLength >>> 16);
      return 29 * hash + (this.objectLengths != null ? this.objectLengths.hashCode() : 0);
   }

   public final Object get(long i) {
      if (this.ptr == 0L) {
         return this.isConstant ? this.data[0] : this.data[(int)i];
      } else {
         short objLen = this.objectLengths.getShort(i);
         if (objLen < 0) {
            return null;
         } else {
            long offset = this.sizeof * i * (long)this.maxObjectLength;

            for(int j = 0; j < objLen; ++j) {
               this.byteArray[j] = LargeArrayUtils.UNSAFE.getByte(this.ptr + offset + this.sizeof * (long)j);
            }

            return fromByteArray(this.byteArray);
         }
      }
   }

   public final Object getFromNative(long i) {
      short objLen = this.objectLengths.getShort(i);
      if (objLen < 0) {
         return null;
      } else {
         long offset = this.sizeof * i * (long)this.maxObjectLength;

         for(int j = 0; j < objLen; ++j) {
            this.byteArray[j] = LargeArrayUtils.UNSAFE.getByte(this.ptr + offset + this.sizeof * (long)j);
         }

         return fromByteArray(this.byteArray);
      }
   }

   public final boolean getBoolean(long i) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final byte getByte(long i) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final short getUnsignedByte(long i) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final short getShort(long i) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final int getInt(long i) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final long getLong(long i) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final float getFloat(long i) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final double getDouble(long i) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final Object[] getData() {
      return this.data;
   }

   public final boolean[] getBooleanData() {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final boolean[] getBooleanData(boolean[] a, long startPos, long endPos, long step) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final byte[] getByteData() {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final byte[] getByteData(byte[] a, long startPos, long endPos, long step) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final short[] getShortData() {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final short[] getShortData(short[] a, long startPos, long endPos, long step) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final int[] getIntData() {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final int[] getIntData(int[] a, long startPos, long endPos, long step) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final long[] getLongData() {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final long[] getLongData(long[] a, long startPos, long endPos, long step) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final float[] getFloatData() {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final float[] getFloatData(float[] a, long startPos, long endPos, long step) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final double[] getDoubleData() {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final double[] getDoubleData(double[] a, long startPos, long endPos, long step) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final void setToNative(long i, Object value) {
      if (value == null) {
         this.objectLengths.setShort(i, (short)-1);
      } else {
         byte[] ba = toByteArray(value);
         if (ba.length > this.maxObjectLength) {
            throw new IllegalArgumentException("Object  " + value + " is too long.");
         }

         int objLen = ba.length;
         if (objLen > 32767) {
            throw new IllegalArgumentException("Object  " + value + " is too long.");
         }

         this.objectLengths.setShort(i, (short)objLen);
         long offset = this.sizeof * i * (long)this.maxObjectLength;

         for(int j = 0; j < objLen; ++j) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + offset + this.sizeof * (long)j, ba[j]);
         }
      }

   }

   public final void set(long i, Object o) {
      if (o == null) {
         if (this.ptr != 0L) {
            this.objectLengths.setShort(i, (short)-1);
         } else {
            if (this.isConstant) {
               throw new IllegalAccessError("Constant arrays cannot be modified.");
            }

            this.data[(int)i] = null;
         }
      } else if (this.ptr != 0L) {
         byte[] ba = toByteArray(o);
         if (ba.length > this.maxObjectLength) {
            throw new IllegalArgumentException("Object  " + o + " is too long.");
         }

         int objLen = ba.length;
         if (objLen > 32767) {
            throw new IllegalArgumentException("Object  " + o + " is too long.");
         }

         this.objectLengths.setShort(i, (short)objLen);
         long offset = this.sizeof * i * (long)this.maxObjectLength;

         for(int j = 0; j < objLen; ++j) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + offset + this.sizeof * (long)j, ba[j]);
         }
      } else {
         if (this.isConstant) {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
         }

         this.data[(int)i] = o;
      }

   }

   public final void set_safe(long i, Object value) {
      if (i >= 0L && i < this.length) {
         this.set(i, value);
      } else {
         throw new ArrayIndexOutOfBoundsException(Long.toString(i));
      }
   }

   public final void setBoolean(long i, boolean value) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final void setByte(long i, byte value) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final void setUnsignedByte(long i, short value) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final void setShort(long i, short value) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final void setInt(long i, int value) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final void setLong(long i, long value) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final void setFloat(long i, float value) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   public final void setDouble(long i, double value) {
      throw new UnsupportedOperationException("Not supported yet");
   }

   private static byte[] toByteArray(Object obj) {
      ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
      ObjectOutputStream oos = null;

      try {
         oos = new ObjectOutputStream(baos);
         oos.writeObject(obj);
      } catch (Exception ex) {
         throw new SerializationException(ex);
      } finally {
         try {
            if (oos != null) {
               oos.close();
            }
         } catch (IOException var10) {
         }

      }

      return baos.toByteArray();
   }

   private static Object fromByteArray(byte[] objectData) {
      ByteArrayInputStream bais = new ByteArrayInputStream(objectData);
      ObjectInputStream ois = null;

      Object var4;
      try {
         ois = new ObjectInputStream(bais);
         Object obj = ois.readObject();
         var4 = obj;
      } catch (Exception ex) {
         throw new SerializationException(ex);
      } finally {
         try {
            if (ois != null) {
               ois.close();
            }
         } catch (IOException var12) {
         }

      }

      return var4;
   }

   public int getMaxObjectLength() {
      return this.maxObjectLength;
   }
}
