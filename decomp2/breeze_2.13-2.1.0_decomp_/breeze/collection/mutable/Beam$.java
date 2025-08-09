package breeze.collection.mutable;

import java.io.Serializable;
import scala.collection.BuildFrom;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.Iterable.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Builder;
import scala.collection.mutable.GrowableBuilder;
import scala.math.Ordering;
import scala.runtime.ModuleSerializationProxy;

public final class Beam$ implements Serializable {
   public static final Beam$ MODULE$ = new Beam$();
   private static final Beam.BeamResult NothingEvicted;

   static {
      NothingEvicted = new Beam.Added((Iterable).MODULE$.empty());
   }

   public Beam.BeamResult NothingEvicted() {
      return NothingEvicted;
   }

   public BuildFrom canBuildFrom(final Ordering evidence$1) {
      return new BuildFrom(evidence$1) {
         private final Ordering evidence$1$1;

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
            return new GrowableBuilder(new Beam(from.maxSize(), this.evidence$1$1));
         }

         public {
            this.evidence$1$1 = evidence$1$1;
            BuildFrom.$init$(this);
         }
      };
   }

   public Beam apply(final int maxSize, final Seq xs, final Ordering evidence$2) {
      return (Beam)(new Beam(maxSize, evidence$2)).$plus$plus$eq(xs);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Beam$.class);
   }

   private Beam$() {
   }
}
