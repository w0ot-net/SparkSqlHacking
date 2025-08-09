package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface SemiringProduct2$mcDF$sp extends SemiringProduct2 {
   // $FF: synthetic method
   static Tuple2 zero$(final SemiringProduct2$mcDF$sp $this) {
      return $this.zero();
   }

   default Tuple2 zero() {
      return this.zero$mcDF$sp();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcDF$sp$(final SemiringProduct2$mcDF$sp $this) {
      return $this.zero$mcDF$sp();
   }

   default Tuple2 zero$mcDF$sp() {
      return new Tuple2(BoxesRunTime.boxToDouble(this.structure1$mcD$sp().zero$mcD$sp()), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().zero$mcF$sp()));
   }

   // $FF: synthetic method
   static Tuple2 plus$(final SemiringProduct2$mcDF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple2 plus(final Tuple2 x0, final Tuple2 x1) {
      return this.plus$mcDF$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcDF$sp$(final SemiringProduct2$mcDF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcDF$sp(x0, x1);
   }

   default Tuple2 plus$mcDF$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2(BoxesRunTime.boxToDouble(this.structure1$mcD$sp().plus$mcD$sp(x0._1$mcD$sp(), x1._1$mcD$sp())), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().plus$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()), BoxesRunTime.unboxToFloat(x1._2()))));
   }

   // $FF: synthetic method
   static Tuple2 times$(final SemiringProduct2$mcDF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times(x0, x1);
   }

   default Tuple2 times(final Tuple2 x0, final Tuple2 x1) {
      return this.times$mcDF$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcDF$sp$(final SemiringProduct2$mcDF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcDF$sp(x0, x1);
   }

   default Tuple2 times$mcDF$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2(BoxesRunTime.boxToDouble(this.structure1$mcD$sp().times$mcD$sp(x0._1$mcD$sp(), x1._1$mcD$sp())), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().times$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()), BoxesRunTime.unboxToFloat(x1._2()))));
   }

   // $FF: synthetic method
   static Tuple2 pow$(final SemiringProduct2$mcDF$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple2 pow(final Tuple2 x0, final int x1) {
      return this.pow$mcDF$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcDF$sp$(final SemiringProduct2$mcDF$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcDF$sp(x0, x1);
   }

   default Tuple2 pow$mcDF$sp(final Tuple2 x0, final int x1) {
      return new Tuple2(BoxesRunTime.boxToDouble(this.structure1$mcD$sp().pow$mcD$sp(x0._1$mcD$sp(), x1)), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().pow$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()), x1)));
   }
}
