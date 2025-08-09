package spire.math;

public interface JetIsTrig$mcD$sp extends JetIsTrig {
   // $FF: synthetic method
   static Jet e$(final JetIsTrig$mcD$sp $this) {
      return $this.e();
   }

   default Jet e() {
      return this.e$mcD$sp();
   }

   // $FF: synthetic method
   static Jet e$mcD$sp$(final JetIsTrig$mcD$sp $this) {
      return $this.e$mcD$sp();
   }

   default Jet e$mcD$sp() {
      return Jet$.MODULE$.apply$mDc$sp(this.t$mcD$sp().e$mcD$sp(), this.c(), this.d(), this.f$mcD$sp());
   }

   // $FF: synthetic method
   static Jet pi$(final JetIsTrig$mcD$sp $this) {
      return $this.pi();
   }

   default Jet pi() {
      return this.pi$mcD$sp();
   }

   // $FF: synthetic method
   static Jet pi$mcD$sp$(final JetIsTrig$mcD$sp $this) {
      return $this.pi$mcD$sp();
   }

   default Jet pi$mcD$sp() {
      return Jet$.MODULE$.apply$mDc$sp(this.t$mcD$sp().pi$mcD$sp(), this.c(), this.d(), this.f$mcD$sp());
   }

   // $FF: synthetic method
   static Jet exp$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.exp(a);
   }

   default Jet exp(final Jet a) {
      return this.exp$mcD$sp(a);
   }

   // $FF: synthetic method
   static Jet exp$mcD$sp$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.exp$mcD$sp(a);
   }

   default Jet exp$mcD$sp(final Jet a) {
      return a.exp$mcD$sp(this.t$mcD$sp(), this.v$mcD$sp());
   }

   // $FF: synthetic method
   static Jet expm1$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.expm1(a);
   }

   default Jet expm1(final Jet a) {
      return this.expm1$mcD$sp(a);
   }

   // $FF: synthetic method
   static Jet expm1$mcD$sp$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.expm1$mcD$sp(a);
   }

   default Jet expm1$mcD$sp(final Jet a) {
      return a.exp$mcD$sp(this.t$mcD$sp(), this.v$mcD$sp()).$minus$mcD$sp(this.f$mcD$sp().one$mcD$sp(), this.f$mcD$sp());
   }

   // $FF: synthetic method
   static Jet log$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.log(a);
   }

   default Jet log(final Jet a) {
      return this.log$mcD$sp(a);
   }

   // $FF: synthetic method
   static Jet log$mcD$sp$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.log$mcD$sp(a);
   }

   default Jet log$mcD$sp(final Jet a) {
      return a.log$mcD$sp(this.f$mcD$sp(), this.t$mcD$sp(), this.v$mcD$sp());
   }

   // $FF: synthetic method
   static Jet log1p$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.log1p(a);
   }

   default Jet log1p(final Jet a) {
      return this.log1p$mcD$sp(a);
   }

   // $FF: synthetic method
   static Jet log1p$mcD$sp$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.log1p$mcD$sp(a);
   }

   default Jet log1p$mcD$sp(final Jet a) {
      return a.$plus$mcD$sp(this.f$mcD$sp().one$mcD$sp(), this.f$mcD$sp()).log$mcD$sp(this.f$mcD$sp(), this.t$mcD$sp(), this.v$mcD$sp());
   }

   // $FF: synthetic method
   static Jet sin$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.sin(a);
   }

   default Jet sin(final Jet a) {
      return this.sin$mcD$sp(a);
   }

   // $FF: synthetic method
   static Jet sin$mcD$sp$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.sin$mcD$sp(a);
   }

   default Jet sin$mcD$sp(final Jet a) {
      return a.sin$mcD$sp(this.t$mcD$sp(), this.v$mcD$sp());
   }

   // $FF: synthetic method
   static Jet cos$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.cos(a);
   }

   default Jet cos(final Jet a) {
      return this.cos$mcD$sp(a);
   }

   // $FF: synthetic method
   static Jet cos$mcD$sp$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.cos$mcD$sp(a);
   }

   default Jet cos$mcD$sp(final Jet a) {
      return a.cos$mcD$sp(this.f$mcD$sp(), this.t$mcD$sp(), this.v$mcD$sp());
   }

   // $FF: synthetic method
   static Jet tan$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.tan(a);
   }

   default Jet tan(final Jet a) {
      return this.tan$mcD$sp(a);
   }

   // $FF: synthetic method
   static Jet tan$mcD$sp$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.tan$mcD$sp(a);
   }

   default Jet tan$mcD$sp(final Jet a) {
      return a.tan$mcD$sp(this.f$mcD$sp(), this.t$mcD$sp(), this.v$mcD$sp());
   }

   // $FF: synthetic method
   static Jet asin$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.asin(a);
   }

   default Jet asin(final Jet a) {
      return this.asin$mcD$sp(a);
   }

   // $FF: synthetic method
   static Jet asin$mcD$sp$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.asin$mcD$sp(a);
   }

   default Jet asin$mcD$sp(final Jet a) {
      return a.asin$mcD$sp(this.f$mcD$sp(), this.n$mcD$sp(), this.t$mcD$sp(), this.v$mcD$sp());
   }

   // $FF: synthetic method
   static Jet acos$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.acos(a);
   }

   default Jet acos(final Jet a) {
      return this.acos$mcD$sp(a);
   }

   // $FF: synthetic method
   static Jet acos$mcD$sp$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.acos$mcD$sp(a);
   }

   default Jet acos$mcD$sp(final Jet a) {
      return a.acos$mcD$sp(this.f$mcD$sp(), this.n$mcD$sp(), this.t$mcD$sp(), this.v$mcD$sp());
   }

   // $FF: synthetic method
   static Jet atan$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.atan(a);
   }

   default Jet atan(final Jet a) {
      return this.atan$mcD$sp(a);
   }

   // $FF: synthetic method
   static Jet atan$mcD$sp$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.atan$mcD$sp(a);
   }

   default Jet atan$mcD$sp(final Jet a) {
      return a.atan$mcD$sp(this.f$mcD$sp(), this.t$mcD$sp(), this.v$mcD$sp());
   }

   // $FF: synthetic method
   static Jet atan2$(final JetIsTrig$mcD$sp $this, final Jet y, final Jet x) {
      return $this.atan2(y, x);
   }

   default Jet atan2(final Jet y, final Jet x) {
      return this.atan2$mcD$sp(y, x);
   }

   // $FF: synthetic method
   static Jet atan2$mcD$sp$(final JetIsTrig$mcD$sp $this, final Jet y, final Jet x) {
      return $this.atan2$mcD$sp(y, x);
   }

   default Jet atan2$mcD$sp(final Jet y, final Jet x) {
      return y.atan2$mcD$sp(x, this.f$mcD$sp(), this.t$mcD$sp(), this.v$mcD$sp());
   }

   // $FF: synthetic method
   static Jet sinh$(final JetIsTrig$mcD$sp $this, final Jet x) {
      return $this.sinh(x);
   }

   default Jet sinh(final Jet x) {
      return this.sinh$mcD$sp(x);
   }

   // $FF: synthetic method
   static Jet sinh$mcD$sp$(final JetIsTrig$mcD$sp $this, final Jet x) {
      return $this.sinh$mcD$sp(x);
   }

   default Jet sinh$mcD$sp(final Jet x) {
      return x.sinh$mcD$sp(this.t$mcD$sp(), this.v$mcD$sp());
   }

   // $FF: synthetic method
   static Jet cosh$(final JetIsTrig$mcD$sp $this, final Jet x) {
      return $this.cosh(x);
   }

   default Jet cosh(final Jet x) {
      return this.cosh$mcD$sp(x);
   }

   // $FF: synthetic method
   static Jet cosh$mcD$sp$(final JetIsTrig$mcD$sp $this, final Jet x) {
      return $this.cosh$mcD$sp(x);
   }

   default Jet cosh$mcD$sp(final Jet x) {
      return x.cosh$mcD$sp(this.t$mcD$sp(), this.v$mcD$sp());
   }

   // $FF: synthetic method
   static Jet tanh$(final JetIsTrig$mcD$sp $this, final Jet x) {
      return $this.tanh(x);
   }

   default Jet tanh(final Jet x) {
      return this.tanh$mcD$sp(x);
   }

   // $FF: synthetic method
   static Jet tanh$mcD$sp$(final JetIsTrig$mcD$sp $this, final Jet x) {
      return $this.tanh$mcD$sp(x);
   }

   default Jet tanh$mcD$sp(final Jet x) {
      return x.tanh$mcD$sp(this.f$mcD$sp(), this.t$mcD$sp(), this.v$mcD$sp());
   }

   // $FF: synthetic method
   static Jet toRadians$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.toRadians(a);
   }

   default Jet toRadians(final Jet a) {
      return this.toRadians$mcD$sp(a);
   }

   // $FF: synthetic method
   static Jet toRadians$mcD$sp$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.toRadians$mcD$sp(a);
   }

   default Jet toRadians$mcD$sp(final Jet a) {
      return a;
   }

   // $FF: synthetic method
   static Jet toDegrees$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.toDegrees(a);
   }

   default Jet toDegrees(final Jet a) {
      return this.toDegrees$mcD$sp(a);
   }

   // $FF: synthetic method
   static Jet toDegrees$mcD$sp$(final JetIsTrig$mcD$sp $this, final Jet a) {
      return $this.toDegrees$mcD$sp(a);
   }

   default Jet toDegrees$mcD$sp(final Jet a) {
      return a;
   }
}
