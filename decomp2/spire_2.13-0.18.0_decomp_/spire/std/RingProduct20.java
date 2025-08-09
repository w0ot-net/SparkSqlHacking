package spire.std;

import algebra.ring.Ring;
import scala.Tuple20;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]e\u0001\u0003\r\u001a!\u0003\r\taG\u000f\t\u000f\u0005\r\u0001\u0001\"\u0001\u0002\u0006!9\u0011Q\u0002\u0001\u0007\u0004\u0005=\u0001bBA\n\u0001\u0019\r\u0011Q\u0003\u0005\b\u00033\u0001a1AA\u000e\u0011\u001d\ty\u0002\u0001D\u0002\u0003CAq!!\n\u0001\r\u0007\t9\u0003C\u0004\u0002,\u00011\u0019!!\f\t\u000f\u0005E\u0002Ab\u0001\u00024!9\u0011q\u0007\u0001\u0007\u0004\u0005e\u0002bBA\u001f\u0001\u0019\r\u0011q\b\u0005\b\u0003\u0007\u0002a1AA#\u0011\u001d\tI\u0005\u0001D\u0002\u0003\u0017Bq!a\u0014\u0001\r\u0007\t\t\u0006C\u0004\u0002V\u00011\u0019!a\u0016\t\u000f\u0005m\u0003Ab\u0001\u0002^!9\u0011\u0011\r\u0001\u0007\u0004\u0005\r\u0004bBA4\u0001\u0019\r\u0011\u0011\u000e\u0005\b\u0003[\u0002a1AA8\u0011\u001d\t\u0019\b\u0001D\u0002\u0003kBq!!\u001f\u0001\r\u0007\tY\bC\u0004\u0002\u0000\u00011\u0019!!!\t\u000f\u0005\u0015\u0005\u0001\"\u0011\u0002\b\"9\u00111\u0013\u0001\u0005\u0002\u0005U%!\u0004*j]\u001e\u0004&o\u001c3vGR\u0014\u0004G\u0003\u0002\u001b7\u0005\u00191\u000f\u001e3\u000b\u0003q\tQa\u001d9je\u0016,RCH\u001eF\u0011.s\u0015\u000bV,[;\u0002\u001cg-\u001b7peVD8p\u0005\u0003\u0001?\u0015j\bC\u0001\u0011$\u001b\u0005\t#\"\u0001\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0011\n#AB!osJ+g\rE\u0002'gYr!a\n\u0019\u000f\u0005!rcBA\u0015.\u001b\u0005Q#BA\u0016-\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\u000f\n\u0005=Z\u0012aB1mO\u0016\u0014'/Y\u0005\u0003cI\nq\u0001]1dW\u0006<WM\u0003\u000207%\u0011A'\u000e\u0002\u0005%&twM\u0003\u00022eA1\u0002eN\u001dE\u000f*k\u0005k\u0015,Z9~\u0013W\r[6ocR<(0\u0003\u00029C\t9A+\u001e9mKJ\u0002\u0004C\u0001\u001e<\u0019\u0001!Q\u0001\u0010\u0001C\u0002u\u0012\u0011!Q\t\u0003}\u0005\u0003\"\u0001I \n\u0005\u0001\u000b#a\u0002(pi\"Lgn\u001a\t\u0003A\tK!aQ\u0011\u0003\u0007\u0005s\u0017\u0010\u0005\u0002;\u000b\u0012)a\t\u0001b\u0001{\t\t!\t\u0005\u0002;\u0011\u0012)\u0011\n\u0001b\u0001{\t\t1\t\u0005\u0002;\u0017\u0012)A\n\u0001b\u0001{\t\tA\t\u0005\u0002;\u001d\u0012)q\n\u0001b\u0001{\t\tQ\t\u0005\u0002;#\u0012)!\u000b\u0001b\u0001{\t\ta\t\u0005\u0002;)\u0012)Q\u000b\u0001b\u0001{\t\tq\t\u0005\u0002;/\u0012)\u0001\f\u0001b\u0001{\t\t\u0001\n\u0005\u0002;5\u0012)1\f\u0001b\u0001{\t\t\u0011\n\u0005\u0002;;\u0012)a\f\u0001b\u0001{\t\t!\n\u0005\u0002;A\u0012)\u0011\r\u0001b\u0001{\t\t1\n\u0005\u0002;G\u0012)A\r\u0001b\u0001{\t\tA\n\u0005\u0002;M\u0012)q\r\u0001b\u0001{\t\tQ\n\u0005\u0002;S\u0012)!\u000e\u0001b\u0001{\t\ta\n\u0005\u0002;Y\u0012)Q\u000e\u0001b\u0001{\t\tq\n\u0005\u0002;_\u0012)\u0001\u000f\u0001b\u0001{\t\t\u0001\u000b\u0005\u0002;e\u0012)1\u000f\u0001b\u0001{\t\t\u0011\u000b\u0005\u0002;k\u0012)a\u000f\u0001b\u0001{\t\t!\u000b\u0005\u0002;q\u0012)\u0011\u0010\u0001b\u0001{\t\t1\u000b\u0005\u0002;w\u0012)A\u0010\u0001b\u0001{\t\tA\u000b\u0005\f\u007f\u007ff\"uIS'Q'ZKFl\u00182fQ.t\u0017\u000f^<{\u001b\u0005I\u0012bAA\u00013\ta!K\\4Qe>$Wo\u0019;3a\u00051A%\u001b8ji\u0012\"\"!a\u0002\u0011\u0007\u0001\nI!C\u0002\u0002\f\u0005\u0012A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0005\u0005E\u0001c\u0001\u00144s\u0005Q1\u000f\u001e:vGR,(/\u001a\u001a\u0016\u0005\u0005]\u0001c\u0001\u00144\t\u0006Q1\u000f\u001e:vGR,(/Z\u001a\u0016\u0005\u0005u\u0001c\u0001\u00144\u000f\u0006Q1\u000f\u001e:vGR,(/\u001a\u001b\u0016\u0005\u0005\r\u0002c\u0001\u00144\u0015\u0006Q1\u000f\u001e:vGR,(/Z\u001b\u0016\u0005\u0005%\u0002c\u0001\u00144\u001b\u0006Q1\u000f\u001e:vGR,(/\u001a\u001c\u0016\u0005\u0005=\u0002c\u0001\u00144!\u0006Q1\u000f\u001e:vGR,(/Z\u001c\u0016\u0005\u0005U\u0002c\u0001\u00144'\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0005\u0005m\u0002c\u0001\u00144-\u0006Q1\u000f\u001e:vGR,(/Z\u001d\u0016\u0005\u0005\u0005\u0003c\u0001\u001443\u0006Y1\u000f\u001e:vGR,(/Z\u00191+\t\t9\u0005E\u0002'gq\u000b1b\u001d;sk\u000e$XO]32cU\u0011\u0011Q\n\t\u0004MMz\u0016aC:ueV\u001cG/\u001e:fcI*\"!a\u0015\u0011\u0007\u0019\u001a$-A\u0006tiJ,8\r^;sKF\u001aTCAA-!\r13'Z\u0001\fgR\u0014Xo\u0019;ve\u0016\fD'\u0006\u0002\u0002`A\u0019ae\r5\u0002\u0017M$(/^2ukJ,\u0017'N\u000b\u0003\u0003K\u00022AJ\u001al\u0003-\u0019HO];diV\u0014X-\r\u001c\u0016\u0005\u0005-\u0004c\u0001\u00144]\u0006Y1\u000f\u001e:vGR,(/Z\u00198+\t\t\t\bE\u0002'gE\f1b\u001d;sk\u000e$XO]32qU\u0011\u0011q\u000f\t\u0004MM\"\u0018aC:ueV\u001cG/\u001e:fce*\"!! \u0011\u0007\u0019\u001at/A\u0006tiJ,8\r^;sKJ\u0002TCAAB!\r13G_\u0001\bMJ|W.\u00138u)\r1\u0014\u0011\u0012\u0005\b\u0003\u00173\u0002\u0019AAG\u0003\tA\b\u0007E\u0002!\u0003\u001fK1!!%\"\u0005\rIe\u000e^\u0001\u0004_:,W#\u0001\u001c"
)
public interface RingProduct20 extends Ring, RngProduct20 {
   Ring structure1();

   Ring structure2();

   Ring structure3();

   Ring structure4();

   Ring structure5();

   Ring structure6();

   Ring structure7();

   Ring structure8();

   Ring structure9();

   Ring structure10();

   Ring structure11();

   Ring structure12();

   Ring structure13();

   Ring structure14();

   Ring structure15();

   Ring structure16();

   Ring structure17();

   Ring structure18();

   Ring structure19();

   Ring structure20();

   // $FF: synthetic method
   static Tuple20 fromInt$(final RingProduct20 $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple20 fromInt(final int x0) {
      return new Tuple20(this.structure1().fromInt(x0), this.structure2().fromInt(x0), this.structure3().fromInt(x0), this.structure4().fromInt(x0), this.structure5().fromInt(x0), this.structure6().fromInt(x0), this.structure7().fromInt(x0), this.structure8().fromInt(x0), this.structure9().fromInt(x0), this.structure10().fromInt(x0), this.structure11().fromInt(x0), this.structure12().fromInt(x0), this.structure13().fromInt(x0), this.structure14().fromInt(x0), this.structure15().fromInt(x0), this.structure16().fromInt(x0), this.structure17().fromInt(x0), this.structure18().fromInt(x0), this.structure19().fromInt(x0), this.structure20().fromInt(x0));
   }

   // $FF: synthetic method
   static Tuple20 one$(final RingProduct20 $this) {
      return $this.one();
   }

   default Tuple20 one() {
      return new Tuple20(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one(), this.structure8().one(), this.structure9().one(), this.structure10().one(), this.structure11().one(), this.structure12().one(), this.structure13().one(), this.structure14().one(), this.structure15().one(), this.structure16().one(), this.structure17().one(), this.structure18().one(), this.structure19().one(), this.structure20().one());
   }

   static void $init$(final RingProduct20 $this) {
   }
}
