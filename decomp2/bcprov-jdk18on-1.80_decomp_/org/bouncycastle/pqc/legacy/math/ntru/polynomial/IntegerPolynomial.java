package org.bouncycastle.pqc.legacy.math.ntru.polynomial;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import org.bouncycastle.pqc.legacy.math.ntru.euclid.BigIntEuclidean;
import org.bouncycastle.pqc.legacy.math.ntru.util.ArrayEncoder;
import org.bouncycastle.pqc.legacy.math.ntru.util.Util;
import org.bouncycastle.util.Arrays;

public class IntegerPolynomial implements Polynomial {
   private static final int NUM_EQUAL_RESULTANTS = 3;
   private static final int[] PRIMES = new int[]{4507, 4513, 4517, 4519, 4523, 4547, 4549, 4561, 4567, 4583, 4591, 4597, 4603, 4621, 4637, 4639, 4643, 4649, 4651, 4657, 4663, 4673, 4679, 4691, 4703, 4721, 4723, 4729, 4733, 4751, 4759, 4783, 4787, 4789, 4793, 4799, 4801, 4813, 4817, 4831, 4861, 4871, 4877, 4889, 4903, 4909, 4919, 4931, 4933, 4937, 4943, 4951, 4957, 4967, 4969, 4973, 4987, 4993, 4999, 5003, 5009, 5011, 5021, 5023, 5039, 5051, 5059, 5077, 5081, 5087, 5099, 5101, 5107, 5113, 5119, 5147, 5153, 5167, 5171, 5179, 5189, 5197, 5209, 5227, 5231, 5233, 5237, 5261, 5273, 5279, 5281, 5297, 5303, 5309, 5323, 5333, 5347, 5351, 5381, 5387, 5393, 5399, 5407, 5413, 5417, 5419, 5431, 5437, 5441, 5443, 5449, 5471, 5477, 5479, 5483, 5501, 5503, 5507, 5519, 5521, 5527, 5531, 5557, 5563, 5569, 5573, 5581, 5591, 5623, 5639, 5641, 5647, 5651, 5653, 5657, 5659, 5669, 5683, 5689, 5693, 5701, 5711, 5717, 5737, 5741, 5743, 5749, 5779, 5783, 5791, 5801, 5807, 5813, 5821, 5827, 5839, 5843, 5849, 5851, 5857, 5861, 5867, 5869, 5879, 5881, 5897, 5903, 5923, 5927, 5939, 5953, 5981, 5987, 6007, 6011, 6029, 6037, 6043, 6047, 6053, 6067, 6073, 6079, 6089, 6091, 6101, 6113, 6121, 6131, 6133, 6143, 6151, 6163, 6173, 6197, 6199, 6203, 6211, 6217, 6221, 6229, 6247, 6257, 6263, 6269, 6271, 6277, 6287, 6299, 6301, 6311, 6317, 6323, 6329, 6337, 6343, 6353, 6359, 6361, 6367, 6373, 6379, 6389, 6397, 6421, 6427, 6449, 6451, 6469, 6473, 6481, 6491, 6521, 6529, 6547, 6551, 6553, 6563, 6569, 6571, 6577, 6581, 6599, 6607, 6619, 6637, 6653, 6659, 6661, 6673, 6679, 6689, 6691, 6701, 6703, 6709, 6719, 6733, 6737, 6761, 6763, 6779, 6781, 6791, 6793, 6803, 6823, 6827, 6829, 6833, 6841, 6857, 6863, 6869, 6871, 6883, 6899, 6907, 6911, 6917, 6947, 6949, 6959, 6961, 6967, 6971, 6977, 6983, 6991, 6997, 7001, 7013, 7019, 7027, 7039, 7043, 7057, 7069, 7079, 7103, 7109, 7121, 7127, 7129, 7151, 7159, 7177, 7187, 7193, 7207, 7211, 7213, 7219, 7229, 7237, 7243, 7247, 7253, 7283, 7297, 7307, 7309, 7321, 7331, 7333, 7349, 7351, 7369, 7393, 7411, 7417, 7433, 7451, 7457, 7459, 7477, 7481, 7487, 7489, 7499, 7507, 7517, 7523, 7529, 7537, 7541, 7547, 7549, 7559, 7561, 7573, 7577, 7583, 7589, 7591, 7603, 7607, 7621, 7639, 7643, 7649, 7669, 7673, 7681, 7687, 7691, 7699, 7703, 7717, 7723, 7727, 7741, 7753, 7757, 7759, 7789, 7793, 7817, 7823, 7829, 7841, 7853, 7867, 7873, 7877, 7879, 7883, 7901, 7907, 7919, 7927, 7933, 7937, 7949, 7951, 7963, 7993, 8009, 8011, 8017, 8039, 8053, 8059, 8069, 8081, 8087, 8089, 8093, 8101, 8111, 8117, 8123, 8147, 8161, 8167, 8171, 8179, 8191, 8209, 8219, 8221, 8231, 8233, 8237, 8243, 8263, 8269, 8273, 8287, 8291, 8293, 8297, 8311, 8317, 8329, 8353, 8363, 8369, 8377, 8387, 8389, 8419, 8423, 8429, 8431, 8443, 8447, 8461, 8467, 8501, 8513, 8521, 8527, 8537, 8539, 8543, 8563, 8573, 8581, 8597, 8599, 8609, 8623, 8627, 8629, 8641, 8647, 8663, 8669, 8677, 8681, 8689, 8693, 8699, 8707, 8713, 8719, 8731, 8737, 8741, 8747, 8753, 8761, 8779, 8783, 8803, 8807, 8819, 8821, 8831, 8837, 8839, 8849, 8861, 8863, 8867, 8887, 8893, 8923, 8929, 8933, 8941, 8951, 8963, 8969, 8971, 8999, 9001, 9007, 9011, 9013, 9029, 9041, 9043, 9049, 9059, 9067, 9091, 9103, 9109, 9127, 9133, 9137, 9151, 9157, 9161, 9173, 9181, 9187, 9199, 9203, 9209, 9221, 9227, 9239, 9241, 9257, 9277, 9281, 9283, 9293, 9311, 9319, 9323, 9337, 9341, 9343, 9349, 9371, 9377, 9391, 9397, 9403, 9413, 9419, 9421, 9431, 9433, 9437, 9439, 9461, 9463, 9467, 9473, 9479, 9491, 9497, 9511, 9521, 9533, 9539, 9547, 9551, 9587, 9601, 9613, 9619, 9623, 9629, 9631, 9643, 9649, 9661, 9677, 9679, 9689, 9697, 9719, 9721, 9733, 9739, 9743, 9749, 9767, 9769, 9781, 9787, 9791, 9803, 9811, 9817, 9829, 9833, 9839, 9851, 9857, 9859, 9871, 9883, 9887, 9901, 9907, 9923, 9929, 9931, 9941, 9949, 9967, 9973};
   private static final List BIGINT_PRIMES = new ArrayList();
   public int[] coeffs;

   public IntegerPolynomial(int var1) {
      this.coeffs = new int[var1];
   }

   public IntegerPolynomial(int[] var1) {
      this.coeffs = var1;
   }

   public IntegerPolynomial(BigIntPolynomial var1) {
      this.coeffs = new int[var1.coeffs.length];

      for(int var2 = 0; var2 < var1.coeffs.length; ++var2) {
         this.coeffs[var2] = var1.coeffs[var2].intValue();
      }

   }

   public static IntegerPolynomial fromBinary3Sves(byte[] var0, int var1) {
      return new IntegerPolynomial(ArrayEncoder.decodeMod3Sves(var0, var1));
   }

   public static IntegerPolynomial fromBinary3Tight(byte[] var0, int var1) {
      return new IntegerPolynomial(ArrayEncoder.decodeMod3Tight(var0, var1));
   }

   public static IntegerPolynomial fromBinary3Tight(InputStream var0, int var1) throws IOException {
      return new IntegerPolynomial(ArrayEncoder.decodeMod3Tight(var0, var1));
   }

   public static IntegerPolynomial fromBinary(byte[] var0, int var1, int var2) {
      return new IntegerPolynomial(ArrayEncoder.decodeModQ(var0, var1, var2));
   }

   public static IntegerPolynomial fromBinary(InputStream var0, int var1, int var2) throws IOException {
      return new IntegerPolynomial(ArrayEncoder.decodeModQ(var0, var1, var2));
   }

   public byte[] toBinary3Sves() {
      return ArrayEncoder.encodeMod3Sves(this.coeffs);
   }

   public byte[] toBinary3Tight() {
      BigInteger var1 = Constants.BIGINT_ZERO;

      for(int var2 = this.coeffs.length - 1; var2 >= 0; --var2) {
         var1 = var1.multiply(BigInteger.valueOf(3L));
         var1 = var1.add(BigInteger.valueOf((long)(this.coeffs[var2] + 1)));
      }

      int var6 = (BigInteger.valueOf(3L).pow(this.coeffs.length).bitLength() + 7) / 8;
      byte[] var3 = var1.toByteArray();
      if (var3.length < var6) {
         byte[] var4 = new byte[var6];
         System.arraycopy(var3, 0, var4, var6 - var3.length, var3.length);
         return var4;
      } else {
         if (var3.length > var6) {
            var3 = Arrays.copyOfRange((byte[])var3, 1, var3.length);
         }

         return var3;
      }
   }

   public byte[] toBinary(int var1) {
      return ArrayEncoder.encodeModQ(this.coeffs, var1);
   }

   public IntegerPolynomial mult(IntegerPolynomial var1, int var2) {
      IntegerPolynomial var3 = this.mult(var1);
      var3.mod(var2);
      return var3;
   }

   public IntegerPolynomial mult(IntegerPolynomial var1) {
      int var2 = this.coeffs.length;
      if (var1.coeffs.length != var2) {
         throw new IllegalArgumentException("Number of coefficients must be the same");
      } else {
         IntegerPolynomial var3 = this.multRecursive(var1);
         if (var3.coeffs.length > var2) {
            for(int var4 = var2; var4 < var3.coeffs.length; ++var4) {
               int[] var10000 = var3.coeffs;
               var10000[var4 - var2] += var3.coeffs[var4];
            }

            var3.coeffs = Arrays.copyOf(var3.coeffs, var2);
         }

         return var3;
      }
   }

   public BigIntPolynomial mult(BigIntPolynomial var1) {
      return (new BigIntPolynomial(this)).mult(var1);
   }

   private IntegerPolynomial multRecursive(IntegerPolynomial var1) {
      int[] var2 = this.coeffs;
      int[] var3 = var1.coeffs;
      int var4 = var1.coeffs.length;
      if (var4 <= 32) {
         int var17 = 2 * var4 - 1;
         IntegerPolynomial var18 = new IntegerPolynomial(new int[var17]);

         for(int var19 = 0; var19 < var17; ++var19) {
            for(int var20 = Math.max(0, var19 - var4 + 1); var20 <= Math.min(var19, var4 - 1); ++var20) {
               int[] var24 = var18.coeffs;
               var24[var19] += var3[var20] * var2[var19 - var20];
            }
         }

         return var18;
      } else {
         int var5 = var4 / 2;
         IntegerPolynomial var6 = new IntegerPolynomial(Arrays.copyOf(var2, var5));
         IntegerPolynomial var7 = new IntegerPolynomial(Arrays.copyOfRange(var2, var5, var4));
         IntegerPolynomial var8 = new IntegerPolynomial(Arrays.copyOf(var3, var5));
         IntegerPolynomial var9 = new IntegerPolynomial(Arrays.copyOfRange(var3, var5, var4));
         IntegerPolynomial var10 = (IntegerPolynomial)var6.clone();
         var10.add(var7);
         IntegerPolynomial var11 = (IntegerPolynomial)var8.clone();
         var11.add(var9);
         IntegerPolynomial var12 = var6.multRecursive(var8);
         IntegerPolynomial var13 = var7.multRecursive(var9);
         IntegerPolynomial var14 = var10.multRecursive(var11);
         var14.sub(var12);
         var14.sub(var13);
         IntegerPolynomial var15 = new IntegerPolynomial(2 * var4 - 1);

         for(int var16 = 0; var16 < var12.coeffs.length; ++var16) {
            var15.coeffs[var16] = var12.coeffs[var16];
         }

         for(int var21 = 0; var21 < var14.coeffs.length; ++var21) {
            int[] var10000 = var15.coeffs;
            var10000[var5 + var21] += var14.coeffs[var21];
         }

         for(int var22 = 0; var22 < var13.coeffs.length; ++var22) {
            int[] var23 = var15.coeffs;
            var23[2 * var5 + var22] += var13.coeffs[var22];
         }

         return var15;
      }
   }

   public IntegerPolynomial invertFq(int var1) {
      int var2 = this.coeffs.length;
      int var3 = 0;
      IntegerPolynomial var4 = new IntegerPolynomial(var2 + 1);
      var4.coeffs[0] = 1;
      IntegerPolynomial var5 = new IntegerPolynomial(var2 + 1);
      IntegerPolynomial var6 = new IntegerPolynomial(var2 + 1);
      var6.coeffs = Arrays.copyOf(this.coeffs, var2 + 1);
      var6.modPositive(2);
      IntegerPolynomial var7 = new IntegerPolynomial(var2 + 1);
      var7.coeffs[0] = 1;
      var7.coeffs[var2] = 1;

      do {
         while(var6.coeffs[0] != 0) {
            if (var6.equalsOne()) {
               if (var4.coeffs[var2] != 0) {
                  return null;
               }

               IntegerPolynomial var13 = new IntegerPolynomial(var2);
               int var9 = 0;
               var3 %= var2;

               for(int var10 = var2 - 1; var10 >= 0; --var10) {
                  var9 = var10 - var3;
                  if (var9 < 0) {
                     var9 += var2;
                  }

                  var13.coeffs[var9] = var4.coeffs[var10];
               }

               return this.mod2ToModq(var13, var1);
            }

            if (var6.degree() < var7.degree()) {
               IntegerPolynomial var8 = var6;
               var6 = var7;
               var7 = var8;
               var8 = var4;
               var4 = var5;
               var5 = var8;
            }

            var6.add(var7, 2);
            var4.add(var5, 2);
         }

         for(int var14 = 1; var14 <= var2; ++var14) {
            var6.coeffs[var14 - 1] = var6.coeffs[var14];
            var5.coeffs[var2 + 1 - var14] = var5.coeffs[var2 - var14];
         }

         var6.coeffs[var2] = 0;
         var5.coeffs[0] = 0;
         ++var3;
      } while(!var6.equalsZero());

      return null;
   }

   private IntegerPolynomial mod2ToModq(IntegerPolynomial var1, int var2) {
      if (Util.is64BitJVM() && var2 == 2048) {
         LongPolynomial2 var8 = new LongPolynomial2(this);
         LongPolynomial2 var9 = new LongPolynomial2(var1);

         LongPolynomial2 var6;
         for(int var5 = 2; var5 < var2; var9 = var6) {
            var5 *= 2;
            var6 = (LongPolynomial2)var9.clone();
            var6.mult2And(var5 - 1);
            var9 = var8.mult(var9).mult(var9);
            var6.subAnd(var9, var5 - 1);
         }

         return var9.toIntegerPolynomial();
      } else {
         IntegerPolynomial var4;
         for(int var3 = 2; var3 < var2; var1 = var4) {
            var3 *= 2;
            var4 = new IntegerPolynomial(Arrays.copyOf(var1.coeffs, var1.coeffs.length));
            var4.mult2(var3);
            var1 = this.mult(var1, var3).mult(var1, var3);
            var4.sub(var1, var3);
         }

         return var1;
      }
   }

   public IntegerPolynomial invertF3() {
      int var1 = this.coeffs.length;
      int var2 = 0;
      IntegerPolynomial var3 = new IntegerPolynomial(var1 + 1);
      var3.coeffs[0] = 1;
      IntegerPolynomial var4 = new IntegerPolynomial(var1 + 1);
      IntegerPolynomial var5 = new IntegerPolynomial(var1 + 1);
      var5.coeffs = Arrays.copyOf(this.coeffs, var1 + 1);
      var5.modPositive(3);
      IntegerPolynomial var6 = new IntegerPolynomial(var1 + 1);
      var6.coeffs[0] = -1;
      var6.coeffs[var1] = 1;

      do {
         while(var5.coeffs[0] != 0) {
            if (var5.equalsAbsOne()) {
               if (var3.coeffs[var1] != 0) {
                  return null;
               }

               IntegerPolynomial var12 = new IntegerPolynomial(var1);
               int var8 = 0;
               var2 %= var1;

               for(int var9 = var1 - 1; var9 >= 0; --var9) {
                  var8 = var9 - var2;
                  if (var8 < 0) {
                     var8 += var1;
                  }

                  var12.coeffs[var8] = var5.coeffs[0] * var3.coeffs[var9];
               }

               var12.ensurePositive(3);
               return var12;
            }

            if (var5.degree() < var6.degree()) {
               IntegerPolynomial var7 = var5;
               var5 = var6;
               var6 = var7;
               var7 = var3;
               var3 = var4;
               var4 = var7;
            }

            if (var5.coeffs[0] == var6.coeffs[0]) {
               var5.sub(var6, 3);
               var3.sub(var4, 3);
            } else {
               var5.add(var6, 3);
               var3.add(var4, 3);
            }
         }

         for(int var13 = 1; var13 <= var1; ++var13) {
            var5.coeffs[var13 - 1] = var5.coeffs[var13];
            var4.coeffs[var1 + 1 - var13] = var4.coeffs[var1 - var13];
         }

         var5.coeffs[var1] = 0;
         var4.coeffs[0] = 0;
         ++var2;
      } while(!var5.equalsZero());

      return null;
   }

   public Resultant resultant() {
      int var1 = this.coeffs.length;
      LinkedList var2 = new LinkedList();
      BigInteger var3 = Constants.BIGINT_ONE;
      BigInteger var4 = Constants.BIGINT_ONE;
      int var5 = 1;
      PrimeGenerator var6 = new PrimeGenerator();

      while(true) {
         BigInteger var7 = var6.nextPrime();
         ModularResultant var8 = this.resultant(var7.intValue());
         var2.add(var8);
         BigInteger var9 = var3.multiply(var7);
         BigIntEuclidean var10 = BigIntEuclidean.calculate(var7, var3);
         BigInteger var11 = var4;
         var4 = var4.multiply(var10.x.multiply(var7));
         BigInteger var12 = var8.res.multiply(var10.y.multiply(var3));
         var4 = var4.add(var12).mod(var9);
         var3 = var9;
         BigInteger var13 = var9.divide(BigInteger.valueOf(2L));
         BigInteger var14 = var13.negate();
         if (var4.compareTo(var13) > 0) {
            var4 = var4.subtract(var9);
         } else if (var4.compareTo(var14) < 0) {
            var4 = var4.add(var9);
         }

         if (var4.equals(var11)) {
            ++var5;
            if (var5 >= 3) {
               while(var2.size() > 1) {
                  ModularResultant var16 = (ModularResultant)var2.removeFirst();
                  var8 = (ModularResultant)var2.removeFirst();
                  ModularResultant var20 = ModularResultant.combineRho(var16, var8);
                  var2.addLast(var20);
               }

               BigIntPolynomial var17 = ((ModularResultant)var2.getFirst()).rho;
               BigInteger var19 = var9.divide(BigInteger.valueOf(2L));
               var9 = var19.negate();
               if (var4.compareTo(var19) > 0) {
                  var4 = var4.subtract(var9);
               }

               if (var4.compareTo(var9) < 0) {
                  var4 = var4.add(var9);
               }

               for(int var22 = 0; var22 < var1; ++var22) {
                  var11 = var17.coeffs[var22];
                  if (var11.compareTo(var19) > 0) {
                     var17.coeffs[var22] = var11.subtract(var3);
                  }

                  if (var11.compareTo(var9) < 0) {
                     var17.coeffs[var22] = var11.add(var3);
                  }
               }

               return new Resultant(var17, var4);
            }
         } else {
            var5 = 1;
         }
      }
   }

   public Resultant resultantMultiThread() {
      int var1 = this.coeffs.length;
      BigInteger var2 = this.squareSum().pow((var1 + 1) / 2);
      var2 = var2.multiply(BigInteger.valueOf(2L).pow((this.degree() + 1) / 2));
      BigInteger var3 = var2.multiply(BigInteger.valueOf(2L));
      BigInteger var4 = BigInteger.valueOf(10000L);
      BigInteger var5 = Constants.BIGINT_ONE;
      LinkedBlockingQueue var6 = new LinkedBlockingQueue();
      Iterator var7 = BIGINT_PRIMES.iterator();

      ExecutorService var8;
      for(var8 = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()); var5.compareTo(var3) < 0; var5 = var5.multiply(var4)) {
         if (var7.hasNext()) {
            var4 = (BigInteger)var7.next();
         } else {
            var4 = var4.nextProbablePrime();
         }

         Future var9 = var8.submit(new ModResultantTask(var4.intValue()));
         var6.add(var9);
      }

      ModularResultant var18 = null;

      while(!var6.isEmpty()) {
         try {
            Future var10 = (Future)var6.take();
            Future var11 = (Future)var6.poll();
            if (var11 == null) {
               var18 = (ModularResultant)var10.get();
               break;
            }

            Future var12 = var8.submit(new CombineTask((ModularResultant)var10.get(), (ModularResultant)var11.get()));
            var6.add(var12);
         } catch (Exception var16) {
            throw new IllegalStateException(var16.toString());
         }
      }

      var8.shutdown();
      BigInteger var19 = var18.res;
      BigIntPolynomial var20 = var18.rho;
      BigInteger var21 = var5.divide(BigInteger.valueOf(2L));
      BigInteger var13 = var21.negate();
      if (var19.compareTo(var21) > 0) {
         var19 = var19.subtract(var5);
      }

      if (var19.compareTo(var13) < 0) {
         var19 = var19.add(var5);
      }

      for(int var14 = 0; var14 < var1; ++var14) {
         BigInteger var15 = var20.coeffs[var14];
         if (var15.compareTo(var21) > 0) {
            var20.coeffs[var14] = var15.subtract(var5);
         }

         if (var15.compareTo(var13) < 0) {
            var20.coeffs[var14] = var15.add(var5);
         }
      }

      return new Resultant(var20, var19);
   }

   public ModularResultant resultant(int var1) {
      int[] var2 = Arrays.copyOf(this.coeffs, this.coeffs.length + 1);
      IntegerPolynomial var3 = new IntegerPolynomial(var2);
      int var4 = var2.length;
      IntegerPolynomial var5 = new IntegerPolynomial(var4);
      var5.coeffs[0] = -1;
      var5.coeffs[var4 - 1] = 1;
      IntegerPolynomial var6 = new IntegerPolynomial(var3.coeffs);
      IntegerPolynomial var7 = new IntegerPolynomial(var4);
      IntegerPolynomial var8 = new IntegerPolynomial(var4);
      var8.coeffs[0] = 1;
      int var9 = var4 - 1;
      int var10 = var6.degree();
      int var11 = var9;
      int var12 = 0;
      int var13 = 1;

      while(var10 > 0) {
         var12 = Util.invert(var6.coeffs[var10], var1);
         var12 = var12 * var5.coeffs[var9] % var1;
         var5.multShiftSub(var6, var12, var9 - var10, var1);
         var7.multShiftSub(var8, var12, var9 - var10, var1);
         var9 = var5.degree();
         if (var9 < var10) {
            var13 *= Util.pow(var6.coeffs[var10], var11 - var9, var1);
            var13 %= var1;
            if (var11 % 2 == 1 && var10 % 2 == 1) {
               var13 = -var13 % var1;
            }

            IntegerPolynomial var14 = var5;
            var5 = var6;
            var6 = var14;
            int var15 = var9;
            var9 = var10;
            var14 = var7;
            var7 = var8;
            var8 = var14;
            var11 = var10;
            var10 = var15;
         }
      }

      var13 *= Util.pow(var6.coeffs[0], var9, var1);
      var13 %= var1;
      var12 = Util.invert(var6.coeffs[0], var1);
      var8.mult(var12);
      var8.mod(var1);
      var8.mult(var13);
      var8.mod(var1);
      var8.coeffs = Arrays.copyOf(var8.coeffs, var8.coeffs.length - 1);
      return new ModularResultant(new BigIntPolynomial(var8), BigInteger.valueOf((long)var13), BigInteger.valueOf((long)var1));
   }

   private void multShiftSub(IntegerPolynomial var1, int var2, int var3, int var4) {
      int var5 = this.coeffs.length;

      for(int var6 = var3; var6 < var5; ++var6) {
         this.coeffs[var6] = (this.coeffs[var6] - var1.coeffs[var6 - var3] * var2) % var4;
      }

   }

   private BigInteger squareSum() {
      BigInteger var1 = Constants.BIGINT_ZERO;

      for(int var2 = 0; var2 < this.coeffs.length; ++var2) {
         var1 = var1.add(BigInteger.valueOf((long)(this.coeffs[var2] * this.coeffs[var2])));
      }

      return var1;
   }

   int degree() {
      int var1;
      for(var1 = this.coeffs.length - 1; var1 > 0 && this.coeffs[var1] == 0; --var1) {
      }

      return var1;
   }

   public void add(IntegerPolynomial var1, int var2) {
      this.add(var1);
      this.mod(var2);
   }

   public void add(IntegerPolynomial var1) {
      if (var1.coeffs.length > this.coeffs.length) {
         this.coeffs = Arrays.copyOf(this.coeffs, var1.coeffs.length);
      }

      for(int var2 = 0; var2 < var1.coeffs.length; ++var2) {
         int[] var10000 = this.coeffs;
         var10000[var2] += var1.coeffs[var2];
      }

   }

   public void sub(IntegerPolynomial var1, int var2) {
      this.sub(var1);
      this.mod(var2);
   }

   public void sub(IntegerPolynomial var1) {
      if (var1.coeffs.length > this.coeffs.length) {
         this.coeffs = Arrays.copyOf(this.coeffs, var1.coeffs.length);
      }

      for(int var2 = 0; var2 < var1.coeffs.length; ++var2) {
         int[] var10000 = this.coeffs;
         var10000[var2] -= var1.coeffs[var2];
      }

   }

   void sub(int var1) {
      for(int var2 = 0; var2 < this.coeffs.length; ++var2) {
         int[] var10000 = this.coeffs;
         var10000[var2] -= var1;
      }

   }

   public void mult(int var1) {
      for(int var2 = 0; var2 < this.coeffs.length; ++var2) {
         int[] var10000 = this.coeffs;
         var10000[var2] *= var1;
      }

   }

   private void mult2(int var1) {
      for(int var2 = 0; var2 < this.coeffs.length; ++var2) {
         int[] var10000 = this.coeffs;
         var10000[var2] *= 2;
         var10000 = this.coeffs;
         var10000[var2] %= var1;
      }

   }

   public void mult3(int var1) {
      for(int var2 = 0; var2 < this.coeffs.length; ++var2) {
         int[] var10000 = this.coeffs;
         var10000[var2] *= 3;
         var10000 = this.coeffs;
         var10000[var2] %= var1;
      }

   }

   public void div(int var1) {
      int var2 = (var1 + 1) / 2;

      for(int var3 = 0; var3 < this.coeffs.length; ++var3) {
         int[] var10000 = this.coeffs;
         var10000[var3] += this.coeffs[var3] > 0 ? var2 : -var2;
         var10000 = this.coeffs;
         var10000[var3] /= var1;
      }

   }

   public void mod3() {
      for(int var1 = 0; var1 < this.coeffs.length; ++var1) {
         int[] var10000 = this.coeffs;
         var10000[var1] %= 3;
         if (this.coeffs[var1] > 1) {
            var10000 = this.coeffs;
            var10000[var1] -= 3;
         }

         if (this.coeffs[var1] < -1) {
            var10000 = this.coeffs;
            var10000[var1] += 3;
         }
      }

   }

   public void modPositive(int var1) {
      this.mod(var1);
      this.ensurePositive(var1);
   }

   void modCenter(int var1) {
      this.mod(var1);

      for(int var2 = 0; var2 < this.coeffs.length; ++var2) {
         while(this.coeffs[var2] < var1 / 2) {
            int[] var10000 = this.coeffs;
            var10000[var2] += var1;
         }

         while(this.coeffs[var2] >= var1 / 2) {
            int[] var3 = this.coeffs;
            var3[var2] -= var1;
         }
      }

   }

   public void mod(int var1) {
      for(int var2 = 0; var2 < this.coeffs.length; ++var2) {
         int[] var10000 = this.coeffs;
         var10000[var2] %= var1;
      }

   }

   public void ensurePositive(int var1) {
      for(int var2 = 0; var2 < this.coeffs.length; ++var2) {
         while(this.coeffs[var2] < 0) {
            int[] var10000 = this.coeffs;
            var10000[var2] += var1;
         }
      }

   }

   public long centeredNormSq(int var1) {
      int var2 = this.coeffs.length;
      IntegerPolynomial var3 = (IntegerPolynomial)this.clone();
      var3.shiftGap(var1);
      long var4 = 0L;
      long var6 = 0L;

      for(int var8 = 0; var8 != var3.coeffs.length; ++var8) {
         int var9 = var3.coeffs[var8];
         var4 += (long)var9;
         var6 += (long)(var9 * var9);
      }

      long var10 = var6 - var4 * var4 / (long)var2;
      return var10;
   }

   void shiftGap(int var1) {
      this.modCenter(var1);
      int[] var2 = Arrays.clone(this.coeffs);
      this.sort(var2);
      int var3 = 0;
      int var4 = 0;

      for(int var5 = 0; var5 < var2.length - 1; ++var5) {
         int var6 = var2[var5 + 1] - var2[var5];
         if (var6 > var3) {
            var3 = var6;
            var4 = var2[var5];
         }
      }

      int var9 = var2[0];
      int var10 = var2[var2.length - 1];
      int var7 = var1 - var10 + var9;
      int var8;
      if (var7 > var3) {
         var8 = (var10 + var9) / 2;
      } else {
         var8 = var4 + var3 / 2 + var1 / 2;
      }

      this.sub(var8);
   }

   private void sort(int[] var1) {
      boolean var2 = true;

      while(var2) {
         var2 = false;

         for(int var3 = 0; var3 != var1.length - 1; ++var3) {
            if (var1[var3] > var1[var3 + 1]) {
               int var4 = var1[var3];
               var1[var3] = var1[var3 + 1];
               var1[var3 + 1] = var4;
               var2 = true;
            }
         }
      }

   }

   public void center0(int var1) {
      for(int var2 = 0; var2 < this.coeffs.length; ++var2) {
         while(this.coeffs[var2] < -var1 / 2) {
            int[] var10000 = this.coeffs;
            var10000[var2] += var1;
         }

         while(this.coeffs[var2] > var1 / 2) {
            int[] var3 = this.coeffs;
            var3[var2] -= var1;
         }
      }

   }

   public int sumCoeffs() {
      int var1 = 0;

      for(int var2 = 0; var2 < this.coeffs.length; ++var2) {
         var1 += this.coeffs[var2];
      }

      return var1;
   }

   private boolean equalsZero() {
      for(int var1 = 0; var1 < this.coeffs.length; ++var1) {
         if (this.coeffs[var1] != 0) {
            return false;
         }
      }

      return true;
   }

   public boolean equalsOne() {
      for(int var1 = 1; var1 < this.coeffs.length; ++var1) {
         if (this.coeffs[var1] != 0) {
            return false;
         }
      }

      return this.coeffs[0] == 1;
   }

   private boolean equalsAbsOne() {
      for(int var1 = 1; var1 < this.coeffs.length; ++var1) {
         if (this.coeffs[var1] != 0) {
            return false;
         }
      }

      return Math.abs(this.coeffs[0]) == 1;
   }

   public int count(int var1) {
      int var2 = 0;

      for(int var3 = 0; var3 != this.coeffs.length; ++var3) {
         if (this.coeffs[var3] == var1) {
            ++var2;
         }
      }

      return var2;
   }

   public void rotate1() {
      int var1 = this.coeffs[this.coeffs.length - 1];

      for(int var2 = this.coeffs.length - 1; var2 > 0; --var2) {
         this.coeffs[var2] = this.coeffs[var2 - 1];
      }

      this.coeffs[0] = var1;
   }

   public void clear() {
      for(int var1 = 0; var1 < this.coeffs.length; ++var1) {
         this.coeffs[var1] = 0;
      }

   }

   public IntegerPolynomial toIntegerPolynomial() {
      return (IntegerPolynomial)this.clone();
   }

   public Object clone() {
      return new IntegerPolynomial((int[])this.coeffs.clone());
   }

   public boolean equals(Object var1) {
      return var1 instanceof IntegerPolynomial ? Arrays.areEqual(this.coeffs, ((IntegerPolynomial)var1).coeffs) : false;
   }

   static {
      for(int var0 = 0; var0 != PRIMES.length; ++var0) {
         BIGINT_PRIMES.add(BigInteger.valueOf((long)PRIMES[var0]));
      }

   }

   private static class CombineTask implements Callable {
      private ModularResultant modRes1;
      private ModularResultant modRes2;

      private CombineTask(ModularResultant var1, ModularResultant var2) {
         this.modRes1 = var1;
         this.modRes2 = var2;
      }

      public ModularResultant call() {
         return ModularResultant.combineRho(this.modRes1, this.modRes2);
      }
   }

   private class ModResultantTask implements Callable {
      private int modulus;

      private ModResultantTask(int var2) {
         this.modulus = var2;
      }

      public ModularResultant call() {
         return IntegerPolynomial.this.resultant(this.modulus);
      }
   }

   private static class PrimeGenerator {
      private int index;
      private BigInteger prime;

      private PrimeGenerator() {
         this.index = 0;
      }

      public BigInteger nextPrime() {
         if (this.index < IntegerPolynomial.BIGINT_PRIMES.size()) {
            this.prime = (BigInteger)IntegerPolynomial.BIGINT_PRIMES.get(this.index++);
         } else {
            this.prime = this.prime.nextProbablePrime();
         }

         return this.prime;
      }
   }
}
