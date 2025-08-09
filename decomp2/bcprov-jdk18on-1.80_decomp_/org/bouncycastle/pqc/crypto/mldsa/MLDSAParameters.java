package org.bouncycastle.pqc.crypto.mldsa;

import java.security.SecureRandom;

public class MLDSAParameters {
   public static final int TYPE_PURE = 0;
   public static final int TYPE_SHA2_512 = 1;
   public static final MLDSAParameters ml_dsa_44 = new MLDSAParameters("ml-dsa-44", 2, 0);
   public static final MLDSAParameters ml_dsa_65 = new MLDSAParameters("ml-dsa-65", 3, 0);
   public static final MLDSAParameters ml_dsa_87 = new MLDSAParameters("ml-dsa-87", 5, 0);
   public static final MLDSAParameters ml_dsa_44_with_sha512 = new MLDSAParameters("ml-dsa-44-with-sha512", 2, 1);
   public static final MLDSAParameters ml_dsa_65_with_sha512 = new MLDSAParameters("ml-dsa-65-with-sha512", 3, 1);
   public static final MLDSAParameters ml_dsa_87_with_sha512 = new MLDSAParameters("ml-dsa-87-with-sha512", 5, 1);
   private final int k;
   private final String name;
   private final int preHashDigest;

   private MLDSAParameters(String var1, int var2, int var3) {
      this.name = var1;
      this.k = var2;
      this.preHashDigest = var3;
   }

   public boolean isPreHash() {
      return this.preHashDigest != 0;
   }

   public int getType() {
      return this.preHashDigest;
   }

   MLDSAEngine getEngine(SecureRandom var1) {
      return new MLDSAEngine(this.k, var1);
   }

   public String getName() {
      return this.name;
   }
}
