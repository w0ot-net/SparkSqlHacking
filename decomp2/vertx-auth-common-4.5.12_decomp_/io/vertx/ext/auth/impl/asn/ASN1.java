package io.vertx.ext.auth.impl.asn;

import io.vertx.core.buffer.Buffer;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ASN1 {
   public static final int ANY = 0;
   public static final int BOOLEAN = 1;
   public static final int INTEGER = 2;
   public static final int BIT_STRING = 3;
   public static final int OCTET_STRING = 4;
   public static final int NULL = 5;
   public static final int OBJECT_IDENTIFIER = 6;
   public static final int REAL = 9;
   public static final int ENUMERATED = 10;
   public static final int UTF8_STRING = 12;
   public static final int SEQUENCE = 16;
   public static final int SET = 17;
   public static final int NUMERIC_STRING = 18;
   public static final int PRINTABLE_STRING = 19;
   public static final int VIDEOTEX_STRING = 21;
   public static final int IA5_STRING = 22;
   public static final int UTC_TIME = 23;
   public static final int GRAPHIC_STRING = 25;
   public static final int ISO646_STRING = 26;
   public static final int GENERAL_STRING = 27;
   public static final int UNIVERSAL_STRING = 28;
   public static final int BMP_STRING = 30;
   public static final int CONSTRUCTED = 32;
   public static final int CONTEXT_SPECIFIC = 128;
   private static final int CLASS_MASK = 192;
   private static final int CONSTRUCTED_MASK = 32;
   private static final int NUMBER_MASK = 31;
   private static final int LEADING_BIT_MASK = 128;

   public static byte[] length(int x) {
      if (x <= 127) {
         return new byte[]{(byte)x};
      } else if (x < 256) {
         return new byte[]{-127, (byte)x};
      } else {
         throw new IllegalArgumentException("length >= 256");
      }
   }

   public static byte[] sequence(byte[] data) {
      byte sequenceTag = 48;
      return Buffer.buffer().appendByte((byte)48).appendBytes(length(data.length)).appendBytes(data).getBytes();
   }

   public static ASN parseASN1(byte[] buffer) {
      return parseASN1(Buffer.buffer(buffer), 0);
   }

   public static ASN parseASN1(Buffer buffer) {
      return parseASN1(buffer, 0);
   }

   public static ASN parseASN1(Buffer buffer, int startPos) {
      ASNTag tag = readTag(buffer, startPos);
      ASNLength length = readLength(buffer, tag.nextPos);
      List<Object> value = readValue(buffer, length.nextPos, tag, length);
      return new ASN(tag, length, value);
   }

   private static ASNTag readTag(Buffer buffer, int startPos) {
      int pos = startPos + 1;
      int firstByte = buffer.getUnsignedByte(startPos);
      boolean tagConstructed = (firstByte & 32) > 0;
      int tagNumber = 0;
      int octet;
      if ((firstByte & 31) != 31) {
         tagNumber = firstByte & 31;
      } else {
         do {
            octet = buffer.getUnsignedByte(pos++);
            tagNumber = tagNumber * 128 + (octet & -129);
         } while((octet & 128) != 0);
      }

      return new ASNTag(firstByte, tagConstructed, tagNumber, pos);
   }

   private static ASNLength readLength(Buffer buffer, int startPos) {
      int pos = startPos + 1;
      int firstByte = buffer.getUnsignedByte(startPos);
      boolean longForm = (firstByte & 128) != 0;
      int contentLength = 0;
      if (!longForm) {
         contentLength = firstByte & -129;
      } else {
         for(int lengthOctets = firstByte & -129; pos <= startPos + lengthOctets; contentLength = contentLength * 256 + buffer.getUnsignedByte(pos++)) {
         }
      }

      return new ASNLength(longForm && contentLength == 0, contentLength, pos);
   }

   private static List readValue(Buffer buffer, int startPos, ASNTag tagObj, ASNLength lengthObj) {
      List<Object> res = new ArrayList();
      int pos = startPos;
      if (!tagObj.constructed) {
         res.add(buffer.getBytes(startPos, startPos + lengthObj.contentLength));
      } else {
         while(pos < startPos + lengthObj.contentLength) {
            ASN newObj = parseASN1(buffer, pos);
            pos = newObj.length.nextPos + newObj.length.contentLength;
            if (newObj.tag.type == 0 && !newObj.tag.constructed && newObj.tag.number == 0 && newObj.length.contentLength == 0) {
               break;
            }

            res.add(newObj);
         }
      }

      return res;
   }

   public static class ASN {
      public final ASNTag tag;
      public final List value;
      public final ASNLength length;

      private ASN(ASNTag tag, ASNLength length, List value) {
         this.tag = tag;
         this.length = length;
         this.value = value;
      }

      public byte[] binary(int index) {
         return (byte[])this.value.get(index);
      }

      public int integer(int index) {
         byte[] bytes = this.binary(index);
         if (bytes.length > 4) {
            throw new IllegalArgumentException("integer too long");
         } else {
            int result = 0;

            for(byte b : bytes) {
               result = result << 8 | b & 255;
            }

            return result;
         }
      }

      public BigInteger bigInteger(int index) {
         return new BigInteger(this.binary(index));
      }

      public ASN object(int index) {
         return (ASN)this.value.get(index);
      }

      public ASN object(int index, int type) {
         ASN object = (ASN)this.value.get(index);
         if (!object.is(type)) {
            throw new ClassCastException("Object at index(" + index + ") is not of type: " + type);
         } else {
            return object;
         }
      }

      public String oid(int index) {
         byte[] bytes = this.object(index, 6).binary(0);
         StringBuilder oid = new StringBuilder();

         for(int i = 0; i < bytes.length; ++i) {
            int uint8 = Byte.toUnsignedInt(bytes[i]);
            if (i == 0) {
               int b = uint8 % 40;
               int a = (uint8 - b) / 40;
               oid.append(a).append('.').append(b);
            } else if (uint8 < 128) {
               oid.append('.').append(uint8);
            } else {
               oid.append('.').append((uint8 - 128) * 128 + Byte.toUnsignedInt(bytes[i + 1]));
               ++i;
            }
         }

         return oid.toString();
      }

      public int length() {
         return this.value.size();
      }

      public boolean is(int number) {
         if (this.tag.constructed) {
            return this.tag.type == 32 + number;
         } else {
            return this.tag.type == number;
         }
      }
   }

   public static class ASNTag {
      public final int type;
      public final boolean constructed;
      public final int number;
      private final int nextPos;

      private ASNTag(int type, boolean constructed, int number, int nextPos) {
         this.type = type;
         this.constructed = constructed;
         this.number = number;
         this.nextPos = nextPos;
      }
   }

   public static class ASNLength {
      public final boolean indefinite;
      public final int contentLength;
      private final int nextPos;

      private ASNLength(boolean indefinite, int contentLength, int nextPos) {
         this.indefinite = indefinite;
         this.contentLength = contentLength;
         this.nextPos = nextPos;
      }
   }
}
