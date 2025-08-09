package spire.random;

import algebra.ring.Field;
import algebra.ring.Rig;
import scala.Function1;
import scala.Function2;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class DistFromGen$mcF$sp extends DistFromGen implements Dist$mcF$sp {
   public final Function1 f$mcF$sp;

   public void fill(final Generator gen, final float[] arr) {
      Dist$mcF$sp.fill$(this, gen, arr);
   }

   public void fill$mcF$sp(final Generator gen, final float[] arr) {
      Dist$mcF$sp.fill$mcF$sp$(this, gen, arr);
   }

   public final Dist map(final Function1 f) {
      return Dist$mcF$sp.map$(this, f);
   }

   public final Dist map$mcF$sp(final Function1 f) {
      return Dist$mcF$sp.map$mcF$sp$(this, f);
   }

   public final Dist flatMap(final Function1 f) {
      return Dist$mcF$sp.flatMap$(this, f);
   }

   public final Dist flatMap$mcF$sp(final Function1 f) {
      return Dist$mcF$sp.flatMap$mcF$sp$(this, f);
   }

   public final Dist filter(final Function1 pred) {
      return Dist$mcF$sp.filter$(this, pred);
   }

   public final Dist filter$mcF$sp(final Function1 pred) {
      return Dist$mcF$sp.filter$mcF$sp$(this, pred);
   }

   public final Dist given(final Function1 pred) {
      return Dist$mcF$sp.given$(this, pred);
   }

   public final Dist given$mcF$sp(final Function1 pred) {
      return Dist$mcF$sp.given$mcF$sp$(this, pred);
   }

   public Dist until(final Function1 pred) {
      return Dist$mcF$sp.until$(this, pred);
   }

   public Dist until$mcF$sp(final Function1 pred) {
      return Dist$mcF$sp.until$mcF$sp$(this, pred);
   }

   public Dist foldn(final Object init, final int n, final Function2 f) {
      return Dist$mcF$sp.foldn$(this, init, n, f);
   }

   public Dist foldn$mcF$sp(final Object init, final int n, final Function2 f) {
      return Dist$mcF$sp.foldn$mcF$sp$(this, init, n, f);
   }

   public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
      return Dist$mcF$sp.unfold$(this, init, f, pred);
   }

   public Dist unfold$mcF$sp(final Object init, final Function2 f, final Function1 pred) {
      return Dist$mcF$sp.unfold$mcF$sp$(this, init, f, pred);
   }

   public Dist pack(final int n, final ClassTag ct) {
      return Dist$mcF$sp.pack$(this, n, ct);
   }

   public Dist pack$mcF$sp(final int n, final ClassTag ct) {
      return Dist$mcF$sp.pack$mcF$sp$(this, n, ct);
   }

   public Dist iterate(final int n, final Function1 f) {
      return Dist$mcF$sp.iterate$(this, n, f);
   }

   public Dist iterate$mcF$sp(final int n, final Function1 f) {
      return Dist$mcF$sp.iterate$mcF$sp$(this, n, f);
   }

   public Dist iterateUntil(final Function1 pred, final Function1 f) {
      return Dist$mcF$sp.iterateUntil$(this, pred, f);
   }

   public Dist iterateUntil$mcF$sp(final Function1 pred, final Function1 f) {
      return Dist$mcF$sp.iterateUntil$mcF$sp$(this, pred, f);
   }

   public final Dist zip(final Dist that) {
      return Dist$mcF$sp.zip$(this, that);
   }

   public final Dist zip$mcF$sp(final Dist that) {
      return Dist$mcF$sp.zip$mcF$sp$(this, that);
   }

   public Dist zipWith(final Dist that, final Function2 f) {
      return Dist$mcF$sp.zipWith$(this, that, f);
   }

   public Dist zipWith$mcF$sp(final Dist that, final Function2 f) {
      return Dist$mcF$sp.zipWith$mcF$sp$(this, that, f);
   }

   public final int count(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcF$sp.count$(this, pred, n, gen);
   }

   public final int count$mcF$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcF$sp.count$mcF$sp$(this, pred, n, gen);
   }

   public double pr(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcF$sp.pr$(this, pred, n, gen);
   }

   public double pr$mcF$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcF$sp.pr$mcF$sp$(this, pred, n, gen);
   }

   public float sum(final int n, final Generator gen, final Rig alg) {
      return Dist$mcF$sp.sum$(this, n, gen, alg);
   }

   public float sum$mcF$sp(final int n, final Generator gen, final Rig alg) {
      return Dist$mcF$sp.sum$mcF$sp$(this, n, gen, alg);
   }

   public float ev(final int n, final Generator gen, final Field alg) {
      return Dist$mcF$sp.ev$(this, n, gen, alg);
   }

   public float ev$mcF$sp(final int n, final Generator gen, final Field alg) {
      return Dist$mcF$sp.ev$mcF$sp$(this, n, gen, alg);
   }

   public float apply(final Generator gen) {
      return this.apply$mcF$sp(gen);
   }

   public float apply$mcF$sp(final Generator gen) {
      return BoxesRunTime.unboxToFloat(this.f$mcF$sp.apply(gen));
   }

   public DistFromGen$mcF$sp(final Function1 f$mcF$sp) {
      super(f$mcF$sp);
      this.f$mcF$sp = f$mcF$sp;
   }
}
