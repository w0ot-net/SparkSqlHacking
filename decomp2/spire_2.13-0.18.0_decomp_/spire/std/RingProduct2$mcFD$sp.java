package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface RingProduct2$mcFD$sp extends RingProduct2, RngProduct2$mcFD$sp {
   // $FF: synthetic method
   static Tuple2 fromInt$(final RingProduct2$mcFD$sp $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple2 fromInt(final int x0) {
      return this.fromInt$mcFD$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcFD$sp$(final RingProduct2$mcFD$sp $this, final int x0) {
      return $this.fromInt$mcFD$sp(x0);
   }

   default Tuple2 fromInt$mcFD$sp(final int x0) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().fromInt$mcF$sp(x0)), BoxesRunTime.boxToDouble(this.structure2$mcD$sp().fromInt$mcD$sp(x0)));
   }

   // $FF: synthetic method
   static Tuple2 one$(final RingProduct2$mcFD$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcFD$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcFD$sp$(final RingProduct2$mcFD$sp $this) {
      return $this.one$mcFD$sp();
   }

   default Tuple2 one$mcFD$sp() {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().one$mcF$sp()), BoxesRunTime.boxToDouble(this.structure2$mcD$sp().one$mcD$sp()));
   }
}
