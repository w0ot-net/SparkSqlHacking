package spire.math.poly;

import algebra.ring.Field;
import algebra.ring.Ring;
import algebra.ring.Rng;
import algebra.ring.Semiring;
import cats.kernel.Eq;
import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public class Term$mcF$sp extends Term {
   public final float coeff$mcF$sp;

   public float coeff$mcF$sp() {
      return this.coeff$mcF$sp;
   }

   public float coeff() {
      return this.coeff$mcF$sp();
   }

   public Term unary_$minus(final Rng r) {
      return this.unary_$minus$mcF$sp(r);
   }

   public Term unary_$minus$mcF$sp(final Rng r) {
      return new Term$mcF$sp(r.negate$mcF$sp(this.coeff()), this.exp());
   }

   public Term $plus(final Term rhs, final Semiring r) {
      return this.$plus$mcF$sp(rhs, r);
   }

   public Term $plus$mcF$sp(final Term rhs, final Semiring r) {
      if (this.exp() != rhs.exp()) {
         throw new IllegalArgumentException((new StringBuilder(31)).append("can't add terms of degree ").append(this.exp()).append(" and ").append(rhs.exp()).toString());
      } else {
         return new Term$mcF$sp(r.plus$mcF$sp(this.coeff(), rhs.coeff$mcF$sp()), this.exp());
      }
   }

   public Term $times(final Term rhs, final Semiring r) {
      return this.$times$mcF$sp(rhs, r);
   }

   public Term $times$mcF$sp(final Term rhs, final Semiring r) {
      return new Term$mcF$sp(r.times$mcF$sp(this.coeff(), rhs.coeff$mcF$sp()), this.exp() + rhs.exp());
   }

   public Tuple2 toTuple() {
      return this.toTuple$mcF$sp();
   }

   public Tuple2 toTuple$mcF$sp() {
      return new Tuple2(BoxesRunTime.boxToInteger(this.exp()), BoxesRunTime.boxToFloat(this.coeff()));
   }

   public float eval(final float x, final Semiring r) {
      return this.eval$mcF$sp(x, r);
   }

   public float eval$mcF$sp(final float x, final Semiring r) {
      return this.exp() != 0 ? r.times$mcF$sp(this.coeff(), r.pow$mcF$sp(x, this.exp())) : this.coeff();
   }

   public boolean isZero(final Semiring ring, final Eq eq) {
      return this.isZero$mcF$sp(ring, eq);
   }

   public boolean isZero$mcF$sp(final Semiring ring, final Eq eq) {
      return eq.eqv$mcF$sp(this.coeff(), ring.zero$mcF$sp());
   }

   public Term divideBy(final float x, final Field f) {
      return this.divideBy$mcF$sp(x, f);
   }

   public Term divideBy$mcF$sp(final float x, final Field f) {
      return new Term$mcF$sp(f.div$mcF$sp(this.coeff(), x), this.exp());
   }

   public Term der(final Ring r) {
      return this.der$mcF$sp(r);
   }

   public Term der$mcF$sp(final Ring r) {
      return new Term$mcF$sp(r.times$mcF$sp(this.coeff(), r.fromInt$mcF$sp(this.exp())), this.exp() - 1);
   }

   public Term int(final Field f) {
      return this.int$mcF$sp(f);
   }

   public Term int$mcF$sp(final Field f) {
      return new Term$mcF$sp(f.div$mcF$sp(this.coeff(), f.fromInt$mcF$sp(this.exp() + 1)), this.exp() + 1);
   }

   public float copy$default$1() {
      return this.copy$default$1$mcF$sp();
   }

   public float copy$default$1$mcF$sp() {
      return this.coeff();
   }

   public boolean specInstance$() {
      return true;
   }

   public Term$mcF$sp(final float coeff$mcF$sp, final int exp) {
      super((Object)null, exp);
      this.coeff$mcF$sp = coeff$mcF$sp;
   }
}
