package com.esotericsoftware.kryo.io;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.util.UnsafeUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

public class ByteBufferOutput extends Output {
   protected ByteBuffer niobuffer;
   protected boolean varIntsEnabled;
   ByteOrder byteOrder;
   protected static final ByteOrder nativeOrder = ByteOrder.nativeOrder();

   public ByteBufferOutput() {
      this.varIntsEnabled = true;
      this.byteOrder = ByteOrder.BIG_ENDIAN;
   }

   public ByteBufferOutput(int bufferSize) {
      this(bufferSize, bufferSize);
   }

   public ByteBufferOutput(int bufferSize, int maxBufferSize) {
      this.varIntsEnabled = true;
      this.byteOrder = ByteOrder.BIG_ENDIAN;
      if (maxBufferSize < -1) {
         throw new IllegalArgumentException("maxBufferSize cannot be < -1: " + maxBufferSize);
      } else {
         this.capacity = bufferSize;
         this.maxCapacity = maxBufferSize == -1 ? 2147483639 : maxBufferSize;
         this.niobuffer = ByteBuffer.allocateDirect(bufferSize);
         this.niobuffer.order(this.byteOrder);
      }
   }

   public ByteBufferOutput(OutputStream outputStream) {
      this(4096, 4096);
      if (outputStream == null) {
         throw new IllegalArgumentException("outputStream cannot be null.");
      } else {
         this.outputStream = outputStream;
      }
   }

   public ByteBufferOutput(OutputStream outputStream, int bufferSize) {
      this(bufferSize, bufferSize);
      if (outputStream == null) {
         throw new IllegalArgumentException("outputStream cannot be null.");
      } else {
         this.outputStream = outputStream;
      }
   }

   public ByteBufferOutput(ByteBuffer buffer) {
      this.varIntsEnabled = true;
      this.byteOrder = ByteOrder.BIG_ENDIAN;
      this.setBuffer(buffer);
   }

   public ByteBufferOutput(ByteBuffer buffer, int maxBufferSize) {
      this.varIntsEnabled = true;
      this.byteOrder = ByteOrder.BIG_ENDIAN;
      this.setBuffer(buffer, maxBufferSize);
   }

   public ByteBufferOutput(long address, int maxBufferSize) {
      this.varIntsEnabled = true;
      this.byteOrder = ByteOrder.BIG_ENDIAN;
      this.niobuffer = UnsafeUtil.getDirectBufferAt(address, maxBufferSize);
      this.setBuffer(this.niobuffer, maxBufferSize);
   }

   public void release() {
      this.clear();
      UnsafeUtil.releaseBuffer(this.niobuffer);
      this.niobuffer = null;
   }

   public ByteOrder order() {
      return this.byteOrder;
   }

   public void order(ByteOrder byteOrder) {
      this.byteOrder = byteOrder;
      this.niobuffer.order(byteOrder);
   }

   public OutputStream getOutputStream() {
      return this.outputStream;
   }

   public void setOutputStream(OutputStream outputStream) {
      this.outputStream = outputStream;
      this.position = 0;
      this.total = 0L;
   }

   public void setBuffer(ByteBuffer buffer) {
      this.setBuffer(buffer, buffer.capacity());
   }

   public void setBuffer(ByteBuffer buffer, int maxBufferSize) {
      if (buffer == null) {
         throw new IllegalArgumentException("buffer cannot be null.");
      } else if (maxBufferSize < -1) {
         throw new IllegalArgumentException("maxBufferSize cannot be < -1: " + maxBufferSize);
      } else {
         this.niobuffer = buffer;
         this.maxCapacity = maxBufferSize == -1 ? 2147483639 : maxBufferSize;
         this.byteOrder = buffer.order();
         this.capacity = buffer.capacity();
         this.position = buffer.position();
         this.total = 0L;
         this.outputStream = null;
      }
   }

   public ByteBuffer getByteBuffer() {
      this.niobuffer.position(this.position);
      return this.niobuffer;
   }

   public byte[] toBytes() {
      byte[] newBuffer = new byte[this.position];
      this.niobuffer.position(0);
      this.niobuffer.get(newBuffer, 0, this.position);
      return newBuffer;
   }

   public void setPosition(int position) {
      this.position = position;
      this.niobuffer.position(position);
   }

   public void clear() {
      this.niobuffer.clear();
      this.position = 0;
      this.total = 0L;
   }

   protected boolean require(int required) throws KryoException {
      if (this.capacity - this.position >= required) {
         return false;
      } else if (required > this.maxCapacity) {
         this.niobuffer.order(this.byteOrder);
         throw new KryoException("Buffer overflow. Max capacity: " + this.maxCapacity + ", required: " + required);
      } else {
         this.flush();

         while(this.capacity - this.position < required) {
            if (this.capacity == this.maxCapacity) {
               this.niobuffer.order(this.byteOrder);
               throw new KryoException("Buffer overflow. Available: " + (this.capacity - this.position) + ", required: " + required);
            }

            if (this.capacity == 0) {
               this.capacity = 1;
            }

            this.capacity = Math.min(this.capacity * 2, this.maxCapacity);
            if (this.capacity < 0) {
               this.capacity = this.maxCapacity;
            }

            ByteBuffer newBuffer = this.niobuffer != null && !this.niobuffer.isDirect() ? ByteBuffer.allocate(this.capacity) : ByteBuffer.allocateDirect(this.capacity);
            this.niobuffer.position(0);
            this.niobuffer.limit(this.position);
            newBuffer.put(this.niobuffer);
            newBuffer.order(this.niobuffer.order());
            ByteOrder currentByteOrder = this.byteOrder;
            this.setBuffer(newBuffer, this.maxCapacity);
            this.byteOrder = currentByteOrder;
         }

         return true;
      }
   }

   public void flush() throws KryoException {
      if (this.outputStream != null) {
         try {
            byte[] tmp = new byte[this.position];
            this.niobuffer.position(0);
            this.niobuffer.get(tmp);
            this.niobuffer.position(0);
            this.outputStream.write(tmp, 0, this.position);
         } catch (IOException ex) {
            throw new KryoException(ex);
         }

         this.total += (long)this.position;
         this.position = 0;
      }
   }

   public void close() throws KryoException {
      this.flush();
      if (this.outputStream != null) {
         try {
            this.outputStream.close();
         } catch (IOException var2) {
         }
      }

   }

   public void write(int value) throws KryoException {
      if (this.position == this.capacity) {
         this.require(1);
      }

      this.niobuffer.put((byte)value);
      ++this.position;
   }

   public void write(byte[] bytes) throws KryoException {
      if (bytes == null) {
         throw new IllegalArgumentException("bytes cannot be null.");
      } else {
         this.writeBytes(bytes, 0, bytes.length);
      }
   }

   public void write(byte[] bytes, int offset, int length) throws KryoException {
      this.writeBytes(bytes, offset, length);
   }

   public void writeByte(byte value) throws KryoException {
      if (this.position == this.capacity) {
         this.require(1);
      }

      this.niobuffer.put(value);
      ++this.position;
   }

   public void writeByte(int value) throws KryoException {
      if (this.position == this.capacity) {
         this.require(1);
      }

      this.niobuffer.put((byte)value);
      ++this.position;
   }

   public void writeBytes(byte[] bytes) throws KryoException {
      if (bytes == null) {
         throw new IllegalArgumentException("bytes cannot be null.");
      } else {
         this.writeBytes(bytes, 0, bytes.length);
      }
   }

   public void writeBytes(byte[] bytes, int offset, int count) throws KryoException {
      if (bytes == null) {
         throw new IllegalArgumentException("bytes cannot be null.");
      } else {
         int copyCount = Math.min(this.capacity - this.position, count);

         while(true) {
            this.niobuffer.put(bytes, offset, copyCount);
            this.position += copyCount;
            count -= copyCount;
            if (count == 0) {
               return;
            }

            offset += copyCount;
            copyCount = Math.min(this.capacity, count);
            this.require(copyCount);
         }
      }
   }

   public void writeInt(int value) throws KryoException {
      this.require(4);
      this.niobuffer.putInt(value);
      this.position += 4;
   }

   public int writeInt(int value, boolean optimizePositive) throws KryoException {
      if (!this.varIntsEnabled) {
         this.writeInt(value);
         return 4;
      } else {
         return this.writeVarInt(value, optimizePositive);
      }
   }

   public int writeVarInt(int val, boolean optimizePositive) throws KryoException {
      this.niobuffer.position(this.position);
      int value = val;
      if (!optimizePositive) {
         value = val << 1 ^ val >> 31;
      }

      int varInt = 0;
      varInt = value & 127;
      value >>>= 7;
      if (value == 0) {
         this.writeByte(varInt);
         return 1;
      } else {
         varInt |= 128;
         varInt |= (value & 127) << 8;
         value >>>= 7;
         if (value == 0) {
            this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
            this.writeInt(varInt);
            this.niobuffer.order(this.byteOrder);
            this.position -= 2;
            this.niobuffer.position(this.position);
            return 2;
         } else {
            varInt |= 32768;
            varInt |= (value & 127) << 16;
            value >>>= 7;
            if (value == 0) {
               this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
               this.writeInt(varInt);
               this.niobuffer.order(this.byteOrder);
               --this.position;
               this.niobuffer.position(this.position);
               return 3;
            } else {
               varInt |= 8388608;
               varInt |= (value & 127) << 24;
               value >>>= 7;
               if (value == 0) {
                  this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
                  this.writeInt(varInt);
                  this.niobuffer.order(this.byteOrder);
                  this.position -= 0;
                  return 4;
               } else {
                  varInt |= Integer.MIN_VALUE;
                  long varLong = (long)varInt & 4294967295L | (long)value << 32;
                  this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
                  this.writeLong(varLong);
                  this.niobuffer.order(this.byteOrder);
                  this.position -= 3;
                  this.niobuffer.position(this.position);
                  return 5;
               }
            }
         }
      }
   }

   public void writeString(String value) throws KryoException {
      this.niobuffer.position(this.position);
      if (value == null) {
         this.writeByte((int)128);
      } else {
         int charCount = value.length();
         if (charCount == 0) {
            this.writeByte((int)129);
         } else {
            boolean ascii = false;
            if (charCount > 1 && charCount < 64) {
               ascii = true;

               for(int i = 0; i < charCount; ++i) {
                  int c = value.charAt(i);
                  if (c > 127) {
                     ascii = false;
                     break;
                  }
               }
            }

            if (ascii) {
               if (this.capacity - this.position < charCount) {
                  this.writeAscii_slow(value, charCount);
               } else {
                  byte[] tmp = value.getBytes();
                  this.niobuffer.put(tmp, 0, tmp.length);
                  this.position += charCount;
               }

               this.niobuffer.put(this.position - 1, (byte)(this.niobuffer.get(this.position - 1) | 128));
            } else {
               this.writeUtf8Length(charCount + 1);
               int charIndex = 0;
               if (this.capacity - this.position >= charCount) {
                  int position;
                  for(position = this.position; charIndex < charCount; ++charIndex) {
                     int c = value.charAt(charIndex);
                     if (c > 127) {
                        break;
                     }

                     this.niobuffer.put(position++, (byte)c);
                  }

                  this.position = position;
                  this.niobuffer.position(position);
               }

               if (charIndex < charCount) {
                  this.writeString_slow(value, charCount, charIndex);
               }

               this.niobuffer.position(this.position);
            }

         }
      }
   }

   public void writeString(CharSequence value) throws KryoException {
      if (value == null) {
         this.writeByte((int)128);
      } else {
         int charCount = value.length();
         if (charCount == 0) {
            this.writeByte((int)129);
         } else {
            this.writeUtf8Length(charCount + 1);
            int charIndex = 0;
            if (this.capacity - this.position >= charCount) {
               int position;
               for(position = this.position; charIndex < charCount; ++charIndex) {
                  int c = value.charAt(charIndex);
                  if (c > 127) {
                     break;
                  }

                  this.niobuffer.put(position++, (byte)c);
               }

               this.position = position;
               this.niobuffer.position(position);
            }

            if (charIndex < charCount) {
               this.writeString_slow(value, charCount, charIndex);
            }

            this.niobuffer.position(this.position);
         }
      }
   }

   public void writeAscii(String value) throws KryoException {
      if (value == null) {
         this.writeByte((int)128);
      } else {
         int charCount = value.length();
         if (charCount == 0) {
            this.writeByte((int)129);
         } else {
            if (this.capacity - this.position < charCount) {
               this.writeAscii_slow(value, charCount);
            } else {
               byte[] tmp = value.getBytes();
               this.niobuffer.put(tmp, 0, tmp.length);
               this.position += charCount;
            }

            this.niobuffer.put(this.position - 1, (byte)(this.niobuffer.get(this.position - 1) | 128));
         }
      }
   }

   private void writeUtf8Length(int value) {
      if (value >>> 6 == 0) {
         this.require(1);
         this.niobuffer.put((byte)(value | 128));
         ++this.position;
      } else if (value >>> 13 == 0) {
         this.require(2);
         this.niobuffer.put((byte)(value | 64 | 128));
         this.niobuffer.put((byte)(value >>> 6));
         this.position += 2;
      } else if (value >>> 20 == 0) {
         this.require(3);
         this.niobuffer.put((byte)(value | 64 | 128));
         this.niobuffer.put((byte)(value >>> 6 | 128));
         this.niobuffer.put((byte)(value >>> 13));
         this.position += 3;
      } else if (value >>> 27 == 0) {
         this.require(4);
         this.niobuffer.put((byte)(value | 64 | 128));
         this.niobuffer.put((byte)(value >>> 6 | 128));
         this.niobuffer.put((byte)(value >>> 13 | 128));
         this.niobuffer.put((byte)(value >>> 20));
         this.position += 4;
      } else {
         this.require(5);
         this.niobuffer.put((byte)(value | 64 | 128));
         this.niobuffer.put((byte)(value >>> 6 | 128));
         this.niobuffer.put((byte)(value >>> 13 | 128));
         this.niobuffer.put((byte)(value >>> 20 | 128));
         this.niobuffer.put((byte)(value >>> 27));
         this.position += 5;
      }

   }

   private void writeString_slow(CharSequence value, int charCount, int charIndex) {
      for(; charIndex < charCount; ++charIndex) {
         if (this.position == this.capacity) {
            this.require(Math.min(this.capacity, charCount - charIndex));
         }

         int c = value.charAt(charIndex);
         if (c <= 127) {
            this.niobuffer.put(this.position++, (byte)c);
         } else if (c > 2047) {
            this.niobuffer.put(this.position++, (byte)(224 | c >> 12 & 15));
            this.require(2);
            this.niobuffer.put(this.position++, (byte)(128 | c >> 6 & 63));
            this.niobuffer.put(this.position++, (byte)(128 | c & 63));
         } else {
            this.niobuffer.put(this.position++, (byte)(192 | c >> 6 & 31));
            this.require(1);
            this.niobuffer.put(this.position++, (byte)(128 | c & 63));
         }
      }

   }

   private void writeAscii_slow(String value, int charCount) throws KryoException {
      ByteBuffer buffer = this.niobuffer;
      int charIndex = 0;
      int charsToWrite = Math.min(charCount, this.capacity - this.position);

      while(charIndex < charCount) {
         byte[] tmp = new byte[charCount];
         value.getBytes(charIndex, charIndex + charsToWrite, tmp, 0);
         buffer.put(tmp, 0, charsToWrite);
         charIndex += charsToWrite;
         this.position += charsToWrite;
         charsToWrite = Math.min(charCount - charIndex, this.capacity);
         if (this.require(charsToWrite)) {
            buffer = this.niobuffer;
         }
      }

   }

   public void writeFloat(float value) throws KryoException {
      this.require(4);
      this.niobuffer.putFloat(value);
      this.position += 4;
   }

   public int writeFloat(float value, float precision, boolean optimizePositive) throws KryoException {
      return this.writeInt((int)(value * precision), optimizePositive);
   }

   public void writeShort(int value) throws KryoException {
      this.require(2);
      this.niobuffer.putShort((short)value);
      this.position += 2;
   }

   public void writeLong(long value) throws KryoException {
      this.require(8);
      this.niobuffer.putLong(value);
      this.position += 8;
   }

   public int writeLong(long value, boolean optimizePositive) throws KryoException {
      if (!this.varIntsEnabled) {
         this.writeLong(value);
         return 8;
      } else {
         return this.writeVarLong(value, optimizePositive);
      }
   }

   public int writeVarLong(long value, boolean optimizePositive) throws KryoException {
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
            this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
            this.writeInt(varInt);
            this.niobuffer.order(this.byteOrder);
            this.position -= 2;
            this.niobuffer.position(this.position);
            return 2;
         } else {
            varInt |= 32768;
            varInt = (int)((long)varInt | (value & 127L) << 16);
            value >>>= 7;
            if (value == 0L) {
               this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
               this.writeInt(varInt);
               this.niobuffer.order(this.byteOrder);
               --this.position;
               this.niobuffer.position(this.position);
               return 3;
            } else {
               varInt |= 8388608;
               varInt = (int)((long)varInt | (value & 127L) << 24);
               value >>>= 7;
               if (value == 0L) {
                  this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
                  this.writeInt(varInt);
                  this.niobuffer.order(this.byteOrder);
                  this.position -= 0;
                  return 4;
               } else {
                  varInt |= Integer.MIN_VALUE;
                  long varLong = (long)varInt & 4294967295L;
                  varLong |= (value & 127L) << 32;
                  value >>>= 7;
                  if (value == 0L) {
                     this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
                     this.writeLong(varLong);
                     this.niobuffer.order(this.byteOrder);
                     this.position -= 3;
                     this.niobuffer.position(this.position);
                     return 5;
                  } else {
                     varLong |= 549755813888L;
                     varLong |= (value & 127L) << 40;
                     value >>>= 7;
                     if (value == 0L) {
                        this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
                        this.writeLong(varLong);
                        this.niobuffer.order(this.byteOrder);
                        this.position -= 2;
                        this.niobuffer.position(this.position);
                        return 6;
                     } else {
                        varLong |= 140737488355328L;
                        varLong |= (value & 127L) << 48;
                        value >>>= 7;
                        if (value == 0L) {
                           this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
                           this.writeLong(varLong);
                           this.niobuffer.order(this.byteOrder);
                           --this.position;
                           this.niobuffer.position(this.position);
                           return 7;
                        } else {
                           varLong |= 36028797018963968L;
                           varLong |= (value & 127L) << 56;
                           value >>>= 7;
                           if (value == 0L) {
                              this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
                              this.writeLong(varLong);
                              this.niobuffer.order(this.byteOrder);
                              return 8;
                           } else {
                              varLong |= Long.MIN_VALUE;
                              this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
                              this.writeLong(varLong);
                              this.niobuffer.order(this.byteOrder);
                              this.write((byte)((int)value));
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

   public int writeLongS(long value, boolean optimizePositive) throws KryoException {
      if (!optimizePositive) {
         value = value << 1 ^ value >> 63;
      }

      if (value >>> 7 == 0L) {
         this.require(1);
         this.niobuffer.put((byte)((int)value));
         ++this.position;
         return 1;
      } else if (value >>> 14 == 0L) {
         this.require(2);
         this.niobuffer.put((byte)((int)(value & 127L | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 7)));
         this.position += 2;
         return 2;
      } else if (value >>> 21 == 0L) {
         this.require(3);
         this.niobuffer.put((byte)((int)(value & 127L | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 7 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 14)));
         this.position += 3;
         return 3;
      } else if (value >>> 28 == 0L) {
         this.require(4);
         this.niobuffer.put((byte)((int)(value & 127L | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 7 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 14 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 21)));
         this.position += 4;
         return 4;
      } else if (value >>> 35 == 0L) {
         this.require(5);
         this.niobuffer.put((byte)((int)(value & 127L | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 7 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 14 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 21 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 28)));
         this.position += 5;
         return 5;
      } else if (value >>> 42 == 0L) {
         this.require(6);
         this.niobuffer.put((byte)((int)(value & 127L | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 7 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 14 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 21 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 28 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 35)));
         this.position += 6;
         return 6;
      } else if (value >>> 49 == 0L) {
         this.require(7);
         this.niobuffer.put((byte)((int)(value & 127L | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 7 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 14 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 21 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 28 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 35 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 42)));
         this.position += 7;
         return 7;
      } else if (value >>> 56 == 0L) {
         this.require(8);
         this.niobuffer.put((byte)((int)(value & 127L | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 7 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 14 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 21 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 28 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 35 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 42 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 49)));
         this.position += 8;
         return 8;
      } else {
         this.require(9);
         this.niobuffer.put((byte)((int)(value & 127L | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 7 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 14 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 21 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 28 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 35 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 42 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 49 | 128L)));
         this.niobuffer.put((byte)((int)(value >>> 56)));
         this.position += 9;
         return 9;
      }
   }

   public void writeBoolean(boolean value) throws KryoException {
      this.require(1);
      this.niobuffer.put((byte)(value ? 1 : 0));
      ++this.position;
   }

   public void writeChar(char value) throws KryoException {
      this.require(2);
      this.niobuffer.putChar(value);
      this.position += 2;
   }

   public void writeDouble(double value) throws KryoException {
      this.require(8);
      this.niobuffer.putDouble(value);
      this.position += 8;
   }

   public int writeDouble(double value, double precision, boolean optimizePositive) throws KryoException {
      return this.writeLong((long)(value * precision), optimizePositive);
   }

   public void writeInts(int[] object) throws KryoException {
      if (this.capacity - this.position >= object.length * 4 && this.isNativeOrder()) {
         IntBuffer buf = this.niobuffer.asIntBuffer();
         buf.put(object);
         this.position += object.length * 4;
      } else {
         super.writeInts(object);
      }

   }

   public void writeLongs(long[] object) throws KryoException {
      if (this.capacity - this.position >= object.length * 8 && this.isNativeOrder()) {
         LongBuffer buf = this.niobuffer.asLongBuffer();
         buf.put(object);
         this.position += object.length * 8;
      } else {
         super.writeLongs(object);
      }

   }

   public void writeFloats(float[] object) throws KryoException {
      if (this.capacity - this.position >= object.length * 4 && this.isNativeOrder()) {
         FloatBuffer buf = this.niobuffer.asFloatBuffer();
         buf.put(object);
         this.position += object.length * 4;
      } else {
         super.writeFloats(object);
      }

   }

   public void writeShorts(short[] object) throws KryoException {
      if (this.capacity - this.position >= object.length * 2 && this.isNativeOrder()) {
         ShortBuffer buf = this.niobuffer.asShortBuffer();
         buf.put(object);
         this.position += object.length * 2;
      } else {
         super.writeShorts(object);
      }

   }

   public void writeChars(char[] object) throws KryoException {
      if (this.capacity - this.position >= object.length * 2 && this.isNativeOrder()) {
         CharBuffer buf = this.niobuffer.asCharBuffer();
         buf.put(object);
         this.position += object.length * 2;
      } else {
         super.writeChars(object);
      }

   }

   public void writeDoubles(double[] object) throws KryoException {
      if (this.capacity - this.position >= object.length * 8 && this.isNativeOrder()) {
         DoubleBuffer buf = this.niobuffer.asDoubleBuffer();
         buf.put(object);
         this.position += object.length * 8;
      } else {
         super.writeDoubles(object);
      }

   }

   private boolean isNativeOrder() {
      return this.byteOrder == nativeOrder;
   }

   public boolean getVarIntsEnabled() {
      return this.varIntsEnabled;
   }

   public void setVarIntsEnabled(boolean varIntsEnabled) {
      this.varIntsEnabled = varIntsEnabled;
   }
}
