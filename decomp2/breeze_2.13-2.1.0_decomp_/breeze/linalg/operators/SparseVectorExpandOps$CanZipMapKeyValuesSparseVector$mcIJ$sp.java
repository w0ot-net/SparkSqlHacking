package breeze.linalg.operators;

import breeze.linalg.SparseVector;
import breeze.linalg.SparseVector$;
import breeze.linalg.VectorBuilder;
import breeze.linalg.VectorBuilder$;
import breeze.linalg.VectorBuilder$mcI$sp;
import breeze.linalg.support.CanZipMapKeyValues$mcIIJ$sp;
import breeze.math.Semiring;
import breeze.storage.Zero;
import scala.Function3;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class SparseVectorExpandOps$CanZipMapKeyValuesSparseVector$mcIJ$sp extends SparseVectorExpandOps.CanZipMapKeyValuesSparseVector implements CanZipMapKeyValues$mcIIJ$sp {
   public final Zero evidence$15$mcI$sp;
   public final Semiring evidence$16$mcI$sp;
   private final ClassTag evidence$14;

   public SparseVector create(final int length) {
      return this.create$mcI$sp(length);
   }

   public SparseVector create$mcI$sp(final int length) {
      return SparseVector$.MODULE$.zeros$mIc$sp(length, this.breeze$linalg$operators$SparseVectorExpandOps$CanZipMapKeyValuesSparseVector$$evidence$14, this.evidence$15$mcI$sp);
   }

   public SparseVector map(final SparseVector from, final SparseVector from2, final Function3 fn) {
      return this.map$mcIJ$sp(from, from2, fn);
   }

   public SparseVector map$mcIJ$sp(final SparseVector from, final SparseVector from2, final Function3 fn) {
      int left$macro$1 = from.length();
      int right$macro$2 = from2.length();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vector lengths must match!: ").append("from.length == from2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         SparseVector result = this.create$mcI$sp(from.length());
         int index$macro$4 = 0;

         for(int limit$macro$6 = from.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
            result.update$mcI$sp(index$macro$4, BoxesRunTime.unboxToInt(fn.apply(BoxesRunTime.boxToInteger(index$macro$4), BoxesRunTime.boxToLong(from.apply$mcJ$sp(index$macro$4)), BoxesRunTime.boxToLong(from2.apply$mcJ$sp(index$macro$4)))));
         }

         return result;
      }
   }

   public SparseVector mapActive(final SparseVector from, final SparseVector from2, final Function3 fn) {
      return this.mapActive$mcIJ$sp(from, from2, fn);
   }

   public SparseVector mapActive$mcIJ$sp(final SparseVector from, final SparseVector from2, final Function3 fn) {
      int left$macro$1 = from.length();
      int right$macro$2 = from2.length();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vector lengths must match!: ").append("from.length == from2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         VectorBuilder vb = new VectorBuilder$mcI$sp(from.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), this.evidence$16$mcI$sp, this.breeze$linalg$operators$SparseVectorExpandOps$CanZipMapKeyValuesSparseVector$$evidence$14);
         int off1 = 0;

         int off2;
         for(off2 = 0; off1 < from.activeSize(); ++off1) {
            while(off2 < from2.activeSize() && from2.indexAt(off2) < from.indexAt(off1)) {
               int index = from2.indexAt(off2);
               vb.add$mcI$sp(index, BoxesRunTime.unboxToInt(fn.apply(BoxesRunTime.boxToInteger(index), BoxesRunTime.boxToLong(from.default$mcJ$sp()), BoxesRunTime.boxToLong(from2.valueAt$mcJ$sp(off2)))));
               ++off2;
            }

            if (off2 < from2.activeSize() && from.indexAt(off1) == from2.indexAt(off2)) {
               int index = from2.indexAt(off2);
               vb.add$mcI$sp(index, BoxesRunTime.unboxToInt(fn.apply(BoxesRunTime.boxToInteger(index), BoxesRunTime.boxToLong(from.valueAt$mcJ$sp(off1)), BoxesRunTime.boxToLong(from2.valueAt$mcJ$sp(off2)))));
               ++off2;
            } else {
               int index = from.indexAt(off1);
               vb.add$mcI$sp(index, BoxesRunTime.unboxToInt(fn.apply(BoxesRunTime.boxToInteger(index), BoxesRunTime.boxToLong(from.valueAt$mcJ$sp(off1)), BoxesRunTime.boxToLong(from2.default$mcJ$sp()))));
            }
         }

         while(off2 < from2.activeSize()) {
            int index = from2.indexAt(off2);
            vb.add$mcI$sp(index, BoxesRunTime.unboxToInt(fn.apply(BoxesRunTime.boxToInteger(index), BoxesRunTime.boxToLong(from.default$mcJ$sp()), BoxesRunTime.boxToLong(from2.valueAt$mcJ$sp(off2)))));
            ++off2;
         }

         return vb.toSparseVector$mcI$sp(true, true);
      }
   }

   // $FF: synthetic method
   public SparseVectorExpandOps breeze$linalg$operators$SparseVectorExpandOps$CanZipMapKeyValuesSparseVector$mcIJ$sp$$$outer() {
      return this.$outer;
   }

   public SparseVectorExpandOps$CanZipMapKeyValuesSparseVector$mcIJ$sp(final SparseVectorExpandOps $outer, final ClassTag evidence$14, final Zero evidence$15$mcI$sp, final Semiring evidence$16$mcI$sp) {
      super(evidence$14, evidence$15$mcI$sp, evidence$16$mcI$sp);
      this.evidence$15$mcI$sp = evidence$15$mcI$sp;
      this.evidence$16$mcI$sp = evidence$16$mcI$sp;
      this.evidence$14 = evidence$14;
   }
}
