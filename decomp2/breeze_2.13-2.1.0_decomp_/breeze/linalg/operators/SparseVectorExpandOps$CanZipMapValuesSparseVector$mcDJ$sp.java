package breeze.linalg.operators;

import breeze.linalg.SparseVector;
import breeze.linalg.SparseVector$;
import breeze.linalg.VectorBuilder;
import breeze.linalg.VectorBuilder$;
import breeze.linalg.VectorBuilder$mcD$sp;
import breeze.linalg.support.CanZipMapValues$mcDJ$sp;
import breeze.math.Semiring;
import breeze.storage.Zero;
import scala.Function2;
import scala.Predef.;
import scala.reflect.ClassTag;

public class SparseVectorExpandOps$CanZipMapValuesSparseVector$mcDJ$sp extends SparseVectorExpandOps.CanZipMapValuesSparseVector implements CanZipMapValues$mcDJ$sp {
   public final Zero evidence$9$mcD$sp;
   public final Semiring evidence$10$mcD$sp;
   private final ClassTag evidence$8;

   public SparseVector create(final int length) {
      return this.create$mcD$sp(length);
   }

   public SparseVector create$mcD$sp(final int length) {
      return SparseVector$.MODULE$.zeros$mDc$sp(length, this.breeze$linalg$operators$SparseVectorExpandOps$CanZipMapValuesSparseVector$$evidence$8, this.evidence$9$mcD$sp);
   }

   public SparseVector map(final SparseVector from, final SparseVector from2, final Function2 fn) {
      return this.map$mcDJ$sp(from, from2, fn);
   }

   public SparseVector map$mcDJ$sp(final SparseVector from, final SparseVector from2, final Function2 fn) {
      int left$macro$1 = from.length();
      int right$macro$2 = from2.length();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vector lengths must match!: ").append("from.length == from2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         double zz = fn.apply$mcDJJ$sp(from.default$mcJ$sp(), from2.default$mcJ$sp());
         SparseVector var10000;
         if (zz != ((Zero).MODULE$.implicitly(this.evidence$9$mcD$sp)).zero$mcD$sp()) {
            SparseVector result = this.create$mcD$sp(from.length());

            for(int i = 0; i < from.length(); ++i) {
               result.update$mcD$sp(i, fn.apply$mcDJJ$sp(from.apply$mcJ$sp(i), from2.apply$mcJ$sp(i)));
            }

            var10000 = result;
         } else {
            VectorBuilder vb = new VectorBuilder$mcD$sp(from.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), this.evidence$10$mcD$sp, this.breeze$linalg$operators$SparseVectorExpandOps$CanZipMapValuesSparseVector$$evidence$8);
            int off1 = 0;

            int off2;
            for(off2 = 0; off1 < from.activeSize(); ++off1) {
               while(off2 < from2.activeSize() && from2.indexAt(off2) < from.indexAt(off1)) {
                  int index = from2.indexAt(off2);
                  vb.add$mcD$sp(index, fn.apply$mcDJJ$sp(from.default$mcJ$sp(), from2.valueAt$mcJ$sp(off2)));
                  ++off2;
               }

               if (off2 < from2.activeSize() && from.indexAt(off1) == from2.indexAt(off2)) {
                  int index = from2.indexAt(off2);
                  vb.add$mcD$sp(index, fn.apply$mcDJJ$sp(from.valueAt$mcJ$sp(off1), from2.valueAt$mcJ$sp(off2)));
                  ++off2;
               } else {
                  int index = from.indexAt(off1);
                  vb.add$mcD$sp(index, fn.apply$mcDJJ$sp(from.valueAt$mcJ$sp(off1), from2.default$mcJ$sp()));
               }
            }

            while(off2 < from2.activeSize()) {
               int index = from2.indexAt(off2);
               vb.add$mcD$sp(index, fn.apply$mcDJJ$sp(from.default$mcJ$sp(), from2.valueAt$mcJ$sp(off2)));
               ++off2;
            }

            var10000 = vb.toSparseVector$mcD$sp(true, true);
         }

         return var10000;
      }
   }

   // $FF: synthetic method
   public SparseVectorExpandOps breeze$linalg$operators$SparseVectorExpandOps$CanZipMapValuesSparseVector$mcDJ$sp$$$outer() {
      return this.$outer;
   }

   public SparseVectorExpandOps$CanZipMapValuesSparseVector$mcDJ$sp(final SparseVectorExpandOps $outer, final ClassTag evidence$8, final Zero evidence$9$mcD$sp, final Semiring evidence$10$mcD$sp) {
      super(evidence$8, evidence$9$mcD$sp, evidence$10$mcD$sp);
      this.evidence$9$mcD$sp = evidence$9$mcD$sp;
      this.evidence$10$mcD$sp = evidence$10$mcD$sp;
      this.evidence$8 = evidence$8;
   }
}
