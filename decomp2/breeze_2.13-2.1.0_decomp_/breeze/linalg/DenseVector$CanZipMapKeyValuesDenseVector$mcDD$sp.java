package breeze.linalg;

import breeze.linalg.support.CanZipMapKeyValues$mcIDD$sp;
import scala.Function3;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class DenseVector$CanZipMapKeyValuesDenseVector$mcDD$sp extends DenseVector.CanZipMapKeyValuesDenseVector implements CanZipMapKeyValues$mcIDD$sp {
   private final ClassTag evidence$17;

   public DenseVector create(final int length) {
      return this.create$mcD$sp(length);
   }

   public DenseVector create$mcD$sp(final int length) {
      return DenseVector$.MODULE$.apply$mDc$sp((double[])this.breeze$linalg$DenseVector$CanZipMapKeyValuesDenseVector$$evidence$17.newArray(length));
   }

   public DenseVector map(final DenseVector from, final DenseVector from2, final Function3 fn) {
      return this.map$mcDD$sp(from, from2, fn);
   }

   public DenseVector map$mcDD$sp(final DenseVector from, final DenseVector from2, final Function3 fn) {
      int left$macro$1 = from.length();
      int right$macro$2 = from2.length();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vector lengths must match!: ").append("from.length == from2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         DenseVector result = this.create$mcD$sp(from.length());

         for(int i = 0; i < from.length(); ++i) {
            result.data$mcD$sp()[i] = BoxesRunTime.unboxToDouble(fn.apply(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToDouble(from.apply$mcD$sp(i)), BoxesRunTime.boxToDouble(from2.apply$mcD$sp(i))));
         }

         return result;
      }
   }

   public DenseVector mapActive(final DenseVector from, final DenseVector from2, final Function3 fn) {
      return this.mapActive$mcDD$sp(from, from2, fn);
   }

   public DenseVector mapActive$mcDD$sp(final DenseVector from, final DenseVector from2, final Function3 fn) {
      return this.map$mcDD$sp(from, from2, fn);
   }

   public DenseVector$CanZipMapKeyValuesDenseVector$mcDD$sp(final ClassTag evidence$17) {
      super(evidence$17);
      this.evidence$17 = evidence$17;
   }
}
