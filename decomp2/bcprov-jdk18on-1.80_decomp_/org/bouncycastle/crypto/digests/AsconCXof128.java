package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.util.Pack;

public class AsconCXof128 extends AsconBaseDigest implements Xof {
   private boolean m_squeezing;
   private final long z0;
   private final long z1;
   private final long z2;
   private final long z3;
   private final long z4;

   public AsconCXof128() {
      this(new byte[0], 0, 0);
   }

   public AsconCXof128(byte[] var1) {
      this(var1, 0, var1.length);
   }

   public AsconCXof128(byte[] var1, int var2, int var3) {
      this.m_squeezing = false;
      if (var2 + var3 > var1.length) {
         throw new DataLengthException("input buffer too short");
      } else if (var3 > 256) {
         throw new DataLengthException("customized string is too long");
      } else {
         this.initState(var1, var2, var3);
         this.z0 = this.x0;
         this.z1 = this.x1;
         this.z2 = this.x2;
         this.z3 = this.x3;
         this.z4 = this.x4;
      }
   }

   public void update(byte var1) {
      if (this.m_squeezing) {
         throw new IllegalArgumentException("attempt to absorb while squeezing");
      } else {
         super.update(var1);
      }
   }

   public void update(byte[] var1, int var2, int var3) {
      if (this.m_squeezing) {
         throw new IllegalArgumentException("attempt to absorb while squeezing");
      } else {
         super.update(var1, var2, var3);
      }
   }

   protected long pad(int var1) {
      return 1L << (var1 << 3);
   }

   protected long loadBytes(byte[] var1, int var2) {
      return Pack.littleEndianToLong(var1, var2);
   }

   protected long loadBytes(byte[] var1, int var2, int var3) {
      return Pack.littleEndianToLong(var1, var2, var3);
   }

   protected void setBytes(long var1, byte[] var3, int var4) {
      Pack.longToLittleEndian(var1, var3, var4);
   }

   protected void setBytes(long var1, byte[] var3, int var4, int var5) {
      Pack.longToLittleEndian(var1, var3, var4, var5);
   }

   protected void padAndAbsorb() {
      this.m_squeezing = true;
      super.padAndAbsorb();
   }

   public String getAlgorithmName() {
      return "Ascon-CXOF128";
   }

   public int doOutput(byte[] var1, int var2, int var3) {
      if (32 + var2 > var1.length) {
         throw new OutputLengthException("output buffer is too short");
      } else {
         this.padAndAbsorb();
         this.squeeze(var1, var2, var3);
         return var3;
      }
   }

   public int doFinal(byte[] var1, int var2, int var3) {
      int var4 = this.doOutput(var1, var2, var3);
      this.reset();
      return var4;
   }

   public void reset() {
      super.reset();
      this.m_squeezing = false;
      this.x0 = this.z0;
      this.x1 = this.z1;
      this.x2 = this.z2;
      this.x3 = this.z3;
      this.x4 = this.z4;
   }

   private void initState(byte[] var1, int var2, int var3) {
      this.x0 = 7445901275803737603L;
      this.x1 = 4886737088792722364L;
      this.x2 = -1616759365661982283L;
      this.x3 = 3076320316797452470L;
      this.x4 = -8124743304765850554L;
      long var4 = (long)var3 << 3;
      Pack.longToLittleEndian(var4, this.m_buf, 0);
      this.p(12);
      this.update(var1, var2, var3);
      this.padAndAbsorb();
      this.m_squeezing = false;
   }
}
