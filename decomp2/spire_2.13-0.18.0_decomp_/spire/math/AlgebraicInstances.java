package spire.math;

import algebra.ring.Field;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0012\u0001\u0011\u0005!\u0003C\u0004\u0017\u0001\t\u0007IqA\f\t\u000fe\u0002!\u0019!C\u0004u\t\u0011\u0012\t\\4fEJ\f\u0017nY%ogR\fgnY3t\u0015\t1q!\u0001\u0003nCRD'\"\u0001\u0005\u0002\u000bM\u0004\u0018N]3\u0004\u0001M\u0011\u0001a\u0003\t\u0003\u0019=i\u0011!\u0004\u0006\u0002\u001d\u0005)1oY1mC&\u0011\u0001#\u0004\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005\u0019\u0002C\u0001\u0007\u0015\u0013\t)RB\u0001\u0003V]&$\u0018\u0001E!mO\u0016\u0014'/Y5d\u00032<WM\u0019:b+\u0005A\"#B\r\u001c_M2d\u0001\u0002\u000e\u0001\u0001a\u0011A\u0002\u0010:fM&tW-\\3oiz\u00022\u0001\b\u0015,\u001d\tiRE\u0004\u0002\u001fG9\u0011qDI\u0007\u0002A)\u0011\u0011%C\u0001\u0007yI|w\u000e\u001e \n\u0003!I!\u0001J\u0004\u0002\u000f\u0005dw-\u001a2sC&\u0011aeJ\u0001\ba\u0006\u001c7.Y4f\u0015\t!s!\u0003\u0002*U\t)a)[3mI*\u0011ae\n\t\u0003Y5j\u0011!B\u0005\u0003]\u0015\u0011\u0011\"\u00117hK\n\u0014\u0018-[2\u0011\u0007A\n4&D\u0001(\u0013\t\u0011tEA\u0003O%>|G\u000fE\u00021i-J!!N\u0014\u0003\u0017%\u001b\u0018\t\\4fEJ\f\u0017n\u0019\t\u00049]Z\u0013B\u0001\u001d+\u0005Y!&/\u001e8dCR,G\rR5wSNLwN\\\"SS:<\u0017\u0001D!mO\u0016\u0014'/Y5d)\u0006<W#A\u001e\u0011\u00071b4&\u0003\u0002>\u000b\tIa*^7cKJ$\u0016m\u001a"
)
public interface AlgebraicInstances {
   void spire$math$AlgebraicInstances$_setter_$AlgebraicAlgebra_$eq(final Field x$1);

   void spire$math$AlgebraicInstances$_setter_$AlgebraicTag_$eq(final NumberTag x$1);

   Field AlgebraicAlgebra();

   NumberTag AlgebraicTag();

   static void $init$(final AlgebraicInstances $this) {
      $this.spire$math$AlgebraicInstances$_setter_$AlgebraicAlgebra_$eq(new AlgebraicAlgebra());
      $this.spire$math$AlgebraicInstances$_setter_$AlgebraicTag_$eq(new NumberTag.LargeTag(NumberTag.Exact$.MODULE$, Algebraic$.MODULE$.apply(0)));
   }
}
