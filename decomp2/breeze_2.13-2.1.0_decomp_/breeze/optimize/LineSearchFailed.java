package breeze.optimize;

import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005q1A\u0001B\u0003\u0001\u0015!Aq\u0002\u0001B\u0001B\u0003%\u0001\u0003\u0003\u0005\u0017\u0001\t\u0005\t\u0015!\u0003\u0011\u0011\u00159\u0002\u0001\"\u0001\u0019\u0005Aa\u0015N\\3TK\u0006\u00148\r\u001b$bS2,GM\u0003\u0002\u0007\u000f\u0005Aq\u000e\u001d;j[&TXMC\u0001\t\u0003\u0019\u0011'/Z3{K\u000e\u00011C\u0001\u0001\f!\taQ\"D\u0001\u0006\u0013\tqQAA\nGSJ\u001cHo\u0014:eKJ,\u0005pY3qi&|g.\u0001\u0005he\u0006$gj\u001c:n!\t\tB#D\u0001\u0013\u0015\u0005\u0019\u0012!B:dC2\f\u0017BA\u000b\u0013\u0005\u0019!u.\u001e2mK\u00069A-\u001b:O_Jl\u0017A\u0002\u001fj]&$h\bF\u0002\u001a5m\u0001\"\u0001\u0004\u0001\t\u000b=\u0019\u0001\u0019\u0001\t\t\u000bY\u0019\u0001\u0019\u0001\t"
)
public class LineSearchFailed extends FirstOrderException {
   public LineSearchFailed(final double gradNorm, final double dirNorm) {
      super(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Grad norm: %.4f Dir Norm: %.4f"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble(gradNorm), BoxesRunTime.boxToDouble(dirNorm)})));
   }
}
