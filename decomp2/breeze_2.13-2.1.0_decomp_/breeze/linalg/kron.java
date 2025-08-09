package breeze.linalg;

import breeze.generic.UFunc;
import breeze.storage.Zero;
import scala.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q;Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0004u\tAa\u001b:p]*\u0011aaB\u0001\u0007Y&t\u0017\r\\4\u000b\u0003!\taA\u0019:fKj,7\u0001\u0001\t\u0003\u0017\u0005i\u0011!\u0002\u0002\u0005WJ|gnE\u0002\u0002\u001dQ\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0007CA\u000b\u0019\u001b\u00051\"BA\f\b\u0003\u001d9WM\\3sS\u000eL!!\u0007\f\u0003\u000bU3UO\\2\u0002\rqJg.\u001b;?)\u0005Q\u0011\u0001C6s_:$UjX'\u0016\u000byA#J\r\u001c\u0015\u000b}A\u0014\t\u0014+\u0011\u000b\u0001\n3%\r\u001b\u000e\u0003\u0005I!A\t\r\u0003\u000b%k\u0007\u000f\u001c\u001a\u0011\u0007-!c%\u0003\u0002&\u000b\tYA)\u001a8tK6\u000bGO]5y!\t9\u0003\u0006\u0004\u0001\u0005\u000b%\u001a!\u0019\u0001\u0016\u0003\u0005Y\u000b\u0014CA\u0016/!\tyA&\u0003\u0002.!\t9aj\u001c;iS:<\u0007CA\b0\u0013\t\u0001\u0004CA\u0002B]f\u0004\"a\n\u001a\u0005\u000bM\u001a!\u0019\u0001\u0016\u0003\u00035\u00032a\u0003\u00136!\t9c\u0007B\u00038\u0007\t\u0007!F\u0001\u0002S-\")\u0011h\u0001a\u0002u\u0005\u0019Q.\u001e7\u0011\u000bm\nc%\r\u001b\u000f\u0005qzT\"A\u001f\u000b\u0005y*\u0011!C8qKJ\fGo\u001c:t\u0013\t\u0001U(A\u0006Pa6+HnU2bY\u0006\u0014\b\"\u0002\"\u0004\u0001\b\u0019\u0015!B1t\u001b\u0006$\b\u0003B\bEc\u0019K!!\u0012\t\u0003!\u0011bWm]:%G>dwN\u001c\u0013mKN\u001c\bcA\u0006H\u0013&\u0011\u0001*\u0002\u0002\u0007\u001b\u0006$(/\u001b=\u0011\u0005\u001dRE!B&\u0004\u0005\u0004Q#A\u0001,3\u0011\u0015i5\u0001q\u0001O\u0003\ri\u0017M\u001c\t\u0004\u001fJ+T\"\u0001)\u000b\u0005E\u0003\u0012a\u0002:fM2,7\r^\u0005\u0003'B\u0013\u0001b\u00117bgN$\u0016m\u001a\u0005\u0006+\u000e\u0001\u001dAV\u0001\u0005u\u0016\u0014x\u000eE\u0002X5Vj\u0011\u0001\u0017\u0006\u00033\u001e\tqa\u001d;pe\u0006<W-\u0003\u0002\\1\n!!,\u001a:p\u0001"
)
public final class kron {
   public static UFunc.UImpl2 kronDM_M(final UFunc.UImpl2 mul, final .less.colon.less asMat, final ClassTag man, final Zero zero) {
      return kron$.MODULE$.kronDM_M(mul, asMat, man, zero);
   }

   public static Object withSink(final Object s) {
      return kron$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return kron$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return kron$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return kron$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return kron$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return kron$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return kron$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return kron$.MODULE$.apply(v, impl);
   }
}
