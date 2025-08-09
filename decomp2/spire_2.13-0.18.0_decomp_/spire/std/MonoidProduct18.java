package spire.std;

import cats.kernel.Monoid;
import scala.Tuple18;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-d\u0001C\u000b\u0017!\u0003\r\t\u0001\u0007\u000e\t\u000ba\u0004A\u0011A=\t\u000bu\u0004a1\u0001@\t\u000f\u0005\u0005\u0001Ab\u0001\u0002\u0004!9\u0011q\u0001\u0001\u0007\u0004\u0005%\u0001bBA\u0007\u0001\u0019\r\u0011q\u0002\u0005\b\u0003'\u0001a1AA\u000b\u0011\u001d\tI\u0002\u0001D\u0002\u00037Aq!a\b\u0001\r\u0007\t\t\u0003C\u0004\u0002&\u00011\u0019!a\n\t\u000f\u0005-\u0002Ab\u0001\u0002.!9\u0011\u0011\u0007\u0001\u0007\u0004\u0005M\u0002bBA\u001c\u0001\u0019\r\u0011\u0011\b\u0005\b\u0003{\u0001a1AA \u0011\u001d\t\u0019\u0005\u0001D\u0002\u0003\u000bBq!!\u0013\u0001\r\u0007\tY\u0005C\u0004\u0002P\u00011\u0019!!\u0015\t\u000f\u0005U\u0003Ab\u0001\u0002X!9\u00111\f\u0001\u0007\u0004\u0005u\u0003bBA1\u0001\u0019\r\u00111\r\u0005\b\u0003O\u0002A\u0011AA5\u0005=iuN\\8jIB\u0013x\u000eZ;diFB$BA\f\u0019\u0003\r\u0019H\u000f\u001a\u0006\u00023\u0005)1\u000f]5sKV\u00192\u0004\u000f\"F\u0011.s\u0015\u000bV,[;\u0002\u001cg-\u001b7peN!\u0001\u0001\b\u0012u!\ti\u0002%D\u0001\u001f\u0015\u0005y\u0012!B:dC2\f\u0017BA\u0011\u001f\u0005\u0019\te.\u001f*fMB\u00191\u0005M\u001a\u000f\u0005\u0011jcBA\u0013,\u001d\t1#&D\u0001(\u0015\tA\u0013&\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005I\u0012B\u0001\u0017\u0019\u0003\u001d\tGnZ3ce\u0006L!AL\u0018\u0002\u000fA\f7m[1hK*\u0011A\u0006G\u0005\u0003cI\u0012a!T8o_&$'B\u0001\u00180!QiBGN!E\u000f*k\u0005k\u0015,Z9~\u0013W\r[6oc&\u0011QG\b\u0002\b)V\u0004H.Z\u00199!\t9\u0004\b\u0004\u0001\u0005\u000be\u0002!\u0019\u0001\u001e\u0003\u0003\u0005\u000b\"a\u000f \u0011\u0005ua\u0014BA\u001f\u001f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!H \n\u0005\u0001s\"aA!osB\u0011qG\u0011\u0003\u0006\u0007\u0002\u0011\rA\u000f\u0002\u0002\u0005B\u0011q'\u0012\u0003\u0006\r\u0002\u0011\rA\u000f\u0002\u0002\u0007B\u0011q\u0007\u0013\u0003\u0006\u0013\u0002\u0011\rA\u000f\u0002\u0002\tB\u0011qg\u0013\u0003\u0006\u0019\u0002\u0011\rA\u000f\u0002\u0002\u000bB\u0011qG\u0014\u0003\u0006\u001f\u0002\u0011\rA\u000f\u0002\u0002\rB\u0011q'\u0015\u0003\u0006%\u0002\u0011\rA\u000f\u0002\u0002\u000fB\u0011q\u0007\u0016\u0003\u0006+\u0002\u0011\rA\u000f\u0002\u0002\u0011B\u0011qg\u0016\u0003\u00061\u0002\u0011\rA\u000f\u0002\u0002\u0013B\u0011qG\u0017\u0003\u00067\u0002\u0011\rA\u000f\u0002\u0002\u0015B\u0011q'\u0018\u0003\u0006=\u0002\u0011\rA\u000f\u0002\u0002\u0017B\u0011q\u0007\u0019\u0003\u0006C\u0002\u0011\rA\u000f\u0002\u0002\u0019B\u0011qg\u0019\u0003\u0006I\u0002\u0011\rA\u000f\u0002\u0002\u001bB\u0011qG\u001a\u0003\u0006O\u0002\u0011\rA\u000f\u0002\u0002\u001dB\u0011q'\u001b\u0003\u0006U\u0002\u0011\rA\u000f\u0002\u0002\u001fB\u0011q\u0007\u001c\u0003\u0006[\u0002\u0011\rA\u000f\u0002\u0002!B\u0011qg\u001c\u0003\u0006a\u0002\u0011\rA\u000f\u0002\u0002#B\u0011qG\u001d\u0003\u0006g\u0002\u0011\rA\u000f\u0002\u0002%B!RO\u001e\u001cB\t\u001eSU\nU*W3r{&-\u001a5l]Fl\u0011AF\u0005\u0003oZ\u0011!cU3nS\u001e\u0014x.\u001e9Qe>$Wo\u0019;2q\u00051A%\u001b8ji\u0012\"\u0012A\u001f\t\u0003;mL!\u0001 \u0010\u0003\tUs\u0017\u000e^\u0001\u000bgR\u0014Xo\u0019;ve\u0016\fT#A@\u0011\u0007\r\u0002d'\u0001\u0006tiJ,8\r^;sKJ*\"!!\u0002\u0011\u0007\r\u0002\u0014)\u0001\u0006tiJ,8\r^;sKN*\"!a\u0003\u0011\u0007\r\u0002D)\u0001\u0006tiJ,8\r^;sKR*\"!!\u0005\u0011\u0007\r\u0002t)\u0001\u0006tiJ,8\r^;sKV*\"!a\u0006\u0011\u0007\r\u0002$*\u0001\u0006tiJ,8\r^;sKZ*\"!!\b\u0011\u0007\r\u0002T*\u0001\u0006tiJ,8\r^;sK^*\"!a\t\u0011\u0007\r\u0002\u0004+\u0001\u0006tiJ,8\r^;sKb*\"!!\u000b\u0011\u0007\r\u00024+\u0001\u0006tiJ,8\r^;sKf*\"!a\f\u0011\u0007\r\u0002d+A\u0006tiJ,8\r^;sKF\u0002TCAA\u001b!\r\u0019\u0003'W\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0014'\u0006\u0002\u0002<A\u00191\u0005\r/\u0002\u0017M$(/^2ukJ,\u0017GM\u000b\u0003\u0003\u0003\u00022a\t\u0019`\u0003-\u0019HO];diV\u0014X-M\u001a\u0016\u0005\u0005\u001d\u0003cA\u00121E\u0006Y1\u000f\u001e:vGR,(/Z\u00195+\t\ti\u0005E\u0002$a\u0015\f1b\u001d;sk\u000e$XO]32kU\u0011\u00111\u000b\t\u0004GAB\u0017aC:ueV\u001cG/\u001e:fcY*\"!!\u0017\u0011\u0007\r\u00024.A\u0006tiJ,8\r^;sKF:TCAA0!\r\u0019\u0003G\\\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0004(\u0006\u0002\u0002fA\u00191\u0005M9\u0002\u000b\u0015l\u0007\u000f^=\u0016\u0003M\u0002"
)
public interface MonoidProduct18 extends Monoid, SemigroupProduct18 {
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

   // $FF: synthetic method
   static Tuple18 empty$(final MonoidProduct18 $this) {
      return $this.empty();
   }

   default Tuple18 empty() {
      return new Tuple18(this.structure1().empty(), this.structure2().empty(), this.structure3().empty(), this.structure4().empty(), this.structure5().empty(), this.structure6().empty(), this.structure7().empty(), this.structure8().empty(), this.structure9().empty(), this.structure10().empty(), this.structure11().empty(), this.structure12().empty(), this.structure13().empty(), this.structure14().empty(), this.structure15().empty(), this.structure16().empty(), this.structure17().empty(), this.structure18().empty());
   }

   static void $init$(final MonoidProduct18 $this) {
   }
}
