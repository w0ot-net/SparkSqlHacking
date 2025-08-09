package com.google.flatbuffers;

import java.nio.ByteBuffer;

public class FlexBuffers {
   public static final int FBT_NULL = 0;
   public static final int FBT_INT = 1;
   public static final int FBT_UINT = 2;
   public static final int FBT_FLOAT = 3;
   public static final int FBT_KEY = 4;
   public static final int FBT_STRING = 5;
   public static final int FBT_INDIRECT_INT = 6;
   public static final int FBT_INDIRECT_UINT = 7;
   public static final int FBT_INDIRECT_FLOAT = 8;
   public static final int FBT_MAP = 9;
   public static final int FBT_VECTOR = 10;
   public static final int FBT_VECTOR_INT = 11;
   public static final int FBT_VECTOR_UINT = 12;
   public static final int FBT_VECTOR_FLOAT = 13;
   public static final int FBT_VECTOR_KEY = 14;
   public static final int FBT_VECTOR_STRING_DEPRECATED = 15;
   public static final int FBT_VECTOR_INT2 = 16;
   public static final int FBT_VECTOR_UINT2 = 17;
   public static final int FBT_VECTOR_FLOAT2 = 18;
   public static final int FBT_VECTOR_INT3 = 19;
   public static final int FBT_VECTOR_UINT3 = 20;
   public static final int FBT_VECTOR_FLOAT3 = 21;
   public static final int FBT_VECTOR_INT4 = 22;
   public static final int FBT_VECTOR_UINT4 = 23;
   public static final int FBT_VECTOR_FLOAT4 = 24;
   public static final int FBT_BLOB = 25;
   public static final int FBT_BOOL = 26;
   public static final int FBT_VECTOR_BOOL = 36;
   private static final ReadBuf EMPTY_BB = new ArrayReadWriteBuf(new byte[]{0}, 1);

   static boolean isTypedVector(int type) {
      return type >= 11 && type <= 15 || type == 36;
   }

   static boolean isTypeInline(int type) {
      return type <= 3 || type == 26;
   }

   static int toTypedVectorElementType(int original_type) {
      return original_type - 11 + 1;
   }

   static int toTypedVector(int type, int fixedLength) {
      assert isTypedVectorElementType(type);

      switch (fixedLength) {
         case 0:
            return type - 1 + 11;
         case 1:
         default:
            assert false;

            return 0;
         case 2:
            return type - 1 + 16;
         case 3:
            return type - 1 + 19;
         case 4:
            return type - 1 + 22;
      }
   }

   static boolean isTypedVectorElementType(int type) {
      return type >= 1 && type <= 4 || type == 26;
   }

   private static int indirect(ReadBuf bb, int offset, int byteWidth) {
      return (int)((long)offset - readUInt(bb, offset, byteWidth));
   }

   private static long readUInt(ReadBuf buff, int end, int byteWidth) {
      switch (byteWidth) {
         case 1:
            return (long)FlexBuffers.Unsigned.byteToUnsignedInt(buff.get(end));
         case 2:
            return (long)FlexBuffers.Unsigned.shortToUnsignedInt(buff.getShort(end));
         case 3:
         case 5:
         case 6:
         case 7:
         default:
            return -1L;
         case 4:
            return FlexBuffers.Unsigned.intToUnsignedLong(buff.getInt(end));
         case 8:
            return buff.getLong(end);
      }
   }

   private static int readInt(ReadBuf buff, int end, int byteWidth) {
      return (int)readLong(buff, end, byteWidth);
   }

   private static long readLong(ReadBuf buff, int end, int byteWidth) {
      switch (byteWidth) {
         case 1:
            return (long)buff.get(end);
         case 2:
            return (long)buff.getShort(end);
         case 3:
         case 5:
         case 6:
         case 7:
         default:
            return -1L;
         case 4:
            return (long)buff.getInt(end);
         case 8:
            return buff.getLong(end);
      }
   }

   private static double readDouble(ReadBuf buff, int end, int byteWidth) {
      switch (byteWidth) {
         case 4:
            return (double)buff.getFloat(end);
         case 8:
            return buff.getDouble(end);
         default:
            return (double)-1.0F;
      }
   }

   /** @deprecated */
   @Deprecated
   public static Reference getRoot(ByteBuffer buffer) {
      return getRoot((ReadBuf)(buffer.hasArray() ? new ArrayReadWriteBuf(buffer.array(), buffer.limit()) : new ByteBufferReadWriteBuf(buffer)));
   }

   public static Reference getRoot(ReadBuf buffer) {
      int end = buffer.limit();
      --end;
      int byteWidth = buffer.get(end);
      --end;
      int packetType = FlexBuffers.Unsigned.byteToUnsignedInt(buffer.get(end));
      end -= byteWidth;
      return new Reference(buffer, end, byteWidth, packetType);
   }

   public static class Reference {
      private static final Reference NULL_REFERENCE;
      private ReadBuf bb;
      private int end;
      private int parentWidth;
      private int byteWidth;
      private int type;

      Reference(ReadBuf bb, int end, int parentWidth, int packedType) {
         this(bb, end, parentWidth, 1 << (packedType & 3), packedType >> 2);
      }

      Reference(ReadBuf bb, int end, int parentWidth, int byteWidth, int type) {
         this.bb = bb;
         this.end = end;
         this.parentWidth = parentWidth;
         this.byteWidth = byteWidth;
         this.type = type;
      }

      public int getType() {
         return this.type;
      }

      public boolean isNull() {
         return this.type == 0;
      }

      public boolean isBoolean() {
         return this.type == 26;
      }

      public boolean isNumeric() {
         return this.isIntOrUInt() || this.isFloat();
      }

      public boolean isIntOrUInt() {
         return this.isInt() || this.isUInt();
      }

      public boolean isFloat() {
         return this.type == 3 || this.type == 8;
      }

      public boolean isInt() {
         return this.type == 1 || this.type == 6;
      }

      public boolean isUInt() {
         return this.type == 2 || this.type == 7;
      }

      public boolean isString() {
         return this.type == 5;
      }

      public boolean isKey() {
         return this.type == 4;
      }

      public boolean isVector() {
         return this.type == 10 || this.type == 9;
      }

      public boolean isTypedVector() {
         return FlexBuffers.isTypedVector(this.type);
      }

      public boolean isMap() {
         return this.type == 9;
      }

      public boolean isBlob() {
         return this.type == 25;
      }

      public int asInt() {
         if (this.type == 1) {
            return FlexBuffers.readInt(this.bb, this.end, this.parentWidth);
         } else {
            switch (this.type) {
               case 0:
                  return 0;
               case 1:
               case 4:
               case 9:
               case 11:
               case 12:
               case 13:
               case 14:
               case 15:
               case 16:
               case 17:
               case 18:
               case 19:
               case 20:
               case 21:
               case 22:
               case 23:
               case 24:
               case 25:
               default:
                  return 0;
               case 2:
                  return (int)FlexBuffers.readUInt(this.bb, this.end, this.parentWidth);
               case 3:
                  return (int)FlexBuffers.readDouble(this.bb, this.end, this.parentWidth);
               case 5:
                  return Integer.parseInt(this.asString());
               case 6:
                  return FlexBuffers.readInt(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth);
               case 7:
                  return (int)FlexBuffers.readUInt(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.parentWidth);
               case 8:
                  return (int)FlexBuffers.readDouble(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth);
               case 10:
                  return this.asVector().size();
               case 26:
                  return FlexBuffers.readInt(this.bb, this.end, this.parentWidth);
            }
         }
      }

      public long asUInt() {
         if (this.type == 2) {
            return FlexBuffers.readUInt(this.bb, this.end, this.parentWidth);
         } else {
            switch (this.type) {
               case 0:
                  return 0L;
               case 1:
                  return FlexBuffers.readLong(this.bb, this.end, this.parentWidth);
               case 2:
               case 4:
               case 9:
               case 11:
               case 12:
               case 13:
               case 14:
               case 15:
               case 16:
               case 17:
               case 18:
               case 19:
               case 20:
               case 21:
               case 22:
               case 23:
               case 24:
               case 25:
               default:
                  return 0L;
               case 3:
                  return (long)FlexBuffers.readDouble(this.bb, this.end, this.parentWidth);
               case 5:
                  return Long.parseLong(this.asString());
               case 6:
                  return FlexBuffers.readLong(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth);
               case 7:
                  return FlexBuffers.readUInt(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth);
               case 8:
                  return (long)FlexBuffers.readDouble(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.parentWidth);
               case 10:
                  return (long)this.asVector().size();
               case 26:
                  return (long)FlexBuffers.readInt(this.bb, this.end, this.parentWidth);
            }
         }
      }

      public long asLong() {
         if (this.type == 1) {
            return FlexBuffers.readLong(this.bb, this.end, this.parentWidth);
         } else {
            switch (this.type) {
               case 0:
                  return 0L;
               case 1:
               case 4:
               case 9:
               case 11:
               case 12:
               case 13:
               case 14:
               case 15:
               case 16:
               case 17:
               case 18:
               case 19:
               case 20:
               case 21:
               case 22:
               case 23:
               case 24:
               case 25:
               default:
                  return 0L;
               case 2:
                  return FlexBuffers.readUInt(this.bb, this.end, this.parentWidth);
               case 3:
                  return (long)FlexBuffers.readDouble(this.bb, this.end, this.parentWidth);
               case 5:
                  try {
                     return Long.parseLong(this.asString());
                  } catch (NumberFormatException var2) {
                     return 0L;
                  }
               case 6:
                  return FlexBuffers.readLong(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth);
               case 7:
                  return FlexBuffers.readUInt(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.parentWidth);
               case 8:
                  return (long)FlexBuffers.readDouble(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth);
               case 10:
                  return (long)this.asVector().size();
               case 26:
                  return (long)FlexBuffers.readInt(this.bb, this.end, this.parentWidth);
            }
         }
      }

      public double asFloat() {
         if (this.type == 3) {
            return FlexBuffers.readDouble(this.bb, this.end, this.parentWidth);
         } else {
            switch (this.type) {
               case 0:
                  return (double)0.0F;
               case 1:
                  return (double)FlexBuffers.readInt(this.bb, this.end, this.parentWidth);
               case 2:
               case 26:
                  return (double)FlexBuffers.readUInt(this.bb, this.end, this.parentWidth);
               case 3:
               case 4:
               case 9:
               case 11:
               case 12:
               case 13:
               case 14:
               case 15:
               case 16:
               case 17:
               case 18:
               case 19:
               case 20:
               case 21:
               case 22:
               case 23:
               case 24:
               case 25:
               default:
                  return (double)0.0F;
               case 5:
                  return Double.parseDouble(this.asString());
               case 6:
                  return (double)FlexBuffers.readInt(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth);
               case 7:
                  return (double)FlexBuffers.readUInt(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth);
               case 8:
                  return FlexBuffers.readDouble(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth);
               case 10:
                  return (double)this.asVector().size();
            }
         }
      }

      public Key asKey() {
         return this.isKey() ? new Key(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth) : FlexBuffers.Key.empty();
      }

      public String asString() {
         if (this.isString()) {
            int start = FlexBuffers.indirect(this.bb, this.end, this.parentWidth);
            int size = (int)FlexBuffers.readUInt(this.bb, start - this.byteWidth, this.byteWidth);
            return this.bb.getString(start, size);
         } else if (!this.isKey()) {
            return "";
         } else {
            int start = FlexBuffers.indirect(this.bb, this.end, this.byteWidth);

            int i;
            for(i = start; this.bb.get(i) != 0; ++i) {
            }

            return this.bb.getString(start, i - start);
         }
      }

      public Map asMap() {
         return this.isMap() ? new Map(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth) : FlexBuffers.Map.empty();
      }

      public Vector asVector() {
         if (this.isVector()) {
            return new Vector(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth);
         } else if (this.type == 15) {
            return new TypedVector(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth, 4);
         } else {
            return (Vector)(FlexBuffers.isTypedVector(this.type) ? new TypedVector(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth, FlexBuffers.toTypedVectorElementType(this.type)) : FlexBuffers.Vector.empty());
         }
      }

      public Blob asBlob() {
         return !this.isBlob() && !this.isString() ? FlexBuffers.Blob.empty() : new Blob(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth);
      }

      public boolean asBoolean() {
         if (this.isBoolean()) {
            return this.bb.get(this.end) != 0;
         } else {
            return this.asUInt() != 0L;
         }
      }

      public String toString() {
         return this.toString(new StringBuilder(128)).toString();
      }

      StringBuilder toString(StringBuilder sb) {
         switch (this.type) {
            case 0:
               return sb.append("null");
            case 1:
            case 6:
               return sb.append(this.asLong());
            case 2:
            case 7:
               return sb.append(this.asUInt());
            case 3:
            case 8:
               return sb.append(this.asFloat());
            case 4:
               return this.asKey().toString(sb.append('"')).append('"');
            case 5:
               return sb.append('"').append(this.asString()).append('"');
            case 9:
               return this.asMap().toString(sb);
            case 10:
               return this.asVector().toString(sb);
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 36:
               return sb.append(this.asVector());
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
               throw new FlexBufferException("not_implemented:" + this.type);
            case 25:
               return this.asBlob().toString(sb);
            case 26:
               return sb.append(this.asBoolean());
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            default:
               return sb;
         }
      }

      static {
         NULL_REFERENCE = new Reference(FlexBuffers.EMPTY_BB, 0, 1, 0);
      }
   }

   private abstract static class Object {
      ReadBuf bb;
      int end;
      int byteWidth;

      Object(ReadBuf buff, int end, int byteWidth) {
         this.bb = buff;
         this.end = end;
         this.byteWidth = byteWidth;
      }

      public String toString() {
         return this.toString(new StringBuilder(128)).toString();
      }

      public abstract StringBuilder toString(StringBuilder var1);
   }

   private abstract static class Sized extends Object {
      protected final int size;

      Sized(ReadBuf buff, int end, int byteWidth) {
         super(buff, end, byteWidth);
         this.size = (int)FlexBuffers.readUInt(this.bb, end - byteWidth, byteWidth);
      }

      public int size() {
         return this.size;
      }
   }

   public static class Blob extends Sized {
      static final Blob EMPTY;

      Blob(ReadBuf buff, int end, int byteWidth) {
         super(buff, end, byteWidth);
      }

      public static Blob empty() {
         return EMPTY;
      }

      public ByteBuffer data() {
         ByteBuffer dup = ByteBuffer.wrap(this.bb.data());
         dup.position(this.end);
         dup.limit(this.end + this.size());
         return dup.asReadOnlyBuffer().slice();
      }

      public byte[] getBytes() {
         int size = this.size();
         byte[] result = new byte[size];

         for(int i = 0; i < size; ++i) {
            result[i] = this.bb.get(this.end + i);
         }

         return result;
      }

      public byte get(int pos) {
         assert pos >= 0 && pos <= this.size();

         return this.bb.get(this.end + pos);
      }

      public String toString() {
         return this.bb.getString(this.end, this.size());
      }

      public StringBuilder toString(StringBuilder sb) {
         sb.append('"');
         sb.append(this.bb.getString(this.end, this.size()));
         return sb.append('"');
      }

      static {
         EMPTY = new Blob(FlexBuffers.EMPTY_BB, 1, 1);
      }
   }

   public static class Key extends Object {
      private static final Key EMPTY;

      Key(ReadBuf buff, int end, int byteWidth) {
         super(buff, end, byteWidth);
      }

      public static Key empty() {
         return EMPTY;
      }

      public StringBuilder toString(StringBuilder sb) {
         return sb.append(this.toString());
      }

      public String toString() {
         int i;
         for(i = this.end; this.bb.get(i) != 0; ++i) {
         }

         int size = i - this.end;
         return this.bb.getString(this.end, size);
      }

      int compareTo(byte[] other) {
         int ia = this.end;
         int io = 0;

         byte c1;
         byte c2;
         do {
            c1 = this.bb.get(ia);
            c2 = other[io];
            if (c1 == 0) {
               return c1 - c2;
            }

            ++ia;
            ++io;
            if (io == other.length) {
               int cmp = c1 - c2;
               if (cmp == 0 && this.bb.get(ia) != 0) {
                  return 1;
               }

               return cmp;
            }
         } while(c1 == c2);

         return c1 - c2;
      }

      public boolean equals(java.lang.Object obj) {
         if (!(obj instanceof Key)) {
            return false;
         } else {
            return ((Key)obj).end == this.end && ((Key)obj).byteWidth == this.byteWidth;
         }
      }

      public int hashCode() {
         return this.end ^ this.byteWidth;
      }

      static {
         EMPTY = new Key(FlexBuffers.EMPTY_BB, 0, 0);
      }
   }

   public static class Map extends Vector {
      private static final Map EMPTY_MAP;
      private final byte[] comparisonBuffer = new byte[4];

      Map(ReadBuf bb, int end, int byteWidth) {
         super(bb, end, byteWidth);
      }

      public static Map empty() {
         return EMPTY_MAP;
      }

      public Reference get(String key) {
         int index = this.binarySearch((CharSequence)key);
         return index >= 0 && index < this.size ? this.get(index) : FlexBuffers.Reference.NULL_REFERENCE;
      }

      public Reference get(byte[] key) {
         int index = this.binarySearch(key);
         return index >= 0 && index < this.size ? this.get(index) : FlexBuffers.Reference.NULL_REFERENCE;
      }

      public KeyVector keys() {
         int num_prefixed_fields = 3;
         int keysOffset = this.end - this.byteWidth * 3;
         return new KeyVector(new TypedVector(this.bb, FlexBuffers.indirect(this.bb, keysOffset, this.byteWidth), FlexBuffers.readInt(this.bb, keysOffset + this.byteWidth, this.byteWidth), 4));
      }

      public Vector values() {
         return new Vector(this.bb, this.end, this.byteWidth);
      }

      public StringBuilder toString(StringBuilder builder) {
         builder.append("{ ");
         KeyVector keys = this.keys();
         int size = this.size();
         Vector vals = this.values();

         for(int i = 0; i < size; ++i) {
            builder.append('"').append(keys.get(i).toString()).append("\" : ");
            builder.append(vals.get(i).toString());
            if (i != size - 1) {
               builder.append(", ");
            }
         }

         builder.append(" }");
         return builder;
      }

      private int binarySearch(CharSequence searchedKey) {
         int low = 0;
         int high = this.size - 1;
         int num_prefixed_fields = 3;
         int keysOffset = this.end - this.byteWidth * 3;
         int keysStart = FlexBuffers.indirect(this.bb, keysOffset, this.byteWidth);
         int keyByteWidth = FlexBuffers.readInt(this.bb, keysOffset + this.byteWidth, this.byteWidth);

         while(low <= high) {
            int mid = low + high >>> 1;
            int keyPos = FlexBuffers.indirect(this.bb, keysStart + mid * keyByteWidth, keyByteWidth);
            int cmp = this.compareCharSequence(keyPos, searchedKey);
            if (cmp < 0) {
               low = mid + 1;
            } else {
               if (cmp <= 0) {
                  return mid;
               }

               high = mid - 1;
            }
         }

         return -(low + 1);
      }

      private int binarySearch(byte[] searchedKey) {
         int low = 0;
         int high = this.size - 1;
         int num_prefixed_fields = 3;
         int keysOffset = this.end - this.byteWidth * 3;
         int keysStart = FlexBuffers.indirect(this.bb, keysOffset, this.byteWidth);
         int keyByteWidth = FlexBuffers.readInt(this.bb, keysOffset + this.byteWidth, this.byteWidth);

         while(low <= high) {
            int mid = low + high >>> 1;
            int keyPos = FlexBuffers.indirect(this.bb, keysStart + mid * keyByteWidth, keyByteWidth);
            int cmp = this.compareBytes(this.bb, keyPos, searchedKey);
            if (cmp < 0) {
               low = mid + 1;
            } else {
               if (cmp <= 0) {
                  return mid;
               }

               high = mid - 1;
            }
         }

         return -(low + 1);
      }

      private int compareBytes(ReadBuf bb, int start, byte[] other) {
         int l1 = start;
         int l2 = 0;

         byte c1;
         byte c2;
         do {
            c1 = bb.get(l1);
            c2 = other[l2];
            if (c1 == 0) {
               return c1 - c2;
            }

            ++l1;
            ++l2;
            if (l2 == other.length) {
               int cmp = c1 - c2;
               if (cmp == 0 && bb.get(l1) != 0) {
                  return 1;
               }

               return cmp;
            }
         } while(c1 == c2);

         return c1 - c2;
      }

      private int compareCharSequence(int start, CharSequence other) {
         int bufferPos = start;
         int otherPos = 0;
         int limit = this.bb.limit();

         for(int otherLimit = other.length(); otherPos < otherLimit; ++otherPos) {
            char c2 = other.charAt(otherPos);
            if (c2 >= 128) {
               break;
            }

            byte b = this.bb.get(bufferPos);
            if (b == 0) {
               return -c2;
            }

            if (b < 0) {
               break;
            }

            if ((char)b != c2) {
               return b - c2;
            }

            ++bufferPos;
         }

         while(bufferPos < limit) {
            int sizeInBuff = Utf8.encodeUtf8CodePoint(other, otherPos, this.comparisonBuffer);
            if (sizeInBuff == 0) {
               return this.bb.get(bufferPos);
            }

            for(int i = 0; i < sizeInBuff; ++i) {
               byte bufferByte = this.bb.get(bufferPos++);
               byte otherByte = this.comparisonBuffer[i];
               if (bufferByte == 0) {
                  return -otherByte;
               }

               if (bufferByte != otherByte) {
                  return bufferByte - otherByte;
               }
            }

            otherPos += sizeInBuff == 4 ? 2 : 1;
         }

         return 0;
      }

      static {
         EMPTY_MAP = new Map(FlexBuffers.EMPTY_BB, 1, 1);
      }
   }

   public static class Vector extends Sized {
      private static final Vector EMPTY_VECTOR;

      Vector(ReadBuf bb, int end, int byteWidth) {
         super(bb, end, byteWidth);
      }

      public static Vector empty() {
         return EMPTY_VECTOR;
      }

      public boolean isEmpty() {
         return this == EMPTY_VECTOR;
      }

      public StringBuilder toString(StringBuilder sb) {
         sb.append("[ ");
         int size = this.size();

         for(int i = 0; i < size; ++i) {
            this.get(i).toString(sb);
            if (i != size - 1) {
               sb.append(", ");
            }
         }

         sb.append(" ]");
         return sb;
      }

      public Reference get(int index) {
         long len = (long)this.size();
         if ((long)index >= len) {
            return FlexBuffers.Reference.NULL_REFERENCE;
         } else {
            int packedType = FlexBuffers.Unsigned.byteToUnsignedInt(this.bb.get((int)((long)this.end + len * (long)this.byteWidth + (long)index)));
            int obj_end = this.end + index * this.byteWidth;
            return new Reference(this.bb, obj_end, this.byteWidth, packedType);
         }
      }

      static {
         EMPTY_VECTOR = new Vector(FlexBuffers.EMPTY_BB, 1, 1);
      }
   }

   public static class TypedVector extends Vector {
      private static final TypedVector EMPTY_VECTOR;
      private final int elemType;

      TypedVector(ReadBuf bb, int end, int byteWidth, int elemType) {
         super(bb, end, byteWidth);
         this.elemType = elemType;
      }

      public static TypedVector empty() {
         return EMPTY_VECTOR;
      }

      public boolean isEmptyVector() {
         return this == EMPTY_VECTOR;
      }

      public int getElemType() {
         return this.elemType;
      }

      public Reference get(int pos) {
         int len = this.size();
         if (pos >= len) {
            return FlexBuffers.Reference.NULL_REFERENCE;
         } else {
            int childPos = this.end + pos * this.byteWidth;
            return new Reference(this.bb, childPos, this.byteWidth, 1, this.elemType);
         }
      }

      static {
         EMPTY_VECTOR = new TypedVector(FlexBuffers.EMPTY_BB, 1, 1, 1);
      }
   }

   public static class KeyVector {
      private final TypedVector vec;

      KeyVector(TypedVector vec) {
         this.vec = vec;
      }

      public Key get(int pos) {
         int len = this.size();
         if (pos >= len) {
            return FlexBuffers.Key.EMPTY;
         } else {
            int childPos = this.vec.end + pos * this.vec.byteWidth;
            return new Key(this.vec.bb, FlexBuffers.indirect(this.vec.bb, childPos, this.vec.byteWidth), 1);
         }
      }

      public int size() {
         return this.vec.size();
      }

      public String toString() {
         StringBuilder b = new StringBuilder();
         b.append('[');

         for(int i = 0; i < this.vec.size(); ++i) {
            this.vec.get(i).toString(b);
            if (i != this.vec.size() - 1) {
               b.append(", ");
            }
         }

         return b.append("]").toString();
      }
   }

   public static class FlexBufferException extends RuntimeException {
      FlexBufferException(String msg) {
         super(msg);
      }
   }

   static class Unsigned {
      static int byteToUnsignedInt(byte x) {
         return x & 255;
      }

      static int shortToUnsignedInt(short x) {
         return x & '\uffff';
      }

      static long intToUnsignedLong(int x) {
         return (long)x & 4294967295L;
      }
   }
}
