package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.CSHAKEDigest;
import org.bouncycastle.crypto.digests.XofUtils;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

public class KMAC implements Mac, Xof {
   private static final byte[] padding = new byte[100];
   private final CSHAKEDigest cshake;
   private final int bitLength;
   private final int outputLength;
   private byte[] key;
   private boolean initialised;
   private boolean firstOutput;

   public KMAC(int var1, byte[] var2) {
      this.cshake = new CSHAKEDigest(var1, Strings.toByteArray("KMAC"), var2);
      this.bitLength = var1;
      this.outputLength = var1 * 2 / 8;
   }

   public void init(CipherParameters var1) throws IllegalArgumentException {
      KeyParameter var2 = (KeyParameter)var1;
      this.key = Arrays.clone(var2.getKey());
      this.initialised = true;
      this.reset();
   }

   public String getAlgorithmName() {
      return "KMAC" + this.cshake.getAlgorithmName().substring(6);
   }

   public int getByteLength() {
      return this.cshake.getByteLength();
   }

   public int getMacSize() {
      return this.outputLength;
   }

   public int getDigestSize() {
      return this.outputLength;
   }

   public void update(byte var1) throws IllegalStateException {
      if (!this.initialised) {
         throw new IllegalStateException("KMAC not initialized");
      } else {
         this.cshake.update(var1);
      }
   }

   public void update(byte[] var1, int var2, int var3) throws DataLengthException, IllegalStateException {
      if (!this.initialised) {
         throw new IllegalStateException("KMAC not initialized");
      } else {
         this.cshake.update(var1, var2, var3);
      }
   }

   public int doFinal(byte[] var1, int var2) throws DataLengthException, IllegalStateException {
      if (this.firstOutput) {
         if (!this.initialised) {
            throw new IllegalStateException("KMAC not initialized");
         }

         byte[] var3 = XofUtils.rightEncode((long)(this.getMacSize() * 8));
         this.cshake.update(var3, 0, var3.length);
      }

      int var4 = this.cshake.doFinal(var1, var2, this.getMacSize());
      this.reset();
      return var4;
   }

   public int doFinal(byte[] var1, int var2, int var3) {
      if (this.firstOutput) {
         if (!this.initialised) {
            throw new IllegalStateException("KMAC not initialized");
         }

         byte[] var4 = XofUtils.rightEncode((long)(var3 * 8));
         this.cshake.update(var4, 0, var4.length);
      }

      int var5 = this.cshake.doFinal(var1, var2, var3);
      this.reset();
      return var5;
   }

   public int doOutput(byte[] var1, int var2, int var3) {
      if (this.firstOutput) {
         if (!this.initialised) {
            throw new IllegalStateException("KMAC not initialized");
         }

         byte[] var4 = XofUtils.rightEncode(0L);
         this.cshake.update(var4, 0, var4.length);
         this.firstOutput = false;
      }

      return this.cshake.doOutput(var1, var2, var3);
   }

   public void reset() {
      this.cshake.reset();
      if (this.key != null) {
         if (this.bitLength == 128) {
            this.bytePad(this.key, 168);
         } else {
            this.bytePad(this.key, 136);
         }
      }

      this.firstOutput = true;
   }

   private void bytePad(byte[] var1, int var2) {
      byte[] var3 = XofUtils.leftEncode((long)var2);
      this.update(var3, 0, var3.length);
      byte[] var4 = encode(var1);
      this.update(var4, 0, var4.length);
      int var5 = var2 - (var3.length + var4.length) % var2;
      if (var5 > 0 && var5 != var2) {
         while(var5 > padding.length) {
            this.update(padding, 0, padding.length);
            var5 -= padding.length;
         }

         this.update(padding, 0, var5);
      }

   }

   private static byte[] encode(byte[] var0) {
      return Arrays.concatenate(XofUtils.leftEncode((long)(var0.length * 8)), var0);
   }
}
