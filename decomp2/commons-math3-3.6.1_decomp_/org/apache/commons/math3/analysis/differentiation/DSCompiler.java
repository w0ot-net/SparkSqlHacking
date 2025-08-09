package org.apache.commons.math3.analysis.differentiation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class DSCompiler {
   private static AtomicReference compilers = new AtomicReference((Object)null);
   private final int parameters;
   private final int order;
   private final int[][] sizes;
   private final int[][] derivativesIndirection;
   private final int[] lowerIndirection;
   private final int[][][] multIndirection;
   private final int[][][] compIndirection;

   private DSCompiler(int parameters, int order, DSCompiler valueCompiler, DSCompiler derivativeCompiler) throws NumberIsTooLargeException {
      this.parameters = parameters;
      this.order = order;
      this.sizes = compileSizes(parameters, order, valueCompiler);
      this.derivativesIndirection = compileDerivativesIndirection(parameters, order, valueCompiler, derivativeCompiler);
      this.lowerIndirection = compileLowerIndirection(parameters, order, valueCompiler, derivativeCompiler);
      this.multIndirection = compileMultiplicationIndirection(parameters, order, valueCompiler, derivativeCompiler, this.lowerIndirection);
      this.compIndirection = compileCompositionIndirection(parameters, order, valueCompiler, derivativeCompiler, this.sizes, this.derivativesIndirection);
   }

   public static DSCompiler getCompiler(int parameters, int order) throws NumberIsTooLargeException {
      DSCompiler[][] cache = (DSCompiler[][])compilers.get();
      if (cache != null && cache.length > parameters && cache[parameters].length > order && cache[parameters][order] != null) {
         return cache[parameters][order];
      } else {
         int maxParameters = FastMath.max(parameters, cache == null ? 0 : cache.length);
         int maxOrder = FastMath.max(order, cache == null ? 0 : cache[0].length);
         DSCompiler[][] newCache = new DSCompiler[maxParameters + 1][maxOrder + 1];
         if (cache != null) {
            for(int i = 0; i < cache.length; ++i) {
               System.arraycopy(cache[i], 0, newCache[i], 0, cache[i].length);
            }
         }

         for(int diag = 0; diag <= parameters + order; ++diag) {
            for(int o = FastMath.max(0, diag - parameters); o <= FastMath.min(order, diag); ++o) {
               int p = diag - o;
               if (newCache[p][o] == null) {
                  DSCompiler valueCompiler = p == 0 ? null : newCache[p - 1][o];
                  DSCompiler derivativeCompiler = o == 0 ? null : newCache[p][o - 1];
                  newCache[p][o] = new DSCompiler(p, o, valueCompiler, derivativeCompiler);
               }
            }
         }

         compilers.compareAndSet(cache, newCache);
         return newCache[parameters][order];
      }
   }

   private static int[][] compileSizes(int parameters, int order, DSCompiler valueCompiler) {
      int[][] sizes = new int[parameters + 1][order + 1];
      if (parameters == 0) {
         Arrays.fill(sizes[0], 1);
      } else {
         System.arraycopy(valueCompiler.sizes, 0, sizes, 0, parameters);
         sizes[parameters][0] = 1;

         for(int i = 0; i < order; ++i) {
            sizes[parameters][i + 1] = sizes[parameters][i] + sizes[parameters - 1][i + 1];
         }
      }

      return sizes;
   }

   private static int[][] compileDerivativesIndirection(int parameters, int order, DSCompiler valueCompiler, DSCompiler derivativeCompiler) {
      if (parameters != 0 && order != 0) {
         int vSize = valueCompiler.derivativesIndirection.length;
         int dSize = derivativeCompiler.derivativesIndirection.length;
         int[][] derivativesIndirection = new int[vSize + dSize][parameters];

         for(int i = 0; i < vSize; ++i) {
            System.arraycopy(valueCompiler.derivativesIndirection[i], 0, derivativesIndirection[i], 0, parameters - 1);
         }

         for(int i = 0; i < dSize; ++i) {
            System.arraycopy(derivativeCompiler.derivativesIndirection[i], 0, derivativesIndirection[vSize + i], 0, parameters);
            int var10002 = derivativesIndirection[vSize + i][parameters - 1]++;
         }

         return derivativesIndirection;
      } else {
         return new int[1][parameters];
      }
   }

   private static int[] compileLowerIndirection(int parameters, int order, DSCompiler valueCompiler, DSCompiler derivativeCompiler) {
      if (parameters != 0 && order > 1) {
         int vSize = valueCompiler.lowerIndirection.length;
         int dSize = derivativeCompiler.lowerIndirection.length;
         int[] lowerIndirection = new int[vSize + dSize];
         System.arraycopy(valueCompiler.lowerIndirection, 0, lowerIndirection, 0, vSize);

         for(int i = 0; i < dSize; ++i) {
            lowerIndirection[vSize + i] = valueCompiler.getSize() + derivativeCompiler.lowerIndirection[i];
         }

         return lowerIndirection;
      } else {
         return new int[]{0};
      }
   }

   private static int[][][] compileMultiplicationIndirection(int parameters, int order, DSCompiler valueCompiler, DSCompiler derivativeCompiler, int[] lowerIndirection) {
      if (parameters != 0 && order != 0) {
         int vSize = valueCompiler.multIndirection.length;
         int dSize = derivativeCompiler.multIndirection.length;
         int[][][] multIndirection = new int[vSize + dSize][][];
         System.arraycopy(valueCompiler.multIndirection, 0, multIndirection, 0, vSize);

         for(int i = 0; i < dSize; ++i) {
            int[][] dRow = derivativeCompiler.multIndirection[i];
            List<int[]> row = new ArrayList(dRow.length * 2);

            for(int j = 0; j < dRow.length; ++j) {
               row.add(new int[]{dRow[j][0], lowerIndirection[dRow[j][1]], vSize + dRow[j][2]});
               row.add(new int[]{dRow[j][0], vSize + dRow[j][1], lowerIndirection[dRow[j][2]]});
            }

            List<int[]> combined = new ArrayList(row.size());

            for(int j = 0; j < row.size(); ++j) {
               int[] termJ = (int[])row.get(j);
               if (termJ[0] > 0) {
                  for(int k = j + 1; k < row.size(); ++k) {
                     int[] termK = (int[])row.get(k);
                     if (termJ[1] == termK[1] && termJ[2] == termK[2]) {
                        termJ[0] += termK[0];
                        termK[0] = 0;
                     }
                  }

                  combined.add(termJ);
               }
            }

            multIndirection[vSize + i] = (int[][])combined.toArray(new int[combined.size()][]);
         }

         return multIndirection;
      } else {
         return new int[][][]{{{1, 0, 0}}};
      }
   }

   private static int[][][] compileCompositionIndirection(int parameters, int order, DSCompiler valueCompiler, DSCompiler derivativeCompiler, int[][] sizes, int[][] derivativesIndirection) throws NumberIsTooLargeException {
      if (parameters != 0 && order != 0) {
         int vSize = valueCompiler.compIndirection.length;
         int dSize = derivativeCompiler.compIndirection.length;
         int[][][] compIndirection = new int[vSize + dSize][][];
         System.arraycopy(valueCompiler.compIndirection, 0, compIndirection, 0, vSize);

         for(int i = 0; i < dSize; ++i) {
            List<int[]> row = new ArrayList();

            for(int[] term : derivativeCompiler.compIndirection[i]) {
               int[] derivedTermF = new int[term.length + 1];
               derivedTermF[0] = term[0];
               derivedTermF[1] = term[1] + 1;
               int[] orders = new int[parameters];
               orders[parameters - 1] = 1;
               derivedTermF[term.length] = getPartialDerivativeIndex(parameters, order, sizes, orders);

               for(int j = 2; j < term.length; ++j) {
                  derivedTermF[j] = convertIndex(term[j], parameters, derivativeCompiler.derivativesIndirection, parameters, order, sizes);
               }

               Arrays.sort(derivedTermF, 2, derivedTermF.length);
               row.add(derivedTermF);

               for(int l = 2; l < term.length; ++l) {
                  int[] derivedTermG = new int[term.length];
                  derivedTermG[0] = term[0];
                  derivedTermG[1] = term[1];

                  for(int j = 2; j < term.length; ++j) {
                     derivedTermG[j] = convertIndex(term[j], parameters, derivativeCompiler.derivativesIndirection, parameters, order, sizes);
                     if (j == l) {
                        System.arraycopy(derivativesIndirection[derivedTermG[j]], 0, orders, 0, parameters);
                        ++orders[parameters - 1];
                        derivedTermG[j] = getPartialDerivativeIndex(parameters, order, sizes, orders);
                     }
                  }

                  Arrays.sort(derivedTermG, 2, derivedTermG.length);
                  row.add(derivedTermG);
               }
            }

            List<int[]> combined = new ArrayList(row.size());

            for(int j = 0; j < row.size(); ++j) {
               int[] termJ = (int[])row.get(j);
               if (termJ[0] > 0) {
                  for(int k = j + 1; k < row.size(); ++k) {
                     int[] termK = (int[])row.get(k);
                     boolean equals = termJ.length == termK.length;

                     for(int l = 1; equals && l < termJ.length; ++l) {
                        equals &= termJ[l] == termK[l];
                     }

                     if (equals) {
                        termJ[0] += termK[0];
                        termK[0] = 0;
                     }
                  }

                  combined.add(termJ);
               }
            }

            compIndirection[vSize + i] = (int[][])combined.toArray(new int[combined.size()][]);
         }

         return compIndirection;
      } else {
         return new int[][][]{{{1, 0}}};
      }
   }

   public int getPartialDerivativeIndex(int... orders) throws DimensionMismatchException, NumberIsTooLargeException {
      if (orders.length != this.getFreeParameters()) {
         throw new DimensionMismatchException(orders.length, this.getFreeParameters());
      } else {
         return getPartialDerivativeIndex(this.parameters, this.order, this.sizes, orders);
      }
   }

   private static int getPartialDerivativeIndex(int parameters, int order, int[][] sizes, int... orders) throws NumberIsTooLargeException {
      int index = 0;
      int m = order;
      int ordersSum = 0;

      for(int i = parameters - 1; i >= 0; --i) {
         int derivativeOrder = orders[i];
         ordersSum += derivativeOrder;
         if (ordersSum > order) {
            throw new NumberIsTooLargeException(ordersSum, order, true);
         }

         while(derivativeOrder-- > 0) {
            index += sizes[i][m--];
         }
      }

      return index;
   }

   private static int convertIndex(int index, int srcP, int[][] srcDerivativesIndirection, int destP, int destO, int[][] destSizes) throws NumberIsTooLargeException {
      int[] orders = new int[destP];
      System.arraycopy(srcDerivativesIndirection[index], 0, orders, 0, FastMath.min(srcP, destP));
      return getPartialDerivativeIndex(destP, destO, destSizes, orders);
   }

   public int[] getPartialDerivativeOrders(int index) {
      return this.derivativesIndirection[index];
   }

   public int getFreeParameters() {
      return this.parameters;
   }

   public int getOrder() {
      return this.order;
   }

   public int getSize() {
      return this.sizes[this.parameters][this.order];
   }

   public void linearCombination(double a1, double[] c1, int offset1, double a2, double[] c2, int offset2, double[] result, int resultOffset) {
      for(int i = 0; i < this.getSize(); ++i) {
         result[resultOffset + i] = MathArrays.linearCombination(a1, c1[offset1 + i], a2, c2[offset2 + i]);
      }

   }

   public void linearCombination(double a1, double[] c1, int offset1, double a2, double[] c2, int offset2, double a3, double[] c3, int offset3, double[] result, int resultOffset) {
      for(int i = 0; i < this.getSize(); ++i) {
         result[resultOffset + i] = MathArrays.linearCombination(a1, c1[offset1 + i], a2, c2[offset2 + i], a3, c3[offset3 + i]);
      }

   }

   public void linearCombination(double a1, double[] c1, int offset1, double a2, double[] c2, int offset2, double a3, double[] c3, int offset3, double a4, double[] c4, int offset4, double[] result, int resultOffset) {
      for(int i = 0; i < this.getSize(); ++i) {
         result[resultOffset + i] = MathArrays.linearCombination(a1, c1[offset1 + i], a2, c2[offset2 + i], a3, c3[offset3 + i], a4, c4[offset4 + i]);
      }

   }

   public void add(double[] lhs, int lhsOffset, double[] rhs, int rhsOffset, double[] result, int resultOffset) {
      for(int i = 0; i < this.getSize(); ++i) {
         result[resultOffset + i] = lhs[lhsOffset + i] + rhs[rhsOffset + i];
      }

   }

   public void subtract(double[] lhs, int lhsOffset, double[] rhs, int rhsOffset, double[] result, int resultOffset) {
      for(int i = 0; i < this.getSize(); ++i) {
         result[resultOffset + i] = lhs[lhsOffset + i] - rhs[rhsOffset + i];
      }

   }

   public void multiply(double[] lhs, int lhsOffset, double[] rhs, int rhsOffset, double[] result, int resultOffset) {
      for(int i = 0; i < this.multIndirection.length; ++i) {
         int[][] mappingI = this.multIndirection[i];
         double r = (double)0.0F;

         for(int j = 0; j < mappingI.length; ++j) {
            r += (double)mappingI[j][0] * lhs[lhsOffset + mappingI[j][1]] * rhs[rhsOffset + mappingI[j][2]];
         }

         result[resultOffset + i] = r;
      }

   }

   public void divide(double[] lhs, int lhsOffset, double[] rhs, int rhsOffset, double[] result, int resultOffset) {
      double[] reciprocal = new double[this.getSize()];
      this.pow(rhs, lhsOffset, -1, reciprocal, 0);
      this.multiply(lhs, lhsOffset, reciprocal, 0, result, resultOffset);
   }

   public void remainder(double[] lhs, int lhsOffset, double[] rhs, int rhsOffset, double[] result, int resultOffset) {
      double rem = FastMath.IEEEremainder(lhs[lhsOffset], rhs[rhsOffset]);
      double k = FastMath.rint((lhs[lhsOffset] - rem) / rhs[rhsOffset]);
      result[resultOffset] = rem;

      for(int i = 1; i < this.getSize(); ++i) {
         result[resultOffset + i] = lhs[lhsOffset + i] - k * rhs[rhsOffset + i];
      }

   }

   public void pow(double a, double[] operand, int operandOffset, double[] result, int resultOffset) {
      double[] function = new double[1 + this.order];
      if (a == (double)0.0F) {
         if (operand[operandOffset] == (double)0.0F) {
            function[0] = (double)1.0F;
            double infinity = Double.POSITIVE_INFINITY;

            for(int i = 1; i < function.length; ++i) {
               infinity = -infinity;
               function[i] = infinity;
            }
         } else if (operand[operandOffset] < (double)0.0F) {
            Arrays.fill(function, Double.NaN);
         }
      } else {
         function[0] = FastMath.pow(a, operand[operandOffset]);
         double lnA = FastMath.log(a);

         for(int i = 1; i < function.length; ++i) {
            function[i] = lnA * function[i - 1];
         }
      }

      this.compose(operand, operandOffset, function, result, resultOffset);
   }

   public void pow(double[] operand, int operandOffset, double p, double[] result, int resultOffset) {
      double[] function = new double[1 + this.order];
      double xk = FastMath.pow(operand[operandOffset], p - (double)this.order);

      for(int i = this.order; i > 0; --i) {
         function[i] = xk;
         xk *= operand[operandOffset];
      }

      function[0] = xk;
      double coefficient = p;

      for(int i = 1; i <= this.order; ++i) {
         function[i] *= coefficient;
         coefficient *= p - (double)i;
      }

      this.compose(operand, operandOffset, function, result, resultOffset);
   }

   public void pow(double[] operand, int operandOffset, int n, double[] result, int resultOffset) {
      if (n == 0) {
         result[resultOffset] = (double)1.0F;
         Arrays.fill(result, resultOffset + 1, resultOffset + this.getSize(), (double)0.0F);
      } else {
         double[] function = new double[1 + this.order];
         if (n > 0) {
            int maxOrder = FastMath.min(this.order, n);
            double xk = FastMath.pow(operand[operandOffset], n - maxOrder);

            for(int i = maxOrder; i > 0; --i) {
               function[i] = xk;
               xk *= operand[operandOffset];
            }

            function[0] = xk;
         } else {
            double inv = (double)1.0F / operand[operandOffset];
            double xk = FastMath.pow(inv, -n);

            for(int i = 0; i <= this.order; ++i) {
               function[i] = xk;
               xk *= inv;
            }
         }

         double coefficient = (double)n;

         for(int i = 1; i <= this.order; ++i) {
            function[i] *= coefficient;
            coefficient *= (double)(n - i);
         }

         this.compose(operand, operandOffset, function, result, resultOffset);
      }
   }

   public void pow(double[] x, int xOffset, double[] y, int yOffset, double[] result, int resultOffset) {
      double[] logX = new double[this.getSize()];
      this.log(x, xOffset, logX, 0);
      double[] yLogX = new double[this.getSize()];
      this.multiply(logX, 0, y, yOffset, yLogX, 0);
      this.exp(yLogX, 0, result, resultOffset);
   }

   public void rootN(double[] operand, int operandOffset, int n, double[] result, int resultOffset) {
      double[] function = new double[1 + this.order];
      double xk;
      if (n == 2) {
         function[0] = FastMath.sqrt(operand[operandOffset]);
         xk = (double)0.5F / function[0];
      } else if (n == 3) {
         function[0] = FastMath.cbrt(operand[operandOffset]);
         xk = (double)1.0F / ((double)3.0F * function[0] * function[0]);
      } else {
         function[0] = FastMath.pow(operand[operandOffset], (double)1.0F / (double)n);
         xk = (double)1.0F / ((double)n * FastMath.pow(function[0], n - 1));
      }

      double nReciprocal = (double)1.0F / (double)n;
      double xReciprocal = (double)1.0F / operand[operandOffset];

      for(int i = 1; i <= this.order; ++i) {
         function[i] = xk;
         xk *= xReciprocal * (nReciprocal - (double)i);
      }

      this.compose(operand, operandOffset, function, result, resultOffset);
   }

   public void exp(double[] operand, int operandOffset, double[] result, int resultOffset) {
      double[] function = new double[1 + this.order];
      Arrays.fill(function, FastMath.exp(operand[operandOffset]));
      this.compose(operand, operandOffset, function, result, resultOffset);
   }

   public void expm1(double[] operand, int operandOffset, double[] result, int resultOffset) {
      double[] function = new double[1 + this.order];
      function[0] = FastMath.expm1(operand[operandOffset]);
      Arrays.fill(function, 1, 1 + this.order, FastMath.exp(operand[operandOffset]));
      this.compose(operand, operandOffset, function, result, resultOffset);
   }

   public void log(double[] operand, int operandOffset, double[] result, int resultOffset) {
      double[] function = new double[1 + this.order];
      function[0] = FastMath.log(operand[operandOffset]);
      if (this.order > 0) {
         double inv = (double)1.0F / operand[operandOffset];
         double xk = inv;

         for(int i = 1; i <= this.order; ++i) {
            function[i] = xk;
            xk *= (double)(-i) * inv;
         }
      }

      this.compose(operand, operandOffset, function, result, resultOffset);
   }

   public void log1p(double[] operand, int operandOffset, double[] result, int resultOffset) {
      double[] function = new double[1 + this.order];
      function[0] = FastMath.log1p(operand[operandOffset]);
      if (this.order > 0) {
         double inv = (double)1.0F / ((double)1.0F + operand[operandOffset]);
         double xk = inv;

         for(int i = 1; i <= this.order; ++i) {
            function[i] = xk;
            xk *= (double)(-i) * inv;
         }
      }

      this.compose(operand, operandOffset, function, result, resultOffset);
   }

   public void log10(double[] operand, int operandOffset, double[] result, int resultOffset) {
      double[] function = new double[1 + this.order];
      function[0] = FastMath.log10(operand[operandOffset]);
      if (this.order > 0) {
         double inv = (double)1.0F / operand[operandOffset];
         double xk = inv / FastMath.log((double)10.0F);

         for(int i = 1; i <= this.order; ++i) {
            function[i] = xk;
            xk *= (double)(-i) * inv;
         }
      }

      this.compose(operand, operandOffset, function, result, resultOffset);
   }

   public void cos(double[] operand, int operandOffset, double[] result, int resultOffset) {
      double[] function = new double[1 + this.order];
      function[0] = FastMath.cos(operand[operandOffset]);
      if (this.order > 0) {
         function[1] = -FastMath.sin(operand[operandOffset]);

         for(int i = 2; i <= this.order; ++i) {
            function[i] = -function[i - 2];
         }
      }

      this.compose(operand, operandOffset, function, result, resultOffset);
   }

   public void sin(double[] operand, int operandOffset, double[] result, int resultOffset) {
      double[] function = new double[1 + this.order];
      function[0] = FastMath.sin(operand[operandOffset]);
      if (this.order > 0) {
         function[1] = FastMath.cos(operand[operandOffset]);

         for(int i = 2; i <= this.order; ++i) {
            function[i] = -function[i - 2];
         }
      }

      this.compose(operand, operandOffset, function, result, resultOffset);
   }

   public void tan(double[] operand, int operandOffset, double[] result, int resultOffset) {
      double[] function = new double[1 + this.order];
      double t = FastMath.tan(operand[operandOffset]);
      function[0] = t;
      if (this.order > 0) {
         double[] p = new double[this.order + 2];
         p[1] = (double)1.0F;
         double t2 = t * t;

         for(int n = 1; n <= this.order; ++n) {
            double v = (double)0.0F;
            p[n + 1] = (double)n * p[n];

            for(int k = n + 1; k >= 0; k -= 2) {
               v = v * t2 + p[k];
               if (k > 2) {
                  p[k - 2] = (double)(k - 1) * p[k - 1] + (double)(k - 3) * p[k - 3];
               } else if (k == 2) {
                  p[0] = p[1];
               }
            }

            if ((n & 1) == 0) {
               v *= t;
            }

            function[n] = v;
         }
      }

      this.compose(operand, operandOffset, function, result, resultOffset);
   }

   public void acos(double[] operand, int operandOffset, double[] result, int resultOffset) {
      double[] function = new double[1 + this.order];
      double x = operand[operandOffset];
      function[0] = FastMath.acos(x);
      if (this.order > 0) {
         double[] p = new double[this.order];
         p[0] = (double)-1.0F;
         double x2 = x * x;
         double f = (double)1.0F / ((double)1.0F - x2);
         double coeff = FastMath.sqrt(f);
         function[1] = coeff * p[0];

         for(int n = 2; n <= this.order; ++n) {
            double v = (double)0.0F;
            p[n - 1] = (double)(n - 1) * p[n - 2];

            for(int k = n - 1; k >= 0; k -= 2) {
               v = v * x2 + p[k];
               if (k > 2) {
                  p[k - 2] = (double)(k - 1) * p[k - 1] + (double)(2 * n - k) * p[k - 3];
               } else if (k == 2) {
                  p[0] = p[1];
               }
            }

            if ((n & 1) == 0) {
               v *= x;
            }

            coeff *= f;
            function[n] = coeff * v;
         }
      }

      this.compose(operand, operandOffset, function, result, resultOffset);
   }

   public void asin(double[] operand, int operandOffset, double[] result, int resultOffset) {
      double[] function = new double[1 + this.order];
      double x = operand[operandOffset];
      function[0] = FastMath.asin(x);
      if (this.order > 0) {
         double[] p = new double[this.order];
         p[0] = (double)1.0F;
         double x2 = x * x;
         double f = (double)1.0F / ((double)1.0F - x2);
         double coeff = FastMath.sqrt(f);
         function[1] = coeff * p[0];

         for(int n = 2; n <= this.order; ++n) {
            double v = (double)0.0F;
            p[n - 1] = (double)(n - 1) * p[n - 2];

            for(int k = n - 1; k >= 0; k -= 2) {
               v = v * x2 + p[k];
               if (k > 2) {
                  p[k - 2] = (double)(k - 1) * p[k - 1] + (double)(2 * n - k) * p[k - 3];
               } else if (k == 2) {
                  p[0] = p[1];
               }
            }

            if ((n & 1) == 0) {
               v *= x;
            }

            coeff *= f;
            function[n] = coeff * v;
         }
      }

      this.compose(operand, operandOffset, function, result, resultOffset);
   }

   public void atan(double[] operand, int operandOffset, double[] result, int resultOffset) {
      double[] function = new double[1 + this.order];
      double x = operand[operandOffset];
      function[0] = FastMath.atan(x);
      if (this.order > 0) {
         double[] q = new double[this.order];
         q[0] = (double)1.0F;
         double x2 = x * x;
         double f = (double)1.0F / ((double)1.0F + x2);
         double coeff = f;
         function[1] = f * q[0];

         for(int n = 2; n <= this.order; ++n) {
            double v = (double)0.0F;
            q[n - 1] = (double)(-n) * q[n - 2];

            for(int k = n - 1; k >= 0; k -= 2) {
               v = v * x2 + q[k];
               if (k > 2) {
                  q[k - 2] = (double)(k - 1) * q[k - 1] + (double)(k - 1 - 2 * n) * q[k - 3];
               } else if (k == 2) {
                  q[0] = q[1];
               }
            }

            if ((n & 1) == 0) {
               v *= x;
            }

            coeff *= f;
            function[n] = coeff * v;
         }
      }

      this.compose(operand, operandOffset, function, result, resultOffset);
   }

   public void atan2(double[] y, int yOffset, double[] x, int xOffset, double[] result, int resultOffset) {
      double[] tmp1 = new double[this.getSize()];
      this.multiply(x, xOffset, x, xOffset, tmp1, 0);
      double[] tmp2 = new double[this.getSize()];
      this.multiply(y, yOffset, y, yOffset, tmp2, 0);
      this.add(tmp1, 0, tmp2, 0, tmp2, 0);
      this.rootN(tmp2, 0, 2, tmp1, 0);
      if (x[xOffset] >= (double)0.0F) {
         this.add(tmp1, 0, x, xOffset, tmp2, 0);
         this.divide(y, yOffset, tmp2, 0, tmp1, 0);
         this.atan(tmp1, 0, tmp2, 0);

         for(int i = 0; i < tmp2.length; ++i) {
            result[resultOffset + i] = (double)2.0F * tmp2[i];
         }
      } else {
         this.subtract(tmp1, 0, x, xOffset, tmp2, 0);
         this.divide(y, yOffset, tmp2, 0, tmp1, 0);
         this.atan(tmp1, 0, tmp2, 0);
         result[resultOffset] = (tmp2[0] <= (double)0.0F ? -Math.PI : Math.PI) - (double)2.0F * tmp2[0];

         for(int i = 1; i < tmp2.length; ++i) {
            result[resultOffset + i] = (double)-2.0F * tmp2[i];
         }
      }

      result[resultOffset] = FastMath.atan2(y[yOffset], x[xOffset]);
   }

   public void cosh(double[] operand, int operandOffset, double[] result, int resultOffset) {
      double[] function = new double[1 + this.order];
      function[0] = FastMath.cosh(operand[operandOffset]);
      if (this.order > 0) {
         function[1] = FastMath.sinh(operand[operandOffset]);

         for(int i = 2; i <= this.order; ++i) {
            function[i] = function[i - 2];
         }
      }

      this.compose(operand, operandOffset, function, result, resultOffset);
   }

   public void sinh(double[] operand, int operandOffset, double[] result, int resultOffset) {
      double[] function = new double[1 + this.order];
      function[0] = FastMath.sinh(operand[operandOffset]);
      if (this.order > 0) {
         function[1] = FastMath.cosh(operand[operandOffset]);

         for(int i = 2; i <= this.order; ++i) {
            function[i] = function[i - 2];
         }
      }

      this.compose(operand, operandOffset, function, result, resultOffset);
   }

   public void tanh(double[] operand, int operandOffset, double[] result, int resultOffset) {
      double[] function = new double[1 + this.order];
      double t = FastMath.tanh(operand[operandOffset]);
      function[0] = t;
      if (this.order > 0) {
         double[] p = new double[this.order + 2];
         p[1] = (double)1.0F;
         double t2 = t * t;

         for(int n = 1; n <= this.order; ++n) {
            double v = (double)0.0F;
            p[n + 1] = (double)(-n) * p[n];

            for(int k = n + 1; k >= 0; k -= 2) {
               v = v * t2 + p[k];
               if (k > 2) {
                  p[k - 2] = (double)(k - 1) * p[k - 1] - (double)(k - 3) * p[k - 3];
               } else if (k == 2) {
                  p[0] = p[1];
               }
            }

            if ((n & 1) == 0) {
               v *= t;
            }

            function[n] = v;
         }
      }

      this.compose(operand, operandOffset, function, result, resultOffset);
   }

   public void acosh(double[] operand, int operandOffset, double[] result, int resultOffset) {
      double[] function = new double[1 + this.order];
      double x = operand[operandOffset];
      function[0] = FastMath.acosh(x);
      if (this.order > 0) {
         double[] p = new double[this.order];
         p[0] = (double)1.0F;
         double x2 = x * x;
         double f = (double)1.0F / (x2 - (double)1.0F);
         double coeff = FastMath.sqrt(f);
         function[1] = coeff * p[0];

         for(int n = 2; n <= this.order; ++n) {
            double v = (double)0.0F;
            p[n - 1] = (double)(1 - n) * p[n - 2];

            for(int k = n - 1; k >= 0; k -= 2) {
               v = v * x2 + p[k];
               if (k > 2) {
                  p[k - 2] = (double)(1 - k) * p[k - 1] + (double)(k - 2 * n) * p[k - 3];
               } else if (k == 2) {
                  p[0] = -p[1];
               }
            }

            if ((n & 1) == 0) {
               v *= x;
            }

            coeff *= f;
            function[n] = coeff * v;
         }
      }

      this.compose(operand, operandOffset, function, result, resultOffset);
   }

   public void asinh(double[] operand, int operandOffset, double[] result, int resultOffset) {
      double[] function = new double[1 + this.order];
      double x = operand[operandOffset];
      function[0] = FastMath.asinh(x);
      if (this.order > 0) {
         double[] p = new double[this.order];
         p[0] = (double)1.0F;
         double x2 = x * x;
         double f = (double)1.0F / ((double)1.0F + x2);
         double coeff = FastMath.sqrt(f);
         function[1] = coeff * p[0];

         for(int n = 2; n <= this.order; ++n) {
            double v = (double)0.0F;
            p[n - 1] = (double)(1 - n) * p[n - 2];

            for(int k = n - 1; k >= 0; k -= 2) {
               v = v * x2 + p[k];
               if (k > 2) {
                  p[k - 2] = (double)(k - 1) * p[k - 1] + (double)(k - 2 * n) * p[k - 3];
               } else if (k == 2) {
                  p[0] = p[1];
               }
            }

            if ((n & 1) == 0) {
               v *= x;
            }

            coeff *= f;
            function[n] = coeff * v;
         }
      }

      this.compose(operand, operandOffset, function, result, resultOffset);
   }

   public void atanh(double[] operand, int operandOffset, double[] result, int resultOffset) {
      double[] function = new double[1 + this.order];
      double x = operand[operandOffset];
      function[0] = FastMath.atanh(x);
      if (this.order > 0) {
         double[] q = new double[this.order];
         q[0] = (double)1.0F;
         double x2 = x * x;
         double f = (double)1.0F / ((double)1.0F - x2);
         double coeff = f;
         function[1] = f * q[0];

         for(int n = 2; n <= this.order; ++n) {
            double v = (double)0.0F;
            q[n - 1] = (double)n * q[n - 2];

            for(int k = n - 1; k >= 0; k -= 2) {
               v = v * x2 + q[k];
               if (k > 2) {
                  q[k - 2] = (double)(k - 1) * q[k - 1] + (double)(2 * n - k + 1) * q[k - 3];
               } else if (k == 2) {
                  q[0] = q[1];
               }
            }

            if ((n & 1) == 0) {
               v *= x;
            }

            coeff *= f;
            function[n] = coeff * v;
         }
      }

      this.compose(operand, operandOffset, function, result, resultOffset);
   }

   public void compose(double[] operand, int operandOffset, double[] f, double[] result, int resultOffset) {
      for(int i = 0; i < this.compIndirection.length; ++i) {
         int[][] mappingI = this.compIndirection[i];
         double r = (double)0.0F;

         for(int j = 0; j < mappingI.length; ++j) {
            int[] mappingIJ = mappingI[j];
            double product = (double)mappingIJ[0] * f[mappingIJ[1]];

            for(int k = 2; k < mappingIJ.length; ++k) {
               product *= operand[operandOffset + mappingIJ[k]];
            }

            r += product;
         }

         result[resultOffset + i] = r;
      }

   }

   public double taylor(double[] ds, int dsOffset, double... delta) throws MathArithmeticException {
      double value = (double)0.0F;

      for(int i = this.getSize() - 1; i >= 0; --i) {
         int[] orders = this.getPartialDerivativeOrders(i);
         double term = ds[dsOffset + i];

         for(int k = 0; k < orders.length; ++k) {
            if (orders[k] > 0) {
               try {
                  term *= FastMath.pow(delta[k], orders[k]) / (double)CombinatoricsUtils.factorial(orders[k]);
               } catch (NotPositiveException e) {
                  throw new MathInternalError(e);
               }
            }
         }

         value += term;
      }

      return value;
   }

   public void checkCompatibility(DSCompiler compiler) throws DimensionMismatchException {
      if (this.parameters != compiler.parameters) {
         throw new DimensionMismatchException(this.parameters, compiler.parameters);
      } else if (this.order != compiler.order) {
         throw new DimensionMismatchException(this.order, compiler.order);
      }
   }
}
