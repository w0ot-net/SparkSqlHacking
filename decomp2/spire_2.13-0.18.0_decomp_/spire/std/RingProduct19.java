package spire.std;

import algebra.ring.Ring;
import scala.Tuple19;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%e\u0001C\f\u0019!\u0003\r\tA\u0007\u000f\t\u000bu\u0004A\u0011\u0001@\t\u000f\u0005\u0015\u0001Ab\u0001\u0002\b!9\u00111\u0002\u0001\u0007\u0004\u00055\u0001bBA\t\u0001\u0019\r\u00111\u0003\u0005\b\u0003/\u0001a1AA\r\u0011\u001d\ti\u0002\u0001D\u0002\u0003?Aq!a\t\u0001\r\u0007\t)\u0003C\u0004\u0002*\u00011\u0019!a\u000b\t\u000f\u0005=\u0002Ab\u0001\u00022!9\u0011Q\u0007\u0001\u0007\u0004\u0005]\u0002bBA\u001e\u0001\u0019\r\u0011Q\b\u0005\b\u0003\u0003\u0002a1AA\"\u0011\u001d\t9\u0005\u0001D\u0002\u0003\u0013Bq!!\u0014\u0001\r\u0007\ty\u0005C\u0004\u0002T\u00011\u0019!!\u0016\t\u000f\u0005e\u0003Ab\u0001\u0002\\!9\u0011q\f\u0001\u0007\u0004\u0005\u0005\u0004bBA3\u0001\u0019\r\u0011q\r\u0005\b\u0003W\u0002a1AA7\u0011\u001d\t\t\b\u0001D\u0002\u0003gBq!a\u001e\u0001\t\u0003\nI\bC\u0004\u0002\u0006\u0002!\t!a\"\u0003\u001bIKgn\u001a)s_\u0012,8\r^\u0019:\u0015\tI\"$A\u0002ti\u0012T\u0011aG\u0001\u0006gBL'/Z\u000b\u0015;i\"uIS'Q'ZKFl\u00182fQ.t\u0017\u000f^<\u0014\t\u0001qB%\u001f\t\u0003?\tj\u0011\u0001\t\u0006\u0002C\u0005)1oY1mC&\u00111\u0005\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0007\u0015\u0012TG\u0004\u0002'_9\u0011q%\f\b\u0003Q1j\u0011!\u000b\u0006\u0003U-\na\u0001\u0010:p_Rt4\u0001A\u0005\u00027%\u0011aFG\u0001\bC2<WM\u0019:b\u0013\t\u0001\u0014'A\u0004qC\u000e\\\u0017mZ3\u000b\u00059R\u0012BA\u001a5\u0005\u0011\u0011\u0016N\\4\u000b\u0005A\n\u0004#F\u00107q\r3\u0015\nT(S+b[f,\u00193hU6\u00048O^\u0005\u0003o\u0001\u0012q\u0001V;qY\u0016\f\u0014\b\u0005\u0002:u1\u0001A!B\u001e\u0001\u0005\u0004a$!A!\u0012\u0005u\u0002\u0005CA\u0010?\u0013\ty\u0004EA\u0004O_RD\u0017N\\4\u0011\u0005}\t\u0015B\u0001\"!\u0005\r\te.\u001f\t\u0003s\u0011#Q!\u0012\u0001C\u0002q\u0012\u0011A\u0011\t\u0003s\u001d#Q\u0001\u0013\u0001C\u0002q\u0012\u0011a\u0011\t\u0003s)#Qa\u0013\u0001C\u0002q\u0012\u0011\u0001\u0012\t\u0003s5#QA\u0014\u0001C\u0002q\u0012\u0011!\u0012\t\u0003sA#Q!\u0015\u0001C\u0002q\u0012\u0011A\u0012\t\u0003sM#Q\u0001\u0016\u0001C\u0002q\u0012\u0011a\u0012\t\u0003sY#Qa\u0016\u0001C\u0002q\u0012\u0011\u0001\u0013\t\u0003se#QA\u0017\u0001C\u0002q\u0012\u0011!\u0013\t\u0003sq#Q!\u0018\u0001C\u0002q\u0012\u0011A\u0013\t\u0003s}#Q\u0001\u0019\u0001C\u0002q\u0012\u0011a\u0013\t\u0003s\t$Qa\u0019\u0001C\u0002q\u0012\u0011\u0001\u0014\t\u0003s\u0015$QA\u001a\u0001C\u0002q\u0012\u0011!\u0014\t\u0003s!$Q!\u001b\u0001C\u0002q\u0012\u0011A\u0014\t\u0003s-$Q\u0001\u001c\u0001C\u0002q\u0012\u0011a\u0014\t\u0003s9$Qa\u001c\u0001C\u0002q\u0012\u0011\u0001\u0015\t\u0003sE$QA\u001d\u0001C\u0002q\u0012\u0011!\u0015\t\u0003sQ$Q!\u001e\u0001C\u0002q\u0012\u0011A\u0015\t\u0003s]$Q\u0001\u001f\u0001C\u0002q\u0012\u0011a\u0015\t\u0016unD4IR%M\u001fJ+\u0006l\u00170bI\u001eTW\u000e]:w\u001b\u0005A\u0012B\u0001?\u0019\u00051\u0011fn\u001a)s_\u0012,8\r^\u0019:\u0003\u0019!\u0013N\\5uIQ\tq\u0010E\u0002 \u0003\u0003I1!a\u0001!\u0005\u0011)f.\u001b;\u0002\u0015M$(/^2ukJ,\u0017'\u0006\u0002\u0002\nA\u0019QE\r\u001d\u0002\u0015M$(/^2ukJ,''\u0006\u0002\u0002\u0010A\u0019QEM\"\u0002\u0015M$(/^2ukJ,7'\u0006\u0002\u0002\u0016A\u0019QE\r$\u0002\u0015M$(/^2ukJ,G'\u0006\u0002\u0002\u001cA\u0019QEM%\u0002\u0015M$(/^2ukJ,W'\u0006\u0002\u0002\"A\u0019QE\r'\u0002\u0015M$(/^2ukJ,g'\u0006\u0002\u0002(A\u0019QEM(\u0002\u0015M$(/^2ukJ,w'\u0006\u0002\u0002.A\u0019QE\r*\u0002\u0015M$(/^2ukJ,\u0007(\u0006\u0002\u00024A\u0019QEM+\u0002\u0015M$(/^2ukJ,\u0017(\u0006\u0002\u0002:A\u0019QE\r-\u0002\u0017M$(/^2ukJ,\u0017\u0007M\u000b\u0003\u0003\u007f\u00012!\n\u001a\\\u0003-\u0019HO];diV\u0014X-M\u0019\u0016\u0005\u0005\u0015\u0003cA\u00133=\u0006Y1\u000f\u001e:vGR,(/Z\u00193+\t\tY\u0005E\u0002&e\u0005\f1b\u001d;sk\u000e$XO]32gU\u0011\u0011\u0011\u000b\t\u0004KI\"\u0017aC:ueV\u001cG/\u001e:fcQ*\"!a\u0016\u0011\u0007\u0015\u0012t-A\u0006tiJ,8\r^;sKF*TCAA/!\r)#G[\u0001\fgR\u0014Xo\u0019;ve\u0016\fd'\u0006\u0002\u0002dA\u0019QEM7\u0002\u0017M$(/^2ukJ,\u0017gN\u000b\u0003\u0003S\u00022!\n\u001aq\u0003-\u0019HO];diV\u0014X-\r\u001d\u0016\u0005\u0005=\u0004cA\u00133g\u0006Y1\u000f\u001e:vGR,(/Z\u0019:+\t\t)\bE\u0002&eY\fqA\u001a:p[&sG\u000fF\u00026\u0003wBq!! \u0016\u0001\u0004\ty(\u0001\u0002yaA\u0019q$!!\n\u0007\u0005\r\u0005EA\u0002J]R\f1a\u001c8f+\u0005)\u0004"
)
public interface RingProduct19 extends Ring, RngProduct19 {
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

   // $FF: synthetic method
   static Tuple19 fromInt$(final RingProduct19 $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple19 fromInt(final int x0) {
      return new Tuple19(this.structure1().fromInt(x0), this.structure2().fromInt(x0), this.structure3().fromInt(x0), this.structure4().fromInt(x0), this.structure5().fromInt(x0), this.structure6().fromInt(x0), this.structure7().fromInt(x0), this.structure8().fromInt(x0), this.structure9().fromInt(x0), this.structure10().fromInt(x0), this.structure11().fromInt(x0), this.structure12().fromInt(x0), this.structure13().fromInt(x0), this.structure14().fromInt(x0), this.structure15().fromInt(x0), this.structure16().fromInt(x0), this.structure17().fromInt(x0), this.structure18().fromInt(x0), this.structure19().fromInt(x0));
   }

   // $FF: synthetic method
   static Tuple19 one$(final RingProduct19 $this) {
      return $this.one();
   }

   default Tuple19 one() {
      return new Tuple19(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one(), this.structure8().one(), this.structure9().one(), this.structure10().one(), this.structure11().one(), this.structure12().one(), this.structure13().one(), this.structure14().one(), this.structure15().one(), this.structure16().one(), this.structure17().one(), this.structure18().one(), this.structure19().one());
   }

   static void $init$(final RingProduct19 $this) {
   }
}
