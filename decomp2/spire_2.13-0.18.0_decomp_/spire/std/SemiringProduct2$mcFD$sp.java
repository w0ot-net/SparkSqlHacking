package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface SemiringProduct2$mcFD$sp extends SemiringProduct2 {
   // $FF: synthetic method
   static Tuple2 zero$(final SemiringProduct2$mcFD$sp $this) {
      return $this.zero();
   }

   default Tuple2 zero() {
      return this.zero$mcFD$sp();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcFD$sp$(final SemiringProduct2$mcFD$sp $this) {
      return $this.zero$mcFD$sp();
   }

   default Tuple2 zero$mcFD$sp() {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().zero$mcF$sp()), BoxesRunTime.boxToDouble(this.structure2$mcD$sp().zero$mcD$sp()));
   }

   // $FF: synthetic method
   static Tuple2 plus$(final SemiringProduct2$mcFD$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple2 plus(final Tuple2 x0, final Tuple2 x1) {
      return this.plus$mcFD$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcFD$sp$(final SemiringProduct2$mcFD$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcFD$sp(x0, x1);
   }

   default Tuple2 plus$mcFD$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().plus$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()), BoxesRunTime.unboxToFloat(x1._1()))), BoxesRunTime.boxToDouble(this.structure2$mcD$sp().plus$mcD$sp(x0._2$mcD$sp(), x1._2$mcD$sp())));
   }

   // $FF: synthetic method
   static Tuple2 times$(final SemiringProduct2$mcFD$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times(x0, x1);
   }

   default Tuple2 times(final Tuple2 x0, final Tuple2 x1) {
      return this.times$mcFD$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcFD$sp$(final SemiringProduct2$mcFD$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcFD$sp(x0, x1);
   }

   default Tuple2 times$mcFD$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().times$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()), BoxesRunTime.unboxToFloat(x1._1()))), BoxesRunTime.boxToDouble(this.structure2$mcD$sp().times$mcD$sp(x0._2$mcD$sp(), x1._2$mcD$sp())));
   }

   // $FF: synthetic method
   static Tuple2 pow$(final SemiringProduct2$mcFD$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple2 pow(final Tuple2 x0, final int x1) {
      return this.pow$mcFD$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcFD$sp$(final SemiringProduct2$mcFD$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcFD$sp(x0, x1);
   }

   default Tuple2 pow$mcFD$sp(final Tuple2 x0, final int x1) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().pow$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()), x1)), BoxesRunTime.boxToDouble(this.structure2$mcD$sp().pow$mcD$sp(x0._2$mcD$sp(), x1)));
   }
}
