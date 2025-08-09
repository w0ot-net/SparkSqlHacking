package spire.std;

import scala.Tuple2;

public interface RingProduct2$mcIJ$sp extends RingProduct2, RngProduct2$mcIJ$sp {
   // $FF: synthetic method
   static Tuple2 fromInt$(final RingProduct2$mcIJ$sp $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple2 fromInt(final int x0) {
      return this.fromInt$mcIJ$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcIJ$sp$(final RingProduct2$mcIJ$sp $this, final int x0) {
      return $this.fromInt$mcIJ$sp(x0);
   }

   default Tuple2 fromInt$mcIJ$sp(final int x0) {
      return new Tuple2.mcIJ.sp(this.structure1$mcI$sp().fromInt$mcI$sp(x0), this.structure2$mcJ$sp().fromInt$mcJ$sp(x0));
   }

   // $FF: synthetic method
   static Tuple2 one$(final RingProduct2$mcIJ$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcIJ$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcIJ$sp$(final RingProduct2$mcIJ$sp $this) {
      return $this.one$mcIJ$sp();
   }

   default Tuple2 one$mcIJ$sp() {
      return new Tuple2.mcIJ.sp(this.structure1$mcI$sp().one$mcI$sp(), this.structure2$mcJ$sp().one$mcJ$sp());
   }
}
