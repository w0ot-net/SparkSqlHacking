package breeze.linalg.operators;

import breeze.linalg.HashVector;
import breeze.linalg.HashVector$;
import breeze.linalg.support.CanZipMapKeyValues$mcIID$sp;
import breeze.storage.Zero;
import scala.Function3;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class HashVector_GenericOps$CanZipMapKeyValuesHashVector$mcID$sp extends HashVector_GenericOps.CanZipMapKeyValuesHashVector implements CanZipMapKeyValues$mcIID$sp {
   public final Zero evidence$21$mcI$sp;
   private final ClassTag evidence$20;

   public HashVector create(final int length) {
      return this.create$mcI$sp(length);
   }

   public HashVector create$mcI$sp(final int length) {
      return HashVector$.MODULE$.zeros$mIc$sp(length, this.breeze$linalg$operators$HashVector_GenericOps$CanZipMapKeyValuesHashVector$$evidence$20, this.evidence$21$mcI$sp);
   }

   public HashVector map(final HashVector from, final HashVector from2, final Function3 fn) {
      return this.map$mcID$sp(from, from2, fn);
   }

   public HashVector map$mcID$sp(final HashVector from, final HashVector from2, final Function3 fn) {
      int left$macro$1 = from.length();
      int right$macro$2 = from2.length();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vector lengths must match!: ").append("from.length == from2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         HashVector result = this.create$mcI$sp(from.length());

         for(int i = 0; i < from.length(); ++i) {
            result.update$mcI$sp(i, BoxesRunTime.unboxToInt(fn.apply(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToDouble(from.apply$mcD$sp(i)), BoxesRunTime.boxToDouble(from2.apply$mcD$sp(i)))));
         }

         return result;
      }
   }

   public HashVector mapActive(final HashVector from, final HashVector from2, final Function3 fn) {
      return this.mapActive$mcID$sp(from, from2, fn);
   }

   public HashVector mapActive$mcID$sp(final HashVector from, final HashVector from2, final Function3 fn) {
      return this.map$mcID$sp(from, from2, fn);
   }

   // $FF: synthetic method
   public HashVector_GenericOps breeze$linalg$operators$HashVector_GenericOps$CanZipMapKeyValuesHashVector$mcID$sp$$$outer() {
      return this.$outer;
   }

   public HashVector_GenericOps$CanZipMapKeyValuesHashVector$mcID$sp(final HashVector_GenericOps $outer, final ClassTag evidence$20, final Zero evidence$21$mcI$sp) {
      super(evidence$20, evidence$21$mcI$sp);
      this.evidence$21$mcI$sp = evidence$21$mcI$sp;
      this.evidence$20 = evidence$20;
   }
}
