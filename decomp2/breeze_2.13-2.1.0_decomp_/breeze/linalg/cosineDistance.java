package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i;Q!\u0002\u0004\t\u0002-1Q!\u0004\u0004\t\u00029AQaG\u0001\u0005\u0002qAQ!H\u0001\u0005\u0004yAQ!S\u0001\u0005\u0004)\u000babY8tS:,G)[:uC:\u001cWM\u0003\u0002\b\u0011\u00051A.\u001b8bY\u001eT\u0011!C\u0001\u0007EJ,WM_3\u0004\u0001A\u0011A\"A\u0007\u0002\r\tq1m\\:j]\u0016$\u0015n\u001d;b]\u000e,7cA\u0001\u0010+A\u0011\u0001cE\u0007\u0002#)\t!#A\u0003tG\u0006d\u0017-\u0003\u0002\u0015#\t1\u0011I\\=SK\u001a\u0004\"AF\r\u000e\u0003]Q!\u0001\u0007\u0005\u0002\u000f\u001d,g.\u001a:jG&\u0011!d\u0006\u0002\u0006+\u001a+hnY\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003-\t1eY8tS:,G)[:uC:\u001cWM\u0012:p[\u0012{G\u000f\u0015:pIV\u001cG/\u00118e\u001d>\u0014X.F\u0002 MA\"B\u0001I\u001b?\rB)\u0011E\t\u00130e5\t\u0011!\u0003\u0002$3\t)\u0011*\u001c9meA\u0011QE\n\u0007\u0001\t\u001593A1\u0001)\u0005\u0005!\u0016CA\u0015-!\t\u0001\"&\u0003\u0002,#\t9aj\u001c;iS:<\u0007C\u0001\t.\u0013\tq\u0013CA\u0002B]f\u0004\"!\n\u0019\u0005\u000bE\u001a!\u0019\u0001\u0015\u0003\u0003U\u0003\"\u0001E\u001a\n\u0005Q\n\"A\u0002#pk\ndW\rC\u00037\u0007\u0001\u000fq'A\u0002e_R\u0004R\u0001\u000f\u0012%_Ir!!\u000f\u001f\u000e\u0003iR!a\u000f\u0004\u0002\u0013=\u0004XM]1u_J\u001c\u0018BA\u001f;\u0003)y\u0005/T;m\u0013:tWM\u001d\u0005\u0006\u007f\r\u0001\u001d\u0001Q\u0001\u0006]>\u0014X\u000e\u0016\t\u0005\u0003\u0012##G\u0004\u0002\r\u0005&\u00111IB\u0001\u0005]>\u0014X.\u0003\u0002F3\t!\u0011*\u001c9m\u0011\u001595\u0001q\u0001I\u0003\u0015qwN]7V!\u0011\tEi\f\u001a\u0002K\r|7/\u001b8f\t&\u001cH/\u00198dK\u001a\u0013x.\u001c#piB\u0013x\u000eZ;di\u0006sGMT8s[~3UcA&O!R!A*\u0015,Y!\u0015\t#%T(3!\t)c\nB\u0003(\t\t\u0007\u0001\u0006\u0005\u0002&!\u0012)\u0011\u0007\u0002b\u0001Q!)a\u0007\u0002a\u0002%B)\u0001HI'P'B\u0011\u0001\u0003V\u0005\u0003+F\u0011QA\u00127pCRDQa\u0010\u0003A\u0004]\u0003B!\u0011#Ne!)q\t\u0002a\u00023B!\u0011\tR(3\u0001"
)
public final class cosineDistance {
   public static UFunc.UImpl2 cosineDistanceFromDotProductAndNorm_F(final UFunc.UImpl2 dot, final UFunc.UImpl normT, final UFunc.UImpl normU) {
      return cosineDistance$.MODULE$.cosineDistanceFromDotProductAndNorm_F(dot, normT, normU);
   }

   public static UFunc.UImpl2 cosineDistanceFromDotProductAndNorm(final UFunc.UImpl2 dot, final UFunc.UImpl normT, final UFunc.UImpl normU) {
      return cosineDistance$.MODULE$.cosineDistanceFromDotProductAndNorm(dot, normT, normU);
   }

   public static Object withSink(final Object s) {
      return cosineDistance$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return cosineDistance$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return cosineDistance$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return cosineDistance$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return cosineDistance$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return cosineDistance$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return cosineDistance$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return cosineDistance$.MODULE$.apply(v, impl);
   }
}
