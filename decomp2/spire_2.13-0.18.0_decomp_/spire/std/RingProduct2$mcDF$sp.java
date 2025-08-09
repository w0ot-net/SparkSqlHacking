package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface RingProduct2$mcDF$sp extends RingProduct2, RngProduct2$mcDF$sp {
   // $FF: synthetic method
   static Tuple2 fromInt$(final RingProduct2$mcDF$sp $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple2 fromInt(final int x0) {
      return this.fromInt$mcDF$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcDF$sp$(final RingProduct2$mcDF$sp $this, final int x0) {
      return $this.fromInt$mcDF$sp(x0);
   }

   default Tuple2 fromInt$mcDF$sp(final int x0) {
      return new Tuple2(BoxesRunTime.boxToDouble(this.structure1$mcD$sp().fromInt$mcD$sp(x0)), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().fromInt$mcF$sp(x0)));
   }

   // $FF: synthetic method
   static Tuple2 one$(final RingProduct2$mcDF$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcDF$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcDF$sp$(final RingProduct2$mcDF$sp $this) {
      return $this.one$mcDF$sp();
   }

   default Tuple2 one$mcDF$sp() {
      return new Tuple2(BoxesRunTime.boxToDouble(this.structure1$mcD$sp().one$mcD$sp()), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().one$mcF$sp()));
   }
}
