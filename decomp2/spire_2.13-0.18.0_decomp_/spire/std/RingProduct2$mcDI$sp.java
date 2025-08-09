package spire.std;

import scala.Tuple2;

public interface RingProduct2$mcDI$sp extends RingProduct2, RngProduct2$mcDI$sp {
   // $FF: synthetic method
   static Tuple2 fromInt$(final RingProduct2$mcDI$sp $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple2 fromInt(final int x0) {
      return this.fromInt$mcDI$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcDI$sp$(final RingProduct2$mcDI$sp $this, final int x0) {
      return $this.fromInt$mcDI$sp(x0);
   }

   default Tuple2 fromInt$mcDI$sp(final int x0) {
      return new Tuple2.mcDI.sp(this.structure1$mcD$sp().fromInt$mcD$sp(x0), this.structure2$mcI$sp().fromInt$mcI$sp(x0));
   }

   // $FF: synthetic method
   static Tuple2 one$(final RingProduct2$mcDI$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcDI$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcDI$sp$(final RingProduct2$mcDI$sp $this) {
      return $this.one$mcDI$sp();
   }

   default Tuple2 one$mcDI$sp() {
      return new Tuple2.mcDI.sp(this.structure1$mcD$sp().one$mcD$sp(), this.structure2$mcI$sp().one$mcI$sp());
   }
}
