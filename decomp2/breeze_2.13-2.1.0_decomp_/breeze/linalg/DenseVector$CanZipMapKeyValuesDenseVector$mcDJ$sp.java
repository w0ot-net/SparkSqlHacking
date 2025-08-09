package breeze.linalg;

import breeze.linalg.support.CanZipMapKeyValues$mcIDJ$sp;
import scala.Function3;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class DenseVector$CanZipMapKeyValuesDenseVector$mcDJ$sp extends DenseVector.CanZipMapKeyValuesDenseVector implements CanZipMapKeyValues$mcIDJ$sp {
   private final ClassTag evidence$17;

   public DenseVector create(final int length) {
      return this.create$mcD$sp(length);
   }

   public DenseVector create$mcD$sp(final int length) {
      return DenseVector$.MODULE$.apply$mDc$sp((double[])this.breeze$linalg$DenseVector$CanZipMapKeyValuesDenseVector$$evidence$17.newArray(length));
   }

   public DenseVector map(final DenseVector from, final DenseVector from2, final Function3 fn) {
      return this.map$mcDJ$sp(from, from2, fn);
   }

   public DenseVector map$mcDJ$sp(final DenseVector from, final DenseVector from2, final Function3 fn) {
      int left$macro$1 = from.length();
      int right$macro$2 = from2.length();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vector lengths must match!: ").append("from.length == from2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         DenseVector result = this.create$mcD$sp(from.length());

         for(int i = 0; i < from.length(); ++i) {
            result.data$mcD$sp()[i] = BoxesRunTime.unboxToDouble(fn.apply(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToLong(from.apply$mcJ$sp(i)), BoxesRunTime.boxToLong(from2.apply$mcJ$sp(i))));
         }

         return result;
      }
   }

   public DenseVector mapActive(final DenseVector from, final DenseVector from2, final Function3 fn) {
      return this.mapActive$mcDJ$sp(from, from2, fn);
   }

   public DenseVector mapActive$mcDJ$sp(final DenseVector from, final DenseVector from2, final Function3 fn) {
      return this.map$mcDJ$sp(from, from2, fn);
   }

   public DenseVector$CanZipMapKeyValuesDenseVector$mcDJ$sp(final ClassTag evidence$17) {
      super(evidence$17);
      this.evidence$17 = evidence$17;
   }
}
