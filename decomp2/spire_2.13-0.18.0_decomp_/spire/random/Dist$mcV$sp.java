package spire.random;

import algebra.ring.Field;
import algebra.ring.Rig;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.Tuple2;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.immutable.LazyList;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Stream;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArrayBuffer.;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public interface Dist$mcV$sp extends Dist {
   default void fill(final Generator gen, final BoxedUnit[] arr) {
      this.fill$mcV$sp(gen, arr);
   }

   default void fill$mcV$sp(final Generator gen, final BoxedUnit[] arr) {
      for(int i = 0; i < arr.length; ++i) {
         this.apply$mcV$sp(gen);
         arr[i] = BoxedUnit.UNIT;
      }

   }

   default Dist map(final Function1 f) {
      return this.map$mcV$sp(f);
   }

   default Dist map$mcV$sp(final Function1 f) {
      return new DistFromGen((g) -> {
         this.apply$mcV$sp(g);
         return f.apply(BoxedUnit.UNIT);
      });
   }

   default Dist flatMap(final Function1 f) {
      return this.flatMap$mcV$sp(f);
   }

   default Dist flatMap$mcV$sp(final Function1 f) {
      return new DistFromGen((g) -> {
         this.apply$mcV$sp(g);
         return ((Dist)f.apply(BoxedUnit.UNIT)).apply(g);
      });
   }

   default Dist filter(final Function1 pred) {
      return this.filter$mcV$sp(pred);
   }

   default Dist filter$mcV$sp(final Function1 pred) {
      return new Dist$mcV$sp(pred) {
         // $FF: synthetic field
         private final Dist$mcV$sp $outer;
         private final Function1 pred$41;

         public void fill(final Generator gen, final BoxedUnit[] arr) {
            Dist$mcV$sp.super.fill(gen, arr);
         }

         public void fill$mcV$sp(final Generator gen, final BoxedUnit[] arr) {
            Dist$mcV$sp.super.fill$mcV$sp(gen, arr);
         }

         public final Dist map(final Function1 f) {
            return Dist$mcV$sp.super.map(f);
         }

         public final Dist map$mcV$sp(final Function1 f) {
            return Dist$mcV$sp.super.map$mcV$sp(f);
         }

         public final Dist flatMap(final Function1 f) {
            return Dist$mcV$sp.super.flatMap(f);
         }

         public final Dist flatMap$mcV$sp(final Function1 f) {
            return Dist$mcV$sp.super.flatMap$mcV$sp(f);
         }

         public final Dist filter(final Function1 pred) {
            return Dist$mcV$sp.super.filter(pred);
         }

         public final Dist filter$mcV$sp(final Function1 pred) {
            return Dist$mcV$sp.super.filter$mcV$sp(pred);
         }

         public final Dist given(final Function1 pred) {
            return Dist$mcV$sp.super.given(pred);
         }

         public final Dist given$mcV$sp(final Function1 pred) {
            return Dist$mcV$sp.super.given$mcV$sp(pred);
         }

         public Dist until(final Function1 pred) {
            return Dist$mcV$sp.super.until(pred);
         }

         public Dist until$mcV$sp(final Function1 pred) {
            return Dist$mcV$sp.super.until$mcV$sp(pred);
         }

         public Dist foldn(final Object init, final int n, final Function2 f) {
            return Dist$mcV$sp.super.foldn(init, n, f);
         }

         public Dist foldn$mcV$sp(final Object init, final int n, final Function2 f) {
            return Dist$mcV$sp.super.foldn$mcV$sp(init, n, f);
         }

         public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
            return Dist$mcV$sp.super.unfold(init, f, pred);
         }

         public Dist unfold$mcV$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist$mcV$sp.super.unfold$mcV$sp(init, f, pred);
         }

         public Dist pack(final int n, final ClassTag ct) {
            return Dist$mcV$sp.super.pack(n, ct);
         }

         public Dist pack$mcV$sp(final int n, final ClassTag ct) {
            return Dist$mcV$sp.super.pack$mcV$sp(n, ct);
         }

         public Dist iterate(final int n, final Function1 f) {
            return Dist$mcV$sp.super.iterate(n, f);
         }

         public Dist iterate$mcV$sp(final int n, final Function1 f) {
            return Dist$mcV$sp.super.iterate$mcV$sp(n, f);
         }

         public Dist iterateUntil(final Function1 pred, final Function1 f) {
            return Dist$mcV$sp.super.iterateUntil(pred, f);
         }

         public Dist iterateUntil$mcV$sp(final Function1 pred, final Function1 f) {
            return Dist$mcV$sp.super.iterateUntil$mcV$sp(pred, f);
         }

         public final Dist zip(final Dist that) {
            return Dist$mcV$sp.super.zip(that);
         }

         public final Dist zip$mcV$sp(final Dist that) {
            return Dist$mcV$sp.super.zip$mcV$sp(that);
         }

         public Dist zipWith(final Dist that, final Function2 f) {
            return Dist$mcV$sp.super.zipWith(that, f);
         }

         public Dist zipWith$mcV$sp(final Dist that, final Function2 f) {
            return Dist$mcV$sp.super.zipWith$mcV$sp(that, f);
         }

         public final int count(final Function1 pred, final int n, final Generator gen) {
            return Dist$mcV$sp.super.count(pred, n, gen);
         }

         public final int count$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist$mcV$sp.super.count$mcV$sp(pred, n, gen);
         }

         public double pr(final Function1 pred, final int n, final Generator gen) {
            return Dist$mcV$sp.super.pr(pred, n, gen);
         }

         public double pr$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist$mcV$sp.super.pr$mcV$sp(pred, n, gen);
         }

         public void sum(final int n, final Generator gen, final Rig alg) {
            Dist$mcV$sp.super.sum(n, gen, alg);
         }

         public void sum$mcV$sp(final int n, final Generator gen, final Rig alg) {
            Dist$mcV$sp.super.sum$mcV$sp(n, gen, alg);
         }

         public void ev(final int n, final Generator gen, final Field alg) {
            Dist$mcV$sp.super.ev(n, gen, alg);
         }

         public void ev$mcV$sp(final int n, final Generator gen, final Field alg) {
            Dist$mcV$sp.super.ev$mcV$sp(n, gen, alg);
         }

         public boolean apply$mcZ$sp(final Generator gen) {
            return Dist.apply$mcZ$sp$(this, gen);
         }

         public byte apply$mcB$sp(final Generator gen) {
            return Dist.apply$mcB$sp$(this, gen);
         }

         public char apply$mcC$sp(final Generator gen) {
            return Dist.apply$mcC$sp$(this, gen);
         }

         public double apply$mcD$sp(final Generator gen) {
            return Dist.apply$mcD$sp$(this, gen);
         }

         public float apply$mcF$sp(final Generator gen) {
            return Dist.apply$mcF$sp$(this, gen);
         }

         public int apply$mcI$sp(final Generator gen) {
            return Dist.apply$mcI$sp$(this, gen);
         }

         public long apply$mcJ$sp(final Generator gen) {
            return Dist.apply$mcJ$sp$(this, gen);
         }

         public short apply$mcS$sp(final Generator gen) {
            return Dist.apply$mcS$sp$(this, gen);
         }

         public void fill$mcZ$sp(final Generator gen, final boolean[] arr) {
            Dist.fill$mcZ$sp$(this, gen, arr);
         }

         public void fill$mcB$sp(final Generator gen, final byte[] arr) {
            Dist.fill$mcB$sp$(this, gen, arr);
         }

         public void fill$mcC$sp(final Generator gen, final char[] arr) {
            Dist.fill$mcC$sp$(this, gen, arr);
         }

         public void fill$mcD$sp(final Generator gen, final double[] arr) {
            Dist.fill$mcD$sp$(this, gen, arr);
         }

         public void fill$mcF$sp(final Generator gen, final float[] arr) {
            Dist.fill$mcF$sp$(this, gen, arr);
         }

         public void fill$mcI$sp(final Generator gen, final int[] arr) {
            Dist.fill$mcI$sp$(this, gen, arr);
         }

         public void fill$mcJ$sp(final Generator gen, final long[] arr) {
            Dist.fill$mcJ$sp$(this, gen, arr);
         }

         public void fill$mcS$sp(final Generator gen, final short[] arr) {
            Dist.fill$mcS$sp$(this, gen, arr);
         }

         public Dist map$mcZ$sp(final Function1 f) {
            return Dist.map$mcZ$sp$(this, f);
         }

         public Dist map$mcB$sp(final Function1 f) {
            return Dist.map$mcB$sp$(this, f);
         }

         public Dist map$mcC$sp(final Function1 f) {
            return Dist.map$mcC$sp$(this, f);
         }

         public Dist map$mcD$sp(final Function1 f) {
            return Dist.map$mcD$sp$(this, f);
         }

         public Dist map$mcF$sp(final Function1 f) {
            return Dist.map$mcF$sp$(this, f);
         }

         public Dist map$mcI$sp(final Function1 f) {
            return Dist.map$mcI$sp$(this, f);
         }

         public Dist map$mcJ$sp(final Function1 f) {
            return Dist.map$mcJ$sp$(this, f);
         }

         public Dist map$mcS$sp(final Function1 f) {
            return Dist.map$mcS$sp$(this, f);
         }

         public Dist flatMap$mcZ$sp(final Function1 f) {
            return Dist.flatMap$mcZ$sp$(this, f);
         }

         public Dist flatMap$mcB$sp(final Function1 f) {
            return Dist.flatMap$mcB$sp$(this, f);
         }

         public Dist flatMap$mcC$sp(final Function1 f) {
            return Dist.flatMap$mcC$sp$(this, f);
         }

         public Dist flatMap$mcD$sp(final Function1 f) {
            return Dist.flatMap$mcD$sp$(this, f);
         }

         public Dist flatMap$mcF$sp(final Function1 f) {
            return Dist.flatMap$mcF$sp$(this, f);
         }

         public Dist flatMap$mcI$sp(final Function1 f) {
            return Dist.flatMap$mcI$sp$(this, f);
         }

         public Dist flatMap$mcJ$sp(final Function1 f) {
            return Dist.flatMap$mcJ$sp$(this, f);
         }

         public Dist flatMap$mcS$sp(final Function1 f) {
            return Dist.flatMap$mcS$sp$(this, f);
         }

         public Dist filter$mcZ$sp(final Function1 pred) {
            return Dist.filter$mcZ$sp$(this, pred);
         }

         public Dist filter$mcB$sp(final Function1 pred) {
            return Dist.filter$mcB$sp$(this, pred);
         }

         public Dist filter$mcC$sp(final Function1 pred) {
            return Dist.filter$mcC$sp$(this, pred);
         }

         public Dist filter$mcD$sp(final Function1 pred) {
            return Dist.filter$mcD$sp$(this, pred);
         }

         public Dist filter$mcF$sp(final Function1 pred) {
            return Dist.filter$mcF$sp$(this, pred);
         }

         public Dist filter$mcI$sp(final Function1 pred) {
            return Dist.filter$mcI$sp$(this, pred);
         }

         public Dist filter$mcJ$sp(final Function1 pred) {
            return Dist.filter$mcJ$sp$(this, pred);
         }

         public Dist filter$mcS$sp(final Function1 pred) {
            return Dist.filter$mcS$sp$(this, pred);
         }

         public Dist given$mcZ$sp(final Function1 pred) {
            return Dist.given$mcZ$sp$(this, pred);
         }

         public Dist given$mcB$sp(final Function1 pred) {
            return Dist.given$mcB$sp$(this, pred);
         }

         public Dist given$mcC$sp(final Function1 pred) {
            return Dist.given$mcC$sp$(this, pred);
         }

         public Dist given$mcD$sp(final Function1 pred) {
            return Dist.given$mcD$sp$(this, pred);
         }

         public Dist given$mcF$sp(final Function1 pred) {
            return Dist.given$mcF$sp$(this, pred);
         }

         public Dist given$mcI$sp(final Function1 pred) {
            return Dist.given$mcI$sp$(this, pred);
         }

         public Dist given$mcJ$sp(final Function1 pred) {
            return Dist.given$mcJ$sp$(this, pred);
         }

         public Dist given$mcS$sp(final Function1 pred) {
            return Dist.given$mcS$sp$(this, pred);
         }

         public Dist until$mcZ$sp(final Function1 pred) {
            return Dist.until$mcZ$sp$(this, pred);
         }

         public Dist until$mcB$sp(final Function1 pred) {
            return Dist.until$mcB$sp$(this, pred);
         }

         public Dist until$mcC$sp(final Function1 pred) {
            return Dist.until$mcC$sp$(this, pred);
         }

         public Dist until$mcD$sp(final Function1 pred) {
            return Dist.until$mcD$sp$(this, pred);
         }

         public Dist until$mcF$sp(final Function1 pred) {
            return Dist.until$mcF$sp$(this, pred);
         }

         public Dist until$mcI$sp(final Function1 pred) {
            return Dist.until$mcI$sp$(this, pred);
         }

         public Dist until$mcJ$sp(final Function1 pred) {
            return Dist.until$mcJ$sp$(this, pred);
         }

         public Dist until$mcS$sp(final Function1 pred) {
            return Dist.until$mcS$sp$(this, pred);
         }

         public Dist foldn$mcZ$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcZ$sp$(this, init, n, f);
         }

         public Dist foldn$mcB$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcB$sp$(this, init, n, f);
         }

         public Dist foldn$mcC$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcC$sp$(this, init, n, f);
         }

         public Dist foldn$mcD$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcD$sp$(this, init, n, f);
         }

         public Dist foldn$mcF$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcF$sp$(this, init, n, f);
         }

         public Dist foldn$mcI$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcI$sp$(this, init, n, f);
         }

         public Dist foldn$mcJ$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcJ$sp$(this, init, n, f);
         }

         public Dist foldn$mcS$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcS$sp$(this, init, n, f);
         }

         public Dist unfold$mcZ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcZ$sp$(this, init, f, pred);
         }

         public Dist unfold$mcB$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcB$sp$(this, init, f, pred);
         }

         public Dist unfold$mcC$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcC$sp$(this, init, f, pred);
         }

         public Dist unfold$mcD$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcD$sp$(this, init, f, pred);
         }

         public Dist unfold$mcF$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcF$sp$(this, init, f, pred);
         }

         public Dist unfold$mcI$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcI$sp$(this, init, f, pred);
         }

         public Dist unfold$mcJ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcJ$sp$(this, init, f, pred);
         }

         public Dist unfold$mcS$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcS$sp$(this, init, f, pred);
         }

         public Dist pack$mcZ$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcZ$sp$(this, n, ct);
         }

         public Dist pack$mcB$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcB$sp$(this, n, ct);
         }

         public Dist pack$mcC$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcC$sp$(this, n, ct);
         }

         public Dist pack$mcD$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcD$sp$(this, n, ct);
         }

         public Dist pack$mcF$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcF$sp$(this, n, ct);
         }

         public Dist pack$mcI$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcI$sp$(this, n, ct);
         }

         public Dist pack$mcJ$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcJ$sp$(this, n, ct);
         }

         public Dist pack$mcS$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcS$sp$(this, n, ct);
         }

         public Dist repeat(final int n, final Factory cbf) {
            return Dist.repeat$(this, n, cbf);
         }

         public Dist iterate$mcZ$sp(final int n, final Function1 f) {
            return Dist.iterate$mcZ$sp$(this, n, f);
         }

         public Dist iterate$mcB$sp(final int n, final Function1 f) {
            return Dist.iterate$mcB$sp$(this, n, f);
         }

         public Dist iterate$mcC$sp(final int n, final Function1 f) {
            return Dist.iterate$mcC$sp$(this, n, f);
         }

         public Dist iterate$mcD$sp(final int n, final Function1 f) {
            return Dist.iterate$mcD$sp$(this, n, f);
         }

         public Dist iterate$mcF$sp(final int n, final Function1 f) {
            return Dist.iterate$mcF$sp$(this, n, f);
         }

         public Dist iterate$mcI$sp(final int n, final Function1 f) {
            return Dist.iterate$mcI$sp$(this, n, f);
         }

         public Dist iterate$mcJ$sp(final int n, final Function1 f) {
            return Dist.iterate$mcJ$sp$(this, n, f);
         }

         public Dist iterate$mcS$sp(final int n, final Function1 f) {
            return Dist.iterate$mcS$sp$(this, n, f);
         }

         public Dist iterateUntil$mcZ$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcZ$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcB$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcB$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcC$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcC$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcD$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcD$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcF$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcF$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcI$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcI$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcJ$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcJ$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcS$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcS$sp$(this, pred, f);
         }

         public Dist zip$mcZ$sp(final Dist that) {
            return Dist.zip$mcZ$sp$(this, that);
         }

         public Dist zip$mcB$sp(final Dist that) {
            return Dist.zip$mcB$sp$(this, that);
         }

         public Dist zip$mcC$sp(final Dist that) {
            return Dist.zip$mcC$sp$(this, that);
         }

         public Dist zip$mcD$sp(final Dist that) {
            return Dist.zip$mcD$sp$(this, that);
         }

         public Dist zip$mcF$sp(final Dist that) {
            return Dist.zip$mcF$sp$(this, that);
         }

         public Dist zip$mcI$sp(final Dist that) {
            return Dist.zip$mcI$sp$(this, that);
         }

         public Dist zip$mcJ$sp(final Dist that) {
            return Dist.zip$mcJ$sp$(this, that);
         }

         public Dist zip$mcS$sp(final Dist that) {
            return Dist.zip$mcS$sp$(this, that);
         }

         public Dist zipWith$mcZ$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcZ$sp$(this, that, f);
         }

         public Dist zipWith$mcB$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcB$sp$(this, that, f);
         }

         public Dist zipWith$mcC$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcC$sp$(this, that, f);
         }

         public Dist zipWith$mcD$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcD$sp$(this, that, f);
         }

         public Dist zipWith$mcF$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcF$sp$(this, that, f);
         }

         public Dist zipWith$mcI$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcI$sp$(this, that, f);
         }

         public Dist zipWith$mcJ$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcJ$sp$(this, that, f);
         }

         public Dist zipWith$mcS$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcS$sp$(this, that, f);
         }

         public final Iterator toIterator(final Generator gen) {
            return Dist.toIterator$(this, gen);
         }

         public final LazyList toLazyList(final Generator gen) {
            return Dist.toLazyList$(this, gen);
         }

         /** @deprecated */
         public final Stream toStream(final Generator gen) {
            return Dist.toStream$(this, gen);
         }

         public Iterable sample(final int n, final Generator gen, final Factory cbf) {
            return Dist.sample$(this, n, gen, cbf);
         }

         public int count$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcZ$sp$(this, pred, n, gen);
         }

         public int count$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcB$sp$(this, pred, n, gen);
         }

         public int count$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcC$sp$(this, pred, n, gen);
         }

         public int count$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcD$sp$(this, pred, n, gen);
         }

         public int count$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcF$sp$(this, pred, n, gen);
         }

         public int count$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcI$sp$(this, pred, n, gen);
         }

         public int count$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcJ$sp$(this, pred, n, gen);
         }

         public int count$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcS$sp$(this, pred, n, gen);
         }

         public double pr$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcZ$sp$(this, pred, n, gen);
         }

         public double pr$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcB$sp$(this, pred, n, gen);
         }

         public double pr$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcC$sp$(this, pred, n, gen);
         }

         public double pr$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcD$sp$(this, pred, n, gen);
         }

         public double pr$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcF$sp$(this, pred, n, gen);
         }

         public double pr$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcI$sp$(this, pred, n, gen);
         }

         public double pr$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcJ$sp$(this, pred, n, gen);
         }

         public double pr$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcS$sp$(this, pred, n, gen);
         }

         public boolean sum$mcZ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcZ$sp$(this, n, gen, alg);
         }

         public byte sum$mcB$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcB$sp$(this, n, gen, alg);
         }

         public char sum$mcC$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcC$sp$(this, n, gen, alg);
         }

         public double sum$mcD$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcD$sp$(this, n, gen, alg);
         }

         public float sum$mcF$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcF$sp$(this, n, gen, alg);
         }

         public int sum$mcI$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcI$sp$(this, n, gen, alg);
         }

         public long sum$mcJ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcJ$sp$(this, n, gen, alg);
         }

         public short sum$mcS$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcS$sp$(this, n, gen, alg);
         }

         public boolean ev$mcZ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcZ$sp$(this, n, gen, alg);
         }

         public byte ev$mcB$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcB$sp$(this, n, gen, alg);
         }

         public char ev$mcC$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcC$sp$(this, n, gen, alg);
         }

         public double ev$mcD$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcD$sp$(this, n, gen, alg);
         }

         public float ev$mcF$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcF$sp$(this, n, gen, alg);
         }

         public int ev$mcI$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcI$sp$(this, n, gen, alg);
         }

         public long ev$mcJ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcJ$sp$(this, n, gen, alg);
         }

         public short ev$mcS$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcS$sp$(this, n, gen, alg);
         }

         public Map histogram(final int n, final Generator gen) {
            return Dist.histogram$(this, n, gen);
         }

         public Map rawHistogram(final int n, final Generator gen) {
            return Dist.rawHistogram$(this, n, gen);
         }

         public final void apply(final Generator gen) {
            this.apply$mcV$sp(gen);
         }

         public void apply$mcV$sp(final Generator gen) {
            this.loop$49(gen);
         }

         private final void loop$49(final Generator gen$27) {
            BoxedUnit a;
            do {
               this.$outer.apply$mcV$sp(gen$27);
               a = BoxedUnit.UNIT;
            } while(!BoxesRunTime.unboxToBoolean(this.pred$41.apply(a)));

         }

         public {
            if (Dist$mcV$sp.this == null) {
               throw null;
            } else {
               this.$outer = Dist$mcV$sp.this;
               this.pred$41 = pred$41;
               Dist.$init$(this);
            }
         }
      };
   }

   default Dist given(final Function1 pred) {
      return this.given$mcV$sp(pred);
   }

   default Dist given$mcV$sp(final Function1 pred) {
      return this.filter$mcV$sp(pred);
   }

   default Dist until(final Function1 pred) {
      return this.until$mcV$sp(pred);
   }

   default Dist until$mcV$sp(final Function1 pred) {
      return new DistFromGen((g) -> {
         this.apply$mcV$sp(g);
         return this.loop$50(g, BoxedUnit.UNIT, .MODULE$.empty(), pred);
      });
   }

   default Dist foldn(final Object init, final int n, final Function2 f) {
      return this.foldn$mcV$sp(init, n, f);
   }

   default Dist foldn$mcV$sp(final Object init, final int n, final Function2 f) {
      return new DistFromGen((g) -> this.loop$51(g, n, init, f));
   }

   default Dist unfold(final Object init, final Function2 f, final Function1 pred) {
      return this.unfold$mcV$sp(init, f, pred);
   }

   default Dist unfold$mcV$sp(final Object init, final Function2 f, final Function1 pred) {
      return new DistFromGen((g) -> this.loop$52(g, init, pred, f));
   }

   default Dist pack(final int n, final ClassTag ct) {
      return this.pack$mcV$sp(n, ct);
   }

   default Dist pack$mcV$sp(final int n, final ClassTag ct) {
      return new Dist(ct, n) {
         // $FF: synthetic field
         private final Dist$mcV$sp $outer;
         private final ClassTag ct$9;
         private final int n$24;

         public boolean apply$mcZ$sp(final Generator gen) {
            return Dist.apply$mcZ$sp$(this, gen);
         }

         public byte apply$mcB$sp(final Generator gen) {
            return Dist.apply$mcB$sp$(this, gen);
         }

         public char apply$mcC$sp(final Generator gen) {
            return Dist.apply$mcC$sp$(this, gen);
         }

         public double apply$mcD$sp(final Generator gen) {
            return Dist.apply$mcD$sp$(this, gen);
         }

         public float apply$mcF$sp(final Generator gen) {
            return Dist.apply$mcF$sp$(this, gen);
         }

         public int apply$mcI$sp(final Generator gen) {
            return Dist.apply$mcI$sp$(this, gen);
         }

         public long apply$mcJ$sp(final Generator gen) {
            return Dist.apply$mcJ$sp$(this, gen);
         }

         public short apply$mcS$sp(final Generator gen) {
            return Dist.apply$mcS$sp$(this, gen);
         }

         public void apply$mcV$sp(final Generator gen) {
            Dist.apply$mcV$sp$(this, gen);
         }

         public void fill(final Generator gen, final Object arr) {
            Dist.fill$(this, gen, arr);
         }

         public void fill$mcZ$sp(final Generator gen, final boolean[] arr) {
            Dist.fill$mcZ$sp$(this, gen, arr);
         }

         public void fill$mcB$sp(final Generator gen, final byte[] arr) {
            Dist.fill$mcB$sp$(this, gen, arr);
         }

         public void fill$mcC$sp(final Generator gen, final char[] arr) {
            Dist.fill$mcC$sp$(this, gen, arr);
         }

         public void fill$mcD$sp(final Generator gen, final double[] arr) {
            Dist.fill$mcD$sp$(this, gen, arr);
         }

         public void fill$mcF$sp(final Generator gen, final float[] arr) {
            Dist.fill$mcF$sp$(this, gen, arr);
         }

         public void fill$mcI$sp(final Generator gen, final int[] arr) {
            Dist.fill$mcI$sp$(this, gen, arr);
         }

         public void fill$mcJ$sp(final Generator gen, final long[] arr) {
            Dist.fill$mcJ$sp$(this, gen, arr);
         }

         public void fill$mcS$sp(final Generator gen, final short[] arr) {
            Dist.fill$mcS$sp$(this, gen, arr);
         }

         public void fill$mcV$sp(final Generator gen, final BoxedUnit[] arr) {
            Dist.fill$mcV$sp$(this, gen, arr);
         }

         public Dist map(final Function1 f) {
            return Dist.map$(this, f);
         }

         public Dist map$mcZ$sp(final Function1 f) {
            return Dist.map$mcZ$sp$(this, f);
         }

         public Dist map$mcB$sp(final Function1 f) {
            return Dist.map$mcB$sp$(this, f);
         }

         public Dist map$mcC$sp(final Function1 f) {
            return Dist.map$mcC$sp$(this, f);
         }

         public Dist map$mcD$sp(final Function1 f) {
            return Dist.map$mcD$sp$(this, f);
         }

         public Dist map$mcF$sp(final Function1 f) {
            return Dist.map$mcF$sp$(this, f);
         }

         public Dist map$mcI$sp(final Function1 f) {
            return Dist.map$mcI$sp$(this, f);
         }

         public Dist map$mcJ$sp(final Function1 f) {
            return Dist.map$mcJ$sp$(this, f);
         }

         public Dist map$mcS$sp(final Function1 f) {
            return Dist.map$mcS$sp$(this, f);
         }

         public Dist map$mcV$sp(final Function1 f) {
            return Dist.map$mcV$sp$(this, f);
         }

         public Dist flatMap(final Function1 f) {
            return Dist.flatMap$(this, f);
         }

         public Dist flatMap$mcZ$sp(final Function1 f) {
            return Dist.flatMap$mcZ$sp$(this, f);
         }

         public Dist flatMap$mcB$sp(final Function1 f) {
            return Dist.flatMap$mcB$sp$(this, f);
         }

         public Dist flatMap$mcC$sp(final Function1 f) {
            return Dist.flatMap$mcC$sp$(this, f);
         }

         public Dist flatMap$mcD$sp(final Function1 f) {
            return Dist.flatMap$mcD$sp$(this, f);
         }

         public Dist flatMap$mcF$sp(final Function1 f) {
            return Dist.flatMap$mcF$sp$(this, f);
         }

         public Dist flatMap$mcI$sp(final Function1 f) {
            return Dist.flatMap$mcI$sp$(this, f);
         }

         public Dist flatMap$mcJ$sp(final Function1 f) {
            return Dist.flatMap$mcJ$sp$(this, f);
         }

         public Dist flatMap$mcS$sp(final Function1 f) {
            return Dist.flatMap$mcS$sp$(this, f);
         }

         public Dist flatMap$mcV$sp(final Function1 f) {
            return Dist.flatMap$mcV$sp$(this, f);
         }

         public Dist filter(final Function1 pred) {
            return Dist.filter$(this, pred);
         }

         public Dist filter$mcZ$sp(final Function1 pred) {
            return Dist.filter$mcZ$sp$(this, pred);
         }

         public Dist filter$mcB$sp(final Function1 pred) {
            return Dist.filter$mcB$sp$(this, pred);
         }

         public Dist filter$mcC$sp(final Function1 pred) {
            return Dist.filter$mcC$sp$(this, pred);
         }

         public Dist filter$mcD$sp(final Function1 pred) {
            return Dist.filter$mcD$sp$(this, pred);
         }

         public Dist filter$mcF$sp(final Function1 pred) {
            return Dist.filter$mcF$sp$(this, pred);
         }

         public Dist filter$mcI$sp(final Function1 pred) {
            return Dist.filter$mcI$sp$(this, pred);
         }

         public Dist filter$mcJ$sp(final Function1 pred) {
            return Dist.filter$mcJ$sp$(this, pred);
         }

         public Dist filter$mcS$sp(final Function1 pred) {
            return Dist.filter$mcS$sp$(this, pred);
         }

         public Dist filter$mcV$sp(final Function1 pred) {
            return Dist.filter$mcV$sp$(this, pred);
         }

         public Dist given(final Function1 pred) {
            return Dist.given$(this, pred);
         }

         public Dist given$mcZ$sp(final Function1 pred) {
            return Dist.given$mcZ$sp$(this, pred);
         }

         public Dist given$mcB$sp(final Function1 pred) {
            return Dist.given$mcB$sp$(this, pred);
         }

         public Dist given$mcC$sp(final Function1 pred) {
            return Dist.given$mcC$sp$(this, pred);
         }

         public Dist given$mcD$sp(final Function1 pred) {
            return Dist.given$mcD$sp$(this, pred);
         }

         public Dist given$mcF$sp(final Function1 pred) {
            return Dist.given$mcF$sp$(this, pred);
         }

         public Dist given$mcI$sp(final Function1 pred) {
            return Dist.given$mcI$sp$(this, pred);
         }

         public Dist given$mcJ$sp(final Function1 pred) {
            return Dist.given$mcJ$sp$(this, pred);
         }

         public Dist given$mcS$sp(final Function1 pred) {
            return Dist.given$mcS$sp$(this, pred);
         }

         public Dist given$mcV$sp(final Function1 pred) {
            return Dist.given$mcV$sp$(this, pred);
         }

         public Dist until(final Function1 pred) {
            return Dist.until$(this, pred);
         }

         public Dist until$mcZ$sp(final Function1 pred) {
            return Dist.until$mcZ$sp$(this, pred);
         }

         public Dist until$mcB$sp(final Function1 pred) {
            return Dist.until$mcB$sp$(this, pred);
         }

         public Dist until$mcC$sp(final Function1 pred) {
            return Dist.until$mcC$sp$(this, pred);
         }

         public Dist until$mcD$sp(final Function1 pred) {
            return Dist.until$mcD$sp$(this, pred);
         }

         public Dist until$mcF$sp(final Function1 pred) {
            return Dist.until$mcF$sp$(this, pred);
         }

         public Dist until$mcI$sp(final Function1 pred) {
            return Dist.until$mcI$sp$(this, pred);
         }

         public Dist until$mcJ$sp(final Function1 pred) {
            return Dist.until$mcJ$sp$(this, pred);
         }

         public Dist until$mcS$sp(final Function1 pred) {
            return Dist.until$mcS$sp$(this, pred);
         }

         public Dist until$mcV$sp(final Function1 pred) {
            return Dist.until$mcV$sp$(this, pred);
         }

         public Dist foldn(final Object init, final int n, final Function2 f) {
            return Dist.foldn$(this, init, n, f);
         }

         public Dist foldn$mcZ$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcZ$sp$(this, init, n, f);
         }

         public Dist foldn$mcB$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcB$sp$(this, init, n, f);
         }

         public Dist foldn$mcC$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcC$sp$(this, init, n, f);
         }

         public Dist foldn$mcD$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcD$sp$(this, init, n, f);
         }

         public Dist foldn$mcF$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcF$sp$(this, init, n, f);
         }

         public Dist foldn$mcI$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcI$sp$(this, init, n, f);
         }

         public Dist foldn$mcJ$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcJ$sp$(this, init, n, f);
         }

         public Dist foldn$mcS$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcS$sp$(this, init, n, f);
         }

         public Dist foldn$mcV$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcV$sp$(this, init, n, f);
         }

         public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$(this, init, f, pred);
         }

         public Dist unfold$mcZ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcZ$sp$(this, init, f, pred);
         }

         public Dist unfold$mcB$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcB$sp$(this, init, f, pred);
         }

         public Dist unfold$mcC$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcC$sp$(this, init, f, pred);
         }

         public Dist unfold$mcD$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcD$sp$(this, init, f, pred);
         }

         public Dist unfold$mcF$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcF$sp$(this, init, f, pred);
         }

         public Dist unfold$mcI$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcI$sp$(this, init, f, pred);
         }

         public Dist unfold$mcJ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcJ$sp$(this, init, f, pred);
         }

         public Dist unfold$mcS$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcS$sp$(this, init, f, pred);
         }

         public Dist unfold$mcV$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcV$sp$(this, init, f, pred);
         }

         public Dist pack(final int n, final ClassTag ct) {
            return Dist.pack$(this, n, ct);
         }

         public Dist pack$mcZ$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcZ$sp$(this, n, ct);
         }

         public Dist pack$mcB$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcB$sp$(this, n, ct);
         }

         public Dist pack$mcC$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcC$sp$(this, n, ct);
         }

         public Dist pack$mcD$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcD$sp$(this, n, ct);
         }

         public Dist pack$mcF$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcF$sp$(this, n, ct);
         }

         public Dist pack$mcI$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcI$sp$(this, n, ct);
         }

         public Dist pack$mcJ$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcJ$sp$(this, n, ct);
         }

         public Dist pack$mcS$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcS$sp$(this, n, ct);
         }

         public Dist pack$mcV$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcV$sp$(this, n, ct);
         }

         public Dist repeat(final int n, final Factory cbf) {
            return Dist.repeat$(this, n, cbf);
         }

         public Dist iterate(final int n, final Function1 f) {
            return Dist.iterate$(this, n, f);
         }

         public Dist iterate$mcZ$sp(final int n, final Function1 f) {
            return Dist.iterate$mcZ$sp$(this, n, f);
         }

         public Dist iterate$mcB$sp(final int n, final Function1 f) {
            return Dist.iterate$mcB$sp$(this, n, f);
         }

         public Dist iterate$mcC$sp(final int n, final Function1 f) {
            return Dist.iterate$mcC$sp$(this, n, f);
         }

         public Dist iterate$mcD$sp(final int n, final Function1 f) {
            return Dist.iterate$mcD$sp$(this, n, f);
         }

         public Dist iterate$mcF$sp(final int n, final Function1 f) {
            return Dist.iterate$mcF$sp$(this, n, f);
         }

         public Dist iterate$mcI$sp(final int n, final Function1 f) {
            return Dist.iterate$mcI$sp$(this, n, f);
         }

         public Dist iterate$mcJ$sp(final int n, final Function1 f) {
            return Dist.iterate$mcJ$sp$(this, n, f);
         }

         public Dist iterate$mcS$sp(final int n, final Function1 f) {
            return Dist.iterate$mcS$sp$(this, n, f);
         }

         public Dist iterate$mcV$sp(final int n, final Function1 f) {
            return Dist.iterate$mcV$sp$(this, n, f);
         }

         public Dist iterateUntil(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$(this, pred, f);
         }

         public Dist iterateUntil$mcZ$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcZ$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcB$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcB$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcC$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcC$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcD$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcD$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcF$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcF$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcI$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcI$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcJ$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcJ$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcS$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcS$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcV$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcV$sp$(this, pred, f);
         }

         public Dist zip(final Dist that) {
            return Dist.zip$(this, that);
         }

         public Dist zip$mcZ$sp(final Dist that) {
            return Dist.zip$mcZ$sp$(this, that);
         }

         public Dist zip$mcB$sp(final Dist that) {
            return Dist.zip$mcB$sp$(this, that);
         }

         public Dist zip$mcC$sp(final Dist that) {
            return Dist.zip$mcC$sp$(this, that);
         }

         public Dist zip$mcD$sp(final Dist that) {
            return Dist.zip$mcD$sp$(this, that);
         }

         public Dist zip$mcF$sp(final Dist that) {
            return Dist.zip$mcF$sp$(this, that);
         }

         public Dist zip$mcI$sp(final Dist that) {
            return Dist.zip$mcI$sp$(this, that);
         }

         public Dist zip$mcJ$sp(final Dist that) {
            return Dist.zip$mcJ$sp$(this, that);
         }

         public Dist zip$mcS$sp(final Dist that) {
            return Dist.zip$mcS$sp$(this, that);
         }

         public Dist zip$mcV$sp(final Dist that) {
            return Dist.zip$mcV$sp$(this, that);
         }

         public Dist zipWith(final Dist that, final Function2 f) {
            return Dist.zipWith$(this, that, f);
         }

         public Dist zipWith$mcZ$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcZ$sp$(this, that, f);
         }

         public Dist zipWith$mcB$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcB$sp$(this, that, f);
         }

         public Dist zipWith$mcC$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcC$sp$(this, that, f);
         }

         public Dist zipWith$mcD$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcD$sp$(this, that, f);
         }

         public Dist zipWith$mcF$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcF$sp$(this, that, f);
         }

         public Dist zipWith$mcI$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcI$sp$(this, that, f);
         }

         public Dist zipWith$mcJ$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcJ$sp$(this, that, f);
         }

         public Dist zipWith$mcS$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcS$sp$(this, that, f);
         }

         public Dist zipWith$mcV$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcV$sp$(this, that, f);
         }

         public final Iterator toIterator(final Generator gen) {
            return Dist.toIterator$(this, gen);
         }

         public final LazyList toLazyList(final Generator gen) {
            return Dist.toLazyList$(this, gen);
         }

         /** @deprecated */
         public final Stream toStream(final Generator gen) {
            return Dist.toStream$(this, gen);
         }

         public Iterable sample(final int n, final Generator gen, final Factory cbf) {
            return Dist.sample$(this, n, gen, cbf);
         }

         public int count(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$(this, pred, n, gen);
         }

         public int count$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcZ$sp$(this, pred, n, gen);
         }

         public int count$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcB$sp$(this, pred, n, gen);
         }

         public int count$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcC$sp$(this, pred, n, gen);
         }

         public int count$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcD$sp$(this, pred, n, gen);
         }

         public int count$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcF$sp$(this, pred, n, gen);
         }

         public int count$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcI$sp$(this, pred, n, gen);
         }

         public int count$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcJ$sp$(this, pred, n, gen);
         }

         public int count$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcS$sp$(this, pred, n, gen);
         }

         public int count$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcV$sp$(this, pred, n, gen);
         }

         public double pr(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$(this, pred, n, gen);
         }

         public double pr$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcZ$sp$(this, pred, n, gen);
         }

         public double pr$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcB$sp$(this, pred, n, gen);
         }

         public double pr$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcC$sp$(this, pred, n, gen);
         }

         public double pr$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcD$sp$(this, pred, n, gen);
         }

         public double pr$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcF$sp$(this, pred, n, gen);
         }

         public double pr$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcI$sp$(this, pred, n, gen);
         }

         public double pr$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcJ$sp$(this, pred, n, gen);
         }

         public double pr$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcS$sp$(this, pred, n, gen);
         }

         public double pr$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcV$sp$(this, pred, n, gen);
         }

         public Object sum(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$(this, n, gen, alg);
         }

         public boolean sum$mcZ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcZ$sp$(this, n, gen, alg);
         }

         public byte sum$mcB$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcB$sp$(this, n, gen, alg);
         }

         public char sum$mcC$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcC$sp$(this, n, gen, alg);
         }

         public double sum$mcD$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcD$sp$(this, n, gen, alg);
         }

         public float sum$mcF$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcF$sp$(this, n, gen, alg);
         }

         public int sum$mcI$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcI$sp$(this, n, gen, alg);
         }

         public long sum$mcJ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcJ$sp$(this, n, gen, alg);
         }

         public short sum$mcS$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcS$sp$(this, n, gen, alg);
         }

         public void sum$mcV$sp(final int n, final Generator gen, final Rig alg) {
            Dist.sum$mcV$sp$(this, n, gen, alg);
         }

         public Object ev(final int n, final Generator gen, final Field alg) {
            return Dist.ev$(this, n, gen, alg);
         }

         public boolean ev$mcZ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcZ$sp$(this, n, gen, alg);
         }

         public byte ev$mcB$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcB$sp$(this, n, gen, alg);
         }

         public char ev$mcC$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcC$sp$(this, n, gen, alg);
         }

         public double ev$mcD$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcD$sp$(this, n, gen, alg);
         }

         public float ev$mcF$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcF$sp$(this, n, gen, alg);
         }

         public int ev$mcI$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcI$sp$(this, n, gen, alg);
         }

         public long ev$mcJ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcJ$sp$(this, n, gen, alg);
         }

         public short ev$mcS$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcS$sp$(this, n, gen, alg);
         }

         public void ev$mcV$sp(final int n, final Generator gen, final Field alg) {
            Dist.ev$mcV$sp$(this, n, gen, alg);
         }

         public Map histogram(final int n, final Generator gen) {
            return Dist.histogram$(this, n, gen);
         }

         public Map rawHistogram(final int n, final Generator gen) {
            return Dist.rawHistogram$(this, n, gen);
         }

         public BoxedUnit[] apply(final Generator gen) {
            int i = 0;

            BoxedUnit[] arr;
            for(arr = (BoxedUnit[])this.ct$9.newArray(this.n$24); i < arr.length; ++i) {
               this.$outer.apply$mcV$sp(gen);
               arr[i] = BoxedUnit.UNIT;
            }

            return arr;
         }

         public {
            if (Dist$mcV$sp.this == null) {
               throw null;
            } else {
               this.$outer = Dist$mcV$sp.this;
               this.ct$9 = ct$9;
               this.n$24 = n$24;
               Dist.$init$(this);
            }
         }
      };
   }

   default Dist iterate(final int n, final Function1 f) {
      return this.iterate$mcV$sp(n, f);
   }

   default Dist iterate$mcV$sp(final int n, final Function1 f) {
      return (Dist)(n == 0 ? this : this.flatMap$mcV$sp(f).iterate$mcV$sp(n - 1, f));
   }

   default Dist iterateUntil(final Function1 pred, final Function1 f) {
      return this.iterateUntil$mcV$sp(pred, f);
   }

   default Dist iterateUntil$mcV$sp(final Function1 pred, final Function1 f) {
      return new Dist$mcV$sp(pred, f) {
         // $FF: synthetic field
         private final Dist$mcV$sp $outer;
         private final Function1 pred$44;
         private final Function1 f$58;

         public void fill(final Generator gen, final BoxedUnit[] arr) {
            Dist$mcV$sp.super.fill(gen, arr);
         }

         public void fill$mcV$sp(final Generator gen, final BoxedUnit[] arr) {
            Dist$mcV$sp.super.fill$mcV$sp(gen, arr);
         }

         public final Dist map(final Function1 f) {
            return Dist$mcV$sp.super.map(f);
         }

         public final Dist map$mcV$sp(final Function1 f) {
            return Dist$mcV$sp.super.map$mcV$sp(f);
         }

         public final Dist flatMap(final Function1 f) {
            return Dist$mcV$sp.super.flatMap(f);
         }

         public final Dist flatMap$mcV$sp(final Function1 f) {
            return Dist$mcV$sp.super.flatMap$mcV$sp(f);
         }

         public final Dist filter(final Function1 pred) {
            return Dist$mcV$sp.super.filter(pred);
         }

         public final Dist filter$mcV$sp(final Function1 pred) {
            return Dist$mcV$sp.super.filter$mcV$sp(pred);
         }

         public final Dist given(final Function1 pred) {
            return Dist$mcV$sp.super.given(pred);
         }

         public final Dist given$mcV$sp(final Function1 pred) {
            return Dist$mcV$sp.super.given$mcV$sp(pred);
         }

         public Dist until(final Function1 pred) {
            return Dist$mcV$sp.super.until(pred);
         }

         public Dist until$mcV$sp(final Function1 pred) {
            return Dist$mcV$sp.super.until$mcV$sp(pred);
         }

         public Dist foldn(final Object init, final int n, final Function2 f) {
            return Dist$mcV$sp.super.foldn(init, n, f);
         }

         public Dist foldn$mcV$sp(final Object init, final int n, final Function2 f) {
            return Dist$mcV$sp.super.foldn$mcV$sp(init, n, f);
         }

         public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
            return Dist$mcV$sp.super.unfold(init, f, pred);
         }

         public Dist unfold$mcV$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist$mcV$sp.super.unfold$mcV$sp(init, f, pred);
         }

         public Dist pack(final int n, final ClassTag ct) {
            return Dist$mcV$sp.super.pack(n, ct);
         }

         public Dist pack$mcV$sp(final int n, final ClassTag ct) {
            return Dist$mcV$sp.super.pack$mcV$sp(n, ct);
         }

         public Dist iterate(final int n, final Function1 f) {
            return Dist$mcV$sp.super.iterate(n, f);
         }

         public Dist iterate$mcV$sp(final int n, final Function1 f) {
            return Dist$mcV$sp.super.iterate$mcV$sp(n, f);
         }

         public Dist iterateUntil(final Function1 pred, final Function1 f) {
            return Dist$mcV$sp.super.iterateUntil(pred, f);
         }

         public Dist iterateUntil$mcV$sp(final Function1 pred, final Function1 f) {
            return Dist$mcV$sp.super.iterateUntil$mcV$sp(pred, f);
         }

         public final Dist zip(final Dist that) {
            return Dist$mcV$sp.super.zip(that);
         }

         public final Dist zip$mcV$sp(final Dist that) {
            return Dist$mcV$sp.super.zip$mcV$sp(that);
         }

         public Dist zipWith(final Dist that, final Function2 f) {
            return Dist$mcV$sp.super.zipWith(that, f);
         }

         public Dist zipWith$mcV$sp(final Dist that, final Function2 f) {
            return Dist$mcV$sp.super.zipWith$mcV$sp(that, f);
         }

         public final int count(final Function1 pred, final int n, final Generator gen) {
            return Dist$mcV$sp.super.count(pred, n, gen);
         }

         public final int count$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist$mcV$sp.super.count$mcV$sp(pred, n, gen);
         }

         public double pr(final Function1 pred, final int n, final Generator gen) {
            return Dist$mcV$sp.super.pr(pred, n, gen);
         }

         public double pr$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist$mcV$sp.super.pr$mcV$sp(pred, n, gen);
         }

         public void sum(final int n, final Generator gen, final Rig alg) {
            Dist$mcV$sp.super.sum(n, gen, alg);
         }

         public void sum$mcV$sp(final int n, final Generator gen, final Rig alg) {
            Dist$mcV$sp.super.sum$mcV$sp(n, gen, alg);
         }

         public void ev(final int n, final Generator gen, final Field alg) {
            Dist$mcV$sp.super.ev(n, gen, alg);
         }

         public void ev$mcV$sp(final int n, final Generator gen, final Field alg) {
            Dist$mcV$sp.super.ev$mcV$sp(n, gen, alg);
         }

         public boolean apply$mcZ$sp(final Generator gen) {
            return Dist.apply$mcZ$sp$(this, gen);
         }

         public byte apply$mcB$sp(final Generator gen) {
            return Dist.apply$mcB$sp$(this, gen);
         }

         public char apply$mcC$sp(final Generator gen) {
            return Dist.apply$mcC$sp$(this, gen);
         }

         public double apply$mcD$sp(final Generator gen) {
            return Dist.apply$mcD$sp$(this, gen);
         }

         public float apply$mcF$sp(final Generator gen) {
            return Dist.apply$mcF$sp$(this, gen);
         }

         public int apply$mcI$sp(final Generator gen) {
            return Dist.apply$mcI$sp$(this, gen);
         }

         public long apply$mcJ$sp(final Generator gen) {
            return Dist.apply$mcJ$sp$(this, gen);
         }

         public short apply$mcS$sp(final Generator gen) {
            return Dist.apply$mcS$sp$(this, gen);
         }

         public void fill$mcZ$sp(final Generator gen, final boolean[] arr) {
            Dist.fill$mcZ$sp$(this, gen, arr);
         }

         public void fill$mcB$sp(final Generator gen, final byte[] arr) {
            Dist.fill$mcB$sp$(this, gen, arr);
         }

         public void fill$mcC$sp(final Generator gen, final char[] arr) {
            Dist.fill$mcC$sp$(this, gen, arr);
         }

         public void fill$mcD$sp(final Generator gen, final double[] arr) {
            Dist.fill$mcD$sp$(this, gen, arr);
         }

         public void fill$mcF$sp(final Generator gen, final float[] arr) {
            Dist.fill$mcF$sp$(this, gen, arr);
         }

         public void fill$mcI$sp(final Generator gen, final int[] arr) {
            Dist.fill$mcI$sp$(this, gen, arr);
         }

         public void fill$mcJ$sp(final Generator gen, final long[] arr) {
            Dist.fill$mcJ$sp$(this, gen, arr);
         }

         public void fill$mcS$sp(final Generator gen, final short[] arr) {
            Dist.fill$mcS$sp$(this, gen, arr);
         }

         public Dist map$mcZ$sp(final Function1 f) {
            return Dist.map$mcZ$sp$(this, f);
         }

         public Dist map$mcB$sp(final Function1 f) {
            return Dist.map$mcB$sp$(this, f);
         }

         public Dist map$mcC$sp(final Function1 f) {
            return Dist.map$mcC$sp$(this, f);
         }

         public Dist map$mcD$sp(final Function1 f) {
            return Dist.map$mcD$sp$(this, f);
         }

         public Dist map$mcF$sp(final Function1 f) {
            return Dist.map$mcF$sp$(this, f);
         }

         public Dist map$mcI$sp(final Function1 f) {
            return Dist.map$mcI$sp$(this, f);
         }

         public Dist map$mcJ$sp(final Function1 f) {
            return Dist.map$mcJ$sp$(this, f);
         }

         public Dist map$mcS$sp(final Function1 f) {
            return Dist.map$mcS$sp$(this, f);
         }

         public Dist flatMap$mcZ$sp(final Function1 f) {
            return Dist.flatMap$mcZ$sp$(this, f);
         }

         public Dist flatMap$mcB$sp(final Function1 f) {
            return Dist.flatMap$mcB$sp$(this, f);
         }

         public Dist flatMap$mcC$sp(final Function1 f) {
            return Dist.flatMap$mcC$sp$(this, f);
         }

         public Dist flatMap$mcD$sp(final Function1 f) {
            return Dist.flatMap$mcD$sp$(this, f);
         }

         public Dist flatMap$mcF$sp(final Function1 f) {
            return Dist.flatMap$mcF$sp$(this, f);
         }

         public Dist flatMap$mcI$sp(final Function1 f) {
            return Dist.flatMap$mcI$sp$(this, f);
         }

         public Dist flatMap$mcJ$sp(final Function1 f) {
            return Dist.flatMap$mcJ$sp$(this, f);
         }

         public Dist flatMap$mcS$sp(final Function1 f) {
            return Dist.flatMap$mcS$sp$(this, f);
         }

         public Dist filter$mcZ$sp(final Function1 pred) {
            return Dist.filter$mcZ$sp$(this, pred);
         }

         public Dist filter$mcB$sp(final Function1 pred) {
            return Dist.filter$mcB$sp$(this, pred);
         }

         public Dist filter$mcC$sp(final Function1 pred) {
            return Dist.filter$mcC$sp$(this, pred);
         }

         public Dist filter$mcD$sp(final Function1 pred) {
            return Dist.filter$mcD$sp$(this, pred);
         }

         public Dist filter$mcF$sp(final Function1 pred) {
            return Dist.filter$mcF$sp$(this, pred);
         }

         public Dist filter$mcI$sp(final Function1 pred) {
            return Dist.filter$mcI$sp$(this, pred);
         }

         public Dist filter$mcJ$sp(final Function1 pred) {
            return Dist.filter$mcJ$sp$(this, pred);
         }

         public Dist filter$mcS$sp(final Function1 pred) {
            return Dist.filter$mcS$sp$(this, pred);
         }

         public Dist given$mcZ$sp(final Function1 pred) {
            return Dist.given$mcZ$sp$(this, pred);
         }

         public Dist given$mcB$sp(final Function1 pred) {
            return Dist.given$mcB$sp$(this, pred);
         }

         public Dist given$mcC$sp(final Function1 pred) {
            return Dist.given$mcC$sp$(this, pred);
         }

         public Dist given$mcD$sp(final Function1 pred) {
            return Dist.given$mcD$sp$(this, pred);
         }

         public Dist given$mcF$sp(final Function1 pred) {
            return Dist.given$mcF$sp$(this, pred);
         }

         public Dist given$mcI$sp(final Function1 pred) {
            return Dist.given$mcI$sp$(this, pred);
         }

         public Dist given$mcJ$sp(final Function1 pred) {
            return Dist.given$mcJ$sp$(this, pred);
         }

         public Dist given$mcS$sp(final Function1 pred) {
            return Dist.given$mcS$sp$(this, pred);
         }

         public Dist until$mcZ$sp(final Function1 pred) {
            return Dist.until$mcZ$sp$(this, pred);
         }

         public Dist until$mcB$sp(final Function1 pred) {
            return Dist.until$mcB$sp$(this, pred);
         }

         public Dist until$mcC$sp(final Function1 pred) {
            return Dist.until$mcC$sp$(this, pred);
         }

         public Dist until$mcD$sp(final Function1 pred) {
            return Dist.until$mcD$sp$(this, pred);
         }

         public Dist until$mcF$sp(final Function1 pred) {
            return Dist.until$mcF$sp$(this, pred);
         }

         public Dist until$mcI$sp(final Function1 pred) {
            return Dist.until$mcI$sp$(this, pred);
         }

         public Dist until$mcJ$sp(final Function1 pred) {
            return Dist.until$mcJ$sp$(this, pred);
         }

         public Dist until$mcS$sp(final Function1 pred) {
            return Dist.until$mcS$sp$(this, pred);
         }

         public Dist foldn$mcZ$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcZ$sp$(this, init, n, f);
         }

         public Dist foldn$mcB$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcB$sp$(this, init, n, f);
         }

         public Dist foldn$mcC$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcC$sp$(this, init, n, f);
         }

         public Dist foldn$mcD$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcD$sp$(this, init, n, f);
         }

         public Dist foldn$mcF$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcF$sp$(this, init, n, f);
         }

         public Dist foldn$mcI$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcI$sp$(this, init, n, f);
         }

         public Dist foldn$mcJ$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcJ$sp$(this, init, n, f);
         }

         public Dist foldn$mcS$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcS$sp$(this, init, n, f);
         }

         public Dist unfold$mcZ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcZ$sp$(this, init, f, pred);
         }

         public Dist unfold$mcB$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcB$sp$(this, init, f, pred);
         }

         public Dist unfold$mcC$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcC$sp$(this, init, f, pred);
         }

         public Dist unfold$mcD$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcD$sp$(this, init, f, pred);
         }

         public Dist unfold$mcF$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcF$sp$(this, init, f, pred);
         }

         public Dist unfold$mcI$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcI$sp$(this, init, f, pred);
         }

         public Dist unfold$mcJ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcJ$sp$(this, init, f, pred);
         }

         public Dist unfold$mcS$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcS$sp$(this, init, f, pred);
         }

         public Dist pack$mcZ$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcZ$sp$(this, n, ct);
         }

         public Dist pack$mcB$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcB$sp$(this, n, ct);
         }

         public Dist pack$mcC$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcC$sp$(this, n, ct);
         }

         public Dist pack$mcD$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcD$sp$(this, n, ct);
         }

         public Dist pack$mcF$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcF$sp$(this, n, ct);
         }

         public Dist pack$mcI$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcI$sp$(this, n, ct);
         }

         public Dist pack$mcJ$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcJ$sp$(this, n, ct);
         }

         public Dist pack$mcS$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcS$sp$(this, n, ct);
         }

         public Dist repeat(final int n, final Factory cbf) {
            return Dist.repeat$(this, n, cbf);
         }

         public Dist iterate$mcZ$sp(final int n, final Function1 f) {
            return Dist.iterate$mcZ$sp$(this, n, f);
         }

         public Dist iterate$mcB$sp(final int n, final Function1 f) {
            return Dist.iterate$mcB$sp$(this, n, f);
         }

         public Dist iterate$mcC$sp(final int n, final Function1 f) {
            return Dist.iterate$mcC$sp$(this, n, f);
         }

         public Dist iterate$mcD$sp(final int n, final Function1 f) {
            return Dist.iterate$mcD$sp$(this, n, f);
         }

         public Dist iterate$mcF$sp(final int n, final Function1 f) {
            return Dist.iterate$mcF$sp$(this, n, f);
         }

         public Dist iterate$mcI$sp(final int n, final Function1 f) {
            return Dist.iterate$mcI$sp$(this, n, f);
         }

         public Dist iterate$mcJ$sp(final int n, final Function1 f) {
            return Dist.iterate$mcJ$sp$(this, n, f);
         }

         public Dist iterate$mcS$sp(final int n, final Function1 f) {
            return Dist.iterate$mcS$sp$(this, n, f);
         }

         public Dist iterateUntil$mcZ$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcZ$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcB$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcB$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcC$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcC$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcD$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcD$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcF$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcF$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcI$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcI$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcJ$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcJ$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcS$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcS$sp$(this, pred, f);
         }

         public Dist zip$mcZ$sp(final Dist that) {
            return Dist.zip$mcZ$sp$(this, that);
         }

         public Dist zip$mcB$sp(final Dist that) {
            return Dist.zip$mcB$sp$(this, that);
         }

         public Dist zip$mcC$sp(final Dist that) {
            return Dist.zip$mcC$sp$(this, that);
         }

         public Dist zip$mcD$sp(final Dist that) {
            return Dist.zip$mcD$sp$(this, that);
         }

         public Dist zip$mcF$sp(final Dist that) {
            return Dist.zip$mcF$sp$(this, that);
         }

         public Dist zip$mcI$sp(final Dist that) {
            return Dist.zip$mcI$sp$(this, that);
         }

         public Dist zip$mcJ$sp(final Dist that) {
            return Dist.zip$mcJ$sp$(this, that);
         }

         public Dist zip$mcS$sp(final Dist that) {
            return Dist.zip$mcS$sp$(this, that);
         }

         public Dist zipWith$mcZ$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcZ$sp$(this, that, f);
         }

         public Dist zipWith$mcB$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcB$sp$(this, that, f);
         }

         public Dist zipWith$mcC$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcC$sp$(this, that, f);
         }

         public Dist zipWith$mcD$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcD$sp$(this, that, f);
         }

         public Dist zipWith$mcF$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcF$sp$(this, that, f);
         }

         public Dist zipWith$mcI$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcI$sp$(this, that, f);
         }

         public Dist zipWith$mcJ$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcJ$sp$(this, that, f);
         }

         public Dist zipWith$mcS$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcS$sp$(this, that, f);
         }

         public final Iterator toIterator(final Generator gen) {
            return Dist.toIterator$(this, gen);
         }

         public final LazyList toLazyList(final Generator gen) {
            return Dist.toLazyList$(this, gen);
         }

         /** @deprecated */
         public final Stream toStream(final Generator gen) {
            return Dist.toStream$(this, gen);
         }

         public Iterable sample(final int n, final Generator gen, final Factory cbf) {
            return Dist.sample$(this, n, gen, cbf);
         }

         public int count$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcZ$sp$(this, pred, n, gen);
         }

         public int count$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcB$sp$(this, pred, n, gen);
         }

         public int count$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcC$sp$(this, pred, n, gen);
         }

         public int count$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcD$sp$(this, pred, n, gen);
         }

         public int count$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcF$sp$(this, pred, n, gen);
         }

         public int count$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcI$sp$(this, pred, n, gen);
         }

         public int count$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcJ$sp$(this, pred, n, gen);
         }

         public int count$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcS$sp$(this, pred, n, gen);
         }

         public double pr$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcZ$sp$(this, pred, n, gen);
         }

         public double pr$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcB$sp$(this, pred, n, gen);
         }

         public double pr$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcC$sp$(this, pred, n, gen);
         }

         public double pr$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcD$sp$(this, pred, n, gen);
         }

         public double pr$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcF$sp$(this, pred, n, gen);
         }

         public double pr$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcI$sp$(this, pred, n, gen);
         }

         public double pr$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcJ$sp$(this, pred, n, gen);
         }

         public double pr$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcS$sp$(this, pred, n, gen);
         }

         public boolean sum$mcZ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcZ$sp$(this, n, gen, alg);
         }

         public byte sum$mcB$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcB$sp$(this, n, gen, alg);
         }

         public char sum$mcC$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcC$sp$(this, n, gen, alg);
         }

         public double sum$mcD$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcD$sp$(this, n, gen, alg);
         }

         public float sum$mcF$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcF$sp$(this, n, gen, alg);
         }

         public int sum$mcI$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcI$sp$(this, n, gen, alg);
         }

         public long sum$mcJ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcJ$sp$(this, n, gen, alg);
         }

         public short sum$mcS$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcS$sp$(this, n, gen, alg);
         }

         public boolean ev$mcZ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcZ$sp$(this, n, gen, alg);
         }

         public byte ev$mcB$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcB$sp$(this, n, gen, alg);
         }

         public char ev$mcC$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcC$sp$(this, n, gen, alg);
         }

         public double ev$mcD$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcD$sp$(this, n, gen, alg);
         }

         public float ev$mcF$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcF$sp$(this, n, gen, alg);
         }

         public int ev$mcI$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcI$sp$(this, n, gen, alg);
         }

         public long ev$mcJ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcJ$sp$(this, n, gen, alg);
         }

         public short ev$mcS$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcS$sp$(this, n, gen, alg);
         }

         public Map histogram(final int n, final Generator gen) {
            return Dist.histogram$(this, n, gen);
         }

         public Map rawHistogram(final int n, final Generator gen) {
            return Dist.rawHistogram$(this, n, gen);
         }

         public void loop(final Generator gen, final BoxedUnit a) {
            while(!BoxesRunTime.unboxToBoolean(this.pred$44.apply(a))) {
               ((Dist)this.f$58.apply(a)).apply$mcV$sp(gen);
               a = BoxedUnit.UNIT;
               gen = gen;
            }

         }

         public void apply(final Generator gen) {
            this.apply$mcV$sp(gen);
         }

         public void apply$mcV$sp(final Generator gen) {
            this.$outer.apply$mcV$sp(gen);
            this.loop(gen, BoxedUnit.UNIT);
         }

         public {
            if (Dist$mcV$sp.this == null) {
               throw null;
            } else {
               this.$outer = Dist$mcV$sp.this;
               this.pred$44 = pred$44;
               this.f$58 = f$58;
               Dist.$init$(this);
            }
         }
      };
   }

   default Dist zip(final Dist that) {
      return this.zip$mcV$sp(that);
   }

   default Dist zip$mcV$sp(final Dist that) {
      return new DistFromGen((g) -> {
         this.apply$mcV$sp(g);
         return new Tuple2(BoxedUnit.UNIT, that.apply(g));
      });
   }

   default Dist zipWith(final Dist that, final Function2 f) {
      return this.zipWith$mcV$sp(that, f);
   }

   default Dist zipWith$mcV$sp(final Dist that, final Function2 f) {
      return new DistFromGen((g) -> {
         this.apply$mcV$sp(g);
         return f.apply(BoxedUnit.UNIT, that.apply(g));
      });
   }

   default int count(final Function1 pred, final int n, final Generator gen) {
      return this.count$mcV$sp(pred, n, gen);
   }

   default int count$mcV$sp(final Function1 pred, final int n, final Generator gen) {
      return this.loop$53(0, n, pred, gen);
   }

   default double pr(final Function1 pred, final int n, final Generator gen) {
      return this.pr$mcV$sp(pred, n, gen);
   }

   default double pr$mcV$sp(final Function1 pred, final int n, final Generator gen) {
      return (double)1.0F * (double)this.count$mcV$sp(pred, n, gen) / (double)n;
   }

   default void sum(final int n, final Generator gen, final Rig alg) {
      this.sum$mcV$sp(n, gen, alg);
   }

   default void sum$mcV$sp(final int n, final Generator gen, final Rig alg) {
      this.loop$54((BoxedUnit)alg.zero(), n, alg, gen);
   }

   default void ev(final int n, final Generator gen, final Field alg) {
      this.ev$mcV$sp(n, gen, alg);
   }

   default void ev$mcV$sp(final int n, final Generator gen, final Field alg) {
      this.sum$mcV$sp(n, gen, alg);
      alg.div(BoxedUnit.UNIT, alg.fromInt(n));
   }

   private Seq loop$50(final Generator gen, final BoxedUnit a, final ArrayBuffer buf, final Function1 pred$42) {
      while(true) {
         buf.append(a);
         if (BoxesRunTime.unboxToBoolean(pred$42.apply(a))) {
            return buf.toSeq();
         }

         this.apply$mcV$sp(gen);
         buf = buf;
         a = BoxedUnit.UNIT;
         gen = gen;
      }
   }

   private Object loop$51(final Generator gen, final int i, final Object b, final Function2 f$56) {
      while(i != 0) {
         int var10001 = i - 1;
         this.apply$mcV$sp(gen);
         b = f$56.apply(b, BoxedUnit.UNIT);
         i = var10001;
         gen = gen;
      }

      return b;
   }

   private Object loop$52(final Generator gen, final Object b, final Function1 pred$43, final Function2 f$57) {
      while(!BoxesRunTime.unboxToBoolean(pred$43.apply(b))) {
         this.apply$mcV$sp(gen);
         b = f$57.apply(b, BoxedUnit.UNIT);
         gen = gen;
      }

      return b;
   }

   private int loop$53(final int num, final int i, final Function1 pred$45, final Generator gen$28) {
      while(i != 0) {
         this.apply$mcV$sp(gen$28);
         int var10000 = num + (BoxesRunTime.unboxToBoolean(pred$45.apply(BoxedUnit.UNIT)) ? 1 : 0);
         --i;
         num = var10000;
      }

      return num;
   }

   private void loop$54(final BoxedUnit total, final int i, final Rig alg$9, final Generator gen$29) {
      while(i != 0) {
         this.apply$mcV$sp(gen$29);
         BoxedUnit var10000 = (BoxedUnit)alg$9.plus(total, BoxedUnit.UNIT);
         --i;
         total = var10000;
      }

   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
