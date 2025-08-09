package spire.std;

import scala.Tuple2;

public interface SemiringProduct2$mcDD$sp extends SemiringProduct2 {
   // $FF: synthetic method
   static Tuple2 zero$(final SemiringProduct2$mcDD$sp $this) {
      return $this.zero();
   }

   default Tuple2 zero() {
      return this.zero$mcDD$sp();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcDD$sp$(final SemiringProduct2$mcDD$sp $this) {
      return $this.zero$mcDD$sp();
   }

   default Tuple2 zero$mcDD$sp() {
      return new Tuple2.mcDD.sp(this.structure1$mcD$sp().zero$mcD$sp(), this.structure2$mcD$sp().zero$mcD$sp());
   }

   // $FF: synthetic method
   static Tuple2 plus$(final SemiringProduct2$mcDD$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple2 plus(final Tuple2 x0, final Tuple2 x1) {
      return this.plus$mcDD$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcDD$sp$(final SemiringProduct2$mcDD$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcDD$sp(x0, x1);
   }

   default Tuple2 plus$mcDD$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2.mcDD.sp(this.structure1$mcD$sp().plus$mcD$sp(x0._1$mcD$sp(), x1._1$mcD$sp()), this.structure2$mcD$sp().plus$mcD$sp(x0._2$mcD$sp(), x1._2$mcD$sp()));
   }

   // $FF: synthetic method
   static Tuple2 times$(final SemiringProduct2$mcDD$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times(x0, x1);
   }

   default Tuple2 times(final Tuple2 x0, final Tuple2 x1) {
      return this.times$mcDD$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcDD$sp$(final SemiringProduct2$mcDD$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcDD$sp(x0, x1);
   }

   default Tuple2 times$mcDD$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2.mcDD.sp(this.structure1$mcD$sp().times$mcD$sp(x0._1$mcD$sp(), x1._1$mcD$sp()), this.structure2$mcD$sp().times$mcD$sp(x0._2$mcD$sp(), x1._2$mcD$sp()));
   }

   // $FF: synthetic method
   static Tuple2 pow$(final SemiringProduct2$mcDD$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple2 pow(final Tuple2 x0, final int x1) {
      return this.pow$mcDD$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcDD$sp$(final SemiringProduct2$mcDD$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcDD$sp(x0, x1);
   }

   default Tuple2 pow$mcDD$sp(final Tuple2 x0, final int x1) {
      return new Tuple2.mcDD.sp(this.structure1$mcD$sp().pow$mcD$sp(x0._1$mcD$sp(), x1), this.structure2$mcD$sp().pow$mcD$sp(x0._2$mcD$sp(), x1));
   }
}
