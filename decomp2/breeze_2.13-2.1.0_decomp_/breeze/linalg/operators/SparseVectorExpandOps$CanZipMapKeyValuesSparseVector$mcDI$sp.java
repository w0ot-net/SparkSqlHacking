package breeze.linalg.operators;

import breeze.linalg.SparseVector;
import breeze.linalg.SparseVector$;
import breeze.linalg.VectorBuilder;
import breeze.linalg.VectorBuilder$;
import breeze.linalg.VectorBuilder$mcD$sp;
import breeze.linalg.support.CanZipMapKeyValues$mcIDI$sp;
import breeze.math.Semiring;
import breeze.storage.Zero;
import scala.Function3;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class SparseVectorExpandOps$CanZipMapKeyValuesSparseVector$mcDI$sp extends SparseVectorExpandOps.CanZipMapKeyValuesSparseVector implements CanZipMapKeyValues$mcIDI$sp {
   public final Zero evidence$15$mcD$sp;
   public final Semiring evidence$16$mcD$sp;
   private final ClassTag evidence$14;

   public SparseVector create(final int length) {
      return this.create$mcD$sp(length);
   }

   public SparseVector create$mcD$sp(final int length) {
      return SparseVector$.MODULE$.zeros$mDc$sp(length, this.breeze$linalg$operators$SparseVectorExpandOps$CanZipMapKeyValuesSparseVector$$evidence$14, this.evidence$15$mcD$sp);
   }

   public SparseVector map(final SparseVector from, final SparseVector from2, final Function3 fn) {
      return this.map$mcDI$sp(from, from2, fn);
   }

   public SparseVector map$mcDI$sp(final SparseVector from, final SparseVector from2, final Function3 fn) {
      int left$macro$1 = from.length();
      int right$macro$2 = from2.length();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vector lengths must match!: ").append("from.length == from2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         SparseVector result = this.create$mcD$sp(from.length());
         int index$macro$4 = 0;

         for(int limit$macro$6 = from.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
            result.update$mcD$sp(index$macro$4, BoxesRunTime.unboxToDouble(fn.apply(BoxesRunTime.boxToInteger(index$macro$4), BoxesRunTime.boxToInteger(from.apply$mcI$sp(index$macro$4)), BoxesRunTime.boxToInteger(from2.apply$mcI$sp(index$macro$4)))));
         }

         return result;
      }
   }

   public SparseVector mapActive(final SparseVector from, final SparseVector from2, final Function3 fn) {
      return this.mapActive$mcDI$sp(from, from2, fn);
   }

   public SparseVector mapActive$mcDI$sp(final SparseVector from, final SparseVector from2, final Function3 fn) {
      int left$macro$1 = from.length();
      int right$macro$2 = from2.length();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vector lengths must match!: ").append("from.length == from2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         VectorBuilder vb = new VectorBuilder$mcD$sp(from.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), this.evidence$16$mcD$sp, this.breeze$linalg$operators$SparseVectorExpandOps$CanZipMapKeyValuesSparseVector$$evidence$14);
         int off1 = 0;

         int off2;
         for(off2 = 0; off1 < from.activeSize(); ++off1) {
            while(off2 < from2.activeSize() && from2.indexAt(off2) < from.indexAt(off1)) {
               int index = from2.indexAt(off2);
               vb.add$mcD$sp(index, BoxesRunTime.unboxToDouble(fn.apply(BoxesRunTime.boxToInteger(index), BoxesRunTime.boxToInteger(from.default$mcI$sp()), BoxesRunTime.boxToInteger(from2.valueAt$mcI$sp(off2)))));
               ++off2;
            }

            if (off2 < from2.activeSize() && from.indexAt(off1) == from2.indexAt(off2)) {
               int index = from2.indexAt(off2);
               vb.add$mcD$sp(index, BoxesRunTime.unboxToDouble(fn.apply(BoxesRunTime.boxToInteger(index), BoxesRunTime.boxToInteger(from.valueAt$mcI$sp(off1)), BoxesRunTime.boxToInteger(from2.valueAt$mcI$sp(off2)))));
               ++off2;
            } else {
               int index = from.indexAt(off1);
               vb.add$mcD$sp(index, BoxesRunTime.unboxToDouble(fn.apply(BoxesRunTime.boxToInteger(index), BoxesRunTime.boxToInteger(from.valueAt$mcI$sp(off1)), BoxesRunTime.boxToInteger(from2.default$mcI$sp()))));
            }
         }

         while(off2 < from2.activeSize()) {
            int index = from2.indexAt(off2);
            vb.add$mcD$sp(index, BoxesRunTime.unboxToDouble(fn.apply(BoxesRunTime.boxToInteger(index), BoxesRunTime.boxToInteger(from.default$mcI$sp()), BoxesRunTime.boxToInteger(from2.valueAt$mcI$sp(off2)))));
            ++off2;
         }

         return vb.toSparseVector$mcD$sp(true, true);
      }
   }

   // $FF: synthetic method
   public SparseVectorExpandOps breeze$linalg$operators$SparseVectorExpandOps$CanZipMapKeyValuesSparseVector$mcDI$sp$$$outer() {
      return this.$outer;
   }

   public SparseVectorExpandOps$CanZipMapKeyValuesSparseVector$mcDI$sp(final SparseVectorExpandOps $outer, final ClassTag evidence$14, final Zero evidence$15$mcD$sp, final Semiring evidence$16$mcD$sp) {
      super(evidence$14, evidence$15$mcD$sp, evidence$16$mcD$sp);
      this.evidence$15$mcD$sp = evidence$15$mcD$sp;
      this.evidence$16$mcD$sp = evidence$16$mcD$sp;
      this.evidence$14 = evidence$14;
   }
}
