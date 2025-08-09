package spire.std;

import algebra.ring.Rig;
import scala.Tuple18;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-d\u0001C\u000b\u0017!\u0003\r\t\u0001\u0007\u000e\t\u000ba\u0004A\u0011A=\t\u000bu\u0004a1\u0001@\t\u000f\u0005\u0005\u0001Ab\u0001\u0002\u0004!9\u0011q\u0001\u0001\u0007\u0004\u0005%\u0001bBA\u0007\u0001\u0019\r\u0011q\u0002\u0005\b\u0003'\u0001a1AA\u000b\u0011\u001d\tI\u0002\u0001D\u0002\u00037Aq!a\b\u0001\r\u0007\t\t\u0003C\u0004\u0002&\u00011\u0019!a\n\t\u000f\u0005-\u0002Ab\u0001\u0002.!9\u0011\u0011\u0007\u0001\u0007\u0004\u0005M\u0002bBA\u001c\u0001\u0019\r\u0011\u0011\b\u0005\b\u0003{\u0001a1AA \u0011\u001d\t\u0019\u0005\u0001D\u0002\u0003\u000bBq!!\u0013\u0001\r\u0007\tY\u0005C\u0004\u0002P\u00011\u0019!!\u0015\t\u000f\u0005U\u0003Ab\u0001\u0002X!9\u00111\f\u0001\u0007\u0004\u0005u\u0003bBA1\u0001\u0019\r\u00111\r\u0005\b\u0003O\u0002A\u0011AA5\u00051\u0011\u0016n\u001a)s_\u0012,8\r^\u00199\u0015\t9\u0002$A\u0002ti\u0012T\u0011!G\u0001\u0006gBL'/Z\u000b\u00147a\u0012U\tS&O#R;&,\u00181dM&dwN]\n\u0005\u0001q\u0011C\u000f\u0005\u0002\u001eA5\taDC\u0001 \u0003\u0015\u00198-\u00197b\u0013\t\tcD\u0001\u0004B]f\u0014VM\u001a\t\u0004GA\u001adB\u0001\u0013.\u001d\t)3F\u0004\u0002'U5\tqE\u0003\u0002)S\u00051AH]8piz\u001a\u0001!C\u0001\u001a\u0013\ta\u0003$A\u0004bY\u001e,'M]1\n\u00059z\u0013a\u00029bG.\fw-\u001a\u0006\u0003YaI!!\r\u001a\u0003\u0007IKwM\u0003\u0002/_A!R\u0004\u000e\u001cB\t\u001eSU\nU*W3r{&-\u001a5l]FL!!\u000e\u0010\u0003\u000fQ+\b\u000f\\32qA\u0011q\u0007\u000f\u0007\u0001\t\u0015I\u0004A1\u0001;\u0005\u0005\t\u0015CA\u001e?!\tiB(\u0003\u0002>=\t9aj\u001c;iS:<\u0007CA\u000f@\u0013\t\u0001eDA\u0002B]f\u0004\"a\u000e\"\u0005\u000b\r\u0003!\u0019\u0001\u001e\u0003\u0003\t\u0003\"aN#\u0005\u000b\u0019\u0003!\u0019\u0001\u001e\u0003\u0003\r\u0003\"a\u000e%\u0005\u000b%\u0003!\u0019\u0001\u001e\u0003\u0003\u0011\u0003\"aN&\u0005\u000b1\u0003!\u0019\u0001\u001e\u0003\u0003\u0015\u0003\"a\u000e(\u0005\u000b=\u0003!\u0019\u0001\u001e\u0003\u0003\u0019\u0003\"aN)\u0005\u000bI\u0003!\u0019\u0001\u001e\u0003\u0003\u001d\u0003\"a\u000e+\u0005\u000bU\u0003!\u0019\u0001\u001e\u0003\u0003!\u0003\"aN,\u0005\u000ba\u0003!\u0019\u0001\u001e\u0003\u0003%\u0003\"a\u000e.\u0005\u000bm\u0003!\u0019\u0001\u001e\u0003\u0003)\u0003\"aN/\u0005\u000by\u0003!\u0019\u0001\u001e\u0003\u0003-\u0003\"a\u000e1\u0005\u000b\u0005\u0004!\u0019\u0001\u001e\u0003\u00031\u0003\"aN2\u0005\u000b\u0011\u0004!\u0019\u0001\u001e\u0003\u00035\u0003\"a\u000e4\u0005\u000b\u001d\u0004!\u0019\u0001\u001e\u0003\u00039\u0003\"aN5\u0005\u000b)\u0004!\u0019\u0001\u001e\u0003\u0003=\u0003\"a\u000e7\u0005\u000b5\u0004!\u0019\u0001\u001e\u0003\u0003A\u0003\"aN8\u0005\u000bA\u0004!\u0019\u0001\u001e\u0003\u0003E\u0003\"a\u000e:\u0005\u000bM\u0004!\u0019\u0001\u001e\u0003\u0003I\u0003B#\u001e<7\u0003\u0012;%*\u0014)T-fcvLY3iW:\fX\"\u0001\f\n\u0005]4\"!E*f[&\u0014\u0018N\\4Qe>$Wo\u0019;2q\u00051A%\u001b8ji\u0012\"\u0012A\u001f\t\u0003;mL!\u0001 \u0010\u0003\tUs\u0017\u000e^\u0001\u000bgR\u0014Xo\u0019;ve\u0016\fT#A@\u0011\u0007\r\u0002d'\u0001\u0006tiJ,8\r^;sKJ*\"!!\u0002\u0011\u0007\r\u0002\u0014)\u0001\u0006tiJ,8\r^;sKN*\"!a\u0003\u0011\u0007\r\u0002D)\u0001\u0006tiJ,8\r^;sKR*\"!!\u0005\u0011\u0007\r\u0002t)\u0001\u0006tiJ,8\r^;sKV*\"!a\u0006\u0011\u0007\r\u0002$*\u0001\u0006tiJ,8\r^;sKZ*\"!!\b\u0011\u0007\r\u0002T*\u0001\u0006tiJ,8\r^;sK^*\"!a\t\u0011\u0007\r\u0002\u0004+\u0001\u0006tiJ,8\r^;sKb*\"!!\u000b\u0011\u0007\r\u00024+\u0001\u0006tiJ,8\r^;sKf*\"!a\f\u0011\u0007\r\u0002d+A\u0006tiJ,8\r^;sKF\u0002TCAA\u001b!\r\u0019\u0003'W\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0014'\u0006\u0002\u0002<A\u00191\u0005\r/\u0002\u0017M$(/^2ukJ,\u0017GM\u000b\u0003\u0003\u0003\u00022a\t\u0019`\u0003-\u0019HO];diV\u0014X-M\u001a\u0016\u0005\u0005\u001d\u0003cA\u00121E\u0006Y1\u000f\u001e:vGR,(/Z\u00195+\t\ti\u0005E\u0002$a\u0015\f1b\u001d;sk\u000e$XO]32kU\u0011\u00111\u000b\t\u0004GAB\u0017aC:ueV\u001cG/\u001e:fcY*\"!!\u0017\u0011\u0007\r\u00024.A\u0006tiJ,8\r^;sKF:TCAA0!\r\u0019\u0003G\\\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0004(\u0006\u0002\u0002fA\u00191\u0005M9\u0002\u0007=tW-F\u00014\u0001"
)
public interface RigProduct18 extends Rig, SemiringProduct18 {
   Rig structure1();

   Rig structure2();

   Rig structure3();

   Rig structure4();

   Rig structure5();

   Rig structure6();

   Rig structure7();

   Rig structure8();

   Rig structure9();

   Rig structure10();

   Rig structure11();

   Rig structure12();

   Rig structure13();

   Rig structure14();

   Rig structure15();

   Rig structure16();

   Rig structure17();

   Rig structure18();

   // $FF: synthetic method
   static Tuple18 one$(final RigProduct18 $this) {
      return $this.one();
   }

   default Tuple18 one() {
      return new Tuple18(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one(), this.structure8().one(), this.structure9().one(), this.structure10().one(), this.structure11().one(), this.structure12().one(), this.structure13().one(), this.structure14().one(), this.structure15().one(), this.structure16().one(), this.structure17().one(), this.structure18().one());
   }

   static void $init$(final RigProduct18 $this) {
   }
}
