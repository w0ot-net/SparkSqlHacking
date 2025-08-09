package spire.math;

public interface ComplexOnTrig$mcD$sp extends ComplexOnTrig {
   // $FF: synthetic method
   static Complex e$(final ComplexOnTrig$mcD$sp $this) {
      return $this.e();
   }

   default Complex e() {
      return this.e$mcD$sp();
   }

   // $FF: synthetic method
   static Complex e$mcD$sp$(final ComplexOnTrig$mcD$sp $this) {
      return $this.e$mcD$sp();
   }

   default Complex e$mcD$sp() {
      return new Complex$mcD$sp(this.trig$mcD$sp().e$mcD$sp(), this.scalar$mcD$sp().zero$mcD$sp());
   }

   // $FF: synthetic method
   static Complex pi$(final ComplexOnTrig$mcD$sp $this) {
      return $this.pi();
   }

   default Complex pi() {
      return this.pi$mcD$sp();
   }

   // $FF: synthetic method
   static Complex pi$mcD$sp$(final ComplexOnTrig$mcD$sp $this) {
      return $this.pi$mcD$sp();
   }

   default Complex pi$mcD$sp() {
      return new Complex$mcD$sp(this.trig$mcD$sp().pi$mcD$sp(), this.scalar$mcD$sp().zero$mcD$sp());
   }

   // $FF: synthetic method
   static Complex exp$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.exp(a);
   }

   default Complex exp(final Complex a) {
      return this.exp$mcD$sp(a);
   }

   // $FF: synthetic method
   static Complex exp$mcD$sp$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.exp$mcD$sp(a);
   }

   default Complex exp$mcD$sp(final Complex a) {
      return a.exp$mcD$sp(this.scalar$mcD$sp(), this.trig$mcD$sp());
   }

   // $FF: synthetic method
   static Complex expm1$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.expm1(a);
   }

   default Complex expm1(final Complex a) {
      return this.expm1$mcD$sp(a);
   }

   // $FF: synthetic method
   static Complex expm1$mcD$sp$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.expm1$mcD$sp(a);
   }

   default Complex expm1$mcD$sp(final Complex a) {
      return a.exp$mcD$sp(this.scalar$mcD$sp(), this.trig$mcD$sp()).$minus$mcD$sp(this.scalar$mcD$sp().one$mcD$sp(), this.scalar$mcD$sp());
   }

   // $FF: synthetic method
   static Complex log$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.log(a);
   }

   default Complex log(final Complex a) {
      return this.log$mcD$sp(a);
   }

   // $FF: synthetic method
   static Complex log$mcD$sp$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.log$mcD$sp(a);
   }

   default Complex log$mcD$sp(final Complex a) {
      return a.log$mcD$sp(this.scalar$mcD$sp(), this.nroot$mcD$sp(), this.order$mcD$sp(), this.trig$mcD$sp(), this.signed$mcD$sp());
   }

   // $FF: synthetic method
   static Complex log1p$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.log1p(a);
   }

   default Complex log1p(final Complex a) {
      return this.log1p$mcD$sp(a);
   }

   // $FF: synthetic method
   static Complex log1p$mcD$sp$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.log1p$mcD$sp(a);
   }

   default Complex log1p$mcD$sp(final Complex a) {
      return a.$plus$mcD$sp(this.scalar$mcD$sp().one$mcD$sp(), this.scalar$mcD$sp()).log$mcD$sp(this.scalar$mcD$sp(), this.nroot$mcD$sp(), this.order$mcD$sp(), this.trig$mcD$sp(), this.signed$mcD$sp());
   }

   // $FF: synthetic method
   static Complex sin$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.sin(a);
   }

   default Complex sin(final Complex a) {
      return this.sin$mcD$sp(a);
   }

   // $FF: synthetic method
   static Complex sin$mcD$sp$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.sin$mcD$sp(a);
   }

   default Complex sin$mcD$sp(final Complex a) {
      return a.sin$mcD$sp(this.scalar$mcD$sp(), this.trig$mcD$sp());
   }

   // $FF: synthetic method
   static Complex cos$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.cos(a);
   }

   default Complex cos(final Complex a) {
      return this.cos$mcD$sp(a);
   }

   // $FF: synthetic method
   static Complex cos$mcD$sp$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.cos$mcD$sp(a);
   }

   default Complex cos$mcD$sp(final Complex a) {
      return a.cos$mcD$sp(this.scalar$mcD$sp(), this.trig$mcD$sp());
   }

   // $FF: synthetic method
   static Complex tan$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.tan(a);
   }

   default Complex tan(final Complex a) {
      return this.tan$mcD$sp(a);
   }

   // $FF: synthetic method
   static Complex tan$mcD$sp$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.tan$mcD$sp(a);
   }

   default Complex tan$mcD$sp(final Complex a) {
      return a.tan$mcD$sp(this.scalar$mcD$sp(), this.trig$mcD$sp());
   }

   // $FF: synthetic method
   static Complex asin$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.asin(a);
   }

   default Complex asin(final Complex a) {
      return this.asin$mcD$sp(a);
   }

   // $FF: synthetic method
   static Complex asin$mcD$sp$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.asin$mcD$sp(a);
   }

   default Complex asin$mcD$sp(final Complex a) {
      return a.asin$mcD$sp(this.scalar$mcD$sp(), this.nroot$mcD$sp(), this.order$mcD$sp(), this.trig$mcD$sp(), this.signed$mcD$sp());
   }

   // $FF: synthetic method
   static Complex acos$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.acos(a);
   }

   default Complex acos(final Complex a) {
      return this.acos$mcD$sp(a);
   }

   // $FF: synthetic method
   static Complex acos$mcD$sp$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.acos$mcD$sp(a);
   }

   default Complex acos$mcD$sp(final Complex a) {
      return a.acos$mcD$sp(this.scalar$mcD$sp(), this.nroot$mcD$sp(), this.order$mcD$sp(), this.trig$mcD$sp(), this.signed$mcD$sp());
   }

   // $FF: synthetic method
   static Complex atan$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.atan(a);
   }

   default Complex atan(final Complex a) {
      return this.atan$mcD$sp(a);
   }

   // $FF: synthetic method
   static Complex atan$mcD$sp$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.atan$mcD$sp(a);
   }

   default Complex atan$mcD$sp(final Complex a) {
      return a.atan$mcD$sp(this.scalar$mcD$sp(), this.order$mcD$sp(), this.nroot$mcD$sp(), this.signed$mcD$sp(), this.trig$mcD$sp());
   }

   // $FF: synthetic method
   static Complex atan2$(final ComplexOnTrig$mcD$sp $this, final Complex y, final Complex x) {
      return $this.atan2(y, x);
   }

   default Complex atan2(final Complex y, final Complex x) {
      return this.atan2$mcD$sp(y, x);
   }

   // $FF: synthetic method
   static Complex atan2$mcD$sp$(final ComplexOnTrig$mcD$sp $this, final Complex y, final Complex x) {
      return $this.atan2$mcD$sp(y, x);
   }

   default Complex atan2$mcD$sp(final Complex y, final Complex x) {
      return (new Complex$mcD$sp(x.real$mcD$sp(), y.imag$mcD$sp())).atan$mcD$sp(this.scalar$mcD$sp(), this.order$mcD$sp(), this.nroot$mcD$sp(), this.signed$mcD$sp(), this.trig$mcD$sp());
   }

   // $FF: synthetic method
   static Complex sinh$(final ComplexOnTrig$mcD$sp $this, final Complex x) {
      return $this.sinh(x);
   }

   default Complex sinh(final Complex x) {
      return this.sinh$mcD$sp(x);
   }

   // $FF: synthetic method
   static Complex sinh$mcD$sp$(final ComplexOnTrig$mcD$sp $this, final Complex x) {
      return $this.sinh$mcD$sp(x);
   }

   default Complex sinh$mcD$sp(final Complex x) {
      return x.sinh$mcD$sp(this.scalar$mcD$sp(), this.trig$mcD$sp());
   }

   // $FF: synthetic method
   static Complex cosh$(final ComplexOnTrig$mcD$sp $this, final Complex x) {
      return $this.cosh(x);
   }

   default Complex cosh(final Complex x) {
      return this.cosh$mcD$sp(x);
   }

   // $FF: synthetic method
   static Complex cosh$mcD$sp$(final ComplexOnTrig$mcD$sp $this, final Complex x) {
      return $this.cosh$mcD$sp(x);
   }

   default Complex cosh$mcD$sp(final Complex x) {
      return x.cosh$mcD$sp(this.scalar$mcD$sp(), this.trig$mcD$sp());
   }

   // $FF: synthetic method
   static Complex tanh$(final ComplexOnTrig$mcD$sp $this, final Complex x) {
      return $this.tanh(x);
   }

   default Complex tanh(final Complex x) {
      return this.tanh$mcD$sp(x);
   }

   // $FF: synthetic method
   static Complex tanh$mcD$sp$(final ComplexOnTrig$mcD$sp $this, final Complex x) {
      return $this.tanh$mcD$sp(x);
   }

   default Complex tanh$mcD$sp(final Complex x) {
      return x.tanh$mcD$sp(this.scalar$mcD$sp(), this.trig$mcD$sp());
   }

   // $FF: synthetic method
   static Complex toRadians$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.toRadians(a);
   }

   default Complex toRadians(final Complex a) {
      return this.toRadians$mcD$sp(a);
   }

   // $FF: synthetic method
   static Complex toRadians$mcD$sp$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.toRadians$mcD$sp(a);
   }

   default Complex toRadians$mcD$sp(final Complex a) {
      return a;
   }

   // $FF: synthetic method
   static Complex toDegrees$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.toDegrees(a);
   }

   default Complex toDegrees(final Complex a) {
      return this.toDegrees$mcD$sp(a);
   }

   // $FF: synthetic method
   static Complex toDegrees$mcD$sp$(final ComplexOnTrig$mcD$sp $this, final Complex a) {
      return $this.toDegrees$mcD$sp(a);
   }

   default Complex toDegrees$mcD$sp(final Complex a) {
      return a;
   }
}
