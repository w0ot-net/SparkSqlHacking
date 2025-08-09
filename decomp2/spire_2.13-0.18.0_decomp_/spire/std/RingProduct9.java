package spire.std;

import algebra.ring.Ring;
import scala.Tuple9;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y4\u0001\"\u0004\b\u0011\u0002\u0007\u0005\u0001C\u0005\u0005\u0006+\u0002!\tA\u0016\u0005\u00065\u00021\u0019a\u0017\u0005\u0006;\u00021\u0019A\u0018\u0005\u0006A\u00021\u0019!\u0019\u0005\u0006G\u00021\u0019\u0001\u001a\u0005\u0006M\u00021\u0019a\u001a\u0005\u0006S\u00021\u0019A\u001b\u0005\u0006Y\u00021\u0019!\u001c\u0005\u0006_\u00021\u0019\u0001\u001d\u0005\u0006e\u00021\u0019a\u001d\u0005\u0006k\u0002!\tE\u001e\u0005\u0006y\u0002!\t! \u0002\r%&tw\r\u0015:pIV\u001cG/\u000f\u0006\u0003\u001fA\t1a\u001d;e\u0015\u0005\t\u0012!B:qSJ,WCC\n1uu\u00025IR%M\u001fN!\u0001\u0001\u0006\u000eR!\t)\u0002$D\u0001\u0017\u0015\u00059\u0012!B:dC2\f\u0017BA\r\u0017\u0005\u0019\te.\u001f*fMB\u00191\u0004K\u0016\u000f\u0005q)cBA\u000f$\u001d\tq\"%D\u0001 \u0015\t\u0001\u0013%\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005\t\u0012B\u0001\u0013\u0011\u0003\u001d\tGnZ3ce\u0006L!AJ\u0014\u0002\u000fA\f7m[1hK*\u0011A\u0005E\u0005\u0003S)\u0012AAU5oO*\u0011ae\n\t\f+1r\u0013\bP C\u000b\"[e*\u0003\u0002.-\t1A+\u001e9mKf\u0002\"a\f\u0019\r\u0001\u0011)\u0011\u0007\u0001b\u0001e\t\t\u0011)\u0005\u00024mA\u0011Q\u0003N\u0005\u0003kY\u0011qAT8uQ&tw\r\u0005\u0002\u0016o%\u0011\u0001H\u0006\u0002\u0004\u0003:L\bCA\u0018;\t\u0015Y\u0004A1\u00013\u0005\u0005\u0011\u0005CA\u0018>\t\u0015q\u0004A1\u00013\u0005\u0005\u0019\u0005CA\u0018A\t\u0015\t\u0005A1\u00013\u0005\u0005!\u0005CA\u0018D\t\u0015!\u0005A1\u00013\u0005\u0005)\u0005CA\u0018G\t\u00159\u0005A1\u00013\u0005\u00051\u0005CA\u0018J\t\u0015Q\u0005A1\u00013\u0005\u00059\u0005CA\u0018M\t\u0015i\u0005A1\u00013\u0005\u0005A\u0005CA\u0018P\t\u0015\u0001\u0006A1\u00013\u0005\u0005I\u0005c\u0003*T]ebtHQ#I\u0017:k\u0011AD\u0005\u0003):\u00111B\u00158h!J|G-^2us\u00051A%\u001b8ji\u0012\"\u0012a\u0016\t\u0003+aK!!\u0017\f\u0003\tUs\u0017\u000e^\u0001\u000bgR\u0014Xo\u0019;ve\u0016\fT#\u0001/\u0011\u0007mAc&\u0001\u0006tiJ,8\r^;sKJ*\u0012a\u0018\t\u00047!J\u0014AC:ueV\u001cG/\u001e:fgU\t!\rE\u0002\u001cQq\n!b\u001d;sk\u000e$XO]35+\u0005)\u0007cA\u000e)\u007f\u0005Q1\u000f\u001e:vGR,(/Z\u001b\u0016\u0003!\u00042a\u0007\u0015C\u0003)\u0019HO];diV\u0014XMN\u000b\u0002WB\u00191\u0004K#\u0002\u0015M$(/^2ukJ,w'F\u0001o!\rY\u0002\u0006S\u0001\u000bgR\u0014Xo\u0019;ve\u0016DT#A9\u0011\u0007mA3*\u0001\u0006tiJ,8\r^;sKf*\u0012\u0001\u001e\t\u00047!r\u0015a\u00024s_6Le\u000e\u001e\u000b\u0003W]DQ\u0001_\u0006A\u0002e\f!\u0001\u001f\u0019\u0011\u0005UQ\u0018BA>\u0017\u0005\rIe\u000e^\u0001\u0004_:,W#A\u0016"
)
public interface RingProduct9 extends Ring, RngProduct9 {
   Ring structure1();

   Ring structure2();

   Ring structure3();

   Ring structure4();

   Ring structure5();

   Ring structure6();

   Ring structure7();

   Ring structure8();

   Ring structure9();

   // $FF: synthetic method
   static Tuple9 fromInt$(final RingProduct9 $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple9 fromInt(final int x0) {
      return new Tuple9(this.structure1().fromInt(x0), this.structure2().fromInt(x0), this.structure3().fromInt(x0), this.structure4().fromInt(x0), this.structure5().fromInt(x0), this.structure6().fromInt(x0), this.structure7().fromInt(x0), this.structure8().fromInt(x0), this.structure9().fromInt(x0));
   }

   // $FF: synthetic method
   static Tuple9 one$(final RingProduct9 $this) {
      return $this.one();
   }

   default Tuple9 one() {
      return new Tuple9(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one(), this.structure8().one(), this.structure9().one());
   }

   static void $init$(final RingProduct9 $this) {
   }
}
