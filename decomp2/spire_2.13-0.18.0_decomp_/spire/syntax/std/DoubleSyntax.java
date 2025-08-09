package spire.syntax.std;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t2qa\u0001\u0003\u0011\u0002\u0007\u00051\u0002C\u0003\u0013\u0001\u0011\u00051\u0003C\u0003\u0018\u0001\u0011\r\u0001D\u0001\u0007E_V\u0014G.Z*z]R\f\u0007P\u0003\u0002\u0006\r\u0005\u00191\u000f\u001e3\u000b\u0005\u001dA\u0011AB:z]R\f\u0007PC\u0001\n\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0019\"\u0001\u0001\u0007\u0011\u00055\u0001R\"\u0001\b\u000b\u0003=\tQa]2bY\u0006L!!\u0005\b\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\tA\u0003\u0005\u0002\u000e+%\u0011aC\u0004\u0002\u0005+:LG/\u0001\tmSR,'/\u00197E_V\u0014G.Z(qgR\u0011\u0011$\b\t\u00035mi\u0011\u0001B\u0005\u00039\u0011\u0011\u0001\u0003T5uKJ\fG\u000eR8vE2,w\n]:\t\u000by\u0011\u0001\u0019A\u0010\u0002\u00039\u0004\"!\u0004\u0011\n\u0005\u0005r!A\u0002#pk\ndW\r"
)
public interface DoubleSyntax {
   // $FF: synthetic method
   static double literalDoubleOps$(final DoubleSyntax $this, final double n) {
      return $this.literalDoubleOps(n);
   }

   default double literalDoubleOps(final double n) {
      return n;
   }

   static void $init$(final DoubleSyntax $this) {
   }
}
