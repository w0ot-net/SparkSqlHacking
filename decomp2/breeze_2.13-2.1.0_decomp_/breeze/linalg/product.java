package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanTraverseValues;
import breeze.math.Semiring;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E<Q\u0001C\u0005\t\u000291Q\u0001E\u0005\t\u0002EAQAH\u0001\u0005\u0002}AQ\u0001I\u0001\u0005\u0004\u0005BQ!P\u0001\u0005\u0004yBQ\u0001S\u0001\u0005\u0004%CQaU\u0001\u0005\u0004QCQAX\u0001\u0005\u0004}\u000bq\u0001\u001d:pIV\u001cGO\u0003\u0002\u000b\u0017\u00051A.\u001b8bY\u001eT\u0011\u0001D\u0001\u0007EJ,WM_3\u0004\u0001A\u0011q\"A\u0007\u0002\u0013\t9\u0001O]8ek\u000e$8cA\u0001\u00131A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\u0004\"!\u0007\u000f\u000e\u0003iQ!aG\u0006\u0002\u000f\u001d,g.\u001a:jG&\u0011QD\u0007\u0002\u0006+\u001a+hnY\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00039\t!B]3ek\u000e,w,\u00138u+\t\u0011\u0013\u0006\u0006\u0002$kA!A%J\u00143\u001b\u0005\t\u0011B\u0001\u0014\u001d\u0005\u0011IU\u000e\u001d7\u0011\u0005!JC\u0002\u0001\u0003\u0006U\r\u0011\ra\u000b\u0002\u0002)F\u0011Af\f\t\u0003'5J!A\f\u000b\u0003\u000f9{G\u000f[5oOB\u00111\u0003M\u0005\u0003cQ\u00111!\u00118z!\t\u00192'\u0003\u00025)\t\u0019\u0011J\u001c;\t\u000bY\u001a\u00019A\u001c\u0002\t%$XM\u001d\t\u0005qm:#'D\u0001:\u0015\tQ\u0014\"A\u0004tkB\u0004xN\u001d;\n\u0005qJ$!E\"b]R\u0013\u0018M^3sg\u00164\u0016\r\\;fg\u0006i!/\u001a3vG\u0016|Fi\\;cY\u0016,\"a\u0010\"\u0015\u0005\u00013\u0005\u0003\u0002\u0013&\u0003\u000e\u0003\"\u0001\u000b\"\u0005\u000b)\"!\u0019A\u0016\u0011\u0005M!\u0015BA#\u0015\u0005\u0019!u.\u001e2mK\")a\u0007\u0002a\u0002\u000fB!\u0001hO!D\u00031\u0011X\rZ;dK~3En\\1u+\tQU\n\u0006\u0002L#B!A%\n'O!\tAS\nB\u0003+\u000b\t\u00071\u0006\u0005\u0002\u0014\u001f&\u0011\u0001\u000b\u0006\u0002\u0006\r2|\u0017\r\u001e\u0005\u0006m\u0015\u0001\u001dA\u0015\t\u0005qmbe*A\u0006sK\u0012,8-Z0M_:<WCA+Y)\t1F\f\u0005\u0003%K]K\u0006C\u0001\u0015Y\t\u0015QcA1\u0001,!\t\u0019\",\u0003\u0002\\)\t!Aj\u001c8h\u0011\u00151d\u0001q\u0001^!\u0011A4hV-\u0002\u001dI,G-^2f'\u0016l\u0017N]5oOV\u0019\u0001mY3\u0015\u0007\u0005<\u0017\u000e\u0005\u0003%K\t$\u0007C\u0001\u0015d\t\u0015QsA1\u0001,!\tAS\rB\u0003g\u000f\t\u00071FA\u0001T\u0011\u00151t\u0001q\u0001i!\u0011A4H\u00193\t\u000b)<\u00019A6\u0002\u0011M,W.\u001b:j]\u001e\u00042\u0001\\8e\u001b\u0005i'B\u00018\f\u0003\u0011i\u0017\r\u001e5\n\u0005Al'\u0001C*f[&\u0014\u0018N\\4"
)
public final class product {
   public static UFunc.UImpl reduceSemiring(final CanTraverseValues iter, final Semiring semiring) {
      return product$.MODULE$.reduceSemiring(iter, semiring);
   }

   public static UFunc.UImpl reduce_Long(final CanTraverseValues iter) {
      return product$.MODULE$.reduce_Long(iter);
   }

   public static UFunc.UImpl reduce_Float(final CanTraverseValues iter) {
      return product$.MODULE$.reduce_Float(iter);
   }

   public static UFunc.UImpl reduce_Double(final CanTraverseValues iter) {
      return product$.MODULE$.reduce_Double(iter);
   }

   public static UFunc.UImpl reduce_Int(final CanTraverseValues iter) {
      return product$.MODULE$.reduce_Int(iter);
   }

   public static Object withSink(final Object s) {
      return product$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return product$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return product$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return product$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return product$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return product$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return product$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return product$.MODULE$.apply(v, impl);
   }
}
