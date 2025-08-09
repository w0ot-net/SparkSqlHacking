package com.esotericsoftware.kryo.io;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.util.UnsafeUtil;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

public class ByteBufferInput extends Input {
   protected ByteBuffer niobuffer;
   protected boolean varIntsEnabled;
   ByteOrder byteOrder;
   protected static final ByteOrder nativeOrder = ByteOrder.nativeOrder();

   public ByteBufferInput() {
      this.varIntsEnabled = true;
      this.byteOrder = ByteOrder.BIG_ENDIAN;
   }

   public ByteBufferInput(int bufferSize) {
      this.varIntsEnabled = true;
      this.byteOrder = ByteOrder.BIG_ENDIAN;
      this.capacity = bufferSize;
      this.niobuffer = ByteBuffer.allocateDirect(bufferSize);
      this.niobuffer.order(this.byteOrder);
   }

   public ByteBufferInput(byte[] buffer) {
      this.varIntsEnabled = true;
      this.byteOrder = ByteOrder.BIG_ENDIAN;
      this.setBuffer(buffer);
   }

   public ByteBufferInput(ByteBuffer buffer) {
      this.varIntsEnabled = true;
      this.byteOrder = ByteOrder.BIG_ENDIAN;
      this.setBuffer(buffer);
   }

   public ByteBufferInput(InputStream inputStream) {
      this(4096);
      if (inputStream == null) {
         throw new IllegalArgumentException("inputStream cannot be null.");
      } else {
         this.inputStream = inputStream;
      }
   }

   public ByteBufferInput(InputStream inputStream, int bufferSize) {
      this(bufferSize);
      if (inputStream == null) {
         throw new IllegalArgumentException("inputStream cannot be null.");
      } else {
         this.inputStream = inputStream;
      }
   }

   public ByteOrder order() {
      return this.byteOrder;
   }

   public void order(ByteOrder byteOrder) {
      this.byteOrder = byteOrder;
   }

   public void setBuffer(byte[] bytes) {
      ByteBuffer directBuffer = ByteBuffer.allocateDirect(bytes.length);
      directBuffer.put(bytes);
      directBuffer.position(0);
      directBuffer.limit(bytes.length);
      directBuffer.order(this.byteOrder);
      this.setBuffer(directBuffer);
   }

   public void setBuffer(ByteBuffer buffer) {
      if (buffer == null) {
         throw new IllegalArgumentException("buffer cannot be null.");
      } else {
         this.niobuffer = buffer;
         this.position = buffer.position();
         this.limit = buffer.limit();
         this.capacity = buffer.capacity();
         this.byteOrder = buffer.order();
         this.total = 0L;
         this.inputStream = null;
      }
   }

   public void release() {
      this.close();
      UnsafeUtil.releaseBuffer(this.niobuffer);
      this.niobuffer = null;
   }

   public ByteBufferInput(long address, int size) {
      this.varIntsEnabled = true;
      this.byteOrder = ByteOrder.BIG_ENDIAN;
      this.setBuffer(UnsafeUtil.getDirectBufferAt(address, size));
   }

   public ByteBuffer getByteBuffer() {
      return this.niobuffer;
   }

   public InputStream getInputStream() {
      return this.inputStream;
   }

   public void setInputStream(InputStream inputStream) {
      this.inputStream = inputStream;
      this.limit = 0;
      this.rewind();
   }

   public void rewind() {
      super.rewind();
      this.niobuffer.position(0);
   }

   protected int fill(ByteBuffer buffer, int offset, int count) throws KryoException {
      if (this.inputStream == null) {
         return -1;
      } else {
         try {
            byte[] tmp = new byte[count];
            int result = this.inputStream.read(tmp, 0, count);
            buffer.position(offset);
            if (result >= 0) {
               buffer.put(tmp, 0, result);
               buffer.position(offset);
            }

            return result;
         } catch (IOException ex) {
            throw new KryoException(ex);
         }
      }
   }

   protected final int require(int required) throws KryoException {
      int remaining = this.limit - this.position;
      if (remaining >= required) {
         return remaining;
      } else if (required > this.capacity) {
         throw new KryoException("Buffer too small: capacity: " + this.capacity + ", required: " + required);
      } else {
         if (remaining > 0) {
            int count = this.fill(this.niobuffer, this.limit, this.capacity - this.limit);
            if (count == -1) {
               throw new KryoException("Buffer underflow.");
            }

            remaining += count;
            if (remaining >= required) {
               this.limit += count;
               return remaining;
            }
         }

         this.niobuffer.position(this.position);
         this.niobuffer.compact();
         this.total += (long)this.position;
         this.position = 0;

         do {
            int count = this.fill(this.niobuffer, remaining, this.capacity - remaining);
            if (count == -1) {
               if (remaining < required) {
                  throw new KryoException("Buffer underflow.");
               }
               break;
            }

            remaining += count;
         } while(remaining < required);

         this.limit = remaining;
         this.niobuffer.position(0);
         return remaining;
      }
   }

   private int optional(int optional) throws KryoException {
      int remaining = this.limit - this.position;
      if (remaining >= optional) {
         return optional;
      } else {
         optional = Math.min(optional, this.capacity);
         int count = this.fill(this.niobuffer, this.limit, this.capacity - this.limit);
         if (count == -1) {
            return remaining == 0 ? -1 : Math.min(remaining, optional);
         } else {
            remaining += count;
            if (remaining >= optional) {
               this.limit += count;
               return optional;
            } else {
               this.niobuffer.compact();
               this.total += (long)this.position;
               this.position = 0;

               do {
                  count = this.fill(this.niobuffer, remaining, this.capacity - remaining);
                  if (count == -1) {
                     break;
                  }

                  remaining += count;
               } while(remaining < optional);

               this.limit = remaining;
               this.niobuffer.position(this.position);
               return remaining == 0 ? -1 : Math.min(remaining, optional);
            }
         }
      }
   }

   public int read() throws KryoException {
      if (this.optional(1) <= 0) {
         return -1;
      } else {
         this.niobuffer.position(this.position);
         ++this.position;
         return this.niobuffer.get() & 255;
      }
   }

   public int read(byte[] bytes) throws KryoException {
      this.niobuffer.position(this.position);
      return this.read(bytes, 0, bytes.length);
   }

   public int read(byte[] bytes, int offset, int count) throws KryoException {
      this.niobuffer.position(this.position);
      if (bytes == null) {
         throw new IllegalArgumentException("bytes cannot be null.");
      } else {
         int startingCount = count;
         int copyCount = Math.min(this.limit - this.position, count);

         do {
            this.niobuffer.get(bytes, offset, copyCount);
            this.position += copyCount;
            count -= copyCount;
            if (count == 0) {
               break;
            }

            offset += copyCount;
            copyCount = this.optional(count);
            if (copyCount == -1) {
               if (startingCount == count) {
                  return -1;
               }
               break;
            }
         } while(this.position != this.limit);

         return startingCount - count;
      }
   }

   public void setPosition(int position) {
      this.position = position;
      this.niobuffer.position(position);
   }

   public void setLimit(int limit) {
      this.limit = limit;
      this.niobuffer.limit(limit);
   }

   public void skip(int count) throws KryoException {
      super.skip(count);
      this.niobuffer.position(this.position());
   }

   public long skip(long count) throws KryoException {
      int skip;
      for(long remaining = count; remaining > 0L; remaining -= (long)skip) {
         skip = (int)Math.min(2147483639L, remaining);
         this.skip(skip);
      }

      return count;
   }

   public void close() throws KryoException {
      if (this.inputStream != null) {
         try {
            this.inputStream.close();
         } catch (IOException var2) {
         }
      }

   }

   public byte readByte() throws KryoException {
      this.niobuffer.position(this.position);
      this.require(1);
      ++this.position;
      return this.niobuffer.get();
   }

   public int readByteUnsigned() throws KryoException {
      this.require(1);
      ++this.position;
      return this.niobuffer.get() & 255;
   }

   public byte[] readBytes(int length) throws KryoException {
      byte[] bytes = new byte[length];
      this.readBytes(bytes, 0, length);
      return bytes;
   }

   public void readBytes(byte[] bytes) throws KryoException {
      this.readBytes(bytes, 0, bytes.length);
   }

   public void readBytes(byte[] bytes, int offset, int count) throws KryoException {
      if (bytes == null) {
         throw new IllegalArgumentException("bytes cannot be null.");
      } else {
         int copyCount = Math.min(this.limit - this.position, count);

         while(true) {
            this.niobuffer.get(bytes, offset, copyCount);
            this.position += copyCount;
            count -= copyCount;
            if (count == 0) {
               return;
            }

            offset += copyCount;
            copyCount = Math.min(count, this.capacity);
            this.require(copyCount);
         }
      }
   }

   public int readInt() throws KryoException {
      this.require(4);
      this.position += 4;
      return this.niobuffer.getInt();
   }

   public int readInt(boolean optimizePositive) throws KryoException {
      return this.varIntsEnabled ? this.readVarInt(optimizePositive) : this.readInt();
   }

   public int readVarInt(boolean optimizePositive) throws KryoException {
      this.niobuffer.position(this.position);
      if (this.require(1) < 5) {
         return this.readInt_slow(optimizePositive);
      } else {
         ++this.position;
         int b = this.niobuffer.get();
         int result = b & 127;
         if ((b & 128) != 0) {
            ++this.position;
            b = this.niobuffer.get();
            result |= (b & 127) << 7;
            if ((b & 128) != 0) {
               ++this.position;
               b = this.niobuffer.get();
               result |= (b & 127) << 14;
               if ((b & 128) != 0) {
                  ++this.position;
                  b = this.niobuffer.get();
                  result |= (b & 127) << 21;
                  if ((b & 128) != 0) {
                     ++this.position;
                     b = this.niobuffer.get();
                     result |= (b & 127) << 28;
                  }
               }
            }
         }

         return optimizePositive ? result : result >>> 1 ^ -(result & 1);
      }
   }

   private int readInt_slow(boolean optimizePositive) {
      ++this.position;
      int b = this.niobuffer.get();
      int result = b & 127;
      if ((b & 128) != 0) {
         this.require(1);
         ++this.position;
         b = this.niobuffer.get();
         result |= (b & 127) << 7;
         if ((b & 128) != 0) {
            this.require(1);
            ++this.position;
            b = this.niobuffer.get();
            result |= (b & 127) << 14;
            if ((b & 128) != 0) {
               this.require(1);
               ++this.position;
               b = this.niobuffer.get();
               result |= (b & 127) << 21;
               if ((b & 128) != 0) {
                  this.require(1);
                  ++this.position;
                  b = this.niobuffer.get();
                  result |= (b & 127) << 28;
               }
            }
         }
      }

      return optimizePositive ? result : result >>> 1 ^ -(result & 1);
   }

   public boolean canReadInt() throws KryoException {
      if (this.limit - this.position >= 5) {
         return true;
      } else if (this.optional(5) <= 0) {
         return false;
      } else {
         int p = this.position;
         if ((this.niobuffer.get(p++) & 128) == 0) {
            return true;
         } else if (p == this.limit) {
            return false;
         } else if ((this.niobuffer.get(p++) & 128) == 0) {
            return true;
         } else if (p == this.limit) {
            return false;
         } else if ((this.niobuffer.get(p++) & 128) == 0) {
            return true;
         } else if (p == this.limit) {
            return false;
         } else if ((this.niobuffer.get(p++) & 128) == 0) {
            return true;
         } else {
            return p != this.limit;
         }
      }
   }

   public boolean canReadLong() throws KryoException {
      if (this.limit - this.position >= 9) {
         return true;
      } else if (this.optional(5) <= 0) {
         return false;
      } else {
         int p = this.position;
         if ((this.niobuffer.get(p++) & 128) == 0) {
            return true;
         } else if (p == this.limit) {
            return false;
         } else if ((this.niobuffer.get(p++) & 128) == 0) {
            return true;
         } else if (p == this.limit) {
            return false;
         } else if ((this.niobuffer.get(p++) & 128) == 0) {
            return true;
         } else if (p == this.limit) {
            return false;
         } else if ((this.niobuffer.get(p++) & 128) == 0) {
            return true;
         } else if (p == this.limit) {
            return false;
         } else if ((this.niobuffer.get(p++) & 128) == 0) {
            return true;
         } else if (p == this.limit) {
            return false;
         } else if ((this.niobuffer.get(p++) & 128) == 0) {
            return true;
         } else if (p == this.limit) {
            return false;
         } else if ((this.niobuffer.get(p++) & 128) == 0) {
            return true;
         } else if (p == this.limit) {
            return false;
         } else if ((this.niobuffer.get(p++) & 128) == 0) {
            return true;
         } else {
            return p != this.limit;
         }
      }
   }

   public String readString() {
      this.niobuffer.position(this.position);
      int available = this.require(1);
      ++this.position;
      int b = this.niobuffer.get();
      if ((b & 128) == 0) {
         return this.readAscii();
      } else {
         int charCount = available >= 5 ? this.readUtf8Length(b) : this.readUtf8Length_slow(b);
         switch (charCount) {
            case 0:
               return null;
            case 1:
               return "";
            default:
               --charCount;
               if (this.chars.length < charCount) {
                  this.chars = new char[charCount];
               }

               this.readUtf8(charCount);
               return new String(this.chars, 0, charCount);
         }
      }
   }

   private int readUtf8Length(int b) {
      int result = b & 63;
      if ((b & 64) != 0) {
         ++this.position;
         b = this.niobuffer.get();
         result |= (b & 127) << 6;
         if ((b & 128) != 0) {
            ++this.position;
            b = this.niobuffer.get();
            result |= (b & 127) << 13;
            if ((b & 128) != 0) {
               ++this.position;
               b = this.niobuffer.get();
               result |= (b & 127) << 20;
               if ((b & 128) != 0) {
                  ++this.position;
                  b = this.niobuffer.get();
                  result |= (b & 127) << 27;
               }
            }
         }
      }

      return result;
   }

   private int readUtf8Length_slow(int b) {
      int result = b & 63;
      if ((b & 64) != 0) {
         this.require(1);
         ++this.position;
         b = this.niobuffer.get();
         result |= (b & 127) << 6;
         if ((b & 128) != 0) {
            this.require(1);
            ++this.position;
            b = this.niobuffer.get();
            result |= (b & 127) << 13;
            if ((b & 128) != 0) {
               this.require(1);
               ++this.position;
               b = this.niobuffer.get();
               result |= (b & 127) << 20;
               if ((b & 128) != 0) {
                  this.require(1);
                  ++this.position;
                  b = this.niobuffer.get();
                  result |= (b & 127) << 27;
               }
            }
         }
      }

      return result;
   }

   private void readUtf8(int charCount) {
      char[] chars = this.chars;
      int charIndex = 0;
      int count = Math.min(this.require(1), charCount);

      int position;
      int b;
      for(position = this.position; charIndex < count; chars[charIndex++] = (char)b) {
         ++position;
         b = this.niobuffer.get();
         if (b < 0) {
            --position;
            break;
         }
      }

      this.position = position;
      if (charIndex < charCount) {
         this.niobuffer.position(position);
         this.readUtf8_slow(charCount, charIndex);
      }

   }

   private void readUtf8_slow(int charCount, int charIndex) {
      for(char[] chars = this.chars; charIndex < charCount; ++charIndex) {
         if (this.position == this.limit) {
            this.require(1);
         }

         ++this.position;
         int b = this.niobuffer.get() & 255;
         switch (b >> 4) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
               chars[charIndex] = (char)b;
            case 8:
            case 9:
            case 10:
            case 11:
            default:
               break;
            case 12:
            case 13:
               if (this.position == this.limit) {
                  this.require(1);
               }

               ++this.position;
               chars[charIndex] = (char)((b & 31) << 6 | this.niobuffer.get() & 63);
               break;
            case 14:
               this.require(2);
               this.position += 2;
               int b2 = this.niobuffer.get();
               int b3 = this.niobuffer.get();
               chars[charIndex] = (char)((b & 15) << 12 | (b2 & 63) << 6 | b3 & 63);
         }
      }

   }

   private String readAscii() {
      int end = this.position;
      int start = end - 1;
      int limit = this.limit;

      while(end != limit) {
         ++end;
         int b = this.niobuffer.get();
         if ((b & 128) != 0) {
            this.niobuffer.put(end - 1, (byte)(this.niobuffer.get(end - 1) & 127));
            byte[] tmp = new byte[end - start];
            this.niobuffer.position(start);
            this.niobuffer.get(tmp);
            String value = new String(tmp, 0, 0, end - start);
            this.niobuffer.put(end - 1, (byte)(this.niobuffer.get(end - 1) | 128));
            this.position = end;
            this.niobuffer.position(this.position);
            return value;
         }
      }

      return this.readAscii_slow();
   }

   private String readAscii_slow() {
      --this.position;
      int charCount = this.limit - this.position;
      if (charCount > this.chars.length) {
         this.chars = new char[charCount * 2];
      }

      char[] chars = this.chars;
      int i = this.position;
      int ii = 0;

      for(int n = this.limit; i < n; ++ii) {
         chars[ii] = (char)this.niobuffer.get(i);
         ++i;
      }

      this.position = this.limit;

      while(true) {
         this.require(1);
         ++this.position;
         i = this.niobuffer.get();
         if (charCount == chars.length) {
            char[] newChars = new char[charCount * 2];
            System.arraycopy(chars, 0, newChars, 0, charCount);
            chars = newChars;
            this.chars = newChars;
         }

         if ((i & 128) == 128) {
            chars[charCount++] = (char)(i & 127);
            return new String(chars, 0, charCount);
         }

         chars[charCount++] = (char)i;
      }
   }

   public StringBuilder readStringBuilder() {
      this.niobuffer.position(this.position);
      int available = this.require(1);
      ++this.position;
      int b = this.niobuffer.get();
      if ((b & 128) == 0) {
         return new StringBuilder(this.readAscii());
      } else {
         int charCount = available >= 5 ? this.readUtf8Length(b) : this.readUtf8Length_slow(b);
         switch (charCount) {
            case 0:
               return null;
            case 1:
               return new StringBuilder("");
            default:
               --charCount;
               if (this.chars.length < charCount) {
                  this.chars = new char[charCount];
               }

               this.readUtf8(charCount);
               StringBuilder builder = new StringBuilder(charCount);
               builder.append(this.chars, 0, charCount);
               return builder;
         }
      }
   }

   public float readFloat() throws KryoException {
      this.require(4);
      this.position += 4;
      return this.niobuffer.getFloat();
   }

   public float readFloat(float precision, boolean optimizePositive) throws KryoException {
      return (float)this.readInt(optimizePositive) / precision;
   }

   public short readShort() throws KryoException {
      this.require(2);
      this.position += 2;
      return this.niobuffer.getShort();
   }

   public int readShortUnsigned() throws KryoException {
      this.require(2);
      this.position += 2;
      return this.niobuffer.getShort();
   }

   public long readLong() throws KryoException {
      this.require(8);
      this.position += 8;
      return this.niobuffer.getLong();
   }

   public long readLong(boolean optimizePositive) throws KryoException {
      return this.varIntsEnabled ? this.readVarLong(optimizePositive) : this.readLong();
   }

   public long readVarLong(boolean optimizePositive) throws KryoException {
      this.niobuffer.position(this.position);
      if (this.require(1) < 9) {
         return this.readLong_slow(optimizePositive);
      } else {
         ++this.position;
         int b = this.niobuffer.get();
         long result = (long)(b & 127);
         if ((b & 128) != 0) {
            ++this.position;
            b = this.niobuffer.get();
            result |= (long)((b & 127) << 7);
            if ((b & 128) != 0) {
               ++this.position;
               b = this.niobuffer.get();
               result |= (long)((b & 127) << 14);
               if ((b & 128) != 0) {
                  ++this.position;
                  b = this.niobuffer.get();
                  result |= (long)((b & 127) << 21);
                  if ((b & 128) != 0) {
                     ++this.position;
                     b = this.niobuffer.get();
                     result |= (long)(b & 127) << 28;
                     if ((b & 128) != 0) {
                        ++this.position;
                        b = this.niobuffer.get();
                        result |= (long)(b & 127) << 35;
                        if ((b & 128) != 0) {
                           ++this.position;
                           b = this.niobuffer.get();
                           result |= (long)(b & 127) << 42;
                           if ((b & 128) != 0) {
                              ++this.position;
                              b = this.niobuffer.get();
                              result |= (long)(b & 127) << 49;
                              if ((b & 128) != 0) {
                                 ++this.position;
                                 b = this.niobuffer.get();
                                 result |= (long)b << 56;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (!optimizePositive) {
            result = result >>> 1 ^ -(result & 1L);
         }

         return result;
      }
   }

   private long readLong_slow(boolean optimizePositive) {
      ++this.position;
      int b = this.niobuffer.get();
      long result = (long)(b & 127);
      if ((b & 128) != 0) {
         this.require(1);
         ++this.position;
         b = this.niobuffer.get();
         result |= (long)((b & 127) << 7);
         if ((b & 128) != 0) {
            this.require(1);
            ++this.position;
            b = this.niobuffer.get();
            result |= (long)((b & 127) << 14);
            if ((b & 128) != 0) {
               this.require(1);
               ++this.position;
               b = this.niobuffer.get();
               result |= (long)((b & 127) << 21);
               if ((b & 128) != 0) {
                  this.require(1);
                  ++this.position;
                  b = this.niobuffer.get();
                  result |= (long)(b & 127) << 28;
                  if ((b & 128) != 0) {
                     this.require(1);
                     ++this.position;
                     b = this.niobuffer.get();
                     result |= (long)(b & 127) << 35;
                     if ((b & 128) != 0) {
                        this.require(1);
                        ++this.position;
                        b = this.niobuffer.get();
                        result |= (long)(b & 127) << 42;
                        if ((b & 128) != 0) {
                           this.require(1);
                           ++this.position;
                           b = this.niobuffer.get();
                           result |= (long)(b & 127) << 49;
                           if ((b & 128) != 0) {
                              this.require(1);
                              ++this.position;
                              b = this.niobuffer.get();
                              result |= (long)b << 56;
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      if (!optimizePositive) {
         result = result >>> 1 ^ -(result & 1L);
      }

      return result;
   }

   public boolean readBoolean() throws KryoException {
      this.require(1);
      ++this.position;
      return this.niobuffer.get() == 1;
   }

   public char readChar() throws KryoException {
      this.require(2);
      this.position += 2;
      return this.niobuffer.getChar();
   }

   public double readDouble() throws KryoException {
      this.require(8);
      this.position += 8;
      return this.niobuffer.getDouble();
   }

   public double readDouble(double precision, boolean optimizePositive) throws KryoException {
      return (double)this.readLong(optimizePositive) / precision;
   }

   public int[] readInts(int length) throws KryoException {
      if (this.capacity - this.position >= length * 4 && this.isNativeOrder()) {
         int[] array = new int[length];
         IntBuffer buf = this.niobuffer.asIntBuffer();
         buf.get(array);
         this.position += length * 4;
         this.niobuffer.position(this.position);
         return array;
      } else {
         return super.readInts(length);
      }
   }

   public long[] readLongs(int length) throws KryoException {
      if (this.capacity - this.position >= length * 8 && this.isNativeOrder()) {
         long[] array = new long[length];
         LongBuffer buf = this.niobuffer.asLongBuffer();
         buf.get(array);
         this.position += length * 8;
         this.niobuffer.position(this.position);
         return array;
      } else {
         return super.readLongs(length);
      }
   }

   public float[] readFloats(int length) throws KryoException {
      if (this.capacity - this.position >= length * 4 && this.isNativeOrder()) {
         float[] array = new float[length];
         FloatBuffer buf = this.niobuffer.asFloatBuffer();
         buf.get(array);
         this.position += length * 4;
         this.niobuffer.position(this.position);
         return array;
      } else {
         return super.readFloats(length);
      }
   }

   public short[] readShorts(int length) throws KryoException {
      if (this.capacity - this.position >= length * 2 && this.isNativeOrder()) {
         short[] array = new short[length];
         ShortBuffer buf = this.niobuffer.asShortBuffer();
         buf.get(array);
         this.position += length * 2;
         this.niobuffer.position(this.position);
         return array;
      } else {
         return super.readShorts(length);
      }
   }

   public char[] readChars(int length) throws KryoException {
      if (this.capacity - this.position >= length * 2 && this.isNativeOrder()) {
         char[] array = new char[length];
         CharBuffer buf = this.niobuffer.asCharBuffer();
         buf.get(array);
         this.position += length * 2;
         this.niobuffer.position(this.position);
         return array;
      } else {
         return super.readChars(length);
      }
   }

   public double[] readDoubles(int length) throws KryoException {
      if (this.capacity - this.position >= length * 8 && this.isNativeOrder()) {
         double[] array = new double[length];
         DoubleBuffer buf = this.niobuffer.asDoubleBuffer();
         buf.get(array);
         this.position += length * 8;
         this.niobuffer.position(this.position);
         return array;
      } else {
         return super.readDoubles(length);
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
