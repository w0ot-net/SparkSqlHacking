package breeze.linalg.operators;

import breeze.linalg.HashVector;
import breeze.linalg.HashVector$;
import breeze.linalg.support.CanZipMapValues$mcII$sp;
import breeze.storage.Zero;
import scala.Function2;
import scala.reflect.ClassTag;

public class HashVector_GenericOps$CanZipMapValuesHashVector$mcII$sp extends HashVector_GenericOps.CanZipMapValuesHashVector implements CanZipMapValues$mcII$sp {
   public final Zero evidence$17$mcI$sp;
   private final ClassTag evidence$16;

   public HashVector create(final int length) {
      return this.create$mcI$sp(length);
   }

   public HashVector create$mcI$sp(final int length) {
      return HashVector$.MODULE$.zeros$mIc$sp(length, this.breeze$linalg$operators$HashVector_GenericOps$CanZipMapValuesHashVector$$evidence$16, this.evidence$17$mcI$sp);
   }

   public HashVector map(final HashVector from, final HashVector from2, final Function2 fn) {
      return this.map$mcII$sp(from, from2, fn);
   }

   public HashVector map$mcII$sp(final HashVector from, final HashVector from2, final Function2 fn) {
      int left$macro$1 = from.length();
      int right$macro$2 = from2.length();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vector lengths must match!: ").append("from.length == from2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         HashVector result = this.create$mcI$sp(from.length());

         for(int i = 0; i < from.length(); ++i) {
            result.update$mcI$sp(i, fn.apply$mcIII$sp(from.apply$mcI$sp(i), from2.apply$mcI$sp(i)));
         }

         return result;
      }
   }

   public HashVector mapActive(final HashVector from, final HashVector from2, final Function2 fn) {
      return this.mapActive$mcII$sp(from, from2, fn);
   }

   public HashVector mapActive$mcII$sp(final HashVector from, final HashVector from2, final Function2 fn) {
      return this.map$mcII$sp(from, from2, fn);
   }

   // $FF: synthetic method
   public HashVector_GenericOps breeze$linalg$operators$HashVector_GenericOps$CanZipMapValuesHashVector$mcII$sp$$$outer() {
      return this.$outer;
   }

   public HashVector_GenericOps$CanZipMapValuesHashVector$mcII$sp(final HashVector_GenericOps $outer, final ClassTag evidence$16, final Zero evidence$17$mcI$sp) {
      super(evidence$16, evidence$17$mcI$sp);
      this.evidence$17$mcI$sp = evidence$17$mcI$sp;
      this.evidence$16 = evidence$16;
   }
}
