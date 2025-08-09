package spire.std;

import algebra.ring.Ring;
import scala.Tuple22;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mf\u0001\u0003\u000e\u001c!\u0003\r\t!H\u0010\t\u000f\u0005M\u0001\u0001\"\u0001\u0002\u0016!9\u0011Q\u0004\u0001\u0007\u0004\u0005}\u0001bBA\u0012\u0001\u0019\r\u0011Q\u0005\u0005\b\u0003S\u0001a1AA\u0016\u0011\u001d\ty\u0003\u0001D\u0002\u0003cAq!!\u000e\u0001\r\u0007\t9\u0004C\u0004\u0002<\u00011\u0019!!\u0010\t\u000f\u0005\u0005\u0003Ab\u0001\u0002D!9\u0011q\t\u0001\u0007\u0004\u0005%\u0003bBA'\u0001\u0019\r\u0011q\n\u0005\b\u0003'\u0002a1AA+\u0011\u001d\tI\u0006\u0001D\u0002\u00037Bq!a\u0018\u0001\r\u0007\t\t\u0007C\u0004\u0002f\u00011\u0019!a\u001a\t\u000f\u0005-\u0004Ab\u0001\u0002n!9\u0011\u0011\u000f\u0001\u0007\u0004\u0005M\u0004bBA<\u0001\u0019\r\u0011\u0011\u0010\u0005\b\u0003{\u0002a1AA@\u0011\u001d\t\u0019\t\u0001D\u0002\u0003\u000bCq!!#\u0001\r\u0007\tY\tC\u0004\u0002\u0010\u00021\u0019!!%\t\u000f\u0005U\u0005Ab\u0001\u0002\u0018\"9\u00111\u0014\u0001\u0007\u0004\u0005u\u0005bBAQ\u0001\u0011\u0005\u00131\u0015\u0005\b\u0003_\u0003A\u0011AAY\u00055\u0011\u0016N\\4Qe>$Wo\u0019;3e)\u0011A$H\u0001\u0004gR$'\"\u0001\u0010\u0002\u000bM\u0004\u0018N]3\u00163\u0001jtIS'Q'ZKFl\u00182fQ.t\u0017\u000f^<{{\u0006\u0005\u0011qA\n\u0006\u0001\u0005:\u00131\u0002\t\u0003E\u0015j\u0011a\t\u0006\u0002I\u0005)1oY1mC&\u0011ae\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0007!*\u0004H\u0004\u0002*e9\u0011!\u0006\r\b\u0003W=j\u0011\u0001\f\u0006\u0003[9\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002=%\u0011\u0011'H\u0001\bC2<WM\u0019:b\u0013\t\u0019D'A\u0004qC\u000e\\\u0017mZ3\u000b\u0005Ej\u0012B\u0001\u001c8\u0005\u0011\u0011\u0016N\\4\u000b\u0005M\"\u0004#\u0007\u0012:w\u0019KEj\u0014*V1ns\u0016\rZ4k[B\u001ch/\u001f?\u0000\u0003\u000bI!AO\u0012\u0003\u000fQ+\b\u000f\\33eA\u0011A(\u0010\u0007\u0001\t\u0015q\u0004A1\u0001@\u0005\u0005\t\u0015C\u0001!D!\t\u0011\u0013)\u0003\u0002CG\t9aj\u001c;iS:<\u0007C\u0001\u0012E\u0013\t)5EA\u0002B]f\u0004\"\u0001P$\u0005\u000b!\u0003!\u0019A \u0003\u0003\t\u0003\"\u0001\u0010&\u0005\u000b-\u0003!\u0019A \u0003\u0003\r\u0003\"\u0001P'\u0005\u000b9\u0003!\u0019A \u0003\u0003\u0011\u0003\"\u0001\u0010)\u0005\u000bE\u0003!\u0019A \u0003\u0003\u0015\u0003\"\u0001P*\u0005\u000bQ\u0003!\u0019A \u0003\u0003\u0019\u0003\"\u0001\u0010,\u0005\u000b]\u0003!\u0019A \u0003\u0003\u001d\u0003\"\u0001P-\u0005\u000bi\u0003!\u0019A \u0003\u0003!\u0003\"\u0001\u0010/\u0005\u000bu\u0003!\u0019A \u0003\u0003%\u0003\"\u0001P0\u0005\u000b\u0001\u0004!\u0019A \u0003\u0003)\u0003\"\u0001\u00102\u0005\u000b\r\u0004!\u0019A \u0003\u0003-\u0003\"\u0001P3\u0005\u000b\u0019\u0004!\u0019A \u0003\u00031\u0003\"\u0001\u00105\u0005\u000b%\u0004!\u0019A \u0003\u00035\u0003\"\u0001P6\u0005\u000b1\u0004!\u0019A \u0003\u00039\u0003\"\u0001\u00108\u0005\u000b=\u0004!\u0019A \u0003\u0003=\u0003\"\u0001P9\u0005\u000bI\u0004!\u0019A \u0003\u0003A\u0003\"\u0001\u0010;\u0005\u000bU\u0004!\u0019A \u0003\u0003E\u0003\"\u0001P<\u0005\u000ba\u0004!\u0019A \u0003\u0003I\u0003\"\u0001\u0010>\u0005\u000bm\u0004!\u0019A \u0003\u0003M\u0003\"\u0001P?\u0005\u000by\u0004!\u0019A \u0003\u0003Q\u00032\u0001PA\u0001\t\u0019\t\u0019\u0001\u0001b\u0001\u007f\t\tQ\u000bE\u0002=\u0003\u000f!a!!\u0003\u0001\u0005\u0004y$!\u0001,\u00117\u00055\u0011qB\u001eG\u00132{%+\u0016-\\=\u0006$wM[7qgZLHp`A\u0003\u001b\u0005Y\u0012bAA\t7\ta!K\\4Qe>$Wo\u0019;3e\u00051A%\u001b8ji\u0012\"\"!a\u0006\u0011\u0007\t\nI\"C\u0002\u0002\u001c\r\u0012A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0005\u0005\u0005\u0002c\u0001\u00156w\u0005Q1\u000f\u001e:vGR,(/\u001a\u001a\u0016\u0005\u0005\u001d\u0002c\u0001\u00156\r\u0006Q1\u000f\u001e:vGR,(/Z\u001a\u0016\u0005\u00055\u0002c\u0001\u00156\u0013\u0006Q1\u000f\u001e:vGR,(/\u001a\u001b\u0016\u0005\u0005M\u0002c\u0001\u00156\u0019\u0006Q1\u000f\u001e:vGR,(/Z\u001b\u0016\u0005\u0005e\u0002c\u0001\u00156\u001f\u0006Q1\u000f\u001e:vGR,(/\u001a\u001c\u0016\u0005\u0005}\u0002c\u0001\u00156%\u0006Q1\u000f\u001e:vGR,(/Z\u001c\u0016\u0005\u0005\u0015\u0003c\u0001\u00156+\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0005\u0005-\u0003c\u0001\u001561\u0006Q1\u000f\u001e:vGR,(/Z\u001d\u0016\u0005\u0005E\u0003c\u0001\u001567\u0006Y1\u000f\u001e:vGR,(/Z\u00191+\t\t9\u0006E\u0002)ky\u000b1b\u001d;sk\u000e$XO]32cU\u0011\u0011Q\f\t\u0004QU\n\u0017aC:ueV\u001cG/\u001e:fcI*\"!a\u0019\u0011\u0007!*D-A\u0006tiJ,8\r^;sKF\u001aTCAA5!\rASgZ\u0001\fgR\u0014Xo\u0019;ve\u0016\fD'\u0006\u0002\u0002pA\u0019\u0001&\u000e6\u0002\u0017M$(/^2ukJ,\u0017'N\u000b\u0003\u0003k\u00022\u0001K\u001bn\u0003-\u0019HO];diV\u0014X-\r\u001c\u0016\u0005\u0005m\u0004c\u0001\u00156a\u0006Y1\u000f\u001e:vGR,(/Z\u00198+\t\t\t\tE\u0002)kM\f1b\u001d;sk\u000e$XO]32qU\u0011\u0011q\u0011\t\u0004QU2\u0018aC:ueV\u001cG/\u001e:fce*\"!!$\u0011\u0007!*\u00140A\u0006tiJ,8\r^;sKJ\u0002TCAAJ!\rAS\u0007`\u0001\fgR\u0014Xo\u0019;ve\u0016\u0014\u0014'\u0006\u0002\u0002\u001aB\u0019\u0001&N@\u0002\u0017M$(/^2ukJ,'GM\u000b\u0003\u0003?\u0003B\u0001K\u001b\u0002\u0006\u00059aM]8n\u0013:$Hc\u0001\u001d\u0002&\"9\u0011q\u0015\rA\u0002\u0005%\u0016A\u0001=1!\r\u0011\u00131V\u0005\u0004\u0003[\u001b#aA%oi\u0006\u0019qN\\3\u0016\u0003a\u0002"
)
public interface RingProduct22 extends Ring, RngProduct22 {
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

   Ring structure21();

   Ring structure22();

   // $FF: synthetic method
   static Tuple22 fromInt$(final RingProduct22 $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple22 fromInt(final int x0) {
      return new Tuple22(this.structure1().fromInt(x0), this.structure2().fromInt(x0), this.structure3().fromInt(x0), this.structure4().fromInt(x0), this.structure5().fromInt(x0), this.structure6().fromInt(x0), this.structure7().fromInt(x0), this.structure8().fromInt(x0), this.structure9().fromInt(x0), this.structure10().fromInt(x0), this.structure11().fromInt(x0), this.structure12().fromInt(x0), this.structure13().fromInt(x0), this.structure14().fromInt(x0), this.structure15().fromInt(x0), this.structure16().fromInt(x0), this.structure17().fromInt(x0), this.structure18().fromInt(x0), this.structure19().fromInt(x0), this.structure20().fromInt(x0), this.structure21().fromInt(x0), this.structure22().fromInt(x0));
   }

   // $FF: synthetic method
   static Tuple22 one$(final RingProduct22 $this) {
      return $this.one();
   }

   default Tuple22 one() {
      return new Tuple22(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one(), this.structure8().one(), this.structure9().one(), this.structure10().one(), this.structure11().one(), this.structure12().one(), this.structure13().one(), this.structure14().one(), this.structure15().one(), this.structure16().one(), this.structure17().one(), this.structure18().one(), this.structure19().one(), this.structure20().one(), this.structure21().one(), this.structure22().one());
   }

   static void $init$(final RingProduct22 $this) {
   }
}
