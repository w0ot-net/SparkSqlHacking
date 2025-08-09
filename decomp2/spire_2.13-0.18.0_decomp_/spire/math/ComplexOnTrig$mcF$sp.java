package spire.math;

public interface ComplexOnTrig$mcF$sp extends ComplexOnTrig {
   // $FF: synthetic method
   static Complex e$(final ComplexOnTrig$mcF$sp $this) {
      return $this.e();
   }

   default Complex e() {
      return this.e$mcF$sp();
   }

   // $FF: synthetic method
   static Complex e$mcF$sp$(final ComplexOnTrig$mcF$sp $this) {
      return $this.e$mcF$sp();
   }

   default Complex e$mcF$sp() {
      return new Complex$mcF$sp(this.trig$mcF$sp().e$mcF$sp(), this.scalar$mcF$sp().zero$mcF$sp());
   }

   // $FF: synthetic method
   static Complex pi$(final ComplexOnTrig$mcF$sp $this) {
      return $this.pi();
   }

   default Complex pi() {
      return this.pi$mcF$sp();
   }

   // $FF: synthetic method
   static Complex pi$mcF$sp$(final ComplexOnTrig$mcF$sp $this) {
      return $this.pi$mcF$sp();
   }

   default Complex pi$mcF$sp() {
      return new Complex$mcF$sp(this.trig$mcF$sp().pi$mcF$sp(), this.scalar$mcF$sp().zero$mcF$sp());
   }

   // $FF: synthetic method
   static Complex exp$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.exp(a);
   }

   default Complex exp(final Complex a) {
      return this.exp$mcF$sp(a);
   }

   // $FF: synthetic method
   static Complex exp$mcF$sp$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.exp$mcF$sp(a);
   }

   default Complex exp$mcF$sp(final Complex a) {
      return a.exp$mcF$sp(this.scalar$mcF$sp(), this.trig$mcF$sp());
   }

   // $FF: synthetic method
   static Complex expm1$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.expm1(a);
   }

   default Complex expm1(final Complex a) {
      return this.expm1$mcF$sp(a);
   }

   // $FF: synthetic method
   static Complex expm1$mcF$sp$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.expm1$mcF$sp(a);
   }

   default Complex expm1$mcF$sp(final Complex a) {
      return a.exp$mcF$sp(this.scalar$mcF$sp(), this.trig$mcF$sp()).$minus$mcF$sp(this.scalar$mcF$sp().one$mcF$sp(), this.scalar$mcF$sp());
   }

   // $FF: synthetic method
   static Complex log$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.log(a);
   }

   default Complex log(final Complex a) {
      return this.log$mcF$sp(a);
   }

   // $FF: synthetic method
   static Complex log$mcF$sp$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.log$mcF$sp(a);
   }

   default Complex log$mcF$sp(final Complex a) {
      return a.log$mcF$sp(this.scalar$mcF$sp(), this.nroot$mcF$sp(), this.order$mcF$sp(), this.trig$mcF$sp(), this.signed$mcF$sp());
   }

   // $FF: synthetic method
   static Complex log1p$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.log1p(a);
   }

   default Complex log1p(final Complex a) {
      return this.log1p$mcF$sp(a);
   }

   // $FF: synthetic method
   static Complex log1p$mcF$sp$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.log1p$mcF$sp(a);
   }

   default Complex log1p$mcF$sp(final Complex a) {
      return a.$plus$mcF$sp(this.scalar$mcF$sp().one$mcF$sp(), this.scalar$mcF$sp()).log$mcF$sp(this.scalar$mcF$sp(), this.nroot$mcF$sp(), this.order$mcF$sp(), this.trig$mcF$sp(), this.signed$mcF$sp());
   }

   // $FF: synthetic method
   static Complex sin$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.sin(a);
   }

   default Complex sin(final Complex a) {
      return this.sin$mcF$sp(a);
   }

   // $FF: synthetic method
   static Complex sin$mcF$sp$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.sin$mcF$sp(a);
   }

   default Complex sin$mcF$sp(final Complex a) {
      return a.sin$mcF$sp(this.scalar$mcF$sp(), this.trig$mcF$sp());
   }

   // $FF: synthetic method
   static Complex cos$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.cos(a);
   }

   default Complex cos(final Complex a) {
      return this.cos$mcF$sp(a);
   }

   // $FF: synthetic method
   static Complex cos$mcF$sp$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.cos$mcF$sp(a);
   }

   default Complex cos$mcF$sp(final Complex a) {
      return a.cos$mcF$sp(this.scalar$mcF$sp(), this.trig$mcF$sp());
   }

   // $FF: synthetic method
   static Complex tan$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.tan(a);
   }

   default Complex tan(final Complex a) {
      return this.tan$mcF$sp(a);
   }

   // $FF: synthetic method
   static Complex tan$mcF$sp$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.tan$mcF$sp(a);
   }

   default Complex tan$mcF$sp(final Complex a) {
      return a.tan$mcF$sp(this.scalar$mcF$sp(), this.trig$mcF$sp());
   }

   // $FF: synthetic method
   static Complex asin$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.asin(a);
   }

   default Complex asin(final Complex a) {
      return this.asin$mcF$sp(a);
   }

   // $FF: synthetic method
   static Complex asin$mcF$sp$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.asin$mcF$sp(a);
   }

   default Complex asin$mcF$sp(final Complex a) {
      return a.asin$mcF$sp(this.scalar$mcF$sp(), this.nroot$mcF$sp(), this.order$mcF$sp(), this.trig$mcF$sp(), this.signed$mcF$sp());
   }

   // $FF: synthetic method
   static Complex acos$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.acos(a);
   }

   default Complex acos(final Complex a) {
      return this.acos$mcF$sp(a);
   }

   // $FF: synthetic method
   static Complex acos$mcF$sp$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.acos$mcF$sp(a);
   }

   default Complex acos$mcF$sp(final Complex a) {
      return a.acos$mcF$sp(this.scalar$mcF$sp(), this.nroot$mcF$sp(), this.order$mcF$sp(), this.trig$mcF$sp(), this.signed$mcF$sp());
   }

   // $FF: synthetic method
   static Complex atan$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.atan(a);
   }

   default Complex atan(final Complex a) {
      return this.atan$mcF$sp(a);
   }

   // $FF: synthetic method
   static Complex atan$mcF$sp$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.atan$mcF$sp(a);
   }

   default Complex atan$mcF$sp(final Complex a) {
      return a.atan$mcF$sp(this.scalar$mcF$sp(), this.order$mcF$sp(), this.nroot$mcF$sp(), this.signed$mcF$sp(), this.trig$mcF$sp());
   }

   // $FF: synthetic method
   static Complex atan2$(final ComplexOnTrig$mcF$sp $this, final Complex y, final Complex x) {
      return $this.atan2(y, x);
   }

   default Complex atan2(final Complex y, final Complex x) {
      return this.atan2$mcF$sp(y, x);
   }

   // $FF: synthetic method
   static Complex atan2$mcF$sp$(final ComplexOnTrig$mcF$sp $this, final Complex y, final Complex x) {
      return $this.atan2$mcF$sp(y, x);
   }

   default Complex atan2$mcF$sp(final Complex y, final Complex x) {
      return (new Complex$mcF$sp(x.real$mcF$sp(), y.imag$mcF$sp())).atan$mcF$sp(this.scalar$mcF$sp(), this.order$mcF$sp(), this.nroot$mcF$sp(), this.signed$mcF$sp(), this.trig$mcF$sp());
   }

   // $FF: synthetic method
   static Complex sinh$(final ComplexOnTrig$mcF$sp $this, final Complex x) {
      return $this.sinh(x);
   }

   default Complex sinh(final Complex x) {
      return this.sinh$mcF$sp(x);
   }

   // $FF: synthetic method
   static Complex sinh$mcF$sp$(final ComplexOnTrig$mcF$sp $this, final Complex x) {
      return $this.sinh$mcF$sp(x);
   }

   default Complex sinh$mcF$sp(final Complex x) {
      return x.sinh$mcF$sp(this.scalar$mcF$sp(), this.trig$mcF$sp());
   }

   // $FF: synthetic method
   static Complex cosh$(final ComplexOnTrig$mcF$sp $this, final Complex x) {
      return $this.cosh(x);
   }

   default Complex cosh(final Complex x) {
      return this.cosh$mcF$sp(x);
   }

   // $FF: synthetic method
   static Complex cosh$mcF$sp$(final ComplexOnTrig$mcF$sp $this, final Complex x) {
      return $this.cosh$mcF$sp(x);
   }

   default Complex cosh$mcF$sp(final Complex x) {
      return x.cosh$mcF$sp(this.scalar$mcF$sp(), this.trig$mcF$sp());
   }

   // $FF: synthetic method
   static Complex tanh$(final ComplexOnTrig$mcF$sp $this, final Complex x) {
      return $this.tanh(x);
   }

   default Complex tanh(final Complex x) {
      return this.tanh$mcF$sp(x);
   }

   // $FF: synthetic method
   static Complex tanh$mcF$sp$(final ComplexOnTrig$mcF$sp $this, final Complex x) {
      return $this.tanh$mcF$sp(x);
   }

   default Complex tanh$mcF$sp(final Complex x) {
      return x.tanh$mcF$sp(this.scalar$mcF$sp(), this.trig$mcF$sp());
   }

   // $FF: synthetic method
   static Complex toRadians$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.toRadians(a);
   }

   default Complex toRadians(final Complex a) {
      return this.toRadians$mcF$sp(a);
   }

   // $FF: synthetic method
   static Complex toRadians$mcF$sp$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.toRadians$mcF$sp(a);
   }

   default Complex toRadians$mcF$sp(final Complex a) {
      return a;
   }

   // $FF: synthetic method
   static Complex toDegrees$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.toDegrees(a);
   }

   default Complex toDegrees(final Complex a) {
      return this.toDegrees$mcF$sp(a);
   }

   // $FF: synthetic method
   static Complex toDegrees$mcF$sp$(final ComplexOnTrig$mcF$sp $this, final Complex a) {
      return $this.toDegrees$mcF$sp(a);
   }

   default Complex toDegrees$mcF$sp(final Complex a) {
      return a;
   }
}
