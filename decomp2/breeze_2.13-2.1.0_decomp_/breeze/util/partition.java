package breeze.util;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCopy;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=;Q!\u0002\u0004\t\u0002-1Q!\u0004\u0004\t\u00029AQaG\u0001\u0005\u0002qAQ!H\u0001\u0005\u0004yAQ!P\u0001\u0005\u0004y\n\u0011\u0002]1si&$\u0018n\u001c8\u000b\u0005\u001dA\u0011\u0001B;uS2T\u0011!C\u0001\u0007EJ,WM_3\u0004\u0001A\u0011A\"A\u0007\u0002\r\tI\u0001/\u0019:uSRLwN\\\n\u0004\u0003=)\u0002C\u0001\t\u0014\u001b\u0005\t\"\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\t\"AB!osJ+g\r\u0005\u0002\u001735\tqC\u0003\u0002\u0019\u0011\u00059q-\u001a8fe&\u001c\u0017B\u0001\u000e\u0018\u0005\u0015)f)\u001e8d\u0003\u0019a\u0014N\\5u}Q\t1\"\u0001\u000ej]Bc\u0017mY3Ge>l\u0017kU3mK\u000e$\u0018*\u001c9m\u00136\u0004H.F\u0002 Mm\"\"\u0001\t\u001a\u0011\t\u0005\u0012CeL\u0007\u0002\u0003%\u00111%\u0007\u0002\r\u0013:\u0004F.Y2f\u00136\u0004HN\r\t\u0003K\u0019b\u0001\u0001B\u0003(\u0007\t\u0007\u0001FA\u0002BeJ\f\"!\u000b\u0017\u0011\u0005AQ\u0013BA\u0016\u0012\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001E\u0017\n\u00059\n\"aA!osB\u0011\u0001\u0003M\u0005\u0003cE\u00111!\u00138u\u0011\u0015\u00194\u0001q\u00015\u0003\t\t8\u000fE\u00036q\u0011z#H\u0004\u0002\rm%\u0011qGB\u0001\u0010cVL7m[*fY\u0016\u001cG/S7qY&\u0011\u0011(\u0007\u0002\u0006\u00136\u0004HN\r\t\u0003Km\"Q\u0001P\u0002C\u0002!\u0012\u0011\u0001V\u0001\u0017S6\u0004HN\u0012:p[&s\u0007\u000b\\1dK\u0006sGmY8qsV\u0011qH\u0011\u000b\u0004\u0001\u000e+\u0005#B\u00119\u0003>\n\u0005CA\u0013C\t\u00159CA1\u0001)\u0011\u0015\u0019D\u0001q\u0001E!\u0011\t#%Q\u0018\t\u000b\u0019#\u00019A$\u0002\t\r|\u0007/\u001f\t\u0004\u00116\u000bU\"A%\u000b\u0005)[\u0015aB:vaB|'\u000f\u001e\u0006\u0003\u0019\"\ta\u0001\\5oC2<\u0017B\u0001(J\u0005\u001d\u0019\u0015M\\\"paf\u0004"
)
public final class partition {
   public static UFunc.UImpl2 implFromInPlaceAndcopy(final UFunc.InPlaceImpl2 qs, final CanCopy copy) {
      return partition$.MODULE$.implFromInPlaceAndcopy(qs, copy);
   }

   public static UFunc.InPlaceImpl2 inPlaceFromQSelectImplImpl(final UFunc.UImpl2 qs) {
      return partition$.MODULE$.inPlaceFromQSelectImplImpl(qs);
   }

   public static Object withSink(final Object s) {
      return partition$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return partition$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return partition$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return partition$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return partition$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return partition$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return partition$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return partition$.MODULE$.apply(v, impl);
   }
}
