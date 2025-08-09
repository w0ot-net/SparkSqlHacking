package breeze.collection.compat;

import scala.collection.compat.immutable.package.;
import scala.collection.mutable.Builder;
import scala.reflect.ClassTag;

public final class package$ {
   public static final package$ MODULE$ = new package$();

   public Builder arraySeqBuilder(final ClassTag evidence$1) {
      return .MODULE$.ArraySeq().newBuilder(evidence$1);
   }

   private package$() {
   }
}
