package algebra.ring;

public interface Signed$mcJ$sp extends Signed {
   // $FF: synthetic method
   static Signed.Sign sign$(final Signed$mcJ$sp $this, final long a) {
      return $this.sign(a);
   }

   default Signed.Sign sign(final long a) {
      return this.sign$mcJ$sp(a);
   }

   // $FF: synthetic method
   static Signed.Sign sign$mcJ$sp$(final Signed$mcJ$sp $this, final long a) {
      return $this.sign$mcJ$sp(a);
   }

   default Signed.Sign sign$mcJ$sp(final long a) {
      return Signed.Sign$.MODULE$.apply(this.signum$mcJ$sp(a));
   }

   // $FF: synthetic method
   static boolean isSignZero$(final Signed$mcJ$sp $this, final long a) {
      return $this.isSignZero(a);
   }

   default boolean isSignZero(final long a) {
      return this.isSignZero$mcJ$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignZero$mcJ$sp$(final Signed$mcJ$sp $this, final long a) {
      return $this.isSignZero$mcJ$sp(a);
   }

   default boolean isSignZero$mcJ$sp(final long a) {
      return this.signum$mcJ$sp(a) == 0;
   }

   // $FF: synthetic method
   static boolean isSignPositive$(final Signed$mcJ$sp $this, final long a) {
      return $this.isSignPositive(a);
   }

   default boolean isSignPositive(final long a) {
      return this.isSignPositive$mcJ$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignPositive$mcJ$sp$(final Signed$mcJ$sp $this, final long a) {
      return $this.isSignPositive$mcJ$sp(a);
   }

   default boolean isSignPositive$mcJ$sp(final long a) {
      return this.signum$mcJ$sp(a) > 0;
   }

   // $FF: synthetic method
   static boolean isSignNegative$(final Signed$mcJ$sp $this, final long a) {
      return $this.isSignNegative(a);
   }

   default boolean isSignNegative(final long a) {
      return this.isSignNegative$mcJ$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNegative$mcJ$sp$(final Signed$mcJ$sp $this, final long a) {
      return $this.isSignNegative$mcJ$sp(a);
   }

   default boolean isSignNegative$mcJ$sp(final long a) {
      return this.signum$mcJ$sp(a) < 0;
   }

   // $FF: synthetic method
   static boolean isSignNonZero$(final Signed$mcJ$sp $this, final long a) {
      return $this.isSignNonZero(a);
   }

   default boolean isSignNonZero(final long a) {
      return this.isSignNonZero$mcJ$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNonZero$mcJ$sp$(final Signed$mcJ$sp $this, final long a) {
      return $this.isSignNonZero$mcJ$sp(a);
   }

   default boolean isSignNonZero$mcJ$sp(final long a) {
      return this.signum$mcJ$sp(a) != 0;
   }

   // $FF: synthetic method
   static boolean isSignNonPositive$(final Signed$mcJ$sp $this, final long a) {
      return $this.isSignNonPositive(a);
   }

   default boolean isSignNonPositive(final long a) {
      return this.isSignNonPositive$mcJ$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNonPositive$mcJ$sp$(final Signed$mcJ$sp $this, final long a) {
      return $this.isSignNonPositive$mcJ$sp(a);
   }

   default boolean isSignNonPositive$mcJ$sp(final long a) {
      return this.signum$mcJ$sp(a) <= 0;
   }

   // $FF: synthetic method
   static boolean isSignNonNegative$(final Signed$mcJ$sp $this, final long a) {
      return $this.isSignNonNegative(a);
   }

   default boolean isSignNonNegative(final long a) {
      return this.isSignNonNegative$mcJ$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNonNegative$mcJ$sp$(final Signed$mcJ$sp $this, final long a) {
      return $this.isSignNonNegative$mcJ$sp(a);
   }

   default boolean isSignNonNegative$mcJ$sp(final long a) {
      return this.signum$mcJ$sp(a) >= 0;
   }
}
