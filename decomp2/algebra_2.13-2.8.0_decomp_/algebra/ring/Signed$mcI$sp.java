package algebra.ring;

public interface Signed$mcI$sp extends Signed {
   // $FF: synthetic method
   static Signed.Sign sign$(final Signed$mcI$sp $this, final int a) {
      return $this.sign(a);
   }

   default Signed.Sign sign(final int a) {
      return this.sign$mcI$sp(a);
   }

   // $FF: synthetic method
   static Signed.Sign sign$mcI$sp$(final Signed$mcI$sp $this, final int a) {
      return $this.sign$mcI$sp(a);
   }

   default Signed.Sign sign$mcI$sp(final int a) {
      return Signed.Sign$.MODULE$.apply(this.signum$mcI$sp(a));
   }

   // $FF: synthetic method
   static boolean isSignZero$(final Signed$mcI$sp $this, final int a) {
      return $this.isSignZero(a);
   }

   default boolean isSignZero(final int a) {
      return this.isSignZero$mcI$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignZero$mcI$sp$(final Signed$mcI$sp $this, final int a) {
      return $this.isSignZero$mcI$sp(a);
   }

   default boolean isSignZero$mcI$sp(final int a) {
      return this.signum$mcI$sp(a) == 0;
   }

   // $FF: synthetic method
   static boolean isSignPositive$(final Signed$mcI$sp $this, final int a) {
      return $this.isSignPositive(a);
   }

   default boolean isSignPositive(final int a) {
      return this.isSignPositive$mcI$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignPositive$mcI$sp$(final Signed$mcI$sp $this, final int a) {
      return $this.isSignPositive$mcI$sp(a);
   }

   default boolean isSignPositive$mcI$sp(final int a) {
      return this.signum$mcI$sp(a) > 0;
   }

   // $FF: synthetic method
   static boolean isSignNegative$(final Signed$mcI$sp $this, final int a) {
      return $this.isSignNegative(a);
   }

   default boolean isSignNegative(final int a) {
      return this.isSignNegative$mcI$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNegative$mcI$sp$(final Signed$mcI$sp $this, final int a) {
      return $this.isSignNegative$mcI$sp(a);
   }

   default boolean isSignNegative$mcI$sp(final int a) {
      return this.signum$mcI$sp(a) < 0;
   }

   // $FF: synthetic method
   static boolean isSignNonZero$(final Signed$mcI$sp $this, final int a) {
      return $this.isSignNonZero(a);
   }

   default boolean isSignNonZero(final int a) {
      return this.isSignNonZero$mcI$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNonZero$mcI$sp$(final Signed$mcI$sp $this, final int a) {
      return $this.isSignNonZero$mcI$sp(a);
   }

   default boolean isSignNonZero$mcI$sp(final int a) {
      return this.signum$mcI$sp(a) != 0;
   }

   // $FF: synthetic method
   static boolean isSignNonPositive$(final Signed$mcI$sp $this, final int a) {
      return $this.isSignNonPositive(a);
   }

   default boolean isSignNonPositive(final int a) {
      return this.isSignNonPositive$mcI$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNonPositive$mcI$sp$(final Signed$mcI$sp $this, final int a) {
      return $this.isSignNonPositive$mcI$sp(a);
   }

   default boolean isSignNonPositive$mcI$sp(final int a) {
      return this.signum$mcI$sp(a) <= 0;
   }

   // $FF: synthetic method
   static boolean isSignNonNegative$(final Signed$mcI$sp $this, final int a) {
      return $this.isSignNonNegative(a);
   }

   default boolean isSignNonNegative(final int a) {
      return this.isSignNonNegative$mcI$sp(a);
   }

   // $FF: synthetic method
   static boolean isSignNonNegative$mcI$sp$(final Signed$mcI$sp $this, final int a) {
      return $this.isSignNonNegative$mcI$sp(a);
   }

   default boolean isSignNonNegative$mcI$sp(final int a) {
      return this.signum$mcI$sp(a) >= 0;
   }
}
