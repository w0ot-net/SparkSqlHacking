package com.google.flatbuffers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Comparator;

public class Table {
   protected int bb_pos;
   protected ByteBuffer bb;
   private int vtable_start;
   private int vtable_size;
   Utf8 utf8 = Utf8.getDefault();

   public ByteBuffer getByteBuffer() {
      return this.bb;
   }

   protected int __offset(int vtable_offset) {
      return vtable_offset < this.vtable_size ? this.bb.getShort(this.vtable_start + vtable_offset) : 0;
   }

   protected static int __offset(int vtable_offset, int offset, ByteBuffer bb) {
      int vtable = bb.capacity() - offset;
      return bb.getShort(vtable + vtable_offset - bb.getInt(vtable)) + vtable;
   }

   protected int __indirect(int offset) {
      return offset + this.bb.getInt(offset);
   }

   protected static int __indirect(int offset, ByteBuffer bb) {
      return offset + bb.getInt(offset);
   }

   protected String __string(int offset) {
      return __string(offset, this.bb, this.utf8);
   }

   protected static String __string(int offset, ByteBuffer bb, Utf8 utf8) {
      offset += bb.getInt(offset);
      int length = bb.getInt(offset);
      return utf8.decodeUtf8(bb, offset + 4, length);
   }

   protected int __vector_len(int offset) {
      offset += this.bb_pos;
      offset += this.bb.getInt(offset);
      return this.bb.getInt(offset);
   }

   protected int __vector(int offset) {
      offset += this.bb_pos;
      return offset + this.bb.getInt(offset) + 4;
   }

   protected ByteBuffer __vector_as_bytebuffer(int vector_offset, int elem_size) {
      int o = this.__offset(vector_offset);
      if (o == 0) {
         return null;
      } else {
         ByteBuffer bb = this.bb.duplicate().order(ByteOrder.LITTLE_ENDIAN);
         int vectorstart = this.__vector(o);
         bb.position(vectorstart);
         bb.limit(vectorstart + this.__vector_len(o) * elem_size);
         return bb;
      }
   }

   protected ByteBuffer __vector_in_bytebuffer(ByteBuffer bb, int vector_offset, int elem_size) {
      int o = this.__offset(vector_offset);
      if (o == 0) {
         return null;
      } else {
         int vectorstart = this.__vector(o);
         bb.rewind();
         bb.limit(vectorstart + this.__vector_len(o) * elem_size);
         bb.position(vectorstart);
         return bb;
      }
   }

   protected Table __union(Table t, int offset) {
      return __union(t, offset, this.bb);
   }

   protected static Table __union(Table t, int offset, ByteBuffer bb) {
      t.__reset(__indirect(offset, bb), bb);
      return t;
   }

   protected static boolean __has_identifier(ByteBuffer bb, String ident) {
      if (ident.length() != 4) {
         throw new AssertionError("FlatBuffers: file identifier must be length 4");
      } else {
         for(int i = 0; i < 4; ++i) {
            if (ident.charAt(i) != (char)bb.get(bb.position() + 4 + i)) {
               return false;
            }
         }

         return true;
      }
   }

   protected void sortTables(int[] offsets, final ByteBuffer bb) {
      Integer[] off = new Integer[offsets.length];

      for(int i = 0; i < offsets.length; ++i) {
         off[i] = offsets[i];
      }

      Arrays.sort(off, new Comparator() {
         public int compare(Integer o1, Integer o2) {
            return Table.this.keysCompare(o1, o2, bb);
         }
      });

      for(int i = 0; i < offsets.length; ++i) {
         offsets[i] = off[i];
      }

   }

   protected int keysCompare(Integer o1, Integer o2, ByteBuffer bb) {
      return 0;
   }

   protected static int compareStrings(int offset_1, int offset_2, ByteBuffer bb) {
      offset_1 += bb.getInt(offset_1);
      offset_2 += bb.getInt(offset_2);
      int len_1 = bb.getInt(offset_1);
      int len_2 = bb.getInt(offset_2);
      int startPos_1 = offset_1 + 4;
      int startPos_2 = offset_2 + 4;
      int len = Math.min(len_1, len_2);

      for(int i = 0; i < len; ++i) {
         if (bb.get(i + startPos_1) != bb.get(i + startPos_2)) {
            return bb.get(i + startPos_1) - bb.get(i + startPos_2);
         }
      }

      return len_1 - len_2;
   }

   protected static int compareStrings(int offset_1, byte[] key, ByteBuffer bb) {
      offset_1 += bb.getInt(offset_1);
      int len_1 = bb.getInt(offset_1);
      int len_2 = key.length;
      int startPos_1 = offset_1 + 4;
      int len = Math.min(len_1, len_2);

      for(int i = 0; i < len; ++i) {
         if (bb.get(i + startPos_1) != key[i]) {
            return bb.get(i + startPos_1) - key[i];
         }
      }

      return len_1 - len_2;
   }

   protected void __reset(int _i, ByteBuffer _bb) {
      this.bb = _bb;
      if (this.bb != null) {
         this.bb_pos = _i;
         this.vtable_start = this.bb_pos - this.bb.getInt(this.bb_pos);
         this.vtable_size = this.bb.getShort(this.vtable_start);
      } else {
         this.bb_pos = 0;
         this.vtable_start = 0;
         this.vtable_size = 0;
      }

   }

   public void __reset() {
      this.__reset(0, (ByteBuffer)null);
   }
}
