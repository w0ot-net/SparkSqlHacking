package io.vertx.core.net.impl.pkcs1;

import io.vertx.core.VertxException;
import io.vertx.core.buffer.Buffer;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyPairGenerator;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.util.Arrays;

public class PrivateKeyParser {
   private static final byte[] OID_RSA_PUBLIC_KEY = new byte[]{42, -122, 72, -122, -9, 13, 1, 1, 1};
   private static final byte[] OID_EC_PUBLIC_KEY = new byte[]{42, -122, 72, -50, 61, 2, 1};

   private static String oidToString(byte[] oid) {
      StringBuilder result = new StringBuilder();
      int value = oid[0] & 255;
      result.append(value / 40).append(".").append(value % 40);

      for(int index = 1; index < oid.length; ++index) {
         byte bValue = oid[index];
         if (bValue < 0) {
            value = bValue & 127;
            ++index;
            if (index == oid.length) {
               throw new IllegalArgumentException("Invalid OID");
            }

            value <<= 7;
            value |= oid[index] & 127;
            result.append(".").append(value);
         } else {
            result.append(".").append(bValue);
         }
      }

      return result.toString();
   }

   private static ECParameterSpec getECParameterSpec(String curveName) throws VertxException {
      try {
         KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
         keyPairGenerator.initialize(new ECGenParameterSpec(curveName));
         ECPublicKey publicKey = (ECPublicKey)keyPairGenerator.generateKeyPair().getPublic();
         return publicKey.getParams();
      } catch (GeneralSecurityException e) {
         throw new VertxException("Cannot determine EC parameter spec for curve name/OID", e);
      }
   }

   public static String getPKCS8EncodedKeyAlgorithm(byte[] encodedKey) {
      DerParser parser = new DerParser(encodedKey);
      Asn1Object sequence = parser.read();
      if (sequence.getType() != 16) {
         throw new VertxException("Invalid PKCS8 encoding: not a sequence");
      } else {
         parser = sequence.getParser();
         BigInteger version = parser.read().getInteger();
         if (version.intValue() != 0) {
            throw new VertxException("Unsupported version, expected 0 but found " + version.intValue());
         } else {
            sequence = parser.read();
            if (sequence.getType() != 16) {
               throw new VertxException("Invalid PKCS8 encoding: could not read Algorithm Identifier");
            } else {
               parser = sequence.getParser();
               byte[] algorithmIdentifier = parser.read().getObjectIdentifier();
               if (Arrays.equals(OID_RSA_PUBLIC_KEY, algorithmIdentifier)) {
                  return "RSA";
               } else if (Arrays.equals(OID_EC_PUBLIC_KEY, algorithmIdentifier)) {
                  return "EC";
               } else {
                  throw new VertxException("Unsupported algorithm identifier");
               }
            }
         }
      }
   }

   public static ECPrivateKeySpec getECKeySpec(byte[] keyBytes) throws VertxException {
      DerParser parser = new DerParser(keyBytes);
      Asn1Object sequence = parser.read();
      if (sequence.getType() != 16) {
         throw new VertxException("Invalid DER: not a sequence");
      } else {
         parser = sequence.getParser();
         Asn1Object version = parser.read();
         if (version.getType() != 2) {
            throw new VertxException(String.format("Invalid DER: 'version' field must be of type INTEGER (2) but found type `%d`", version.getType()));
         } else if (version.getInteger().intValue() != 1) {
            throw new VertxException(String.format("Invalid DER: expected 'version' field to have value '1' but found '%d'", version.getInteger().intValue()));
         } else {
            byte[] privateValue = parser.read().getValue();
            parser = parser.read().getParser();
            Asn1Object params = parser.read();
            if (params.getType() != 6) {
               throw new VertxException(String.format("Invalid DER: expected to find an OBJECT_IDENTIFIER (6) in 'parameters' but found type '%d'", params.getType()));
            } else {
               byte[] namedCurveOid = params.getValue();
               ECParameterSpec spec = getECParameterSpec(oidToString(namedCurveOid));
               return new ECPrivateKeySpec(new BigInteger(1, privateValue), spec);
            }
         }
      }
   }

   public static RSAPrivateCrtKeySpec getRSAKeySpec(byte[] keyBytes) throws VertxException {
      DerParser parser = new DerParser(keyBytes);
      Asn1Object sequence = parser.read();
      if (sequence.getType() != 16) {
         throw new VertxException("Invalid DER: not a sequence");
      } else {
         parser = sequence.getParser();
         parser.read();
         BigInteger modulus = parser.read().getInteger();
         BigInteger publicExp = parser.read().getInteger();
         BigInteger privateExp = parser.read().getInteger();
         BigInteger prime1 = parser.read().getInteger();
         BigInteger prime2 = parser.read().getInteger();
         BigInteger exp1 = parser.read().getInteger();
         BigInteger exp2 = parser.read().getInteger();
         BigInteger crtCoef = parser.read().getInteger();
         return new RSAPrivateCrtKeySpec(modulus, publicExp, privateExp, prime1, prime2, exp1, exp2, crtCoef);
      }
   }

   static class DerParser {
      private static final int UNIVERSAL = 0;
      private static final int APPLICATION = 64;
      private static final int CONTEXT = 128;
      private static final int PRIVATE = 192;
      private static final int CONSTRUCTED = 32;
      private static final int ANY = 0;
      private static final int BOOLEAN = 1;
      private static final int INTEGER = 2;
      private static final int BIT_STRING = 3;
      private static final int OCTET_STRING = 4;
      private static final int NULL = 5;
      private static final int OBJECT_IDENTIFIER = 6;
      private static final int REAL = 9;
      private static final int ENUMERATED = 10;
      private static final int SEQUENCE = 16;
      private static final int SET = 17;
      private static final int NUMERIC_STRING = 18;
      private static final int PRINTABLE_STRING = 19;
      private static final int VIDEOTEX_STRING = 21;
      private static final int IA5_STRING = 22;
      private static final int GRAPHIC_STRING = 25;
      private static final int ISO646_STRING = 26;
      private static final int GENERAL_STRING = 27;
      private static final int UTF8_STRING = 12;
      private static final int UNIVERSAL_STRING = 28;
      private static final int BMP_STRING = 30;
      private static final int UTC_TIME = 23;
      private Buffer in;
      private int pos;

      DerParser(Buffer in) throws VertxException {
         this.in = in;
      }

      DerParser(byte[] bytes) throws VertxException {
         this(Buffer.buffer(bytes));
      }

      private int readByte() throws VertxException {
         if (this.pos + 1 >= this.in.length()) {
            throw new VertxException("Invalid DER: stream too short, missing tag");
         } else {
            return this.in.getUnsignedByte(this.pos++);
         }
      }

      private byte[] readBytes(int len) throws VertxException {
         if (this.pos + len > this.in.length()) {
            throw new VertxException("Invalid DER: stream too short, missing tag");
         } else {
            Buffer s = this.in.slice(this.pos, this.pos + len);
            this.pos += len;
            return s.getBytes();
         }
      }

      public Asn1Object read() throws VertxException {
         int tag = this.readByte();
         int length = this.getLength();
         byte[] value = this.readBytes(length);
         return new Asn1Object(tag, length, value);
      }

      private int getLength() throws VertxException {
         int i = this.readByte();
         if ((i & -128) == 0) {
            return i;
         } else {
            int num = i & 127;
            if (i < 255 && num <= 4) {
               byte[] bytes = this.readBytes(num);
               return (new BigInteger(1, bytes)).intValue();
            } else {
               throw new VertxException("Invalid DER: length field too big (" + i + ")");
            }
         }
      }
   }

   static class Asn1Object {
      protected final int type;
      protected final int length;
      protected final byte[] value;
      protected final int tag;

      public Asn1Object(int tag, int length, byte[] value) {
         this.tag = tag;
         this.type = tag & 31;
         this.length = length;
         this.value = value;
      }

      public int getType() {
         return this.type;
      }

      public int getLength() {
         return this.length;
      }

      public byte[] getValue() {
         return this.value;
      }

      public boolean isConstructed() {
         return (this.tag & 32) == 32;
      }

      public DerParser getParser() throws VertxException {
         if (!this.isConstructed()) {
            throw new VertxException("Invalid DER: can't parse primitive entity");
         } else {
            return new DerParser(this.value);
         }
      }

      public BigInteger getInteger() throws VertxException {
         if (this.type != 2) {
            throw new VertxException("Invalid DER: object is not integer");
         } else {
            return new BigInteger(this.value);
         }
      }

      public byte[] getObjectIdentifier() throws VertxException {
         switch (this.type) {
            case 6:
               return this.value;
            default:
               throw new VertxException("Invalid DER: object is not an Object Identifier");
         }
      }

      public String getString() throws VertxException {
         String encoding;
         switch (this.type) {
            case 12:
               encoding = "UTF-8";
               break;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 20:
            case 23:
            case 24:
            case 29:
            default:
               throw new VertxException("Invalid DER: object is not a string");
            case 18:
            case 19:
            case 21:
            case 22:
            case 25:
            case 26:
            case 27:
               encoding = "ISO-8859-1";
               break;
            case 28:
               throw new VertxException("Invalid DER: can't handle UCS-4 string");
            case 30:
               encoding = "UTF-16BE";
         }

         try {
            return new String(this.value, encoding);
         } catch (UnsupportedEncodingException e) {
            throw new VertxException(e);
         }
      }
   }
}
