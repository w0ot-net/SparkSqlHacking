package spire.std;

import algebra.ring.Ring;
import scala.Tuple18;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005md\u0001\u0003\f\u0018!\u0003\r\t!G\u000e\t\u000be\u0004A\u0011\u0001>\t\u000by\u0004a1A@\t\u000f\u0005\r\u0001Ab\u0001\u0002\u0006!9\u0011\u0011\u0002\u0001\u0007\u0004\u0005-\u0001bBA\b\u0001\u0019\r\u0011\u0011\u0003\u0005\b\u0003+\u0001a1AA\f\u0011\u001d\tY\u0002\u0001D\u0002\u0003;Aq!!\t\u0001\r\u0007\t\u0019\u0003C\u0004\u0002(\u00011\u0019!!\u000b\t\u000f\u00055\u0002Ab\u0001\u00020!9\u00111\u0007\u0001\u0007\u0004\u0005U\u0002bBA\u001d\u0001\u0019\r\u00111\b\u0005\b\u0003\u007f\u0001a1AA!\u0011\u001d\t)\u0005\u0001D\u0002\u0003\u000fBq!a\u0013\u0001\r\u0007\ti\u0005C\u0004\u0002R\u00011\u0019!a\u0015\t\u000f\u0005]\u0003Ab\u0001\u0002Z!9\u0011Q\f\u0001\u0007\u0004\u0005}\u0003bBA2\u0001\u0019\r\u0011Q\r\u0005\b\u0003S\u0002A\u0011IA6\u0011\u001d\t9\b\u0001C\u0001\u0003s\u0012QBU5oOB\u0013x\u000eZ;diFB$B\u0001\r\u001a\u0003\r\u0019H\u000f\u001a\u0006\u00025\u0005)1\u000f]5sKV\u0019B$O\"G\u00132{%+\u0016-\\=\u0006$wM[7qgN!\u0001!H\u0012v!\tq\u0012%D\u0001 \u0015\u0005\u0001\u0013!B:dC2\f\u0017B\u0001\u0012 \u0005\u0019\te.\u001f*fMB\u0019A%\r\u001b\u000f\u0005\u0015rcB\u0001\u0014-\u001d\t93&D\u0001)\u0015\tI#&\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005Q\u0012BA\u0017\u001a\u0003\u001d\tGnZ3ce\u0006L!a\f\u0019\u0002\u000fA\f7m[1hK*\u0011Q&G\u0005\u0003eM\u0012AAU5oO*\u0011q\u0006\r\t\u0015=U:$)\u0012%L\u001dF#vKW/aG\u001aLGn\u001c:\n\u0005Yz\"a\u0002+va2,\u0017\u0007\u000f\t\u0003qeb\u0001\u0001B\u0003;\u0001\t\u00071HA\u0001B#\tat\b\u0005\u0002\u001f{%\u0011ah\b\u0002\b\u001d>$\b.\u001b8h!\tq\u0002)\u0003\u0002B?\t\u0019\u0011I\\=\u0011\u0005a\u001aE!\u0002#\u0001\u0005\u0004Y$!\u0001\"\u0011\u0005a2E!B$\u0001\u0005\u0004Y$!A\"\u0011\u0005aJE!\u0002&\u0001\u0005\u0004Y$!\u0001#\u0011\u0005abE!B'\u0001\u0005\u0004Y$!A#\u0011\u0005azE!\u0002)\u0001\u0005\u0004Y$!\u0001$\u0011\u0005a\u0012F!B*\u0001\u0005\u0004Y$!A$\u0011\u0005a*F!\u0002,\u0001\u0005\u0004Y$!\u0001%\u0011\u0005aBF!B-\u0001\u0005\u0004Y$!A%\u0011\u0005aZF!\u0002/\u0001\u0005\u0004Y$!\u0001&\u0011\u0005arF!B0\u0001\u0005\u0004Y$!A&\u0011\u0005a\nG!\u00022\u0001\u0005\u0004Y$!\u0001'\u0011\u0005a\"G!B3\u0001\u0005\u0004Y$!A'\u0011\u0005a:G!\u00025\u0001\u0005\u0004Y$!\u0001(\u0011\u0005aRG!B6\u0001\u0005\u0004Y$!A(\u0011\u0005ajG!\u00028\u0001\u0005\u0004Y$!\u0001)\u0011\u0005a\u0002H!B9\u0001\u0005\u0004Y$!A)\u0011\u0005a\u001aH!\u0002;\u0001\u0005\u0004Y$!\u0001*\u0011)Y<xGQ#I\u0017:\u000bFk\u0016.^A\u000e4\u0017\u000e\\8s\u001b\u00059\u0012B\u0001=\u0018\u00051\u0011fn\u001a)s_\u0012,8\r^\u00199\u0003\u0019!\u0013N\\5uIQ\t1\u0010\u0005\u0002\u001fy&\u0011Qp\b\u0002\u0005+:LG/\u0001\u0006tiJ,8\r^;sKF*\"!!\u0001\u0011\u0007\u0011\nt'\u0001\u0006tiJ,8\r^;sKJ*\"!a\u0002\u0011\u0007\u0011\n$)\u0001\u0006tiJ,8\r^;sKN*\"!!\u0004\u0011\u0007\u0011\nT)\u0001\u0006tiJ,8\r^;sKR*\"!a\u0005\u0011\u0007\u0011\n\u0004*\u0001\u0006tiJ,8\r^;sKV*\"!!\u0007\u0011\u0007\u0011\n4*\u0001\u0006tiJ,8\r^;sKZ*\"!a\b\u0011\u0007\u0011\nd*\u0001\u0006tiJ,8\r^;sK^*\"!!\n\u0011\u0007\u0011\n\u0014+\u0001\u0006tiJ,8\r^;sKb*\"!a\u000b\u0011\u0007\u0011\nD+\u0001\u0006tiJ,8\r^;sKf*\"!!\r\u0011\u0007\u0011\nt+A\u0006tiJ,8\r^;sKF\u0002TCAA\u001c!\r!\u0013GW\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0014'\u0006\u0002\u0002>A\u0019A%M/\u0002\u0017M$(/^2ukJ,\u0017GM\u000b\u0003\u0003\u0007\u00022\u0001J\u0019a\u0003-\u0019HO];diV\u0014X-M\u001a\u0016\u0005\u0005%\u0003c\u0001\u00132G\u0006Y1\u000f\u001e:vGR,(/Z\u00195+\t\ty\u0005E\u0002%c\u0019\f1b\u001d;sk\u000e$XO]32kU\u0011\u0011Q\u000b\t\u0004IEJ\u0017aC:ueV\u001cG/\u001e:fcY*\"!a\u0017\u0011\u0007\u0011\nD.A\u0006tiJ,8\r^;sKF:TCAA1!\r!\u0013g\\\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0004(\u0006\u0002\u0002hA\u0019A%\r:\u0002\u000f\u0019\u0014x.\\%oiR\u0019A'!\u001c\t\u000f\u0005=D\u00031\u0001\u0002r\u0005\u0011\u0001\u0010\r\t\u0004=\u0005M\u0014bAA;?\t\u0019\u0011J\u001c;\u0002\u0007=tW-F\u00015\u0001"
)
public interface RingProduct18 extends Ring, RngProduct18 {
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

   // $FF: synthetic method
   static Tuple18 fromInt$(final RingProduct18 $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple18 fromInt(final int x0) {
      return new Tuple18(this.structure1().fromInt(x0), this.structure2().fromInt(x0), this.structure3().fromInt(x0), this.structure4().fromInt(x0), this.structure5().fromInt(x0), this.structure6().fromInt(x0), this.structure7().fromInt(x0), this.structure8().fromInt(x0), this.structure9().fromInt(x0), this.structure10().fromInt(x0), this.structure11().fromInt(x0), this.structure12().fromInt(x0), this.structure13().fromInt(x0), this.structure14().fromInt(x0), this.structure15().fromInt(x0), this.structure16().fromInt(x0), this.structure17().fromInt(x0), this.structure18().fromInt(x0));
   }

   // $FF: synthetic method
   static Tuple18 one$(final RingProduct18 $this) {
      return $this.one();
   }

   default Tuple18 one() {
      return new Tuple18(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one(), this.structure8().one(), this.structure9().one(), this.structure10().one(), this.structure11().one(), this.structure12().one(), this.structure13().one(), this.structure14().one(), this.structure15().one(), this.structure16().one(), this.structure17().one(), this.structure18().one());
   }

   static void $init$(final RingProduct18 $this) {
   }
}
