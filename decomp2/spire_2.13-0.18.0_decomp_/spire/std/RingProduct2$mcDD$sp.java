package spire.std;

import scala.Tuple2;

public interface RingProduct2$mcDD$sp extends RingProduct2, RngProduct2$mcDD$sp {
   // $FF: synthetic method
   static Tuple2 fromInt$(final RingProduct2$mcDD$sp $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple2 fromInt(final int x0) {
      return this.fromInt$mcDD$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcDD$sp$(final RingProduct2$mcDD$sp $this, final int x0) {
      return $this.fromInt$mcDD$sp(x0);
   }

   default Tuple2 fromInt$mcDD$sp(final int x0) {
      return new Tuple2.mcDD.sp(this.structure1$mcD$sp().fromInt$mcD$sp(x0), this.structure2$mcD$sp().fromInt$mcD$sp(x0));
   }

   // $FF: synthetic method
   static Tuple2 one$(final RingProduct2$mcDD$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcDD$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcDD$sp$(final RingProduct2$mcDD$sp $this) {
      return $this.one$mcDD$sp();
   }

   default Tuple2 one$mcDD$sp() {
      return new Tuple2.mcDD.sp(this.structure1$mcD$sp().one$mcD$sp(), this.structure2$mcD$sp().one$mcD$sp());
   }
}
