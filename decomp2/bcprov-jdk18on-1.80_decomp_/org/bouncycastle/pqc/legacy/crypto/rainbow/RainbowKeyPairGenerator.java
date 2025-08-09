package org.bouncycastle.pqc.legacy.crypto.rainbow;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.pqc.legacy.crypto.rainbow.util.ComputeInField;
import org.bouncycastle.pqc.legacy.crypto.rainbow.util.GF2Field;

public class RainbowKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
   private boolean initialized = false;
   private SecureRandom sr;
   private RainbowKeyGenerationParameters rainbowParams;
   private short[][] A1;
   private short[][] A1inv;
   private short[] b1;
   private short[][] A2;
   private short[][] A2inv;
   private short[] b2;
   private int numOfLayers;
   private Layer[] layers;
   private int[] vi;
   private short[][] pub_quadratic;
   private short[][] pub_singular;
   private short[] pub_scalar;

   public AsymmetricCipherKeyPair genKeyPair() {
      if (!this.initialized) {
         this.initializeDefault();
      }

      this.keygen();
      RainbowPrivateKeyParameters var1 = new RainbowPrivateKeyParameters(this.A1inv, this.b1, this.A2inv, this.b2, this.vi, this.layers);
      RainbowPublicKeyParameters var2 = new RainbowPublicKeyParameters(this.vi[this.vi.length - 1] - this.vi[0], this.pub_quadratic, this.pub_singular, this.pub_scalar);
      return new AsymmetricCipherKeyPair(var2, var1);
   }

   public void initialize(KeyGenerationParameters var1) {
      this.rainbowParams = (RainbowKeyGenerationParameters)var1;
      this.sr = this.rainbowParams.getRandom();
      this.vi = this.rainbowParams.getParameters().getVi();
      this.numOfLayers = this.rainbowParams.getParameters().getNumOfLayers();
      this.initialized = true;
   }

   private void initializeDefault() {
      RainbowKeyGenerationParameters var1 = new RainbowKeyGenerationParameters(CryptoServicesRegistrar.getSecureRandom(), new RainbowParameters());
      this.initialize(var1);
   }

   private void keygen() {
      this.generateL1();
      this.generateL2();
      this.generateF();
      this.computePublicKey();
   }

   private void generateL1() {
      int var1 = this.vi[this.vi.length - 1] - this.vi[0];
      this.A1 = new short[var1][var1];
      this.A1inv = null;

      for(ComputeInField var2 = new ComputeInField(); this.A1inv == null; this.A1inv = var2.inverse(this.A1)) {
         for(int var3 = 0; var3 < var1; ++var3) {
            for(int var4 = 0; var4 < var1; ++var4) {
               this.A1[var3][var4] = (short)(this.sr.nextInt() & 255);
            }
         }
      }

      this.b1 = new short[var1];

      for(int var5 = 0; var5 < var1; ++var5) {
         this.b1[var5] = (short)(this.sr.nextInt() & 255);
      }

   }

   private void generateL2() {
      int var1 = this.vi[this.vi.length - 1];
      this.A2 = new short[var1][var1];
      this.A2inv = null;

      for(ComputeInField var2 = new ComputeInField(); this.A2inv == null; this.A2inv = var2.inverse(this.A2)) {
         for(int var3 = 0; var3 < var1; ++var3) {
            for(int var4 = 0; var4 < var1; ++var4) {
               this.A2[var3][var4] = (short)(this.sr.nextInt() & 255);
            }
         }
      }

      this.b2 = new short[var1];

      for(int var5 = 0; var5 < var1; ++var5) {
         this.b2[var5] = (short)(this.sr.nextInt() & 255);
      }

   }

   private void generateF() {
      this.layers = new Layer[this.numOfLayers];

      for(int var1 = 0; var1 < this.numOfLayers; ++var1) {
         this.layers[var1] = new Layer(this.vi[var1], this.vi[var1 + 1], this.sr);
      }

   }

   private void computePublicKey() {
      ComputeInField var1 = new ComputeInField();
      int var2 = this.vi[this.vi.length - 1] - this.vi[0];
      int var3 = this.vi[this.vi.length - 1];
      short[][][] var4 = new short[var2][var3][var3];
      this.pub_singular = new short[var2][var3];
      this.pub_scalar = new short[var2];
      int var9 = 0;
      int var10 = 0;
      int var11 = 0;
      short[] var12 = new short[var3];
      short var13 = 0;

      for(int var14 = 0; var14 < this.layers.length; ++var14) {
         short[][][] var5 = this.layers[var14].getCoeffAlpha();
         short[][][] var6 = this.layers[var14].getCoeffBeta();
         short[][] var7 = this.layers[var14].getCoeffGamma();
         short[] var8 = this.layers[var14].getCoeffEta();
         var9 = var5[0].length;
         var10 = var6[0].length;

         for(int var15 = 0; var15 < var9; ++var15) {
            for(int var16 = 0; var16 < var9; ++var16) {
               for(int var17 = 0; var17 < var10; ++var17) {
                  var12 = var1.multVect(var5[var15][var16][var17], this.A2[var16 + var10]);
                  var4[var11 + var15] = var1.addSquareMatrix(var4[var11 + var15], var1.multVects(var12, this.A2[var17]));
                  var12 = var1.multVect(this.b2[var17], var12);
                  this.pub_singular[var11 + var15] = var1.addVect(var12, this.pub_singular[var11 + var15]);
                  var12 = var1.multVect(var5[var15][var16][var17], this.A2[var17]);
                  var12 = var1.multVect(this.b2[var16 + var10], var12);
                  this.pub_singular[var11 + var15] = var1.addVect(var12, this.pub_singular[var11 + var15]);
                  var13 = GF2Field.multElem(var5[var15][var16][var17], this.b2[var16 + var10]);
                  this.pub_scalar[var11 + var15] = GF2Field.addElem(this.pub_scalar[var11 + var15], GF2Field.multElem(var13, this.b2[var17]));
               }
            }

            for(int var34 = 0; var34 < var10; ++var34) {
               for(int var37 = 0; var37 < var10; ++var37) {
                  var12 = var1.multVect(var6[var15][var34][var37], this.A2[var34]);
                  var4[var11 + var15] = var1.addSquareMatrix(var4[var11 + var15], var1.multVects(var12, this.A2[var37]));
                  var12 = var1.multVect(this.b2[var37], var12);
                  this.pub_singular[var11 + var15] = var1.addVect(var12, this.pub_singular[var11 + var15]);
                  var12 = var1.multVect(var6[var15][var34][var37], this.A2[var37]);
                  var12 = var1.multVect(this.b2[var34], var12);
                  this.pub_singular[var11 + var15] = var1.addVect(var12, this.pub_singular[var11 + var15]);
                  var13 = GF2Field.multElem(var6[var15][var34][var37], this.b2[var34]);
                  this.pub_scalar[var11 + var15] = GF2Field.addElem(this.pub_scalar[var11 + var15], GF2Field.multElem(var13, this.b2[var37]));
               }
            }

            for(int var35 = 0; var35 < var10 + var9; ++var35) {
               var12 = var1.multVect(var7[var15][var35], this.A2[var35]);
               this.pub_singular[var11 + var15] = var1.addVect(var12, this.pub_singular[var11 + var15]);
               this.pub_scalar[var11 + var15] = GF2Field.addElem(this.pub_scalar[var11 + var15], GF2Field.multElem(var7[var15][var35], this.b2[var35]));
            }

            this.pub_scalar[var11 + var15] = GF2Field.addElem(this.pub_scalar[var11 + var15], var8[var15]);
         }

         var11 += var9;
      }

      short[][][] var32 = new short[var2][var3][var3];
      short[][] var33 = new short[var2][var3];
      short[] var36 = new short[var2];

      for(int var38 = 0; var38 < var2; ++var38) {
         for(int var18 = 0; var18 < this.A1.length; ++var18) {
            var32[var38] = var1.addSquareMatrix(var32[var38], var1.multMatrix(this.A1[var38][var18], var4[var18]));
            var33[var38] = var1.addVect(var33[var38], var1.multVect(this.A1[var38][var18], this.pub_singular[var18]));
            var36[var38] = GF2Field.addElem(var36[var38], GF2Field.multElem(this.A1[var38][var18], this.pub_scalar[var18]));
         }

         var36[var38] = GF2Field.addElem(var36[var38], this.b1[var38]);
      }

      this.pub_singular = var33;
      this.pub_scalar = var36;
      this.compactPublicKey(var32);
   }

   private void compactPublicKey(short[][][] var1) {
      int var2 = var1.length;
      int var3 = var1[0].length;
      int var4 = var3 * (var3 + 1) / 2;
      this.pub_quadratic = new short[var2][var4];
      int var5 = 0;

      for(int var6 = 0; var6 < var2; ++var6) {
         var5 = 0;

         for(int var7 = 0; var7 < var3; ++var7) {
            for(int var8 = var7; var8 < var3; ++var8) {
               if (var8 == var7) {
                  this.pub_quadratic[var6][var5] = var1[var6][var7][var8];
               } else {
                  this.pub_quadratic[var6][var5] = GF2Field.addElem(var1[var6][var7][var8], var1[var6][var8][var7]);
               }

               ++var5;
            }
         }
      }

   }

   public void init(KeyGenerationParameters var1) {
      this.initialize(var1);
   }

   public AsymmetricCipherKeyPair generateKeyPair() {
      return this.genKeyPair();
   }
}
