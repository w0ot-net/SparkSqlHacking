package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r;Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0004u\tAaY8oI*\u0011aaB\u0001\u0007Y&t\u0017\r\\4\u000b\u0003!\taA\u0019:fKj,7\u0001\u0001\t\u0003\u0017\u0005i\u0011!\u0002\u0002\u0005G>tGmE\u0002\u0002\u001dQ\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0007CA\u000b\u0019\u001b\u00051\"BA\f\b\u0003\u001d9WM\\3sS\u000eL!!\u0007\f\u0003\u000bU3UO\\2\u0002\rqJg.\u001b;?)\u0005Q\u0011AD2b]\u0012+G/V:j]\u001e\u001cf\u000bR\u000b\u0003=\u0015\"\"aH\u0019\u0011\t\u0001\n3EL\u0007\u0002\u0003%\u0011!\u0005\u0007\u0002\u0005\u00136\u0004H\u000e\u0005\u0002%K1\u0001A!\u0002\u0014\u0004\u0005\u00049#!\u0001+\u0012\u0005!Z\u0003CA\b*\u0013\tQ\u0003CA\u0004O_RD\u0017N\\4\u0011\u0005=a\u0013BA\u0017\u0011\u0005\r\te.\u001f\t\u0003\u001f=J!\u0001\r\t\u0003\r\u0011{WO\u00197f\u0011\u0015\u00114\u0001q\u00014\u0003\u001d\u0019h\u000fZ%na2\u0004B\u0001N\u0011$o9\u00111\"N\u0005\u0003m\u0015\t1a\u001d<e!\tA\u0004I\u0004\u0002:k9\u0011!h\u0010\b\u0003wyj\u0011\u0001\u0010\u0006\u0003{%\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0005\n\u0005\u00199\u0011BA!C\u0005!!UM\\:f'Z#%B\u0001\u001c\u0006\u0001"
)
public final class cond {
   public static UFunc.UImpl canDetUsingSVD(final UFunc.UImpl svdImpl) {
      return cond$.MODULE$.canDetUsingSVD(svdImpl);
   }

   public static Object withSink(final Object s) {
      return cond$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return cond$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return cond$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return cond$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return cond$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return cond$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return cond$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return cond$.MODULE$.apply(v, impl);
   }
}
