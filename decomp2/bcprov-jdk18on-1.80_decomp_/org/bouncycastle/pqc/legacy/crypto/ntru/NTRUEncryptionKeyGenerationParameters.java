package org.bouncycastle.pqc.legacy.crypto.ntru;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Arrays;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.util.DigestFactory;

public class NTRUEncryptionKeyGenerationParameters extends KeyGenerationParameters implements Cloneable {
   public static final NTRUEncryptionKeyGenerationParameters EES1087EP2 = new NTRUEncryptionKeyGenerationParameters(1087, 2048, 120, 120, 256, 13, 25, 14, true, new byte[]{0, 6, 3}, true, false, new SHA512Digest());
   public static final NTRUEncryptionKeyGenerationParameters EES1171EP1 = new NTRUEncryptionKeyGenerationParameters(1171, 2048, 106, 106, 256, 13, 20, 15, true, new byte[]{0, 6, 4}, true, false, new SHA512Digest());
   public static final NTRUEncryptionKeyGenerationParameters EES1499EP1 = new NTRUEncryptionKeyGenerationParameters(1499, 2048, 79, 79, 256, 13, 17, 19, true, new byte[]{0, 6, 5}, true, false, new SHA512Digest());
   public static final NTRUEncryptionKeyGenerationParameters APR2011_439 = new NTRUEncryptionKeyGenerationParameters(439, 2048, 146, 130, 128, 9, 32, 9, true, new byte[]{0, 7, 101}, true, false, new SHA256Digest());
   public static final NTRUEncryptionKeyGenerationParameters APR2011_439_FAST = new NTRUEncryptionKeyGenerationParameters(439, 2048, 9, 8, 5, 130, 128, 9, 32, 9, true, new byte[]{0, 7, 101}, true, true, new SHA256Digest());
   public static final NTRUEncryptionKeyGenerationParameters APR2011_743 = new NTRUEncryptionKeyGenerationParameters(743, 2048, 248, 220, 256, 10, 27, 14, true, new byte[]{0, 7, 105}, false, false, new SHA512Digest());
   public static final NTRUEncryptionKeyGenerationParameters APR2011_743_FAST = new NTRUEncryptionKeyGenerationParameters(743, 2048, 11, 11, 15, 220, 256, 10, 27, 14, true, new byte[]{0, 7, 105}, false, true, new SHA512Digest());
   public int N;
   public int q;
   public int df;
   public int df1;
   public int df2;
   public int df3;
   public int dr;
   public int dr1;
   public int dr2;
   public int dr3;
   public int dg;
   int llen;
   public int maxMsgLenBytes;
   public int db;
   public int bufferLenBits;
   int bufferLenTrits;
   public int dm0;
   public int pkLen;
   public int c;
   public int minCallsR;
   public int minCallsMask;
   public boolean hashSeed;
   public byte[] oid;
   public boolean sparse;
   public boolean fastFp;
   public int polyType;
   public Digest hashAlg;

   public NTRUEncryptionKeyGenerationParameters(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, boolean var9, byte[] var10, boolean var11, boolean var12, Digest var13, SecureRandom var14) {
      super(null != var14 ? var14 : CryptoServicesRegistrar.getSecureRandom(), var5);
      this.N = var1;
      this.q = var2;
      this.df = var3;
      this.db = var5;
      this.dm0 = var4;
      this.c = var6;
      this.minCallsR = var7;
      this.minCallsMask = var8;
      this.hashSeed = var9;
      this.oid = var10;
      this.sparse = var11;
      this.fastFp = var12;
      this.polyType = 0;
      this.hashAlg = var13;
      this.init();
   }

   public NTRUEncryptionKeyGenerationParameters(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, boolean var9, byte[] var10, boolean var11, boolean var12, Digest var13) {
      this(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, (SecureRandom)null);
   }

   public NTRUEncryptionKeyGenerationParameters(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, boolean var11, byte[] var12, boolean var13, boolean var14, Digest var15, SecureRandom var16) {
      super(null != var16 ? var16 : CryptoServicesRegistrar.getSecureRandom(), var7);
      this.N = var1;
      this.q = var2;
      this.df1 = var3;
      this.df2 = var4;
      this.df3 = var5;
      this.db = var7;
      this.dm0 = var6;
      this.c = var8;
      this.minCallsR = var9;
      this.minCallsMask = var10;
      this.hashSeed = var11;
      this.oid = var12;
      this.sparse = var13;
      this.fastFp = var14;
      this.polyType = 1;
      this.hashAlg = var15;
      this.init();
   }

   public NTRUEncryptionKeyGenerationParameters(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, boolean var11, byte[] var12, boolean var13, boolean var14, Digest var15) {
      this(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, (SecureRandom)null);
   }

   private void init() {
      this.dr = this.df;
      this.dr1 = this.df1;
      this.dr2 = this.df2;
      this.dr3 = this.df3;
      this.dg = this.N / 3;
      this.llen = 1;
      this.maxMsgLenBytes = this.N * 3 / 2 / 8 - this.llen - this.db / 8 - 1;
      this.bufferLenBits = (this.N * 3 / 2 + 7) / 8 * 8 + 1;
      this.bufferLenTrits = this.N - 1;
      this.pkLen = this.db;
   }

   public NTRUEncryptionKeyGenerationParameters(InputStream var1) throws IOException {
      super(CryptoServicesRegistrar.getSecureRandom(), -1);
      DataInputStream var2 = new DataInputStream(var1);
      this.N = var2.readInt();
      this.q = var2.readInt();
      this.df = var2.readInt();
      this.df1 = var2.readInt();
      this.df2 = var2.readInt();
      this.df3 = var2.readInt();
      this.db = var2.readInt();
      this.dm0 = var2.readInt();
      this.c = var2.readInt();
      this.minCallsR = var2.readInt();
      this.minCallsMask = var2.readInt();
      this.hashSeed = var2.readBoolean();
      this.oid = new byte[3];
      var2.readFully(this.oid);
      this.sparse = var2.readBoolean();
      this.fastFp = var2.readBoolean();
      this.polyType = var2.read();
      String var3 = var2.readUTF();
      if ("SHA-512".equals(var3)) {
         this.hashAlg = new SHA512Digest();
      } else if ("SHA-256".equals(var3)) {
         this.hashAlg = new SHA256Digest();
      }

      this.init();
   }

   public NTRUEncryptionParameters getEncryptionParameters() {
      return this.polyType == 0 ? new NTRUEncryptionParameters(this.N, this.q, this.df, this.dm0, this.db, this.c, this.minCallsR, this.minCallsMask, this.hashSeed, this.oid, this.sparse, this.fastFp, DigestFactory.cloneDigest(this.hashAlg)) : new NTRUEncryptionParameters(this.N, this.q, this.df1, this.df2, this.df3, this.dm0, this.db, this.c, this.minCallsR, this.minCallsMask, this.hashSeed, this.oid, this.sparse, this.fastFp, DigestFactory.cloneDigest(this.hashAlg));
   }

   public NTRUEncryptionKeyGenerationParameters clone() {
      return this.polyType == 0 ? new NTRUEncryptionKeyGenerationParameters(this.N, this.q, this.df, this.dm0, this.db, this.c, this.minCallsR, this.minCallsMask, this.hashSeed, this.oid, this.sparse, this.fastFp, DigestFactory.cloneDigest(this.hashAlg)) : new NTRUEncryptionKeyGenerationParameters(this.N, this.q, this.df1, this.df2, this.df3, this.dm0, this.db, this.c, this.minCallsR, this.minCallsMask, this.hashSeed, this.oid, this.sparse, this.fastFp, DigestFactory.cloneDigest(this.hashAlg));
   }

   public int getMaxMessageLength() {
      return this.maxMsgLenBytes;
   }

   public void writeTo(OutputStream var1) throws IOException {
      DataOutputStream var2 = new DataOutputStream(var1);
      var2.writeInt(this.N);
      var2.writeInt(this.q);
      var2.writeInt(this.df);
      var2.writeInt(this.df1);
      var2.writeInt(this.df2);
      var2.writeInt(this.df3);
      var2.writeInt(this.db);
      var2.writeInt(this.dm0);
      var2.writeInt(this.c);
      var2.writeInt(this.minCallsR);
      var2.writeInt(this.minCallsMask);
      var2.writeBoolean(this.hashSeed);
      var2.write(this.oid);
      var2.writeBoolean(this.sparse);
      var2.writeBoolean(this.fastFp);
      var2.write(this.polyType);
      var2.writeUTF(this.hashAlg.getAlgorithmName());
   }

   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.N;
      var1 = 31 * var1 + this.bufferLenBits;
      var1 = 31 * var1 + this.bufferLenTrits;
      var1 = 31 * var1 + this.c;
      var1 = 31 * var1 + this.db;
      var1 = 31 * var1 + this.df;
      var1 = 31 * var1 + this.df1;
      var1 = 31 * var1 + this.df2;
      var1 = 31 * var1 + this.df3;
      var1 = 31 * var1 + this.dg;
      var1 = 31 * var1 + this.dm0;
      var1 = 31 * var1 + this.dr;
      var1 = 31 * var1 + this.dr1;
      var1 = 31 * var1 + this.dr2;
      var1 = 31 * var1 + this.dr3;
      var1 = 31 * var1 + (this.fastFp ? 1231 : 1237);
      var1 = 31 * var1 + (this.hashAlg == null ? 0 : this.hashAlg.getAlgorithmName().hashCode());
      var1 = 31 * var1 + (this.hashSeed ? 1231 : 1237);
      var1 = 31 * var1 + this.llen;
      var1 = 31 * var1 + this.maxMsgLenBytes;
      var1 = 31 * var1 + this.minCallsMask;
      var1 = 31 * var1 + this.minCallsR;
      var1 = 31 * var1 + Arrays.hashCode(this.oid);
      var1 = 31 * var1 + this.pkLen;
      var1 = 31 * var1 + this.polyType;
      var1 = 31 * var1 + this.q;
      var1 = 31 * var1 + (this.sparse ? 1231 : 1237);
      return var1;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         NTRUEncryptionKeyGenerationParameters var2 = (NTRUEncryptionKeyGenerationParameters)var1;
         if (this.N != var2.N) {
            return false;
         } else if (this.bufferLenBits != var2.bufferLenBits) {
            return false;
         } else if (this.bufferLenTrits != var2.bufferLenTrits) {
            return false;
         } else if (this.c != var2.c) {
            return false;
         } else if (this.db != var2.db) {
            return false;
         } else if (this.df != var2.df) {
            return false;
         } else if (this.df1 != var2.df1) {
            return false;
         } else if (this.df2 != var2.df2) {
            return false;
         } else if (this.df3 != var2.df3) {
            return false;
         } else if (this.dg != var2.dg) {
            return false;
         } else if (this.dm0 != var2.dm0) {
            return false;
         } else if (this.dr != var2.dr) {
            return false;
         } else if (this.dr1 != var2.dr1) {
            return false;
         } else if (this.dr2 != var2.dr2) {
            return false;
         } else if (this.dr3 != var2.dr3) {
            return false;
         } else if (this.fastFp != var2.fastFp) {
            return false;
         } else {
            if (this.hashAlg == null) {
               if (var2.hashAlg != null) {
                  return false;
               }
            } else if (!this.hashAlg.getAlgorithmName().equals(var2.hashAlg.getAlgorithmName())) {
               return false;
            }

            if (this.hashSeed != var2.hashSeed) {
               return false;
            } else if (this.llen != var2.llen) {
               return false;
            } else if (this.maxMsgLenBytes != var2.maxMsgLenBytes) {
               return false;
            } else if (this.minCallsMask != var2.minCallsMask) {
               return false;
            } else if (this.minCallsR != var2.minCallsR) {
               return false;
            } else if (!Arrays.equals(this.oid, var2.oid)) {
               return false;
            } else if (this.pkLen != var2.pkLen) {
               return false;
            } else if (this.polyType != var2.polyType) {
               return false;
            } else if (this.q != var2.q) {
               return false;
            } else {
               return this.sparse == var2.sparse;
            }
         }
      }
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder("EncryptionParameters(N=" + this.N + " q=" + this.q);
      if (this.polyType == 0) {
         var1.append(" polyType=SIMPLE df=" + this.df);
      } else {
         var1.append(" polyType=PRODUCT df1=" + this.df1 + " df2=" + this.df2 + " df3=" + this.df3);
      }

      var1.append(" dm0=" + this.dm0 + " db=" + this.db + " c=" + this.c + " minCallsR=" + this.minCallsR + " minCallsMask=" + this.minCallsMask + " hashSeed=" + this.hashSeed + " hashAlg=" + this.hashAlg + " oid=" + Arrays.toString(this.oid) + " sparse=" + this.sparse + ")");
      return var1.toString();
   }
}
