package spire.std;

import algebra.ring.Semiring;
import scala.Tuple17;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ud\u0001C\f\u0019!\u0003\r\tA\u0007\u000f\t\u000bM\u0004A\u0011\u0001;\t\u000ba\u0004a1A=\t\u000bm\u0004a1\u0001?\t\u000by\u0004a1A@\t\u000f\u0005\r\u0001Ab\u0001\u0002\u0006!9\u0011\u0011\u0002\u0001\u0007\u0004\u0005-\u0001bBA\b\u0001\u0019\r\u0011\u0011\u0003\u0005\b\u0003+\u0001a1AA\f\u0011\u001d\tY\u0002\u0001D\u0002\u0003;Aq!!\t\u0001\r\u0007\t\u0019\u0003C\u0004\u0002(\u00011\u0019!!\u000b\t\u000f\u00055\u0002Ab\u0001\u00020!9\u00111\u0007\u0001\u0007\u0004\u0005U\u0002bBA\u001d\u0001\u0019\r\u00111\b\u0005\b\u0003\u007f\u0001a1AA!\u0011\u001d\t)\u0005\u0001D\u0002\u0003\u000fBq!a\u0013\u0001\r\u0007\ti\u0005C\u0004\u0002R\u00011\u0019!a\u0015\t\u000f\u0005]\u0003\u0001\"\u0001\u0002Z!9\u00111\f\u0001\u0005\u0002\u0005u\u0003bBA4\u0001\u0011\u0005\u0011\u0011\u000e\u0005\b\u0003_\u0002A\u0011IA9\u0005E\u0019V-\\5sS:<\u0007K]8ek\u000e$\u0018g\u000e\u0006\u00033i\t1a\u001d;e\u0015\u0005Y\u0012!B:qSJ,WCE\u000f;\t\u001eSU\nU*W3r{&-\u001a5l]F\u001c2\u0001\u0001\u0010%!\ty\"%D\u0001!\u0015\u0005\t\u0013!B:dC2\f\u0017BA\u0012!\u0005\u0019\te.\u001f*fMB\u0019QEM\u001b\u000f\u0005\u0019zcBA\u0014.\u001d\tAC&D\u0001*\u0015\tQ3&\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005Y\u0012B\u0001\u0018\u001b\u0003\u001d\tGnZ3ce\u0006L!\u0001M\u0019\u0002\u000fA\f7m[1hK*\u0011aFG\u0005\u0003gQ\u0012\u0001bU3nSJLgn\u001a\u0006\u0003aE\u00022c\b\u001c9\u0007\u001aKEj\u0014*V1ns\u0016\rZ4k[BL!a\u000e\u0011\u0003\u000fQ+\b\u000f\\32oA\u0011\u0011H\u000f\u0007\u0001\t\u0015Y\u0004A1\u0001=\u0005\u0005\t\u0015CA\u001fA!\tyb(\u0003\u0002@A\t9aj\u001c;iS:<\u0007CA\u0010B\u0013\t\u0011\u0005EA\u0002B]f\u0004\"!\u000f#\u0005\u000b\u0015\u0003!\u0019\u0001\u001f\u0003\u0003\t\u0003\"!O$\u0005\u000b!\u0003!\u0019\u0001\u001f\u0003\u0003\r\u0003\"!\u000f&\u0005\u000b-\u0003!\u0019\u0001\u001f\u0003\u0003\u0011\u0003\"!O'\u0005\u000b9\u0003!\u0019\u0001\u001f\u0003\u0003\u0015\u0003\"!\u000f)\u0005\u000bE\u0003!\u0019\u0001\u001f\u0003\u0003\u0019\u0003\"!O*\u0005\u000bQ\u0003!\u0019\u0001\u001f\u0003\u0003\u001d\u0003\"!\u000f,\u0005\u000b]\u0003!\u0019\u0001\u001f\u0003\u0003!\u0003\"!O-\u0005\u000bi\u0003!\u0019\u0001\u001f\u0003\u0003%\u0003\"!\u000f/\u0005\u000bu\u0003!\u0019\u0001\u001f\u0003\u0003)\u0003\"!O0\u0005\u000b\u0001\u0004!\u0019\u0001\u001f\u0003\u0003-\u0003\"!\u000f2\u0005\u000b\r\u0004!\u0019\u0001\u001f\u0003\u00031\u0003\"!O3\u0005\u000b\u0019\u0004!\u0019\u0001\u001f\u0003\u00035\u0003\"!\u000f5\u0005\u000b%\u0004!\u0019\u0001\u001f\u0003\u00039\u0003\"!O6\u0005\u000b1\u0004!\u0019\u0001\u001f\u0003\u0003=\u0003\"!\u000f8\u0005\u000b=\u0004!\u0019\u0001\u001f\u0003\u0003A\u0003\"!O9\u0005\u000bI\u0004!\u0019\u0001\u001f\u0003\u0003E\u000ba\u0001J5oSR$C#A;\u0011\u0005}1\u0018BA<!\u0005\u0011)f.\u001b;\u0002\u0015M$(/^2ukJ,\u0017'F\u0001{!\r)#\u0007O\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u0014T#A?\u0011\u0007\u0015\u00124)\u0001\u0006tiJ,8\r^;sKN*\"!!\u0001\u0011\u0007\u0015\u0012d)\u0001\u0006tiJ,8\r^;sKR*\"!a\u0002\u0011\u0007\u0015\u0012\u0014*\u0001\u0006tiJ,8\r^;sKV*\"!!\u0004\u0011\u0007\u0015\u0012D*\u0001\u0006tiJ,8\r^;sKZ*\"!a\u0005\u0011\u0007\u0015\u0012t*\u0001\u0006tiJ,8\r^;sK^*\"!!\u0007\u0011\u0007\u0015\u0012$+\u0001\u0006tiJ,8\r^;sKb*\"!a\b\u0011\u0007\u0015\u0012T+\u0001\u0006tiJ,8\r^;sKf*\"!!\n\u0011\u0007\u0015\u0012\u0004,A\u0006tiJ,8\r^;sKF\u0002TCAA\u0016!\r)#gW\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0014'\u0006\u0002\u00022A\u0019QE\r0\u0002\u0017M$(/^2ukJ,\u0017GM\u000b\u0003\u0003o\u00012!\n\u001ab\u0003-\u0019HO];diV\u0014X-M\u001a\u0016\u0005\u0005u\u0002cA\u00133I\u0006Y1\u000f\u001e:vGR,(/Z\u00195+\t\t\u0019\u0005E\u0002&e\u001d\f1b\u001d;sk\u000e$XO]32kU\u0011\u0011\u0011\n\t\u0004KIR\u0017aC:ueV\u001cG/\u001e:fcY*\"!a\u0014\u0011\u0007\u0015\u0012T.A\u0006tiJ,8\r^;sKF:TCAA+!\r)#\u0007]\u0001\u0005u\u0016\u0014x.F\u00016\u0003\u0011\u0001H.^:\u0015\u000bU\ny&a\u0019\t\r\u0005\u0005D\u00031\u00016\u0003\tA\b\u0007\u0003\u0004\u0002fQ\u0001\r!N\u0001\u0003qF\nQ\u0001^5nKN$R!NA6\u0003[Ba!!\u0019\u0016\u0001\u0004)\u0004BBA3+\u0001\u0007Q'A\u0002q_^$R!NA:\u0003kBa!!\u0019\u0017\u0001\u0004)\u0004bBA3-\u0001\u0007\u0011q\u000f\t\u0004?\u0005e\u0014bAA>A\t\u0019\u0011J\u001c;"
)
public interface SemiringProduct17 extends Semiring {
   Semiring structure1();

   Semiring structure2();

   Semiring structure3();

   Semiring structure4();

   Semiring structure5();

   Semiring structure6();

   Semiring structure7();

   Semiring structure8();

   Semiring structure9();

   Semiring structure10();

   Semiring structure11();

   Semiring structure12();

   Semiring structure13();

   Semiring structure14();

   Semiring structure15();

   Semiring structure16();

   Semiring structure17();

   // $FF: synthetic method
   static Tuple17 zero$(final SemiringProduct17 $this) {
      return $this.zero();
   }

   default Tuple17 zero() {
      return new Tuple17(this.structure1().zero(), this.structure2().zero(), this.structure3().zero(), this.structure4().zero(), this.structure5().zero(), this.structure6().zero(), this.structure7().zero(), this.structure8().zero(), this.structure9().zero(), this.structure10().zero(), this.structure11().zero(), this.structure12().zero(), this.structure13().zero(), this.structure14().zero(), this.structure15().zero(), this.structure16().zero(), this.structure17().zero());
   }

   // $FF: synthetic method
   static Tuple17 plus$(final SemiringProduct17 $this, final Tuple17 x0, final Tuple17 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple17 plus(final Tuple17 x0, final Tuple17 x1) {
      return new Tuple17(this.structure1().plus(x0._1(), x1._1()), this.structure2().plus(x0._2(), x1._2()), this.structure3().plus(x0._3(), x1._3()), this.structure4().plus(x0._4(), x1._4()), this.structure5().plus(x0._5(), x1._5()), this.structure6().plus(x0._6(), x1._6()), this.structure7().plus(x0._7(), x1._7()), this.structure8().plus(x0._8(), x1._8()), this.structure9().plus(x0._9(), x1._9()), this.structure10().plus(x0._10(), x1._10()), this.structure11().plus(x0._11(), x1._11()), this.structure12().plus(x0._12(), x1._12()), this.structure13().plus(x0._13(), x1._13()), this.structure14().plus(x0._14(), x1._14()), this.structure15().plus(x0._15(), x1._15()), this.structure16().plus(x0._16(), x1._16()), this.structure17().plus(x0._17(), x1._17()));
   }

   // $FF: synthetic method
   static Tuple17 times$(final SemiringProduct17 $this, final Tuple17 x0, final Tuple17 x1) {
      return $this.times(x0, x1);
   }

   default Tuple17 times(final Tuple17 x0, final Tuple17 x1) {
      return new Tuple17(this.structure1().times(x0._1(), x1._1()), this.structure2().times(x0._2(), x1._2()), this.structure3().times(x0._3(), x1._3()), this.structure4().times(x0._4(), x1._4()), this.structure5().times(x0._5(), x1._5()), this.structure6().times(x0._6(), x1._6()), this.structure7().times(x0._7(), x1._7()), this.structure8().times(x0._8(), x1._8()), this.structure9().times(x0._9(), x1._9()), this.structure10().times(x0._10(), x1._10()), this.structure11().times(x0._11(), x1._11()), this.structure12().times(x0._12(), x1._12()), this.structure13().times(x0._13(), x1._13()), this.structure14().times(x0._14(), x1._14()), this.structure15().times(x0._15(), x1._15()), this.structure16().times(x0._16(), x1._16()), this.structure17().times(x0._17(), x1._17()));
   }

   // $FF: synthetic method
   static Tuple17 pow$(final SemiringProduct17 $this, final Tuple17 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple17 pow(final Tuple17 x0, final int x1) {
      return new Tuple17(this.structure1().pow(x0._1(), x1), this.structure2().pow(x0._2(), x1), this.structure3().pow(x0._3(), x1), this.structure4().pow(x0._4(), x1), this.structure5().pow(x0._5(), x1), this.structure6().pow(x0._6(), x1), this.structure7().pow(x0._7(), x1), this.structure8().pow(x0._8(), x1), this.structure9().pow(x0._9(), x1), this.structure10().pow(x0._10(), x1), this.structure11().pow(x0._11(), x1), this.structure12().pow(x0._12(), x1), this.structure13().pow(x0._13(), x1), this.structure14().pow(x0._14(), x1), this.structure15().pow(x0._15(), x1), this.structure16().pow(x0._16(), x1), this.structure17().pow(x0._17(), x1));
   }

   static void $init$(final SemiringProduct17 $this) {
   }
}
