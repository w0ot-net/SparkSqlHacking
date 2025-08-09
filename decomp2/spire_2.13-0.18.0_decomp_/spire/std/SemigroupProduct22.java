package spire.std;

import cats.kernel.Semigroup;
import scala.Tuple22;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rf\u0001C\r\u001b!\u0003\r\t\u0001\b\u0010\t\u000f\u0005%\u0001\u0001\"\u0001\u0002\f!9\u00111\u0003\u0001\u0007\u0004\u0005U\u0001bBA\r\u0001\u0019\r\u00111\u0004\u0005\b\u0003?\u0001a1AA\u0011\u0011\u001d\t)\u0003\u0001D\u0002\u0003OAq!a\u000b\u0001\r\u0007\ti\u0003C\u0004\u00022\u00011\u0019!a\r\t\u000f\u0005]\u0002Ab\u0001\u0002:!9\u0011Q\b\u0001\u0007\u0004\u0005}\u0002bBA\"\u0001\u0019\r\u0011Q\t\u0005\b\u0003\u0013\u0002a1AA&\u0011\u001d\ty\u0005\u0001D\u0002\u0003#Bq!!\u0016\u0001\r\u0007\t9\u0006C\u0004\u0002\\\u00011\u0019!!\u0018\t\u000f\u0005\u0005\u0004Ab\u0001\u0002d!9\u0011q\r\u0001\u0007\u0004\u0005%\u0004bBA7\u0001\u0019\r\u0011q\u000e\u0005\b\u0003g\u0002a1AA;\u0011\u001d\tI\b\u0001D\u0002\u0003wBq!a \u0001\r\u0007\t\t\tC\u0004\u0002\u0006\u00021\u0019!a\"\t\u000f\u0005-\u0005Ab\u0001\u0002\u000e\"9\u0011\u0011\u0013\u0001\u0007\u0004\u0005M\u0005bBAL\u0001\u0011\u0005\u0011\u0011\u0014\u0002\u0013'\u0016l\u0017n\u001a:pkB\u0004&o\u001c3vGR\u0014$G\u0003\u0002\u001c9\u0005\u00191\u000f\u001e3\u000b\u0003u\tQa\u001d9je\u0016,\u0002d\b\u001fG\u00132{%+\u0016-\\=\u0006$wM[7qgZLHp`A\u0003'\r\u0001\u0001E\n\t\u0003C\u0011j\u0011A\t\u0006\u0002G\u0005)1oY1mC&\u0011QE\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0007\u001d\"tG\u0004\u0002)c9\u0011\u0011f\f\b\u0003U9j\u0011a\u000b\u0006\u0003Y5\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002;%\u0011\u0001\u0007H\u0001\bC2<WM\u0019:b\u0013\t\u00114'A\u0004qC\u000e\\\u0017mZ3\u000b\u0005Ab\u0012BA\u001b7\u0005%\u0019V-\\5he>,\bO\u0003\u00023gAI\u0012\u0005\u000f\u001eF\u0011.s\u0015\u000bV,[;\u0002\u001cg-\u001b7peVD8P`A\u0002\u0013\tI$EA\u0004UkBdWM\r\u001a\u0011\u0005mbD\u0002\u0001\u0003\u0006{\u0001\u0011\rA\u0010\u0002\u0002\u0003F\u0011qH\u0011\t\u0003C\u0001K!!\u0011\u0012\u0003\u000f9{G\u000f[5oOB\u0011\u0011eQ\u0005\u0003\t\n\u00121!\u00118z!\tYd\tB\u0003H\u0001\t\u0007aHA\u0001C!\tY\u0014\nB\u0003K\u0001\t\u0007aHA\u0001D!\tYD\nB\u0003N\u0001\t\u0007aHA\u0001E!\tYt\nB\u0003Q\u0001\t\u0007aHA\u0001F!\tY$\u000bB\u0003T\u0001\t\u0007aHA\u0001G!\tYT\u000bB\u0003W\u0001\t\u0007aHA\u0001H!\tY\u0004\fB\u0003Z\u0001\t\u0007aHA\u0001I!\tY4\fB\u0003]\u0001\t\u0007aHA\u0001J!\tYd\fB\u0003`\u0001\t\u0007aHA\u0001K!\tY\u0014\rB\u0003c\u0001\t\u0007aHA\u0001L!\tYD\rB\u0003f\u0001\t\u0007aHA\u0001M!\tYt\rB\u0003i\u0001\t\u0007aHA\u0001N!\tY$\u000eB\u0003l\u0001\t\u0007aHA\u0001O!\tYT\u000eB\u0003o\u0001\t\u0007aHA\u0001P!\tY\u0004\u000fB\u0003r\u0001\t\u0007aHA\u0001Q!\tY4\u000fB\u0003u\u0001\t\u0007aHA\u0001R!\tYd\u000fB\u0003x\u0001\t\u0007aHA\u0001S!\tY\u0014\u0010B\u0003{\u0001\t\u0007aHA\u0001T!\tYD\u0010B\u0003~\u0001\t\u0007aHA\u0001U!\tYt\u0010\u0002\u0004\u0002\u0002\u0001\u0011\rA\u0010\u0002\u0002+B\u00191(!\u0002\u0005\r\u0005\u001d\u0001A1\u0001?\u0005\u00051\u0016A\u0002\u0013j]&$H\u0005\u0006\u0002\u0002\u000eA\u0019\u0011%a\u0004\n\u0007\u0005E!E\u0001\u0003V]&$\u0018AC:ueV\u001cG/\u001e:fcU\u0011\u0011q\u0003\t\u0004OQR\u0014AC:ueV\u001cG/\u001e:feU\u0011\u0011Q\u0004\t\u0004OQ*\u0015AC:ueV\u001cG/\u001e:fgU\u0011\u00111\u0005\t\u0004OQB\u0015AC:ueV\u001cG/\u001e:fiU\u0011\u0011\u0011\u0006\t\u0004OQZ\u0015AC:ueV\u001cG/\u001e:fkU\u0011\u0011q\u0006\t\u0004OQr\u0015AC:ueV\u001cG/\u001e:fmU\u0011\u0011Q\u0007\t\u0004OQ\n\u0016AC:ueV\u001cG/\u001e:foU\u0011\u00111\b\t\u0004OQ\"\u0016AC:ueV\u001cG/\u001e:fqU\u0011\u0011\u0011\t\t\u0004OQ:\u0016AC:ueV\u001cG/\u001e:fsU\u0011\u0011q\t\t\u0004OQR\u0016aC:ueV\u001cG/\u001e:fcA*\"!!\u0014\u0011\u0007\u001d\"T,A\u0006tiJ,8\r^;sKF\nTCAA*!\r9C\u0007Y\u0001\fgR\u0014Xo\u0019;ve\u0016\f$'\u0006\u0002\u0002ZA\u0019q\u0005N2\u0002\u0017M$(/^2ukJ,\u0017gM\u000b\u0003\u0003?\u00022a\n\u001bg\u0003-\u0019HO];diV\u0014X-\r\u001b\u0016\u0005\u0005\u0015\u0004cA\u00145S\u0006Y1\u000f\u001e:vGR,(/Z\u00196+\t\tY\u0007E\u0002(i1\f1b\u001d;sk\u000e$XO]32mU\u0011\u0011\u0011\u000f\t\u0004OQz\u0017aC:ueV\u001cG/\u001e:fc]*\"!a\u001e\u0011\u0007\u001d\"$/A\u0006tiJ,8\r^;sKFBTCAA?!\r9C'^\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0014(\u0006\u0002\u0002\u0004B\u0019q\u0005\u000e=\u0002\u0017M$(/^2ukJ,'\u0007M\u000b\u0003\u0003\u0013\u00032a\n\u001b|\u0003-\u0019HO];diV\u0014XMM\u0019\u0016\u0005\u0005=\u0005cA\u00145}\u0006Y1\u000f\u001e:vGR,(/\u001a\u001a3+\t\t)\n\u0005\u0003(i\u0005\r\u0011aB2p[\nLg.\u001a\u000b\u0006o\u0005m\u0015q\u0014\u0005\u0007\u0003;C\u0002\u0019A\u001c\u0002\u0005a\u0004\u0004BBAQ1\u0001\u0007q'\u0001\u0002yc\u0001"
)
public interface SemigroupProduct22 extends Semigroup {
   Semigroup structure1();

   Semigroup structure2();

   Semigroup structure3();

   Semigroup structure4();

   Semigroup structure5();

   Semigroup structure6();

   Semigroup structure7();

   Semigroup structure8();

   Semigroup structure9();

   Semigroup structure10();

   Semigroup structure11();

   Semigroup structure12();

   Semigroup structure13();

   Semigroup structure14();

   Semigroup structure15();

   Semigroup structure16();

   Semigroup structure17();

   Semigroup structure18();

   Semigroup structure19();

   Semigroup structure20();

   Semigroup structure21();

   Semigroup structure22();

   // $FF: synthetic method
   static Tuple22 combine$(final SemigroupProduct22 $this, final Tuple22 x0, final Tuple22 x1) {
      return $this.combine(x0, x1);
   }

   default Tuple22 combine(final Tuple22 x0, final Tuple22 x1) {
      return new Tuple22(this.structure1().combine(x0._1(), x1._1()), this.structure2().combine(x0._2(), x1._2()), this.structure3().combine(x0._3(), x1._3()), this.structure4().combine(x0._4(), x1._4()), this.structure5().combine(x0._5(), x1._5()), this.structure6().combine(x0._6(), x1._6()), this.structure7().combine(x0._7(), x1._7()), this.structure8().combine(x0._8(), x1._8()), this.structure9().combine(x0._9(), x1._9()), this.structure10().combine(x0._10(), x1._10()), this.structure11().combine(x0._11(), x1._11()), this.structure12().combine(x0._12(), x1._12()), this.structure13().combine(x0._13(), x1._13()), this.structure14().combine(x0._14(), x1._14()), this.structure15().combine(x0._15(), x1._15()), this.structure16().combine(x0._16(), x1._16()), this.structure17().combine(x0._17(), x1._17()), this.structure18().combine(x0._18(), x1._18()), this.structure19().combine(x0._19(), x1._19()), this.structure20().combine(x0._20(), x1._20()), this.structure21().combine(x0._21(), x1._21()), this.structure22().combine(x0._22(), x1._22()));
   }

   static void $init$(final SemigroupProduct22 $this) {
   }
}
