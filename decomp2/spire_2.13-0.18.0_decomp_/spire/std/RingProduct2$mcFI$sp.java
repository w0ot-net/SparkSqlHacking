package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface RingProduct2$mcFI$sp extends RingProduct2, RngProduct2$mcFI$sp {
   // $FF: synthetic method
   static Tuple2 fromInt$(final RingProduct2$mcFI$sp $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple2 fromInt(final int x0) {
      return this.fromInt$mcFI$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcFI$sp$(final RingProduct2$mcFI$sp $this, final int x0) {
      return $this.fromInt$mcFI$sp(x0);
   }

   default Tuple2 fromInt$mcFI$sp(final int x0) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().fromInt$mcF$sp(x0)), BoxesRunTime.boxToInteger(this.structure2$mcI$sp().fromInt$mcI$sp(x0)));
   }

   // $FF: synthetic method
   static Tuple2 one$(final RingProduct2$mcFI$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcFI$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcFI$sp$(final RingProduct2$mcFI$sp $this) {
      return $this.one$mcFI$sp();
   }

   default Tuple2 one$mcFI$sp() {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().one$mcF$sp()), BoxesRunTime.boxToInteger(this.structure2$mcI$sp().one$mcI$sp()));
   }
}
