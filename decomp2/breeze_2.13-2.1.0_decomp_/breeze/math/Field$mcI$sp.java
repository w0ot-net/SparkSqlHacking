package breeze.math;

public interface Field$mcI$sp extends Field, Ring$mcI$sp {
   // $FF: synthetic method
   static int inverse$(final Field$mcI$sp $this, final int a) {
      return $this.inverse(a);
   }

   default int inverse(final int a) {
      return this.inverse$mcI$sp(a);
   }

   // $FF: synthetic method
   static int inverse$mcI$sp$(final Field$mcI$sp $this, final int a) {
      return $this.inverse$mcI$sp(a);
   }

   default int inverse$mcI$sp(final int a) {
      return this.$div$mcI$sp(this.one$mcI$sp(), a);
   }
}
