package spire.math.poly;

import algebra.ring.Field;
import algebra.ring.Ring;
import algebra.ring.Rng;
import algebra.ring.Semiring;
import cats.kernel.Eq;
import scala.Tuple2;

public class Term$mcD$sp extends Term {
   public final double coeff$mcD$sp;

   public double coeff$mcD$sp() {
      return this.coeff$mcD$sp;
   }

   public double coeff() {
      return this.coeff$mcD$sp();
   }

   public Term unary_$minus(final Rng r) {
      return this.unary_$minus$mcD$sp(r);
   }

   public Term unary_$minus$mcD$sp(final Rng r) {
      return new Term$mcD$sp(r.negate$mcD$sp(this.coeff()), this.exp());
   }

   public Term $plus(final Term rhs, final Semiring r) {
      return this.$plus$mcD$sp(rhs, r);
   }

   public Term $plus$mcD$sp(final Term rhs, final Semiring r) {
      if (this.exp() != rhs.exp()) {
         throw new IllegalArgumentException((new StringBuilder(31)).append("can't add terms of degree ").append(this.exp()).append(" and ").append(rhs.exp()).toString());
      } else {
         return new Term$mcD$sp(r.plus$mcD$sp(this.coeff(), rhs.coeff$mcD$sp()), this.exp());
      }
   }

   public Term $times(final Term rhs, final Semiring r) {
      return this.$times$mcD$sp(rhs, r);
   }

   public Term $times$mcD$sp(final Term rhs, final Semiring r) {
      return new Term$mcD$sp(r.times$mcD$sp(this.coeff(), rhs.coeff$mcD$sp()), this.exp() + rhs.exp());
   }

   public Tuple2 toTuple() {
      return this.toTuple$mcD$sp();
   }

   public Tuple2 toTuple$mcD$sp() {
      return new Tuple2.mcID.sp(this.exp(), this.coeff());
   }

   public double eval(final double x, final Semiring r) {
      return this.eval$mcD$sp(x, r);
   }

   public double eval$mcD$sp(final double x, final Semiring r) {
      return this.exp() != 0 ? r.times$mcD$sp(this.coeff(), r.pow$mcD$sp(x, this.exp())) : this.coeff();
   }

   public boolean isZero(final Semiring ring, final Eq eq) {
      return this.isZero$mcD$sp(ring, eq);
   }

   public boolean isZero$mcD$sp(final Semiring ring, final Eq eq) {
      return eq.eqv$mcD$sp(this.coeff(), ring.zero$mcD$sp());
   }

   public Term divideBy(final double x, final Field f) {
      return this.divideBy$mcD$sp(x, f);
   }

   public Term divideBy$mcD$sp(final double x, final Field f) {
      return new Term$mcD$sp(f.div$mcD$sp(this.coeff(), x), this.exp());
   }

   public Term der(final Ring r) {
      return this.der$mcD$sp(r);
   }

   public Term der$mcD$sp(final Ring r) {
      return new Term$mcD$sp(r.times$mcD$sp(this.coeff(), r.fromInt$mcD$sp(this.exp())), this.exp() - 1);
   }

   public Term int(final Field f) {
      return this.int$mcD$sp(f);
   }

   public Term int$mcD$sp(final Field f) {
      return new Term$mcD$sp(f.div$mcD$sp(this.coeff(), f.fromInt$mcD$sp(this.exp() + 1)), this.exp() + 1);
   }

   public double copy$default$1() {
      return this.copy$default$1$mcD$sp();
   }

   public double copy$default$1$mcD$sp() {
      return this.coeff();
   }

   public boolean specInstance$() {
      return true;
   }

   public Term$mcD$sp(final double coeff$mcD$sp, final int exp) {
      super((Object)null, exp);
      this.coeff$mcD$sp = coeff$mcD$sp;
   }
}
