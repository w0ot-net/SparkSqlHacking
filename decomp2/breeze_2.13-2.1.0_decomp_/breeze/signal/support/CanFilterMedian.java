package breeze.signal.support;

import breeze.linalg.DenseVector;
import breeze.signal.OptOverhang;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q3q!\u0003\u0006\u0011\u0002G\u0005\u0011\u0003C\u0003\u001a\u0001\u0019\u0005!dB\u0003:\u0015!\u0005!HB\u0003\n\u0015!\u0005A\bC\u0003>\u0007\u0011\u0005a\bC\u0003@\u0007\u0011\r\u0001\tC\u0003C\u0007\u0011\r1\tC\u0003I\u0007\u0011\r\u0011\nC\u0003O\u0007\u0011\rqJA\bDC:4\u0015\u000e\u001c;fe6+G-[1o\u0015\tYA\"A\u0004tkB\u0004xN\u001d;\u000b\u00055q\u0011AB:jO:\fGNC\u0001\u0010\u0003\u0019\u0011'/Z3{K\u000e\u0001QC\u0001\n$'\t\u00011\u0003\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VMZ\u0001\u0006CB\u0004H.\u001f\u000b\u000571r3\u0007E\u0002\u001d?\u0005j\u0011!\b\u0006\u0003=9\ta\u0001\\5oC2<\u0017B\u0001\u0011\u001e\u0005-!UM\\:f-\u0016\u001cGo\u001c:\u0011\u0005\t\u001aC\u0002\u0001\u0003\u0006I\u0001\u0011\r!\n\u0002\u0006\u0013:\u0004X\u000f^\t\u0003M%\u0002\"\u0001F\u0014\n\u0005!*\"a\u0002(pi\"Lgn\u001a\t\u0003))J!aK\u000b\u0003\u0007\u0005s\u0017\u0010C\u0003.\u0003\u0001\u00071$\u0001\u0003eCR\f\u0007\"B\u0018\u0002\u0001\u0004\u0001\u0014\u0001D<j]\u0012|w\u000fT3oORD\u0007C\u0001\u000b2\u0013\t\u0011TCA\u0002J]RDQ\u0001N\u0001A\u0002U\n\u0001b\u001c<fe\"\fgn\u001a\t\u0003m]j\u0011\u0001D\u0005\u0003q1\u00111b\u00149u\u001fZ,'\u000f[1oO\u0006y1)\u00198GS2$XM]'fI&\fg\u000e\u0005\u0002<\u00075\t!b\u0005\u0002\u0004'\u00051A(\u001b8jiz\"\u0012AO\u0001\u0014IZ4\u0015\u000e\u001c;fe6+G-[1o)~Ke\u000e^\u000b\u0002\u0003B\u00191\b\u0001\u0019\u0002)\u00114h)\u001b7uKJlU\rZ5b]R{Fj\u001c8h+\u0005!\u0005cA\u001e\u0001\u000bB\u0011ACR\u0005\u0003\u000fV\u0011A\u0001T8oO\u00061BM\u001e$jYR,'/T3eS\u0006tGk\u0018#pk\ndW-F\u0001K!\rY\u0004a\u0013\t\u0003)1K!!T\u000b\u0003\r\u0011{WO\u00197f\u0003U!gOR5mi\u0016\u0014X*\u001a3jC:$vL\u00127pCR,\u0012\u0001\u0015\t\u0004w\u0001\t\u0006C\u0001\u000bS\u0013\t\u0019VCA\u0003GY>\fG\u000f"
)
public interface CanFilterMedian {
   static CanFilterMedian dvFilterMedianT_Float() {
      return CanFilterMedian$.MODULE$.dvFilterMedianT_Float();
   }

   static CanFilterMedian dvFilterMedianT_Double() {
      return CanFilterMedian$.MODULE$.dvFilterMedianT_Double();
   }

   static CanFilterMedian dvFilterMedianT_Long() {
      return CanFilterMedian$.MODULE$.dvFilterMedianT_Long();
   }

   static CanFilterMedian dvFilterMedianT_Int() {
      return CanFilterMedian$.MODULE$.dvFilterMedianT_Int();
   }

   DenseVector apply(final DenseVector data, final int windowLength, final OptOverhang overhang);
}
