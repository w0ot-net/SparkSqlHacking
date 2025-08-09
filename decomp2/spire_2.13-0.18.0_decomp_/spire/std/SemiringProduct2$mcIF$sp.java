package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface SemiringProduct2$mcIF$sp extends SemiringProduct2 {
   // $FF: synthetic method
   static Tuple2 zero$(final SemiringProduct2$mcIF$sp $this) {
      return $this.zero();
   }

   default Tuple2 zero() {
      return this.zero$mcIF$sp();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcIF$sp$(final SemiringProduct2$mcIF$sp $this) {
      return $this.zero$mcIF$sp();
   }

   default Tuple2 zero$mcIF$sp() {
      return new Tuple2(BoxesRunTime.boxToInteger(this.structure1$mcI$sp().zero$mcI$sp()), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().zero$mcF$sp()));
   }

   // $FF: synthetic method
   static Tuple2 plus$(final SemiringProduct2$mcIF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple2 plus(final Tuple2 x0, final Tuple2 x1) {
      return this.plus$mcIF$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcIF$sp$(final SemiringProduct2$mcIF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcIF$sp(x0, x1);
   }

   default Tuple2 plus$mcIF$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2(BoxesRunTime.boxToInteger(this.structure1$mcI$sp().plus$mcI$sp(x0._1$mcI$sp(), x1._1$mcI$sp())), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().plus$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()), BoxesRunTime.unboxToFloat(x1._2()))));
   }

   // $FF: synthetic method
   static Tuple2 times$(final SemiringProduct2$mcIF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times(x0, x1);
   }

   default Tuple2 times(final Tuple2 x0, final Tuple2 x1) {
      return this.times$mcIF$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcIF$sp$(final SemiringProduct2$mcIF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcIF$sp(x0, x1);
   }

   default Tuple2 times$mcIF$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2(BoxesRunTime.boxToInteger(this.structure1$mcI$sp().times$mcI$sp(x0._1$mcI$sp(), x1._1$mcI$sp())), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().times$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()), BoxesRunTime.unboxToFloat(x1._2()))));
   }

   // $FF: synthetic method
   static Tuple2 pow$(final SemiringProduct2$mcIF$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple2 pow(final Tuple2 x0, final int x1) {
      return this.pow$mcIF$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcIF$sp$(final SemiringProduct2$mcIF$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcIF$sp(x0, x1);
   }

   default Tuple2 pow$mcIF$sp(final Tuple2 x0, final int x1) {
      return new Tuple2(BoxesRunTime.boxToInteger(this.structure1$mcI$sp().pow$mcI$sp(x0._1$mcI$sp(), x1)), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().pow$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()), x1)));
   }
}
