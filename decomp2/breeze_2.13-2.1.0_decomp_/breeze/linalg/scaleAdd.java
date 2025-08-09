package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0001;Qa\u0002\u0005\t\u000251Qa\u0004\u0005\t\u0002AAQ!H\u0001\u0005\u0002yAQaH\u0001\u0005\u0004\u0001BQaK\u0001\u0005\u00041BQAM\u0001\u0005\u0004MBQ!O\u0001\u0005\u0004i\n\u0001b]2bY\u0016\fE\r\u001a\u0006\u0003\u0013)\ta\u0001\\5oC2<'\"A\u0006\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"AD\u0001\u000e\u0003!\u0011\u0001b]2bY\u0016\fE\rZ\n\u0004\u0003E9\u0002C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"AB!osJ+g\r\u0005\u0002\u001975\t\u0011D\u0003\u0002\u001b\u0015\u00059q-\u001a8fe&\u001c\u0017B\u0001\u000f\u001a\u0005\u0015)f)\u001e8d\u0003\u0019a\u0014N\\5u}Q\tQ\"A\ttG\u0006dW-\u00113e\u0003J\u0014\u0018-_0J]R,\u0012!\t\t\u0006E\r*\u0003&J\u0007\u0002\u0003%\u0011Ae\u0007\u0002\r\u0013:\u0004F.Y2f\u00136\u0004Hn\r\t\u0004%\u0019B\u0013BA\u0014\u0014\u0005\u0015\t%O]1z!\t\u0011\u0012&\u0003\u0002+'\t\u0019\u0011J\u001c;\u0002)M\u001c\u0017\r\\3BI\u0012\f%O]1z?\u0012{WO\u00197f+\u0005i\u0003#\u0002\u0012$]=r\u0003c\u0001\n'_A\u0011!\u0003M\u0005\u0003cM\u0011a\u0001R8vE2,\u0017AE:dC2,\u0017\t\u001a3BeJ\f\u0017p\u0018'p]\u001e,\u0012\u0001\u000e\t\u0006E\r*d'\u000e\t\u0004%\u00192\u0004C\u0001\n8\u0013\tA4C\u0001\u0003M_:<\u0017aE:dC2,\u0017\t\u001a3BeJ\f\u0017p\u0018$m_\u0006$X#A\u001e\u0011\u000b\t\u001aC(\u0010\u001f\u0011\u0007I1S\b\u0005\u0002\u0013}%\u0011qh\u0005\u0002\u0006\r2|\u0017\r\u001e"
)
public final class scaleAdd {
   public static UFunc.InPlaceImpl3 scaleAddArray_Float() {
      return scaleAdd$.MODULE$.scaleAddArray_Float();
   }

   public static UFunc.InPlaceImpl3 scaleAddArray_Long() {
      return scaleAdd$.MODULE$.scaleAddArray_Long();
   }

   public static UFunc.InPlaceImpl3 scaleAddArray_Double() {
      return scaleAdd$.MODULE$.scaleAddArray_Double();
   }

   public static UFunc.InPlaceImpl3 scaleAddArray_Int() {
      return scaleAdd$.MODULE$.scaleAddArray_Int();
   }

   public static Object withSink(final Object s) {
      return scaleAdd$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return scaleAdd$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return scaleAdd$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return scaleAdd$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return scaleAdd$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return scaleAdd$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return scaleAdd$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return scaleAdd$.MODULE$.apply(v, impl);
   }
}
