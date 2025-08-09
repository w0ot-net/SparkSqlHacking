package spire.random;

import algebra.ring.Field;
import algebra.ring.Rig;
import scala.Function1;
import scala.Function2;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class DistFromGen$mcS$sp extends DistFromGen implements Dist$mcS$sp {
   public final Function1 f$mcS$sp;

   public void fill(final Generator gen, final short[] arr) {
      Dist$mcS$sp.fill$(this, gen, arr);
   }

   public void fill$mcS$sp(final Generator gen, final short[] arr) {
      Dist$mcS$sp.fill$mcS$sp$(this, gen, arr);
   }

   public final Dist map(final Function1 f) {
      return Dist$mcS$sp.map$(this, f);
   }

   public final Dist map$mcS$sp(final Function1 f) {
      return Dist$mcS$sp.map$mcS$sp$(this, f);
   }

   public final Dist flatMap(final Function1 f) {
      return Dist$mcS$sp.flatMap$(this, f);
   }

   public final Dist flatMap$mcS$sp(final Function1 f) {
      return Dist$mcS$sp.flatMap$mcS$sp$(this, f);
   }

   public final Dist filter(final Function1 pred) {
      return Dist$mcS$sp.filter$(this, pred);
   }

   public final Dist filter$mcS$sp(final Function1 pred) {
      return Dist$mcS$sp.filter$mcS$sp$(this, pred);
   }

   public final Dist given(final Function1 pred) {
      return Dist$mcS$sp.given$(this, pred);
   }

   public final Dist given$mcS$sp(final Function1 pred) {
      return Dist$mcS$sp.given$mcS$sp$(this, pred);
   }

   public Dist until(final Function1 pred) {
      return Dist$mcS$sp.until$(this, pred);
   }

   public Dist until$mcS$sp(final Function1 pred) {
      return Dist$mcS$sp.until$mcS$sp$(this, pred);
   }

   public Dist foldn(final Object init, final int n, final Function2 f) {
      return Dist$mcS$sp.foldn$(this, init, n, f);
   }

   public Dist foldn$mcS$sp(final Object init, final int n, final Function2 f) {
      return Dist$mcS$sp.foldn$mcS$sp$(this, init, n, f);
   }

   public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
      return Dist$mcS$sp.unfold$(this, init, f, pred);
   }

   public Dist unfold$mcS$sp(final Object init, final Function2 f, final Function1 pred) {
      return Dist$mcS$sp.unfold$mcS$sp$(this, init, f, pred);
   }

   public Dist pack(final int n, final ClassTag ct) {
      return Dist$mcS$sp.pack$(this, n, ct);
   }

   public Dist pack$mcS$sp(final int n, final ClassTag ct) {
      return Dist$mcS$sp.pack$mcS$sp$(this, n, ct);
   }

   public Dist iterate(final int n, final Function1 f) {
      return Dist$mcS$sp.iterate$(this, n, f);
   }

   public Dist iterate$mcS$sp(final int n, final Function1 f) {
      return Dist$mcS$sp.iterate$mcS$sp$(this, n, f);
   }

   public Dist iterateUntil(final Function1 pred, final Function1 f) {
      return Dist$mcS$sp.iterateUntil$(this, pred, f);
   }

   public Dist iterateUntil$mcS$sp(final Function1 pred, final Function1 f) {
      return Dist$mcS$sp.iterateUntil$mcS$sp$(this, pred, f);
   }

   public final Dist zip(final Dist that) {
      return Dist$mcS$sp.zip$(this, that);
   }

   public final Dist zip$mcS$sp(final Dist that) {
      return Dist$mcS$sp.zip$mcS$sp$(this, that);
   }

   public Dist zipWith(final Dist that, final Function2 f) {
      return Dist$mcS$sp.zipWith$(this, that, f);
   }

   public Dist zipWith$mcS$sp(final Dist that, final Function2 f) {
      return Dist$mcS$sp.zipWith$mcS$sp$(this, that, f);
   }

   public final int count(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcS$sp.count$(this, pred, n, gen);
   }

   public final int count$mcS$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcS$sp.count$mcS$sp$(this, pred, n, gen);
   }

   public double pr(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcS$sp.pr$(this, pred, n, gen);
   }

   public double pr$mcS$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcS$sp.pr$mcS$sp$(this, pred, n, gen);
   }

   public short sum(final int n, final Generator gen, final Rig alg) {
      return Dist$mcS$sp.sum$(this, n, gen, alg);
   }

   public short sum$mcS$sp(final int n, final Generator gen, final Rig alg) {
      return Dist$mcS$sp.sum$mcS$sp$(this, n, gen, alg);
   }

   public short ev(final int n, final Generator gen, final Field alg) {
      return Dist$mcS$sp.ev$(this, n, gen, alg);
   }

   public short ev$mcS$sp(final int n, final Generator gen, final Field alg) {
      return Dist$mcS$sp.ev$mcS$sp$(this, n, gen, alg);
   }

   public short apply(final Generator gen) {
      return this.apply$mcS$sp(gen);
   }

   public short apply$mcS$sp(final Generator gen) {
      return BoxesRunTime.unboxToShort(this.f$mcS$sp.apply(gen));
   }

   public DistFromGen$mcS$sp(final Function1 f$mcS$sp) {
      super(f$mcS$sp);
      this.f$mcS$sp = f$mcS$sp;
   }
}
