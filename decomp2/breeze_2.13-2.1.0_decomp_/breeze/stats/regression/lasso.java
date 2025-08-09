package breeze.stats.regression;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%;Q!\u0003\u0006\t\u0002E1Qa\u0005\u0006\t\u0002QAQ!I\u0001\u0005\u0002\tBqaI\u0001C\u0002\u0013\rA\u0005\u0003\u0004<\u0003\u0001\u0006I!\n\u0005\by\u0005\u0011\r\u0011b\u0001>\u0011\u0019\u0011\u0015\u0001)A\u0005}!91)\u0001b\u0001\n\u0007!\u0005B\u0002%\u0002A\u0003%Q)A\u0003mCN\u001cxN\u0003\u0002\f\u0019\u0005Q!/Z4sKN\u001c\u0018n\u001c8\u000b\u00055q\u0011!B:uCR\u001c(\"A\b\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"AE\u0001\u000e\u0003)\u0011Q\u0001\\1tg>\u001c2!A\u000b\u001c!\t1\u0012$D\u0001\u0018\u0015\u0005A\u0012!B:dC2\f\u0017B\u0001\u000e\u0018\u0005\u0019\te.\u001f*fMB\u0011AdH\u0007\u0002;)\u0011aDD\u0001\bO\u0016tWM]5d\u0013\t\u0001SDA\u0003V\rVt7-\u0001\u0004=S:LGO\u0010\u000b\u0002#\u0005IR.\u0019;sSb4Vm\u0019;pe^KG\u000f[,pe.\f%O]1z+\u0005)\u0003c\u0002\u0014(SIzS\u0007O\u0007\u0002\u0003%\u0011\u0001f\b\u0002\u0006\u00136\u0004H\u000e\u000e\t\u0004U5zS\"A\u0016\u000b\u00051r\u0011A\u00027j]\u0006dw-\u0003\u0002/W\tYA)\u001a8tK6\u000bGO]5y!\t1\u0002'\u0003\u00022/\t1Ai\\;cY\u0016\u00042AK\u001a0\u0013\t!4FA\u0006EK:\u001cXMV3di>\u0014\bc\u0001\f7_%\u0011qg\u0006\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003%eJ!A\u000f\u0006\u0003\u00171\u000b7o]8SKN,H\u000e^\u0001\u001b[\u0006$(/\u001b=WK\u000e$xN],ji\"<vN]6BeJ\f\u0017\u0010I\u0001\u001a[\u0006$(/\u001b=WK\u000e$xN]*qK\u000eLg-[3e/>\u00148.F\u0001?!\u001d1s%\u000b\u001a0\u007fa\u0002\"A\u0006!\n\u0005\u0005;\"aA%oi\u0006QR.\u0019;sSb4Vm\u0019;peN\u0003XmY5gS\u0016$wk\u001c:lA\u0005aQ.\u0019;sSb4Vm\u0019;peV\tQ\t\u0005\u0004'\r&\u0012t\u0006O\u0005\u0003\u000f~\u0011Q!S7qYN\nQ\"\\1ue&Dh+Z2u_J\u0004\u0003"
)
public final class lasso {
   public static UFunc.UImpl3 matrixVector() {
      return lasso$.MODULE$.matrixVector();
   }

   public static UFunc.UImpl4 matrixVectorSpecifiedWork() {
      return lasso$.MODULE$.matrixVectorSpecifiedWork();
   }

   public static UFunc.UImpl4 matrixVectorWithWorkArray() {
      return lasso$.MODULE$.matrixVectorWithWorkArray();
   }

   public static Object withSink(final Object s) {
      return lasso$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return lasso$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return lasso$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return lasso$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return lasso$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return lasso$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return lasso$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return lasso$.MODULE$.apply(v, impl);
   }
}
