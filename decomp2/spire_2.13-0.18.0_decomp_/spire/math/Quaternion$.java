package spire.math;

import algebra.ring.CommutativeRing;
import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Order;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import spire.algebra.NRoot;
import spire.algebra.Trig;

public final class Quaternion$ implements QuaternionInstances, Serializable {
   public static final Quaternion$ MODULE$ = new Quaternion$();

   static {
      QuaternionInstances1.$init$(MODULE$);
      QuaternionInstances.$init$(MODULE$);
   }

   public QuaternionOverRichField QuaternionOverRichField(final Field f0, final NRoot n0, final Order o0, final Signed s0, final Trig t0) {
      return QuaternionInstances.QuaternionOverRichField$(this, f0, n0, o0, s0, t0);
   }

   public QuaternionOverField QuaternionOverField(final Field f0, final Order o0, final Signed s0) {
      return QuaternionInstances1.QuaternionOverField$(this, f0, o0, s0);
   }

   public Quaternion i(final CommutativeRing f) {
      return new Quaternion(f.zero(), f.one(), f.zero(), f.zero());
   }

   public Quaternion j(final CommutativeRing f) {
      return new Quaternion(f.zero(), f.zero(), f.one(), f.zero());
   }

   public Quaternion k(final CommutativeRing f) {
      return new Quaternion(f.zero(), f.zero(), f.zero(), f.one());
   }

   public Quaternion zero(final CommutativeRing f) {
      return new Quaternion(f.zero(), f.zero(), f.zero(), f.zero());
   }

   public Quaternion one(final CommutativeRing f) {
      return new Quaternion(f.one(), f.zero(), f.zero(), f.zero());
   }

   public Quaternion apply(final Object a, final CommutativeRing f) {
      return new Quaternion(a, f.zero(), f.zero(), f.zero());
   }

   public Quaternion apply(final Complex c, final CommutativeRing f) {
      return new Quaternion(c.real(), c.imag(), f.zero(), f.zero());
   }

   public Quaternion apply(final Object r, final Object i, final Object j, final Object k) {
      return new Quaternion(r, i, j, k);
   }

   public Option unapply(final Quaternion x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.r(), x$0.i(), x$0.j(), x$0.k())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Quaternion$.class);
   }

   public Quaternion i$mDc$sp(final CommutativeRing f) {
      return new Quaternion$mcD$sp(f.zero$mcD$sp(), f.one$mcD$sp(), f.zero$mcD$sp(), f.zero$mcD$sp());
   }

   public Quaternion i$mFc$sp(final CommutativeRing f) {
      return new Quaternion$mcF$sp(f.zero$mcF$sp(), f.one$mcF$sp(), f.zero$mcF$sp(), f.zero$mcF$sp());
   }

   public Quaternion j$mDc$sp(final CommutativeRing f) {
      return new Quaternion$mcD$sp(f.zero$mcD$sp(), f.zero$mcD$sp(), f.one$mcD$sp(), f.zero$mcD$sp());
   }

   public Quaternion j$mFc$sp(final CommutativeRing f) {
      return new Quaternion$mcF$sp(f.zero$mcF$sp(), f.zero$mcF$sp(), f.one$mcF$sp(), f.zero$mcF$sp());
   }

   public Quaternion k$mDc$sp(final CommutativeRing f) {
      return new Quaternion$mcD$sp(f.zero$mcD$sp(), f.zero$mcD$sp(), f.zero$mcD$sp(), f.one$mcD$sp());
   }

   public Quaternion k$mFc$sp(final CommutativeRing f) {
      return new Quaternion$mcF$sp(f.zero$mcF$sp(), f.zero$mcF$sp(), f.zero$mcF$sp(), f.one$mcF$sp());
   }

   public Quaternion zero$mDc$sp(final CommutativeRing f) {
      return new Quaternion$mcD$sp(f.zero$mcD$sp(), f.zero$mcD$sp(), f.zero$mcD$sp(), f.zero$mcD$sp());
   }

   public Quaternion zero$mFc$sp(final CommutativeRing f) {
      return new Quaternion$mcF$sp(f.zero$mcF$sp(), f.zero$mcF$sp(), f.zero$mcF$sp(), f.zero$mcF$sp());
   }

   public Quaternion one$mDc$sp(final CommutativeRing f) {
      return new Quaternion$mcD$sp(f.one$mcD$sp(), f.zero$mcD$sp(), f.zero$mcD$sp(), f.zero$mcD$sp());
   }

   public Quaternion one$mFc$sp(final CommutativeRing f) {
      return new Quaternion$mcF$sp(f.one$mcF$sp(), f.zero$mcF$sp(), f.zero$mcF$sp(), f.zero$mcF$sp());
   }

   public Quaternion apply$mDc$sp(final double a, final CommutativeRing f) {
      return new Quaternion$mcD$sp(a, f.zero$mcD$sp(), f.zero$mcD$sp(), f.zero$mcD$sp());
   }

   public Quaternion apply$mFc$sp(final float a, final CommutativeRing f) {
      return new Quaternion$mcF$sp(a, f.zero$mcF$sp(), f.zero$mcF$sp(), f.zero$mcF$sp());
   }

   public Quaternion apply$mDc$sp(final Complex c, final CommutativeRing f) {
      return new Quaternion$mcD$sp(c.real$mcD$sp(), c.imag$mcD$sp(), f.zero$mcD$sp(), f.zero$mcD$sp());
   }

   public Quaternion apply$mFc$sp(final Complex c, final CommutativeRing f) {
      return new Quaternion$mcF$sp(c.real$mcF$sp(), c.imag$mcF$sp(), f.zero$mcF$sp(), f.zero$mcF$sp());
   }

   public Quaternion apply$mDc$sp(final double r, final double i, final double j, final double k) {
      return new Quaternion$mcD$sp(r, i, j, k);
   }

   public Quaternion apply$mFc$sp(final float r, final float i, final float j, final float k) {
      return new Quaternion$mcF$sp(r, i, j, k);
   }

   public Option unapply$mDc$sp(final Quaternion x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(BoxesRunTime.boxToDouble(x$0.r$mcD$sp()), BoxesRunTime.boxToDouble(x$0.i$mcD$sp()), BoxesRunTime.boxToDouble(x$0.j$mcD$sp()), BoxesRunTime.boxToDouble(x$0.k$mcD$sp()))));
   }

   public Option unapply$mFc$sp(final Quaternion x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(BoxesRunTime.boxToFloat(x$0.r$mcF$sp()), BoxesRunTime.boxToFloat(x$0.i$mcF$sp()), BoxesRunTime.boxToFloat(x$0.j$mcF$sp()), BoxesRunTime.boxToFloat(x$0.k$mcF$sp()))));
   }

   private Quaternion$() {
   }
}
