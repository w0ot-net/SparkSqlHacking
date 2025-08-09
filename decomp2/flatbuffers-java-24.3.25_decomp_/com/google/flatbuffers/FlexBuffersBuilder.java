package com.google.flatbuffers;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class FlexBuffersBuilder {
   public static final int BUILDER_FLAG_NONE = 0;
   public static final int BUILDER_FLAG_SHARE_KEYS = 1;
   public static final int BUILDER_FLAG_SHARE_STRINGS = 2;
   public static final int BUILDER_FLAG_SHARE_KEYS_AND_STRINGS = 3;
   public static final int BUILDER_FLAG_SHARE_KEY_VECTORS = 4;
   public static final int BUILDER_FLAG_SHARE_ALL = 7;
   private static final int WIDTH_8 = 0;
   private static final int WIDTH_16 = 1;
   private static final int WIDTH_32 = 2;
   private static final int WIDTH_64 = 3;
   private final ReadWriteBuf bb;
   private final ArrayList stack;
   private final HashMap keyPool;
   private final HashMap stringPool;
   private final int flags;
   private boolean finished;
   private Comparator keyComparator;

   public FlexBuffersBuilder(int bufSize) {
      this((ReadWriteBuf)(new ArrayReadWriteBuf(bufSize)), 1);
   }

   public FlexBuffersBuilder() {
      this(256);
   }

   /** @deprecated */
   @Deprecated
   public FlexBuffersBuilder(ByteBuffer bb, int flags) {
      this((ReadWriteBuf)(new ArrayReadWriteBuf(bb.array())), flags);
   }

   public FlexBuffersBuilder(ReadWriteBuf bb, int flags) {
      this.stack = new ArrayList();
      this.keyPool = new HashMap();
      this.stringPool = new HashMap();
      this.finished = false;
      this.keyComparator = new Comparator() {
         public int compare(Value o1, Value o2) {
            int ia = o1.key;
            int io = o2.key;

            byte c1;
            byte c2;
            do {
               c1 = FlexBuffersBuilder.this.bb.get(ia);
               c2 = FlexBuffersBuilder.this.bb.get(io);
               if (c1 == 0) {
                  return c1 - c2;
               }

               ++ia;
               ++io;
            } while(c1 == c2);

            return c1 - c2;
         }
      };
      this.bb = bb;
      this.flags = flags;
   }

   public FlexBuffersBuilder(ByteBuffer bb) {
      this((ByteBuffer)bb, 1);
   }

   public void clear() {
      this.bb.clear();
      this.stack.clear();
      this.keyPool.clear();
      this.stringPool.clear();
      this.finished = false;
   }

   public ReadWriteBuf getBuffer() {
      assert this.finished;

      return this.bb;
   }

   public void putNull() {
      this.putNull((String)null);
   }

   public void putNull(String key) {
      this.stack.add(FlexBuffersBuilder.Value.nullValue(this.putKey(key)));
   }

   public void putBoolean(boolean val) {
      this.putBoolean((String)null, val);
   }

   public void putBoolean(String key, boolean val) {
      this.stack.add(FlexBuffersBuilder.Value.bool(this.putKey(key), val));
   }

   private int putKey(String key) {
      if (key == null) {
         return -1;
      } else {
         int pos = this.bb.writePosition();
         if ((this.flags & 1) != 0) {
            Integer keyFromPool = (Integer)this.keyPool.get(key);
            if (keyFromPool == null) {
               byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
               this.bb.put(keyBytes, 0, keyBytes.length);
               this.bb.put((byte)0);
               this.keyPool.put(key, pos);
            } else {
               pos = keyFromPool;
            }
         } else {
            byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
            this.bb.put(keyBytes, 0, keyBytes.length);
            this.bb.put((byte)0);
            this.keyPool.put(key, pos);
         }

         return pos;
      }
   }

   public void putInt(int val) {
      this.putInt((String)null, val);
   }

   public void putInt(String key, int val) {
      this.putInt(key, (long)val);
   }

   public void putInt(String key, long val) {
      int iKey = this.putKey(key);
      if (-128L <= val && val <= 127L) {
         this.stack.add(FlexBuffersBuilder.Value.int8(iKey, (int)val));
      } else if (-32768L <= val && val <= 32767L) {
         this.stack.add(FlexBuffersBuilder.Value.int16(iKey, (int)val));
      } else if (-2147483648L <= val && val <= 2147483647L) {
         this.stack.add(FlexBuffersBuilder.Value.int32(iKey, (int)val));
      } else {
         this.stack.add(FlexBuffersBuilder.Value.int64(iKey, val));
      }

   }

   public void putInt(long value) {
      this.putInt((String)null, value);
   }

   public void putUInt(int value) {
      this.putUInt((String)null, (long)value);
   }

   public void putUInt(long value) {
      this.putUInt((String)null, value);
   }

   public void putUInt64(BigInteger value) {
      this.putUInt64((String)null, value.longValue());
   }

   private void putUInt64(String key, long value) {
      this.stack.add(FlexBuffersBuilder.Value.uInt64(this.putKey(key), value));
   }

   private void putUInt(String key, long value) {
      int iKey = this.putKey(key);
      int width = widthUInBits(value);
      Value vVal;
      if (width == 0) {
         vVal = FlexBuffersBuilder.Value.uInt8(iKey, (int)value);
      } else if (width == 1) {
         vVal = FlexBuffersBuilder.Value.uInt16(iKey, (int)value);
      } else if (width == 2) {
         vVal = FlexBuffersBuilder.Value.uInt32(iKey, (int)value);
      } else {
         vVal = FlexBuffersBuilder.Value.uInt64(iKey, value);
      }

      this.stack.add(vVal);
   }

   public void putFloat(float value) {
      this.putFloat((String)null, value);
   }

   public void putFloat(String key, float val) {
      this.stack.add(FlexBuffersBuilder.Value.float32(this.putKey(key), val));
   }

   public void putFloat(double value) {
      this.putFloat((String)null, value);
   }

   public void putFloat(String key, double val) {
      this.stack.add(FlexBuffersBuilder.Value.float64(this.putKey(key), val));
   }

   public int putString(String value) {
      return this.putString((String)null, value);
   }

   public int putString(String key, String val) {
      int iKey = this.putKey(key);
      if ((this.flags & 2) != 0) {
         Integer i = (Integer)this.stringPool.get(val);
         if (i == null) {
            Value value = this.writeString(iKey, val);
            this.stringPool.put(val, (int)value.iValue);
            this.stack.add(value);
            return (int)value.iValue;
         } else {
            int bitWidth = widthUInBits((long)val.length());
            this.stack.add(FlexBuffersBuilder.Value.blob(iKey, i, 5, bitWidth));
            return i;
         }
      } else {
         Value value = this.writeString(iKey, val);
         this.stack.add(value);
         return (int)value.iValue;
      }
   }

   private Value writeString(int key, String s) {
      return this.writeBlob(key, s.getBytes(StandardCharsets.UTF_8), 5, true);
   }

   static int widthUInBits(long len) {
      if (len <= (long)FlexBuffers.Unsigned.byteToUnsignedInt((byte)-1)) {
         return 0;
      } else if (len <= (long)FlexBuffers.Unsigned.shortToUnsignedInt((short)-1)) {
         return 1;
      } else {
         return len <= FlexBuffers.Unsigned.intToUnsignedLong(-1) ? 2 : 3;
      }
   }

   private Value writeBlob(int key, byte[] blob, int type, boolean trailing) {
      int bitWidth = widthUInBits((long)blob.length);
      int byteWidth = this.align(bitWidth);
      this.writeInt((long)blob.length, byteWidth);
      int sloc = this.bb.writePosition();
      this.bb.put(blob, 0, blob.length);
      if (trailing) {
         this.bb.put((byte)0);
      }

      return FlexBuffersBuilder.Value.blob(key, sloc, type, bitWidth);
   }

   private int align(int alignment) {
      int byteWidth = 1 << alignment;
      int padBytes = FlexBuffersBuilder.Value.paddingBytes(this.bb.writePosition(), byteWidth);

      while(padBytes-- != 0) {
         this.bb.put((byte)0);
      }

      return byteWidth;
   }

   private void writeInt(long value, int byteWidth) {
      switch (byteWidth) {
         case 1:
            this.bb.put((byte)((int)value));
            break;
         case 2:
            this.bb.putShort((short)((int)value));
         case 3:
         case 5:
         case 6:
         case 7:
         default:
            break;
         case 4:
            this.bb.putInt((int)value);
            break;
         case 8:
            this.bb.putLong(value);
      }

   }

   public int putBlob(byte[] value) {
      return this.putBlob((String)null, value);
   }

   public int putBlob(String key, byte[] val) {
      int iKey = this.putKey(key);
      Value value = this.writeBlob(iKey, val, 25, false);
      this.stack.add(value);
      return (int)value.iValue;
   }

   public int startVector() {
      return this.stack.size();
   }

   public int endVector(String key, int start, boolean typed, boolean fixed) {
      int iKey = this.putKey(key);
      Value vec = this.createVector(iKey, start, this.stack.size() - start, typed, fixed, (Value)null);

      while(this.stack.size() > start) {
         this.stack.remove(this.stack.size() - 1);
      }

      this.stack.add(vec);
      return (int)vec.iValue;
   }

   public ByteBuffer finish() {
      assert this.stack.size() == 1;

      int byteWidth = this.align(((Value)this.stack.get(0)).elemWidth(this.bb.writePosition(), 0));
      this.writeAny((Value)this.stack.get(0), byteWidth);
      this.bb.put(((Value)this.stack.get(0)).storedPackedType());
      this.bb.put((byte)byteWidth);
      this.finished = true;
      return ByteBuffer.wrap(this.bb.data(), 0, this.bb.writePosition());
   }

   private Value createVector(int key, int start, int length, boolean typed, boolean fixed, Value keys) {
      if (fixed & !typed) {
         throw new UnsupportedOperationException("Untyped fixed vector is not supported");
      } else {
         int bitWidth = Math.max(0, widthUInBits((long)length));
         int prefixElems = 1;
         if (keys != null) {
            bitWidth = Math.max(bitWidth, keys.elemWidth(this.bb.writePosition(), 0));
            prefixElems += 2;
         }

         int vectorType = 4;

         for(int i = start; i < this.stack.size(); ++i) {
            int elemWidth = ((Value)this.stack.get(i)).elemWidth(this.bb.writePosition(), i + prefixElems);
            bitWidth = Math.max(bitWidth, elemWidth);
            if (typed) {
               if (i == start) {
                  vectorType = ((Value)this.stack.get(i)).type;
                  if (!FlexBuffers.isTypedVectorElementType(vectorType)) {
                     throw new FlexBuffers.FlexBufferException("TypedVector does not support this element type");
                  }
               } else {
                  assert vectorType == ((Value)this.stack.get(i)).type;
               }
            }
         }

         assert !fixed || FlexBuffers.isTypedVectorElementType(vectorType);

         int byteWidth = this.align(bitWidth);
         if (keys != null) {
            this.writeOffset(keys.iValue, byteWidth);
            this.writeInt(1L << keys.minBitWidth, byteWidth);
         }

         if (!fixed) {
            this.writeInt((long)length, byteWidth);
         }

         int vloc = this.bb.writePosition();

         for(int i = start; i < this.stack.size(); ++i) {
            this.writeAny((Value)this.stack.get(i), byteWidth);
         }

         if (!typed) {
            for(int i = start; i < this.stack.size(); ++i) {
               this.bb.put(((Value)this.stack.get(i)).storedPackedType(bitWidth));
            }
         }

         return new Value(key, keys != null ? 9 : (typed ? FlexBuffers.toTypedVector(vectorType, fixed ? length : 0) : 10), bitWidth, (long)vloc);
      }
   }

   private void writeOffset(long val, int byteWidth) {
      int reloff = (int)((long)this.bb.writePosition() - val);

      assert byteWidth == 8 || (long)reloff < 1L << byteWidth * 8;

      this.writeInt((long)reloff, byteWidth);
   }

   private void writeAny(Value val, int byteWidth) {
      switch (val.type) {
         case 0:
         case 1:
         case 2:
         case 26:
            this.writeInt(val.iValue, byteWidth);
            break;
         case 3:
            this.writeDouble(val.dValue, byteWidth);
            break;
         default:
            this.writeOffset(val.iValue, byteWidth);
      }

   }

   private void writeDouble(double val, int byteWidth) {
      if (byteWidth == 4) {
         this.bb.putFloat((float)val);
      } else if (byteWidth == 8) {
         this.bb.putDouble(val);
      }

   }

   public int startMap() {
      return this.stack.size();
   }

   public int endMap(String key, int start) {
      int iKey = this.putKey(key);
      Collections.sort(this.stack.subList(start, this.stack.size()), this.keyComparator);
      Value keys = this.createKeyVector(start, this.stack.size() - start);
      Value vec = this.createVector(iKey, start, this.stack.size() - start, false, false, keys);

      while(this.stack.size() > start) {
         this.stack.remove(this.stack.size() - 1);
      }

      this.stack.add(vec);
      return (int)vec.iValue;
   }

   private Value createKeyVector(int start, int length) {
      int bitWidth = Math.max(0, widthUInBits((long)length));
      int prefixElems = 1;

      for(int i = start; i < this.stack.size(); ++i) {
         int elemWidth = FlexBuffersBuilder.Value.elemWidth(4, 0, (long)((Value)this.stack.get(i)).key, this.bb.writePosition(), i + prefixElems);
         bitWidth = Math.max(bitWidth, elemWidth);
      }

      int byteWidth = this.align(bitWidth);
      this.writeInt((long)length, byteWidth);
      int vloc = this.bb.writePosition();

      for(int i = start; i < this.stack.size(); ++i) {
         int pos = ((Value)this.stack.get(i)).key;

         assert pos != -1;

         this.writeOffset((long)((Value)this.stack.get(i)).key, byteWidth);
      }

      return new Value(-1, FlexBuffers.toTypedVector(4, 0), bitWidth, (long)vloc);
   }

   private static class Value {
      final int type;
      final int minBitWidth;
      final double dValue;
      long iValue;
      int key;

      Value(int key, int type, int bitWidth, long iValue) {
         this.key = key;
         this.type = type;
         this.minBitWidth = bitWidth;
         this.iValue = iValue;
         this.dValue = Double.MIN_VALUE;
      }

      Value(int key, int type, int bitWidth, double dValue) {
         this.key = key;
         this.type = type;
         this.minBitWidth = bitWidth;
         this.dValue = dValue;
         this.iValue = Long.MIN_VALUE;
      }

      static Value nullValue(int key) {
         return new Value(key, 0, 0, 0L);
      }

      static Value bool(int key, boolean b) {
         return new Value(key, 26, 0, b ? 1L : 0L);
      }

      static Value blob(int key, int position, int type, int bitWidth) {
         return new Value(key, type, bitWidth, (long)position);
      }

      static Value int8(int key, int value) {
         return new Value(key, 1, 0, (long)value);
      }

      static Value int16(int key, int value) {
         return new Value(key, 1, 1, (long)value);
      }

      static Value int32(int key, int value) {
         return new Value(key, 1, 2, (long)value);
      }

      static Value int64(int key, long value) {
         return new Value(key, 1, 3, value);
      }

      static Value uInt8(int key, int value) {
         return new Value(key, 2, 0, (long)value);
      }

      static Value uInt16(int key, int value) {
         return new Value(key, 2, 1, (long)value);
      }

      static Value uInt32(int key, int value) {
         return new Value(key, 2, 2, (long)value);
      }

      static Value uInt64(int key, long value) {
         return new Value(key, 2, 3, value);
      }

      static Value float32(int key, float value) {
         return new Value(key, 3, 2, (double)value);
      }

      static Value float64(int key, double value) {
         return new Value(key, 3, 3, value);
      }

      private byte storedPackedType() {
         return this.storedPackedType(0);
      }

      private byte storedPackedType(int parentBitWidth) {
         return packedType(this.storedWidth(parentBitWidth), this.type);
      }

      private static byte packedType(int bitWidth, int type) {
         return (byte)(bitWidth | type << 2);
      }

      private int storedWidth(int parentBitWidth) {
         return FlexBuffers.isTypeInline(this.type) ? Math.max(this.minBitWidth, parentBitWidth) : this.minBitWidth;
      }

      private int elemWidth(int bufSize, int elemIndex) {
         return elemWidth(this.type, this.minBitWidth, this.iValue, bufSize, elemIndex);
      }

      private static int elemWidth(int type, int minBitWidth, long iValue, int bufSize, int elemIndex) {
         if (FlexBuffers.isTypeInline(type)) {
            return minBitWidth;
         } else {
            for(int byteWidth = 1; byteWidth <= 32; byteWidth *= 2) {
               int offsetLoc = bufSize + paddingBytes(bufSize, byteWidth) + elemIndex * byteWidth;
               long offset = (long)offsetLoc - iValue;
               int bitWidth = FlexBuffersBuilder.widthUInBits(offset);
               if (1L << bitWidth == (long)byteWidth) {
                  return bitWidth;
               }
            }

            assert false;

            return 3;
         }
      }

      private static int paddingBytes(int bufSize, int scalarSize) {
         return ~bufSize + 1 & scalarSize - 1;
      }
   }
}
