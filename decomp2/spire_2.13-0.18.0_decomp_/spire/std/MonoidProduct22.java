package spire.std;

import cats.kernel.Monoid;
import scala.Tuple22;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rf\u0001C\r\u001b!\u0003\r\t\u0001\b\u0010\t\u000f\u0005E\u0001\u0001\"\u0001\u0002\u0014!9\u00111\u0004\u0001\u0007\u0004\u0005u\u0001bBA\u0011\u0001\u0019\r\u00111\u0005\u0005\b\u0003O\u0001a1AA\u0015\u0011\u001d\ti\u0003\u0001D\u0002\u0003_Aq!a\r\u0001\r\u0007\t)\u0004C\u0004\u0002:\u00011\u0019!a\u000f\t\u000f\u0005}\u0002Ab\u0001\u0002B!9\u0011Q\t\u0001\u0007\u0004\u0005\u001d\u0003bBA&\u0001\u0019\r\u0011Q\n\u0005\b\u0003#\u0002a1AA*\u0011\u001d\t9\u0006\u0001D\u0002\u00033Bq!!\u0018\u0001\r\u0007\ty\u0006C\u0004\u0002d\u00011\u0019!!\u001a\t\u000f\u0005%\u0004Ab\u0001\u0002l!9\u0011q\u000e\u0001\u0007\u0004\u0005E\u0004bBA;\u0001\u0019\r\u0011q\u000f\u0005\b\u0003w\u0002a1AA?\u0011\u001d\t\t\t\u0001D\u0002\u0003\u0007Cq!a\"\u0001\r\u0007\tI\tC\u0004\u0002\u000e\u00021\u0019!a$\t\u000f\u0005M\u0005Ab\u0001\u0002\u0016\"9\u0011\u0011\u0014\u0001\u0007\u0004\u0005m\u0005bBAP\u0001\u0011\u0005\u0011\u0011\u0015\u0002\u0010\u001b>tw.\u001b3Qe>$Wo\u0019;3e)\u00111\u0004H\u0001\u0004gR$'\"A\u000f\u0002\u000bM\u0004\u0018N]3\u00161}ad)\u0013'P%VC6LX1eO*l\u0007o\u001d<zy~\f)aE\u0003\u0001A\u0019\nI\u0001\u0005\u0002\"I5\t!EC\u0001$\u0003\u0015\u00198-\u00197b\u0013\t)#E\u0001\u0004B]f\u0014VM\u001a\t\u0004OQ:dB\u0001\u00152\u001d\tIsF\u0004\u0002+]5\t1F\u0003\u0002-[\u00051AH]8piz\u001a\u0001!C\u0001\u001e\u0013\t\u0001D$A\u0004bY\u001e,'M]1\n\u0005I\u001a\u0014a\u00029bG.\fw-\u001a\u0006\u0003aqI!!\u000e\u001c\u0003\r5{gn\\5e\u0015\t\u00114\u0007E\r\"qi*\u0005j\u0013(R)^SV\fY2gS2|'/\u001e=|}\u0006\r\u0011BA\u001d#\u0005\u001d!V\u000f\u001d7feI\u0002\"a\u000f\u001f\r\u0001\u0011)Q\b\u0001b\u0001}\t\t\u0011)\u0005\u0002@\u0005B\u0011\u0011\u0005Q\u0005\u0003\u0003\n\u0012qAT8uQ&tw\r\u0005\u0002\"\u0007&\u0011AI\t\u0002\u0004\u0003:L\bCA\u001eG\t\u00159\u0005A1\u0001?\u0005\u0005\u0011\u0005CA\u001eJ\t\u0015Q\u0005A1\u0001?\u0005\u0005\u0019\u0005CA\u001eM\t\u0015i\u0005A1\u0001?\u0005\u0005!\u0005CA\u001eP\t\u0015\u0001\u0006A1\u0001?\u0005\u0005)\u0005CA\u001eS\t\u0015\u0019\u0006A1\u0001?\u0005\u00051\u0005CA\u001eV\t\u00151\u0006A1\u0001?\u0005\u00059\u0005CA\u001eY\t\u0015I\u0006A1\u0001?\u0005\u0005A\u0005CA\u001e\\\t\u0015a\u0006A1\u0001?\u0005\u0005I\u0005CA\u001e_\t\u0015y\u0006A1\u0001?\u0005\u0005Q\u0005CA\u001eb\t\u0015\u0011\u0007A1\u0001?\u0005\u0005Y\u0005CA\u001ee\t\u0015)\u0007A1\u0001?\u0005\u0005a\u0005CA\u001eh\t\u0015A\u0007A1\u0001?\u0005\u0005i\u0005CA\u001ek\t\u0015Y\u0007A1\u0001?\u0005\u0005q\u0005CA\u001en\t\u0015q\u0007A1\u0001?\u0005\u0005y\u0005CA\u001eq\t\u0015\t\bA1\u0001?\u0005\u0005\u0001\u0006CA\u001et\t\u0015!\bA1\u0001?\u0005\u0005\t\u0006CA\u001ew\t\u00159\bA1\u0001?\u0005\u0005\u0011\u0006CA\u001ez\t\u0015Q\bA1\u0001?\u0005\u0005\u0019\u0006CA\u001e}\t\u0015i\bA1\u0001?\u0005\u0005!\u0006CA\u001e\u0000\t\u0019\t\t\u0001\u0001b\u0001}\t\tQ\u000bE\u0002<\u0003\u000b!a!a\u0002\u0001\u0005\u0004q$!\u0001,\u00117\u0005-\u0011Q\u0002\u001eF\u0011.s\u0015\u000bV,[;\u0002\u001cg-\u001b7peVD8P`A\u0002\u001b\u0005Q\u0012bAA\b5\t\u00112+Z7jOJ|W\u000f\u001d)s_\u0012,8\r\u001e\u001a3\u0003\u0019!\u0013N\\5uIQ\u0011\u0011Q\u0003\t\u0004C\u0005]\u0011bAA\rE\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0003\u0003?\u00012a\n\u001b;\u0003)\u0019HO];diV\u0014XMM\u000b\u0003\u0003K\u00012a\n\u001bF\u0003)\u0019HO];diV\u0014XmM\u000b\u0003\u0003W\u00012a\n\u001bI\u0003)\u0019HO];diV\u0014X\rN\u000b\u0003\u0003c\u00012a\n\u001bL\u0003)\u0019HO];diV\u0014X-N\u000b\u0003\u0003o\u00012a\n\u001bO\u0003)\u0019HO];diV\u0014XMN\u000b\u0003\u0003{\u00012a\n\u001bR\u0003)\u0019HO];diV\u0014XmN\u000b\u0003\u0003\u0007\u00022a\n\u001bU\u0003)\u0019HO];diV\u0014X\rO\u000b\u0003\u0003\u0013\u00022a\n\u001bX\u0003)\u0019HO];diV\u0014X-O\u000b\u0003\u0003\u001f\u00022a\n\u001b[\u0003-\u0019HO];diV\u0014X-\r\u0019\u0016\u0005\u0005U\u0003cA\u00145;\u0006Y1\u000f\u001e:vGR,(/Z\u00192+\t\tY\u0006E\u0002(i\u0001\f1b\u001d;sk\u000e$XO]32eU\u0011\u0011\u0011\r\t\u0004OQ\u001a\u0017aC:ueV\u001cG/\u001e:fcM*\"!a\u001a\u0011\u0007\u001d\"d-A\u0006tiJ,8\r^;sKF\"TCAA7!\r9C'[\u0001\fgR\u0014Xo\u0019;ve\u0016\fT'\u0006\u0002\u0002tA\u0019q\u0005\u000e7\u0002\u0017M$(/^2ukJ,\u0017GN\u000b\u0003\u0003s\u00022a\n\u001bp\u0003-\u0019HO];diV\u0014X-M\u001c\u0016\u0005\u0005}\u0004cA\u00145e\u0006Y1\u000f\u001e:vGR,(/Z\u00199+\t\t)\tE\u0002(iU\f1b\u001d;sk\u000e$XO]32sU\u0011\u00111\u0012\t\u0004OQB\u0018aC:ueV\u001cG/\u001e:feA*\"!!%\u0011\u0007\u001d\"40A\u0006tiJ,8\r^;sKJ\nTCAAL!\r9CG`\u0001\fgR\u0014Xo\u0019;ve\u0016\u0014$'\u0006\u0002\u0002\u001eB!q\u0005NA\u0002\u0003\u0015)W\u000e\u001d;z+\u00059\u0004"
)
public interface MonoidProduct22 extends Monoid, SemigroupProduct22 {
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

   Monoid structure20();

   Monoid structure21();

   Monoid structure22();

   // $FF: synthetic method
   static Tuple22 empty$(final MonoidProduct22 $this) {
      return $this.empty();
   }

   default Tuple22 empty() {
      return new Tuple22(this.structure1().empty(), this.structure2().empty(), this.structure3().empty(), this.structure4().empty(), this.structure5().empty(), this.structure6().empty(), this.structure7().empty(), this.structure8().empty(), this.structure9().empty(), this.structure10().empty(), this.structure11().empty(), this.structure12().empty(), this.structure13().empty(), this.structure14().empty(), this.structure15().empty(), this.structure16().empty(), this.structure17().empty(), this.structure18().empty(), this.structure19().empty(), this.structure20().empty(), this.structure21().empty(), this.structure22().empty());
   }

   static void $init$(final MonoidProduct22 $this) {
   }
}
