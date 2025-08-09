package spire.std;

import algebra.ring.Rng;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)4\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005\u0001B\u0003\u0005\u00067\u0002!\t\u0001\u0018\u0005\u0006A\u00021\u0019!\u0019\u0005\u0006G\u00021\u0019\u0001\u001a\u0005\u0006M\u0002!\ta\u001a\u0002\f%:<\u0007K]8ek\u000e$(G\u0003\u0002\b\u0011\u0005\u00191\u000f\u001e3\u000b\u0003%\tQa\u001d9je\u0016,2a\u0003\u0015M'\u0011\u0001ABE,\u0011\u00055\u0001R\"\u0001\b\u000b\u0003=\tQa]2bY\u0006L!!\u0005\b\u0003\r\u0005s\u0017PU3g!\r\u0019\u0002e\t\b\u0003)uq!!F\u000e\u000f\u0005YQR\"A\f\u000b\u0005aI\u0012A\u0002\u001fs_>$hh\u0001\u0001\n\u0003%I!\u0001\b\u0005\u0002\u000f\u0005dw-\u001a2sC&\u0011adH\u0001\ba\u0006\u001c7.Y4f\u0015\ta\u0002\"\u0003\u0002\"E\t\u0019!K\\4\u000b\u0005yy\u0002\u0003B\u0007%M-K!!\n\b\u0003\rQ+\b\u000f\\33!\t9\u0003\u0006\u0004\u0001\u0005\u0013%\u0002\u0001\u0015!A\u0001\u0006\u0004Q#!A!\u0012\u0005-r\u0003CA\u0007-\u0013\ticBA\u0004O_RD\u0017N\\4\u0011\u00055y\u0013B\u0001\u0019\u000f\u0005\r\te.\u001f\u0015\u0007QI*D(\u0011$\u0011\u00055\u0019\u0014B\u0001\u001b\u000f\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\r2t'\u000f\u001d\u000f\u000559\u0014B\u0001\u001d\u000f\u0003\rIe\u000e^\u0019\u0005IiZtB\u0004\u0002\u0017w%\tq\"M\u0003${y\u0002uH\u0004\u0002\u000e}%\u0011qHD\u0001\u0005\u0019>tw-\r\u0003%umz\u0011'B\u0012C\u0007\u0016#eBA\u0007D\u0013\t!e\"A\u0003GY>\fG/\r\u0003%umz\u0011'B\u0012H\u0011*KeBA\u0007I\u0013\tIe\"\u0001\u0004E_V\u0014G.Z\u0019\u0005IiZt\u0002\u0005\u0002(\u0019\u0012IQ\n\u0001Q\u0001\u0002\u0003\u0015\rA\u000b\u0002\u0002\u0005\"2AJM(R'V\u000bTa\t\u001c8!b\nD\u0001\n\u001e<\u001fE*1%\u0010 S\u007fE\"AEO\u001e\u0010c\u0015\u0019#i\u0011+Ec\u0011!#hO\b2\u000b\r:\u0005JV%2\t\u0011R4h\u0004\t\u00051f33*D\u0001\u0007\u0013\tQfA\u0001\tTK6L'/\u001b8h!J|G-^2ue\u00051A%\u001b8ji\u0012\"\u0012!\u0018\t\u0003\u001byK!a\u0018\b\u0003\tUs\u0017\u000e^\u0001\u000bgR\u0014Xo\u0019;ve\u0016\fT#\u00012\u0011\u0007M\u0001c%\u0001\u0006tiJ,8\r^;sKJ*\u0012!\u001a\t\u0004'\u0001Z\u0015A\u00028fO\u0006$X\r\u0006\u0002$Q\")\u0011\u000e\u0002a\u0001G\u0005\u0011\u0001\u0010\r"
)
public interface RngProduct2 extends Rng, SemiringProduct2 {
   Rng structure1();

   Rng structure2();

   // $FF: synthetic method
   static Tuple2 negate$(final RngProduct2 $this, final Tuple2 x0) {
      return $this.negate(x0);
   }

   default Tuple2 negate(final Tuple2 x0) {
      return new Tuple2(this.structure1().negate(x0._1()), this.structure2().negate(x0._2()));
   }

   // $FF: synthetic method
   static Rng structure1$mcD$sp$(final RngProduct2 $this) {
      return $this.structure1$mcD$sp();
   }

   default Rng structure1$mcD$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Rng structure1$mcF$sp$(final RngProduct2 $this) {
      return $this.structure1$mcF$sp();
   }

   default Rng structure1$mcF$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Rng structure1$mcI$sp$(final RngProduct2 $this) {
      return $this.structure1$mcI$sp();
   }

   default Rng structure1$mcI$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Rng structure1$mcJ$sp$(final RngProduct2 $this) {
      return $this.structure1$mcJ$sp();
   }

   default Rng structure1$mcJ$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Rng structure2$mcD$sp$(final RngProduct2 $this) {
      return $this.structure2$mcD$sp();
   }

   default Rng structure2$mcD$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Rng structure2$mcF$sp$(final RngProduct2 $this) {
      return $this.structure2$mcF$sp();
   }

   default Rng structure2$mcF$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Rng structure2$mcI$sp$(final RngProduct2 $this) {
      return $this.structure2$mcI$sp();
   }

   default Rng structure2$mcI$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Rng structure2$mcJ$sp$(final RngProduct2 $this) {
      return $this.structure2$mcJ$sp();
   }

   default Rng structure2$mcJ$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Tuple2 negate$mcDD$sp$(final RngProduct2 $this, final Tuple2 x0) {
      return $this.negate$mcDD$sp(x0);
   }

   default Tuple2 negate$mcDD$sp(final Tuple2 x0) {
      return this.negate(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcDF$sp$(final RngProduct2 $this, final Tuple2 x0) {
      return $this.negate$mcDF$sp(x0);
   }

   default Tuple2 negate$mcDF$sp(final Tuple2 x0) {
      return this.negate(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcDI$sp$(final RngProduct2 $this, final Tuple2 x0) {
      return $this.negate$mcDI$sp(x0);
   }

   default Tuple2 negate$mcDI$sp(final Tuple2 x0) {
      return this.negate(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcDJ$sp$(final RngProduct2 $this, final Tuple2 x0) {
      return $this.negate$mcDJ$sp(x0);
   }

   default Tuple2 negate$mcDJ$sp(final Tuple2 x0) {
      return this.negate(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcFD$sp$(final RngProduct2 $this, final Tuple2 x0) {
      return $this.negate$mcFD$sp(x0);
   }

   default Tuple2 negate$mcFD$sp(final Tuple2 x0) {
      return this.negate(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcFF$sp$(final RngProduct2 $this, final Tuple2 x0) {
      return $this.negate$mcFF$sp(x0);
   }

   default Tuple2 negate$mcFF$sp(final Tuple2 x0) {
      return this.negate(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcFI$sp$(final RngProduct2 $this, final Tuple2 x0) {
      return $this.negate$mcFI$sp(x0);
   }

   default Tuple2 negate$mcFI$sp(final Tuple2 x0) {
      return this.negate(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcFJ$sp$(final RngProduct2 $this, final Tuple2 x0) {
      return $this.negate$mcFJ$sp(x0);
   }

   default Tuple2 negate$mcFJ$sp(final Tuple2 x0) {
      return this.negate(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcID$sp$(final RngProduct2 $this, final Tuple2 x0) {
      return $this.negate$mcID$sp(x0);
   }

   default Tuple2 negate$mcID$sp(final Tuple2 x0) {
      return this.negate(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcIF$sp$(final RngProduct2 $this, final Tuple2 x0) {
      return $this.negate$mcIF$sp(x0);
   }

   default Tuple2 negate$mcIF$sp(final Tuple2 x0) {
      return this.negate(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcII$sp$(final RngProduct2 $this, final Tuple2 x0) {
      return $this.negate$mcII$sp(x0);
   }

   default Tuple2 negate$mcII$sp(final Tuple2 x0) {
      return this.negate(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcIJ$sp$(final RngProduct2 $this, final Tuple2 x0) {
      return $this.negate$mcIJ$sp(x0);
   }

   default Tuple2 negate$mcIJ$sp(final Tuple2 x0) {
      return this.negate(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcJD$sp$(final RngProduct2 $this, final Tuple2 x0) {
      return $this.negate$mcJD$sp(x0);
   }

   default Tuple2 negate$mcJD$sp(final Tuple2 x0) {
      return this.negate(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcJF$sp$(final RngProduct2 $this, final Tuple2 x0) {
      return $this.negate$mcJF$sp(x0);
   }

   default Tuple2 negate$mcJF$sp(final Tuple2 x0) {
      return this.negate(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcJI$sp$(final RngProduct2 $this, final Tuple2 x0) {
      return $this.negate$mcJI$sp(x0);
   }

   default Tuple2 negate$mcJI$sp(final Tuple2 x0) {
      return this.negate(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcJJ$sp$(final RngProduct2 $this, final Tuple2 x0) {
      return $this.negate$mcJJ$sp(x0);
   }

   default Tuple2 negate$mcJJ$sp(final Tuple2 x0) {
      return this.negate(x0);
   }

   static void $init$(final RngProduct2 $this) {
   }
}
