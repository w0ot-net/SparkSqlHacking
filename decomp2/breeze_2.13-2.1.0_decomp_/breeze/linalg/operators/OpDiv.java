package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.math.Field;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u:Q\u0001B\u0003\t\u000211QAD\u0003\t\u0002=AQaH\u0001\u0005\u0002\u0001BQ!I\u0001\u0005\u0004\t\nQa\u00149ESZT!AB\u0004\u0002\u0013=\u0004XM]1u_J\u001c(B\u0001\u0005\n\u0003\u0019a\u0017N\\1mO*\t!\"\u0001\u0004ce\u0016,'0Z\u0002\u0001!\ti\u0011!D\u0001\u0006\u0005\u0015y\u0005\u000fR5w'\u0011\t\u0001CF\r\u0011\u0005E!R\"\u0001\n\u000b\u0003M\tQa]2bY\u0006L!!\u0006\n\u0003\r\u0005s\u0017PU3g!\tiq#\u0003\u0002\u0019\u000b\t1q\n\u001d+za\u0016\u0004\"AG\u000f\u000e\u0003mQ!\u0001H\u0005\u0002\u000f\u001d,g.\u001a:jG&\u0011ad\u0007\u0002\u0011\u000b2,W.\u001a8uo&\u001cX-\u0016$v]\u000e\fa\u0001P5oSRtD#\u0001\u0007\u0002\u001d=\u0004H)\u001b<Ge>lg)[3mIV\u00111\u0005\f\u000b\u0003IU\u0002R!\n\u0014+U)j\u0011!A\u0005\u0003O!\u0012Q!S7qYJJ!!K\u000e\u0003\u000bU3UO\\2\u0011\u0005-bC\u0002\u0001\u0003\u0006[\r\u0011\rA\f\u0002\u0002'F\u0011qF\r\t\u0003#AJ!!\r\n\u0003\u000f9{G\u000f[5oOB\u0011\u0011cM\u0005\u0003iI\u00111!\u00118z\u0011\u001d14!!AA\u0004]\n!\"\u001a<jI\u0016t7-\u001a\u00135!\rA4HK\u0007\u0002s)\u0011!(C\u0001\u0005[\u0006$\b.\u0003\u0002=s\t)a)[3mI\u0002"
)
public final class OpDiv {
   public static UFunc.UImpl2 opDivFromField(final Field evidence$4) {
      return OpDiv$.MODULE$.opDivFromField(evidence$4);
   }

   public static Object withSink(final Object s) {
      return OpDiv$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return OpDiv$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return OpDiv$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return OpDiv$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return OpDiv$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return OpDiv$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return OpDiv$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return OpDiv$.MODULE$.apply(v, impl);
   }
}
