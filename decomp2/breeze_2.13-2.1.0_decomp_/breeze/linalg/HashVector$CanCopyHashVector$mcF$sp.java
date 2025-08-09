package breeze.linalg;

import breeze.storage.Zero;
import scala.reflect.ClassTag;

public class HashVector$CanCopyHashVector$mcF$sp extends HashVector.CanCopyHashVector {
   public final Zero evidence$15$mcF$sp;
   private final ClassTag evidence$14;

   public HashVector apply(final HashVector v1) {
      return this.apply$mcF$sp(v1);
   }

   public HashVector apply$mcF$sp(final HashVector v1) {
      return v1.copy$mcF$sp();
   }

   public HashVector$CanCopyHashVector$mcF$sp(final ClassTag evidence$14, final Zero evidence$15$mcF$sp) {
      super(evidence$14, evidence$15$mcF$sp);
      this.evidence$15$mcF$sp = evidence$15$mcF$sp;
      this.evidence$14 = evidence$14;
   }
}
