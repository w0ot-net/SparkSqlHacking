package org.apache.commons.math3.analysis.interpolation;

import [Lorg.apache.commons.math3.FieldElement;;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public class FieldHermiteInterpolator {
   private final List abscissae = new ArrayList();
   private final List topDiagonal = new ArrayList();
   private final List bottomDiagonal = new ArrayList();

   public void addSamplePoint(FieldElement x, FieldElement[]... value) throws ZeroException, MathArithmeticException, DimensionMismatchException, NullArgumentException {
      MathUtils.checkNotNull(x);
      T factorial = (T)((FieldElement)x.getField().getOne());

      for(int i = 0; i < value.length; ++i) {
         T[] y = (T[])((FieldElement[])value[i].clone());
         if (i > 1) {
            factorial = (T)((FieldElement)factorial.multiply(i));
            T inv = (T)((FieldElement)factorial.reciprocal());

            for(int j = 0; j < y.length; ++j) {
               y[j] = (FieldElement)y[j].multiply(inv);
            }
         }

         int n = this.abscissae.size();
         this.bottomDiagonal.add(n - i, y);
         T[] bottom0 = y;

         for(int j = i; j < n; ++j) {
            T[] bottom1 = (T[])((FieldElement[])this.bottomDiagonal.get(n - (j + 1)));
            if (x.equals(this.abscissae.get(n - (j + 1)))) {
               throw new ZeroException(LocalizedFormats.DUPLICATED_ABSCISSA_DIVISION_BY_ZERO, new Object[]{x});
            }

            T inv = (T)((FieldElement)((FieldElement)x.subtract(this.abscissae.get(n - (j + 1)))).reciprocal());

            for(int k = 0; k < y.length; ++k) {
               bottom1[k] = (FieldElement)inv.multiply(bottom0[k].subtract(bottom1[k]));
            }

            bottom0 = bottom1;
         }

         this.topDiagonal.add(((FieldElement;)bottom0).clone());
         this.abscissae.add(x);
      }

   }

   public FieldElement[] value(FieldElement x) throws NoDataException, NullArgumentException {
      MathUtils.checkNotNull(x);
      if (this.abscissae.isEmpty()) {
         throw new NoDataException(LocalizedFormats.EMPTY_INTERPOLATION_SAMPLE);
      } else {
         T[] value = (T[])((FieldElement[])MathArrays.buildArray(x.getField(), ((FieldElement[])this.topDiagonal.get(0)).length));
         T valueCoeff = (T)((FieldElement)x.getField().getOne());

         for(int i = 0; i < this.topDiagonal.size(); ++i) {
            T[] dividedDifference = (T[])((FieldElement[])this.topDiagonal.get(i));

            for(int k = 0; k < value.length; ++k) {
               value[k] = (FieldElement)value[k].add(dividedDifference[k].multiply(valueCoeff));
            }

            T deltaX = (T)((FieldElement)x.subtract(this.abscissae.get(i)));
            valueCoeff = (T)((FieldElement)valueCoeff.multiply(deltaX));
         }

         return value;
      }
   }

   public FieldElement[][] derivatives(FieldElement x, int order) throws NoDataException, NullArgumentException {
      MathUtils.checkNotNull(x);
      if (this.abscissae.isEmpty()) {
         throw new NoDataException(LocalizedFormats.EMPTY_INTERPOLATION_SAMPLE);
      } else {
         T zero = (T)((FieldElement)x.getField().getZero());
         T one = (T)((FieldElement)x.getField().getOne());
         T[] tj = (T[])((FieldElement[])MathArrays.buildArray(x.getField(), order + 1));
         tj[0] = zero;

         for(int i = 0; i < order; ++i) {
            tj[i + 1] = (FieldElement)tj[i].add(one);
         }

         T[][] derivatives = (T[][])((FieldElement[][])MathArrays.buildArray(x.getField(), order + 1, ((FieldElement[])this.topDiagonal.get(0)).length));
         T[] valueCoeff = (T[])((FieldElement[])MathArrays.buildArray(x.getField(), order + 1));
         valueCoeff[0] = (FieldElement)x.getField().getOne();

         for(int i = 0; i < this.topDiagonal.size(); ++i) {
            T[] dividedDifference = (T[])((FieldElement[])this.topDiagonal.get(i));
            T deltaX = (T)((FieldElement)x.subtract(this.abscissae.get(i)));

            for(int j = order; j >= 0; --j) {
               for(int k = 0; k < derivatives[j].length; ++k) {
                  derivatives[j][k] = (FieldElement)derivatives[j][k].add(dividedDifference[k].multiply(valueCoeff[j]));
               }

               valueCoeff[j] = (FieldElement)valueCoeff[j].multiply(deltaX);
               if (j > 0) {
                  valueCoeff[j] = (FieldElement)valueCoeff[j].add(tj[j].multiply(valueCoeff[j - 1]));
               }
            }
         }

         return derivatives;
      }
   }
}
