package org.bouncycastle.pqc.crypto.gemss;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.digests.SHAKEDigest;

public class GeMSSKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
   private SecureRandom random;
   private GeMSSParameters parameters;

   public void init(KeyGenerationParameters var1) {
      this.random = var1.getRandom();
      this.parameters = ((GeMSSKeyGenerationParameters)var1).getParameters();
   }

   public AsymmetricCipherKeyPair generateKeyPair() {
      GeMSSEngine var1 = this.parameters.getEngine();
      byte[] var4 = this.sec_rand(var1.SIZE_SEED_SK);
      int var5 = 2 + var1.HFEDegJ + (var1.HFEDegI * (var1.HFEDegI + 1) >>> 1);
      int var6 = var5 + (var1.NB_MONOMIAL_VINEGAR - 1) + (var1.HFEDegI + 1) * var1.HFEv;
      int var7 = var6 * var1.NB_WORD_GFqn;
      int var8 = var7 + (var1.LTRIANGULAR_NV_SIZE << 1) + (var1.LTRIANGULAR_N_SIZE << 1) << 3;
      Pointer var9 = new Pointer(var8 >>> 3);
      byte[] var10 = new byte[var8];
      SHAKEDigest var11 = new SHAKEDigest(var1.ShakeBitStrength);
      var11.update(var4, 0, var1.SIZE_SEED_SK);
      var11.doFinal(var10, 0, var8);
      byte[] var12 = new byte[var1.SIZE_SEED_SK];
      int var13 = var1.NB_MONOMIAL_PK * var1.HFEm + 7 >> 3;
      byte[] var14 = new byte[var13];
      System.arraycopy(var4, 0, var12, 0, var12.length);
      var9.fill(0, var10, 0, var10.length);
      var1.cleanMonicHFEv_gf2nx(var9);
      Pointer var15 = new Pointer(var1.NB_MONOMIAL_PK * var1.NB_WORD_GFqn);
      if (var1.HFEDeg > 34) {
         var1.genSecretMQS_gf2_opt(var15, var9);
      }

      Pointer var16 = new Pointer(var1.MATRIXnv_SIZE);
      Pointer var17 = new Pointer(var16);
      Pointer var18 = new Pointer(var9, var7);
      Pointer var19 = new Pointer(var18, var1.LTRIANGULAR_NV_SIZE);
      var1.cleanLowerMatrix(var18, GeMSSEngine.FunctionParams.NV);
      var1.cleanLowerMatrix(var19, GeMSSEngine.FunctionParams.NV);
      var1.invMatrixLU_gf2(var16, var18, var19, GeMSSEngine.FunctionParams.NV);
      if (var1.HFEDeg <= 34) {
         int var3 = var1.interpolateHFE_FS_ref(var15, var9, var16);
         if (var3 != 0) {
            throw new IllegalArgumentException("Error");
         }
      } else {
         var1.changeVariablesMQS64_gf2(var15, var16);
      }

      var18.move(var1.LTRIANGULAR_NV_SIZE << 1);
      var19.changeIndex(var18.getIndex() + var1.LTRIANGULAR_N_SIZE);
      var1.cleanLowerMatrix(var18, GeMSSEngine.FunctionParams.N);
      var1.cleanLowerMatrix(var19, GeMSSEngine.FunctionParams.N);
      var1.invMatrixLU_gf2(var17, var18, var19, GeMSSEngine.FunctionParams.N);
      if (var1.HFEmr8 != 0) {
         int var20 = var1.NB_MONOMIAL_PK * var1.NB_BYTES_GFqm + (8 - (var1.NB_BYTES_GFqm & 7) & 7);
         PointerUnion var21 = new PointerUnion(var20);

         for(int var2 = (var1.NB_BYTES_GFqm & 7) != 0 ? 1 : 0; var2 < var1.NB_MONOMIAL_PK; ++var2) {
            var1.vecMatProduct(var21, var15, var17, GeMSSEngine.FunctionParams.M);
            var15.move(var1.NB_WORD_GFqn);
            var21.moveNextBytes(var1.NB_BYTES_GFqm);
         }

         if ((var1.NB_BYTES_GFqm & 7) != 0) {
            Pointer var22 = new Pointer(var1.NB_WORD_GF2m);
            var1.vecMatProduct(var22, var15, var17, GeMSSEngine.FunctionParams.M);

            for(int var23 = 0; var23 < var1.NB_WORD_GF2m; ++var23) {
               var21.set(var23, var22.get(var23));
            }
         }

         var21.indexReset();
         byte[] var27 = new byte[var1.HFEmr8 * var1.NB_BYTES_EQUATION];
         var1.convMQS_one_to_last_mr8_equations_gf2(var27, var21);
         var21.indexReset();
         if (var1.HFENr8 != 0 && var1.HFEmr8 > 1) {
            var1.convMQS_one_eq_to_hybrid_rep8_uncomp_gf2(var14, var21, var27);
         } else {
            var1.convMQS_one_eq_to_hybrid_rep8_comp_gf2(var14, var21, var27);
         }
      } else {
         PointerUnion var25 = new PointerUnion(var1.NB_WORD_GF2m << 3);
         int var26 = 0;

         for(int var24 = 0; var24 < var1.NB_MONOMIAL_PK; ++var24) {
            var1.vecMatProduct(var25, var15, var17, GeMSSEngine.FunctionParams.M);
            var26 = var25.toBytesMove(var14, var26, var1.NB_BYTES_GFqm);
            var25.indexReset();
            var15.move(var1.NB_WORD_GFqn);
         }
      }

      return new AsymmetricCipherKeyPair(new GeMSSPublicKeyParameters(this.parameters, var14), new GeMSSPrivateKeyParameters(this.parameters, var12));
   }

   private byte[] sec_rand(int var1) {
      byte[] var2 = new byte[var1];
      this.random.nextBytes(var2);
      return var2;
   }
}
