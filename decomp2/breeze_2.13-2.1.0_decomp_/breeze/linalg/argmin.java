package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019<Qa\u0002\u0005\t\u000251Qa\u0004\u0005\t\u0002AAQ!H\u0001\u0005\u0002yAQaH\u0001\u0005\u0004\u0001BQaP\u0001\u0005\u0004\u0001CQ\u0001T\u0001\u0005\u00045CQ!W\u0001\u0005\u0004i\u000ba!\u0019:h[&t'BA\u0005\u000b\u0003\u0019a\u0017N\\1mO*\t1\"\u0001\u0004ce\u0016,'0Z\u0002\u0001!\tq\u0011!D\u0001\t\u0005\u0019\t'oZ7j]N\u0019\u0011!E\f\u0011\u0005I)R\"A\n\u000b\u0003Q\tQa]2bY\u0006L!AF\n\u0003\r\u0005s\u0017PU3g!\tA2$D\u0001\u001a\u0015\tQ\"\"A\u0004hK:,'/[2\n\u0005qI\"!B+Gk:\u001c\u0017A\u0002\u001fj]&$h\bF\u0001\u000e\u0003)\u0011X\rZ;dK~Ke\u000e^\u000b\u0004C!\u0012DC\u0001\u00125!\u0011\u0019CEJ\u0019\u000e\u0003\u0005I!!J\u000e\u0003\t%k\u0007\u000f\u001c\t\u0003O!b\u0001\u0001B\u0003*\u0007\t\u0007!FA\u0001U#\tYc\u0006\u0005\u0002\u0013Y%\u0011Qf\u0005\u0002\b\u001d>$\b.\u001b8h!\t\u0011r&\u0003\u00021'\t\u0019\u0011I\\=\u0011\u0005\u001d\u0012D!B\u001a\u0004\u0005\u0004Q#!A%\t\u000bU\u001a\u00019\u0001\u001c\u0002\t%$XM\u001d\t\u0006oi2\u0013\u0007P\u0007\u0002q)\u0011\u0011\bC\u0001\bgV\u0004\bo\u001c:u\u0013\tY\u0004H\u0001\rDC:$&/\u0019<feN,7*Z=WC2,X\rU1jeN\u0004\"AE\u001f\n\u0005y\u001a\"aA%oi\u0006i!/\u001a3vG\u0016|Fi\\;cY\u0016,2!\u0011#G)\t\u0011u\t\u0005\u0003$I\r+\u0005CA\u0014E\t\u0015ICA1\u0001+!\t9c\tB\u00034\t\t\u0007!\u0006C\u00036\t\u0001\u000f\u0001\nE\u00038u\r+\u0015\n\u0005\u0002\u0013\u0015&\u00111j\u0005\u0002\u0007\t>,(\r\\3\u0002\u0019I,G-^2f?\u001acw.\u0019;\u0016\u00079\u000b6\u000b\u0006\u0002P)B!1\u0005\n)S!\t9\u0013\u000bB\u0003*\u000b\t\u0007!\u0006\u0005\u0002('\u0012)1'\u0002b\u0001U!)Q'\u0002a\u0002+B)qG\u000f)S-B\u0011!cV\u0005\u00031N\u0011QA\u00127pCR\f1B]3ek\u000e,w\fT8oOV\u00191L\u00181\u0015\u0005q\u000b\u0007\u0003B\u0012%;~\u0003\"a\n0\u0005\u000b%2!\u0019\u0001\u0016\u0011\u0005\u001d\u0002G!B\u001a\u0007\u0005\u0004Q\u0003\"B\u001b\u0007\u0001\b\u0011\u0007#B\u001c;;~\u001b\u0007C\u0001\ne\u0013\t)7C\u0001\u0003M_:<\u0007"
)
public final class argmin {
   public static UFunc.UImpl reduce_Long(final CanTraverseKeyValuePairs iter) {
      return argmin$.MODULE$.reduce_Long(iter);
   }

   public static UFunc.UImpl reduce_Float(final CanTraverseKeyValuePairs iter) {
      return argmin$.MODULE$.reduce_Float(iter);
   }

   public static UFunc.UImpl reduce_Double(final CanTraverseKeyValuePairs iter) {
      return argmin$.MODULE$.reduce_Double(iter);
   }

   public static UFunc.UImpl reduce_Int(final CanTraverseKeyValuePairs iter) {
      return argmin$.MODULE$.reduce_Int(iter);
   }

   public static Object withSink(final Object s) {
      return argmin$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return argmin$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return argmin$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return argmin$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return argmin$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return argmin$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return argmin$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return argmin$.MODULE$.apply(v, impl);
   }
}
