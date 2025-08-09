package spire.syntax;

import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!D\u0001\nQCJ$\u0018.\u00197Pe\u0012,'oU=oi\u0006D(BA\u0003\u0007\u0003\u0019\u0019\u0018P\u001c;bq*\tq!A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0007\u0001Q\u0001\u0003\u0005\u0002\f\u001d5\tABC\u0001\u000e\u0003\u0015\u00198-\u00197b\u0013\tyAB\u0001\u0004B]f\u0014VM\u001a\t\u0003#Ii\u0011\u0001B\u0005\u0003'\u0011\u0011\u0001\"R9Ts:$\u0018\r_\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003Y\u0001\"aC\f\n\u0005aa!\u0001B+oSR\fq\u0002]1si&\fGn\u0014:eKJ|\u0005o]\u000b\u00037\t\"\"\u0001H\u001f\u0015\u0005uY\u0003cA\t\u001fA%\u0011q\u0004\u0002\u0002\u0010!\u0006\u0014H/[1m\u001fJ$WM](qgB\u0011\u0011E\t\u0007\u0001\t\u0015\u0019#A1\u0001%\u0005\u0005\t\u0015CA\u0013)!\tYa%\u0003\u0002(\u0019\t9aj\u001c;iS:<\u0007CA\u0006*\u0013\tQCBA\u0002B]fDq\u0001\f\u0002\u0002\u0002\u0003\u000fQ&\u0001\u0006fm&$WM\\2fII\u00022A\f\u001e!\u001d\tysG\u0004\u00021k9\u0011\u0011\u0007N\u0007\u0002e)\u00111\u0007C\u0001\u0007yI|w\u000e\u001e \n\u0003\u001dI!A\u000e\u0004\u0002\u000f\u0005dw-\u001a2sC&\u0011\u0001(O\u0001\ba\u0006\u001c7.Y4f\u0015\t1d!\u0003\u0002<y\ta\u0001+\u0019:uS\u0006dwJ\u001d3fe*\u0011\u0001(\u000f\u0005\u0006}\t\u0001\r\u0001I\u0001\u0002C\u0002"
)
public interface PartialOrderSyntax extends EqSyntax {
   // $FF: synthetic method
   static PartialOrderOps partialOrderOps$(final PartialOrderSyntax $this, final Object a, final PartialOrder evidence$2) {
      return $this.partialOrderOps(a, evidence$2);
   }

   default PartialOrderOps partialOrderOps(final Object a, final PartialOrder evidence$2) {
      return new PartialOrderOps(a, evidence$2);
   }

   static void $init$(final PartialOrderSyntax $this) {
   }
}
