package breeze.linalg.operators;

import breeze.linalg.HashVector;
import breeze.linalg.HashVector$;
import breeze.linalg.support.CanZipMapValues$mcDJ$sp;
import breeze.storage.Zero;
import scala.Function2;
import scala.reflect.ClassTag;

public class HashVector_GenericOps$CanZipMapValuesHashVector$mcDJ$sp extends HashVector_GenericOps.CanZipMapValuesHashVector implements CanZipMapValues$mcDJ$sp {
   public final Zero evidence$17$mcD$sp;
   private final ClassTag evidence$16;

   public HashVector create(final int length) {
      return this.create$mcD$sp(length);
   }

   public HashVector create$mcD$sp(final int length) {
      return HashVector$.MODULE$.zeros$mDc$sp(length, this.breeze$linalg$operators$HashVector_GenericOps$CanZipMapValuesHashVector$$evidence$16, this.evidence$17$mcD$sp);
   }

   public HashVector map(final HashVector from, final HashVector from2, final Function2 fn) {
      return this.map$mcDJ$sp(from, from2, fn);
   }

   public HashVector map$mcDJ$sp(final HashVector from, final HashVector from2, final Function2 fn) {
      int left$macro$1 = from.length();
      int right$macro$2 = from2.length();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vector lengths must match!: ").append("from.length == from2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         HashVector result = this.create$mcD$sp(from.length());

         for(int i = 0; i < from.length(); ++i) {
            result.update$mcD$sp(i, fn.apply$mcDJJ$sp(from.apply$mcJ$sp(i), from2.apply$mcJ$sp(i)));
         }

         return result;
      }
   }

   public HashVector mapActive(final HashVector from, final HashVector from2, final Function2 fn) {
      return this.mapActive$mcDJ$sp(from, from2, fn);
   }

   public HashVector mapActive$mcDJ$sp(final HashVector from, final HashVector from2, final Function2 fn) {
      return this.map$mcDJ$sp(from, from2, fn);
   }

   // $FF: synthetic method
   public HashVector_GenericOps breeze$linalg$operators$HashVector_GenericOps$CanZipMapValuesHashVector$mcDJ$sp$$$outer() {
      return this.$outer;
   }

   public HashVector_GenericOps$CanZipMapValuesHashVector$mcDJ$sp(final HashVector_GenericOps $outer, final ClassTag evidence$16, final Zero evidence$17$mcD$sp) {
      super(evidence$16, evidence$17$mcD$sp);
      this.evidence$17$mcD$sp = evidence$17$mcD$sp;
      this.evidence$16 = evidence$16;
   }
}
