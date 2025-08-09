package algebra.ring;

public interface Signed$mcD$sp extends Signed {
   // $FF: synthetic method
   static Signed.Sign sign$(final Signed$mcD$sp $this, final double a) {
      return $this.sign(a);
   }

   default Signed.Sign sign(final double a) {
      return this.sign$mcD$sp(a);
   }

   // $FF: synthetic method
   static Signed.Sign sign$mcD$sp$(final Signed$mcD$sp $this, final double a) {
      return $this.sign$mcD$sp(a);
   }

   default Signed.Sign sign$mcD$sp(final double a) {
      return Signed.Sign$.MODULE$.apply(this.signum$mcD$sp(a));
   }

   // $FF: synthetic method
   static boolean isSignZero$(final Signed$mcD$sp $this, final double a) {
      return $this.isSignZero(a);
   }

   default boolean isSignZero(final double a) {
      return this.isSignZero$mcD$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignZero$mcD$sp$(final Signed$mcD$sp $this, final double a) {
      return $this.isSignZero$mcD$sp(a);
   }

   default boolean isSignZero$mcD$sp(final double a) {
      return this.signum$mcD$sp(a) == 0;
   }

   // $FF: synthetic method
   static boolean isSignPositive$(final Signed$mcD$sp $this, final double a) {
      return $this.isSignPositive(a);
   }

   default boolean isSignPositive(final double a) {
      return this.isSignPositive$mcD$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignPositive$mcD$sp$(final Signed$mcD$sp $this, final double a) {
      return $this.isSignPositive$mcD$sp(a);
   }

   default boolean isSignPositive$mcD$sp(final double a) {
      return this.signum$mcD$sp(a) > 0;
   }

   // $FF: synthetic method
   static boolean isSignNegative$(final Signed$mcD$sp $this, final double a) {
      return $this.isSignNegative(a);
   }

   default boolean isSignNegative(final double a) {
      return this.isSignNegative$mcD$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNegative$mcD$sp$(final Signed$mcD$sp $this, final double a) {
      return $this.isSignNegative$mcD$sp(a);
   }

   default boolean isSignNegative$mcD$sp(final double a) {
      return this.signum$mcD$sp(a) < 0;
   }

   // $FF: synthetic method
   static boolean isSignNonZero$(final Signed$mcD$sp $this, final double a) {
      return $this.isSignNonZero(a);
   }

   default boolean isSignNonZero(final double a) {
      return this.isSignNonZero$mcD$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNonZero$mcD$sp$(final Signed$mcD$sp $this, final double a) {
      return $this.isSignNonZero$mcD$sp(a);
   }

   default boolean isSignNonZero$mcD$sp(final double a) {
      return this.signum$mcD$sp(a) != 0;
   }

   // $FF: synthetic method
   static boolean isSignNonPositive$(final Signed$mcD$sp $this, final double a) {
      return $this.isSignNonPositive(a);
   }

   default boolean isSignNonPositive(final double a) {
      return this.isSignNonPositive$mcD$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNonPositive$mcD$sp$(final Signed$mcD$sp $this, final double a) {
      return $this.isSignNonPositive$mcD$sp(a);
   }

   default boolean isSignNonPositive$mcD$sp(final double a) {
      return this.signum$mcD$sp(a) <= 0;
   }

   // $FF: synthetic method
   static boolean isSignNonNegative$(final Signed$mcD$sp $this, final double a) {
      return $this.isSignNonNegative(a);
   }

   default boolean isSignNonNegative(final double a) {
      return this.isSignNonNegative$mcD$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNonNegative$mcD$sp$(final Signed$mcD$sp $this, final double a) {
      return $this.isSignNonNegative$mcD$sp(a);
   }

   default boolean isSignNonNegative$mcD$sp(final double a) {
      return this.signum$mcD$sp(a) >= 0;
   }
}
