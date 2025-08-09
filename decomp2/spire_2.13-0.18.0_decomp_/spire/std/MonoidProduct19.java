package spire.std;

import cats.kernel.Monoid;
import scala.Tuple19;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ed\u0001\u0003\f\u0018!\u0003\r\t!G\u000e\t\u000bq\u0004A\u0011A?\t\u000f\u0005\r\u0001Ab\u0001\u0002\u0006!9\u0011\u0011\u0002\u0001\u0007\u0004\u0005-\u0001bBA\b\u0001\u0019\r\u0011\u0011\u0003\u0005\b\u0003+\u0001a1AA\f\u0011\u001d\tY\u0002\u0001D\u0002\u0003;Aq!!\t\u0001\r\u0007\t\u0019\u0003C\u0004\u0002(\u00011\u0019!!\u000b\t\u000f\u00055\u0002Ab\u0001\u00020!9\u00111\u0007\u0001\u0007\u0004\u0005U\u0002bBA\u001d\u0001\u0019\r\u00111\b\u0005\b\u0003\u007f\u0001a1AA!\u0011\u001d\t)\u0005\u0001D\u0002\u0003\u000fBq!a\u0013\u0001\r\u0007\ti\u0005C\u0004\u0002R\u00011\u0019!a\u0015\t\u000f\u0005]\u0003Ab\u0001\u0002Z!9\u0011Q\f\u0001\u0007\u0004\u0005}\u0003bBA2\u0001\u0019\r\u0011Q\r\u0005\b\u0003S\u0002a1AA6\u0011\u001d\ty\u0007\u0001D\u0002\u0003cBq!!\u001e\u0001\t\u0003\t9HA\bN_:|\u0017\u000e\u001a)s_\u0012,8\r^\u0019:\u0015\tA\u0012$A\u0002ti\u0012T\u0011AG\u0001\u0006gBL'/Z\u000b\u00159e\u001ae)\u0013'P%VC6LX1eO*l\u0007o\u001d<\u0014\t\u0001i2\u0005\u001f\t\u0003=\u0005j\u0011a\b\u0006\u0002A\u0005)1oY1mC&\u0011!e\b\u0002\u0007\u0003:L(+\u001a4\u0011\u0007\u0011\nDG\u0004\u0002&]9\u0011a\u0005\f\b\u0003O-j\u0011\u0001\u000b\u0006\u0003S)\na\u0001\u0010:p_Rt4\u0001A\u0005\u00025%\u0011Q&G\u0001\bC2<WM\u0019:b\u0013\ty\u0003'A\u0004qC\u000e\\\u0017mZ3\u000b\u00055J\u0012B\u0001\u001a4\u0005\u0019iuN\\8jI*\u0011q\u0006\r\t\u0016=U:$)\u0012%L\u001dF#vKW/aG\u001aLGn\u001c:v\u0013\t1tDA\u0004UkBdW-M\u001d\u0011\u0005aJD\u0002\u0001\u0003\u0006u\u0001\u0011\ra\u000f\u0002\u0002\u0003F\u0011Ah\u0010\t\u0003=uJ!AP\u0010\u0003\u000f9{G\u000f[5oOB\u0011a\u0004Q\u0005\u0003\u0003~\u00111!\u00118z!\tA4\tB\u0003E\u0001\t\u00071HA\u0001C!\tAd\tB\u0003H\u0001\t\u00071HA\u0001D!\tA\u0014\nB\u0003K\u0001\t\u00071HA\u0001E!\tAD\nB\u0003N\u0001\t\u00071HA\u0001F!\tAt\nB\u0003Q\u0001\t\u00071HA\u0001G!\tA$\u000bB\u0003T\u0001\t\u00071HA\u0001H!\tAT\u000bB\u0003W\u0001\t\u00071HA\u0001I!\tA\u0004\fB\u0003Z\u0001\t\u00071HA\u0001J!\tA4\fB\u0003]\u0001\t\u00071HA\u0001K!\tAd\fB\u0003`\u0001\t\u00071HA\u0001L!\tA\u0014\rB\u0003c\u0001\t\u00071HA\u0001M!\tAD\rB\u0003f\u0001\t\u00071HA\u0001N!\tAt\rB\u0003i\u0001\t\u00071HA\u0001O!\tA$\u000eB\u0003l\u0001\t\u00071HA\u0001P!\tAT\u000eB\u0003o\u0001\t\u00071HA\u0001Q!\tA\u0004\u000fB\u0003r\u0001\t\u00071HA\u0001R!\tA4\u000fB\u0003u\u0001\t\u00071HA\u0001S!\tAd\u000fB\u0003x\u0001\t\u00071HA\u0001T!UI(p\u000e\"F\u0011.s\u0015\u000bV,[;\u0002\u001cg-\u001b7peVl\u0011aF\u0005\u0003w^\u0011!cU3nS\u001e\u0014x.\u001e9Qe>$Wo\u0019;2s\u00051A%\u001b8ji\u0012\"\u0012A \t\u0003=}L1!!\u0001 \u0005\u0011)f.\u001b;\u0002\u0015M$(/^2ukJ,\u0017'\u0006\u0002\u0002\bA\u0019A%M\u001c\u0002\u0015M$(/^2ukJ,''\u0006\u0002\u0002\u000eA\u0019A%\r\"\u0002\u0015M$(/^2ukJ,7'\u0006\u0002\u0002\u0014A\u0019A%M#\u0002\u0015M$(/^2ukJ,G'\u0006\u0002\u0002\u001aA\u0019A%\r%\u0002\u0015M$(/^2ukJ,W'\u0006\u0002\u0002 A\u0019A%M&\u0002\u0015M$(/^2ukJ,g'\u0006\u0002\u0002&A\u0019A%\r(\u0002\u0015M$(/^2ukJ,w'\u0006\u0002\u0002,A\u0019A%M)\u0002\u0015M$(/^2ukJ,\u0007(\u0006\u0002\u00022A\u0019A%\r+\u0002\u0015M$(/^2ukJ,\u0017(\u0006\u0002\u00028A\u0019A%M,\u0002\u0017M$(/^2ukJ,\u0017\u0007M\u000b\u0003\u0003{\u00012\u0001J\u0019[\u0003-\u0019HO];diV\u0014X-M\u0019\u0016\u0005\u0005\r\u0003c\u0001\u00132;\u0006Y1\u000f\u001e:vGR,(/Z\u00193+\t\tI\u0005E\u0002%c\u0001\f1b\u001d;sk\u000e$XO]32gU\u0011\u0011q\n\t\u0004IE\u001a\u0017aC:ueV\u001cG/\u001e:fcQ*\"!!\u0016\u0011\u0007\u0011\nd-A\u0006tiJ,8\r^;sKF*TCAA.!\r!\u0013'[\u0001\fgR\u0014Xo\u0019;ve\u0016\fd'\u0006\u0002\u0002bA\u0019A%\r7\u0002\u0017M$(/^2ukJ,\u0017gN\u000b\u0003\u0003O\u00022\u0001J\u0019p\u0003-\u0019HO];diV\u0014X-\r\u001d\u0016\u0005\u00055\u0004c\u0001\u00132e\u0006Y1\u000f\u001e:vGR,(/Z\u0019:+\t\t\u0019\bE\u0002%cU\fQ!Z7qif,\u0012\u0001\u000e"
)
public interface MonoidProduct19 extends Monoid, SemigroupProduct19 {
   Monoid structure1();

   Monoid structure2();

   Monoid structure3();

   Monoid structure4();

   Monoid structure5();

   Monoid structure6();

   Monoid structure7();

   Monoid structure8();

   Monoid structure9();

   Monoid structure10();

   Monoid structure11();

   Monoid structure12();

   Monoid structure13();

   Monoid structure14();

   Monoid structure15();

   Monoid structure16();

   Monoid structure17();

   Monoid structure18();

   Monoid structure19();

   // $FF: synthetic method
   static Tuple19 empty$(final MonoidProduct19 $this) {
      return $this.empty();
   }

   default Tuple19 empty() {
      return new Tuple19(this.structure1().empty(), this.structure2().empty(), this.structure3().empty(), this.structure4().empty(), this.structure5().empty(), this.structure6().empty(), this.structure7().empty(), this.structure8().empty(), this.structure9().empty(), this.structure10().empty(), this.structure11().empty(), this.structure12().empty(), this.structure13().empty(), this.structure14().empty(), this.structure15().empty(), this.structure16().empty(), this.structure17().empty(), this.structure18().empty(), this.structure19().empty());
   }

   static void $init$(final MonoidProduct19 $this) {
   }
}
