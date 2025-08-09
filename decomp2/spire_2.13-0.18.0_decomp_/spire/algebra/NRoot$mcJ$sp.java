package spire.algebra;

public interface NRoot$mcJ$sp extends NRoot {
   // $FF: synthetic method
   static long sqrt$(final NRoot$mcJ$sp $this, final long a) {
      return $this.sqrt(a);
   }

   default long sqrt(final long a) {
      return this.sqrt$mcJ$sp(a);
   }

   // $FF: synthetic method
   static long sqrt$mcJ$sp$(final NRoot$mcJ$sp $this, final long a) {
      return $this.sqrt$mcJ$sp(a);
   }

   default long sqrt$mcJ$sp(final long a) {
      return this.nroot$mcJ$sp(a, 2);
   }
}
