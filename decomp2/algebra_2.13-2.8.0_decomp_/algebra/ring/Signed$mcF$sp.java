package algebra.ring;

public interface Signed$mcF$sp extends Signed {
   // $FF: synthetic method
   static Signed.Sign sign$(final Signed$mcF$sp $this, final float a) {
      return $this.sign(a);
   }

   default Signed.Sign sign(final float a) {
      return this.sign$mcF$sp(a);
   }

   // $FF: synthetic method
   static Signed.Sign sign$mcF$sp$(final Signed$mcF$sp $this, final float a) {
      return $this.sign$mcF$sp(a);
   }

   default Signed.Sign sign$mcF$sp(final float a) {
      return Signed.Sign$.MODULE$.apply(this.signum$mcF$sp(a));
   }

   // $FF: synthetic method
   static boolean isSignZero$(final Signed$mcF$sp $this, final float a) {
      return $this.isSignZero(a);
   }

   default boolean isSignZero(final float a) {
      return this.isSignZero$mcF$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignZero$mcF$sp$(final Signed$mcF$sp $this, final float a) {
      return $this.isSignZero$mcF$sp(a);
   }

   default boolean isSignZero$mcF$sp(final float a) {
      return this.signum$mcF$sp(a) == 0;
   }

   // $FF: synthetic method
   static boolean isSignPositive$(final Signed$mcF$sp $this, final float a) {
      return $this.isSignPositive(a);
   }

   default boolean isSignPositive(final float a) {
      return this.isSignPositive$mcF$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignPositive$mcF$sp$(final Signed$mcF$sp $this, final float a) {
      return $this.isSignPositive$mcF$sp(a);
   }

   default boolean isSignPositive$mcF$sp(final float a) {
      return this.signum$mcF$sp(a) > 0;
   }

   // $FF: synthetic method
   static boolean isSignNegative$(final Signed$mcF$sp $this, final float a) {
      return $this.isSignNegative(a);
   }

   default boolean isSignNegative(final float a) {
      return this.isSignNegative$mcF$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNegative$mcF$sp$(final Signed$mcF$sp $this, final float a) {
      return $this.isSignNegative$mcF$sp(a);
   }

   default boolean isSignNegative$mcF$sp(final float a) {
      return this.signum$mcF$sp(a) < 0;
   }

   // $FF: synthetic method
   static boolean isSignNonZero$(final Signed$mcF$sp $this, final float a) {
      return $this.isSignNonZero(a);
   }

   default boolean isSignNonZero(final float a) {
      return this.isSignNonZero$mcF$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNonZero$mcF$sp$(final Signed$mcF$sp $this, final float a) {
      return $this.isSignNonZero$mcF$sp(a);
   }

   default boolean isSignNonZero$mcF$sp(final float a) {
      return this.signum$mcF$sp(a) != 0;
   }

   // $FF: synthetic method
   static boolean isSignNonPositive$(final Signed$mcF$sp $this, final float a) {
      return $this.isSignNonPositive(a);
   }

   default boolean isSignNonPositive(final float a) {
      return this.isSignNonPositive$mcF$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNonPositive$mcF$sp$(final Signed$mcF$sp $this, final float a) {
      return $this.isSignNonPositive$mcF$sp(a);
   }

   default boolean isSignNonPositive$mcF$sp(final float a) {
      return this.signum$mcF$sp(a) <= 0;
   }

   // $FF: synthetic method
   static boolean isSignNonNegative$(final Signed$mcF$sp $this, final float a) {
      return $this.isSignNonNegative(a);
   }

   default boolean isSignNonNegative(final float a) {
      return this.isSignNonNegative$mcF$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNonNegative$mcF$sp$(final Signed$mcF$sp $this, final float a) {
      return $this.isSignNonNegative$mcF$sp(a);
   }

   default boolean isSignNonNegative$mcF$sp(final float a) {
      return this.signum$mcF$sp(a) >= 0;
   }
}
