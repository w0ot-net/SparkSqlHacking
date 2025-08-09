package breeze.linalg;

import breeze.linalg.support.CanZipMapKeyValues$mcIIJ$sp;
import scala.Function3;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class DenseVector$CanZipMapKeyValuesDenseVector$mcIJ$sp extends DenseVector.CanZipMapKeyValuesDenseVector implements CanZipMapKeyValues$mcIIJ$sp {
   private final ClassTag evidence$17;

   public DenseVector create(final int length) {
      return this.create$mcI$sp(length);
   }

   public DenseVector create$mcI$sp(final int length) {
      return DenseVector$.MODULE$.apply$mIc$sp((int[])this.breeze$linalg$DenseVector$CanZipMapKeyValuesDenseVector$$evidence$17.newArray(length));
   }

   public DenseVector map(final DenseVector from, final DenseVector from2, final Function3 fn) {
      return this.map$mcIJ$sp(from, from2, fn);
   }

   public DenseVector map$mcIJ$sp(final DenseVector from, final DenseVector from2, final Function3 fn) {
      int left$macro$1 = from.length();
      int right$macro$2 = from2.length();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vector lengths must match!: ").append("from.length == from2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         DenseVector result = this.create$mcI$sp(from.length());

         for(int i = 0; i < from.length(); ++i) {
            result.data$mcI$sp()[i] = BoxesRunTime.unboxToInt(fn.apply(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToLong(from.apply$mcJ$sp(i)), BoxesRunTime.boxToLong(from2.apply$mcJ$sp(i))));
         }

         return result;
      }
   }

   public DenseVector mapActive(final DenseVector from, final DenseVector from2, final Function3 fn) {
      return this.mapActive$mcIJ$sp(from, from2, fn);
   }

   public DenseVector mapActive$mcIJ$sp(final DenseVector from, final DenseVector from2, final Function3 fn) {
      return this.map$mcIJ$sp(from, from2, fn);
   }

   public DenseVector$CanZipMapKeyValuesDenseVector$mcIJ$sp(final ClassTag evidence$17) {
      super(evidence$17);
      this.evidence$17 = evidence$17;
   }
}
