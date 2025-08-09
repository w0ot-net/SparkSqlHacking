package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.util.Strings;

public class TupleHash implements Xof, Digest {
   private static final byte[] N_TUPLE_HASH = Strings.toByteArray("TupleHash");
   private final CSHAKEDigest cshake;
   private final int bitLength;
   private final int outputLength;
   private boolean firstOutput;

   public TupleHash(int var1, byte[] var2) {
      this(var1, var2, var1 * 2);
   }

   public TupleHash(int var1, byte[] var2, int var3) {
      this.cshake = new CSHAKEDigest(var1, N_TUPLE_HASH, var2);
      this.bitLength = var1;
      this.outputLength = (var3 + 7) / 8;
      this.reset();
   }

   public TupleHash(TupleHash var1) {
      this.cshake = new CSHAKEDigest(var1.cshake);
      this.bitLength = this.cshake.fixedOutputLength;
      this.outputLength = this.bitLength * 2 / 8;
      this.firstOutput = var1.firstOutput;
   }

   public String getAlgorithmName() {
      return "TupleHash" + this.cshake.getAlgorithmName().substring(6);
   }

   public int getByteLength() {
      return this.cshake.getByteLength();
   }

   public int getDigestSize() {
      return this.outputLength;
   }

   public void update(byte var1) throws IllegalStateException {
      byte[] var2 = XofUtils.encode(var1);
      this.cshake.update(var2, 0, var2.length);
   }

   public void update(byte[] var1, int var2, int var3) throws DataLengthException, IllegalStateException {
      byte[] var4 = XofUtils.encode(var1, var2, var3);
      this.cshake.update(var4, 0, var4.length);
   }

   private void wrapUp(int var1) {
      byte[] var2 = XofUtils.rightEncode((long)var1 * 8L);
      this.cshake.update(var2, 0, var2.length);
      this.firstOutput = false;
   }

   public int doFinal(byte[] var1, int var2) throws DataLengthException, IllegalStateException {
      if (this.firstOutput) {
         this.wrapUp(this.getDigestSize());
      }

      int var3 = this.cshake.doFinal(var1, var2, this.getDigestSize());
      this.reset();
      return var3;
   }

   public int doFinal(byte[] var1, int var2, int var3) {
      if (this.firstOutput) {
         this.wrapUp(this.getDigestSize());
      }

      int var4 = this.cshake.doFinal(var1, var2, var3);
      this.reset();
      return var4;
   }

   public int doOutput(byte[] var1, int var2, int var3) {
      if (this.firstOutput) {
         this.wrapUp(0);
      }

      return this.cshake.doOutput(var1, var2, var3);
   }

   public void reset() {
      this.cshake.reset();
      this.firstOutput = true;
   }
}
