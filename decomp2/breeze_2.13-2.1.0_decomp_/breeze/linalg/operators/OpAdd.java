package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.math.Semiring;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u:Q\u0001B\u0003\t\u000211QAD\u0003\t\u0002=AQaH\u0001\u0005\u0002\u0001BQ!I\u0001\u0005\u0004\t\nQa\u00149BI\u0012T!AB\u0004\u0002\u0013=\u0004XM]1u_J\u001c(B\u0001\u0005\n\u0003\u0019a\u0017N\\1mO*\t!\"\u0001\u0004ce\u0016,'0Z\u0002\u0001!\ti\u0011!D\u0001\u0006\u0005\u0015y\u0005/\u00113e'\u0011\t\u0001CF\r\u0011\u0005E!R\"\u0001\n\u000b\u0003M\tQa]2bY\u0006L!!\u0006\n\u0003\r\u0005s\u0017PU3g!\tiq#\u0003\u0002\u0019\u000b\t1q\n\u001d+za\u0016\u0004\"AG\u000f\u000e\u0003mQ!\u0001H\u0005\u0002\u000f\u001d,g.\u001a:jG&\u0011ad\u0007\u0002\u0011\u000b2,W.\u001a8uo&\u001cX-\u0016$v]\u000e\fa\u0001P5oSRtD#\u0001\u0007\u0002#=\u0004\u0018\t\u001a3Ge>l7+Z7je&tw-\u0006\u0002$YQ\u0011A%\u000e\t\u0006K\u0019R#FK\u0007\u0002\u0003%\u0011q\u0005\u000b\u0002\u0006\u00136\u0004HNM\u0005\u0003Sm\u0011Q!\u0016$v]\u000e\u0004\"a\u000b\u0017\r\u0001\u0011)Qf\u0001b\u0001]\t\t1+\u0005\u00020eA\u0011\u0011\u0003M\u0005\u0003cI\u0011qAT8uQ&tw\r\u0005\u0002\u0012g%\u0011AG\u0005\u0002\u0004\u0003:L\bb\u0002\u001c\u0004\u0003\u0003\u0005\u001daN\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004c\u0001\u001d<U5\t\u0011H\u0003\u0002;\u0013\u0005!Q.\u0019;i\u0013\ta\u0014H\u0001\u0005TK6L'/\u001b8h\u0001"
)
public final class OpAdd {
   public static UFunc.UImpl2 opAddFromSemiring(final Semiring evidence$1) {
      return OpAdd$.MODULE$.opAddFromSemiring(evidence$1);
   }

   public static Object withSink(final Object s) {
      return OpAdd$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return OpAdd$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return OpAdd$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return OpAdd$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return OpAdd$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return OpAdd$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return OpAdd$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return OpAdd$.MODULE$.apply(v, impl);
   }
}
