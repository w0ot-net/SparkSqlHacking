package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface RingProduct2$mcJF$sp extends RingProduct2, RngProduct2$mcJF$sp {
   // $FF: synthetic method
   static Tuple2 fromInt$(final RingProduct2$mcJF$sp $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple2 fromInt(final int x0) {
      return this.fromInt$mcJF$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcJF$sp$(final RingProduct2$mcJF$sp $this, final int x0) {
      return $this.fromInt$mcJF$sp(x0);
   }

   default Tuple2 fromInt$mcJF$sp(final int x0) {
      return new Tuple2(BoxesRunTime.boxToLong(this.structure1$mcJ$sp().fromInt$mcJ$sp(x0)), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().fromInt$mcF$sp(x0)));
   }

   // $FF: synthetic method
   static Tuple2 one$(final RingProduct2$mcJF$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcJF$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcJF$sp$(final RingProduct2$mcJF$sp $this) {
      return $this.one$mcJF$sp();
   }

   default Tuple2 one$mcJF$sp() {
      return new Tuple2(BoxesRunTime.boxToLong(this.structure1$mcJ$sp().one$mcJ$sp()), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().one$mcF$sp()));
   }
}
