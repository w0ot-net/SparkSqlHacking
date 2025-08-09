package spire.random;

import algebra.ring.Field;
import algebra.ring.Rig;
import scala.Function1;
import scala.Function2;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;

public class DistFromGen$mcV$sp extends DistFromGen implements Dist$mcV$sp {
   public final Function1 f$mcV$sp;

   public void fill(final Generator gen, final BoxedUnit[] arr) {
      Dist$mcV$sp.fill$(this, gen, arr);
   }

   public void fill$mcV$sp(final Generator gen, final BoxedUnit[] arr) {
      Dist$mcV$sp.fill$mcV$sp$(this, gen, arr);
   }

   public final Dist map(final Function1 f) {
      return Dist$mcV$sp.map$(this, f);
   }

   public final Dist map$mcV$sp(final Function1 f) {
      return Dist$mcV$sp.map$mcV$sp$(this, f);
   }

   public final Dist flatMap(final Function1 f) {
      return Dist$mcV$sp.flatMap$(this, f);
   }

   public final Dist flatMap$mcV$sp(final Function1 f) {
      return Dist$mcV$sp.flatMap$mcV$sp$(this, f);
   }

   public final Dist filter(final Function1 pred) {
      return Dist$mcV$sp.filter$(this, pred);
   }

   public final Dist filter$mcV$sp(final Function1 pred) {
      return Dist$mcV$sp.filter$mcV$sp$(this, pred);
   }

   public final Dist given(final Function1 pred) {
      return Dist$mcV$sp.given$(this, pred);
   }

   public final Dist given$mcV$sp(final Function1 pred) {
      return Dist$mcV$sp.given$mcV$sp$(this, pred);
   }

   public Dist until(final Function1 pred) {
      return Dist$mcV$sp.until$(this, pred);
   }

   public Dist until$mcV$sp(final Function1 pred) {
      return Dist$mcV$sp.until$mcV$sp$(this, pred);
   }

   public Dist foldn(final Object init, final int n, final Function2 f) {
      return Dist$mcV$sp.foldn$(this, init, n, f);
   }

   public Dist foldn$mcV$sp(final Object init, final int n, final Function2 f) {
      return Dist$mcV$sp.foldn$mcV$sp$(this, init, n, f);
   }

   public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
      return Dist$mcV$sp.unfold$(this, init, f, pred);
   }

   public Dist unfold$mcV$sp(final Object init, final Function2 f, final Function1 pred) {
      return Dist$mcV$sp.unfold$mcV$sp$(this, init, f, pred);
   }

   public Dist pack(final int n, final ClassTag ct) {
      return Dist$mcV$sp.pack$(this, n, ct);
   }

   public Dist pack$mcV$sp(final int n, final ClassTag ct) {
      return Dist$mcV$sp.pack$mcV$sp$(this, n, ct);
   }

   public Dist iterate(final int n, final Function1 f) {
      return Dist$mcV$sp.iterate$(this, n, f);
   }

   public Dist iterate$mcV$sp(final int n, final Function1 f) {
      return Dist$mcV$sp.iterate$mcV$sp$(this, n, f);
   }

   public Dist iterateUntil(final Function1 pred, final Function1 f) {
      return Dist$mcV$sp.iterateUntil$(this, pred, f);
   }

   public Dist iterateUntil$mcV$sp(final Function1 pred, final Function1 f) {
      return Dist$mcV$sp.iterateUntil$mcV$sp$(this, pred, f);
   }

   public final Dist zip(final Dist that) {
      return Dist$mcV$sp.zip$(this, that);
   }

   public final Dist zip$mcV$sp(final Dist that) {
      return Dist$mcV$sp.zip$mcV$sp$(this, that);
   }

   public Dist zipWith(final Dist that, final Function2 f) {
      return Dist$mcV$sp.zipWith$(this, that, f);
   }

   public Dist zipWith$mcV$sp(final Dist that, final Function2 f) {
      return Dist$mcV$sp.zipWith$mcV$sp$(this, that, f);
   }

   public final int count(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcV$sp.count$(this, pred, n, gen);
   }

   public final int count$mcV$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcV$sp.count$mcV$sp$(this, pred, n, gen);
   }

   public double pr(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcV$sp.pr$(this, pred, n, gen);
   }

   public double pr$mcV$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcV$sp.pr$mcV$sp$(this, pred, n, gen);
   }

   public void sum(final int n, final Generator gen, final Rig alg) {
      Dist$mcV$sp.sum$(this, n, gen, alg);
   }

   public void sum$mcV$sp(final int n, final Generator gen, final Rig alg) {
      Dist$mcV$sp.sum$mcV$sp$(this, n, gen, alg);
   }

   public void ev(final int n, final Generator gen, final Field alg) {
      Dist$mcV$sp.ev$(this, n, gen, alg);
   }

   public void ev$mcV$sp(final int n, final Generator gen, final Field alg) {
      Dist$mcV$sp.ev$mcV$sp$(this, n, gen, alg);
   }

   public void apply(final Generator gen) {
      this.apply$mcV$sp(gen);
   }

   public void apply$mcV$sp(final Generator gen) {
      this.f$mcV$sp.apply(gen);
   }

   public DistFromGen$mcV$sp(final Function1 f$mcV$sp) {
      super(f$mcV$sp);
      this.f$mcV$sp = f$mcV$sp;
   }
}
