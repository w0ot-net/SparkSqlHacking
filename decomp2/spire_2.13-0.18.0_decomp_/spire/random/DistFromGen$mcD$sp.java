package spire.random;

import algebra.ring.Field;
import algebra.ring.Rig;
import scala.Function1;
import scala.Function2;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class DistFromGen$mcD$sp extends DistFromGen implements Dist$mcD$sp {
   public final Function1 f$mcD$sp;

   public void fill(final Generator gen, final double[] arr) {
      Dist$mcD$sp.fill$(this, gen, arr);
   }

   public void fill$mcD$sp(final Generator gen, final double[] arr) {
      Dist$mcD$sp.fill$mcD$sp$(this, gen, arr);
   }

   public final Dist map(final Function1 f) {
      return Dist$mcD$sp.map$(this, f);
   }

   public final Dist map$mcD$sp(final Function1 f) {
      return Dist$mcD$sp.map$mcD$sp$(this, f);
   }

   public final Dist flatMap(final Function1 f) {
      return Dist$mcD$sp.flatMap$(this, f);
   }

   public final Dist flatMap$mcD$sp(final Function1 f) {
      return Dist$mcD$sp.flatMap$mcD$sp$(this, f);
   }

   public final Dist filter(final Function1 pred) {
      return Dist$mcD$sp.filter$(this, pred);
   }

   public final Dist filter$mcD$sp(final Function1 pred) {
      return Dist$mcD$sp.filter$mcD$sp$(this, pred);
   }

   public final Dist given(final Function1 pred) {
      return Dist$mcD$sp.given$(this, pred);
   }

   public final Dist given$mcD$sp(final Function1 pred) {
      return Dist$mcD$sp.given$mcD$sp$(this, pred);
   }

   public Dist until(final Function1 pred) {
      return Dist$mcD$sp.until$(this, pred);
   }

   public Dist until$mcD$sp(final Function1 pred) {
      return Dist$mcD$sp.until$mcD$sp$(this, pred);
   }

   public Dist foldn(final Object init, final int n, final Function2 f) {
      return Dist$mcD$sp.foldn$(this, init, n, f);
   }

   public Dist foldn$mcD$sp(final Object init, final int n, final Function2 f) {
      return Dist$mcD$sp.foldn$mcD$sp$(this, init, n, f);
   }

   public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
      return Dist$mcD$sp.unfold$(this, init, f, pred);
   }

   public Dist unfold$mcD$sp(final Object init, final Function2 f, final Function1 pred) {
      return Dist$mcD$sp.unfold$mcD$sp$(this, init, f, pred);
   }

   public Dist pack(final int n, final ClassTag ct) {
      return Dist$mcD$sp.pack$(this, n, ct);
   }

   public Dist pack$mcD$sp(final int n, final ClassTag ct) {
      return Dist$mcD$sp.pack$mcD$sp$(this, n, ct);
   }

   public Dist iterate(final int n, final Function1 f) {
      return Dist$mcD$sp.iterate$(this, n, f);
   }

   public Dist iterate$mcD$sp(final int n, final Function1 f) {
      return Dist$mcD$sp.iterate$mcD$sp$(this, n, f);
   }

   public Dist iterateUntil(final Function1 pred, final Function1 f) {
      return Dist$mcD$sp.iterateUntil$(this, pred, f);
   }

   public Dist iterateUntil$mcD$sp(final Function1 pred, final Function1 f) {
      return Dist$mcD$sp.iterateUntil$mcD$sp$(this, pred, f);
   }

   public final Dist zip(final Dist that) {
      return Dist$mcD$sp.zip$(this, that);
   }

   public final Dist zip$mcD$sp(final Dist that) {
      return Dist$mcD$sp.zip$mcD$sp$(this, that);
   }

   public Dist zipWith(final Dist that, final Function2 f) {
      return Dist$mcD$sp.zipWith$(this, that, f);
   }

   public Dist zipWith$mcD$sp(final Dist that, final Function2 f) {
      return Dist$mcD$sp.zipWith$mcD$sp$(this, that, f);
   }

   public final int count(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcD$sp.count$(this, pred, n, gen);
   }

   public final int count$mcD$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcD$sp.count$mcD$sp$(this, pred, n, gen);
   }

   public double pr(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcD$sp.pr$(this, pred, n, gen);
   }

   public double pr$mcD$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcD$sp.pr$mcD$sp$(this, pred, n, gen);
   }

   public double sum(final int n, final Generator gen, final Rig alg) {
      return Dist$mcD$sp.sum$(this, n, gen, alg);
   }

   public double sum$mcD$sp(final int n, final Generator gen, final Rig alg) {
      return Dist$mcD$sp.sum$mcD$sp$(this, n, gen, alg);
   }

   public double ev(final int n, final Generator gen, final Field alg) {
      return Dist$mcD$sp.ev$(this, n, gen, alg);
   }

   public double ev$mcD$sp(final int n, final Generator gen, final Field alg) {
      return Dist$mcD$sp.ev$mcD$sp$(this, n, gen, alg);
   }

   public double apply(final Generator gen) {
      return this.apply$mcD$sp(gen);
   }

   public double apply$mcD$sp(final Generator gen) {
      return BoxesRunTime.unboxToDouble(this.f$mcD$sp.apply(gen));
   }

   public DistFromGen$mcD$sp(final Function1 f$mcD$sp) {
      super(f$mcD$sp);
      this.f$mcD$sp = f$mcD$sp;
   }
}
