package algebra.ring;

public interface Signed$mcB$sp extends Signed {
   // $FF: synthetic method
   static Signed.Sign sign$(final Signed$mcB$sp $this, final byte a) {
      return $this.sign(a);
   }

   default Signed.Sign sign(final byte a) {
      return this.sign$mcB$sp(a);
   }

   // $FF: synthetic method
   static Signed.Sign sign$mcB$sp$(final Signed$mcB$sp $this, final byte a) {
      return $this.sign$mcB$sp(a);
   }

   default Signed.Sign sign$mcB$sp(final byte a) {
      return Signed.Sign$.MODULE$.apply(this.signum$mcB$sp(a));
   }

   // $FF: synthetic method
   static boolean isSignZero$(final Signed$mcB$sp $this, final byte a) {
      return $this.isSignZero(a);
   }

   default boolean isSignZero(final byte a) {
      return this.isSignZero$mcB$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignZero$mcB$sp$(final Signed$mcB$sp $this, final byte a) {
      return $this.isSignZero$mcB$sp(a);
   }

   default boolean isSignZero$mcB$sp(final byte a) {
      return this.signum$mcB$sp(a) == 0;
   }

   // $FF: synthetic method
   static boolean isSignPositive$(final Signed$mcB$sp $this, final byte a) {
      return $this.isSignPositive(a);
   }

   default boolean isSignPositive(final byte a) {
      return this.isSignPositive$mcB$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignPositive$mcB$sp$(final Signed$mcB$sp $this, final byte a) {
      return $this.isSignPositive$mcB$sp(a);
   }

   default boolean isSignPositive$mcB$sp(final byte a) {
      return this.signum$mcB$sp(a) > 0;
   }

   // $FF: synthetic method
   static boolean isSignNegative$(final Signed$mcB$sp $this, final byte a) {
      return $this.isSignNegative(a);
   }

   default boolean isSignNegative(final byte a) {
      return this.isSignNegative$mcB$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNegative$mcB$sp$(final Signed$mcB$sp $this, final byte a) {
      return $this.isSignNegative$mcB$sp(a);
   }

   default boolean isSignNegative$mcB$sp(final byte a) {
      return this.signum$mcB$sp(a) < 0;
   }

   // $FF: synthetic method
   static boolean isSignNonZero$(final Signed$mcB$sp $this, final byte a) {
      return $this.isSignNonZero(a);
   }

   default boolean isSignNonZero(final byte a) {
      return this.isSignNonZero$mcB$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNonZero$mcB$sp$(final Signed$mcB$sp $this, final byte a) {
      return $this.isSignNonZero$mcB$sp(a);
   }

   default boolean isSignNonZero$mcB$sp(final byte a) {
      return this.signum$mcB$sp(a) != 0;
   }

   // $FF: synthetic method
   static boolean isSignNonPositive$(final Signed$mcB$sp $this, final byte a) {
      return $this.isSignNonPositive(a);
   }

   default boolean isSignNonPositive(final byte a) {
      return this.isSignNonPositive$mcB$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNonPositive$mcB$sp$(final Signed$mcB$sp $this, final byte a) {
      return $this.isSignNonPositive$mcB$sp(a);
   }

   default boolean isSignNonPositive$mcB$sp(final byte a) {
      return this.signum$mcB$sp(a) <= 0;
   }

   // $FF: synthetic method
   static boolean isSignNonNegative$(final Signed$mcB$sp $this, final byte a) {
      return $this.isSignNonNegative(a);
   }

   default boolean isSignNonNegative(final byte a) {
      return this.isSignNonNegative$mcB$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNonNegative$mcB$sp$(final Signed$mcB$sp $this, final byte a) {
      return $this.isSignNonNegative$mcB$sp(a);
   }

   default boolean isSignNonNegative$mcB$sp(final byte a) {
      return this.signum$mcB$sp(a) >= 0;
   }
}
