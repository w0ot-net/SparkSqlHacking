package breeze.linalg;

import breeze.linalg.support.CanZipMapValues$mcIF$sp;
import scala.Function2;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class DenseVector$CanZipMapValuesDenseVector$mcIF$sp extends DenseVector.CanZipMapValuesDenseVector implements CanZipMapValues$mcIF$sp {
   private final ClassTag evidence$15;

   public DenseVector create(final int length) {
      return this.create$mcI$sp(length);
   }

   public DenseVector create$mcI$sp(final int length) {
      return DenseVector$.MODULE$.apply$mIc$sp((int[])this.breeze$linalg$DenseVector$CanZipMapValuesDenseVector$$evidence$15.newArray(length));
   }

   public DenseVector map(final DenseVector from, final DenseVector from2, final Function2 fn) {
      return this.map$mcIF$sp(from, from2, fn);
   }

   public DenseVector map$mcIF$sp(final DenseVector from, final DenseVector from2, final Function2 fn) {
      int left$macro$1 = from.length();
      int right$macro$2 = from2.length();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(56)).append("requirement failed: ").append("Vectors must have same length").append(": ").append("from.length == from2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         DenseVector result = this.create$mcI$sp(from.length());

         for(int i = 0; i < from.length(); ++i) {
            result.data$mcI$sp()[i] = BoxesRunTime.unboxToInt(fn.apply(BoxesRunTime.boxToFloat(from.apply$mcF$sp(i)), BoxesRunTime.boxToFloat(from2.apply$mcF$sp(i))));
         }

         return result;
      }
   }

   public DenseVector$CanZipMapValuesDenseVector$mcIF$sp(final ClassTag evidence$15) {
      super(evidence$15);
      this.evidence$15 = evidence$15;
   }
}
