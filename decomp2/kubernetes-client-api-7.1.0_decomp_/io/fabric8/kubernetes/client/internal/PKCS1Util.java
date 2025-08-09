package io.fabric8.kubernetes.client.internal;

import io.fabric8.kubernetes.client.KubernetesClientException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyPairGenerator;
import java.security.Provider;
import java.security.Security;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;

class PKCS1Util {
   private PKCS1Util() {
   }

   public static RSAPrivateCrtKeySpec decodePKCS1(byte[] keyBytes) throws IOException {
      DerParser parser = new DerParser(keyBytes);
      Asn1Object sequence = parser.read();
      sequence.validateSequence();
      parser = new DerParser(sequence.getValue());
      parser.read();
      return new RSAPrivateCrtKeySpec(next(parser), next(parser), next(parser), next(parser), next(parser), next(parser), next(parser), next(parser));
   }

   private static BigInteger next(DerParser parser) throws IOException {
      return parser.read().getInteger();
   }

   public static ECPrivateKeySpec getECKeySpec(byte[] keyBytes) throws IOException {
      DerParser parser = new DerParser(keyBytes);
      Asn1Object sequence = parser.read();
      if (sequence.type != 16) {
         throw new KubernetesClientException("Invalid DER: not a sequence");
      } else {
         parser = new DerParser(sequence.value);
         Asn1Object version = parser.read();
         if (version.type != 2) {
            throw new KubernetesClientException(String.format("Invalid DER: 'version' field must be of type INTEGER (2) but found type `%d`", version.type));
         } else if (version.getInteger().intValue() != 1) {
            throw new KubernetesClientException(String.format("Invalid DER: expected 'version' field to have value '1' but found '%d'", version.getInteger().intValue()));
         } else {
            byte[] privateValue = parser.read().getValue();
            parser = new DerParser(parser.read().getValue());
            Asn1Object params = parser.read();
            if (params.type != 6) {
               throw new KubernetesClientException(String.format("Invalid DER: expected to find an OBJECT_IDENTIFIER (6) in 'parameters' but found type '%d'", params.type));
            } else {
               byte[] namedCurveOid = params.getValue();
               ECParameterSpec spec = getECParameterSpec(oidToString(namedCurveOid));
               return new ECPrivateKeySpec(new BigInteger(1, privateValue), spec);
            }
         }
      }
   }

   private static ECParameterSpec getECParameterSpec(String curveName) {
      Provider[] providers = Security.getProviders();
      GeneralSecurityException ex = null;

      for(Provider provider : providers) {
         try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", provider);
            keyPairGenerator.initialize(new ECGenParameterSpec(curveName));
            ECPublicKey publicKey = (ECPublicKey)keyPairGenerator.generateKeyPair().getPublic();
            return publicKey.getParams();
         } catch (GeneralSecurityException e) {
            ex = e;
         }
      }

      boolean bcProvider = Security.getProvider("BC") != null || Security.getProvider("BCFIPS") != null;
      throw new KubernetesClientException("Cannot determine EC parameter spec for curve name/OID" + (bcProvider ? "" : ". A BouncyCastle provider is not installed, it may be needed for this EC algorithm."), ex);
   }

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

   static class DerParser {
      private static final int SEQUENCE = 16;
      private static final int INTEGER = 2;
      private static final int OBJECT_IDENTIFIER = 6;
      private InputStream in;

      DerParser(byte[] bytes) {
         this.in = new ByteArrayInputStream(bytes);
      }

      Asn1Object read() throws IOException {
         int tag = this.in.read();
         if (tag == -1) {
            throw new IOException("Invalid DER: stream too short, missing tag");
         } else {
            int length = this.getLength();
            byte[] value = new byte[length];
            if (this.in.read(value) < length) {
               throw new IOException("Invalid DER: stream too short, missing value");
            } else {
               return new Asn1Object(tag, value);
            }
         }
      }

      private int getLength() throws IOException {
         int i = this.in.read();
         if (i == -1) {
            throw new IOException("Invalid DER: length missing");
         } else if ((i & -128) == 0) {
            return i;
         } else {
            int num = i & 127;
            if (i < 255 && num <= 4) {
               byte[] bytes = new byte[num];
               if (this.in.read(bytes) < num) {
                  throw new IOException("Invalid DER: length too short");
               } else {
                  return (new BigInteger(1, bytes)).intValue();
               }
            } else {
               throw new IOException("Invalid DER: length field too big (" + i + ")");
            }
         }
      }
   }

   static class Asn1Object {
      private final int type;
      private final byte[] value;
      private final int tag;

      public Asn1Object(int tag, byte[] value) {
         this.tag = tag;
         this.type = tag & 31;
         this.value = value;
      }

      public byte[] getValue() {
         return this.value;
      }

      BigInteger getInteger() throws IOException {
         if (this.type != 2) {
            throw new IOException("Invalid DER: object is not integer");
         } else {
            return new BigInteger(this.value);
         }
      }

      void validateSequence() throws IOException {
         if (this.type != 16) {
            throw new IOException("Invalid DER: not a sequence");
         } else if ((this.tag & 32) != 32) {
            throw new IOException("Invalid DER: can't parse primitive entity");
         }
      }
   }
}
