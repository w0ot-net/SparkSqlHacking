package spire.syntax;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0011\rQDA\tWK\u000e$xN]*qC\u000e,7+\u001f8uCbT!!\u0002\u0004\u0002\rMLh\u000e^1y\u0015\u00059\u0011!B:qSJ,7\u0001A\n\u0005\u0001)\u0001B\u0003\u0005\u0002\f\u001d5\tABC\u0001\u000e\u0003\u0015\u00198-\u00197b\u0013\tyAB\u0001\u0004B]f\u0014VM\u001a\t\u0003#Ii\u0011\u0001B\u0005\u0003'\u0011\u0011QbQ'pIVdWmU=oi\u0006D\bCA\t\u0016\u0013\t1BAA\u0006GS\u0016dGmU=oi\u0006D\u0018A\u0002\u0013j]&$H\u0005F\u0001\u001a!\tY!$\u0003\u0002\u001c\u0019\t!QK\\5u\u000391Xm\u0019;peN\u0003\u0018mY3PaN,\"A\b\u0013\u0015\u0005}i\u0003cA\t!E%\u0011\u0011\u0005\u0002\u0002\u000f-\u0016\u001cGo\u001c:Ta\u0006\u001cWm\u00149t!\t\u0019C\u0005\u0004\u0001\u0005\u000b\u0015\u0012!\u0019\u0001\u0014\u0003\u0003Y\u000b\"a\n\u0016\u0011\u0005-A\u0013BA\u0015\r\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aC\u0016\n\u00051b!aA!os\")aF\u0001a\u0001E\u0005\ta\u000f"
)
public interface VectorSpaceSyntax extends CModuleSyntax, FieldSyntax {
   // $FF: synthetic method
   static VectorSpaceOps vectorSpaceOps$(final VectorSpaceSyntax $this, final Object v) {
      return $this.vectorSpaceOps(v);
   }

   default VectorSpaceOps vectorSpaceOps(final Object v) {
      return new VectorSpaceOps(v);
   }

   static void $init$(final VectorSpaceSyntax $this) {
   }
}
