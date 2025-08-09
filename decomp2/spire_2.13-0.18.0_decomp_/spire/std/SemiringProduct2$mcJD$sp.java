package spire.std;

import scala.Tuple2;

public interface SemiringProduct2$mcJD$sp extends SemiringProduct2 {
   // $FF: synthetic method
   static Tuple2 zero$(final SemiringProduct2$mcJD$sp $this) {
      return $this.zero();
   }

   default Tuple2 zero() {
      return this.zero$mcJD$sp();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcJD$sp$(final SemiringProduct2$mcJD$sp $this) {
      return $this.zero$mcJD$sp();
   }

   default Tuple2 zero$mcJD$sp() {
      return new Tuple2.mcJD.sp(this.structure1$mcJ$sp().zero$mcJ$sp(), this.structure2$mcD$sp().zero$mcD$sp());
   }

   // $FF: synthetic method
   static Tuple2 plus$(final SemiringProduct2$mcJD$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple2 plus(final Tuple2 x0, final Tuple2 x1) {
      return this.plus$mcJD$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcJD$sp$(final SemiringProduct2$mcJD$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcJD$sp(x0, x1);
   }

   default Tuple2 plus$mcJD$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2.mcJD.sp(this.structure1$mcJ$sp().plus$mcJ$sp(x0._1$mcJ$sp(), x1._1$mcJ$sp()), this.structure2$mcD$sp().plus$mcD$sp(x0._2$mcD$sp(), x1._2$mcD$sp()));
   }

   // $FF: synthetic method
   static Tuple2 times$(final SemiringProduct2$mcJD$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times(x0, x1);
   }

   default Tuple2 times(final Tuple2 x0, final Tuple2 x1) {
      return this.times$mcJD$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcJD$sp$(final SemiringProduct2$mcJD$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcJD$sp(x0, x1);
   }

   default Tuple2 times$mcJD$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2.mcJD.sp(this.structure1$mcJ$sp().times$mcJ$sp(x0._1$mcJ$sp(), x1._1$mcJ$sp()), this.structure2$mcD$sp().times$mcD$sp(x0._2$mcD$sp(), x1._2$mcD$sp()));
   }

   // $FF: synthetic method
   static Tuple2 pow$(final SemiringProduct2$mcJD$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple2 pow(final Tuple2 x0, final int x1) {
      return this.pow$mcJD$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcJD$sp$(final SemiringProduct2$mcJD$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcJD$sp(x0, x1);
   }

   default Tuple2 pow$mcJD$sp(final Tuple2 x0, final int x1) {
      return new Tuple2.mcJD.sp(this.structure1$mcJ$sp().pow$mcJ$sp(x0._1$mcJ$sp(), x1), this.structure2$mcD$sp().pow$mcD$sp(x0._2$mcD$sp(), x1));
   }
}
