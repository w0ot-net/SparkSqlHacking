package spire.std;

import algebra.ring.Semiring;
import scala.Tuple22;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rg\u0001\u0003\u000f\u001e!\u0003\r\taH\u0011\t\u000f\u0005=\u0001\u0001\"\u0001\u0002\u0012!9\u0011\u0011\u0004\u0001\u0007\u0004\u0005m\u0001bBA\u0010\u0001\u0019\r\u0011\u0011\u0005\u0005\b\u0003K\u0001a1AA\u0014\u0011\u001d\tY\u0003\u0001D\u0002\u0003[Aq!!\r\u0001\r\u0007\t\u0019\u0004C\u0004\u00028\u00011\u0019!!\u000f\t\u000f\u0005u\u0002Ab\u0001\u0002@!9\u00111\t\u0001\u0007\u0004\u0005\u0015\u0003bBA%\u0001\u0019\r\u00111\n\u0005\b\u0003\u001f\u0002a1AA)\u0011\u001d\t)\u0006\u0001D\u0002\u0003/Bq!a\u0017\u0001\r\u0007\ti\u0006C\u0004\u0002b\u00011\u0019!a\u0019\t\u000f\u0005\u001d\u0004Ab\u0001\u0002j!9\u0011Q\u000e\u0001\u0007\u0004\u0005=\u0004bBA:\u0001\u0019\r\u0011Q\u000f\u0005\b\u0003s\u0002a1AA>\u0011\u001d\ty\b\u0001D\u0002\u0003\u0003Cq!!\"\u0001\r\u0007\t9\tC\u0004\u0002\f\u00021\u0019!!$\t\u000f\u0005E\u0005Ab\u0001\u0002\u0014\"9\u0011q\u0013\u0001\u0007\u0004\u0005e\u0005bBAO\u0001\u0011\u0005\u0011q\u0014\u0005\b\u0003C\u0003A\u0011AAR\u0011\u001d\ti\u000b\u0001C\u0001\u0003_Cq!!.\u0001\t\u0003\n9LA\tTK6L'/\u001b8h!J|G-^2ueIR!AH\u0010\u0002\u0007M$HMC\u0001!\u0003\u0015\u0019\b/\u001b:f+e\u0011s(\u0013'P%VC6LX1eO*l\u0007o\u001d<zy~\f)!a\u0003\u0014\u0007\u0001\u0019\u0013\u0006\u0005\u0002%O5\tQEC\u0001'\u0003\u0015\u00198-\u00197b\u0013\tASE\u0001\u0004B]f\u0014VM\u001a\t\u0004U]RdBA\u00165\u001d\ta#G\u0004\u0002.c5\taF\u0003\u00020a\u00051AH]8piz\u001a\u0001!C\u0001!\u0013\t\u0019t$A\u0004bY\u001e,'M]1\n\u0005U2\u0014a\u00029bG.\fw-\u001a\u0006\u0003g}I!\u0001O\u001d\u0003\u0011M+W.\u001b:j]\u001eT!!\u000e\u001c\u00115\u0011ZT\bS&O#R;&,\u00181dM&dwN];ywz\f\u0019!!\u0003\n\u0005q*#a\u0002+va2,'G\r\t\u0003}}b\u0001\u0001B\u0003A\u0001\t\u0007\u0011IA\u0001B#\t\u0011U\t\u0005\u0002%\u0007&\u0011A)\n\u0002\b\u001d>$\b.\u001b8h!\t!c)\u0003\u0002HK\t\u0019\u0011I\\=\u0011\u0005yJE!\u0002&\u0001\u0005\u0004\t%!\u0001\"\u0011\u0005ybE!B'\u0001\u0005\u0004\t%!A\"\u0011\u0005yzE!\u0002)\u0001\u0005\u0004\t%!\u0001#\u0011\u0005y\u0012F!B*\u0001\u0005\u0004\t%!A#\u0011\u0005y*F!\u0002,\u0001\u0005\u0004\t%!\u0001$\u0011\u0005yBF!B-\u0001\u0005\u0004\t%!A$\u0011\u0005yZF!\u0002/\u0001\u0005\u0004\t%!\u0001%\u0011\u0005yrF!B0\u0001\u0005\u0004\t%!A%\u0011\u0005y\nG!\u00022\u0001\u0005\u0004\t%!\u0001&\u0011\u0005y\"G!B3\u0001\u0005\u0004\t%!A&\u0011\u0005y:G!\u00025\u0001\u0005\u0004\t%!\u0001'\u0011\u0005yRG!B6\u0001\u0005\u0004\t%!A'\u0011\u0005yjG!\u00028\u0001\u0005\u0004\t%!\u0001(\u0011\u0005y\u0002H!B9\u0001\u0005\u0004\t%!A(\u0011\u0005y\u001aH!\u0002;\u0001\u0005\u0004\t%!\u0001)\u0011\u0005y2H!B<\u0001\u0005\u0004\t%!A)\u0011\u0005yJH!\u0002>\u0001\u0005\u0004\t%!\u0001*\u0011\u0005ybH!B?\u0001\u0005\u0004\t%!A*\u0011\u0005yzHABA\u0001\u0001\t\u0007\u0011IA\u0001U!\rq\u0014Q\u0001\u0003\u0007\u0003\u000f\u0001!\u0019A!\u0003\u0003U\u00032APA\u0006\t\u0019\ti\u0001\u0001b\u0001\u0003\n\ta+\u0001\u0004%S:LG\u000f\n\u000b\u0003\u0003'\u00012\u0001JA\u000b\u0013\r\t9\"\n\u0002\u0005+:LG/\u0001\u0006tiJ,8\r^;sKF*\"!!\b\u0011\u0007):T(\u0001\u0006tiJ,8\r^;sKJ*\"!a\t\u0011\u0007):\u0004*\u0001\u0006tiJ,8\r^;sKN*\"!!\u000b\u0011\u0007):4*\u0001\u0006tiJ,8\r^;sKR*\"!a\f\u0011\u0007):d*\u0001\u0006tiJ,8\r^;sKV*\"!!\u000e\u0011\u0007):\u0014+\u0001\u0006tiJ,8\r^;sKZ*\"!a\u000f\u0011\u0007):D+\u0001\u0006tiJ,8\r^;sK^*\"!!\u0011\u0011\u0007):t+\u0001\u0006tiJ,8\r^;sKb*\"!a\u0012\u0011\u0007):$,\u0001\u0006tiJ,8\r^;sKf*\"!!\u0014\u0011\u0007):T,A\u0006tiJ,8\r^;sKF\u0002TCAA*!\rQs\u0007Y\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0014'\u0006\u0002\u0002ZA\u0019!fN2\u0002\u0017M$(/^2ukJ,\u0017GM\u000b\u0003\u0003?\u00022AK\u001cg\u0003-\u0019HO];diV\u0014X-M\u001a\u0016\u0005\u0005\u0015\u0004c\u0001\u00168S\u0006Y1\u000f\u001e:vGR,(/Z\u00195+\t\tY\u0007E\u0002+o1\f1b\u001d;sk\u000e$XO]32kU\u0011\u0011\u0011\u000f\t\u0004U]z\u0017aC:ueV\u001cG/\u001e:fcY*\"!a\u001e\u0011\u0007):$/A\u0006tiJ,8\r^;sKF:TCAA?!\rQs'^\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0004(\u0006\u0002\u0002\u0004B\u0019!f\u000e=\u0002\u0017M$(/^2ukJ,\u0017'O\u000b\u0003\u0003\u0013\u00032AK\u001c|\u0003-\u0019HO];diV\u0014XM\r\u0019\u0016\u0005\u0005=\u0005c\u0001\u00168}\u0006Y1\u000f\u001e:vGR,(/\u001a\u001a2+\t\t)\n\u0005\u0003+o\u0005\r\u0011aC:ueV\u001cG/\u001e:feI*\"!a'\u0011\t):\u0014\u0011B\u0001\u0005u\u0016\u0014x.F\u0001;\u0003\u0011\u0001H.^:\u0015\u000bi\n)+!+\t\r\u0005\u001d\u0016\u00041\u0001;\u0003\tA\b\u0007\u0003\u0004\u0002,f\u0001\rAO\u0001\u0003qF\nQ\u0001^5nKN$RAOAY\u0003gCa!a*\u001b\u0001\u0004Q\u0004BBAV5\u0001\u0007!(A\u0002q_^$RAOA]\u0003wCa!a*\u001c\u0001\u0004Q\u0004bBAV7\u0001\u0007\u0011Q\u0018\t\u0004I\u0005}\u0016bAAaK\t\u0019\u0011J\u001c;"
)
public interface SemiringProduct22 extends Semiring {
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

   Semiring structure18();

   Semiring structure19();

   Semiring structure20();

   Semiring structure21();

   Semiring structure22();

   // $FF: synthetic method
   static Tuple22 zero$(final SemiringProduct22 $this) {
      return $this.zero();
   }

   default Tuple22 zero() {
      return new Tuple22(this.structure1().zero(), this.structure2().zero(), this.structure3().zero(), this.structure4().zero(), this.structure5().zero(), this.structure6().zero(), this.structure7().zero(), this.structure8().zero(), this.structure9().zero(), this.structure10().zero(), this.structure11().zero(), this.structure12().zero(), this.structure13().zero(), this.structure14().zero(), this.structure15().zero(), this.structure16().zero(), this.structure17().zero(), this.structure18().zero(), this.structure19().zero(), this.structure20().zero(), this.structure21().zero(), this.structure22().zero());
   }

   // $FF: synthetic method
   static Tuple22 plus$(final SemiringProduct22 $this, final Tuple22 x0, final Tuple22 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple22 plus(final Tuple22 x0, final Tuple22 x1) {
      return new Tuple22(this.structure1().plus(x0._1(), x1._1()), this.structure2().plus(x0._2(), x1._2()), this.structure3().plus(x0._3(), x1._3()), this.structure4().plus(x0._4(), x1._4()), this.structure5().plus(x0._5(), x1._5()), this.structure6().plus(x0._6(), x1._6()), this.structure7().plus(x0._7(), x1._7()), this.structure8().plus(x0._8(), x1._8()), this.structure9().plus(x0._9(), x1._9()), this.structure10().plus(x0._10(), x1._10()), this.structure11().plus(x0._11(), x1._11()), this.structure12().plus(x0._12(), x1._12()), this.structure13().plus(x0._13(), x1._13()), this.structure14().plus(x0._14(), x1._14()), this.structure15().plus(x0._15(), x1._15()), this.structure16().plus(x0._16(), x1._16()), this.structure17().plus(x0._17(), x1._17()), this.structure18().plus(x0._18(), x1._18()), this.structure19().plus(x0._19(), x1._19()), this.structure20().plus(x0._20(), x1._20()), this.structure21().plus(x0._21(), x1._21()), this.structure22().plus(x0._22(), x1._22()));
   }

   // $FF: synthetic method
   static Tuple22 times$(final SemiringProduct22 $this, final Tuple22 x0, final Tuple22 x1) {
      return $this.times(x0, x1);
   }

   default Tuple22 times(final Tuple22 x0, final Tuple22 x1) {
      return new Tuple22(this.structure1().times(x0._1(), x1._1()), this.structure2().times(x0._2(), x1._2()), this.structure3().times(x0._3(), x1._3()), this.structure4().times(x0._4(), x1._4()), this.structure5().times(x0._5(), x1._5()), this.structure6().times(x0._6(), x1._6()), this.structure7().times(x0._7(), x1._7()), this.structure8().times(x0._8(), x1._8()), this.structure9().times(x0._9(), x1._9()), this.structure10().times(x0._10(), x1._10()), this.structure11().times(x0._11(), x1._11()), this.structure12().times(x0._12(), x1._12()), this.structure13().times(x0._13(), x1._13()), this.structure14().times(x0._14(), x1._14()), this.structure15().times(x0._15(), x1._15()), this.structure16().times(x0._16(), x1._16()), this.structure17().times(x0._17(), x1._17()), this.structure18().times(x0._18(), x1._18()), this.structure19().times(x0._19(), x1._19()), this.structure20().times(x0._20(), x1._20()), this.structure21().times(x0._21(), x1._21()), this.structure22().times(x0._22(), x1._22()));
   }

   // $FF: synthetic method
   static Tuple22 pow$(final SemiringProduct22 $this, final Tuple22 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple22 pow(final Tuple22 x0, final int x1) {
      return new Tuple22(this.structure1().pow(x0._1(), x1), this.structure2().pow(x0._2(), x1), this.structure3().pow(x0._3(), x1), this.structure4().pow(x0._4(), x1), this.structure5().pow(x0._5(), x1), this.structure6().pow(x0._6(), x1), this.structure7().pow(x0._7(), x1), this.structure8().pow(x0._8(), x1), this.structure9().pow(x0._9(), x1), this.structure10().pow(x0._10(), x1), this.structure11().pow(x0._11(), x1), this.structure12().pow(x0._12(), x1), this.structure13().pow(x0._13(), x1), this.structure14().pow(x0._14(), x1), this.structure15().pow(x0._15(), x1), this.structure16().pow(x0._16(), x1), this.structure17().pow(x0._17(), x1), this.structure18().pow(x0._18(), x1), this.structure19().pow(x0._19(), x1), this.structure20().pow(x0._20(), x1), this.structure21().pow(x0._21(), x1), this.structure22().pow(x0._22(), x1));
   }

   static void $init$(final SemiringProduct22 $this) {
   }
}
