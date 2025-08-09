package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U;Q!\u0002\u0004\t\u0002-1Q!\u0004\u0004\t\u00029AQaG\u0001\u0005\u0002qAQ!H\u0001\u0005\u0004yAQ\u0001S\u0001\u0005\u0004%\u000b1!\u001b8w\u0015\t9\u0001\"\u0001\u0004mS:\fGn\u001a\u0006\u0002\u0013\u00051!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002\r\u00035\taAA\u0002j]Z\u001c2!A\b\u0016!\t\u00012#D\u0001\u0012\u0015\u0005\u0011\u0012!B:dC2\f\u0017B\u0001\u000b\u0012\u0005\u0019\te.\u001f*fMB\u0011a#G\u0007\u0002/)\u0011\u0001\u0004C\u0001\bO\u0016tWM]5d\u0013\tQrCA\u0003V\rVt7-\u0001\u0004=S:LGO\u0010\u000b\u0002\u0017\u0005!2-\u00198J]Z,6/\u001b8h\u0019V{Fi\\;cY\u0016,\"a\b\u0014\u0015\u0005\u0001*\u0004\u0003B\u0011#I=j\u0011!A\u0005\u0003Ge\u0011A!S7qYB\u0011QE\n\u0007\u0001\t\u001593A1\u0001)\u0005\u0005!\u0016CA\u0015-!\t\u0001\"&\u0003\u0002,#\t9aj\u001c;iS:<\u0007C\u0001\t.\u0013\tq\u0013CA\u0002B]f\u00042\u0001\u0004\u00193\u0013\t\tdAA\u0006EK:\u001cX-T1ue&D\bC\u0001\t4\u0013\t!\u0014C\u0001\u0004E_V\u0014G.\u001a\u0005\u0006m\r\u0001\u001daN\u0001\u0007YVLU\u000e\u001d7\u0011\ta\u0012Ce\u0010\b\u0003sqr!\u0001\u0004\u001e\n\u0005m2\u0011A\u0001'V\u0013\tid(A\u0005qe&l\u0017\u000e^5wK*\u00111H\u0002\t\u0005!\u0001{#)\u0003\u0002B#\t1A+\u001e9mKJ\u00022\u0001E\"F\u0013\t!\u0015CA\u0003BeJ\f\u0017\u0010\u0005\u0002\u0011\r&\u0011q)\u0005\u0002\u0004\u0013:$\u0018aE2b]&sg/V:j]\u001edUk\u0018$m_\u0006$XC\u0001&N)\tY%\u000b\u0005\u0003\"E1s\u0005CA\u0013N\t\u00159CA1\u0001)!\ra\u0001g\u0014\t\u0003!AK!!U\t\u0003\u000b\u0019cw.\u0019;\t\u000bY\"\u00019A*\u0011\ta\u0012C\n\u0016\t\u0005!\u0001s%\t"
)
public final class inv {
   public static UFunc.UImpl canInvUsingLU_Float(final UFunc.UImpl luImpl) {
      return inv$.MODULE$.canInvUsingLU_Float(luImpl);
   }

   public static UFunc.UImpl canInvUsingLU_Double(final UFunc.UImpl luImpl) {
      return inv$.MODULE$.canInvUsingLU_Double(luImpl);
   }

   public static Object withSink(final Object s) {
      return inv$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return inv$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return inv$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return inv$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return inv$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return inv$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return inv$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return inv$.MODULE$.apply(v, impl);
   }
}
