package spire.std;

import cats.kernel.Group;
import scala.Tuple16;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mc\u0001C\n\u0015!\u0003\r\tA\u0006\r\t\u000bA\u0004A\u0011A9\t\u000bU\u0004a1\u0001<\t\u000ba\u0004a1A=\t\u000bm\u0004a1\u0001?\t\u000by\u0004a1A@\t\u000f\u0005\r\u0001Ab\u0001\u0002\u0006!9\u0011\u0011\u0002\u0001\u0007\u0004\u0005-\u0001bBA\b\u0001\u0019\r\u0011\u0011\u0003\u0005\b\u0003+\u0001a1AA\f\u0011\u001d\tY\u0002\u0001D\u0002\u0003;Aq!!\t\u0001\r\u0007\t\u0019\u0003C\u0004\u0002(\u00011\u0019!!\u000b\t\u000f\u00055\u0002Ab\u0001\u00020!9\u00111\u0007\u0001\u0007\u0004\u0005U\u0002bBA\u001d\u0001\u0019\r\u00111\b\u0005\b\u0003\u007f\u0001a1AA!\u0011\u001d\t)\u0005\u0001D\u0002\u0003\u000fBq!a\u0013\u0001\t\u0003\tiE\u0001\bHe>,\b\u000f\u0015:pIV\u001cG/\r\u001c\u000b\u0005U1\u0012aA:uI*\tq#A\u0003ta&\u0014X-F\t\u001am\u0001\u001be)\u0013'P%VC6LX1eO*\u001cB\u0001\u0001\u000e!YB\u00111DH\u0007\u00029)\tQ$A\u0003tG\u0006d\u0017-\u0003\u0002 9\t1\u0011I\\=SK\u001a\u00042!\t\u00182\u001d\t\u00113F\u0004\u0002$S9\u0011A\u0005K\u0007\u0002K)\u0011aeJ\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tq#\u0003\u0002+-\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u0017.\u0003\u001d\u0001\u0018mY6bO\u0016T!A\u000b\f\n\u0005=\u0002$!B$s_V\u0004(B\u0001\u0017.!IY\"\u0007N C\u000b\"[e*\u0015+X5v\u00037MZ5\n\u0005Mb\"a\u0002+va2,\u0017G\u000e\t\u0003kYb\u0001\u0001B\u00038\u0001\t\u0007\u0001HA\u0001B#\tID\b\u0005\u0002\u001cu%\u00111\b\b\u0002\b\u001d>$\b.\u001b8h!\tYR(\u0003\u0002?9\t\u0019\u0011I\\=\u0011\u0005U\u0002E!B!\u0001\u0005\u0004A$!\u0001\"\u0011\u0005U\u001aE!\u0002#\u0001\u0005\u0004A$!A\"\u0011\u0005U2E!B$\u0001\u0005\u0004A$!\u0001#\u0011\u0005UJE!\u0002&\u0001\u0005\u0004A$!A#\u0011\u0005UbE!B'\u0001\u0005\u0004A$!\u0001$\u0011\u0005UzE!\u0002)\u0001\u0005\u0004A$!A$\u0011\u0005U\u0012F!B*\u0001\u0005\u0004A$!\u0001%\u0011\u0005U*F!\u0002,\u0001\u0005\u0004A$!A%\u0011\u0005UBF!B-\u0001\u0005\u0004A$!\u0001&\u0011\u0005UZF!\u0002/\u0001\u0005\u0004A$!A&\u0011\u0005UrF!B0\u0001\u0005\u0004A$!\u0001'\u0011\u0005U\nG!\u00022\u0001\u0005\u0004A$!A'\u0011\u0005U\"G!B3\u0001\u0005\u0004A$!\u0001(\u0011\u0005U:G!\u00025\u0001\u0005\u0004A$!A(\u0011\u0005URG!B6\u0001\u0005\u0004A$!\u0001)\u0011%5tGg\u0010\"F\u0011.s\u0015\u000bV,[;\u0002\u001cg-[\u0007\u0002)%\u0011q\u000e\u0006\u0002\u0010\u001b>tw.\u001b3Qe>$Wo\u0019;2m\u00051A%\u001b8ji\u0012\"\u0012A\u001d\t\u00037ML!\u0001\u001e\u000f\u0003\tUs\u0017\u000e^\u0001\u000bgR\u0014Xo\u0019;ve\u0016\fT#A<\u0011\u0007\u0005rC'\u0001\u0006tiJ,8\r^;sKJ*\u0012A\u001f\t\u0004C9z\u0014AC:ueV\u001cG/\u001e:fgU\tQ\u0010E\u0002\"]\t\u000b!b\u001d;sk\u000e$XO]35+\t\t\t\u0001E\u0002\"]\u0015\u000b!b\u001d;sk\u000e$XO]36+\t\t9\u0001E\u0002\"]!\u000b!b\u001d;sk\u000e$XO]37+\t\ti\u0001E\u0002\"]-\u000b!b\u001d;sk\u000e$XO]38+\t\t\u0019\u0002E\u0002\"]9\u000b!b\u001d;sk\u000e$XO]39+\t\tI\u0002E\u0002\"]E\u000b!b\u001d;sk\u000e$XO]3:+\t\ty\u0002E\u0002\"]Q\u000b1b\u001d;sk\u000e$XO]32aU\u0011\u0011Q\u0005\t\u0004C9:\u0016aC:ueV\u001cG/\u001e:fcE*\"!a\u000b\u0011\u0007\u0005r#,A\u0006tiJ,8\r^;sKF\u0012TCAA\u0019!\r\tc&X\u0001\fgR\u0014Xo\u0019;ve\u0016\f4'\u0006\u0002\u00028A\u0019\u0011E\f1\u0002\u0017M$(/^2ukJ,\u0017\u0007N\u000b\u0003\u0003{\u00012!\t\u0018d\u0003-\u0019HO];diV\u0014X-M\u001b\u0016\u0005\u0005\r\u0003cA\u0011/M\u0006Y1\u000f\u001e:vGR,(/Z\u00197+\t\tI\u0005E\u0002\"]%\fq!\u001b8wKJ\u001cX\rF\u00022\u0003\u001fBa!!\u0015\u0013\u0001\u0004\t\u0014A\u0001=1\u0001"
)
public interface GroupProduct16 extends Group, MonoidProduct16 {
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

   // $FF: synthetic method
   static Tuple16 inverse$(final GroupProduct16 $this, final Tuple16 x0) {
      return $this.inverse(x0);
   }

   default Tuple16 inverse(final Tuple16 x0) {
      return new Tuple16(this.structure1().inverse(x0._1()), this.structure2().inverse(x0._2()), this.structure3().inverse(x0._3()), this.structure4().inverse(x0._4()), this.structure5().inverse(x0._5()), this.structure6().inverse(x0._6()), this.structure7().inverse(x0._7()), this.structure8().inverse(x0._8()), this.structure9().inverse(x0._9()), this.structure10().inverse(x0._10()), this.structure11().inverse(x0._11()), this.structure12().inverse(x0._12()), this.structure13().inverse(x0._13()), this.structure14().inverse(x0._14()), this.structure15().inverse(x0._15()), this.structure16().inverse(x0._16()));
   }

   static void $init$(final GroupProduct16 $this) {
   }
}
