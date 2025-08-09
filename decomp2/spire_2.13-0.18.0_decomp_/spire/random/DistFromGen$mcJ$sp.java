package spire.random;

import algebra.ring.Field;
import algebra.ring.Rig;
import scala.Function1;
import scala.Function2;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class DistFromGen$mcJ$sp extends DistFromGen implements Dist$mcJ$sp {
   public final Function1 f$mcJ$sp;

   public void fill(final Generator gen, final long[] arr) {
      Dist$mcJ$sp.fill$(this, gen, arr);
   }

   public void fill$mcJ$sp(final Generator gen, final long[] arr) {
      Dist$mcJ$sp.fill$mcJ$sp$(this, gen, arr);
   }

   public final Dist map(final Function1 f) {
      return Dist$mcJ$sp.map$(this, f);
   }

   public final Dist map$mcJ$sp(final Function1 f) {
      return Dist$mcJ$sp.map$mcJ$sp$(this, f);
   }

   public final Dist flatMap(final Function1 f) {
      return Dist$mcJ$sp.flatMap$(this, f);
   }

   public final Dist flatMap$mcJ$sp(final Function1 f) {
      return Dist$mcJ$sp.flatMap$mcJ$sp$(this, f);
   }

   public final Dist filter(final Function1 pred) {
      return Dist$mcJ$sp.filter$(this, pred);
   }

   public final Dist filter$mcJ$sp(final Function1 pred) {
      return Dist$mcJ$sp.filter$mcJ$sp$(this, pred);
   }

   public final Dist given(final Function1 pred) {
      return Dist$mcJ$sp.given$(this, pred);
   }

   public final Dist given$mcJ$sp(final Function1 pred) {
      return Dist$mcJ$sp.given$mcJ$sp$(this, pred);
   }

   public Dist until(final Function1 pred) {
      return Dist$mcJ$sp.until$(this, pred);
   }

   public Dist until$mcJ$sp(final Function1 pred) {
      return Dist$mcJ$sp.until$mcJ$sp$(this, pred);
   }

   public Dist foldn(final Object init, final int n, final Function2 f) {
      return Dist$mcJ$sp.foldn$(this, init, n, f);
   }

   public Dist foldn$mcJ$sp(final Object init, final int n, final Function2 f) {
      return Dist$mcJ$sp.foldn$mcJ$sp$(this, init, n, f);
   }

   public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
      return Dist$mcJ$sp.unfold$(this, init, f, pred);
   }

   public Dist unfold$mcJ$sp(final Object init, final Function2 f, final Function1 pred) {
      return Dist$mcJ$sp.unfold$mcJ$sp$(this, init, f, pred);
   }

   public Dist pack(final int n, final ClassTag ct) {
      return Dist$mcJ$sp.pack$(this, n, ct);
   }

   public Dist pack$mcJ$sp(final int n, final ClassTag ct) {
      return Dist$mcJ$sp.pack$mcJ$sp$(this, n, ct);
   }

   public Dist iterate(final int n, final Function1 f) {
      return Dist$mcJ$sp.iterate$(this, n, f);
   }

   public Dist iterate$mcJ$sp(final int n, final Function1 f) {
      return Dist$mcJ$sp.iterate$mcJ$sp$(this, n, f);
   }

   public Dist iterateUntil(final Function1 pred, final Function1 f) {
      return Dist$mcJ$sp.iterateUntil$(this, pred, f);
   }

   public Dist iterateUntil$mcJ$sp(final Function1 pred, final Function1 f) {
      return Dist$mcJ$sp.iterateUntil$mcJ$sp$(this, pred, f);
   }

   public final Dist zip(final Dist that) {
      return Dist$mcJ$sp.zip$(this, that);
   }

   public final Dist zip$mcJ$sp(final Dist that) {
      return Dist$mcJ$sp.zip$mcJ$sp$(this, that);
   }

   public Dist zipWith(final Dist that, final Function2 f) {
      return Dist$mcJ$sp.zipWith$(this, that, f);
   }

   public Dist zipWith$mcJ$sp(final Dist that, final Function2 f) {
      return Dist$mcJ$sp.zipWith$mcJ$sp$(this, that, f);
   }

   public final int count(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcJ$sp.count$(this, pred, n, gen);
   }

   public final int count$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcJ$sp.count$mcJ$sp$(this, pred, n, gen);
   }

   public double pr(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcJ$sp.pr$(this, pred, n, gen);
   }

   public double pr$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcJ$sp.pr$mcJ$sp$(this, pred, n, gen);
   }

   public long sum(final int n, final Generator gen, final Rig alg) {
      return Dist$mcJ$sp.sum$(this, n, gen, alg);
   }

   public long sum$mcJ$sp(final int n, final Generator gen, final Rig alg) {
      return Dist$mcJ$sp.sum$mcJ$sp$(this, n, gen, alg);
   }

   public long ev(final int n, final Generator gen, final Field alg) {
      return Dist$mcJ$sp.ev$(this, n, gen, alg);
   }

   public long ev$mcJ$sp(final int n, final Generator gen, final Field alg) {
      return Dist$mcJ$sp.ev$mcJ$sp$(this, n, gen, alg);
   }

   public long apply(final Generator gen) {
      return this.apply$mcJ$sp(gen);
   }

   public long apply$mcJ$sp(final Generator gen) {
      return BoxesRunTime.unboxToLong(this.f$mcJ$sp.apply(gen));
   }

   public DistFromGen$mcJ$sp(final Function1 f$mcJ$sp) {
      super(f$mcJ$sp);
      this.f$mcJ$sp = f$mcJ$sp;
   }
}
