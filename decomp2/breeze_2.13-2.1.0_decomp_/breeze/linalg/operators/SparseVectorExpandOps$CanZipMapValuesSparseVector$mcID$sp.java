package breeze.linalg.operators;

import breeze.linalg.SparseVector;
import breeze.linalg.SparseVector$;
import breeze.linalg.VectorBuilder;
import breeze.linalg.VectorBuilder$;
import breeze.linalg.VectorBuilder$mcI$sp;
import breeze.linalg.support.CanZipMapValues$mcID$sp;
import breeze.math.Semiring;
import breeze.storage.Zero;
import scala.Function2;
import scala.Predef.;
import scala.reflect.ClassTag;

public class SparseVectorExpandOps$CanZipMapValuesSparseVector$mcID$sp extends SparseVectorExpandOps.CanZipMapValuesSparseVector implements CanZipMapValues$mcID$sp {
   public final Zero evidence$9$mcI$sp;
   public final Semiring evidence$10$mcI$sp;
   private final ClassTag evidence$8;

   public SparseVector create(final int length) {
      return this.create$mcI$sp(length);
   }

   public SparseVector create$mcI$sp(final int length) {
      return SparseVector$.MODULE$.zeros$mIc$sp(length, this.breeze$linalg$operators$SparseVectorExpandOps$CanZipMapValuesSparseVector$$evidence$8, this.evidence$9$mcI$sp);
   }

   public SparseVector map(final SparseVector from, final SparseVector from2, final Function2 fn) {
      return this.map$mcID$sp(from, from2, fn);
   }

   public SparseVector map$mcID$sp(final SparseVector from, final SparseVector from2, final Function2 fn) {
      int left$macro$1 = from.length();
      int right$macro$2 = from2.length();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vector lengths must match!: ").append("from.length == from2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         int zz = fn.apply$mcIDD$sp(from.default$mcD$sp(), from2.default$mcD$sp());
         SparseVector var10000;
         if (zz != ((Zero).MODULE$.implicitly(this.evidence$9$mcI$sp)).zero$mcI$sp()) {
            SparseVector result = this.create$mcI$sp(from.length());

            for(int i = 0; i < from.length(); ++i) {
               result.update$mcI$sp(i, fn.apply$mcIDD$sp(from.apply$mcD$sp(i), from2.apply$mcD$sp(i)));
            }

            var10000 = result;
         } else {
            VectorBuilder vb = new VectorBuilder$mcI$sp(from.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), this.evidence$10$mcI$sp, this.breeze$linalg$operators$SparseVectorExpandOps$CanZipMapValuesSparseVector$$evidence$8);
            int off1 = 0;

            int off2;
            for(off2 = 0; off1 < from.activeSize(); ++off1) {
               while(off2 < from2.activeSize() && from2.indexAt(off2) < from.indexAt(off1)) {
                  int index = from2.indexAt(off2);
                  vb.add$mcI$sp(index, fn.apply$mcIDD$sp(from.default$mcD$sp(), from2.valueAt$mcD$sp(off2)));
                  ++off2;
               }

               if (off2 < from2.activeSize() && from.indexAt(off1) == from2.indexAt(off2)) {
                  int index = from2.indexAt(off2);
                  vb.add$mcI$sp(index, fn.apply$mcIDD$sp(from.valueAt$mcD$sp(off1), from2.valueAt$mcD$sp(off2)));
                  ++off2;
               } else {
                  int index = from.indexAt(off1);
                  vb.add$mcI$sp(index, fn.apply$mcIDD$sp(from.valueAt$mcD$sp(off1), from2.default$mcD$sp()));
               }
            }

            while(off2 < from2.activeSize()) {
               int index = from2.indexAt(off2);
               vb.add$mcI$sp(index, fn.apply$mcIDD$sp(from.default$mcD$sp(), from2.valueAt$mcD$sp(off2)));
               ++off2;
            }

            var10000 = vb.toSparseVector$mcI$sp(true, true);
         }

         return var10000;
      }
   }

   // $FF: synthetic method
   public SparseVectorExpandOps breeze$linalg$operators$SparseVectorExpandOps$CanZipMapValuesSparseVector$mcID$sp$$$outer() {
      return this.$outer;
   }

   public SparseVectorExpandOps$CanZipMapValuesSparseVector$mcID$sp(final SparseVectorExpandOps $outer, final ClassTag evidence$8, final Zero evidence$9$mcI$sp, final Semiring evidence$10$mcI$sp) {
      super(evidence$8, evidence$9$mcI$sp, evidence$10$mcI$sp);
      this.evidence$9$mcI$sp = evidence$9$mcI$sp;
      this.evidence$10$mcI$sp = evidence$10$mcI$sp;
      this.evidence$8 = evidence$8;
   }
}
