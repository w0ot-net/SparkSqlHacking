package com.google.crypto.tink.subtle.prf;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.internal.EnumTypeProtoConverter;
import com.google.crypto.tink.prf.HkdfPrfKey;
import com.google.crypto.tink.prf.HkdfPrfParameters;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.Enums;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Immutable
@AccessesPartialKey
public class HkdfStreamingPrf implements StreamingPrf {
   private static final EnumTypeProtoConverter HASH_TYPE_CONVERTER;
   private final Enums.HashType hashType;
   private final byte[] ikm;
   private final byte[] salt;

   private static String getJavaxHmacName(Enums.HashType hashType) throws GeneralSecurityException {
      switch (hashType) {
         case SHA1:
            return "HmacSha1";
         case SHA256:
            return "HmacSha256";
         case SHA384:
            return "HmacSha384";
         case SHA512:
            return "HmacSha512";
         default:
            throw new GeneralSecurityException("No getJavaxHmacName for given hash " + hashType + " known");
      }
   }

   public HkdfStreamingPrf(final Enums.HashType hashType, final byte[] ikm, final byte[] salt) {
      this.hashType = hashType;
      this.ikm = Arrays.copyOf(ikm, ikm.length);
      this.salt = Arrays.copyOf(salt, salt.length);
   }

   public static StreamingPrf create(HkdfPrfKey key) throws GeneralSecurityException {
      Bytes saltFromKey = key.getParameters().getSalt();
      return new HkdfStreamingPrf((Enums.HashType)HASH_TYPE_CONVERTER.toProtoEnum(key.getParameters().getHashType()), key.getKeyBytes().toByteArray(InsecureSecretKeyAccess.get()), saltFromKey == null ? new byte[0] : saltFromKey.toByteArray());
   }

   public InputStream computePrf(final byte[] input) {
      return new HkdfInputStream(input);
   }

   static {
      HASH_TYPE_CONVERTER = EnumTypeProtoConverter.builder().add(Enums.HashType.SHA1, HkdfPrfParameters.HashType.SHA1).add(Enums.HashType.SHA224, HkdfPrfParameters.HashType.SHA224).add(Enums.HashType.SHA256, HkdfPrfParameters.HashType.SHA256).add(Enums.HashType.SHA384, HkdfPrfParameters.HashType.SHA384).add(Enums.HashType.SHA512, HkdfPrfParameters.HashType.SHA512).build();
   }

   private class HkdfInputStream extends InputStream {
      private final byte[] input;
      private Mac mac;
      private byte[] prk;
      private ByteBuffer buffer;
      private int ctr = -1;

      public HkdfInputStream(final byte[] input) {
         this.input = Arrays.copyOf(input, input.length);
      }

      private void initialize() throws GeneralSecurityException, IOException {
         try {
            this.mac = (Mac)EngineFactory.MAC.getInstance(HkdfStreamingPrf.getJavaxHmacName(HkdfStreamingPrf.this.hashType));
         } catch (GeneralSecurityException e) {
            throw new IOException("Creating HMac failed", e);
         }

         if (HkdfStreamingPrf.this.salt != null && HkdfStreamingPrf.this.salt.length != 0) {
            this.mac.init(new SecretKeySpec(HkdfStreamingPrf.this.salt, HkdfStreamingPrf.getJavaxHmacName(HkdfStreamingPrf.this.hashType)));
         } else {
            this.mac.init(new SecretKeySpec(new byte[this.mac.getMacLength()], HkdfStreamingPrf.getJavaxHmacName(HkdfStreamingPrf.this.hashType)));
         }

         this.mac.update(HkdfStreamingPrf.this.ikm);
         this.prk = this.mac.doFinal();
         this.buffer = ByteBuffer.allocateDirect(0);
         this.buffer.mark();
         this.ctr = 0;
      }

      private void updateBuffer() throws GeneralSecurityException, IOException {
         this.mac.init(new SecretKeySpec(this.prk, HkdfStreamingPrf.getJavaxHmacName(HkdfStreamingPrf.this.hashType)));
         this.buffer.reset();
         this.mac.update(this.buffer);
         this.mac.update(this.input);
         ++this.ctr;
         this.mac.update((byte)this.ctr);
         this.buffer = ByteBuffer.wrap(this.mac.doFinal());
         this.buffer.mark();
      }

      public int read() throws IOException {
         byte[] oneByte = new byte[1];
         int ret = this.read(oneByte, 0, 1);
         if (ret == 1) {
            return oneByte[0] & 255;
         } else if (ret == -1) {
            return ret;
         } else {
            throw new IOException("Reading failed");
         }
      }

      public int read(byte[] dst) throws IOException {
         return this.read(dst, 0, dst.length);
      }

      public int read(byte[] b, int off, int len) throws IOException {
         int totalRead = 0;

         try {
            if (this.ctr == -1) {
               this.initialize();
            }

            while(totalRead < len) {
               if (!this.buffer.hasRemaining()) {
                  if (this.ctr == 255) {
                     return totalRead;
                  }

                  this.updateBuffer();
               }

               int toRead = Math.min(len - totalRead, this.buffer.remaining());
               this.buffer.get(b, off, toRead);
               off += toRead;
               totalRead += toRead;
            }

            return totalRead;
         } catch (GeneralSecurityException e) {
            this.mac = null;
            throw new IOException("HkdfInputStream failed", e);
         }
      }
   }
}
