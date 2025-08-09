package spire.algebra;

public interface NRoot$mcF$sp extends NRoot {
   // $FF: synthetic method
   static float sqrt$(final NRoot$mcF$sp $this, final float a) {
      return $this.sqrt(a);
   }

   default float sqrt(final float a) {
      return this.sqrt$mcF$sp(a);
   }

   // $FF: synthetic method
   static float sqrt$mcF$sp$(final NRoot$mcF$sp $this, final float a) {
      return $this.sqrt$mcF$sp(a);
   }

   default float sqrt$mcF$sp(final float a) {
      return this.nroot$mcF$sp(a, 2);
   }
}
