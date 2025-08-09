package breeze.stats;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCreateZerosLike;
import breeze.linalg.support.CanTraverseValues;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e;QAB\u0004\t\u000211QAD\u0004\t\u0002=AQaH\u0001\u0005\u0002\u0001BQ!I\u0001\u0005\u0004\tBQ\u0001Q\u0001\u0005\u0004\u0005CQaS\u0001\u0005\u00041\u000bA!\\3b]*\u0011\u0001\"C\u0001\u0006gR\fGo\u001d\u0006\u0002\u0015\u00051!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002\u000e\u00035\tqA\u0001\u0003nK\u0006t7\u0003B\u0001\u0011-q\u0001\"!\u0005\u000b\u000e\u0003IQ\u0011aE\u0001\u0006g\u000e\fG.Y\u0005\u0003+I\u0011a!\u00118z%\u00164\u0007CA\f\u001b\u001b\u0005A\"BA\r\n\u0003\u001d9WM\\3sS\u000eL!a\u0007\r\u0003\u000bU3UO\\2\u0011\u00055i\u0012B\u0001\u0010\b\u0005=iW-\u00198M_^\u0004&/[8sSRL\u0018A\u0002\u001fj]&$h\bF\u0001\r\u00031\u0011X\rZ;dK~3En\\1u+\t\u0019#\u0006\u0006\u0002%mA!QE\n\u00154\u001b\u0005\t\u0011BA\u0014\u001b\u0005\u0011IU\u000e\u001d7\u0011\u0005%RC\u0002\u0001\u0003\u0006W\r\u0011\r\u0001\f\u0002\u0002)F\u0011Q\u0006\r\t\u0003#9J!a\f\n\u0003\u000f9{G\u000f[5oOB\u0011\u0011#M\u0005\u0003eI\u00111!\u00118z!\t\tB'\u0003\u00026%\t)a\t\\8bi\")qg\u0001a\u0002q\u0005!\u0011\u000e^3s!\u0011Id\bK\u001a\u000e\u0003iR!a\u000f\u001f\u0002\u000fM,\b\u000f]8si*\u0011Q(C\u0001\u0007Y&t\u0017\r\\4\n\u0005}R$!E\"b]R\u0013\u0018M^3sg\u00164\u0016\r\\;fg\u0006i!/\u001a3vG\u0016|Fi\\;cY\u0016,\"AQ#\u0015\u0005\rK\u0005\u0003B\u0013'\t\u001a\u0003\"!K#\u0005\u000b-\"!\u0019\u0001\u0017\u0011\u0005E9\u0015B\u0001%\u0013\u0005\u0019!u.\u001e2mK\")q\u0007\u0002a\u0002\u0015B!\u0011H\u0010#G\u00039\u0011X\rZ;dK~\u001bu.\u001c9mKb,\"!\u0014)\u0015\u00059;\u0006\u0003B\u0013'\u001fF\u0003\"!\u000b)\u0005\u000b-*!\u0019\u0001\u0017\u0011\u0005I+V\"A*\u000b\u0005QK\u0011\u0001B7bi\"L!AV*\u0003\u000f\r{W\u000e\u001d7fq\")q'\u0002a\u00021B!\u0011HP(R\u0001"
)
public final class mean {
   public static UFunc.UImpl reduce_Complex(final CanTraverseValues iter) {
      return mean$.MODULE$.reduce_Complex(iter);
   }

   public static UFunc.UImpl reduce_Double(final CanTraverseValues iter) {
      return mean$.MODULE$.reduce_Double(iter);
   }

   public static UFunc.UImpl reduce_Float(final CanTraverseValues iter) {
      return mean$.MODULE$.reduce_Float(iter);
   }

   public static UFunc.UImpl canMeanGeneric(final CanTraverseValues iter, final CanCreateZerosLike zerosLike, final UFunc.InPlaceImpl2 setInto, final UFunc.InPlaceImpl3 axpy, final UFunc.InPlaceImpl2 canMulIntoVS) {
      return mean$.MODULE$.canMeanGeneric(iter, zerosLike, setInto, axpy, canMulIntoVS);
   }

   public static Object withSink(final Object s) {
      return mean$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return mean$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return mean$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return mean$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return mean$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return mean$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return mean$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return mean$.MODULE$.apply(v, impl);
   }
}
