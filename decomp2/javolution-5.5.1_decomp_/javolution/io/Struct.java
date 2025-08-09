package javolution.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javolution.lang.Configurable;
import javolution.lang.MathLib;
import javolution.lang.Reflection;
import javolution.text.TextBuilder;

public class Struct {
   public static final Configurable MAXIMUM_ALIGNMENT = new Configurable(new Integer(4)) {
   };
   private Struct _outer;
   private ByteBuffer _byteBuffer;
   private int _outerOffset;
   private int _alignment = 1;
   private int _length;
   private int _index;
   private int _wordSize;
   private int _bitsUsed;
   private boolean _resetIndex = this.isUnion();
   private byte[] _bytes;
   private static final Reflection.Method ADDRESS_METHOD = Reflection.getInstance().getMethod("sun.nio.ch.DirectBuffer.address()");
   private static final char[] HEXA = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
   private static final Class BOOL = (new Bool[0]).getClass();
   private static final Class SIGNED_8 = (new Signed8[0]).getClass();
   private static final Class UNSIGNED_8 = (new Unsigned8[0]).getClass();
   private static final Class SIGNED_16 = (new Signed16[0]).getClass();
   private static final Class UNSIGNED_16 = (new Unsigned16[0]).getClass();
   private static final Class SIGNED_32 = (new Signed32[0]).getClass();
   private static final Class UNSIGNED_32 = (new Unsigned32[0]).getClass();
   private static final Class SIGNED_64 = (new Signed64[0]).getClass();
   private static final Class FLOAT_32 = (new Float32[0]).getClass();
   private static final Class FLOAT_64 = (new Float64[0]).getClass();

   public final int size() {
      return this._alignment <= 1 ? this._length : (this._length + this._alignment - 1) / this._alignment * this._alignment;
   }

   public Struct outer() {
      return this._outer;
   }

   public final ByteBuffer getByteBuffer() {
      if (this._outer != null) {
         return this._outer.getByteBuffer();
      } else {
         return this._byteBuffer != null ? this._byteBuffer : this.newBuffer();
      }
   }

   private synchronized ByteBuffer newBuffer() {
      if (this._byteBuffer != null) {
         return this._byteBuffer;
      } else {
         ByteBuffer bf = ByteBuffer.allocateDirect(this.size());
         bf.order(this.byteOrder());
         this.setByteBuffer(bf, 0);
         return this._byteBuffer;
      }
   }

   public final Struct setByteBuffer(ByteBuffer byteBuffer, int position) {
      if (byteBuffer.order() != this.byteOrder()) {
         throw new IllegalArgumentException("The byte order of the specified byte buffer is different from this struct byte order");
      } else if (this._outer != null) {
         throw new UnsupportedOperationException("Inner struct byte buffer is inherited from outer");
      } else {
         this._byteBuffer = byteBuffer;
         this._outerOffset = position;
         return this;
      }
   }

   public final Struct setByteBufferPosition(int position) {
      return this.setByteBuffer(this.getByteBuffer(), position);
   }

   public final int getByteBufferPosition() {
      return this._outer != null ? this._outer.getByteBufferPosition() + this._outerOffset : this._outerOffset;
   }

   public int read(InputStream in) throws IOException {
      ByteBuffer buffer = this.getByteBuffer();
      if (buffer.hasArray()) {
         int offset = buffer.arrayOffset() + this.getByteBufferPosition();
         return in.read(buffer.array(), offset, this.size());
      } else {
         synchronized(buffer) {
            if (this._bytes == null) {
               this._bytes = new byte[this.size()];
            }

            int bytesRead = in.read(this._bytes);
            buffer.position(this.getByteBufferPosition());
            buffer.put(this._bytes);
            return bytesRead;
         }
      }
   }

   public void write(OutputStream out) throws IOException {
      ByteBuffer buffer = this.getByteBuffer();
      if (buffer.hasArray()) {
         int offset = buffer.arrayOffset() + this.getByteBufferPosition();
         out.write(buffer.array(), offset, this.size());
      } else {
         synchronized(buffer) {
            if (this._bytes == null) {
               this._bytes = new byte[this.size()];
            }

            buffer.position(this.getByteBufferPosition());
            buffer.get(this._bytes);
            out.write(this._bytes);
         }
      }

   }

   public final long address() {
      ByteBuffer thisBuffer = this.getByteBuffer();
      if (ADDRESS_METHOD != null) {
         Long start = (Long)ADDRESS_METHOD.invoke(thisBuffer);
         return start + (long)this.getByteBufferPosition();
      } else {
         throw new UnsupportedOperationException("Operation not supported for " + thisBuffer.getClass());
      }
   }

   public String toString() {
      TextBuilder tmp = TextBuilder.newInstance();

      String var10;
      try {
         int size = this.size();
         ByteBuffer buffer = this.getByteBuffer();
         int start = this.getByteBufferPosition();

         for(int i = 0; i < size; ++i) {
            int b = buffer.get(start + i) & 255;
            tmp.append(HEXA[b >> 4]);
            tmp.append(HEXA[b & 15]);
            tmp.append((char)((i & 15) == 15 ? '\n' : ' '));
         }

         var10 = tmp.toString();
      } finally {
         TextBuilder.recycle(tmp);
      }

      return var10;
   }

   public boolean isUnion() {
      return false;
   }

   public ByteOrder byteOrder() {
      return this._outer != null ? this._outer.byteOrder() : ByteOrder.BIG_ENDIAN;
   }

   public boolean isPacked() {
      return false;
   }

   protected Struct inner(Struct struct) {
      if (struct._outer != null) {
         throw new IllegalArgumentException("struct: Already an inner struct");
      } else {
         Member inner = new Member(struct.size() << 3, struct._alignment);
         struct._outer = this;
         struct._outerOffset = inner.offset();
         return struct;
      }
   }

   protected Struct[] array(Struct[] structs) {
      Class structClass = null;
      boolean resetIndexSaved = this._resetIndex;
      if (this._resetIndex) {
         this._index = 0;
         this._resetIndex = false;
      }

      S struct;
      for(int i = 0; i < structs.length; structs[i++] = this.inner(struct)) {
         struct = (S)structs[i];
         if (struct == null) {
            try {
               if (structClass == null) {
                  String arrayName = structs.getClass().getName();
                  String structName = arrayName.substring(2, arrayName.length() - 1);
                  structClass = Reflection.getInstance().getClass(structName);
                  if (structClass == null) {
                     throw new IllegalArgumentException("Struct class: " + structName + " not found");
                  }
               }

               struct = (S)((Struct)structClass.newInstance());
            } catch (Exception e) {
               throw new RuntimeException(e.getMessage());
            }
         }
      }

      this._resetIndex = resetIndexSaved;
      return (Struct[])structs;
   }

   protected Struct[][] array(Struct[][] structs) {
      boolean resetIndexSaved = this._resetIndex;
      if (this._resetIndex) {
         this._index = 0;
         this._resetIndex = false;
      }

      for(int i = 0; i < structs.length; ++i) {
         this.array(structs[i]);
      }

      this._resetIndex = resetIndexSaved;
      return (Struct[][])structs;
   }

   protected Struct[][][] array(Struct[][][] structs) {
      boolean resetIndexSaved = this._resetIndex;
      if (this._resetIndex) {
         this._index = 0;
         this._resetIndex = false;
      }

      for(int i = 0; i < structs.length; ++i) {
         this.array(structs[i]);
      }

      this._resetIndex = resetIndexSaved;
      return (Struct[][][])structs;
   }

   protected Member[] array(Member[] arrayMember) {
      boolean resetIndexSaved = this._resetIndex;
      if (this._resetIndex) {
         this._index = 0;
         this._resetIndex = false;
      }

      if (BOOL.isInstance(arrayMember)) {
         for(int i = 0; i < arrayMember.length; arrayMember[i++] = new Bool()) {
         }
      } else if (SIGNED_8.isInstance(arrayMember)) {
         for(int i = 0; i < arrayMember.length; arrayMember[i++] = new Signed8()) {
         }
      } else if (UNSIGNED_8.isInstance(arrayMember)) {
         for(int i = 0; i < arrayMember.length; arrayMember[i++] = new Unsigned8()) {
         }
      } else if (SIGNED_16.isInstance(arrayMember)) {
         for(int i = 0; i < arrayMember.length; arrayMember[i++] = new Signed16()) {
         }
      } else if (UNSIGNED_16.isInstance(arrayMember)) {
         for(int i = 0; i < arrayMember.length; arrayMember[i++] = new Unsigned16()) {
         }
      } else if (SIGNED_32.isInstance(arrayMember)) {
         for(int i = 0; i < arrayMember.length; arrayMember[i++] = new Signed32()) {
         }
      } else if (UNSIGNED_32.isInstance(arrayMember)) {
         for(int i = 0; i < arrayMember.length; arrayMember[i++] = new Unsigned32()) {
         }
      } else if (SIGNED_64.isInstance(arrayMember)) {
         for(int i = 0; i < arrayMember.length; arrayMember[i++] = new Signed64()) {
         }
      } else if (FLOAT_32.isInstance(arrayMember)) {
         for(int i = 0; i < arrayMember.length; arrayMember[i++] = new Float32()) {
         }
      } else {
         if (!FLOAT_64.isInstance(arrayMember)) {
            throw new UnsupportedOperationException("Cannot create member elements, the arrayMember should contain the member instances instead of null");
         }

         for(int i = 0; i < arrayMember.length; arrayMember[i++] = new Float64()) {
         }
      }

      this._resetIndex = resetIndexSaved;
      return (Member[])arrayMember;
   }

   protected Member[][] array(Member[][] arrayMember) {
      boolean resetIndexSaved = this._resetIndex;
      if (this._resetIndex) {
         this._index = 0;
         this._resetIndex = false;
      }

      for(int i = 0; i < arrayMember.length; ++i) {
         this.array(arrayMember[i]);
      }

      this._resetIndex = resetIndexSaved;
      return (Member[][])arrayMember;
   }

   protected Member[][][] array(Member[][][] arrayMember) {
      boolean resetIndexSaved = this._resetIndex;
      if (this._resetIndex) {
         this._index = 0;
         this._resetIndex = false;
      }

      for(int i = 0; i < arrayMember.length; ++i) {
         this.array(arrayMember[i]);
      }

      this._resetIndex = resetIndexSaved;
      return (Member[][][])arrayMember;
   }

   protected UTF8String[] array(UTF8String[] array, int stringLength) {
      boolean resetIndexSaved = this._resetIndex;
      if (this._resetIndex) {
         this._index = 0;
         this._resetIndex = false;
      }

      for(int i = 0; i < array.length; ++i) {
         array[i] = new UTF8String(stringLength);
      }

      this._resetIndex = resetIndexSaved;
      return array;
   }

   public long readBits(int bitOffset, int bitSize) {
      if (bitOffset + bitSize - 1 >> 3 >= this.size()) {
         throw new IllegalArgumentException("Attempt to read outside the Struct");
      } else {
         int offset = bitOffset >> 3;
         int bitStart = bitOffset - (offset << 3);
         bitStart = this.byteOrder() == ByteOrder.BIG_ENDIAN ? bitStart : 64 - bitSize - bitStart;
         int index = this.getByteBufferPosition() + offset;
         long value = this.readByteBufferLong(index);
         value <<= bitStart;
         value >>= 64 - bitSize;
         return value;
      }
   }

   private long readByteBufferLong(int index) {
      ByteBuffer byteBuffer = this.getByteBuffer();
      if (index + 8 < byteBuffer.limit()) {
         return byteBuffer.getLong(index);
      } else if (byteBuffer.order() == ByteOrder.LITTLE_ENDIAN) {
         int var23 = readByte(index, byteBuffer) & 255;
         ++index;
         var23 += (readByte(index, byteBuffer) & 255) << 8;
         ++index;
         long var25 = (long)(var23 + ((readByte(index, byteBuffer) & 255) << 16));
         ++index;
         var25 += ((long)readByte(index, byteBuffer) & 255L) << 24;
         ++index;
         var25 += ((long)readByte(index, byteBuffer) & 255L) << 32;
         ++index;
         var25 += ((long)readByte(index, byteBuffer) & 255L) << 40;
         ++index;
         var25 += ((long)readByte(index, byteBuffer) & 255L) << 48;
         ++index;
         return var25 + (((long)readByte(index, byteBuffer) & 255L) << 56);
      } else {
         long var10000 = (long)readByte(index, byteBuffer) << 56;
         ++index;
         var10000 += ((long)readByte(index, byteBuffer) & 255L) << 48;
         ++index;
         var10000 += ((long)readByte(index, byteBuffer) & 255L) << 40;
         ++index;
         var10000 += ((long)readByte(index, byteBuffer) & 255L) << 32;
         ++index;
         var10000 += ((long)readByte(index, byteBuffer) & 255L) << 24;
         ++index;
         var10000 += (long)((readByte(index, byteBuffer) & 255) << 16);
         ++index;
         var10000 += (long)((readByte(index, byteBuffer) & 255) << 8);
         ++index;
         return var10000 + ((long)readByte(index, byteBuffer) & 255L);
      }
   }

   private static byte readByte(int index, ByteBuffer byteBuffer) {
      return index < byteBuffer.limit() ? byteBuffer.get(index) : 0;
   }

   public void writeBits(long value, int bitOffset, int bitSize) {
      if (bitOffset + bitSize - 1 >> 3 >= this.size()) {
         throw new IllegalArgumentException("Attempt to write outside the Struct");
      } else {
         int offset = bitOffset >> 3;
         int bitStart = this.byteOrder() == ByteOrder.BIG_ENDIAN ? bitOffset - (offset << 3) : 64 - bitSize - (bitOffset - (offset << 3));
         long mask = -1L;
         mask <<= bitStart;
         mask >>>= 64 - bitSize;
         mask <<= 64 - bitSize - bitStart;
         int index = this.getByteBufferPosition() + offset;
         long oldValue = this.readByteBufferLong(index);
         long resetValue = oldValue & ~mask;
         long newValue = resetValue | value << 64 - bitSize - bitStart;
         this.writeByteBufferLong(index, newValue);
      }
   }

   private void writeByteBufferLong(int index, long value) {
      ByteBuffer byteBuffer = this.getByteBuffer();
      if (index + 8 < byteBuffer.limit()) {
         byteBuffer.putLong(index, value);
      } else {
         if (byteBuffer.order() == ByteOrder.LITTLE_ENDIAN) {
            writeByte(index, byteBuffer, (byte)((int)value));
            ++index;
            writeByte(index, byteBuffer, (byte)((int)(value >> 8)));
            ++index;
            writeByte(index, byteBuffer, (byte)((int)(value >> 16)));
            ++index;
            writeByte(index, byteBuffer, (byte)((int)(value >> 24)));
            ++index;
            writeByte(index, byteBuffer, (byte)((int)(value >> 32)));
            ++index;
            writeByte(index, byteBuffer, (byte)((int)(value >> 40)));
            ++index;
            writeByte(index, byteBuffer, (byte)((int)(value >> 48)));
            ++index;
            writeByte(index, byteBuffer, (byte)((int)(value >> 56)));
         } else {
            writeByte(index, byteBuffer, (byte)((int)(value >> 56)));
            ++index;
            writeByte(index, byteBuffer, (byte)((int)(value >> 48)));
            ++index;
            writeByte(index, byteBuffer, (byte)((int)(value >> 40)));
            ++index;
            writeByte(index, byteBuffer, (byte)((int)(value >> 32)));
            ++index;
            writeByte(index, byteBuffer, (byte)((int)(value >> 24)));
            ++index;
            writeByte(index, byteBuffer, (byte)((int)(value >> 16)));
            ++index;
            writeByte(index, byteBuffer, (byte)((int)(value >> 8)));
            ++index;
            writeByte(index, byteBuffer, (byte)((int)value));
         }

      }
   }

   private static void writeByte(int index, ByteBuffer byteBuffer, byte value) {
      if (index < byteBuffer.limit()) {
         byteBuffer.put(index, value);
      }

   }

   protected class Member {
      private final int _offset;
      private final int _bitIndex;
      private final int _bitLength;

      protected Member(int bitLength, int wordSize) {
         this._bitLength = bitLength;
         if (Struct.this._resetIndex) {
            Struct.this._index = 0;
         }

         if (wordSize == 0 || bitLength != 0 && wordSize == Struct.this._wordSize && Struct.this._bitsUsed + bitLength <= wordSize << 3) {
            this._offset = Struct.this._index - Struct.this._wordSize;
            this._bitIndex = Struct.this._bitsUsed;
            Struct.this._bitsUsed = bitLength;

            while(Struct.this._bitsUsed > Struct.this._wordSize << 3) {
               Struct.this._index++;
               Struct.this._wordSize++;
               Struct.this._length = MathLib.max(Struct.this._length, Struct.this._index);
            }

         } else {
            if (!Struct.this.isPacked()) {
               if (Struct.this._alignment < wordSize) {
                  Struct.this._alignment = wordSize;
               }

               int misaligned = Struct.this._index % wordSize;
               if (misaligned != 0) {
                  Struct.this._index = wordSize - misaligned;
               }
            }

            this._offset = Struct.this._index;
            this._bitIndex = 0;
            Struct.this._index = MathLib.max(wordSize, bitLength + 7 >> 3);
            Struct.this._wordSize = wordSize;
            Struct.this._bitsUsed = bitLength;
            Struct.this._length = MathLib.max(Struct.this._length, Struct.this._index);
         }
      }

      public final Struct struct() {
         return Struct.this;
      }

      public final int offset() {
         return this._offset;
      }

      public final int bitIndex() {
         return this._bitIndex;
      }

      public final int bitLength() {
         return this._bitLength;
      }

      final int get(int wordSize, int word) {
         int shift = Struct.this.byteOrder() == ByteOrder.BIG_ENDIAN ? (wordSize << 3) - this.bitIndex() - this.bitLength() : this.bitIndex();
         word >>= shift;
         int mask = -1 >>> 32 - this.bitLength();
         return word & mask;
      }

      final int set(int value, int wordSize, int word) {
         int shift = Struct.this.byteOrder() == ByteOrder.BIG_ENDIAN ? (wordSize << 3) - this.bitIndex() - this.bitLength() : this.bitIndex();
         int mask = -1 >>> 32 - this.bitLength();
         mask <<= shift;
         value <<= shift;
         return word & ~mask | value & mask;
      }

      final long get(int wordSize, long word) {
         int shift = Struct.this.byteOrder() == ByteOrder.BIG_ENDIAN ? (wordSize << 3) - this.bitIndex() - this.bitLength() : this.bitIndex();
         word >>= shift;
         long mask = -1L >>> 64 - this.bitLength();
         return word & mask;
      }

      final long set(long value, int wordSize, long word) {
         int shift = Struct.this.byteOrder() == ByteOrder.BIG_ENDIAN ? (wordSize << 3) - this.bitIndex() - this.bitLength() : this.bitIndex();
         long mask = -1L >>> 64 - this.bitLength();
         mask <<= shift;
         value <<= shift;
         return word & ~mask | value & mask;
      }
   }

   public class UTF8String extends Member {
      private final UTF8ByteBufferWriter _writer = new UTF8ByteBufferWriter();
      private final UTF8ByteBufferReader _reader = new UTF8ByteBufferReader();
      private final int _length;

      public UTF8String(int length) {
         super(length << 3, 1);
         this._length = length;
      }

      public void set(String string) {
         ByteBuffer buffer = Struct.this.getByteBuffer();
         synchronized(buffer) {
            try {
               int index = Struct.this.getByteBufferPosition() + this.offset();
               buffer.position(index);
               this._writer.setOutput(buffer);
               if (string.length() < this._length) {
                  this._writer.write(string);
                  this._writer.write((int)0);
               } else if (string.length() > this._length) {
                  this._writer.write(string.substring(0, this._length));
               } else {
                  this._writer.write(string);
               }
            } catch (IOException e) {
               throw new Error(e.getMessage());
            } finally {
               this._writer.reset();
            }

         }
      }

      public String get() {
         ByteBuffer buffer = Struct.this.getByteBuffer();
         synchronized(buffer) {
            TextBuilder tmp = TextBuilder.newInstance();

            try {
               int index = Struct.this.getByteBufferPosition() + this.offset();
               buffer.position(index);
               this._reader.setInput(buffer);

               for(int i = 0; i < this._length; ++i) {
                  char c = (char)this._reader.read();
                  if (c == 0) {
                     String var7 = tmp.toString();
                     return var7;
                  }

                  tmp.append(c);
               }

               String var16 = tmp.toString();
               return var16;
            } catch (IOException e) {
               throw new Error(e.getMessage());
            } finally {
               this._reader.reset();
               TextBuilder.recycle(tmp);
            }
         }
      }

      public String toString() {
         return this.get();
      }
   }

   public class Bool extends Member {
      public Bool() {
         super(8, 1);
      }

      public Bool(int nbrOfBits) {
         super(nbrOfBits, 1);
      }

      public boolean get() {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         int word = Struct.this.getByteBuffer().get(index);
         word = this.bitLength() == 8 ? word : this.get(1, word);
         return word != 0;
      }

      public void set(boolean value) {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         if (this.bitLength() == 8) {
            Struct.this.getByteBuffer().put(index, (byte)(value ? -1 : 0));
         } else {
            Struct.this.getByteBuffer().put(index, (byte)this.set(value ? -1 : 0, 1, Struct.this.getByteBuffer().get(index)));
         }

      }

      public String toString() {
         return String.valueOf(this.get());
      }
   }

   public class Signed8 extends Member {
      public Signed8() {
         super(8, 1);
      }

      public Signed8(int nbrOfBits) {
         super(nbrOfBits, 1);
      }

      public byte get() {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         int word = Struct.this.getByteBuffer().get(index);
         return (byte)(this.bitLength() == 8 ? word : this.get(1, word));
      }

      public void set(byte value) {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         if (this.bitLength() == 8) {
            Struct.this.getByteBuffer().put(index, value);
         } else {
            Struct.this.getByteBuffer().put(index, (byte)this.set(value, 1, Struct.this.getByteBuffer().get(index)));
         }

      }

      public String toString() {
         return String.valueOf(this.get());
      }
   }

   public class Unsigned8 extends Member {
      public Unsigned8() {
         super(8, 1);
      }

      public Unsigned8(int nbrOfBits) {
         super(nbrOfBits, 1);
      }

      public short get() {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         int word = Struct.this.getByteBuffer().get(index);
         return (short)(255 & (this.bitLength() == 8 ? word : this.get(1, word)));
      }

      public void set(short value) {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         if (this.bitLength() == 8) {
            Struct.this.getByteBuffer().put(index, (byte)value);
         } else {
            Struct.this.getByteBuffer().put(index, (byte)this.set(value, 1, Struct.this.getByteBuffer().get(index)));
         }

      }

      public String toString() {
         return String.valueOf(this.get());
      }
   }

   public class Signed16 extends Member {
      public Signed16() {
         super(16, 2);
      }

      public Signed16(int nbrOfBits) {
         super(nbrOfBits, 2);
      }

      public short get() {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         int word = Struct.this.getByteBuffer().getShort(index);
         return (short)(this.bitLength() == 16 ? word : this.get(2, word));
      }

      public void set(short value) {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         if (this.bitLength() == 16) {
            Struct.this.getByteBuffer().putShort(index, value);
         } else {
            Struct.this.getByteBuffer().putShort(index, (short)this.set(value, 2, Struct.this.getByteBuffer().getShort(index)));
         }

      }

      public String toString() {
         return String.valueOf(this.get());
      }
   }

   public class Unsigned16 extends Member {
      public Unsigned16() {
         super(16, 2);
      }

      public Unsigned16(int nbrOfBits) {
         super(nbrOfBits, 2);
      }

      public int get() {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         int word = Struct.this.getByteBuffer().getShort(index);
         return '\uffff' & (this.bitLength() == 16 ? word : this.get(2, word));
      }

      public void set(int value) {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         if (this.bitLength() == 16) {
            Struct.this.getByteBuffer().putShort(index, (short)value);
         } else {
            Struct.this.getByteBuffer().putShort(index, (short)this.set(value, 2, Struct.this.getByteBuffer().getShort(index)));
         }

      }

      public String toString() {
         return String.valueOf(this.get());
      }
   }

   public class Signed32 extends Member {
      public Signed32() {
         super(32, 4);
      }

      public Signed32(int nbrOfBits) {
         super(nbrOfBits, 4);
      }

      public int get() {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         int word = Struct.this.getByteBuffer().getInt(index);
         return this.bitLength() == 32 ? word : this.get(4, word);
      }

      public void set(int value) {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         if (this.bitLength() == 32) {
            Struct.this.getByteBuffer().putInt(index, value);
         } else {
            Struct.this.getByteBuffer().putInt(index, this.set(value, 4, Struct.this.getByteBuffer().getInt(index)));
         }

      }

      public String toString() {
         return String.valueOf(this.get());
      }
   }

   public class Unsigned32 extends Member {
      public Unsigned32() {
         super(32, 4);
      }

      public Unsigned32(int nbrOfBits) {
         super(nbrOfBits, 4);
      }

      public long get() {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         int word = Struct.this.getByteBuffer().getInt(index);
         return 4294967295L & (long)(this.bitLength() == 32 ? word : this.get(4, word));
      }

      public void set(long value) {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         if (this.bitLength() == 32) {
            Struct.this.getByteBuffer().putInt(index, (int)value);
         } else {
            Struct.this.getByteBuffer().putInt(index, this.set((int)value, 4, Struct.this.getByteBuffer().getInt(index)));
         }

      }

      public String toString() {
         return String.valueOf(this.get());
      }
   }

   public class Signed64 extends Member {
      public Signed64() {
         super(64, 8);
      }

      public Signed64(int nbrOfBits) {
         super(nbrOfBits, 8);
      }

      public long get() {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         long word = Struct.this.getByteBuffer().getLong(index);
         return this.bitLength() == 64 ? word : this.get(8, word);
      }

      public void set(long value) {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         if (this.bitLength() == 64) {
            Struct.this.getByteBuffer().putLong(index, value);
         } else {
            Struct.this.getByteBuffer().putLong(index, this.set(value, 8, Struct.this.getByteBuffer().getLong(index)));
         }

      }

      public String toString() {
         return String.valueOf(this.get());
      }
   }

   public class BitField extends Member {
      public BitField(int nbrOfBits) {
         super(nbrOfBits, 0);
      }

      public long longValue() {
         long signedValue = Struct.this.readBits(this.bitIndex() + (this.offset() << 3), this.bitLength());
         return ~(-1L << this.bitLength()) & signedValue;
      }

      public int intValue() {
         return (int)this.longValue();
      }

      public short shortValue() {
         return (short)((int)this.longValue());
      }

      public byte byteValue() {
         return (byte)((int)this.longValue());
      }

      public void set(long value) {
         Struct.this.writeBits(value, this.bitIndex() + (this.offset() << 3), this.bitLength());
      }

      public String toString() {
         return String.valueOf(this.longValue());
      }
   }

   public class Float32 extends Member {
      public Float32() {
         super(32, 4);
      }

      public float get() {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         return Struct.this.getByteBuffer().getFloat(index);
      }

      public void set(float value) {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         Struct.this.getByteBuffer().putFloat(index, value);
      }

      public String toString() {
         return String.valueOf(this.get());
      }
   }

   public class Float64 extends Member {
      public Float64() {
         super(64, 8);
      }

      public double get() {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         return Struct.this.getByteBuffer().getDouble(index);
      }

      public void set(double value) {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         Struct.this.getByteBuffer().putDouble(index, value);
      }

      public String toString() {
         return String.valueOf(this.get());
      }
   }

   public class Reference32 extends Member {
      private Struct _struct;

      public Reference32() {
         super(32, 4);
      }

      public void set(Struct struct) {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         if (struct != null) {
            Struct.this.getByteBuffer().putInt(index, (int)struct.address());
         } else {
            Struct.this.getByteBuffer().putInt(index, 0);
         }

         this._struct = struct;
      }

      public Struct get() {
         return this._struct;
      }

      public int value() {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         return Struct.this.getByteBuffer().getInt(index);
      }

      public boolean isUpToDate() {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         if (this._struct != null) {
            return Struct.this.getByteBuffer().getInt(index) == (int)this._struct.address();
         } else {
            return Struct.this.getByteBuffer().getInt(index) == 0;
         }
      }
   }

   public class Reference64 extends Member {
      private Struct _struct;

      public Reference64() {
         super(64, 8);
      }

      public void set(Struct struct) {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         if (struct != null) {
            Struct.this.getByteBuffer().putLong(index, struct.address());
         } else if (struct == null) {
            Struct.this.getByteBuffer().putLong(index, 0L);
         }

         this._struct = struct;
      }

      public Struct get() {
         return this._struct;
      }

      public long value() {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         return Struct.this.getByteBuffer().getLong(index);
      }

      public boolean isUpToDate() {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         if (this._struct != null) {
            return Struct.this.getByteBuffer().getLong(index) == this._struct.address();
         } else {
            return Struct.this.getByteBuffer().getLong(index) == 0L;
         }
      }
   }

   public class Enum8 extends Member {
      private final Enum[] _values;

      public Enum8(Enum[] values) {
         super(8, 1);
         this._values = values;
      }

      public Enum8(Enum[] values, int nbrOfBits) {
         super(nbrOfBits, 1);
         this._values = values;
      }

      public Enum get() {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         int word = Struct.this.getByteBuffer().get(index);
         return this._values[255 & this.get(1, word)];
      }

      public void set(Enum e) {
         int value = e.ordinal();
         if (this._values[value] != e) {
            throw new IllegalArgumentException("enum: " + e + ", ordinal value does not reflect enum values position");
         } else {
            int index = Struct.this.getByteBufferPosition() + this.offset();
            int word = Struct.this.getByteBuffer().get(index);
            Struct.this.getByteBuffer().put(index, (byte)this.set(value, 1, word));
         }
      }

      public String toString() {
         return String.valueOf(this.get());
      }
   }

   public class Enum16 extends Member {
      private final Enum[] _values;

      public Enum16(Enum[] values) {
         super(16, 2);
         this._values = values;
      }

      public Enum16(Enum[] values, int nbrOfBits) {
         super(nbrOfBits, 2);
         this._values = values;
      }

      public Enum get() {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         int word = Struct.this.getByteBuffer().getShort(index);
         return this._values['\uffff' & this.get(2, word)];
      }

      public void set(Enum e) {
         int value = e.ordinal();
         if (this._values[value] != e) {
            throw new IllegalArgumentException("enum: " + e + ", ordinal value does not reflect enum values position");
         } else {
            int index = Struct.this.getByteBufferPosition() + this.offset();
            int word = Struct.this.getByteBuffer().getShort(index);
            Struct.this.getByteBuffer().putShort(index, (short)this.set(value, 2, word));
         }
      }

      public String toString() {
         return String.valueOf(this.get());
      }
   }

   public class Enum32 extends Member {
      private final Enum[] _values;

      public Enum32(Enum[] values) {
         super(32, 4);
         this._values = values;
      }

      public Enum32(Enum[] values, int nbrOfBits) {
         super(nbrOfBits, 4);
         this._values = values;
      }

      public Enum get() {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         int word = Struct.this.getByteBuffer().getInt(index);
         return this._values[this.get(4, word)];
      }

      public void set(Enum e) {
         int value = e.ordinal();
         if (this._values[value] != e) {
            throw new IllegalArgumentException("enum: " + e + ", ordinal value does not reflect enum values position");
         } else {
            int index = Struct.this.getByteBufferPosition() + this.offset();
            int word = Struct.this.getByteBuffer().getInt(index);
            Struct.this.getByteBuffer().putInt(index, this.set(value, 4, word));
         }
      }

      public String toString() {
         return String.valueOf(this.get());
      }
   }

   public class Enum64 extends Member {
      private final Enum[] _values;

      public Enum64(Enum[] values) {
         super(64, 8);
         this._values = values;
      }

      public Enum64(Enum[] values, int nbrOfBits) {
         super(nbrOfBits, 8);
         this._values = values;
      }

      public Enum get() {
         int index = Struct.this.getByteBufferPosition() + this.offset();
         long word = Struct.this.getByteBuffer().getLong(index);
         return this._values[(int)this.get(8, word)];
      }

      public void set(Enum e) {
         long value = (long)e.ordinal();
         if (this._values[(int)value] != e) {
            throw new IllegalArgumentException("enum: " + e + ", ordinal value does not reflect enum values position");
         } else {
            int index = Struct.this.getByteBufferPosition() + this.offset();
            long word = Struct.this.getByteBuffer().getLong(index);
            Struct.this.getByteBuffer().putLong(index, this.set(value, 8, word));
         }
      }

      public String toString() {
         return String.valueOf(this.get());
      }
   }
}
