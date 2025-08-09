package com.google.crypto.tink.mac.internal;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.mac.AesCmacKey;
import com.google.crypto.tink.mac.AesCmacParameters;
import com.google.crypto.tink.mac.ChunkedMacComputation;
import com.google.crypto.tink.subtle.Bytes;
import com.google.crypto.tink.subtle.EngineFactory;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

@AccessesPartialKey
final class ChunkedAesCmacComputation implements ChunkedMacComputation {
   private static final byte[] FORMAT_VERSION = new byte[]{0};
   private final Cipher aes;
   private final AesCmacKey key;
   private final byte[] subKey1;
   private final byte[] subKey2;
   private final ByteBuffer localStash;
   private final ByteBuffer x;
   private final ByteBuffer y;
   private boolean finalized = false;

   ChunkedAesCmacComputation(AesCmacKey key) throws GeneralSecurityException {
      this.key = key;
      this.aes = (Cipher)EngineFactory.CIPHER.getInstance("AES/ECB/NoPadding");
      this.aes.init(1, new SecretKeySpec(this.key.getAesKey().toByteArray(InsecureSecretKeyAccess.get()), "AES"));
      byte[] zeroes = new byte[16];
      byte[] l = this.aes.doFinal(zeroes);
      this.subKey1 = AesUtil.dbl(l);
      this.subKey2 = AesUtil.dbl(this.subKey1);
      this.localStash = ByteBuffer.allocate(16);
      this.x = ByteBuffer.allocate(16);
      this.y = ByteBuffer.allocate(16);
   }

   private void munch(ByteBuffer data) throws GeneralSecurityException {
      this.y.rewind();
      this.x.rewind();
      Bytes.xor(this.y, this.x, data, 16);
      this.y.rewind();
      this.x.rewind();
      this.aes.doFinal(this.y, this.x);
   }

   public void update(ByteBuffer data) throws GeneralSecurityException {
      if (this.finalized) {
         throw new IllegalStateException("Can not update after computing the MAC tag. Please create a new object.");
      } else {
         if (this.localStash.remaining() != 16) {
            int bytesToCopy = Math.min(this.localStash.remaining(), data.remaining());

            for(int i = 0; i < bytesToCopy; ++i) {
               this.localStash.put(data.get());
            }
         }

         if (this.localStash.remaining() == 0 && data.remaining() > 0) {
            this.localStash.rewind();
            this.munch(this.localStash);
            this.localStash.rewind();
         }

         while(data.remaining() > 16) {
            this.munch(data);
         }

         this.localStash.put(data);
      }
   }

   public byte[] computeMac() throws GeneralSecurityException {
      if (this.finalized) {
         throw new IllegalStateException("Can not compute after computing the MAC tag. Please create a new object.");
      } else {
         if (this.key.getParameters().getVariant() == AesCmacParameters.Variant.LEGACY) {
            this.update(ByteBuffer.wrap(FORMAT_VERSION));
         }

         this.finalized = true;
         byte[] mLast;
         if (this.localStash.remaining() > 0) {
            byte[] lastChunkToPad = Arrays.copyOf(this.localStash.array(), this.localStash.position());
            mLast = Bytes.xor(AesUtil.cmacPad(lastChunkToPad), this.subKey2);
         } else {
            mLast = Bytes.xor(this.localStash.array(), 0, this.subKey1, 0, 16);
         }

         return Bytes.concat(this.key.getOutputPrefix().toByteArray(), Arrays.copyOf(this.aes.doFinal(Bytes.xor(mLast, this.x.array())), this.key.getParameters().getCryptographicTagSizeBytes()));
      }
   }
}
