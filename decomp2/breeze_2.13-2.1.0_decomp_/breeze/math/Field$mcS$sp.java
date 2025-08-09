package breeze.math;

public interface Field$mcS$sp extends Field, Ring$mcS$sp {
   // $FF: synthetic method
   static short inverse$(final Field$mcS$sp $this, final short a) {
      return $this.inverse(a);
   }

   default short inverse(final short a) {
      return this.inverse$mcS$sp(a);
   }

   // $FF: synthetic method
   static short inverse$mcS$sp$(final Field$mcS$sp $this, final short a) {
      return $this.inverse$mcS$sp(a);
   }

   default short inverse$mcS$sp(final short a) {
      return this.$div$mcS$sp(this.one$mcS$sp(), a);
   }
}
