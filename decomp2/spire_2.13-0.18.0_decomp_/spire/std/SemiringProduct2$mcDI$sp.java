package spire.std;

import scala.Tuple2;

public interface SemiringProduct2$mcDI$sp extends SemiringProduct2 {
   // $FF: synthetic method
   static Tuple2 zero$(final SemiringProduct2$mcDI$sp $this) {
      return $this.zero();
   }

   default Tuple2 zero() {
      return this.zero$mcDI$sp();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcDI$sp$(final SemiringProduct2$mcDI$sp $this) {
      return $this.zero$mcDI$sp();
   }

   default Tuple2 zero$mcDI$sp() {
      return new Tuple2.mcDI.sp(this.structure1$mcD$sp().zero$mcD$sp(), this.structure2$mcI$sp().zero$mcI$sp());
   }

   // $FF: synthetic method
   static Tuple2 plus$(final SemiringProduct2$mcDI$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple2 plus(final Tuple2 x0, final Tuple2 x1) {
      return this.plus$mcDI$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcDI$sp$(final SemiringProduct2$mcDI$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcDI$sp(x0, x1);
   }

   default Tuple2 plus$mcDI$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2.mcDI.sp(this.structure1$mcD$sp().plus$mcD$sp(x0._1$mcD$sp(), x1._1$mcD$sp()), this.structure2$mcI$sp().plus$mcI$sp(x0._2$mcI$sp(), x1._2$mcI$sp()));
   }

   // $FF: synthetic method
   static Tuple2 times$(final SemiringProduct2$mcDI$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times(x0, x1);
   }

   default Tuple2 times(final Tuple2 x0, final Tuple2 x1) {
      return this.times$mcDI$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcDI$sp$(final SemiringProduct2$mcDI$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcDI$sp(x0, x1);
   }

   default Tuple2 times$mcDI$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2.mcDI.sp(this.structure1$mcD$sp().times$mcD$sp(x0._1$mcD$sp(), x1._1$mcD$sp()), this.structure2$mcI$sp().times$mcI$sp(x0._2$mcI$sp(), x1._2$mcI$sp()));
   }

   // $FF: synthetic method
   static Tuple2 pow$(final SemiringProduct2$mcDI$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple2 pow(final Tuple2 x0, final int x1) {
      return this.pow$mcDI$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcDI$sp$(final SemiringProduct2$mcDI$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcDI$sp(x0, x1);
   }

   default Tuple2 pow$mcDI$sp(final Tuple2 x0, final int x1) {
      return new Tuple2.mcDI.sp(this.structure1$mcD$sp().pow$mcD$sp(x0._1$mcD$sp(), x1), this.structure2$mcI$sp().pow$mcI$sp(x0._2$mcI$sp(), x1));
   }
}
