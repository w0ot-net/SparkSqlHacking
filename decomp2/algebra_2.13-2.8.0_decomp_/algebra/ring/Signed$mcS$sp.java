package algebra.ring;

public interface Signed$mcS$sp extends Signed {
   // $FF: synthetic method
   static Signed.Sign sign$(final Signed$mcS$sp $this, final short a) {
      return $this.sign(a);
   }

   default Signed.Sign sign(final short a) {
      return this.sign$mcS$sp(a);
   }

   // $FF: synthetic method
   static Signed.Sign sign$mcS$sp$(final Signed$mcS$sp $this, final short a) {
      return $this.sign$mcS$sp(a);
   }

   default Signed.Sign sign$mcS$sp(final short a) {
      return Signed.Sign$.MODULE$.apply(this.signum$mcS$sp(a));
   }

   // $FF: synthetic method
   static boolean isSignZero$(final Signed$mcS$sp $this, final short a) {
      return $this.isSignZero(a);
   }

   default boolean isSignZero(final short a) {
      return this.isSignZero$mcS$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignZero$mcS$sp$(final Signed$mcS$sp $this, final short a) {
      return $this.isSignZero$mcS$sp(a);
   }

   default boolean isSignZero$mcS$sp(final short a) {
      return this.signum$mcS$sp(a) == 0;
   }

   // $FF: synthetic method
   static boolean isSignPositive$(final Signed$mcS$sp $this, final short a) {
      return $this.isSignPositive(a);
   }

   default boolean isSignPositive(final short a) {
      return this.isSignPositive$mcS$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignPositive$mcS$sp$(final Signed$mcS$sp $this, final short a) {
      return $this.isSignPositive$mcS$sp(a);
   }

   default boolean isSignPositive$mcS$sp(final short a) {
      return this.signum$mcS$sp(a) > 0;
   }

   // $FF: synthetic method
   static boolean isSignNegative$(final Signed$mcS$sp $this, final short a) {
      return $this.isSignNegative(a);
   }

   default boolean isSignNegative(final short a) {
      return this.isSignNegative$mcS$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNegative$mcS$sp$(final Signed$mcS$sp $this, final short a) {
      return $this.isSignNegative$mcS$sp(a);
   }

   default boolean isSignNegative$mcS$sp(final short a) {
      return this.signum$mcS$sp(a) < 0;
   }

   // $FF: synthetic method
   static boolean isSignNonZero$(final Signed$mcS$sp $this, final short a) {
      return $this.isSignNonZero(a);
   }

   default boolean isSignNonZero(final short a) {
      return this.isSignNonZero$mcS$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNonZero$mcS$sp$(final Signed$mcS$sp $this, final short a) {
      return $this.isSignNonZero$mcS$sp(a);
   }

   default boolean isSignNonZero$mcS$sp(final short a) {
      return this.signum$mcS$sp(a) != 0;
   }

   // $FF: synthetic method
   static boolean isSignNonPositive$(final Signed$mcS$sp $this, final short a) {
      return $this.isSignNonPositive(a);
   }

   default boolean isSignNonPositive(final short a) {
      return this.isSignNonPositive$mcS$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNonPositive$mcS$sp$(final Signed$mcS$sp $this, final short a) {
      return $this.isSignNonPositive$mcS$sp(a);
   }

   default boolean isSignNonPositive$mcS$sp(final short a) {
      return this.signum$mcS$sp(a) <= 0;
   }

   // $FF: synthetic method
   static boolean isSignNonNegative$(final Signed$mcS$sp $this, final short a) {
      return $this.isSignNonNegative(a);
   }

   default boolean isSignNonNegative(final short a) {
      return this.isSignNonNegative$mcS$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNonNegative$mcS$sp$(final Signed$mcS$sp $this, final short a) {
      return $this.isSignNonNegative$mcS$sp(a);
   }

   default boolean isSignNonNegative$mcS$sp(final short a) {
      return this.signum$mcS$sp(a) >= 0;
   }
}
