package spire.std;

import algebra.ring.Ring;
import scala.Tuple17;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055d\u0001C\u000b\u0017!\u0003\r\t\u0001\u0007\u000e\t\u000bU\u0004A\u0011\u0001<\t\u000bi\u0004a1A>\t\u000bu\u0004a1\u0001@\t\u000f\u0005\u0005\u0001Ab\u0001\u0002\u0004!9\u0011q\u0001\u0001\u0007\u0004\u0005%\u0001bBA\u0007\u0001\u0019\r\u0011q\u0002\u0005\b\u0003'\u0001a1AA\u000b\u0011\u001d\tI\u0002\u0001D\u0002\u00037Aq!a\b\u0001\r\u0007\t\t\u0003C\u0004\u0002&\u00011\u0019!a\n\t\u000f\u0005-\u0002Ab\u0001\u0002.!9\u0011\u0011\u0007\u0001\u0007\u0004\u0005M\u0002bBA\u001c\u0001\u0019\r\u0011\u0011\b\u0005\b\u0003{\u0001a1AA \u0011\u001d\t\u0019\u0005\u0001D\u0002\u0003\u000bBq!!\u0013\u0001\r\u0007\tY\u0005C\u0004\u0002P\u00011\u0019!!\u0015\t\u000f\u0005U\u0003Ab\u0001\u0002X!9\u00111\f\u0001\u0005B\u0005u\u0003bBA5\u0001\u0011\u0005\u00111\u000e\u0002\u000e%&tw\r\u0015:pIV\u001cG/M\u001c\u000b\u0005]A\u0012aA:uI*\t\u0011$A\u0003ta&\u0014X-\u0006\n\u001cq\t+\u0005j\u0013(R)^SV\fY2gS2|7\u0003\u0002\u0001\u001dEE\u0004\"!\b\u0011\u000e\u0003yQ\u0011aH\u0001\u0006g\u000e\fG.Y\u0005\u0003Cy\u0011a!\u00118z%\u00164\u0007cA\u00121g9\u0011A%\f\b\u0003K-r!A\n\u0016\u000e\u0003\u001dR!\u0001K\u0015\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011!G\u0005\u0003Ya\tq!\u00197hK\n\u0014\u0018-\u0003\u0002/_\u00059\u0001/Y2lC\u001e,'B\u0001\u0017\u0019\u0013\t\t$G\u0001\u0003SS:<'B\u0001\u00180!MiBGN!E\u000f*k\u0005k\u0015,Z9~\u0013W\r[6o\u0013\t)dDA\u0004UkBdW-M\u001c\u0011\u0005]BD\u0002\u0001\u0003\u0006s\u0001\u0011\rA\u000f\u0002\u0002\u0003F\u00111H\u0010\t\u0003;qJ!!\u0010\u0010\u0003\u000f9{G\u000f[5oOB\u0011QdP\u0005\u0003\u0001z\u00111!\u00118z!\t9$\tB\u0003D\u0001\t\u0007!HA\u0001C!\t9T\tB\u0003G\u0001\t\u0007!HA\u0001D!\t9\u0004\nB\u0003J\u0001\t\u0007!HA\u0001E!\t94\nB\u0003M\u0001\t\u0007!HA\u0001F!\t9d\nB\u0003P\u0001\t\u0007!HA\u0001G!\t9\u0014\u000bB\u0003S\u0001\t\u0007!HA\u0001H!\t9D\u000bB\u0003V\u0001\t\u0007!HA\u0001I!\t9t\u000bB\u0003Y\u0001\t\u0007!HA\u0001J!\t9$\fB\u0003\\\u0001\t\u0007!HA\u0001K!\t9T\fB\u0003_\u0001\t\u0007!HA\u0001L!\t9\u0004\rB\u0003b\u0001\t\u0007!HA\u0001M!\t94\rB\u0003e\u0001\t\u0007!HA\u0001N!\t9d\rB\u0003h\u0001\t\u0007!HA\u0001O!\t9\u0014\u000eB\u0003k\u0001\t\u0007!HA\u0001P!\t9D\u000eB\u0003n\u0001\t\u0007!HA\u0001Q!\t9t\u000eB\u0003q\u0001\t\u0007!HA\u0001R!M\u00118ON!E\u000f*k\u0005k\u0015,Z9~\u0013W\r[6o\u001b\u00051\u0012B\u0001;\u0017\u00051\u0011fn\u001a)s_\u0012,8\r^\u00198\u0003\u0019!\u0013N\\5uIQ\tq\u000f\u0005\u0002\u001eq&\u0011\u0011P\b\u0002\u0005+:LG/\u0001\u0006tiJ,8\r^;sKF*\u0012\u0001 \t\u0004GA2\u0014AC:ueV\u001cG/\u001e:feU\tq\u0010E\u0002$a\u0005\u000b!b\u001d;sk\u000e$XO]34+\t\t)\u0001E\u0002$a\u0011\u000b!b\u001d;sk\u000e$XO]35+\t\tY\u0001E\u0002$a\u001d\u000b!b\u001d;sk\u000e$XO]36+\t\t\t\u0002E\u0002$a)\u000b!b\u001d;sk\u000e$XO]37+\t\t9\u0002E\u0002$a5\u000b!b\u001d;sk\u000e$XO]38+\t\ti\u0002E\u0002$aA\u000b!b\u001d;sk\u000e$XO]39+\t\t\u0019\u0003E\u0002$aM\u000b!b\u001d;sk\u000e$XO]3:+\t\tI\u0003E\u0002$aY\u000b1b\u001d;sk\u000e$XO]32aU\u0011\u0011q\u0006\t\u0004GAJ\u0016aC:ueV\u001cG/\u001e:fcE*\"!!\u000e\u0011\u0007\r\u0002D,A\u0006tiJ,8\r^;sKF\u0012TCAA\u001e!\r\u0019\u0003gX\u0001\fgR\u0014Xo\u0019;ve\u0016\f4'\u0006\u0002\u0002BA\u00191\u0005\r2\u0002\u0017M$(/^2ukJ,\u0017\u0007N\u000b\u0003\u0003\u000f\u00022a\t\u0019f\u0003-\u0019HO];diV\u0014X-M\u001b\u0016\u0005\u00055\u0003cA\u00121Q\u0006Y1\u000f\u001e:vGR,(/Z\u00197+\t\t\u0019\u0006E\u0002$a-\f1b\u001d;sk\u000e$XO]32oU\u0011\u0011\u0011\f\t\u0004GAr\u0017a\u00024s_6Le\u000e\u001e\u000b\u0004g\u0005}\u0003bBA1'\u0001\u0007\u00111M\u0001\u0003qB\u00022!HA3\u0013\r\t9G\b\u0002\u0004\u0013:$\u0018aA8oKV\t1\u0007"
)
public interface RingProduct17 extends Ring, RngProduct17 {
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

   // $FF: synthetic method
   static Tuple17 fromInt$(final RingProduct17 $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple17 fromInt(final int x0) {
      return new Tuple17(this.structure1().fromInt(x0), this.structure2().fromInt(x0), this.structure3().fromInt(x0), this.structure4().fromInt(x0), this.structure5().fromInt(x0), this.structure6().fromInt(x0), this.structure7().fromInt(x0), this.structure8().fromInt(x0), this.structure9().fromInt(x0), this.structure10().fromInt(x0), this.structure11().fromInt(x0), this.structure12().fromInt(x0), this.structure13().fromInt(x0), this.structure14().fromInt(x0), this.structure15().fromInt(x0), this.structure16().fromInt(x0), this.structure17().fromInt(x0));
   }

   // $FF: synthetic method
   static Tuple17 one$(final RingProduct17 $this) {
      return $this.one();
   }

   default Tuple17 one() {
      return new Tuple17(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one(), this.structure8().one(), this.structure9().one(), this.structure10().one(), this.structure11().one(), this.structure12().one(), this.structure13().one(), this.structure14().one(), this.structure15().one(), this.structure16().one(), this.structure17().one());
   }

   static void $init$(final RingProduct17 $this) {
   }
}
