package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface SemiringProduct2$mcFF$sp extends SemiringProduct2 {
   // $FF: synthetic method
   static Tuple2 zero$(final SemiringProduct2$mcFF$sp $this) {
      return $this.zero();
   }

   default Tuple2 zero() {
      return this.zero$mcFF$sp();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcFF$sp$(final SemiringProduct2$mcFF$sp $this) {
      return $this.zero$mcFF$sp();
   }

   default Tuple2 zero$mcFF$sp() {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().zero$mcF$sp()), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().zero$mcF$sp()));
   }

   // $FF: synthetic method
   static Tuple2 plus$(final SemiringProduct2$mcFF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple2 plus(final Tuple2 x0, final Tuple2 x1) {
      return this.plus$mcFF$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcFF$sp$(final SemiringProduct2$mcFF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcFF$sp(x0, x1);
   }

   default Tuple2 plus$mcFF$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().plus$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()), BoxesRunTime.unboxToFloat(x1._1()))), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().plus$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()), BoxesRunTime.unboxToFloat(x1._2()))));
   }

   // $FF: synthetic method
   static Tuple2 times$(final SemiringProduct2$mcFF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times(x0, x1);
   }

   default Tuple2 times(final Tuple2 x0, final Tuple2 x1) {
      return this.times$mcFF$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcFF$sp$(final SemiringProduct2$mcFF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcFF$sp(x0, x1);
   }

   default Tuple2 times$mcFF$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().times$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()), BoxesRunTime.unboxToFloat(x1._1()))), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().times$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()), BoxesRunTime.unboxToFloat(x1._2()))));
   }

   // $FF: synthetic method
   static Tuple2 pow$(final SemiringProduct2$mcFF$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple2 pow(final Tuple2 x0, final int x1) {
      return this.pow$mcFF$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcFF$sp$(final SemiringProduct2$mcFF$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcFF$sp(x0, x1);
   }

   default Tuple2 pow$mcFF$sp(final Tuple2 x0, final int x1) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().pow$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()), x1)), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().pow$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()), x1)));
   }
}
