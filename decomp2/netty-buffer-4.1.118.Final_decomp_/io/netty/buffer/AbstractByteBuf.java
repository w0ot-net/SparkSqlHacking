package io.netty.buffer;

import io.netty.util.AsciiString;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;
import io.netty.util.IllegalReferenceCountException;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.ResourceLeakDetectorFactory;
import io.netty.util.internal.MathUtil;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;

public abstract class AbstractByteBuf extends ByteBuf {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(AbstractByteBuf.class);
   private static final String LEGACY_PROP_CHECK_ACCESSIBLE = "io.netty.buffer.bytebuf.checkAccessible";
   private static final String PROP_CHECK_ACCESSIBLE = "io.netty.buffer.checkAccessible";
   static final boolean checkAccessible;
   private static final String PROP_CHECK_BOUNDS = "io.netty.buffer.checkBounds";
   private static final boolean checkBounds;
   static final ResourceLeakDetector leakDetector;
   int readerIndex;
   int writerIndex;
   private int markedReaderIndex;
   private int markedWriterIndex;
   private int maxCapacity;

   protected AbstractByteBuf(int maxCapacity) {
      ObjectUtil.checkPositiveOrZero(maxCapacity, "maxCapacity");
      this.maxCapacity = maxCapacity;
   }

   public boolean isReadOnly() {
      return false;
   }

   public ByteBuf asReadOnly() {
      return (ByteBuf)(this.isReadOnly() ? this : Unpooled.unmodifiableBuffer((ByteBuf)this));
   }

   public int maxCapacity() {
      return this.maxCapacity;
   }

   protected final void maxCapacity(int maxCapacity) {
      this.maxCapacity = maxCapacity;
   }

   public int readerIndex() {
      return this.readerIndex;
   }

   private static void checkIndexBounds(int readerIndex, int writerIndex, int capacity) {
      if (readerIndex < 0 || readerIndex > writerIndex || writerIndex > capacity) {
         throw new IndexOutOfBoundsException(String.format("readerIndex: %d, writerIndex: %d (expected: 0 <= readerIndex <= writerIndex <= capacity(%d))", readerIndex, writerIndex, capacity));
      }
   }

   public ByteBuf readerIndex(int readerIndex) {
      if (checkBounds) {
         checkIndexBounds(readerIndex, this.writerIndex, this.capacity());
      }

      this.readerIndex = readerIndex;
      return this;
   }

   public int writerIndex() {
      return this.writerIndex;
   }

   public ByteBuf writerIndex(int writerIndex) {
      if (checkBounds) {
         checkIndexBounds(this.readerIndex, writerIndex, this.capacity());
      }

      this.writerIndex = writerIndex;
      return this;
   }

   public ByteBuf setIndex(int readerIndex, int writerIndex) {
      if (checkBounds) {
         checkIndexBounds(readerIndex, writerIndex, this.capacity());
      }

      this.setIndex0(readerIndex, writerIndex);
      return this;
   }

   public ByteBuf clear() {
      this.readerIndex = this.writerIndex = 0;
      return this;
   }

   public boolean isReadable() {
      return this.writerIndex > this.readerIndex;
   }

   public boolean isReadable(int numBytes) {
      return this.writerIndex - this.readerIndex >= numBytes;
   }

   public boolean isWritable() {
      return this.capacity() > this.writerIndex;
   }

   public boolean isWritable(int numBytes) {
      return this.capacity() - this.writerIndex >= numBytes;
   }

   public int readableBytes() {
      return this.writerIndex - this.readerIndex;
   }

   public int writableBytes() {
      return this.capacity() - this.writerIndex;
   }

   public int maxWritableBytes() {
      return this.maxCapacity() - this.writerIndex;
   }

   public ByteBuf markReaderIndex() {
      this.markedReaderIndex = this.readerIndex;
      return this;
   }

   public ByteBuf resetReaderIndex() {
      this.readerIndex(this.markedReaderIndex);
      return this;
   }

   public ByteBuf markWriterIndex() {
      this.markedWriterIndex = this.writerIndex;
      return this;
   }

   public ByteBuf resetWriterIndex() {
      this.writerIndex(this.markedWriterIndex);
      return this;
   }

   public ByteBuf discardReadBytes() {
      if (this.readerIndex == 0) {
         this.ensureAccessible();
         return this;
      } else {
         if (this.readerIndex != this.writerIndex) {
            this.setBytes(0, this, this.readerIndex, this.writerIndex - this.readerIndex);
            this.writerIndex -= this.readerIndex;
            this.adjustMarkers(this.readerIndex);
            this.readerIndex = 0;
         } else {
            this.ensureAccessible();
            this.adjustMarkers(this.readerIndex);
            this.writerIndex = this.readerIndex = 0;
         }

         return this;
      }
   }

   public ByteBuf discardSomeReadBytes() {
      if (this.readerIndex > 0) {
         if (this.readerIndex == this.writerIndex) {
            this.ensureAccessible();
            this.adjustMarkers(this.readerIndex);
            this.writerIndex = this.readerIndex = 0;
            return this;
         }

         if (this.readerIndex >= this.capacity() >>> 1) {
            this.setBytes(0, this, this.readerIndex, this.writerIndex - this.readerIndex);
            this.writerIndex -= this.readerIndex;
            this.adjustMarkers(this.readerIndex);
            this.readerIndex = 0;
            return this;
         }
      }

      this.ensureAccessible();
      return this;
   }

   protected final void adjustMarkers(int decrement) {
      if (this.markedReaderIndex <= decrement) {
         this.markedReaderIndex = 0;
         if (this.markedWriterIndex <= decrement) {
            this.markedWriterIndex = 0;
         } else {
            this.markedWriterIndex -= decrement;
         }
      } else {
         this.markedReaderIndex -= decrement;
         this.markedWriterIndex -= decrement;
      }

   }

   protected final void trimIndicesToCapacity(int newCapacity) {
      if (this.writerIndex() > newCapacity) {
         this.setIndex0(Math.min(this.readerIndex(), newCapacity), newCapacity);
      }

   }

   public ByteBuf ensureWritable(int minWritableBytes) {
      this.ensureWritable0(ObjectUtil.checkPositiveOrZero(minWritableBytes, "minWritableBytes"));
      return this;
   }

   final void ensureWritable0(int minWritableBytes) {
      int writerIndex = this.writerIndex();
      int targetCapacity = writerIndex + minWritableBytes;
      if (targetCapacity >= 0 & targetCapacity <= this.capacity()) {
         this.ensureAccessible();
      } else if (!checkBounds || targetCapacity >= 0 && targetCapacity <= this.maxCapacity) {
         int fastWritable = this.maxFastWritableBytes();
         int newCapacity = fastWritable >= minWritableBytes ? writerIndex + fastWritable : this.alloc().calculateNewCapacity(targetCapacity, this.maxCapacity);
         this.capacity(newCapacity);
      } else {
         this.ensureAccessible();
         throw new IndexOutOfBoundsException(String.format("writerIndex(%d) + minWritableBytes(%d) exceeds maxCapacity(%d): %s", writerIndex, minWritableBytes, this.maxCapacity, this));
      }
   }

   public int ensureWritable(int minWritableBytes, boolean force) {
      this.ensureAccessible();
      ObjectUtil.checkPositiveOrZero(minWritableBytes, "minWritableBytes");
      if (minWritableBytes <= this.writableBytes()) {
         return 0;
      } else {
         int maxCapacity = this.maxCapacity();
         int writerIndex = this.writerIndex();
         if (minWritableBytes > maxCapacity - writerIndex) {
            if (force && this.capacity() != maxCapacity) {
               this.capacity(maxCapacity);
               return 3;
            } else {
               return 1;
            }
         } else {
            int fastWritable = this.maxFastWritableBytes();
            int newCapacity = fastWritable >= minWritableBytes ? writerIndex + fastWritable : this.alloc().calculateNewCapacity(writerIndex + minWritableBytes, maxCapacity);
            this.capacity(newCapacity);
            return 2;
         }
      }
   }

   public ByteBuf order(ByteOrder endianness) {
      if (endianness == this.order()) {
         return this;
      } else {
         ObjectUtil.checkNotNull(endianness, "endianness");
         return this.newSwappedByteBuf();
      }
   }

   protected SwappedByteBuf newSwappedByteBuf() {
      return new SwappedByteBuf(this);
   }

   public byte getByte(int index) {
      this.checkIndex(index);
      return this._getByte(index);
   }

   protected abstract byte _getByte(int var1);

   public boolean getBoolean(int index) {
      return this.getByte(index) != 0;
   }

   public short getUnsignedByte(int index) {
      return (short)(this.getByte(index) & 255);
   }

   public short getShort(int index) {
      this.checkIndex(index, 2);
      return this._getShort(index);
   }

   protected abstract short _getShort(int var1);

   public short getShortLE(int index) {
      this.checkIndex(index, 2);
      return this._getShortLE(index);
   }

   protected abstract short _getShortLE(int var1);

   public int getUnsignedShort(int index) {
      return this.getShort(index) & '\uffff';
   }

   public int getUnsignedShortLE(int index) {
      return this.getShortLE(index) & '\uffff';
   }

   public int getUnsignedMedium(int index) {
      this.checkIndex(index, 3);
      return this._getUnsignedMedium(index);
   }

   protected abstract int _getUnsignedMedium(int var1);

   public int getUnsignedMediumLE(int index) {
      this.checkIndex(index, 3);
      return this._getUnsignedMediumLE(index);
   }

   protected abstract int _getUnsignedMediumLE(int var1);

   public int getMedium(int index) {
      int value = this.getUnsignedMedium(index);
      if ((value & 8388608) != 0) {
         value |= -16777216;
      }

      return value;
   }

   public int getMediumLE(int index) {
      int value = this.getUnsignedMediumLE(index);
      if ((value & 8388608) != 0) {
         value |= -16777216;
      }

      return value;
   }

   public int getInt(int index) {
      this.checkIndex(index, 4);
      return this._getInt(index);
   }

   protected abstract int _getInt(int var1);

   public int getIntLE(int index) {
      this.checkIndex(index, 4);
      return this._getIntLE(index);
   }

   protected abstract int _getIntLE(int var1);

   public long getUnsignedInt(int index) {
      return (long)this.getInt(index) & 4294967295L;
   }

   public long getUnsignedIntLE(int index) {
      return (long)this.getIntLE(index) & 4294967295L;
   }

   public long getLong(int index) {
      this.checkIndex(index, 8);
      return this._getLong(index);
   }

   protected abstract long _getLong(int var1);

   public long getLongLE(int index) {
      this.checkIndex(index, 8);
      return this._getLongLE(index);
   }

   protected abstract long _getLongLE(int var1);

   public char getChar(int index) {
      return (char)this.getShort(index);
   }

   public float getFloat(int index) {
      return Float.intBitsToFloat(this.getInt(index));
   }

   public double getDouble(int index) {
      return Double.longBitsToDouble(this.getLong(index));
   }

   public ByteBuf getBytes(int index, byte[] dst) {
      this.getBytes(index, dst, 0, dst.length);
      return this;
   }

   public ByteBuf getBytes(int index, ByteBuf dst) {
      this.getBytes(index, dst, dst.writableBytes());
      return this;
   }

   public ByteBuf getBytes(int index, ByteBuf dst, int length) {
      this.getBytes(index, dst, dst.writerIndex(), length);
      dst.writerIndex(dst.writerIndex() + length);
      return this;
   }

   public CharSequence getCharSequence(int index, int length, Charset charset) {
      return (CharSequence)(!CharsetUtil.US_ASCII.equals(charset) && !CharsetUtil.ISO_8859_1.equals(charset) ? this.toString(index, length, charset) : new AsciiString(ByteBufUtil.getBytes(this, index, length, true), false));
   }

   public CharSequence readCharSequence(int length, Charset charset) {
      CharSequence sequence = this.getCharSequence(this.readerIndex, length, charset);
      this.readerIndex += length;
      return sequence;
   }

   public ByteBuf setByte(int index, int value) {
      this.checkIndex(index);
      this._setByte(index, value);
      return this;
   }

   protected abstract void _setByte(int var1, int var2);

   public ByteBuf setBoolean(int index, boolean value) {
      this.setByte(index, value ? 1 : 0);
      return this;
   }

   public ByteBuf setShort(int index, int value) {
      this.checkIndex(index, 2);
      this._setShort(index, value);
      return this;
   }

   protected abstract void _setShort(int var1, int var2);

   public ByteBuf setShortLE(int index, int value) {
      this.checkIndex(index, 2);
      this._setShortLE(index, value);
      return this;
   }

   protected abstract void _setShortLE(int var1, int var2);

   public ByteBuf setChar(int index, int value) {
      this.setShort(index, value);
      return this;
   }

   public ByteBuf setMedium(int index, int value) {
      this.checkIndex(index, 3);
      this._setMedium(index, value);
      return this;
   }

   protected abstract void _setMedium(int var1, int var2);

   public ByteBuf setMediumLE(int index, int value) {
      this.checkIndex(index, 3);
      this._setMediumLE(index, value);
      return this;
   }

   protected abstract void _setMediumLE(int var1, int var2);

   public ByteBuf setInt(int index, int value) {
      this.checkIndex(index, 4);
      this._setInt(index, value);
      return this;
   }

   protected abstract void _setInt(int var1, int var2);

   public ByteBuf setIntLE(int index, int value) {
      this.checkIndex(index, 4);
      this._setIntLE(index, value);
      return this;
   }

   protected abstract void _setIntLE(int var1, int var2);

   public ByteBuf setFloat(int index, float value) {
      this.setInt(index, Float.floatToRawIntBits(value));
      return this;
   }

   public ByteBuf setLong(int index, long value) {
      this.checkIndex(index, 8);
      this._setLong(index, value);
      return this;
   }

   protected abstract void _setLong(int var1, long var2);

   public ByteBuf setLongLE(int index, long value) {
      this.checkIndex(index, 8);
      this._setLongLE(index, value);
      return this;
   }

   protected abstract void _setLongLE(int var1, long var2);

   public ByteBuf setDouble(int index, double value) {
      this.setLong(index, Double.doubleToRawLongBits(value));
      return this;
   }

   public ByteBuf setBytes(int index, byte[] src) {
      this.setBytes(index, src, 0, src.length);
      return this;
   }

   public ByteBuf setBytes(int index, ByteBuf src) {
      this.setBytes(index, src, src.readableBytes());
      return this;
   }

   private static void checkReadableBounds(ByteBuf src, int length) {
      if (length > src.readableBytes()) {
         throw new IndexOutOfBoundsException(String.format("length(%d) exceeds src.readableBytes(%d) where src is: %s", length, src.readableBytes(), src));
      }
   }

   public ByteBuf setBytes(int index, ByteBuf src, int length) {
      this.checkIndex(index, length);
      ObjectUtil.checkNotNull(src, "src");
      if (checkBounds) {
         checkReadableBounds(src, length);
      }

      this.setBytes(index, src, src.readerIndex(), length);
      src.readerIndex(src.readerIndex() + length);
      return this;
   }

   public ByteBuf setZero(int index, int length) {
      if (length == 0) {
         return this;
      } else {
         this.checkIndex(index, length);
         int nLong = length >>> 3;
         int nBytes = length & 7;

         for(int i = nLong; i > 0; --i) {
            this._setLong(index, 0L);
            index += 8;
         }

         if (nBytes == 4) {
            this._setInt(index, 0);
         } else if (nBytes < 4) {
            for(int i = nBytes; i > 0; --i) {
               this._setByte(index, 0);
               ++index;
            }
         } else {
            this._setInt(index, 0);
            index += 4;

            for(int i = nBytes - 4; i > 0; --i) {
               this._setByte(index, 0);
               ++index;
            }
         }

         return this;
      }
   }

   public int setCharSequence(int index, CharSequence sequence, Charset charset) {
      return this.setCharSequence0(index, sequence, charset, false);
   }

   private int setCharSequence0(int index, CharSequence sequence, Charset charset, boolean expand) {
      if (charset.equals(CharsetUtil.UTF_8)) {
         int length = ByteBufUtil.utf8MaxBytes(sequence);
         if (expand) {
            this.ensureWritable0(length);
            this.checkIndex0(index, length);
         } else {
            this.checkIndex(index, length);
         }

         return ByteBufUtil.writeUtf8(this, index, length, sequence, sequence.length());
      } else if (!charset.equals(CharsetUtil.US_ASCII) && !charset.equals(CharsetUtil.ISO_8859_1)) {
         byte[] bytes = sequence.toString().getBytes(charset);
         if (expand) {
            this.ensureWritable0(bytes.length);
         }

         this.setBytes(index, bytes);
         return bytes.length;
      } else {
         int length = sequence.length();
         if (expand) {
            this.ensureWritable0(length);
            this.checkIndex0(index, length);
         } else {
            this.checkIndex(index, length);
         }

         return ByteBufUtil.writeAscii(this, index, sequence, length);
      }
   }

   public byte readByte() {
      this.checkReadableBytes0(1);
      int i = this.readerIndex;
      byte b = this._getByte(i);
      this.readerIndex = i + 1;
      return b;
   }

   public boolean readBoolean() {
      return this.readByte() != 0;
   }

   public short readUnsignedByte() {
      return (short)(this.readByte() & 255);
   }

   public short readShort() {
      this.checkReadableBytes0(2);
      short v = this._getShort(this.readerIndex);
      this.readerIndex += 2;
      return v;
   }

   public short readShortLE() {
      this.checkReadableBytes0(2);
      short v = this._getShortLE(this.readerIndex);
      this.readerIndex += 2;
      return v;
   }

   public int readUnsignedShort() {
      return this.readShort() & '\uffff';
   }

   public int readUnsignedShortLE() {
      return this.readShortLE() & '\uffff';
   }

   public int readMedium() {
      int value = this.readUnsignedMedium();
      if ((value & 8388608) != 0) {
         value |= -16777216;
      }

      return value;
   }

   public int readMediumLE() {
      int value = this.readUnsignedMediumLE();
      if ((value & 8388608) != 0) {
         value |= -16777216;
      }

      return value;
   }

   public int readUnsignedMedium() {
      this.checkReadableBytes0(3);
      int v = this._getUnsignedMedium(this.readerIndex);
      this.readerIndex += 3;
      return v;
   }

   public int readUnsignedMediumLE() {
      this.checkReadableBytes0(3);
      int v = this._getUnsignedMediumLE(this.readerIndex);
      this.readerIndex += 3;
      return v;
   }

   public int readInt() {
      this.checkReadableBytes0(4);
      int v = this._getInt(this.readerIndex);
      this.readerIndex += 4;
      return v;
   }

   public int readIntLE() {
      this.checkReadableBytes0(4);
      int v = this._getIntLE(this.readerIndex);
      this.readerIndex += 4;
      return v;
   }

   public long readUnsignedInt() {
      return (long)this.readInt() & 4294967295L;
   }

   public long readUnsignedIntLE() {
      return (long)this.readIntLE() & 4294967295L;
   }

   public long readLong() {
      this.checkReadableBytes0(8);
      long v = this._getLong(this.readerIndex);
      this.readerIndex += 8;
      return v;
   }

   public long readLongLE() {
      this.checkReadableBytes0(8);
      long v = this._getLongLE(this.readerIndex);
      this.readerIndex += 8;
      return v;
   }

   public char readChar() {
      return (char)this.readShort();
   }

   public float readFloat() {
      return Float.intBitsToFloat(this.readInt());
   }

   public double readDouble() {
      return Double.longBitsToDouble(this.readLong());
   }

   public ByteBuf readBytes(int length) {
      this.checkReadableBytes(length);
      if (length == 0) {
         return Unpooled.EMPTY_BUFFER;
      } else {
         ByteBuf buf = this.alloc().buffer(length, this.maxCapacity);
         buf.writeBytes((ByteBuf)this, this.readerIndex, length);
         this.readerIndex += length;
         return buf;
      }
   }

   public ByteBuf readSlice(int length) {
      this.checkReadableBytes(length);
      ByteBuf slice = this.slice(this.readerIndex, length);
      this.readerIndex += length;
      return slice;
   }

   public ByteBuf readRetainedSlice(int length) {
      this.checkReadableBytes(length);
      ByteBuf slice = this.retainedSlice(this.readerIndex, length);
      this.readerIndex += length;
      return slice;
   }

   public ByteBuf readBytes(byte[] dst, int dstIndex, int length) {
      this.checkReadableBytes(length);
      this.getBytes(this.readerIndex, dst, dstIndex, length);
      this.readerIndex += length;
      return this;
   }

   public ByteBuf readBytes(byte[] dst) {
      this.readBytes((byte[])dst, 0, dst.length);
      return this;
   }

   public ByteBuf readBytes(ByteBuf dst) {
      this.readBytes(dst, dst.writableBytes());
      return this;
   }

   public ByteBuf readBytes(ByteBuf dst, int length) {
      if (checkBounds && length > dst.writableBytes()) {
         throw new IndexOutOfBoundsException(String.format("length(%d) exceeds dst.writableBytes(%d) where dst is: %s", length, dst.writableBytes(), dst));
      } else {
         this.readBytes(dst, dst.writerIndex(), length);
         dst.writerIndex(dst.writerIndex() + length);
         return this;
      }
   }

   public ByteBuf readBytes(ByteBuf dst, int dstIndex, int length) {
      this.checkReadableBytes(length);
      this.getBytes(this.readerIndex, dst, dstIndex, length);
      this.readerIndex += length;
      return this;
   }

   public ByteBuf readBytes(ByteBuffer dst) {
      int length = dst.remaining();
      this.checkReadableBytes(length);
      this.getBytes(this.readerIndex, (ByteBuffer)dst);
      this.readerIndex += length;
      return this;
   }

   public int readBytes(GatheringByteChannel out, int length) throws IOException {
      this.checkReadableBytes(length);
      int readBytes = this.getBytes(this.readerIndex, out, length);
      this.readerIndex += readBytes;
      return readBytes;
   }

   public int readBytes(FileChannel out, long position, int length) throws IOException {
      this.checkReadableBytes(length);
      int readBytes = this.getBytes(this.readerIndex, out, position, length);
      this.readerIndex += readBytes;
      return readBytes;
   }

   public ByteBuf readBytes(OutputStream out, int length) throws IOException {
      this.checkReadableBytes(length);
      this.getBytes(this.readerIndex, out, length);
      this.readerIndex += length;
      return this;
   }

   public ByteBuf skipBytes(int length) {
      this.checkReadableBytes(length);
      this.readerIndex += length;
      return this;
   }

   public ByteBuf writeBoolean(boolean value) {
      this.writeByte(value ? 1 : 0);
      return this;
   }

   public ByteBuf writeByte(int value) {
      this.ensureWritable0(1);
      this._setByte(this.writerIndex++, value);
      return this;
   }

   public ByteBuf writeShort(int value) {
      this.ensureWritable0(2);
      this._setShort(this.writerIndex, value);
      this.writerIndex += 2;
      return this;
   }

   public ByteBuf writeShortLE(int value) {
      this.ensureWritable0(2);
      this._setShortLE(this.writerIndex, value);
      this.writerIndex += 2;
      return this;
   }

   public ByteBuf writeMedium(int value) {
      this.ensureWritable0(3);
      this._setMedium(this.writerIndex, value);
      this.writerIndex += 3;
      return this;
   }

   public ByteBuf writeMediumLE(int value) {
      this.ensureWritable0(3);
      this._setMediumLE(this.writerIndex, value);
      this.writerIndex += 3;
      return this;
   }

   public ByteBuf writeInt(int value) {
      this.ensureWritable0(4);
      this._setInt(this.writerIndex, value);
      this.writerIndex += 4;
      return this;
   }

   public ByteBuf writeIntLE(int value) {
      this.ensureWritable0(4);
      this._setIntLE(this.writerIndex, value);
      this.writerIndex += 4;
      return this;
   }

   public ByteBuf writeLong(long value) {
      this.ensureWritable0(8);
      this._setLong(this.writerIndex, value);
      this.writerIndex += 8;
      return this;
   }

   public ByteBuf writeLongLE(long value) {
      this.ensureWritable0(8);
      this._setLongLE(this.writerIndex, value);
      this.writerIndex += 8;
      return this;
   }

   public ByteBuf writeChar(int value) {
      this.writeShort(value);
      return this;
   }

   public ByteBuf writeFloat(float value) {
      this.writeInt(Float.floatToRawIntBits(value));
      return this;
   }

   public ByteBuf writeDouble(double value) {
      this.writeLong(Double.doubleToRawLongBits(value));
      return this;
   }

   public ByteBuf writeBytes(byte[] src, int srcIndex, int length) {
      this.ensureWritable(length);
      this.setBytes(this.writerIndex, src, srcIndex, length);
      this.writerIndex += length;
      return this;
   }

   public ByteBuf writeBytes(byte[] src) {
      this.writeBytes((byte[])src, 0, src.length);
      return this;
   }

   public ByteBuf writeBytes(ByteBuf src) {
      this.writeBytes(src, src.readableBytes());
      return this;
   }

   public ByteBuf writeBytes(ByteBuf src, int length) {
      if (checkBounds) {
         checkReadableBounds(src, length);
      }

      this.writeBytes(src, src.readerIndex(), length);
      src.readerIndex(src.readerIndex() + length);
      return this;
   }

   public ByteBuf writeBytes(ByteBuf src, int srcIndex, int length) {
      this.ensureWritable(length);
      this.setBytes(this.writerIndex, src, srcIndex, length);
      this.writerIndex += length;
      return this;
   }

   public ByteBuf writeBytes(ByteBuffer src) {
      int length = src.remaining();
      this.ensureWritable0(length);
      this.setBytes(this.writerIndex, (ByteBuffer)src);
      this.writerIndex += length;
      return this;
   }

   public int writeBytes(InputStream in, int length) throws IOException {
      this.ensureWritable(length);
      int writtenBytes = this.setBytes(this.writerIndex, in, length);
      if (writtenBytes > 0) {
         this.writerIndex += writtenBytes;
      }

      return writtenBytes;
   }

   public int writeBytes(ScatteringByteChannel in, int length) throws IOException {
      this.ensureWritable(length);
      int writtenBytes = this.setBytes(this.writerIndex, in, length);
      if (writtenBytes > 0) {
         this.writerIndex += writtenBytes;
      }

      return writtenBytes;
   }

   public int writeBytes(FileChannel in, long position, int length) throws IOException {
      this.ensureWritable(length);
      int writtenBytes = this.setBytes(this.writerIndex, in, position, length);
      if (writtenBytes > 0) {
         this.writerIndex += writtenBytes;
      }

      return writtenBytes;
   }

   public ByteBuf writeZero(int length) {
      if (length == 0) {
         return this;
      } else {
         this.ensureWritable(length);
         int wIndex = this.writerIndex;
         this.checkIndex0(wIndex, length);
         int nLong = length >>> 3;
         int nBytes = length & 7;

         for(int i = nLong; i > 0; --i) {
            this._setLong(wIndex, 0L);
            wIndex += 8;
         }

         if (nBytes == 4) {
            this._setInt(wIndex, 0);
            wIndex += 4;
         } else if (nBytes < 4) {
            for(int i = nBytes; i > 0; --i) {
               this._setByte(wIndex, 0);
               ++wIndex;
            }
         } else {
            this._setInt(wIndex, 0);
            wIndex += 4;

            for(int i = nBytes - 4; i > 0; --i) {
               this._setByte(wIndex, 0);
               ++wIndex;
            }
         }

         this.writerIndex = wIndex;
         return this;
      }
   }

   public int writeCharSequence(CharSequence sequence, Charset charset) {
      int written = this.setCharSequence0(this.writerIndex, sequence, charset, true);
      this.writerIndex += written;
      return written;
   }

   public ByteBuf copy() {
      return this.copy(this.readerIndex, this.readableBytes());
   }

   public ByteBuf duplicate() {
      this.ensureAccessible();
      return new UnpooledDuplicatedByteBuf(this);
   }

   public ByteBuf retainedDuplicate() {
      return this.duplicate().retain();
   }

   public ByteBuf slice() {
      return this.slice(this.readerIndex, this.readableBytes());
   }

   public ByteBuf retainedSlice() {
      return this.slice().retain();
   }

   public ByteBuf slice(int index, int length) {
      this.ensureAccessible();
      return new UnpooledSlicedByteBuf(this, index, length);
   }

   public ByteBuf retainedSlice(int index, int length) {
      return this.slice(index, length).retain();
   }

   public ByteBuffer nioBuffer() {
      return this.nioBuffer(this.readerIndex, this.readableBytes());
   }

   public ByteBuffer[] nioBuffers() {
      return this.nioBuffers(this.readerIndex, this.readableBytes());
   }

   public String toString(Charset charset) {
      return this.toString(this.readerIndex, this.readableBytes(), charset);
   }

   public String toString(int index, int length, Charset charset) {
      return ByteBufUtil.decodeString(this, index, length, charset);
   }

   public int indexOf(int fromIndex, int toIndex, byte value) {
      return fromIndex <= toIndex ? ByteBufUtil.firstIndexOf(this, fromIndex, toIndex, value) : ByteBufUtil.lastIndexOf(this, fromIndex, toIndex, value);
   }

   public int bytesBefore(byte value) {
      return this.bytesBefore(this.readerIndex(), this.readableBytes(), value);
   }

   public int bytesBefore(int length, byte value) {
      this.checkReadableBytes(length);
      return this.bytesBefore(this.readerIndex(), length, value);
   }

   public int bytesBefore(int index, int length, byte value) {
      int endIndex = this.indexOf(index, index + length, value);
      return endIndex < 0 ? -1 : endIndex - index;
   }

   public int forEachByte(ByteProcessor processor) {
      this.ensureAccessible();

      try {
         return this.forEachByteAsc0(this.readerIndex, this.writerIndex, processor);
      } catch (Exception e) {
         PlatformDependent.throwException(e);
         return -1;
      }
   }

   public int forEachByte(int index, int length, ByteProcessor processor) {
      this.checkIndex(index, length);

      try {
         return this.forEachByteAsc0(index, index + length, processor);
      } catch (Exception e) {
         PlatformDependent.throwException(e);
         return -1;
      }
   }

   int forEachByteAsc0(int start, int end, ByteProcessor processor) throws Exception {
      while(start < end) {
         if (!processor.process(this._getByte(start))) {
            return start;
         }

         ++start;
      }

      return -1;
   }

   public int forEachByteDesc(ByteProcessor processor) {
      this.ensureAccessible();

      try {
         return this.forEachByteDesc0(this.writerIndex - 1, this.readerIndex, processor);
      } catch (Exception e) {
         PlatformDependent.throwException(e);
         return -1;
      }
   }

   public int forEachByteDesc(int index, int length, ByteProcessor processor) {
      this.checkIndex(index, length);

      try {
         return this.forEachByteDesc0(index + length - 1, index, processor);
      } catch (Exception e) {
         PlatformDependent.throwException(e);
         return -1;
      }
   }

   int forEachByteDesc0(int rStart, int rEnd, ByteProcessor processor) throws Exception {
      while(rStart >= rEnd) {
         if (!processor.process(this._getByte(rStart))) {
            return rStart;
         }

         --rStart;
      }

      return -1;
   }

   public int hashCode() {
      return ByteBufUtil.hashCode(this);
   }

   public boolean equals(Object o) {
      return o instanceof ByteBuf && ByteBufUtil.equals(this, (ByteBuf)o);
   }

   public int compareTo(ByteBuf that) {
      return ByteBufUtil.compare(this, that);
   }

   public String toString() {
      if (this.refCnt() == 0) {
         return StringUtil.simpleClassName(this) + "(freed)";
      } else {
         StringBuilder buf = (new StringBuilder()).append(StringUtil.simpleClassName(this)).append("(ridx: ").append(this.readerIndex).append(", widx: ").append(this.writerIndex).append(", cap: ").append(this.capacity());
         if (this.maxCapacity != Integer.MAX_VALUE) {
            buf.append('/').append(this.maxCapacity);
         }

         ByteBuf unwrapped = this.unwrap();
         if (unwrapped != null) {
            buf.append(", unwrapped: ").append(unwrapped);
         }

         buf.append(')');
         return buf.toString();
      }
   }

   protected final void checkIndex(int index) {
      this.checkIndex(index, 1);
   }

   protected final void checkIndex(int index, int fieldLength) {
      this.ensureAccessible();
      this.checkIndex0(index, fieldLength);
   }

   private static void checkRangeBounds(String indexName, int index, int fieldLength, int capacity) {
      if (MathUtil.isOutOfBounds(index, fieldLength, capacity)) {
         throw new IndexOutOfBoundsException(String.format("%s: %d, length: %d (expected: range(0, %d))", indexName, index, fieldLength, capacity));
      }
   }

   final void checkIndex0(int index, int fieldLength) {
      if (checkBounds) {
         checkRangeBounds("index", index, fieldLength, this.capacity());
      }

   }

   protected final void checkSrcIndex(int index, int length, int srcIndex, int srcCapacity) {
      this.checkIndex(index, length);
      if (checkBounds) {
         checkRangeBounds("srcIndex", srcIndex, length, srcCapacity);
      }

   }

   protected final void checkDstIndex(int index, int length, int dstIndex, int dstCapacity) {
      this.checkIndex(index, length);
      if (checkBounds) {
         checkRangeBounds("dstIndex", dstIndex, length, dstCapacity);
      }

   }

   protected final void checkDstIndex(int length, int dstIndex, int dstCapacity) {
      this.checkReadableBytes(length);
      if (checkBounds) {
         checkRangeBounds("dstIndex", dstIndex, length, dstCapacity);
      }

   }

   protected final void checkReadableBytes(int minimumReadableBytes) {
      this.checkReadableBytes0(ObjectUtil.checkPositiveOrZero(minimumReadableBytes, "minimumReadableBytes"));
   }

   protected final void checkNewCapacity(int newCapacity) {
      this.ensureAccessible();
      if (checkBounds && (newCapacity < 0 || newCapacity > this.maxCapacity())) {
         throw new IllegalArgumentException("newCapacity: " + newCapacity + " (expected: 0-" + this.maxCapacity() + ')');
      }
   }

   private void checkReadableBytes0(int minimumReadableBytes) {
      this.ensureAccessible();
      if (checkBounds && this.readerIndex > this.writerIndex - minimumReadableBytes) {
         throw new IndexOutOfBoundsException(String.format("readerIndex(%d) + length(%d) exceeds writerIndex(%d): %s", this.readerIndex, minimumReadableBytes, this.writerIndex, this));
      }
   }

   protected final void ensureAccessible() {
      if (checkAccessible && !this.isAccessible()) {
         throw new IllegalReferenceCountException(0);
      }
   }

   final void setIndex0(int readerIndex, int writerIndex) {
      this.readerIndex = readerIndex;
      this.writerIndex = writerIndex;
   }

   final void discardMarks() {
      this.markedReaderIndex = this.markedWriterIndex = 0;
   }

   static {
      if (SystemPropertyUtil.contains("io.netty.buffer.checkAccessible")) {
         checkAccessible = SystemPropertyUtil.getBoolean("io.netty.buffer.checkAccessible", true);
      } else {
         checkAccessible = SystemPropertyUtil.getBoolean("io.netty.buffer.bytebuf.checkAccessible", true);
      }

      checkBounds = SystemPropertyUtil.getBoolean("io.netty.buffer.checkBounds", true);
      if (logger.isDebugEnabled()) {
         logger.debug("-D{}: {}", "io.netty.buffer.checkAccessible", checkAccessible);
         logger.debug("-D{}: {}", "io.netty.buffer.checkBounds", checkBounds);
      }

      leakDetector = ResourceLeakDetectorFactory.instance().newResourceLeakDetector(ByteBuf.class);
   }
}
