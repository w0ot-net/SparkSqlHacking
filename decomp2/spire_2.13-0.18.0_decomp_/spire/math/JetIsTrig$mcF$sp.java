package spire.math;

public interface JetIsTrig$mcF$sp extends JetIsTrig {
   // $FF: synthetic method
   static Jet e$(final JetIsTrig$mcF$sp $this) {
      return $this.e();
   }

   default Jet e() {
      return this.e$mcF$sp();
   }

   // $FF: synthetic method
   static Jet e$mcF$sp$(final JetIsTrig$mcF$sp $this) {
      return $this.e$mcF$sp();
   }

   default Jet e$mcF$sp() {
      return Jet$.MODULE$.apply$mFc$sp(this.t$mcF$sp().e$mcF$sp(), this.c(), this.d(), this.f$mcF$sp());
   }

   // $FF: synthetic method
   static Jet pi$(final JetIsTrig$mcF$sp $this) {
      return $this.pi();
   }

   default Jet pi() {
      return this.pi$mcF$sp();
   }

   // $FF: synthetic method
   static Jet pi$mcF$sp$(final JetIsTrig$mcF$sp $this) {
      return $this.pi$mcF$sp();
   }

   default Jet pi$mcF$sp() {
      return Jet$.MODULE$.apply$mFc$sp(this.t$mcF$sp().pi$mcF$sp(), this.c(), this.d(), this.f$mcF$sp());
   }

   // $FF: synthetic method
   static Jet exp$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.exp(a);
   }

   default Jet exp(final Jet a) {
      return this.exp$mcF$sp(a);
   }

   // $FF: synthetic method
   static Jet exp$mcF$sp$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.exp$mcF$sp(a);
   }

   default Jet exp$mcF$sp(final Jet a) {
      return a.exp$mcF$sp(this.t$mcF$sp(), this.v$mcF$sp());
   }

   // $FF: synthetic method
   static Jet expm1$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.expm1(a);
   }

   default Jet expm1(final Jet a) {
      return this.expm1$mcF$sp(a);
   }

   // $FF: synthetic method
   static Jet expm1$mcF$sp$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.expm1$mcF$sp(a);
   }

   default Jet expm1$mcF$sp(final Jet a) {
      return a.exp$mcF$sp(this.t$mcF$sp(), this.v$mcF$sp()).$minus$mcF$sp(this.f$mcF$sp().one$mcF$sp(), this.f$mcF$sp());
   }

   // $FF: synthetic method
   static Jet log$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.log(a);
   }

   default Jet log(final Jet a) {
      return this.log$mcF$sp(a);
   }

   // $FF: synthetic method
   static Jet log$mcF$sp$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.log$mcF$sp(a);
   }

   default Jet log$mcF$sp(final Jet a) {
      return a.log$mcF$sp(this.f$mcF$sp(), this.t$mcF$sp(), this.v$mcF$sp());
   }

   // $FF: synthetic method
   static Jet log1p$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.log1p(a);
   }

   default Jet log1p(final Jet a) {
      return this.log1p$mcF$sp(a);
   }

   // $FF: synthetic method
   static Jet log1p$mcF$sp$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.log1p$mcF$sp(a);
   }

   default Jet log1p$mcF$sp(final Jet a) {
      return a.$plus$mcF$sp(this.f$mcF$sp().one$mcF$sp(), this.f$mcF$sp()).log$mcF$sp(this.f$mcF$sp(), this.t$mcF$sp(), this.v$mcF$sp());
   }

   // $FF: synthetic method
   static Jet sin$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.sin(a);
   }

   default Jet sin(final Jet a) {
      return this.sin$mcF$sp(a);
   }

   // $FF: synthetic method
   static Jet sin$mcF$sp$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.sin$mcF$sp(a);
   }

   default Jet sin$mcF$sp(final Jet a) {
      return a.sin$mcF$sp(this.t$mcF$sp(), this.v$mcF$sp());
   }

   // $FF: synthetic method
   static Jet cos$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.cos(a);
   }

   default Jet cos(final Jet a) {
      return this.cos$mcF$sp(a);
   }

   // $FF: synthetic method
   static Jet cos$mcF$sp$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.cos$mcF$sp(a);
   }

   default Jet cos$mcF$sp(final Jet a) {
      return a.cos$mcF$sp(this.f$mcF$sp(), this.t$mcF$sp(), this.v$mcF$sp());
   }

   // $FF: synthetic method
   static Jet tan$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.tan(a);
   }

   default Jet tan(final Jet a) {
      return this.tan$mcF$sp(a);
   }

   // $FF: synthetic method
   static Jet tan$mcF$sp$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.tan$mcF$sp(a);
   }

   default Jet tan$mcF$sp(final Jet a) {
      return a.tan$mcF$sp(this.f$mcF$sp(), this.t$mcF$sp(), this.v$mcF$sp());
   }

   // $FF: synthetic method
   static Jet asin$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.asin(a);
   }

   default Jet asin(final Jet a) {
      return this.asin$mcF$sp(a);
   }

   // $FF: synthetic method
   static Jet asin$mcF$sp$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.asin$mcF$sp(a);
   }

   default Jet asin$mcF$sp(final Jet a) {
      return a.asin$mcF$sp(this.f$mcF$sp(), this.n$mcF$sp(), this.t$mcF$sp(), this.v$mcF$sp());
   }

   // $FF: synthetic method
   static Jet acos$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.acos(a);
   }

   default Jet acos(final Jet a) {
      return this.acos$mcF$sp(a);
   }

   // $FF: synthetic method
   static Jet acos$mcF$sp$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.acos$mcF$sp(a);
   }

   default Jet acos$mcF$sp(final Jet a) {
      return a.acos$mcF$sp(this.f$mcF$sp(), this.n$mcF$sp(), this.t$mcF$sp(), this.v$mcF$sp());
   }

   // $FF: synthetic method
   static Jet atan$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.atan(a);
   }

   default Jet atan(final Jet a) {
      return this.atan$mcF$sp(a);
   }

   // $FF: synthetic method
   static Jet atan$mcF$sp$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.atan$mcF$sp(a);
   }

   default Jet atan$mcF$sp(final Jet a) {
      return a.atan$mcF$sp(this.f$mcF$sp(), this.t$mcF$sp(), this.v$mcF$sp());
   }

   // $FF: synthetic method
   static Jet atan2$(final JetIsTrig$mcF$sp $this, final Jet y, final Jet x) {
      return $this.atan2(y, x);
   }

   default Jet atan2(final Jet y, final Jet x) {
      return this.atan2$mcF$sp(y, x);
   }

   // $FF: synthetic method
   static Jet atan2$mcF$sp$(final JetIsTrig$mcF$sp $this, final Jet y, final Jet x) {
      return $this.atan2$mcF$sp(y, x);
   }

   default Jet atan2$mcF$sp(final Jet y, final Jet x) {
      return y.atan2$mcF$sp(x, this.f$mcF$sp(), this.t$mcF$sp(), this.v$mcF$sp());
   }

   // $FF: synthetic method
   static Jet sinh$(final JetIsTrig$mcF$sp $this, final Jet x) {
      return $this.sinh(x);
   }

   default Jet sinh(final Jet x) {
      return this.sinh$mcF$sp(x);
   }

   // $FF: synthetic method
   static Jet sinh$mcF$sp$(final JetIsTrig$mcF$sp $this, final Jet x) {
      return $this.sinh$mcF$sp(x);
   }

   default Jet sinh$mcF$sp(final Jet x) {
      return x.sinh$mcF$sp(this.t$mcF$sp(), this.v$mcF$sp());
   }

   // $FF: synthetic method
   static Jet cosh$(final JetIsTrig$mcF$sp $this, final Jet x) {
      return $this.cosh(x);
   }

   default Jet cosh(final Jet x) {
      return this.cosh$mcF$sp(x);
   }

   // $FF: synthetic method
   static Jet cosh$mcF$sp$(final JetIsTrig$mcF$sp $this, final Jet x) {
      return $this.cosh$mcF$sp(x);
   }

   default Jet cosh$mcF$sp(final Jet x) {
      return x.cosh$mcF$sp(this.t$mcF$sp(), this.v$mcF$sp());
   }

   // $FF: synthetic method
   static Jet tanh$(final JetIsTrig$mcF$sp $this, final Jet x) {
      return $this.tanh(x);
   }

   default Jet tanh(final Jet x) {
      return this.tanh$mcF$sp(x);
   }

   // $FF: synthetic method
   static Jet tanh$mcF$sp$(final JetIsTrig$mcF$sp $this, final Jet x) {
      return $this.tanh$mcF$sp(x);
   }

   default Jet tanh$mcF$sp(final Jet x) {
      return x.tanh$mcF$sp(this.f$mcF$sp(), this.t$mcF$sp(), this.v$mcF$sp());
   }

   // $FF: synthetic method
   static Jet toRadians$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.toRadians(a);
   }

   default Jet toRadians(final Jet a) {
      return this.toRadians$mcF$sp(a);
   }

   // $FF: synthetic method
   static Jet toRadians$mcF$sp$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.toRadians$mcF$sp(a);
   }

   default Jet toRadians$mcF$sp(final Jet a) {
      return a;
   }

   // $FF: synthetic method
   static Jet toDegrees$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.toDegrees(a);
   }

   default Jet toDegrees(final Jet a) {
      return this.toDegrees$mcF$sp(a);
   }

   // $FF: synthetic method
   static Jet toDegrees$mcF$sp$(final JetIsTrig$mcF$sp $this, final Jet a) {
      return $this.toDegrees$mcF$sp(a);
   }

   default Jet toDegrees$mcF$sp(final Jet a) {
      return a;
   }
}
