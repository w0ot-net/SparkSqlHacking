package breeze.linalg;

import breeze.generic.UFunc;
import scala.;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!;Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQ!H\u0001\u0005\u0002yAQaH\u0001\u0005\u0004\u0001\nq!\u0019:hi>\u00048N\u0003\u0002\u0007\u000f\u00051A.\u001b8bY\u001eT\u0011\u0001C\u0001\u0007EJ,WM_3\u0004\u0001A\u00111\"A\u0007\u0002\u000b\t9\u0011M]4u_B\\7\u0003B\u0001\u000f)i\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0007CA\u000b\u0019\u001b\u00051\"BA\f\b\u0003\u001d9WM\\3sS\u000eL!!\u0007\f\u0003\u000bU3UO\\2\u0011\u0005-Y\u0012B\u0001\u000f\u0006\u0005Iaun\u001e)sS>\u0014\u0018\u000e^=Be\u001e$v\u000e]&\u0002\rqJg.\u001b;?)\u0005Q\u0011AE1sOR|\u0007o\u001b#f]N,g+Z2u_J,\"!I\u0016\u0015\u0005\t\u001a\u0005#B\u0012%MQ:T\"A\u0001\n\u0005\u0015B\"!B%na2\u0014\u0004cA\u0006(S%\u0011\u0001&\u0002\u0002\f\t\u0016t7/\u001a,fGR|'\u000f\u0005\u0002+W1\u0001A!\u0002\u0017\u0004\u0005\u0004i#!\u0001+\u0012\u00059\n\u0004CA\b0\u0013\t\u0001\u0004CA\u0004O_RD\u0017N\\4\u0011\u0005=\u0011\u0014BA\u001a\u0011\u0005\r\te.\u001f\t\u0003\u001fUJ!A\u000e\t\u0003\u0007%sG\u000fE\u00029\u0001Rr!!\u000f \u000f\u0005ijT\"A\u001e\u000b\u0005qJ\u0011A\u0002\u001fs_>$h(C\u0001\u0012\u0013\ty\u0004#A\u0004qC\u000e\\\u0017mZ3\n\u0005\u0005\u0013%AC%oI\u0016DX\rZ*fc*\u0011q\b\u0005\u0005\b\t\u000e\t\t\u0011q\u0001F\u0003))g/\u001b3f]\u000e,G%\r\t\u0004q\u0019K\u0013BA$C\u0005!y%\u000fZ3sS:<\u0007"
)
public final class argtopk {
   public static UFunc.UImpl2 argtopkDenseVector(final Ordering evidence$1) {
      return argtopk$.MODULE$.argtopkDenseVector(evidence$1);
   }

   public static UFunc.UImpl2 argtopkWithQT(final .less.colon.less qt, final Ordering ord) {
      return argtopk$.MODULE$.argtopkWithQT(qt, ord);
   }

   public static Object withSink(final Object s) {
      return argtopk$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return argtopk$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return argtopk$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return argtopk$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return argtopk$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return argtopk$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return argtopk$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return argtopk$.MODULE$.apply(v, impl);
   }
}
