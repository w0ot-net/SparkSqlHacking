package spire.std;

import algebra.ring.Semiring;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a4\u0001\u0002C\u0005\u0011\u0002\u0007\u00051\"\u0004\u0005\u00065\u0002!\ta\u0017\u0005\u0006?\u00021\u0019\u0001\u0019\u0005\u0006E\u00021\u0019a\u0019\u0005\u0006K\u0002!\tA\u001a\u0005\u0006O\u0002!\t\u0001\u001b\u0005\u0006[\u0002!\tA\u001c\u0005\u0006c\u0002!\tE\u001d\u0002\u0011'\u0016l\u0017N]5oOB\u0013x\u000eZ;diJR!AC\u0006\u0002\u0007M$HMC\u0001\r\u0003\u0015\u0019\b/\u001b:f+\rq1fT\n\u0004\u0001=)\u0002C\u0001\t\u0014\u001b\u0005\t\"\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\t\"AB!osJ+g\rE\u0002\u0017G\u0019r!a\u0006\u0011\u000f\u0005aqbBA\r\u001e\u001b\u0005Q\"BA\u000e\u001d\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\u0007\n\u0005}Y\u0011aB1mO\u0016\u0014'/Y\u0005\u0003C\t\nq\u0001]1dW\u0006<WM\u0003\u0002 \u0017%\u0011A%\n\u0002\t'\u0016l\u0017N]5oO*\u0011\u0011E\t\t\u0005!\u001dJc*\u0003\u0002)#\t1A+\u001e9mKJ\u0002\"AK\u0016\r\u0001\u0011IA\u0006\u0001Q\u0001\u0002\u0003\u0015\r!\f\u0002\u0002\u0003F\u0011a&\r\t\u0003!=J!\u0001M\t\u0003\u000f9{G\u000f[5oOB\u0011\u0001CM\u0005\u0003gE\u00111!\u00118zQ\u0019YS\u0007O E\u0013B\u0011\u0001CN\u0005\u0003oE\u00111b\u001d9fG&\fG.\u001b>fIF*1%\u000f\u001e=w9\u0011\u0001CO\u0005\u0003wE\t1!\u00138uc\u0011!SH\u0010\n\u000f\u0005eq\u0014\"\u0001\n2\u000b\r\u0002\u0015i\u0011\"\u000f\u0005A\t\u0015B\u0001\"\u0012\u0003\u0011auN\\42\t\u0011jdHE\u0019\u0006G\u00153\u0005j\u0012\b\u0003!\u0019K!aR\t\u0002\u000b\u0019cw.\u0019;2\t\u0011jdHE\u0019\u0006G)[U\n\u0014\b\u0003!-K!\u0001T\t\u0002\r\u0011{WO\u00197fc\u0011!SH\u0010\n\u0011\u0005)zE!\u0003)\u0001A\u0003\u0005\tQ1\u0001.\u0005\u0005\u0011\u0005FB(6%R3\u0006,M\u0003$si\u001a6(\r\u0003%{y\u0012\u0012'B\u0012A\u0003V\u0013\u0015\u0007\u0002\u0013>}I\tTaI#G/\u001e\u000bD\u0001J\u001f?%E*1ES&Z\u0019F\"A%\u0010 \u0013\u0003\u0019!\u0013N\\5uIQ\tA\f\u0005\u0002\u0011;&\u0011a,\u0005\u0002\u0005+:LG/\u0001\u0006tiJ,8\r^;sKF*\u0012!\u0019\t\u0004-\rJ\u0013AC:ueV\u001cG/\u001e:feU\tA\rE\u0002\u0017G9\u000bAA_3s_V\ta%\u0001\u0003qYV\u001cHc\u0001\u0014jW\")!.\u0002a\u0001M\u0005\u0011\u0001\u0010\r\u0005\u0006Y\u0016\u0001\rAJ\u0001\u0003qF\nQ\u0001^5nKN$2AJ8q\u0011\u0015Qg\u00011\u0001'\u0011\u0015ag\u00011\u0001'\u0003\r\u0001xn\u001e\u000b\u0004MM$\b\"\u00026\b\u0001\u00041\u0003\"\u00027\b\u0001\u0004)\bC\u0001\tw\u0013\t9\u0018CA\u0002J]R\u0004"
)
public interface SemiringProduct2 extends Semiring {
   Semiring structure1();

   Semiring structure2();

   // $FF: synthetic method
   static Tuple2 zero$(final SemiringProduct2 $this) {
      return $this.zero();
   }

   default Tuple2 zero() {
      return new Tuple2(this.structure1().zero(), this.structure2().zero());
   }

   // $FF: synthetic method
   static Tuple2 plus$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple2 plus(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2(this.structure1().plus(x0._1(), x1._1()), this.structure2().plus(x0._2(), x1._2()));
   }

   // $FF: synthetic method
   static Tuple2 times$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times(x0, x1);
   }

   default Tuple2 times(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2(this.structure1().times(x0._1(), x1._1()), this.structure2().times(x0._2(), x1._2()));
   }

   // $FF: synthetic method
   static Tuple2 pow$(final SemiringProduct2 $this, final Tuple2 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple2 pow(final Tuple2 x0, final int x1) {
      return new Tuple2(this.structure1().pow(x0._1(), x1), this.structure2().pow(x0._2(), x1));
   }

   // $FF: synthetic method
   static Semiring structure1$mcD$sp$(final SemiringProduct2 $this) {
      return $this.structure1$mcD$sp();
   }

   default Semiring structure1$mcD$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Semiring structure1$mcF$sp$(final SemiringProduct2 $this) {
      return $this.structure1$mcF$sp();
   }

   default Semiring structure1$mcF$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Semiring structure1$mcI$sp$(final SemiringProduct2 $this) {
      return $this.structure1$mcI$sp();
   }

   default Semiring structure1$mcI$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Semiring structure1$mcJ$sp$(final SemiringProduct2 $this) {
      return $this.structure1$mcJ$sp();
   }

   default Semiring structure1$mcJ$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Semiring structure2$mcD$sp$(final SemiringProduct2 $this) {
      return $this.structure2$mcD$sp();
   }

   default Semiring structure2$mcD$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Semiring structure2$mcF$sp$(final SemiringProduct2 $this) {
      return $this.structure2$mcF$sp();
   }

   default Semiring structure2$mcF$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Semiring structure2$mcI$sp$(final SemiringProduct2 $this) {
      return $this.structure2$mcI$sp();
   }

   default Semiring structure2$mcI$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Semiring structure2$mcJ$sp$(final SemiringProduct2 $this) {
      return $this.structure2$mcJ$sp();
   }

   default Semiring structure2$mcJ$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcDD$sp$(final SemiringProduct2 $this) {
      return $this.zero$mcDD$sp();
   }

   default Tuple2 zero$mcDD$sp() {
      return this.zero();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcDF$sp$(final SemiringProduct2 $this) {
      return $this.zero$mcDF$sp();
   }

   default Tuple2 zero$mcDF$sp() {
      return this.zero();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcDI$sp$(final SemiringProduct2 $this) {
      return $this.zero$mcDI$sp();
   }

   default Tuple2 zero$mcDI$sp() {
      return this.zero();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcDJ$sp$(final SemiringProduct2 $this) {
      return $this.zero$mcDJ$sp();
   }

   default Tuple2 zero$mcDJ$sp() {
      return this.zero();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcFD$sp$(final SemiringProduct2 $this) {
      return $this.zero$mcFD$sp();
   }

   default Tuple2 zero$mcFD$sp() {
      return this.zero();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcFF$sp$(final SemiringProduct2 $this) {
      return $this.zero$mcFF$sp();
   }

   default Tuple2 zero$mcFF$sp() {
      return this.zero();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcFI$sp$(final SemiringProduct2 $this) {
      return $this.zero$mcFI$sp();
   }

   default Tuple2 zero$mcFI$sp() {
      return this.zero();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcFJ$sp$(final SemiringProduct2 $this) {
      return $this.zero$mcFJ$sp();
   }

   default Tuple2 zero$mcFJ$sp() {
      return this.zero();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcID$sp$(final SemiringProduct2 $this) {
      return $this.zero$mcID$sp();
   }

   default Tuple2 zero$mcID$sp() {
      return this.zero();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcIF$sp$(final SemiringProduct2 $this) {
      return $this.zero$mcIF$sp();
   }

   default Tuple2 zero$mcIF$sp() {
      return this.zero();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcII$sp$(final SemiringProduct2 $this) {
      return $this.zero$mcII$sp();
   }

   default Tuple2 zero$mcII$sp() {
      return this.zero();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcIJ$sp$(final SemiringProduct2 $this) {
      return $this.zero$mcIJ$sp();
   }

   default Tuple2 zero$mcIJ$sp() {
      return this.zero();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcJD$sp$(final SemiringProduct2 $this) {
      return $this.zero$mcJD$sp();
   }

   default Tuple2 zero$mcJD$sp() {
      return this.zero();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcJF$sp$(final SemiringProduct2 $this) {
      return $this.zero$mcJF$sp();
   }

   default Tuple2 zero$mcJF$sp() {
      return this.zero();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcJI$sp$(final SemiringProduct2 $this) {
      return $this.zero$mcJI$sp();
   }

   default Tuple2 zero$mcJI$sp() {
      return this.zero();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcJJ$sp$(final SemiringProduct2 $this) {
      return $this.zero$mcJJ$sp();
   }

   default Tuple2 zero$mcJJ$sp() {
      return this.zero();
   }

   // $FF: synthetic method
   static Tuple2 plus$mcDD$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcDD$sp(x0, x1);
   }

   default Tuple2 plus$mcDD$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.plus(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcDF$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcDF$sp(x0, x1);
   }

   default Tuple2 plus$mcDF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.plus(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcDI$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcDI$sp(x0, x1);
   }

   default Tuple2 plus$mcDI$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.plus(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcDJ$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcDJ$sp(x0, x1);
   }

   default Tuple2 plus$mcDJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.plus(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcFD$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcFD$sp(x0, x1);
   }

   default Tuple2 plus$mcFD$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.plus(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcFF$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcFF$sp(x0, x1);
   }

   default Tuple2 plus$mcFF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.plus(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcFI$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcFI$sp(x0, x1);
   }

   default Tuple2 plus$mcFI$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.plus(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcFJ$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcFJ$sp(x0, x1);
   }

   default Tuple2 plus$mcFJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.plus(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcID$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcID$sp(x0, x1);
   }

   default Tuple2 plus$mcID$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.plus(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcIF$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcIF$sp(x0, x1);
   }

   default Tuple2 plus$mcIF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.plus(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcII$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcII$sp(x0, x1);
   }

   default Tuple2 plus$mcII$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.plus(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcIJ$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcIJ$sp(x0, x1);
   }

   default Tuple2 plus$mcIJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.plus(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcJD$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcJD$sp(x0, x1);
   }

   default Tuple2 plus$mcJD$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.plus(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcJF$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcJF$sp(x0, x1);
   }

   default Tuple2 plus$mcJF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.plus(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcJI$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcJI$sp(x0, x1);
   }

   default Tuple2 plus$mcJI$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.plus(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcJJ$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcJJ$sp(x0, x1);
   }

   default Tuple2 plus$mcJJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.plus(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcDD$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcDD$sp(x0, x1);
   }

   default Tuple2 times$mcDD$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.times(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcDF$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcDF$sp(x0, x1);
   }

   default Tuple2 times$mcDF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.times(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcDI$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcDI$sp(x0, x1);
   }

   default Tuple2 times$mcDI$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.times(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcDJ$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcDJ$sp(x0, x1);
   }

   default Tuple2 times$mcDJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.times(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcFD$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcFD$sp(x0, x1);
   }

   default Tuple2 times$mcFD$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.times(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcFF$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcFF$sp(x0, x1);
   }

   default Tuple2 times$mcFF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.times(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcFI$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcFI$sp(x0, x1);
   }

   default Tuple2 times$mcFI$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.times(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcFJ$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcFJ$sp(x0, x1);
   }

   default Tuple2 times$mcFJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.times(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcID$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcID$sp(x0, x1);
   }

   default Tuple2 times$mcID$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.times(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcIF$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcIF$sp(x0, x1);
   }

   default Tuple2 times$mcIF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.times(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcII$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcII$sp(x0, x1);
   }

   default Tuple2 times$mcII$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.times(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcIJ$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcIJ$sp(x0, x1);
   }

   default Tuple2 times$mcIJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.times(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcJD$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcJD$sp(x0, x1);
   }

   default Tuple2 times$mcJD$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.times(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcJF$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcJF$sp(x0, x1);
   }

   default Tuple2 times$mcJF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.times(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcJI$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcJI$sp(x0, x1);
   }

   default Tuple2 times$mcJI$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.times(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcJJ$sp$(final SemiringProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcJJ$sp(x0, x1);
   }

   default Tuple2 times$mcJJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.times(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcDD$sp$(final SemiringProduct2 $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcDD$sp(x0, x1);
   }

   default Tuple2 pow$mcDD$sp(final Tuple2 x0, final int x1) {
      return this.pow(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcDF$sp$(final SemiringProduct2 $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcDF$sp(x0, x1);
   }

   default Tuple2 pow$mcDF$sp(final Tuple2 x0, final int x1) {
      return this.pow(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcDI$sp$(final SemiringProduct2 $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcDI$sp(x0, x1);
   }

   default Tuple2 pow$mcDI$sp(final Tuple2 x0, final int x1) {
      return this.pow(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcDJ$sp$(final SemiringProduct2 $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcDJ$sp(x0, x1);
   }

   default Tuple2 pow$mcDJ$sp(final Tuple2 x0, final int x1) {
      return this.pow(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcFD$sp$(final SemiringProduct2 $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcFD$sp(x0, x1);
   }

   default Tuple2 pow$mcFD$sp(final Tuple2 x0, final int x1) {
      return this.pow(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcFF$sp$(final SemiringProduct2 $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcFF$sp(x0, x1);
   }

   default Tuple2 pow$mcFF$sp(final Tuple2 x0, final int x1) {
      return this.pow(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcFI$sp$(final SemiringProduct2 $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcFI$sp(x0, x1);
   }

   default Tuple2 pow$mcFI$sp(final Tuple2 x0, final int x1) {
      return this.pow(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcFJ$sp$(final SemiringProduct2 $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcFJ$sp(x0, x1);
   }

   default Tuple2 pow$mcFJ$sp(final Tuple2 x0, final int x1) {
      return this.pow(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcID$sp$(final SemiringProduct2 $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcID$sp(x0, x1);
   }

   default Tuple2 pow$mcID$sp(final Tuple2 x0, final int x1) {
      return this.pow(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcIF$sp$(final SemiringProduct2 $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcIF$sp(x0, x1);
   }

   default Tuple2 pow$mcIF$sp(final Tuple2 x0, final int x1) {
      return this.pow(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcII$sp$(final SemiringProduct2 $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcII$sp(x0, x1);
   }

   default Tuple2 pow$mcII$sp(final Tuple2 x0, final int x1) {
      return this.pow(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcIJ$sp$(final SemiringProduct2 $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcIJ$sp(x0, x1);
   }

   default Tuple2 pow$mcIJ$sp(final Tuple2 x0, final int x1) {
      return this.pow(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcJD$sp$(final SemiringProduct2 $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcJD$sp(x0, x1);
   }

   default Tuple2 pow$mcJD$sp(final Tuple2 x0, final int x1) {
      return this.pow(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcJF$sp$(final SemiringProduct2 $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcJF$sp(x0, x1);
   }

   default Tuple2 pow$mcJF$sp(final Tuple2 x0, final int x1) {
      return this.pow(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcJI$sp$(final SemiringProduct2 $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcJI$sp(x0, x1);
   }

   default Tuple2 pow$mcJI$sp(final Tuple2 x0, final int x1) {
      return this.pow(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcJJ$sp$(final SemiringProduct2 $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcJJ$sp(x0, x1);
   }

   default Tuple2 pow$mcJJ$sp(final Tuple2 x0, final int x1) {
      return this.pow(x0, x1);
   }

   static void $init$(final SemiringProduct2 $this) {
   }
}
