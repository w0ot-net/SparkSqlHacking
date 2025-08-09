package breeze.math;

public interface Field$mcD$sp extends Field, Ring$mcD$sp {
   // $FF: synthetic method
   static double inverse$(final Field$mcD$sp $this, final double a) {
      return $this.inverse(a);
   }

   default double inverse(final double a) {
      return this.inverse$mcD$sp(a);
   }

   // $FF: synthetic method
   static double inverse$mcD$sp$(final Field$mcD$sp $this, final double a) {
      return $this.inverse$mcD$sp(a);
   }

   default double inverse$mcD$sp(final double a) {
      return this.$div$mcD$sp(this.one$mcD$sp(), a);
   }
}
