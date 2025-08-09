package spire.std;

import algebra.ring.Ring;
import scala.Tuple15;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ec\u0001C\n\u0015!\u0003\r\tA\u0006\r\t\u000b5\u0004A\u0011\u00018\t\u000bI\u0004a1A:\t\u000bU\u0004a1\u0001<\t\u000ba\u0004a1A=\t\u000bm\u0004a1\u0001?\t\u000by\u0004a1A@\t\u000f\u0005\r\u0001Ab\u0001\u0002\u0006!9\u0011\u0011\u0002\u0001\u0007\u0004\u0005-\u0001bBA\b\u0001\u0019\r\u0011\u0011\u0003\u0005\b\u0003+\u0001a1AA\f\u0011\u001d\tY\u0002\u0001D\u0002\u0003;Aq!!\t\u0001\r\u0007\t\u0019\u0003C\u0004\u0002(\u00011\u0019!!\u000b\t\u000f\u00055\u0002Ab\u0001\u00020!9\u00111\u0007\u0001\u0007\u0004\u0005U\u0002bBA\u001d\u0001\u0019\r\u00111\b\u0005\b\u0003\u007f\u0001A\u0011IA!\u0011\u001d\ti\u0005\u0001C\u0001\u0003\u001f\u0012QBU5oOB\u0013x\u000eZ;diF*$BA\u000b\u0017\u0003\r\u0019H\u000f\u001a\u0006\u0002/\u0005)1\u000f]5sKV\u0001\u0012D\u000e!D\r&cuJU+Y7z\u000bGmZ\n\u0005\u0001i\u0001\u0013\u000e\u0005\u0002\u001c=5\tADC\u0001\u001e\u0003\u0015\u00198-\u00197b\u0013\tyBD\u0001\u0004B]f\u0014VM\u001a\t\u0004C9\ndB\u0001\u0012,\u001d\t\u0019\u0013F\u0004\u0002%Q5\tQE\u0003\u0002'O\u00051AH]8piz\u001a\u0001!C\u0001\u0018\u0013\tQc#A\u0004bY\u001e,'M]1\n\u00051j\u0013a\u00029bG.\fw-\u001a\u0006\u0003UYI!a\f\u0019\u0003\tIKgn\u001a\u0006\u0003Y5\u0002\u0012c\u0007\u001a5\u007f\t+\u0005j\u0013(R)^SV\fY2g\u0013\t\u0019DDA\u0004UkBdW-M\u001b\u0011\u0005U2D\u0002\u0001\u0003\u0006o\u0001\u0011\r\u0001\u000f\u0002\u0002\u0003F\u0011\u0011\b\u0010\t\u00037iJ!a\u000f\u000f\u0003\u000f9{G\u000f[5oOB\u00111$P\u0005\u0003}q\u00111!\u00118z!\t)\u0004\tB\u0003B\u0001\t\u0007\u0001HA\u0001C!\t)4\tB\u0003E\u0001\t\u0007\u0001HA\u0001D!\t)d\tB\u0003H\u0001\t\u0007\u0001HA\u0001E!\t)\u0014\nB\u0003K\u0001\t\u0007\u0001HA\u0001F!\t)D\nB\u0003N\u0001\t\u0007\u0001HA\u0001G!\t)t\nB\u0003Q\u0001\t\u0007\u0001HA\u0001H!\t)$\u000bB\u0003T\u0001\t\u0007\u0001HA\u0001I!\t)T\u000bB\u0003W\u0001\t\u0007\u0001HA\u0001J!\t)\u0004\fB\u0003Z\u0001\t\u0007\u0001HA\u0001K!\t)4\fB\u0003]\u0001\t\u0007\u0001HA\u0001L!\t)d\fB\u0003`\u0001\t\u0007\u0001HA\u0001M!\t)\u0014\rB\u0003c\u0001\t\u0007\u0001HA\u0001N!\t)D\rB\u0003f\u0001\t\u0007\u0001HA\u0001O!\t)t\rB\u0003i\u0001\t\u0007\u0001HA\u0001P!EQ7\u000eN C\u000b\"[e*\u0015+X5v\u00037MZ\u0007\u0002)%\u0011A\u000e\u0006\u0002\r%:<\u0007K]8ek\u000e$\u0018'N\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003=\u0004\"a\u00079\n\u0005Ed\"\u0001B+oSR\f!b\u001d;sk\u000e$XO]32+\u0005!\bcA\u0011/i\u0005Q1\u000f\u001e:vGR,(/\u001a\u001a\u0016\u0003]\u00042!\t\u0018@\u0003)\u0019HO];diV\u0014XmM\u000b\u0002uB\u0019\u0011E\f\"\u0002\u0015M$(/^2ukJ,G'F\u0001~!\r\tc&R\u0001\u000bgR\u0014Xo\u0019;ve\u0016,TCAA\u0001!\r\tc\u0006S\u0001\u000bgR\u0014Xo\u0019;ve\u00164TCAA\u0004!\r\tcfS\u0001\u000bgR\u0014Xo\u0019;ve\u0016<TCAA\u0007!\r\tcFT\u0001\u000bgR\u0014Xo\u0019;ve\u0016DTCAA\n!\r\tc&U\u0001\u000bgR\u0014Xo\u0019;ve\u0016LTCAA\r!\r\tc\u0006V\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0004'\u0006\u0002\u0002 A\u0019\u0011EL,\u0002\u0017M$(/^2ukJ,\u0017'M\u000b\u0003\u0003K\u00012!\t\u0018[\u0003-\u0019HO];diV\u0014X-\r\u001a\u0016\u0005\u0005-\u0002cA\u0011/;\u0006Y1\u000f\u001e:vGR,(/Z\u00194+\t\t\t\u0004E\u0002\"]\u0001\f1b\u001d;sk\u000e$XO]32iU\u0011\u0011q\u0007\t\u0004C9\u001a\u0017aC:ueV\u001cG/\u001e:fcU*\"!!\u0010\u0011\u0007\u0005rc-A\u0004ge>l\u0017J\u001c;\u0015\u0007E\n\u0019\u0005C\u0004\u0002FE\u0001\r!a\u0012\u0002\u0005a\u0004\u0004cA\u000e\u0002J%\u0019\u00111\n\u000f\u0003\u0007%sG/A\u0002p]\u0016,\u0012!\r"
)
public interface RingProduct15 extends Ring, RngProduct15 {
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

   // $FF: synthetic method
   static Tuple15 fromInt$(final RingProduct15 $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple15 fromInt(final int x0) {
      return new Tuple15(this.structure1().fromInt(x0), this.structure2().fromInt(x0), this.structure3().fromInt(x0), this.structure4().fromInt(x0), this.structure5().fromInt(x0), this.structure6().fromInt(x0), this.structure7().fromInt(x0), this.structure8().fromInt(x0), this.structure9().fromInt(x0), this.structure10().fromInt(x0), this.structure11().fromInt(x0), this.structure12().fromInt(x0), this.structure13().fromInt(x0), this.structure14().fromInt(x0), this.structure15().fromInt(x0));
   }

   // $FF: synthetic method
   static Tuple15 one$(final RingProduct15 $this) {
      return $this.one();
   }

   default Tuple15 one() {
      return new Tuple15(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one(), this.structure8().one(), this.structure9().one(), this.structure10().one(), this.structure11().one(), this.structure12().one(), this.structure13().one(), this.structure14().one(), this.structure15().one());
   }

   static void $init$(final RingProduct15 $this) {
   }
}
