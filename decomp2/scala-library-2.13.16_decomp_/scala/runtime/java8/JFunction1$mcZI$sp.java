package scala.runtime.java8;

import java.io.Serializable;
import scala.Function1;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@FunctionalInterface
@ScalaSignature(
   bytes = "\u0006\u0005}2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003$\u0001\u0011\u0005A\u0005C\u0003)\u0001\u0019\u0005\u0011\u0006C\u00033\u0001\u0011\u00053G\u0001\nK\rVt7\r^5p]F\"Sn\u0019.JIM\u0004(B\u0001\u0004\b\u0003\u0015Q\u0017M^19\u0015\tA\u0011\"A\u0004sk:$\u0018.\\3\u000b\u0003)\tQa]2bY\u0006\u001c\u0001a\u0005\u0003\u0001\u001bE9\u0002C\u0001\b\u0010\u001b\u0005I\u0011B\u0001\t\n\u0005\u0019\te.\u001f*fMB!aB\u0005\u000b\u0015\u0013\t\u0019\u0012BA\u0005Gk:\u001cG/[8ocA\u0011a\"F\u0005\u0003-%\u00111!\u00118z!\tA\u0002E\u0004\u0002\u001a=9\u0011!$H\u0007\u00027)\u0011AdC\u0001\u0007yI|w\u000e\u001e \n\u0003)I!aH\u0005\u0002\u000fA\f7m[1hK&\u0011\u0011E\t\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003?%\ta\u0001J5oSR$C#A\u0013\u0011\u000591\u0013BA\u0014\n\u0005\u0011)f.\u001b;\u0002\u001b\u0005\u0004\b\u000f\\=%[\u000eT\u0016\nJ:q)\tQS\u0006\u0005\u0002\u000fW%\u0011A&\u0003\u0002\b\u0005>|G.Z1o\u0011\u0015q#\u00011\u00010\u0003\t1\u0018\u0007\u0005\u0002\u000fa%\u0011\u0011'\u0003\u0002\u0004\u0013:$\u0018!B1qa2LHC\u0001\u000b5\u0011\u0015)4\u00011\u0001\u0015\u0003\u0005!\bF\u0001\u00018!\tAT(D\u0001:\u0015\tQ4(\u0001\u0003mC:<'\"\u0001\u001f\u0002\t)\fg/Y\u0005\u0003}e\u00121CR;oGRLwN\\1m\u0013:$XM\u001d4bG\u0016\u0004"
)
public interface JFunction1$mcZI$sp extends Function1, Serializable {
   boolean apply$mcZI$sp(final int v1);

   // $FF: synthetic method
   static Object apply$(final JFunction1$mcZI$sp $this, final Object t) {
      return $this.apply(t);
   }

   default Object apply(final Object t) {
      return this.apply$mcZI$sp(BoxesRunTime.unboxToInt(t));
   }

   static void $init$(final JFunction1$mcZI$sp $this) {
   }
}
