package breeze.integrate.quasimontecarlo;

import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005);Q!\u0003\u0006\t\u0002E1Qa\u0005\u0006\t\u0002QAQaG\u0001\u0005\u0002qAq!H\u0001C\u0002\u0013\u0005a\u0004\u0003\u0004#\u0003\u0001\u0006Ia\b\u0005\u0006G\u0005!I\u0001\n\u0005\tk\u0005A)\u0019!C\u0001m!Aq'\u0001EC\u0002\u0013\u0005a\u0007C\u0003\u000e\u0003\u0011\u0005\u0001(\u0001\u0004IC2$xN\u001c\u0006\u0003\u00171\tq\"];bg&lwN\u001c;fG\u0006\u0014Hn\u001c\u0006\u0003\u001b9\t\u0011\"\u001b8uK\u001e\u0014\u0018\r^3\u000b\u0003=\taA\u0019:fKj,7\u0001\u0001\t\u0003%\u0005i\u0011A\u0003\u0002\u0007\u0011\u0006dGo\u001c8\u0014\u0005\u0005)\u0002C\u0001\f\u001a\u001b\u00059\"\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005i9\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002#\u0005!\u0002*\u0011'U\u001f:{V*\u0011-`\t&kUIT*J\u001f:+\u0012a\b\t\u0003-\u0001J!!I\f\u0003\u0007%sG/A\u000bI\u00032#vJT0N\u0003b{F)S'F\u001dNKuJ\u0014\u0011\u00027I,\u0017\rZ\"mCN\u001c\b/\u0019;i\r&dW\rV8J]R\f%O]1z)\t)\u0003\u0006E\u0002\u0017M}I!aJ\f\u0003\u000b\u0005\u0013(/Y=\t\u000b%*\u0001\u0019\u0001\u0016\u0002\u0011\u0019LG.\u001a8b[\u0016\u0004\"a\u000b\u001a\u000f\u00051\u0002\u0004CA\u0017\u0018\u001b\u0005q#BA\u0018\u0011\u0003\u0019a$o\\8u}%\u0011\u0011gF\u0001\u0007!J,G-\u001a4\n\u0005M\"$AB*ue&twM\u0003\u00022/\u00051\u0001KU%N\u000bN+\u0012!J\u0001\t\u000b\u0006{\u0006+\u0012*N'R\u0011\u0011\b\u0012\u000b\u0004uuz\u0004C\u0001\f<\u0013\tatC\u0001\u0004E_V\u0014G.\u001a\u0005\u0006}!\u0001\raH\u0001\nI&lWM\\:j_:DQ\u0001\u0011\u0005A\u0002\u0005\u000b!B\\;n'\u0006l\u0007\u000f\\3t!\t1\")\u0003\u0002D/\t!Aj\u001c8h\u0011\u0015)\u0005\u00021\u0001G\u0003\u00111WO\\2\u0011\tY9\u0015JO\u0005\u0003\u0011^\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0007Y1#\b"
)
public final class Halton {
   public static double integrate(final Function1 func, final int dimension, final long numSamples) {
      return Halton$.MODULE$.integrate(func, dimension, numSamples);
   }

   public static int[] EA_PERMS() {
      return Halton$.MODULE$.EA_PERMS();
   }

   public static int[] PRIMES() {
      return Halton$.MODULE$.PRIMES();
   }

   public static int HALTON_MAX_DIMENSION() {
      return Halton$.MODULE$.HALTON_MAX_DIMENSION();
   }
}
