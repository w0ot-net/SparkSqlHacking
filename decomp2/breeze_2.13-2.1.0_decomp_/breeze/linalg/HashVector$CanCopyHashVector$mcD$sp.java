package breeze.linalg;

import breeze.storage.Zero;
import scala.reflect.ClassTag;

public class HashVector$CanCopyHashVector$mcD$sp extends HashVector.CanCopyHashVector {
   public final Zero evidence$15$mcD$sp;
   private final ClassTag evidence$14;

   public HashVector apply(final HashVector v1) {
      return this.apply$mcD$sp(v1);
   }

   public HashVector apply$mcD$sp(final HashVector v1) {
      return v1.copy$mcD$sp();
   }

   public HashVector$CanCopyHashVector$mcD$sp(final ClassTag evidence$14, final Zero evidence$15$mcD$sp) {
      super(evidence$14, evidence$15$mcD$sp);
      this.evidence$15$mcD$sp = evidence$15$mcD$sp;
      this.evidence$14 = evidence$14;
   }
}
