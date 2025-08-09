package com.esotericsoftware.kryo.io;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.util.UnsafeUtil;
import java.io.OutputStream;
import java.nio.ByteOrder;

public final class UnsafeOutput extends Output {
   private boolean supportVarInts;
   private static final boolean isLittleEndian;

   public UnsafeOutput() {
      this.supportVarInts = false;
   }

   public UnsafeOutput(int bufferSize) {
      this(bufferSize, bufferSize);
   }

   public UnsafeOutput(int bufferSize, int maxBufferSize) {
      super(bufferSize, maxBufferSize);
      this.supportVarInts = false;
   }

   public UnsafeOutput(byte[] buffer) {
      this(buffer, buffer.length);
   }

   public UnsafeOutput(byte[] buffer, int maxBufferSize) {
      super(buffer, maxBufferSize);
      this.supportVarInts = false;
   }

   public UnsafeOutput(OutputStream outputStream) {
      super(outputStream);
      this.supportVarInts = false;
   }

   public UnsafeOutput(OutputStream outputStream, int bufferSize) {
      super(outputStream, bufferSize);
      this.supportVarInts = false;
   }

   public final void writeInt(int value) throws KryoException {
      this.require(4);
      UnsafeUtil.unsafe().putInt(this.buffer, UnsafeUtil.byteArrayBaseOffset + (long)this.position, value);
      this.position += 4;
   }

   private final void writeLittleEndianInt(int val) {
      if (isLittleEndian) {
         this.writeInt(val);
      } else {
         this.writeInt(com.esotericsoftware.kryo.util.Util.swapInt(val));
      }

   }

   public final void writeFloat(float value) throws KryoException {
      this.require(4);
      UnsafeUtil.unsafe().putFloat(this.buffer, UnsafeUtil.byteArrayBaseOffset + (long)this.position, value);
      this.position += 4;
   }

   public final void writeShort(int value) throws KryoException {
      this.require(2);
      UnsafeUtil.unsafe().putShort(this.buffer, UnsafeUtil.byteArrayBaseOffset + (long)this.position, (short)value);
      this.position += 2;
   }

   public final void writeLong(long value) throws KryoException {
      this.require(8);
      UnsafeUtil.unsafe().putLong(this.buffer, UnsafeUtil.byteArrayBaseOffset + (long)this.position, value);
      this.position += 8;
   }

   private final void writeLittleEndianLong(long val) {
      if (isLittleEndian) {
         this.writeLong(val);
      } else {
         this.writeLong(com.esotericsoftware.kryo.util.Util.swapLong(val));
      }

   }

   public final void writeDouble(double value) throws KryoException {
      this.require(8);
      UnsafeUtil.unsafe().putDouble(this.buffer, UnsafeUtil.byteArrayBaseOffset + (long)this.position, value);
      this.position += 8;
   }

   public final void writeChar(char value) throws KryoException {
      this.require(2);
      UnsafeUtil.unsafe().putChar(this.buffer, UnsafeUtil.byteArrayBaseOffset + (long)this.position, value);
      this.position += 2;
   }

   public final int writeInt(int value, boolean optimizePositive) throws KryoException {
      if (!this.supportVarInts) {
         this.writeInt(value);
         return 4;
      } else {
         return this.writeVarInt(value, optimizePositive);
      }
   }

   public final int writeLong(long value, boolean optimizePositive) throws KryoException {
      if (!this.supportVarInts) {
         this.writeLong(value);
         return 8;
      } else {
         return this.writeVarLong(value, optimizePositive);
      }
   }

   public final int writeVarInt(int val, boolean optimizePositive) throws KryoException {
      int value = val;
      if (!optimizePositive) {
         value = val << 1 ^ val >> 31;
      }

      int varInt = 0;
      varInt = value & 127;
      value >>>= 7;
      if (value == 0) {
         this.write(varInt);
         return 1;
      } else {
         varInt |= 128;
         varInt |= (value & 127) << 8;
         value >>>= 7;
         if (value == 0) {
            this.writeLittleEndianInt(varInt);
            this.position -= 2;
            return 2;
         } else {
            varInt |= 32768;
            varInt |= (value & 127) << 16;
            value >>>= 7;
            if (value == 0) {
               this.writeLittleEndianInt(varInt);
               --this.position;
               return 3;
            } else {
               varInt |= 8388608;
               varInt |= (value & 127) << 24;
               value >>>= 7;
               if (value == 0) {
                  this.writeLittleEndianInt(varInt);
                  this.position -= 0;
                  return 4;
               } else {
                  long varLong = (long)varInt;
                  varLong |= 2147483648L;
                  varLong |= (long)(value & 127) << 32;
                  varLong &= 68719476735L;
                  this.writeLittleEndianLong(varLong);
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
                  long varLong = (long)varInt & 4294967295L | 2147483648L;
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
                              this.write((int)(value & 255L));
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

   public final void writeInts(int[] object, boolean optimizePositive) throws KryoException {
      if (!this.supportVarInts) {
         int bytesToCopy = object.length << 2;
         this.writeBytes(object, UnsafeUtil.intArrayBaseOffset, 0L, (long)bytesToCopy);
      } else {
         super.writeInts(object, optimizePositive);
      }

   }

   public final void writeLongs(long[] object, boolean optimizePositive) throws KryoException {
      if (!this.supportVarInts) {
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

   public final void writeBytes(Object obj, long offset, long count) throws KryoException {
      this.writeBytes(obj, 0L, offset, count);
   }

   private final void writeBytes(Object srcArray, long srcArrayTypeOffset, long srcOffset, long count) throws KryoException {
      int copyCount = Math.min(this.capacity - this.position, (int)count);

      while(true) {
         UnsafeUtil.unsafe().copyMemory(srcArray, srcArrayTypeOffset + srcOffset, this.buffer, UnsafeUtil.byteArrayBaseOffset + (long)this.position, (long)copyCount);
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

   public boolean supportVarInts() {
      return this.supportVarInts;
   }

   public void supportVarInts(boolean supportVarInts) {
      this.supportVarInts = supportVarInts;
   }

   static {
      isLittleEndian = ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN);
   }
}
