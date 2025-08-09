package spire.std;

import cats.kernel.Monoid;
import scala.Tuple14;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mb\u0001C\t\u0013!\u0003\r\t\u0001\u0006\f\t\u000b!\u0004A\u0011A5\t\u000b5\u0004a1\u00018\t\u000bA\u0004a1A9\t\u000bM\u0004a1\u0001;\t\u000bY\u0004a1A<\t\u000be\u0004a1\u0001>\t\u000bq\u0004a1A?\t\r}\u0004a1AA\u0001\u0011\u001d\t)\u0001\u0001D\u0002\u0003\u000fAq!a\u0003\u0001\r\u0007\ti\u0001C\u0004\u0002\u0012\u00011\u0019!a\u0005\t\u000f\u0005]\u0001Ab\u0001\u0002\u001a!9\u0011Q\u0004\u0001\u0007\u0004\u0005}\u0001bBA\u0012\u0001\u0019\r\u0011Q\u0005\u0005\b\u0003S\u0001a1AA\u0016\u0011\u001d\ty\u0003\u0001C\u0001\u0003c\u0011q\"T8o_&$\u0007K]8ek\u000e$\u0018\u0007\u000e\u0006\u0003'Q\t1a\u001d;e\u0015\u0005)\u0012!B:qSJ,WcD\f5}\u0005#uIS'Q'ZKFl\u00182\u0014\t\u0001Ab\u0004\u001a\t\u00033qi\u0011A\u0007\u0006\u00027\u0005)1oY1mC&\u0011QD\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u0007}asF\u0004\u0002!S9\u0011\u0011e\n\b\u0003E\u0019j\u0011a\t\u0006\u0003I\u0015\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002+%\u0011\u0001\u0006F\u0001\bC2<WM\u0019:b\u0013\tQ3&A\u0004qC\u000e\\\u0017mZ3\u000b\u0005!\"\u0012BA\u0017/\u0005\u0019iuN\\8jI*\u0011!f\u000b\t\u00113A\u0012T\bQ\"G\u00132{%+\u0016-\\=\u0006L!!\r\u000e\u0003\u000fQ+\b\u000f\\32iA\u00111\u0007\u000e\u0007\u0001\t\u0015)\u0004A1\u00017\u0005\u0005\t\u0015CA\u001c;!\tI\u0002(\u0003\u0002:5\t9aj\u001c;iS:<\u0007CA\r<\u0013\ta$DA\u0002B]f\u0004\"a\r \u0005\u000b}\u0002!\u0019\u0001\u001c\u0003\u0003\t\u0003\"aM!\u0005\u000b\t\u0003!\u0019\u0001\u001c\u0003\u0003\r\u0003\"a\r#\u0005\u000b\u0015\u0003!\u0019\u0001\u001c\u0003\u0003\u0011\u0003\"aM$\u0005\u000b!\u0003!\u0019\u0001\u001c\u0003\u0003\u0015\u0003\"a\r&\u0005\u000b-\u0003!\u0019\u0001\u001c\u0003\u0003\u0019\u0003\"aM'\u0005\u000b9\u0003!\u0019\u0001\u001c\u0003\u0003\u001d\u0003\"a\r)\u0005\u000bE\u0003!\u0019\u0001\u001c\u0003\u0003!\u0003\"aM*\u0005\u000bQ\u0003!\u0019\u0001\u001c\u0003\u0003%\u0003\"a\r,\u0005\u000b]\u0003!\u0019\u0001\u001c\u0003\u0003)\u0003\"aM-\u0005\u000bi\u0003!\u0019\u0001\u001c\u0003\u0003-\u0003\"a\r/\u0005\u000bu\u0003!\u0019\u0001\u001c\u0003\u00031\u0003\"aM0\u0005\u000b\u0001\u0004!\u0019\u0001\u001c\u0003\u00035\u0003\"a\r2\u0005\u000b\r\u0004!\u0019\u0001\u001c\u0003\u00039\u0003\u0002#\u001a43{\u0001\u001be)\u0013'P%VC6LX1\u000e\u0003II!a\u001a\n\u0003%M+W.[4s_V\u0004\bK]8ek\u000e$\u0018\u0007N\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003)\u0004\"!G6\n\u00051T\"\u0001B+oSR\f!b\u001d;sk\u000e$XO]32+\u0005y\u0007cA\u0010-e\u0005Q1\u000f\u001e:vGR,(/\u001a\u001a\u0016\u0003I\u00042a\b\u0017>\u0003)\u0019HO];diV\u0014XmM\u000b\u0002kB\u0019q\u0004\f!\u0002\u0015M$(/^2ukJ,G'F\u0001y!\ryBfQ\u0001\u000bgR\u0014Xo\u0019;ve\u0016,T#A>\u0011\u0007}ac)\u0001\u0006tiJ,8\r^;sKZ*\u0012A \t\u0004?1J\u0015AC:ueV\u001cG/\u001e:foU\u0011\u00111\u0001\t\u0004?1b\u0015AC:ueV\u001cG/\u001e:fqU\u0011\u0011\u0011\u0002\t\u0004?1z\u0015AC:ueV\u001cG/\u001e:fsU\u0011\u0011q\u0002\t\u0004?1\u0012\u0016aC:ueV\u001cG/\u001e:fcA*\"!!\u0006\u0011\u0007}aS+A\u0006tiJ,8\r^;sKF\nTCAA\u000e!\ryB\u0006W\u0001\fgR\u0014Xo\u0019;ve\u0016\f$'\u0006\u0002\u0002\"A\u0019q\u0004L.\u0002\u0017M$(/^2ukJ,\u0017gM\u000b\u0003\u0003O\u00012a\b\u0017_\u0003-\u0019HO];diV\u0014X-\r\u001b\u0016\u0005\u00055\u0002cA\u0010-C\u0006)Q-\u001c9usV\tq\u0006"
)
public interface MonoidProduct14 extends Monoid, SemigroupProduct14 {
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

   // $FF: synthetic method
   static Tuple14 empty$(final MonoidProduct14 $this) {
      return $this.empty();
   }

   default Tuple14 empty() {
      return new Tuple14(this.structure1().empty(), this.structure2().empty(), this.structure3().empty(), this.structure4().empty(), this.structure5().empty(), this.structure6().empty(), this.structure7().empty(), this.structure8().empty(), this.structure9().empty(), this.structure10().empty(), this.structure11().empty(), this.structure12().empty(), this.structure13().empty(), this.structure14().empty());
   }

   static void $init$(final MonoidProduct14 $this) {
   }
}
