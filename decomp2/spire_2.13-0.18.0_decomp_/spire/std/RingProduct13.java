package spire.std;

import algebra.ring.Ring;
import scala.Tuple13;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ub\u0001C\t\u0013!\u0003\r\t\u0001\u0006\f\t\u000b\u0015\u0004A\u0011\u00014\t\u000b)\u0004a1A6\t\u000b5\u0004a1\u00018\t\u000bA\u0004a1A9\t\u000bM\u0004a1\u0001;\t\u000bY\u0004a1A<\t\u000be\u0004a1\u0001>\t\u000bq\u0004a1A?\t\r}\u0004a1AA\u0001\u0011\u001d\t)\u0001\u0001D\u0002\u0003\u000fAq!a\u0003\u0001\r\u0007\ti\u0001C\u0004\u0002\u0012\u00011\u0019!a\u0005\t\u000f\u0005]\u0001Ab\u0001\u0002\u001a!9\u0011Q\u0004\u0001\u0007\u0004\u0005}\u0001bBA\u0012\u0001\u0011\u0005\u0013Q\u0005\u0005\b\u0003c\u0001A\u0011AA\u001a\u00055\u0011\u0016N\\4Qe>$Wo\u0019;2g)\u00111\u0003F\u0001\u0004gR$'\"A\u000b\u0002\u000bM\u0004\u0018N]3\u0016\u001d]!d(\u0011#H\u00156\u00036KV-]?N!\u0001\u0001\u0007\u0010b!\tIB$D\u0001\u001b\u0015\u0005Y\u0012!B:dC2\f\u0017BA\u000f\u001b\u0005\u0019\te.\u001f*fMB\u0019q\u0004L\u0018\u000f\u0005\u0001JcBA\u0011(\u001d\t\u0011c%D\u0001$\u0015\t!S%\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005)\u0012B\u0001\u0015\u0015\u0003\u001d\tGnZ3ce\u0006L!AK\u0016\u0002\u000fA\f7m[1hK*\u0011\u0001\u0006F\u0005\u0003[9\u0012AAU5oO*\u0011!f\u000b\t\u00103A\u0012T\bQ\"G\u00132{%+\u0016-\\=&\u0011\u0011G\u0007\u0002\b)V\u0004H.Z\u00194!\t\u0019D\u0007\u0004\u0001\u0005\u000bU\u0002!\u0019\u0001\u001c\u0003\u0003\u0005\u000b\"a\u000e\u001e\u0011\u0005eA\u0014BA\u001d\u001b\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!G\u001e\n\u0005qR\"aA!osB\u00111G\u0010\u0003\u0006\u007f\u0001\u0011\rA\u000e\u0002\u0002\u0005B\u00111'\u0011\u0003\u0006\u0005\u0002\u0011\rA\u000e\u0002\u0002\u0007B\u00111\u0007\u0012\u0003\u0006\u000b\u0002\u0011\rA\u000e\u0002\u0002\tB\u00111g\u0012\u0003\u0006\u0011\u0002\u0011\rA\u000e\u0002\u0002\u000bB\u00111G\u0013\u0003\u0006\u0017\u0002\u0011\rA\u000e\u0002\u0002\rB\u00111'\u0014\u0003\u0006\u001d\u0002\u0011\rA\u000e\u0002\u0002\u000fB\u00111\u0007\u0015\u0003\u0006#\u0002\u0011\rA\u000e\u0002\u0002\u0011B\u00111g\u0015\u0003\u0006)\u0002\u0011\rA\u000e\u0002\u0002\u0013B\u00111G\u0016\u0003\u0006/\u0002\u0011\rA\u000e\u0002\u0002\u0015B\u00111'\u0017\u0003\u00065\u0002\u0011\rA\u000e\u0002\u0002\u0017B\u00111\u0007\u0018\u0003\u0006;\u0002\u0011\rA\u000e\u0002\u0002\u0019B\u00111g\u0018\u0003\u0006A\u0002\u0011\rA\u000e\u0002\u0002\u001bBy!m\u0019\u001a>\u0001\u000e3\u0015\nT(S+b[f,D\u0001\u0013\u0013\t!'C\u0001\u0007S]\u001e\u0004&o\u001c3vGR\f4'\u0001\u0004%S:LG\u000f\n\u000b\u0002OB\u0011\u0011\u0004[\u0005\u0003Sj\u0011A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u00031\u00042a\b\u00173\u0003)\u0019HO];diV\u0014XMM\u000b\u0002_B\u0019q\u0004L\u001f\u0002\u0015M$(/^2ukJ,7'F\u0001s!\ryB\u0006Q\u0001\u000bgR\u0014Xo\u0019;ve\u0016$T#A;\u0011\u0007}a3)\u0001\u0006tiJ,8\r^;sKV*\u0012\u0001\u001f\t\u0004?12\u0015AC:ueV\u001cG/\u001e:fmU\t1\u0010E\u0002 Y%\u000b!b\u001d;sk\u000e$XO]38+\u0005q\bcA\u0010-\u0019\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0005\u0005\r\u0001cA\u0010-\u001f\u0006Q1\u000f\u001e:vGR,(/Z\u001d\u0016\u0005\u0005%\u0001cA\u0010-%\u0006Y1\u000f\u001e:vGR,(/Z\u00191+\t\ty\u0001E\u0002 YU\u000b1b\u001d;sk\u000e$XO]32cU\u0011\u0011Q\u0003\t\u0004?1B\u0016aC:ueV\u001cG/\u001e:fcI*\"!a\u0007\u0011\u0007}a3,A\u0006tiJ,8\r^;sKF\u001aTCAA\u0011!\ryBFX\u0001\bMJ|W.\u00138u)\ry\u0013q\u0005\u0005\b\u0003Sy\u0001\u0019AA\u0016\u0003\tA\b\u0007E\u0002\u001a\u0003[I1!a\f\u001b\u0005\rIe\u000e^\u0001\u0004_:,W#A\u0018"
)
public interface RingProduct13 extends Ring, RngProduct13 {
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

   // $FF: synthetic method
   static Tuple13 fromInt$(final RingProduct13 $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple13 fromInt(final int x0) {
      return new Tuple13(this.structure1().fromInt(x0), this.structure2().fromInt(x0), this.structure3().fromInt(x0), this.structure4().fromInt(x0), this.structure5().fromInt(x0), this.structure6().fromInt(x0), this.structure7().fromInt(x0), this.structure8().fromInt(x0), this.structure9().fromInt(x0), this.structure10().fromInt(x0), this.structure11().fromInt(x0), this.structure12().fromInt(x0), this.structure13().fromInt(x0));
   }

   // $FF: synthetic method
   static Tuple13 one$(final RingProduct13 $this) {
      return $this.one();
   }

   default Tuple13 one() {
      return new Tuple13(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one(), this.structure8().one(), this.structure9().one(), this.structure10().one(), this.structure11().one(), this.structure12().one(), this.structure13().one());
   }

   static void $init$(final RingProduct13 $this) {
   }
}
