package breeze.stats;

import breeze.generic.UFunc;
import breeze.linalg.support.CanTraverseValues;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y;QAB\u0004\t\u000211QAD\u0004\t\u0002=AQ\u0001H\u0001\u0005\u0002uAQAH\u0001\u0005\u0004}AQaQ\u0001\u0005\u0004\u0011CQAU\u0001\u0005\u0004M\u000b!#Y2dk6,H.\u0019;f\u0003:$7i\\;oi*\u0011\u0001\"C\u0001\u0006gR\fGo\u001d\u0006\u0002\u0015\u00051!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002\u000e\u00035\tqA\u0001\nbG\u000e,X.\u001e7bi\u0016\fe\u000eZ\"pk:$8cA\u0001\u0011-A\u0011\u0011\u0003F\u0007\u0002%)\t1#A\u0003tG\u0006d\u0017-\u0003\u0002\u0016%\t1\u0011I\\=SK\u001a\u0004\"a\u0006\u000e\u000e\u0003aQ!!G\u0005\u0002\u000f\u001d,g.\u001a:jG&\u00111\u0004\u0007\u0002\u0006+\u001a+hnY\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00031\tQB]3ek\u000e,w\fR8vE2,WC\u0001\u0011()\t\t\u0013\b\u0005\u0003#G\u0015\u0002T\"A\u0001\n\u0005\u0011R\"\u0001B%na2\u0004\"AJ\u0014\r\u0001\u0011)\u0001f\u0001b\u0001S\t\tA+\u0005\u0002+[A\u0011\u0011cK\u0005\u0003YI\u0011qAT8uQ&tw\r\u0005\u0002\u0012]%\u0011qF\u0005\u0002\u0004\u0003:L\b\u0003B\t2gYJ!A\r\n\u0003\rQ+\b\u000f\\33!\t\tB'\u0003\u00026%\t1Ai\\;cY\u0016\u0004\"!E\u001c\n\u0005a\u0012\"aA%oi\")!h\u0001a\u0002w\u0005!\u0011\u000e^3s!\u0011a\u0014)J\u001a\u000e\u0003uR!AP \u0002\u000fM,\b\u000f]8si*\u0011\u0001)C\u0001\u0007Y&t\u0017\r\\4\n\u0005\tk$!E\"b]R\u0013\u0018M^3sg\u00164\u0016\r\\;fg\u0006q!/\u001a3vG\u0016|6i\\7qY\u0016DXCA#I)\t1\u0005\u000b\u0005\u0003#G\u001dK\u0005C\u0001\u0014I\t\u0015ACA1\u0001*!\u0011\t\u0012G\u0013\u001c\u0011\u0005-sU\"\u0001'\u000b\u00055K\u0011\u0001B7bi\"L!a\u0014'\u0003\u000f\r{W\u000e\u001d7fq\")!\b\u0002a\u0002#B!A(Q$K\u00031\u0011X\rZ;dK~3En\\1u+\t!v\u000b\u0006\u0002V9B!!e\t,Y!\t1s\u000bB\u0003)\u000b\t\u0007\u0011\u0006\u0005\u0003\u0012ce3\u0004CA\t[\u0013\tY&CA\u0003GY>\fG\u000fC\u0003;\u000b\u0001\u000fQ\f\u0005\u0003=\u0003ZK\u0006"
)
public final class accumulateAndCount {
   public static UFunc.UImpl reduce_Float(final CanTraverseValues iter) {
      return accumulateAndCount$.MODULE$.reduce_Float(iter);
   }

   public static UFunc.UImpl reduce_Complex(final CanTraverseValues iter) {
      return accumulateAndCount$.MODULE$.reduce_Complex(iter);
   }

   public static UFunc.UImpl reduce_Double(final CanTraverseValues iter) {
      return accumulateAndCount$.MODULE$.reduce_Double(iter);
   }

   public static Object withSink(final Object s) {
      return accumulateAndCount$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return accumulateAndCount$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return accumulateAndCount$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return accumulateAndCount$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return accumulateAndCount$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return accumulateAndCount$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return accumulateAndCount$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return accumulateAndCount$.MODULE$.apply(v, impl);
   }
}
