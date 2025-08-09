package spire.random;

import algebra.ring.Field;
import algebra.ring.Rig;
import scala.Function1;
import scala.Function2;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class DistFromGen$mcC$sp extends DistFromGen implements Dist$mcC$sp {
   public final Function1 f$mcC$sp;

   public void fill(final Generator gen, final char[] arr) {
      Dist$mcC$sp.fill$(this, gen, arr);
   }

   public void fill$mcC$sp(final Generator gen, final char[] arr) {
      Dist$mcC$sp.fill$mcC$sp$(this, gen, arr);
   }

   public final Dist map(final Function1 f) {
      return Dist$mcC$sp.map$(this, f);
   }

   public final Dist map$mcC$sp(final Function1 f) {
      return Dist$mcC$sp.map$mcC$sp$(this, f);
   }

   public final Dist flatMap(final Function1 f) {
      return Dist$mcC$sp.flatMap$(this, f);
   }

   public final Dist flatMap$mcC$sp(final Function1 f) {
      return Dist$mcC$sp.flatMap$mcC$sp$(this, f);
   }

   public final Dist filter(final Function1 pred) {
      return Dist$mcC$sp.filter$(this, pred);
   }

   public final Dist filter$mcC$sp(final Function1 pred) {
      return Dist$mcC$sp.filter$mcC$sp$(this, pred);
   }

   public final Dist given(final Function1 pred) {
      return Dist$mcC$sp.given$(this, pred);
   }

   public final Dist given$mcC$sp(final Function1 pred) {
      return Dist$mcC$sp.given$mcC$sp$(this, pred);
   }

   public Dist until(final Function1 pred) {
      return Dist$mcC$sp.until$(this, pred);
   }

   public Dist until$mcC$sp(final Function1 pred) {
      return Dist$mcC$sp.until$mcC$sp$(this, pred);
   }

   public Dist foldn(final Object init, final int n, final Function2 f) {
      return Dist$mcC$sp.foldn$(this, init, n, f);
   }

   public Dist foldn$mcC$sp(final Object init, final int n, final Function2 f) {
      return Dist$mcC$sp.foldn$mcC$sp$(this, init, n, f);
   }

   public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
      return Dist$mcC$sp.unfold$(this, init, f, pred);
   }

   public Dist unfold$mcC$sp(final Object init, final Function2 f, final Function1 pred) {
      return Dist$mcC$sp.unfold$mcC$sp$(this, init, f, pred);
   }

   public Dist pack(final int n, final ClassTag ct) {
      return Dist$mcC$sp.pack$(this, n, ct);
   }

   public Dist pack$mcC$sp(final int n, final ClassTag ct) {
      return Dist$mcC$sp.pack$mcC$sp$(this, n, ct);
   }

   public Dist iterate(final int n, final Function1 f) {
      return Dist$mcC$sp.iterate$(this, n, f);
   }

   public Dist iterate$mcC$sp(final int n, final Function1 f) {
      return Dist$mcC$sp.iterate$mcC$sp$(this, n, f);
   }

   public Dist iterateUntil(final Function1 pred, final Function1 f) {
      return Dist$mcC$sp.iterateUntil$(this, pred, f);
   }

   public Dist iterateUntil$mcC$sp(final Function1 pred, final Function1 f) {
      return Dist$mcC$sp.iterateUntil$mcC$sp$(this, pred, f);
   }

   public final Dist zip(final Dist that) {
      return Dist$mcC$sp.zip$(this, that);
   }

   public final Dist zip$mcC$sp(final Dist that) {
      return Dist$mcC$sp.zip$mcC$sp$(this, that);
   }

   public Dist zipWith(final Dist that, final Function2 f) {
      return Dist$mcC$sp.zipWith$(this, that, f);
   }

   public Dist zipWith$mcC$sp(final Dist that, final Function2 f) {
      return Dist$mcC$sp.zipWith$mcC$sp$(this, that, f);
   }

   public final int count(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcC$sp.count$(this, pred, n, gen);
   }

   public final int count$mcC$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcC$sp.count$mcC$sp$(this, pred, n, gen);
   }

   public double pr(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcC$sp.pr$(this, pred, n, gen);
   }

   public double pr$mcC$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcC$sp.pr$mcC$sp$(this, pred, n, gen);
   }

   public char sum(final int n, final Generator gen, final Rig alg) {
      return Dist$mcC$sp.sum$(this, n, gen, alg);
   }

   public char sum$mcC$sp(final int n, final Generator gen, final Rig alg) {
      return Dist$mcC$sp.sum$mcC$sp$(this, n, gen, alg);
   }

   public char ev(final int n, final Generator gen, final Field alg) {
      return Dist$mcC$sp.ev$(this, n, gen, alg);
   }

   public char ev$mcC$sp(final int n, final Generator gen, final Field alg) {
      return Dist$mcC$sp.ev$mcC$sp$(this, n, gen, alg);
   }

   public char apply(final Generator gen) {
      return this.apply$mcC$sp(gen);
   }

   public char apply$mcC$sp(final Generator gen) {
      return BoxesRunTime.unboxToChar(this.f$mcC$sp.apply(gen));
   }

   public DistFromGen$mcC$sp(final Function1 f$mcC$sp) {
      super(f$mcC$sp);
      this.f$mcC$sp = f$mcC$sp;
   }
}
