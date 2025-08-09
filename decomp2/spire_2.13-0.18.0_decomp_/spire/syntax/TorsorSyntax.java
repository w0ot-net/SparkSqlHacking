package spire.syntax;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raC\u0001\u0007U_J\u001cxN]*z]R\f\u0007P\u0003\u0002\u0006\r\u000511/\u001f8uCbT\u0011aB\u0001\u0006gBL'/Z\u0002\u0001'\t\u0001!\u0002\u0005\u0002\f\u001d5\tABC\u0001\u000e\u0003\u0015\u00198-\u00197b\u0013\tyAB\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003I\u0001\"aC\n\n\u0005Qa!\u0001B+oSR\fa\u0002^8sg>\u0014\bk\\5oi>\u00038/\u0006\u0002\u0018=Q\u0011\u0001d\n\t\u00043iaR\"\u0001\u0003\n\u0005m!!A\u0004+peN|'\u000fU8j]R|\u0005o\u001d\t\u0003;ya\u0001\u0001B\u0003 \u0005\t\u0007\u0001EA\u0001Q#\t\tC\u0005\u0005\u0002\fE%\u00111\u0005\u0004\u0002\b\u001d>$\b.\u001b8h!\tYQ%\u0003\u0002'\u0019\t\u0019\u0011I\\=\t\u000b!\u0012\u0001\u0019\u0001\u000f\u0002\u0003A\u0004"
)
public interface TorsorSyntax {
   // $FF: synthetic method
   static TorsorPointOps torsorPointOps$(final TorsorSyntax $this, final Object p) {
      return $this.torsorPointOps(p);
   }

   default TorsorPointOps torsorPointOps(final Object p) {
      return new TorsorPointOps(p);
   }

   static void $init$(final TorsorSyntax $this) {
   }
}
