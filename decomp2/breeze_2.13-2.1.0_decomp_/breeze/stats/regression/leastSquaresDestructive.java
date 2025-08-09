package breeze.stats.regression;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%;Q!\u0003\u0006\t\u0002E1Qa\u0005\u0006\t\u0002QAQ!I\u0001\u0005\u0002\tBqaI\u0001C\u0002\u0013\rA\u0005\u0003\u0004<\u0003\u0001\u0006I!\n\u0005\by\u0005\u0011\r\u0011b\u0001>\u0011\u0019\u0011\u0015\u0001)A\u0005}!91)\u0001b\u0001\n\u0007!\u0005B\u0002%\u0002A\u0003%Q)A\fmK\u0006\u001cHoU9vCJ,7\u000fR3tiJ,8\r^5wK*\u00111\u0002D\u0001\u000be\u0016<'/Z:tS>t'BA\u0007\u000f\u0003\u0015\u0019H/\u0019;t\u0015\u0005y\u0011A\u00022sK\u0016TXm\u0001\u0001\u0011\u0005I\tQ\"\u0001\u0006\u0003/1,\u0017m\u001d;TcV\f'/Z:EKN$(/^2uSZ,7cA\u0001\u00167A\u0011a#G\u0007\u0002/)\t\u0001$A\u0003tG\u0006d\u0017-\u0003\u0002\u001b/\t1\u0011I\\=SK\u001a\u0004\"\u0001H\u0010\u000e\u0003uQ!A\b\b\u0002\u000f\u001d,g.\u001a:jG&\u0011\u0001%\b\u0002\u0006+\u001a+hnY\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003E\t\u0011$\\1ue&Dh+Z2u_J<\u0016\u000e\u001e5X_J\\\u0017I\u001d:bsV\tQ\u0005\u0005\u0004'O%\u0012T\u0007O\u0007\u0002\u0003%\u0011\u0001f\b\u0002\u0006\u00136\u0004Hn\r\t\u0004U5zS\"A\u0016\u000b\u00051r\u0011A\u00027j]\u0006dw-\u0003\u0002/W\tYA)\u001a8tK6\u000bGO]5y!\t1\u0002'\u0003\u00022/\t1Ai\\;cY\u0016\u00042AK\u001a0\u0013\t!4FA\u0006EK:\u001cXMV3di>\u0014\bc\u0001\f7_%\u0011qg\u0006\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003%eJ!A\u000f\u0006\u000391+\u0017m\u001d;TcV\f'/Z:SK\u001e\u0014Xm]:j_:\u0014Vm];mi\u0006QR.\u0019;sSb4Vm\u0019;pe^KG\u000f[,pe.\f%O]1zA\u0005IR.\u0019;sSb4Vm\u0019;peN\u0003XmY5gS\u0016$wk\u001c:l+\u0005q\u0004C\u0002\u0014(SIz\u0004\b\u0005\u0002\u0017\u0001&\u0011\u0011i\u0006\u0002\u0004\u0013:$\u0018AG7biJL\u0007PV3di>\u00148\u000b]3dS\u001aLW\rZ,pe.\u0004\u0013\u0001D7biJL\u0007PV3di>\u0014X#A#\u0011\u000b\u00192\u0015F\r\u001d\n\u0005\u001d{\"!B%na2\u0014\u0014!D7biJL\u0007PV3di>\u0014\b\u0005"
)
public final class leastSquaresDestructive {
   public static UFunc.UImpl2 matrixVector() {
      return leastSquaresDestructive$.MODULE$.matrixVector();
   }

   public static UFunc.UImpl3 matrixVectorSpecifiedWork() {
      return leastSquaresDestructive$.MODULE$.matrixVectorSpecifiedWork();
   }

   public static UFunc.UImpl3 matrixVectorWithWorkArray() {
      return leastSquaresDestructive$.MODULE$.matrixVectorWithWorkArray();
   }

   public static Object withSink(final Object s) {
      return leastSquaresDestructive$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return leastSquaresDestructive$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return leastSquaresDestructive$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return leastSquaresDestructive$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return leastSquaresDestructive$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return leastSquaresDestructive$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return leastSquaresDestructive$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return leastSquaresDestructive$.MODULE$.apply(v, impl);
   }
}
