package spire.syntax.std;

import cats.kernel.Order;
import scala.collection.immutable.IndexedSeq;
import spire.math.Searching$;

public final class IndexedSeqOps$mcF$sp extends IndexedSeqOps {
   private final IndexedSeq as;

   public int qsearch(final float a, final Order ev) {
      return this.qsearch$mcF$sp(a, ev);
   }

   public int qsearch$mcF$sp(final float a, final Order ev) {
      return Searching$.MODULE$.search$mFc$sp(this.spire$syntax$std$IndexedSeqOps$$as, a, ev);
   }

   public IndexedSeqOps$mcF$sp(final IndexedSeq as) {
      super(as);
      this.as = as;
   }
}
