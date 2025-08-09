package breeze.math;

public interface Field$mcF$sp extends Field, Ring$mcF$sp {
   // $FF: synthetic method
   static float inverse$(final Field$mcF$sp $this, final float a) {
      return $this.inverse(a);
   }

   default float inverse(final float a) {
      return this.inverse$mcF$sp(a);
   }

   // $FF: synthetic method
   static float inverse$mcF$sp$(final Field$mcF$sp $this, final float a) {
      return $this.inverse$mcF$sp(a);
   }

   default float inverse$mcF$sp(final float a) {
      return this.$div$mcF$sp(this.one$mcF$sp(), a);
   }
}
