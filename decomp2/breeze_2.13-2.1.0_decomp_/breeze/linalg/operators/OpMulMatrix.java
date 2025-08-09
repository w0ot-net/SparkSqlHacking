package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.math.Semiring;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m:Q\u0001B\u0003\t\u000211QAD\u0003\t\u0002=AQaH\u0001\u0005\u0002\u0001BQ!I\u0001\u0005\u0004\t\n1b\u00149Nk2l\u0015\r\u001e:jq*\u0011aaB\u0001\n_B,'/\u0019;peNT!\u0001C\u0005\u0002\r1Lg.\u00197h\u0015\u0005Q\u0011A\u00022sK\u0016TXm\u0001\u0001\u0011\u00055\tQ\"A\u0003\u0003\u0017=\u0003X*\u001e7NCR\u0014\u0018\u000e_\n\u0005\u0003A1\u0012\u0004\u0005\u0002\u0012)5\t!CC\u0001\u0014\u0003\u0015\u00198-\u00197b\u0013\t)\"C\u0001\u0004B]f\u0014VM\u001a\t\u0003\u001b]I!\u0001G\u0003\u0003\r=\u0003H+\u001f9f!\tQR$D\u0001\u001c\u0015\ta\u0012\"A\u0004hK:,'/[2\n\u0005yY\"!B+Gk:\u001c\u0017A\u0002\u001fj]&$h\bF\u0001\r\u0003]y\u0007/T;m\u001b\u0006$(/\u001b=Ge>l7+Z7je&tw-\u0006\u0002$UQ\u0011Ae\r\t\u0006K\u0019B\u0003\u0006K\u0007\u0002\u0003%\u0011q%\b\u0002\u0006\u00136\u0004HN\r\t\u0003S)b\u0001\u0001B\u0003,\u0007\t\u0007AFA\u0001T#\ti\u0003\u0007\u0005\u0002\u0012]%\u0011qF\u0005\u0002\b\u001d>$\b.\u001b8h!\t\t\u0012'\u0003\u00023%\t\u0019\u0011I\\=\t\u000fQ\u001a\u0011\u0011!a\u0002k\u0005YQM^5eK:\u001cW\rJ\u00194!\r1\u0014\bK\u0007\u0002o)\u0011\u0001(C\u0001\u0005[\u0006$\b.\u0003\u0002;o\tA1+Z7je&tw\r"
)
public final class OpMulMatrix {
   public static UFunc.UImpl2 opMulMatrixFromSemiring(final Semiring evidence$13) {
      return OpMulMatrix$.MODULE$.opMulMatrixFromSemiring(evidence$13);
   }

   public static Object withSink(final Object s) {
      return OpMulMatrix$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return OpMulMatrix$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return OpMulMatrix$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return OpMulMatrix$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return OpMulMatrix$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return OpMulMatrix$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return OpMulMatrix$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return OpMulMatrix$.MODULE$.apply(v, impl);
   }
}
