package spire.syntax.std;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t2qa\u0001\u0003\u0011\u0002\u0007\u00051\u0002C\u0003\u0013\u0001\u0011\u00051\u0003C\u0003\u0018\u0001\u0011\r\u0001D\u0001\u0006M_:<7+\u001f8uCbT!!\u0002\u0004\u0002\u0007M$HM\u0003\u0002\b\u0011\u000511/\u001f8uCbT\u0011!C\u0001\u0006gBL'/Z\u0002\u0001'\t\u0001A\u0002\u0005\u0002\u000e!5\taBC\u0001\u0010\u0003\u0015\u00198-\u00197b\u0013\t\tbB\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003Q\u0001\"!D\u000b\n\u0005Yq!\u0001B+oSR\fa\u0002\\5uKJ\fG\u000eT8oO>\u00038\u000f\u0006\u0002\u001a;A\u0011!dG\u0007\u0002\t%\u0011A\u0004\u0002\u0002\u000f\u0019&$XM]1m\u0019>twm\u00149t\u0011\u0015q\"\u00011\u0001 \u0003\u0005q\u0007CA\u0007!\u0013\t\tcB\u0001\u0003M_:<\u0007"
)
public interface LongSyntax {
   // $FF: synthetic method
   static long literalLongOps$(final LongSyntax $this, final long n) {
      return $this.literalLongOps(n);
   }

   default long literalLongOps(final long n) {
      return n;
   }

   static void $init$(final LongSyntax $this) {
   }
}
