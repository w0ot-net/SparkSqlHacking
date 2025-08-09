package spire.syntax;

import scala.reflect.ScalaSignature;
import spire.algebra.IsReal;

@ScalaSignature(
   bytes = "\u0006\u0005U2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!D\u0001\u0007JgJ+\u0017\r\\*z]R\f\u0007P\u0003\u0002\u0006\r\u000511/\u001f8uCbT\u0011aB\u0001\u0006gBL'/Z\u0002\u0001'\r\u0001!\u0002\u0005\t\u0003\u00179i\u0011\u0001\u0004\u0006\u0002\u001b\u0005)1oY1mC&\u0011q\u0002\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005E\u0011R\"\u0001\u0003\n\u0005M!!\u0001D*jO:,GmU=oi\u0006D\u0018A\u0002\u0013j]&$H\u0005F\u0001\u0017!\tYq#\u0003\u0002\u0019\u0019\t!QK\\5u\u0003%I7OU3bY>\u00038/\u0006\u0002\u001cEQ\u0011Ad\r\u000b\u0003;-\u00022!\u0005\u0010!\u0013\tyBAA\u0005JgJ+\u0017\r\\(qgB\u0011\u0011E\t\u0007\u0001\t\u0015\u0019#A1\u0001%\u0005\u0005\t\u0015CA\u0013)!\tYa%\u0003\u0002(\u0019\t9aj\u001c;iS:<\u0007CA\u0006*\u0013\tQCBA\u0002B]fDq\u0001\f\u0002\u0002\u0002\u0003\u000fQ&\u0001\u0006fm&$WM\\2fI]\u00022AL\u0019!\u001b\u0005y#B\u0001\u0019\u0007\u0003\u001d\tGnZ3ce\u0006L!AM\u0018\u0003\r%\u001b(+Z1m\u0011\u0015!$\u00011\u0001!\u0003\u0005\t\u0007"
)
public interface IsRealSyntax extends SignedSyntax {
   // $FF: synthetic method
   static IsRealOps isRealOps$(final IsRealSyntax $this, final Object a, final IsReal evidence$7) {
      return $this.isRealOps(a, evidence$7);
   }

   default IsRealOps isRealOps(final Object a, final IsReal evidence$7) {
      return new IsRealOps(a, evidence$7);
   }

   static void $init$(final IsRealSyntax $this) {
   }
}
