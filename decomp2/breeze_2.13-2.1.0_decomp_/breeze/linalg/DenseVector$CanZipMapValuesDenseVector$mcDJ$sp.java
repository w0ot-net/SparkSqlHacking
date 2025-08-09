package breeze.linalg;

import breeze.linalg.support.CanZipMapValues$mcDJ$sp;
import scala.Function2;
import scala.reflect.ClassTag;

public class DenseVector$CanZipMapValuesDenseVector$mcDJ$sp extends DenseVector.CanZipMapValuesDenseVector implements CanZipMapValues$mcDJ$sp {
   private final ClassTag evidence$15;

   public DenseVector create(final int length) {
      return this.create$mcD$sp(length);
   }

   public DenseVector create$mcD$sp(final int length) {
      return DenseVector$.MODULE$.apply$mDc$sp((double[])this.breeze$linalg$DenseVector$CanZipMapValuesDenseVector$$evidence$15.newArray(length));
   }

   public DenseVector map(final DenseVector from, final DenseVector from2, final Function2 fn) {
      return this.map$mcDJ$sp(from, from2, fn);
   }

   public DenseVector map$mcDJ$sp(final DenseVector from, final DenseVector from2, final Function2 fn) {
      int left$macro$1 = from.length();
      int right$macro$2 = from2.length();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(56)).append("requirement failed: ").append("Vectors must have same length").append(": ").append("from.length == from2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         DenseVector result = this.create$mcD$sp(from.length());

         for(int i = 0; i < from.length(); ++i) {
            result.data$mcD$sp()[i] = fn.apply$mcDJJ$sp(from.apply$mcJ$sp(i), from2.apply$mcJ$sp(i));
         }

         return result;
      }
   }

   public DenseVector$CanZipMapValuesDenseVector$mcDJ$sp(final ClassTag evidence$15) {
      super(evidence$15);
      this.evidence$15 = evidence$15;
   }
}
