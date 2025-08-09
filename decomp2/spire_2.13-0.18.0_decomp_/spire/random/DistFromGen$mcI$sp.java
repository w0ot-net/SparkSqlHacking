package spire.random;

import algebra.ring.Field;
import algebra.ring.Rig;
import scala.Function1;
import scala.Function2;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class DistFromGen$mcI$sp extends DistFromGen implements Dist$mcI$sp {
   public final Function1 f$mcI$sp;

   public void fill(final Generator gen, final int[] arr) {
      Dist$mcI$sp.fill$(this, gen, arr);
   }

   public void fill$mcI$sp(final Generator gen, final int[] arr) {
      Dist$mcI$sp.fill$mcI$sp$(this, gen, arr);
   }

   public final Dist map(final Function1 f) {
      return Dist$mcI$sp.map$(this, f);
   }

   public final Dist map$mcI$sp(final Function1 f) {
      return Dist$mcI$sp.map$mcI$sp$(this, f);
   }

   public final Dist flatMap(final Function1 f) {
      return Dist$mcI$sp.flatMap$(this, f);
   }

   public final Dist flatMap$mcI$sp(final Function1 f) {
      return Dist$mcI$sp.flatMap$mcI$sp$(this, f);
   }

   public final Dist filter(final Function1 pred) {
      return Dist$mcI$sp.filter$(this, pred);
   }

   public final Dist filter$mcI$sp(final Function1 pred) {
      return Dist$mcI$sp.filter$mcI$sp$(this, pred);
   }

   public final Dist given(final Function1 pred) {
      return Dist$mcI$sp.given$(this, pred);
   }

   public final Dist given$mcI$sp(final Function1 pred) {
      return Dist$mcI$sp.given$mcI$sp$(this, pred);
   }

   public Dist until(final Function1 pred) {
      return Dist$mcI$sp.until$(this, pred);
   }

   public Dist until$mcI$sp(final Function1 pred) {
      return Dist$mcI$sp.until$mcI$sp$(this, pred);
   }

   public Dist foldn(final Object init, final int n, final Function2 f) {
      return Dist$mcI$sp.foldn$(this, init, n, f);
   }

   public Dist foldn$mcI$sp(final Object init, final int n, final Function2 f) {
      return Dist$mcI$sp.foldn$mcI$sp$(this, init, n, f);
   }

   public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
      return Dist$mcI$sp.unfold$(this, init, f, pred);
   }

   public Dist unfold$mcI$sp(final Object init, final Function2 f, final Function1 pred) {
      return Dist$mcI$sp.unfold$mcI$sp$(this, init, f, pred);
   }

   public Dist pack(final int n, final ClassTag ct) {
      return Dist$mcI$sp.pack$(this, n, ct);
   }

   public Dist pack$mcI$sp(final int n, final ClassTag ct) {
      return Dist$mcI$sp.pack$mcI$sp$(this, n, ct);
   }

   public Dist iterate(final int n, final Function1 f) {
      return Dist$mcI$sp.iterate$(this, n, f);
   }

   public Dist iterate$mcI$sp(final int n, final Function1 f) {
      return Dist$mcI$sp.iterate$mcI$sp$(this, n, f);
   }

   public Dist iterateUntil(final Function1 pred, final Function1 f) {
      return Dist$mcI$sp.iterateUntil$(this, pred, f);
   }

   public Dist iterateUntil$mcI$sp(final Function1 pred, final Function1 f) {
      return Dist$mcI$sp.iterateUntil$mcI$sp$(this, pred, f);
   }

   public final Dist zip(final Dist that) {
      return Dist$mcI$sp.zip$(this, that);
   }

   public final Dist zip$mcI$sp(final Dist that) {
      return Dist$mcI$sp.zip$mcI$sp$(this, that);
   }

   public Dist zipWith(final Dist that, final Function2 f) {
      return Dist$mcI$sp.zipWith$(this, that, f);
   }

   public Dist zipWith$mcI$sp(final Dist that, final Function2 f) {
      return Dist$mcI$sp.zipWith$mcI$sp$(this, that, f);
   }

   public final int count(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcI$sp.count$(this, pred, n, gen);
   }

   public final int count$mcI$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcI$sp.count$mcI$sp$(this, pred, n, gen);
   }

   public double pr(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcI$sp.pr$(this, pred, n, gen);
   }

   public double pr$mcI$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcI$sp.pr$mcI$sp$(this, pred, n, gen);
   }

   public int sum(final int n, final Generator gen, final Rig alg) {
      return Dist$mcI$sp.sum$(this, n, gen, alg);
   }

   public int sum$mcI$sp(final int n, final Generator gen, final Rig alg) {
      return Dist$mcI$sp.sum$mcI$sp$(this, n, gen, alg);
   }

   public int ev(final int n, final Generator gen, final Field alg) {
      return Dist$mcI$sp.ev$(this, n, gen, alg);
   }

   public int ev$mcI$sp(final int n, final Generator gen, final Field alg) {
      return Dist$mcI$sp.ev$mcI$sp$(this, n, gen, alg);
   }

   public int apply(final Generator gen) {
      return this.apply$mcI$sp(gen);
   }

   public int apply$mcI$sp(final Generator gen) {
      return BoxesRunTime.unboxToInt(this.f$mcI$sp.apply(gen));
   }

   public DistFromGen$mcI$sp(final Function1 f$mcI$sp) {
      super(f$mcI$sp);
      this.f$mcI$sp = f$mcI$sp;
   }
}
