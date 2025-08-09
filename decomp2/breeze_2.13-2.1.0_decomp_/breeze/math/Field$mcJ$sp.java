package breeze.math;

public interface Field$mcJ$sp extends Field, Ring$mcJ$sp {
   // $FF: synthetic method
   static long inverse$(final Field$mcJ$sp $this, final long a) {
      return $this.inverse(a);
   }

   default long inverse(final long a) {
      return this.inverse$mcJ$sp(a);
   }

   // $FF: synthetic method
   static long inverse$mcJ$sp$(final Field$mcJ$sp $this, final long a) {
      return $this.inverse$mcJ$sp(a);
   }

   default long inverse$mcJ$sp(final long a) {
      return this.$div$mcJ$sp(this.one$mcJ$sp(), a);
   }
}
