package spire.random;

import algebra.ring.Field;
import algebra.ring.Rig;
import scala.Function1;
import scala.Function2;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class DistFromGen$mcZ$sp extends DistFromGen implements Dist$mcZ$sp {
   public final Function1 f$mcZ$sp;

   public void fill(final Generator gen, final boolean[] arr) {
      Dist$mcZ$sp.fill$(this, gen, arr);
   }

   public void fill$mcZ$sp(final Generator gen, final boolean[] arr) {
      Dist$mcZ$sp.fill$mcZ$sp$(this, gen, arr);
   }

   public final Dist map(final Function1 f) {
      return Dist$mcZ$sp.map$(this, f);
   }

   public final Dist map$mcZ$sp(final Function1 f) {
      return Dist$mcZ$sp.map$mcZ$sp$(this, f);
   }

   public final Dist flatMap(final Function1 f) {
      return Dist$mcZ$sp.flatMap$(this, f);
   }

   public final Dist flatMap$mcZ$sp(final Function1 f) {
      return Dist$mcZ$sp.flatMap$mcZ$sp$(this, f);
   }

   public final Dist filter(final Function1 pred) {
      return Dist$mcZ$sp.filter$(this, pred);
   }

   public final Dist filter$mcZ$sp(final Function1 pred) {
      return Dist$mcZ$sp.filter$mcZ$sp$(this, pred);
   }

   public final Dist given(final Function1 pred) {
      return Dist$mcZ$sp.given$(this, pred);
   }

   public final Dist given$mcZ$sp(final Function1 pred) {
      return Dist$mcZ$sp.given$mcZ$sp$(this, pred);
   }

   public Dist until(final Function1 pred) {
      return Dist$mcZ$sp.until$(this, pred);
   }

   public Dist until$mcZ$sp(final Function1 pred) {
      return Dist$mcZ$sp.until$mcZ$sp$(this, pred);
   }

   public Dist foldn(final Object init, final int n, final Function2 f) {
      return Dist$mcZ$sp.foldn$(this, init, n, f);
   }

   public Dist foldn$mcZ$sp(final Object init, final int n, final Function2 f) {
      return Dist$mcZ$sp.foldn$mcZ$sp$(this, init, n, f);
   }

   public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
      return Dist$mcZ$sp.unfold$(this, init, f, pred);
   }

   public Dist unfold$mcZ$sp(final Object init, final Function2 f, final Function1 pred) {
      return Dist$mcZ$sp.unfold$mcZ$sp$(this, init, f, pred);
   }

   public Dist pack(final int n, final ClassTag ct) {
      return Dist$mcZ$sp.pack$(this, n, ct);
   }

   public Dist pack$mcZ$sp(final int n, final ClassTag ct) {
      return Dist$mcZ$sp.pack$mcZ$sp$(this, n, ct);
   }

   public Dist iterate(final int n, final Function1 f) {
      return Dist$mcZ$sp.iterate$(this, n, f);
   }

   public Dist iterate$mcZ$sp(final int n, final Function1 f) {
      return Dist$mcZ$sp.iterate$mcZ$sp$(this, n, f);
   }

   public Dist iterateUntil(final Function1 pred, final Function1 f) {
      return Dist$mcZ$sp.iterateUntil$(this, pred, f);
   }

   public Dist iterateUntil$mcZ$sp(final Function1 pred, final Function1 f) {
      return Dist$mcZ$sp.iterateUntil$mcZ$sp$(this, pred, f);
   }

   public final Dist zip(final Dist that) {
      return Dist$mcZ$sp.zip$(this, that);
   }

   public final Dist zip$mcZ$sp(final Dist that) {
      return Dist$mcZ$sp.zip$mcZ$sp$(this, that);
   }

   public Dist zipWith(final Dist that, final Function2 f) {
      return Dist$mcZ$sp.zipWith$(this, that, f);
   }

   public Dist zipWith$mcZ$sp(final Dist that, final Function2 f) {
      return Dist$mcZ$sp.zipWith$mcZ$sp$(this, that, f);
   }

   public final int count(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcZ$sp.count$(this, pred, n, gen);
   }

   public final int count$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcZ$sp.count$mcZ$sp$(this, pred, n, gen);
   }

   public double pr(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcZ$sp.pr$(this, pred, n, gen);
   }

   public double pr$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcZ$sp.pr$mcZ$sp$(this, pred, n, gen);
   }

   public boolean sum(final int n, final Generator gen, final Rig alg) {
      return Dist$mcZ$sp.sum$(this, n, gen, alg);
   }

   public boolean sum$mcZ$sp(final int n, final Generator gen, final Rig alg) {
      return Dist$mcZ$sp.sum$mcZ$sp$(this, n, gen, alg);
   }

   public boolean ev(final int n, final Generator gen, final Field alg) {
      return Dist$mcZ$sp.ev$(this, n, gen, alg);
   }

   public boolean ev$mcZ$sp(final int n, final Generator gen, final Field alg) {
      return Dist$mcZ$sp.ev$mcZ$sp$(this, n, gen, alg);
   }

   public boolean apply(final Generator gen) {
      return this.apply$mcZ$sp(gen);
   }

   public boolean apply$mcZ$sp(final Generator gen) {
      return BoxesRunTime.unboxToBoolean(this.f$mcZ$sp.apply(gen));
   }

   public DistFromGen$mcZ$sp(final Function1 f$mcZ$sp) {
      super(f$mcZ$sp);
      this.f$mcZ$sp = f$mcZ$sp;
   }
}
