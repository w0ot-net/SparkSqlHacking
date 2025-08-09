package breeze.linalg;

import breeze.generic.UFunc;
import breeze.storage.Zero;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u00059;Q!\u0002\u0004\t\u0002-1Q!\u0004\u0004\t\u00029AQaG\u0001\u0005\u0002qAQ!H\u0001\u0005\u0004yAQAO\u0001\u0005\u0004m\nqA]3wKJ\u001cXM\u0003\u0002\b\u0011\u00051A.\u001b8bY\u001eT\u0011!C\u0001\u0007EJ,WM_3\u0004\u0001A\u0011A\"A\u0007\u0002\r\t9!/\u001a<feN,7cA\u0001\u0010+A\u0011\u0001cE\u0007\u0002#)\t!#A\u0003tG\u0006d\u0017-\u0003\u0002\u0015#\t1\u0011I\\=SK\u001a\u0004\"AF\r\u000e\u0003]Q!\u0001\u0007\u0005\u0002\u000f\u001d,g.\u001a:jG&\u0011!d\u0006\u0002\u0006+\u001a+hnY\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003-\t\u0011\u0002\u001a<SKZ,'o]3\u0016\u0005}ICC\u0001\u00113!\u0011\t#\u0005\n\u0013\u000e\u0003\u0005I!aI\r\u0003\t%k\u0007\u000f\u001c\t\u0004\u0019\u0015:\u0013B\u0001\u0014\u0007\u0005-!UM\\:f-\u0016\u001cGo\u001c:\u0011\u0005!JC\u0002\u0001\u0003\u0006U\r\u0011\ra\u000b\u0002\u0002)F\u0011Af\f\t\u0003!5J!AL\t\u0003\u000f9{G\u000f[5oOB\u0011\u0001\u0003M\u0005\u0003cE\u00111!\u00118z\u0011\u001d\u00194!!AA\u0004Q\n!\"\u001a<jI\u0016t7-\u001a\u00132!\r)\u0004hJ\u0007\u0002m)\u0011q'E\u0001\be\u00164G.Z2u\u0013\tIdG\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003%\u0019hOU3wKJ\u001cX-\u0006\u0002=\u0005R\u0019Qh\u0011$\u0011\t\u0005\u0012cH\u0010\t\u0004\u0019}\n\u0015B\u0001!\u0007\u00051\u0019\u0006/\u0019:tKZ+7\r^8s!\tA#\tB\u0003+\t\t\u00071\u0006C\u0004E\t\u0005\u0005\t9A#\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007E\u00026q\u0005Cqa\u0012\u0003\u0002\u0002\u0003\u000f\u0001*\u0001\u0006fm&$WM\\2fIM\u00022!\u0013'B\u001b\u0005Q%BA&\t\u0003\u001d\u0019Ho\u001c:bO\u0016L!!\u0014&\u0003\ti+'o\u001c"
)
public final class reverse {
   public static UFunc.UImpl svReverse(final ClassTag evidence$2, final Zero evidence$3) {
      return reverse$.MODULE$.svReverse(evidence$2, evidence$3);
   }

   public static UFunc.UImpl dvReverse(final ClassTag evidence$1) {
      return reverse$.MODULE$.dvReverse(evidence$1);
   }

   public static Object withSink(final Object s) {
      return reverse$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return reverse$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return reverse$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return reverse$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return reverse$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return reverse$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return reverse$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return reverse$.MODULE$.apply(v, impl);
   }
}
