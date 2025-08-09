package io.netty.buffer;

import io.netty.util.internal.PlatformDependent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteOrder;
import java.util.concurrent.atomic.AtomicLong;

public class UnsafeDirectLittleEndian extends WrappedByteBuf {
   private static final AtomicLong ID_GENERATOR = new AtomicLong(0L);
   public final long id;
   private final AbstractByteBuf wrapped;
   private final long memoryAddress;

   public UnsafeDirectLittleEndian(AbstractByteBuf buf) {
      super(buf);
      this.id = ID_GENERATOR.incrementAndGet();
      this.wrapped = buf;
      this.memoryAddress = buf.memoryAddress();
   }

   private long addr(int index) {
      return this.memoryAddress + (long)index;
   }

   public long getLong(int index) {
      long v = PlatformDependent.getLong(this.addr(index));
      return v;
   }

   public float getFloat(int index) {
      return Float.intBitsToFloat(this.getInt(index));
   }

   public ByteBuf slice() {
      return this.slice(this.readerIndex(), this.readableBytes());
   }

   public ByteBuf slice(int index, int length) {
      return new SlicedByteBuf(this, index, length);
   }

   public ByteBuf order(ByteOrder endianness) {
      return this;
   }

   public double getDouble(int index) {
      return Double.longBitsToDouble(this.getLong(index));
   }

   public char getChar(int index) {
      return (char)this.getShort(index);
   }

   public long getUnsignedInt(int index) {
      return (long)this.getInt(index) & 4294967295L;
   }

   public int getInt(int index) {
      int v = PlatformDependent.getInt(this.addr(index));
      return v;
   }

   public int getUnsignedShort(int index) {
      return this.getShort(index) & '\uffff';
   }

   public short getShort(int index) {
      short v = PlatformDependent.getShort(this.addr(index));
      return v;
   }

   public ByteBuf setShort(int index, int value) {
      this.wrapped.checkIndex(index, 2);
      this.setShort_(index, value);
      return this;
   }

   public ByteBuf setInt(int index, int value) {
      this.wrapped.checkIndex(index, 4);
      this.setInt_(index, value);
      return this;
   }

   public ByteBuf setLong(int index, long value) {
      this.wrapped.checkIndex(index, 8);
      this.setLong_(index, value);
      return this;
   }

   public ByteBuf setChar(int index, int value) {
      this.setShort(index, value);
      return this;
   }

   public ByteBuf setFloat(int index, float value) {
      this.setInt(index, Float.floatToRawIntBits(value));
      return this;
   }

   public ByteBuf setDouble(int index, double value) {
      this.setLong(index, Double.doubleToRawLongBits(value));
      return this;
   }

   public ByteBuf writeShort(int value) {
      this.wrapped.ensureWritable(2);
      this.setShort_(this.wrapped.writerIndex, value);
      AbstractByteBuf var10000 = this.wrapped;
      var10000.writerIndex += 2;
      return this;
   }

   public ByteBuf writeInt(int value) {
      this.wrapped.ensureWritable(4);
      this.setInt_(this.wrapped.writerIndex, value);
      AbstractByteBuf var10000 = this.wrapped;
      var10000.writerIndex += 4;
      return this;
   }

   public ByteBuf writeLong(long value) {
      this.wrapped.ensureWritable(8);
      this.setLong_(this.wrapped.writerIndex, value);
      AbstractByteBuf var10000 = this.wrapped;
      var10000.writerIndex += 8;
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

   private void setShort_(int index, int value) {
      PlatformDependent.putShort(this.addr(index), (short)value);
   }

   private void setInt_(int index, int value) {
      PlatformDependent.putInt(this.addr(index), value);
   }

   private void setLong_(int index, long value) {
      PlatformDependent.putLong(this.addr(index), value);
   }

   public byte getByte(int index) {
      return PlatformDependent.getByte(this.addr(index));
   }

   public ByteBuf setByte(int index, int value) {
      PlatformDependent.putByte(this.addr(index), (byte)value);
      return this;
   }

   public boolean release() {
      return this.release(1);
   }

   public int setBytes(int index, InputStream in, int length) throws IOException {
      this.wrapped.checkIndex(index, length);
      byte[] tmp = new byte[length];
      int readBytes = in.read(tmp);
      if (readBytes > 0) {
         PlatformDependent.copyMemory(tmp, 0, this.addr(index), (long)readBytes);
      }

      return readBytes;
   }

   public ByteBuf getBytes(int index, OutputStream out, int length) throws IOException {
      this.wrapped.checkIndex(index, length);
      if (length != 0) {
         byte[] tmp = new byte[length];
         PlatformDependent.copyMemory(this.addr(index), tmp, 0, (long)length);
         out.write(tmp);
      }

      return this;
   }

   public int hashCode() {
      return System.identityHashCode(this);
   }

   public boolean equals(Object obj) {
      return this == obj;
   }
}
