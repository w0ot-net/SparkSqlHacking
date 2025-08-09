package spire.syntax;

import algebra.ring.AdditiveMonoid;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!D\u0001\u000bBI\u0012LG/\u001b<f\u001b>tw.\u001b3Ts:$\u0018\r\u001f\u0006\u0003\u000b\u0019\taa]=oi\u0006D(\"A\u0004\u0002\u000bM\u0004\u0018N]3\u0004\u0001M\u0019\u0001A\u0003\t\u0011\u0005-qQ\"\u0001\u0007\u000b\u00035\tQa]2bY\u0006L!a\u0004\u0007\u0003\r\u0005s\u0017PU3g!\t\t\"#D\u0001\u0005\u0013\t\u0019BAA\fBI\u0012LG/\u001b<f'\u0016l\u0017n\u001a:pkB\u001c\u0016P\u001c;bq\u00061A%\u001b8ji\u0012\"\u0012A\u0006\t\u0003\u0017]I!\u0001\u0007\u0007\u0003\tUs\u0017\u000e^\u0001\u0012C\u0012$\u0017\u000e^5wK6{gn\\5e\u001fB\u001cXCA\u000e#)\taR\b\u0006\u0002\u001eWA\u0019\u0011C\b\u0011\n\u0005}!!!E!eI&$\u0018N^3N_:|\u0017\u000eZ(qgB\u0011\u0011E\t\u0007\u0001\t\u0015\u0019#A1\u0001%\u0005\u0005\t\u0015CA\u0013)!\tYa%\u0003\u0002(\u0019\t9aj\u001c;iS:<\u0007CA\u0006*\u0013\tQCBA\u0002B]fDQ\u0001\f\u0002A\u00045\n!!\u001a<\u0011\u00079R\u0004E\u0004\u00020o9\u0011\u0001'\u000e\b\u0003cQj\u0011A\r\u0006\u0003g!\ta\u0001\u0010:p_Rt\u0014\"A\u0004\n\u0005Y2\u0011aB1mO\u0016\u0014'/Y\u0005\u0003qe\nq\u0001]1dW\u0006<WM\u0003\u00027\r%\u00111\b\u0010\u0002\u000f\u0003\u0012$\u0017\u000e^5wK6{gn\\5e\u0015\tA\u0014\bC\u0003?\u0005\u0001\u0007\u0001%A\u0001b\u0001"
)
public interface AdditiveMonoidSyntax extends AdditiveSemigroupSyntax {
   // $FF: synthetic method
   static AdditiveMonoidOps additiveMonoidOps$(final AdditiveMonoidSyntax $this, final Object a, final AdditiveMonoid ev) {
      return $this.additiveMonoidOps(a, ev);
   }

   default AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
      return new AdditiveMonoidOps(a, ev);
   }

   static void $init$(final AdditiveMonoidSyntax $this) {
   }
}
