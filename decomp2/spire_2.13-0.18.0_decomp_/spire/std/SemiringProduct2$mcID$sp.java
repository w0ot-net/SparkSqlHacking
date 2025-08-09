package spire.std;

import scala.Tuple2;

public interface SemiringProduct2$mcID$sp extends SemiringProduct2 {
   // $FF: synthetic method
   static Tuple2 zero$(final SemiringProduct2$mcID$sp $this) {
      return $this.zero();
   }

   default Tuple2 zero() {
      return this.zero$mcID$sp();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcID$sp$(final SemiringProduct2$mcID$sp $this) {
      return $this.zero$mcID$sp();
   }

   default Tuple2 zero$mcID$sp() {
      return new Tuple2.mcID.sp(this.structure1$mcI$sp().zero$mcI$sp(), this.structure2$mcD$sp().zero$mcD$sp());
   }

   // $FF: synthetic method
   static Tuple2 plus$(final SemiringProduct2$mcID$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple2 plus(final Tuple2 x0, final Tuple2 x1) {
      return this.plus$mcID$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcID$sp$(final SemiringProduct2$mcID$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcID$sp(x0, x1);
   }

   default Tuple2 plus$mcID$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2.mcID.sp(this.structure1$mcI$sp().plus$mcI$sp(x0._1$mcI$sp(), x1._1$mcI$sp()), this.structure2$mcD$sp().plus$mcD$sp(x0._2$mcD$sp(), x1._2$mcD$sp()));
   }

   // $FF: synthetic method
   static Tuple2 times$(final SemiringProduct2$mcID$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times(x0, x1);
   }

   default Tuple2 times(final Tuple2 x0, final Tuple2 x1) {
      return this.times$mcID$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcID$sp$(final SemiringProduct2$mcID$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcID$sp(x0, x1);
   }

   default Tuple2 times$mcID$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2.mcID.sp(this.structure1$mcI$sp().times$mcI$sp(x0._1$mcI$sp(), x1._1$mcI$sp()), this.structure2$mcD$sp().times$mcD$sp(x0._2$mcD$sp(), x1._2$mcD$sp()));
   }

   // $FF: synthetic method
   static Tuple2 pow$(final SemiringProduct2$mcID$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple2 pow(final Tuple2 x0, final int x1) {
      return this.pow$mcID$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcID$sp$(final SemiringProduct2$mcID$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcID$sp(x0, x1);
   }

   default Tuple2 pow$mcID$sp(final Tuple2 x0, final int x1) {
      return new Tuple2.mcID.sp(this.structure1$mcI$sp().pow$mcI$sp(x0._1$mcI$sp(), x1), this.structure2$mcD$sp().pow$mcD$sp(x0._2$mcD$sp(), x1));
   }
}
