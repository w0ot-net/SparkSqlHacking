package breeze.collection.immutable;

import java.lang.invoke.SerializedLambda;
import scala.collection.BuildFrom;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Builder;
import scala.collection.mutable.GrowableBuilder;
import scala.math.Ordering;

public final class Beam$ {
   public static final Beam$ MODULE$ = new Beam$();

   public Beam apply(final int maxSize, final Seq items, final Ordering evidence$1) {
      return new Beam(maxSize, items, evidence$1);
   }

   public BuildFrom canBuildFrom(final Ordering evidence$2) {
      return new BuildFrom(evidence$2) {
         private final Ordering evidence$2$1;

         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.apply$(this, from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.toFactory$(this, from);
         }

         public Beam fromSpecific(final Beam from, final IterableOnce it) {
            return (Beam)((Builder)this.newBuilder(from).$plus$plus$eq(it)).result();
         }

         public Builder newBuilder(final Beam from) {
            return (new GrowableBuilder(new breeze.collection.mutable.Beam(from.maxSize(), this.evidence$2$1))).mapResult((b) -> (new Beam(b.maxSize(), this.evidence$2$1)).$plus$plus(b));
         }

         public {
            this.evidence$2$1 = evidence$2$1;
            BuildFrom.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   private Beam$() {
   }
}
