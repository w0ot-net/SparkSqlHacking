package spire.std;

import cats.kernel.Group;
import scala.Tuple22;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001df\u0001C\r\u001b!\u0003\r\t\u0001\b\u0010\t\u000f\u0005E\u0001\u0001\"\u0001\u0002\u0014!9\u00111\u0004\u0001\u0007\u0004\u0005u\u0001bBA\u0011\u0001\u0019\r\u00111\u0005\u0005\b\u0003O\u0001a1AA\u0015\u0011\u001d\ti\u0003\u0001D\u0002\u0003_Aq!a\r\u0001\r\u0007\t)\u0004C\u0004\u0002:\u00011\u0019!a\u000f\t\u000f\u0005}\u0002Ab\u0001\u0002B!9\u0011Q\t\u0001\u0007\u0004\u0005\u001d\u0003bBA&\u0001\u0019\r\u0011Q\n\u0005\b\u0003#\u0002a1AA*\u0011\u001d\t9\u0006\u0001D\u0002\u00033Bq!!\u0018\u0001\r\u0007\ty\u0006C\u0004\u0002d\u00011\u0019!!\u001a\t\u000f\u0005%\u0004Ab\u0001\u0002l!9\u0011q\u000e\u0001\u0007\u0004\u0005E\u0004bBA;\u0001\u0019\r\u0011q\u000f\u0005\b\u0003w\u0002a1AA?\u0011\u001d\t\t\t\u0001D\u0002\u0003\u0007Cq!a\"\u0001\r\u0007\tI\tC\u0004\u0002\u000e\u00021\u0019!a$\t\u000f\u0005M\u0005Ab\u0001\u0002\u0016\"9\u0011\u0011\u0014\u0001\u0007\u0004\u0005m\u0005bBAP\u0001\u0011\u0005\u0011\u0011\u0015\u0002\u000f\u000fJ|W\u000f\u001d)s_\u0012,8\r\u001e\u001a3\u0015\tYB$A\u0002ti\u0012T\u0011!H\u0001\u0006gBL'/Z\u000b\u0019?q2\u0015\nT(S+b[f,\u00193hU6\u00048O^=}\u007f\u0006\u00151#\u0002\u0001!M\u0005%\u0001CA\u0011%\u001b\u0005\u0011#\"A\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0015\u0012#AB!osJ+g\rE\u0002(i]r!\u0001K\u0019\u000f\u0005%zcB\u0001\u0016/\u001b\u0005Y#B\u0001\u0017.\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u000f\n\u0005Ab\u0012aB1mO\u0016\u0014'/Y\u0005\u0003eM\nq\u0001]1dW\u0006<WM\u0003\u000219%\u0011QG\u000e\u0002\u0006\u000fJ|W\u000f\u001d\u0006\u0003eM\u0002\u0012$\t\u001d;\u000b\"[e*\u0015+X5v\u00037MZ5m_J,\bp\u001f@\u0002\u0004%\u0011\u0011H\t\u0002\b)V\u0004H.\u001a\u001a3!\tYD\b\u0004\u0001\u0005\u000bu\u0002!\u0019\u0001 \u0003\u0003\u0005\u000b\"a\u0010\"\u0011\u0005\u0005\u0002\u0015BA!#\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!I\"\n\u0005\u0011\u0013#aA!osB\u00111H\u0012\u0003\u0006\u000f\u0002\u0011\rA\u0010\u0002\u0002\u0005B\u00111(\u0013\u0003\u0006\u0015\u0002\u0011\rA\u0010\u0002\u0002\u0007B\u00111\b\u0014\u0003\u0006\u001b\u0002\u0011\rA\u0010\u0002\u0002\tB\u00111h\u0014\u0003\u0006!\u0002\u0011\rA\u0010\u0002\u0002\u000bB\u00111H\u0015\u0003\u0006'\u0002\u0011\rA\u0010\u0002\u0002\rB\u00111(\u0016\u0003\u0006-\u0002\u0011\rA\u0010\u0002\u0002\u000fB\u00111\b\u0017\u0003\u00063\u0002\u0011\rA\u0010\u0002\u0002\u0011B\u00111h\u0017\u0003\u00069\u0002\u0011\rA\u0010\u0002\u0002\u0013B\u00111H\u0018\u0003\u0006?\u0002\u0011\rA\u0010\u0002\u0002\u0015B\u00111(\u0019\u0003\u0006E\u0002\u0011\rA\u0010\u0002\u0002\u0017B\u00111\b\u001a\u0003\u0006K\u0002\u0011\rA\u0010\u0002\u0002\u0019B\u00111h\u001a\u0003\u0006Q\u0002\u0011\rA\u0010\u0002\u0002\u001bB\u00111H\u001b\u0003\u0006W\u0002\u0011\rA\u0010\u0002\u0002\u001dB\u00111(\u001c\u0003\u0006]\u0002\u0011\rA\u0010\u0002\u0002\u001fB\u00111\b\u001d\u0003\u0006c\u0002\u0011\rA\u0010\u0002\u0002!B\u00111h\u001d\u0003\u0006i\u0002\u0011\rA\u0010\u0002\u0002#B\u00111H\u001e\u0003\u0006o\u0002\u0011\rA\u0010\u0002\u0002%B\u00111(\u001f\u0003\u0006u\u0002\u0011\rA\u0010\u0002\u0002'B\u00111\b \u0003\u0006{\u0002\u0011\rA\u0010\u0002\u0002)B\u00111h \u0003\u0007\u0003\u0003\u0001!\u0019\u0001 \u0003\u0003U\u00032aOA\u0003\t\u0019\t9\u0001\u0001b\u0001}\t\ta\u000bE\u000e\u0002\f\u00055!(\u0012%L\u001dF#vKW/aG\u001aLGn\u001c:vqnt\u00181A\u0007\u00025%\u0019\u0011q\u0002\u000e\u0003\u001f5{gn\\5e!J|G-^2ueI\na\u0001J5oSR$CCAA\u000b!\r\t\u0013qC\u0005\u0004\u00033\u0011#\u0001B+oSR\f!b\u001d;sk\u000e$XO]32+\t\ty\u0002E\u0002(ii\n!b\u001d;sk\u000e$XO]33+\t\t)\u0003E\u0002(i\u0015\u000b!b\u001d;sk\u000e$XO]34+\t\tY\u0003E\u0002(i!\u000b!b\u001d;sk\u000e$XO]35+\t\t\t\u0004E\u0002(i-\u000b!b\u001d;sk\u000e$XO]36+\t\t9\u0004E\u0002(i9\u000b!b\u001d;sk\u000e$XO]37+\t\ti\u0004E\u0002(iE\u000b!b\u001d;sk\u000e$XO]38+\t\t\u0019\u0005E\u0002(iQ\u000b!b\u001d;sk\u000e$XO]39+\t\tI\u0005E\u0002(i]\u000b!b\u001d;sk\u000e$XO]3:+\t\ty\u0005E\u0002(ii\u000b1b\u001d;sk\u000e$XO]32aU\u0011\u0011Q\u000b\t\u0004OQj\u0016aC:ueV\u001cG/\u001e:fcE*\"!a\u0017\u0011\u0007\u001d\"\u0004-A\u0006tiJ,8\r^;sKF\u0012TCAA1!\r9CgY\u0001\fgR\u0014Xo\u0019;ve\u0016\f4'\u0006\u0002\u0002hA\u0019q\u0005\u000e4\u0002\u0017M$(/^2ukJ,\u0017\u0007N\u000b\u0003\u0003[\u00022a\n\u001bj\u0003-\u0019HO];diV\u0014X-M\u001b\u0016\u0005\u0005M\u0004cA\u00145Y\u0006Y1\u000f\u001e:vGR,(/Z\u00197+\t\tI\bE\u0002(i=\f1b\u001d;sk\u000e$XO]32oU\u0011\u0011q\u0010\t\u0004OQ\u0012\u0018aC:ueV\u001cG/\u001e:fca*\"!!\"\u0011\u0007\u001d\"T/A\u0006tiJ,8\r^;sKFJTCAAF!\r9C\u0007_\u0001\fgR\u0014Xo\u0019;ve\u0016\u0014\u0004'\u0006\u0002\u0002\u0012B\u0019q\u0005N>\u0002\u0017M$(/^2ukJ,''M\u000b\u0003\u0003/\u00032a\n\u001b\u007f\u0003-\u0019HO];diV\u0014XM\r\u001a\u0016\u0005\u0005u\u0005\u0003B\u00145\u0003\u0007\tq!\u001b8wKJ\u001cX\rF\u00028\u0003GCa!!*\u0019\u0001\u00049\u0014A\u0001=1\u0001"
)
public interface GroupProduct22 extends Group, MonoidProduct22 {
   Group structure1();

   Group structure2();

   Group structure3();

   Group structure4();

   Group structure5();

   Group structure6();

   Group structure7();

   Group structure8();

   Group structure9();

   Group structure10();

   Group structure11();

   Group structure12();

   Group structure13();

   Group structure14();

   Group structure15();

   Group structure16();

   Group structure17();

   Group structure18();

   Group structure19();

   Group structure20();

   Group structure21();

   Group structure22();

   // $FF: synthetic method
   static Tuple22 inverse$(final GroupProduct22 $this, final Tuple22 x0) {
      return $this.inverse(x0);
   }

   default Tuple22 inverse(final Tuple22 x0) {
      return new Tuple22(this.structure1().inverse(x0._1()), this.structure2().inverse(x0._2()), this.structure3().inverse(x0._3()), this.structure4().inverse(x0._4()), this.structure5().inverse(x0._5()), this.structure6().inverse(x0._6()), this.structure7().inverse(x0._7()), this.structure8().inverse(x0._8()), this.structure9().inverse(x0._9()), this.structure10().inverse(x0._10()), this.structure11().inverse(x0._11()), this.structure12().inverse(x0._12()), this.structure13().inverse(x0._13()), this.structure14().inverse(x0._14()), this.structure15().inverse(x0._15()), this.structure16().inverse(x0._16()), this.structure17().inverse(x0._17()), this.structure18().inverse(x0._18()), this.structure19().inverse(x0._19()), this.structure20().inverse(x0._20()), this.structure21().inverse(x0._21()), this.structure22().inverse(x0._22()));
   }

   static void $init$(final GroupProduct22 $this) {
   }
}
