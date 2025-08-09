package breeze.linalg;

import breeze.storage.Zero;
import scala.reflect.ClassTag;

public class HashVector$CanCopyHashVector$mcI$sp extends HashVector.CanCopyHashVector {
   public final Zero evidence$15$mcI$sp;
   private final ClassTag evidence$14;

   public HashVector apply(final HashVector v1) {
      return this.apply$mcI$sp(v1);
   }

   public HashVector apply$mcI$sp(final HashVector v1) {
      return v1.copy$mcI$sp();
   }

   public HashVector$CanCopyHashVector$mcI$sp(final ClassTag evidence$14, final Zero evidence$15$mcI$sp) {
      super(evidence$14, evidence$15$mcI$sp);
      this.evidence$15$mcI$sp = evidence$15$mcI$sp;
      this.evidence$14 = evidence$14;
   }
}
