package spire.algebra;

public interface NRoot$mcI$sp extends NRoot {
   // $FF: synthetic method
   static int sqrt$(final NRoot$mcI$sp $this, final int a) {
      return $this.sqrt(a);
   }

   default int sqrt(final int a) {
      return this.sqrt$mcI$sp(a);
   }

   // $FF: synthetic method
   static int sqrt$mcI$sp$(final NRoot$mcI$sp $this, final int a) {
      return $this.sqrt$mcI$sp(a);
   }

   default int sqrt$mcI$sp(final int a) {
      return this.nroot$mcI$sp(a, 2);
   }
}
