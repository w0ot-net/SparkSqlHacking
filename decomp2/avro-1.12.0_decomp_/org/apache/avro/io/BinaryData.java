package org.apache.avro.io;

import java.io.IOException;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.util.internal.ThreadLocalWithInitial;

public class BinaryData {
   private static final ThreadLocal DECODERS = ThreadLocalWithInitial.of(Decoders::new);
   private static final ThreadLocal HASH_DATA = ThreadLocalWithInitial.of(HashData::new);

   private BinaryData() {
   }

   public static int compare(byte[] b1, int s1, byte[] b2, int s2, Schema schema) {
      return compare(b1, s1, b1.length - s1, b2, s2, b2.length - s2, schema);
   }

   public static int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2, Schema schema) {
      Decoders decoders = (Decoders)DECODERS.get();
      decoders.set(b1, s1, l1, b2, s2, l2);

      int var8;
      try {
         var8 = compare(decoders, schema);
      } catch (IOException e) {
         throw new AvroRuntimeException(e);
      } finally {
         decoders.clear();
      }

      return var8;
   }

   private static int compare(Decoders d, Schema schema) throws IOException {
      Decoder d1 = d.d1;
      Decoder d2 = d.d2;
      switch (schema.getType()) {
         case RECORD:
            for(Schema.Field field : schema.getFields()) {
               if (field.order() == Schema.Field.Order.IGNORE) {
                  GenericDatumReader.skip(field.schema(), d1);
                  GenericDatumReader.skip(field.schema(), d2);
               } else {
                  int c = compare(d, field.schema());
                  if (c != 0) {
                     return field.order() != Schema.Field.Order.DESCENDING ? c : -c;
                  }
               }
            }

            return 0;
         case ENUM:
         case INT:
            return Integer.compare(d1.readInt(), d2.readInt());
         case LONG:
            return Long.compare(d1.readLong(), d2.readLong());
         case FLOAT:
            return Float.compare(d1.readFloat(), d2.readFloat());
         case DOUBLE:
            return Double.compare(d1.readDouble(), d2.readDouble());
         case BOOLEAN:
            return Boolean.compare(d1.readBoolean(), d2.readBoolean());
         case ARRAY:
            long i = 0L;
            long r1 = 0L;
            long r2 = 0L;
            long l1 = 0L;
            long l2 = 0L;

            while(true) {
               if (r1 == 0L) {
                  r1 = d1.readLong();
                  if (r1 < 0L) {
                     r1 = -r1;
                     d1.readLong();
                  }

                  l1 += r1;
               }

               if (r2 == 0L) {
                  r2 = d2.readLong();
                  if (r2 < 0L) {
                     r2 = -r2;
                     d2.readLong();
                  }

                  l2 += r2;
               }

               if (r1 == 0L || r2 == 0L) {
                  return Long.compare(l1, l2);
               }

               for(long l = Math.min(l1, l2); i < l; --r2) {
                  int c = compare(d, schema.getElementType());
                  if (c != 0) {
                     return c;
                  }

                  ++i;
                  --r1;
               }
            }
         case MAP:
            throw new AvroRuntimeException("Can't compare maps!");
         case UNION:
            int i1 = d1.readInt();
            int i2 = d2.readInt();
            int c = Integer.compare(i1, i2);
            return c == 0 ? compare(d, (Schema)schema.getTypes().get(i1)) : c;
         case FIXED:
            int size = schema.getFixedSize();
            int c = compareBytes(d.d1.getBuf(), d.d1.getPos(), size, d.d2.getBuf(), d.d2.getPos(), size);
            d.d1.skipFixed(size);
            d.d2.skipFixed(size);
            return c;
         case STRING:
         case BYTES:
            int l1 = d1.readInt();
            int l2 = d2.readInt();
            int c = compareBytes(d.d1.getBuf(), d.d1.getPos(), l1, d.d2.getBuf(), d.d2.getPos(), l2);
            d.d1.skipFixed(l1);
            d.d2.skipFixed(l2);
            return c;
         case NULL:
            return 0;
         default:
            throw new AvroRuntimeException("Unexpected schema to compare!");
      }
   }

   public static int compareBytes(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
      int end1 = s1 + l1;
      int end2 = s2 + l2;
      int i = s1;

      for(int j = s2; i < end1 && j < end2; ++j) {
         int a = b1[i] & 255;
         int b = b2[j] & 255;
         if (a != b) {
            return a - b;
         }

         ++i;
      }

      return l1 - l2;
   }

   public static int hashCode(byte[] bytes, int start, int length, Schema schema) {
      HashData data = (HashData)HASH_DATA.get();
      data.set(bytes, start, length);

      try {
         return hashCode(data, schema);
      } catch (IOException e) {
         throw new AvroRuntimeException(e);
      }
   }

   private static int hashCode(HashData data, Schema schema) throws IOException {
      Decoder decoder = data.decoder;
      switch (schema.getType()) {
         case RECORD:
            int hashCode = 1;

            for(Schema.Field field : schema.getFields()) {
               if (field.order() == Schema.Field.Order.IGNORE) {
                  GenericDatumReader.skip(field.schema(), decoder);
               } else {
                  hashCode = hashCode * 31 + hashCode(data, field.schema());
               }
            }

            return hashCode;
         case ENUM:
         case INT:
            return decoder.readInt();
         case LONG:
            return Long.hashCode(decoder.readLong());
         case FLOAT:
            return Float.hashCode(decoder.readFloat());
         case DOUBLE:
            return Double.hashCode(decoder.readDouble());
         case BOOLEAN:
            return Boolean.hashCode(decoder.readBoolean());
         case ARRAY:
            Schema elementType = schema.getElementType();
            int hashCode = 1;

            for(long l = decoder.readArrayStart(); l != 0L; l = decoder.arrayNext()) {
               for(long i = 0L; i < l; ++i) {
                  hashCode = hashCode * 31 + hashCode(data, elementType);
               }
            }

            return hashCode;
         case MAP:
            throw new AvroRuntimeException("Can't hashCode maps!");
         case UNION:
            return hashCode(data, (Schema)schema.getTypes().get(decoder.readInt()));
         case FIXED:
            return hashBytes(1, data, schema.getFixedSize(), false);
         case STRING:
            return hashBytes(0, data, decoder.readInt(), false);
         case BYTES:
            return hashBytes(1, data, decoder.readInt(), true);
         case NULL:
            return 0;
         default:
            throw new AvroRuntimeException("Unexpected schema to hashCode!");
      }
   }

   private static int hashBytes(int init, HashData data, int len, boolean rev) throws IOException {
      int hashCode = init;
      byte[] bytes = data.decoder.getBuf();
      int start = data.decoder.getPos();
      int end = start + len;
      if (rev) {
         for(int i = end - 1; i >= start; --i) {
            hashCode = hashCode * 31 + bytes[i];
         }
      } else {
         for(int i = start; i < end; ++i) {
            hashCode = hashCode * 31 + bytes[i];
         }
      }

      data.decoder.skipFixed(len);
      return hashCode;
   }

   public static int skipLong(final byte[] bytes, int start) {
      while((bytes[start++] & 128) != 0) {
      }

      return start;
   }

   public static int encodeBoolean(boolean b, byte[] buf, int pos) {
      buf[pos] = (byte)(b ? 1 : 0);
      return 1;
   }

   public static int encodeInt(int n, byte[] buf, int pos) {
      n = n << 1 ^ n >> 31;
      int start = pos;
      if ((n & -128) != 0) {
         buf[pos++] = (byte)((n | 128) & 255);
         n >>>= 7;
         if (n > 127) {
            buf[pos++] = (byte)((n | 128) & 255);
            n >>>= 7;
            if (n > 127) {
               buf[pos++] = (byte)((n | 128) & 255);
               n >>>= 7;
               if (n > 127) {
                  buf[pos++] = (byte)((n | 128) & 255);
                  n >>>= 7;
               }
            }
         }
      }

      buf[pos++] = (byte)n;
      return pos - start;
   }

   public static int encodeLong(long n, byte[] buf, int pos) {
      // $FF: Couldn't be decompiled
   }

   public static int encodeFloat(float f, byte[] buf, int pos) {
      int bits = Float.floatToRawIntBits(f);
      buf[pos + 3] = (byte)(bits >>> 24);
      buf[pos + 2] = (byte)(bits >>> 16);
      buf[pos + 1] = (byte)(bits >>> 8);
      buf[pos] = (byte)bits;
      return 4;
   }

   public static int encodeDouble(double d, byte[] buf, int pos) {
      long bits = Double.doubleToRawLongBits(d);
      int first = (int)(bits & -1L);
      int second = (int)(bits >>> 32 & -1L);
      buf[pos] = (byte)first;
      buf[pos + 4] = (byte)second;
      buf[pos + 5] = (byte)(second >>> 8);
      buf[pos + 1] = (byte)(first >>> 8);
      buf[pos + 2] = (byte)(first >>> 16);
      buf[pos + 6] = (byte)(second >>> 16);
      buf[pos + 7] = (byte)(second >>> 24);
      buf[pos + 3] = (byte)(first >>> 24);
      return 8;
   }

   private static class Decoders {
      private final BinaryDecoder d1 = new BinaryDecoder(new byte[0], 0, 0);
      private final BinaryDecoder d2 = new BinaryDecoder(new byte[0], 0, 0);

      public Decoders() {
      }

      public void set(byte[] data1, int off1, int len1, byte[] data2, int off2, int len2) {
         this.d1.setBuf(data1, off1, len1);
         this.d2.setBuf(data2, off2, len2);
      }

      public void clear() {
         this.d1.clearBuf();
         this.d2.clearBuf();
      }
   }

   private static class HashData {
      private final BinaryDecoder decoder = new BinaryDecoder(new byte[0], 0, 0);

      public HashData() {
      }

      public void set(byte[] bytes, int start, int len) {
         this.decoder.setBuf(bytes, start, len);
      }
   }
}
