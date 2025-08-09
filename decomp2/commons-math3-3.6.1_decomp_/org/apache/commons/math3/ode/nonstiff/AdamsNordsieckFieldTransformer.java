package org.apache.commons.math3.ode.nonstiff;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.ArrayFieldVector;
import org.apache.commons.math3.linear.FieldDecompositionSolver;
import org.apache.commons.math3.linear.FieldLUDecomposition;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.FieldVector;
import org.apache.commons.math3.util.MathArrays;

public class AdamsNordsieckFieldTransformer {
   private static final Map CACHE = new HashMap();
   private final Field field;
   private final Array2DRowFieldMatrix update;
   private final RealFieldElement[] c1;

   private AdamsNordsieckFieldTransformer(Field field, int n) {
      this.field = field;
      int rows = n - 1;
      FieldMatrix<T> bigP = this.buildP(rows);
      FieldDecompositionSolver<T> pSolver = (new FieldLUDecomposition(bigP)).getSolver();
      T[] u = (T[])((RealFieldElement[])MathArrays.buildArray(field, rows));
      Arrays.fill(u, field.getOne());
      this.c1 = (RealFieldElement[])pSolver.solve((FieldVector)(new ArrayFieldVector(u, false))).toArray();
      T[][] shiftedP = (T[][])((RealFieldElement[][])bigP.getData());

      for(int i = shiftedP.length - 1; i > 0; --i) {
         shiftedP[i] = shiftedP[i - 1];
      }

      shiftedP[0] = (RealFieldElement[])MathArrays.buildArray(field, rows);
      Arrays.fill(shiftedP[0], field.getZero());
      this.update = new Array2DRowFieldMatrix(pSolver.solve((FieldMatrix)(new Array2DRowFieldMatrix(shiftedP, false))).getData());
   }

   public static AdamsNordsieckFieldTransformer getInstance(Field field, int nSteps) {
      synchronized(CACHE) {
         Map<Field<? extends RealFieldElement<?>>, AdamsNordsieckFieldTransformer<? extends RealFieldElement<?>>> map = (Map)CACHE.get(nSteps);
         if (map == null) {
            map = new HashMap();
            CACHE.put(nSteps, map);
         }

         AdamsNordsieckFieldTransformer t = (AdamsNordsieckFieldTransformer)map.get(field);
         if (t == null) {
            t = new AdamsNordsieckFieldTransformer(field, nSteps);
            map.put(field, t);
         }

         return t;
      }
   }

   private FieldMatrix buildP(int rows) {
      T[][] pData = (T[][])((RealFieldElement[][])MathArrays.buildArray(this.field, rows, rows));

      for(int i = 1; i <= pData.length; ++i) {
         T[] pI = (T[])pData[i - 1];
         int factor = -i;
         T aj = (T)((RealFieldElement)((RealFieldElement)this.field.getZero()).add((double)factor));

         for(int j = 1; j <= pI.length; ++j) {
            pI[j - 1] = (RealFieldElement)aj.multiply(j + 1);
            aj = (T)((RealFieldElement)aj.multiply(factor));
         }
      }

      return new Array2DRowFieldMatrix(pData, false);
   }

   public Array2DRowFieldMatrix initializeHighOrderDerivatives(RealFieldElement h, RealFieldElement[] t, RealFieldElement[][] y, RealFieldElement[][] yDot) {
      T[][] a = (T[][])((RealFieldElement[][])MathArrays.buildArray(this.field, this.c1.length + 1, this.c1.length + 1));
      T[][] b = (T[][])((RealFieldElement[][])MathArrays.buildArray(this.field, this.c1.length + 1, y[0].length));
      T[] y0 = (T[])y[0];
      T[] yDot0 = (T[])yDot[0];

      for(int i = 1; i < y.length; ++i) {
         T di = (T)((RealFieldElement)t[i].subtract(t[0]));
         T ratio = (T)((RealFieldElement)di.divide(h));
         T dikM1Ohk = (T)((RealFieldElement)h.reciprocal());
         T[] aI = (T[])a[2 * i - 2];
         T[] aDotI = (T[])(2 * i - 1 < a.length ? a[2 * i - 1] : null);

         for(int j = 0; j < aI.length; ++j) {
            dikM1Ohk = (T)((RealFieldElement)dikM1Ohk.multiply(ratio));
            aI[j] = (RealFieldElement)di.multiply(dikM1Ohk);
            if (aDotI != null) {
               aDotI[j] = (RealFieldElement)dikM1Ohk.multiply(j + 2);
            }
         }

         T[] yI = (T[])y[i];
         T[] yDotI = (T[])yDot[i];
         T[] bI = (T[])b[2 * i - 2];
         T[] bDotI = (T[])(2 * i - 1 < b.length ? b[2 * i - 1] : null);

         for(int j = 0; j < yI.length; ++j) {
            bI[j] = (RealFieldElement)((RealFieldElement)yI[j].subtract(y0[j])).subtract(di.multiply(yDot0[j]));
            if (bDotI != null) {
               bDotI[j] = (RealFieldElement)yDotI[j].subtract(yDot0[j]);
            }
         }
      }

      FieldLUDecomposition<T> decomposition = new FieldLUDecomposition(new Array2DRowFieldMatrix(a, false));
      FieldMatrix<T> x = decomposition.getSolver().solve((FieldMatrix)(new Array2DRowFieldMatrix(b, false)));
      Array2DRowFieldMatrix<T> truncatedX = new Array2DRowFieldMatrix(this.field, x.getRowDimension() - 1, x.getColumnDimension());

      for(int i = 0; i < truncatedX.getRowDimension(); ++i) {
         for(int j = 0; j < truncatedX.getColumnDimension(); ++j) {
            truncatedX.setEntry(i, j, x.getEntry(i, j));
         }
      }

      return truncatedX;
   }

   public Array2DRowFieldMatrix updateHighOrderDerivativesPhase1(Array2DRowFieldMatrix highOrder) {
      return this.update.multiply(highOrder);
   }

   public void updateHighOrderDerivativesPhase2(RealFieldElement[] start, RealFieldElement[] end, Array2DRowFieldMatrix highOrder) {
      T[][] data = (T[][])((RealFieldElement[][])highOrder.getDataRef());

      for(int i = 0; i < data.length; ++i) {
         T[] dataI = (T[])data[i];
         T c1I = (T)this.c1[i];

         for(int j = 0; j < dataI.length; ++j) {
            dataI[j] = (RealFieldElement)dataI[j].add(c1I.multiply(start[j].subtract(end[j])));
         }
      }

   }
}
