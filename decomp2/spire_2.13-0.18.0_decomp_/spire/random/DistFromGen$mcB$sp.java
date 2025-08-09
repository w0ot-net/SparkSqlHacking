package spire.random;

import algebra.ring.Field;
import algebra.ring.Rig;
import scala.Function1;
import scala.Function2;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class DistFromGen$mcB$sp extends DistFromGen implements Dist$mcB$sp {
   public final Function1 f$mcB$sp;

   public void fill(final Generator gen, final byte[] arr) {
      Dist$mcB$sp.fill$(this, gen, arr);
   }

   public void fill$mcB$sp(final Generator gen, final byte[] arr) {
      Dist$mcB$sp.fill$mcB$sp$(this, gen, arr);
   }

   public final Dist map(final Function1 f) {
      return Dist$mcB$sp.map$(this, f);
   }

   public final Dist map$mcB$sp(final Function1 f) {
      return Dist$mcB$sp.map$mcB$sp$(this, f);
   }

   public final Dist flatMap(final Function1 f) {
      return Dist$mcB$sp.flatMap$(this, f);
   }

   public final Dist flatMap$mcB$sp(final Function1 f) {
      return Dist$mcB$sp.flatMap$mcB$sp$(this, f);
   }

   public final Dist filter(final Function1 pred) {
      return Dist$mcB$sp.filter$(this, pred);
   }

   public final Dist filter$mcB$sp(final Function1 pred) {
      return Dist$mcB$sp.filter$mcB$sp$(this, pred);
   }

   public final Dist given(final Function1 pred) {
      return Dist$mcB$sp.given$(this, pred);
   }

   public final Dist given$mcB$sp(final Function1 pred) {
      return Dist$mcB$sp.given$mcB$sp$(this, pred);
   }

   public Dist until(final Function1 pred) {
      return Dist$mcB$sp.until$(this, pred);
   }

   public Dist until$mcB$sp(final Function1 pred) {
      return Dist$mcB$sp.until$mcB$sp$(this, pred);
   }

   public Dist foldn(final Object init, final int n, final Function2 f) {
      return Dist$mcB$sp.foldn$(this, init, n, f);
   }

   public Dist foldn$mcB$sp(final Object init, final int n, final Function2 f) {
      return Dist$mcB$sp.foldn$mcB$sp$(this, init, n, f);
   }

   public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
      return Dist$mcB$sp.unfold$(this, init, f, pred);
   }

   public Dist unfold$mcB$sp(final Object init, final Function2 f, final Function1 pred) {
      return Dist$mcB$sp.unfold$mcB$sp$(this, init, f, pred);
   }

   public Dist pack(final int n, final ClassTag ct) {
      return Dist$mcB$sp.pack$(this, n, ct);
   }

   public Dist pack$mcB$sp(final int n, final ClassTag ct) {
      return Dist$mcB$sp.pack$mcB$sp$(this, n, ct);
   }

   public Dist iterate(final int n, final Function1 f) {
      return Dist$mcB$sp.iterate$(this, n, f);
   }

   public Dist iterate$mcB$sp(final int n, final Function1 f) {
      return Dist$mcB$sp.iterate$mcB$sp$(this, n, f);
   }

   public Dist iterateUntil(final Function1 pred, final Function1 f) {
      return Dist$mcB$sp.iterateUntil$(this, pred, f);
   }

   public Dist iterateUntil$mcB$sp(final Function1 pred, final Function1 f) {
      return Dist$mcB$sp.iterateUntil$mcB$sp$(this, pred, f);
   }

   public final Dist zip(final Dist that) {
      return Dist$mcB$sp.zip$(this, that);
   }

   public final Dist zip$mcB$sp(final Dist that) {
      return Dist$mcB$sp.zip$mcB$sp$(this, that);
   }

   public Dist zipWith(final Dist that, final Function2 f) {
      return Dist$mcB$sp.zipWith$(this, that, f);
   }

   public Dist zipWith$mcB$sp(final Dist that, final Function2 f) {
      return Dist$mcB$sp.zipWith$mcB$sp$(this, that, f);
   }

   public final int count(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcB$sp.count$(this, pred, n, gen);
   }

   public final int count$mcB$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcB$sp.count$mcB$sp$(this, pred, n, gen);
   }

   public double pr(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcB$sp.pr$(this, pred, n, gen);
   }

   public double pr$mcB$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist$mcB$sp.pr$mcB$sp$(this, pred, n, gen);
   }

   public byte sum(final int n, final Generator gen, final Rig alg) {
      return Dist$mcB$sp.sum$(this, n, gen, alg);
   }

   public byte sum$mcB$sp(final int n, final Generator gen, final Rig alg) {
      return Dist$mcB$sp.sum$mcB$sp$(this, n, gen, alg);
   }

   public byte ev(final int n, final Generator gen, final Field alg) {
      return Dist$mcB$sp.ev$(this, n, gen, alg);
   }

   public byte ev$mcB$sp(final int n, final Generator gen, final Field alg) {
      return Dist$mcB$sp.ev$mcB$sp$(this, n, gen, alg);
   }

   public byte apply(final Generator gen) {
      return this.apply$mcB$sp(gen);
   }

   public byte apply$mcB$sp(final Generator gen) {
      return BoxesRunTime.unboxToByte(this.f$mcB$sp.apply(gen));
   }

   public DistFromGen$mcB$sp(final Function1 f$mcB$sp) {
      super(f$mcB$sp);
      this.f$mcB$sp = f$mcB$sp;
   }
}
