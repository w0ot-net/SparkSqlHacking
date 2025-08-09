package spire.random;

import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;
import spire.algebra.NormedVectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005i2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003/\u0001\u0011\u0005q\u0006C\u00034\u0001\u0019\rA\u0007C\u00037\u0001\u0011\u0005qGA\u000bESN$hj\u001c:nK\u00124Vm\u0019;peN\u0003\u0018mY3\u000b\u0005\u00199\u0011A\u0002:b]\u0012|WNC\u0001\t\u0003\u0015\u0019\b/\u001b:f\u0007\u0001)2a\u0003\r#'\u0011\u0001AB\u0005\u0013\u0011\u00055\u0001R\"\u0001\b\u000b\u0003=\tQa]2bY\u0006L!!\u0005\b\u0003\r\u0005s\u0017PU3g!\u0011\u0019BCF\u0011\u000e\u0003\u0015I!!F\u0003\u0003\u001f\u0011K7\u000f\u001e,fGR|'o\u00159bG\u0016\u0004\"a\u0006\r\r\u0001\u0011)\u0011\u0004\u0001b\u00015\t\ta+\u0005\u0002\u001c=A\u0011Q\u0002H\u0005\u0003;9\u0011qAT8uQ&tw\r\u0005\u0002\u000e?%\u0011\u0001E\u0004\u0002\u0004\u0003:L\bCA\f#\t\u0015\u0019\u0003A1\u0001\u001b\u0005\u0005Y\u0005\u0003B\u0013)U5j\u0011A\n\u0006\u0003O\u001d\tq!\u00197hK\n\u0014\u0018-\u0003\u0002*M\t\tbj\u001c:nK\u00124Vm\u0019;peN\u0003\u0018mY3\u0011\u0007MYc#\u0003\u0002-\u000b\t!A)[:u!\r\u00192&I\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003A\u0002\"!D\u0019\n\u0005Ir!\u0001B+oSR\f1!\u00197h+\u0005)\u0004\u0003B\u0013)-\u0005\nAA\\8s[R\u0011Q\u0006\u000f\u0005\u0006s\r\u0001\rAK\u0001\u0002m\u0002"
)
public interface DistNormedVectorSpace extends DistVectorSpace, NormedVectorSpace {
   NormedVectorSpace alg();

   // $FF: synthetic method
   static Dist norm$(final DistNormedVectorSpace $this, final Dist v) {
      return $this.norm(v);
   }

   default Dist norm(final Dist v) {
      NormedVectorSpace var2 = this.alg();
      return v.map((vx) -> var2.norm(vx));
   }

   static void $init$(final DistNormedVectorSpace $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
