package com.google.flatbuffers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FlatBufferBuilder {
   ByteBuffer bb;
   int space;
   int minalign;
   int[] vtable;
   int vtable_in_use;
   boolean nested;
   boolean finished;
   int object_start;
   int[] vtables;
   int num_vtables;
   int vector_num_elems;
   boolean force_defaults;
   ByteBufferFactory bb_factory;
   final Utf8 utf8;
   Map string_pool;
   private static final int MAX_BUFFER_SIZE = 2147483639;
   private static final int DEFAULT_BUFFER_SIZE = 1024;

   public FlatBufferBuilder(int initial_size, ByteBufferFactory bb_factory) {
      this(initial_size, bb_factory, (ByteBuffer)null, Utf8.getDefault());
   }

   public FlatBufferBuilder(int initial_size, ByteBufferFactory bb_factory, ByteBuffer existing_bb, Utf8 utf8) {
      this.minalign = 1;
      this.vtable = null;
      this.vtable_in_use = 0;
      this.nested = false;
      this.finished = false;
      this.vtables = new int[16];
      this.num_vtables = 0;
      this.vector_num_elems = 0;
      this.force_defaults = false;
      if (initial_size <= 0) {
         initial_size = 1024;
      }

      this.bb_factory = bb_factory;
      if (existing_bb != null) {
         this.bb = existing_bb;
         this.bb.clear();
         this.bb.order(ByteOrder.LITTLE_ENDIAN);
      } else {
         this.bb = bb_factory.newByteBuffer(initial_size);
      }

      this.utf8 = utf8;
      this.space = this.bb.capacity();
   }

   public FlatBufferBuilder(int initial_size) {
      this(initial_size, FlatBufferBuilder.HeapByteBufferFactory.INSTANCE, (ByteBuffer)null, Utf8.getDefault());
   }

   public FlatBufferBuilder() {
      this(1024);
   }

   public FlatBufferBuilder(ByteBuffer existing_bb, ByteBufferFactory bb_factory) {
      this(existing_bb.capacity(), bb_factory, existing_bb, Utf8.getDefault());
   }

   public FlatBufferBuilder(ByteBuffer existing_bb) {
      this(existing_bb, new HeapByteBufferFactory());
   }

   public FlatBufferBuilder init(ByteBuffer existing_bb, ByteBufferFactory bb_factory) {
      this.bb_factory = bb_factory;
      this.bb = existing_bb;
      this.bb.clear();
      this.bb.order(ByteOrder.LITTLE_ENDIAN);
      this.minalign = 1;
      this.space = this.bb.capacity();
      this.vtable_in_use = 0;
      this.nested = false;
      this.finished = false;
      this.object_start = 0;
      this.num_vtables = 0;
      this.vector_num_elems = 0;
      if (this.string_pool != null) {
         this.string_pool.clear();
      }

      return this;
   }

   public static boolean isFieldPresent(Table table, int offset) {
      return table.__offset(offset) != 0;
   }

   public void clear() {
      this.space = this.bb.capacity();
      this.bb.clear();

      for(this.minalign = 1; this.vtable_in_use > 0; this.vtable[--this.vtable_in_use] = 0) {
      }

      this.vtable_in_use = 0;
      this.nested = false;
      this.finished = false;
      this.object_start = 0;
      this.num_vtables = 0;
      this.vector_num_elems = 0;
      if (this.string_pool != null) {
         this.string_pool.clear();
      }

   }

   static ByteBuffer growByteBuffer(ByteBuffer bb, ByteBufferFactory bb_factory) {
      int old_buf_size = bb.capacity();
      int new_buf_size;
      if (old_buf_size == 0) {
         new_buf_size = 1024;
      } else {
         if (old_buf_size == 2147483639) {
            throw new AssertionError("FlatBuffers: cannot grow buffer beyond 2 gigabytes.");
         }

         new_buf_size = (old_buf_size & -1073741824) != 0 ? 2147483639 : old_buf_size << 1;
      }

      bb.position(0);
      ByteBuffer nbb = bb_factory.newByteBuffer(new_buf_size);
      new_buf_size = nbb.clear().capacity();
      nbb.position(new_buf_size - old_buf_size);
      nbb.put(bb);
      return nbb;
   }

   public int offset() {
      return this.bb.capacity() - this.space;
   }

   public void pad(int byte_size) {
      for(int i = 0; i < byte_size; ++i) {
         this.bb.put(--this.space, (byte)0);
      }

   }

   public void prep(int size, int additional_bytes) {
      if (size > this.minalign) {
         this.minalign = size;
      }

      int align_size;
      int old_buf_size;
      for(align_size = ~(this.bb.capacity() - this.space + additional_bytes) + 1 & size - 1; this.space < align_size + size + additional_bytes; this.space += this.bb.capacity() - old_buf_size) {
         old_buf_size = this.bb.capacity();
         ByteBuffer old = this.bb;
         this.bb = growByteBuffer(old, this.bb_factory);
         if (old != this.bb) {
            this.bb_factory.releaseByteBuffer(old);
         }
      }

      this.pad(align_size);
   }

   public void putBoolean(boolean x) {
      this.bb.put(--this.space, (byte)(x ? 1 : 0));
   }

   public void putByte(byte x) {
      this.bb.put(--this.space, x);
   }

   public void putShort(short x) {
      this.bb.putShort(this.space -= 2, x);
   }

   public void putInt(int x) {
      this.bb.putInt(this.space -= 4, x);
   }

   public void putLong(long x) {
      this.bb.putLong(this.space -= 8, x);
   }

   public void putFloat(float x) {
      this.bb.putFloat(this.space -= 4, x);
   }

   public void putDouble(double x) {
      this.bb.putDouble(this.space -= 8, x);
   }

   public void addBoolean(boolean x) {
      this.prep(1, 0);
      this.putBoolean(x);
   }

   public void addByte(byte x) {
      this.prep(1, 0);
      this.putByte(x);
   }

   public void addShort(short x) {
      this.prep(2, 0);
      this.putShort(x);
   }

   public void addInt(int x) {
      this.prep(4, 0);
      this.putInt(x);
   }

   public void addLong(long x) {
      this.prep(8, 0);
      this.putLong(x);
   }

   public void addFloat(float x) {
      this.prep(4, 0);
      this.putFloat(x);
   }

   public void addDouble(double x) {
      this.prep(8, 0);
      this.putDouble(x);
   }

   public void addOffset(int off) {
      this.prep(4, 0);

      assert off <= this.offset();

      off = this.offset() - off + 4;
      this.putInt(off);
   }

   public void startVector(int elem_size, int num_elems, int alignment) {
      this.notNested();
      this.vector_num_elems = num_elems;
      this.prep(4, elem_size * num_elems);
      this.prep(alignment, elem_size * num_elems);
      this.nested = true;
   }

   public int endVector() {
      if (!this.nested) {
         throw new AssertionError("FlatBuffers: endVector called without startVector");
      } else {
         this.nested = false;
         this.putInt(this.vector_num_elems);
         return this.offset();
      }
   }

   public ByteBuffer createUnintializedVector(int elem_size, int num_elems, int alignment) {
      int length = elem_size * num_elems;
      this.startVector(elem_size, num_elems, alignment);
      this.bb.position(this.space -= length);
      ByteBuffer copy = this.bb.slice().order(ByteOrder.LITTLE_ENDIAN);
      copy.limit(length);
      return copy;
   }

   public int createVectorOfTables(int[] offsets) {
      this.notNested();
      this.startVector(4, offsets.length, 4);

      for(int i = offsets.length - 1; i >= 0; --i) {
         this.addOffset(offsets[i]);
      }

      return this.endVector();
   }

   public int createSortedVectorOfTables(Table obj, int[] offsets) {
      obj.sortTables(offsets, this.bb);
      return this.createVectorOfTables(offsets);
   }

   public int createSharedString(String s) {
      if (this.string_pool == null) {
         this.string_pool = new HashMap();
         int offset = this.createString((CharSequence)s);
         this.string_pool.put(s, offset);
         return offset;
      } else {
         Integer offset = (Integer)this.string_pool.get(s);
         if (offset == null) {
            offset = this.createString((CharSequence)s);
            this.string_pool.put(s, offset);
         }

         return offset;
      }
   }

   public int createString(CharSequence s) {
      int length = this.utf8.encodedLength(s);
      this.addByte((byte)0);
      this.startVector(1, length, 1);
      this.bb.position(this.space -= length);
      this.utf8.encodeUtf8(s, this.bb);
      return this.endVector();
   }

   public int createString(ByteBuffer s) {
      int length = s.remaining();
      this.addByte((byte)0);
      this.startVector(1, length, 1);
      this.bb.position(this.space -= length);
      this.bb.put(s);
      return this.endVector();
   }

   public int createByteVector(byte[] arr) {
      int length = arr.length;
      this.startVector(1, length, 1);
      this.bb.position(this.space -= length);
      this.bb.put(arr);
      return this.endVector();
   }

   public int createByteVector(byte[] arr, int offset, int length) {
      this.startVector(1, length, 1);
      this.bb.position(this.space -= length);
      this.bb.put(arr, offset, length);
      return this.endVector();
   }

   public int createByteVector(ByteBuffer byteBuffer) {
      int length = byteBuffer.remaining();
      this.startVector(1, length, 1);
      this.bb.position(this.space -= length);
      this.bb.put(byteBuffer);
      return this.endVector();
   }

   public void finished() {
      if (!this.finished) {
         throw new AssertionError("FlatBuffers: you can only access the serialized buffer after it has been finished by FlatBufferBuilder.finish().");
      }
   }

   public void notNested() {
      if (this.nested) {
         throw new AssertionError("FlatBuffers: object serialization must not be nested.");
      }
   }

   public void Nested(int obj) {
      if (obj != this.offset()) {
         throw new AssertionError("FlatBuffers: struct must be serialized inline.");
      }
   }

   public void startTable(int numfields) {
      this.notNested();
      if (this.vtable == null || this.vtable.length < numfields) {
         this.vtable = new int[numfields];
      }

      this.vtable_in_use = numfields;
      Arrays.fill(this.vtable, 0, this.vtable_in_use, 0);
      this.nested = true;
      this.object_start = this.offset();
   }

   public void addBoolean(int o, boolean x, boolean d) {
      if (this.force_defaults || x != d) {
         this.addBoolean(x);
         this.slot(o);
      }

   }

   public void addByte(int o, byte x, int d) {
      if (this.force_defaults || x != d) {
         this.addByte(x);
         this.slot(o);
      }

   }

   public void addShort(int o, short x, int d) {
      if (this.force_defaults || x != d) {
         this.addShort(x);
         this.slot(o);
      }

   }

   public void addInt(int o, int x, int d) {
      if (this.force_defaults || x != d) {
         this.addInt(x);
         this.slot(o);
      }

   }

   public void addLong(int o, long x, long d) {
      if (this.force_defaults || x != d) {
         this.addLong(x);
         this.slot(o);
      }

   }

   public void addFloat(int o, float x, double d) {
      if (this.force_defaults || (double)x != d) {
         this.addFloat(x);
         this.slot(o);
      }

   }

   public void addDouble(int o, double x, double d) {
      if (this.force_defaults || x != d) {
         this.addDouble(x);
         this.slot(o);
      }

   }

   public void addOffset(int o, int x, int d) {
      if (this.force_defaults || x != d) {
         this.addOffset(x);
         this.slot(o);
      }

   }

   public void addStruct(int voffset, int x, int d) {
      if (x != d) {
         this.Nested(x);
         this.slot(voffset);
      }

   }

   public void slot(int voffset) {
      this.vtable[voffset] = this.offset();
   }

   public int endTable() {
      if (this.vtable != null && this.nested) {
         this.addInt(0);
         int vtableloc = this.offset();

         int i;
         for(i = this.vtable_in_use - 1; i >= 0 && this.vtable[i] == 0; --i) {
         }

         int trimmed_size;
         for(trimmed_size = i + 1; i >= 0; --i) {
            short off = (short)(this.vtable[i] != 0 ? vtableloc - this.vtable[i] : 0);
            this.addShort(off);
         }

         int standard_fields = 2;
         this.addShort((short)(vtableloc - this.object_start));
         this.addShort((short)((trimmed_size + 2) * 2));
         int existing_vtable = 0;

         label51:
         for(int var10 = 0; var10 < this.num_vtables; ++var10) {
            int vt1 = this.bb.capacity() - this.vtables[var10];
            int vt2 = this.space;
            short len = this.bb.getShort(vt1);
            if (len == this.bb.getShort(vt2)) {
               for(int j = 2; j < len; j += 2) {
                  if (this.bb.getShort(vt1 + j) != this.bb.getShort(vt2 + j)) {
                     continue label51;
                  }
               }

               existing_vtable = this.vtables[var10];
               break;
            }
         }

         if (existing_vtable != 0) {
            this.space = this.bb.capacity() - vtableloc;
            this.bb.putInt(this.space, existing_vtable - vtableloc);
         } else {
            if (this.num_vtables == this.vtables.length) {
               this.vtables = Arrays.copyOf(this.vtables, this.num_vtables * 2);
            }

            this.vtables[this.num_vtables++] = this.offset();
            this.bb.putInt(this.bb.capacity() - vtableloc, this.offset() - vtableloc);
         }

         this.nested = false;
         return vtableloc;
      } else {
         throw new AssertionError("FlatBuffers: endTable called without startTable");
      }
   }

   public void required(int table, int field) {
      int table_start = this.bb.capacity() - table;
      int vtable_start = table_start - this.bb.getInt(table_start);
      boolean ok = this.bb.getShort(vtable_start + field) != 0;
      if (!ok) {
         throw new AssertionError("FlatBuffers: field " + field + " must be set");
      }
   }

   protected void finish(int root_table, boolean size_prefix) {
      this.prep(this.minalign, 4 + (size_prefix ? 4 : 0));
      this.addOffset(root_table);
      if (size_prefix) {
         this.addInt(this.bb.capacity() - this.space);
      }

      this.bb.position(this.space);
      this.finished = true;
   }

   public void finish(int root_table) {
      this.finish(root_table, false);
   }

   public void finishSizePrefixed(int root_table) {
      this.finish(root_table, true);
   }

   protected void finish(int root_table, String file_identifier, boolean size_prefix) {
      this.prep(this.minalign, 8 + (size_prefix ? 4 : 0));
      if (file_identifier.length() != 4) {
         throw new AssertionError("FlatBuffers: file identifier must be length 4");
      } else {
         for(int i = 3; i >= 0; --i) {
            this.addByte((byte)file_identifier.charAt(i));
         }

         this.finish(root_table, size_prefix);
      }
   }

   public void finish(int root_table, String file_identifier) {
      this.finish(root_table, file_identifier, false);
   }

   public void finishSizePrefixed(int root_table, String file_identifier) {
      this.finish(root_table, file_identifier, true);
   }

   public FlatBufferBuilder forceDefaults(boolean forceDefaults) {
      this.force_defaults = forceDefaults;
      return this;
   }

   public ByteBuffer dataBuffer() {
      this.finished();
      return this.bb;
   }

   /** @deprecated */
   @Deprecated
   private int dataStart() {
      this.finished();
      return this.space;
   }

   public byte[] sizedByteArray(int start, int length) {
      this.finished();
      byte[] array = new byte[length];
      this.bb.position(start);
      this.bb.get(array);
      return array;
   }

   public byte[] sizedByteArray() {
      return this.sizedByteArray(this.space, this.bb.capacity() - this.space);
   }

   public InputStream sizedInputStream() {
      this.finished();
      ByteBuffer duplicate = this.bb.duplicate();
      duplicate.position(this.space);
      duplicate.limit(this.bb.capacity());
      return new ByteBufferBackedInputStream(duplicate);
   }

   public abstract static class ByteBufferFactory {
      public abstract ByteBuffer newByteBuffer(int var1);

      public void releaseByteBuffer(ByteBuffer bb) {
      }
   }

   public static final class HeapByteBufferFactory extends ByteBufferFactory {
      public static final HeapByteBufferFactory INSTANCE = new HeapByteBufferFactory();

      public ByteBuffer newByteBuffer(int capacity) {
         return ByteBuffer.allocate(capacity).order(ByteOrder.LITTLE_ENDIAN);
      }
   }

   static class ByteBufferBackedInputStream extends InputStream {
      ByteBuffer buf;

      public ByteBufferBackedInputStream(ByteBuffer buf) {
         this.buf = buf;
      }

      public int read() throws IOException {
         try {
            return this.buf.get() & 255;
         } catch (BufferUnderflowException var2) {
            return -1;
         }
      }
   }
}
