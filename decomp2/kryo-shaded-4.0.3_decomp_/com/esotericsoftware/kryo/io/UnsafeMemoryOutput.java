package com.esotericsoftware.kryo.io;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.util.UnsafeUtil;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import sun.nio.ch.DirectBuffer;

public final class UnsafeMemoryOutput extends ByteBufferOutput {
   private long bufaddress;
   private static final boolean isLittleEndian;

   public UnsafeMemoryOutput() {
      this.varIntsEnabled = false;
   }

   public UnsafeMemoryOutput(int bufferSize) {
      this(bufferSize, bufferSize);
   }

   public UnsafeMemoryOutput(int bufferSize, int maxBufferSize) {
      super(bufferSize, maxBufferSize);
      this.varIntsEnabled = false;
      this.updateBufferAddress();
   }

   public UnsafeMemoryOutput(OutputStream outputStream) {
      super(outputStream);
      this.varIntsEnabled = false;
      this.updateBufferAddress();
   }

   public UnsafeMemoryOutput(OutputStream outputStream, int bufferSize) {
      super(outputStream, bufferSize);
      this.varIntsEnabled = false;
      this.updateBufferAddress();
   }

   public UnsafeMemoryOutput(long address, int maxBufferSize) {
      super(address, maxBufferSize);
      this.varIntsEnabled = false;
      this.updateBufferAddress();
   }

   public void setBuffer(ByteBuffer buffer, int maxBufferSize) {
      super.setBuffer(buffer, maxBufferSize);
      this.updateBufferAddress();
   }

   private void updateBufferAddress() {
      this.bufaddress = ((DirectBuffer)super.niobuffer).address();
   }

   public final void writeInt(int value) throws KryoException {
      this.require(4);
      UnsafeUtil.unsafe().putInt(this.bufaddress + (long)this.position, value);
      this.position += 4;
   }

   public final void writeFloat(float value) throws KryoException {
      this.require(4);
      UnsafeUtil.unsafe().putFloat(this.bufaddress + (long)this.position, value);
      this.position += 4;
   }

   public final void writeShort(int value) throws KryoException {
      this.require(2);
      UnsafeUtil.unsafe().putShort(this.bufaddress + (long)this.position, (short)value);
      this.position += 2;
   }

   public final void writeLong(long value) throws KryoException {
      this.require(8);
      UnsafeUtil.unsafe().putLong(this.bufaddress + (long)this.position, value);
      this.position += 8;
   }

   public final void writeByte(int value) throws KryoException {
      super.niobuffer.position(this.position);
      super.writeByte(value);
   }

   public void writeByte(byte value) throws KryoException {
      super.niobuffer.position(this.position);
      super.writeByte(value);
   }

   public final void writeBoolean(boolean value) throws KryoException {
      super.niobuffer.position(this.position);
      super.writeBoolean(value);
   }

   public final void writeChar(char value) throws KryoException {
      this.require(2);
      UnsafeUtil.unsafe().putChar(this.bufaddress + (long)this.position, value);
      this.position += 2;
   }

   public final void writeDouble(double value) throws KryoException {
      this.require(8);
      UnsafeUtil.unsafe().putDouble(this.bufaddress + (long)this.position, value);
      this.position += 8;
   }

   public final int writeInt(int value, boolean optimizePositive) throws KryoException {
      if (!this.varIntsEnabled) {
         this.writeInt(value);
         return 4;
      } else {
         return this.writeVarInt(value, optimizePositive);
      }
   }

   public final int writeLong(long value, boolean optimizePositive) throws KryoException {
      if (!this.varIntsEnabled) {
         this.writeLong(value);
         return 8;
      } else {
         return this.writeVarLong(value, optimizePositive);
      }
   }

   public final int writeVarInt(int val, boolean optimizePositive) throws KryoException {
      long value = (long)val;
      if (!optimizePositive) {
         value = value << 1 ^ value >> 31;
      }

      long varInt = 0L;
      varInt = value & 127L;
      value >>>= 7;
      if (value == 0L) {
         this.writeByte((byte)((int)varInt));
         return 1;
      } else {
         varInt |= 128L;
         varInt |= (value & 127L) << 8;
         value >>>= 7;
         if (value == 0L) {
            this.writeLittleEndianInt((int)varInt);
            this.position -= 2;
            return 2;
         } else {
            varInt |= 32768L;
            varInt |= (value & 127L) << 16;
            value >>>= 7;
            if (value == 0L) {
               this.writeLittleEndianInt((int)varInt);
               --this.position;
               return 3;
            } else {
               varInt |= 8388608L;
               varInt |= (value & 127L) << 24;
               value >>>= 7;
               if (value == 0L) {
                  this.writeLittleEndianInt((int)varInt);
                  this.position -= 0;
                  return 4;
               } else {
                  varInt |= 2147483648L;
                  varInt |= (value & 127L) << 32;
                  varInt &= 68719476735L;
                  this.writeLittleEndianLong(varInt);
                  this.position -= 3;
                  return 5;
               }
            }
         }
      }
   }

   public final int writeVarLong(long value, boolean optimizePositive) throws KryoException {
      if (!optimizePositive) {
         value = value << 1 ^ value >> 63;
      }

      int varInt = 0;
      varInt = (int)(value & 127L);
      value >>>= 7;
      if (value == 0L) {
         this.writeByte(varInt);
         return 1;
      } else {
         varInt |= 128;
         varInt = (int)((long)varInt | (value & 127L) << 8);
         value >>>= 7;
         if (value == 0L) {
            this.writeLittleEndianInt(varInt);
            this.position -= 2;
            return 2;
         } else {
            varInt |= 32768;
            varInt = (int)((long)varInt | (value & 127L) << 16);
            value >>>= 7;
            if (value == 0L) {
               this.writeLittleEndianInt(varInt);
               --this.position;
               return 3;
            } else {
               varInt |= 8388608;
               varInt = (int)((long)varInt | (value & 127L) << 24);
               value >>>= 7;
               if (value == 0L) {
                  this.writeLittleEndianInt(varInt);
                  this.position -= 0;
                  return 4;
               } else {
                  varInt = (int)((long)varInt | 2147483648L);
                  long varLong = (long)varInt & 4294967295L;
                  varLong |= (value & 127L) << 32;
                  value >>>= 7;
                  if (value == 0L) {
                     this.writeLittleEndianLong(varLong);
                     this.position -= 3;
                     return 5;
                  } else {
                     varLong |= 549755813888L;
                     varLong |= (value & 127L) << 40;
                     value >>>= 7;
                     if (value == 0L) {
                        this.writeLittleEndianLong(varLong);
                        this.position -= 2;
                        return 6;
                     } else {
                        varLong |= 140737488355328L;
                        varLong |= (value & 127L) << 48;
                        value >>>= 7;
                        if (value == 0L) {
                           this.writeLittleEndianLong(varLong);
                           --this.position;
                           return 7;
                        } else {
                           varLong |= 36028797018963968L;
                           varLong |= (value & 127L) << 56;
                           value >>>= 7;
                           if (value == 0L) {
                              this.writeLittleEndianLong(varLong);
                              return 8;
                           } else {
                              varLong |= Long.MIN_VALUE;
                              this.writeLittleEndianLong(varLong);
                              this.writeByte((int)(value & 255L));
                              return 9;
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private final void writeLittleEndianInt(int val) {
      if (isLittleEndian) {
         this.writeInt(val);
      } else {
         this.writeInt(com.esotericsoftware.kryo.util.Util.swapInt(val));
      }

   }

   private final void writeLittleEndianLong(long val) {
      if (isLittleEndian) {
         this.writeLong(val);
      } else {
         this.writeLong(com.esotericsoftware.kryo.util.Util.swapLong(val));
      }

   }

   public final void writeInts(int[] object, boolean optimizePositive) throws KryoException {
      if (!this.varIntsEnabled) {
         int bytesToCopy = object.length << 2;
         this.writeBytes(object, UnsafeUtil.intArrayBaseOffset, 0L, (long)bytesToCopy);
      } else {
         super.writeInts(object, optimizePositive);
      }

   }

   public final void writeLongs(long[] object, boolean optimizePositive) throws KryoException {
      if (!this.varIntsEnabled) {
         int bytesToCopy = object.length << 3;
         this.writeBytes(object, UnsafeUtil.longArrayBaseOffset, 0L, (long)bytesToCopy);
      } else {
         super.writeLongs(object, optimizePositive);
      }

   }

   public final void writeInts(int[] object) throws KryoException {
      int bytesToCopy = object.length << 2;
      this.writeBytes(object, UnsafeUtil.intArrayBaseOffset, 0L, (long)bytesToCopy);
   }

   public final void writeLongs(long[] object) throws KryoException {
      int bytesToCopy = object.length << 3;
      this.writeBytes(object, UnsafeUtil.longArrayBaseOffset, 0L, (long)bytesToCopy);
   }

   public final void writeFloats(float[] object) throws KryoException {
      int bytesToCopy = object.length << 2;
      this.writeBytes(object, UnsafeUtil.floatArrayBaseOffset, 0L, (long)bytesToCopy);
   }

   public final void writeShorts(short[] object) throws KryoException {
      int bytesToCopy = object.length << 1;
      this.writeBytes(object, UnsafeUtil.shortArrayBaseOffset, 0L, (long)bytesToCopy);
   }

   public final void writeChars(char[] object) throws KryoException {
      int bytesToCopy = object.length << 1;
      this.writeBytes(object, UnsafeUtil.charArrayBaseOffset, 0L, (long)bytesToCopy);
   }

   public final void writeDoubles(double[] object) throws KryoException {
      int bytesToCopy = object.length << 3;
      this.writeBytes(object, UnsafeUtil.doubleArrayBaseOffset, 0L, (long)bytesToCopy);
   }

   public void writeBytes(byte[] bytes) throws KryoException {
      if (bytes == null) {
         throw new IllegalArgumentException("bytes cannot be null.");
      } else {
         this.writeBytes(bytes, 0L, (long)bytes.length);
      }
   }

   public final void writeBytes(Object obj, long offset, long count) throws KryoException {
      this.writeBytes(obj, UnsafeUtil.byteArrayBaseOffset, offset, count);
   }

   private final void writeBytes(Object srcArray, long srcArrayTypeOffset, long srcOffset, long count) throws KryoException {
      int copyCount = Math.min(this.capacity - this.position, (int)count);

      while(true) {
         UnsafeUtil.unsafe().copyMemory(srcArray, srcArrayTypeOffset + srcOffset, (Object)null, this.bufaddress + (long)this.position, (long)copyCount);
         this.position += copyCount;
         count -= (long)copyCount;
         if (count == 0L) {
            return;
         }

         srcOffset += (long)copyCount;
         copyCount = Math.min(this.capacity, (int)count);
         this.require(copyCount);
      }
   }

   static {
      isLittleEndian = ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN);
   }
}
