package breeze.linalg;

import breeze.generic.UFunc;
import breeze.stats.distributions.RandBasis;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005);Q!\u0003\u0006\t\u0002=1Q!\u0005\u0006\t\u0002IAQaH\u0001\u0005\u0002\u0001BQ!I\u0001\u0005\u0012\tBQ\u0001M\u0001\u0005\u0012EBq\u0001O\u0001C\u0002\u0013E\u0011\b\u0003\u0004A\u0003\u0001\u0006IA\u000f\u0005\b\u0003\u0006\u0011\r\u0011\"\u0005C\u0011\u0019I\u0015\u0001)A\u0005\u0007\u0006a!/\u00198e_6$u.\u001e2mK*\u00111\u0002D\u0001\u0007Y&t\u0017\r\\4\u000b\u00035\taA\u0019:fKj,7\u0001\u0001\t\u0003!\u0005i\u0011A\u0003\u0002\re\u0006tGm\\7E_V\u0014G.Z\n\u0004\u0003MI\u0002C\u0001\u000b\u0018\u001b\u0005)\"\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005a)\"AB!osJ+g\rE\u0002\u00115qI!a\u0007\u0006\u0003)I\u000bg\u000eZ8n\u000f\u0016tWM]1u_J,f)\u001e8d!\t!R$\u0003\u0002\u001f+\t1Ai\\;cY\u0016\fa\u0001P5oSRtD#A\b\u0002\u0007\u001d,g\u000e\u0006\u0002$WA\u0019A%\u000b\u000f\u000e\u0003\u0015R!AJ\u0014\u0002\u001b\u0011L7\u000f\u001e:jEV$\u0018n\u001c8t\u0015\tAC\"A\u0003ti\u0006$8/\u0003\u0002+K\t!!+\u00198e\u0011\u0015a3\u0001q\u0001.\u0003\u0015\u0011\u0017m]5t!\t!c&\u0003\u00020K\tI!+\u00198e\u0005\u0006\u001c\u0018n]\u0001\tO\u0016t'+\u00198hKR\u0019!\u0007\u000e\u001c\u0015\u0005\r\u001a\u0004\"\u0002\u0017\u0005\u0001\bi\u0003\"B\u001b\u0005\u0001\u0004a\u0012a\u00017po\")q\u0007\u0002a\u00019\u0005!\u0001.[4i\u0003%y6\r\\1tgR\u000bw-F\u0001;!\rYd\bH\u0007\u0002y)\u0011Q(F\u0001\be\u00164G.Z2u\u0013\tyDH\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003)y6\r\\1tgR\u000bw\rI\u0001\u0006?j,'o\\\u000b\u0002\u0007B\u0019Ai\u0012\u000f\u000e\u0003\u0015S!A\u0012\u0007\u0002\u000fM$xN]1hK&\u0011\u0001*\u0012\u0002\u00055\u0016\u0014x.\u0001\u0004`u\u0016\u0014x\u000e\t"
)
public final class randomDouble {
   public static UFunc.UImpl2 implRandomT_2DRange(final RandBasis basis) {
      return randomDouble$.MODULE$.implRandomT_2DRange(basis);
   }

   public static UFunc.UImpl implRandomT_2D(final RandBasis basis) {
      return randomDouble$.MODULE$.implRandomT_2D(basis);
   }

   public static UFunc.UImpl2 implRandomT_1DRange(final RandBasis basis) {
      return randomDouble$.MODULE$.implRandomT_1DRange(basis);
   }

   public static UFunc.UImpl implRandomT_1D(final RandBasis basis) {
      return randomDouble$.MODULE$.implRandomT_1D(basis);
   }

   public static Object apply(final RandBasis basis) {
      return randomDouble$.MODULE$.apply(basis);
   }

   public static Object withSink(final Object s) {
      return randomDouble$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return randomDouble$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return randomDouble$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return randomDouble$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return randomDouble$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return randomDouble$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return randomDouble$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return randomDouble$.MODULE$.apply(v, impl);
   }
}
