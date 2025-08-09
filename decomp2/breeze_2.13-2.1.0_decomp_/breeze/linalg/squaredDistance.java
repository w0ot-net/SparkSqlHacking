package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0001;Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQ!H\u0001\u0005\u0002yAQaH\u0001\u0005\u0004\u0001\nqb]9vCJ,G\rR5ti\u0006t7-\u001a\u0006\u0003\r\u001d\ta\u0001\\5oC2<'\"\u0001\u0005\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"aC\u0001\u000e\u0003\u0015\u0011qb]9vCJ,G\rR5ti\u0006t7-Z\n\u0005\u00039!\"\u0004\u0005\u0002\u0010%5\t\u0001CC\u0001\u0012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0002C\u0001\u0004B]f\u0014VM\u001a\t\u0003+ai\u0011A\u0006\u0006\u0003/\u001d\tqaZ3oKJL7-\u0003\u0002\u001a-\t)QKR;oGB\u00111bG\u0005\u00039\u0015\u0011ac]9vCJ,G\rR5ti\u0006t7-\u001a'poB\u0013\u0018n\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003)\tqd]9vCJ,G\rR5ti\u0006t7-\u001a$s_6T\u0016\u000e\u001d9fIZ\u000bG.^3t+\r\t\u0003F\r\u000b\u0003E]\u0002Ra\t\u0013'cQj\u0011!A\u0005\u0003Ka\u0011Q!S7qYJ\u0002\"a\n\u0015\r\u0001\u0011)\u0011f\u0001b\u0001U\t\tA+\u0005\u0002,]A\u0011q\u0002L\u0005\u0003[A\u0011qAT8uQ&tw\r\u0005\u0002\u0010_%\u0011\u0001\u0007\u0005\u0002\u0004\u0003:L\bCA\u00143\t\u0015\u00194A1\u0001+\u0005\u0005)\u0006CA\b6\u0013\t1\u0004C\u0001\u0004E_V\u0014G.\u001a\u0005\u0006q\r\u0001\u001d!O\u0001\bu&\u0004\u0018*\u001c9m!\u0015QDEJ\u0019>\u001d\tY1(\u0003\u0002=\u000b\u0005I!0\u001b9WC2,Xm\u001d\t\u0005\u0017y\"D'\u0003\u0002@\u000b\ta!,\u001b9qK\u00124\u0016\r\\;fg\u0002"
)
public final class squaredDistance {
   public static UFunc.UImpl2 squaredDistanceFromZippedValues(final UFunc.UImpl2 zipImpl) {
      return squaredDistance$.MODULE$.squaredDistanceFromZippedValues(zipImpl);
   }

   public static UFunc.UImpl2 distanceFromDotAndSub(final UFunc.UImpl2 subImpl, final UFunc.UImpl2 dotImpl) {
      return squaredDistance$.MODULE$.distanceFromDotAndSub(subImpl, dotImpl);
   }

   public static Object withSink(final Object s) {
      return squaredDistance$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return squaredDistance$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return squaredDistance$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return squaredDistance$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return squaredDistance$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return squaredDistance$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return squaredDistance$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return squaredDistance$.MODULE$.apply(v, impl);
   }
}
